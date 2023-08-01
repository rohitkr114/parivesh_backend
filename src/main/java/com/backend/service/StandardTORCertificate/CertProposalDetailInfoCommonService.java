package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.model.*;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10ProjectDetails;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.StandardTORCertificate.StandardTorCertificateDetailOfProduct;
import com.backend.model.StandardTORCertificate.StandardTorCertificatePlantEquipment;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcFactsheet.EcFactsheetRepository;
import com.backend.repository.postgres.EcForm10Repository.EcForm10ProjectDetailRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPartBRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcFreshLetterTemplateCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.backend.util.CommonUtils.replaceEmptyWithNA;


@Slf4j
@Service
public class CertProposalDetailInfoCommonService {

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    EcPartBRepository ecPartBRepository;

    @Autowired
    EcProdTransportDetailsRepository ecProdTransportDetailsRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    EcForm10ProjectDetailRepository ecForm10ProjectDetailRepository;

    @Autowired
    EcFreshLetterTemplateCertificateRepository ecFreshLetterTemplateCertificateRepository;

    @Autowired
    EcFactsheetRepository ecFactsheetRepository;

    @Autowired
    Environment environment;

    @Autowired
    StateRepository stateRepository;

    /**
     * Common implemented method used by other services for fetching CertProposalDetailInfo
     * data using below-mentioned parameters.
     *
     * @param proposalDetailInfo
     * @param proposalNo
     * @param propId
     * @return
     */
    public CertProposalDetailInfo getDetailsFromDTO(CertProposalDetailInfo proposalDetailInfo, String proposalNo, int propId,String certificateName) {
        EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalNo);
        environmentClearence.getEnvironmentClearanceProjectActivityDetails();
        List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
        /**
         * Getting project activity id and name on behalf od sector code.
         */

        List<String> projectIds = new ArrayList<>();
        List<String> projectNames = new ArrayList<>();
        projectActivityDetails.stream()
                .forEach(item -> {
                    Activities activities = item.getActivities();
                    projectIds.add(activities.getItem_no() + "/" + activities.getName() );
                    projectNames.add(activities.getName());
                    System.out.println("===================" + activities.getId() + "==========================" + activities.getName());
                });

        proposalDetailInfo.setProjectIncludedScheduleNo(String.join(",", projectIds));
        proposalDetailInfo.setProjectActivity(projectNames);
        //ProponentApplications proponentApplications = environmentClearence.getProponentApplications();
        ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo).get();
        if (proponentApplications != null) {
            proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplications.getClearance_id()).getName());
            proposalDetailInfo.setFileNo(proponentApplications.getMoefccFileNumber());
            proposalDetailInfo.setCategory(environmentClearence.getProject_category());
            proposalDetailInfo.setDateOfSubmission(environmentClearence.getInterlink_date_of_submission());
            proposalDetailInfo.setForestLandB(environmentClearence.getFc_applied_diversion());
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
            if (commonFormDetail != null) {
                proposalDetailInfo.setProjectID(projectDetails.getId());
                proposalDetailInfo.setRnrInvolved(commonFormDetail.getCafOthers().getRnr_involved());
                proposalDetailInfo.setProponent(commonFormDetail.getApplicant_name());
                proposalDetailInfo.setNameOfProject(projectDetails.getName());
                proposalDetailInfo.setCompanyname(commonFormDetail.getOrganization_name());
                proposalDetailInfo.setProponentEmail(commonFormDetail.getApplicant_email());
                District district = districtRepository.getDistrictById(projectDetails.getMain_district());
                State state = stateRepository.getByStateCode(projectDetails.getMain_state());
                String districtName = district != null ? district.getName() : "NA";
                String stateName = state != null ? state.getName() : "NA";
                String registeredAddress = commonFormDetail.getOrganization_street() + ", " + commonFormDetail.getOrganization_city()
                        + districtName + ", " + stateName
                        + ", " + commonFormDetail.getOrganization_landmark()
                        + ", " + commonFormDetail.getOrganization_pincode();
                proposalDetailInfo.setRegisteredAddress(registeredAddress);
                proposalDetailInfo.setNameOfOrganization(commonFormDetail.getOrganization_name());
                proposalDetailInfo.setLocationOfProject(districtName + ", " + stateName);
                //String proposalNo = environmentClearence.getProposal_no();
                String proposalNoParts[] = proposalNo.split("/");
                String authorityCode = proposalNoParts[0];
                if ("SIA".equals(authorityCode)) {
                    proposalDetailInfo.setIssuingAuthority("SIAEE");
                } else {
                    proposalDetailInfo.setIssuingAuthority("MOEFCC");
                }
                if(environmentClearence.getProject_category().equals("Application mode to appraisal at Central Level for")){
                    proposalDetailInfo.setEac("Expert Appraisal Committee");
                } else if (environmentClearence.getProject_category().equals("B1") || environmentClearence.getProject_category().equals("B2")) {
                    proposalDetailInfo.setEac("State Expert Appraisal Committee");
                } else if(environmentClearence.getProject_category().equals("A")){
                    proposalDetailInfo.setEac("Expert Appraisal Committee");
                }
                proposalDetailInfo.setProponentId(propId);
                proposalDetailInfo.setCafId(proponentApplications.getCaf_id());
                proposalDetailInfo.setEcId(environmentClearence.getId());
                proposalDetailInfo.setProposal_No(environmentClearence.getProposal_no());
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
            }
            proposalDetailInfo.setEmpCost(ecFactsheetRepository.getEmpCostByEcId(environmentClearence.getId()));

            EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                    .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));

            //Plant information
            List<StandardTorCertificatePlantEquipment> listOfEcIndustryProposals = new ArrayList<>();
            StandardTorCertificatePlantEquipment standardTorCertificatePlantEquipment = new StandardTorCertificatePlantEquipment();
            if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
                standardTorCertificatePlantEquipment.setPlant_equipment(ecPartB.getEcIndustryProposal().getPlant());
                standardTorCertificatePlantEquipment.setConfiguration(ecPartB.getEcIndustryProposal().getFinal_configuration());
                standardTorCertificatePlantEquipment.setRemarks(ecPartB.getEcIndustryProposal().getRemarks());
            }
            listOfEcIndustryProposals.add(standardTorCertificatePlantEquipment);

            if (ObjectUtils.isNotEmpty(ecPartB.getEcPollutionDetails())) {
                proposalDetailInfo.setDetailsOfReuseRecycleOfWasteWater(ecPartB.getEcPollutionDetails().getRecycle_of_waste_water());
                proposalDetailInfo.setDetailOfProposedCSTPETP(ecPartB.getEcPollutionDetails().getOffsite_treatment_plant_particulars());
            }

            if (ObjectUtils.isNotEmpty(ecPartB.getEcMiningProposals())) {
                if (ObjectUtils.isNotEmpty(ecPartB.getEcMiningProposals().getMiningMineralsMineds())) {
                    proposalDetailInfo.setMiningMineralsMineds(ecPartB.getEcMiningProposals().getMiningMineralsMineds());
                }
            }

            if (ObjectUtils.isNotEmpty(ecPartB.getEcWaterDetails())) {
                proposalDetailInfo.setCategorySafeSemiCriticalOverExploited(ecPartB.getEcWaterDetails().getGround_Water_Availability_Description());
                proposalDetailInfo.setMeasuresToRechargeGroundWater(ecPartB.getEcWaterDetails().getIs_Water_Conservation());
            }


            if (ObjectUtils.isNotEmpty(ecPartB.getEcConstructionDetail())) {
                proposalDetailInfo.setWaterRequirement(ecPartB.getEcConstructionDetail().isWater_requirement());
            }


            //EC MINING PROPOSAL DETAILS
            if (ObjectUtils.isNotEmpty(ecPartB.getEcMiningProposals())) {
                proposalDetailInfo.setTotalExcavationMtpa(ecPartB.getEcMiningProposals().getTotal_excavation_mtpa());
                proposalDetailInfo.setTotalExcavationAnnum(ecPartB.getEcMiningProposals().getTotal_excavation_annum());
                proposalDetailInfo.setStrippingRatio(ecPartB.getEcMiningProposals().getStripping_ratio());
                proposalDetailInfo.setExcavationOtherInfo(ecPartB.getEcMiningProposals().getExcavation_other_info());
                proposalDetailInfo.setLeasePeriod(ecPartB.getEcMiningProposals().getLease_period());
                proposalDetailInfo.setCoalBeneficiation(ecPartB.getEcMiningProposals().getCoal_beneficiation());
                proposalDetailInfo.setCoalBeneficiationCapacity(ecPartB.getEcMiningProposals().getCoal_capacity());
                proposalDetailInfo.setProposedToInstallCrusher(ecPartB.getEcMiningProposals().isProposed_to_install_crusher());
                proposalDetailInfo.setNumberOfCrusher(ecPartB.getEcMiningProposals().getNumber_of_crusher());
                proposalDetailInfo.setCapacityOfCrusher(ecPartB.getEcMiningProposals().getCapacity_of_crusher());
                proposalDetailInfo.setTotalCapacityOfCrusher(ecPartB.getEcMiningProposals().getTotal_capacity_of_crusher());
                proposalDetailInfo.setWaterBody(ecPartB.getEcMiningProposals().getWater_body());
            }

            //Transport details
            List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository.findDetailByEcId(environmentClearence.getId());
            List<StandardTorCertificateDetailOfProduct> productList = new ArrayList<>();

            List<EcForm10ProjectDetails> ecForm10ProjectDetailsList = ecForm10ProjectDetailRepository.getEcForm10ProjectDetailsByCafId(commonFormDetail.getId());
            if (ObjectUtils.isNotEmpty(ecForm10ProjectDetailsList)) {
                proposalDetailInfo.setEmploymentDetails(ecForm10ProjectDetailsList.get(0).getApplicant_name()+" "+ecForm10ProjectDetailsList.get(0).getApplicant_email()+" "+ecForm10ProjectDetailsList.get(0).getApplicant_state());
            }


            ecProdTransportDetails.stream()
                    .forEach(item -> {
                        StandardTorCertificateDetailOfProduct product = new StandardTorCertificateDetailOfProduct();
                        if (ObjectUtils.isNotEmpty(item)) {
                            product.setByProduct(item.getProduct_by());
                            product.setName_of_the_product(item.getProduct_name());
                            product.setQuantity(item.getQuantity());
                            product.setMode_of_transport(item.getTransport_mode());
                            product.setUnit(item.getUnit());
                            product.setRemarks(item.getRemarks());
                        }
                        productList.add(product);
                    });
            proposalDetailInfo.setPlantEquipmentArray(listOfEcIndustryProposals);
            proposalDetailInfo.setDetailsOfProducts(productList);

            String folderDir = null;
            String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
            String fileName = FileUploadUtil.fileNameMaker(certificateName + convertedProposalNo + "_" + propId, "pdf");

            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();

            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
            proposalDetailInfo.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
            proposalDetailInfo.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
            proposalDetailInfo.setVersion(0);
            proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplications.getMoefccFileNumber()));

        }
        return proposalDetailInfo;
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
}
