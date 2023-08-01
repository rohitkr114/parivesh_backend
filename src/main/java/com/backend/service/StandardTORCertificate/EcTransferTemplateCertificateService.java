package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.backend.model.StandardTORCertificate.EcMiningTransferCertificate;
import com.backend.model.StandardTORCertificate.EcTorAmendmentCertificate;
import com.backend.model.StandardTORCertificate.EcTransferTemplateCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcMiningTransferCertificateRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcTransferTemplateCertificateRepository;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class EcTransferTemplateCertificateService {

    @Autowired
    EcTransferTemplateCertificateRepository ecTransferTemplateCertificateRepository;

    @Autowired
    Environment environment;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;
    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;
    public Object saveEcTransferTemplateCertificate(UserPrincipal principal, EcTransferTemplateCertificate ecTransferTemplateCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for EcTransferTemplateCertificate========================");
        EcTransferTemplateCertificate ecTransferTemplateCertificateResult = null;
        //Maintaining History
        if (ecTransferTemplateCertificateRepository.existsById(ecTransferTemplateCertificate.getId())) {
            log.info("==================One Record is already exist for TOR Amendment Certificate========================");
            Optional<EcTransferTemplateCertificate> ecTransferTemplateCertificate1 = ecTransferTemplateCertificateRepository.findById(ecTransferTemplateCertificate.getId());
            ecTransferTemplateCertificateResult = ecTransferTemplateCertificate1.get();
            if (ecTransferTemplateCertificateResult.getCreated_by().equals(principal.getId())) {
                ecTransferTemplateCertificate.setIsActive(true);
                ecTransferTemplateCertificate.setId(ecTransferTemplateCertificateResult.getId());
            } else {
                ecTransferTemplateCertificateResult.setIsActive(false);
                ecTransferTemplateCertificateRepository.save(ecTransferTemplateCertificateResult);
                ecTransferTemplateCertificate.setId(null);
                int version = ObjectUtils.isEmpty(ecTransferTemplateCertificate.getVersion()) ? 1 : ecTransferTemplateCertificate.getVersion() + 1;
                ecTransferTemplateCertificate.setIsActive(true);
                ecTransferTemplateCertificate.setVersion(version);
            }
        }
        log.info("==================Saved TOR Amendment Certificate========================");
        int version = ObjectUtils.isEmpty(ecTransferTemplateCertificate.getVersion()) ? 1 : ecTransferTemplateCertificate.getVersion() + 1;
        ecTransferTemplateCertificate.setIsActive(true);
        ecTransferTemplateCertificate.setVersion(version);

        Optional.ofNullable(ecTransferTemplateCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecTransferTemplateCertificate.getProponentId(), ecTransferTemplateCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecTransferTemplateCertificate.getProponentId() + " proposal no: " + ecTransferTemplateCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecTransferTemplateCertificate.getProponentId() + " proposal no: " + ecTransferTemplateCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecTransferTemplateCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_transfer" + convertedProposalNo + "_" + ecTransferTemplateCertificate.getProponentId(), "pdf");

        //if (ecTransferTemplateCertificate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath = FileUploadUtil.generatePdfFromHtml(ecTransferTemplateCertificate.getHtmlContent(),
                    folderDir, fileName,ecTransferTemplateCertificate.getProposal_No());
        //}
        ecTransferTemplateCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecTransferTemplateCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proponentApplicationRepository.save(proponentApplications);

        return ecTransferTemplateCertificateRepository.save(ecTransferTemplateCertificate);
    }

    public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String proposalNo) {
        log.info("==============================getting EC detail by ProId===========================");
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
        EcTransferTemplateCertificate ecTransferTemplateCertificate = ecTransferTemplateCertificateRepository
                .getEcTransferTemplateInfoBytPropId(propId, proposalNo);
        

        if (!ObjectUtils.isEmpty(ecTransferTemplateCertificate)) {
            BeanUtils.copyProperties(ecTransferTemplateCertificate, proposalDetailInfo);
            return proposalDetailInfo;
        }
        String certificateName="ec_transfer";
        return certProposalDetailInfoCommonService.getDetailsFromDTO(proposalDetailInfo, proposalNo, propId,certificateName);
    }


}
