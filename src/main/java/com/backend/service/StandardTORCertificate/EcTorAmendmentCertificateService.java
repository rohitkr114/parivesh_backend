package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.ActivityDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.*;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.StandardTORCertificate.EcTorAmendmentCertificate;
import com.backend.model.StandardTORCertificate.EcTorAmendmentCertificatePlantEquipment;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EnvironmentClearance.EcPartBRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcTorAmendmentCertificateRepository;
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

@Service
@Slf4j
public class EcTorAmendmentCertificateService {

    @Autowired
    EcTorAmendmentCertificateRepository ecTorAmendmentCertificateRepository;

    @Autowired
    EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

    @Autowired
    Environment environment;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

    @Autowired
    ApplicationsRepository applicationsRepository;


    @Autowired
    EcPartBRepository ecPartBRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    StateRepository stateRepository;

    public Object saveAmendmentCertificate(UserPrincipal principal, EcTorAmendmentCertificate ecTorAmendmentCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for TOR Amendment Certificate========================");
        EcTorAmendmentCertificate ecTorAmendmentCertificateResult = null;
        //Maintaining History
        if (ecTorAmendmentCertificateRepository.existsById(ecTorAmendmentCertificate.getId())) {
            log.info("==================One Record is already exist for TOR Amendment Certificate========================");
            Optional<EcTorAmendmentCertificate> ecTorAmendmentCertificate1 = ecTorAmendmentCertificateRepository.findById(ecTorAmendmentCertificate.getId());
            ecTorAmendmentCertificateResult = ecTorAmendmentCertificate1.get();
            if (ecTorAmendmentCertificateResult.getCreated_by().equals(principal.getId())) {
                ecTorAmendmentCertificate.setIsActive(true);
                ecTorAmendmentCertificate.setId(ecTorAmendmentCertificateResult.getId());
            } else {
                ecTorAmendmentCertificateResult.setIsActive(false);
                ecTorAmendmentCertificateRepository.save(ecTorAmendmentCertificateResult);
                ecTorAmendmentCertificate.setId(null);
                int version = ObjectUtils.isEmpty(ecTorAmendmentCertificate.getVersion()) ? 1 : ecTorAmendmentCertificate.getVersion() + 1;
                ecTorAmendmentCertificate.setIsActive(true);
                ecTorAmendmentCertificate.setVersion(version);
            }
        }
        log.info("==================Saved TOR Amendment Certificate========================");
        int version = ObjectUtils.isEmpty(ecTorAmendmentCertificate.getVersion()) ? 1 : ecTorAmendmentCertificate.getVersion() + 1;
        ecTorAmendmentCertificate.setIsActive(true);
        ecTorAmendmentCertificate.setVersion(version);

        Optional.ofNullable(ecTorAmendmentCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecTorAmendmentCertificate.getProponentId(), ecTorAmendmentCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecTorAmendmentCertificate.getProponentId() + " proposal no: " + ecTorAmendmentCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecTorAmendmentCertificate.getProponentId() + " proposal no: " + ecTorAmendmentCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecTorAmendmentCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_tor_amendment" + convertedProposalNo + "_" + ecTorAmendmentCertificate.getProponentId(), "pdf");

        //if (ecTorAmendmentCertificate.getStatus().equalsIgnoreCase("complete")) {
        //File path generation code this could be common
        String filePath = FileUploadUtil.generatePdfFromHtml(ecTorAmendmentCertificate.getHtmlContent(),
                folderDir, fileName, ecTorAmendmentCertificate.getProposal_No());
        //}
        ecTorAmendmentCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecTorAmendmentCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);


        return ecTorAmendmentCertificateRepository.save(ecTorAmendmentCertificate);
    }

    public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String proposalNo) {
        log.info("==============================getting EC detail by ProId===========================");
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
        EcTorAmendmentCertificate ecTorAmendmentCertificate = ecTorAmendmentCertificateRepository
                .getEcTorAmendmentCertificateInfoBytPropId(propId, proposalNo);

        if (!ObjectUtils.isEmpty(ecTorAmendmentCertificate)) {
            BeanUtils.copyProperties(ecTorAmendmentCertificate, proposalDetailInfo);

            ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo).get();
            if (proponentApplications != null) {

                CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
                proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

                proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
            }

            return proposalDetailInfo;
        }
        String proposalNoParts[] = proposalNo.split("/");
        String authorityCode = proposalNoParts[0];
        if ("SIA".equals(authorityCode)) {
            proposalDetailInfo.setIssuingAuthority("SIAEE");
        } else {
            proposalDetailInfo.setIssuingAuthority("MoEF&CC");
        }
        EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalNo);
        //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
        List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());

        proposalDetailInfo.setEcId(environmentClearence.getId());

        Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(propId);


        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        proposalDetailInfo.setFileNo(proponentApplication.getMoefccFileNumber());

        //Populating using CommonFormDetail
        CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplication.getCaf_id());
        proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
        proposalDetailInfo.setCompanyname(CommonUtils.handleEmpty(commonFormDetail.getOrganization_name()));
        proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
        proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
        //proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getApplicant_email()));
        proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
        proposalDetailInfo.setNameOfOrganization(CommonUtils.replaceEmptyWithNA(commonFormDetail.getOrganization_name()));
        proposalDetailInfo.setCafId(proponentApplication.getCaf_id());
        //Populating using ProjectDetails
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
        proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));
        proposalDetailInfo.setCategory(environmentClearence.getProject_category());
        proposalDetailInfo.setProponentId(propId);
        proposalDetailInfo.setProposal_No(proposalNo);
        //proposalDetailInfo.setTorIdentificationNumber(getIdentificationNo(propId,proposalNo));

        //String ProposalFor= commonFormDetail.getProposal_for() != null ? commonFormDetail.getProposal_for() : "";

        proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name());


        /**
         * Getting project activity id and name on behalf od sector code.
         */

        List<String> projectIds = new ArrayList<>();
        List<String> projectNames = new ArrayList<>();
        
        /*projectActivityDetails.stream()
                .forEach(item -> {
                    Activities activities = item.getActivities();
                    projectIds.add(activities.getItem_no() + "/" + activities.getName());
                    projectNames.add(activities.getName());
                    System.out.println("===================" + activities.getId() + "==========================" + activities.getName());
                });*/

        projectActivityDetails.stream()
                .forEach(item -> {
                    ActivityDto activities = item.getActivityDto();

                    if (activities.getItem_no() != null)
                        projectIds.add(activities.getItem_no() + " " + activities.getName());
                    else
                        projectIds.add(activities.getName());

                    projectNames.add(activities.getName());
                    System.out.println("===================" + activities.getId() + "==========================" + activities.getName());
                });

        proposalDetailInfo.setProjectIncludedScheduleNo(String.join(",", projectIds));
        proposalDetailInfo.setProjectActivity(projectNames);

        if (environmentClearence.getMajor_activity_id() != null || environmentClearence.getMajor_sub_activity_id() != null)
            proposalDetailInfo.setSector(activitySectorRepository.getSectorName(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()));

        //Populating using District
        District district = districtRepository.getDistrictByCode(projectDetails.getMain_state(), projectDetails.getMain_district());
        State state = stateRepository.getByStateCode(projectDetails.getMain_state());
        String districtName = district != null ? district.getName() : "NA";
        String stateName = state != null ? state.getName() : "NA";
        proposalDetailInfo.setLocationOfProject(CommonUtils.replaceEmptyWithNA(districtName) + ", " + CommonUtils.replaceEmptyWithNA(stateName));


        Integer stateId = commonFormDetail.getOrganization_state();
        State stateOrg = stateRepository.getByStateCode(stateId);
        String stateOrgName = stateOrg != null ? stateOrg.getName() + ", " : "";

        Integer districtId = commonFormDetail.getOrganization_district();
        District districtOrg = districtRepository.getDistrictByCode(stateId, districtId);
        String districtOrgName = districtOrg != null ? districtOrg.getName() + ", " : "";

        String streetOrg = commonFormDetail.getOrganization_street() != null ? commonFormDetail.getOrganization_street() + ", " : "";
        String cityOrg = commonFormDetail.getOrganization_city() != null ? commonFormDetail.getOrganization_city() + ", " : "";
        String landmarkOrg = commonFormDetail.getOrganization_landmark() != null ? commonFormDetail.getOrganization_landmark() + ", " : "";
        String pinOrg = commonFormDetail.getOrganization_pincode() != null ? commonFormDetail.getOrganization_pincode() : "";

        String registeredAddress = null;
        if (cityOrg != null) {
            registeredAddress = streetOrg + cityOrg
                    + districtOrgName + stateOrgName
                    + landmarkOrg
                    + pinOrg;
        } else {
            registeredAddress = streetOrg + districtOrgName + stateOrgName
                    + landmarkOrg + pinOrg;
        }
        proposalDetailInfo.setRegisteredAddress(registeredAddress);


        EcPartB ecPartB1 = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));

        //log.info(ecPartB1.getId().toString());
        //log.info(ecPartB1.getEcIndustryProposal().getId().toString());
        //Plant information
        List<EcTorAmendmentCertificatePlantEquipment> listPlantEquipments = new ArrayList<>();
        EcTorAmendmentCertificatePlantEquipment ecTorAmendmentCertificatePlantEquipment = new EcTorAmendmentCertificatePlantEquipment();
        if (ObjectUtils.isNotEmpty(ecPartB1.getEcIndustryProposal())) {
            ecTorAmendmentCertificatePlantEquipment.setPlantEquipment(ecPartB1.getEcIndustryProposal().getPlant());
            ecTorAmendmentCertificatePlantEquipment.setExistingConfiguration(ecPartB1.getEcIndustryProposal().getExisting_configuration());
            ecTorAmendmentCertificatePlantEquipment.setRemarks(ecPartB1.getEcIndustryProposal().getRemarks());
            ecTorAmendmentCertificatePlantEquipment.setProposedConfiguration(ecPartB1.getEcIndustryProposal().getProposed_configuration());
            ecTorAmendmentCertificatePlantEquipment.setFinalConfiguration(ecPartB1.getEcIndustryProposal().getFinal_configuration());

        }
        listPlantEquipments.add(ecTorAmendmentCertificatePlantEquipment);
        //log.info(ecTorAmendmentCertificatePlantEquipment.toString());
        proposalDetailInfo.setAmendmentCertificatePlantEquipments(listPlantEquipments);

        String folderDir = null;
        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
        String fileName = FileUploadUtil.fileNameMaker("ec_tor_amendment" + convertedProposalNo + "_" + propId, "pdf");

        String clearanceTypeId = proponentApplication.getClearance_id().toString();
        String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
        String proposalId = proponentApplication.getProposal_id().toString();

        folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
        proposalDetailInfo.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setVersion(0);
        proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplication.getMoefccFileNumber()));
        proposalDetailInfo.setDateOfSubmission(environmentClearence.getCreated_on());
        proposalDetailInfo.setIs_for_old_proposal(environmentClearence.getIs_for_old_proposal());

        return proposalDetailInfo;


        /*return certProposalDetailInfoCommonService.getDetailsFromDTO(proposalDetailInfo, proposalNo, propId);*/
    }

    EnvironmentClearence getEnvironmentClearanceByPropId(String proposalNo) {
   /* Integer proposalId = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo)
            .orElseThrow(() -> new PariveshException("No record found"));*/
        EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(proposalNo);
        environmentClearence.setProponentApplications(
                proponentApplicationRepository.getApplicationByProposalId(environmentClearence.getId()));
        environmentClearence.setCommonFormDetail(commonFormDetailRepository
                .findDetailByCafId(environmentClearence.getProponentApplications().getCaf_id()));
        return environmentClearence;
    }

    public String getIdentificationNo(int propId, String proposalNO) {
        String sCode = null;
        Integer formCode = null;
        String clearanceTypeCode = null;
        log.info(propId + " This is prop Id ");
        log.info("=====================Identification No generation================================");
        EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalNO);
        ProponentApplications proponentApplications = environmentClearence.getProponentApplications();
        Applications applications = proponentApplications.getApplications();
        String formId = applications.getDd_name();

        log.info("Form Id: " + formId);
        Integer majorActivityId = environmentClearence.getMajor_activity_id();
        log.info("Major activity Id: " + majorActivityId);
        String proposalNo = environmentClearence.getProposal_no();
        log.info("proposal No: " + proposalNo);

        if (majorActivityId != null) {
            Optional<String> sectorCode = activityRepository.getSectorCodeByEcId(majorActivityId);
            sCode = sectorCode.orElse("NA");
            //log.info("Sector code: " + sectorCode.get());
        }

        if (formId != null) {
            formCode = applicationsRepository.getFormCodeByDdName(formId);
            log.info("Form code: " + formCode);
            clearanceTypeCode = applicationsRepository.getClearanceTypeByDdName(formId)
                    .orElse("NA");
        }

        String[] propArray = proposalNo.split("/");
        String propSectorName = propArray[2];
        String propNo = propArray[3];
        String year = propArray[4];
        String torIdentificationNo = new StringBuilder()
                .append(clearanceTypeCode != null ? clearanceTypeCode : "NA")
                .append(formCode != null ? formCode : "NA")
                .append(propSectorName)
                .append(year.substring(1))
                .append(propNo)
                .toString();
        return torIdentificationNo;
    }
}
