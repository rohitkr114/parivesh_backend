package com.backend.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Activities;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcConsultant;
import com.backend.model.EcEnclosureDetail;
import com.backend.model.EcOthersDetail;
import com.backend.model.EcProductDetail;
import com.backend.model.EcProjectDetail;
import com.backend.model.EcSectorFormDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ActivityRepository;
import com.backend.repository.postgres.ActivitySubActivitySectorRepository;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.ECEnclosureDetailRepository;
import com.backend.repository.postgres.ECOthersDetailRepository;
import com.backend.repository.postgres.ECProductDetailRepository;
import com.backend.repository.postgres.ECProjectDetailRepository;
import com.backend.repository.postgres.EcConsultantRepository;
import com.backend.repository.postgres.EcSectorFormDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.UserRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EnvironmentClearanceService {

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private ECProductDetailRepository ecProductDetailRepository;

    @Autowired
    private ECProjectDetailRepository ecProjectDetailRepository;

    @Autowired
    private ECOthersDetailRepository ecOthersDetailRepository;

    @Autowired
    private ECEnclosureDetailRepository ecEnclosureDetailRepository;

    @Autowired
    private EcConsultantRepository ecConsultantRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private EcSectorFormDetailsRepository ecSectorFormDetailsRepository;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;


    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;


    @Autowired
    private NotificationService notificationSevice;

    public EnvironmentClearence saveEnvironmentClearance(Integer caf_id, EnvironmentClearence environmentClearence,
                                                         HttpServletRequest request, Integer clearance_id) {
        try {
            if (clearance_id == null) {
                clearance_id = 5;
            }
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
            environmentClearence.setCommonFormDetail(commonForm);
            if (environmentClearence.getId() != null && environmentClearence.getId() != 0) {
                log.info("INFO ------------ saveEnvironmentClearance WITH EcID NOT NULL OR ZERO for caf id ----> "
                        + caf_id + "- SUCCESS");
                EnvironmentClearence form = environmentClearenceRepository.findByFormId(environmentClearence.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
                environmentClearence.setProposal_no(form.getProposal_no());
//			return ResponseHandler.generateResponse("Save Common Form ",HttpStatus.OK,"",commonFormDetailRepository.save(commonFormDetail));
                log.info("INFO ------------ saveEnvironmentClearance for caf id ----> " + caf_id
                        + " --- SAVED - SUCCESS");
                return environmentClearenceRepository.save(environmentClearence);
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            ProponentApplications applications = new ProponentApplications();
            if (commonForm.getParent_id() == null) {
                if (tempClearances.isEmpty()) {
                    log.info(
                            "INFO ------------ saveEnvironmentClearance no proponent applications found for caf id ----> "
                                    + caf_id + "---- EMPTY LIST");
                    String proposal_no = null;
                    if (environmentClearence.getProject_category().equalsIgnoreCase("A")) {
                        proposal_no = "IA/"
                                + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                                .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                                .getState_abbr()
                                + "/"
                                + activitySectorRepository.getSector(environmentClearence.getMajor_activity_id(),
                                environmentClearence.getMajor_sub_activity_id()).getSector_code()
                                + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                    } else {
                        if (environmentClearence.getIs_proposed_required()) {
                            proposal_no = "IA/"
                                    + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                                    .orElseThrow(
                                            () -> new ProjectNotFoundException("state not found with code"))
                                    .getState_abbr()
                                    + "/"
                                    + activitySectorRepository.getSector(environmentClearence.getMajor_activity_id(),
                                    environmentClearence.getMajor_sub_activity_id()).getSector_code()
                                    + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                            // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                        } else {
//					ResponseEntity<List<sieea>> resp = sieaaStatusAPI.getSieeaStatus(projectDetailRepository
//							.findById(commonForm.getProject_id())
//							.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//					System.out.println("SIEEA API Response:  ------------" + resp.getBody().toString());
//					if (resp.getBody().get(0).getStatus().equals("active")) {
                            proposal_no = "SIA/"
                                    + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                                    .orElseThrow(
                                            () -> new ProjectNotFoundException("state not found with code"))
                                    .getState_abbr()
                                    + "/"
                                    + activitySectorRepository.getSector(environmentClearence.getMajor_activity_id(),
                                    environmentClearence.getMajor_sub_activity_id()).getSector_code()
                                    + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                            // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);

                        }

                    }
                    applications.setProposal_sequence(400000);
                    proposal_no = proposal_no.replaceAll("\\s", "");
                    applications.setProposal_no(proposal_no);
                    environmentClearence.setProposal_no(proposal_no);
                } else {
                    log.info("INFO ------------ saveEnvironmentClearance proponent applications for caf id ----> "
                            + caf_id + " ---- FOUND - SUCCESS");
                    Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                            .max(Comparator.comparing(Integer::valueOf)).get();
                    if (maxCount != null) {
                        applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                        applications.setProposal_no(generateProposalNo(maxCount, environmentClearence, commonForm));
                        environmentClearence.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                    }
                }
                if (environmentClearence.getFc_approval_copy() != null) {
                    environmentClearence.getFc_approval_copy()
                            .setProposal_no(environmentClearence.getProposal_no().replaceAll("\\s", ""));
                }
                if (environmentClearence.getUpload_nbwl_recomm() != null) {
                    environmentClearence.getUpload_nbwl_recomm()
                            .setProposal_no(environmentClearence.getProposal_no().replaceAll("\\s", ""));
                }
                if (environmentClearence.getInterlink_ec_letter() != null) {
                    environmentClearence.getInterlink_ec_letter()
                            .setProposal_no(environmentClearence.getProposal_no().replaceAll("\\s", ""));
                }
            }
            log.info("INFO ------------ saveEnvironmentClearance SAVED at environmentClearence ----> "
                    + environmentClearence.getId() + " - SUCCESS");
            EnvironmentClearence temp2 = environmentClearenceRepository.save(environmentClearence);
            Applications app = applicationsRepository.findById(clearance_id).get();
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

            /*
             * applications.setState_id(stateRepository.getStateByCode(commonForm.
             * getProjectDetails().getMain_state()) .orElseThrow(() -> new
             * ProjectNotFoundException("state not found with code")).getId());
             */

            applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(commonForm.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            applications.setIs_legacy_proposal(environmentClearence.getIs_legacy_proposal());
            // ---- for CopyProposal Part C ----- //
            if (environmentClearence.getLegacy_proposal_type()!=null) {
				applications.setLegacy_proposal_type(environmentClearence.getLegacy_proposal_type());
			}
			proponentApplicationRepository.save(applications);

            /*
             * Updating the Proponent Application JSON String
             */
            CommonFormDetail caf= commonFormDetailRepository.findById(caf_id).orElse(null);
            if (temp2.getProject_category().equalsIgnoreCase("B2") && caf.getCafOthers().getIs_any_violayion_involved().equalsIgnoreCase("NO")){
                OtherPropString.put("Proposal For", "Fresh EC");
            }else {
                OtherPropString.put("Proposal For", app.getGeneral_name());
            }
            String activity_name = environmentClearenceRepository.getMajorActivityName(temp2.getId());
            String itemNo = environmentClearenceRepository.getItemNo(temp2.getId());
//			System.out.println("activity:"+ activity_name);
            OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
            OtherPropString.put("Sector",
                    activitySectorRepository.getSector(environmentClearence.getMajor_activity_id(),
                            environmentClearence.getMajor_sub_activity_id()).getSector_code());

//			System.out.println(OtherPropString);

            updateOtherPropertyService.updateOtherProperty(temp2.getId(), OtherPropString);

            return temp2;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC saveEnvironmentClearance for ecPartBId- " + caf_id, e);
        }
    }

    public EcProductDetail saveProductDetail(Integer ecId, EcProductDetail ecProductDetail) throws PariveshException {
        try {
            if (ecProductDetail.getId() == null || ecProductDetail.getId() == 0) {
                EcProductDetail ecProductDetail2 = ecProductDetailRepository.getRecordExist(ecId).orElse(null);
                if (ecProductDetail2 != null) {
                    ecProductDetail.setId(ecProductDetail2.getId());
                }
            }
            EnvironmentClearence temp = environmentClearenceRepository.findById(ecId)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
            ecProductDetail.setEnviromentClearence(temp);
            if (ecProductDetail.getBaselineDataCollectionSummary() != null) {
                ecProductDetail.getBaselineDataCollectionSummary().setProposal_no(temp.getProposal_no());
            }
            if (ecProductDetail.getBaselineMonitoingLocationMap() != null) {
                ecProductDetail.getBaselineMonitoingLocationMap().setProposal_no(temp.getProposal_no());
            }
            EcProductDetail ecProductDetail2 = ecProductDetailRepository.save(ecProductDetail);
            log.info("INFO ------------ saveProductDetail product detail WITH ecId---->" + ecId
                    + " ---- SAVED - SUCCESS");
            return ecProductDetail2;
        } catch (Exception e) {
            log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC saveProductDetail for ecId- " + ecId, e);
        }
    }

    public EcProjectDetail saveProjectDetail(Integer ecId, EcProjectDetail ecProjectDetail) throws PariveshException {
        try {
            if (ecProjectDetail.getId() == null || ecProjectDetail.getId() == 0) {
                EcProjectDetail ecProjectDetail2 = ecProjectDetailRepository.getRecordExist(ecId).orElse(null);
                if (ecProjectDetail2 != null) {
                    ecProjectDetail.setId(ecProjectDetail2.getId());
                }
            }
            EnvironmentClearence temp = environmentClearenceRepository.findById(ecId)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
            ecProjectDetail.setEnviromentClearence(temp);
            if (ecProjectDetail.getChronologyNote() != null) {
                ecProjectDetail.getChronologyNote().setProposal_no(temp.getProposal_no());
            }
            if (ecProjectDetail.getIndustrialAreaNotificatnCopy() != null) {
                ecProjectDetail.getIndustrialAreaNotificatnCopy().setProposal_no(temp.getProposal_no());
            }
            if (ecProjectDetail.getSczmaRecommLetter() != null) {
                ecProjectDetail.getSczmaRecommLetter().setProposal_no(temp.getProposal_no());
            }
            if (ecProjectDetail.getEc_letter() != null) {
                ecProjectDetail.getEc_letter().setProposal_no(temp.getProposal_no());
            }
            if (ecProjectDetail.getEc_nia_letter() != null) {
                ecProjectDetail.getEc_nia_letter().setProposal_no(temp.getProposal_no());
            }
            if (!ecProjectDetail.getEcAmendmentTransferDetails().isEmpty()) {
                ecProjectDetail.getEcAmendmentTransferDetails().forEach(value -> {
                    if (value != null) {
                        value.setProposalNo(temp.getProposal_no());
                    }
                });
            }
            if (!ecProjectDetail.getEcStatus().isEmpty()) {
                ecProjectDetail.getEcStatus().forEach(value -> {
                    if (value != null) {
                        value.setProposal_no(temp.getProposal_no());
                    }
                });
            }
            EcProjectDetail ecProjectDetail2 = ecProjectDetailRepository.save(ecProjectDetail);
            log.info("INFO ------------ saveProjectDetail project detail WITH ecId---->" + ecId
                    + " ---- SAVED - SUCCESS");
            return ecProjectDetail2;
        } catch (Exception e) {
            log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC saveProjectDetail for ecId- " + ecId, e);
        }
    }

    public EcOthersDetail saveOthersDetail(Integer ecId, EcOthersDetail ecOthersDetail, Boolean is_submit,
                                           HttpServletRequest request) throws PariveshException {
        try {
            log.info("-----------------> is_submit --> " + is_submit);
            if (ecOthersDetail.getId() == null || ecOthersDetail.getId() == 0) {
                EcOthersDetail ecOthersDetail2 = ecOthersDetailRepository.getRecordExist(ecId).orElse(null);
                if (ecOthersDetail2 != null) {
                    ecOthersDetail.setId(ecOthersDetail2.getId());
                }
            }

            String proposalNo = proponentApplicationRepository.getProposalNo(ecId);
            String identificationNo = ecOthersDetailRepository.getIdentificationNo(proposalNo);
            ecOthersDetail.setIdentification_no(identificationNo);
            log.info(identificationNo);

            EnvironmentClearence temp = environmentClearenceRepository.findById(ecId)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
            ecOthersDetail.setEnviromentClearence(temp);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(ecId);
            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
                    .findByCaf((Integer) environmentClearenceRepository.getCafByEcId(temp.getId()).get(0)[0]);

            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())) {
                if (commonFormDetailRepository
                        .findByCafId((Integer) environmentClearenceRepository.getCafByEcId(temp.getId()).get(0)[0])
                        .getParent_id() == null) {
                    proponentApplications.setProposal_no(generateProposalNo(
                            proponentApplications.getProposal_sequence() - 1, temp, cafDetail.get()));
                    temp.setProposal_no(proponentApplications.getProposal_no());
                    environmentClearenceRepository.save(temp);
                }
            }
            
            
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) || 
            		proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_submission_date(new Date());
            }
            if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);
            } else if (is_submit == true) {
                proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());

                /*
                 * Sending Notification For First Submit
                 */
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());
                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }
            
            /*

            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                    || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (ecOthersDetail.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(ecOthersDetail.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }

            if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.toString())) {
                proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);
            } else {
                if (is_submit == true) {
                    proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
                }
            }
            */

            if (temp.getIs_for_old_proposal() == true)
                proponentApplications.setMigration_status(true);

            log.info("is_submit --> " + is_submit);

            proponentApplicationRepository.save(proponentApplications);

            commonFormDetailService.saveCommonForm(cafDetail.get());
            EcOthersDetail ecOthersDetail2 = ecOthersDetailRepository.save(ecOthersDetail);

            /*if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());

            }*/
            return ecOthersDetail2;
        } catch (Exception e) {
            log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC saveProjectDetail for ecId- " + ecId, e);
        }
    }

    public EcEnclosureDetail saveEnclosureDetail(Integer ecId, EcEnclosureDetail ecEnclosureDetail)
            throws PariveshException {
        try {
            if (ecEnclosureDetail.getId() == null || ecEnclosureDetail.getId() == 0) {
                EcEnclosureDetail ecEnclosureDetail2 = ecEnclosureDetailRepository.getRecordExist(ecId).orElse(null);
                if (ecEnclosureDetail2 != null) {
                    ecEnclosureDetail.setId(ecEnclosureDetail2.getId());
                }
            }
            EnvironmentClearence temp = environmentClearenceRepository.findById(ecId)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
            ecEnclosureDetail.setEnviromentClearence(temp);
            if (ecEnclosureDetail.getBuildingConceptualPlan() != null) {
                ecEnclosureDetail.getBuildingConceptualPlan().setProposal_no(temp.getProposal_no());
            }
            if (ecEnclosureDetail.getComponentLayoutPlan() != null) {
                ecEnclosureDetail.getComponentLayoutPlan().setProposal_no(temp.getProposal_no());
            }
            if (ecEnclosureDetail.getFeasibilityDrawingPlan() != null) {
                ecEnclosureDetail.getFeasibilityDrawingPlan().setProposal_no(temp.getProposal_no());
            }
            if (ecEnclosureDetail.getLetterOfIntentMining() != null) {
                ecEnclosureDetail.getLetterOfIntentMining().setProposal_no(temp.getProposal_no());
            }
            EcEnclosureDetail ecEnclosureDetail2 = ecEnclosureDetailRepository.save(ecEnclosureDetail);
            log.info("INFO ------------ saveEnclosureDetail WITH ecId---->" + ecId + " ---- SAVED - SUCCESS");
            return ecEnclosureDetail2;
        } catch (Exception e) {
            // log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC saveEnclosureDetail for ecId- " + ecId, e);
        }
    }

    public EcConsultant saveConsultantDetail(Integer ecId, EcConsultant ecConsultant) throws PariveshException {
        try {
            if (ecConsultant.getId() == null || ecConsultant.getId() == 0) {
                EcConsultant ecConsultant2 = ecConsultantRepository.getRecordExist(ecId).orElse(null);
                if (ecConsultant2 != null) {
                    ecConsultant.setId(ecConsultant2.getId());
                }
            }
            EnvironmentClearence temp = environmentClearenceRepository.findById(ecId)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
            ecConsultant.setEnviromentClearence(temp);
            EcConsultant ecConsultant2 = ecConsultantRepository.save(ecConsultant);
            log.info("INFO ------------ saveConsultantDetail WITH ecId---->" + ecId + " ---- SAVED - SUCCESS");
            return ecConsultant2;
        } catch (Exception e) {
            // log.error(e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC saveConsultantDetail for ecId- " + ecId, e);
        }
    }

    public EnvironmentClearence getEnvironmentClearence(Integer id) throws PariveshException {
        try {
            EnvironmentClearence environmentClearence = environmentClearenceRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
            environmentClearence.setProponentApplications(
                    proponentApplicationRepository.getApplicationByProposalId(environmentClearence.getId()));
            log.info("INFO ------------ getEnvironmentClearence WITH EcID ---->" + id
                    + " ---- FOUND and RETRIEVED - SUCCESS");
            return environmentClearence;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting EC getEnvironmentClearence for Id- " + id, e);
        }
    }

    /*
     * CRUD for EC Sector Forms
     */

    // Get SectorForm Details
    public ResponseEntity<Object> getSectorForm() {
        try {
            log.info("INFO ------------ getSectorForm ALL RETRIEVED - SUCCESS");
            return ResponseHandler.generateResponse("Get EC Sector Form", HttpStatus.OK, "",
                    ecSectorFormDetailsRepository.findAll());
        } catch (Exception ex) {
            log.info("ERROR ------------ EXPECTATION_FAILED: getSectorForm NOT RETRIEVED - FAILURE");
            return ResponseHandler.generateResponse("Get EC Sector Form", HttpStatus.EXPECTATION_FAILED, "",
                    ex.getMessage());
        }
    }

    // Get SectorForm by Id
    public ResponseEntity<Object> getSectorFormbyId(Integer sectorFormId) {
        log.info("INFO ------------ getSectorFormbyId WITH Sector Form ID ----> " + sectorFormId + " ---- RETRIEVING");
        return ResponseHandler.generateResponse("Get EC Sector Form by Id", HttpStatus.OK, "",
                ecSectorFormDetailsRepository.findById(sectorFormId)
                        .orElseThrow(() -> new ProjectNotFoundException("Ec Sector Form not present by this Id")));

    }

    // Save and Update
    public ResponseEntity<Object> saveSectorForm(List<EcSectorFormDetails> ecSectorFormDetails, Integer ActivityId) {
        try {
            Activities actTemp;
            if (ActivityId != null) {
                actTemp = activityRepository.findById(ActivityId)
                        .orElseThrow(() -> new ProjectNotFoundException("Activity Not Found"));

                List<EcSectorFormDetails> temp2 = ecSectorFormDetails.stream().map(val -> {
                    val.setSectorActivities(actTemp);
                    return val;
                }).collect(Collectors.toList());
                log.info("INFO ------------ saveSectorForm WITH Activity ID ----> " + ActivityId + " SAVING");
                return ResponseHandler.generateResponse("Save EC Sector Form", HttpStatus.OK, "",
                        ecSectorFormDetailsRepository.saveAll(temp2));
            }
            log.info("INFO ------------ saveSectorForm WITH Sector Form Activity ID ----> " + ActivityId
                    + " ---- SAVING ALL");
            return ResponseHandler.generateResponse("Save EC Sector Form", HttpStatus.OK, "",
                    ecSectorFormDetailsRepository.saveAll(ecSectorFormDetails));
        } catch (Exception ex) {
            log.info("ERROR ------------ EXPECTATION_FAILED: saveSectorForm Form Activity ID ----> " + ActivityId
                    + " ---- FAILURE");
            return ResponseHandler.generateResponse("Save EC Sector Form", HttpStatus.EXPECTATION_FAILED, "",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> updateSectorForm(Integer ecSectorFormId, Integer ActivityId, String formName)
            throws PariveshException {
        try {
            EcSectorFormDetails ecSectorFormDetails = ecSectorFormDetailsRepository.findById(ecSectorFormId)
                    .orElseThrow(() -> new ProjectNotFoundException("Sector Form doesnot exist"));
            if (ActivityId != null) {
                Activities temp = activityRepository.findById(ActivityId)
                        .orElseThrow(() -> new ProjectNotFoundException("Activity doesnot exist"));
                ecSectorFormDetails.setSectorActivities(temp);
            }
            if (formName != null) {
                ecSectorFormDetails.setForm(formName);
            }
            log.info("INFO ------------ updateSectorForm WITH Activity ID ----> " + ActivityId + " ---- SAVING");
            return ResponseHandler.generateResponse("Update EC Sector Form", HttpStatus.OK, "",
                    ecSectorFormDetailsRepository.save(ecSectorFormDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in updating EC sectorForm for ID- " + ActivityId, e);
        }

    }

    // Delete Sector Form
    public ResponseEntity<Object> deleteSectorForm(List<EcSectorFormDetails> ecSectorFormDetails) {
        try {

            for (EcSectorFormDetails details : ecSectorFormDetails) {

                Integer upadate = ecSectorFormDetailsRepository.updateSectorForm(details.getId());
                if (upadate == 0) {
                    throw new PariveshException("ID NOT FOUND - " + details.getId());
                }
            }
            return ResponseHandler.generateResponse("Save EC Sector Form", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC sector form ", e);
        }
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, EnvironmentClearence form, CommonFormDetail commonForm) {
        if (form.getProject_category().equalsIgnoreCase("A")) {

            String cafId = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
                    + activitySectorRepository.getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
                    .getSector_code()
                    + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
            // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
            cafId = cafId.replaceAll("\\s", "");
            return cafId;
        } else {
            if (form.getIs_proposed_required()) {
                String cafId = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
                return cafId;
            } else {
//				ResponseEntity<List<sieea>> resp = sieaaStatusAPI
//						.getSieeaStatus(projectDetailRepository.findById(commonForm.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//				if (resp.getBody().get(0).getStatus().equals("active")) {
                String cafId = "SIA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(form.getMajor_activity_id(), form.getMajor_sub_activity_id())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
                return cafId;

            }
        }
    }

    public ResponseEntity<Object> getTORStatus(Integer proposalId) {
        try {
            log.info("In getTORStatus with proposalId" + proposalId);

            return ResponseHandler.generateResponse("Get TORStatus", HttpStatus.OK, "",
                    environmentClearenceRepository.getTORStatus(proposalId));

        } catch (Exception ex) {
            log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
            throw new PariveshException("Error in get TORStatus");
        }

    }

}
