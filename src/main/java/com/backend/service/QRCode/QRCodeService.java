package com.backend.service.QRCode;


import com.backend.model.upload.FileUploadResponse;
import com.backend.model.upload.UploadModel;
import com.backend.util.FileUploadUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

@Service
@Slf4j
public class QRCodeService {

    public String generateQRCodeImage( String filePath, String content)
            throws Exception {

        log.info("Bar code URL: " + content);
        log.info("Bar code file path: " + filePath);
        ImageIO.scanForPlugins();
        String pathToStore = filePath;
        final int GREEN = 0xFF37cc36;

        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 150, 150,
                ImmutableMap.of(com.google.zxing.EncodeHintType.MARGIN, 1));
        MatrixToImageConfig imageConfig = new MatrixToImageConfig(GREEN, MatrixToImageConfig.WHITE);
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, imageConfig);

        // Getting logo image
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("parivesh-logo/Parivesh.png");

        BufferedImage logoImage = ImageIO.read(is);
        int finalImageHeight = qrImage.getHeight() - logoImage.getHeight();
        int finalImageWidth = qrImage.getWidth() - logoImage.getWidth();

        // Merging both images
        BufferedImage finalImage = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) finalImage.getGraphics();
        graphics.drawImage(qrImage, 0, 0, null);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        graphics.drawImage(logoImage, (int) Math.round(finalImageWidth * 2 - 70), (int) Math.round(finalImageHeight * 2 - 30), finalImageWidth / 2, finalImageWidth / 2, null);

        //ImageIO.write(finalImage, "png", new File(pathToStore));

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(finalImage, "png", os);                          // Passing: ​(RenderedImage im, String formatName, OutputStream output)
        InputStream inputStream = new ByteArrayInputStream(os.toByteArray());

        UploadModel model = new UploadModel();
        model.setFileName(filePath);
        model.setPath("barcodes");
        String finalPath = FileUploadUtil.saveFileUsingStream(model, inputStream);
        return finalPath;
    }

    private FileUploadResponse callUpload(InputStream image, String fileName) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("file", image);
        body.add("dir", "{\"fileName\":" + "\""+ fileName + "\"" + ", \"path\":\"/barcode/\"}");

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        String serverUrl = "https://stgdev.parivesh.nic.in/parivesh2_dev/upload/save-file";

        RestTemplate restTemplate = new RestTemplate();
        FileUploadResponse fileUploadResponse = new FileUploadResponse();
        ResponseEntity<String> result = restTemplate.postForEntity(serverUrl, requestEntity, String.class);
        HttpStatus status = result.getStatusCode();
        if (HttpStatus.OK == status) {
            String response = result.getBody();
            fileUploadResponse = mapper.readValue(response, FileUploadResponse.class);
        }

        return fileUploadResponse;
    }

}
