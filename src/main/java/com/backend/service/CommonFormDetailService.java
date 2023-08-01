package com.backend.service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.*;
import com.backend.repository.postgres.*;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class CommonFormDetailService {

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private ProjectDetailRepository projectDetailRepository;

    @Autowired
    private CafLocationKmlRepository cafLocationKmlRepository;

    @Autowired
    private CafProjectActivityCostRepository cafProjectActivityCostRepository;

    @Autowired
    private CafOthersRepository cafOthersRepository;

    @Autowired
    private CafKMLRepository cafKMLRepository;

    @Autowired
    private CAFKMLPlotsRepository cafKMLPlotsRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public ResponseEntity<Object> saveProjectDetails(Integer id, CommonFormDetail commonFormDetail, Integer user_id) {
        ProjectDetails details = projectDetailRepository.findProjectById(id, user_id)
                .orElseThrow(() -> new ProjectNotFoundException("project not found with ID"));
        commonFormDetail.setProjectDetails(details);
        if (commonFormDetail.getId() != null && commonFormDetail.getId() != 0) {
            CommonFormDetail form = commonFormDetailRepository.findDetailByCafId(commonFormDetail.getId());
            commonFormDetail.setCaf_id(form.getCaf_id());
            commonFormDetail.setCaf_id_sequence(form.getCaf_id_sequence());
            return ResponseHandler.generateResponse("Save Common Form ", HttpStatus.OK, "",
                    commonFormDetailRepository.save(commonFormDetail));
            // return commonFormDetailRepository.save(commonFormDetail);
        } else {
            commonFormDetail.setCaf_status(Caf_Status.DRAFT);
        }
//		List<CommonFormDetail> temp = commonFormDetailRepository.findAll();
        Long temp = commonFormDetailRepository.getCount();
        if (temp == null) {
            String cafId = "CAF/" + 100000 + "/" + String.valueOf(LocalDate.now().getYear());
            commonFormDetail.setCaf_id_sequence(100000);
            commonFormDetail.setCaf_id(cafId);
        } else {
//			Integer maxCount = temp.stream().map(e -> e.getCaf_id_sequence())
//					.max(Comparator.comparing(Integer::valueOf)).get();
            Integer maxCount = commonFormDetailRepository.getMaxCafSequence();
            if (maxCount != null) {
                commonFormDetail.setCaf_id_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                commonFormDetail.setCaf_id(generateCafId(maxCount));
            }
        }
        return ResponseHandler.generateResponse("Save Common Form ", HttpStatus.OK, "",
                commonFormDetailRepository.save(commonFormDetail));
        // return commonFormDetailRepository.save(commonFormDetail);
    }

    public ResponseEntity<Object> saveCafLocationDetails(Integer id, CafLocationOfKml cafLocationOfKml) {
        CommonFormDetail temp = commonFormDetailRepository.findDetailByCafId(id);
        cafLocationOfKml.setCommonFormDetail(temp);
        CafLocationOfKml updated = cafLocationKmlRepository.save(cafLocationOfKml);
        return ResponseHandler.generateResponse("Common Location Details ", HttpStatus.OK, "",
                commonFormDetailRepository.findDetailByCafId(id));
        // return commonFormDetailRepository.findDetailByCafId(id);
    }

    public ResponseEntity<Object> saveCafProjectActivityCost(Integer id,
                                                             CafProjectActivityCost cafProjectActivityCost) {
        CommonFormDetail temp = commonFormDetailRepository.findDetailByCafId(id);
        cafProjectActivityCost.setCommonFormDetail(temp);
        cafProjectActivityCostRepository.save(cafProjectActivityCost);
        return ResponseHandler.generateResponse("Common Project Activity Cost", HttpStatus.OK, "",
                commonFormDetailRepository.findDetailByCafId(id));
        // return commonFormDetailRepository.findDetailByCafId(id);
    }

    public ResponseEntity<Object> saveCafOthers(Integer id, CafOthers cafOthers) {
        CommonFormDetail temp = commonFormDetailRepository.findDetailByCafId(id);
        cafOthers.setCommonFormDetail(temp);
        temp.setCaf_status(Caf_Status.COMPLETED);
        commonFormDetailRepository.save(temp);
        if (cafOthers.getAlternative_site_order_copy() != null) {
            cafOthers.getAlternative_site_order_copy().setProposal_no(temp.getCaf_id());
        }
        if (!cafOthers.getCafGovernmentOrders().isEmpty()) {
            cafOthers.getCafGovernmentOrders().forEach(obj -> {
                if (obj.getGovernment_order_copy() != null) {
                    obj.getGovernment_order_copy().setProposal_no(temp.getCaf_id());
                }
            });
        }
        if (!cafOthers.getCafLitigations().isEmpty()) {
            cafOthers.getCafLitigations().forEach(obj -> {
                if (obj.getLitigation_order_copy() != null) {
                    obj.getLitigation_order_copy().setProposal_no(temp.getCaf_id());
                }
            });
        }
        if (!cafOthers.getCafViolations().isEmpty()) {
            cafOthers.getCafViolations().forEach(obj -> {
                if (obj.getViolation_direction_copy() != null) {
                    obj.getViolation_direction_copy().setProposal_no(temp.getCaf_id());
                }
                if (obj.getViolation_report() != null) {
                    obj.getViolation_report().setProposal_no(temp.getCaf_id());
                }
            });
        }
        CafOthers cafOthers2 = cafOthersRepository.save(cafOthers);
        return ResponseHandler.generateResponse("CAF Others", HttpStatus.OK, "", cafOthers2);
        // return commonFormDetailRepository.findDetailByCafId(id);
    }

    public ResponseEntity<Object> saveCafKML1(Integer id, CafKML cafKML) {
        CommonFormDetail temp = commonFormDetailRepository.findDetailByCafId(id);
        cafKML.setCommonFormDetail(temp);
        cafKMLRepository.save(cafKML);
        return ResponseHandler.generateResponse("Save CAF KML", HttpStatus.OK, "", "SUCCESS");
        // return "SUCCESS";
    }

    @Transactional
    public ResponseEntity<Object> saveCafKML(Integer id, List<CafKML> cafKML) {
        CommonFormDetail temp = commonFormDetailRepository.findDetailByCafId(id);
        List<CafKML> cafKMLs = cafKML.stream().map(value -> {
            value.setCommonFormDetail(temp);
            if (value.getCaf_kml() != null) {
                value.getCaf_kml().setProposal_no(temp.getCaf_id());
            }
            return value;
        }).collect(Collectors.toList());
        List<CafKML> temp1 = cafKMLRepository.saveAll(cafKMLs);
        return ResponseHandler.generateResponse("Save CAF KML", HttpStatus.OK, "", temp1);
        // return "SUCCESS";
    }

    public ResponseEntity<Object> deleteCafKML(Integer id) {
        CafKML kml = cafKMLRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("CAF Kml not found with id"));
        kml.setIs_active(false);
        kml.setIs_deleted(true);
        return ResponseHandler.generateResponse("Save CAF KML", HttpStatus.OK, "", cafKMLRepository.save(kml));
        // return "SUCCESS";
    }

    public ResponseEntity<Object> getCafKML(Integer id) {
        return ResponseHandler.generateResponse("Get CAF KML", HttpStatus.OK, "", cafKMLRepository.getById(id));
        // return cafKMLRepository.getById(id);
    }

    public ResponseEntity<Object> getCafKMLbyCAFId(Integer id) {
        List<CafKML> cafKML = cafKMLRepository.findcafKMLbyCafID(id);
        return ResponseHandler.generateResponse("Get CAF KML", HttpStatus.OK, "", cafKML);
        // return cafKML;
    }

    public ResponseEntity<Object> saveCafKMLPlot(Integer id, CAFKMLPlots cafKMLPlots) {
        CafKML temp = cafKMLRepository.getById(id);
//		cafKMLPlots.setCafKML(temp);
        cafKMLPlotsRepository.save(cafKMLPlots);
        return ResponseHandler.generateResponse("Save CAF KML Plot", HttpStatus.OK, "", "SUCCESS");
        // return "SUCCESS";
    }

    public ResponseEntity<Object> getCafKMLPlot(Integer id) {
        return ResponseHandler.generateResponse("CAF Others", HttpStatus.OK, "", cafKMLPlotsRepository.getById(id));
        // return cafKMLPlotsRepository.getById(id);
    }

    public ResponseEntity<Object> getCafDetails(Integer id) {
        CommonFormDetail commonFormDetail = commonFormDetailRepository.findDetailByCafId(id);
        if (commonFormDetail != null) {
            commonFormDetail
                    .setOrganisationState(stateRepository.getStateByCode(commonFormDetail.getOrganization_state())
                            .orElseThrow(() -> new ProjectNotFoundException("state not found with code")));
            commonFormDetail.getOrganisationState().setDistrictlist(new ArrayList<>());
            commonFormDetail.setOrganisationDistrict(districtRepository.getDistrictByCode(
                    commonFormDetail.getOrganization_state(), commonFormDetail.getOrganization_district()));
            commonFormDetail.setApplicantState(stateRepository.getStateByCode(commonFormDetail.getApplicant_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")));
            commonFormDetail.getApplicantState().setDistrictlist(new ArrayList<>());
            commonFormDetail.setApplicantDistrict(districtRepository.getDistrictByCode(
                    commonFormDetail.getApplicant_state(), commonFormDetail.getApplicant_district()));
        }
        return ResponseHandler.generateResponse("CAF Others", HttpStatus.OK, "", commonFormDetail);
        // return commonFormDetail;
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateCafId(int maxcount) {
        String cafId = "CAF/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
        return cafId;
    }

    public ResponseEntity<Object> getCafDetailsByProjectId(Integer project_id) {
        List<CommonFormDetail> commonFormDetail = commonFormDetailRepository.findDetailByProjectId(project_id);
        if (commonFormDetail != null) {
            commonFormDetail.forEach(value -> {
                value.setOrganisationState(stateRepository.getStateByCode(value.getOrganization_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")));
                value.getOrganisationState().setDistrictlist(new ArrayList<>());
                value.setOrganisationDistrict(districtRepository.getDistrictByCode(value.getOrganization_state(),
                        value.getOrganization_district()));
                value.setApplicantState(stateRepository.getStateByCode(value.getApplicant_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")));
                value.getApplicantState().setDistrictlist(new ArrayList<>());
                value.setApplicantDistrict(districtRepository.getDistrictByCode(value.getApplicant_state(),
                        value.getApplicant_district()));
            });
        }
        return ResponseHandler.generateResponse("CAF Others", HttpStatus.OK, "", commonFormDetail);
        // return commonFormDetail;
    }

    public void saveCommonForm(CommonFormDetail commonFormDetail) {
        commonFormDetailRepository.updateStatus(commonFormDetail.getId(), Caf_Status.SUBMITTED);
    }

    public ResponseEntity<Object> getFilledStatus(Integer caf_id, Integer clearance_id) {
        Boolean formFilled = false;
        Boolean cafFormCompleted = false;
        List<ProponentApplications> applications = proponentApplicationRepository.findAll();
        for (ProponentApplications value : applications) {
            if (value.getCaf_id().equals(caf_id) && value.getClearance_id().equals(clearance_id)) {
                formFilled = true;
            }
        }
        CommonFormDetail commonFormDetail = commonFormDetailRepository.findById(caf_id)
                .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
        if (commonFormDetail.getCaf_status().name().equals(Caf_Status.COMPLETED.name())
                || commonFormDetail.getCaf_status().name().equals(Caf_Status.SUBMITTED.name())) {
            cafFormCompleted = true;
        }

        Map<String, Object> formFilledStatus = new HashMap<>();
        formFilledStatus.put("is_form_filled_caf", formFilled);
        formFilledStatus.put("is_caf_completed", cafFormCompleted);
        return ResponseHandler.generateResponse("Is Form Already submitted for this cafId", HttpStatus.OK, "",
                formFilledStatus);
    }

    public ResponseEntity<Object> copyCafDetails(Integer id, String amendment_form) {
        try {
            Integer newCafId = commonFormDetailRepository.copyCafDetails(id);
            commonFormDetailRepository.updateAmendmentForm(amendment_form, newCafId);
            return ResponseHandler.generateResponse("copyCafDetails updated", HttpStatus.OK, "", newCafId);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in copyCafDetails" + id, e);
        }
    }
}