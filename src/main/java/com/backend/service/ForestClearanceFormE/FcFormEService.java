package com.backend.service.ForestClearanceFormE;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.ForestClearanceE.*;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.ForestClearanceFormE.*;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.ProponentApplicationService;
import com.backend.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Transactional
@Slf4j
@Service
public class FcFormEService {

    @Autowired
    private FcFormERepository fcFormERepository;

    @Autowired
    private FcFormEApprovalDetailsRepository fcFormEApprovalDetailsRepository;

    @Autowired
    private FcFormEComplianceRepository fcFormEComplianceRepository;

    @Autowired
    private FcFormEOtherDetailsRepository fcFormEOtherDetailsRepository;

    @Autowired
    private FcFormEPatchDetailsRepository fcFormEPatchDetailsRepository;

    @Autowired
    private FcFormEPriorProposalRepository fcFormEPriorProposalRepository;

    @Autowired
    private FcFormEProposedLandRepository fcFormEProposedLandRepository;

    @Autowired
    private FcFormEUndertakingRepository fcFormEUndertakingRepository;

    @Autowired
    private FcFormEKmlsRepository fcFormEKmlsRepository;

    @Autowired
    private NotifyClient notifyClient;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private DocumentDetailsRepository documentDetailsRepository;

    @Autowired
    private ServerUtil util;

    @Autowired
    private NotificationService notificationSevice;

    @Autowired
    private ProponentApplicationService proponentApplicationService;

    public ResponseEntity<Object> saveFCFormE(FcFormE fcFormE, Integer caf_id, Integer clearance_id,
                                              HttpServletRequest request) throws PariveshException {

        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
            fcFormE.setCommonFormDetail(cafDetail);
            ProponentApplications applications;
            if (fcFormE.getId() != null && fcFormE.getId() != 0) {
                FcFormE form = fcFormERepository.getFcFormEDetailsById(fcFormE.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("FC form E not found with id"));
                fcFormE.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                return ResponseHandler.generateResponse("Update Fc Form E Form ", HttpStatus.OK, "",
                        fcFormERepository.save(fcFormE));
            }

            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {

                String proposal_no = "FP/" + stateRepository.getStateByCode(fcFormE.getState())
                        .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id"))
                        .getState_abbr() + "/REDIV/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());

                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                fcFormE.setProposal_no(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, fcFormE));
                    fcFormE.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }

            FcFormE temp2 = fcFormERepository.save(fcFormE);
            Applications app = applicationsRepository.findById(clearance_id).get();
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(fcFormE.getState())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getName());
            applications.setState_id(stateRepository.getStateByCode(fcFormE.getState())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state code")).getCode());
            applications.setProjectDetails(cafDetail.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            proponentApplicationRepository.save(applications);

//            OtherPropString.put("Form", app.getDd_name());
//            OtherPropString.put("Project Category", "Rediv");
//            proponentApplicationService.updateOtherProperty(temp2.getId(), OtherPropString);

            return ResponseHandler.generateResponse("Update FC Form E Form ", HttpStatus.OK, "", temp2);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving FC formE for fc form E id-", e);
        }

    }

    public String generateSequenceNumber(int maxcount) {
        AtomicInteger sequence = new AtomicInteger(maxcount);
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, FcFormE fcFormE) {

        String cafId = "FP/" + stateRepository.getStateByCode(fcFormE.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/REDIV" + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }

    public ResponseEntity<Object> saveFcFormEPriorProposal(Integer fc_form_e_id,
                                                           List<FcFormEPriorProposal> fcFormEPriorProposals) {
        List<FcFormEPriorProposal> approvals = new ArrayList<FcFormEPriorProposal>();

        FcFormE temp = fcFormERepository.getFcFormEById(fc_form_e_id);

        approvals = fcFormEPriorProposals.stream().map(value -> {
            value.setFcFormE(temp);
            return value;
        }).collect(Collectors.toList());

        log.info("INFO ------------ saveFcFormEPriorProposal WITH fc_id ----> ");
        List<FcFormEPriorProposal> forestDetails = fcFormEPriorProposalRepository.saveAll(approvals);

        return ResponseHandler.generateResponse("saveFcFormEPriorProposal", HttpStatus.OK, "", forestDetails);

    }

    public ResponseEntity<Object> deletePriorProposals(Integer id) throws PariveshException {
        try {
            System.out.println("Deleting the Prior Proposals");
            Integer upadate = fcFormEPriorProposalRepository.deletePriorProposals(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Deleting the Prior Proposals", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deletePriorProposals id- " + id, e);
        }
    }

    public ResponseEntity<Object> saveFcFormEKmls(Integer fc_form_e_id, List<FcFormEKmls> fcFormEKmls) {
        List<FcFormEKmls> approvals = new ArrayList<FcFormEKmls>();

        FcFormE temp = fcFormERepository.getFcFormEById(fc_form_e_id);

        approvals = fcFormEKmls.stream().map(value -> {
            value.setFcFormE(temp);
            return value;
        }).collect(Collectors.toList());

        log.info("INFO ------------ saveFcFormEKmls WITH fc_id ----> ");
        List<FcFormEKmls> forestDetails = fcFormEKmlsRepository.saveAll(approvals);

        return ResponseHandler.generateResponse("saveFcFormEKmls", HttpStatus.OK, "", forestDetails);

    }

    public ResponseEntity<Object> deleteKmls(Integer fc_form_e_id) throws PariveshException {
        try {
            System.out.println("Deleting the kml details");
            Integer upadate = fcFormEKmlsRepository.deleteKmls(fc_form_e_id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + fc_form_e_id);
            }
            return ResponseHandler.generateResponse("Deleting the kml details", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteKmls id- " + fc_form_e_id, e);
        }
    }

//	public ResponseEntity<Object> saveFcFormEApprovalDetails(Integer fc_form_e_kmls_id,
//			List<FcFormEApprovalDetails> fcFormEApprovalDetails) {
//		List<FcFormEApprovalDetails> approvals = new ArrayList<FcFormEApprovalDetails>();
//
//		FcFormEKmls temp = fcFormEKmlsRepository.getByKmlId(fc_form_e_kmls_id);
//
//		approvals = fcFormEApprovalDetails.stream().map(value -> {
//			value.setFcFormEKmls(temp);
//			return value;
//		}).collect(Collectors.toList());
//
//		log.info("INFO ------------ saveFcFormEApprovalDetails WITH fc_id ----> ");
//		List<FcFormEApprovalDetails> forestDetails = fcFormEApprovalDetailsRepository.saveAll(approvals);
//
//		return ResponseHandler.generateResponse("saveFcFormEApprovalDetails", HttpStatus.OK, "", forestDetails);
//
//	}

    public ResponseEntity<Object> deleteApprovalDetails(Integer fc_form_e_id) throws PariveshException {
        try {
            System.out.println("Deleting the Approval Details");
            Integer upadate = fcFormEApprovalDetailsRepository.deleteApprovalDetails(fc_form_e_id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + fc_form_e_id);
            }
            return ResponseHandler.generateResponse("Deleting the Approval Details", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteApprovalDetails id- " + fc_form_e_id, e);
        }
    }

    public ResponseEntity<Object> saveFcFormECompliance(Integer fc_form_e_id,
                                                        List<FcFormECompliance> fcFormECompliance) {
        List<FcFormECompliance> approvals = new ArrayList<FcFormECompliance>();

        FcFormE temp = fcFormERepository.getFcFormEById(fc_form_e_id);

        approvals = fcFormECompliance.stream().map(value -> {
            value.setFcFormE(temp);
            return value;
        }).collect(Collectors.toList());

        log.info("INFO ------------ saveFcFormECompliance WITH fc_id ----> ");
        List<FcFormECompliance> forestDetails = fcFormEComplianceRepository.saveAll(approvals);

        return ResponseHandler.generateResponse("saveFcFormECompliance", HttpStatus.OK, "", forestDetails);

    }

    public ResponseEntity<Object> deleteComplianceDetails(Integer fc_form_e_id) throws PariveshException {
        try {
            System.out.println("Deleting the Compliance Details");
            Integer upadate = fcFormEComplianceRepository.deleteComplianceDetails(fc_form_e_id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + fc_form_e_id);
            }
            return ResponseHandler.generateResponse("Deleting the Compliance Details", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteComplianceDetails id- " + fc_form_e_id, e);
        }
    }

    public ResponseEntity<Object> saveFcFormEProposedLand(FcFormEProposedLand fcFormEProposedLand, Integer fc_form_e_id)
            throws PariveshException {
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            if (fcFormEProposedLand.getId() == null || fcFormEProposedLand.getId() == 0) {
                FcFormEProposedLand fcFormEProposedLand2 = fcFormEProposedLandRepository
                        .getDataByFcFormEId(fc_form_e_id);
                if (fcFormEProposedLand2 != null) {
                    fcFormEProposedLand.setId(fcFormEProposedLand2.getId());
                }
            }

            FcFormE e = fcFormERepository.getFcFormEById(fc_form_e_id);
            log.info("INFO ------------ saveFcFormEProposedLand WITH fc_form_e_id " + fc_form_e_id + "----->SUCCESS");
            fcFormEProposedLand.setFcFormE(e);

            /*
             * Updating the Proponent Application JSON String
             */
            ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalId(e.getId());
            Applications app = proponentApplications.getApplications();
            OtherPropString.put("Form", app.getDd_name());
            OtherPropString.put("Project Category", "Re-Diversion");
            OtherPropString.put("Forest Area", fcFormEProposedLand.getTotal_forest_land_proposed().toString());
            proponentApplicationService.updateOtherProperty(fc_form_e_id, OtherPropString);

            return ResponseHandler.generateResponse("Save FCFormE", HttpStatus.OK, "",
                    fcFormEProposedLandRepository.save(fcFormEProposedLand));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in saving Forest Form E saveFcFormEProposedLand details for fc_form_e_id ");
        }
    }

    public ResponseEntity<Object> saveFcFormEOtherDetails(FcFormEOtherDetails fcFormEOtherDetails, Integer fc_form_e_id)
            throws PariveshException {
        try {
            if (fcFormEOtherDetails.getId() == null || fcFormEOtherDetails.getId() == 0) {
                FcFormEOtherDetails fcFormEOtherDetails2 = fcFormEOtherDetailsRepository
                        .getDataByFcFormEId(fc_form_e_id);
                if (fcFormEOtherDetails2 != null) {
                    fcFormEOtherDetails.setId(fcFormEOtherDetails2.getId());
                }
            }

            FcFormE e = fcFormERepository.getFcFormEById(fc_form_e_id);
            log.info("INFO ------------ saveFcFormEOtherDetails WITH fc_form_e_id " + fc_form_e_id + "----->SUCCESS");
            fcFormEOtherDetails.setFcFormE(e);

            return ResponseHandler.generateResponse("Save FCFormE", HttpStatus.OK, "",
                    fcFormEOtherDetailsRepository.save(fcFormEOtherDetails));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in saving Forest Form E saveFcFormEOtherDetails details for fc_form_e_id ");
        }
    }

    public ResponseEntity<Object> saveFcFormEPatchDetails(FcFormEPatchDetails fcFormEPatchDetails, Integer fc_form_e_id)
            throws PariveshException {
        try {
            if (fcFormEPatchDetails.getId() == null || fcFormEPatchDetails.getId() == 0) {
                FcFormEPatchDetails fcFormEPatchDetails2 = fcFormEPatchDetailsRepository
                        .getDataByFcFormEId(fc_form_e_id);
                if (fcFormEPatchDetails2 != null) {
                    fcFormEPatchDetails.setId(fcFormEPatchDetails2.getId());
                }
            }

            FcFormE e = fcFormERepository.getFcFormEById(fc_form_e_id);
            log.info("INFO ------------ saveFcFormEPatchDetails WITH fc_form_e_id " + fc_form_e_id + "----->SUCCESS");
            fcFormEPatchDetails.setFcFormE(e);

            return ResponseHandler.generateResponse("Save FCFormE", HttpStatus.OK, "",
                    fcFormEPatchDetailsRepository.save(fcFormEPatchDetails));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in saving Forest Form E saveFcFormEPatchDetails details for fc_form_e_id ");
        }
    }

    public ResponseEntity<Object> deletePatchDetails(Integer fc_form_e_id) throws PariveshException {
        try {
            System.out.println("Deleting the Patch Details");
            Integer upadate = fcFormEPatchDetailsRepository.deletePatchDetails(fc_form_e_id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + fc_form_e_id);
            }
            return ResponseHandler.generateResponse("Deleting the Patch Details", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deletePatchDetails id- " + fc_form_e_id, e);
        }
    }

    public ResponseEntity<Object> saveFcFormEUndertaking(FcFormEUndertaking fcFormEUndertaking, Integer fc_form_e_id, Boolean is_submit,
                                                         HttpServletRequest request) throws PariveshException {
        try {
            System.out.println("undertaking...");
            if (fcFormEUndertaking.getId() == null || fcFormEUndertaking.getId() == 0) {
                FcFormEUndertaking fcUndertaking2 = fcFormEUndertakingRepository.getByFcId(fc_form_e_id);
                if (fcUndertaking2 != null) {
                    fcFormEUndertaking.setId(fcUndertaking2.getId());
                }
            }
            FcFormE temp = fcFormERepository.findById(fc_form_e_id).orElseThrow(
                    () -> new ProjectNotFoundException("FC Form E not found with given Id" + fc_form_e_id));

            fcFormEUndertaking.setFcFormE(temp);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());
            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                    || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (fcFormEUndertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(fcFormEUndertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }
            commonFormDetailService.saveCommonForm(temp.getCommonFormDetail());
            proponentApplicationRepository.save(proponentApplications);
            log.info("INFO ------------ saveFcFormEUndertaking WITH fc_form_e_id " + fc_form_e_id + "-----> SUCCESS");

            if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }
            return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
                    fcFormEUndertakingRepository.save(fcFormEUndertaking));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form E Undertaking for fc_form_e_id ");
        }
    }

    public ResponseEntity<Object> getFcFormE(Integer id, Integer step) throws PariveshException {
        FcFormE form = null;
        System.out.println("In Get Method");
        try {
            if (step != null) {
                if (step == 1) {

                    form = fcFormERepository.getFcFormEDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form E Form not found with id"));

                    List<Object[]> docData = fcFormERepository.getDocuments(id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.setUpload_noc_copy(
                                    obj[0] != null ? documentDetailsRepository.findById((Integer) obj[0]).get() : null);
                        }
                    }
                    form.setFcFormEPriorProposals(fcFormEPriorProposalRepository.getByFcID(id));
                    form.setFcFormECompliances(fcFormEComplianceRepository.getByFcID(id));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);
                } else if (step == 2) {
                    form = fcFormERepository.getFcFormEDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));
                    List<Object[]> docData = fcFormERepository.getDocuments(id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.setUpload_noc_copy(
                                    obj[0] != null ? documentDetailsRepository.findById((Integer) obj[0]).get() : null);
                        }
                    }
                    System.out.println(form);
                    form.setFcFormEProposedLand(fcFormEProposedLandRepository.getByFcID(form.getId()));
                    form.setFcFormEKmls(fcFormEKmlsRepository.getByFcID(form.getId()));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);
                } else if (step == 3) {

                    form = fcFormERepository.getFcFormEDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form E Form not found with id"));
                    List<Object[]> docData = fcFormERepository.getDocuments(id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.setUpload_noc_copy(
                                    obj[0] != null ? documentDetailsRepository.findById((Integer) obj[0]).get() : null);
                        }
                    }
                    form.setFcFormEOtherDetails(fcFormEOtherDetailsRepository.getByFcID(id));
                    form.setFcFormEPatchDetails(fcFormEPatchDetailsRepository.getByFcID(id));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);
                } else if (step == 4) {
                    form = fcFormERepository.getFcFormEDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form E Form not found with id"));
                    List<Object[]> docData = fcFormERepository.getDocuments(id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.setUpload_noc_copy(
                                    obj[0] != null ? documentDetailsRepository.findById((Integer) obj[0]).get() : null);
                        }
                    }
                    form.setFcFormEUndertaking(fcFormEUndertakingRepository.getByFcId(id));
                    form.setFcFormEProposedLand(fcFormEProposedLandRepository.getByFcID(form.getId()));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                } else {
                    log.info("========================>>>>> NO Step Mentioned in Get");
                }

            }
            return ResponseHandler.generateResponse("get FCFormE", HttpStatus.OK, "", form);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting form E for fc_id ");
        }
    }

}
