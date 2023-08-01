package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.ActivityDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.*;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10ProjectDetails;
import com.backend.model.EcForm7.EcForm7;
import com.backend.model.EcForm8TransferOfTOR.EcForm8TransferOfTOR;
import com.backend.model.EcForm9TransferOfEC.EcForm9TransferOfEC;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.StandardTORCertificate.EcAllSectorRejectionLetterCertificate;
import com.backend.model.StandardTORCertificate.EcAllSectorRejectionLetterCertificateDetailOfProduct;
import com.backend.model.StandardTORCertificate.EcAllSectorRejectionLetterCertificatePlantEquipments;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcFactsheet.EcFactsheetRepository;
import com.backend.repository.postgres.EcForm10Repository.EcForm10ProjectDetailRepository;
import com.backend.repository.postgres.EcForm7.EcForm7Repository;
import com.backend.repository.postgres.EcForm8.EcForm8TransferOfTORRepository;
import com.backend.repository.postgres.EcForm9Repository.EcForm9TransferOfECRepository;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPartBRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcAllSectorRejectionLetterCertificateRepository;
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

import static com.backend.util.CommonUtils.handleEmpty;

@Service
@Slf4j
public class EcAllSectorRejectionLetterCertificateService {

    @Autowired
    EcAllSectorRejectionLetterCertificateRepository ecAllSectorRejectionLetterCertificateRepository;

    @Autowired
    Environment environment;
    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    ForestClearanceRepository forestClearanceRepository;

    @Autowired
    EcForm8TransferOfTORRepository ecForm8TransferOfTORRepository;

    @Autowired
    EcForm9TransferOfECRepository ecForm9TransferOfECRepository;

    @Autowired
    EcForm10ProjectDetailRepository ecForm10ProjectDetailRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;


    @Autowired
    ActivityRepository activityRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    EcPartBRepository ecPartBRepository;

    @Autowired
    EcProdTransportDetailsRepository ecProdTransportDetailsRepository;

    @Autowired
    EcForm7Repository ecForm7Repository;

    @Autowired
    EcPartCRepository ecPartCRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    EcFactsheetRepository ecFactsheetRepository;

    @Autowired
    ActivitySubActivitySectorRepository activitySectorRepository;


    public Object saveEcAllSectorRejectionLetterCertificate(UserPrincipal principal, EcAllSectorRejectionLetterCertificate ecAllSectorRejectionLetterCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for Standard tor Certificate========================");
        EcAllSectorRejectionLetterCertificate ecAllSectorRejectionLetterCertificateResult = null;
        //Maintaining History
        if (ecAllSectorRejectionLetterCertificateRepository.existsById(ecAllSectorRejectionLetterCertificate.getId())) {
            log.info("==================One Record is already exist for Standard tor Certificate========================");
            Optional<EcAllSectorRejectionLetterCertificate> ecAllSectorRejectionLetterCertificate1
                    = ecAllSectorRejectionLetterCertificateRepository.findById(ecAllSectorRejectionLetterCertificate.getId());
            ecAllSectorRejectionLetterCertificateResult = ecAllSectorRejectionLetterCertificate1.get();
            if (ecAllSectorRejectionLetterCertificateResult.getCreated_by().equals(principal.getId())) {
                ecAllSectorRejectionLetterCertificate.setIsActive(true);
                ecAllSectorRejectionLetterCertificate.setId(ecAllSectorRejectionLetterCertificateResult.getId());
            } else {
                ecAllSectorRejectionLetterCertificateResult.setIsActive(false);
                ecAllSectorRejectionLetterCertificateRepository.save(ecAllSectorRejectionLetterCertificateResult);
                ecAllSectorRejectionLetterCertificate.setId(null);
                int version = ObjectUtils.isEmpty(ecAllSectorRejectionLetterCertificate.getVersion()) ? 1 : ecAllSectorRejectionLetterCertificate.getVersion() + 1;
                ecAllSectorRejectionLetterCertificate.setIsActive(true);
                ecAllSectorRejectionLetterCertificate.setVersion(version);
            }
        }
        log.info("==================Saved Standard tor Certificate========================");
        int version = ObjectUtils.isEmpty(ecAllSectorRejectionLetterCertificate.getVersion()) ? 1 : ecAllSectorRejectionLetterCertificate.getVersion() + 1;
        ecAllSectorRejectionLetterCertificate.setIsActive(true);
        ecAllSectorRejectionLetterCertificate.setVersion(version);

        Optional.ofNullable(ecAllSectorRejectionLetterCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecAllSectorRejectionLetterCertificate.getProponentId(), ecAllSectorRejectionLetterCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecAllSectorRejectionLetterCertificate.getProponentId() + " proposal no: " + ecAllSectorRejectionLetterCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecAllSectorRejectionLetterCertificate.getProponentId() + " proposal no: " + ecAllSectorRejectionLetterCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecAllSectorRejectionLetterCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_all_sector_rejection" + convertedProposalNo + "_" + ecAllSectorRejectionLetterCertificate.getProponentId(), "pdf");

        if (ecAllSectorRejectionLetterCertificate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath =
                    FileUploadUtil.generatePdfFromHtml(ecAllSectorRejectionLetterCertificate.getHtmlContent(),
                            folderDir, fileName, ecAllSectorRejectionLetterCertificate.getProposal_No());
        }
        ecAllSectorRejectionLetterCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecAllSectorRejectionLetterCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);


        return ecAllSectorRejectionLetterCertificateRepository.save(ecAllSectorRejectionLetterCertificate);
    }

    public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String proposalNo) {
        log.info("==============================getting EC detail by ProId===========================");
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
        EcAllSectorRejectionLetterCertificate ecAllSectorRejectionLetterCertificate = ecAllSectorRejectionLetterCertificateRepository
                .ecAllSectorRejectionLetterCertificateInfoBytPropId(propId, proposalNo);

        if (!ObjectUtils.isEmpty(ecAllSectorRejectionLetterCertificate)) {
            BeanUtils.copyProperties(ecAllSectorRejectionLetterCertificate, proposalDetailInfo);

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

        Optional<ProponentApplications> proponentApplications = proponentApplicationRepository.findById(propId);

        //Populating using ProponentApplications
        ProponentApplications proponentApplication = proponentApplications.orElseThrow(() -> new PariveshException("proponent not found"));
        proposalDetailInfo.setFileNo(proponentApplication.getMoefccFileNumber());

        //Populating using CommonFormDetail
        CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplication.getCaf_id());
        proposalDetailInfo.setProponent(CommonUtils.handleEmpty(commonFormDetail.getApplicant_name()));
        proposalDetailInfo.setCompanyname(CommonUtils.handleEmpty(commonFormDetail.getOrganization_name()));
        //proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getApplicant_email()));
        proposalDetailInfo.setProponentEmail(CommonUtils.handleEmpty(commonFormDetail.getOrganization_email()));
        proposalDetailInfo.setCafId(proponentApplication.getCaf_id());
        proposalDetailInfo.setProponentId(propId);
        proposalDetailInfo.setProjectCost(commonFormDetail.getCafProjectActivityCost().getTotal_cost());
        proposalDetailInfo.setTotalNonForestLand(commonFormDetail.getCafLocationOfKml().getTotal_non_forest_land());
        proposalDetailInfo.setTotalLand(String.valueOf(commonFormDetail.getCafLocationOfKml().getTotal_land()));
        proposalDetailInfo.setProjectCost(commonFormDetail.getCafProjectActivityCost().getTotal_cost());
        proposalDetailInfo.setRnrInvolved(commonFormDetail.getCafOthers().getRnr_involved());
        proposalDetailInfo.setRnrStatus(commonFormDetail.getCafOthers().getRnr_status());
        proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
        proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

        proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplication.getClearance_id()).getGeneral_name());


        List<EcForm10ProjectDetails> ecForm10ProjectDetailsList = ecForm10ProjectDetailRepository.getEcForm10ProjectDetailsByCafId(commonFormDetail.getId());
        if (ObjectUtils.isNotEmpty(ecForm10ProjectDetailsList)) {
            proposalDetailInfo.setEmploymentDetails(ecForm10ProjectDetailsList.get(0).getApplicant_name() + " " + ecForm10ProjectDetailsList.get(0).getApplicant_email() + " " + ecForm10ProjectDetailsList.get(0).getApplicant_state());
        }

        //Populating using ProjectDetails
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
        proposalDetailInfo.setNameOfProject(CommonUtils.replaceEmptyWithNA(projectDetails.getName()));
        proposalDetailInfo.setProponentId(propId);
        proposalDetailInfo.setProposal_No(proposalNo);

        //Populating using District

        State state = stateRepository.getByStateCode(projectDetails.getMain_state());
        String stateName = state != null ? state.getName() + ", " : "";
        District district = districtRepository.getDistrictByCode(projectDetails.getMain_state(), projectDetails.getMain_district());
        String districtName = district != null ? district.getName() + ", " : "";

        Integer stateId = commonFormDetail.getOrganization_state();
        State stateOrg = stateRepository.getByStateCode(stateId);
        String stateOrgName = stateOrg != null ? stateOrg.getName() + ", " : "";

        Integer districtId = commonFormDetail.getOrganization_district();
        District districtOrg = districtRepository.getDistrictByCode(stateId, districtId);
        String districtOrgName = districtOrg != null ? districtOrg.getName() + ", " : "";


        String OrganizationStreet = commonFormDetail.getOrganization_street() != null ? commonFormDetail.getOrganization_street() + ", " : "";
        String OrganizationCity = commonFormDetail.getOrganization_city() != null ? commonFormDetail.getOrganization_city() + ", " : "";
        String OrganizationLandmark = commonFormDetail.getOrganization_landmark() != null ? commonFormDetail.getOrganization_landmark() + ", " : "";
        String OrganizationPincode = commonFormDetail.getOrganization_pincode() != null ? commonFormDetail.getOrganization_pincode() : "";

        String registeredAddress = null;
        if (OrganizationCity != null) {
            registeredAddress = OrganizationStreet + OrganizationCity
                    + districtOrgName + stateOrgName
                    + OrganizationLandmark
                    + OrganizationPincode;
        } else {
            registeredAddress = OrganizationStreet + districtOrgName + stateOrgName
                    + OrganizationLandmark + OrganizationPincode;
        }
        proposalDetailInfo.setRegisteredAddress(registeredAddress);
        proposalDetailInfo.setNameOfOrganization(commonFormDetail.getOrganization_name());
        proposalDetailInfo.setLocationOfProject(districtName + ", " + stateName);
        proposalDetailInfo.setTorIdentificationNumber(getIdentificationNo(propId, proposalNo));


        Applications application = applicationsRepository.findById(proponentApplications.get().getApplications().getId())
                .get();

        switch (application.getId()) {

            case 39: {
                EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
                        .findById(proponentApplication.getProposal_id()).get();
                EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecForm8TransferOfTOR.getEnvironmentClearence().getId());
                proposalDetailInfo.setCategory(environmentClearence.getProject_category());
                proposalDetailInfo.setEcId(environmentClearence.getId());
                proposalDetailInfo.setForestLandB(environmentClearence.getFc_applied_diversion());

                EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                        .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));

                //Plant information
                List<EcAllSectorRejectionLetterCertificatePlantEquipments> listOfEcIndustryProposals = new ArrayList<>();
                EcAllSectorRejectionLetterCertificatePlantEquipments ecAllSectorRejectionLetterCertificatePlantEquipments = new EcAllSectorRejectionLetterCertificatePlantEquipments();
                if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setPlant(ecPartB.getEcIndustryProposal().getPlant());
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setConfiguration(ecPartB.getEcIndustryProposal().getFinal_configuration());
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setRemarksIfAny(ecPartB.getEcIndustryProposal().getRemarks());
                }
                listOfEcIndustryProposals.add(ecAllSectorRejectionLetterCertificatePlantEquipments);


                List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository.findDetailByEcId(environmentClearence.getId());

                List<EcAllSectorRejectionLetterCertificateDetailOfProduct> ecAllSectorRejectionLetterCertificateDetailOfProducts = new ArrayList<>();

                ecProdTransportDetails.stream()
                        .forEach(item -> {

                            if (ObjectUtils.isNotEmpty(item)) {
                                EcAllSectorRejectionLetterCertificateDetailOfProduct product = new EcAllSectorRejectionLetterCertificateDetailOfProduct(handleEmpty(item.getProduct_name()), handleEmpty(item.getProduct_by()),
                                        item.getQuantity(), item.getUnit(), item.getTransport_mode(), item.getRemarks());
                                ecAllSectorRejectionLetterCertificateDetailOfProducts.add(product);
                            }
                        });
                proposalDetailInfo.setDetailsOfReuseRecycleOfWasteWater(ecPartB.getEcPollutionDetails().getRecycle_of_waste_water());
                proposalDetailInfo.setDetailOfProposedCSTPETP(ecPartB.getEcPollutionDetails().getOffsite_treatment_plant_particulars());
                proposalDetailInfo.setCategorySafeSemiCriticalOverExploited(ecPartB.getEcWaterDetails().getGround_Water_Availability_Description());
                proposalDetailInfo.setMeasuresToRechargeGroundWater(ecPartB.getEcWaterDetails().getIs_Water_Conservation());
                proposalDetailInfo.setWaterRequirement(ecPartB.getEcConstructionDetail().isWater_requirement());
                proposalDetailInfo.setEmpCost(ecFactsheetRepository.getEmpCostByEcId(environmentClearence.getId()));


                //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
                List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());


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
                if (!(environmentClearence.getMajor_activity_id() == 0 || environmentClearence.getMajor_sub_activity_id() == 0))
                    proposalDetailInfo.setSector(activitySectorRepository.getSectorName(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()));
                break;
            }

            case 44: {
                EcForm9TransferOfEC ecForm9TransferOfEC = ecForm9TransferOfECRepository
                        .findById(proponentApplication.getProposal_id()).get();


                //EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecForm9TransferOfEC.getEnvironmentClearence().getId());
                EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecForm9TransferOfEC.getId());
                proposalDetailInfo.setCategory(environmentClearence.getProject_category());
                proposalDetailInfo.setEcId(environmentClearence.getId());
                proposalDetailInfo.setForestLandB(environmentClearence.getFc_applied_diversion());

                EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                        .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));

                //Plant information
                List<EcAllSectorRejectionLetterCertificatePlantEquipments> listOfEcIndustryProposals = new ArrayList<>();
                EcAllSectorRejectionLetterCertificatePlantEquipments ecAllSectorRejectionLetterCertificatePlantEquipments = new EcAllSectorRejectionLetterCertificatePlantEquipments();
                if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setPlant(ecPartB.getEcIndustryProposal().getPlant());
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setConfiguration(ecPartB.getEcIndustryProposal().getFinal_configuration());
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setRemarksIfAny(ecPartB.getEcIndustryProposal().getRemarks());
                }
                listOfEcIndustryProposals.add(ecAllSectorRejectionLetterCertificatePlantEquipments);


                List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository.findDetailByEcId(environmentClearence.getId());

                List<EcAllSectorRejectionLetterCertificateDetailOfProduct> ecAllSectorRejectionLetterCertificateDetailOfProducts = new ArrayList<>();

                ecProdTransportDetails.stream()
                        .forEach(item -> {

                            if (ObjectUtils.isNotEmpty(item)) {
                                EcAllSectorRejectionLetterCertificateDetailOfProduct product = new EcAllSectorRejectionLetterCertificateDetailOfProduct(handleEmpty(item.getProduct_name()), handleEmpty(item.getProduct_by()),
                                        item.getQuantity(), item.getUnit(), item.getTransport_mode(), item.getRemarks());
                                ecAllSectorRejectionLetterCertificateDetailOfProducts.add(product);
                            }
                        });
                proposalDetailInfo.setDetailsOfReuseRecycleOfWasteWater(ecPartB.getEcPollutionDetails().getRecycle_of_waste_water());
                proposalDetailInfo.setDetailOfProposedCSTPETP(ecPartB.getEcPollutionDetails().getOffsite_treatment_plant_particulars());
                proposalDetailInfo.setCategorySafeSemiCriticalOverExploited(ecPartB.getEcWaterDetails().getGround_Water_Availability_Description());
                proposalDetailInfo.setMeasuresToRechargeGroundWater(ecPartB.getEcWaterDetails().getIs_Water_Conservation());
                proposalDetailInfo.setWaterRequirement(ecPartB.getEcConstructionDetail().isWater_requirement());
                proposalDetailInfo.setEmpCost(ecFactsheetRepository.getEmpCostByEcId(environmentClearence.getId()));


                //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
                List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());

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
                if (!(environmentClearence.getMajor_activity_id() == 0 || environmentClearence.getMajor_sub_activity_id() == 0))
                    proposalDetailInfo.setSector(activitySectorRepository.getSector(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()).getSector_code());
                break;
            }
            case 38: {
                EcForm7 ecForm7 = ecForm7Repository.findById(proponentApplication.getProposal_id()).get();
                EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecForm7.getEnvironmentClearence().getId());
                proposalDetailInfo.setCategory(environmentClearence.getProject_category());
                proposalDetailInfo.setEcId(environmentClearence.getId());
                proposalDetailInfo.setForestLandB(environmentClearence.getFc_applied_diversion());

                EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                        .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));

                //Plant information
                List<EcAllSectorRejectionLetterCertificatePlantEquipments> listOfEcIndustryProposals = new ArrayList<>();
                EcAllSectorRejectionLetterCertificatePlantEquipments ecAllSectorRejectionLetterCertificatePlantEquipments = new EcAllSectorRejectionLetterCertificatePlantEquipments();
                if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setPlant(ecPartB.getEcIndustryProposal().getPlant());
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setConfiguration(ecPartB.getEcIndustryProposal().getFinal_configuration());
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setRemarksIfAny(ecPartB.getEcIndustryProposal().getRemarks());
                }
                listOfEcIndustryProposals.add(ecAllSectorRejectionLetterCertificatePlantEquipments);


                List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository.findDetailByEcId(environmentClearence.getId());

                List<EcAllSectorRejectionLetterCertificateDetailOfProduct> ecAllSectorRejectionLetterCertificateDetailOfProducts = new ArrayList<>();

                ecProdTransportDetails.stream()
                        .forEach(item -> {

                            if (ObjectUtils.isNotEmpty(item)) {
                                EcAllSectorRejectionLetterCertificateDetailOfProduct product = new EcAllSectorRejectionLetterCertificateDetailOfProduct(handleEmpty(item.getProduct_name()), handleEmpty(item.getProduct_by()),
                                        item.getQuantity(), item.getUnit(), item.getTransport_mode(), item.getRemarks());
                                ecAllSectorRejectionLetterCertificateDetailOfProducts.add(product);
                            }
                        });
                proposalDetailInfo.setDetailsOfReuseRecycleOfWasteWater(ecPartB.getEcPollutionDetails().getRecycle_of_waste_water());
                proposalDetailInfo.setDetailOfProposedCSTPETP(ecPartB.getEcPollutionDetails().getOffsite_treatment_plant_particulars());
                proposalDetailInfo.setCategorySafeSemiCriticalOverExploited(ecPartB.getEcWaterDetails().getGround_Water_Availability_Description());
                proposalDetailInfo.setMeasuresToRechargeGroundWater(ecPartB.getEcWaterDetails().getIs_Water_Conservation());
                proposalDetailInfo.setWaterRequirement(ecPartB.getEcConstructionDetail().isWater_requirement());
                proposalDetailInfo.setEmpCost(ecFactsheetRepository.getEmpCostByEcId(environmentClearence.getId()));


                //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
                List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());


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
                if (!(environmentClearence.getMajor_activity_id() == 0 || environmentClearence.getMajor_sub_activity_id() == 0))
                    proposalDetailInfo.setSector(activitySectorRepository.getSector(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()).getSector_code());
                break;
            }
            case 37,2: {
                EcPartC ecPartC = ecPartCRepository.findById(proponentApplication.getProposal_id()).get();
                EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(ecPartC.getEnvironmentClearence().getId());
                proposalDetailInfo.setCategory(environmentClearence.getProject_category());
                proposalDetailInfo.setEcId(environmentClearence.getId());
                proposalDetailInfo.setForestLandB(environmentClearence.getFc_applied_diversion());

                EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                        .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));

                //Plant information
                List<EcAllSectorRejectionLetterCertificatePlantEquipments> listOfEcIndustryProposals = new ArrayList<>();
                EcAllSectorRejectionLetterCertificatePlantEquipments ecAllSectorRejectionLetterCertificatePlantEquipments = new EcAllSectorRejectionLetterCertificatePlantEquipments();
                if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setPlant(ecPartB.getEcIndustryProposal().getPlant());
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setConfiguration(ecPartB.getEcIndustryProposal().getFinal_configuration());
                    ecAllSectorRejectionLetterCertificatePlantEquipments.setRemarksIfAny(ecPartB.getEcIndustryProposal().getRemarks());
                }
                listOfEcIndustryProposals.add(ecAllSectorRejectionLetterCertificatePlantEquipments);


                List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository.findDetailByEcId(environmentClearence.getId());

                List<EcAllSectorRejectionLetterCertificateDetailOfProduct> ecAllSectorRejectionLetterCertificateDetailOfProducts = new ArrayList<>();

                ecProdTransportDetails.stream()
                        .forEach(item -> {

                            if (ObjectUtils.isNotEmpty(item)) {
                                EcAllSectorRejectionLetterCertificateDetailOfProduct product = new EcAllSectorRejectionLetterCertificateDetailOfProduct(handleEmpty(item.getProduct_name()), handleEmpty(item.getProduct_by()),
                                        item.getQuantity(), item.getUnit(), item.getTransport_mode(), item.getRemarks());
                                ecAllSectorRejectionLetterCertificateDetailOfProducts.add(product);
                            }
                        });
                proposalDetailInfo.setDetailsOfReuseRecycleOfWasteWater(ecPartB.getEcPollutionDetails().getRecycle_of_waste_water());
                proposalDetailInfo.setDetailOfProposedCSTPETP(ecPartB.getEcPollutionDetails().getOffsite_treatment_plant_particulars());
                proposalDetailInfo.setCategorySafeSemiCriticalOverExploited(ecPartB.getEcWaterDetails().getGround_Water_Availability_Description());
                proposalDetailInfo.setMeasuresToRechargeGroundWater(ecPartB.getEcWaterDetails().getIs_Water_Conservation());
                proposalDetailInfo.setWaterRequirement(ecPartB.getEcConstructionDetail().isWater_requirement());
                proposalDetailInfo.setEmpCost(ecFactsheetRepository.getEmpCostByEcId(environmentClearence.getId()));


                //               List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
                List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());

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
                if (!(environmentClearence.getMajor_activity_id() == 0 || environmentClearence.getMajor_sub_activity_id() == 0))
                    proposalDetailInfo.setSector(activitySectorRepository.getSector(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()).getSector_code());
                break;
            }
            default: {
                throw new PariveshException("Model Data not found for proposal no");
            }

        }

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

    public String getIdentificationNo(int propId, String proposalNO) {
        String sCode = null;
        Integer formCode = null;
        String clearanceTypeCode = null;
        log.info(propId + " This is prop Id ");
        log.info("=====================Identification No generation================================");

        ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalNo(proposalNO);
        // ProponentApplications proponentApplications = environmentClearence.getProponentApplications();

        Applications applications = proponentApplications.getApplications();
        EnvironmentClearence environmentClearence = new EnvironmentClearence();
        Applications application = applicationsRepository.findById(proponentApplications.getApplications().getId()).get();

        switch (application.getId()) {

            case 39: {
                EcForm8TransferOfTOR ecForm8TransferOfTOR = ecForm8TransferOfTORRepository
                        .findById(proponentApplications.getProposal_id()).get();
                environmentClearence = getEnvironmentClearanceByPropId(ecForm8TransferOfTOR.getEnvironmentClearence().getId());
                break;
            }

            case 44: {
                EcForm9TransferOfEC ecForm9TransferOfEC = ecForm9TransferOfECRepository
                        .findById(proponentApplications.getProposal_id()).get();

                environmentClearence = getEnvironmentClearanceByPropId(ecForm9TransferOfEC.getId());

                break;
            }
            case 38: {
                EcForm7 ecForm7 = ecForm7Repository.findById(proponentApplications.getProposal_id()).get();
                environmentClearence = getEnvironmentClearanceByPropId(ecForm7.getEnvironmentClearence().getId());

                break;
            }
            case 37, 2: {
                EcPartC ecPartC = ecPartCRepository.findById(proponentApplications.getProposal_id()).get();
                environmentClearence = getEnvironmentClearanceByPropId(ecPartC.getEnvironmentClearence().getId());

                break;
            }
            default: {
                throw new PariveshException("Environment Clearance is not available for this proponent id: " + proponentApplications.getId());
            }
        }

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
