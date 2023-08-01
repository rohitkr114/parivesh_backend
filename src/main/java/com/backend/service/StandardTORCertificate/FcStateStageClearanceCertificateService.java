package com.backend.service.StandardTORCertificate;


import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.model.CommonFormDetail;
import com.backend.model.District;
import com.backend.model.ProjectDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.State;
import com.backend.model.StandardTORCertificate.FcMinistryStageClearanceCertificate;
import com.backend.model.StandardTORCertificate.FcStateStageClearanceCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.DistrictRepository;
import com.backend.repository.postgres.ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.StandardTorCertificate.FcMinistryStageClearanceCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.FcStateStageClearanceCertificateRepository;
import com.backend.util.CommonUtils;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.backend.util.CommonUtils.handleEmpty;
import static com.backend.util.CommonUtils.throwException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class FcStateStageClearanceCertificateService {

    @Autowired
    FcStateStageClearanceCertificateRepository fcStateStageClearanceCertificateRepository;
    
    @Autowired
    FcMinistryStageClearanceCertificateRepository fcMinistryStageClearanceCertificateRepository;

  @Autowired
  ApplicationsRepository applicationsRepository;

    @Autowired
    Environment environment;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;
    
    @Autowired
    ProjectDetailRepository projectDetailRepository;
    
    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CertProposalDetailInfoCommonService  certProposalDetailInfoCommonService;
    
    public Object saveFcStateStageClearanceCertificate(FcStateStageClearanceCertificate fcStateStageClearanceCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for FcStateStageClearanceCertificate ========================");
        FcStateStageClearanceCertificate fcStateStageClearanceCertificateResult = null;
        //Maintaining History
        if (fcStateStageClearanceCertificateRepository.existsById(fcStateStageClearanceCertificate.getId())) {
            log.info("==================One Record is already exist for TOR Amendment Certificate========================");
            Optional<FcStateStageClearanceCertificate> fcStateStageClearanceCertificate1 = fcStateStageClearanceCertificateRepository.findById(fcStateStageClearanceCertificate.getId());
            fcStateStageClearanceCertificateResult = fcStateStageClearanceCertificate1.get();
            fcStateStageClearanceCertificateResult.setIsActive(false);
            fcStateStageClearanceCertificateRepository.save(fcStateStageClearanceCertificateResult);

            fcStateStageClearanceCertificate.setId(null);
        }
        log.info("==================Saved TOR Amendment Certificate========================");
        int version = ObjectUtils.isEmpty(fcStateStageClearanceCertificate.getVersion()) ? 1 : fcStateStageClearanceCertificate.getVersion() + 1;
        fcStateStageClearanceCertificate.setIsActive(true);
        fcStateStageClearanceCertificate.setVersion(version);

        Optional.ofNullable(fcStateStageClearanceCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(fcStateStageClearanceCertificate.getProponentId(), fcStateStageClearanceCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + fcStateStageClearanceCertificate.getProponentId() + " proposal no: " + fcStateStageClearanceCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + fcStateStageClearanceCertificate.getProponentId() + " proposal no: " + fcStateStageClearanceCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(fcStateStageClearanceCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("" + convertedProposalNo + "fc_state_stage_ii_clearance_" + String.valueOf(new java.util.Date().getTime()) + String.valueOf(new java.util.Date().getDate()) + "_" + fcStateStageClearanceCertificate.getProponentId(), "pdf");

        //if (fcStateStageClearanceCertificate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath = FileUploadUtil.generatePdfFromHtml(fcStateStageClearanceCertificate.getHtmlContent(),
                    folderDir, fileName,fcStateStageClearanceCertificate.getProposal_No());
        //}
        fcStateStageClearanceCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        fcStateStageClearanceCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);

        return fcStateStageClearanceCertificateRepository.save(fcStateStageClearanceCertificate);
    }


    public FcStateStageClearanceCertificate getAllDataByPropId(int propId, String proposalNo, String officeType) {
        log.info("==============================getting FcStageStageClearanceCertificate detail by ProId===========================");

        FcStateStageClearanceCertificate fcStateStageClearanceCertificate;
        fcStateStageClearanceCertificate = fcStateStageClearanceCertificateRepository
                .getFcStateStageClearanceCertificateInfoBytPropId(propId, proposalNo);

        if (!ObjectUtils.isEmpty(fcStateStageClearanceCertificate)) {

            return fcStateStageClearanceCertificate;
        }
        fcStateStageClearanceCertificate = new FcStateStageClearanceCertificate();
        fcStateStageClearanceCertificate.setVersion(0);
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(propId, proposalNo)
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + propId + " proposal no: " + proposalNo));
        String folderDir = null;
        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
        String fileName = FileUploadUtil.fileNameMaker("fc_state_stage_clearance" + convertedProposalNo + "_" + propId, "pdf");

        CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
        String clearanceTypeId = proponentApplications.getClearance_id().toString();
        String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
        String proposalId = proponentApplications.getProposal_id().toString();
        folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
        fcStateStageClearanceCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        fcStateStageClearanceCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        fcStateStageClearanceCertificate.setVersion(0);
        fcStateStageClearanceCertificate.setFileNo(CommonUtils.handleEmpty(proponentApplications.getMoefccFileNumber()));
        fcStateStageClearanceCertificate.setProposal_No(proposalNo);
        fcStateStageClearanceCertificate.setProponentId(propId);
        
        /**********New Changes**********/
        fcStateStageClearanceCertificate.setTorDate(proponentApplications.getCreated_on().toString());
        fcStateStageClearanceCertificate.setOrganizationName(commonFormDetail.getOrganization_name());
        fcStateStageClearanceCertificate.setFormType(proponentApplications.getApplications().getName());
        
        ProjectDetails projectDetails = projectDetailRepository.findById(commonFormDetail.getProject_id()).get();        
        fcStateStageClearanceCertificate.setProject_name(handleEmpty(projectDetails.getName()));
        
        Integer stateIdP = projectDetails.getMain_state();
        throwException(stateIdP, "state");
        State stateP = stateRepository.getByStateCode(stateIdP);
        String stateNameP = stateP != null ? stateP.getName() : "";
        fcStateStageClearanceCertificate.setState(stateNameP);
        
        District districtP = districtRepository.getDistrictByCode(stateIdP ,projectDetails.getMain_district());
        String districtNameP = districtP != null ? districtP.getName() : "";
        fcStateStageClearanceCertificate.setDistrict(districtNameP);

        List<String> division = fcStateStageClearanceCertificateRepository.getDivision(propId);
        fcStateStageClearanceCertificate.setDivision(String.join(",",division));
        
      //Adding area and state file number
        Double area;
        String fileNo = null;
        
		int clearanceId = proponentApplications.getClearance_id();
		String formType  = applicationsRepository.getAbbrByClearanceId(clearanceId);
        
        if(formType.equalsIgnoreCase("FC_FORM_A")) {
        	area = fcMinistryStageClearanceCertificateRepository.getFormAArea(proponentApplications.getProposal_id());
        	fileNo = fcMinistryStageClearanceCertificateRepository.getFormAFileNo(proponentApplications.getProposal_id());
        	
        	fcStateStageClearanceCertificate.setRecommendedArea(area);
        	
        	if (fileNo != null){
        		fcStateStageClearanceCertificate.setFileNo(fileNo);
        	}   							
		}else if(formType.equalsIgnoreCase("FC_FORM_B")) {
			area = fcMinistryStageClearanceCertificateRepository.getFormBArea(proponentApplications.getProposal_id());
        	fileNo = fcMinistryStageClearanceCertificateRepository.getFormBFileNo(proponentApplications.getProposal_id());
        
        	fcStateStageClearanceCertificate.setRecommendedArea(area);
        	
        	if (fileNo != null){
        		fcStateStageClearanceCertificate.setFileNo(fileNo);
        	}   
		}else if(formType.equalsIgnoreCase("FC_FORM_C")) {
			area = fcMinistryStageClearanceCertificateRepository.getFormCArea(proponentApplications.getProposal_id());
        	fileNo = fcMinistryStageClearanceCertificateRepository.getFormCFileNo(proponentApplications.getProposal_id());
        	
        	//if(area != 0.0d) {
        	fcStateStageClearanceCertificate.setRecommendedArea(area);
        	//}
        	if (fileNo != null){
        		fcStateStageClearanceCertificate.setFileNo(fileNo);
        	}   
		}else if(formType.equalsIgnoreCase("FC_FORM_D")) {
			area = fcMinistryStageClearanceCertificateRepository.getFormDArea(proponentApplications.getProposal_id());
        	fileNo = fcMinistryStageClearanceCertificateRepository.getFormDFileNo(proponentApplications.getProposal_id());
        	log.info(area.toString());
        	//if(area != 0.0d) {
        	fcStateStageClearanceCertificate.setRecommendedArea(area);
        	//}
        	if (fileNo != null){
        		fcStateStageClearanceCertificate.setFileNo(fileNo);
        	}   
		}else if(formType.equalsIgnoreCase("FC_FORM_E")) {
			area = fcMinistryStageClearanceCertificateRepository.getFormEArea(proponentApplications.getProposal_id(), proposalNo);
        	//fileNo = fcMinistryStageClearanceCertificateRepository.getFormAFileNo(proponentApplications.getProposal_id(), proposalNo, "SUBMIT");
			fcStateStageClearanceCertificate.setRecommendedArea(area);    	 
		}else {
			area = fcMinistryStageClearanceCertificateRepository.getArea(proponentApplications.getProposal_id());
        	//fileNo = fcMinistryStageClearanceCertificateRepository.getFormAFileNo(proponentApplications.getProposal_id(), proposalNo, "SUBMIT");
        	
			fcStateStageClearanceCertificate.setRecommendedArea(area);
        	  
		}
        
        //Adding state address			
        String address_str = fcMinistryStageClearanceCertificateRepository.getStateOffice( stateRepository.getStateByCode(  proponentApplications.getState_id() ).get().getId() , officeType);
		String address = address_str != null ? address_str.replaceAll(",+", ",") : null;
        
		fcStateStageClearanceCertificate.setStateGovernmentOfficeAddress(address);
		fcStateStageClearanceCertificate.setThreshold(fcMinistryStageClearanceCertificateRepository.getThreshold(propId));
		fcStateStageClearanceCertificate.setStateGovernmentOfficeName(stateRepository.getByStateCode(proponentApplications.getState_id()).getName());
        /**********New Changes End**********/
        
        return fcStateStageClearanceCertificate;
    }


}
