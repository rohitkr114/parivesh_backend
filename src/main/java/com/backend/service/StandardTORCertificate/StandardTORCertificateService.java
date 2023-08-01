package com.backend.service.StandardTORCertificate;

import com.backend.clearance.LocalClearance;
import com.backend.constant.AppConstant;
import com.backend.dto.ActivityDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.exceptions.PariveshException;
import com.backend.model.Activities;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.District;
import com.backend.model.EcProdTransportDetails;
import com.backend.model.EnvironmentClearance.EcIndustryProposal;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.EnvironmentClearanceProjectActivityDetails;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProjectDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.StandardTORCertificate.*;
import com.backend.model.State;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.model.certificate.IndustryAndTransportDetails;
import com.backend.repository.postgres.ActivityRepository;
import com.backend.repository.postgres.ActivitySubActivitySectorRepository;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DistrictRepository;
import com.backend.repository.postgres.EcProdTransportDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearanceProjectActivityDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPartBRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.SectorEntityRepository;
import com.backend.repository.postgres.StandardTorCertificate.RejectionTorCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.SpecificTorCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.StandardTorCertificateRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.service.GeneratePDFService.EcTORStandardGenPDFService;

import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import com.beust.jcommander.Strings;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.tika.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static com.backend.util.CommonUtils.handleEmpty;
import static com.backend.util.CommonUtils.parseDateInDDMMYYYY;
import static com.backend.util.CommonUtils.replaceEmptyWithNA;
import static com.backend.util.CommonUtils.addStrings;
import static com.backend.util.CommonUtils.throwException;

@Slf4j
@Service
public class StandardTORCertificateService {

    @Autowired
    StandardTorCertificateRepository standardTorCertificateRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;
    
    @Autowired
    EnvironmentClearanceProjectActivityDetailsRepository environmentClearanceProjectActivityDetailsRepository;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;
    @Autowired
    StandardTORCertificateGeneration standardTORCertificateGeneration;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    ActivityRepository activityRepository;
    
    @Autowired
    ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    EcPartBRepository ecPartBRepository;

    @Autowired
    EcProdTransportDetailsRepository ecProdTransportDetailsRepository;

    @Autowired
    ProjectDetailRepository projectDetailRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    EcTORStandardGenPDFService ecTORStandardGenPDFService;

    @Autowired
    RejectionTorCertificateRepository rejectionTorCertificateRepository;
    @Autowired
    SpecificTorCertificateRepository specificTorCertificateRepository;

    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    Environment environment;
    
    @Autowired
    SectorEntityRepository sectorEntityRepository;
    //

    @Transactional
    public Object saveStandardTorCertificate(StandardTorCertificate standardTorCertificate,
                                             HttpServletRequest request, HttpServletResponse response) throws IOException, PariveshException {
        log.info("==================Calling Save API for Standard tor Certificate======================== for proposal: " + standardTorCertificate.getProposal_No());
        StandardTorCertificate standardTorCertificateResult = null;
        //Maintaining History
        if (standardTorCertificateRepository.existsById(standardTorCertificate.getId())) {
            log.info("==================One Record is already exist for Standard tor Certificate========================");
            Optional<StandardTorCertificate> standardTorCertificate1 = standardTorCertificateRepository.findById(standardTorCertificate.getId());
            standardTorCertificateResult = standardTorCertificate1.get();
            standardTorCertificateResult.setIsActive(false);
            standardTorCertificateRepository.save(standardTorCertificateResult);

            standardTorCertificate.setId(null);
        }
        log.info("==================Saved Standard tor Certificate========================");
        int version = ObjectUtils.isEmpty(standardTorCertificate.getVersion()) ? 1 : standardTorCertificate.getVersion() + 1;
        standardTorCertificate.setIsActive(true);
        standardTorCertificate.setVersion(version);

        Optional.ofNullable(standardTorCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(standardTorCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(standardTorCertificate.getProponentId(), standardTorCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + standardTorCertificate.getProponentId() + " proposal no: " + standardTorCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + standardTorCertificate.getProponentId() + " proposal no: " + standardTorCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(standardTorCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("standard_tor_" + convertedProposalNo + "_" + standardTorCertificate.getProponentId(), "pdf");

        //if (standardTorCertificate.getStatus().equalsIgnoreCase("complete")) {
        //File path generation code this could be common
        String filePath = FileUploadUtil.generatePdfFromHtml(standardTorCertificate.getHtmlContent(), folderDir,
                fileName,standardTorCertificate.getProposal_No());
        //}
       // if(StringUtils.isEmpty(standardTorCertificate.getFilePath()))    
        standardTorCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
       // if(StringUtils.isEmpty(standardTorCertificate.getFolderDir()))
        standardTorCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);

        return standardTorCertificateRepository.save(standardTorCertificate);
    }

    public Object getDataForCertificate(Integer id, String proposalNo, HttpServletRequest request) {
        return standardTorCertificateRepository.getDataForCertificate(id);
    }

    private EnvironmentClearence getEnvironmentClearanceByPropId(String proposalNo) {
       /* Integer proposalId = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo)
                .orElseThrow(() -> new PariveshException("No record found"));*/
        EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(proposalNo);
        environmentClearence.setProponentApplications(
                proponentApplicationRepository.getApplicationByProposalId(environmentClearence.getId()));
        environmentClearence.setCommonFormDetail(commonFormDetailRepository
                .findDetailByCafId(environmentClearence.getProponentApplications().getCaf_id()));
        return environmentClearence;
    }

    @Transactional
    public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String proposalNo,String name) throws ParseException {
        log.info("==============================getting EC detail by ProId===========================");
        String proposalNoParts[] = proposalNo.split("/");
        String authorityCode = proposalNoParts[0];
        String sectorCodeFromPP = proposalNoParts[2];
        SpecificTorCertificate specificTorCertificate = null;
        StandardTorCertificate standTorCert=null;
        RejectionTorCertificate rejectionTorCertificate = null;
        if(name.equals("specific")){
            specificTorCertificate = specificTorCertificateRepository.getSpecificCertificateInfoBytPropId(propId, proposalNo);
        } else if (name.equals("standard")) {
            standTorCert = standardTorCertificateRepository.getStandardCertificateInfoBytPropId(propId, proposalNo);
        } else if (name.equals("rejection")) {
            rejectionTorCertificate = rejectionTorCertificateRepository.getRejectionCertificateInfoBytPropId(propId, proposalNo);
        }
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
        if (!ObjectUtils.isEmpty(standTorCert)) {
            BeanUtils.copyProperties(standTorCert, proposalDetailInfo);
            
            EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalNo);
            List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());
            proposalDetailInfo.setEcProjectActivityDetails(projectActivityDetails);
            
            
            ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo).get();
            if (proponentApplications != null) {
            	
            	CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

            	proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
            }
            
        } else if (!ObjectUtils.isEmpty(specificTorCertificate)) {
            BeanUtils.copyProperties(specificTorCertificate, proposalDetailInfo);
            
            EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalNo);
            List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());
            proposalDetailInfo.setEcProjectActivityDetails(projectActivityDetails);
            
            ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo).get();
            if (proponentApplications != null) {
            	
            	CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

            	proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
            }
            
        } else if (!ObjectUtils.isEmpty(rejectionTorCertificate)) {
            BeanUtils.copyProperties(rejectionTorCertificate, proposalDetailInfo);
        } else {
            EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalNo);
            //String submissionDate = parseDateInDDMMYYYY(environmentClearence.getCreated_on().toString());
      
            java.util.Date date = new java.util.Date();             
            DateFormat outputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String submissionDate = outputFormatter.format(date);            
         
            proposalDetailInfo.setTorDate(submissionDate);
            
            proposalDetailInfo.setApplicabilityOfGeneralConditions(environmentClearence.isIs_general_condition_specified() ? "YES" : "NO");           
            proposalDetailInfo.setApplicabilityOfSpecificConditions(environmentClearence.getEcProjectDetail().isIs_specific_condition_specified() ? "YES" : "NO");

            //List<EnvironmentClearanceProjectActivityDetails> projectActivityDetails = environmentClearence.getEnvironmentClearanceProjectActivityDetails();
            List<EnvironmentClearanceProjectActivityDetailsDto> projectActivityDetails = environmentClearanceProjectActivityDetailsRepository.findDetailByEcId(environmentClearence.getId());

            /**
             * Getting project activity id and name on behalf od sector code.
             */

            List<String> projectIds = new ArrayList<>();
            List<String> projectNames = new ArrayList<>();
            List<Integer> activityIds = new ArrayList<>();
           /* projectActivityDetails.stream()
                    .forEach(item -> {
                        Activities activities = item.getActivities();
                        
                        if (activities.getItem_no() != null)
                        projectIds.add(activities.getItem_no() + " " + activities.getName());
                        else
                        projectIds.add(activities.getName());
                        
                        projectNames.add(activities.getName());
                        activityIds.add(activities.getId());
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
                activityIds.add(activities.getId());
                System.out.println("===================" + activities.getId() + "==========================" + activities.getName());
            });
            
            proposalDetailInfo.setProjectIncludedScheduleNo(String.join(",", projectIds));
            proposalDetailInfo.setProjectActivity(projectNames);
            proposalDetailInfo.setProjectActivityId(activityIds);
            proposalDetailInfo.setEcProjectActivityDetails(projectActivityDetails);
            
            if (environmentClearence.getMajor_activity_id() != null || environmentClearence.getMajor_sub_activity_id() !=null)
            proposalDetailInfo.setSector(activitySectorRepository.getSectorName(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()));
            proposalDetailInfo.setSub_activity_condition_applicability(activitySectorRepository.getSubActivityConditionApllicability(environmentClearence.getMajor_activity_id(), environmentClearence.getMajor_sub_activity_id()));
            
            //ProponentApplications proponentApplications = environmentClearence.getProponentApplications();
            ProponentApplications proponentApplications = proponentApplicationRepository.getProponentByIdAndProposalNo(propId, proposalNo).get();
            if (proponentApplications != null) {
            	
            	CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            	proposalDetailInfo.setProposal_for(commonFormDetail.getProposal_for());

            	proposalDetailInfo.setProposal_for_old(commonFormDetail.getProposal_for_old());
            	
                //proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplications.getClearance_id()).getName());
                proposalDetailInfo.setFileNo(replaceEmptyWithNA(proponentApplications.getMoefccFileNumber()));
                proposalDetailInfo.setCategory(replaceEmptyWithNA(environmentClearence.getProject_category()));
                
                ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();
                log.info("standard-tor-certificate: project ID:- " + commonFormDetail.getProject_id());
                if (commonFormDetail != null) {
                    proposalDetailInfo.setProponent(handleEmpty(commonFormDetail.getApplicant_name()));
                    proposalDetailInfo.setNameOfProject(handleEmpty(projectDetails.getName()));
                    proposalDetailInfo.setCompanyname(handleEmpty(commonFormDetail.getOrganization_name()));
                    //proposalDetailInfo.setProponentEmail(handleEmpty(commonFormDetail.getApplicant_email()));
                    proposalDetailInfo.setProponentEmail(handleEmpty(commonFormDetail.getOrganization_email()));
                    
                    //District districtP = districtRepository.getDistrictById(projectDetails.getMain_district());
                    
                    Integer stateIdP = projectDetails.getMain_state();
                    throwException(stateIdP, "state");
                    State stateP = stateRepository.getByStateCode(stateIdP);
                    String stateNameP = stateP != null ? stateP.getName() : "";
                    
                    //State state = commonFormDetail.getOrganisationState();
                    //String stateName = state != null ? state.getName() : "";
                    
                    District districtP = districtRepository.getDistrictByCode(stateIdP ,projectDetails.getMain_district());
                    String districtNameP = districtP != null ? districtP.getName() : "";
                    
                    //District district = commonFormDetail.getOrganisationDistrict();
                    //String districtName = district != null ? district.getName() : "";
                    
                    Integer stateId = commonFormDetail.getOrganization_state();
                    State state = stateRepository.getByStateCode(stateId);
                    String stateName = state != null ? state.getName()+ ", " : "";
                    
					
					Integer districtId = commonFormDetail.getOrganization_district();
                    District district = districtRepository.getDistrictByCode(stateId ,districtId);
                    String districtName = district != null ? district.getName()+ ", " : "";
                    
                    String streetOrg = commonFormDetail.getOrganization_street() != null ? commonFormDetail.getOrganization_street()+ ", " : "";
                    String cityOrg = commonFormDetail.getOrganization_city() != null ? commonFormDetail.getOrganization_city()+ ", " : "";
                    String landmarkOrg = commonFormDetail.getOrganization_landmark() != null ? commonFormDetail.getOrganization_landmark()+ ", " : "";
                    String pinOrg = commonFormDetail.getOrganization_pincode() != null ? commonFormDetail.getOrganization_pincode() : "";
                    
                    String registeredAddress = streetOrg + cityOrg
                            + districtName + stateName
                            + landmarkOrg
                            + pinOrg;
                    
                    /*List<String> registeredAddress = new LinkedList<>();
                    addStrings(registeredAddress, streetOrg);
                    addStrings(registeredAddress, cityOrg);
                    addStrings(registeredAddress, districtName);
                    addStrings(registeredAddress, stateName);
                    addStrings(registeredAddress, landmarkOrg);
                    addStrings(registeredAddress, pinOrg);
                    String registeredAddressStr = Strings.join(",", registeredAddress);*/
                    proposalDetailInfo.setRegisteredAddress(registeredAddress);
                    proposalDetailInfo.setNameOfOrganization(handleEmpty(commonFormDetail.getOrganization_name()));
                    proposalDetailInfo.setLocationOfProject(handleEmpty(districtNameP) + ", " + handleEmpty(stateNameP));

                    if ("SIA".equals(authorityCode)) {
                        proposalDetailInfo.setIssuingAuthority("SEIAA");
                    } else {
                        proposalDetailInfo.setIssuingAuthority("MoEF&CC");
                    }
                    proposalDetailInfo.setProponentId(propId);
                    proposalDetailInfo.setCafId(proponentApplications.getCaf_id());
                    proposalDetailInfo.setEcId(environmentClearence.getId());
                    proposalDetailInfo.setProposal_No(environmentClearence.getProposal_no());
                }
                EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                        .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));

                //Plant information
                List<StandardTorCertificatePlantEquipment> listOfStandardPlant = new ArrayList<>();
                List<SpecificTorCertificatePlantEquipment> listOfSpecificPlant = new ArrayList<>();
                List<RejectionTorCertificatePlantEquipment> listOfRejectionPlant = new ArrayList<>();

                if (ObjectUtils.isNotEmpty(ecPartB.getEcIndustryProposal())) {
                    StandardTorCertificatePlantEquipment standardTorCertificatePlantEquipment =
                            new StandardTorCertificatePlantEquipment(ecPartB.getEcIndustryProposal().getPlant(),
                                    ecPartB.getEcIndustryProposal().getFinal_configuration(),
                                    ecPartB.getEcIndustryProposal().getRemarks());
                    SpecificTorCertificatePlantEquipment specificTorCertificatePlantEquipment =
                            new SpecificTorCertificatePlantEquipment(ecPartB.getEcIndustryProposal().getPlant(),
                                    ecPartB.getEcIndustryProposal().getFinal_configuration(),
                                    ecPartB.getEcIndustryProposal().getRemarks());
                    RejectionTorCertificatePlantEquipment rejectionTorCertificatePlantEquipment =
                            new RejectionTorCertificatePlantEquipment(ecPartB.getEcIndustryProposal().getPlant(),
                                    ecPartB.getEcIndustryProposal().getFinal_configuration(),
                                    ecPartB.getEcIndustryProposal().getRemarks());
                    listOfStandardPlant.add(standardTorCertificatePlantEquipment);
                    listOfSpecificPlant.add(specificTorCertificatePlantEquipment);
                    listOfRejectionPlant.add(rejectionTorCertificatePlantEquipment);
                }

                proposalDetailInfo.setPlantEquipmentArray(listOfStandardPlant);
                proposalDetailInfo.setPlantEquipmentsSpecific(listOfSpecificPlant);
                proposalDetailInfo.setPlantEquipmentsRejection(listOfRejectionPlant);

                //Transport details
                List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository.findDetailByEcId(environmentClearence.getId());
                List<StandardTorCertificateDetailOfProduct> standardDetailList = new ArrayList<>();
                List<SpecificTorCertificateDetailOfProduct> specificDetailsList = new ArrayList<>();
                List<RejectionTorCertificateDetailOfProduct> rejectionDetailList = new ArrayList<>();


                ecProdTransportDetails.stream()
                        .forEach(item -> {

                            if (ObjectUtils.isNotEmpty(item)) {
                                StandardTorCertificateDetailOfProduct product = new StandardTorCertificateDetailOfProduct(handleEmpty(item.getProduct_name()), handleEmpty(item.getProduct_by()),
                                        item.getQuantity(), item.getUnit(), item.getTransport_mode(), item.getRemarks(), item.getProposed_quantity(), item.getQuantity_total());
                                SpecificTorCertificateDetailOfProduct specific = new SpecificTorCertificateDetailOfProduct(handleEmpty(item.getProduct_name()), handleEmpty(item.getProduct_by()),
                                        item.getQuantity(), item.getUnit(), item.getTransport_mode(), item.getRemarks(), item.getProposed_quantity(), item.getQuantity_total());
                                RejectionTorCertificateDetailOfProduct rejection = new RejectionTorCertificateDetailOfProduct(handleEmpty(item.getProduct_name()), handleEmpty(item.getProduct_by()),
                                        item.getQuantity(), item.getUnit(), item.getTransport_mode(), item.getRemarks(), item.getProposed_quantity(), item.getQuantity_total());
                                standardDetailList.add(product);
                                specificDetailsList.add(specific);
                                rejectionDetailList.add(rejection);
                            }

                        });

                proposalDetailInfo.setDetailsOfProducts(standardDetailList);
                proposalDetailInfo.setDetailsOfProductsSpecific(specificDetailsList);
                proposalDetailInfo.setDetailsOfProductsRejection(rejectionDetailList);

                //Getting clearance type.
                Applications applications = proponentApplications.getApplications();
                String formAbbr = applications.getAbbr();
                String catId = environmentClearence.getProject_category();
                String clearanceTypeName = LocalClearance.getClearanceByCat(formAbbr, catId);
                
                //String ProposalFor= commonFormDetail.getProposal_for() != null ? commonFormDetail.getProposal_for() : "";
                //proposalDetailInfo.setClearanceType(clearanceTypeName );
                proposalDetailInfo.setClearanceType(applicationsRepository.getById(proponentApplications.getClearance_id()).getGeneral_name());
                
                String folderDir = null;
                String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
                String fileName = FileUploadUtil.fileNameMaker("standard_tor_" + convertedProposalNo + "_" + propId, "pdf");

                String clearanceTypeId = proponentApplications.getClearance_id().toString();
                String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
                String proposalId = proponentApplications.getProposal_id().toString();

                folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
                proposalDetailInfo.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
                proposalDetailInfo.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
                proposalDetailInfo.setVersion(0);
                proposalDetailInfo.setFileNo(CommonUtils.handleEmpty(proponentApplications.getMoefccFileNumber()));


            }
        }
        return proposalDetailInfo;

    }

    private void copyPlant () {

    }

    public String getIdentificationNo(int propId, String proposalNO) {
        String sCode = null;
        Integer formCode = null;
        String clearanceTypeCode = null;
        log.info(propId + " This is prop Id ");
        log.info("=====================Identification No generation================================");
        EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalNO);
        ProponentApplications proponentApplications = environmentClearence.getProponentApplications();
        /*Applications applications = proponentApplications.getApplications();
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
                    .orElse("NA") ;
        }*/

        String identificationNo = standardTorCertificateRepository.getIdentificationNo(proponentApplications.getProposal_id());
        
        //List<String> torIdDetails = standardTorCertificateRepository.getStandardTorDetail(proposalNo);
        /*String torIdDetails_str = standardTorCertificateRepository.getStandardTorDetail(proposalNo);
        String[] torIdDetails = torIdDetails_str.split(",");
        
        String[] propArray = proposalNo.split("/");
        String header = "TO";
        String authority = "";
        String subActivity = "";
        String stateCode = propArray[1];
        //String propSectorName = propArray[2];
        //String propNo = propArray[3];
        String year = propArray[4];
        String checksum = "5";
        String footer = "";
        Random random = new Random();
        int fiveDigitRandom=random.nextInt(90000)+10000;
      
        if(torIdDetails[0].equals("A") ) {
        	authority = "A";
        }else if(torIdDetails[0].equals("B1")) {
        	authority = "B";
        }else if(torIdDetails[0].equals("B2")) {
        	authority = "C";
        }
        
        int len_sub_activity = torIdDetails[1].length();
        //String padding = "0000";
        if(len_sub_activity == 0) {
        	
        	subActivity = "0000";
        	//log.info(subActivity);
        }
        else
        	subActivity = new StringBuilder()
        			.append(torIdDetails[1]).toString();
        //log.info(subActivity);
        
        if(torIdDetails[2].equalsIgnoreCase("new")) {
        	footer = "N";
        }else if(torIdDetails[2].equalsIgnoreCase("amendment")) {
        	footer = "A";
        }else if(torIdDetails[2].equalsIgnoreCase("expansion")) {
        	footer = "E";
        }else if(torIdDetails[2].equalsIgnoreCase("validity/renewal")) {
        	footer = "R";
        }else if(torIdDetails[2].equalsIgnoreCase("transfer")) {
        	footer = "T";
        }else if(torIdDetails[2].equalsIgnoreCase("revocation/cancellation")) {
        	footer = "C";
        }else if(torIdDetails[2].equalsIgnoreCase("surrender")) {
        	footer = "Q";
        }else if(torIdDetails[2].equalsIgnoreCase("split")) {
        	footer = "S";
        }else if(torIdDetails[2].equalsIgnoreCase("merger")) {
        	footer = "M";
        }else if(torIdDetails[2].equalsIgnoreCase("violation")) {
        	footer = "V";
        }*/
        /*String torIdentificationNo = new StringBuilder()
                .append(clearanceTypeCode != null ? clearanceTypeCode : "NA")
                .append(formCode != null ? formCode : "NA")
                .append(propSectorName)
                .append(year.substring(1))
                .append(propNo)
                .toString();*/
        
       /* String torIdentificationNo = new StringBuilder()
        		.append(header)
        		.append(year.substring(2))
        		.append(authority)
        		.append(subActivity)
        		.append(stateCode)
        		.append(checksum)
        		.append(fiveDigitRandom)
        		.append(footer).toString();*/
        
        return identificationNo;
    }


    public IndustryAndTransportDetails getEcIndustryProposalDetailById(Integer propId, String proposalNo) {
        IndustryAndTransportDetails transportDetails = new IndustryAndTransportDetails();
        EnvironmentClearence environmentClearence = getEnvironmentClearanceByPropId(proposalNo);
        EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(environmentClearence.getId())
                .orElseThrow(() -> new PariveshException("EC partB is not available for id: " + environmentClearence.getId()));
        List<EcProdTransportDetails> ecProdTransportDetails = ecProdTransportDetailsRepository.findDetailByEcId(environmentClearence.getId());
        List<EcIndustryProposal> listOfEcIndustryProposals = new ArrayList<>();
        listOfEcIndustryProposals.add(ecPartB.getEcIndustryProposal());
        transportDetails.setEcIndustryProposal(listOfEcIndustryProposals);
        transportDetails.setEcProdTransportDetails(ecProdTransportDetails);
        return transportDetails;
    }


    public Object getStandTorData(int id) {
        return standardTorCertificateRepository.findById(id);
    }

    /*
    * BELOW SERVICE IS FOR FC CERTIFICATES.
    * */

    // FOR FC IRO STAGE CLEARANCE CERTIFICATES
    public String getFcIROStageClearanceCertificateFormTypeInfoBytPropId(int propId, String proposal_no) {
        log.info("==============================getting FcIROStageClearanceCertificate detail by ProId and proposal_no===========================");
        Integer clearanceId = proponentApplicationRepository.getApplicationsById(propId, proposal_no);
        String fc_Conditions = applicationsRepository.getFormTypeByClearanceId(clearanceId);
        return fc_Conditions;
    }

    // FOR FC MINISTRY STAGE CLEARANCE CERTIFICATES

    public String getFcMinistryStageClearanceCertificateFormTypeInfoBytPropId(int propId,String proposal_no) {
        log.info("==============================getting FcMinistryStageClearanceCertificate detail by ProId and proposal_no===========================");
        Integer clearanceId = proponentApplicationRepository.getApplicationsById(propId ,proposal_no);
        String fc_Conditions = applicationsRepository.getFormTypeByClearanceId(clearanceId);
        return fc_Conditions;
    }


    // FOR FC  STATE STAGE CLEARANCE CERTIFICATES
    public String getFcStateStageClearanceCertificateFormTypeInfoBytPropId(int propId,String proposal_no) {
        log.info("==============================getting FcStateStageClearanceCertificate detail by ProId and proposal_no===========================");
        Integer clearanceId = proponentApplicationRepository.getApplicationsById(propId ,proposal_no);
        String fc_Conditions = applicationsRepository.getFormTypeByClearanceId(clearanceId);
        return fc_Conditions;
    }


}
