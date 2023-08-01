package com.backend.service.EcForm2;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm2.*;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm2.*;
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

@Slf4j
@Service
public class EcForm2Service {

    @Autowired
    public EcForm2ProjectActivityDetailsRepository ecForm2ProjectActivityDetailsRepository;

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
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private EcPartCRepository ecPartCRepository;

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    private EcForm2ProjectDetailsRepository ecForm2ProjectDetailsRepository;

    @Autowired
    private EcForm2CafKMLRepository ecForm2CafKMLRepository;

    @Autowired
    private EcForm2ProjectImplementationStatusRepository ecForm2ProjectImplementationStatusRepository;

    @Autowired
    private EcForm2EnclosureDetailsRepository ecForm2EnclosureDetailsRepository;

    @Autowired
    private EcForm2ImplementationStatusRepository ecForm2ImplementationStatusRepository;

    @Autowired
    private EcForm2CorrigendumDescriptionRepository ecForm2CorrigendumDescriptionRepository;

    @Autowired
    private EcForm2UndertakingRepository ecForm2UndertakingRepository;

    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private CommonFormDetailService commonFormDetailService;


    public ResponseEntity<Object> saveProjectDetails(Integer ecId, Integer ecPartAId, Integer clearanceId, Integer cafId, EcForm2ProjectDetails ecForm2ProjectDetails) {
        EcForm2ProjectDetails temp = null;
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            EcPartC ecPartC = null;
            if (ecId != null && ecId != 0) {
                ecPartC = ecPartCRepository.findById(ecId).orElse(null);
                ecForm2ProjectDetails.setEcPartc(ecPartC);
                ecForm2ProjectDetails.setEc_id(ecId);
            } else {
                ecId = 0;
            }

            EnvironmentClearence ecPartA = null;
            if (ecPartAId != null && ecPartAId != 0) {
                ecPartA = environmentClearenceRepository.findById(ecPartAId).orElse(null);
                ecForm2ProjectDetails.setEnvironmentClearence(ecPartA);
                ecForm2ProjectDetails.setEcPartAId(ecPartAId);
            } else {
                ecPartAId = 0;
            }

            CommonFormDetail commonForm = commonFormDetailRepository.findById(cafId).orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + cafId));
            ecForm2ProjectDetails.setCommonFormDetail(commonForm);

            if (ecForm2ProjectDetails.getId() != null && ecForm2ProjectDetails.getId() != 0) {

                EcForm2ProjectDetails form = ecForm2ProjectDetailsRepository.findById(ecForm2ProjectDetails.getId()).orElseThrow(() -> new ProjectNotFoundException("form 2 project details not found with ID" + ecForm2ProjectDetails.getId()));
                ecForm2ProjectDetails.setEcProposalNo(form.getEcProposalNo());

                log.info(" save EC form 2 WITH id NOT NULL OR ZERO for caf id " + cafId + "- SUCCESS");

                return ResponseHandler.generateResponse("saving ec form 11", HttpStatus.OK, "", ecForm2ProjectDetailsRepository.save(ecForm2ProjectDetails));
            }

            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
            ProponentApplications applications = new ProponentApplications();

            if (tempClearances.isEmpty()) {
                log.info("INFO ------------ save Form2 no proponent applications found for caf id ----> " + cafId + "---- EMPTY LIST");
                String proposal_no = null;
                if (ecForm2ProjectDetails.getProjectCategory().equalsIgnoreCase("A")) {
                    proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(ecForm2ProjectDetails.getMajorActivityId(), ecForm2ProjectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                } else {
                    if (ecForm2ProjectDetails.getIsProposedRequired()) {
                        proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(ecForm2ProjectDetails.getMajorActivityId(), ecForm2ProjectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                    } else {

                        proposal_no = "SIA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(ecForm2ProjectDetails.getMajorActivityId(), ecForm2ProjectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                    }
                }
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                ecForm2ProjectDetails.setEcProposalNo(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence()).max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, commonForm, ecForm2ProjectDetails));
                    ecForm2ProjectDetails.setEcProposalNo(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }

            temp = ecForm2ProjectDetailsRepository.save(ecForm2ProjectDetails);
            Applications app = applicationsRepository.findById(clearanceId).get();

            applications.setCaf_id(commonForm.getId());
            applications.setProposal_id(temp.getId());
            applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

            applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(commonForm.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(AppConstant.Caf_Status.DRAFT.toString());

            proponentApplicationRepository.save(applications);

            /*
             * Updating the Proponent Application JSON String
             */

            OtherPropString.put("Proposal For", app.getGeneral_name());
            String activity_name = ecForm2ProjectDetailsRepository.getMajorActivityName(temp.getId());
            String itemNo = ecForm2ProjectDetailsRepository.getItemNo(temp.getId());
            OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
            OtherPropString.put("Sector", activitySectorRepository.getSector(temp.getMajorActivityId(), temp.getMajorSubActivityId()).getSector_code());

            updateOtherPropertyService.updateOtherProperty(temp.getId(), OtherPropString);
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving project details - ", e);
        }

        return ResponseHandler.generateResponse("saving form 11", HttpStatus.OK, "", temp);
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail form, EcForm2ProjectDetails projectDetails) {
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

    public ResponseEntity<Object> getForm(Integer ecForm2Id) {
        try {
            EcForm2ProjectDetails projectDetails = ecForm2ProjectDetailsRepository.findById(ecForm2Id).orElseThrow(() -> new ProjectNotFoundException("form 2 project details not found with ID" + ecForm2Id));
            projectDetails.setProponentApplications(proponentApplicationRepository.getApplicationByProposalId(ecForm2Id));

            return ResponseHandler.generateResponse("getting ec form 2 details", HttpStatus.OK, "", projectDetails);
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in getting ec form 2 details - ", e);
        }
    }

    public ResponseEntity<Object> saveProjectActivityDetails(Integer ecForm2Id, List<EcForm2ProjectActivityDetails> activityDetails) {
        try {
            EcForm2ProjectDetails projectDetails = ecForm2ProjectDetailsRepository.findById(ecForm2Id).orElseThrow(() -> new ProjectNotFoundException("form 2 project details not found with ID" + ecForm2Id));

            List<EcForm2ProjectActivityDetails> temp = activityDetails.stream().map(value -> {
                value.setEcForm2(projectDetails);
                value.setProposalNo(projectDetails.getEcProposalNo());
                value.setActivities(activityRepository.findById(value.getActivityId()).orElseThrow(() -> new ProjectNotFoundException("activity not found for activity ID" + value.getActivityId())));
                value.setSubActivities(subActivityRepository.findById(value.getSubActivityId()).orElseThrow(() -> new ProjectNotFoundException("subactivity not found for subactivity ID" + value.getSubActivityId())));

                return value;
            }).collect(Collectors.toList());

            List<EcForm2ProjectActivityDetails> activity = ecForm2ProjectActivityDetailsRepository.saveAll(temp);
            log.info("INFO ------------ saveActivityDetails WITH ec form2 id---->" + ecForm2Id + " ---- SAVED - SUCCESS");

            return ResponseHandler.generateResponse("saving project activity details ", HttpStatus.OK, "", activity);
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving project activity details - ", e);
        }
    }

    public ResponseEntity<Object> deleteProjectActivityDetails(Integer id) {
        try {
            EcForm2ProjectActivityDetails temp = ecForm2ProjectActivityDetailsRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("form 2 project activity details not found with ID" + id));
            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting project activity details", HttpStatus.OK, "", ecForm2ProjectActivityDetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in deleting project activity details - ", e);
        }
    }

    public ResponseEntity<Object> deleteCafKml(Integer id) {
        try {
            EcForm2CafKML temp = ecForm2CafKMLRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("form 2 caf kml details not found with ID" + id));
            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting caf kml details for id:" + id, HttpStatus.OK, "", ecForm2CafKMLRepository.save(temp));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in deleting caf kml details - ", e);
        }
    }


//    public ResponseEntity<Object> saveEcForm2CafKML(List<EcForm2CafKML> ecForm2CafKML, Integer ecForm2Id) throws PariveshException {
//        try {
//
//            return ResponseHandler.generateResponse("Save Ec Form form 7 CAF KML", HttpStatus.OK, null,
//                    ecForm2CafKMLRepository.saveAll(ecForm2CafKML));
//
//        } catch (Exception e) {
//            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//            throw new PariveshException("Error in Saving Ec form 7 CAF KML-----" + ec_id, e);
//        }
//
//    }

    public ResponseEntity<Object> saveProjectImplementationStatus(Integer ecForm2Id, EcForm2ProjectImplementationStatus projectImplementationStatus) {
        try {
            if (projectImplementationStatus.getId() == null || projectImplementationStatus.getId() == 0) {
                EcForm2ProjectImplementationStatus temp = ecForm2ProjectImplementationStatusRepository.getByForm2Id(ecForm2Id).orElse(null);

                if (temp != null) {
                    projectImplementationStatus.setId(temp.getId());
                }
            }

            EcForm2ProjectDetails projectDetails = ecForm2ProjectDetailsRepository.findById(ecForm2Id).orElseThrow(() -> new ProjectNotFoundException("project details not found with form 2 ID" + ecForm2Id));
            projectImplementationStatus.setEcForm2(projectDetails);

            return ResponseHandler.generateResponse("saving form 2 project implementation status", HttpStatus.OK, "", ecForm2ProjectImplementationStatusRepository.save(projectImplementationStatus));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving project implementation status - ", e);
        }
    }

    public ResponseEntity<Object> saveImplementationStatus(Integer ecForm2Id,List<EcForm2ImplementationStatus> ecForm2ImplementationStatus){
        try {
            List<EcForm2ImplementationStatus> temp= new ArrayList<>();
            EcForm2ProjectDetails projectDetails = ecForm2ProjectDetailsRepository.findById(ecForm2Id).orElseThrow(() -> new ProjectNotFoundException("project details not found with form 2 ID" + ecForm2Id));

            temp= ecForm2ImplementationStatus.stream().map(value ->{
                value.setEcForm2(projectDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saving implementation status",HttpStatus.OK,null,ecForm2ImplementationStatusRepository.saveAll(temp));
        }catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving implementation status - ", e);
        }
    }

    public ResponseEntity<Object> deleteImplementationStatus(Integer id) {
        try {
            EcForm2ImplementationStatus temp = ecForm2ImplementationStatusRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("form 2 implementation status not found with ID" + id));
            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting implementation status for id:" + id, HttpStatus.OK, "", ecForm2ImplementationStatusRepository.save(temp));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in deleting implementation status details - ", e);
        }
    }

    public ResponseEntity<Object> saveCorrigendumDescription(Integer ecForm2Id, List<EcForm2CorrigendumDescription> ecForm2CorrigendumDescription){
        try {
            List<EcForm2CorrigendumDescription> temp= new ArrayList<>();
            EcForm2ProjectDetails projectDetails = ecForm2ProjectDetailsRepository.findById(ecForm2Id).orElseThrow(() -> new ProjectNotFoundException("project details not found with form 2 ID" + ecForm2Id));

            temp=ecForm2CorrigendumDescription.stream().map(value->{
                value.setEcForm2(projectDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saving corrigendum description",HttpStatus.OK,null,ecForm2CorrigendumDescriptionRepository.saveAll(temp));
        }catch (Exception e){
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving corrigendum description - ", e);
        }
    }

    public ResponseEntity<Object> deleteCorrigendumDescription(Integer id) {
        try {
            EcForm2CorrigendumDescription temp = ecForm2CorrigendumDescriptionRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("form 2 corrigendum description not found with ID" + id));
            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting corrigendum description for id:" + id, HttpStatus.OK, "", ecForm2CorrigendumDescriptionRepository.save(temp));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in deleting corrigendum description details - ", e);
        }
    }


    public ResponseEntity<Object> saveEnclosureDetails(Integer ecForm2Id, EcForm2EnclosureDetails enclosureDetails) {
        try {
            if (enclosureDetails.getId() == null || enclosureDetails.getId() == 0) {
                EcForm2EnclosureDetails temp = ecForm2EnclosureDetailsRepository.getByForm2Id(ecForm2Id).orElse(null);

                if (temp != null) {
                    enclosureDetails.setId(temp.getId());
                }
            }
            EcForm2ProjectDetails projectDetails = ecForm2ProjectDetailsRepository.findById(ecForm2Id).orElseThrow(() -> new ProjectNotFoundException("project details not found with form 2 ID" + ecForm2Id));
            enclosureDetails.setEcForm2(projectDetails);

            return ResponseHandler.generateResponse("saving form 2 enclosure details", HttpStatus.OK, "", ecForm2EnclosureDetailsRepository.save(enclosureDetails));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving enclosure details - ", e);
        }
    }

    public ResponseEntity<Object> saveUndertaking(Integer ecForm2Id, EcForm2Undertaking ecForm2Undertaking, Boolean is_submit) {
        try {
            if (ecForm2Undertaking.getId() == null || ecForm2Undertaking.getId() == 0) {
                EcForm2Undertaking temp = ecForm2UndertakingRepository.getByForm2Id(ecForm2Id).orElse(null);

                if (temp != null) {
                    ecForm2Undertaking.setId(temp.getId());
                }
            }

            String proposalNo = proponentApplicationRepository.getProposalNo(ecForm2Id);
            String identificationNo = ecForm2UndertakingRepository.getIdentificationNo(proposalNo);
            ecForm2Undertaking.setIdentification_no(identificationNo);
            log.info(identificationNo);

            EcForm2ProjectDetails projectDetails = ecForm2ProjectDetailsRepository.findById(ecForm2Id).orElseThrow(() -> new ProjectNotFoundException("EC form 11 not found with id:" + ecForm2Id));
            ecForm2Undertaking.setEcForm2(projectDetails);

            ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalNo(projectDetails.getEcProposalNo());


            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository.findByCaf(projectDetails.getCommonFormDetail().getId());

            proponentApplications.setProposal_no(generateProposalNo(proponentApplications.getProposal_sequence() - 1, cafDetail.get(), projectDetails));

            if (proponentApplications.getLast_status().equals(AppConstant.Caf_Status.DRAFT.toString()) || proponentApplications.getLast_status().equals(AppConstant.Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_submission_date(new Date());
            }
            if (proponentApplications.getLast_status().equals(AppConstant.Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_status(AppConstant.Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);
            } else if (is_submit == true) {
                proponentApplications.setLast_status(AppConstant.Caf_Status.SUBMITTED.toString());

                /*
                 * Sending Notification For First Submit
                 */
                notificationService.sendProposalSMS(proponentApplications.getProposal_no());
                notificationService.sendProposalEmail(proponentApplications.getProposal_no());
            }

            commonFormDetailService.saveCommonForm(cafDetail.get());
            proponentApplicationRepository.save(proponentApplications);


            return ResponseHandler.generateResponse("Save Ec Undertaking form 2 details form", HttpStatus.OK, null, ecForm2UndertakingRepository.save(ecForm2Undertaking));
        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving undertaking form 2 details - ", e);
        }
    }


}
