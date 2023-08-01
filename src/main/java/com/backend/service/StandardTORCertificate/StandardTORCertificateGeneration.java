package com.backend.service.StandardTORCertificate;

import com.backend.model.StandardTORCertificate.StandardTorCertificate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;

@Slf4j
@Service
public class StandardTORCertificateGeneration {

    public static final String IMG1 = "file:///E:/parivesh/satmave.jpeg" ;
    public static final String IMG2 = "file:///E:/parivesh/Nature.jpeg" ;

    public Object generateCertificateXYZ(StandardTorCertificate standardTorCertificate, HttpServletRequest request) throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4);
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("AddTableExample.pdf"));
            document.open();
            Font fontStyle_Bold =  FontFactory.getFont(FontFactory.COURIER_BOLD, 12f, Font.BOLD);
            Font fontname_Times =  FontFactory.getFont(FontFactory.TIMES,15f, Font.BOLD);
            Font fontStyle =  FontFactory.getFont(FontFactory.TIMES,10f);
            PdfPTable table = new PdfPTable(3); // 3 columns.
            table.setWidthPercentage(95); //Width 100%
            // table.setSpacingBefore(20f); //Space before table
            //table.setSpacingAfter(60f); //Space after table
            String urlOfWaterMarKImage = "file:///E:/parivesh/PARIVESH.jpeg"   ;
            //Get waterMarkImage from some URL
            Image waterMarkImage = Image.getInstance(new URL(urlOfWaterMarKImage));
            //Get width and height of whole page
            float pdfPageWidth = document.getPageSize().getWidth();
            float pdfPageHeight = document.getPageSize().getHeight();
            //Set waterMarkImage on whole page
            writer.getDirectContentUnder().addImage(waterMarkImage,
                    pdfPageWidth, 0, 0, pdfPageHeight, 0, 0);
            //Set Column widths
            float[] columnWidths = {1f, 8f, 1f};
            table.setWidths(columnWidths);

            Image img1 = Image.getInstance(new URL(IMG1));

            PdfPCell cell1Logo1 = new PdfPCell();
            cell1Logo1.setImage(img1);
            cell1Logo1.setBorder(0);
            cell1Logo1.setPaddingLeft(5);
            cell1Logo1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1Logo1.setVerticalAlignment(Element.ALIGN_MIDDLE);

            PdfPCell cellBlankHead1 = new PdfPCell(new Paragraph("  "));
            cellBlankHead1.setBorder(0);
            table.addCell(cellBlankHead1);
            table.addCell(cellBlankHead1);
            table.addCell(cellBlankHead1);


            table.addCell(cell1Logo1);

            PdfPTable nestedHeaderTable = new PdfPTable(1);

            PdfPCell cellGov = new PdfPCell(new Paragraph("Government of India",fontname_Times));
            cellGov.setBorder(0);
            cellGov.setPaddingLeft(5);
            cellGov.setPaddingBottom(9);
            cellGov.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellGov.setVerticalAlignment(Element.ALIGN_MIDDLE);
            nestedHeaderTable.addCell(cellGov);

            PdfPCell cellMinister = new PdfPCell(new Paragraph("Ministry of Environment, Forest and Climate Change",fontname_Times));
            cellMinister.setBorder(0);
            cellMinister.setPaddingLeft(5);
            cellMinister.setPadding(1);
            cellMinister.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellMinister.setVerticalAlignment(Element.ALIGN_MIDDLE);
            nestedHeaderTable.addCell(cellMinister);

            PdfPCell cellGreen = new PdfPCell(new Paragraph("Centralized Processing Centre - Green",fontname_Times));
            cellGreen.setBorder(0);
            cellGreen.setPaddingLeft(10);
            cellGreen.setPadding(1);
            cellGreen.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellGreen.setVerticalAlignment(Element.ALIGN_MIDDLE);
            nestedHeaderTable.addCell(cellGreen);

            PdfPCell nesthousing = new PdfPCell(nestedHeaderTable);
            nesthousing.setBorder(0);
            table.addCell(nesthousing);

            Image img2 = Image.getInstance(new URL(IMG2));
            PdfPCell cell1Logo2 = new PdfPCell();
            cell1Logo2.setImage(img2);
            cell1Logo2.setBorder(0);
            cell1Logo2.setPaddingLeft(10);
            cell1Logo2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell1Logo2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell1Logo2);

            /**  ################ SPACE CELL ################################################# */
            PdfPCell cellspace = new PdfPCell(new Paragraph(""));
            cellspace.setBorder(0);
            cellspace.setPaddingLeft(10);
            cellspace.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellspace.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cellspace);
            table.addCell(cellspace);
            table.addCell(cellspace);

            document.add(table);

            /**  ################  TABLE 2 ################################################# */

            PdfPTable table2 = new PdfPTable(2);
            table2.setWidthPercentage(95);
            float[] columnWidthsOf2Table = {1f, 1f};
            table2.setWidths(columnWidthsOf2Table);

            PdfPTable nestedTableofTable2 = new PdfPTable(1);

            PdfPCell cellBlank = new PdfPCell(new Paragraph("  "));
            cellBlank.setBorder(0);
            cellBlank.setPaddingLeft(10);
            cellBlank.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellBlank.setVerticalAlignment(Element.ALIGN_MIDDLE);
            nestedTableofTable2.addCell(cellBlank);

            PdfPCell cellDate = new PdfPCell(new Paragraph("Date - _____________",fontStyle_Bold));
            cellDate.setBorder(0);
            cellDate.setPaddingLeft(10);
            cellDate.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
            nestedTableofTable2.addCell(cellDate);

            PdfPCell cellBlank1 = new PdfPCell(new Paragraph("  "));
            cellBlank1.setBorder(0);
            cellBlank1.setPaddingLeft(10);
            cellBlank1.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellBlank1.setVerticalAlignment(Element.ALIGN_MIDDLE);
            nestedTableofTable2.addCell(cellBlank1);
            nestedTableofTable2.addCell(cellBlank1);
            nestedTableofTable2.addCell(cellBlank1);

            PdfPCell cellTo = new PdfPCell(new Paragraph("        TO",fontStyle_Bold));
            cellTo.setBorder(0);
            cellTo.setPaddingLeft(10);
            cellTo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellTo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            nestedTableofTable2.addCell(cellTo);

            PdfPCell cellOfTable2 = new PdfPCell(nestedTableofTable2);
            cellOfTable2.setBorder(0);
            table2.addCell(cellOfTable2);

            PdfPCell cellQRCode = new PdfPCell(new Paragraph("QR CODE"));
            cellQRCode.setBorder(0);
            cellQRCode.setPaddingLeft(10);
            cellQRCode.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellQRCode.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2.addCell(cellQRCode);

            document.add(table2);

            /**  ################  TABLE 3 ################################################# */
            PdfPTable table3 = new PdfPTable(1);
            table3.setWidthPercentage(95);
            float[] columnWidthsOf3Table = {10f};
            table3.setWidths(columnWidthsOf3Table);

            PdfPCell cellProponent = new PdfPCell(new Paragraph("        The Proponent "+standardTorCertificate.getProponent(),fontStyle_Bold));
            cellProponent.setBorder(0);
            cellProponent.setPaddingLeft(10);
            cellProponent.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellProponent.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cellProponent);

            PdfPCell cellCompanyName = new PdfPCell(new Paragraph("        Company Name "+standardTorCertificate.getCompanyname(),fontStyle_Bold));
            cellCompanyName.setBorder(0);
            cellCompanyName.setPaddingLeft(10);
            cellCompanyName.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellCompanyName.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cellCompanyName);

            PdfPCell cellAddress = new PdfPCell(new Paragraph("        Registered Address "+standardTorCertificate.getRegisteredAddress(),fontStyle_Bold));
            cellAddress.setBorder(0);
            cellAddress.setPaddingLeft(10);
            cellAddress.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAddress.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cellAddress);

            PdfPCell cellEmail = new PdfPCell(new Paragraph("        Proponent’s Email: "+standardTorCertificate.getProponentEmail(),fontStyle_Bold));
            cellEmail.setBorder(0);
            cellEmail.setPaddingLeft(10);
            cellEmail.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellEmail.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cellEmail);

            PdfPCell cellBlank2 = new PdfPCell(new Paragraph("  "));
            cellBlank2.setBorder(0);
            cellBlank2.setPaddingLeft(10);
            cellBlank2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellBlank2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table3.addCell(cellBlank2);

            document.add(table3);



            /**  ################  TABLE 4 ################################################# */
            PdfPTable table4 = new PdfPTable(1);
            table4.setWidthPercentage(95);
            float[] columnWidthsOf4Table = {10f};
            table4.setWidths(columnWidthsOf4Table);

            PdfPCell cellSub = new PdfPCell();
            Paragraph first = new Paragraph("Sub:", fontStyle_Bold);
            first.add(new Chunk(" Grant of Standard Terms of Reference (ToR) to the proposed Project under the EIA Notification 2006-and as amended thereof -regarding.",fontStyle ));
            cellSub.addElement(first);
            cellSub.setBorder(0);
            cellSub.setPaddingLeft(10);
            cellSub.setPadding(5);
            cellSub.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSub.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellSub);

            PdfPCell cellSpace = new PdfPCell(new Paragraph("   " ));
            cellSpace.setBorder(0);
            cellSpace.setPaddingLeft(10);
            cellSub.setPadding(5);
            cellSpace.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSpace.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellSpace);

            PdfPCell cellSir = new PdfPCell(new Paragraph("Sir,",fontStyle_Bold));
            cellSir.setBorder(0);
            cellSir.setPaddingLeft(10);
            cellSub.setPadding(2);
            cellSir.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSir.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellSir);

            PdfPCell cellContent = new PdfPCell(new Paragraph("            This is in reference to your application submitted to Ministry vide proposal number [Unique Clearance Number] dated [Date of Submission] for grant of Terms of Reference (ToR) to the project under the provision of the EIA Notification 2006-and as amended thereof. The particulars of the proposal are as below:",fontStyle));
            cellContent.setBorder(0);
            cellContent.setPaddingLeft(10);
            cellSub.setPadding(5);
            cellContent.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellContent.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table4.addCell(cellContent);

            document.add(table4);

            /**  ################  TABLE 5 ################################################# */

            PdfPTable table5 = new PdfPTable(2);
            table5.setWidthPercentage(95);
            float[] columnWidthsOf5Table = {21f,9f};
            table5.setWidths(columnWidthsOf5Table);

            PdfPCell middelSpace = new PdfPCell(new Paragraph(" "));
            middelSpace.setBorder(0);
            table5.addCell(middelSpace);
            table5.addCell(middelSpace);

            PdfPCell cellIdentification = new PdfPCell(new Paragraph("  i)    ToR Identification No.",fontStyle_Bold));
            cellIdentification.setBorder(0);
            cellIdentification.setPaddingLeft(10);
            cellIdentification.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellIdentification.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellIdentification);

            PdfPCell cellAutoIdentification = new PdfPCell(new Paragraph("[Auto Fill ]",fontStyle_Bold));
            cellAutoIdentification.setBorder(0);
            cellAutoIdentification.setPaddingLeft(10);
            cellAutoIdentification.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoIdentification.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellAutoIdentification);

            table5.addCell(middelSpace);
            table5.addCell(middelSpace);

            PdfPCell cellFileNo = new PdfPCell(new Paragraph("  ii)   File No.",fontStyle_Bold));
            cellFileNo.setBorder(0);
            cellFileNo.setPaddingLeft(10);
            cellFileNo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellFileNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellFileNo);

            PdfPCell cellAutoFileNo = new PdfPCell(new Paragraph(standardTorCertificate.getFileNo(),fontStyle_Bold));
            cellAutoFileNo.setBorder(0);
            cellAutoFileNo.setPaddingLeft(10);
            cellAutoFileNo.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoFileNo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellAutoFileNo);

            table5.addCell(middelSpace);
            table5.addCell(middelSpace);

            PdfPCell cellClearanceType = new PdfPCell(new Paragraph("  iii)  Clearance Type",fontStyle_Bold));
            cellClearanceType.setBorder(0);
            cellClearanceType.setPaddingLeft(10);
            cellClearanceType.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellClearanceType.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellClearanceType);

            PdfPCell cellAutoClearanceType = new PdfPCell(new Paragraph(standardTorCertificate.getClearanceType(),fontStyle_Bold));
            cellAutoClearanceType.setBorder(0);
            cellAutoClearanceType.setPaddingLeft(10);
            cellAutoClearanceType.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoClearanceType.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellAutoClearanceType);

            table5.addCell(middelSpace);
            table5.addCell(middelSpace);

            PdfPCell cellCategory = new PdfPCell(new Paragraph("  iv)   Category",fontStyle_Bold));
            cellCategory.setBorder(0);
            cellCategory.setPaddingLeft(10);
            cellCategory.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellCategory.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellCategory);

            PdfPCell cellAutoCategory = new PdfPCell(new Paragraph(standardTorCertificate.getCategory(),fontStyle_Bold));
            cellAutoCategory.setBorder(0);
            cellAutoCategory.setPaddingLeft(10);
            cellAutoCategory.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoCategory.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellAutoCategory);

            table5.addCell(middelSpace);
            table5.addCell(middelSpace);

            PdfPCell cellActivity = new PdfPCell(new Paragraph("  v)    Project/Activity Included Schedule No.",fontStyle_Bold));
            cellActivity.setBorder(0);
            cellActivity.setPaddingLeft(10);
            cellActivity.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellActivity.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellActivity);

            PdfPCell cellAutoActivity = new PdfPCell(new Paragraph(standardTorCertificate.getProjectIncludedScheduleNo(),fontStyle_Bold));
            cellAutoActivity.setBorder(0);
            cellAutoActivity.setPaddingLeft(10);
            cellAutoActivity.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoActivity.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellAutoActivity);

            table5.addCell(middelSpace);
            table5.addCell(middelSpace);

            PdfPCell cellProject = new PdfPCell(new Paragraph("  vi)   Name of Project",fontStyle_Bold));
            cellProject.setBorder(0);
            cellProject.setPaddingLeft(10);
            cellProject.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellProject.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellProject);

            PdfPCell cellAutoProject = new PdfPCell(new Paragraph(standardTorCertificate.getNameOfProject(),fontStyle_Bold));
            cellAutoProject.setBorder(0);
            cellAutoProject.setPaddingLeft(10);
            cellAutoProject.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoProject.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellAutoProject);

            table5.addCell(middelSpace);
            table5.addCell(middelSpace);

            PdfPCell cellOrganization = new PdfPCell(new Paragraph("  vii)  Name of Company/Organization",fontStyle_Bold));
            cellOrganization.setBorder(0);
            cellOrganization.setPaddingLeft(10);
            cellOrganization.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellOrganization.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellOrganization);

            PdfPCell cellAutoOrganization = new PdfPCell(new Paragraph(standardTorCertificate.getCompanyname(),fontStyle_Bold));
            cellAutoOrganization.setBorder(0);
            cellAutoOrganization.setPaddingLeft(10);
            cellAutoOrganization.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoOrganization.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellAutoOrganization);

            table5.addCell(middelSpace);
            table5.addCell(middelSpace);

            PdfPCell cellLocation = new PdfPCell(new Paragraph("  viii) Location of Project (District, State)",fontStyle_Bold));
            cellLocation.setBorder(0);
            cellLocation.setPaddingLeft(10);
            cellLocation.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellLocation.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellLocation);

            PdfPCell cellAutoLocation = new PdfPCell(new Paragraph(standardTorCertificate.getLocationOfProject(),fontStyle_Bold));
            cellAutoLocation.setBorder(0);
            cellAutoLocation.setPaddingLeft(10);
            cellAutoLocation.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoLocation.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table5.addCell(cellAutoLocation);

            PdfPTable header = new PdfPTable(2);
            PdfPCell text = new PdfPCell();
            text.setPaddingBottom(15);
            text.setPaddingLeft(10);
            text.setBorder(Rectangle.BOTTOM);
            text.setBorderColor(BaseColor.BLACK);
            text.addElement(new Phrase("iText PDF Header Footer Example", new Font(Font.FontFamily.HELVETICA, 12)));
            text.addElement(new Phrase("https://memorynotfound.com", new Font(Font.FontFamily.HELVETICA, 8)));
            header.addCell(text);

            document.add(header);
            document.add(table5);


            document.newPage();


            PdfPTable table1of2 = new PdfPTable(2);
            table1of2.setWidthPercentage(95);
            float[] columnWidths1of2 = {21f,9f};
            table1of2.setWidths(columnWidths1of2);
            // table.setSpacingBefore(20f);
            //table.setSpacingAfter(60f);
            writer.getDirectContentUnder().addImage(waterMarkImage,
                    pdfPageWidth, 0, 0, pdfPageHeight, 0, 0);

            table1of2.addCell(cellSpace);
            table1of2.addCell(cellSpace);
            table1of2.addCell(cellSpace);
            table1of2.addCell(cellSpace);

            PdfPCell cellIssuingAuthority = new PdfPCell(new Paragraph("  ix)   Issuing Authority",fontStyle_Bold));
            cellIssuingAuthority.setBorder(0);
            cellIssuingAuthority.setPaddingLeft(10);
            cellIssuingAuthority.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellIssuingAuthority.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of2.addCell(cellIssuingAuthority);

            PdfPCell cellAutoIssuingAuthority = new PdfPCell(new Paragraph(standardTorCertificate.getIssuingAuthority(),fontStyle_Bold));
            cellAutoIssuingAuthority.setBorder(0);
            cellAutoIssuingAuthority.setPaddingLeft(10);
            cellAutoIssuingAuthority.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoIssuingAuthority.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of2.addCell(cellAutoIssuingAuthority);

            table1of2.addCell(middelSpace);
            table1of2.addCell(middelSpace);

            PdfPCell cellToRDate = new PdfPCell(new Paragraph("  x)    ToR Date",fontStyle_Bold));
            cellToRDate.setBorder(0);
            cellToRDate.setPaddingLeft(10);
            cellToRDate.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellToRDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of2.addCell(cellToRDate);

            PdfPCell cellAutoToRDate = new PdfPCell(new Paragraph(standardTorCertificate.getTorDate(),fontStyle_Bold));
            cellAutoToRDate.setBorder(0);
            cellAutoToRDate.setPaddingLeft(10);
            cellAutoToRDate.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutoToRDate.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of2.addCell(cellAutoToRDate);

            table1of2.addCell(middelSpace);
            table1of2.addCell(middelSpace);

            PdfPCell cellApplicability = new PdfPCell(new Paragraph("  xi)   Applicability of General Conditions",fontStyle_Bold));
            cellApplicability.setBorder(0);
            cellApplicability.setPaddingLeft(10);
            cellApplicability.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellApplicability.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of2.addCell(cellApplicability);

            PdfPCell cellAutocellApplicability = new PdfPCell(new Paragraph(standardTorCertificate.getApplicabilityOfGeneralConditions(),fontStyle_Bold));
            cellAutocellApplicability.setBorder(0);
            cellAutocellApplicability.setPaddingLeft(10);
            cellAutocellApplicability.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellAutocellApplicability.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of2.addCell(cellAutocellApplicability);

            table1of2.addCell(cellSpace);


            PdfPTable table2of2 = new PdfPTable(1);
            table2of2.setWidthPercentage(85);
            float[] columnWidths2of2 = {25f};
            table2of2.setWidths(columnWidths2of2);

            table2of2.addCell(cellSpace);
            table2of2.addCell(cellSpace);
            table2of2.addCell(cellSpace);


            PdfPCell cell2 = new PdfPCell();
            Paragraph firstcell2 = new Paragraph("2.", fontStyle_Bold);
            firstcell2.add(new Chunk(" The [Issuing Authority] has examined the proposal in accordance with the Environment Impact Assessment (EIA) Notification, 2006 & further amendments thereto and after detailed examination hereby decided to grant Standard Terms of Reference for instant proposal of M/s. [Auto Fill] under the provisions of EIA Notification, 2006 and as amended thereof.",fontStyle ));
            cell2.addElement(firstcell2);
            cell2.setBorder(0);
            cell2.setPaddingLeft(10);
            cell2.setPadding(5);
            cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2of2.addCell(cell2);


            PdfPCell cell3 = new PdfPCell();
            Paragraph firstcell3 = new Paragraph("3.", fontStyle_Bold);
            firstcell3.add(new Chunk(" The brief about configuration of plant/equipment, products and byproducts and salient features of the project along with environment settings, as submitted by the Project proponent in Form-1 (Part A, B) and Standard Terms of Reference are annexed to this letter as Annexure (1).",fontStyle ));
            cell3.addElement(firstcell3);
            cell3.setBorder(0);
            cell3.setPaddingLeft(10);
            cell3.setPadding(5);
            cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2of2.addCell(cell3);

            PdfPCell cell4 = new PdfPCell();
            Paragraph firstcell4 = new Paragraph("4.", fontStyle_Bold);
            firstcell4.add(new Chunk(" The Ministry reserves the right to stipulate additional TORs, if found necessary.",fontStyle ));
            cell4.addElement(firstcell4);
            cell4.setBorder(0);
            cell4.setPaddingLeft(10);
            cell4.setPadding(5);
            cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2of2.addCell(cell4);

            PdfPCell cell5 = new PdfPCell();
            Paragraph firstcell5 = new Paragraph("5.", fontStyle_Bold);
            firstcell5.add(new Chunk(" The Terms of Reference (ToR) to the aforementioned project is under provisions of EIA Notification, 2006. It does not tantamount to approvals/consent/permissions etc. required to be obtained under any other Act/Rule/regulation. The Project Proponent is under obligation to obtain approvals /clearances under any other Acts/ Regulations or Statutes, as applicable, to the project.",fontStyle ));
            cell5.addElement(firstcell5);
            cell5.setBorder(0);
            cell5.setPaddingLeft(10);
            cell5.setPadding(5);
            cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2of2.addCell(cell5);

            PdfPCell cell6 = new PdfPCell();
            Paragraph firstcell6 = new Paragraph("6.", fontStyle_Bold);
            firstcell6.add(new Chunk(" The granted letter, all the application documents submitted [(viz. Form-1 Part A, Part B)] are available on PARIVESH portal which can be accessed by scanning the QR Code above.",fontStyle ));
            cell6.addElement(firstcell6);
            cell6.setBorder(0);
            cell6.setPaddingLeft(10);
            cell6.setPadding(5);
            cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table2of2.addCell(cell6);

            document.add(table1of2);
            document.add(table2of2);

            document.newPage();


            PdfPTable table1of3 = new PdfPTable(1);
            table1of3.setWidthPercentage(95);
            float[] columnWidths1of3 = {18f};
            table1of3.setWidths(columnWidths1of3);
            // table.setSpacingBefore(20f);
            //table.setSpacingAfter(60f);
            writer.getDirectContentUnder().addImage(waterMarkImage,
                    pdfPageWidth, 0, 0, pdfPageHeight, 0, 0);

            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);

            PdfPCell cell7 = new PdfPCell();
            Paragraph firstcell7 = new Paragraph("7.", fontStyle_Bold);
            firstcell7.add(new Chunk(" This issues with the approval of the Competent Authority.",fontStyle ));
            cell7.addElement(firstcell7);
            cell7.setBorder(0);
            cell7.setPaddingLeft(10);
            cell7.setPadding(5);
            cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of3.addCell(cell7);

            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);

            PdfPCell cellSignature = new PdfPCell(new Paragraph("e-Signature",fontStyle_Bold));
            cellSignature.setBorder(0);
            cellSignature.setPaddingLeft(10);
            cellSignature.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellSignature.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of3.addCell(cellSignature);

            PdfPCell cellAuthorizedSignatory = new PdfPCell(new Paragraph("Name of Authorized Signatory",fontStyle_Bold));
            cellAuthorizedSignatory.setBorder(0);
            cellAuthorizedSignatory.setPaddingLeft(10);
            cellAuthorizedSignatory.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellAuthorizedSignatory.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of3.addCell(cellAuthorizedSignatory);

            PdfPCell cellDesignation = new PdfPCell(new Paragraph("Designation",fontStyle_Bold));
            cellDesignation.setBorder(0);
            cellDesignation.setPaddingLeft(10);
            cellDesignation.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cellDesignation.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of3.addCell(cellDesignation);

            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            table1of3.addCell(cellSpace);
            PdfPCell cellFooterAddress = new PdfPCell(new Paragraph("Designation",fontStyle ));
            Chunk c1=new Chunk("Address: CPC Green, Ministry of Environment, Forest and Climate Change, Indira Paryavaran",fontStyle);
            Chunk c2=new Chunk("Bhawan, Jor Bagh New Delhi - 110003.",fontStyle);
            Chunk c3=new Chunk(" ");
            Chunk c4=new Chunk("IA Division, Ministry of Environment, Forest and Climate Change, Indira Paryavaran Bhawan, Jor",fontStyle);
            Chunk c5=new Chunk("Bagh New Delhi - 110003",fontStyle);
            //cellFooterAddress.setBorder(0);
            cellFooterAddress.addElement(c1);
            cellFooterAddress.addElement(c2);
            cellFooterAddress.addElement(c3);
            cellFooterAddress.addElement(c4);
            cellFooterAddress.addElement(c5);
            cellFooterAddress.setPaddingLeft(10);
            cellFooterAddress.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellFooterAddress.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table1of3.addCell(cellFooterAddress);



            document.add(table1of3);

            document.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
