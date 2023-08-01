package com.backend.controller.ForestClearanceFormCPartB;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.backend.model.FcFormBPartB.FcFormBPartBCaLand;
import com.backend.model.ForestClearanceFormCPartB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.ForestClearancePartB.ForestClearanceBAfforestationDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBComponentDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBDensityOfVegetation;
import com.backend.model.ForestClearancePartB.ForestClearanceBDistrictWise;
import com.backend.model.ForestClearancePartB.ForestClearanceBOtherDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBPatchWiseDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBPatches;
import com.backend.model.ForestClearancePartB.ForestClearanceBViolationDetails;
import com.backend.model.ForestClearancePartB.ForestClearanceBWLSpecificDetails;
import com.backend.response.ResponseHandler;
import com.backend.service.ForestClearanceFormCPartB.FcFormCPartBService;

@RestController
@RequestMapping("/fc/formc/partb")
public class FcFormCPartBController {
	
	@Autowired
	private FcFormCPartBService fcFormCPartBService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveFCFormCPartBBasicDetails(
			@RequestBody FcFormCPartB fcFormCPartB,HttpServletRequest request)
			throws PariveshException {
		return fcFormCPartBService.saveFcFormCPartB(fcFormCPartB,request);
	}
	
	@PostMapping("/get")
	public ResponseEntity<Object> getFCPartBBasicDetails(@RequestParam Integer fc_form_c_part_b_basic_details_id)
			throws PariveshException {
		return fcFormCPartBService.getFCFormCPartB(fc_form_c_part_b_basic_details_id);
	}
	
	@PostMapping("/saveTreeDetails")
	public ResponseEntity<Object> saveFCFormCPartBTreeDetails(
			@RequestBody List<FcFormCPartBTreeDetails> forestClearanceBTreeDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormCPartBService.saveFcFormCPartBTreeDetails(forestClearanceBTreeDetails,
				fc_part_b_basic_details_id);
	}
	
	@PostMapping("/saveDistrictWiseDetails")
	public ResponseEntity<Object> saveFCFormCPartBDistrictWise(
			@RequestBody List<FcFormCPartBDistrictWise> fcFormCPartBDistrictWises,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormCPartBService.saveFCFormCPartBDistrictwise(fcFormCPartBDistrictWises,
				fc_part_b_basic_details_id);
	}
	
	@PostMapping("/deleteTreeDetails")
	public ResponseEntity<Object> deleteFCFormCPartB(@RequestParam Integer FC_Tree_Details_Id)
			throws PariveshException {
		return fcFormCPartBService.deleteFCFormCPartBTreeDetails(FC_Tree_Details_Id);
	}
	
	@PostMapping("/saveDensityOfVegetation")
	public ResponseEntity<Object> saveFCFormCPartBDensityOfVegetation(
			@RequestBody List<FcFormCPartBDensityOfVegetation> fcFormCPartBDensityOfVegetations,
			@RequestParam Integer fc_form_c_part_b__id) throws PariveshException {
		return fcFormCPartBService.saveFCFormCPartBDensityOfVegetation(fcFormCPartBDensityOfVegetations,
				fc_form_c_part_b__id);
	}

	@PostMapping("/deleteDensityOfVegetation")
	public ResponseEntity<Object> deleteFCFormCPartBDensityOfVegetation(
			@RequestParam Integer fc_form_c_part_b_density_of_vegetation_id) throws PariveshException {
		return fcFormCPartBService.deleteFCFormCPartBDensityOfVegetation(fc_form_c_part_b_density_of_vegetation_id);
	}
	
	@PostMapping("/saveViolationDetails")
	public ResponseEntity<Object> saveFCFormCPartBViolationDetails(
			@RequestBody List<FcFormCPartBViolationDetails> forestClearanceBViolationDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormCPartBService.saveFCFormCPartBViolationDetails(forestClearanceBViolationDetails,
				fc_part_b_basic_details_id);
	}

	@PostMapping("/deleteViolationDetails")
	public ResponseEntity<Object> deleteFCFormCPartBViolationDetails(@RequestParam Integer vd_id) throws PariveshException {
		return fcFormCPartBService.deleteFCFormCPartBViolationDetails(vd_id);
	}
	
	@PostMapping("/deleteDistrictWise")
	public ResponseEntity<Object> deleteFCPartBDistritwise(@RequestParam Integer districtWise_id) {
		return fcFormCPartBService.deleteFCFormCPartBDistrictwise(districtWise_id);
	}

	@PostMapping("/deleteEnumerationDetails")
	public ResponseEntity<Object> deleteFCPartBEnumerationDetails(@RequestParam Integer enumerationDetails_id) {
		return fcFormCPartBService.deleteFCFormCPartBEnumerationDetails(enumerationDetails_id);
	}
	
	/*
	 * Step: 2 component and wl
	 */

	@PostMapping("/saveComponentDetails")
	public ResponseEntity<Object> saveFCPartBComponentDetails(
			@RequestBody List<FcFormCPartBComponentDetails> forestClearanceBComponentDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormCPartBService.saveFcFormCPartBComponentDetails(forestClearanceBComponentDetails,
				fc_part_b_basic_details_id);
	}

	@PostMapping("/deleteComponentDetails")
	public ResponseEntity<Object> deleteFCPartBComponentDetails(@RequestParam Integer cd_id) throws PariveshException {
		return fcFormCPartBService.deleteFcFormBPartBComponentDetails(cd_id);
	}

	@PostMapping("/deleteAllComponentDetails")
	public ResponseEntity<Object> deleteFCPartBAllComponentDetails(@RequestParam Integer fc_form_c_part_b__id)
			throws PariveshException {
		return fcFormCPartBService.deleteFCPartBAllComponentDetails(fc_form_c_part_b__id);
	}
	
	
	@PostMapping("/saveWLSpecificDetails")
	public ResponseEntity<Object> FCPartBWLSpecificDetails(
			@RequestBody FcFormCPartBWLSpecificDetails forestClearanceBWLSpecificDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormCPartBService.saveFCPartBWLSpecificDetails(forestClearanceBWLSpecificDetails,
				fc_part_b_basic_details_id);
	}
	
	
	/*
	 * Step: 3 afforestation
	 */
	@PostMapping("/saveAfforestationDetails")
	public ResponseEntity<Object> saveFCPartBAfforestationDetails(
			@RequestBody FcFormCPartBAfforestationDetails forestClearanceBAfforestationDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormCPartBService.saveFCFormCPartBAfforestationDetails(forestClearanceBAfforestationDetails,
				fc_part_b_basic_details_id);
	}

	@PostMapping("/deleteAfforestationDetails")
	public ResponseEntity<Object> deleteFCPartBAfforestationDetails(@RequestParam Integer ad_id)
			throws PariveshException {
		return fcFormCPartBService.deleteFCFormCPartBAfforestationDetails(ad_id);
	}
	
	
	/*
	 * Step :4 others
	 */
	@PostMapping("/saveOtherDetails")
	public ResponseEntity<Object> FCPartBOtherDetails(
			@RequestBody FcFormCPartBOtherDetails forestClearanceBOtherDetails,
			@RequestParam Integer fc_part_b_patch_id, HttpServletRequest request) throws PariveshException {
		return fcFormCPartBService.saveFCFormCPartBOtherDetails(forestClearanceBOtherDetails, fc_part_b_patch_id,
				request);

	}
	

	/*
	 * Patch and Patch Wise API
	 */
	
//	@PostMapping("/savePatchWiseDetails")
//	public ResponseEntity<Object> saveFCPartBPatchWiseDetails(
//			@RequestBody List<FcFormCPartBPatchWiseDetails> forestClearanceBPatchWiseDetails,
//			@RequestParam Integer patch_id) throws PariveshException {
//		return fcFormCPartBService.saveFCFormCPartBPatchWiseDetails(forestClearanceBPatchWiseDetails, patch_id);
//
//	}

//	@PostMapping("/deletePatchWiseDetails")
//	public ResponseEntity<Object> deleteFCPartBPatchWiseDetails(@RequestParam Integer patch_id)
//			throws PariveshException {
//		return fcFormCPartBService.deleteFCFormCPartBPatchWiseDetails(patch_id);
//
//	}
	
	@PostMapping("/savePatch")
	public ResponseEntity<Object> saveFCPartBPatch(@RequestBody List<FcFormCPartBPatches> forestClearanceBPatches,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormCPartBService.saveFCFormCPartBPatch(forestClearanceBPatches, fc_part_b_basic_details_id);
	}

	@PostMapping("/deletePatch")
	public ResponseEntity<Object> deleteFCPartBPatch(@RequestParam Integer p_id) throws PariveshException {
		return fcFormCPartBService.deleteFCFormCPartBPatch(p_id);
	}
	
	@PostMapping("/deleteLegalStatus")
	public ResponseEntity<Object> deleteFCPartBLegalStatus(@RequestParam Integer legalStstus_id) {
		return fcFormCPartBService.deleteFCFormCPartBLegalStatus(legalStstus_id);
	}

	@PostMapping("/saveCaLand")
	public ResponseEntity<Object> saveFCFormCPartBCaLand(@RequestBody List<FcFormCPartBCaLand> forestClearanceBCaLands,
														 @RequestParam Integer fc_form_c_part_b_basic_details_id) throws PariveshException {
		return fcFormCPartBService.saveFCFormCPartBCaLand(forestClearanceBCaLands, fc_form_c_part_b_basic_details_id);
	}

	@PostMapping("/deleteCaLand")
	public ResponseEntity<Object> deleteFCFormCPartBCaLand(@RequestParam Integer ca_id) throws PariveshException {
		return fcFormCPartBService.deleteFCFormCPartBCaLand(ca_id);
	}
}
