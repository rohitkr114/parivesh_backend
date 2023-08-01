package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.backend.model.StandardTORCertificate.SpecificTorCertificate;
import com.backend.model.StandardTORCertificate.StandardTorCertificate;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StandardTorCertificate.SpecificTorCertificateRepository;

import com.backend.util.FileUploadUtil;
import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
public class SpecificTORCertificateService {

    @Autowired
    SpecificTorCertificateRepository specificTorCertificateRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    Environment environment;

    @Transactional
    public Object saveSpecificTorCertificate(UserPrincipal principal,SpecificTorCertificate specificTorCertificate,
                                             HttpServletRequest request, HttpServletResponse response) throws DocumentException, IOException, JRException {

        log.info("==================Calling Save API for Standard tor Certificate========================");
        SpecificTorCertificate specificTorCertificateResult = null;
        //Maintaining History
        if (specificTorCertificateRepository.existsById(specificTorCertificate.getId())) {
            log.info("==================One Record is already exist for Standard tor Certificate========================");
            Optional<SpecificTorCertificate> specificTorCertificate1 = specificTorCertificateRepository.findById(specificTorCertificate.getId());
            specificTorCertificateResult = specificTorCertificate1.get();
            if (specificTorCertificateResult.getCreated_by().equals(principal.getId())) {
                specificTorCertificate.setIsActive(true);
                specificTorCertificate.setId(specificTorCertificateResult.getId());
            } else {
                specificTorCertificateResult.setIsActive(false);
                specificTorCertificateRepository.save(specificTorCertificateResult);
                specificTorCertificate.setId(null);
                int version = ObjectUtils.isEmpty(specificTorCertificate.getVersion()) ? 1 : specificTorCertificate.getVersion() + 1;
                specificTorCertificate.setIsActive(true);
                specificTorCertificate.setVersion(version);
            }
        }
        log.info("==================Saved Standard tor Certificate========================");
        int version = ObjectUtils.isEmpty(specificTorCertificate.getVersion()) ? 1 : specificTorCertificate.getVersion()+1;
        specificTorCertificate.setIsActive(true);
        specificTorCertificate.setVersion(version);

        ProponentApplications proponentApplications = proponentApplicationRepository
                .getProponentByIdAndProposalNo(specificTorCertificate.getProponentId(), specificTorCertificate.getProposal_No())
                .orElseThrow(() -> new PariveshException("Data is not found for proponent id: "
                        + specificTorCertificate.getProponentId() + " proposal no: " + specificTorCertificate.getProposal_No()));
        String folderDir = null;
        try {
            CommonFormDetail commonFormDetail = commonFormDetailRepository.getById(proponentApplications.getCaf_id());
            String clearanceTypeId = proponentApplications.getClearance_id().toString();
            String userAgencyID = commonFormDetail.getOrganization_name().replace(" ", "_"); //this part still not clear
            String proposalId = proponentApplications.getProposal_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);

        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent id: "
                    + specificTorCertificate.getProponentId() + " proposal no: " + specificTorCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(specificTorCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("specific_tor_" + convertedProposalNo + "_" + specificTorCertificate.getProponentId(), "pdf");

        //if (standardTorCertificate.getStatus().equalsIgnoreCase("complete")) {
        //File path generation code this could be common
        String filePath = FileUploadUtil.generatePdfFromHtml(specificTorCertificate.getHtmlContent(), folderDir,
                fileName, specificTorCertificate.getProposal_No());
        //}
        specificTorCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        specificTorCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //Commented by Ashish
        //proponentApplications.setCertificate_url(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        //proponentApplicationRepository.save(proponentApplications);
        return specificTorCertificateRepository.save(specificTorCertificate);
    }

    public Object getSpecificTorData(int certId, String proposalNo) {
        return specificTorCertificateRepository.findById(certId);
    }
}
