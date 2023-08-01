package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10ProjectDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.StandardTORCertificate.EcExpansionNIPLCertificate;
import com.backend.model.StandardTORCertificate.EcFreshLetterTemplateCertificate;
import com.backend.model.certificate.CertProposalDetailInfo;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.EcForm10Repository.EcForm10ProjectDetailRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StandardTorCertificate.EcExpansionNIPLCertificateRepository;
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
public class EcExpansionNIPLCertificateService {
    @Autowired
    EcExpansionNIPLCertificateRepository ecExpansionNIPLCertificateRepository;

    @Autowired
    CertProposalDetailInfoCommonService certProposalDetailInfoCommonService;
    @Autowired
    Environment environment;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    EcForm10ProjectDetailRepository ecForm10ProjectDetailRepository;

    public Object saveEcExpansionNIPLCertificate(UserPrincipal principal,EcExpansionNIPLCertificate ecExpansionNIPLCertificate, HttpServletRequest request) throws IOException {

        log.info("==================Calling Save API for Standard tor Certificate========================");
        EcExpansionNIPLCertificate ecExpansionNIPLCertificateResult = null;
        //Maintaining History
        if (ecExpansionNIPLCertificateRepository.existsById(ecExpansionNIPLCertificate.getId())) {
            log.info("==================One Record is already exist for Standard tor Certificate========================");
            Optional<EcExpansionNIPLCertificate> ecExpansionNIPLCertificate1
                    = ecExpansionNIPLCertificateRepository.findById(ecExpansionNIPLCertificate.getId());
            ecExpansionNIPLCertificateResult = ecExpansionNIPLCertificate1.get();
            if (ecExpansionNIPLCertificateResult.getCreated_by().equals(principal.getId())) {
                ecExpansionNIPLCertificate.setIsActive(true);
                ecExpansionNIPLCertificate.setId(ecExpansionNIPLCertificateResult.getId());
            } else {
                ecExpansionNIPLCertificateResult.setIsActive(false);
                ecExpansionNIPLCertificateRepository.save(ecExpansionNIPLCertificateResult);
                ecExpansionNIPLCertificate.setId(null);
                int version = ObjectUtils.isEmpty(ecExpansionNIPLCertificate.getVersion()) ? 1 : ecExpansionNIPLCertificate.getVersion() + 1;
                ecExpansionNIPLCertificate.setIsActive(true);
                ecExpansionNIPLCertificate.setVersion(version);
            }
        }
        log.info("==================Saved Standard tor Certificate========================");
        int version = ObjectUtils.isEmpty(ecExpansionNIPLCertificate.getVersion()) ? 1 : ecExpansionNIPLCertificate.getVersion() + 1;
        ecExpansionNIPLCertificate.setIsActive(true);
        ecExpansionNIPLCertificate.setVersion(version);

        Optional.ofNullable(ecExpansionNIPLCertificate.getProposal_No()).orElseThrow(() -> new PariveshException("No proposal no found. This form can not be saved without proposal no."));

        //certProposalDetailInfoCommonService.fileDirectoryGenerate(standardTorCertificate.getProposal_No(),standardTorCertificate.getProponentId());
        //EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(ecTransferOfMiningLeaseAcknowledgmentCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(ecExpansionNIPLCertificate.getProponentId(), ecExpansionNIPLCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + ecExpansionNIPLCertificate.getProponentId() + " proposal no: " + ecExpansionNIPLCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString()  != null ? proponentApplications.getClearance_id().toString() : "" ;
            String userAgencyID = commonFormDetail.getOrganization_name() !=null ? commonFormDetail.getOrganization_name().replace(" ", "_"): "" ;
            String proposalId = proponentApplications.getProposal_id().toString() != null ? proponentApplications.getProposal_id().toString(): "";
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + ecExpansionNIPLCertificate.getProponentId() + " proposal no: " + ecExpansionNIPLCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(ecExpansionNIPLCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("ec_expansion_nipl" + convertedProposalNo + "_" + ecExpansionNIPLCertificate.getProponentId(), "pdf");

        //if (ecExpansionNIPLCertificate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath = FileUploadUtil.generatePdfFromHtml(ecExpansionNIPLCertificate.getHtmlContent(),
                    folderDir, fileName,ecExpansionNIPLCertificate.getProposal_No());
        //}
        ecExpansionNIPLCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        ecExpansionNIPLCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        proponentApplicationRepository.save(proponentApplications);

        return ecExpansionNIPLCertificateRepository.save(ecExpansionNIPLCertificate);
    }

    public CertProposalDetailInfo getStandTorDetailByPropId(int propId, String proposalNo, String name) {
        log.info("==============================getting EcFreshLetterTemplateCertificate detail by ProId===========================");
        CertProposalDetailInfo proposalDetailInfo = new CertProposalDetailInfo();
        EcExpansionNIPLCertificate ecExpansionNIPLCertificate = ecExpansionNIPLCertificateRepository
                .getEcExpansionNIPLCertificateInfoBytPropId(propId, proposalNo);

        if (!ObjectUtils.isEmpty(ecExpansionNIPLCertificate)) {
            BeanUtils.copyProperties(ecExpansionNIPLCertificate, proposalDetailInfo);
            return proposalDetailInfo;
        }
        Optional<ProponentApplications> proponentApplications= proponentApplicationRepository.findById(propId);
        ProponentApplications proponentApplications1= proponentApplications.orElseThrow(() ->
                new PariveshException("No record found for proponent application for this proponent id "
                + propId));

        Optional<EcForm10ProjectDetails> ecForm10ProjectDetails = ecForm10ProjectDetailRepository.findById(proponentApplications1.getProposal_id());
        EcForm10ProjectDetails ecForm10ProjectDetails1 = ecForm10ProjectDetails.orElseThrow(() ->
        new PariveshException("No record found for form 10 for this id "
                + proponentApplications1.getProposal_id()));

        proposalDetailInfo.setCompanyname(ecForm10ProjectDetails1.getName());
        proposalDetailInfo.setNameOfProject(ecForm10ProjectDetails1.getProject_name());
        proposalDetailInfo.setForm10Id(ecForm10ProjectDetails1.getId());
        proposalDetailInfo.setProposal_No(proposalNo);
        proposalDetailInfo.setProponentId(propId);
        proposalDetailInfo.setCafId(proponentApplications1.getCaf_id());


        return proposalDetailInfo;
    }
}
