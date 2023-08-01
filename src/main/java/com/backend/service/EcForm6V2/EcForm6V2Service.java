package com.backend.service.EcForm6V2;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm6V2.*;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm6V2.*;
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
public class EcForm6V2Service {

    @Autowired
    private EcForm6ProjectDetailsV2Repo ecForm6ProjectDetailsRepo;

    @Autowired
    private EcForm6ProjectActivityDetailsV2Repo ecForm6ProjectActivityDetailsV2Repo;

    @Autowired
    private EcForm6AmendmentDetailsV2Repo ecForm6AmendmentDetailsRepo;

    @Autowired
    private EcForm6AmendmentStatusV2Repo ecForm6AmendmentStatusV2Repo;

    @Autowired
    private EcForm6ImplementationDetailsV2Repo ecForm6ImplementationDetailsRepo;

    @Autowired
    private EcForm6ConsultantV2Repo ecForm6ConsultantRepo;

    @Autowired
    private EcForm6ProjectActivityDetailsV2Repo ecForm6ActivityDetailsRepo;

    @Autowired
    private EcForm6UndertakingV2Repo ecForm6UndertakingRepo;

    @Autowired
    private EcForm6UnitDetailsV2Repo ecForm6UnitDetailsRepo;

    @Autowired
    private EcForm6ObtainedStatusV2Repo ecForm6ObtainedStatusV2Repo;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SubActivityRepository subActivityRepository;

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
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private NotificationService notificationSevice;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;

    public ResponseEntity<Object> saveProjectDetails(EcForm6ProjectDetailsV2 projectDetails, Integer clearance_id, Integer ec_part_a_id, Integer ec_id, Integer caf_id) {

        EcForm6ProjectDetailsV2 temp2 = null;
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            EcPartC ecClearence = null;
            if (ec_id != null || ec_id != 0) {
                ecClearence = ecPartCRepository.findById(ec_id).orElse(null);
                projectDetails.setEcPartc(ecClearence);
                projectDetails.setEc_id(ec_id);
            } else {
                ec_id = 0;
            }
            EnvironmentClearence ecPartA = null;
            if (ec_part_a_id != null || ec_part_a_id != 0) {
                ecPartA = environmentClearenceRepository.findById(ec_part_a_id).orElse(null);
                projectDetails.setEnvironmentClearence(ecPartA);
                projectDetails.setEc_part_a_id(ec_part_a_id);
            } else {
                ec_part_a_id = 0;
            }
            CommonFormDetail commonForm = commonFormDetailRepository.findById(caf_id).orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));

            projectDetails.setCommonFormDetail(commonForm);

            if (projectDetails.getId() != null && projectDetails.getId() != 0) {
                log.info("INFO ------------ save EC form 6 WITH EcID NOT NULL OR ZERO for caf id ----> " + caf_id + "- SUCCESS");

                EcForm6ProjectDetailsV2 form = ecForm6ProjectDetailsRepo.findByFormId(projectDetails.getId()).orElseThrow(() -> new ProjectNotFoundException("form 6 not found with id"));
                projectDetails.setProposalNo(form.getProposalNo());

                log.info("INFO ------------ saveForm6 for caf id ----> " + caf_id + " --- SAVED - SUCCESS");

                return ResponseHandler.generateResponse("saving ec form 6", HttpStatus.OK, "", ecForm6ProjectDetailsRepo.save(projectDetails));
            }

            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
            ProponentApplications applications = new ProponentApplications();

            if (tempClearances.isEmpty()) {
                log.info("INFO ------------ save Form6 no proponent applications found for caf id ----> " + caf_id + "---- EMPTY LIST");
                String proposal_no = null;
                if (projectDetails.getProjectCategory().equalsIgnoreCase("A")) {
                    proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                } else {
                    if (projectDetails.getIsProposedRequired()) {
                        proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                    } else {

                        proposal_no = "SIA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);

                    }
                }
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                projectDetails.setProposalNo(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence()).max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, commonForm, projectDetails));
                    projectDetails.setProposalNo(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }

            temp2 = ecForm6ProjectDetailsRepo.save(projectDetails);
            Applications app = applicationsRepository.findById(clearance_id).get();

            applications.setCaf_id(commonForm.getId());
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

            applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(commonForm.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());

            proponentApplicationRepository.save(applications);

            temp2.setProponentApplications(applications);
            temp2 = ecForm6ProjectDetailsRepo.save(temp2);

            /*
             * Updating the Proponent Application JSON String
             */

            OtherPropString.put("Proposal For", app.getGeneral_name());
            String activity_name = ecForm6ProjectDetailsRepo.getMajorActivityName(temp2.getId());
            String itemNo = ecForm6ProjectDetailsRepo.getItemNo(temp2.getId());
            OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
            OtherPropString.put("Sector", activitySectorRepository.getSector(temp2.getMajorActivityId(), temp2.getMajorSubActivityId()).getSector_code());

            updateOtherPropertyService.updateOtherProperty(temp2.getId(), OtherPropString);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in Saving EC form 6", e);
        }

        return ResponseHandler.generateResponse("Save Ec form 6", HttpStatus.OK, null, temp2);
    }

    public ResponseEntity<Object> getEcForm6(Integer id) {
        try {
            EcForm6ProjectDetailsV2 projectDetails = ecForm6ProjectDetailsRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException("form 6 project details not found with ID" + id));
            projectDetails.setProponentApplications(proponentApplicationRepository.getApplicationByProposalId(id));

            return ResponseHandler.generateResponse("getting ec form 6", HttpStatus.OK, "", projectDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in getting EC form 6", e);
        }
    }

    public ResponseEntity<Object> saveProjectActivityDetails(Integer ec_form6_id, List<EcForm6ProjectActivityDetailsV2> activityDetails) {
        try {
            EcForm6ProjectDetailsV2 projectDetails = ecForm6ProjectDetailsRepo.findByFormId(ec_form6_id).orElseThrow(() -> new ProjectNotFoundException("form 6 project details not found with ID" + ec_form6_id));

            List<EcForm6ProjectActivityDetailsV2> temp = activityDetails.stream().map(value -> {
                value.setEcForm6(projectDetails);
                value.setProposalNo(projectDetails.getProposalNo());
                value.setActivities(activityRepository.findById(value.getActivityId()).orElseThrow(() -> new ProjectNotFoundException("activity not found for activity ID" + value.getActivityId())));
                value.setSubActivities(subActivityRepository.findById(value.getSubActivityId()).orElseThrow(() -> new ProjectNotFoundException("subactivity not found for subactivity ID" + value.getSubActivityId())));

                return value;
            }).collect(Collectors.toList());

            List<EcForm6ProjectActivityDetailsV2> activity = ecForm6ProjectActivityDetailsV2Repo.saveAll(temp);
            log.info("INFO ------------ saveActivityDetails WITH ec_form6_id---->" + ec_form6_id + " ---- SAVED - SUCCESS");

            return ResponseHandler.generateResponse("saving project activity details ", HttpStatus.OK, "", activity);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in saving project activity details - ", e);
        }
    }

    public ResponseEntity<Object> getProjectActivityDetailsList(Integer ec_form6_id) {
        try {
            List<EcForm6ProjectActivityDetailsV2> activityDetails = new ArrayList<EcForm6ProjectActivityDetailsV2>();
            activityDetails = ecForm6ActivityDetailsRepo.getProjectActivityList(ec_form6_id);

            return ResponseHandler.generateResponse("getting project activity detail list", HttpStatus.OK, "", activityDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in getting project activity detail list - ", e);
        }

    }

    public ResponseEntity<Object> deleteProjectActivityDetails(Integer id) {
        try {
            EcForm6ProjectActivityDetailsV2 temp = ecForm6ActivityDetailsRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException("project activity not found with ID" + id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting project activity details", HttpStatus.OK, "", ecForm6ActivityDetailsRepo.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in deleting project activity detail list - ", e);
        }
    }

    public ResponseEntity<Object> saveImplementationDetails(EcForm6ImplementationDetailsV2 implementationDetails, Integer ec_form6_id) {
        try {
            if (implementationDetails.getId() == null || implementationDetails.getId() == 0) {
                EcForm6ImplementationDetailsV2 temp = ecForm6ImplementationDetailsRepo.getDataByForm6Id(ec_form6_id).orElse(null);

                if (temp != null) {
                    implementationDetails.setId(temp.getId());
                }
            }

            EcForm6ProjectDetailsV2 projectDetails = ecForm6ProjectDetailsRepo.findById(ec_form6_id).orElseThrow(() -> new ProjectNotFoundException("project details not found with form 6 ID" + ec_form6_id));
            implementationDetails.setEcForm6(projectDetails);

            EcForm6ImplementationDetailsV2 temp = ecForm6ImplementationDetailsRepo.save(implementationDetails);

            return ResponseHandler.generateResponse("saving implementation details", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in saving project implementation detail - ", e);
        }
    }

    public ResponseEntity<Object> getImplementationDetails(Integer ec_form6_id) {
        try {
            EcForm6ImplementationDetailsV2 temp = ecForm6ImplementationDetailsRepo.getDataByForm6Id(ec_form6_id).orElseThrow(() -> new ProjectNotFoundException("implementation details not found with form 6 ID" + ec_form6_id));

            return ResponseHandler.generateResponse("getting implementation details", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in getting project implementation detail - ", e);
        }
    }

    public ResponseEntity<Object> deleteEcForm6ObtainedStatus(Integer id) {
        try {
            EcForm6ObtainedStatusV2 temp = ecForm6ObtainedStatusV2Repo.findById(id).orElseThrow(() -> new ProjectNotFoundException("obtained status not found with ID" + id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting obtained status", HttpStatus.OK, "", ecForm6ObtainedStatusV2Repo.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in deleting obtained status - ", e);
        }
    }

    public ResponseEntity<Object> saveAmendmentDetails(Integer ec_form6_id, EcForm6AmendmentDetailsV2 amendmentDetails) {
        try {
            if (amendmentDetails.getId() == null || amendmentDetails.getId() == 0) {
                EcForm6AmendmentDetailsV2 temp = ecForm6AmendmentDetailsRepo.getDataByForm6Id(ec_form6_id).orElse(null);
                if (temp != null) {
                    amendmentDetails.setId(temp.getId());
                }
            }
            EcForm6ProjectDetailsV2 temp = ecForm6ProjectDetailsRepo.getDataById(ec_form6_id).orElseThrow(() -> new ProjectNotFoundException("EC form 6 not found with id:" + ec_form6_id));
            amendmentDetails.setEcForm6(temp);
            EcForm6AmendmentDetailsV2 amendment = ecForm6AmendmentDetailsRepo.save(amendmentDetails);
            log.info("INFO ------------ saveAmendmentDetail WITH ec_form6_id---->" + ec_form6_id + " ---- SAVED - SUCCESS");
            return ResponseHandler.generateResponse("saving amendment details", HttpStatus.OK, "", amendment);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in saving amendment detail - ", e);
        }
    }

    public ResponseEntity<Object> deleteAmendmentDetails(Integer id) {
        try {
            EcForm6AmendmentDetailsV2 temp = ecForm6AmendmentDetailsRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException("amendment details not found with ID" + id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting amendment details", HttpStatus.OK, "", ecForm6AmendmentDetailsRepo.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in deleting amendment details - ", e);
        }
    }

    public ResponseEntity<Object> getAmendmentDetails(Integer ec_form6_id) {
        try {
            EcForm6AmendmentDetailsV2 temp = ecForm6AmendmentDetailsRepo.getDataByForm6Id(ec_form6_id).orElseThrow(() -> new ProjectNotFoundException("amendment details not found with ec form 6 id" + ec_form6_id));

            return ResponseHandler.generateResponse("getting amendment details", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in getting amendment details - ", e);
        }
    }

    public ResponseEntity<Object> deleteAmendmentStatus(Integer id) {
        try {
            EcForm6AmendmentStatusV2 temp = ecForm6AmendmentStatusV2Repo.findById(id).orElseThrow(() -> new ProjectNotFoundException("amendment status not found with ID" + id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting amendment status", HttpStatus.OK, "", ecForm6AmendmentStatusV2Repo.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in deleting amendment status - ", e);
        }
    }

    public ResponseEntity<Object> saveUnitDetails(List<EcForm6UnitDetailsV2> unitDetails, Integer ec_form6_id) {
        try {
            List<EcForm6UnitDetailsV2> temp = new ArrayList<EcForm6UnitDetailsV2>();
            EcForm6ProjectDetailsV2 form = ecForm6ProjectDetailsRepo.getDataById(ec_form6_id).orElseThrow(() -> new ProjectNotFoundException("EC form 6 not found with id:" + ec_form6_id));

            temp = unitDetails.stream().map(value -> {
                value.setEcForm6(form);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saving unit details", HttpStatus.OK, "", ecForm6UnitDetailsRepo.saveAll(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in saving unit detail - ", e);
        }
    }

    public ResponseEntity<Object> getUnitDetails(Integer ec_form6_id) {
        try {
            List<EcForm6UnitDetailsV2> temp = new ArrayList<EcForm6UnitDetailsV2>();
            temp = ecForm6UnitDetailsRepo.getDataByForm6Id(ec_form6_id);

            return ResponseHandler.generateResponse("getting unit details", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in getting unit details - ", e);
        }
    }

    public ResponseEntity<Object> deleteUnitDetails(Integer id) {
        try {
            EcForm6UnitDetailsV2 temp = ecForm6UnitDetailsRepo.findById(id).orElseThrow(() -> new ProjectNotFoundException("unit details not found with ID" + id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting unit details", HttpStatus.OK, "", ecForm6UnitDetailsRepo.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in deleting unit details - ", e);
        }
    }

    public ResponseEntity<Object> saveConsultantDetails(Integer ec_form6_id, EcForm6ConsultantV2 consultant) {
        try {
            if (consultant.getId() == null || consultant.getId() == 0) {
                EcForm6ConsultantV2 temp = ecForm6ConsultantRepo.getDataByForm6Id(ec_form6_id).orElse(null);
                if (temp != null) {
                    consultant.setId(temp.getId());
                }
            }
            EcForm6ProjectDetailsV2 temp = ecForm6ProjectDetailsRepo.getDataById(ec_form6_id).orElseThrow(() -> new ProjectNotFoundException("EC form 6 not found with id:" + ec_form6_id));
            consultant.setEcForm6(temp);
            EcForm6ConsultantV2 consultant2 = ecForm6ConsultantRepo.save(consultant);
            log.info("INFO ------------ saveConsultantDetail WITH ec_form6_id---->" + ec_form6_id + " ---- SAVED - SUCCESS");
            return ResponseHandler.generateResponse("saving consultant details", HttpStatus.OK, "", consultant2);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in saving consultant details - ", e);
        }
    }

    public ResponseEntity<Object> saveUndertaking(Integer ec_form6_id, EcForm6UndertakingV2 undertaking, Boolean is_submit) {
        try {
            if (undertaking.getId() == null || undertaking.getId() == 0) {
                EcForm6UndertakingV2 ecUndertaking2 = ecForm6UndertakingRepo.getDataByEcId(ec_form6_id).orElse(null);
                if (ecUndertaking2 != null) {
                    undertaking.setId(ecUndertaking2.getId());
                }
            }

            String proposalNo = proponentApplicationRepository.getProposalNo(ec_form6_id);
            String identificationNo = ecForm6UndertakingRepo.getIdentificationNo(proposalNo);
            undertaking.setIdentification_no(identificationNo);
            log.info(identificationNo);

            EcForm6ProjectDetailsV2 temp = ecForm6ProjectDetailsRepo.getDataById(ec_form6_id).orElseThrow(() -> new ProjectNotFoundException("EC form 6 not found with id:" + ec_form6_id));

            undertaking.setEcForm6(temp);

            ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalNo(temp.getProposalNo());


            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository.findByCaf(temp.getCommonFormDetail().getId());
//			Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
//					.findByCaf((Integer) ecForm7Repository.getCafByEcId(temp.getId()).get(0)[0]);
            proponentApplications.setProposal_no(generateProposalNo(proponentApplications.getProposal_sequence() - 1, cafDetail.get(), temp));

            /*if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (undertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(undertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }

            if (is_submit == true) proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());*/
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
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());
                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }
            commonFormDetailService.saveCommonForm(cafDetail.get());
            proponentApplicationRepository.save(proponentApplications);

            /*if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }*/

            return ResponseHandler.generateResponse("Save Ec Undertaking form 6 details form", HttpStatus.OK, null, ecForm6UndertakingRepo.save(undertaking));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>", e);
            throw new PariveshException("Error in saving undertaking details - ", e);
        }
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail form, EcForm6ProjectDetailsV2 projectDetails) {
        if (projectDetails.getProjectCategory().equalsIgnoreCase("A")) {
            String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
            cafId = cafId.replaceAll("\\s", "");
            return cafId;
        } else {
            if (projectDetails.getIsProposedRequired()) {
                String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
                return cafId;
            } else {
//				ResponseEntity<List<sieea>> resp = sieaaStatusAPI
//						.getSieeaStatus(projectDetailRepository.findById(form.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//				if (resp.getBody().get(0).getStatus().equals("active")) {
                String cafId = "SIA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
                return cafId;

            }
        }
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
    }
}
