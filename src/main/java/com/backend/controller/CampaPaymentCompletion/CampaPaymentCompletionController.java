package com.backend.controller.CampaPaymentCompletion;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.CampaPaymentCompletion.FcCampaPaymentBankDetails;
import com.backend.model.CampaPaymentCompletion.FcCampaPaymentCompletionDetails;
import com.backend.model.CampaPaymentCompletion.FcCampaPaymentProposalDetails;
import com.backend.model.CampaPaymentCompletion.FcCampaTransactionDetails;
import com.backend.service.CampPaymentCompletion.CampaPaymentCompletionService;

@RestController
@RequestMapping("/campaPayment/completion")
public class CampaPaymentCompletionController {

	@Autowired
	private CampaPaymentCompletionService campaPaymentCompletionService;
	
	@PostMapping("/save/fcCampaPaymentCompletion")
	public ResponseEntity<Object> saveFcCampaCompletion(@RequestBody FcCampaPaymentCompletionDetails fcCampaPaymentCompletionDetails,
			@RequestParam Integer clearance_id,
			HttpServletRequest request) throws PariveshException {
		return campaPaymentCompletionService.saveFcCampaPaymentCompletionDetails(fcCampaPaymentCompletionDetails,clearance_id,request);
	}
	
	@PostMapping("/get/fcCampaPaymentCompletion")
	public ResponseEntity<Object> getFcCampaCompletion(@RequestParam Integer id) throws PariveshException {
		return campaPaymentCompletionService.getFcCampaPaymentCompletionDetails(id);
	}
	
//	@PostMapping("/save/fcCampaPaymentBankDetails")
//	public ResponseEntity<Object> saveFcCampaCompletion(@RequestBody FcCampaPaymentBankDetails fcCampaPaymentBankDetails,
//			@RequestParam Integer id,HttpServletRequest request) throws PariveshException {
//		return campaPaymentCompletionService.saveFcCampaPaymentBankDetails(id, fcCampaPaymentBankDetails,request);
//	}
	
	@PostMapping("/save/fcCampaPaymentProposalDetails")
	public ResponseEntity<Object> saveFcCampaCompletion(@RequestBody FcCampaPaymentProposalDetails fcCampaPaymentProposalDetails,
			@RequestParam Integer id) throws PariveshException {
		return campaPaymentCompletionService.saveFcCampaPaymentproposalDetails(id, fcCampaPaymentProposalDetails);
	}
	
	@PostMapping("/save/fcCampaPaymentTransactionDetails")
	public ResponseEntity<Object> saveFcCampaCompletion(@RequestBody List<FcCampaTransactionDetails> fcCampaTransactionDetails,
			@RequestParam Integer id) throws PariveshException {
		return campaPaymentCompletionService.saveFcCampaPaymentTransactionDetails(fcCampaTransactionDetails, id);
	}
	
	@PostMapping("/delete/fcCampaPaymentTransactionDetails")
	public ResponseEntity<Object> saveFcCampaCompletion(@RequestParam Integer id) throws PariveshException {
		return campaPaymentCompletionService.deleteFcCampaPaymentTransactionDetails(id);
	}
	
	@PostMapping("/get/fcCampaPaymentTransaction")
	public ResponseEntity<Object> getFcCampaPaymentTransaction(@RequestParam Integer id) throws PariveshException {
		return campaPaymentCompletionService.getFcCampaPaymentTransactionDetails(id);
	}
	
	@PostMapping("/checkTransactionExist")
	public ResponseEntity<Object> checkTransactionExist(@RequestParam String transaction_id) throws PariveshException{
		return campaPaymentCompletionService.checkTransactionExist(transaction_id);
	}
}
