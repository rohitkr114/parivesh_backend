package com.backend.service.EKyc;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.transaction.Transactional;

import java.util.Optional;

import com.backend.model.MailContent;
import com.backend.model.User;
import com.backend.model.VerificationToken;

import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.model.EKyc.UpdateEKyc;
import com.backend.repository.postgres.UserRepository;
import com.backend.repository.postgres.VerificationRepository;
import com.backend.repository.postgres.EKyc.UpdateEKycRepository;
import com.backend.response.ResponseHandler;
import com.backend.util.EncryptionUtility;
import com.backend.util.StringUtil;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UpdateEKycService {
	
	@Autowired
	UpdateEKycRepository updateEKycRepository;
	
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private Environment environment;
    
    @Autowired
    private PasswordEncoder passEncoder;
    
    @Autowired
    private VerificationRepository verificationRepository;
	
    @Autowired
    private NotifyClient notifyClient;
    
	@Value("${ua.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs;

	@Value("${ua.app.secretKey}")
	private String SECRET_KEY;
	
	//@Value("${ekyc.status.job.cron.local}")
	//private String status_update_cron;
	
	public ResponseEntity<Object> saveEKycDetails(UpdateEKyc updateEKyc){
		try {
			UpdateEKyc updateEKycRet = updateEKycRepository.save(updateEKyc);
			
			/*log.info(updateEKyc.getCreated_by().toString());
			User user = userRepository.getUserDataById(updateEKycRet.getCreated_by());
			log.info(user.toString());
			updateEKycRet.setUser_id(user);
			log.info(updateEKycRet.getUser_id().toString());
			
			UpdateEKyc updateEKycSave = updateEKycRepository.save(updateEKycRet);*/
			log.info(updateEKyc.getToken());
			User user = userRepository.getUserDataById(updateEKycRet.getCreated_by()); 
			String token =  updateEKyc.getToken();
			
			
			log.info(updateEKycRet.toString());
			if (updateEKycRet.getEmail() != null ) {
				log.info("new email kyc");
				sendMail(user, updateEKycRet, token);
			}
			else {
				log.info("old email kyc");
				sendMail(user, token);
			}
					
			return ResponseHandler.generateResponse("E KYC Update data saved", HttpStatus.OK, "", updateEKycRet);
		}
		catch(Exception e) {
            
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving E KYC Update -" + updateEKyc,
                    e);
            }
		
		}
	
	
	
	public ResponseEntity<Object> getList(Integer user_id){
		
		try {
			List<UpdateEKyc> updateEKycList = updateEKycRepository.getListByUser(user_id);
			for(UpdateEKyc e : updateEKycList) {
				User user = userRepository.getUserDataById(e.getCreated_by());
				e.setUser_id(user);
			}
			
			return ResponseHandler.generateResponse("E KYC Update data fetch success", HttpStatus.OK, "", updateEKycList);
		}
		catch(Exception e) {
			
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in fetch E KYC Update for -" + user_id, e);
		}
	}
	
	public ResponseEntity<Object> updateStatus(Integer user_id){
		
		try {
			
			UpdateEKyc updateEKyc = updateEKycRepository.findByUser(user_id).orElseThrow(()-> new PariveshException("Data not found for Update EKYC"));
			updateEKyc.setStatus("COMPLETED");
			updateEKycRepository.save(updateEKyc);
			
			Optional<String> retStatus = updateEKycRepository.updateUserKyc(user_id);
			log.info(retStatus.get());
			if (!(retStatus.get().contains("User kyc details updated Succcessfully"))){
				
				throw new PariveshException(retStatus.get());
			}
			
			log.info(user_id.toString());
			 
			sendConfirmationMail(user_id);
				
			return ResponseHandler.generateResponse("E KYC Update status success", HttpStatus.OK, "", retStatus.get());
		
		}
		catch(PariveshException e) {
			
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in update for E KYC -" + e.getLocalizedMessage(), e);
	
		}
		catch(Exception e) {
			
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in update for E KYC -" + user_id, e);
		
		}
		
	}
	
	public void sendConfirmationMail(Integer user_id) {
		
		String genPass = StringUtil.generateRandomPassword(7);
		User user = userRepository.getUserDataById(user_id);
		
		userRepository.updatePassword(user_id, passEncoder.encode(genPass));

		//String token = UUID.randomUUID().toString();
        //createVerificationToken(user, token);
		//final String jwt = generateToken(user);
        //String url = environment.getProperty(AppConstant.tokenUrlKyc) + token + "&email=" + user.getEmail().trim() + "&username=" + user.getName_of_Entity();
		//String url = environment.getProperty(AppConstant.tokenUrlKyc) + jwt + "&email=" + user.getEmail().trim() + "&username=" + user.getName_of_Entity();
		MailContent ob1 = new MailContent();
        ob1.setSubject(AppConstant.ekycConfirmMailSubject);
        ob1.setVmname(AppConstant.ekycConfirmTemplate);
        ob1.setTo(user.getEmail());
        Map<String, String> body = new HashMap<>();
        body.put("entityType", user.getUserType());
        body.put("recieverMail", user.getEmail().trim());
        body.put("password", genPass);
        //body.put("url", url);
        ob1.setBody(body);
        log.info(body.toString());
        log.info("Sending Registration Confirmation Mail to client ");
        notifyClient.sendMail(ob1);

	}
	
	public void sendMail(User user, String token) {
		
		//String genPass = StringUtil.generateRandomPassword(7);
		//String token = UUID.randomUUID().toString();
        //createVerificationToken(user, token);
		final String jwt = generateToken(user);
        //String url = environment.getProperty(AppConstant.tokenUrlKyc) + token + "&email=" + user.getEmail().trim() + "&username=" + user.getName_of_Entity();
		String url = environment.getProperty(AppConstant.tokenUrlKyc) + jwt + "&email=" + user.getEmail().trim() + "&username=" + user.getName_of_Entity();
		MailContent ob1 = new MailContent();
        ob1.setSubject(AppConstant.ekycMailSubject);
        ob1.setVmname(AppConstant.ekycTemplate);
        ob1.setTo(user.getEmail());
        Map<String, String> body = new HashMap<>();
        body.put("entityType", user.getUserType());
        body.put("recieverMail", user.getEmail().trim());
        //body.put("password", genPass);
        body.put("url", url);
        ob1.setBody(body);
        
        log.info("Sending Registration Mail to client "+ url);
        notifyClient.sendMail(ob1);

	}
	
	public void sendMail(User user,  UpdateEKyc updateEKyc, String token) {
		
		//String genPass = StringUtil.generateRandomPassword(7);
		//String token = UUID.randomUUID().toString();
		
		String email = updateEKyc.getEmail();
		String userName = updateEKyc.getContactPersonName();
		
        //createVerificationToken(user, token);
		final String jwt = generateToken(user);
        //String url = environment.getProperty(AppConstant.tokenUrlKyc) + token + "&email=" + email.trim() + "&username=" + userName;
		String url = environment.getProperty(AppConstant.tokenUrlKyc) + jwt + "&email=" + email.trim() + "&username=" + userName;
        
		MailContent ob1 = new MailContent();
        ob1.setSubject(AppConstant.ekycMailSubject);
        ob1.setVmname(AppConstant.ekycTemplate);
        ob1.setTo(email);
        Map<String, String> body = new HashMap<>();
        body.put("entityType", user.getUserType());
        body.put("recieverMail", email);
        //body.put("password", genPass);
        body.put("url", url);
        ob1.setBody(body);
        
        log.info("Sending Registration Mail to client " + url);
        notifyClient.sendMail(ob1);

	}
	
	@Scheduled(cron = "${ekyc.status.job.cron}")
	@Transactional
	public void changeStatus() {
		
		try {
			 updateEKycRepository.setStatusFailed();
		}
		catch(Exception e) {
			
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in updating status for EKYC", e);
		}
	}
	
    public void createVerificationToken(User user, String token) {
        
    	VerificationToken myToken = new VerificationToken(user, token);
        verificationRepository.save(myToken);
    }
    
	public String generateToken(User uProfile) {
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("userprofileId",  new String(EncryptionUtility.encrypt(uProfile.getEntityid().toString()), StandardCharsets.UTF_8));
		claims.put("username",  new String(EncryptionUtility.encrypt(uProfile.getEmailId().toString()), StandardCharsets.UTF_8));
		return CreateToken(claims, uProfile.getEntityid().toString());

	}

	private String CreateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + this.refreshTokenDurationMs))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
}
