package com.backend.service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.constant.AppConstant.Form_for;
import com.backend.dto.*;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.*;
import com.backend.model.Crz.CrzBasicDetails;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10BasicInformation;
import com.backend.model.EcForm11.EcForm11ProjectDetails;
import com.backend.model.EcForm12.EcForm12;
import com.backend.model.EcForm2.EcForm2ProjectDetails;
import com.backend.model.EcForm6V2.EcForm6ProjectDetailsV2;
import com.backend.model.EcForm7.EcForm7;
import com.backend.model.EcForm8TransferOfTOR.EcForm8TransferOfTOR;
import com.backend.model.EcForm9TransferOfEC.EcForm9TransferOfEC;
import com.backend.model.EcFormVModel.EcFormV;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.ForestClearanceE.FcFormE;
import com.backend.model.ForestClearanceFormB.FcFormBProjectDetails;
import com.backend.model.ForestClearanceFormC.FcFormC;
import com.backend.model.ForestClearanceFormC.FcFormCExploredForestLand;
import com.backend.model.ForestClearanceFormD.FcFormD;
import com.backend.model.ForestClearanceFormD.FcFormDLegalStatus;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.Crz.CrzBasicDetailsRepository;
import com.backend.repository.postgres.EcForm10Repository.EcForm10BasicInformationRepository;
import com.backend.repository.postgres.EcForm10Repository.EcForm10ProjectDetailRepository;
import com.backend.repository.postgres.EcForm11.EcForm11ProjectDetailsRepository;
import com.backend.repository.postgres.EcForm12.EcForm12Repository;
import com.backend.repository.postgres.EcForm2.EcForm2ProjectDetailsRepository;
import com.backend.repository.postgres.EcForm6V2.EcForm6ProjectDetailsV2Repo;
import com.backend.repository.postgres.EcForm7.EcForm7Repository;
import com.backend.repository.postgres.EcForm8.EcForm8TransferOfTORRepository;
import com.backend.repository.postgres.EcForm9Repository.EcForm9TransferOfECRepository;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.repository.postgres.FcFormB.FcFormBProjectDetailsRepository;
import com.backend.repository.postgres.ForestClearanceFormC.FcFormCExploredForestLandRepository;
import com.backend.repository.postgres.ForestClearanceFormC.FcFormCRepository;
import com.backend.repository.postgres.ForestClearanceFormD.FcFormDLegalStatusRepository;
import com.backend.repository.postgres.ForestClearanceFormD.FcFormDRepository;
import com.backend.repository.postgres.ForestClearanceFormE.FcFormERepository;
import com.backend.repository.postgres.StandardTorCertificate.FcIROStageIClearanceCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.FcMinistryStageIClearanceCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.FcStateStageIClearanceCertificateRepository;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.EcFormV.EcFormVService;
import com.backend.service.EcPartC.EcPartCService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@Transactional
public class ProponentApplicationService {
    @Autowired
    FcStateStageIClearanceCertificateRepository fcStateStageClearanceCertificateRepository;
    @Autowired
    FcIROStageIClearanceCertificateRepository fcIROStageClearanceCertificateRepository;
    @Autowired
    FcMinistryStageIClearanceCertificateRepository fcMinistryStageIClearanceCertificateRepository;
    @Autowired
    private EcForm10BasicInformationRepository ecForm10BasicInformationRepository;
    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;
    @Autowired
    private ApplicationsRepository applicationsRepository;
    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;
    @Autowired
    private ForestClearanceRepository forestClearanceRepository;
    @Autowired
    private FcFormBProjectDetailsRepository fcFormBProjectDetailsRepository;
    @Autowired
    private FcFormCRepository fcFormCRepository;
    @Autowired
    private FcFormCExploredForestLandRepository fcFormCExploredForestLandRepository;
    @Autowired
    private FcFormDRepository fcFormDRepository;
    @Autowired
    private FcFormDLegalStatusRepository fcFormDLegalStatusRepository;
    @Autowired
    private EcPartCRepository ecPartCRepository;
    @Autowired
    private EcForm8TransferOfTORRepository ecForm8TransferOfTORRepository;
    @Autowired
    private EcForm9TransferOfECRepository ecForm9TransferOfECRepository;
    @Autowired
    private EcForm10ProjectDetailRepository ecForm10ProjectDetailRepository;
    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;
    @Autowired
    private WildLifeClearanceRepository wildLifeClearanceRepository;
    @Autowired
    private CrzBasicDetailsRepository crzBasicDetailsRepository;
    @Autowired
    private ProjectDetailRepository projectDetailsRepository;
    @Autowired
    private SectorEntityRepository sectorEntityRepository;
    @Autowired
    private WorkGroupEntityRepository workGroupEntityRepository;
    @Autowired
    private OfficeEntityRepository officeEntityRepository;
    @Autowired
    private EcPartCService ecPartCService;
    @Autowired
    private EcFormVService ecFormVService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FcFormERepository fcFormERepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;
    @Autowired
    private OfficeJsonFcRepository officeJsonFcRepository;
    @Autowired
    private EcForm7Repository ecForm7Repository;
    @Autowired
    private EcForm6ProjectDetailsV2Repo ecForm6ProjectDetailsRepo;
    @Autowired
    private EcForm11ProjectDetailsRepository ecForm11ProjectDetailsRepository;
    @Autowired
    private EcForm2ProjectDetailsRepository ecForm2ProjectDetailsRepository;
    @Autowired
    private EcForm12Repository ecForm12Repository;
    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;
    @Autowired
    private PasswordEncoder passEncoder;


    public ResponseEntity<Object> getCafDataByProposalNo(String proposalNo, Integer proposal_id, String fileNo) {
        ProponentApplications proponentApplication = new ProponentApplications();
        if (proposal_id != null) {
            proponentApplication = proponentApplicationRepository.findById(proposal_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Proposal not found with id"));
        }
        if (proposalNo != null) {
            proponentApplication = proponentApplicationRepository.getApplicationByProposalNo(proposalNo);
        }
        if (fileNo != null) {
            proponentApplication = proponentApplicationRepository.getApplicationByFileNo(fileNo);

        }
        proponentApplication.setProjectDetailDto(new ProjectDetailDto(proponentApplication.getProjectDetails()));
        log.error("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@AFTER" + proposalNo);

        Applications application = applicationsRepository.findById(proponentApplication.getApplications().getId())
                .get();
        ClearenceCafData clearenceCafData = new ClearenceCafData();
        switch (application.getId()) {

            case 1:
                ForestClearance forestClearance = forestClearanceRepository.findById(proponentApplication.getProposal_id())
                        .get();

                clearenceCafData.setClearence(forestClearance);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING FC part B CAF DATA - SUCCESS");
                return ResponseHandler.generateResponse("FC part B  CAF Data", HttpStatus.OK, "", clearenceCafData);
            case 8:
                FcFormBProjectDetails fcFormBProjectDetails = fcFormBProjectDetailsRepository
                        .findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(fcFormBProjectDetails);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING FC CAF DATA - SUCCESS");
                return ResponseHandler.generateResponse("FC CAF Data", HttpStatus.OK, "", clearenceCafData);
            case 7:
                FcFormC fcFormC = fcFormCRepository.findById(proponentApplication.getProposal_id()).get();
                List<FcFormCExploredForestLand> forestLand = fcFormCExploredForestLandRepository
                        .findByFcFormCByID(proponentApplication.getProposal_id());
                fcFormC.setFcFormCExploredForestLands(forestLand);
                clearenceCafData.setClearence(fcFormC);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING FC CAF DATA - SUCCESS");
                return ResponseHandler.generateResponse("FC CAF Data", HttpStatus.OK, "", clearenceCafData);
            case 9:
                FcFormD fcFormD = fcFormDRepository.findById(proponentApplication.getProposal_id()).get();
                List<FcFormDLegalStatus> legalStatus = fcFormDLegalStatusRepository
                        .findByFcFormDLegalStatus(proponentApplication.getProposal_id());
                fcFormD.setFcFormDLegalStatus(legalStatus);
                clearenceCafData.setClearence(fcFormD);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING FC CAF DATA - SUCCESS");
                return ResponseHandler.generateResponse("FC CAF Data", HttpStatus.OK, "", clearenceCafData);
            case 2:
            case 37:
//			EcPartC ecPartC = ecPartCRepository.findById(proponentApplication.getProposal_id()).get();
                EcPartC ecPartC = ecPartCService.getEcPartCForm(proponentApplication.getProposal_id());
                clearenceCafData.setClearence(ecPartC);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING EC part C CAF Data - SUCCESS");
                return ResponseHandler.generateResponse("EC part C CAF Data", HttpStatus.OK, "", clearenceCafData);

            case 34:
                EcFormV ecFormV = ecFormVService.getEcFormV(proponentApplication.getProposal_id());
                clearenceCafData.setClearence(ecFormV);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING EC part C CAF Data - SUCCESS");
                return ResponseHandler.generateResponse("EC part C CAF Data", HttpStatus.OK, "", clearenceCafData);

            case 3:
                WildLifeClearance wildLifeClearance = wildLifeClearanceRepository
                        .findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(wildLifeClearance);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING Wildlife CAF Data - SUCCESS");
                return ResponseHandler.generateResponse("Wildlife CAF Data", HttpStatus.OK, "", clearenceCafData);
            case 4:
                CrzBasicDetails crzBasicDetails = crzBasicDetailsRepository.findById(proponentApplication.getProposal_id())
                        .get();
                clearenceCafData.setClearence(crzBasicDetails);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING CRZ CAF Data - SUCCESS");
                return ResponseHandler.generateResponse("CRZ CAF Data", HttpStatus.OK, "", clearenceCafData);
            case 5:
            case 36:
                EnvironmentClearence environmentClearence = environmentClearenceRepository
                        .findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(environmentClearence);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING EC Part A+B CAF Data - SUCCESS");
                return ResponseHandler.generateResponse("EC Part A+B CAF Data", HttpStatus.OK, "", clearenceCafData);

            case 38:
                EcForm7 ecForm7 = ecForm7Repository.findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(ecForm7);
                clearenceCafData.setProponentApplications(proponentApplication);
                return ResponseHandler.generateResponse("EC Form 7 caf Data", HttpStatus.OK, "", clearenceCafData);

            case 12:
                FcFormE fcFormE = fcFormERepository.findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(fcFormE);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING FC CAF DATA - SUCCESS");
                return ResponseHandler.generateResponse("FC CAF Data", HttpStatus.OK, "", clearenceCafData);

            case 39:
                EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
                        .findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(ecForm8TransferOfTOR);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING EcForm8TransferOfTOR  SUCCESS");
                return ResponseHandler.generateResponse("FC CAF Data", HttpStatus.OK, "", clearenceCafData);

            case 44:
                EcForm9TransferOfEC ecForm9TransferOfEC = ecForm9TransferOfECRepository
                        .findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(ecForm9TransferOfEC);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING EcForm9TransferOfEC - SUCCESS");
                return ResponseHandler.generateResponse("FC CAF Data", HttpStatus.OK, "", clearenceCafData);

            case 62:
                EcForm10BasicInformation ecForm10BasicInformation = ecForm10BasicInformationRepository
                        .findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(ecForm10BasicInformation);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING EcForm10ProjectDetails - SUCCESS");
                return ResponseHandler.generateResponse("FC CAF Data", HttpStatus.OK, "", clearenceCafData);

            case 65:
                EcForm6ProjectDetailsV2 ecForm6ProjectDetails = ecForm6ProjectDetailsRepo
                        .findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(ecForm6ProjectDetails);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo
                        + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo
                        + " ---- RETRIEVING EcForm6ProjectDetails - SUCCESS");
                return ResponseHandler.generateResponse("FC CAF Data", HttpStatus.OK, "", clearenceCafData);

            case 225:
                EcForm11ProjectDetails ecForm11ProjectDetails = ecForm11ProjectDetailsRepository.findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(ecForm11ProjectDetails);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo + " ---- RETRIEVING EcForm11ProjectDetails - SUCCESS");
                return ResponseHandler.generateResponse("getting form 11 data with caf", HttpStatus.OK, "", clearenceCafData);

            case 229:
                EcForm2ProjectDetails ecForm2ProjectDetails = ecForm2ProjectDetailsRepository.findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(ecForm2ProjectDetails);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo + " ---- RETRIEVING EcForm11ProjectDetails - SUCCESS");
                return ResponseHandler.generateResponse("getting form 2 data with caf", HttpStatus.OK, "", clearenceCafData);

            case 226:
                EcForm12 ecForm12=ecForm12Repository.findById(proponentApplication.getProposal_id()).get();
                clearenceCafData.setClearence(ecForm12);
                clearenceCafData.setProponentApplications(proponentApplication);
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo + " ---- RETRIEVING EcForm12 - SUCCESS");
                return ResponseHandler.generateResponse("getting form 12 data with caf", HttpStatus.OK, "", clearenceCafData);

            default:
                log.info("INFO ------------ getCafDataByProposalNo WITH proposalNo----> " + proposalNo + " ----proposal_id ----> " + proposal_id + " fileNo----> " + fileNo + " ---- NO DATA - SUCCESS");
                return ResponseHandler.generateResponse("No Data", HttpStatus.OK, "", "");

        }
    }

    public ResponseEntity<Object> getProponentDataByProjectId(Integer projectId) {

        List<ProjectDetails> projectDetails = projectDetailsRepository.findAll();
        log.info("INFO ------------ getProponentDataByProjectId WITH projectId----> " + projectId
                + " ---- RETRIEVING PROPONENT DATA BY PROJECT_ID - SUCCESS");
        return ResponseHandler.generateResponse("project List Data", HttpStatus.OK, "", projectDetails);

    }

    public ResponseEntity<Object> updateProponentApplication(String proposalNo, Long SectorId, Long OfficeEntity,
                                                             Long WorkGroupEntity, Long role, Integer userId, String status, String last_remarks,
                                                             String moefccFileNumber, Boolean isState) {
        try {
            SectorEntity secTemp;
            OfficeEntity officeTemp;
            WorkGroupEntity workgroupTemp;
            Role roleTemp;
            User userTemp;

            ProponentApplications proponentApplication = proponentApplicationRepository
                    .getApplicationByProposalNo(proposalNo);

            if (SectorId != null) {
                secTemp = sectorEntityRepository.findById(SectorId)
                        .orElseThrow(() -> new NotFoundException("Sector not Found"));
                proponentApplication.setSectorEntity(secTemp);
            }
            if (OfficeEntity != null) {
                officeTemp = officeEntityRepository.findById(OfficeEntity)
                        .orElseThrow(() -> new NotFoundException("Office not Found"));
                proponentApplication.setOfficeEntity(officeTemp);
            }
            if (WorkGroupEntity != null) {
                workgroupTemp = workGroupEntityRepository.findById(SectorId)
                        .orElseThrow(() -> new NotFoundException("Work Group not Found"));
                proponentApplication.setWorkGroupEntity(workgroupTemp);
            }
            if (role != null) {
                roleTemp = roleRepository.findById(role).orElseThrow(() -> new NotFoundException("Role not Found"));
                proponentApplication.setRole(roleTemp);
            }
            if (userId != null) {
                userTemp = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not Found"));
                proponentApplication.setUser(userTemp);
            }
            if (status != null) {
                proponentApplication.setLast_status(status);
                if (status.equals("EDS_RAISED")) {
                    CommonFormDetail temp = commonFormDetailRepository.getCAF(proponentApplication.getCaf_id());
                    temp.setCaf_status(Caf_Status.EDS_RAISED);
                    commonFormDetailRepository.save(temp);
                }
            }

            if (last_remarks != null) {
                proponentApplication.setLast_remarks(last_remarks);
            }
            if (moefccFileNumber != null) {
                // Integer count =
                // proponentApplicationRepository.fileNameCheck(moefccFileNumber);
//                if (count == 0) {
                if (isState == true) {
                    proponentApplication.setStateFileNumber(moefccFileNumber);
                } else {
                    proponentApplication.setMoefccFileNumber(moefccFileNumber);
                }

//                } else {
//                    return ResponseHandler.generateResponse("Moefcc File name already exists.", HttpStatus.BAD_REQUEST, "Exception", "");
                // }
            }
            /*
             * Integer pid=proponentApplication.getId(); proponent.setId(pid);
             */
            log.info("INFO ------------ updateProponentApplication WITH proposalNo----> " + proposalNo
                    + " ---- UPDATING PROPONENT APPLICATION BY PROPOSAL_NO - SUCCESS");
            return ResponseHandler.generateResponse("Update Proponent Application By Proposal No", HttpStatus.OK, "",
                    proponentApplicationRepository.save(proponentApplication));
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: updateProponentApplication WITH proposalNo----> " + proposalNo
                    + " ---- UPDATING PROPONENT APPLICATION BY PROPOSAL_NO - FAILURE");
            return ResponseHandler.generateResponse("Update Proponent Application By Proposal No",
                    HttpStatus.BAD_REQUEST, "Exception", ex.getMessage());
        }
    }

    public ResponseEntity<Object> getProponentApplication(String status, Integer UserId) {
        try {
            /*
             * List<ProponentApplications> proponentApplications =
             * proponentApplicationRepository.findAll().stream() .map(val -> { if
             * (val.getUpdated_by() != 1 && val.getUpdated_by() != null) {
             * val.setUpdatedByUser(new
             * UpdatedUser(userRepository.findById(val.getUpdated_by()) .orElseThrow(() ->
             * new UserNotFoundException("user not found with id")))); } if
             * (val.getProjectDetails() != null) { val.setProjectDetailDto(new
             * ProjectDetailDto(val.getProjectDetails())); } return val; }).filter(Value ->
             * Value.getLast_status().toString().equals(status != null ? status :
             * Caf_Status.SUBMITTED.name())) .collect(Collectors.toList());
             */
            List<ProponentApplications> proponentApplications = proponentApplicationRepository.findAllProponents(UserId)
                    .stream().map(val -> {
                        if (val.getUpdated_by() != 1 && val.getUpdated_by() != null) {
                            val.setUpdatedByUser(new UpdatedUser(userRepository.findById(val.getUpdated_by())
                                    .orElseThrow(() -> new UserNotFoundException("user not found with id"))));
                        }
                        if (val.getProjectDetails() != null) {
                            val.setProjectDetailDto(new ProjectDetailDto(val.getProjectDetails()));
                        }
                        return val;
                    })
                    .filter(Value -> Value.getLast_status().toString()
                            .equals(status != null ? status : Caf_Status.SUBMITTED.name()))
                    .collect(Collectors.toList());

            log.info("INFO ------------ getProponentApplication WITH status----> " + status
                    + " ---- RETRIEVING PROPONENT APPLICATION BY STATUS - SUCCESS");
            return ResponseHandler.generateResponse("List of Proponent Applications", HttpStatus.OK, "",
                    proponentApplications);
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getProponentApplication WITH status----> " + status
                    + " ---- RETRIEVING PROPONENT APPLICATION BY STATUS - FAILURE");
            return ResponseHandler.generateResponse("List of Proponent Applications", HttpStatus.BAD_REQUEST,
                    "Exception", ex.getMessage());
        }
    }

    public ResponseEntity<Object> getClearancesCount(Integer projectId, UserPrincipal loggedInUser) {

        ClearanceCount clearanceCount = new ClearanceCount();
        List<ProponentApplications> proponentApplications = null;
        if (projectId == 0) {
            proponentApplications = proponentApplicationRepository.findAllProponents(loggedInUser.getId());
        } else {

            proponentApplications = proponentApplicationRepository.findByProjectId(projectId, loggedInUser.getId());

        }

        List<ProponentApplications> ecProponentApplicants = proponentApplications.stream()
                .filter(proponentApplicant -> proponentApplicant.getClearance_id().equals(5))
                .collect(Collectors.toList());

        int noOfAppliedEcProponentApplicants = (int) ecProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.SUBMITTED.name()))
                .count();

        int noOfApprovedEcProponentApplicants = (int) ecProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.APPROVED.name()))
                .count();

        int noOfRejectedEcProponentApplicants = (int) ecProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.REJECTED.name()))
                .count();

        EnvironmentClearanceCount environmentClearanceCount = new EnvironmentClearanceCount();
        environmentClearanceCount.setApplied(noOfAppliedEcProponentApplicants);
        environmentClearanceCount.setApproved(noOfApprovedEcProponentApplicants);
        environmentClearanceCount.setRejected(noOfRejectedEcProponentApplicants);
        clearanceCount.setEnvironmentClearanceCount(environmentClearanceCount);

        List<ProponentApplications> fcProponentApplicants = proponentApplications.stream()
                .filter(proponentApplicant -> proponentApplicant.getClearance_id().equals(1))
                .collect(Collectors.toList());

        int noOfAppliedFcProponentApplicants = (int) fcProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.SUBMITTED.name()))
                .count();

        int noOfApprovedFcProponentApplicants = (int) fcProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.APPROVED.name()))
                .count();

        int noOfRejectedFcProponentApplicants = (int) fcProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.REJECTED.name()))
                .count();

        ForestClearanceCount forestClearanceCount = new ForestClearanceCount();
        forestClearanceCount.setApplied(noOfAppliedFcProponentApplicants);
        forestClearanceCount.setApproved(noOfApprovedFcProponentApplicants);
        forestClearanceCount.setRejected(noOfRejectedFcProponentApplicants);
        clearanceCount.setForestClearanceCount(forestClearanceCount);

        List<ProponentApplications> wildLifeProponentApplicants = proponentApplications.stream()
                .filter(proponentApplicant -> proponentApplicant.getClearance_id().equals(3))
                .collect(Collectors.toList());
        /*
         * int noOfAppliedWcProponentApplicants = (int)
         * wildLifeProponentApplicants.stream() .filter(proponentApplicant ->
         * proponentApplicant.getLast_status().ordinal() == 1).count();
         *
         * int noOfApprovedWcProponentApplicants = (int)
         * wildLifeProponentApplicants.stream() .filter(proponentApplicant ->
         * proponentApplicant.getLast_status().ordinal() == 2).count();
         *
         * int noOfRejectedWcProponentApplicants = (int)
         * wildLifeProponentApplicants.stream() .filter(proponentApplicant ->
         * proponentApplicant.getLast_status().ordinal() == 3).count();
         */
        int noOfAppliedWcProponentApplicants = (int) wildLifeProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.SUBMITTED.name()))
                .count();

        int noOfApprovedWcProponentApplicants = (int) wildLifeProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.APPROVED.name()))
                .count();

        int noOfRejectedWcProponentApplicants = (int) wildLifeProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.REJECTED.name()))
                .count();

        WildLifeClearanceCount wildLifeClearanceCount = new WildLifeClearanceCount();
        wildLifeClearanceCount.setApplied(noOfAppliedWcProponentApplicants);
        wildLifeClearanceCount.setApproved(noOfApprovedWcProponentApplicants);
        wildLifeClearanceCount.setRejected(noOfRejectedWcProponentApplicants);
        clearanceCount.setWildLifeClearanceCount(wildLifeClearanceCount);

        List<ProponentApplications> crzProponentApplicants = proponentApplications.stream()
                .filter(proponentApplicant -> proponentApplicant.getClearance_id().equals(4))
                .collect(Collectors.toList());

        int noOfAppliedcrzProponentApplicants = (int) crzProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.SUBMITTED.name()))
                .count();

        int noOfApprovedcrzProponentApplicants = (int) crzProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.APPROVED.name()))
                .count();

        int noOfRejectedcrzProponentApplicants = (int) crzProponentApplicants.stream()
                .filter(proponentApplicant -> proponentApplicant.getLast_status().equals(Caf_Status.REJECTED.name()))
                .count();

        CRZClearanceCount cRZClearanceCount = new CRZClearanceCount();
        cRZClearanceCount.setApplied(noOfAppliedcrzProponentApplicants);
        cRZClearanceCount.setApproved(noOfApprovedcrzProponentApplicants);
        cRZClearanceCount.setRejected(noOfRejectedcrzProponentApplicants);

        clearanceCount.setcRZClearanceCount(cRZClearanceCount);
        log.info("INFO ------------ getClearancesCount WITH projectId----> " + projectId + "----loggedInUser_ID ---->"
                + loggedInUser.getId() + " ---- RETRIEVING PROPONENT APPLICATION BY STATUS - SUCCESS");
        return ResponseHandler.generateResponse("Clearance Count Data", HttpStatus.OK, "", clearanceCount);

    }

    public ResponseEntity<Object> setTransferProposalNumber(String proposal_no, String transfer_proposal_remarks,
                                                            Date transfer_proposal_date, String transfer_proposal_no) {
        try {
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(proposal_no);
            proponentApplications.setTransfer_proposal_date(transfer_proposal_date);
            proponentApplications.setTransfer_proposal_no(transfer_proposal_no);
            proponentApplications.setTransfer_proposal_remarks(transfer_proposal_remarks);
            proponentApplicationRepository.save(proponentApplications);

            EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(proposal_no);
            environmentClearence.setTransfer_proposal_date(transfer_proposal_date);
            environmentClearence.setTransfer_proposal_no(transfer_proposal_no);
            environmentClearence.setTransfer_proposal_remarks(transfer_proposal_remarks);
            environmentClearenceRepository.save(environmentClearence);

            return ResponseHandler.generateResponse("Updated proponent application and environment clearance",
                    HttpStatus.OK, "", environmentClearence);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Proposal number ------>" + proposal_no + "----- not found.",
                    HttpStatus.BAD_REQUEST, "", ex.getMessage());
        }
    }

    public ResponseEntity<Object> copyProposal(Integer proposalId) throws PariveshException {
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            Integer ec_id = proponentApplicationRepository.getProponentAppId(proposalId);
            String moefcc_file_number = proponentApplicationRepository.getMoefccFileNo(proposalId);
            if (moefcc_file_number == null) {
                moefcc_file_number = "";
            }
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(ec_id);
            ProponentApplications applications = null;
            Integer clearance_id = proponentApplications.getClearance_id();
            if (clearance_id == 5) {
                EnvironmentClearence environmentClearence = environmentClearenceRepository.findById(ec_id)
                        .orElseThrow();
                CommonFormDetail commonForm = commonFormDetailRepository.findById(proponentApplications.getCaf_id())
                        .orElseThrow(() -> new ProjectNotFoundException(
                                "Caf form not found for caf id ----> " + proponentApplications.getCaf_id()));
                String proposal_no = null;
                Integer proposal_sequence = 0;
                Integer maxCount = proponentApplicationRepository.getMaxProposalSequence();
                if (maxCount != null) {
                    proposal_sequence = Integer.valueOf(generateSequenceNumber(maxCount));
                    proposal_no = generateProposalNo(maxCount, environmentClearence, commonForm);
                }
                Applications app = applicationsRepository.findById(36).get();
                proponentApplications.setMoefccFileNumber(moefcc_file_number);
                proponentApplications.setProposal_sequence(proposal_sequence);
                proponentApplications.setProposal_id(ec_id);
                proponentApplications.setApplications(app);
                proponentApplications.setClearance_id(36);
                proponentApplications.setProposal_no(proposal_no);
                proponentApplications.setLast_status(Caf_Status.DRAFT.toString());
                Integer ecUpdate = environmentClearenceRepository.updateEcProposal(ec_id, proposal_no);

                Integer maxCountCaf = proponentApplicationRepository.getMaxCafSequence();
                if (maxCountCaf != null) {
                    Integer id = proponentApplications.getCaf_id();
                    Integer Caf_id_sequence = Integer.valueOf(generateSequenceNumber(maxCountCaf));
                    String Caf_id = generateCafId(maxCountCaf);
                    String Proposal_for = Form_for.Amendment.toString();
                    Integer Caf_status = Caf_Status.DRAFT.ordinal();

                    commonFormDetailRepository.updateCAF(Caf_id_sequence, Caf_id, Proposal_for, Caf_status, id);
                }

                applications = proponentApplicationRepository.save(proponentApplications);

                EnvironmentClearence ec = environmentClearenceRepository.findById(proposalId).orElse(null);
                if (ec != null) {
                    log.info("setting other property for tor amendment");
                    if (ec.getProject_category().equalsIgnoreCase("B2")
                            && commonForm.getCafOthers().getIs_any_violayion_involved().equalsIgnoreCase("NO")) {
                        OtherPropString.put("Proposal For", "Amendment in EC");
                    } else {
                        OtherPropString.put("Proposal For", app.getGeneral_name());
                    }
                    String activity_name = environmentClearenceRepository.getMajorActivityName(ec.getId());
                    String itemNo = environmentClearenceRepository.getItemNo(ec.getId());
//			System.out.println("activity:"+ activity_name);
                    OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
                    OtherPropString.put("Sector",
                            activitySectorRepository.getSector(environmentClearence.getMajor_activity_id(),
                                    environmentClearence.getMajor_sub_activity_id()).getSector_code());

//			System.out.println(OtherPropString);

                    updateOtherPropertyService.updateOtherProperty(ec_id, OtherPropString);
                }

            }
            return ResponseHandler.generateResponse("Proponent Applications----> ", HttpStatus.OK, "null",
                    applications);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in copy proposal for proposalId- " + proposalId, e);
        }
    }

    public ResponseEntity<Object> copyProposalCrz(Integer proposalId) throws PariveshException {
        try {
            Integer crz_id = proponentApplicationRepository.getCrzProponentAppId(proposalId);
            String moefcc_file_number = proponentApplicationRepository.getMoefccFileNo(proposalId);
            if (moefcc_file_number == null) {
                moefcc_file_number = "";
            }
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(crz_id);
            ProponentApplications applications = null;
            Integer clearance_id = proponentApplications.getClearance_id();
            if (clearance_id == 4) {
                CrzBasicDetails crzBasicDetails = crzBasicDetailsRepository.findById(crz_id).orElseThrow();
                CommonFormDetail commonForm = commonFormDetailRepository.findById(proponentApplications.getCaf_id())
                        .orElseThrow(() -> new ProjectNotFoundException(
                                "Caf form not found for caf id ----> " + proponentApplications.getCaf_id()));
                String proposal_no = null;
                Integer proposal_sequence = 0;
                Integer maxCount = proponentApplicationRepository.getMaxProposalSequence();
                if (maxCount != null) {
                    proposal_sequence = Integer.valueOf(generateSequenceNumber(maxCount));
                    proposal_no = generateProposalNoCRZ(maxCount, crzBasicDetails, commonForm);
                }
                Applications app = applicationsRepository.findById(80).get();
                proponentApplications.setMoefccFileNumber(moefcc_file_number);
                proponentApplications.setProposal_sequence(proposal_sequence);
                proponentApplications.setProposal_id(crz_id);
                proponentApplications.setApplications(app);
                proponentApplications.setClearance_id(80);
                proponentApplications.setProposal_no(proposal_no);
                proponentApplications.setLast_status(Caf_Status.DRAFT.toString());
                Integer crzUpdate = crzBasicDetailsRepository.updateCrzProposal(crz_id, proposal_no);

                Integer maxCountCaf = proponentApplicationRepository.getMaxCafSequence();
                if (maxCountCaf != null) {
                    Integer id = proponentApplications.getCaf_id();
                    Integer Caf_id_sequence = Integer.valueOf(generateSequenceNumber(maxCountCaf));
                    String Caf_id = generateCafId(maxCountCaf);
                    String Proposal_for = Form_for.Amendment.toString();
                    Integer Caf_status = Caf_Status.DRAFT.ordinal();

                    commonFormDetailRepository.updateCAF(Caf_id_sequence, Caf_id, Proposal_for, Caf_status, id);
                }

                applications = proponentApplicationRepository.save(proponentApplications);

            }
            return ResponseHandler.generateResponse("Proponent Applications----> ", HttpStatus.OK, "null",
                    applications);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in copy proposal for proposalId- " + proposalId, e);
        }
    }

    public ResponseEntity<Object> copyProposalEcPartC(Integer proposalId) throws PariveshException {
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            Integer ec_c_id_new = proponentApplicationRepository.getProponentAppIdEcPartC(proposalId);
            String moefcc_file_number = proponentApplicationRepository.getMoefccFileNo(proposalId);
            if (moefcc_file_number == null) {
                moefcc_file_number = "";
            }
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(ec_c_id_new);
            ProponentApplications applications = null;
            Integer clearance_id = proponentApplications.getClearance_id();

            if (clearance_id == 2) {
                EcPartC temp2 = null;
                EcPartC ecPartC = ecPartCRepository.findById(ec_c_id_new).orElseThrow();
                CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(proponentApplications.getCaf_id())
                        .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
                String proposal_no = null;
                Integer proposal_sequence = 0;
                Integer maxCount = proponentApplicationRepository.getMaxProposalSequence();
                if (maxCount != null) {
                    proponentApplications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    proposal_no = generateProposalNoEcC(maxCount, cafDetail, ecPartC);
                    proponentApplications.setProposal_no(proposal_no);
                    ecPartC.setProposal_no(proposal_no);
                    Integer ecUpdate = environmentClearenceRepository.updateEcPartCProposal(ec_c_id_new, proposal_no);
                }

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
                Applications app = applicationsRepository.findById(37).get();
                proponentApplications.setMoefccFileNumber(moefcc_file_number);
                proponentApplications.setProposal_id(ec_c_id_new);
                proponentApplications.setApplications(app);
                proponentApplications.setClearance_id(37);
                proponentApplications.setLast_status(Caf_Status.DRAFT.toString());

                Integer ecUpdate = environmentClearenceRepository.updateEcPartCParent(ec_c_id_new, proposalId);
                Integer maxCountCaf = proponentApplicationRepository.getMaxCafSequence();
                if (maxCountCaf != null) {
                    Integer id = proponentApplications.getCaf_id();
                    Integer Caf_id_sequence = Integer.valueOf(generateSequenceNumber(maxCountCaf));
                    String Caf_id = generateCafId(maxCountCaf);
                    String Proposal_for = Form_for.Amendment.toString();
                    Integer Caf_status = Caf_Status.DRAFT.ordinal();

                    commonFormDetailRepository.updateCAF(Caf_id_sequence, Caf_id, Proposal_for, Caf_status, id);
                }

                applications = proponentApplicationRepository.save(proponentApplications);

                /*----------------------tor copy--------------------------------*/
                EnvironmentClearence environmentClearence = environmentClearenceRepository.findById(ecPartC.getEc_id())
                        .orElseThrow();
                moefcc_file_number = proponentApplicationRepository.getMoefccFileNo(ecPartC.getEc_id());
                if (moefcc_file_number == null) {
                    moefcc_file_number = "";
                }
                ProponentApplications proponentApplications2 = proponentApplicationRepository
                        .getApplicationByProposalId(environmentClearence.getId());
                ProponentApplications applications2 = null;
                Integer clearance_id2 = proponentApplications2.getClearance_id();
                if (clearance_id2 == 5) {
                    maxCount = proponentApplicationRepository.getMaxProposalSequence();
                    if (maxCount != null) {
                        proposal_sequence = Integer.valueOf(generateSequenceNumber(maxCount));
                        proposal_no = generateProposalNo(maxCount, environmentClearence, cafDetail);
                    }
                    app = applicationsRepository.findById(37).get();
                    proponentApplications2.setMoefccFileNumber(moefcc_file_number);
                    proponentApplications2.setProposal_sequence(proposal_sequence);
                    proponentApplications2.setProposal_id(ecPartC.getEc_id());
                    proponentApplications2.setApplications(app);
                    proponentApplications2.setClearance_id(37);
                    proponentApplications2.setProposal_no(proposal_no);
                    proponentApplications2.setLast_status(Caf_Status.DRAFT.toString());
                    proponentApplications2.setLegacy_proposal_type("EC_FORM_4_PART_A");
                    ecUpdate = environmentClearenceRepository.updateEcProposalLegacy(ecPartC.getEc_id(), proposal_no);
                    applications2 = proponentApplicationRepository.save(proponentApplications2);
                }

                EcPartC ec = ecPartCRepository.findById(proposalId).orElse(null);
                if (ec != null) {
                    log.info("setting other property for tor amendment");
                    OtherPropString.put("Proposal For", app.getGeneral_name());
                    String activity_name = ecPartCRepository.getMajorActivityName(ec.getId());
                    String itemNo = ecPartCRepository.getItemNo(ec.getId());
                    OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
                    OtherPropString.put("Sector", activitySectorRepository
                            .getSector(ec.getMajor_activity_id(), ec.getMajor_sub_activity_id()).getSector_code());

//			System.out.println(OtherPropString);

                    updateOtherPropertyService.updateOtherProperty(ec_c_id_new, OtherPropString);
                }

            }
            return ResponseHandler.generateResponse("Proponent Applications----> ", HttpStatus.OK, "null",
                    applications);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in copy proposal for proposalId- " + proposalId, e);
        }
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateCafId(int maxcount) {
        String cafId = "CAF/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
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

    private String generateProposalNoCRZ(int maxcount, CrzBasicDetails form, CommonFormDetail commonForm) {

        String cafId = "IA/"
                + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                + "/" + "CRZ" + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }

    public String generateSequenceNumberEcC(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNoEcC(int maxcount, CommonFormDetail form, EcPartC ecPartC) {
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

    public ResponseEntity<Object> getOfficeJsonFc(Integer proposalId) {

        log.info("INFO -------- getOfficeJsonFc WITH proposalId----> " + proposalId);

        return ResponseHandler.generateResponse("project List Data", HttpStatus.OK, "",
                officeJsonFcRepository.getOfficeJsonFc(proposalId));

    }

    public ResponseEntity<Object> updateProponentApplicationV2(String proposalNo, String other_property) {
        try {
            ProponentApplications proponentApplication = proponentApplicationRepository
                    .getApplicationByProposalNo(proposalNo);
            if (other_property != null) {
                proponentApplication.setOther_property(other_property);
            }
            log.info("INFO ------------ updateProponentApplication WITH proposalNo----> " + proposalNo
                    + " ---- UPDATING PROPONENT APPLICATION BY PROPOSAL_NO - SUCCESS");
            return ResponseHandler.generateResponse("Update Proponent Application By Proposal No", HttpStatus.OK, "",
                    proponentApplicationRepository.save(proponentApplication));
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: updateProponentApplication WITH proposalNo----> " + proposalNo
                    + " ---- UPDATING PROPONENT APPLICATION BY PROPOSAL_NO - FAILURE");
            return ResponseHandler.generateResponse("Update Proponent Application By Proposal No",
                    HttpStatus.BAD_REQUEST, "Exception", ex.getMessage());
        }
    }

    /**
     * <b>updateOtherProperty</b>
     * <p>
     * Update other_property in proponent_applications
     * </p>
     *
     * @param proposalId
     * @param newPropertyData
     * @return
     */
    public ProponentApplications updateOtherProperty(Integer proposalId, Map<String, Object> newPropertyData) {

        log.info("UPDATEOTHERPROPERTY  proposalId:{} , newPropertyData:{}", proposalId, newPropertyData);
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getApplicationByProposalId(proposalId);
        String otherProperty = proponentApplications.getOther_property();
        log.info("PROPONENTAPPLICATIONS string-otherProperty: {}", otherProperty);
        try {
            JSONArray otherPropertyJSONArray = new JSONArray();
            Map<String, Object> propertyData = new HashMap<String, Object>();
            if (!StringUtils.isAllEmpty(otherProperty)) {
                otherPropertyJSONArray = new JSONArray(otherProperty);
                propertyData.putAll(convertJsonArrayToMap(otherPropertyJSONArray));

            }
            log.info("OLD DATA jsonArray-propertyData: {}", propertyData);
            propertyData.putAll(newPropertyData);
            JSONArray updatedOtherPropertyJSONArray = convertMapToJSONArray(propertyData);
            log.info("NEW DATA updatedOtherPropertyJSONArray: {}", updatedOtherPropertyJSONArray);

            proponentApplications.setOther_property(updatedOtherPropertyJSONArray.toString());

            proponentApplicationRepository.save(proponentApplications);
            log.info("UPDATED OtherProperty JSONArray: {}", updatedOtherPropertyJSONArray);
        } catch (JSONException ex) {
            log.info("ERROR - JSONException while parsing json array: {}", ex.getMessage());
            log.error("Exception:: ", ex);
        } catch (Exception ex) {
            log.info("ERROR - Exception while parsing json array: {}", ex.getMessage());
            log.error("Exception:: ", ex);
        }

        return proponentApplications;

    }

    /**
     * <b>convertJsonArrayToMap</b>
     * <p>
     * Convert JsonArray to Map
     * </p>
     *
     * @param jsonArray
     * @return
     * @throws JSONException
     */
    public Map<String, Object> convertJsonArrayToMap(JSONArray jsonArray) throws JSONException {
        Map<String, Object> otherProperty = new HashMap<String, Object>();
        if (jsonArray != null) {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject prop = (JSONObject) jsonArray.get(i);
                otherProperty.put(prop.get("label").toString(), prop.get("value"));

            }
        }
        log.info("convertJsonArryToMap otherProperty: {}", otherProperty);
        return otherProperty;
    }

    /**
     * <b>convertMapToJSONArray</b>
     * <p>
     * Convert Map to JsonArray
     * </p>
     *
     * @param otherProperty
     * @return
     * @throws JSONException
     */
    public JSONArray convertMapToJSONArray(Map<String, Object> otherProperty) throws JSONException {
        JSONArray otherPropertyJSONArray = new JSONArray();
        if (!otherProperty.isEmpty()) {
            otherProperty.forEach((key, value) -> {
                try {
                    JSONObject prop = new JSONObject();
                    prop.put("label", key);
                    prop.put("value", value);
                    otherPropertyJSONArray.put(prop);
                } catch (JSONException ex) {
                    log.info("ERROR in convertMapToJSONArray error msg: {}", ex.getMessage());
                    log.error("Exception:: ", ex);
                }

            });
        }
        log.info("convertMapToJSONArray otherPropertyJSONArray: {}", otherPropertyJSONArray);
        return otherPropertyJSONArray;
    }

    /**
     * <b>updateCertificateURL</b>
     * <p>
     * Update Certificate URL in Proponent Application
     * </p>
     *
     * @param id
     * @param url
     * @return
     * @throws PariveshException
     */
    public ResponseEntity<Object> updateCertificateURL(Integer id, String url, String type) throws PariveshException {
        try {
            Integer update;
            if (type.equals("certificate_url")) {
                update = proponentApplicationRepository.updateCertificateURL(id, url);
            } else {
                update = proponentApplicationRepository.updateCertificateURL1(id, url);
            }

            if (update == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Certificate URL Updated", HttpStatus.OK, "", "Updated");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in updateCertificateURL id- " + id, e);
        }
    }

    public ResponseEntity<Object> getProposalList(Integer page, Integer size, UserPrincipal user, String statusList) {

        List<ProponentApplications> applications = null;
        log.info("ProponentApplicationService::getProposalList - statusList " + statusList);
        List<String> status = Stream.of(statusList.split(",", -1)).collect(Collectors.toList());
        log.info("ProponentApplicationService::getProposalList - status " + status.toString());
        if (page == null || size == null) {
            page = 0;
            size = 10000;

            applications = proponentApplicationRepository.getProposalList(user.getId(), status, page, size);
        } else {
            applications = proponentApplicationRepository.getProposalList(user.getId(), status, page, size);
        }
        return ResponseHandler.generateResponse("project List Data", HttpStatus.OK, "", applications);
    }

    public ResponseEntity<Object> getECProposalList(Integer user_id, Integer page, Integer size, String statusList) {

        List<ProponentApplicationWithSector> applications = null;
        log.info("ProponentApplicationService::getProposalList - statusList " + statusList);
        List<String> status = Stream.of(statusList.split(",", -1)).collect(Collectors.toList());
        log.info("ProponentApplicationService::getProposalList - status " + status.toString());
        if (page == null || size == null) {
            page = 0;
            size = 10000;

            applications = proponentApplicationRepository.getECProposalList(user_id, status, page, size);
        } else {
            applications = proponentApplicationRepository.getECProposalList(user_id, status, page, size);
        }
        return ResponseHandler.generateResponse("project List Data", HttpStatus.OK, "", applications);
    }

    public ResponseEntity<Object> updateProponentApplicationV3(String proposalNo, String status, String lastRemarks) {
        try {
            ProponentApplications proponentApplication = proponentApplicationRepository
                    .getApplicationByProposalNo(proposalNo);
            Integer update = proponentApplicationRepository.updateProposal(proposalNo, status, lastRemarks);
            if (status.equals("EDS_RAISED")) {
                CommonFormDetail temp = commonFormDetailRepository.getCAF(proponentApplication.getCaf_id());
                temp.setCaf_status(Caf_Status.EDS_RAISED);
                commonFormDetailRepository.save(temp);
            }
            if (update == 0) {
                throw new PariveshException("proposal no not found - " + proposalNo);
            }
            log.info("INFO ------------ updateProponentApplication WITH proposalNo----> " + proposalNo
                    + " ---- UPDATING PROPONENT APPLICATION BY PROPOSAL_NO - SUCCESS");
            return ResponseHandler.generateResponse("Update Proponent Application By Proposal No", HttpStatus.OK, "",
                    update);
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: updateProponentApplication WITH proposalNo----> " + proposalNo
                    + " ---- UPDATING PROPONENT APPLICATION BY PROPOSAL_NO - FAILURE");
            return ResponseHandler.generateResponse("Update Proponent Application By Proposal No",
                    HttpStatus.BAD_REQUEST, "Exception", ex.getMessage());
        }
    }

    public ResponseEntity<Object> getEDSRaisedApplications(Integer id) {
        try {
            List<ProponentApplications> applications = proponentApplicationRepository.getEDSApplications(id).stream()
                    .map(ele -> {
                        if (ele.getUpdated_by() != 1 && ele.getUpdated_by() != null) {
                            ele.setUpdatedByUser(new UpdatedUser(userRepository.findById(ele.getUpdated_by())
                                    .orElseThrow(() -> new UserNotFoundException("user not found with id"))));
                        }
                        if (ele.getProjectDetails() != null) {
                            ele.setProjectDetailDto(new ProjectDetailDto(ele.getProjectDetails()));
                        }
                        return ele;
                    }).collect(Collectors.toList());
            return ResponseHandler.generateResponse("List of Proponent Applications", HttpStatus.OK, "", applications);
        } catch (Exception ex) {
            log.info("ERROR ------------ BAD_REQUEST: getProponentApplication WITH status----> "
                    + " ---- RETRIEVING PROPONENT APPLICATION BY STATUS - FAILURE");
            return ResponseHandler.generateResponse("List of Proponent Applications", HttpStatus.BAD_REQUEST,
                    "Exception", ex.getMessage());
        }
    }

    public ResponseEntity<Object> getApplicationDivision(Integer application_id) {

        List<ApplicationDivisionDto> dto = proponentApplicationRepository.getApplicationDivision(application_id);

        return ResponseHandler.generateResponse("List of Application Division", HttpStatus.OK, "", dto);
    }

    public ResponseEntity<Object> getStage1CompletedProposal(UserPrincipal userPrincipal) {
        try {
            List<Stage1CompletedProposal> response = new ArrayList<Stage1CompletedProposal>();
            response = proponentApplicationRepository.getProposalList(userPrincipal.getId());
            return ResponseHandler.generateResponse("getting stage 1 completed proposal list", HttpStatus.OK, "",
                    response);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("error in getting proposal list", e);
        }
    }

    public ResponseEntity<Object> addCommittee(AddCommittee committee) {

        String encryptedPassword = passEncoder.encode("Test@1234");
//            System.out.println("password:"+encryptedPassword);
        String response = proponentApplicationRepository.addCommittee(committee.getAddress(),
                committee.getDistrictCode(), committee.getEmailId(), committee.getMobileNumber(), committee.getName(),
                encryptedPassword, committee.getPin(), committee.getUserType(), committee.getSecurityAnswer(),
                committee.getSecurityQuestion(), committee.getSelectedSector(), committee.getSelectedOffice(),
                committee.getSelectedRole(), committee.getStateId(), committee.getIpAddress(),
                committee.getDesignationId(), committee.getOfficeId(), committee.getCommitteeType(),
                committee.getSectorId(), committee.getWorkgroupId(), committee.getId());

        if (response.startsWith("Exception")) {
            return ResponseHandler.generateResponse("error in adding committee", HttpStatus.BAD_REQUEST, "", response);
        } else {
            return ResponseHandler.generateResponse("adding committee", HttpStatus.OK, "", response);
        }
    }

    public ResponseEntity<Object> getCommitteeList(String committeeType, Integer id) {
        try {
            List<CommitteeListDto> response = proponentApplicationRepository.getCommitteeList(committeeType, id);
            return ResponseHandler.generateResponse("getting committee list", HttpStatus.OK, "", response);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("error in getting list", e);
        }
    }

    public ResponseEntity<Object> deleteCommittee(Integer id) {

        String response = proponentApplicationRepository.deleteCommittee(id);
        if (response.startsWith("Exception")) {
            return ResponseHandler.generateResponse("error in deleting committee", HttpStatus.BAD_REQUEST, "",
                    response);
        } else {
            return ResponseHandler.generateResponse("deleting committee", HttpStatus.OK, "", response);
        }
    }

    public ResponseEntity<Object> updateSelectedSector(UserPrincipal userPrincipal, Integer selectedSector) {
        try {
//            log.info("entity Id:"+userPrincipal.getId());
            User response = userRepository.findById(userPrincipal.getId()).orElseThrow(
                    () -> new ProjectNotFoundException("user data not found for id:" + userPrincipal.getId()));
            response.setSelected_sector(selectedSector);
            userRepository.save(response);
            return ResponseHandler.generateResponse("selected sector updated for user:" + userPrincipal.getId(),
                    HttpStatus.OK, "", null);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("error in updating selected sector", e);
        }
    }

    public ResponseEntity<Object> copyProposalFcFormA(Integer proposalId) throws PariveshException {
        try {
            Integer fc_fromA_id_new = proponentApplicationRepository.getProponentAppIdFcFormA(proposalId);
            String moefcc_file_number = proponentApplicationRepository.getMoefccFileNo(proposalId);
            if (moefcc_file_number == null) {
                moefcc_file_number = "";
            }
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(fc_fromA_id_new);
            ProponentApplications applications = null;
            Integer clearance_id = proponentApplications.getClearance_id();

            if (clearance_id == 1) {
                ForestClearance temp2 = null;
                ForestClearance fcFormA = forestClearanceRepository.findById(fc_fromA_id_new).orElseThrow();
                CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(proponentApplications.getCaf_id())
                        .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
                String proposal_no = null;
                Integer proposal_sequence = 0;
                Integer maxCount = proponentApplicationRepository.getMaxProposalSequence();
                if (maxCount != null) {
                    proponentApplications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    proposal_no = generateProposalNoFcFormA(maxCount, fcFormA);
                    proponentApplications.setProposal_no(proposal_no);
                    fcFormA.setProposal_no(proposal_no);
                    Integer fcFormAUpdate = forestClearanceRepository.updateFcFormAProposal(fc_fromA_id_new,
                            proposal_no);
                }

                temp2 = forestClearanceRepository.save(fcFormA);
                Applications app = applicationsRepository.findById(223).get();
                proponentApplications.setMoefccFileNumber(moefcc_file_number);
                proponentApplications.setProposal_id(fc_fromA_id_new);
                proponentApplications.setApplications(app);
                proponentApplications.setClearance_id(223);
                proponentApplications.setLast_status(Caf_Status.DRAFT.toString());

                Integer fcFormAUpdate = forestClearanceRepository.updateFcFormAProposal(fc_fromA_id_new, proposal_no);
                Integer maxCountCaf = proponentApplicationRepository.getMaxCafSequence();
                if (maxCountCaf != null) {
                    Integer id = proponentApplications.getCaf_id();
                    Integer Caf_id_sequence = Integer.valueOf(generateSequenceNumber(maxCountCaf));
                    String Caf_id = generateCafId(maxCountCaf);
                    String Proposal_for = Form_for.Amendment.toString();
                    Integer Caf_status = Caf_Status.DRAFT.ordinal();

                    commonFormDetailRepository.updateCAF(Caf_id_sequence, Caf_id, Proposal_for, Caf_status, id);
                }

                applications = proponentApplicationRepository.save(proponentApplications);

                ForestClearance forestClearance = forestClearanceRepository.findById(fcFormA.getId()).orElseThrow();
                moefcc_file_number = proponentApplicationRepository.getMoefccFileNo(fcFormA.getId());
                if (moefcc_file_number == null) {
                    moefcc_file_number = "";
                }
                ProponentApplications proponentApplications2 = proponentApplicationRepository
                        .getApplicationByProposalId(forestClearance.getId());
                ProponentApplications applications2 = null;
                Integer clearance_id2 = proponentApplications2.getClearance_id();
                if (clearance_id2 == 1) {
                    maxCount = proponentApplicationRepository.getMaxProposalSequence();
                    if (maxCount != null) {
                        proposal_sequence = Integer.valueOf(generateSequenceNumber(maxCount));
                        proposal_no = generateProposalNoFcFormA(maxCount, forestClearance);
                    }
                    app = applicationsRepository.findById(223).get();
                    proponentApplications2.setMoefccFileNumber(moefcc_file_number);
                    proponentApplications2.setProposal_sequence(proposal_sequence);
                    proponentApplications2.setProposal_id(fcFormA.getId());
                    proponentApplications2.setApplications(app);
                    proponentApplications2.setClearance_id(223);
                    proponentApplications2.setProposal_no(proposal_no);
                    proponentApplications2.setLast_status(Caf_Status.DRAFT.toString());
                    applications2 = proponentApplicationRepository.save(proponentApplications2);
                }

                ForestClearance fc = forestClearanceRepository.findById(proposalId).orElse(null);
                HashMap<String, Object> OtherPropString = new HashMap<String, Object>();

                if (fc != null) {
                    log.info("setting other property for FcFormA amendment");
                    OtherPropString.put("Form", app.getDd_name());

                    String activity = fc.getProject_activity_id();
                    if (activity.equalsIgnoreCase("Others")) {
                        String fullActivityName = activity + "(" + fc.getProject_activity_id_other() + ")";
                        OtherPropString.put("Project Category", fullActivityName);
                    } else {
                        OtherPropString.put("Project Category", proponentApplicationRepository
                                .getProjectCategoryName(Integer.parseInt(fc.getProject_category_id())));
                    }
                    OtherPropString.put("Forest Area",
                            fc.getForestProposedLand().getTotal_proposed_diversion_area().toString());

                    updateOtherPropertyService.updateOtherProperty(fc_fromA_id_new, OtherPropString);
                }
            }

            return ResponseHandler.generateResponse("Proponent Applications----> ", HttpStatus.OK, "null",
                    applications);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in copy proposal FcFromA for proposalId- " + proposalId, e);
        }
    }

    private String generateProposalNoFcFormA(int maxcount, ForestClearance fcFormA) {

        String cafId = "FP/" + stateRepository.getStateByCode(fcFormA.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/" + fcFormA.getProject_activity_id() + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());

        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }

    public ResponseEntity<Object> getFcCertificateConditions(Integer applicationId, String type) {
        try {
            FcCertificateConditionDto response = proponentApplicationRepository.getConditions(applicationId, type);

            return ResponseHandler.generateResponse("getting conditions for certificate with category:"
                            + response.getProject_category() + " and state:" + response.getState_id(), HttpStatus.OK, "",
                    response);

        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("error in getting certificate", e);
        }
    }

    public ResponseEntity<Object> copyProposalFcFormB(Integer proposalId) throws PariveshException {
        try {
            Integer fc_fromB_id_new = proponentApplicationRepository.getProponentAppIdFcFormB(proposalId);
            String moefcc_file_number = proponentApplicationRepository.getMoefccFileNo(proposalId);
            if (moefcc_file_number == null) {
                moefcc_file_number = "";
            }
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(fc_fromB_id_new);
            ProponentApplications applications = null;
            Integer clearance_id = proponentApplications.getClearance_id();

            if (clearance_id == 8) {
                FcFormBProjectDetails temp2 = null;
                FcFormBProjectDetails fcFormB = fcFormBProjectDetailsRepository.findById(fc_fromB_id_new).orElseThrow();
                CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(proponentApplications.getCaf_id())
                        .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
                String proposal_no = null;
                Integer proposal_sequence = 0;
                Integer maxCount = proponentApplicationRepository.getMaxProposalSequence();
                if (maxCount != null) {
                    proponentApplications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    proposal_no = generateProposalNoFcFormB(maxCount, fcFormB);
                    proponentApplications.setProposal_no(proposal_no);
                    fcFormB.setProposal_no(proposal_no);
                    Integer fcFormBUpdate = fcFormBProjectDetailsRepository.updateFcFormAProposal(fc_fromB_id_new,
                            proposal_no);
                }

                temp2 = fcFormBProjectDetailsRepository.save(fcFormB);
                Applications app = applicationsRepository.findById(227).get();
                proponentApplications.setMoefccFileNumber(moefcc_file_number);
                proponentApplications.setProposal_id(fc_fromB_id_new);
                proponentApplications.setApplications(app);
                proponentApplications.setClearance_id(227);
                proponentApplications.setLast_status(Caf_Status.DRAFT.toString());

                Integer fcFormBUpdate = fcFormBProjectDetailsRepository.updateFcFormAProposal(fc_fromB_id_new,
                        proposal_no);
                Integer maxCountCaf = proponentApplicationRepository.getMaxCafSequence();
                if (maxCountCaf != null) {
                    Integer id = proponentApplications.getCaf_id();
                    Integer Caf_id_sequence = Integer.valueOf(generateSequenceNumber(maxCountCaf));
                    String Caf_id = generateCafId(maxCountCaf);
                    String Proposal_for = Form_for.Amendment.toString();
                    Integer Caf_status = Caf_Status.DRAFT.ordinal();

                    commonFormDetailRepository.updateCAF(Caf_id_sequence, Caf_id, Proposal_for, Caf_status, id);
                }

                applications = proponentApplicationRepository.save(proponentApplications);

                FcFormBProjectDetails fcFormBProjectDetails = fcFormBProjectDetailsRepository.findById(fcFormB.getId())
                        .orElseThrow();
                moefcc_file_number = proponentApplicationRepository.getMoefccFileNo(fcFormB.getId());
                if (moefcc_file_number == null) {
                    moefcc_file_number = "";
                }
                ProponentApplications proponentApplications2 = proponentApplicationRepository
                        .getApplicationByProposalId(fcFormBProjectDetails.getId());
                ProponentApplications applications2 = null;
                Integer clearance_id2 = proponentApplications2.getClearance_id();
                if (clearance_id2 == 8) {
                    maxCount = proponentApplicationRepository.getMaxProposalSequence();
                    if (maxCount != null) {
                        proposal_sequence = Integer.valueOf(generateSequenceNumber(maxCount));
                        proposal_no = generateProposalNoFcFormB(maxCount, fcFormBProjectDetails);
                    }
                    app = applicationsRepository.findById(227).get();
                    proponentApplications2.setMoefccFileNumber(moefcc_file_number);
                    proponentApplications2.setProposal_sequence(proposal_sequence);
                    proponentApplications2.setProposal_id(fcFormB.getId());
                    proponentApplications2.setApplications(app);
                    proponentApplications2.setClearance_id(227);
                    proponentApplications2.setProposal_no(proposal_no);
                    proponentApplications2.setLast_status(Caf_Status.DRAFT.toString());
                    applications2 = proponentApplicationRepository.save(proponentApplications2);
                }

                FcFormBProjectDetails fc = fcFormBProjectDetailsRepository.findById(proposalId).orElse(null);
                HashMap<String, Object> OtherPropString = new HashMap<String, Object>();

                if (fc != null) {
                    log.info("setting other property for FcFormB amendment");
                    OtherPropString.put("Form", app.getDd_name());
                    OtherPropString.put("Project Category", fc.getProject_category_code());
                    OtherPropString.put("Forest Area",
                            fc.getFormBProposedLands().getTotal_proposed_diversion_area().toString());

                    updateOtherPropertyService.updateOtherProperty(fc_fromB_id_new, OtherPropString);
                }
            }

            return ResponseHandler.generateResponse("Proponent Applications----> ", HttpStatus.OK, "null",
                    applications);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in copy proposal FcFromB for proposalId- " + proposalId, e);
        }
    }

    private String generateProposalNoFcFormB(int maxcount, FcFormBProjectDetails fcFormB) {

        String cafId = "FP/" + stateRepository.getStateByCode(fcFormB.getState_code())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/" + fcFormB.getProject_category_code() + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());

        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }

    public ResponseEntity<Object> getProjectProposalDetails(Integer applicationId) {
        try {
            List<ProjectProposalDetailsDto> response = proponentApplicationRepository.getProjectProposalDetails(applicationId);

            return ResponseHandler.generateResponse("getting proposals of project", HttpStatus.OK, "", response);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("error in getting proposals for project", e);
        }
    }

    public ResponseEntity<Object> copyProposalFcFormC(Integer proposalId) throws PariveshException {
        ProponentApplications applications = null;
        HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
        try {
            Integer fcFormCIdNew = proponentApplicationRepository.getProponentAppIdFcFormC(proposalId);
            String moefccFileNumber = proponentApplicationRepository.getMoefccFileNo(proposalId);
            if (moefccFileNumber == null) {
                moefccFileNumber = "";
            }

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(fcFormCIdNew);
            Integer clearance_id = proponentApplications.getClearance_id();

            if (clearance_id == 7) {
                FcFormC temp2 = null;
                FcFormC fcFormC = fcFormCRepository.findById(fcFormCIdNew).orElseThrow();
                CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(proponentApplications.getCaf_id())
                        .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
                String proposal_no = null;
                Integer proposal_sequence = 0;
                Integer maxCount = proponentApplicationRepository.getMaxProposalSequence();
                if (maxCount != null) {
                    proponentApplications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    proposal_no = generateProposalNoFcFormC(maxCount, fcFormC);
                    proponentApplications.setProposal_no(proposal_no);
                    fcFormC.setProposal_no(proposal_no);
                    Integer fcFormCUpdate = fcFormCRepository.updateFcFormCProposal(fcFormCIdNew, proposal_no);
                }

                temp2 = fcFormCRepository.save(fcFormC);
                Applications app = applicationsRepository.findById(231).get();
                proponentApplications.setMoefccFileNumber(moefccFileNumber);
                proponentApplications.setProposal_id(fcFormCIdNew);
                proponentApplications.setApplications(app);
                proponentApplications.setClearance_id(231);
                proponentApplications.setLast_status(Caf_Status.DRAFT.toString());

                Integer fcFormCUpdate = fcFormCRepository.updateFcFormCProposal(fcFormCIdNew, proposal_no);
                Integer maxCountCaf = proponentApplicationRepository.getMaxCafSequence();
                if (maxCountCaf != null) {
                    Integer id = proponentApplications.getCaf_id();
                    Integer Caf_id_sequence = Integer.valueOf(generateSequenceNumber(maxCountCaf));
                    String Caf_id = generateCafId(maxCountCaf);
                    String Proposal_for = Form_for.Amendment.toString();
                    Integer Caf_status = Caf_Status.DRAFT.ordinal();

                    commonFormDetailRepository.updateCAF(Caf_id_sequence, Caf_id, Proposal_for, Caf_status, id);
                }
                applications = proponentApplicationRepository.save(proponentApplications);

                FcFormC fc = fcFormCRepository.findById(fcFormCIdNew).orElse(null);

                if (fc != null) {
                    log.info("setting other property for FcFormB amendment");
                    OtherPropString.put("Exploration Area", fc.getForest_proposed_exploration_area());
                    OtherPropString.put("Project Category", "Exploration & Survey");
                    OtherPropString.put("Form", app.getDd_name());

                    updateOtherPropertyService.updateOtherProperty(fc.getId(), OtherPropString);
                }
            }

            return ResponseHandler.generateResponse("Proponent Applications----> ", HttpStatus.OK, "null",
                    applications);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("Error in copy proposal FcFromC for proposalId- " + proposalId, e);
        }
    }

    private String generateProposalNoFcFormC(int maxcount, FcFormC fcFormC) {

        String cafId = "FP/" + stateRepository.getStateByCode(fcFormC.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/SRY" + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }

    public ResponseEntity<Object> copyProposalFcFormD(Integer proposalId) throws PariveshException {
        ProponentApplications applications = null;
        HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
        try {
            Integer fcFormDIdNew = proponentApplicationRepository.getProponentAppIdFcFormD(proposalId);
            String moefccFileNumber = proponentApplicationRepository.getMoefccFileNo(proposalId);
            if (moefccFileNumber == null) {
                moefccFileNumber = "";
            }

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(fcFormDIdNew);
            Integer clearance_id = proponentApplications.getClearance_id();

            if (clearance_id == 9) {
                FcFormD temp2 = null;
                FcFormD fcFormD = fcFormDRepository.findById(fcFormDIdNew).orElseThrow();
                CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(proponentApplications.getCaf_id())
                        .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
                String proposal_no = null;
                Integer proposal_sequence = 0;
                Integer maxCount = proponentApplicationRepository.getMaxProposalSequence();
                if (maxCount != null) {
                    proponentApplications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    proposal_no = generateProposalNoFcFormD(maxCount, fcFormD);
                    proponentApplications.setProposal_no(proposal_no);
                    fcFormD.setProposal_no(proposal_no);
                    Integer fcFormDUpdate = fcFormDRepository.updateFcFormDProposal(fcFormDIdNew, proposal_no);
                }

                temp2 = fcFormDRepository.save(fcFormD);
                Applications app = applicationsRepository.findById(232).get();
                proponentApplications.setMoefccFileNumber(moefccFileNumber);
                proponentApplications.setProposal_id(fcFormDIdNew);
                proponentApplications.setApplications(app);
                proponentApplications.setClearance_id(232);
                proponentApplications.setLast_status(Caf_Status.DRAFT.toString());

                Integer fcFormDUpdate = fcFormDRepository.updateFcFormDProposal(fcFormDIdNew, proposal_no);
                Integer maxCountCaf = proponentApplicationRepository.getMaxCafSequence();
                if (maxCountCaf != null) {
                    Integer id = proponentApplications.getCaf_id();
                    Integer Caf_id_sequence = Integer.valueOf(generateSequenceNumber(maxCountCaf));
                    String Caf_id = generateCafId(maxCountCaf);
                    String Proposal_for = Form_for.Amendment.toString();
                    Integer Caf_status = Caf_Status.DRAFT.ordinal();

                    commonFormDetailRepository.updateCAF(Caf_id_sequence, Caf_id, Proposal_for, Caf_status, id);
                }
                applications = proponentApplicationRepository.save(proponentApplications);

                FcFormD fc = fcFormDRepository.findById(fcFormDIdNew).orElse(null);

                if (fc != null) {
                    log.info("setting other property for FcFormB amendment");
                    OtherPropString.put("Form", app.getDd_name());
                    if (fc.getProject_category() == "MIND") {
                        OtherPropString.put("Project Category", "Mining");
                    } else {
                        OtherPropString.put("Project Category", "Non-Mining");
                    }
                    OtherPropString.put("Forest Area", fc.getFcFormDProposedLand().getTotal_proposed_diversion_area().toString());

                    updateOtherPropertyService.updateOtherProperty(fc.getId(), OtherPropString);
                }
            }

            return ResponseHandler.generateResponse("copy proposal for FcFormD success", HttpStatus.OK, "null",
                    applications);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("Error in copy proposal FcFromD for proposalId- " + proposalId, e);
        }
    }

    private String generateProposalNoFcFormD(int maxcount, FcFormD fcFormD) {

        String proposalNo = "FP/" + stateRepository.getStateByCode(fcFormD.getState_code())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/" + fcFormD.getProject_category() + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());
        proposalNo = proposalNo.replaceAll("\\s", "");
        return proposalNo;
    }

    public ResponseEntity<Object> copyProposalFcFormE(Integer proposalId) throws PariveshException {
        ProponentApplications applications = null;
        HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
        try {
            Integer fcFormEIdNew = proponentApplicationRepository.getProponentAppIdFcFormE(proposalId);
            String moefccFileNumber = proponentApplicationRepository.getMoefccFileNo(proposalId);
            if (moefccFileNumber == null) {
                moefccFileNumber = "";
            }

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(fcFormEIdNew);
            Integer clearance_id = proponentApplications.getClearance_id();

            if (clearance_id == 12) {
                FcFormE temp2 = null;
                FcFormE fcFormE = fcFormERepository.findById(fcFormEIdNew).orElseThrow();
                CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(proponentApplications.getCaf_id())
                        .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
                String proposal_no = null;
                Integer proposal_sequence = 0;
                Integer maxCount = proponentApplicationRepository.getMaxProposalSequence();
                if (maxCount != null) {
                    proponentApplications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    proposal_no = generateProposalNoFcFormE(maxCount, fcFormE);
                    proponentApplications.setProposal_no(proposal_no);
                    fcFormE.setProposal_no(proposal_no);
                    Integer fcFormEUpdate = fcFormERepository.updateFcFormEProposal(fcFormEIdNew, proposal_no);
                }

                temp2 = fcFormERepository.save(fcFormE);
                Applications app = applicationsRepository.findById(233).get();
                proponentApplications.setMoefccFileNumber(moefccFileNumber);
                proponentApplications.setProposal_id(fcFormEIdNew);
                proponentApplications.setApplications(app);
                proponentApplications.setClearance_id(233);
                proponentApplications.setLast_status(Caf_Status.DRAFT.toString());

                Integer fcFormEUpdate = fcFormERepository.updateFcFormEProposal(fcFormEIdNew, proposal_no);
                Integer maxCountCaf = proponentApplicationRepository.getMaxCafSequence();
                if (maxCountCaf != null) {
                    Integer id = proponentApplications.getCaf_id();
                    Integer Caf_id_sequence = Integer.valueOf(generateSequenceNumber(maxCountCaf));
                    String Caf_id = generateCafId(maxCountCaf);
                    String Proposal_for = Form_for.Amendment.toString();
                    Integer Caf_status = Caf_Status.DRAFT.ordinal();

                    commonFormDetailRepository.updateCAF(Caf_id_sequence, Caf_id, Proposal_for, Caf_status, id);
                }
                applications = proponentApplicationRepository.save(proponentApplications);

                FcFormE fc = fcFormERepository.findById(fcFormEIdNew).orElse(null);

                if (fc != null) {
                    log.info("setting other property for FcFormB amendment");
                    OtherPropString.put("Form", app.getDd_name());
                    OtherPropString.put("Project Category", "Re-Diversion");
                    OtherPropString.put("Forest Area", fc.getFcFormEProposedLand().getTotal_forest_land_proposed().toString());

                    updateOtherPropertyService.updateOtherProperty(fc.getId(), OtherPropString);
                }
            }

            return ResponseHandler.generateResponse("copy proposal for FcFormE success", HttpStatus.OK, "null",
                    applications);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("Error in copy proposal FcFromE for proposalId- " + proposalId, e);
        }
    }

    private String generateProposalNoFcFormE(int maxcount, FcFormE fcFormE) {

        String proposalNo = "FP/" + stateRepository.getStateByCode(fcFormE.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/REDIV" + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
        proposalNo = proposalNo.replaceAll("\\s", "");
        return proposalNo;
    }
}

