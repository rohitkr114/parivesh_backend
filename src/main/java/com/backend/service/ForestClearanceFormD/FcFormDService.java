package com.backend.service.ForestClearanceFormD;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.ForestClearanceFormD.*;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.ForestClearanceFormD.*;
import com.backend.response.ResponseHandler;
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

@Slf4j
@Service
@Transactional
public class FcFormDService {

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private FcFormDRepository fcFormDRepository;

    @Autowired
    private FcFormDProposedLandRepository fcFormDProposedLandRepository;

    @Autowired
    private FcFormDMiningPlanRepository fcFormDMiningPlanRepository;

    @Autowired
    private FcFormDUndertakingRepository fcFormDUndertakingRepository;

    @Autowired
    private FcFormDLegalStatusRepository fcFormDLegalStatusRepository;

    @Autowired
    private FcFormDProposedDiversionRepository fcFormDProposedDiversionRepository;

    @Autowired
    private FcFormDProposedDiversionDetailsRepository fcFormDProposedDiversionDetailsRepository;

    @Autowired
    private DocumentDetailsRepository documentDetailsRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private ServerUtil util;

    @Autowired
    private NotificationService notificationSevice;

    @Autowired
    private ProponentApplicationService proponentApplicationService;

    public ResponseEntity<Object> saveFCFormD(FcFormD fcFormD, Integer caf_Id, Integer clearance_id,
                                              HttpServletRequest request) throws PariveshException {
        /*
         * Step 1: FcFormD
         */

        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(caf_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
            fcFormD.setCommonFormDetail(cafDetail);

            if (fcFormD.getId() != null && fcFormD.getId() != 0) {

                FcFormD form = fcFormDRepository.findById(fcFormD.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("FC form D not found with id"));

                fcFormD.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                return ResponseHandler.generateResponse("Save FC Form D", HttpStatus.OK, "",
                        fcFormDRepository.save(fcFormD));
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            ProponentApplications applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {
                String proposal_no = "FP/"
                        + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                        .getState_abbr()
                        + "/" + fcFormD.getProject_category() + "/" + 400000 + "/"
                        + String.valueOf(LocalDate.now().getYear());
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                fcFormD.setProposal_no(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, fcFormD));
                    fcFormD.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }
            FcFormD temp2 = fcFormDRepository.save(fcFormD);
            Applications app = applicationsRepository.findById(clearance_id).get();
            applications.setCaf_id(caf_Id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(fcFormD.getState_code())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getName());
            applications.setState_id(stateRepository.getStateByCode(fcFormD.getState_code())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state code")).getCode());
            applications.setProjectDetails(cafDetail.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setLast_remarks("test");
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            proponentApplicationRepository.save(applications);

//            OtherPropString.put("Form", app.getDd_name());
//            OtherPropString.put("Project Category", fcFormD.getProject_category());
//            proponentApplicationService.updateOtherProperty(temp2.getId(), OtherPropString);

            return ResponseHandler.generateResponse("Save FC Form D ", HttpStatus.OK, "", temp2);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving for Fc form D ");
        }
    }

    public ResponseEntity<Object> saveFcFormDProposedLand(FcFormDProposedLand fcFormDProposedLand,
                                                          Integer fc_form_d_id) {
        /*
         * Step 2: Proposed Land
         */

        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            if (fcFormDProposedLand.getId() == null || fcFormDProposedLand.getId() == 0) {
                FcFormDProposedLand fcFormDProposedLand2 = fcFormDProposedLandRepository
                        .getDataByFcFormDId(fc_form_d_id);
                if (fcFormDProposedLand2 != null) {
                    fcFormDProposedLand.setId(fcFormDProposedLand2.getId());
                }
            }

            FcFormD temp = fcFormDRepository.getFcFormDById(fc_form_d_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Fc Form D Proposed Land not found with id"));
            fcFormDProposedLand.setFcFormD(temp);
            log.info("INFO ------------ saveFcFormDProposedLand WITH forest clearance Form D id ----> " + fc_form_d_id
                    + " ------>SUCCESS");
            
            /*
             * Updating the Proponent Application JSON String
             */
            ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalNo(temp.getProposal_no());
            Applications app= proponentApplications.getApplications();
            OtherPropString.put("Form", app.getDd_name());
            if (temp.getProject_category()=="MIND") {
                OtherPropString.put("Project Category", "Mining");
            }else {
                OtherPropString.put("Project Category", "Non-Mining");
            }
            OtherPropString.put("Forest Area", fcFormDProposedLand.getTotal_proposed_diversion_area().toString());
            proponentApplicationService.updateOtherProperty(fc_form_d_id, OtherPropString);

            return ResponseHandler.generateResponse("Save FcFormD Proposed Land", HttpStatus.OK, " ",
                    fcFormDProposedLandRepository.save(fcFormDProposedLand));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in saving Forest Form D Proposed Diversion for fc_form_d_id " + fc_form_d_id);
        }
    }

    public ResponseEntity<Object> saveFcFormDMiningPlan(FcFormDMiningPlan fcFormDMiningPlan, Integer fc_form_d_id) {
        /*
         * Step 3: Mining
         */
        try {
            if (fcFormDMiningPlan.getId() == null || fcFormDMiningPlan.getId() == 0) {
                FcFormDMiningPlan fcFormDMiningPlan2 = fcFormDMiningPlanRepository.getDataByFcFormDId(fc_form_d_id);
                if (fcFormDMiningPlan2 != null) {
                    fcFormDMiningPlan.setId(fcFormDMiningPlan2.getId());
                }
            }

            FcFormD temp = fcFormDRepository.getFcFormDById(fc_form_d_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Fc Form D Mining Plan not found with id"));
            fcFormDMiningPlan.setFcFormD(temp);
            log.info("INFO ------------ saveFcFormDMiningPlan WITH forest clearance Form D id ----> " + fc_form_d_id
                    + " ------>SUCCESS");
            return ResponseHandler.generateResponse("Save FcFormD Mining Plan", HttpStatus.OK, " ",
                    fcFormDMiningPlanRepository.save(fcFormDMiningPlan));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form D Mining Plan for fc_form_d_id " + fc_form_d_id);
        }
    }

    public ResponseEntity<Object> saveFcFormDUndertaking(FcFormDUndertaking fcFormDUndertaking, Integer fc_form_d_id, Boolean is_submit) {
        /*
         * Step 5: Undertaking
         */
        try {
            if (fcFormDUndertaking.getId() == null || fcFormDUndertaking.getId() == 0) {
                FcFormDUndertaking fcFormDUndertaking2 = fcFormDUndertakingRepository.getDataByFcFormDId(fc_form_d_id);
                if (fcFormDUndertaking2 != null) {
                    fcFormDUndertaking.setId(fcFormDUndertaking2.getId());
                }
            }

            FcFormD temp = fcFormDRepository.getFcFormDById(fc_form_d_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Fc Form D Undertaking not found with id"));
            fcFormDUndertaking.setFcFormD(temp);
            log.info("INFO ------------ saveFcFormDUndertaking WITH forest clearance Form D id ----> " + fc_form_d_id
                    + " ------>SUCCESS");

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());

            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                    || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (fcFormDUndertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(fcFormDUndertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }

            if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }

            return ResponseHandler.generateResponse("Save FcFormD Mining Plan", HttpStatus.OK, " ",
                    fcFormDUndertakingRepository.save(fcFormDUndertaking));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saving Forest Form D Undertaking for fc_form_d_id " + fc_form_d_id);
        }
    }

    public ResponseEntity<Object> getFcFormD(Integer fc_form_d_id, Integer step) {
        FcFormD form = null;
        try {
            if (step != null) {
                if (step == 1) {
                    form = fcFormDRepository.getFcFormDDetailsById(fc_form_d_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form D Form not found with id"));

                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);
                } else if (step == 2) {
                    /*
                     * Proposed Land
                     */

                    form = fcFormDRepository.getFcFormDDetailsById(fc_form_d_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form D Form not found with id"));

                    form.setFcFormDProposedLand(fcFormDProposedLandRepository.getFcFormDProposedLand(fc_form_d_id));

                    List<Object[]> docData = fcFormDProposedLandRepository.getDocuments(fc_form_d_id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.getFcFormDProposedLand().setGeo_referenced_map(
                                    obj[0] != null ? documentDetailsRepository.findById((Integer) obj[0]).get() : null);
                            form.getFcFormDProposedLand().setLetter_of_intent(
                                    obj[1] != null ? documentDetailsRepository.findById((Integer) obj[1]).get() : null);
                            form.getFcFormDProposedLand().setCopy_map_outer_boundary(
                                    obj[2] != null ? documentDetailsRepository.findById((Integer) obj[2]).get() : null);
                        }
                    }

                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                } else if (step == 3) {
                    /*
                     * Mining Plan
                     */
                    form = fcFormDRepository.getFcFormDDetailsById(fc_form_d_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form D Form not found with id"));

                    form.setFcFormDMiningPlan(fcFormDMiningPlanRepository.getFcFormDMiningPlan(fc_form_d_id));
                    List<Object[]> docData = fcFormDMiningPlanRepository.getDocuments(fc_form_d_id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            form.getFcFormDMiningPlan().setDpr_copy(
                                    obj[0] != null ? documentDetailsRepository.findById((Integer) obj[0]).get() : null);
                            form.getFcFormDMiningPlan().setCopy_approved_mining_plan(
                                    obj[1] != null ? documentDetailsRepository.findById((Integer) obj[1]).get() : null);

                        }
                    }

                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                } else if (step == 4) {
                    /*
                     * Additional Information
                     */
                    form = fcFormDRepository.getFcFormDDetailsById(fc_form_d_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form D. Form not found with id"));

                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);

                } else if (step == 5) {
                    /*
                     * Undertaking
                     */

                    form = fcFormDRepository.getFcFormDDetailsById(fc_form_d_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Fc Form D Form not found with id"));

                    form.setFcFormDUndertaking(
                            fcFormDUndertakingRepository.getFcFormCUndertakingDetailsById(fc_form_d_id));
                    form.setProponentApplications(proponentApplicationRepository.getAppById(form.getId()) != null
                            ? proponentApplicationRepository.getAppById(form.getId())
                            : null);
                }

            }
            return ResponseHandler.generateResponse("Retrieve getFcFormD with id ----->>>" + fc_form_d_id,
                    HttpStatus.OK, "", form);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Retrieving getFcFormD with id ----->>>" + fc_form_d_id, e);
        }
    }

    public ResponseEntity<Object> deleteFCFormDLegalStatus(Integer id) throws ProjectNotFoundException {
        try {
            FcFormDLegalStatus fcFormDLegalStatus = fcFormDLegalStatusRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part D LegalStatus not found with ID"));
            fcFormDLegalStatus.setIs_active(false);
            fcFormDLegalStatus.setIs_deleted(true);

            fcFormDLegalStatusRepository.save(fcFormDLegalStatus);
            return ResponseHandler.generateResponse("FCFormC Deleted successfully", HttpStatus.OK, "NO ERROR",
                    fcFormDLegalStatus);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FCFormC Part D deleteFCFormDLegalStatus id- " + e);
        }
    }

    public ResponseEntity<Object> addFCFormDLegalStatus(List<FcFormDLegalStatus> fcFormDLegalStatus, Integer FcFormDId)
            throws ProjectNotFoundException {
        try {
            FcFormD fcFormD = fcFormDRepository.getFcFormDById(FcFormDId)
                    .orElseThrow(() -> new ProjectNotFoundException("FcFormD not found"));
            List<FcFormDLegalStatus> fcFormDLegalStatus3 = new ArrayList<>();
            List<FcFormDLegalStatus> fcFormDLegalStatus2 = fcFormDLegalStatus.stream().map(value -> {
                value.setFcFormD(fcFormD);
                fcFormDLegalStatus3.add(fcFormDLegalStatusRepository.save(value));
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("FCFormDLegalStatus Added", HttpStatus.OK, "NO ERROR",
                    fcFormDLegalStatus3);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0] + "FcFormD Id"
                    + FcFormDId);
            throw new PariveshException("Error in Saving FCFormC Part D addFCFormDLegalStatus id- " + e);
        }
    }

    public ResponseEntity<Object> getFcFormDLegalStatus(Integer id) throws ProjectNotFoundException {
        try {

            List<FcFormDLegalStatus> fcFormDLegalStatus = fcFormDLegalStatusRepository.findByFcFormDLegalStatus(id);

            return ResponseHandler.generateResponse("Get FCFormDLegalStatus ", HttpStatus.OK, "NO ERROR",
                    fcFormDLegalStatus);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FCFormC Part D deleteFCFormDLegalStatus id- " + id);
        }
    }

    public ResponseEntity<Object> saveFcFormDProposedDiversion(List<FcFormDProposedDiversion> fcFormDProposedDiversions,
                                                               Integer fc_d_id) {
        List<FcFormDProposedDiversion> diversions = new ArrayList<>();
        if (fc_d_id != null) {
            FcFormD temp = fcFormDRepository.findById(fc_d_id).get();
            fcFormDProposedDiversions.forEach(value -> {
                value.setFcFormD(temp);
                if (value.getDiversion_map_copy() != null) {
                    value.getDiversion_map_copy().setProposal_no(temp.getProposal_no());
                }
                FcFormDProposedDiversion fcFormDProposedDiversion = fcFormDProposedDiversionRepository.save(value);
                diversions.add(fcFormDProposedDiversion);
            });
            log.info("INFO ------------ saveForestProposedDiversionsDetails WITH forest clearance id ----> " + fc_d_id
                    + " ---- SAVE- SUCCESS");
        }
        return ResponseHandler.generateResponse("Save Forest Proposed Diversion Data", HttpStatus.OK, " ", diversions);
    }

    public ResponseEntity<Object> getFcFormDProposedDiversion(Integer fc_d_id) {
        List<FcFormDProposedDiversion> diversion = new ArrayList<FcFormDProposedDiversion>();
        if (fc_d_id != null) {
            log.info("INFO ------------ getFcFormDProposedDiversionDetails WITH forest clearance form D id ----> "
                    + fc_d_id + " ---- RETRIEVING- SUCCESS");
            diversion = fcFormDProposedDiversionRepository.findByFCFormDID(fc_d_id);
        }
        return ResponseHandler.generateResponse("getFcFormDProposedDiversion by fc_d_id", HttpStatus.OK, "", diversion);
    }

    public ResponseEntity<Object> deleteFcFormDProposedDiversion(Integer id) {

        FcFormDProposedDiversion temp = fcFormDProposedDiversionRepository.getById(id);
        temp.setIs_deleted(true);
        log.info("INFO ------------ deleteForestClearancePropesdDiversionData WITH FC id ----> " + id
                + " ---- DELETING - SUCCESS");
        return ResponseHandler.generateResponse("Forest Clearance Proposed Diversion", HttpStatus.OK, "",
                fcFormDProposedDiversionRepository.save(temp));
    }

    public ResponseEntity<Object> deleteFCFormDProposedDiversionDetails(Integer id) {

        FcFormDProposedDiversionDetails details = fcFormDProposedDiversionDetailsRepository.findById(id).orElseThrow();
        details.setIs_deleted(true);
        return ResponseHandler.generateResponse("DELETE TRUE fcProposedDiversionDetails BY ID ------>" + id,
                HttpStatus.OK, "NO ERROR", details);
    }

    private String generateProposalNo(int maxcount, FcFormD fcFormD) {

        String proposalNo = "FP/" + stateRepository.getStateByCode(fcFormD.getState_code())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/" + fcFormD.getProject_category() + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());
        proposalNo = proposalNo.replaceAll("\\s", "");
        return proposalNo;
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

}