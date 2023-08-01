package com.backend.service.StandardTORCertificate;


import com.backend.clearance.LocalClearance;
import com.backend.constant.AppConstant;
import com.backend.dto.ActivityDto;
import com.backend.dto.EcPartA.EcForm7ProjectActivityDetailsDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.*;
import com.backend.model.EcForm7.EcForm7;
import com.backend.model.EcForm8TransferOfTOR.EcForm8TransferOfTOR;
import com.backend.model.StandardTORCertificate.EcMiningFreshLetterCertificate;
import com.backend.model.StandardTORCertificate.EcTransferTemplate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm7.EcForm7ProjectActivityDetailsRepository;
import com.backend.repository.postgres.EcForm7.EcForm7Repository;
import com.backend.repository.postgres.StandardTorCertificate.EcTransferTemplateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import com.beust.jcommander.Strings;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.backend.util.CommonUtils.addStrings;
import static com.backend.util.CommonUtils.replaceEmptyWithNA;

@Slf4j
@Service
public class EcTransferTemplateService {

    @Autowired
    EcTransferTemplateRepository ecTransferTemplateRepository;
    
    @Autowired
    EcForm7ProjectActivityDetailsRepository ecForm7ProjectActivityDetailsRepository;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    EcForm7Repository ecForm7Repository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;
    
	@Autowired
    EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;
    
	 @Autowired
     ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    Environment environment;

    @Autowired
    StateRepository stateRepository;

    public Object saveEcTransferTemplate(UserPrincipal principal, EcTransferTemplate ecTransferTemplate, HttpServletRequest request,
                                         HttpServletResponse response) throws DocumentException, IOException, JRException{

        log.info("==================Calling Save API for Ec Transfer Template========================");
        EcTransferTemplate ecTransferTemplateResult = null;
        //Maintaining History
        if (ecTransferTemplateRepository.existsById(ecTransferTemplate.getId())) {
            log.info("==================One Record is already exist for Ec Transfer Template========================");
            Optional<EcTransferTemplate> ecTransferTemplate1 = ecTransferTemplateRepository.findById(ecTransferTemplate.getId());
            ecTransferTemplateResult = ecTransferTemplate1.get();
            if (ecTransferTemplateResult.getCreated_by().equals(principal.getId())) {
                ecTransferTemplate.setIsActive(true);
                ecTransferTemplate.setId(ecTransferTemplateResult.getId());
            } else {
                ecTransferTemplateResult.setIsActive(false);
                ecTransferTemplateRepository.save(ecTransferTemplateResult);
                ecTransferTemplate.setId(null);
                int version = ObjectUtils.isEmpty(ecTransferTemplate.getVersion()) ? 1 : ecTransferTemplate.getVersion() + 1;
                ecTransferTemplate.setIsActive(true);
                ecTransferTemplate.setVersion(version);
            }
        }
        log.info("==================Saved Ec Transfer Template========================");
        int version = ObjectUtils.isEmpty(ecTransferTemplate.getVersion()) ? 1 : ecTransferTemplate.getVersion() + 1;
        ecTransferTemplate.setIsActive(true);
        ecTransferTemplate.setVersion(version);

        Optional.ofNullable(ecTransferTemplate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(standardTorCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecTransferTemplate.getProponentId(), ecTransferTemplate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecTransferTemplate.getProponentId() + " proposal no: " + ecTransferTemplate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecTransferTemplate.getProponentId() + " proposal no: " + ecTransferTemplate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecTransferTemplate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_transfer" + convertedProposalNo + "_" + ecTransferTemplate.getProponentId(), "pdf");

        //if (ecTransferTemplate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath = FileUploadUtil.generatePdfFromHtml(ecTransferTemplate.getHtmlContent(), folderDir,
                    fileName,ecTransferTemplate.getProposal_No());
        //}
        ecTransferTemplate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecTransferTemplate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);
        return ecTransferTemplateRepository.save(ecTransferTemplate);

    }

//    public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String proposalNo) {
//        log.info("==============================getting EcTransferTemplate detail by ProId===========================");
//        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
//        EcTransferTemplate ecTransferTemplate = ecTransferTemplateRepository
//                .getEcTransferTemplateInfoBytPropId(propId, proposalNo);
//
//        if (!ObjectUtils.isEmpty(ecTransferTemplate)) {
//            BeanUtils.copyProperties(ecTransferTemplate, proposalDetailInfo);
//            return proposalDetailInfo;
//        }
//        return certProposalDetailInfoCommonService.getDetailsFromDTO(proposalDetailInfo, proposalNo, propId);
//    }

    public CertProposalDetailInfo getStandTorDetailByPropId(int proponentApplicationId, String proposalNo) {
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();

        EcTransferTemplate ecTransferTemplateResult = ecTransferTemplateRepository
                .getEcTransferTemplateInfoBytPropId(proponentApplicationId, proposalNo);

        if (!ObjectUtils.isEmpty(ecTransferTemplateResult)) {
            BeanUtils.copyProperties(ecTransferTemplateResult, proposalDetailInfo);
            
            Optional<ProponentApplications> proponentApplications  = proponentApplicationRepository.findById(proponentApplicationId);
            ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
            Optional<EcForm7> ecForm7 = ecForm7Repository.findById(proponentApplication.getProposal_id());
            EcForm7 ecForm7Details = ecForm7.orElseThrow(() -> new PariveshException("No record found in EC Form 7 for this proposal Id: "
                    + proponentApplication.getProposal_id()));
            CommonFormDetail commonFormDetail = ecForm7Details.getCommonFormDetail();
        	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
        	proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
            
        	return proposalDetailInfo;
        }
        
        Optional<ProponentApplications> proponentApplications  = proponentApplicationRepository.findById(proponentApplicationId);
        //Populating using EnvironmentClearanceProjectActivityDetail
        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        Optional<EcForm7> ecForm7 = ecForm7Repository.findById(proponentApplication.getProposal_id());
        EcForm7 ecForm7Details = ecForm7.orElseThrow(() -> new PariveshException("No record found in EC Form 7 for this proposal Id: "
                + proponentApplication.getProposal_id()));
        
        if (ObjectUtils.isEmpty(ecForm7Details.getEc_id()) ) {
            //throw new PariveshException("Invalid parameter : Ec Id must not be null");
        	log.info("Legacy Form 7");
        }
        CommonFormDetail commonFormDetail = ecForm7Details.getCommonFormDetail();
        proposalDetailInfo.setProponentId(proponentApplicationId);

        if (!ObjectUtils.isEmpty(ecForm7Details.getEcForm7ProjectActivity()) ) {
            proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(ecForm7Details.getEcForm7ProjectActivity().getMoef_file_no()));
        }
        
        proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
    	proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
        
        proposalDetailInfo.setCafId(commonFormDetail.getId());
        proposalDetailInfo.setEcId(ecForm7Details.getEc_id());
        proposalDetailInfo.setCompanyname(ecForm7Details.getCompany_name());
        //proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getApplicant_email()));
        proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
        proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
        proposalDetailInfo.setNameOfOrganization(CommonUtils.replaceEmptyWithNA(ecForm7Details.getCompany_name()));

        //Populating using ProjectDetails
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
        if (projectDetails != null) {
            proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));
        }


        //Populating using EnvironmentClearence
        Applications applications = proponentApplication.getApplications();
        String formAbbr = applications.getAbbr();

        String clearanceTypeName = LocalClearance.getClearanceByCat(formAbbr, ecForm7Details.getProject_category());
        //String ProposalFor= commonFormDetail.getProposal_for() != null ? commonFormDetail.getProposal_for() : "";
        //proposalDetailInfo.setClearanceType(CommonUtils.replaceEmptyWithNA(clearanceTypeName) );
        proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name() );
        //EnvironmentClearence environmentClearence = certProposalDetailInfoCommonService.getEnvironmentClearanceByPropId(ecForm7Details.getProposal_no());
        
        proposalDetailInfo.setCategory(CommonUtils.replaceEmptyWithNA(ecForm7Details.getProject_category()));

        String proposalNoParts[] = ecForm7Details.getProposal_no().split("/");
        String authorityCode = proposalNoParts[0];
        if ("SIA".equals(authorityCode)) {
            proposalDetailInfo.setIssuingAuthority("SIAEE");
        } else {
            proposalDetailInfo.setIssuingAuthority("MoEF&CC");
        }
        proposalDetailInfo.setStatusOfImplementationProject("");

        //EnvironmentClearence environmentClearence = ecForm7Details.getEnvironmentClearence();
        //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
        //List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());
        
        List<EcForm7ProjectActivityDetailsDto> projectActivityDetails =  ecForm7ProjectActivityDetailsRepository.findDetailByEcId(ecForm7Details.getId());

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
        
    	if (ecForm7Details.getMajor_activity_id() != null || ecForm7Details.getMajor_sub_activity_id() != null)
    		proposalDetailInfo.setSector(activitySectorRepository.getSectorName(ecForm7Details.getMajor_activity_id(), ecForm7Details.getMajor_sub_activity_id()));

        //Populating using District
        District district = districtRepository.getDistrictByCode(projectDetails.getMain_state(),projectDetails.getMain_district());
        State state = stateRepository.getByStateCode(projectDetails.getMain_state());
        String districtName = district != null ? district.getName() : "";
        String stateName = state != null ? state.getName() : "";
        proposalDetailInfo.setLocationOfProject(CommonUtils.replaceEmptyWithNA(districtName) + ", " + CommonUtils.replaceEmptyWithNA(stateName));

        
        Integer stateId = commonFormDetail.getOrganization_state();
        State stateOrg = stateRepository.getByStateCode(stateId);
        String stateOrgName = stateOrg != null ? stateOrg.getName()+ ", " : "";
        
        Integer districtId = commonFormDetail.getOrganization_district();
        District districtOrg = districtRepository.getDistrictByCode(stateId ,districtId);
        String districtOrgName = districtOrg != null ? districtOrg.getName()+ ", " : "";
        
        String streetOrg = commonFormDetail.getOrganization_street() != null ? commonFormDetail.getOrganization_street()+ ", " : "";
        String cityOrg = commonFormDetail.getOrganization_city() != null ? commonFormDetail.getOrganization_city() + ", ": "";
        String landmarkOrg = commonFormDetail.getOrganization_landmark() != null ? commonFormDetail.getOrganization_landmark()+ ", " : "";
        String pinOrg = commonFormDetail.getOrganization_pincode() != null ? commonFormDetail.getOrganization_pincode() : "";

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
        
        //This needs to be  again
       /* List<String> ListOfRegisteredAddress = new LinkedList<>();
        addStrings(ListOfRegisteredAddress, streetOrg);
        addStrings(ListOfRegisteredAddress, commonFormDetail.getOrganization_city());
        addStrings(ListOfRegisteredAddress, districtOrgName);
        addStrings(ListOfRegisteredAddress, stateOrgName);
        addStrings(ListOfRegisteredAddress, commonFormDetail.getOrganization_landmark());
        addStrings(ListOfRegisteredAddress, commonFormDetail.getOrganization_pincode());

        String registeredAddress = Strings.join(",", ListOfRegisteredAddress);*/
        proposalDetailInfo.setRegisteredAddress(registeredAddress);

       /* proposalDetailInfo.setEcValidity();
        proposalDetailInfo.setEcDate(ecForm7Details.);
        proposalDetailInfo.setStatus_of_implementation(ecForm7Details.gets);*/

        // Transferor and Transferee
        List<String> transfereeRegisteredAddress = new LinkedList<>();
        //addStrings(transfereeRegisteredAddress, ecForm7Details.getCompany_name());
        addStrings(transfereeRegisteredAddress, ecForm7Details.getCompany_house());
        addStrings(transfereeRegisteredAddress, ecForm7Details.getCompany_village());
        addStrings(transfereeRegisteredAddress, ecForm7Details.getCompany_district());
        addStrings(transfereeRegisteredAddress, ecForm7Details.getCompany_state());
        addStrings(transfereeRegisteredAddress, ecForm7Details.getCompany_landmark());
        addStrings(transfereeRegisteredAddress, ecForm7Details.getCompany_pincode());

        List<String> transferorRegisteredAddress = new LinkedList<>();
        addStrings(transferorRegisteredAddress, ecForm7Details.getTransferer_company_house());
        addStrings(transferorRegisteredAddress, ecForm7Details.getTransferer_company_village());
        addStrings(transferorRegisteredAddress, ecForm7Details.getTransferer_company_district());
        addStrings(transferorRegisteredAddress, ecForm7Details.getTransferer_company_state());
        addStrings(transferorRegisteredAddress, ecForm7Details.getTransferer_company_landmark());
        addStrings(transferorRegisteredAddress, ecForm7Details.getTransferer_company_pincode());

        String transfereeRegisteredAddressStr = Strings.join(",", transfereeRegisteredAddress);
        String transferorRegisteredAddressStr = Strings.join(",", transferorRegisteredAddress);
        StringBuilder detailsOfTransferee = new StringBuilder("");
        StringBuilder detailsOfTransferor =new StringBuilder("");
        if (!StringUtils.isEmpty(ecForm7Details.getCompany_name())) {
            detailsOfTransferee.append(ecForm7Details.getCompany_name());
            detailsOfTransferee.append(", ");
        }
        detailsOfTransferee.append(transfereeRegisteredAddressStr);

        if (!StringUtils.isEmpty(ecForm7Details.getTransferer_company_name())) {
            detailsOfTransferor.append(ecForm7Details.getTransferer_company_name());
            detailsOfTransferor.append(", ");
        }
        detailsOfTransferor.append(transferorRegisteredAddressStr);
        proposalDetailInfo.setDetails_of_transferee(detailsOfTransferor.toString());
        proposalDetailInfo.setDetails_of_transferor(detailsOfTransferee.toString());
        //change the detailsOfTransferor to detailsOfTransferee
        String catId = ecForm7Details.getProject_category();

        String folderDir = null;
        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
        String fileName = FileUploadUtil.fileNameMaker("standard_tor_" + convertedProposalNo + "_" + proponentApplicationId, "pdf");

        String clearanceTypeId = proponentApplication.getClearance_id().toString();
        String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
        String proposalId = proponentApplication.getProposal_id().toString();

        folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
        proposalDetailInfo.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setVersion(0);
        proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplication.getMoefccFileNumber()));
        
        if(ecForm7Details.getEc_id() != null) 
        	proposalDetailInfo.setDateOfSubmission(ecForm7Details.getEnvironmentClearence().getCreated_on());
        else
        	proposalDetailInfo.setDateOfSubmission(ecForm7Details.getCreated_on());
        
        return proposalDetailInfo;
    }


    public String getIdentificationNo(int propId) {
        //String sCode = null;
        Integer formCode = null;
        String clearanceTypeCode = null;
        log.info(propId + " This is prop Id ");
        log.info("=====================Identification No generation================================");
        Optional<ProponentApplications> proponentApplications  = proponentApplicationRepository.findById(propId);

        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        int proposalId = proponentApplication.getProposal_id();

        //Populating using EcForm7
       /* Optional<EcForm7> ecForm7 = ecForm7Repository.findById(proposalId);
        EcForm7 ecForm7Detail =  ecForm7.orElseThrow(() -> new PariveshException("Data missing in from 7 for proposalId " + proposalId));
        String proposalNo = ecForm7Detail.getProposal_no();

        Applications applications = proponentApplication.getApplications();
        String formId = applications.getDd_name();
        EnvironmentClearence environmentClearence = environmentClearenceRepository.getById(ecForm7Detail.getEc_id());

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
        String propSectorName = propArray[2];
        String propNo = propArray[3];
        String year = propArray[4];
        String torIdentificationNo = new StringBuilder()
                .append(clearanceTypeCode != null ? clearanceTypeCode : "NA")
                .append(formCode != null ? formCode : "NA")
                .append(propSectorName.trim())
                .append(year.substring(1).trim())
                .append(propNo)
                .toString();
        */
        log.info(String.valueOf( proponentApplications.get().getProposal_no()));
    	String identificationNo = ecTransferTemplateRepository.getIdentificationNo(proponentApplications.get().getProposal_no());
    	log.info(identificationNo);
    	
    	
        return identificationNo;
    }
}
