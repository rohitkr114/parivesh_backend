package com.backend.service.StandardTORCertificate;

import com.backend.clearance.LocalClearance;
import com.backend.constant.AppConstant;
import com.backend.dto.ActivityDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.*;
import com.backend.model.EcForm7.EcForm7;
import com.backend.model.StandardTORCertificate.EcMiningExpansionTemplate;
import com.backend.model.StandardTORCertificate.EcMiningTransferCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm7.EcForm7Repository;
import com.backend.repository.postgres.StandardTorCertificate.EcMiningExpansionTemplateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import com.beust.jcommander.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static com.backend.util.CommonUtils.addStrings;

@Service
@Slf4j
public class EcMiningExpansionTemplateService {

    @Autowired
    EcMiningExpansionTemplateRepository ecMiningExpansionTemplateRepository;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    EcForm7Repository ecForm7Repository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;
    
	@Autowired
    EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

    @Autowired
    Environment environment;

    @Autowired
    StateRepository stateRepository;
    
    @Autowired
    ActivitySubActivitySectorRepository activitySectorRepository;

    public Object saveEcMiningExpansionTemplate(UserPrincipal principal, EcMiningExpansionTemplate ecMiningExpansionTemplate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for Standard tor Certificate========================");
        EcMiningExpansionTemplate ecMiningExpansionTemplateResult = null;
        //Maintaining History
        if (ecMiningExpansionTemplateRepository.existsById(ecMiningExpansionTemplate.getId())) {
            log.info("==================One Record is already exist for Standard tor Certificate========================");
            Optional<EcMiningExpansionTemplate> ecMiningExpansionTemplate1
                    = ecMiningExpansionTemplateRepository.findById(ecMiningExpansionTemplate.getId());
            ecMiningExpansionTemplateResult = ecMiningExpansionTemplate1.get();

            if (ecMiningExpansionTemplateResult.getCreated_by().equals(principal.getId())) {
                ecMiningExpansionTemplate.setIsActive(true);
                ecMiningExpansionTemplate.setId(ecMiningExpansionTemplateResult.getId());
            } else {
                ecMiningExpansionTemplateResult.setIsActive(false);
                ecMiningExpansionTemplateRepository.save(ecMiningExpansionTemplateResult);
                ecMiningExpansionTemplate.setId(null);
                int version = ObjectUtils.isEmpty(ecMiningExpansionTemplate.getVersion()) ? 1 : ecMiningExpansionTemplate.getVersion() + 1;
                ecMiningExpansionTemplate.setIsActive(true);
                ecMiningExpansionTemplate.setVersion(version);
            }
        }
        log.info("==================Saved Standard tor Certificate========================");
        int version = ObjectUtils.isEmpty(ecMiningExpansionTemplate.getVersion()) ? 1 : ecMiningExpansionTemplate.getVersion() + 1;
        ecMiningExpansionTemplate.setIsActive(true);
        ecMiningExpansionTemplate.setVersion(version);

        Optional.ofNullable(ecMiningExpansionTemplate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(standardTorCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecMiningExpansionTemplate.getProponentId(), ecMiningExpansionTemplate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecMiningExpansionTemplate.getProponentId() + " proposal no: " + ecMiningExpansionTemplate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecMiningExpansionTemplate.getProponentId() + " proposal no: " + ecMiningExpansionTemplate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecMiningExpansionTemplate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_mining_expansion" + convertedProposalNo + "_" + ecMiningExpansionTemplate.getProponentId(), "pdf");

        //if (ecMiningExpansionTemplate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath = FileUploadUtil.generatePdfFromHtml(ecMiningExpansionTemplate.getHtmlContent(),
                    folderDir, fileName,ecMiningExpansionTemplate.getProposal_No());
        //}
        ecMiningExpansionTemplate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecMiningExpansionTemplate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);
        return ecMiningExpansionTemplateRepository.save(ecMiningExpansionTemplate);
    }

    public CertProposalDetailInfo getStandTorDetailByPropId(int proponentApplicationId, String proposalNo) {
        log.info("==============================getting EC detail by ProId===========================");
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();

        EcMiningExpansionTemplate ecMiningExpansionTemplateResult = ecMiningExpansionTemplateRepository
                .ecMiningExpansionTemplateInfoBytPropId(proponentApplicationId, proposalNo);

        if (!ObjectUtils.isEmpty(ecMiningExpansionTemplateResult)) {
            BeanUtils.copyProperties(ecMiningExpansionTemplateResult, proposalDetailInfo);
            
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

        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        //Populating using EcForm7
        Optional<EcForm7> ecForm7 = ecForm7Repository.findById(proponentApplication.getProposal_id());
        EcForm7 ecForm7Details = ecForm7.orElseThrow(() -> new PariveshException("No record found in EC Form 7 for this proposal Id: "
                + proponentApplication.getProposal_id()));

        if (ObjectUtils.isEmpty(ecForm7Details.getEc_id()) ) {
            throw new PariveshException("Invalid parameter : Ec Id must not be null");
        }

        CommonFormDetail commonFormDetail = ecForm7Details.getCommonFormDetail();
        
    	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
    	proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
    	
        proposalDetailInfo.setProponentId(proponentApplicationId);
        if (ObjectUtils.isNotEmpty(ecForm7Details.getEcForm7ProjectActivity())) {
            proposalDetailInfo.setFileNo(ecForm7Details.getEcForm7ProjectActivity().getMoef_file_no());
        }
        proposalDetailInfo.setCafId(commonFormDetail.getId());
        proposalDetailInfo.setEcId(ecForm7Details.getEc_id());
        proposalDetailInfo.setCompanyname(ecForm7Details.getCompany_name());
        //proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getApplicant_email()));
        proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
        proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
        if(ObjectUtils.isNotEmpty(commonFormDetail.getCafKML())){
            List<CafKML> cafKML=commonFormDetail.getCafKML();
            proposalDetailInfo.setLatitude_longitude_of__project(cafKML.get(0).getNe_extend()+"   "+cafKML.get(0).getSw_extend());
        }
        if (ObjectUtils.isNotEmpty(commonFormDetail.getCafOthers())) {
            proposalDetailInfo.setRnrInvolved(commonFormDetail.getCafOthers().getRnr_involved());
            proposalDetailInfo.setRnrStatus(commonFormDetail.getCafOthers().getRnr_status());
        }
        if (ObjectUtils.isNotEmpty(commonFormDetail.getCafProjectActivityCost())) {
            proposalDetailInfo.setProjectCost(commonFormDetail.getCafProjectActivityCost().getTotal_cost());
        }
        if (ObjectUtils.isNotEmpty(commonFormDetail.getCafLocationOfKml())) {
            proposalDetailInfo.setTotalNonForestLand(commonFormDetail.getCafLocationOfKml().getTotal_non_forest_land());
            proposalDetailInfo.setTotalLand(String.valueOf(commonFormDetail.getCafLocationOfKml().getTotal_land()));
        }

        proposalDetailInfo.setNameOfOrganization(CommonUtils.replaceEmptyWithNA(ecForm7Details.getCompany_name()));

        //Populating using ProjectDetails
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
        proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));

        //Populating using EnvironmentClearence
        Applications applications = proponentApplication.getApplications();
        String formAbbr = applications.getAbbr();

        String clearanceTypeName = LocalClearance.getClearanceByCat(formAbbr, ecForm7Details.getProject_category());
        //String ProposalFor= commonFormDetail.getProposal_for() != null ? commonFormDetail.getProposal_for() : "";

        //proposalDetailInfo.setClearanceType(CommonUtils.replaceEmptyWithNA(clearanceTypeName) );
        proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name() );
        
         proposalDetailInfo.setCategory(CommonUtils.replaceEmptyWithNA(ecForm7Details.getProject_category()));

        String proposalNoParts[] = ecForm7Details.getProposal_no().split("/");
        String authorityCode = proposalNoParts[0];
        if ("SIA".equals(authorityCode)) {
            proposalDetailInfo.setIssuingAuthority("SIAEE");
        } else {
            proposalDetailInfo.setIssuingAuthority("MoEF&CC");
        }
        proposalDetailInfo.setStatusOfImplementationProject("");


        EnvironmentClearence environmentClearence = ecForm7Details.getEnvironmentClearence();
        //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
        List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());

        
        /**
         * Getting project activity id and name on behalf od sector code.
         */

        List<String> projectIds = new ArrayList<>();
        List<String> projectNames = new ArrayList<>();
        /*projectActivityDetails.stream()
                .forEach(item -> {
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
        
    	if (!(environmentClearence.getMajor_activity_id() == 0 || environmentClearence.getMajor_sub_activity_id() == 0))
    		proposalDetailInfo.setSector(activitySectorRepository.getSectorName(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()));

        //Populating using District
        District district = districtRepository.getDistrictById(projectDetails.getMain_district());
        State state = stateRepository.getByStateCode(projectDetails.getMain_state());
        String districtName = district != null ? district.getName() : "NA";
        String stateName = state != null ? state.getName() : "NA";
        proposalDetailInfo.setLocationOfProject(CommonUtils.replaceEmptyWithNA(districtName) + ", " + CommonUtils.replaceEmptyWithNA(stateName));

        Integer stateId = commonFormDetail.getOrganization_state();
        State stateOrg = stateRepository.getByStateCode(stateId);
        String stateOrgName = stateOrg != null ? stateOrg.getName() + ", " : "";
        
        Integer districtId = commonFormDetail.getOrganization_district();
        District districtOrg = districtRepository.getDistrictByCode(stateId ,districtId);
        String districtOrgName = districtOrg != null ? districtOrg.getName() + ", " : "";
        
        String streetOrg = commonFormDetail.getOrganization_street() != null ? commonFormDetail.getOrganization_street() + ", " : "";
        String cityOrg = commonFormDetail.getOrganization_city() != null ? commonFormDetail.getOrganization_city()+ ", "  : "";
        String landmarkOrg = commonFormDetail.getOrganization_landmark() != null ? commonFormDetail.getOrganization_landmark()+ ", "  : "";
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
        proposalDetailInfo.setRegisteredAddress(registeredAddress);

       /* proposalDetailInfo.setEcValidity();
        proposalDetailInfo.setEcDate(ecForm7Details.);
        proposalDetailInfo.setStatus_of_implementation(ecForm7Details.gets);*/

        // Transferor and Transferee
        List<String> transfereeRegisteredAddress = new LinkedList<>();
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
        StringBuilder detailsOfTransferor = new StringBuilder("");
        if (!StringUtils.isEmpty(ecForm7Details.getCompany_name())) {
            detailsOfTransferee.append(ecForm7Details.getCompany_name()).append(", ");
        }

        if (!StringUtils.isEmpty(ecForm7Details.getTransferer_company_name())) {
            detailsOfTransferor.append(ecForm7Details.getTransferer_company_name()).append(", ");
        }
        detailsOfTransferee.append(transfereeRegisteredAddressStr);
        detailsOfTransferor.append(transferorRegisteredAddressStr);
        proposalDetailInfo.setDetails_of_transferee(detailsOfTransferor.toString());
        proposalDetailInfo.setDetails_of_transferor(detailsOfTransferee.toString());

        // Last Field and Forest Table
        proposalDetailInfo.setAreaExistInHaANonForestLandA(validateDoubleValue(commonFormDetail.getCafLocationOfKml().getTotal_non_forest_land()));
        proposalDetailInfo.setAdditionalAreaExistInHaBNonForestLandA(validateDoubleValue(commonFormDetail.getCafLocationOfKml().getAdditional_non_forest_land()));

        proposalDetailInfo.setAreaExistInHaAForestLandB(validateDoubleValue(environmentClearence.getFc_applied_diversion()));
        proposalDetailInfo.setAdditionalAreaExistInBForestLandB(validateDoubleValue(commonFormDetail.getCafLocationOfKml().getAdditional_forest_land()));

        proposalDetailInfo.setSubTotalAandBForHaA(validateDoubleValue(commonFormDetail.getCafLocationOfKml().getTotal_non_forest_land()) +
                validateDoubleValue(environmentClearence.getFc_applied_diversion()));
        proposalDetailInfo.setSubTotalAandBforHaB(validateDoubleValue((commonFormDetail.getCafLocationOfKml().getAdditional_non_forest_land()) + validateDoubleValue(commonFormDetail.getCafLocationOfKml().getAdditional_forest_land())));

        proposalDetailInfo.setTotalForestLandtotalHaAandB(validateDoubleValue(commonFormDetail.getCafLocationOfKml().getTotal_non_forest_land()) +
                validateDoubleValue(commonFormDetail.getCafLocationOfKml().getAdditional_non_forest_land()));
        proposalDetailInfo.setTotalNonForestLandtotalHaAandB(validateDoubleValue(environmentClearence.getFc_applied_diversion()) +
                validateDoubleValue(commonFormDetail.getCafLocationOfKml().getAdditional_forest_land()));

        proposalDetailInfo.setTotalAandB(validateDoubleValue(proposalDetailInfo.getTotalForestLandtotalHaAandB()) +
                validateDoubleValue(proposalDetailInfo.getTotalNonForestLandtotalHaAandB()));

        proposalDetailInfo.setEmploymentDetailsApplicantName(CommonUtils.replaceEmptyWithNA(commonFormDetail.getApplicant_name()));
        proposalDetailInfo.setEmploymentDetailsApplicantEmail(CommonUtils.replaceEmptyWithNA(commonFormDetail.getApplicant_email()));
        proposalDetailInfo.setProjectCost(validateDoubleValue(commonFormDetail.getCafProjectActivityCost().getTotal_cost()));
        proposalDetailInfo.setEmpCost(validateDoubleValue(commonFormDetail.getCafProjectActivityCost().getTotal_expension_cost()));
        proposalDetailInfo.setRnrInvolved(commonFormDetail.getCafOthers().getRnr_involved());
        //proposalDetailInfo.setForestLandB(environmentClearence.getFc_applied_diversion());

//        if (ObjectUtils.isNotEmpty(commonFormDetail.getCafLocationOfKml())) {
//            proposalDetailInfo.setTotalNonForestLand(commonFormDetail.getCafLocationOfKml().getTotal_non_forest_land());
//            proposalDetailInfo.setTotalLand(commonFormDetail.getCafLocationOfKml().getTotal_land());
//        }

        if(environmentClearence.getProject_category().equals("Application mode to appraisal at Central Level for")){
            proposalDetailInfo.setEac("Expert Appraisal Committee");
        } else if (environmentClearence.getProject_category().equals("B1") || environmentClearence.getProject_category().equals("B2")) {
            proposalDetailInfo.setEac("State Expert Appraisal Committee");
        } else if(environmentClearence.getProject_category().equals("A")){
            proposalDetailInfo.setEac("Expert Appraisal Committee");
        }

        String folderDir = null;
        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
        String fileName = FileUploadUtil.fileNameMaker("ec_mining_expansion" + convertedProposalNo + "_" + proponentApplicationId, "pdf");

        String clearanceTypeId = proponentApplication.getClearance_id().toString();
        String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
        String proposalId = proponentApplication.getProposal_id().toString();

        folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
        proposalDetailInfo.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setVersion(0);
       // proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplication.getMoefccFileNumber()));


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

        //Populating using EcForm8
        Optional<EcForm7> ecForm7 = ecForm7Repository.findById(proposalId);
        EcForm7 ecForm7Detail =  ecForm7.orElseThrow(() -> new PariveshException("Data missing in from 8 for proposalId " + proposalId));
        String proposalNo = ecForm7Detail.getProposal_no();

        Applications applications = proponentApplication.getApplications();
        String formId = applications.getDd_name();
        EnvironmentClearence environmentClearence = environmentClearenceRepository.getById(ecForm7Detail.getEc_id());

        log.info("Form Id: " + formId);
        //Integer majorActivityId = environmentClearence.getMajor_activity_id();
        //Integer subMajorActivityId = environmentClearence.getMajor_sub_activity_id();
        //log.info("Major activity Id: " + majorActivityId);
        log.info("proposal No: " + proposalNo);

       /* if (majorActivityId != null) {
            sCode = activitySectorRepository
                    .getSector(majorActivityId, subMajorActivityId)
                    .getSector_code();
            //log.info("Sector code: " + sectorCode.get());
        }*/

        if (formId != null) {
            formCode = applicationsRepository.getFormCodeByDdName(formId);
            log.info("Form code: " + formCode);
            clearanceTypeCode = applicationsRepository.getClearanceTypeByDdName(formId)
                    .orElse("NA") ;
        }

        /*String[] propArray = proposalNo.split("/");
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
        */
        String identificationNo = ecMiningExpansionTemplateRepository.getIdentificationNo(proponentApplications.get().getProposal_no());
        
        return identificationNo;
    }

    public Double validateDoubleValue (Double value) {
        if (!ObjectUtils.isEmpty(value)) {
            return value;
        }
        return 0.0d;
    }

}
