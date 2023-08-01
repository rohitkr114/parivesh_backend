package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.*;
import com.backend.model.EcForm9TransferOfEC.EcForm9TransferOfEC;
import com.backend.model.StandardTORCertificate.EcTransferOfMiningLeaseAcknowledgmentCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm9Repository.EcForm9TransferOfECRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcTransferOfMiningLeaseAcknowledgmentCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EcTransferOfMiningLeaseAcknowledgmentCertificateService {

    @Autowired
    EcTransferOfMiningLeaseAcknowledgmentCertificateRepository ecTransferOfMiningLeaseAcknowledgmentCertificateRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;
    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

    @Autowired
    EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    EcForm9TransferOfECRepository ecForm9TransferOfECRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    Environment environment;

    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    ActivitySubActivitySectorRepository activitySectorRepository;

    public Object saveEcTransferOfMiningLeaseCertificate(UserPrincipal principal, EcTransferOfMiningLeaseAcknowledgmentCertificate ecTransferOfMiningLeaseAcknowledgmentCertificate, HttpServletRequest request,
                                                         HttpServletResponse response) throws DocumentException, IOException, JRException {

        log.info("==================Calling Save API for Ec TOR Template Certificate========================");
        EcTransferOfMiningLeaseAcknowledgmentCertificate ecTransferOfMiningLeaseAcknowledgmentCertificateResult = null;
        //Maintaining History
        if (ecTransferOfMiningLeaseAcknowledgmentCertificateRepository.existsById(ecTransferOfMiningLeaseAcknowledgmentCertificate.getId())) {
            log.info("==================One Record is already exist for TOR Amendment Certificate========================");
            Optional<EcTransferOfMiningLeaseAcknowledgmentCertificate> ecTransferOfMiningLeaseCertificate1 = ecTransferOfMiningLeaseAcknowledgmentCertificateRepository.findById(ecTransferOfMiningLeaseAcknowledgmentCertificate.getId());
            ecTransferOfMiningLeaseAcknowledgmentCertificateResult = ecTransferOfMiningLeaseCertificate1.get();

            ecTransferOfMiningLeaseAcknowledgmentCertificate.setId(null);
            if (ecTransferOfMiningLeaseAcknowledgmentCertificateResult.getCreated_by().equals(principal.getId())) {
                ecTransferOfMiningLeaseAcknowledgmentCertificate.setIsActive(true);
                ecTransferOfMiningLeaseAcknowledgmentCertificate.setId(ecTransferOfMiningLeaseAcknowledgmentCertificateResult.getId());
            } else {
                ecTransferOfMiningLeaseAcknowledgmentCertificateResult.setIsActive(false);
                ecTransferOfMiningLeaseAcknowledgmentCertificateRepository.save(ecTransferOfMiningLeaseAcknowledgmentCertificateResult);
                ecTransferOfMiningLeaseAcknowledgmentCertificate.setId(null);
                int version = ObjectUtils.isEmpty(ecTransferOfMiningLeaseAcknowledgmentCertificate.getVersion()) ? 1 : ecTransferOfMiningLeaseAcknowledgmentCertificate.getVersion() + 1;
                ecTransferOfMiningLeaseAcknowledgmentCertificate.setIsActive(true);
                ecTransferOfMiningLeaseAcknowledgmentCertificate.setVersion(version);
            }
        }
        log.info("==================Saved Ec TOR Template Certificate========================");
        int version = ObjectUtils.isEmpty(ecTransferOfMiningLeaseAcknowledgmentCertificate.getVersion()) ? 1 : ecTransferOfMiningLeaseAcknowledgmentCertificate.getVersion() + 1;
        ecTransferOfMiningLeaseAcknowledgmentCertificate.setIsActive(true);
        ecTransferOfMiningLeaseAcknowledgmentCertificate.setVersion(version);


        Optional.ofNullable(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProponentId(), ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecTransferOfMiningLeaseAcknowledgmentCertificate.getProponentId() + " proposal no: " + ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecTransferOfMiningLeaseAcknowledgmentCertificate.getProponentId() + " proposal no: " + ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_transfer_of_mining_lease_acknowledgment" + convertedProposalNo + "_" + ecTransferOfMiningLeaseAcknowledgmentCertificate.getProponentId(), "pdf");

        //if (ecTransferOfMiningLeaseAcknowledgmentCertificate.getStatus().equalsIgnoreCase("complete")) {
        //File path generation code this could be common
        String filePath =
                FileUploadUtil.generatePdfFromHtml(ecTransferOfMiningLeaseAcknowledgmentCertificate.getHtmlContent(), folderDir, fileName, ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        //}
        ecTransferOfMiningLeaseAcknowledgmentCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecTransferOfMiningLeaseAcknowledgmentCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);
        return ecTransferOfMiningLeaseAcknowledgmentCertificateRepository.save(ecTransferOfMiningLeaseAcknowledgmentCertificate);
    }

    public CertProposalDetailInfo getStandTorDetailByPropId(int proponentApplicationId, String proposalNo) {
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();

        EcTransferOfMiningLeaseAcknowledgmentCertificate ecTransferOfMiningLeaseAcknowledgmentCertificateResult = ecTransferOfMiningLeaseAcknowledgmentCertificateRepository
                .getTransferMiningLeaseAcknowledgmentTemplateInfoBytPropId(proponentApplicationId, proposalNo);

        if (!ObjectUtils.isEmpty(ecTransferOfMiningLeaseAcknowledgmentCertificateResult)) {
            BeanUtils.copyProperties(ecTransferOfMiningLeaseAcknowledgmentCertificateResult, proposalDetailInfo);

            ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(proponentApplicationId, proposalNo).get();
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

        Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(proponentApplicationId);

        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        proposalDetailInfo.setFileNo(proponentApplication.getMoefccFileNumber());

        //Populating using CommonFormDetail
        CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplication.getCaf_id());
        proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
        proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
        proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
        proposalDetailInfo.setCompanyname(CommonUtils.handleEmpty(commonFormDetail.getOrganization_name()));
        //proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getApplicant_email()));
        proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
        proposalDetailInfo.setCafId(commonFormDetail.getId());
        //Populating using ProjectDetails
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
        proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));

        //String ProposalFor= commonFormDetail.getProposal_for() != null ? commonFormDetail.getProposal_for() : "";
        proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name());


        //Populating using EcForm9
        Optional<EcForm9TransferOfEC> ecForm9TransferOfEC = ecForm9TransferOfECRepository.getEcForm9TransferOfECByID(proponentApplication.getProposal_id());
        EcForm9TransferOfEC ecForm9TransferOfECDetails = ecForm9TransferOfEC.orElseThrow(() -> new PariveshException("No record found in EC Form 9 for this proposal Id: "
                + proponentApplication.getProposal_id()));

        proposalDetailInfo.setNameOfPreviousLessee(ecForm9TransferOfECDetails.getTransferee_name());
        proposalDetailInfo.setNameOfSuccessfulBidder(ecForm9TransferOfECDetails.getTransferer_name());

        proposalDetailInfo.setCategory(ecForm9TransferOfECDetails.getProposal_category());
        proposalDetailInfo.setProposal_No(ecForm9TransferOfECDetails.getProposal_no());

        //  Condition for Ec_id
        /*if (ObjectUtils.isEmpty(ecForm9TransferOfECDetails.getEc_id())) {
            throw new PariveshException("Invalid parameter : Ec Id must not be null");
        }*/

        //Populating using EnvironmentClearence
        /*EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecForm9TransferOfECDetails.getEnvironmentClearence().getId());
        proposalDetailInfo.setCategory(environmentClearence.getProject_category());
        proposalDetailInfo.setEcId(ecForm9TransferOfECDetails.getEnvironmentClearence().getId());
        proposalDetailInfo.setProposal_No(environmentClearence.getProposal_no());
		*/

        //Populating using EnvironmentClearanceProjectActivityDetails
        //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
        //List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());


        // Getting project activity id and name on behalf od sector code.

        //List<String> projectIds = new ArrayList<>();
        List<String> projectNames = new ArrayList<>();
        /*projectActivityDetails.stream()
            .forEach(item -> {
                Activities activities = item.getActivities();
                projectIds.add(activities.getItem_no()+"/"+activities.getName());
                projectNames.add(activities.getName());
                System.out.println("===================" + activities.getId() + "==========================" + activities.getName());
            });*/
        
		/*projectActivityDetails.stream()
        .forEach(item -> {
            ActivityDto activities = item.getActivityDto();

            if (activities.getItem_no() != null)
            projectIds.add(activities.getItem_no() + " " + activities.getName());
            else
            projectIds.add(activities.getName());
            
            projectNames.add(activities.getName());
            System.out.println("===================" + activities.getId() + "==========================" + activities.getName());
        });*/


        proposalDetailInfo.setProjectID(projectDetails.getId());

        Integer activity_id = ecForm9TransferOfECDetails.getActivity_id();
        Integer sub_activity_id = ecForm9TransferOfECDetails.getSubActivity_id();

        List<Activities> activity = activityRepository.findAllActivityById(activity_id);
        String itemNo = activity.get(0).getItem_no() != null ? activity.get(0).getItem_no() + " " : "";
        String actName = activity.get(0).getName();

        String projectId = itemNo + actName;
        projectNames.add(actName);

        proposalDetailInfo.setProjectIncludedScheduleNo(projectId);
        proposalDetailInfo.setProjectActivity(projectNames);

        if (!(activity_id == 0 || sub_activity_id == 0))
            proposalDetailInfo.setSector(activitySectorRepository.getSectorName(activity_id, sub_activity_id));

        //Populating using District
        District district = districtRepository.getDistrictByCode(projectDetails.getMain_state(), projectDetails.getMain_district());
        State state = stateRepository.getByStateCode(projectDetails.getMain_state());
        String districtName = district != null ? district.getName() : "NA";
        String stateName = state != null ? state.getName() : "NA";

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
        proposalDetailInfo.setNameOfOrganization(commonFormDetail.getOrganization_name());
        proposalDetailInfo.setLocationOfProject(districtName + ", " + stateName);

        String folderDir = null;
        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
        String fileName = FileUploadUtil.fileNameMaker("ec_transfer_of_mining_lease_acknowledgment" + convertedProposalNo + "_" + proponentApplicationId, "pdf");

        String clearanceTypeId = proponentApplication.getClearance_id().toString();
        String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
        String proposalId = proponentApplication.getProposal_id().toString();

        folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
        proposalDetailInfo.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setVersion(0);
        proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplication.getMoefccFileNumber()));

        log.info("EcForm9TransferOfEC Certificate");

        BeanUtils.copyProperties(ecForm9TransferOfECDetails, proposalDetailInfo);
        return proposalDetailInfo;

    }

    private EnvironmentClearence getEnvironmentClearanceByPropId(Integer id) {
        EnvironmentClearence environmentClearence = environmentClearenceRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("EnvironmentClearence form not found"));
        environmentClearence.setProponentApplications(
                proponentApplicationRepository.getApplicationByProposalId(environmentClearence.getId()));
        environmentClearence.setCommonFormDetail(commonFormDetailRepository
                .findDetailByCafId(environmentClearence.getProponentApplications().getCaf_id()));
        return environmentClearence;
    }

    public String getIdentificationNo(int propId) {
        //String sCode = null;
        Integer formCode = null;
        String clearanceTypeCode = null;
        log.info(propId + " This is prop Id ");
        log.info("=====================Identification No generation================================");
        Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(propId);

        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        int proposalId = proponentApplication.getProposal_id();

        ////Populating using EcForm9
        /*Optional<EcForm9TransferOfEC> ecForm9 = ecForm9TransferOfECRepository.findById(proposalId);
        EcForm9TransferOfEC ecForm9TORDetail =  ecForm9.orElseThrow(() -> new PariveshException("Data missing in from 8 for proposalId " + proposalId));
        String proposalNo = ecForm9TORDetail.getProposal_no();
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();

        Applications applications = proponentApplication.getApplications();
        String formId = applications.getDd_name();

        //  Condition for Ec_id
        if (ObjectUtils.isEmpty(ecForm9TORDetail.getEc_id()) ) {
            throw new PariveshException("Invalid parameter : Ec Id must not be null");
        }
        EnvironmentClearence environmentClearence= environmentClearenceRepository.getById(ecForm9TORDetail.getId());
        List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
        String formAbbr = applications.getAbbr();
        String catId = environmentClearence.getProject_category();
        String clearanceTypeName = LocalClearance.getClearanceByCat(formAbbr, catId);
        proposalDetailInfo.setClearanceType(clearanceTypeName);
        List<String> projectIds = new ArrayList<>();
        List<String> projectNames = new ArrayList<>();
        projectActivityDetails.stream()
                .forEach(item -> {
                    Activities activities = item.getActivities();
                    projectIds.add(activities.getItem_no());
                    projectNames.add(activities.getName());
                    System.out.println("===================" + activities.getId() + "==========================" + activities.getName());
                });

        proposalDetailInfo.setProjectIncludedScheduleNo(String.join(",", projectIds));
        proposalDetailInfo.setProjectActivity(projectNames);

       if (proponentApplications != null) {
            proposalDetailInfo.setCategory(replaceEmptyWithNA(environmentClearence.getProject_category()));
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.get().getCaf_id());
            ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
            log.info("standard-tor-certificate: project ID:- " + commonFormDetail.getProject_id());
            if (commonFormDetail != null) {

                //Getting Name Of Project.
                proposalDetailInfo.setNameOfProject(handleEmpty(projectDetails.getName()));

                //Getting Location Of Project.
                District district = districtRepository.getDistrictById(projectDetails.getMain_district());
                Integer stateId = projectDetails.getMain_state();
                throwException(stateId, "state");
                State state = stateRepository.getStateById(stateId);
                String districtName = district != null ? district.getName() : "";
                String stateName = state != null ? state.getName() : "";
                List<String> registeredAddress = new LinkedList<>();
                addStrings(registeredAddress, commonFormDetail.getOrganization_street());
                addStrings(registeredAddress, commonFormDetail.getOrganization_city());
                addStrings(registeredAddress, districtName);
                addStrings(registeredAddress, stateName);
                proposalDetailInfo.setLocationOfProject(handleEmpty(districtName) + ", " + handleEmpty(stateName));

                //Getting  proposal no.
                proposalDetailInfo.setProposal_No(environmentClearence.getProposal_no());


            }
        }


        log.info("Form Id: " + formId);
        //Integer majorActivityId = environmentClearence.getMajor_activity_id();
        //Integer subMajorActivityId = environmentClearence.getMajor_sub_activity_id();
        //log.info("Major activity Id: " + majorActivityId);
        log.info("proposal No: " + proposalNo);


        if (formId != null) {
            formCode = applicationsRepository.getFormCodeByDdName(formId);
            log.info("Form code: " + formCode);
            clearanceTypeCode = applicationsRepository.getClearanceTypeByDdName(formId)
                    .orElse("NA") ;
        }

        String[] propArray = proposalNo.split("/");
        String propSectorName;
        String propNo;
        String year;

        if(propArray.length!=5){
            year = "NA";
        }
        else {
            year = propArray[4];
        }
             propSectorName = propArray[2];
             propNo = propArray[3];
             
        
        String torIdentificationNo = new StringBuilder()
                .append(clearanceTypeCode != null ? clearanceTypeCode : "NA")
                .append(formCode != null ? formCode : "NA")
                .append(propSectorName)
                .append(year.substring(1))
                .append(propNo)
                .toString();
         */

        String identificationNo = ecTransferOfMiningLeaseAcknowledgmentCertificateRepository.getIdentificationNo(proponentApplications.get().getProposal_no());

        return identificationNo;
    }

}
