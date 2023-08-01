package com.backend.util;

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PageNumberingStamper extends PdfPageEventHelper {
    private String fileNumber = "";
    private Integer totalPages = 0;

    public PageNumberingStamper(String fileNumber) {
        this.fileNumber = fileNumber;
    }
    public PageNumberingStamper(String fileNumber, Integer totalPages) {
    	this.fileNumber = fileNumber;
    	this.totalPages = totalPages;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        final int currentPageNumber = writer.getCurrentPageNumber();


        try {
            final Rectangle pageSize = document.getPageSize();

            final PdfContentByte line = writer.getDirectContent();
            line.setGrayStroke(3);
            //Footer address
            final PdfContentByte footerAddress1 = writer.getDirectContent();
            footerAddress1.setColorFill(BaseColor.GRAY);
            footerAddress1.setFontAndSize(BaseFont.createFont(), 8);
            footerAddress1.setTextMatrix(pageSize.getLeft(180), pageSize.getBottom(30));
            if (!fileNumber.startsWith("FP")){
                footerAddress1.newlineShowText("Address: IA Division, Ministry of Environment, Forest and Climate Change,");
            }

            final PdfContentByte footerAddress2 = writer.getDirectContent();
            footerAddress2.setColorFill(BaseColor.GRAY);
            footerAddress2.setFontAndSize(BaseFont.createFont(), 8);
            footerAddress2.setTextMatrix(pageSize.getLeft(210), pageSize.getBottom(20));
            if (!fileNumber.startsWith("FP")) {
                footerAddress2.newlineShowText("Indira Paryavaran Bhawan, Jor Bagh New Delhi - 110003");
            }


            //Page Number
            final PdfContentByte directContent = writer.getDirectContent();
            directContent.setColorFill(BaseColor.BLACK);
            directContent.setFontAndSize(BaseFont.createFont(), 10);
            directContent.setTextMatrix(pageSize.getRight(80), pageSize.getBottom(30));
            if(totalPages != 0) {
                directContent.showText(String.valueOf("Page "+ currentPageNumber + " of " + totalPages));
            }else {
                directContent.showText(String.valueOf("Page | "+ currentPageNumber));
            }
            
            
            //File Number
            final PdfContentByte fileNumberFooter = writer.getDirectContent();
            fileNumberFooter.setColorFill(BaseColor.BLACK);
            fileNumberFooter.setFontAndSize(BaseFont.createFont(), 10);
            fileNumberFooter.setTextMatrix(pageSize.getLeft(30), pageSize.getBottom(30));
            fileNumberFooter.showText(String.valueOf(fileNumber));

        } catch (DocumentException | IOException e) {
            log.error("PDF generation error", e);
        }
    }
}


