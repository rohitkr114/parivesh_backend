package com.backend.service.EcPartC;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import com.backend.util.ServerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.client.NotifyClient;
import com.backend.client.SIEAAStatusAPI;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.UpdatedUser;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.model.EcPartC.EcAirQualityImpacts;
import com.backend.model.EcPartC.EcAmbientAirQuality;
import com.backend.model.EcPartC.EcBaseLineCollections;
import com.backend.model.EcPartC.EcBaseLineDetails;
import com.backend.model.EcPartC.EcChemicalProperties;
import com.backend.model.EcPartC.EcEnclosures;
import com.backend.model.EcPartC.EcGroundWaterLevel;
import com.backend.model.EcPartC.EcGroundWaterQuality;
import com.backend.model.EcPartC.EcNoiseLevel;
import com.backend.model.EcPartC.EcOtherDetails;
import com.backend.model.EcPartC.EcParameterMonitor;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EcPartC.EcSoilQuality;
import com.backend.model.EcPartC.EcSummaryAllocations;
import com.backend.model.EcPartC.EcSurfaceWaterQuality;
import com.backend.model.EcPartC.EcUndertaking;
import com.backend.repository.postgres.ActivityRepository;
import com.backend.repository.postgres.ActivitySubActivitySectorRepository;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DocumentDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.UserRepository;
import com.backend.repository.postgres.EcPartC.EcAirQualityImpactRepository;
import com.backend.repository.postgres.EcPartC.EcAmbientAirQualityRepository;
import com.backend.repository.postgres.EcPartC.EcBaseLineCollectionRepository;
import com.backend.repository.postgres.EcPartC.EcBaseLineDetailsRepository;
import com.backend.repository.postgres.EcPartC.EcChemicalPropertiesRepository;
import com.backend.repository.postgres.EcPartC.EcEnclosuresRepository;
import com.backend.repository.postgres.EcPartC.EcGroundWaterLevelRepository;
import com.backend.repository.postgres.EcPartC.EcGroundWaterQualityRepository;
import com.backend.repository.postgres.EcPartC.EcMajorIssuesRepository;
import com.backend.repository.postgres.EcPartC.EcNoiseLevelRepository;
import com.backend.repository.postgres.EcPartC.EcOtherDetailsRepository;
import com.backend.repository.postgres.EcPartC.EcParameterMonitorRepository;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.repository.postgres.EcPartC.EcPublicHearingRepository;
import com.backend.repository.postgres.EcPartC.EcSoilQualityRepository;
import com.backend.repository.postgres.EcPartC.EcSummaryAllocationsRepository;
import com.backend.repository.postgres.EcPartC.EcSurfaceWaterQualityRepository;
import com.backend.repository.postgres.EcPartC.EcUndertakingRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.UpdateOtherPropertyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcPartCService {

    @Autowired
    private EcPartCRepository ecPartCRepository;

    @Autowired
    private EcBaseLineDetailsRepository ecBaseLineDetailsRepository;

    @Autowired
    private EcOtherDetailsRepository ecOtherDetailsRepository;

    @Autowired
    private EcEnclosuresRepository ecEnclosuresRepository;

    @Autowired
    private EcUndertakingRepository ecUndertakingRepository;

    @Autowired
    private EcPublicHearingRepository ecPublicHearingRepository;

    @Autowired
    private EcMajorIssuesRepository ecMajorIssuesRepository;

    @Autowired
    private EcAmbientAirQualityRepository ecAmbientAirQualityRepository;

    @Autowired
    private EcBaseLineCollectionRepository ecBaseLineCollectionRepository;

    @Autowired
    private EcChemicalPropertiesRepository ecChemicalPropertiesRepository;

    @Autowired
    private EcGroundWaterLevelRepository ecGroundWaterLevelRepository;

    @Autowired
    private EcGroundWaterQualityRepository ecGroundWaterQualityRepository;

    @Autowired
    private EcNoiseLevelRepository ecNoiseLevelRepository;

    @Autowired
    private EcSoilQualityRepository ecSoilQualityRepository;

    @Autowired
    private EcSurfaceWaterQualityRepository ecSurfaceWaterQualityRepository;

    @Autowired
    private EcSummaryAllocationsRepository ecSummaryAllocationsRepository;

    @Autowired
    private EcParameterMonitorRepository ecParameterMonitorRepository;

    @Autowired
    private EcAirQualityImpactRepository ecAirQualityImpactRepository;

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private ProjectDetailRepository projectDetailRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SIEAAStatusAPI sieaaStatusAPI;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DocumentDetailsRepository detailsRepository;

    @Autowired
    private NotifyClient notifyClient;

    @Autowired
    private ServerUtil util;

    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;

    @Autowired
    private NotificationService notificationSevice;

    public ResponseEntity<Object> saveEcPartC(EcPartC ecPartC, Integer ec_id, Integer caf_id,
                                              HttpServletRequest request, Integer clearance_id) throws PariveshException {

        EcPartC temp2 = null;
        try {
        	if (clearance_id == null) {
                clearance_id = 2;
            }
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            EnvironmentClearence ecClearence = environmentClearenceRepository.findByFormId(ec_id).orElse(null);
            CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
            ecPartC.setEnvironmentClearence(ecClearence);
            if (ecPartC.getId() != null && ecPartC.getId() != 0) {
                EcPartC form = ecPartCRepository.getFormById(ecPartC.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
                ecPartC.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                String m_file_no = proponentApplicationRepository.getMoefccFileNoEcC(form.getId());
                if (m_file_no == null) {
                    Integer upd = proponentApplicationRepository.updateFileNo(form.getId(),
                            proponentApplicationRepository.getMoefccFileNo(ec_id));
                }
                return ResponseHandler.generateResponse("Update Ec partC Form ", HttpStatus.OK, "",
                        ecPartCRepository.save(ecPartC));
//			return ecPartCRepository.save(ecPartC);
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            ProponentApplications applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {
                String proposal_no = null;
                if (ecPartC.getProject_category().equalsIgnoreCase("A")) {
                    proposal_no = "IA/"
                            + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                            .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                            .getState_abbr()
                            + "/"
                            + activitySectorRepository
                            .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
                            .getSector_code()
                            + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                } else {
                    if (ecPartC.getIs_proposed_required()) {
                        proposal_no = "IA/"
                                + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                                .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                                .getState_abbr()
                                + "/"
                                + activitySectorRepository
                                .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
                                .getSector_code()
                                + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                    } else {
//						ResponseEntity<List<sieea>> resp = sieaaStatusAPI.getSieeaStatus(projectDetailRepository
//								.findById(cafDetail.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//						if (resp.getBody().get(0).getStatus().equals("active")) {
                        proposal_no = "SIA/"
                                + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                                .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                                .getState_abbr()
                                + "/"
                                + activitySectorRepository
                                .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
                                .getSector_code()
                                + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);

                    }

                }
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                ecClearence.setProposal_no(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, cafDetail, ecPartC));
                    ecPartC.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }
            /*
             * if (tempClearances.isEmpty()) { String proposal_no = "IA/" + stateRepository
             * .getStateByCode(projectDetailRepository.findById(ecClearence.
             * getCommonFormDetail().getProject_id()) .orElseThrow(() -> new
             * ProjectNotFoundException("project not found")).getMain_state())
             * .orElseThrow(() -> new
             * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
             * + activityRepository
             * .findById(ecClearence.getEnvironmentClearanceProjectActivityDetails().get(0).
             * getActivities() .getId()) .orElseThrow(() -> new
             * ProjectNotFoundException("activity not found with id"))
             * .getSectorEntity().getSectorCode() + "/" + 100000 + "/" +
             * String.valueOf(LocalDate.now().getYear());
             * applications.setProposal_sequence(100000);
             * applications.setProposal_no(proposal_no);
             * ecPartC.setProposal_no(proposal_no); } else { Integer maxCount =
             * tempClearances.stream().map(e -> e.getProposal_sequence())
             * .max(Comparator.comparing(Integer::valueOf)).get(); if (maxCount != null) {
             * applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(
             * maxCount))); applications.setProposal_no(generateProposalNo(maxCount,
             * ecClearence)); ecPartC.setProposal_no(applications.getProposal_no()); } }
             */
            if (ecPartC.getAction_plan_raised() != null) {
                ecPartC.getAction_plan_raised().setProposal_no(ecPartC.getProposal_no().replaceAll("\\s", ""));
            }
            if (ecPartC.getEac_recommendation() != null) {
                ecPartC.getEac_recommendation().setProposal_no(ecPartC.getProposal_no().replaceAll("\\s", ""));
            }
            if (ecPartC.getTor_letter() != null) {
                ecPartC.getTor_letter().setProposal_no(ecPartC.getProposal_no().replaceAll("\\s", ""));
            }
            if (ecPartC.getTor_letter_copy() != null) {
                ecPartC.getTor_letter_copy().setProposal_no(ecPartC.getProposal_no().replaceAll("\\s", ""));
            }
            temp2 = ecPartCRepository.save(ecPartC);

            Applications app = applicationsRepository.findById(clearance_id).get();
            if (ec_id == null) {
                ec_id = 0;
            } else {
                String MoefccFileNo = proponentApplicationRepository.getMoefccFileNo(ec_id);
                applications.setMoefccFileNumber(MoefccFileNo);
            }
            applications.setCaf_id(cafDetail.getId());
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());
            /*
             * applications.setState_id(stateRepository.getStateByCode(cafDetail.
             * getProjectDetails().getMain_state()) .orElseThrow(() -> new
             * ProjectNotFoundException("state not found with code")).getId());
             */

            applications.setState_id(stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(cafDetail.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            applications.setIs_legacy_proposal(ecPartC.getIs_legacy_proposal());

            proponentApplicationRepository.save(applications);

            /*
             * Updating the Proponent Application JSON String
             */

            OtherPropString.put("Proposal For", app.getGeneral_name());
//            OtherPropString.put("Form", app.getGeneral_name());
            String activity_name = ecPartCRepository.getMajorActivityName(temp2.getId());
            String itemNo = ecPartCRepository.getItemNo(temp2.getId());
            OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
            OtherPropString.put("Sector", activitySectorRepository
                    .getSector(temp2.getMajor_activity_id(), temp2.getMajor_sub_activity_id()).getSector_code());

            updateOtherPropertyService.updateOtherProperty(temp2.getId(), OtherPropString);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec partC form for ec_id-" + ec_id + " & caf_id-" + caf_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec partC form", HttpStatus.OK, null, temp2);
    }

    public ResponseEntity<Object> saveBaseLineDetails(EcBaseLineDetails ecBaseLineDetails, Integer ec_id)
            throws PariveshException {
        try {
            if (ecBaseLineDetails.getId() == null || ecBaseLineDetails.getId() == 0) {
                EcBaseLineDetails ecBaseLineDetails2 = ecBaseLineDetailsRepository.getDataByEcId(ec_id).orElse(null);
                if (ecBaseLineDetails2 != null) {
                    ecBaseLineDetails.setId(ecBaseLineDetails2.getId());
                }
            }
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecBaseLineDetails.setEcPartC(ecPartC);
            if (ecBaseLineDetails.getApproval_copy() != null) {
                ecBaseLineDetails.getApproval_copy().setProposal_no(ecPartC.getProposal_no());
            }
            if (ecBaseLineDetails.getConservation_plan_copy() != null) {
                ecBaseLineDetails.getConservation_plan_copy().setProposal_no(ecPartC.getProposal_no());
            }
            if (ecBaseLineDetails.getGround_water_authority_letter() != null) {
                ecBaseLineDetails.getGround_water_authority_letter().setProposal_no(ecPartC.getProposal_no());
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec BaseLine details form ec_id-" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec BaseLine details form", HttpStatus.OK, null,
                ecBaseLineDetailsRepository.save(ecBaseLineDetails));
    }

    public ResponseEntity<Object> saveOtherDetails(EcOtherDetails ecOtherDetails, Integer ec_id)
            throws PariveshException {
        try {
            if (ecOtherDetails.getId() == null || ecOtherDetails.getId() == 0) {
                EcOtherDetails ecOtherDetails2 = ecOtherDetailsRepository.getDataByEcId(ec_id).orElse(null);
                if (ecOtherDetails2 != null) {
                    ecOtherDetails.setId(ecOtherDetails2.getId());
                }
            }

            String proposalNo = proponentApplicationRepository.getProposalNo(ec_id);
            String identificationNo = ecOtherDetailsRepository.getIdentificationNo(proposalNo);
            ecOtherDetails.setIdentification_no(identificationNo);
            log.info(identificationNo);

            EcPartC ecPartC = ecPartCRepository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecOtherDetails.setEcPartC(ecPartC);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec Other Details form", HttpStatus.OK, null,
                ecOtherDetailsRepository.save(ecOtherDetails));
    }

    public ResponseEntity<Object> saveEnclosures(EcEnclosures ecEnclosures, Integer ec_id) throws PariveshException {
        try {
            if (ecEnclosures.getId() == null || ecEnclosures.getId() == 0) {
                EcEnclosures ecEnclosures2 = ecEnclosuresRepository.getDataByEcId(ec_id).orElse(null);
                if (ecEnclosures2 != null) {
                    ecEnclosures.setId(ecEnclosures2.getId());
                }
            }
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecEnclosures.setEcPartC(ecPartC);
            if (ecEnclosures.getAdditional_upload_copy() != null) {
                ecEnclosures.getAdditional_upload_copy().setProposal_no(ecPartC.getProposal_no());
            }
            if (ecEnclosures.getApproved_mining_plan_copy() != null) {
                ecEnclosures.getApproved_mining_plan_copy().setProposal_no(ecPartC.getProposal_no());
            }
            if (ecEnclosures.getDistrict_survey_report() != null) {
                ecEnclosures.getDistrict_survey_report().setProposal_no(ecPartC.getProposal_no());
            }
            if (ecEnclosures.getEia_final_copy() != null) {
                ecEnclosures.getEia_final_copy().setProposal_no(ecPartC.getProposal_no());
            }
            if (ecEnclosures.getFeasibility_summary_report() != null) {
                ecEnclosures.getFeasibility_summary_report().setProposal_no(ecPartC.getProposal_no());
            }
            if (ecEnclosures.getFinal_layout_copy() != null) {
                ecEnclosures.getFinal_layout_copy().setProposal_no(ecPartC.getProposal_no());
            }
            if (ecEnclosures.getReplenishment_study_report() != null) {
                ecEnclosures.getReplenishment_study_report().setProposal_no(ecPartC.getProposal_no());
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Enclosures details form ec_id-" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec Enclosures details form", HttpStatus.OK, null,
                ecEnclosuresRepository.save(ecEnclosures));
    }

    @Transactional
    public ResponseEntity<Object> saveUndertaking(EcUndertaking ecUndertaking, Integer ec_id, Integer caf_id, Boolean is_submit,
                                                  HttpServletRequest request) throws PariveshException {
        try {
            if (ecUndertaking.getId() == null || ecUndertaking.getId() == 0) {
                EcUndertaking ecUndertaking2 = ecUndertakingRepository.getDataByEcId(ec_id).orElse(null);
                if (ecUndertaking2 != null) {
                    ecUndertaking.setId(ecUndertaking2.getId());
                }
            }
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
            ecUndertaking.setEcPartC(ecPartC);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(ecPartC.getProposal_no());
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) ||
            		proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
            	proponentApplications.setLast_submission_date(new Date());
            }
            if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);

            }else {
                if (is_submit == true) {
                    proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
                    /*
                     * Sending Notification For First Submit
                     */
                    notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());
                    notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
                    if(proponentApplications.getClearance_id() ==37)
                    {
                        Integer torECId= ecPartC.getEc_id();
                        ecUndertakingRepository.updateLastStatus(torECId,Caf_Status.SUBMITTED.toString());

                    }
                }

            }
            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())) {
                proponentApplications.setProposal_no(
                        generateProposalNo(proponentApplications.getProposal_sequence() - 1, cafDetail, ecPartC));
                ecPartCRepository.updateProposal(ecPartC.getId(), proponentApplications.getProposal_no());
            }
            commonFormDetailService.saveCommonForm(cafDetail);
            proponentApplicationRepository.save(proponentApplications);

            return ResponseHandler.generateResponse("Save Ec Undertaking details form", HttpStatus.OK, null,
                    ecUndertakingRepository.save(ecUndertaking));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Undertaking details form ec_id-" + ec_id, e);
        }
    }

    public ResponseEntity<Object> deletePublicHearing(Integer id) throws PariveshException {
        try {

            Integer upadate = ecPublicHearingRepository.updatePublicHearing(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Public Hearing data Id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete public hearing data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteMajorIssues(Integer id) throws PariveshException {
        try {

            Integer upadate = ecMajorIssuesRepository.updateMajorIssues(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Major Issue Data Id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete major issues data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteAmbientAirQuality(Integer id) throws PariveshException {
        try {

            Integer upadate = ecAmbientAirQualityRepository.updateEcAmbientAirQuality(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Ambient air Quality data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Ambient air Quality data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteBaseLineCollection(Integer id) throws PariveshException {
        try {

            Integer upadate = ecBaseLineCollectionRepository.updateBaseLineCollection(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting BaseLine Collection data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete BaseLine Collection data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteChemicalProperties(Integer id) throws PariveshException {
        try {

            Integer upadate = ecChemicalPropertiesRepository.updateChemicalProperties(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Chemical Properties data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Chemical Properties data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteGroundWaterLevel(Integer id) throws PariveshException {
        try {

            Integer upadate = ecGroundWaterLevelRepository.updateGroundWaterLevel(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Ground Water Level data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Ground Water Level data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteGroundWaterQuality(Integer id) throws PariveshException {
        try {

            Integer upadate = ecGroundWaterQualityRepository.updateGroundWaterQuality(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Ground Water Quality data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Ground Water Quality data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteNoiseLevel(Integer id) throws PariveshException {
        try {

            Integer upadate = ecNoiseLevelRepository.updateNoiseLevel(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Noise Level data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Noise Level data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteSoilQuality(Integer id) throws PariveshException {
        try {

            Integer upadate = ecSoilQualityRepository.updateSoilQuality(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Soil Quality data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Soil Quality data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteSurfaceWaterQuality(Integer id) throws PariveshException {
        try {

            Integer upadate = ecSurfaceWaterQualityRepository.updateSurfaceWaterQuality(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Surface Water Quality data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Surface Water Quality data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteAirQualityImpact(Integer id) throws PariveshException {
        try {

            Integer upadate = ecAirQualityImpactRepository.updateAirQualityImpact(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Air Quality Impact data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Air Quality Impact data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteSummaryAllocation(Integer id) throws PariveshException {
        try {

            Integer upadate = ecSummaryAllocationsRepository.updateSummaryAllocation(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Summary Allocation data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Summary Allocation data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> deleteParameterMonitor(Integer id) throws PariveshException {
        try {
            EcParameterMonitor ecParameterMonitor = ecParameterMonitorRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Parameter Monitor not found with id"));
            ecParameterMonitor.setIs_active(false);
            ecParameterMonitor.setIs_deleted(true);
            ecParameterMonitorRepository.save(ecParameterMonitor);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Parameter Monitor data id-" + id, e);
        }
        return ResponseHandler.generateResponse("delete Parameter Monitor data", HttpStatus.OK, null,
                "Record deleted Successfully");
    }

    public ResponseEntity<Object> getEcPartCForm(Integer ec_partc_id, Integer step) throws PariveshException {
        EcPartC ecPartC = null;
        try {
            if (step != null) {
                if (step == 1) {
                    ecPartC = ecPartCRepository.getStepOneForm(ec_partc_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found with id"));
                    ecPartC.setProponentApplications(proponentApplicationRepository.getAppById(ec_partc_id));
                    ecPartC.getProponentApplications().setUpdatedByUser(new UpdatedUser(userRepository
                            .findAuthorById(ecPartC.getProponentApplications().getUpdated_by()).orElse(null)));
                    ecPartC.setEcMajorIssuesRaiseds(ecMajorIssuesRepository.getDataByFormId(ec_partc_id));
                    ecPartC.setEcPublicHearings(ecPublicHearingRepository.getDataByFormId(ec_partc_id));
                    List<Object[]> docData = ecPartCRepository.getDocuments(ec_partc_id);
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            ecPartC.setTor_letter_copy(
                                    obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                            ecPartC.setTor_letter(
                                    obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                            ecPartC.setAction_plan_raised(
                                    obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                            ecPartC.setEac_recommendation(
                                    obj[3] != null ? detailsRepository.findById((Integer) obj[3]).get() : null);
                            ecPartC.setAdditional_document(
                                    obj[4] != null ? detailsRepository.findById((Integer) obj[4]).get() : null);
                        }
                    }
                } else if (step == 2) {
                    ecPartC = ecPartCRepository.getStepTwoForm(ec_partc_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found with id"));
                    ecPartC.setProponentApplications(proponentApplicationRepository.getAppById(ecPartC.getId()));
                    ecPartC.getProponentApplications().setUpdatedByUser(new UpdatedUser(userRepository
                            .findAuthorById(ecPartC.getProponentApplications().getUpdated_by()).orElse(null)));
                    ecPartC.setEcBaseLineDetails(ecBaseLineDetailsRepository.getDataByEcId(ec_partc_id).orElse(null));
                    if (ecPartC.getEcBaseLineDetails() != null) {
                        ecPartC.getEcBaseLineDetails().setEcAmbientAirQualities(
                                ecAmbientAirQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                        ecPartC.getEcBaseLineDetails().setEcBaseLineCollections(
                                ecBaseLineCollectionRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                        ecPartC.getEcBaseLineDetails().setEcChemicalProperties(
                                ecChemicalPropertiesRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                        ecPartC.getEcBaseLineDetails().setEcGroundWaterLevels(
                                ecGroundWaterLevelRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                        ecPartC.getEcBaseLineDetails().setEcGroundWaterQualities(
                                ecGroundWaterQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                        ecPartC.getEcBaseLineDetails().setEcNoiseLevels(
                                ecNoiseLevelRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                        ecPartC.getEcBaseLineDetails().setEcSoilQualities(
                                ecSoilQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                        ecPartC.getEcBaseLineDetails().setEcSurfaceWaterQualities(ecSurfaceWaterQualityRepository
                                .getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                        List<Object[]> docData = ecBaseLineDetailsRepository
                                .getDocuments(ecPartC.getEcBaseLineDetails().getId());
                        for (Object[] obj : docData) {
                            if (obj != null) {
                                ecPartC.getEcBaseLineDetails().setApproval_copy(
                                        obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                                ecPartC.getEcBaseLineDetails().setConservation_plan_copy(
                                        obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                                ecPartC.getEcBaseLineDetails().setGround_water_authority_letter(
                                        obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                            }
                        }
                    }
                    ecPartC.setEcAmbientAirQualities(ecAmbientAirQualityRepository.getDataByEcPartCId(ec_partc_id));
                    ecPartC.setEcBaseLineCollections(ecBaseLineCollectionRepository.getDataByEcId(ec_partc_id));
                    ecPartC.setEcChemicalProperties(ecChemicalPropertiesRepository.getDataByEcId(ec_partc_id));
                    ecPartC.setEcGroundWaterLevels(ecGroundWaterLevelRepository.getDataByEcId(ec_partc_id));
                    ecPartC.setEcGroundWaterQualities(ecGroundWaterQualityRepository.getDataByEcId(ec_partc_id));
                    ecPartC.setEcNoiseLevels(ecNoiseLevelRepository.getDataByEcId(ec_partc_id));
                    ecPartC.setEcSoilQualities(ecSoilQualityRepository.getDataByEcId(ec_partc_id));
                    ecPartC.setEcSurfaceWaterQualities(ecSurfaceWaterQualityRepository.getDataByEcId(ec_partc_id));

                } else if (step == 3) {
                    ecPartC = ecPartCRepository.getStepTwoForm(ec_partc_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found with id"));
                    ecPartC.setProponentApplications(proponentApplicationRepository.getAppById(ecPartC.getId()));
                    ecPartC.getProponentApplications().setUpdatedByUser(new UpdatedUser(userRepository
                            .findAuthorById(ecPartC.getProponentApplications().getUpdated_by()).orElse(null)));
                    ecPartC.setEcBaseLineDetails(ecBaseLineDetailsRepository.getData(ec_partc_id).orElse(null));
                    ecPartC.setEcOtherDetails(ecOtherDetailsRepository.getDataByEcId(ec_partc_id).orElse(null));
                    ecPartC.setEcAmbientAirQualities(ecAmbientAirQualityRepository.getDataByEcPartCId(ec_partc_id));
                    if (ecPartC.getEcBaseLineDetails() != null) {
                        ecPartC.getEcBaseLineDetails().setEcAmbientAirQualities(
                                ecAmbientAirQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    }
                    if (ecPartC.getEcOtherDetails() != null) {
                        ecPartC.getEcOtherDetails().setEcAirQualityImpacts(
                                ecAirQualityImpactRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                        ecPartC.getEcOtherDetails().setEcSummaryAllocations(
                                ecSummaryAllocationsRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                        ecPartC.getEcOtherDetails().setEcParameterMonitors(
                                ecParameterMonitorRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                        List<Object[]> docData = ecOtherDetailsRepository
                                .getDocuments(ecPartC.getEcOtherDetails().getId());
                        for (Object[] obj : docData) {
                            if (obj != null) {
                                ecPartC.getEcOtherDetails().setCompliance_report(
                                        obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                            }
                        }
                    }
                    ecPartC.setEcAirQualityImpacts(ecAirQualityImpactRepository.getDataByEcId(ec_partc_id));
                    ecPartC.setEcSummaryAllocations(ecSummaryAllocationsRepository.getDataByEcId(ec_partc_id));
                    ecPartC.setEcParameterMonitors(ecParameterMonitorRepository.getDataByEcId(ec_partc_id));

                } else if (step == 4) {

                    ecPartC = ecPartCRepository.getStepTwoForm(ec_partc_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found with id"));
                    ecPartC.setProponentApplications(proponentApplicationRepository.getAppById(ecPartC.getId()));
                    ecPartC.getProponentApplications().setUpdatedByUser(new UpdatedUser(userRepository
                            .findAuthorById(ecPartC.getProponentApplications().getUpdated_by()).orElse(null)));
                    ecPartC.setEcEnclosures(ecEnclosuresRepository.getDataByEcId(ec_partc_id).orElse(null));
                    if (ecPartC.getEcEnclosures() != null) {
                        List<Object[]> docData = ecEnclosuresRepository.getDocuments(ecPartC.getEcEnclosures().getId());
                        for (Object[] obj : docData) {
                            if (obj != null) {
                                ecPartC.getEcEnclosures().setAdditional_upload_copy(
                                        obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                                ecPartC.getEcEnclosures().setFeasibility_summary_report(
                                        obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                                ecPartC.getEcEnclosures().setReplenishment_study_report(
                                        obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                                ecPartC.getEcEnclosures().setDistrict_survey_report(
                                        obj[3] != null ? detailsRepository.findById((Integer) obj[3]).get() : null);
                                ecPartC.getEcEnclosures().setFinal_layout_copy(
                                        obj[4] != null ? detailsRepository.findById((Integer) obj[4]).get() : null);
                                ecPartC.getEcEnclosures().setApproved_mining_plan_copy(
                                        obj[5] != null ? detailsRepository.findById((Integer) obj[5]).get() : null);
                                ecPartC.getEcEnclosures().setEia_final_copy(
                                        obj[6] != null ? detailsRepository.findById((Integer) obj[6]).get() : null);
                            }
                        }
                    }
                } else {
                    ecPartC = ecPartCRepository.getStepTwoForm(ec_partc_id)
                            .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found with id"));
                    ecPartC.setProponentApplications(proponentApplicationRepository.getAppById(ecPartC.getId()));
                    ecPartC.getProponentApplications().setUpdatedByUser(new UpdatedUser(userRepository
                            .findAuthorById(ecPartC.getProponentApplications().getUpdated_by()).orElse(null)));
                    ecPartC.setEcUndertaking(ecUndertakingRepository.getDataByEcId(ec_partc_id).orElse(null));

                }
            } else {
                ecPartC = ecPartCRepository.getStepOneForm(ec_partc_id)
                        .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found with id"));
                ecPartC.setProponentApplications(proponentApplicationRepository.getAppById(ec_partc_id));
                ecPartC.getProponentApplications().setUpdatedByUser(new UpdatedUser(userRepository
                        .findAuthorById(ecPartC.getProponentApplications().getUpdated_by()).orElse(null)));
                ecPartC.setEcBaseLineDetails(ecBaseLineDetailsRepository.getDataByEcId(ec_partc_id).orElse(null));
                ecPartC.setEcOtherDetails(ecOtherDetailsRepository.getDataByEcId(ec_partc_id).orElse(null));
                ecPartC.setEcEnclosures(ecEnclosuresRepository.getDataByEcId(ec_partc_id).orElse(null));
                ecPartC.setEcUndertaking(ecUndertakingRepository.getDataByEcId(ec_partc_id).orElse(null));
                if (ecPartC.getEcBaseLineDetails() != null) {
                    ecPartC.getEcBaseLineDetails().setEcAmbientAirQualities(
                            ecAmbientAirQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    ecPartC.getEcBaseLineDetails().setEcBaseLineCollections(
                            ecBaseLineCollectionRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    ecPartC.getEcBaseLineDetails().setEcChemicalProperties(
                            ecChemicalPropertiesRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    ecPartC.getEcBaseLineDetails().setEcGroundWaterLevels(
                            ecGroundWaterLevelRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    ecPartC.getEcBaseLineDetails().setEcGroundWaterQualities(
                            ecGroundWaterQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    ecPartC.getEcBaseLineDetails().setEcNoiseLevels(
                            ecNoiseLevelRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    ecPartC.getEcBaseLineDetails().setEcSoilQualities(
                            ecSoilQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    ecPartC.getEcBaseLineDetails().setEcSurfaceWaterQualities(
                            ecSurfaceWaterQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                    List<Object[]> docData = ecBaseLineDetailsRepository
                            .getDocuments(ecPartC.getEcBaseLineDetails().getId());
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            ecPartC.getEcBaseLineDetails().setApproval_copy(
                                    obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                            ecPartC.getEcBaseLineDetails().setConservation_plan_copy(
                                    obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                            ecPartC.getEcBaseLineDetails().setGround_water_authority_letter(
                                    obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                        }
                    }
                }
                if (ecPartC.getEcOtherDetails() != null) {
                    ecPartC.getEcOtherDetails().setEcAirQualityImpacts(
                            ecAirQualityImpactRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                    ecPartC.getEcOtherDetails().setEcSummaryAllocations(
                            ecSummaryAllocationsRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                    ecPartC.getEcOtherDetails().setEcParameterMonitors(
                            ecParameterMonitorRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                    List<Object[]> docData = ecOtherDetailsRepository.getDocuments(ecPartC.getEcOtherDetails().getId());
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            ecPartC.getEcOtherDetails().setCompliance_report(
                                    obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                        }
                    }
                }
                if (ecPartC.getEcEnclosures() != null) {
                    List<Object[]> docData = ecEnclosuresRepository.getDocuments(ecPartC.getEcEnclosures().getId());
                    for (Object[] obj : docData) {
                        if (obj != null) {
                            ecPartC.getEcEnclosures().setAdditional_upload_copy(
                                    obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                            ecPartC.getEcEnclosures().setFeasibility_summary_report(
                                    obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                            ecPartC.getEcEnclosures().setReplenishment_study_report(
                                    obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                            ecPartC.getEcEnclosures().setDistrict_survey_report(
                                    obj[3] != null ? detailsRepository.findById((Integer) obj[3]).get() : null);
                            ecPartC.getEcEnclosures().setFinal_layout_copy(
                                    obj[4] != null ? detailsRepository.findById((Integer) obj[4]).get() : null);
                            ecPartC.getEcEnclosures().setApproved_mining_plan_copy(
                                    obj[5] != null ? detailsRepository.findById((Integer) obj[5]).get() : null);
                            ecPartC.getEcEnclosures().setEia_final_copy(
                                    obj[6] != null ? detailsRepository.findById((Integer) obj[6]).get() : null);
                        }
                    }
                }
                ecPartC.setEcMajorIssuesRaiseds(ecMajorIssuesRepository.getDataByFormId(ec_partc_id));
                ecPartC.setEcPublicHearings(ecPublicHearingRepository.getDataByFormId(ec_partc_id));
                ecPartC.setEcAmbientAirQualities(ecAmbientAirQualityRepository.getDataByEcPartCId(ec_partc_id));
                ecPartC.setEcBaseLineCollections(ecBaseLineCollectionRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcChemicalProperties(ecChemicalPropertiesRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcGroundWaterLevels(ecGroundWaterLevelRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcGroundWaterQualities(ecGroundWaterQualityRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcNoiseLevels(ecNoiseLevelRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcSoilQualities(ecSoilQualityRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcSurfaceWaterQualities(ecSurfaceWaterQualityRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcAirQualityImpacts(ecAirQualityImpactRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcSummaryAllocations(ecSummaryAllocationsRepository.getDataByEcId(ec_partc_id));
                ecPartC.setEcParameterMonitors(ecParameterMonitorRepository.getDataByEcId(ec_partc_id));
                List<Object[]> docData = ecPartCRepository.getDocuments(ec_partc_id);
                for (Object[] obj : docData) {
                    if (obj != null) {
                        ecPartC.setTor_letter_copy(
                                obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                        ecPartC.setTor_letter(
                                obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                        ecPartC.setAction_plan_raised(
                                obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                        ecPartC.setEac_recommendation(
                                obj[3] != null ? detailsRepository.findById((Integer) obj[3]).get() : null);
                        ecPartC.setAdditional_document(
                                obj[4] != null ? detailsRepository.findById((Integer) obj[4]).get() : null);
                    }
                }
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Fc partC form id-" + ec_partc_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec partC form", HttpStatus.OK, null, ecPartC);
    }

    public ResponseEntity<Object> saveAmbientAirQuality(EcAmbientAirQuality ecAmbientAirQuality, Integer ec_partc_id) {
        try {

            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecAmbientAirQuality.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec AmbientAirQuality details form", HttpStatus.OK, null,
                    ecAmbientAirQualityRepository.save(ecAmbientAirQuality));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC AmbientAirQuality form id- " + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveBaseLineCollection(EcBaseLineCollections ecBaseLineCollections,
                                                         Integer ec_partc_id) {
        try {

            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecBaseLineCollections.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec BaseLineCollection details form", HttpStatus.OK, null,
                    ecBaseLineCollectionRepository.save(ecBaseLineCollections));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC BaseLineCollection form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveChemicalProperties(EcChemicalProperties ecChemicalProperties,
                                                         Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecChemicalProperties.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec ChemicalProperties details form", HttpStatus.OK, null,
                    ecChemicalPropertiesRepository.save(ecChemicalProperties));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC ChemicalProperties form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveGroundWaterLevel(EcGroundWaterLevel ecGroundWaterLevel, Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecGroundWaterLevel.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec GroundWaterLevel details form", HttpStatus.OK, null,
                    ecGroundWaterLevelRepository.save(ecGroundWaterLevel));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC GroundWaterLevel form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveGroundWaterQuality(EcGroundWaterQuality ecGroundWaterQuality,
                                                         Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecGroundWaterQuality.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec GroundWaterQuality details form", HttpStatus.OK, null,
                    ecGroundWaterQualityRepository.save(ecGroundWaterQuality));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC GroundWaterQuality form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveNoiseLevel(EcNoiseLevel ecNoiseLevel, Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecNoiseLevel.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec NoiseLevel details form", HttpStatus.OK, null,
                    ecNoiseLevelRepository.save(ecNoiseLevel));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC NoiseLevel form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveSoilQuality(EcSoilQuality ecSoilQuality, Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecSoilQuality.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec SoilQuality details form", HttpStatus.OK, null,
                    ecSoilQualityRepository.save(ecSoilQuality));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC SoilQuality form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveSurfaceWaterQuality(EcSurfaceWaterQuality ecSurfaceWaterQuality,
                                                          Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecSurfaceWaterQuality.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec SurfaceWaterQuality details form", HttpStatus.OK, null,
                    ecSurfaceWaterQualityRepository.save(ecSurfaceWaterQuality));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC SurfaceWaterQuality form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveAirQualityImpacts(EcAirQualityImpacts ecAirQualityImpacts, Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecAirQualityImpacts.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec AirQualityImpacts details form", HttpStatus.OK, null,
                    ecAirQualityImpactRepository.save(ecAirQualityImpacts));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC AirQualityImpacts form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveSummaryAllocations(EcSummaryAllocations ecSummaryAllocations,
                                                         Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecSummaryAllocations.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec SummaryAllocations details form", HttpStatus.OK, null,
                    ecSummaryAllocationsRepository.save(ecSummaryAllocations));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC SummaryAllocations form id-" + ec_partc_id, e);
        }
    }

    public ResponseEntity<Object> saveParameterMonitor(EcParameterMonitor ecParameterMonitor, Integer ec_partc_id) {
        try {
            EcPartC ecPartC = ecPartCRepository.getFormById(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found"));
            ecParameterMonitor.setEcPartC(ecPartC);

            return ResponseHandler.generateResponse("Save Ec ParameterMonitor details form", HttpStatus.OK, null,
                    ecParameterMonitorRepository.save(ecParameterMonitor));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC partC ParameterMonitor form id-" + ec_partc_id, e);
        }
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail form, EcPartC ecPartC) {
        if (ecPartC.getProject_category().equalsIgnoreCase("A")) {
            String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
                    + activitySectorRepository
                    .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
                    .getSector_code()
                    + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
            cafId = cafId.replaceAll("\\s", "");
            return cafId;
        } else {
            if (ecPartC.getIs_proposed_required()) {
                String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
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
                        .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
                return cafId;

            }
        }
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
    }

    public EcPartC getEcPartCForm(Integer ec_partc_id) throws PariveshException {
        EcPartC ecPartC = null;
        try {
            ecPartC = ecPartCRepository.getStepOneForm(ec_partc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec part C form not found with id"));
            ecPartC.setProponentApplications(proponentApplicationRepository.getAppById(ec_partc_id));
            ecPartC.getProponentApplications().setUpdatedByUser(new UpdatedUser(
                    userRepository.findAuthorById(ecPartC.getProponentApplications().getUpdated_by()).orElse(null)));
            ecPartC.setEcBaseLineDetails(ecBaseLineDetailsRepository.getDataByEcId(ec_partc_id).orElse(null));
            ecPartC.setEcOtherDetails(ecOtherDetailsRepository.getDataByEcId(ec_partc_id).orElse(null));
            ecPartC.setEcEnclosures(ecEnclosuresRepository.getDataByEcId(ec_partc_id).orElse(null));
            ecPartC.setEcUndertaking(ecUndertakingRepository.getDataByEcId(ec_partc_id).orElse(null));
            if (ecPartC.getEcBaseLineDetails() != null) {
                ecPartC.getEcBaseLineDetails().setEcAmbientAirQualities(
                        ecAmbientAirQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                ecPartC.getEcBaseLineDetails().setEcBaseLineCollections(
                        ecBaseLineCollectionRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                ecPartC.getEcBaseLineDetails().setEcChemicalProperties(
                        ecChemicalPropertiesRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                ecPartC.getEcBaseLineDetails().setEcGroundWaterLevels(
                        ecGroundWaterLevelRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                ecPartC.getEcBaseLineDetails().setEcGroundWaterQualities(
                        ecGroundWaterQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                ecPartC.getEcBaseLineDetails().setEcNoiseLevels(
                        ecNoiseLevelRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                ecPartC.getEcBaseLineDetails().setEcSoilQualities(
                        ecSoilQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                ecPartC.getEcBaseLineDetails().setEcSurfaceWaterQualities(
                        ecSurfaceWaterQualityRepository.getDataByFormId(ecPartC.getEcBaseLineDetails().getId()));
                List<Object[]> docData = ecBaseLineDetailsRepository
                        .getDocuments(ecPartC.getEcBaseLineDetails().getId());
                for (Object[] obj : docData) {
                    if (obj != null) {
                        ecPartC.getEcBaseLineDetails().setApproval_copy(
                                obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                        ecPartC.getEcBaseLineDetails().setConservation_plan_copy(
                                obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                        ecPartC.getEcBaseLineDetails().setGround_water_authority_letter(
                                obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                    }
                }
            }
            if (ecPartC.getEcOtherDetails() != null) {
                ecPartC.getEcOtherDetails().setEcAirQualityImpacts(
                        ecAirQualityImpactRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                ecPartC.getEcOtherDetails().setEcSummaryAllocations(
                        ecSummaryAllocationsRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                ecPartC.getEcOtherDetails().setEcParameterMonitors(
                        ecParameterMonitorRepository.getDataByFormId(ecPartC.getEcOtherDetails().getId()));
                List<Object[]> docData = ecOtherDetailsRepository.getDocuments(ecPartC.getEcOtherDetails().getId());
                for (Object[] obj : docData) {
                    if (obj != null) {
                        ecPartC.getEcOtherDetails().setCompliance_report(
                                obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                    }
                }
            }
            if (ecPartC.getEcEnclosures() != null) {
                List<Object[]> docData = ecEnclosuresRepository.getDocuments(ecPartC.getEcEnclosures().getId());
                for (Object[] obj : docData) {
                    if (obj != null) {
                        ecPartC.getEcEnclosures().setAdditional_upload_copy(
                                obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                        ecPartC.getEcEnclosures().setFeasibility_summary_report(
                                obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                        ecPartC.getEcEnclosures().setReplenishment_study_report(
                                obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                        ecPartC.getEcEnclosures().setDistrict_survey_report(
                                obj[3] != null ? detailsRepository.findById((Integer) obj[3]).get() : null);
                        ecPartC.getEcEnclosures().setFinal_layout_copy(
                                obj[4] != null ? detailsRepository.findById((Integer) obj[4]).get() : null);
                        ecPartC.getEcEnclosures().setApproved_mining_plan_copy(
                                obj[5] != null ? detailsRepository.findById((Integer) obj[5]).get() : null);
                        ecPartC.getEcEnclosures().setEia_final_copy(
                                obj[6] != null ? detailsRepository.findById((Integer) obj[6]).get() : null);
                    }
                }
            }
            ecPartC.setEcMajorIssuesRaiseds(ecMajorIssuesRepository.getDataByFormId(ec_partc_id));
            ecPartC.setEcPublicHearings(ecPublicHearingRepository.getDataByFormId(ec_partc_id));
            ecPartC.setEcAmbientAirQualities(ecAmbientAirQualityRepository.getDataByEcPartCId(ec_partc_id));
            ecPartC.setEcBaseLineCollections(ecBaseLineCollectionRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcChemicalProperties(ecChemicalPropertiesRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcGroundWaterLevels(ecGroundWaterLevelRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcGroundWaterQualities(ecGroundWaterQualityRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcNoiseLevels(ecNoiseLevelRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcSoilQualities(ecSoilQualityRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcSurfaceWaterQualities(ecSurfaceWaterQualityRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcAirQualityImpacts(ecAirQualityImpactRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcSummaryAllocations(ecSummaryAllocationsRepository.getDataByEcId(ec_partc_id));
            ecPartC.setEcParameterMonitors(ecParameterMonitorRepository.getDataByEcId(ec_partc_id));
            List<Object[]> docData = ecPartCRepository.getDocuments(ec_partc_id);
            for (Object[] obj : docData) {
                if (obj != null) {
                    ecPartC.setTor_letter_copy(
                            obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
                    ecPartC.setTor_letter(obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);
                    ecPartC.setAction_plan_raised(
                            obj[2] != null ? detailsRepository.findById((Integer) obj[2]).get() : null);
                    ecPartC.setEac_recommendation(
                            obj[3] != null ? detailsRepository.findById((Integer) obj[3]).get() : null);
                }
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Fc partC form id-" + ec_partc_id, e);
        }
        return ecPartC;
    }
}
