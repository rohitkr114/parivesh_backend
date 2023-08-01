package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.backend.model.StandardTORCertificate.EcMiningExpansionTemplate;
import com.backend.model.StandardTORCertificate.EcMiningExtensionofValidityTemplateCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcMiningExtensionOfValidityTemplateCertificateRepository;
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

@Service
@Slf4j
public class EcMiningExtensionofValidityTemplateCertificateService {


    @Autowired
    EcMiningExtensionOfValidityTemplateCertificateRepository ecMiningExtensionofValidityTemplateCertificateRepository;

    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;

    @Autowired
    Environment environment;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    public Object saveEcMiningExtensionofValidityTemplateCertificate(UserPrincipal principal, EcMiningExtensionofValidityTemplateCertificate ecMiningExtensionofValidityTemplateCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for Standard tor Certificate========================");
        EcMiningExtensionofValidityTemplateCertificate ecMiningExtensionofValidityTemplateCertificateResult = null;
        //Maintaining History
        if (ecMiningExtensionofValidityTemplateCertificateRepository.existsById(ecMiningExtensionofValidityTemplateCertificate.getId())) {
            log.info("==================One Record is already exist for Standard tor Certificate========================");
            Optional<EcMiningExtensionofValidityTemplateCertificate> ecMiningExtensionofValidityTemplateCertificate1
                    = ecMiningExtensionofValidityTemplateCertificateRepository.findById(ecMiningExtensionofValidityTemplateCertificate.getId());
            ecMiningExtensionofValidityTemplateCertificateResult = ecMiningExtensionofValidityTemplateCertificate1.get();

            if (ecMiningExtensionofValidityTemplateCertificateResult.getCreated_by().equals(principal.getId())) {
                ecMiningExtensionofValidityTemplateCertificate.setIsActive(true);
                ecMiningExtensionofValidityTemplateCertificate.setId(ecMiningExtensionofValidityTemplateCertificateResult.getId());
            } else {
                ecMiningExtensionofValidityTemplateCertificateResult.setIsActive(false);
                ecMiningExtensionofValidityTemplateCertificateRepository.save(ecMiningExtensionofValidityTemplateCertificateResult);
                ecMiningExtensionofValidityTemplateCertificate.setId(null);
                int version = ObjectUtils.isEmpty(ecMiningExtensionofValidityTemplateCertificate.getVersion()) ? 1 : ecMiningExtensionofValidityTemplateCertificate.getVersion() + 1;
                ecMiningExtensionofValidityTemplateCertificate.setIsActive(true);
                ecMiningExtensionofValidityTemplateCertificate.setVersion(version);
            }
        }
        log.info("==================Saved Standard tor Certificate========================");
        int version = ObjectUtils.isEmpty(ecMiningExtensionofValidityTemplateCertificate.getVersion()) ? 1 : ecMiningExtensionofValidityTemplateCertificate.getVersion() + 1;
        ecMiningExtensionofValidityTemplateCertificate.setIsActive(true);
        ecMiningExtensionofValidityTemplateCertificate.setVersion(version);

        Optional.ofNullable(ecMiningExtensionofValidityTemplateCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecMiningExtensionofValidityTemplateCertificate.getProponentId(), ecMiningExtensionofValidityTemplateCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecMiningExtensionofValidityTemplateCertificate.getProponentId() + " proposal no: " + ecMiningExtensionofValidityTemplateCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecMiningExtensionofValidityTemplateCertificate.getProponentId() + " proposal no: " + ecMiningExtensionofValidityTemplateCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecMiningExtensionofValidityTemplateCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_mining_extension_of_validity" + convertedProposalNo + "_" + ecMiningExtensionofValidityTemplateCertificate.getProponentId(), "pdf");

        //if (ecMiningExtensionofValidityTemplateCertificate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath =
                    FileUploadUtil.generatePdfFromHtml(ecMiningExtensionofValidityTemplateCertificate.getHtmlContent(), folderDir, fileName,ecMiningExtensionofValidityTemplateCertificate.getProposal_No());
        //}
        ecMiningExtensionofValidityTemplateCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecMiningExtensionofValidityTemplateCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);
        return ecMiningExtensionofValidityTemplateCertificateRepository.save(ecMiningExtensionofValidityTemplateCertificate);
    }

    public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String proposalNo) {
        log.info("==============================getting EC detail by ProId===========================");
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
        EcMiningExtensionofValidityTemplateCertificate ecMiningExtensionofValidityTemplateCertificate = ecMiningExtensionofValidityTemplateCertificateRepository
                .getEcMiningExtensionOfValidityTemplateCertificateInfoBytPropId(propId, proposalNo);

        if (!ObjectUtils.isEmpty(ecMiningExtensionofValidityTemplateCertificate)) {
            BeanUtils.copyProperties(ecMiningExtensionofValidityTemplateCertificate, proposalDetailInfo);
            return proposalDetailInfo;
        }
        String certificateName="ec_mining_extension_of_validity";
        return certProposalDetailInfoCommonService.getDetailsFromDTO(proposalDetailInfo, proposalNo, propId,certificateName);
    }

}
