package com.backend.client;

import com.backend.dto.MessageDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.dto.MessageDto;
import com.backend.model.MailContent;
import com.backend.util.FeignConfig;

@FeignClient(value = "notification", url = "${feign.engine.url}", configuration = FeignConfig.class)
public interface NotifyClient {

    @RequestMapping(method = RequestMethod.POST, value = "/email")
    boolean sendMail(@RequestBody MailContent mailContent);

    @RequestMapping(method = RequestMethod.POST, value = "/sendSMS")
    boolean sendSms(@RequestParam("mobile") String mobile);

    @RequestMapping(method = RequestMethod.POST, value = "/authenticateOTP")
    String autheticateOTP(@RequestParam("mobile") String mobileNo, @RequestParam("otp") String OTP);

//	@RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
//	ResponseEntity<Object> sendSMS(@RequestParam("mobile") String mobile,
//			@RequestParam("templateId") String templateId);

	@RequestMapping(method = RequestMethod.POST, value = "/sendMessageV2")
	ResponseEntity<Object> sendSMSV2(@RequestBody MessageDto messageDTO);

}
