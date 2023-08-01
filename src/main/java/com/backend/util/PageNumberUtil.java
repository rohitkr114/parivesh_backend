package com.backend.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.Iterator;

import static com.backend.constant.AppConstant.CERTIFICATE_SERVER_PATH;

@Slf4j
@Component
public class PageNumberUtil {

    @Autowired
    Environment environment;

    private static String WATERMARK_LOGO_PATH;
    @PostConstruct
    public void runAfterObjectCreated() {
        WATERMARK_LOGO_PATH = environment.getProperty("WATERMARK_LOGO_PATH");
    }

    public static void manipulatePdf(String pdfSourceURL,String moefccFileNumber) throws Exception {
        String dest = pdfSourceURL;
        log.info(WATERMARK_LOGO_PATH);
        String imageURL =WATERMARK_LOGO_PATH;
        Integer totalPages = 0;
        
        Document document = new Document();
        File file = new File(pdfSourceURL);
        FileInputStream fileInputStream = new FileInputStream(file);
        PdfReader reader = new PdfReader(fileInputStream);
        totalPages = totalPages + reader.getNumberOfPages();
        
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(dest)));
            writer.setPageEvent(new PageNumberingStamper(moefccFileNumber, totalPages));
            document.open();
            PdfContentByte pageContentByte = writer.getDirectContent();
            PdfImportedPage pdfImportedPage;
            int currentPdfReaderPage = 1;
            int pages_total = 0;
            pages_total += reader.getNumberOfPages();
            while (currentPdfReaderPage <= reader.getNumberOfPages()) {
                document.newPage();
                pdfImportedPage = writer.getImportedPage(reader, currentPdfReaderPage);
                pageContentByte.addTemplate(pdfImportedPage, 0, 0);
                currentPdfReaderPage++;
            }
            document.close();
            WatermarkStamper.addWaterMark(pdfSourceURL);
            log.info("Numbering and Water Mark added in PDF Successfully!!");
        } catch (DocumentException e) {
            log.error("Numbering not added in PDF Document Exception!!");
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            log.error("Numbering not added in PDF File Not Found Exception!!");
            throw new RuntimeException(e);
        }
    }
}