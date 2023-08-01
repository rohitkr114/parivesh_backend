package com.backend.service.EcForm13;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm13.*;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm13.*;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EcForm13Service {

    @Autowired
    private EcForm13ProjectSpecificDetailsRepository projectSpecificDetailsRepository;

    @Autowired
    private EcForm13ProjectDetailsRepository projectDetailsRepository;

    @Autowired
    private EcForm13ProjectActivityDetailsRepository projectActivityDetailsRepository;

    @Autowired
    private EcForm13ConsultantDetailsRepository consultantDetailsRepository;

    @Autowired
    private EcForm13EnclosureDetailsRepository enclosureDetailsRepository;

    @Autowired
    private EcForm13UndertakingRepository undertakingRepository;

    @Autowired
    private EcPartCRepository ecPartCRepository;

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SubActivityRepository subActivityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private NotificationService notificationSevice;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

//    public ResponseEntity<Object> saveProjectDetails(Integer cafId, Integer ecId,Integer clearanceId,
//                                                     EcForm13ProjectDetails projectDetails){
//        EcForm13ProjectDetails response=null;
//        try {
//            EcPartC ecPartC= ecPartCRepository.findById(ecId).orElse(null);
//            if (ecPartC!=null){
//                projectDetails.setEcPartc(ecPartC);
//                projectDetails.setEc_id(ecId);
//                projectDetails.setTor_id(null);
//            }else {
//                EnvironmentClearence tor= environmentClearenceRepository.findById(ecId)
//                        .orElseThrow(()-> new ProjectNotFoundException("neither ec nor tor details found for id:"+ ecId));
//                projectDetails.setEnvironmentClearence(tor);
//                projectDetails.setTor_id(ecId);
//                projectDetails.setEc_id(null);
//            }
//
//            CommonFormDetail commonForm = commonFormDetailRepository.findById(cafId)
//                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id:" + cafId));
//            projectDetails.setCommonFormDetail(commonForm);
//
//            if (projectDetails.getId()==null && projectDetails.getId()==0){
//                log.info("INFO ------------ save EC form 13 WITH EcID NOT NULL OR ZERO for caf id ----> " + cafId + "- SUCCESS");
//
//                EcForm13ProjectDetails form = projectDetailsRepository.findById(projectDetails.getId())
//                        .orElseThrow(() -> new ProjectNotFoundException("form 13 not found with id"));
//                projectDetails.setProposalNo(form.getProposalNo());
//
//                log.info("INFO ------------ saveForm13 for caf id ----> " + cafId + " --- SAVED - SUCCESS");
//
//                return ResponseHandler.generateResponse("saving ec form 13", HttpStatus.OK, "", projectDetailsRepository.save(projectDetails));
//            }
//
//            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
//            ProponentApplications applications = new ProponentApplications();
//
//            if (tempClearances.isEmpty()) {
//                log.info("INFO ------------ save Form13 no proponent applications found for caf id ----> " + cafId + "---- EMPTY LIST");
//                String proposal_no = null;
//                if (projectDetails.getProjectCategory().equalsIgnoreCase("A")) {
//                    proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
//                } else {
//                    if (projectDetails.getIsProposedRequired()) {
//                        proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
//                    } else {
//
//                        proposal_no = "SIA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + activitySectorRepository.getSector(projectDetails.getMajorActivityId(), projectDetails.getMajorSubActivityId()).getSector_code() + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
//                        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
//
//                    }
//                }
//                applications.setProposal_sequence(400000);
//                proposal_no = proposal_no.replaceAll("\\s", "");
//                applications.setProposal_no(proposal_no);
//                projectDetails.setProposalNo(proposal_no);
//            } else {
//                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence()).max(Comparator.comparing(Integer::valueOf)).get();
//                if (maxCount != null) {
//                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
//                    applications.setProposal_no(generateProposalNo(maxCount, commonForm, projectDetails));
//                    projectDetails.setProposalNo(applications.getProposal_no().replaceAll("\\s", ""));
//                }
//            }
//
//
//        }catch (Exception e) {
//            log.error("Encountered exception:", e);
//            throw new PariveshException("Error in saving form 13 project details - ", e);
//        }
//
//        return ResponseHandler.generateResponse("saving project details",HttpStatus.OK,"",response);
//    }

    public ResponseEntity<Object> saveProjectActivityDetails(Integer ecForm13Id, List<EcForm13ProjectActivityDetails> activityDetails){
        try{
            EcForm13ProjectDetails projectDetails= projectDetailsRepository.findById(ecForm13Id)
                    .orElseThrow(() -> new ProjectNotFoundException("project details not found with form 13 ID" + ecForm13Id));
            List<EcForm13ProjectActivityDetails> temp = activityDetails.stream().map(value -> {
                value.setEcForm13(projectDetails);
                value.setProposalNo(projectDetails.getProposalNo());
                value.setActivities(activityRepository.findById(value.getActivityId()).orElseThrow(() -> new ProjectNotFoundException("activity not found for activity ID" + value.getActivityId())));
                value.setSubActivities(subActivityRepository.findById(value.getSubActivityId()).orElseThrow(() -> new ProjectNotFoundException("subactivity not found for subactivity ID" + value.getSubActivityId())));

                return value;
            }).collect(Collectors.toList());

            List<EcForm13ProjectActivityDetails> response = projectActivityDetailsRepository.saveAll(temp);
            log.info("INFO ------------ saveActivityDetails WITH ec Form 13 Id---->" + ecForm13Id + " ---- SAVED - SUCCESS");

            return ResponseHandler.generateResponse("saving form 13 project activity details ", HttpStatus.OK, "", response);
        } catch (Exception e) {
            log.error("Encountered exception:", e);
            throw new PariveshException("Error in saving form 13 project activity details - ", e);
        }
    }


    public ResponseEntity<Object> saveSpecificDetails(Integer ecForm13Id, EcForm13ProjectSpecificDetails specificDetails){
        try {
            if (specificDetails.getId()==null || specificDetails.getId()==0){
                EcForm13ProjectSpecificDetails temp=projectSpecificDetailsRepository.getDataByForm13Id(ecForm13Id).orElse(null);

                if(temp!=null){
                    specificDetails.setId(temp.getId());
                }
            }

            EcForm13ProjectDetails projectDetails= projectDetailsRepository.findById(ecForm13Id)
                    .orElseThrow(() -> new ProjectNotFoundException("project details not found with form 13 ID" + ecForm13Id));
            specificDetails.setEcForm13(projectDetails);

            EcForm13ProjectSpecificDetails response= projectSpecificDetailsRepository.save(specificDetails);

            return ResponseHandler.generateResponse("saving form 13 specific details", HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception:",e);
            throw new PariveshException("error in saving form 13 specific details");
        }
    }

    public ResponseEntity<Object> saveConsultantDetails(Integer ecForm13Id, EcForm13ConsultantDetails consultantDetails){
        try {
            if (consultantDetails.getId()==null||consultantDetails.getId()==0){
                EcForm13ConsultantDetails temp= consultantDetailsRepository.getDataByForm13Id(ecForm13Id).orElse(null);

                if (temp!=null){
                    consultantDetails.setId(temp.getId());
                }
            }
            EcForm13ProjectDetails projectDetails= projectDetailsRepository.findById(ecForm13Id)
                    .orElseThrow(() -> new ProjectNotFoundException("project details not found with form 13 ID" + ecForm13Id));
            consultantDetails.setEcForm13(projectDetails);

            EcForm13ConsultantDetails response= consultantDetailsRepository.save(consultantDetails);

            return ResponseHandler.generateResponse("saving consultant details for form 13",HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception:",e);
            throw new PariveshException("error in saving form 13 consultant details");
        }
    }

    public ResponseEntity<Object> saveEnclosureDetails(Integer ecForm13Id, EcForm13EnclosureDetails enclosureDetails){
        try {
            if(enclosureDetails.getId()==null || enclosureDetails.getId()==0){
                EcForm13EnclosureDetails temp= enclosureDetailsRepository.getDataByForm13Id(ecForm13Id).orElse(null);

                if (temp!=null){
                    enclosureDetails.setId(temp.getId());
                }
            }
            EcForm13ProjectDetails projectDetails= projectDetailsRepository.findById(ecForm13Id)
                    .orElseThrow(() -> new ProjectNotFoundException("project details not found with form 13 ID" + ecForm13Id));
            enclosureDetails.setEcForm13(projectDetails);

            EcForm13EnclosureDetails response= enclosureDetailsRepository.save(enclosureDetails);

            return ResponseHandler.generateResponse("saving enclosure details",HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in saving enclosure details",e);
        }

    }

    public ResponseEntity<Object> saveUndertaking(Integer ecForm13Id, EcForm13Undertaking undertaking,Boolean isSubmit){
        try {
            if (undertaking.getId()==null||undertaking.getId()==0){
                EcForm13Undertaking temp= undertakingRepository.getDataByForm13Id(ecForm13Id).orElse(null);

                if(temp!=null){
                    undertaking.setId(temp.getId());
                }
            }

            String proposalNo= proponentApplicationRepository.getProposalNo(ecForm13Id);
            String identificationNo= undertakingRepository.getIdentificationNo(proposalNo);
            undertaking.setIdentification_no(identificationNo);

            EcForm13ProjectDetails projectDetails= projectDetailsRepository.findById(ecForm13Id)
                    .orElseThrow(() -> new ProjectNotFoundException("project details not found with form 13 ID" + ecForm13Id));
            undertaking.setEcForm13(projectDetails);

            ProponentApplications proponentApplications = proponentApplicationRepository.
                    getApplicationByProposalNo(projectDetails.getProposalNo());

            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository.findByCaf(projectDetails.getCommonFormDetail().getId());
//            proponentApplications.setProposal_no(generateProposalNo(proponentApplications.getProposal_sequence() - 1, cafDetail.get(), projectDetails));

            if (proponentApplications.getLast_status().equals(AppConstant.Caf_Status.DRAFT.toString()) || proponentApplications.getLast_status().equals(AppConstant.Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_submission_date(new Date());
            }
            if (proponentApplications.getLast_status().equals(AppConstant.Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_status(AppConstant.Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);
            } else if (isSubmit == true) {
                proponentApplications.setLast_status(AppConstant.Caf_Status.SUBMITTED.toString());

                /*
                 * Sending Notification For First Submit
                 */
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());
                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }

            commonFormDetailService.saveCommonForm(cafDetail.get());
            proponentApplicationRepository.save(proponentApplications);

            return ResponseHandler.generateResponse("saving undertaking",HttpStatus.OK,"",undertakingRepository.save(undertaking));
        }catch (Exception e){
            log.error("Encountered exception:",e);
            throw new PariveshException("error in saving undertaking",e);
        }
    }




}
