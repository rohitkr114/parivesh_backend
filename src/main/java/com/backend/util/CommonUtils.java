package com.backend.util;

import com.backend.exceptions.PariveshException;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.itextpdf.html2pdf.HtmlConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Slf4j
public class CommonUtils {


    public static String generatePdf (OutputStream outputStream, String version, String pdfName, String dir, String certId) throws IOException {
        String dirPath = new StringBuilder().append(dir).append(File.separator)
                .append(version).append(File.separator)
                .append(certId).append(File.separator)
                .append(pdfName).append(".pdf")
                .toString();
        File file = new File(dirPath);
        file.getParentFile().mkdirs();

        if(file.createNewFile()) {
            log.info("file created: " + file.getName());
            //readOutputStream (outputStream, file.getAbsolutePath());
            log.info("====================== " + file.getAbsolutePath());
            byte[] outputStreamArray = getArrayFromInputStream(outputStream);
            writeContent(outputStreamArray, file);

        } else {
            log.info("File existed: " + file.getName());
            //readOutputStream (outputStream, file.getAbsolutePath());
            byte[] outputStreamArray = getArrayFromInputStream(outputStream);
            writeContent(outputStreamArray, file);
        }
        return dirPath;
    }

    private static void readOutputStream (OutputStream os, String path) {
        try {
            byte[] buf = new byte[8192];
            InputStream is = new FileInputStream(path);
            int c = 0;
            while ((c = is.read(buf, 0, buf.length)) > 0) {
                os.write(buf, 0, c);
                os.flush();
            }
            os.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getArrayFromInputStream(OutputStream outputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.writeTo(outputStream);
        return baos.toByteArray();
    }

    private static void writeContent(byte[] content, File fileToWriteTo) throws IOException {

        try(BufferedOutputStream salida = new BufferedOutputStream(new FileOutputStream(fileToWriteTo))){
            salida.write(content);
            salida.flush();
        }
    }

    public static String parseDateInDDMMYYYY (String inputDate) throws ParseException {
        DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = inputFormatter.parse(inputDate);

        DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
        return outputFormatter.format(date); // Output : 01/20/2012
    }

    public static Date parseStringIntoDateInYYYYMMDD (String inputDate) throws ParseException {
        DateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd");
        return inputFormatter.parse(inputDate);
    }

    public static String handleEmpty (String input) {
        return !StringUtils.isEmpty(input) ? input : "";
    }

    public static String replaceEmptyWithNA (String input) {
        return !StringUtils.isEmpty(input) ? input : "NA";
    }

    public static void addStrings (List<String> input, String str) {
        if (!org.apache.commons.lang3.StringUtils.isEmpty(str))
        input.add(str);
    }

    public static void throwException(Object obj, String fieldName) {
        if (ObjectUtils.isEmpty(obj)) {
            throw new PariveshException("Required filed "+ fieldName +" is null: " + obj);
        }
    }

    @SuppressWarnings("deprecation")
	public static String updateIfEmpty(String oldValue,String newValue) {
    	
    	if(StringUtils.isEmpty(oldValue)) {
    		return newValue;
    	}
    	return oldValue;
    }
    
    
    public static Integer updateIfEmpty(Integer oldValue,Integer newValue) {
    	
    	if(oldValue==null) {
    		return newValue;
    	}
    	return oldValue;
    }
    public static Double updateIfEmpty(Double oldValue,Double newValue) {
    	
    	if(oldValue==null) {
    		return newValue;
    	}
    	return oldValue;
    }
}
