package com.backend.util;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant;
import com.backend.dto.MessageDto;
import com.backend.exceptions.PariveshException;
import com.backend.model.MailContent;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ServerUtil {

	@Autowired
	private NotifyClient notifyClient;

	@Autowired
	private UserRepository userRepository;

	public void sendEmailNotification(String emailId, Map<String, String> body, String subject, String vmName) {
		try {

			MailContent ob1 = new MailContent();
			ob1.setSubject(subject);
			ob1.setVmname(vmName);
			ob1.setTo(emailId);
			ob1.setBody(body);

			log.info("Sending Mail to client.");
			notifyClient.sendMail(ob1);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Sending Email Notification to client. ", e);
		}
	}

	public void sendSMSNotification(MessageDto messageDTO) {
		try {
			log.info("Sending Submission SMS to client.");
			notifyClient.sendSMSV2(messageDTO);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Sending SMS Notification to client. ", e);
		}
	}

	public void sendProposalEmail(ProponentApplications proponentApplications) {
		try {
			String email = userRepository.getUserEmail(proponentApplications.getProjectDetails().getCreated_by());
			String proposalName = proponentApplications.getApplications().getName().replace(",", "-");

			MailContent ob1 = new MailContent();
			ob1.setSubject(AppConstant.proposalSubmissionSubject);
			ob1.setVmname(AppConstant.proposalSubmittedTemplate);
			ob1.setTo(email);
			Map<String, String> body = new HashMap<>();

			body.put("proposalName", proposalName);

			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

			body.put("date", formatter.format(new Date()));
			ob1.setBody(body);
			log.info("Sending Submission Mail to client.");
			notifyClient.sendMail(ob1);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Sending Submission Mail to client. ", e);
		}
	}

}
