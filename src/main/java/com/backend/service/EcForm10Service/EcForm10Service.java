package com.backend.service.EcForm10Service;

import com.backend.constant.AppConstant;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.EcForm10.EcForm10dto;
import com.backend.dto.GetDataDto;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm10NoIncreaseInPL.*;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm10Repository.*;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EcForm10Service {

    @Autowired
    EcForm10LegalDetailsRepository ecForm10LegalDetailsRepository;

    @Autowired
    private EcForm10AuthorizationHazardousDetailsRepository ecForm10AuthorizationHazardousDetailsRepository;

    @Autowired
    private EcForm10ConsentUnderAirDetailsRepository ecForm10ConsentUnderAirDetailsRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;
    @Autowired
    EcForm10EmissionGenerationRepository ecForm10EmissionGenerationRepository;
    @Autowired
    EcForm10UndertakingRepository ecForm10UndertakingRepository;
    @Autowired
    ApplicationsRepository applicationsRepository;
    @Autowired
    EcForm10ProductDetailRepository ecForm10ProductDetailRepository;
    @Autowired
    EcForm10BasicInformationRepository ecForm10BasicInformationRepository;
    @Autowired
    EcForm10LocationOfProjectRepository ecForm10LocationOfProjectRepository;
    @Autowired
    EcForm10ProjectDetailRepository ecForm10ProjectDetailRepository;
    @Autowired
    EcForm10AdditionalDocumentRepository ecForm10AdditionalDocumentRepository;
    @Autowired
    EcForm10HazardousWasteGenerationRepository ecForm10HazardousWasteGenerationRepository;
    @Autowired
    EcForm10ProjectActivityDetailsRepository ecForm10ProjectActivityDetailsRepository;
    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private EcForm10LitigationAndViolationDetailsRepository ecForm10LitigationAndViolationDetailsRepository;
    @Autowired
    private CommonFormDetailService commonFormDetailService;
    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private SubActivityRepository subActivityRepository;

    @Autowired
    private ServerUtil util;

    @Autowired
    private NotificationService notificationSevice;

    public Object saveEcForm10ProjectDetails(EcForm10ProjectDetails ecForm10ProjectDetails, Integer caf_id,
                                             Integer ec_id, HttpServletRequest request) throws PariveshException {
        try {
            // EnvironmentClearence ecClearence =
            // environmentClearenceRepository.findByFormId(ec_id).orElse(null);
            CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
            ecForm10ProjectDetails.setCommonFormDetail(commonForm);
            // ecForm10ProjectDetails.setEc_id(ec_id);
            if (ecForm10ProjectDetails.getId() != null && ecForm10ProjectDetails.getId() != 0) {
                EcForm10ProjectDetails form = ecForm10ProjectDetailRepository
                        .getFormById(ecForm10ProjectDetails.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
                ecForm10ProjectDetails.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                return ecForm10ProjectDetailRepository.save(ecForm10ProjectDetails);
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            ProponentApplications applications = new ProponentApplications();
            generateProposalNo(tempClearances, commonForm, applications, "NA", ecForm10ProjectDetails);
            // String sectorName =
            // ecForm10ProjectDetails.getEcForm10BasicInformation().getSector();
            EcForm10ProjectDetails temp2 = ecForm10ProjectDetailRepository.save(ecForm10ProjectDetails);
            Applications app = applicationsRepository.findById(62).get(); // 62
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

            applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(commonForm.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(AppConstant.Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

            proponentApplicationRepository.save(applications);
            return temp2;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
    }

    public Object saveEcForm10LocationOfProject(EcForm10LocationOfProject ecForm10LocationOfProject, Integer ecForm10Id,
                                                HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm10LocationOfProject == null) {
                throw new ProjectNotFoundException("EC Form 10 Location Of Project is NULL");
            } else {
                EcForm10ProjectDetails ecForm10NoIncreaseInPL = ecForm10ProjectDetailRepository
                        .getEcForm10NoIncreaseInPlByid(ecForm10Id)
                        .orElseThrow(() -> new ProjectNotFoundException("Ec form 10 transfer of EC not found"));
                ecForm10LocationOfProject.setEcForm10ProjectDetails(ecForm10NoIncreaseInPL);

            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
        return ecForm10LocationOfProjectRepository.save(ecForm10LocationOfProject);
    }

    public Object saveEcForm10LitigationAndViolationDetails(
            EcForm10LitigationAndViolationDetails ecForm10LitigationAndViolationDetails, Integer ecForm10Id,
            HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm10LitigationAndViolationDetails == null) {
                throw new ProjectNotFoundException("EC Form 10 Legal Details is NULL");
            } else {
                EcForm10ProjectDetails ecForm10NoIncreaseInPL = ecForm10ProjectDetailRepository
                        .getEcForm10NoIncreaseInPlByid(ecForm10Id)
                        .orElseThrow(() -> new ProjectNotFoundException("Ec form 10 transfer of EC not found"));
                ecForm10LitigationAndViolationDetails.setEcForm10ProjectDetails(ecForm10NoIncreaseInPL);

            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
        return ecForm10LitigationAndViolationDetailsRepository.save(ecForm10LitigationAndViolationDetails);
    }

    @Transactional
    public Object saveEcForm10BasicInformationB(EcForm10BasicInformation ecForm10BasicInformation, Integer ecForm10Id,
                                                HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm10BasicInformation == null) {
                throw new ProjectNotFoundException("EC Form 10 Basic Information is NULL");
            } else {
                EcForm10ProjectDetails ecForm10ProjectDetails = ecForm10ProjectDetailRepository
                        .getEcForm10NoIncreaseInPlByid(ecForm10Id)
                        .orElseThrow(() -> new ProjectNotFoundException("Ec form 10 transfer of EC not found"));
                // ecForm10BasicInformation.setEcForm10ProjectDetails(ecForm10ProjectDetails);
                // CommonFormDetail common =
                // ecForm10BasicInformation.getEcForm10ProjectDetails().getCommonFormDetail();

                // ecForm10BasicInformation.setEcForm10ProjectDetails(ecForm10ProjectDetails);
                GetDataDto sectorCode = activityRepository
                        .getSectorCodeByName(Integer.parseInt(ecForm10BasicInformation.getIs_eia()))
                        .orElseThrow(() -> new PariveshException(
                                "Won't be able to generate proposal no because sector code does not exist for : "
                                        + ecForm10BasicInformation.getIs_eia()));
                List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
                ProponentApplications applications = proponentApplicationRepository
                        .getApplicationByProposalId(ecForm10Id);
                // generateProposalNo(tempClearances, common, applications,
                // sectorCode.getName(), ecForm10ProjectDetails);
                ecForm10ProjectDetails.setProponentApplications(applications);
                ecForm10BasicInformation.setSector_id(sectorCode.getId());
                ecForm10BasicInformation.setMajor_activity_id(sectorCode.getId1());
                ecForm10ProjectDetailRepository.save(ecForm10ProjectDetails);
                proponentApplicationRepository.save(applications);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }

        return ecForm10BasicInformationRepository.save(ecForm10BasicInformation);
    }

    @Transactional
    public Object saveEcForm10BasicInformation(EcForm10BasicInformation ecForm10BasicInformation, Integer caf_id,
                                               HttpServletRequest request) throws PariveshException {
        try {

            CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
            ecForm10BasicInformation.setCommonFormDetail(commonForm);

            if (ecForm10BasicInformation.getId() != null && ecForm10BasicInformation.getId() != 0) {

                EcForm10BasicInformation form = ecForm10BasicInformationRepository
                        .getFormById(ecForm10BasicInformation.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
                ecForm10BasicInformation.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                return ecForm10BasicInformationRepository.save(ecForm10BasicInformation);
            }
//            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
            Integer maxSequence = proponentApplicationRepository.getMaxProposalSequence();
//            String sectorName = activitySectorRepository
//                    .getSectorByActivityID(ecForm10BasicInformation.getActivities_id());

            String sectorName = activitySectorRepository.getSector(ecForm10BasicInformation.getMajor_activity_id(),
                    ecForm10BasicInformation.getMajor_sub_activity_id()).getSector_code();
            ecForm10BasicInformation.setSector(sectorName);

            ProponentApplications applications = new ProponentApplications();
            generateProposalNo(maxSequence, commonForm, applications, sectorName, ecForm10BasicInformation);

            EcForm10BasicInformation temp2 = ecForm10BasicInformationRepository.save(ecForm10BasicInformation);
            Applications app = applicationsRepository.findById(62).get();
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

//            GetDataDto sectorCode = activityRepository.getSectorCodeByName(Integer.parseInt(ecForm10BasicInformation.getIs_eia())).orElseThrow(() -> new PariveshException("Won't be able to generate proposal no because sector code does not exist for : " + ecForm10BasicInformation.getIs_eia()));
            Integer sectorId = activitySectorRepository.getSectorID(ecForm10BasicInformation.getMajor_activity_id());

            applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(commonForm.getProjectDetails());
            ecForm10BasicInformation.setSector_id(sectorId);
            ecForm10BasicInformation.setMajor_activity_id(ecForm10BasicInformation.getMajor_activity_id());
            applications.setApplications(app);
            applications.setLast_status(AppConstant.Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

            proponentApplicationRepository.save(applications);
            return temp2;

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EcForm10 saveEcForm10BasicInformation form caf_id-" + caf_id,
                    e);
        }
    }

    public Object saveEcForm10ProductDetails(EcForm10ProductDetails ecForm10ProductDetails, Integer ecForm10Id,
                                             HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm10ProductDetails == null) {
                throw new ProjectNotFoundException("EC Form 10 Basic Information is NULL");
            } else {
                /*
                 * EcForm10ProjectDetails ecForm10ProjectDetails =
                 * ecForm10ProjectDetailRepository .getEcForm10NoIncreaseInPlByid(ecForm10Id)
                 * .orElseThrow(() -> new
                 * ProjectNotFoundException("Ec form 10 transfer of EC not found"));
                 */
                EcForm10BasicInformation ecForm10BasicInformation = ecForm10BasicInformationRepository
                        .findById(ecForm10Id)
                        .orElseThrow(() -> new ProjectNotFoundException("EcForm10 Basic Information not found"));
                ecForm10ProductDetails.setEcForm10BasicInformation(ecForm10BasicInformation);
                // ecForm10ProductDetails.setEcForm10ProjectDetails(ecForm10ProjectDetails);

            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
        return ecForm10ProductDetailRepository.save(ecForm10ProductDetails);
    }

    public Object saveEcForm10EmissionGeneration(EcForm10EmissionGeneration ecForm10EmissionGeneration,
                                                 Integer ecForm10Id, HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm10EmissionGeneration == null) {
                throw new ProjectNotFoundException("EC Form 10 Basic Information is NULL");
            } else {
                /*
                 * EcForm10ProjectDetails ecForm10ProjectDetails =
                 * ecForm10ProjectDetailRepository .getEcForm10NoIncreaseInPlByid(ecForm10Id)
                 * .orElseThrow(() -> new
                 * ProjectNotFoundException("Ec form 10 transfer of EC not found"));
                 */
                EcForm10BasicInformation ecForm10BasicInformation = ecForm10BasicInformationRepository
                        .findById(ecForm10Id)
                        .orElseThrow(() -> new ProjectNotFoundException("EcForm10 Basic Information not found"));
                ecForm10EmissionGeneration.setEcForm10BasicInformation(ecForm10BasicInformation);

            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
        return ecForm10EmissionGenerationRepository.save(ecForm10EmissionGeneration);
    }

    public Object saveEcForm10HazardousWasteGeneration(
            EcForm10HazardousWasteGeneration ecForm10HazardousWasteGeneration, Integer ecForm10Id,
            HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm10HazardousWasteGeneration == null) {
                throw new ProjectNotFoundException("EC Form 10 Basic Information is NULL");
            } else {
                /*
                 * EcForm10ProjectDetails ecForm10ProjectDetails =
                 * ecForm10ProjectDetailRepository .getEcForm10NoIncreaseInPlByid(ecForm10Id)
                 * .orElseThrow(() -> new
                 * ProjectNotFoundException("Ec form 10 transfer of EC not found"));
                 */
                EcForm10BasicInformation ecForm10BasicInformation = ecForm10BasicInformationRepository
                        .findById(ecForm10Id)
                        .orElseThrow(() -> new ProjectNotFoundException("EcForm10 Basic Information not found"));
                ecForm10HazardousWasteGeneration.setEcForm10BasicInformation(ecForm10BasicInformation);

            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
        return ecForm10HazardousWasteGenerationRepository.save(ecForm10HazardousWasteGeneration);
    }

    public Object saveEcForm10AdditionalDocument(EcForm10AdditionalDocument ecForm10AdditionalDocument,
                                                 Integer ecForm10Id, HttpServletRequest request) {
        try {
            if (ecForm10AdditionalDocument == null) {
                throw new ProjectNotFoundException("EC Form 10 Document Attached is NULL");
            } else {
                /*
                 * EcForm10ProjectDetails ecForm10ProjectDetails =
                 * ecForm10ProjectDetailRepository .getEcForm10NoIncreaseInPlByid(ecForm10Id)
                 * .orElseThrow(() -> new
                 * ProjectNotFoundException("Ec form 10 Details not found"));
                 */
                EcForm10BasicInformation ecForm10BasicInformation = ecForm10BasicInformationRepository
                        .findById(ecForm10Id)
                        .orElseThrow(() -> new ProjectNotFoundException("EcForm10 Basic Information not found"));
                ecForm10AdditionalDocument.setEcForm10BasicInformation(ecForm10BasicInformation);

            }

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
        return ecForm10AdditionalDocumentRepository.save(ecForm10AdditionalDocument);
    }

    public Object saveEcForm10Undertaking(EcForm10Undertaking ecForm10Undertaking, Integer ecForm10Id, Integer caf_id,
                                          Boolean is_submit, HttpServletRequest request) throws PariveshException {
        /*
         * EcForm10ProjectDetails ecForm10ProjectDetails =
         * ecForm10ProjectDetailRepository .getEcForm10NoIncreaseInPlByid(ecForm10Id)
         * .orElseThrow(() -> new
         * ProjectNotFoundException("Ec form 10 transfer of EC not found"));
         */
        EcForm10BasicInformation ecForm10BasicInformation = ecForm10BasicInformationRepository.findById(ecForm10Id)
                .orElseThrow(() -> new ProjectNotFoundException("EcForm10 Basic Information not found"));
        // EnvironmentClearence ecClearence =
        // environmentClearenceRepository.findByFormId(ecForm10ProjectDetails.getEc_id()).orElse(null);

        String proposalNo = proponentApplicationRepository.getProposalNo(ecForm10Id);
        String identificationNo = ecForm10UndertakingRepository.getIdentificationNo(proposalNo);
        ecForm10Undertaking.setIdentification_no(identificationNo);
        log.info(identificationNo);

        // ecForm10Undertaking.setEcForm10ProjectDetails(ecForm10ProjectDetails);
        // ProponentApplications proponentApplications =
        // proponentApplicationRepository.getApplicationByProposalNo(ecForm10ProjectDetails.getProposal_no());

        ecForm10Undertaking.setEcForm10BasicInformation(ecForm10BasicInformation);
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getApplicationByProposalNo(ecForm10BasicInformation.getProposal_no());
        /*
        if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
            if (ecForm10Undertaking.getSubmission_date() != null)
                proponentApplications.setLast_submission_date(ecForm10Undertaking.getSubmission_date());
            else {
                proponentApplications.setLast_submission_date(new Date());
            }
        }

        if (is_submit == true)
            proponentApplications.setLast_status(AppConstant.Caf_Status.SUBMITTED.toString());
		*/

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

        proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
        Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
                .findByCaf(ecForm10BasicInformationRepository.getCafByEcId(ecForm10BasicInformation.getId()));

        proponentApplications.setProposal_no(ecForm10BasicInformation.getProposal_no());

        /*
         *
         * if (ecForm10Undertaking.getSubmission_date() != null)
         * proponentApplications.setLast_submission_date(ecForm10Undertaking.
         * getSubmission_date()); else {
         * proponentApplications.setLast_submission_date(new Date()); }
         */
        commonFormDetailService.saveCommonForm(cafDetail.get());
        proponentApplicationRepository.save(proponentApplications);
        /*if (is_submit == true) {
            notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

            notificationSevice.sendAcknowledgement(proponentApplications.getProposal_no());

        }*/
        return ecForm10UndertakingRepository.save(ecForm10Undertaking);
    }

    public Object getEcForm10(Integer id) {

        /*
         * EcForm10dto ecForm10dto =
         * ecForm10ProjectDetailRepository.getForm10ByIdDet(id) .orElseThrow(() -> new
         * ProjectNotFoundException("Form 10 not found with id  ----> " + id)); Integer
         * caf_id =
         * ecForm10ProjectDetailRepository.getCafIdByFormId(ecForm10dto.getId());
         * CommonFormDetail commonFormDetail =
         * commonFormDetailRepository.getById(caf_id); ProponentApplications
         * proponentApplications = proponentApplicationRepository
         * .getApplicationByProposalId(ecForm10dto.getId()); EcForm10LocationOfProject
         * ecForm10LocationOfProject = ecForm10LocationOfProjectRepository
         * .getByEc10Id(ecForm10dto.getId()); EcForm10LitigationAndViolationDetails
         * ecForm10LitigationAndViolationDetails =
         * ecForm10LitigationAndViolationDetailsRepository
         * .getByEc10Id(ecForm10dto.getId()); EcForm10BasicInformation
         * ecForm10BasicInformation = ecForm10BasicInformationRepository
         * .getByEc10Id(ecForm10dto.getId()); EcForm10ProductDetails
         * ecForm10ProductDetails = ecForm10ProductDetailRepository
         * .getByEc10Id(ecForm10dto.getId()); EcForm10EmissionGeneration
         * ecForm10EmissionGeneration = ecForm10EmissionGenerationRepository
         * .getByEc10Id(ecForm10dto.getId()); EcForm10HazardousWasteGeneration
         * ecForm10HazardousWasteGeneration = ecForm10HazardousWasteGenerationRepository
         * .getByEc10Id(ecForm10dto.getId()); EcForm10AdditionalDocument
         * ecForm10AdditionalDocument = ecForm10AdditionalDocumentRepository
         * .findAllByFormId1(ecForm10dto.getId()); EcForm10Undertaking
         * ecForm10Undertaking =
         * ecForm10UndertakingRepository.getByEc10Id(ecForm10dto.getId());
         *
         * EcForm10ProjectDetails ecForm10ProjectDetails = new EcForm10ProjectDetails();
         *
         * BeanUtils.copyProperties(ecForm10dto, ecForm10ProjectDetails);
         * ecForm10ProjectDetails.setCommonFormDetail(commonFormDetail);
         * ecForm10ProjectDetails.setProponentApplications(proponentApplications);
         * ecForm10ProjectDetails.setEcForm10LocationOfProject(ecForm10LocationOfProject
         * ); ecForm10ProjectDetails.setEcForm10LitigationAndViolationDetails(
         * ecForm10LitigationAndViolationDetails);
         * ecForm10ProjectDetails.setEcForm10BasicInformation(ecForm10BasicInformation);
         * ecForm10ProjectDetails.setEcForm10ProductDetails(ecForm10ProductDetails);
         * ecForm10ProjectDetails.setEcForm10EmissionGeneration(
         * ecForm10EmissionGeneration);
         * ecForm10ProjectDetails.setEcForm10HazardousWasteGeneration(
         * ecForm10HazardousWasteGeneration);
         * ecForm10ProjectDetails.setEcForm10AdditionalDocument(
         * ecForm10AdditionalDocument);
         * ecForm10ProjectDetails.setEcForm10Undertaking(ecForm10Undertaking);
         *
         */

        ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalId(id);
        EcForm10BasicInformation ecForm10BasicInformation = ecForm10BasicInformationRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("EcForm10 Basic Information not found"));
        ecForm10BasicInformation.setProponentApplications(proponentApplications);

        return ResponseHandler.generateResponse("Save Ec Other Details form", HttpStatus.OK, null,
                ecForm10BasicInformation);
    }

    public Object getByEcForm10B(Integer id) {
        EcForm10dto ecForm10dto = ecForm10ProjectDetailRepository.getForm10ByIdDet(id)
                .orElseThrow(() -> new ProjectNotFoundException("Form 10 not found with id  ----> " + id));
        Integer caf_id = ecForm10ProjectDetailRepository.getCafIdByFormId(ecForm10dto.getId());
        CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(caf_id);
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getApplicationByProposalId(ecForm10dto.getId());
        EcForm10LocationOfProject ecForm10LocationOfProject = ecForm10LocationOfProjectRepository
                .getByEc10Id(ecForm10dto.getId());
        EcForm10LitigationAndViolationDetails ecForm10LitigationAndViolationDetails = ecForm10LitigationAndViolationDetailsRepository
                .getByEc10Id(ecForm10dto.getId());

        EcForm10EmissionGeneration ecForm10EmissionGeneration = ecForm10EmissionGenerationRepository
                .getByEc10Id(ecForm10dto.getId());
        EcForm10HazardousWasteGeneration ecForm10HazardousWasteGeneration = ecForm10HazardousWasteGenerationRepository
                .getByEc10Id(ecForm10dto.getId());
        EcForm10AdditionalDocument ecForm10AdditionalDocument = ecForm10AdditionalDocumentRepository
                .findAllByFormId1(ecForm10dto.getId());
        EcForm10Undertaking ecForm10Undertaking = ecForm10UndertakingRepository.getByEc10Id(ecForm10dto.getId());

        EcForm10ProjectDetails ecForm10ProjectDetails = new EcForm10ProjectDetails();

        BeanUtils.copyProperties(ecForm10dto, ecForm10ProjectDetails);
        /*
         * ecForm10ProjectDetails.setCommonFormDetail(commonFormDetail);
         * ecForm10ProjectDetails.setProponentApplications(proponentApplications);
         * ecForm10ProjectDetails.setEcForm10LocationOfProject(ecForm10LocationOfProject
         * ); ecForm10ProjectDetails.setEcForm10LitigationAndViolationDetails(
         * ecForm10LitigationAndViolationDetails);
         * ecForm10ProjectDetails.setEcForm10BasicInformation(ecForm10BasicInformation);
         * ecForm10ProjectDetails.setEcForm10ProductDetails(ecForm10ProductDetails);
         * ecForm10ProjectDetails.setEcForm10EmissionGeneration(
         * ecForm10EmissionGeneration);
         * ecForm10ProjectDetails.setEcForm10HazardousWasteGeneration(
         * ecForm10HazardousWasteGeneration);
         * ecForm10ProjectDetails.setEcForm10AdditionalDocument(
         * ecForm10AdditionalDocument);
         * ecForm10ProjectDetails.setEcForm10Undertaking(ecForm10Undertaking);
         */
        return ResponseHandler.generateResponse("Save Ec Other Details form", HttpStatus.OK, null,
                ecForm10ProjectDetails);
    }

    public Object getEcForgetAdditionalInformation10(Integer ecForm10Id) {
        return ecForm10AdditionalDocumentRepository.findAllByFormId1(ecForm10Id);
    }

    public Object deleteAdditionalInformation(Integer ecForm10Id) {
        try {
            if (ecForm10AdditionalDocumentRepository.findAllByFormId1(ecForm10Id) != null) {
                ecForm10AdditionalDocumentRepository.deleteAdditionalDocumentsByFormId(ecForm10Id);
                ecForm10AdditionalDocumentRepository.deleteAllByFormId(ecForm10Id);
                return "Deleted Successfully";
            } else {
                throw new ProjectNotFoundException("Additional Information not found with ID ---> " + ecForm10Id);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error occurred while deleting Additional information with Form 10 Id ->  " + ecForm10Id, e);
        }
    }

    private void generateProposalNo(Integer maxCount, CommonFormDetail commonForm, ProponentApplications applications,
                                    String sectorName, EcForm10BasicInformation ecForm10BasicInformation) {
//        String proposal_no = null;
//        if (tempClearances.isEmpty()) {
//
//            proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()).orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/" + sectorName + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
//            applications.setProposal_sequence(400000);
//            proposal_no = proposal_no.replaceAll("\\s", "");
//            applications.setProposal_no(proposal_no);
//            ecForm10BasicInformation.setProposal_no(proposal_no);
//        } else {
//            Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence()).max(Comparator.comparing(Integer::valueOf)).get();
        if (maxCount != null) {
            applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
            applications.setProposal_no(generateProposalNo(maxCount, commonForm, sectorName));
            ecForm10BasicInformation.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
        }
//        }
    }

    private void generateProposalNo(List<ProponentApplications> tempClearances, CommonFormDetail commonForm,
                                    ProponentApplications applications, String sectorName, EcForm10ProjectDetails ecForm10ProjectDetails) {
        String proposal_no = null;
        if (tempClearances.isEmpty()) {

            proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
                    + sectorName + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
            applications.setProposal_sequence(400000);
            proposal_no = proposal_no.replaceAll("\\s", "");
            applications.setProposal_no(proposal_no);
            ecForm10ProjectDetails.setProposal_no(proposal_no);
        } else {
            Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                    .max(Comparator.comparing(Integer::valueOf)).get();
            if (maxCount != null) {
                applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                applications.setProposal_no(generateProposalNo(maxCount, commonForm, sectorName));
                ecForm10ProjectDetails.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
            }
        }
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);

        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail commonForm, String sectorName) {
        String cafId = "IA/"
                + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                + "/" + sectorName + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }



    /*
     * EcForm10ProjectActivityDetails
     */

    public List<EcForm10ProjectActivityDetails> saveEcForm10ProjectActivityDetails(Integer ecForm10Id,
                                                                                 List<EcForm10ProjectActivityDetails> environmentClearanceProjectActivityDetails) {

        EcForm10BasicInformation temp = ecForm10BasicInformationRepository.findById(ecForm10Id).get();

        List<EcForm10ProjectActivityDetails> projectActivityDetails = environmentClearanceProjectActivityDetails.stream()
                .map(value -> {
                    value.setEcForm10BasicInformation(temp);
                    value.setProposalNo(temp.getProposal_no());
                    value.setActivities(activityRepository.findById(value.getActivityId())
                            .orElseThrow(() -> new ProjectNotFoundException("activity not found for activity ID")));
                    value.setSubActivities(subActivityRepository.findById(value.getSubActivityId()).orElseThrow(
                            () -> new ProjectNotFoundException("subactivity not found for subactivity ID")));

                    return value;
                }).collect(Collectors.toList());
        List<EcForm10ProjectActivityDetails> environmentDetails = ecForm10ProjectActivityDetailsRepository
                .saveAll(projectActivityDetails);
        log.info(
                "INFO ------------ saveEnvironmentClearanceProjectActivityDetails Environment Clearance Project Activity Details WITH ecId ----> "
                        + ecForm10Id + " ----SAVED - SUCCESS");
        return environmentDetails;
    }

    public ResponseEntity<Object> getECProjectActivityData(int id) {
        log.info(
                "INFO ------------ getECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "
                        + id + " ---- FOUND and RETRIEVED - SUCCESS");
        return ResponseHandler.generateResponse("get environment data list by ec_id", HttpStatus.OK, "",
                ecForm10ProjectActivityDetailsRepository.findDetailByEcId(id));

    }

    public ResponseEntity<Object> deleteECProjectActivityData(int id) {

        EcForm10ProjectActivityDetails environmentClearanceProjectActivityDetails = ecForm10ProjectActivityDetailsRepository
                .findById(id).get();
        environmentClearanceProjectActivityDetails.setIsDelete(true);
        ecForm10ProjectActivityDetailsRepository.save(environmentClearanceProjectActivityDetails);
        log.info(
                "INFO ------------ deleteECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "
                        + id + " ---- FOUND and DELETED - SUCCESS");
        return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
                "deleted successfully");

    }

    public ResponseEntity<Object> saveConsentUnderAirDetails(List<EcForm10ConsentUnderAirDetails> consentUnderAirDetails,Integer ecForm10Id){
        try {
            EcForm10BasicInformation basicInformation=ecForm10BasicInformationRepository.findById(ecForm10Id)
                    .orElseThrow(() -> new ProjectNotFoundException("form 10 basic details not found with ID" + ecForm10Id));

            List<EcForm10ConsentUnderAirDetails> response= new ArrayList<>();
            response=consentUnderAirDetails.stream().map(value->{
                value.setEcForm10BasicInformation(basicInformation);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saving consent under air details",HttpStatus.OK,"",ecForm10ConsentUnderAirDetailsRepository.saveAll(response));
        }catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving consent under air details - ", e);
        }
    }

    public ResponseEntity<Object> deleteConsentUnderAirDetails(Integer id){
        try {
            EcForm10ConsentUnderAirDetails temp= ecForm10ConsentUnderAirDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("form 10 consent under air details not found with ID" + id));
            temp.setIs_active(false);
            temp.setIs_delete(true);

            return ResponseHandler.generateResponse("deleting consent under air details for id:"+id,HttpStatus.OK,"",ecForm10ConsentUnderAirDetailsRepository.save(temp));
        }catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in deleting consent under air details - ", e);
        }
    }

    public ResponseEntity<Object> saveAuthorizationHazardousDetails(List<EcForm10AuthorizationHazardousDetails> hazardousDetails,Integer ecForm10Id){
        try {
            EcForm10BasicInformation basicInformation=ecForm10BasicInformationRepository.findById(ecForm10Id)
                    .orElseThrow(() -> new ProjectNotFoundException("form 10 basic details not found with ID" + ecForm10Id));

            List<EcForm10AuthorizationHazardousDetails> response= new ArrayList<>();
            response=hazardousDetails.stream().map(value->{
                value.setEcForm10BasicInformation(basicInformation);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saving authorization hazardous details",HttpStatus.OK,"",ecForm10AuthorizationHazardousDetailsRepository.saveAll(response));
        }catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in saving authorization hazardous details - ", e);
        }
    }

    public ResponseEntity<Object> deleteAuthorizationHazardousDetails(Integer id){
        try {
            EcForm10AuthorizationHazardousDetails temp= ecForm10AuthorizationHazardousDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("form 10 authorization hazardous details not found with ID" + id));
            temp.setIs_active(false);
            temp.setIs_delete(true);

            return ResponseHandler.generateResponse("deleting authorization hazardous details for id:"+id,HttpStatus.OK,"",ecForm10AuthorizationHazardousDetailsRepository.save(temp));
        }catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("Error in deleting authorization hazardous details - ", e);
        }
    }
}
