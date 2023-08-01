package com.backend.service.Crz;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.OfficeHeirarchyDto;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.Crz.*;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.Crz.*;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.UpdateOtherPropertyService;
import com.backend.util.ServerUtil;

import lombok.extern.slf4j.Slf4j;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CrzService {

    @Autowired
    private CrzProjectDetailsRepository crzProjectDetailsRepository;

    @Autowired
    private CrzBasicDetailsRepository crzBasicDetailsRepository;

    @Autowired
    private CrzOtherDetailsRepository crzOtherDetailsRepository;

    @Autowired
    private CrzUndertakingRepository crzUndertakingRepository;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private CrzFacilityStorageGoodsRepository crzFacilityStorageGoodsRepository;

    @Autowired
    private CrzOtherEffluentRepository crzOtherEffluentRepository;

    @Autowired
    private CrzSolidWasteRepository crzSolidWasteRepository;

    @Autowired
    private CrzWaterRequirementRepository crzWaterRequirementRepository;

    @Autowired
    private CrzDisposalRepository crzDisposalRepository;
    
    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;

    @Autowired
    private ServerUtil util;
    @Autowired
    private NotificationService notificationSevice;

    public ResponseEntity<Object> saveCRZBasicDetails(CrzBasicDetails crzBasicDetails, Integer caf_id,
                                                      HttpServletRequest request) throws PariveshException {
        CommonFormDetail temp = null;
        CrzBasicDetails temp2 = null;
        try {
            temp = new CommonFormDetail();
            temp = commonFormDetailRepository.findDetailByCafId(caf_id);
            crzBasicDetails.setCommonFormDetail(temp);
            if (crzBasicDetails.getId() != null && crzBasicDetails.getId() != 0) {
                CrzBasicDetails form = crzBasicDetailsRepository.findById(crzBasicDetails.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("CRZ clearance form not found"));
                crzBasicDetails.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));

                return ResponseHandler.generateResponse("Save crz Basic Details form", HttpStatus.OK, null,
                        crzBasicDetailsRepository.save(crzBasicDetails));
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
            ProponentApplications applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {
                String proposal_no = "IA/" + stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/" + "CRZ" + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                crzBasicDetails.setProposal_no(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, temp));
                    crzBasicDetails.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }
            temp2 = new CrzBasicDetails();
            temp2 = crzBasicDetailsRepository.save(crzBasicDetails);
            Applications app = applicationsRepository.findById(4).get();
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

            /*
             * applications.setState_id(stateRepository.getStateByCode(temp.
             * getProjectDetails().getMain_state()) .orElseThrow(() -> new
             * ProjectNotFoundException("state not found with code")).getId());
             */

            applications.setState_id(stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());

            applications.setProjectDetails(temp.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            proponentApplicationRepository.save(applications);
        } catch (Exception e) {
            // log.error(e.toString() + " " + e.getStackTrace()[0]);
            return ResponseHandler.generateResponse("------------------->>>>", HttpStatus.INTERNAL_SERVER_ERROR, "", e);


        }
        return ResponseHandler.generateResponse("Save crz Basic Details form", HttpStatus.OK, null, temp2);
    }

    public ResponseEntity<Object> saveProjectDetails(CrzProjectDetails crzProjectDetails, Integer crz_id)
            throws PariveshException {

        try {

            CrzBasicDetails crzBasicdetails = crzBasicDetailsRepository.findById(crz_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Crz form not found"));
            crzProjectDetails.setCrzBasicDetails(crzBasicdetails);
            if (crzProjectDetails.getCopy_of_noc() != null) {
                crzProjectDetails.getCopy_of_noc().setProposal_no(crzBasicdetails.getProposal_no());
            }
        } catch (Exception e) {
//			log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving CRZ Project Details for crz_id-" + crz_id);
        }
        return ResponseHandler.generateResponse("Save crz project Details form", HttpStatus.OK, null,
                crzProjectDetailsRepository.save(crzProjectDetails));
    }

    public ResponseEntity<Object> saveOtherDetails(CrzOtherDetails crzOtherDetails, Integer crz_id)
            throws PariveshException {
        try {

            CrzBasicDetails crzBasicdetails = crzBasicDetailsRepository.findById(crz_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Crz form not found"));
            crzOtherDetails.setCrzBasicDetails(crzBasicdetails);
            if (crzOtherDetails.getUpload_eias() != null) {
                crzOtherDetails.getUpload_eias().setProposal_no(crzBasicdetails.getProposal_no());
            }

        } catch (Exception e) {
//		log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Other Details for crz_id-" + crz_id);
        }
        return ResponseHandler.generateResponse("Save crz project Details form", HttpStatus.OK, null,
                crzOtherDetailsRepository.save(crzOtherDetails));
    }

    public ResponseEntity<Object> saveUndertaking(CrzUndertaking crzUndertaking, Integer crz_id, Boolean is_submit,
                                                  HttpServletRequest request) throws PariveshException {
        try {
            CrzBasicDetails crzBasicdetails = crzBasicDetailsRepository.findById(crz_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Crz form not found"));
            crzUndertaking.setCrzBasicDetails(crzBasicdetails);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(crzBasicdetails.getProposal_no());
           
            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                    || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (crzUndertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(crzUndertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }
            
            if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);
            } else {
                proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
            }
            
            /* Update Other Properties */
            if(proponentApplicationRepository.getOtherPropertyValues(proponentApplications.getProposal_id())!=null)
            {
    		List<String> propertyResult= Arrays.asList(proponentApplicationRepository.getOtherPropertyValues(proponentApplications.getProposal_id()).split(","));
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            //OtherPropString.put("Proposal For", propertyResult.get(0));
            OtherPropString.put("Proposal For", proponentApplications.getApplications().getGeneral_name());
            
            //OtherPropString.put("Project Name", propertyResult.get(1));
            //if(propertyResult.get(2)!=null)
            //OtherPropString.put("File No", propertyResult.get(2));
            Map<String, Object> propertyData = new HashMap<String, Object>();
            propertyData.putAll(OtherPropString);
			JSONArray updatedOtherPropertyJSONArray = updateOtherPropertyService.convertMapToJSONArray(propertyData);    
            proponentApplications.setOther_property(updatedOtherPropertyJSONArray.toString());
            }
            commonFormDetailService.saveCommonForm(crzBasicdetails.getCommonFormDetail());
            proponentApplicationRepository.save(proponentApplications);
            
            if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                util.sendProposalEmail(proponentApplications);
            }

            return ResponseHandler.generateResponse("Save crz undertaking form", HttpStatus.OK, null,
                    crzUndertakingRepository.save(crzUndertaking));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving CRZ Undertaking Form for crz_id-" + crz_id);
        }
    }

    public ResponseEntity<Object> saveFacilityStorageGoods(Integer id, CrzFacilityStorageGoods crzFacilitySorageGoods)
            throws PariveshException {
        CrzFacilityStorageGoods temp = null;
        try {
            temp = new CrzFacilityStorageGoods();
            temp = crzFacilityStorageGoodsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Crz form not found"));
            temp.setName_of_product(crzFacilitySorageGoods.getName_of_product());
            temp.setCapacity_of_tanks(crzFacilitySorageGoods.getCapacity_of_tanks());
            temp.setType_of_product(crzFacilitySorageGoods.getType_of_product());
            temp.setPlease_specify_product(crzFacilitySorageGoods.getPlease_specify_product());
            temp.setEnd_use_of_the_product(crzFacilitySorageGoods.getEnd_use_of_the_product());
            temp.setNo_of_tanks_for_storage(crzFacilitySorageGoods.getNo_of_tanks_for_storage());
        } catch (Exception e) {
//			log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving CRZ Facility Storage Goods form for Id-" + id);
        }
        return ResponseHandler.generateResponse("Save crz Facility Storage Goods form", HttpStatus.OK, null,
                crzFacilityStorageGoodsRepository.save(temp));

    }

    public ResponseEntity<Object> deleteFacilityStorageGoods(Integer id) throws PariveshException {
        try {
            CrzFacilityStorageGoods crzFacilityStorageGoods = crzFacilityStorageGoodsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Crz form not found"));
            crzFacilityStorageGoods.setIs_active(false);
            crzFacilityStorageGoods.setIs_deleted(true);
            crzFacilityStorageGoodsRepository.save(crzFacilityStorageGoods);
        } catch (Exception e) {
//			log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting CRZ Facility Storage Goods data for Id-" + id);
        }
        return ResponseHandler.generateResponse("Delete crz Facility Storage Goods data", HttpStatus.OK, null,
                "Record deleted successfully");
    }

    public ResponseEntity<Object> deleteProjectDetails(Integer id) throws PariveshException {
        try {
            CrzProjectDetails crzProjectdetails = crzProjectDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Noise Level not found with Id"));
            crzProjectDetailsRepository.save(crzProjectdetails);
        } catch (Exception e) {
//			log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting CRZ Project Details for id-" + id);
        }
        return ResponseHandler.generateResponse("delete Noise Level data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteOtherDetails(Integer id) throws PariveshException {
        try {
            CrzOtherDetails crzOtherdetails = crzOtherDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Noise Level not found with Id"));
            crzOtherDetailsRepository.save(crzOtherdetails);
        } catch (Exception e) {
//			log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting CRZ Other Details for id-" + id);
        }
        return ResponseHandler.generateResponse("delete Noise Level data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteOtherEffluent(Integer id) throws PariveshException {
        try {
            OtherEffluent crzOtherEffluent = crzOtherEffluentRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Other Effluent not found by Id"));
            crzOtherEffluent.setIs_active(false);
            crzOtherEffluent.setIs_deleted(true);
            crzOtherEffluentRepository.save(crzOtherEffluent);
        } catch (Exception e) {
//			log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting CRZ Other Effluent data for Id-" + id);
        }
        return ResponseHandler.generateResponse("delete Other Effluent data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> getBasicDetails(Integer crz_id) throws PariveshException {
        try {
            CrzBasicDetails crzBasicDetails = crzBasicDetailsRepository.findById(crz_id)
                    .orElseThrow(() -> new ProjectNotFoundException("CRZ form not found with id"));
            crzBasicDetails.setProponentApplications(
                    proponentApplicationRepository.getApplicationByProposalId(crzBasicDetails.getId()));
        } catch (Exception e) {
//			log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting Basic CRZ Details for crz_id-" + crz_id);
        }
        return ResponseHandler.generateResponse("Save crz project Details form", HttpStatus.OK, null,
                crzBasicDetailsRepository.findById(crz_id)
                        .orElseThrow(() -> new ProjectNotFoundException("Crz form not found with id")));

    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail temp) {
        String cafId = "IA/"
                + stateRepository.getStateByCode(temp.getProjectDetails().getMain_state())
                .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                + "/" + "CRZ" + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }


    /*
     * Add Row save/delete/get
     */

    public ResponseEntity<Object> saveCrzWaterRequirement(
            List<CrzWaterRequirement> crzWaterRequirements, Integer id) {
        try {
            CrzBasicDetails crzBasicDetails = crzBasicDetailsRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("CRZ Basic Details not found "));
            List<CrzWaterRequirement> crzWaterRequirements2 = new ArrayList<CrzWaterRequirement>();
            crzWaterRequirements2 = crzWaterRequirements.stream().map(value -> {
                value.setCrzBasicDetails(crzBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saveCrzWaterRequirement ", HttpStatus.OK, "",
                    crzWaterRequirementRepository.saveAll(crzWaterRequirements2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveCrzWaterRequirement- ", e);
        }
    }

    public ResponseEntity<Object> deleteCrzWaterRequirement(Integer id) {
        try {
            CrzWaterRequirement crzWaterRequirement = crzWaterRequirementRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("CRZ Water Requirement not found"));

            crzWaterRequirement.setIs_deleted(true);
            crzWaterRequirement.setIs_active(false);

            return ResponseHandler.generateResponse("deleteCrzWaterRequirement", HttpStatus.OK, "",
                    crzWaterRequirementRepository.save(crzWaterRequirement));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteCrzWaterRequirement - ", e);
        }
    }

    public ResponseEntity<Object> getCrzWaterRequirement(Integer id) {
        try {
            List<CrzWaterRequirement> crzWaterRequirement = crzWaterRequirementRepository.getDataByCrzId(id);
            return ResponseHandler.generateResponse("getCrzWaterRequirement", HttpStatus.OK, "",
                    crzWaterRequirement);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getCrzSolidWaste - ", e);
        }
    }


    public ResponseEntity<Object> saveCrzSolidWaste(
            List<CrzSolidWaste> crzSolidWastes, Integer id) {
        try {
            CrzBasicDetails crzBasicDetails = crzBasicDetailsRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("CRZ Basic Details not found "));
            List<CrzSolidWaste> crzSolidWastes2 = new ArrayList<CrzSolidWaste>();
            crzSolidWastes2 = crzSolidWastes.stream().map(value -> {
                value.setCrzBasicDetails(crzBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saveCrzSolidWaste ", HttpStatus.OK, "",
                    crzSolidWasteRepository.saveAll(crzSolidWastes2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveCrzSolidWaste- ", e);
        }
    }

    public ResponseEntity<Object> deleteCrzSolidWaste(Integer id) {
        try {
            CrzSolidWaste crzSolidWaste = crzSolidWasteRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("CRZ Solid Waste not found"));

            crzSolidWaste.setIs_deleted(true);
            crzSolidWaste.setIs_active(false);

            return ResponseHandler.generateResponse("deleteCrzSolidWaste", HttpStatus.OK, "",
                    crzSolidWasteRepository.save(crzSolidWaste));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteCrzSolidWaste - ", e);
        }
    }

    public ResponseEntity<Object> getCrzSolidWaste(Integer id) {
        try {
            List<CrzSolidWaste> crzSolidWaste = crzSolidWasteRepository.getDataByCrzId(id);
            return ResponseHandler.generateResponse("getCrzSolidWaste", HttpStatus.OK, "",
                    crzSolidWaste);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getCrzSolidWaste - ", e);
        }
    }

    public ResponseEntity<Object> saveCrzDisposal(
            List<CrzDisposal> crzDisposals, Integer id) {
        try {
            CrzBasicDetails crzBasicDetails = crzBasicDetailsRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("CRZ Basic Details not found "));
            List<CrzDisposal> crzDisposals2 = new ArrayList<CrzDisposal>();
            crzDisposals2 = crzDisposals.stream().map(value -> {
                value.setCrzBasicDetails(crzBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saveCrzDisposal ", HttpStatus.OK, "",
                    crzDisposalRepository.saveAll(crzDisposals2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveCrzDisposal- ", e);
        }
    }

    public ResponseEntity<Object> deleteCrzDisposal(Integer id) {
        try {
            CrzDisposal crzDisposal = crzDisposalRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("CRZ Disposal not found"));

            crzDisposal.setIs_deleted(true);
            crzDisposal.setIs_active(false);

            return ResponseHandler.generateResponse("deleteCrzDisposal", HttpStatus.OK, "",
                    crzDisposalRepository.save(crzDisposal));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteCrzDisposal - ", e);
        }
    }

    public ResponseEntity<Object> getCrzDisposal(Integer id) {
        try {
            List<CrzDisposal> crzDisposal = crzDisposalRepository.getDataByCrzId(id);
            return ResponseHandler.generateResponse("getCrzDisposal", HttpStatus.OK, "",
                    crzDisposal);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getCrzDisposal - ", e);
        }
    }
    
    public List<OfficeHeirarchyDto> getCrzStateOfficeHierarchy(Integer state_id) throws JSONException{
    	
    	List<OfficeHeirarchyDto> offices = new ArrayList<OfficeHeirarchyDto>();
    	String rawJson = crzDisposalRepository.getOfficeHeirarchy(state_id);
    	
    	if (rawJson != null) {
			JSONArray result = new JSONArray(rawJson);

			for (int i = 0; i < result.length(); i++) {

				JSONObject jsonState = result.getJSONObject(i);

				OfficeHeirarchyDto dto = new OfficeHeirarchyDto();

				if (jsonState.isNull("office_id")) {
					dto.setOffice_id(0);
				} else {
					dto.setOffice_id(jsonState.getInt("office_id"));
				}
				if (jsonState.isNull("office_name")) {
					dto.setOffice_name("");
				} else {
					dto.setOffice_name(jsonState.getString("office_name"));
				}
				if (jsonState.isNull("office_type")) {
					dto.setOffice_type("");
				} else {
					dto.setOffice_type(jsonState.getString("office_type"));
				}
				if (jsonState.isNull("parent_office")) {
					dto.setParent_office(0);
				} else {
					dto.setParent_office(jsonState.getInt("parent_office"));
				}

				offices.add(dto);
			}
		}

		return offices;
    }
}
