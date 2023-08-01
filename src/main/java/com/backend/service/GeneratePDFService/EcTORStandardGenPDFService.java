package com.backend.service.GeneratePDFService;

import com.backend.model.StandardTORCertificate.StandardTorCertificate;
import com.backend.util.CommonUtils;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EcTORStandardGenPDFService {

    public String generateEcTORStandardPDF(StandardTorCertificate standardTorCertificate, HttpServletResponse response) {
        String exportPath = null;
        try {

            List<StandardTorCertificate> standardTorCertificateList = new ArrayList<>();
            standardTorCertificateList.add(standardTorCertificate);

            BufferedImage barcodeImage;
            if(standardTorCertificate.getStatus().equals("draft")){
                barcodeImage = ImageIO.read(getClass().getResource("images/draftedBarcode.png"));
            }
            else{
                barcodeImage = ImageIO.read(getClass().getResource("images/barcode.png"));
            }


            Map<String, Object> empParams = new HashMap<String, Object>();
            empParams.put("detailsOfProducts", new JRBeanCollectionDataSource(standardTorCertificate.getDetailsOfProducts()));   //employeeData
            empParams.put("plantEquipment", new JRBeanCollectionDataSource(standardTorCertificate.getPlantEquipmentArray()));   //raja
           // empParams.put("generalCondition", new JRBeanCollectionDataSource(standardTorCertificate.getGeneralConditions()));
            empParams.put("barcode", barcodeImage );

            JasperPrint standardReport =
                    JasperFillManager.fillReport
                            (
                                    JasperCompileManager.compileReport(
                                            ResourceUtils.getFile("classpath:jrxmls/EC_TOR_STANDARD_LETTER1.jrxml")
                                                    .getAbsolutePath()) // path of the jasper report
                                    , empParams // dynamic parameters
                                    , new JRBeanCollectionDataSource(standardTorCertificateList)
                            );

            JasperPrint standardReport2 =
                    JasperFillManager.fillReport
                            (
                                    JasperCompileManager.compileReport(
                                            ResourceUtils.getFile("classpath:jrxmls/EC_TOR_STANDARD_LETTER2.jrxml")
                                                    .getAbsolutePath()) // path of the jasper report
                                    , empParams // dynamic parameters
                                    , new JRBeanCollectionDataSource(standardTorCertificateList)
                            );

            JasperPrint standardReport3 =
                    JasperFillManager.fillReport
                            (
                                    JasperCompileManager.compileReport(
                                            ResourceUtils.getFile("classpath:jrxmls/EC_TOR_STANDARD_LETTER3.jrxml")
                                                    .getAbsolutePath())
                                    , empParams
                                    , new JRBeanCollectionDataSource(standardTorCertificateList)
                            );

            JasperPrint standardReport4 =
                    JasperFillManager.fillReport
                            (
                                    JasperCompileManager.compileReport(
                                            ResourceUtils.getFile("classpath:jrxmls/EC_TOR_STANDARD_LETTER4.jrxml")
                                                    .getAbsolutePath()) // path of the jasper report
                                    , empParams // dynamic parameters
                                    , new JRBeanCollectionDataSource(standardTorCertificateList)
                            );


            List jpList = new ArrayList();
            jpList.add(standardReport);
            jpList.add(standardReport2);
            jpList.add(standardReport3);
            jpList.add(standardReport4);

            exportPath = "D:\\certificate\\" + "standard-tor_" + "-" + standardTorCertificate.getVersion() + standardTorCertificate.getId() + ".pdf";
            OutputStream output = new FileOutputStream(new File(exportPath));
            JRPdfExporter exporter = new JRPdfExporter();
            exporter = new JRPdfExporter();
//this sets the list of jasperPrint objects to be exported and merged
            exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jpList);
//the bookmarks is a neat extra that creates a bookmark for each jasper print
            exporter.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
            exporter.exportReport();
            response.flushBuffer();

        } catch (Exception e) {
        }
        return exportPath;
    }
}
