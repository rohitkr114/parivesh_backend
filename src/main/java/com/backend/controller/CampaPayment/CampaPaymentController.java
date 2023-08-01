package com.backend.controller.CampaPayment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.backend.dto.UserPrincipal;
import com.backend.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.CampaPayment.CampaPaymentDetails;
import com.backend.model.CampaPayment.CampaPaymentOtherCharges;
import com.backend.model.CampaPayment.CampaPaymentOtherDetails;
import com.backend.service.CampaPayment.CampaPaymentService;



@RestController
@RequestMapping("/campaPayment")
public class CampaPaymentController {
	
	 @Autowired
	 private CampaPaymentService campaPaymentService;
	 
	 @PostMapping("/save")
	 public ResponseEntity<Object> saveCampaPaymentDetails(@RequestBody CampaPaymentDetails campaPaymentDetails,
			 @RequestParam Integer clearance_id,HttpServletRequest request) throws PariveshException{
		 return campaPaymentService.saveCampaPaymentDetails(campaPaymentDetails, clearance_id,request);
	 }
	 
	 @PostMapping("/get")
	 public ResponseEntity<Object> getCampaPaymentDetails(@RequestParam Integer id) throws PariveshException{
		 return campaPaymentService.getCampaPaymentDetails(id);
	 }

	@PostMapping("/getByApplicationId")
	public ResponseEntity<Object> getCampaPaymentDetailsByApplicationId(@RequestParam Integer applicationId) throws PariveshException{
		return campaPaymentService.getCampaPaymentByApplicationId(applicationId);
	}
	 @PostMapping("/saveOtherDetails")
	 public ResponseEntity<Object> saveCampaPaymentOtherDetails(@RequestParam Integer campa_payment_id,
			 @RequestBody CampaPaymentOtherDetails campaPaymentOtherDetails,HttpServletRequest request) throws PariveshException{
		 return campaPaymentService.saveCampaPaymentOtherDetails(campa_payment_id, campaPaymentOtherDetails,request);
	 }
	 
	 @PostMapping("/saveOtherCharges")
	 public ResponseEntity<Object> saveCampaPaymentOtherCharges(@RequestParam Integer campa_payment_id,
			 @RequestBody List<CampaPaymentOtherCharges> listOtherCharges) throws PariveshException{
		 return campaPaymentService.saveCampaPaymentOtherCharges(campa_payment_id, listOtherCharges);
	 }
	 
	 @PostMapping("/deleteOtherCharges")
	 public ResponseEntity<Object> deleteCampaPaymentOtherCharges(@RequestParam Integer other_charges_id) throws PariveshException{
		 	 return campaPaymentService.deleteCampaPaymentOtherCharges(other_charges_id);
	 }

	@PostMapping("/getCampaDashboardDetails")
	public ResponseEntity<Object> getCampaDashboardDetails(@CurrentUser UserPrincipal userPrincipal){
		 return campaPaymentService.getCampaDashboardDetails(userPrincipal);
	}

	@PostMapping("/deleteAllOtherCharges")
	public ResponseEntity<Object> deleteAllCampaOtherCharges(@RequestParam Integer campaId)throws PariveshException{
		 return campaPaymentService.deleteAllCampaOtherCharges(campaId);
	}

}
