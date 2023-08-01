package com.backend.controller.QRCode;

import com.backend.constant.AppConstant;
import com.backend.model.StandardTORCertificate.BarCodeBody;
import com.backend.response.ResponseHandler;
import com.backend.service.QRCode.QRCodeService;
import com.backend.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping(value = "/qr-code")
@Slf4j
public class QRCodeController {

    @Autowired
    Environment environment;

    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping(value = "/build-qr")
    public Object download(
             @RequestParam("proposalId") Integer proposalId, @RequestParam("proposalNo")String proposalNo, @RequestBody BarCodeBody folderDir)
            throws Exception {
        log.info("QR code generation for proposal id: " + proposalId + " Proposal no: " + proposalNo);

        String fileName = fileNameMaker ();
        String modifiedProposalNo = FileUploadUtil.convertSlashIntoUnderscore(proposalNo);
        //String redirectPDFName = FileUploadUtil.fileNameMaker("certificate_" + modifiedProposalNo + "_" +proposalId, "pdf");
        //URI redirectUrl = new URI(environment.getProperty(AppConstant.CERTIFICATE_URL) + folderDir.getFolderDir() + "/" + redirectPDFName);
        String path = qrCodeService.generateQRCodeImage(fileName, folderDir.getFolderDir());
        log.info("QR code: " + fileName + " Folder dir: " + folderDir.getFolderDir());

        URI uri = new URI(environment.getProperty(AppConstant.CERTIFICATE_URL) + path);

        return ResponseHandler.generateResponse("QR Code path Generated", HttpStatus.OK, "", uri.toString());
    }

    private String fileNameMaker () {
        DateTimeFormatter timeStampPattern = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return FileUploadUtil.fileNameMaker("qr_code_" + timeStampPattern.format(java.time.LocalDateTime.now()).toString(), "png");
    }


}
