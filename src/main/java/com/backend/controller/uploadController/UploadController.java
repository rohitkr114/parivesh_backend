package com.backend.controller.uploadController;

import com.backend.constant.AppConstant;
import com.backend.model.upload.FileUploadResponse;
import com.backend.model.upload.UploadModel;
import com.backend.response.ResponseHandler;
import com.backend.service.QRCode.QRCodeService;
import com.backend.util.FileUploadUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    Environment environment;

    @PostMapping("/save-file")
    public ResponseEntity<Object> uploadCertificate (@RequestPart("dir") String model,
                                                                 @RequestPart("file") MultipartFile multipartFile) throws Exception {

        log.info("Called Upload controller for saving file");
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        long size = multipartFile.getSize();
        ObjectMapper mapper = new ObjectMapper();
        UploadModel uploadModel = mapper.readValue(model, UploadModel.class);

        String downloadUrl = FileUploadUtil.saveFile(uploadModel, multipartFile);
        URI uri = new URI(environment.getProperty(AppConstant.CERTIFICATE_URL) + downloadUrl);
        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDownloadUrl(uri.toString());

       return ResponseHandler.generateResponse("Upload File", HttpStatus.OK, "",
                response);
    }
}
