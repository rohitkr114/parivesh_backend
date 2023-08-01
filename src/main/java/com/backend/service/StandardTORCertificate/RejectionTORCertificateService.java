package com.backend.service.StandardTORCertificate;


import com.backend.constant.AppConstant;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.CommonFormDetail;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.model.StandardTORCertificate.RejectionTorCertificate;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StandardTorCertificate.RejectionTorCertificateRepository;
import com.backend.util.FileUploadUtil;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class RejectionTORCertificateService {

    @Autowired
    RejectionTorCertificateRepository rejectionTorCertificateRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    Environment environment;

    @Transactional
    public Object saveRejectionTorCertificate(UserPrincipal principal, RejectionTorCertificate rejectionTorCertificate, HttpServletRequest request) throws DocumentException, IOException {
        log.info("==================Calling Save API for Rejection tor Certificate========================");
        RejectionTorCertificate rejectionTorCertificateResult = null;
        if (rejectionTorCertificateRepository.existsById(rejectionTorCertificate.getId())) {
            log.info("==================One Record is already exist for Rejection tor Certificate========================");
            Optional<RejectionTorCertificate> rejectionTorCertificate1 = rejectionTorCertificateRepository.findById(rejectionTorCertificate.getId());
            rejectionTorCertificateResult = rejectionTorCertificate1.get();
            if (rejectionTorCertificateResult.getCreated_by().equals(principal.getId())) {
                rejectionTorCertificate.setIsActive(true);
                rejectionTorCertificate.setId(rejectionTorCertificateResult.getId());
            } else {
                rejectionTorCertificateResult.setIsActive(false);
                rejectionTorCertificateRepository.save(rejectionTorCertificateResult);
                rejectionTorCertificate.setId(null);
                int version = ObjectUtils.isEmpty(rejectionTorCertificate.getVersion()) ? 1 : rejectionTorCertificate.getVersion() + 1;
                rejectionTorCertificate.setIsActive(true);
                rejectionTorCertificate.setVersion(version);
            }
        }
        log.info("==================Saved Rejection tor Certificate========================");
        int version = ObjectUtils.isEmpty(rejectionTorCertificate.getVersion()) ? 1 : rejectionTorCertificate.getVersion() + 1;
        rejectionTorCertificate.setIsActive(true);
        rejectionTorCertificate.setVersion(version);

        EnvironmentClearence environmentClearence = environmentClearenceRepository.findByProposalNo(rejectionTorCertificate.getProposal_No());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(rejectionTorCertificate.getProponentId(), rejectionTorCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + rejectionTorCertificate.getProponentId() + " proposal no: " + rejectionTorCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + rejectionTorCertificate.getProponentId() + " proposal no: " + rejectionTorCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(rejectionTorCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("rejection_tor_" + convertedProposalNo + "_" + rejectionTorCertificate.getProponentId(), "pdf");

        //if (rejectionTorCertificate.getStatus().equalsIgnoreCase("complete")) {
            //File path generation code this could be common
            String filePath = FileUploadUtil.generatePdfFromHtml(rejectionTorCertificate.getHtmlContent(), folderDir,
                    fileName,rejectionTorCertificate.getProposal_No());
       // }
        rejectionTorCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        rejectionTorCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName); //need to integrate barcode API
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);
        return rejectionTorCertificateRepository.save(rejectionTorCertificate);
    }


    public Object getDataForCertificate(Integer id, HttpServletRequest request) {
        return rejectionTorCertificateRepository.getDataForCertificate(id);
    }
}



