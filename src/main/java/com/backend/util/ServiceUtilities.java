package com.backend.util;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;

public class ServiceUtilities {
	private static final Logger logger = LoggerFactory.getLogger(ServiceUtilities.class);
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	private static SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd-yyyy");
	private static SimpleDateFormat internalSDF = new SimpleDateFormat("MMddyyyyHHmmss");
	private static SimpleDateFormat reportDf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");
	private static SimpleDateFormat sdfWithoutTime = new SimpleDateFormat("MM/dd/yyyy");
	private static SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss"); 
	
	private static SimpleDateFormat sdfTime2 = new SimpleDateFormat("HHmmss"); 
	
	private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*";

    private static SecureRandom random = new SecureRandom();
    
	public static Date getDate(String dateString) {
		try {
			return sdf.parse(dateString);
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}
	
	
	public static Date getstysytemDate() {
		try {
			return new Date(System.currentTimeMillis());
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}

	public static String getDate(Date date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String format = formatter.format(date);
			System.out.println(format);
			return format;
		   
		} catch (Exception e) {
			e.getStackTrace();
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}
	
	public static String getSdfDate(Date date) {
		try {
			return sdfDate.format(date);
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}
	
	public static Date getSdfDate(String dateString) {
		try {
			return sdfDate.parse(dateString);
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}
	public static Date getDateWithoutTime(String dateString) {
		try {
			return sdfWithoutTime.parse(dateString);
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}

	public static String getDateWithoutTime(Date date) {
		try {
			return sdfWithoutTime.format(date);
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}
	

	public static String getFormatedDate(Date date) {
		try {
			return internalSDF.format(date);
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}
//	public static Date getFormatedTime(Date date) {
//		try {
//			return sdfTime.format(date);
//		} catch (Exception e) {
//			logger.error("Error occured while parsing date", e);
//			return null;
//		}
//	}
	
	
	public static Pageable getPageable(String page, String size, String direction, String orderBy) {
		Sort sort = null;
		if (direction.equalsIgnoreCase("ASC")) {
			sort = Sort.by(Direction.ASC, orderBy);
		}
		if (direction.equalsIgnoreCase("DESC")) {
			sort = Sort.by(Direction.DESC, orderBy);
		}
		Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size), sort);
		return pageable;
	}
	
	public static Pageable getPageableForIdDescOnly(String page, String size) {
		Pageable pageable = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
		return pageable;
		
	}

	public static Sort getSort(String direction, String orderBy) {
		Sort sort = null;
		if (direction.equalsIgnoreCase("ASC")) {
			sort = Sort.by(Direction.ASC, orderBy);
		}
		if (direction.equalsIgnoreCase("DESC")) {
			sort = Sort.by(Direction.DESC, orderBy);
		}
		return sort;
	}

	public static Boolean isValidListType(String listType) {
		if (!"WHITE LIST".equalsIgnoreCase(listType) && !"BLUE LIST".equalsIgnoreCase(listType)
				&& !"BLACK LIST".equalsIgnoreCase(listType)) {
			return false;
		}
		return true;
	}

	public static String getReportDate(Date date) {
		if (date != null) {
			return reportDf.format(date);
		}
		return "";

	}

	public static String getBearerToken(String token) {
		if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return token.substring(7, token.length());
		}
		return null;
	}

	public static Pageable getPageable(int page, int size, String direction,String integrationDate) {
		Sort sort = null;
		if (direction.equalsIgnoreCase("ASC")) {
			sort = Sort.by(Direction.ASC, integrationDate);
		}
		if (direction.equalsIgnoreCase("DESC")) {
			sort = Sort.by(Direction.DESC,integrationDate);
		}
		Pageable pageable = PageRequest.of(page,size, sort);
		return pageable;
	}
	

	public static String generateStrongPassword(Integer size) {

        StringBuilder result = new StringBuilder(size);

        String strLowerCase = generateRandomString(CHAR_LOWERCASE, 2);
        result.append(strLowerCase);

        String strUppercaseCase = generateRandomString(CHAR_UPPERCASE, 2);
        result.append(strUppercaseCase);

        String strDigit = generateRandomString(DIGIT, 2);
        result.append(strDigit);
        
        String strSpecialChar = generateRandomString(SYMBOLS, 2);
        result.append(strSpecialChar);
        
        if(size > 8) {
        	String randomChar = generateRandomString(CHAR_LOWERCASE+DIGIT+SYMBOLS+CHAR_UPPERCASE, size-8);
        	result.append(randomChar);
        }
        String finalPassword = shuffleString(result.toString());

        return finalPassword;
    }
	
	private static String generateRandomString(String input, int size) {

        if (input == null || input.length() <= 0)
            throw new IllegalArgumentException("Invalid input.");
        if (size < 1) throw new IllegalArgumentException("Invalid size.");

        StringBuilder result = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // produce a random order
            int index = random.nextInt(input.length());
            result.append(input.charAt(index));
        }
        return result.toString();
    }

    public static String shuffleString(String input) {
        List<String> result = Arrays.asList(input.split(""));
        Collections.shuffle(result);
        return result.stream().collect(Collectors.joining());
    }
    
	public static String getFormatedTime(Date date) {
		try {
			return sdfTime.format(date);
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}
	
	
	public static String getFormatedTime2(Date date) {
		try {
			return sdfTime2.format(date);
		} catch (Exception e) {
			logger.error("Error occured while parsing date", e);
			return null;
		}
	}
	
	public static int datedifference(Date formDate, Date currentDate) {

		long duration = currentDate.getTime() - formDate.getTime();
		long date = TimeUnit.MILLISECONDS.toDays(duration);
//			Period difference=Period.between(formDate, currentDate);
//			int date= difference.getDays();
		return (int) date;

	}
}
