package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.*;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.StandardTORCertificate.EcAmendmentCertificate;
import com.backend.model.StandardTORCertificate.EcAmendmentCertificatePlantEquipment;
import com.backend.model.StandardTORCertificate.EcExtensionOfValidityCertificate;
import com.backend.model.StandardTORCertificate.EcMiningAmendmentCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPartBRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcAmendmentCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcMiningAmendmentCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EcMiningAmendmentCertificateService {
    @Autowired
    EcMiningAmendmentCertificateRepository ecMiningAmendmentCertificateRepository;

    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

    @Autowired
    Environment environment;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    EcAmendmentCertificateRepository ecAmendmentCertificateRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    EcPartCRepository ecPartCRepository;

    @Autowired
    ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    EcPartBRepository ecPartBRepository;

    @Autowired
    ActivityRepository activityRepository;

    public Object saveEcMiningAmendmentCertificate(UserPrincipal principal, EcMiningAmendmentCertificate ecMiningAmendmentCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for Standard tor Certificate========================");
        EcMiningAmendmentCertificate ecMiningAmendmentCertificateResult = null;
        //Maintaining History
        if (ecMiningAmendmentCertificateRepository.existsById(ecMiningAmendmentCertificate.getId())) {
            log.info("==================One Record is already exist for Standard tor Certificate========================");
            Optional<EcMiningAmendmentCertificate> ecMiningAmendmentCertificate1
                    = ecMiningAmendmentCertificateRepository.findById(ecMiningAmendmentCertificate.getId());
            ecMiningAmendmentCertificateResult = ecMiningAmendmentCertificate1.get();
            if (ecMiningAmendmentCertificateResult.getCreated_by().equals(principal.getId())) {
                ecMiningAmendmentCertificate.setIsActive(true);
                ecMiningAmendmentCertificate.setId(ecMiningAmendmentCertificateResult.getId());
            } else {
                ecMiningAmendmentCertificateResult.setIsActive(false);
                ecMiningAmendmentCertificateRepository.save(ecMiningAmendmentCertificateResult);
                ecMiningAmendmentCertificate.setId(null);
                int version = ObjectUtils.isEmpty(ecMiningAmendmentCertificate.getVersion()) ? 1 : ecMiningAmendmentCertificate.getVersion() + 1;
                ecMiningAmendmentCertificate.setIsActive(true);
                ecMiningAmendmentCertificate.setVersion(version);
            }
        }
        log.info("==================Saved Standard tor Certificate========================");
        int version = ObjectUtils.isEmpty(ecMiningAmendmentCertificate.getVersion()) ? 1 : ecMiningAmendmentCertificate.getVersion() + 1;
        ecMiningAmendmentCertificate.setIsActive(true);
        ecMiningAmendmentCertificate.setVersion(version);

        Optional.ofNullable(ecMiningAmendmentCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecMiningAmendmentCertificate.getProponentId(), ecMiningAmendmentCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecMiningAmendmentCertificate.getProponentId() + " proposal no: " + ecMiningAmendmentCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecMiningAmendmentCertificate.getProponentId() + " proposal no: " + ecMiningAmendmentCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecMiningAmendmentCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_mining_amendment" + convertedProposalNo + "_" + ecMiningAmendmentCertificate.getProponentId(), "pdf");

        //if (ecMiningAmendmentCertificate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath = FileUploadUtil.generatePdfFromHtml(ecMiningAmendmentCertificate.getHtmlContent(),
                    folderDir, fileName,ecMiningAmendmentCertificate.getProposal_No());
        //}
        ecMiningAmendmentCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecMiningAmendmentCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);

        return ecMiningAmendmentCertificateRepository.save(ecMiningAmendmentCertificate);
    }



    public CertProposalDetailInfo getEcAmendmentDetailByPropId(int propId, String proposalNo) {
        log.info("==============================getting EC Amendment detail by ProId===========================");
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
        EcMiningAmendmentCertificate ecAmendmentCertificate = ecMiningAmendmentCertificateRepository
                .ecMiningAmendmentCertificateInfoBytPropId(propId, proposalNo);

        if (!ObjectUtils.isEmpty(ecAmendmentCertificate)) {
            BeanUtils.copyProperties(ecAmendmentCertificate, proposalDetailInfo);

            ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo).get();
            if (proponentApplications != null) {

                CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
                proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

                proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
            }

            return proposalDetailInfo;
        }

        /******** New Changes Start ************/

        String proposalNoParts[] = proposalNo.split("/");
        String authorityCode = proposalNoParts[0];

        if ("SIA".equals(authorityCode)) {
            proposalDetailInfo.setIssuingAuthority("SIAEE");
        } else {
            proposalDetailInfo.setIssuingAuthority("MoEF&CC");
        }

        // Populating using ProponentApplications
        Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(propId);
        ProponentApplications proponentApplication = proponentApplications
                .orElseThrow(() -> new PariveshException("proponent not found"));
        proposalDetailInfo.setFileNo(proponentApplication.getMoefccFileNumber());
        proposalDetailInfo
                .setClearanceType(applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name());

        // Populating using CommonFormDetail
        CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplication.getCaf_id());
        proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
        proposalDetailInfo.setCompanyname(CommonUtils.handleEmpty(commonFormDetail.getOrganization_name()));
        proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
        proposalDetailInfo.setCafId(proponentApplication.getCaf_id());
        proposalDetailInfo.setProponentId(propId);
        proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
        proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

        // Populating using ProjectDetails
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
        proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));

        // Populating using EcPartC
        //Optional<EcPartC> ecPartC = ecPartCRepository.getFormById(proponentApplication.getProposal_id());
        Optional<EcPartC> ecPartC = ecPartCRepository.getStepOneForm(proponentApplication.getProposal_id());
        EcPartC ecPartCs = ecPartC.orElseThrow(() -> new PariveshException(
                "No record found in EC Part C for this proposal Id: " + proponentApplication.getProposal_id()));

        // Condition for Ec_id
        if (!(ObjectUtils.isEmpty(ecPartCs.getEc_id()))) {
            proposalDetailInfo.setEcId(ecPartCs.getEc_id());
            // throw new PariveshException("Invalid parameter : Ec Id must not be null");
        }

        // Populating using EnvironmentClearence
        // EnvironmentClearence environmentClearence =
        // getEnvironmentClearanceByPropId(ecPartCs.getEc_id());
        proposalDetailInfo.setCategory(ecPartCs.getProject_category());
        // proposalDetailInfo.setForestLandB(ecPartCs.getFc_applied_diversion());
        proposalDetailInfo.setProposal_No(ecPartCs.getProposal_no());
        log.info(String.valueOf(ecPartCs));
        //log.info(String.valueOf(ecPartC.get().getCreated_on()));
        proposalDetailInfo.setEcDate(ecPartCs.getDate_of_issue_tor() != null? ecPartCs.getDate_of_issue_tor().toString() : "NA");
        proposalDetailInfo.setIs_for_old_proposal(ecPartCs.getIs_for_old_proposal());
        // Getting project activity id and name on behalf of sector code
        // List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails =
        // environmentClearanceProjectActivityDetailsRepository
        // .findDetailByEcId(environmentClearence.getId());
        List<String> projectIds = new ArrayList<>();
        List<String> projectNames = new ArrayList<>();

        Integer activity_id = ecPartCs.getMajor_activity_id();
        Integer sub_activity_id = ecPartCs.getMajor_sub_activity_id();

        List<Activities> activity = activityRepository.findAllActivityById(activity_id);
        String itemNo = activity.get(0).getItem_no() != null ? activity.get(0).getItem_no() + " " : "";
        String actName = activity.get(0).getName();

        String projectId = itemNo + actName;
        projectNames.add(actName);

        /*
         * projectActivityDetails.stream().forEach(item -> { ActivityDto activities =
         * item.getActivityDto();
         *
         * if (activities.getItem_no() != null) projectIds.add(activities.getItem_no() +
         * " " + activities.getName()); else projectIds.add(activities.getName());
         *
         * projectNames.add(activities.getName()); System.out.println(
         * "===================" + activities.getId() + "==========================" +
         * activities.getName()); });
         */

        proposalDetailInfo.setProjectIncludedScheduleNo(projectId);
        proposalDetailInfo.setProjectActivity(projectNames);
        proposalDetailInfo.setProjectID(projectDetails.getId());

        if (activity_id != null || sub_activity_id != null)
            proposalDetailInfo.setSector(activitySectorRepository.getSectorName(activity_id, sub_activity_id));

        // Populating using Address and Location
        District district = districtRepository.getDistrictByCode(projectDetails.getMain_state(),
                projectDetails.getMain_district());
        State state = stateRepository.getByStateCode(projectDetails.getMain_state());
        String districtName = district != null ? district.getName() : "NA";
        String stateName = state != null ? state.getName() : "NA";

        Integer stateId = commonFormDetail.getOrganization_state();
        State stateOrg = stateRepository.getByStateCode(stateId);
        String stateOrgName = stateOrg != null ? stateOrg.getName() + ", " : "";

        Integer districtId = commonFormDetail.getOrganization_district();
        District districtOrg = districtRepository.getDistrictByCode(stateId, districtId);
        String districtOrgName = districtOrg != null ? districtOrg.getName() + ", " : "";

        String streetOrg = commonFormDetail.getOrganization_street() != null
                ? commonFormDetail.getOrganization_street() + ", "
                : "";
        String cityOrg = commonFormDetail.getOrganization_city() != null
                ? commonFormDetail.getOrganization_city() + ", "
                : "";
        String landmarkOrg = commonFormDetail.getOrganization_landmark() != null
                ? commonFormDetail.getOrganization_landmark() + ", "
                : "";
        String pinOrg = commonFormDetail.getOrganization_pincode() != null ? commonFormDetail.getOrganization_pincode()
                : "";

        String registeredAddress=null;
        if (cityOrg!=null){
            registeredAddress = streetOrg + cityOrg
                    + districtOrgName + stateOrgName
                    + landmarkOrg
                    + pinOrg;
        }else{
            registeredAddress = streetOrg + districtOrgName + stateOrgName
                    + landmarkOrg + pinOrg;
        }

        proposalDetailInfo.setRegisteredAddress(registeredAddress);
        proposalDetailInfo.setNameOfOrganization(commonFormDetail.getOrganization_name());
        proposalDetailInfo.setLocationOfProject(districtName + ", " + stateName);
        proposalDetailInfo.setDateOfSubmission(proponentApplication.getLast_submission_date());
        proposalDetailInfo.setReason(ecPartCs.getProject_exempted_reason());;


        // Adding Plant information
        if (ecPartCs.getEc_id() != null) {
            EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecPartCs.getEc_id());
            EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId()).orElseThrow(
                    () -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));

            List<EcAmendmentCertificatePlantEquipment> listOfEcIndustryProposals = new ArrayList<>();
            EcAmendmentCertificatePlantEquipment ecAmendmentCertificatePlantEquipment = new EcAmendmentCertificatePlantEquipment();
            if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
                ecAmendmentCertificatePlantEquipment.setPlant_equipment(ecPartB.getEcIndustryProposal().getPlant());
                ecAmendmentCertificatePlantEquipment
                        .setExistingConfiguration(ecPartB.getEcIndustryProposal().getExisting_configuration());
                ecAmendmentCertificatePlantEquipment
                        .setProposedConfiguration(ecPartB.getEcIndustryProposal().getProposed_configuration());
                ecAmendmentCertificatePlantEquipment.setRemarksIfAny(ecPartB.getEcIndustryProposal().getRemarks());
            }

            listOfEcIndustryProposals.add(ecAmendmentCertificatePlantEquipment);
            proposalDetailInfo.setEcAmendmentCertificatePlantEquipments(listOfEcIndustryProposals);
            proposalDetailInfo.setApplicabilityOfGeneralConditions(environmentClearence.isIs_general_condition_specified() ? "YES" : "NO");
            //proposalDetailInfo.setDateOfSubmission(environmentClearence.getCreated_on());

            proposalDetailInfo.setStatusOfImplementationProject( environmentClearence.getEcProjectDetail() != null ? environmentClearence.getEcProjectDetail().getProject_implementation_status() : "NA");
        }
        /******** New Changes End ************/

        // String certificateName = "ec_amendment";
        // return
        // certProposalDetailInfoCommonService.getDetailsFromDTO(proposalDetailInfo,
        // proposalNo, propId,
        // certificateName);

        return proposalDetailInfo;
    }

    private EnvironmentClearence getEnvironmentClearanceByPropId(Integer id) {
        EnvironmentClearence environmentClearence = environmentClearenceRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("EnvironmentClearence form not found"));
        environmentClearence.setProponentApplications(
                proponentApplicationRepository.getApplicationByProposalId(environmentClearence.getId()));
        environmentClearence.setCommonFormDetail(commonFormDetailRepository
                .findDetailByCafId(environmentClearence.getProponentApplications().getCaf_id()));
        return environmentClearence;
    }

    public String getIdentificationNo(int propId) {

        log.info("=====================Identification No generation for {}================================", propId);

        // Populating using ProponentApplications
        Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(propId);
        ProponentApplications proponentApplication = proponentApplications
                .orElseThrow(() -> new PariveshException("proponent not found"));

        String identificationNo = ecAmendmentCertificateRepository
                .getIdentificationNo(proponentApplication.getProposal_no());

        return identificationNo;
    }

}
