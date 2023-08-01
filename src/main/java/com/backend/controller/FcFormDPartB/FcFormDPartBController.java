package com.backend.controller.FcFormDPartB;

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
import com.backend.model.FcFormBPartB.FcFormBPartBTreeDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBAfforestationDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBBasicDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBDensityOfVegetation;
import com.backend.model.FcFormDPartB.FcFormDPartBDistrictWise;
import com.backend.model.FcFormDPartB.FcFormDPartBEnumerationDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBLegalStatus;
import com.backend.model.FcFormDPartB.FcFormDPartBOtherDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBTreeDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBViolationDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBWLSpecificDetails;
import com.backend.service.FcFormDPartB.FcFormDPartBService;

@RestController
@RequestMapping("/fc/formd/partb")
public class FcFormDPartBController {
	
	@Autowired
	private FcFormDPartBService fcFormDPartBService;
	
	//Section Basic details
	//for saving the basic details 
	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveFCformDPartBBasicDetails(
			@RequestBody FcFormDPartBBasicDetails fcFormDPartBBasicDetails, HttpServletRequest request)
			throws PariveshException {
		return fcFormDPartBService.saveFCformDPartBBasicDetails(fcFormDPartBBasicDetails, request);
	}
	
	//Section Basic details
	//for fetching the basic details 
	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> getFCformDPartBBasicDetails(@RequestParam Integer fc_part_b_basic_details_id)
			throws PariveshException {
		return fcFormDPartBService.getFCformDPartBBasicDetails(fc_part_b_basic_details_id);
	}
	
	//Section 1
	//Save District Wise Details 
	@PostMapping("/saveDistrictWise")
	public ResponseEntity<Object> saveFcFormDPartBDistrictWise(
			@RequestBody List<FcFormDPartBDistrictWise> fcFormDPartBDistrictWises,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormDPartBService.saveFcFormDPartBDistrictWise(fcFormDPartBDistrictWises,
				fc_part_b_basic_details_id);
	}

	//Section 1
	//Delete District Wise Details 
	@PostMapping("/deleteDistrictWise")
	public ResponseEntity<Object> deleteFcFormDPartBDistritwise(@RequestParam Integer districtWise_id) {
		return fcFormDPartBService.deleteFcFormDPartBDistritwise(districtWise_id);
	}

	//Section 2
	//Save Legal Status Details 
//	@PostMapping("/saveLegalStatus")
//	public ResponseEntity<Object> saveFcFormDPartBLegalStatus(
//			@RequestBody List<FcFormDPartBLegalStatus> fcFormDPartBLegalStatus,
//			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
//		return fcFormDPartBService.saveFcFormDPartBLegalStatus(fcFormDPartBLegalStatus,
//				fc_part_b_basic_details_id);
//	}

	//Section 2
	//Delete Legal Status Details 
	@PostMapping("/deleteLegalStatus")
	public ResponseEntity<Object> deleteFcFormDPartBLegalStatus(@RequestParam Integer legalStstus_id) {
		return fcFormDPartBService.deleteFcFormDPartBLegalStatus(legalStstus_id);
	}

	//Section 3
	//Save Density of Vegetation Details
	@PostMapping("/saveDensityOfVegetation")
	public ResponseEntity<Object> saveFcFormDPartBDensityOfVegetation(
			@RequestBody List<FcFormDPartBDensityOfVegetation> fcFormDPartBDensityOfVegetation,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormDPartBService.saveFcFormDPartBDensityOfVegetation(fcFormDPartBDensityOfVegetation,
				fc_part_b_basic_details_id);
	}

	//Section 3
	//Delete Density of Vegetation Details
	@PostMapping("/deleteDensityOfVegetation")
	public ResponseEntity<Object> deleteFcFormDPartBDensityOfVegetation(
			@RequestParam Integer fc_part_b_density_of_vegetation_id) throws PariveshException {
		return fcFormDPartBService.deleteFcFormDPartBDensityOfVegetation(fc_part_b_density_of_vegetation_id);
	}
	
	//Section 4
	//Save Enumeration Details 
//	@PostMapping("/saveEnumerationDetails")
//	public ResponseEntity<Object> saveFcFormDPartBEnumerationDetails(
//			@RequestBody FcFormDPartBEnumerationDetails fcFormDPartBEnumerationDetails,
//			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
//		return fcFormDPartBService.saveFcFormDPartBEnumerationDetails(fcFormDPartBEnumerationDetails,
//				fc_part_b_basic_details_id);
//	}
	
	@PostMapping("/saveTreeDetails")
	public ResponseEntity<Object> saveFCformDPartBTreeDetails(
			@RequestBody List<FcFormDPartBTreeDetails> fcFormDPartBTreeDetails,
			@RequestParam Integer fc_form_d_part_b_basic_details_id) throws PariveshException {
		return fcFormDPartBService.saveFCFormDPartBTreeDetails(fcFormDPartBTreeDetails,
				fc_form_d_part_b_basic_details_id);
	}

	@PostMapping("/deleteTreeDetails")
	public ResponseEntity<Object> deleteFCformDPartBTreeDetails(@RequestParam Integer FC_Tree_Details_Id)
			throws PariveshException {
		return fcFormDPartBService.deleteFCformDPartBTreeDetails(FC_Tree_Details_Id);
	}

	//Section 4
	//Delete Enumeration Details 
	@PostMapping("/deleteEnumerationDetails")
	public ResponseEntity<Object> deleteFcFormDPartBEnumerationDetails(@RequestParam Integer enumerationDetails_id) {
		return fcFormDPartBService.deleteFcFormDPartBEnumerationDetails(enumerationDetails_id);
	}
	
	//Section 8
	//Save WL specific Details
	@PostMapping("/saveWLSpecificDetails")
	public ResponseEntity<Object> saveFcFormDPartBWLSpecificDetails(
			@RequestBody FcFormDPartBWLSpecificDetails fcFormDPartBWLSpecificDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormDPartBService.saveFcFormDPartBWLSpecificDetails(fcFormDPartBWLSpecificDetails,
				fc_part_b_basic_details_id);
	}
	
	//Section 9
	//Save Violation Details
	@PostMapping("/saveViolationDetails")
	public ResponseEntity<Object> saveFcFormDPartBViolationDetails(
			@RequestBody List<FcFormDPartBViolationDetails> fcFormDPartBViolationDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormDPartBService.saveFcFormDPartBViolationDetails(fcFormDPartBViolationDetails,
				fc_part_b_basic_details_id);
	}
	//Section 9
	//Delete Violation Details
	@PostMapping("/deleteViolationDetails")
	public ResponseEntity<Object> deleteFcFormDPartBViolationDetails(@RequestParam Integer vd_id)
			throws PariveshException {
		return fcFormDPartBService.deleteFcFormDPartBViolationDetails(vd_id);
	}
	
	//Section 10 & 11
	//Save Afforestation Details 
	@PostMapping("/saveAfforestationDetails")
	public ResponseEntity<Object> saveFcFormDPartBAfforestationDetails(
			@RequestBody FcFormDPartBAfforestationDetails fcFormDPartBAfforestationDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormDPartBService.saveFcFormDPartBAfforestationDetails(fcFormDPartBAfforestationDetails,
				fc_part_b_basic_details_id);
	}
	
	//Section recommendation 
	//Save Other Details like recommendation, site inspection report
	@PostMapping("/saveOtherDetails")
	public ResponseEntity<Object> saveFcFormDPartBOtherDetails(
			@RequestBody FcFormDPartBOtherDetails fcFormDPartBOtherDetails, @RequestParam Integer fc_form_d_part_b_id,
			HttpServletRequest request) throws PariveshException {
		return fcFormDPartBService.saveFcFormDPartBOtherDetails(fcFormDPartBOtherDetails, fc_form_d_part_b_id, request);

	}
	
	
	
}
