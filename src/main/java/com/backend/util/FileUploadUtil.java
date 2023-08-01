package com.backend.util;

import com.backend.client.DocumentClient;
import com.backend.constant.AppConstant;
import com.backend.model.DocumentDetails;
import com.backend.model.upload.UploadModel;
import com.backend.repository.postgres.DocumentDetailsRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.layout.Html2PdfProperty;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.font.FontProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static com.backend.constant.AppConstant.CERTIFICATE_SERVER_PATH;

@Slf4j
@Component
public class FileUploadUtil {

    @Autowired
    Environment environment;

    @Autowired
    DocumentDetailsRepository detailsRepository;

    @Autowired
    private DocumentClient documentClient;

    private static String CERT_PATH_SERVER_DIR;
    private static DocumentDetailsRepository detailsRepositoryStatic;

    private static DocumentClient documentClientstatic;

    @PostConstruct
    public void runAfterObjectCreated() {
        CERT_PATH_SERVER_DIR = environment.getProperty(CERTIFICATE_SERVER_PATH);
        detailsRepositoryStatic = detailsRepository;
        documentClientstatic=documentClient;
    }


    //public static final String CERT_PATH_LOCAL_DIR = "C:\\Users\\CT 18-02\\Desktop\\ey-local\\apache-tomcat-9.0.62\\webapps\\certificate\\";
    public static String saveFile(UploadModel model, MultipartFile multipartFile) throws Exception {
        log.info("Uploading file: Dir:-" + model.getPath() + " File Name: " + model.getFileName());

        Path uploadPath = Paths.get(CERT_PATH_SERVER_DIR + model.getPath()).toAbsolutePath().normalize();
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(model.getFileName());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new Exception("Could not save file: " + model.getFileName(), e);
        }

        return model.getPath() + "/" + model.getFileName();
    }

    public static String generatePdfFromHtml(String html, String filePath, String fileName, String moefccFileNumber) throws IOException {
        Path finalPath = null;

        Path uploadPath = Paths.get(CERT_PATH_SERVER_DIR + filePath).toAbsolutePath().normalize();

        Scanner s = new Scanner(html).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        finalPath = uploadPath.resolve(fileName);
        String FONTS = "src/main/resources/font/BOOKOS.woff";
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(finalPath.toString()))) {
            ConverterProperties properties = new ConverterProperties();
            FontProvider fontProvider = new DefaultFontProvider();
            fontProvider.addDirectory(FONTS);
            properties.setFontProvider(fontProvider);
            HtmlConverter.convertToPdf(result, out, properties);
            log.info("PDF path:" + CERT_PATH_SERVER_DIR + filePath + "/" + fileName);
            String address = CERT_PATH_SERVER_DIR + filePath + "/" + fileName;
            Integer docId = detailsRepositoryStatic.getDocIdByProposalNo(moefccFileNumber);
            log.info(String.valueOf(docId));
            if (docId != null) {
                FileUploadUtil fileUploadUtil = new FileUploadUtil();
                fileUploadUtil.getAdditionalDocument(address, docId,filePath,fileName);
            }
            PageNumberUtil.manipulatePdf(CERT_PATH_SERVER_DIR + filePath + "/" + fileName, moefccFileNumber);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return filePath + "/" + fileName;
    }

    public static String folderDirMaker(String userAgencyId, String clearanceType, String proposalId) {
        return String.format("%s/%s/%s", userAgencyId, clearanceType, proposalId);
    }

    public static String fileNameMaker(String initials, String extension) {
        return initials + "_" + "." + extension;
    }


    public static String saveFileUsingStream(UploadModel model, InputStream inputStream) throws Exception {
        log.info("Uploading file: Dir:-" + model.getPath() + " File Name: " + model.getFileName());

        Path uploadPath = Paths.get(CERT_PATH_SERVER_DIR + model.getPath()).toAbsolutePath().normalize();
        log.info("QR code: Service " + uploadPath.toString());
        log.info("QR code: Service---- absolute path " + uploadPath.toAbsolutePath().normalize());
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        Path filePath = null;
        try {
            filePath = uploadPath.resolve(model.getFileName());
            log.info("QR code: Service---- file path " + filePath.toString());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error(e.getLocalizedMessage());
            e.printStackTrace();
            throw new Exception("Could not save file: " + model.getFileName(), e);
        } finally {
            inputStream.close();
        }

        return model.getPath() + "/" + model.getFileName();
    }

    public void getAdditionalDocument(String address, Integer docId,String filePath,String fileName) throws FileNotFoundException {
        try {
            if (docId != null) {
                File file=new File(address);
                String address1=address+"_";
                File rename = new File(address1);
                file.renameTo(rename);
                File newOS = new File(address1);
                FileInputStream fis = new FileInputStream(newOS);
                List<InputStream> file_list = new ArrayList<>();
                file_list.add(fis);
                DocumentDetails doc = detailsRepositoryStatic.getById(docId);
                File addDoc = new File(CERT_PATH_SERVER_DIR + "temp_additional_doc.pdf");
                FileUtils.writeByteArrayToFile(addDoc, documentClientstatic.getDocumentClient(doc.getDocument_mapping_id(),
                        doc.getRef_id(), doc.getType(), doc.getUUID(), doc.getVersion()));
                FileInputStream fis2 = new FileInputStream(addDoc);
                file_list.add(fis2);
                address = CERT_PATH_SERVER_DIR + filePath + "/" + fileName;

                OutputStream output = new FileOutputStream(address);
                MergePdf.mergePdfFiles(file_list, output);
            }
        } catch (IOException e) {
           log.info("failed to merge pdf files due to IOException "+e.getMessage());
        } catch (Exception e) {
            log.info("failed to merge pdf files "+e.getMessage());
        }
    }

    public static String convertSlashIntoUnderscore(String str) {
        return str.replaceAll("/", "_");
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        File file = ResourceUtils.getFile("classpath:images/barcode.png");

//Read File Content
        String content = file.toPath().toString();
        System.out.println(file.toPath());

        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>Page Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>My First Heading</h1>\n" +
                "<p>My first paragraph.</p>\n" +
                "<div style=\"background-image:url('file:/C:\\Users\\CT 18-02\\Desktop\\ey-local\\apache-tomcat-9.0.62\\webapps\\certificate\\barcodes/qr_66873_.png'); background-size: 100%; width: 200px; height: 200px;\">\n" +
                "Barcode image\n" +
                "\t\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
        System.out.println(html);
        generatePdfFromHtml(html, "Prakash_Sinha\\5\\66872", "sample.pdf", "filenumber");
    }
}
