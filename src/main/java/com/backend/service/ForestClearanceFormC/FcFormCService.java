package com.backend.service.ForestClearanceFormC;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.ForestClearanceFormC.*;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.ForestClearanceFormC.*;
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
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FcFormCService {

    @Autowired
    private FcFormCRepository fcFormCRepository;

    @Autowired
    private FcFormCProposedLandRepository fcFormCProposedLandRepository;

    @Autowired
    private FcFormCOtherDetailsRepository fcFormCOtherDetailsRepository;

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
    private FcFormCAfforestationDetailsRepository fcFormCAfforestationDetailsRepository;

    @Autowired
    private FcFormCActivitiesDetailsRepository fcFormCActivitiesDetailsRepository;

    @Autowired
    private FcFormCLandDetailsRepository fcFormCLandDetailsRepository;

    @Autowired
    private DocumentDetailsRepository documentdetailsRepository;

    @Autowired
    private FcFormCPriorApprovalRepository fcFormCPriorApprovalRepository;

    @Autowired
    private FcFormCProposedDiversionsRepository fcFormCProposedDiversionsRepository;

    @Autowired
    private FcFormCUndertakingRepository fcFormCUndertakingRepository;

    @Autowired
    private NotifyClient notifyClient;

    @Autowired
    private FcFormCPatchKmlsRepository fcFormCPatchKmlsRepository;

    @Autowired
    private FcFormCSurfaceSamplingRepository fcFormCSurfaceSamplingRepository;

    @Autowired
    private FcFormCDetailsOfMachineryRepository fcFormCDetailsOfMachineryRepository;

    @Autowired
    private FcFormCComplianceReportRepository fcComplianceReportRepository;

    @Autowired
    private FcFormCExploredForestLandRepository fcFormCExploredForestLandRepository;

    @Autowired
    private FcFormCProposedLandNRepository fcFormCProposedLandNRepository;

    @Autowired
    private FcFormCLandDetailsNRepository fcFormCLandDetailsNRepository;

    @Autowired
    private ServerUtil util;

    @Autowired
    private NotificationService notificationSevice;

    @Autowired
    private ProponentApplicationService proponentApplicationService;

    public ResponseEntity<Object> saveFCFormC(FcFormC fcFormC, Integer caf_id, Integer clearance_id,
                                              HttpServletRequest request) throws PariveshException {

        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
            fcFormC.setCommonFormDetail(cafDetail);
            ProponentApplications applications;
            if (fcFormC.getId() != null && fcFormC.getId() != 0) {
                FcFormC form = fcFormCRepository.getFcFormCDetailsById(fcFormC.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("FC form c not found with id"));
                fcFormC.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));

                applications = proponentApplicationRepository.getApplicationByProposalId(form.getId());

//				if (fcFormC.getIs_project_falls_within_protected_area() == true) {
//					applications.setLast_status(Caf_Status.SUBMITTED.toString());
//				} else
//					applications.setLast_status(Caf_Status.DRAFT.toString());
//				proponentApplicationRepository.save(applications);

                return ResponseHandler.generateResponse("Update Ec partC Form ", HttpStatus.OK, "",
                        fcFormCRepository.save(fcFormC));
            }

            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {

                String proposal_no = "FP/" + stateRepository.getStateByCode(fcFormC.getState())
                        .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id"))
                        .getState_abbr() + "/SRY/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());

                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                fcFormC.setProposal_no(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, fcFormC));
                    fcFormC.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }

            FcFormC temp2 = fcFormCRepository.save(fcFormC);
            Applications app = applicationsRepository.findById(clearance_id).get();
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(fcFormC.getState())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getName());
            applications.setState_id(stateRepository.getStateByCode(fcFormC.getState())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state code")).getCode());
            applications.setProjectDetails(cafDetail.getProjectDetails());
            applications.setApplications(app);
            if (fcFormC.getIs_project_falls_within_protected_area() == true) {
                applications.setLast_status(Caf_Status.SUBMITTED.toString());
            } else
                applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            proponentApplicationRepository.save(applications);
            
            OtherPropString.put("Exploration Area", fcFormC.getForest_proposed_exploration_area());
            OtherPropString.put("Project Category", "Exploration & Survey");
            OtherPropString.put("Form", app.getDd_name());

            proponentApplicationService.updateOtherProperty(temp2.getId(), OtherPropString);

            return ResponseHandler.generateResponse("Update FC partC Form ", HttpStatus.OK, "", temp2);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving FC partC form for fc form c id-");
        }

    }

    public ResponseEntity<Object> saveFcFormCProposedLand(FcFormCProposedLand fcFormCProposedLand, Integer fc_form_c_id,
                                                          HttpServletRequest request) throws PariveshException {
        try {
            /*
             * Step : Proposed Land
             */
            if (fcFormCProposedLand.getId() == null || fcFormCProposedLand.getId() == 0) {
                FcFormCProposedLand fcFormCProposedLand2 = fcFormCProposedLandRepository
                        .getDataByFcFormCId(fc_form_c_id);
                if (fcFormCProposedLand2 != null) {
                    fcFormCProposedLand.setId(fcFormCProposedLand2.getId());
                }
            }

            FcFormC c = fcFormCRepository.getFcFormcById(fc_form_c_id);
            log.info("INFO ------------ saveFcFormCProposedLand WITH fc_id " + fc_form_c_id + "----->SUCCESS");
            fcFormCProposedLand.setFcFormC(c);

            return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
                    fcFormCProposedLandRepository.save(fcFormCProposedLand));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Proposed Land for fc_id- ");
        }
    }

    public ResponseEntity<Object> saveFcFormCProposedLandN(FcFormCProposedLandN fcFormCProposedLandN,
                                                           Integer fc_form_c_id, HttpServletRequest request) throws PariveshException {
        try {
            /*
             * Step : Proposed Land N
             */
            if (fcFormCProposedLandN.getId() == null || fcFormCProposedLandN.getId() == 0) {
                FcFormCProposedLandN fcFormCProposedLand2 = fcFormCProposedLandNRepository
                        .getDataByFcFormCId(fc_form_c_id);
                if (fcFormCProposedLand2 != null) {
                    fcFormCProposedLandN.setId(fcFormCProposedLand2.getId());
                }
            }

            FcFormC c = fcFormCRepository.getFcFormcById(fc_form_c_id);
            log.info("INFO ------------ saveFcFormCProposedLand WITH fc_id " + fc_form_c_id + "----->SUCCESS");
            fcFormCProposedLandN.setFcFormC(c);

            return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
                    fcFormCProposedLandNRepository.save(fcFormCProposedLandN));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Proposed Land for fc_id- ");
        }
    }

    public ResponseEntity<Object> saveFcFormCOtherDetails(FcFormCOtherDetails fcFormCOtherDetails, Integer fc_form_c_id,
                                                          HttpServletRequest request) throws PariveshException {
        try {
            /*
             * Step : Other details
             */
            if (fcFormCOtherDetails.getId() == null || fcFormCOtherDetails.getId() == 0) {
                FcFormCOtherDetails fcFormCOtherDetails2 = fcFormCOtherDetailsRepository
                        .getDataByFcFormCId(fc_form_c_id);
                if (fcFormCOtherDetails2 != null) {
                    fcFormCOtherDetails.setId(fcFormCOtherDetails2.getId());
                }
            }

            FcFormC c = fcFormCRepository.getFcFormcById(fc_form_c_id);
            log.info("INFO ------------ saveFcFormCOtherDetails WITH fc_form_c_id " + fc_form_c_id + "----->SUCCESS");
            fcFormCOtherDetails.setFcFormC(c);

            return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
                    fcFormCOtherDetailsRepository.save(fcFormCOtherDetails));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Other Details for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> saveFcFormCAfforestationDetails(
            FcFormCAfforestationDetails fcFormCAfforestationDetails, Integer fc_form_c_id, HttpServletRequest request)
            throws PariveshException {
        try {
            /*
             * Step : Afforestation Details
             */
            if (fcFormCAfforestationDetails.getId() == null || fcFormCAfforestationDetails.getId() == 0) {
                FcFormCAfforestationDetails fcFormCAfforestationDetails2 = fcFormCAfforestationDetailsRepository
                        .getDataByFcFormCId(fc_form_c_id);
                if (fcFormCAfforestationDetails2 != null) {
                    fcFormCAfforestationDetails.setId(fcFormCAfforestationDetails2.getId());
                }
            }

            FcFormC c = fcFormCRepository.getFcFormcById(fc_form_c_id);
            log.info("INFO ------------ saveFcFormCAfforestationDetails WITH fc_form_c_id " + fc_form_c_id
                    + "----->SUCCESS");
            fcFormCAfforestationDetails.setFcFormC(c);

            return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
                    fcFormCAfforestationDetailsRepository.save(fcFormCAfforestationDetails));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Afforestation details for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> saveFcFormCActivityDetails(FcFormCActivitiesDetails fcFormCActivitiesDetails,
                                                             Integer fc_form_c_id, HttpServletRequest request) throws PariveshException {
        try {
            /*
             * Step : Activity Details
             */
            if (fcFormCActivitiesDetails.getId() == null || fcFormCActivitiesDetails.getId() == 0) {
                FcFormCActivitiesDetails fcFormCActivitiesDetails2 = fcFormCActivitiesDetailsRepository
                        .getDataByFcFormCId(fc_form_c_id);
                if (fcFormCActivitiesDetails2 != null) {
                    fcFormCActivitiesDetails.setId(fcFormCActivitiesDetails2.getId());
                }
            }

            FcFormC c = fcFormCRepository.getFcFormcById(fc_form_c_id);
            log.info("INFO ------------ saveFcFormCActivityDetails WITH fc_form_c_id " + fc_form_c_id
                    + "-----> SUCCESS");
            fcFormCActivitiesDetails.setFcFormC(c);

            return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
                    fcFormCActivitiesDetailsRepository.save(fcFormCActivitiesDetails));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Activity Details for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> saveFcFormCLandDetails(FcFormCLandDetails fcFormCLandDetails, Integer fc_form_c_id,
                                                         HttpServletRequest request) throws PariveshException {
        try {
            /*
             * Step : Land Details
             */
            if (fcFormCLandDetails.getId() == null || fcFormCLandDetails.getId() == 0) {
                FcFormCLandDetails fcFormCLandDetails2 = fcFormCLandDetailsRepository.getDataByFcFormCId(fc_form_c_id);
                if (fcFormCLandDetails2 != null) {
                    fcFormCLandDetails.setId(fcFormCLandDetails2.getId());
                }
            }

            FcFormC c = fcFormCRepository.getFcFormcById(fc_form_c_id);
            log.info("INFO ------------ saveFcFormCLandDetails WITH fc_form_c_id " + fc_form_c_id + "-----> SUCCESS");
            fcFormCLandDetails.setFcFormC(c);

            return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
                    fcFormCLandDetailsRepository.save(fcFormCLandDetails));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Land Details for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> saveFcFormCLandDetailsN(FcFormCLandDetailsN fcFormCLandDetailsN, Integer fc_form_c_id,
                                                          HttpServletRequest request) throws PariveshException {
        try {
            /*
             * Step : Land Details : fcFormCLandDetailsNRepository
             */
            if (fcFormCLandDetailsN.getId() == null || fcFormCLandDetailsN.getId() == 0) {
                FcFormCLandDetailsN fcFormCLandDetails2 = fcFormCLandDetailsNRepository
                        .getDataByFcFormCId(fc_form_c_id);
                if (fcFormCLandDetails2 != null) {
                    fcFormCLandDetailsN.setId(fcFormCLandDetails2.getId());
                }
            }

            FcFormC c = fcFormCRepository.getFcFormcById(fc_form_c_id);
            log.info("INFO ------------ saveFcFormCLandDetails WITH fc_form_c_id " + fc_form_c_id + "-----> SUCCESS");
            fcFormCLandDetailsN.setFcFormC(c);

            return ResponseHandler.generateResponse("Save FCFormC Land Details N", HttpStatus.OK, "",
                    fcFormCLandDetailsNRepository.save(fcFormCLandDetailsN));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Land Details N for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> saveFcFormCUndertaking(FcFormCUndertaking fcFormCUndertaking, Integer fc_form_c_id,Boolean is_submit,
                                                         HttpServletRequest request) throws PariveshException {
        try {

            /*
             * Step : Undertaking
             */
            System.out.println("undertaking...");
            if (fcFormCUndertaking.getId() == null || fcFormCUndertaking.getId() == 0) {
                FcFormCUndertaking fcUndertaking2 = fcFormCUndertakingRepository
                        .getFcFormCUndertakingDetailsById(fc_form_c_id);
                if (fcUndertaking2 != null) {
                    fcFormCUndertaking.setId(fcUndertaking2.getId());
                }
            }

            /*
             * FcFormC temp =
             * fcFormCRepository.getFcFormCDetailsById(fc_form_c_id).orElseThrow(()->new
             * ProjectNotFoundException("FC Form C not found with given Id"+fc_form_c_id));
             * FcFormC
             * tempCAF=fcFormCRepository.getCAFByFcFormCId(fc_form_c_id).orElseThrow(()->new
             * ProjectNotFoundException("CAF not found with given FCFormC Id"+fc_form_c_id))
             * ; CommonFormDetail
             * commonFormDetail=commonFormDetailRepository.findDetailByCafId(tempCAF.
             * getCommonFormDetail().getId());
             */

            FcFormC temp = fcFormCRepository.findById(fc_form_c_id).orElseThrow(
                    () -> new ProjectNotFoundException("FC Form C not found with given Id" + fc_form_c_id));

            fcFormCUndertaking.setFcFormC(temp);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());
//			if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
//				proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
//				proponentApplications.setMigration_status(false);
//			} else {
//				proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
//			}
            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                    || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (fcFormCUndertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(fcFormCUndertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }
            commonFormDetailService.saveCommonForm(temp.getCommonFormDetail());
            // commonFormDetailService.saveCommonForm(commonFormDetail);

            proponentApplicationRepository.save(proponentApplications);
            log.info("INFO ------------ saveFcFormCUndertaking WITH fc_form_c_id " + fc_form_c_id + "-----> SUCCESS");
            fcFormCUndertakingRepository.save(fcFormCUndertaking);

            if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Undertaking for fc_form_c_id ");
        }
        return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "",
                fcFormCUndertakingRepository.save(fcFormCUndertaking));
    }

    public ResponseEntity<Object> saveFcFormCProposedDiversion(
            List<FcFormCProposedDiversions> fcFormCProposedDiversions, Integer fc_form_c_id) {
        try {
            List<FcFormCProposedDiversions> diversions = new ArrayList<>();
            FcFormC temp = fcFormCRepository.getFcFormcById(fc_form_c_id);
            fcFormCProposedDiversions.forEach(value -> {
                value.setFcFormC(temp);
                if (value.getDiversion_map_copy() != null) {
                    value.getDiversion_map_copy().setProposal_no(temp.getProposal_no());
                }
                FcFormCProposedDiversions forestClearanceProposedDiversions = fcFormCProposedDiversionsRepository
                        .save(value);
                diversions.add(forestClearanceProposedDiversions);
            });
            log.info("INFO ------------ saveForestProposedDiversionsDetails WITH forest clearance id ----> "
                    + fc_form_c_id + " ------>SUCCESS");
            return ResponseHandler.generateResponse("Save Forest Proposed Diversion Data", HttpStatus.OK, " ",
                    diversions);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Proposed Diversion for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> getFCFormCPropesdDiversion(int id) throws PariveshException {
        try {

            return ResponseHandler.generateResponse("get forest kmls data list by fc_id", HttpStatus.OK, "",
                    fcFormCProposedDiversionsRepository.findByFcFormCByID(id));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting FC Form C getForestClearancePropesdDiversionData id- " + id,
                    e);
        }

    }

    public ResponseEntity<Object> deleteForestClearancePropesdDiversion(int id) throws PariveshException {
        try {
            System.out.println("Deleting the proposed Diversion");
            Integer upadate = fcFormCProposedDiversionsRepository.updateFcFormCById(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Forest Clearance Proposed Diversion", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Deleting FC Form C deleteForestClearancePropesdDiversionData id- " + id, e);
        }
    }

    public ResponseEntity<Object> getFcFormC(Integer id, Integer step) throws PariveshException {
        FcFormC form = null;
        System.out.println("In Get Method");
        try {
            if (step != null) {
                if (step == 1) {

                    /*
                     * Step : FC Form C / Project Details
                     */

                    form = fcFormCRepository.getFcFormCDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));

                    List<Object[]> docData = fcFormCRepository.getDocuments(id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.setLetter_of_intent_copy(
                                    obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
                        }
                    }
                    form.setFcFormCPriorApprovals(fcFormCPriorApprovalRepository.getFcPriorAppById(id));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);
                } else if (step == 2) {

                    /*
                     * Step : Proposed Land fcFormCProposedLandNRepository
                     */

                    // 1. Without Changes
                    form = fcFormCRepository.getFcFormCDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));
                    System.out.println(form);
                    form.setFcFormCProposedLand(fcFormCProposedLandRepository.getFcFormCProposedLandById(form.getId()));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                    /*
                     * form = fcFormCRepository.getFcFormCDetailsById(id) .orElseThrow(() -> new
                     * ProjectNotFoundException("Fc Form C Form not found with id"));
                     * System.out.println(form);
                     * form.setFcFormCProposedLandN(fcFormCProposedLandNRepository.
                     * getFcFormCProposedLandById(form.getId()));
                     * form.setProponentApplications(proponentApplicationRepository.getAppById(form.
                     * getId()) != null ? proponentApplicationRepository.getAppById(form.getId()) :
                     * null);
                     */
                } else if (step == 3) {

                    /*
                     * Step : Other Details
                     */

                    form = fcFormCRepository.getFcFormCDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));
                    System.out.println(form);
                    form.setFcFormCOtherDetails(fcFormCOtherDetailsRepository.getFcFormCOtherDetailsById(id));

                    List<Object[]> docData = fcFormCOtherDetailsRepository.getDocuments(form.getId());
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.getFcFormCOtherDetails().setEc_letter(
                                    obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
                        }
                    }
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                } else if (step == 4) {

                    /*
                     * Step : Activity Details
                     */

                    form = fcFormCRepository.getFcFormCDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));
                    form.setFcFormCActivitiesDetails(
                            fcFormCActivitiesDetailsRepository.getFcFormCActivityDetailsById(id));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                } else if (step == 5) {

                    /*
                     * Step : Land Details : fcFormCLandDetailsNRepository
                     */

                    /*
                     * form = fcFormCRepository.getFcFormCDetailsById(id) .orElseThrow(() -> new
                     * ProjectNotFoundException("Fc Form C Form not found with id"));
                     * form.setFcFormCLandDetails(fcFormCLandDetailsRepository.
                     * getFcFormCLandDetailsById(id)); List<Object[]> docData =
                     * fcFormCLandDetailsRepository.getDocuments(id); for (Object[] obj : docData) {
                     * Old Changes if(obj!=null) {
                     * form.getFcFormCLandDetails().setJustification_for_extension_copy( obj[0] !=
                     * null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
                     * form.getFcFormCLandDetails().setDetails_of_existing_path_copy( obj[1] != null
                     * ? documentdetailsRepository.findById((Integer) obj[1]).get() : null);
                     * form.getFcFormCLandDetails().setNote_containing_details_copy( obj[2] != null
                     * ? documentdetailsRepository.findById((Integer) obj[2]).get() : null); }
                     *
                     */

                    form = fcFormCRepository.getFcFormCDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));
                    form.setFcFormCLandDetailsN(fcFormCLandDetailsNRepository.getFcFormCLandDetailsById(id));
                    List<Object[]> docData = fcFormCLandDetailsNRepository.getDocuments(id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.getFcFormCLandDetailsN().setDetails_of_existing_path_copy(
                                    obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
                            form.getFcFormCLandDetailsN().setNote_containing_details_copy(
                                    obj[1] != null ? documentdetailsRepository.findById((Integer) obj[1]).get() : null);
                        }
                    }
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);
                } else if (step == 6) {

                    /*
                     * Step : Afforestation
                     */

                    form = fcFormCRepository.getFcFormCDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));
                    form.setFcFormCAfforestationDetails(
                            fcFormCAfforestationDetailsRepository.getFcFormCAfforestationDetailsById(id));

                    List<Object[]> docData = fcFormCAfforestationDetailsRepository.getDocuments(id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.getFcFormCAfforestationDetails().setIdentified_land_for_compensatory_afforestaion_copy(
                                    obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
                            form.getFcFormCAfforestationDetails().setCopy_of_mou(
                                    obj[1] != null ? documentdetailsRepository.findById((Integer) obj[1]).get() : null);
                        }
                    }
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);
                } else if (step == 7) {

                    /*
                     * Step: Additional Information
                     */
                    form = fcFormCRepository.getFcFormCDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));

                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                } else if (step == 8) {

                    /*
                     * Step: Undertaking
                     */

                    form = fcFormCRepository.getFcFormCDetailsById(id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form C Form not found with id"));
                    form.setFcFormCUndertaking(fcFormCUndertakingRepository.getFcFormCUndertakingDetailsById(id));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                } else {
                    log.info("========================>>>>> NO Step Mentioned in Get");
                }

            }
            return ResponseHandler.generateResponse("Save FCFormC", HttpStatus.OK, "", form);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in adding Forest Form C Proposed Land for fc_id ");
        }
    }

    public ResponseEntity<Object> saveFcFormCApprovalDeatails(Integer fc_form_c_id,
                                                              List<FcFormCPriorApproval> fcFormCPriorApprovals) {
        List<FcFormCPriorApproval> approvals = new ArrayList<FcFormCPriorApproval>();

        FcFormC temp = fcFormCRepository.getFcFormcById(fc_form_c_id);

        approvals = fcFormCPriorApprovals.stream().map(value -> {
            value.setFcFormC(temp);
            return value;
        }).collect(Collectors.toList());

        log.info("INFO ------------ saveForestApprovalDeatails WITH fc_id ----> ");
        List<FcFormCPriorApproval> forestDetails = fcFormCPriorApprovalRepository.saveAll(approvals);

        return ResponseHandler.generateResponse("Save Forest Map Data", HttpStatus.OK, "", forestDetails);

    }

    public ResponseEntity<Object> getFcFormCApprovalData(Integer fc_form_c_id) {

        log.info(
                "INFO ------------ getForestPriorApprovalData WITH fc_id ----> RETRIEVING FOREST DATA FROM FC_ID - SUCCESS");
        return ResponseHandler.generateResponse("get forest data approval list by fc_id", HttpStatus.OK, "",
                fcFormCPriorApprovalRepository.getFcPriorAppById(fc_form_c_id));

    }

    public ResponseEntity<Object> deleteFcFormCApprovalData(Integer priorId) {
        try {
            FcFormCPriorApproval priorTemp = fcFormCPriorApprovalRepository.getById(priorId);
            priorTemp.setIs_delete(true);
            log.info("INFO ------------ deletePriorApprovalData WITH PRIOR APPROVAL ID--- priorId ----> " + priorId
                    + "---- DELETING PRIOR APPROVAL DATA FROM priorId - SUCCESS");
            return ResponseHandler.generateResponse("Delete Prior Approval ", HttpStatus.OK, "",
                    fcFormCPriorApprovalRepository.save(priorTemp));
        } catch (Exception ex) {
            log.info(
                    "ERROR ------------ EXPECTATION_FAILED: deletePriorApprovalData WITH PRIOR APPROVAL ID--- priorId ----> "
                            + priorId + "---- DELETING PRIOR APPROVAL DATA FROM priorId - FAILURE");
            return ResponseHandler.generateResponse("Delete Prior Approval ", HttpStatus.EXPECTATION_FAILED, "",
                    ex.getMessage());
        }
    }

    public String generateSequenceNumber(int maxcount) {
        AtomicInteger sequence = new AtomicInteger(maxcount);
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, FcFormC fcFormC) {

        String cafId = "FP/" + stateRepository.getStateByCode(fcFormC.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/SRY" + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }

    public ResponseEntity<Object> saveFCFormCPatchKML(Integer fc_form_c_id,
                                                      List<FcFormCPatchKmls> forestClearancePatchKmls) throws PariveshException {
        List<FcFormCPatchKmls> listtemp = new ArrayList<>();
        try {
            FcFormC temp = fcFormCRepository.getFcFormcById(fc_form_c_id);
            for (FcFormCPatchKmls patchkml : forestClearancePatchKmls) {
                patchkml.setFcFormC(temp);
                /*
                 * if (patchkml.getPatch_kml() != null) {
                 * patchkml.getPatch_kml().setProposal_no(temp.getProposal_no()); }
                 */
                FcFormCPatchKmls temp1 = fcFormCPatchKmlsRepository.save(patchkml);
                listtemp.add(temp1);
            }

            return ResponseHandler.generateResponse("Save Patch KML", HttpStatus.OK, "", listtemp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFCFormCPatchKML for FcFormCId- " + fc_form_c_id, e);
        }
    }

    public ResponseEntity<Object> getFcFormCPatchKML(Integer id) throws PariveshException {
        try {

            return ResponseHandler.generateResponse("Get Form C Patch KML", HttpStatus.OK, "",
                    fcFormCPatchKmlsRepository.findfcKMLbyfcFormCID(id));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getFcFormCPatchKML for FcFormC- " + id, e);
        }
    }

    public ResponseEntity<Object> deleteFcFormCPatchKML(Integer id) throws PariveshException {
        try {
            FcFormCPatchKmls fcFormCPatchKmls = fcFormCPatchKmlsRepository.getById(id);
            fcFormCPatchKmls.set_deleted(true);
            return ResponseHandler.generateResponse("Get Form C Patch KML", HttpStatus.OK, "",
                    fcFormCPatchKmlsRepository.save(fcFormCPatchKmls));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteFcFormCPatchKML for id- " + id, e);
        }
    }

    // ----------------Changes-------------

    /*
     * Surface Sampling
     */

    public ResponseEntity<Object> saveFcFormCSurfaceSampling(List<FcFormCSurfaceSampling> fcFormCSurfaceSamplings,
                                                             Integer fc_form_c_id) {
        try {
            List<FcFormCSurfaceSampling> sampling = new ArrayList<>();
            FcFormC temp = fcFormCRepository.getFcFormcById(fc_form_c_id);
            fcFormCSurfaceSamplings.forEach(value -> {
                value.setFcFormC(temp);
                FcFormCSurfaceSampling cSurfaceSampling = fcFormCSurfaceSamplingRepository.save(value);
                sampling.add(cSurfaceSampling);
            });
            log.info("INFO ------------ saveFcFormCSurfaceSampling WITH forest clearance id ----> " + fc_form_c_id
                    + " ------>SUCCESS");
            return ResponseHandler.generateResponse("Save FC Form C Surface Sampling", HttpStatus.OK, " ", sampling);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving FC Form C Surface Sampling for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> getFCFormCSurfaceSampling(int id) throws PariveshException {
        try {

            return ResponseHandler.generateResponse("get form c surface sampling data list by fc_id", HttpStatus.OK, "",
                    fcFormCSurfaceSamplingRepository.findByFcFormCByID(id));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting FC Form C getFCFormCSurfaceSampling id- " + id, e);
        }

    }

    public ResponseEntity<Object> deleteFCFormCSurfaceSampling(int id) throws PariveshException {
        try {
            System.out.println("Deleting the Surface Sampling");
            Integer update = fcFormCSurfaceSamplingRepository.updateFcFormCById(id);
            if (update == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Forest Clearance Form C Surface Sampling", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form C deleteFCFormCSurfaceSampling id- " + id, e);
        }
    }

    /*
     * Compliance Report
     */

    public ResponseEntity<Object> saveFcFormCComplianceReport(List<FcFormCComplianceReport> fcFormCComplianceReports,
                                                              Integer fc_form_c_id) {
        try {
            List<FcFormCComplianceReport> reports = new ArrayList<>();
            FcFormC temp = fcFormCRepository.getFcFormcById(fc_form_c_id);
            fcFormCComplianceReports.forEach(value -> {
                value.setFcFormC(temp);
                FcFormCComplianceReport cComplianceReport = fcComplianceReportRepository.save(value);
                reports.add(cComplianceReport);
            });
            log.info("INFO ------------ saveFcFormCComplianceReport WITH forest clearance id ----> " + fc_form_c_id
                    + " ------>SUCCESS");
            return ResponseHandler.generateResponse("Save FC Form C Compliance Report", HttpStatus.OK, " ", reports);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form C Compliance Report for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> getFCFormCComplianceReport(Integer id) throws PariveshException {
        try {

            return ResponseHandler.generateResponse("get form c compliance Report data list by fc_id", HttpStatus.OK,
                    "", fcComplianceReportRepository.findByFcFormCByID(id));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting FC Form C getFCFormCSurfaceSampling id- " + id, e);
        }

    }

    public ResponseEntity<Object> deleteFCFormCComplianceReport(Integer id) throws PariveshException {
        try {

            Integer update = fcComplianceReportRepository.updateFcFormCById(id);
            if (update == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Forest Clearance Form C Compliance Report", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form C deleteFCFormCComplianceReport id- " + id, e);
        }
    }

    /*
     * Explored Forest Land --- fcFormCExploredForestLandRepository
     */

    public ResponseEntity<Object> saveFcFormCExploredForestReport(List<FcFormCExploredForestLand> fCExploredForestLands,
                                                                  Integer fc_form_c_id) {
        try {
            List<FcFormCExploredForestLand> reports = new ArrayList<>();
            FcFormC temp = fcFormCRepository.getFcFormcById(fc_form_c_id);
            fCExploredForestLands.forEach(value -> {
                value.setFcFormC(temp);
                FcFormCExploredForestLand cExploredForestLand = fcFormCExploredForestLandRepository.save(value);
                reports.add(cExploredForestLand);
            });
            log.info("INFO ------------ saveFcFormCExploredForestReport WITH forest clearance id ----> " + fc_form_c_id
                    + " ------>SUCCESS");
            return ResponseHandler.generateResponse("Save FC Form C Explored Forest Report", HttpStatus.OK, " ",
                    reports);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcFormCExploredForestReport for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> getFCFormCExploredForestReport(int id) throws PariveshException {
        try {

            return ResponseHandler.generateResponse("get form c explored Forest Report data list by fc_id",
                    HttpStatus.OK, "", fcFormCExploredForestLandRepository.findByFcFormCByID(id));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting FC Form C getFCFormCExploredForestReport id- " + id, e);
        }

    }

    public ResponseEntity<Object> deleteFCFormCExploredForestReport(int id) throws PariveshException {
        try {

            Integer update = fcFormCExploredForestLandRepository.updateFcFormCById(id);
            if (update == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Forest Clearance Form C Explored Forest Report", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form C deleteFCFormCExploredForestReport id- " + id, e);
        }
    }

    /*
     * Details of Machinery
     */

    public ResponseEntity<Object> saveFcFormCDetailsOfMachinery(
            List<FcFormCDetailsOfMachinery> fcFormCDetailsOfMachineries, Integer fc_form_c_id) {
        try {
            List<FcFormCDetailsOfMachinery> detailsMachinery = new ArrayList<>();
            FcFormC temp = fcFormCRepository.getFcFormcById(fc_form_c_id);
            fcFormCDetailsOfMachineries.forEach(value -> {
                value.setFcFormC(temp);
                FcFormCDetailsOfMachinery cDetailsOfMachinery = fcFormCDetailsOfMachineryRepository.save(value);
                detailsMachinery.add(cDetailsOfMachinery);
            });
            log.info("INFO ------------ saveFcFormCDetailsOfMachinery WITH forest clearance id ----> " + fc_form_c_id
                    + " ------>SUCCESS");
            return ResponseHandler.generateResponse("Save FC Form C Details of Machinery", HttpStatus.OK, " ",
                    detailsMachinery);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving FC Form C Details of Machinery for fc_form_c_id ");
        }
    }

    public ResponseEntity<Object> getFCFormCDetailsOfMachinery(int id) throws PariveshException {
        try {

            return ResponseHandler.generateResponse("get form c Details of Machinery data list by fc_id", HttpStatus.OK,
                    "", fcFormCDetailsOfMachineryRepository.findByFcFormCByID(id));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getting FC Form C getFCFormCDetailsOfMachinery id- " + id, e);
        }

    }

    public ResponseEntity<Object> deleteFCFormCDetailsOfMachinery(int id) throws PariveshException {
        try {
            System.out.println("Deleting the Details of Machinery");
            Integer update = fcFormCDetailsOfMachineryRepository.updateFcFormCById(id);
            if (update == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Forest Clearance Form C Details of Machinery", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form C deleteFCFormCDetailsOfMachinery id- " + id, e);
        }
    }

}