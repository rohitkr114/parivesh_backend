package com.backend.controller.EcForm6V2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm6V2.EcForm6AmendmentDetailsV2;
import com.backend.model.EcForm6V2.EcForm6ConsultantV2;
import com.backend.model.EcForm6V2.EcForm6ImplementationDetailsV2;
import com.backend.model.EcForm6V2.EcForm6ProjectActivityDetailsV2;
import com.backend.model.EcForm6V2.EcForm6ProjectDetailsV2;
import com.backend.model.EcForm6V2.EcForm6UndertakingV2;
import com.backend.model.EcForm6V2.EcForm6UnitDetailsV2;
import com.backend.service.EcForm6V2.EcForm6V2Service;

@RestController
@RequestMapping("/ecForm6/v2")
public class EcForm6V2Controller {

	@Autowired
	private EcForm6V2Service ecForm6Service;

	@PostMapping("/saveProjectDetails")
	public ResponseEntity<Object> saveProjectDetails(@RequestBody EcForm6ProjectDetailsV2 projectDetails,
			@RequestParam Integer clearance_id, @RequestParam(required=false) Integer ec_part_a_id, @RequestParam(required=false) Integer ec_id, @RequestParam Integer caf_id)
			throws PariveshException {

		return ecForm6Service.saveProjectDetails(projectDetails, clearance_id, ec_part_a_id, ec_id, caf_id);
	}

	@PostMapping("/getForm")
	public ResponseEntity<Object> getEcForm6(@RequestParam Integer id) throws PariveshException {

		return ecForm6Service.getEcForm6(id);
	}
	
	@PostMapping("/saveProjectActivityDetails")
    public ResponseEntity<Object> saveProjectActivityDetails(@RequestParam Integer ec_form6_id,
    		@RequestBody List<EcForm6ProjectActivityDetailsV2> activityDetails) throws PariveshException{
    	
    	return ecForm6Service.saveProjectActivityDetails(ec_form6_id, activityDetails);
    }

	@PostMapping("/getProjectActivityList")
	public ResponseEntity<Object> getProjectActivityDetailsList(@RequestParam Integer ec_form6_id)
			throws PariveshException {

		return ecForm6Service.getProjectActivityDetailsList(ec_form6_id);
	}

	@PostMapping("/deleteProjectActivityList")
	public ResponseEntity<Object> deleteProjectActivityDetails(@RequestParam Integer id) throws PariveshException {

		return ecForm6Service.deleteProjectActivityDetails(id);
	}

	@PostMapping("/saveImplementationDetails")
	public ResponseEntity<Object> saveImplementationDetails(
			@RequestBody EcForm6ImplementationDetailsV2 implementationDetails, @RequestParam Integer ec_form6_id)
			throws PariveshException {

		return ecForm6Service.saveImplementationDetails(implementationDetails, ec_form6_id);
	}

	@PostMapping("/getImplementationDetails")
	public ResponseEntity<Object> getImplementationDetails(@RequestParam Integer ec_form6_id) throws PariveshException {

		return ecForm6Service.getImplementationDetails(ec_form6_id);
	}
	
	@PostMapping("/deleteObtainedStatus")
	public ResponseEntity<Object> deleteEcForm6ObtainedStatus(@RequestParam Integer id) throws PariveshException{
		
		return ecForm6Service.deleteEcForm6ObtainedStatus(id);
	}

	@PostMapping("/saveAmendmentDetails")
	public ResponseEntity<Object> saveAmendmentDetails(@RequestParam Integer ec_form6_id,
			@RequestBody EcForm6AmendmentDetailsV2 amendmentDetails) throws PariveshException {

		return ecForm6Service.saveAmendmentDetails(ec_form6_id, amendmentDetails);
	}

	@PostMapping("/deleteAmendmentDetails")
	public ResponseEntity<Object> deleteAmendmentDetails(@RequestParam Integer id) throws PariveshException {

		return ecForm6Service.deleteAmendmentDetails(id);
	}

	@PostMapping("/getAmendmentDetails")
	public ResponseEntity<Object> getAmendmentDetails(@RequestParam Integer ec_form6_id) throws PariveshException {

		return ecForm6Service.getAmendmentDetails(ec_form6_id);
	}

	@PostMapping("/deleteAmendmentStatus")
	public ResponseEntity<Object> deleteAmendmentStatus(@RequestParam Integer id) throws PariveshException {

		return ecForm6Service.deleteAmendmentStatus(id);
	}

	@PostMapping("/saveUnitDetails")
	public ResponseEntity<Object> saveUnitDetails(@RequestBody List<EcForm6UnitDetailsV2> unitDetails,
			@RequestParam Integer ec_form6_id) throws PariveshException {

		return ecForm6Service.saveUnitDetails(unitDetails, ec_form6_id);
	}

	@PostMapping("/getUnitDetails")
	public ResponseEntity<Object> getUnitDetails(@RequestParam Integer ec_form6_id) throws PariveshException {

		return ecForm6Service.getUnitDetails(ec_form6_id);
	}

	@PostMapping("/deleteUnitDetails")
	public ResponseEntity<Object> deleteUnitDetails(Integer id) throws PariveshException {

		return ecForm6Service.deleteUnitDetails(id);
	}

	@PostMapping("/saveConsultantDetails")
	public ResponseEntity<Object> saveConsultantDetails(@RequestParam Integer ec_form6_id,
			@RequestBody EcForm6ConsultantV2 consultant) throws PariveshException {

		return ecForm6Service.saveConsultantDetails(ec_form6_id, consultant);
	}

	@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveUndertaking(@RequestParam Integer ec_form6_id, 
			@RequestBody EcForm6UndertakingV2 undertaking,@RequestParam(required = false) Boolean is_submit) throws PariveshException {

		return ecForm6Service.saveUndertaking(ec_form6_id, undertaking,is_submit);
	}

}
