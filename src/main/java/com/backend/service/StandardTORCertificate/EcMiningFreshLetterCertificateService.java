package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.ActivityDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.*;
import com.backend.model.EcForm7.EcForm7;
import com.backend.model.EcPartC.EcOtherDetails;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearance.EcMiningProposals;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.EnvironmentClearance.MiningMineralsMined;
import com.backend.model.StandardTORCertificate.*;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcPartC.EcOtherDetailsRepository;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPartBRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcMiningFreshLetterCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EcMiningFreshLetterCertificateService {

    @Autowired
    EcMiningFreshLetterCertificateRepository  ecMiningFreshLetterCertificateRepository;

    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

    @Autowired
    Environment environment;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;
    
	 @Autowired
     ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;
    
	@Autowired
    EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

    @Autowired
    EcPartCRepository ecPartCRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    EcPartBRepository ecPartBRepository;

    @Autowired
    EcProdTransportDetailsRepository ecProdTransportDetailsRepository;

    @Autowired
    StateRepository stateRepository;
    
    @Autowired
    EcOtherDetailsRepository ecOtherDetailsRepository;


    @Transactional
    public Object saveEcMiningFreshLetterCertificate(UserPrincipal principal, EcMiningFreshLetterCertificate ecMiningFreshLetterCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for EcMiningFreshLetterCertificate========================");
        EcMiningFreshLetterCertificate ecMiningFreshLetterCertificateResult = null;
        //Maintaining History
        if (ecMiningFreshLetterCertificateRepository.existsById(ecMiningFreshLetterCertificate.getId())) {
            log.info("==================One Record is already exist for EcMiningFreshLetterCertificate========================");
            Optional<EcMiningFreshLetterCertificate> ecMiningFreshLetterCertificate1 = ecMiningFreshLetterCertificateRepository.findById(ecMiningFreshLetterCertificate.getId());
            ecMiningFreshLetterCertificateResult = ecMiningFreshLetterCertificate1.get();

            if (ecMiningFreshLetterCertificateResult.getCreated_by().equals(principal.getId())) {
                ecMiningFreshLetterCertificate.setIsActive(true);
                ecMiningFreshLetterCertificate.setId(ecMiningFreshLetterCertificateResult.getId());
            } else {
                ecMiningFreshLetterCertificateResult.setIsActive(false);
                ecMiningFreshLetterCertificateRepository.save(ecMiningFreshLetterCertificateResult);
                ecMiningFreshLetterCertificate.setId(null);
                int version = ObjectUtils.isEmpty(ecMiningFreshLetterCertificate.getVersion()) ? 1 : ecMiningFreshLetterCertificate.getVersion() + 1;
                ecMiningFreshLetterCertificate.setIsActive(true);
                ecMiningFreshLetterCertificate.setVersion(version);
            }
        }
        log.info("==================Saved EcMiningFreshLetterCertificate========================");
        int version = ObjectUtils.isEmpty(ecMiningFreshLetterCertificate.getVersion()) ? 1 : ecMiningFreshLetterCertificate.getVersion() + 1;
        ecMiningFreshLetterCertificate.setIsActive(true);
        ecMiningFreshLetterCertificate.setVersion(version);

        Optional.ofNullable(ecMiningFreshLetterCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecMiningFreshLetterCertificate.getProponentId(), ecMiningFreshLetterCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecMiningFreshLetterCertificate.getProponentId() + " proposal no: " + ecMiningFreshLetterCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecMiningFreshLetterCertificate.getProponentId() + " proposal no: " + ecMiningFreshLetterCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecMiningFreshLetterCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_mining_fresh_letter" + convertedProposalNo + "_" + ecMiningFreshLetterCertificate.getProponentId(), "pdf");

        //if (ecMiningFreshLetterCertificate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath = FileUploadUtil.generatePdfFromHtml(ecMiningFreshLetterCertificate.getHtmlContent(),
                    folderDir, fileName,ecMiningFreshLetterCertificate.getProposal_No());
        //}
        ecMiningFreshLetterCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecMiningFreshLetterCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);

        return ecMiningFreshLetterCertificateRepository.save(ecMiningFreshLetterCertificate);
    }

    @Transactional
    public CertProposalDetailInfo getStandTorDetailByPropId(int proponentApplicationId, String proposalNo) {
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();

        EcMiningFreshLetterCertificate ecMiningFreshLetterCertificateResult = ecMiningFreshLetterCertificateRepository
                .getEcMiningFreshLetterCertificateInfoBytPropId(proponentApplicationId, proposalNo);

        if (!ObjectUtils.isEmpty(ecMiningFreshLetterCertificateResult)) {
            BeanUtils.copyProperties(ecMiningFreshLetterCertificateResult, proposalDetailInfo);
            
            ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(proponentApplicationId, proposalNo).get();
            if (proponentApplications != null) {
            	
            	CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
            	proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
            	//tempfix
                addDetailsOfTheProject(proposalDetailInfo, commonFormDetail);
            }
            
            //tempfix
            addDetailsOfProducts(proposalDetailInfo);
            //tempfix
            addEmpCost(proposalDetailInfo, proponentApplications.getProposal_id());
            
            return proposalDetailInfo;
        }
        String proposalNoParts[] = proposalNo.split("/");
        String authorityCode = proposalNoParts[0];
        if ("SIA".equals(authorityCode)) {
            proposalDetailInfo.setIssuingAuthority("SIAEE");
        } else {
            proposalDetailInfo.setIssuingAuthority("MoEF&CC");
        }

        Optional<ProponentApplications> proponentApplications  = proponentApplicationRepository.findById(proponentApplicationId);

        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        proposalDetailInfo.setFileNo(proponentApplication.getMoefccFileNumber());
        proposalDetailInfo.setDateOfSubmission(proponentApplication.getLast_submission_date());
        //Populating using CommonFormDetail
        CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplication.getCaf_id());
        proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
        proposalDetailInfo.setCompanyname(CommonUtils.handleEmpty(commonFormDetail.getOrganization_name()));
        //proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getApplicant_email()));
        proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
        proposalDetailInfo.setCafId(proponentApplication.getCaf_id());
        proposalDetailInfo.setProponentId(proponentApplicationId);
    	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());
        proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
        //String ProposalFor= commonFormDetail.getProposal_for() != null ? commonFormDetail.getProposal_for() : "";
        proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name() );
        
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
        	 proposalDetailInfo
             .setTotalNonForestLand(CommonUtils.updateIfEmpty(proposalDetailInfo.getTotalNonForestLand(),commonFormDetail.getCafLocationOfKml().getExisting_non_forest_land()));
        	 proposalDetailInfo.setTotalLand(CommonUtils.updateIfEmpty( proposalDetailInfo.getTotalLand(),String.valueOf(commonFormDetail.getCafLocationOfKml().getExisting_total_land())));
        	 proposalDetailInfo.setForestLandB(CommonUtils.updateIfEmpty(proposalDetailInfo.getForestLandB(),commonFormDetail.getCafLocationOfKml().getExisting_forest_land()));

        }


        //Populating using ProjectDetails
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
        proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));

        //Populating using EcForm9
        Optional<EcPartC> ecPartC = ecPartCRepository.getFormById(proponentApplication.getProposal_id());
        EcPartC ecPartCs = ecPartC.orElseThrow(() -> new PariveshException("No record found in EC Form 9 for this proposal Id: "
                + proponentApplication.getProposal_id()));

        //  Condition for Ec_id
        if (ObjectUtils.isEmpty(ecPartCs.getEc_id())) {
            throw new PariveshException("Invalid parameter : Ec Id must not be null");
        }
        
        //set EmpCost data
        addEmpCost(proposalDetailInfo, proponentApplication.getProposal_id());

        //Populating using EnvironmentClearence
        EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecPartCs.getEc_id());
        proposalDetailInfo.setCategory(environmentClearence.getProject_category());
        proposalDetailInfo.setForestLandB(environmentClearence.getFc_applied_diversion());
        proposalDetailInfo.setProposal_No(environmentClearence.getProposal_no());
        proposalDetailInfo.setEcId(ecPartCs.getEc_id());
        proposalDetailInfo.setEcDate(String.valueOf(ecPartCs.getDate_of_issue_tor()));
        //Populating using EnvironmentClearanceProjectActivityDetails
        //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
        List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());

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
        proposalDetailInfo.setProjectID(projectDetails.getId());
        
    	if (environmentClearence.getMajor_activity_id() != null || environmentClearence.getMajor_sub_activity_id() !=null )
    		proposalDetailInfo.setSector(activitySectorRepository.getSectorName(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()));

        //Populating using District
        District district = districtRepository.getDistrictByCode(projectDetails.getMain_state(),projectDetails.getMain_district());
        State state = stateRepository.getByStateCode(projectDetails.getMain_state());
        String districtName = district != null ? district.getName() : "NA";
        String stateName = state != null ? state.getName() : "NA";
        
        Integer stateId = commonFormDetail.getOrganization_state();
        State stateOrg = stateRepository.getByStateCode(stateId);
        String stateOrgName = stateOrg != null ? stateOrg.getName()+ ", " : "";
        
        Integer districtId = commonFormDetail.getOrganization_district();
        District districtOrg = districtRepository.getDistrictByCode(stateId ,districtId);
        String districtOrgName = districtOrg != null ? districtOrg.getName()+ ", " : "";
        
        String streetOrg = commonFormDetail.getOrganization_street() != null ? commonFormDetail.getOrganization_street() + ", ": "";
        String cityOrg = commonFormDetail.getOrganization_city() != null ? commonFormDetail.getOrganization_city()+ ", " : "";
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
        proposalDetailInfo.setRegisteredAddress(registeredAddress);
        proposalDetailInfo.setNameOfOrganization(commonFormDetail.getOrganization_name());
        proposalDetailInfo.setLocationOfProject(districtName + ", " + stateName);

        EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));
        Set<MiningMineralsMined> miningMineralsMines=  ecPartB.getEcMiningProposals().getMiningMineralsMineds();
        Set<EcMiningFreshLetterCertificateDetailOfMinerals> detailOfMineralsArray = new HashSet<>();
        miningMineralsMines.stream().forEach(item->{
            EcMiningFreshLetterCertificateDetailOfMinerals detailOfMinerals = new EcMiningFreshLetterCertificateDetailOfMinerals();
            if(ObjectUtils.isNotEmpty(item)){
                detailOfMinerals.setName_of_mineral(item.getMineral_name());
                detailOfMinerals.setClassification_on_mineral(item.getMineral_classification());
                detailOfMinerals.setProduction_on_capacity(String.valueOf(item.getProduction_capacity()));
                detailOfMinerals.setMode_of_transport(item.getRemarks());
            }
            detailOfMineralsArray.add(detailOfMinerals);
        });

        proposalDetailInfo.setDetailOfMineralsArray(detailOfMineralsArray);
        //Plant information
//        List<StandardTorCertificatePlantEquipment> listOfEcIndustryProposals = new ArrayList<>();
//        StandardTorCertificatePlantEquipment standardTorCertificatePlantEquipment = new StandardTorCertificatePlantEquipment();
//        if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
//            standardTorCertificatePlantEquipment.setPlant_equipment(ecPartB.getEcIndustryProposal().getPlant());
//            standardTorCertificatePlantEquipment.setConfiguration(ecPartB.getEcIndustryProposal().getFinal_configuration());
//            standardTorCertificatePlantEquipment.setRemarks(ecPartB.getEcIndustryProposal().getRemarks());
//        }
//        listOfEcIndustryProposals.add(standardTorCertificatePlantEquipment);
//        List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository.findDetailByEcId(environmentClearence.getId());
//        List<StandardTorCertificateDetailOfProduct> productList = new ArrayList<>();
//        ecProdTransportDetails.stream()
//                .forEach(item -> {
//                    StandardTorCertificateDetailOfProduct product = new StandardTorCertificateDetailOfProduct();
//                    if (ObjectUtils.isNotEmpty(item)) {
//                        product.setByProduct(item.getProduct_by());
//                        product.setName_of_the_product(item.getProduct_name());
//                        product.setQuantity(item.getQuantity());
//                        product.setMode_of_transport(item.getTransport_mode());
//                        product.setUnit(item.getUnit());
//                        product.setRemarks(item.getRemarks());
//                    }
//                    productList.add(product);
//                });
//        proposalDetailInfo.setPlantEquipmentArray(listOfEcIndustryProposals);
//        proposalDetailInfo.setDetailsOfProducts(productList);
        
        // Plant information
        List<StandardTorCertificatePlantEquipment> listOfEcIndustryProposals = new ArrayList<>();
        StandardTorCertificatePlantEquipment standardTorCertificatePlantEquipment = new StandardTorCertificatePlantEquipment();
        if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
            standardTorCertificatePlantEquipment.setPlant_equipment(ecPartB.getEcIndustryProposal().getPlant());
            standardTorCertificatePlantEquipment
                    .setConfiguration(ecPartB.getEcIndustryProposal().getFinal_configuration());
            standardTorCertificatePlantEquipment.setRemarks(ecPartB.getEcIndustryProposal().getRemarks());
        }

        //need of project added
        proposalDetailInfo.setNeed_of_project(ecPartB.getNeed_of_project());
        listOfEcIndustryProposals.add(standardTorCertificatePlantEquipment);
        proposalDetailInfo.setPlantEquipmentArray(listOfEcIndustryProposals);
        //details of products assign value for DetailsOfProducts and EcFreshLetterCertificateDetailOfProduct
        addDetailsOfProducts(proposalDetailInfo);

        String folderDir = null;
        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
        String fileName = FileUploadUtil.fileNameMaker("ec_mining_fresh_letter" + convertedProposalNo + "_" + proponentApplicationId, "pdf");

        String clearanceTypeId = proponentApplication.getClearance_id().toString();
        String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
        String proposalId = proponentApplication.getProposal_id().toString();

        folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
        proposalDetailInfo.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proposalDetailInfo.setVersion(0);
        proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplication.getMoefccFileNumber()));


        BeanUtils.copyProperties(ecPartCs, proposalDetailInfo);
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
        Optional<ProponentApplications> proponentApplications  = proponentApplicationRepository.findById(propId);

        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        int proposalId = proponentApplication.getProposal_id();

        //Populating using EcFormC
        Optional<EcPartC> ecPartC = ecPartCRepository.getFormById(proponentApplication.getProposal_id());
        EcPartC ecPartCDetail =  ecPartC.orElseThrow(() -> new PariveshException("Data missing in from 8 for proposalId " + proposalId));
        String proposalNo = ecPartCDetail.getProposal_no();

        Applications applications = proponentApplication.getApplications();
        String formId = applications.getDd_name();
        EnvironmentClearence environmentClearence = environmentClearenceRepository.getById(ecPartCDetail.getEc_id());

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

       /* String[] propArray = proposalNo.split("/");
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
        
        String identificationNo = ecMiningFreshLetterCertificateRepository.getIdentificationNo(proponentApplications.get().getProposal_no());
        log.info(identificationNo);
        
        return identificationNo;
    }

    public Double validateDoubleValue (Double value) {
        if (!ObjectUtils.isEmpty(value)) {
            return value;
        }
        return 0.0d;
    }
    
    public void addDetailsOfProducts(CertProposalDetailInfo proposalDetailInfo) {

        log.info("addDetailsOfProducts:DetailsOfProducts:{}, ecId:{}",
                proposalDetailInfo.getDetailsOfProducts() == null ? null
                        : proposalDetailInfo.getDetailsOfProducts().size(),
                proposalDetailInfo.getEcId());
        // Populating using EnvironmentClearence
        if (proposalDetailInfo.getDetailsOfProducts() == null || proposalDetailInfo.getDetailsOfProducts().isEmpty()
                || proposalDetailInfo.getEcFreshLetterCertificateDetailOfProduct().isEmpty()) {
            EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalDetailInfo.getEcId());

            List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository
                    .findDetailByEcId(environmentClearence.getId());
            List<StandardTorCertificateDetailOfProduct> productList = new ArrayList<>();
            List<EcFreshLetterCertificateDetailOfProduct> ecProductList = new ArrayList<>();

            ecProdTransportDetails.stream().forEach(item -> {
                StandardTorCertificateDetailOfProduct product = new StandardTorCertificateDetailOfProduct();
                EcFreshLetterCertificateDetailOfProduct product2 = new EcFreshLetterCertificateDetailOfProduct();
                if (ObjectUtils.isNotEmpty(item)) {
                    product.setId(item.getProduct_id());
                    product.setByProduct(item.getProduct_by());
                    product.setName_of_the_product(item.getProduct_name());
                    product.setQuantity(item.getQuantity());
                    product.setProposed_quantity(item.getProposed_quantity());
                    product.setQuantity_total(item.getQuantity_total());                
                    product.setMode_of_transport(item.getTransport_mode());
                    product.setUnit(item.getUnit());
                    product.setRemarks(item.getRemarks());
                    // EcFreshLetterCertificateDetailOfProduct
                    product2.setId(item.getProduct_id());
                    product2.setByProduct(item.getProduct_by());
                    product2.setName_of_the_product(item.getProduct_name());
                    product2.setQuantity(item.getQuantity() == null ? null : String.valueOf(item.getQuantity()));
                    product2.setMode_of_transport(item.getTransport_mode());
                    product2.setUnit(item.getUnit());
                    product2.setRemarks(item.getRemarks());
                }
                productList.add(product);
                ecProductList.add(product2);
            });
            proposalDetailInfo.setDetailsOfProducts(productList);
            proposalDetailInfo
                    .setEcFreshLetterCertificateDetailOfProduct(ecProductList.stream().collect(Collectors.toSet()));
        }

    }

    public void addDetailsOfTheProject(CertProposalDetailInfo proposalDetailInfo, CommonFormDetail commonFormDetail) {
        if (ObjectUtils.isNotEmpty(commonFormDetail.getCafKML())) {
            List<CafKML> cafKML = commonFormDetail.getCafKML();
            proposalDetailInfo.setLatitude_longitude_of__project(
                    cafKML.get(0).getNe_extend() + "   " + cafKML.get(0).getSw_extend());
        }
        if (ObjectUtils.isNotEmpty(commonFormDetail.getCafOthers())) {
        	         
        	proposalDetailInfo.setRnrInvolved(CommonUtils.updateIfEmpty(proposalDetailInfo.getRnrInvolved(), commonFormDetail.getCafOthers().getRnr_involved())  );
            proposalDetailInfo.setRnrStatus(CommonUtils.updateIfEmpty(proposalDetailInfo.getRnrStatus(),commonFormDetail.getCafOthers().getRnr_status()));
        }
        if (ObjectUtils.isNotEmpty(commonFormDetail.getCafProjectActivityCost())) {
            proposalDetailInfo.setProjectCost(commonFormDetail.getCafProjectActivityCost().getTotal_cost());
        }
        if (ObjectUtils.isNotEmpty(commonFormDetail.getCafLocationOfKml())) {
        	
            proposalDetailInfo
                    .setTotalNonForestLand(CommonUtils.updateIfEmpty(proposalDetailInfo.getTotalNonForestLand(),commonFormDetail.getCafLocationOfKml().getExisting_non_forest_land()));
            proposalDetailInfo.setTotalLand(CommonUtils.updateIfEmpty( proposalDetailInfo.getTotalLand(),String.valueOf(commonFormDetail.getCafLocationOfKml().getExisting_total_land())));
            proposalDetailInfo.setForestLandB(CommonUtils.updateIfEmpty(proposalDetailInfo.getForestLandB(),commonFormDetail.getCafLocationOfKml().getExisting_forest_land()));
        }
    }

    public void addEmpCost(CertProposalDetailInfo proposalDetailInfo, Integer ecId) {
        log.info("addEmpCost: ecId:{}, EmpCost:{} ", ecId, proposalDetailInfo.getEmpCost());
        Optional<EcOtherDetails> otherDetails = ecOtherDetailsRepository.getDataByEcId(ecId);
        otherDetails.ifPresent(otherDetail -> {
            proposalDetailInfo.setEmpCost(otherDetail.getEnvironment_mgmt_funds());
            proposalDetailInfo.setEmploymentDetails(null);
        });
    }
}
