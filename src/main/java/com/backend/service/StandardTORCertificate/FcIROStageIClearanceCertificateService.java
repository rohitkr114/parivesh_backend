package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.model.CommonFormDetail;
import com.backend.model.District;
import com.backend.model.ProjectDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.State;
import com.backend.model.StandardTORCertificate.EcMiningFreshLetterCertificate;
import com.backend.model.StandardTORCertificate.EcTransferTemplate;
import com.backend.model.StandardTORCertificate.FcIROStageClearanceCertificate;
import com.backend.model.StandardTORCertificate.FcIROStageIClearanceCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DistrictRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.StandardTorCertificate.FcIROStageClearanceCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.FcIROStageIClearanceCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.FcMinistryStageClearanceCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.FcMinistryStageIClearanceCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

import static com.backend.util.CommonUtils.handleEmpty;
import static com.backend.util.CommonUtils.throwException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FcIROStageIClearanceCertificateService {


    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    FcIROStageIClearanceCertificateRepository fcIROStageClearanceCertificateRepository;
    
    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

    @Autowired
    Environment environment;


    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;
    
    @Autowired
    ProjectDetailRepository projectDetailRepository;
    
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    StateRepository stateRepository;
    
    @Autowired
    FcMinistryStageIClearanceCertificateRepository fcMinistryStageClearanceCertificateRepository;
    
    @Autowired
    FcMinistryStageClearanceCertificateRepository fcMinistryStageIIClearanceCertificateRepository;
    
    @Transactional
    public Object saveFcIROStageIClearanceCertificate(FcIROStageIClearanceCertificate fcIROStageClearanceCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for FcIROStageIClearanceCertificate ========================");
        FcIROStageIClearanceCertificate fcIROStageClearanceCertificateResult = null;
        //Maintaining History
        if (fcIROStageClearanceCertificateRepository.existsById(fcIROStageClearanceCertificate.getId())) {
            log.info("==================One Record is already exist for TOR Amendment Certificate========================");
            Optional<FcIROStageIClearanceCertificate> fcIROStageClearanceCertificate1 = fcIROStageClearanceCertificateRepository.findById(fcIROStageClearanceCertificate.getId());
            fcIROStageClearanceCertificateResult = fcIROStageClearanceCertificate1.get();
            fcIROStageClearanceCertificateResult.setIsActive(false);
            fcIROStageClearanceCertificateRepository.save(fcIROStageClearanceCertificateResult);

            fcIROStageClearanceCertificate.setId(null);
        }
        log.info("==================Saved TOR Amendment Certificate========================");
        int version = ObjectUtils.isEmpty(fcIROStageClearanceCertificate.getVersion()) ? 1 : fcIROStageClearanceCertificate.getVersion() + 1;
        fcIROStageClearanceCertificate.setIsActive(true);
        fcIROStageClearanceCertificate.setVersion(version);


        Optional.ofNullable(fcIROStageClearanceCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(fcIROStageClearanceCertificate.getProponentId(), fcIROStageClearanceCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + fcIROStageClearanceCertificate.getProponentId() + " proposal no: " + fcIROStageClearanceCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + fcIROStageClearanceCertificate.getProponentId() + " proposal no: " + fcIROStageClearanceCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(fcIROStageClearanceCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("fc_iro_stage_i_clearance" + String.valueOf(new java.util.Date().getTime()) + String.valueOf(new java.util.Date().getDate()) + "_" + convertedProposalNo + "_" + fcIROStageClearanceCertificate.getProponentId(), "pdf");

        //if (fcIROStageClearanceCertificate.getStatus().equalsIgnoreCase("complete")) {
        //File path generation code this could be common
        String filePath = FileUploadUtil.generatePdfFromHtml(fcIROStageClearanceCertificate.getHtmlContent(),
                folderDir, fileName,fcIROStageClearanceCertificate.getProposal_No());
        //}
        fcIROStageClearanceCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        fcIROStageClearanceCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);


        return fcIROStageClearanceCertificateRepository.save(fcIROStageClearanceCertificate);
    }

    @Transactional
    public FcIROStageIClearanceCertificate getAllDataByPropId(int propId, String proposalNo, String officeType) {
        log.info("==============================getting FcIROStageIClearanceCertificate detail by ProId===========================");

        FcIROStageIClearanceCertificate fcIROStageClearanceCertificate;
        fcIROStageClearanceCertificate = fcIROStageClearanceCertificateRepository
                .getFcIROStageIClearanceCertificateInfoBytPropId(propId, proposalNo);

        if (!ObjectUtils.isEmpty(fcIROStageClearanceCertificate)) {

            return fcIROStageClearanceCertificate;
        }
        fcIROStageClearanceCertificate= new FcIROStageIClearanceCertificate();
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(propId, proposalNo)
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + propId + " proposal no: " + proposalNo));
        String folderDir = null;
        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
        String fileName = FileUploadUtil.fileNameMaker("fc_iro_stage_clearance" + convertedProposalNo + "_" + propId, "pdf");

        CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
        String clearanceTypeId = proponentApplications.getClearance_id().toString();
        String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
        String proposalId = proponentApplications.getProposal_id().toString();
        folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
        fcIROStageClearanceCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        fcIROStageClearanceCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        fcIROStageClearanceCertificate.setVersion(0);
        fcIROStageClearanceCertificate.setFileNo(CommonUtils.handleEmpty(proponentApplications.getMoefccFileNumber()));
        fcIROStageClearanceCertificate.setProposal_No(proposalNo);
        fcIROStageClearanceCertificate.setProponentId(propId);
        
        /**********New Changes**********/
        fcIROStageClearanceCertificate.setTorDate(proponentApplications.getCreated_on().toString());
        fcIROStageClearanceCertificate.setOrganizationName(commonFormDetail.getOrganization_name());
        fcIROStageClearanceCertificate.setFormType(proponentApplications.getApplications().getName());
        
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();        
        fcIROStageClearanceCertificate.setProject_name(handleEmpty(projectDetails.getName()));
        
        Integer stateIdP = projectDetails.getMain_state();
        throwException(stateIdP, "state");
//        State stateP = stateRepository.getByStateCode(stateIdP);
        String stateP = stateRepository.getNameByStateCode(stateIdP);
//        String stateNameP = stateP != null ? stateP.getName() : "";
        fcIROStageClearanceCertificate.setState(stateP);
        
        District districtP = districtRepository.getDistrictByCode(stateIdP ,projectDetails.getMain_district());
        String districtNameP = districtP != null ? districtP.getName() : "";
        fcIROStageClearanceCertificate.setDistrict(districtNameP);

        List<String> division = fcMinistryStageIIClearanceCertificateRepository.getDivision(propId);
        fcIROStageClearanceCertificate.setDivision(String.join(",",division));
        
        
      //Adding area and state file number
        Double area;
        String fileNo = null;
        
		int clearanceId = proponentApplications.getClearance_id();
		String formType  = applicationsRepository.getAbbrByClearanceId(clearanceId);
        
        if(formType.equalsIgnoreCase("FC_FORM_A")) {
        	area = fcMinistryStageIIClearanceCertificateRepository.getFormAArea(proponentApplications.getProposal_id());
        	fileNo = fcMinistryStageIIClearanceCertificateRepository.getFormAFileNo(proponentApplications.getProposal_id());
        	
        	fcIROStageClearanceCertificate.setRecommendedArea(area);
        	
        	if (fileNo != null){
        		fcIROStageClearanceCertificate.setFileNo(fileNo);
        	}   							
		}else if(formType.equalsIgnoreCase("FC_FORM_B")) {
			area = fcMinistryStageIIClearanceCertificateRepository.getFormBArea(proponentApplications.getProposal_id());
        	fileNo = fcMinistryStageIIClearanceCertificateRepository.getFormBFileNo(proponentApplications.getProposal_id());
        
        	fcIROStageClearanceCertificate.setRecommendedArea(area);
        	
        	if (fileNo != null){
        		fcIROStageClearanceCertificate.setFileNo(fileNo);
        	}   
		}else if(formType.equalsIgnoreCase("FC_FORM_C")) {
			area = fcMinistryStageIIClearanceCertificateRepository.getFormCArea(proponentApplications.getProposal_id());
        	fileNo = fcMinistryStageIIClearanceCertificateRepository.getFormCFileNo(proponentApplications.getProposal_id());
        	
        	//if(area != 0.0d) {
        	fcIROStageClearanceCertificate.setRecommendedArea(area);
        	//}
        	if (fileNo != null){
        		fcIROStageClearanceCertificate.setFileNo(fileNo);
        	}   
		}else if(formType.equalsIgnoreCase("FC_FORM_D")) {
			area = fcMinistryStageIIClearanceCertificateRepository.getFormDArea(proponentApplications.getProposal_id());
        	fileNo = fcMinistryStageIIClearanceCertificateRepository.getFormDFileNo(proponentApplications.getProposal_id());
        	log.info(area.toString());
        	//if(area != 0.0d) {
        	fcIROStageClearanceCertificate.setRecommendedArea(area);
        	//}
        	if (fileNo != null){
        		fcIROStageClearanceCertificate.setFileNo(fileNo);
        	}   
		}else if(formType.equalsIgnoreCase("FC_FORM_E")) {
			area = fcMinistryStageIIClearanceCertificateRepository.getFormEArea(proponentApplications.getProposal_id(), proposalNo);
        	//fileNo = fcMinistryStageClearanceCertificateRepository.getFormAFileNo(proponentApplications.getProposal_id(), proposalNo, "SUBMIT");
			fcIROStageClearanceCertificate.setRecommendedArea(area);    	 
		}else {
			area = fcMinistryStageIIClearanceCertificateRepository.getArea(proponentApplications.getProposal_id());
        	//fileNo = fcMinistryStageClearanceCertificateRepository.getFormAFileNo(proponentApplications.getProposal_id(), proposalNo, "SUBMIT");
        	
			fcIROStageClearanceCertificate.setRecommendedArea(area);
        	  
		}
        
        //Adding state address				
        String address_str = fcMinistryStageIIClearanceCertificateRepository.getStateOffice( stateRepository.getStateByCode(  proponentApplications.getState_id() ).get().getId() , officeType);
		String address = address_str != null ? address_str.replaceAll(",+", ",") : null;

		fcIROStageClearanceCertificate.setStateGovernmentOfficeAddress(address);
		fcIROStageClearanceCertificate.setThreshold(fcMinistryStageIIClearanceCertificateRepository.getThreshold(propId));
		fcIROStageClearanceCertificate.setStateGovernmentOfficeName(stateRepository.getByStateCode(proponentApplications.getState_id()).getName());
    
        
        /**********New Changes End**********/

        
        return fcIROStageClearanceCertificate;
    }

}
