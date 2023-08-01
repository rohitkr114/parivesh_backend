package com.backend.controller.StandardTorCertificate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.dto.StandadCertificateDto.ConditionMasterDto;
import com.backend.service.StandardTORCertificate.ConditionMasterService;

@RestController
@RequestMapping(value = "/conditionMaster")
public class FcConditionMaster {

	@Autowired
	ConditionMasterService conditionMasterService;

	/**
	 * Method is used to getAllCondition by status
	 * @param status
	 * @return {@link List<ConditionMasterEntity>}
	 */
	@GetMapping("/getAllFCCondition")

	public List<ConditionMasterDto> getAllCondition(
			@RequestParam(required = false) String conditionType,
			@RequestParam Integer applicationId){
        /*Calling service method*/
		return conditionMasterService.getAllFCCondition(applicationId,conditionType);

	}
	
	/**
	 * Method is used to getAllSpecificCondition by status and projectCategory
	 * @param status
	 * @param projectCategory
	 * @return {@link List<ConditionMasterEntity>}
	 
	@GetMapping("/getAllSpecificCondition")
	public List<ConditionMasterEntity> getAllSpecificCondition(
			@RequestParam(value = "status", required = true) String status, @RequestParam(value = "projectCategory", required = true) String projectCategory) {

		return conditionMasterService.getAllSpecificCondition(status, projectCategory);

	}*/

}
