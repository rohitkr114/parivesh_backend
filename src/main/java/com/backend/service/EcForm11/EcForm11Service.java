package com.backend.service.EcForm11;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm11.*;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm11.*;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.UpdateOtherPropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EcForm11Service {

    @Autowired
    private EcForm11SPCBDetailsRepository ecForm11SPCBDetailsRepository;

    @Autowired
    private EcForm11OtherDetailsRepository ecForm11OtherDetailsRepository;

    @Autowired
    private EcForm11ProjectDetailsRepository ecForm11ProjectDetailsRepository;

    @Autowired
    private EcForm11ProjectActivityDetailsRepository ecForm11ProjectActivityDetailsRepository;

    @Autowired
    private EcForm11UndertakingRepository ecForm11UndertakingRepository;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private EcPartCRepository ecPartCRepository;

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SubActivityRepository subActivityRepository;

    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;

    @Autowired
    private NotificationService notificationService;

    public ResponseEntity<Object> saveProjectDetails(Integer ecId, Integer ecPartAId, Integer clearanceId,
                                                     Integer cafId, EcForm11ProjectDetails ecForm11ProjectDetails) {
        EcForm11ProjectDetails temp = null;
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            EcPartC ecPartC = null;
            if (ecId != null && ecId != 0) {
                ecPartC = ecPartCRepository.findById(ecId).orElse(null);
                ecForm11ProjectDetails.setEcPartc(ecPartC);
                ecForm11ProjectDetails.setEc_id(ecId);
            } else {
                ecId = 0;
            }

            EnvironmentClearence ecPartA = null;
            if (ecPartAId != null && ecPartAId != 0) {
                ecPartA = environmentClearenceRepository.findById(ecPartAId).orElse(null);
                ecForm11ProjectDetails.setEnvironmentClearence(ecPartA);
                ecForm11ProjectDetails.setEcPartAId(ecPartAId);
            } else {
                ecPartAId = 0;
            }

            CommonFormDetail commonForm = commonFormDetailRepository.findById(cafId)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + cafId));

            ecForm11ProjectDetails.setCommonFormDetail(commonForm);

            if (ecForm11ProjectDetails.getId() != null && ecForm11ProjectDetails.getId() != 0) {

                EcForm11ProjectDetails form = ecForm11ProjectDetailsRepository.findById(ecForm11ProjectDetails.getId())
                        .orElseThrow(() -> new ProjectNotFoundException(
                                "form 11 project details not found with ID" + ecForm11ProjectDetails.getId()));
                ecForm11ProjectDetails.setProposalNo(form.getProposalNo());

                log.info(" save EC form 11 WITH id NOT NULL OR ZERO for caf id " + cafId + "- SUCCESS");

                return ResponseHandler.generateResponse("saving ec form 11", HttpStatus.OK, "",
                        ecForm11ProjectDetailsRepository.save(ecForm11ProjectDetails));
            }

            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
            ProponentApplications applications = new ProponentApplications();

            if (tempClearances.isEmpty()) {
                log.info("INFO ------------ save Form11 no proponent applications found for caf id ----> " + cafId
                        + "---- EMPTY LIST");
                String proposal_no = null;
                if (ecForm11ProjectDetails.getProjectCategory().equalsIgnoreCase("A")) {
                    proposal_no = "IA/"
                            + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                            .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                            .getState_abbr()
                            + "/"
                            + activitySectorRepository.getSector(ecForm11ProjectDetails.getMajorActivityId(),
                            ecForm11ProjectDetails.getMajorSubActivityId()).getSector_code()
                            + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                } else {
                    if (ecForm11ProjectDetails.getIsProposedRequired()) {
                        proposal_no = "IA/"
                                + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                                .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                                .getState_abbr()
                                + "/"
                                + activitySectorRepository.getSector(ecForm11ProjectDetails.getMajorActivityId(),
                                ecForm11ProjectDetails.getMajorSubActivityId()).getSector_code()
                                + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                    } else {

                        proposal_no = "SIA/"
                                + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                                .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                                .getState_abbr()
                                + "/"
                                + activitySectorRepository.getSector(ecForm11ProjectDetails.getMajorActivityId(),
                                ecForm11ProjectDetails.getMajorSubActivityId()).getSector_code()
                                + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);

                    }
                }
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                ecForm11ProjectDetails.setProposalNo(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, commonForm, ecForm11ProjectDetails));
                    ecForm11ProjectDetails.setProposalNo(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }

            temp = ecForm11ProjectDetailsRepository.save(ecForm11ProjectDetails);
            Applications app = applicationsRepository.findById(clearanceId).get();

            applications.setCaf_id(commonForm.getId());
            applications.setProposal_id(temp.getId());
            applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

            applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(commonForm.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());

            proponentApplicationRepository.save(applications);

            /*
             * Updating the Proponent Application JSON String
             */

            OtherPropString.put("Proposal For", app.getGeneral_name());
            String activity_name = ecForm11ProjectDetailsRepository.getMajorActivityName(temp.getId());
            String itemNo = ecForm11ProjectDetailsRepository.getItemNo(temp.getId());
            OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
            OtherPropString.put("Sector", activitySectorRepository
                    .getSector(temp.getMajorActivityId(), temp.getMajorSubActivityId()).getSector_code());

            updateOtherPropertyService.updateOtherProperty(temp.getId(), OtherPropString);

        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving project details - ", e);
        }

        return ResponseHandler.generateResponse("saving form 11", HttpStatus.OK, "", temp);
    }

    public ResponseEntity<Object> getEcForm11(Integer id) {
        try {
            EcForm11ProjectDetails projectDetails = ecForm11ProjectDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("form 11 project details not found with ID" + id));
            projectDetails.setProponentApplications(proponentApplicationRepository.getApplicationByProposalId(id));

            return ResponseHandler.generateResponse("getting ec form 11 details", HttpStatus.OK, "", projectDetails);
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in getting ec form 11 details - ", e);
        }
    }

    public ResponseEntity<Object> saveProjectActivityDetails(List<EcForm11ProjectActivityDetails> activityDetails,
                                                             Integer ecForm11Id) {
        try {
            EcForm11ProjectDetails projectDetails = ecForm11ProjectDetailsRepository.findById(ecForm11Id).orElseThrow(
                    () -> new ProjectNotFoundException("form 11 project details not found with ID" + ecForm11Id));

            List<EcForm11ProjectActivityDetails> temp = activityDetails.stream().map(value -> {
                value.setEcForm11ProjectDetails(projectDetails);
                value.setProposalNo(projectDetails.getProposalNo());
                value.setActivities(activityRepository.findById(value.getActivityId())
                        .orElseThrow(() -> new ProjectNotFoundException(
                                "activity not found for activity ID" + value.getActivityId())));
                value.setSubActivities(subActivityRepository.findById(value.getSubActivityId())
                        .orElseThrow(() -> new ProjectNotFoundException(
                                "subactivity not found for subactivity ID" + value.getSubActivityId())));

                return value;
            }).collect(Collectors.toList());

            List<EcForm11ProjectActivityDetails> activity = ecForm11ProjectActivityDetailsRepository.saveAll(temp);
            log.info("INFO ------------ saveActivityDetails WITH ec form11 id---->" + ecForm11Id
                    + " ---- SAVED - SUCCESS");

            return ResponseHandler.generateResponse("saving project activity details ", HttpStatus.OK, "", activity);
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving project activity details - ", e);
        }
    }

    public ResponseEntity<Object> getProjectActivityDetails(Integer ecForm11Id) {
        try {
            List<EcForm11ProjectActivityDetails> activityDetails = new ArrayList<EcForm11ProjectActivityDetails>();
            activityDetails = ecForm11ProjectActivityDetailsRepository.getProjectActivityDetails(ecForm11Id);

            return ResponseHandler.generateResponse("getting project activity details", HttpStatus.OK, "",
                    activityDetails);
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in getting project activity details - ", e);
        }
    }

    public ResponseEntity<Object> deleteProjectActivityDetails(Integer id) {
        try {
            EcForm11ProjectActivityDetails temp = ecForm11ProjectActivityDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("project activity not found with ID" + id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting project activity details", HttpStatus.OK, "",
                    ecForm11ProjectActivityDetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in deleting project activity details - ", e);
        }
    }

    public ResponseEntity<Object> saveOtherDetails(EcForm11OtherDetails otherDetails, Integer ecForm11Id){
        try {
            if (otherDetails.getId() == null || otherDetails.getId() == 0) {
                EcForm11OtherDetails temp = ecForm11OtherDetailsRepository.getDataByForm11Id(ecForm11Id).orElse(null);

                if (temp != null) {
                    otherDetails.setId(temp.getId());
                }
            }

            EcForm11ProjectDetails projectDetails= ecForm11ProjectDetailsRepository.findById(ecForm11Id)
                    .orElseThrow(() -> new ProjectNotFoundException("project details not found with form 11 ID" + ecForm11Id));
            otherDetails.setEcForm11ProjectDetails(projectDetails);

            EcForm11OtherDetails response= ecForm11OtherDetailsRepository.save(otherDetails);

            return ResponseHandler.generateResponse("saving other details", HttpStatus.OK, "", response);
        }catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving other details - ", e);
        }
    }

    public ResponseEntity<Object> saveSPCBDetails(List<EcForm11SPCBDetails> ecForm11SPCBDetails, Integer ecForm11Id) {
        try {
            EcForm11ProjectDetails projectDetails = ecForm11ProjectDetailsRepository.findById(ecForm11Id).orElseThrow(
                    () -> new ProjectNotFoundException("form 11 project details not found with ID" + ecForm11Id));

            List<EcForm11SPCBDetails> spcbDetails = new ArrayList<EcForm11SPCBDetails>();
            spcbDetails = ecForm11SPCBDetails.stream().map(value -> {
                value.setEcForm11ProjectDetails(projectDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saving spcb details", HttpStatus.OK, "",
                    ecForm11SPCBDetailsRepository.saveAll(spcbDetails));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving spcb details - ", e);
        }
    }

    public ResponseEntity<Object> getSPCBDetails(Integer ecForm11Id) {
        try {
            List<EcForm11SPCBDetails> spcbDetails = new ArrayList<EcForm11SPCBDetails>();
            spcbDetails = ecForm11SPCBDetailsRepository.getDataByForm11Id(ecForm11Id);

            return ResponseHandler.generateResponse("getting spcb details", HttpStatus.OK, "", spcbDetails);
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in getting spcb details - ", e);
        }
    }

    public ResponseEntity<Object> deleteSPCBDetails(Integer id) {
        try {
            EcForm11SPCBDetails response = ecForm11SPCBDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("form 11 spcb details not found with ID" + id));

            response.setIsActive(false);
            response.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting spcb details", HttpStatus.OK, "", response);
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in deleting spcb details - ", e);
        }
    }

    public ResponseEntity<Object> saveUndertaking(Integer ecForm11Id, EcForm11Undertaking ecForm11Undertaking,
                                                  Boolean is_submit) {
        try {
            if (ecForm11Undertaking.getId() == null || ecForm11Undertaking.getId() == 0) {
                EcForm11Undertaking temp = ecForm11UndertakingRepository.getDataByForm11Id(ecForm11Id).orElse(null);

                if (temp != null) {
                    ecForm11Undertaking.setId(temp.getId());
                }
            }

            String proposalNo = proponentApplicationRepository.getProposalNo(ecForm11Id);
            String identificationNo = ecForm11UndertakingRepository.getIdentificationNo(proposalNo);
            ecForm11Undertaking.setIdentification_no(identificationNo);
            log.info(identificationNo);

            EcForm11ProjectDetails projectDetails = ecForm11ProjectDetailsRepository.findById(ecForm11Id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form 11 not found with id:" + ecForm11Id));
            ecForm11Undertaking.setEcForm11ProjectDetails(projectDetails);

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(projectDetails.getProposalNo());

            /*if (is_submit == true)
                proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
             */
            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
                    .findByCaf(projectDetails.getCommonFormDetail().getId());
//			Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
//					.findByCaf((Integer) ecForm7Repository.getCafByEcId(temp.getId()).get(0)[0]);
            proponentApplications.setProposal_no(generateProposalNo(proponentApplications.getProposal_sequence() - 1,
                    cafDetail.get(), projectDetails));

            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) || proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
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
                notificationService.sendProposalSMS(proponentApplications.getProposal_no());
                notificationService.sendProposalEmail(proponentApplications.getProposal_no());
            }
            /*
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                    || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (ecForm11Undertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(ecForm11Undertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }*/
            commonFormDetailService.saveCommonForm(cafDetail.get());
            proponentApplicationRepository.save(proponentApplications);
/*
            if (is_submit == true) {
                notificationService.sendProposalSMS(proponentApplications.getProposal_no());

                notificationService.sendProposalEmail(proponentApplications.getProposal_no());
            }*/

            return ResponseHandler.generateResponse("Save Ec Undertaking form 11 details form", HttpStatus.OK, null,
                    ecForm11UndertakingRepository.save(ecForm11Undertaking));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving undertaking form 11 details - ", e);
        }
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail form, EcForm11ProjectDetails projectDetails) {
        if (projectDetails.getProjectCategory().equalsIgnoreCase("A")) {
            String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
                    + activitySectorRepository
                    .getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId())
                    .getSector_code()
                    + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
            cafId = cafId.replaceAll("\\s", "");
            return cafId;
        } else {
            if (projectDetails.getIsProposedRequired()) {
                String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
                return cafId;
            } else {
//				ResponseEntity<List<sieea>> resp = sieaaStatusAPI
//						.getSieeaStatus(projectDetailRepository.findById(form.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//				if (resp.getBody().get(0).getStatus().equals("active")) {
                String cafId = "SIA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
                return cafId;

            }
        }
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
    }
}
