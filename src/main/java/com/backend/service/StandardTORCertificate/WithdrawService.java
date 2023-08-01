package com.backend.service.StandardTORCertificate;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.WithdrawCertificate;
import com.backend.repository.postgres.StandardTorCertificate.WithdrawCertificateRepo;
import com.backend.response.ResponseHandler;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Service
public class WithdrawService {
    @Autowired
    WithdrawCertificateRepo withdrawCertificateRepo;
    @Autowired
    Environment environment;
    @Transactional
    public Object saveWithdrawCertificate(WithdrawCertificate withdrawCertificate,
                                          HttpServletRequest request, HttpServletResponse response) throws IOException, PariveshException {
        log.info("Calling Save API for Withdraw Certificate for proposal:{} ", withdrawCertificate.getProposal_No());


        String folderDir = null;
        try {
            String clearanceTypeId = "withdraw";
            String userAgencyID = withdrawCertificate.getCompanyname();
            String proposalId = withdrawCertificate.getApplication_id().toString();
            folderDir = FileUploadUtil.folderDirMaker(userAgencyID, clearanceTypeId, proposalId);
        } catch (Exception e) {
            throw new PariveshException("Data is not found for proponent  no: " + withdrawCertificate.getProposal_No());
        }

        String convertedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(withdrawCertificate.getProposal_No());
        String fileName = FileUploadUtil.fileNameMaker("withdraw_certificate" + convertedProposalNo + withdrawCertificate.getApplication_id(), "pdf");

        //File path generation code this could be common
        String filePath = FileUploadUtil.generatePdfFromHtml(withdrawCertificate.getHtmlContent(), folderDir,
                fileName, withdrawCertificate.getProposal_No());

        withdrawCertificate.setFilePath(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);
        withdrawCertificate.setFolderDir(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir + "/" + fileName);

        return withdrawCertificateRepo.save(withdrawCertificate);
    }

    public ResponseEntity<Object> get(String proposalNo) {
        log.info("WithdrawService::get Proposal No {}", proposalNo);

        WithdrawCertificate withdrawCertificate = withdrawCertificateRepo.getByProposalNo(proposalNo);
        if (withdrawCertificate == null) {
            WithdrawCertificate withdrawCertificate1 = new WithdrawCertificate();
            withdrawCertificate1.setWithdrawRequestDate(withdrawCertificateRepo.getWithdrawData(proposalNo));
            return ResponseHandler.generateResponse("get certificate Detail", HttpStatus.OK, "",
                    withdrawCertificate1);
        } else {
            return ResponseHandler.generateResponse("get certificate Detail", HttpStatus.OK, "",
                    withdrawCertificate);
        }
    }
}
