package com.backend.util;

import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class to Merge PDF
 * Param -> List of input Stream ,single merged output stream
 */
public class MergePdf {
    public static void mergePdfFiles(List<InputStream> inputPdfList, OutputStream outputStream) throws Exception {

        float margin = 30;
        com.itextpdf.text.Document document = new com.itextpdf.text.Document();

        List<PdfReader> readers = new ArrayList<PdfReader>();

        int totalPages = 0;

        Iterator<InputStream> pdfIterator = inputPdfList.iterator();

        while (pdfIterator.hasNext()) {

            InputStream pdf = pdfIterator.next();

            PdfReader pdfReader = new PdfReader(pdf);
            readers.add(pdfReader);
            totalPages = totalPages + pdfReader.getNumberOfPages();
            //close inputstream
//			pdf.close();;
        }

        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        writer.setPageEvent(new PageNumberingStamper("", totalPages));
        document.open();
        PdfContentByte pageContentByte = writer.getDirectContent();

        PdfImportedPage pdfImportedPage;
        int currentPdfReaderPage = 1;
        int pages_total = 0;
        Iterator<PdfReader> iteratorPDFReader = readers.iterator();
        while (iteratorPDFReader.hasNext()) {
            PdfReader pdfReader = iteratorPDFReader.next();
            pages_total += pdfReader.getNumberOfPages();
            while (currentPdfReaderPage <= pdfReader.getNumberOfPages()) {
                document.newPage();
//				document.setMargins(10, 10, 10, 10);
//				document.add(Element("Page :"+ String.valueOf(currentPdfReaderPage)+" of " + pages_total);
                pdfImportedPage = writer.getImportedPage(pdfReader, currentPdfReaderPage);
                pageContentByte.addTemplate(pdfImportedPage, 0, 0);
                currentPdfReaderPage++;
            }
            currentPdfReaderPage = 1;
            //close pdfReader
//			pdfReader.close();
        }

        outputStream.flush();
        document.close();
        outputStream.close();

        System.out.println("Success.");
    }
}

