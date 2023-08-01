package com.backend.controller.FcFormBPartB;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.backend.model.FcFormBPartB.*;
import com.backend.model.ForestClearancePartB.ForestClearanceBCaLand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.service.FcFormBPartB.FcFormBPartBService;

@RestController
@RequestMapping("/fc/formb/partb")
public class FcFormBPartBController {

	@Autowired
	private FcFormBPartBService fcFormBPartBService;

	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveFCformBPartBBasicDetails(
			@RequestBody FcFormBPartBBasicDetails fcFormBPartBBasicDetails, HttpServletRequest request)
			throws PariveshException {
		return fcFormBPartBService.saveFCformBPartBBasicDetails(fcFormBPartBBasicDetails, request);

	}

	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> getFCformBPartBBasicDetails(@RequestParam Integer fc_part_b_basic_details_id)
			throws PariveshException {
		return fcFormBPartBService.getFCformBPartBBasicDetails(fc_part_b_basic_details_id);
	}

	@PostMapping("/saveTreeDetails")
	public ResponseEntity<Object> saveFCformBPartBTreeDetails(
			@RequestBody List<FcFormBPartBTreeDetails> fcFormBPartBTreeDetails,
			@RequestParam Integer fc_form_b_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFCFormBPartBTreeDetails(fcFormBPartBTreeDetails,
				fc_form_b_part_b_basic_details_id);
	}

	@PostMapping("/deleteTreeDetails")
	public ResponseEntity<Object> deleteFCformBPartBTreeDetails(@RequestParam Integer FC_Tree_Details_Id)
			throws PariveshException {
		return fcFormBPartBService.deleteFCformBPartBTreeDetails(FC_Tree_Details_Id);
	}

	@PostMapping("/saveViolationDetails")
	public ResponseEntity<Object> saveFcFormBPartBViolationDetails(
			@RequestBody List<FcFormBPartBViolationDetails> fcFormBPartBViolationDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFcFormBPartBViolationDetails(fcFormBPartBViolationDetails,
				fc_part_b_basic_details_id);
	}

	@PostMapping("/deleteViolationDetails")
	public ResponseEntity<Object> deleteFcFormBPartBViolationDetails(@RequestParam Integer vd_id)
			throws PariveshException {
		return fcFormBPartBService.deleteFcFormBPartBViolationDetails(vd_id);
	}

	@PostMapping("/saveDensityOfVegetation")
	public ResponseEntity<Object> saveFcFormBPartBDensityOfVegetation(
			@RequestBody List<FcFormBPartBDensityOfVegetation> fcFormBPartBDensityOfVegetation,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFcFormBPartBDensityOfVegetation(fcFormBPartBDensityOfVegetation,
				fc_part_b_basic_details_id);
	}

	@PostMapping("/deleteDensityOfVegetation")
	public ResponseEntity<Object> deleteFcFormBPartBDensityOfVegetation(
			@RequestParam Integer fc_part_b_density_of_vegetation_id) throws PariveshException {
		return fcFormBPartBService.deleteFcFormBPartBDensityOfVegetation(fc_part_b_density_of_vegetation_id);
	}

	@PostMapping("/saveComponentDetails")
	public ResponseEntity<Object> saveFcFormBPartBComponentDetails(
			@RequestBody List<FcFormBPartBComponentDetails> fcFormBPartBComponentDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFcFormBPartBComponentDetails(fcFormBPartBComponentDetails,
				fc_part_b_basic_details_id);
	}

	@PostMapping("/deleteComponentDetails")
	public ResponseEntity<Object> deleteFcFormBPartBComponentDetails(@RequestParam Integer cd_id)
			throws PariveshException {
		return fcFormBPartBService.deleteFcFormBPartBComponentDetails(cd_id);
	}

	@PostMapping("/saveWLSpecificDetails")
	public ResponseEntity<Object> saveFcFormBPartBWLSpecificDetails(
			@RequestBody FcFormBPartBWLSpecificDetails fcFormBPartBWLSpecificDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFcFormBPartBWLSpecificDetails(fcFormBPartBWLSpecificDetails,
				fc_part_b_basic_details_id);
	}

	@PostMapping("/saveOtherDetails")
	public ResponseEntity<Object> saveFcFormBPartBOtherDetails(
			@RequestBody FcFormBPartBOtherDetails fcFormBPartBOtherDetails, @RequestParam Integer fc_part_b_patch_id,
			HttpServletRequest request) throws PariveshException {
		return fcFormBPartBService.saveFcFormBPartBOtherDetails(fcFormBPartBOtherDetails, fc_part_b_patch_id, request);

	}

	@PostMapping("/saveAfforestationDetails")
	public ResponseEntity<Object> saveFcFormBPartBAfforestationDetails(
			@RequestBody FcFormBPartBAfforestationDetails fcFormBPartBAfforestationDetails,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFcFormBPartBAfforestationDetails(fcFormBPartBAfforestationDetails,
				fc_part_b_basic_details_id);
	}

	@PostMapping("/deleteEnumerationDetails")
	public ResponseEntity<Object> deleteFcFormBPartBEnumerationDetails(@RequestParam Integer enumerationDetails_id) {
		return fcFormBPartBService.deleteFcFormBPartBEnumerationDetails(enumerationDetails_id);
	}

	@PostMapping("/saveDistrictWise")
	public ResponseEntity<Object> saveFcFormBPartBDistrictWise(
			@RequestBody List<FcFormBPartBDistrictWise> fcFormBPartBDistrictWises,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFcFormBPartBDistrictWise(fcFormBPartBDistrictWises,
				fc_part_b_basic_details_id);
	}
	
	@PostMapping("/deleteDistrictWise")
	public ResponseEntity<Object> deleteFcFormBPartBDistritwise(@RequestParam Integer districtWise_id) {
		return fcFormBPartBService.deleteFcFormBPartBDistritwise(districtWise_id);
	}

	@PostMapping("/deleteLegalStatus")
	public ResponseEntity<Object> deleteFcFormBPartBLegalStatus(@RequestParam Integer legalStstus_id) {
		return fcFormBPartBService.deleteFcFormBPartBLegalStatus(legalStstus_id);
	}

	@PostMapping("/deletePatch")
	public ResponseEntity<Object> deleteFcFormBPartBPatch(@RequestParam Integer p_id) throws PariveshException {
		return fcFormBPartBService.deleteFcFormBPartBPatch(p_id);
	}

//	@PostMapping("/savePatchWiseDetails")
//	public ResponseEntity<Object> saveFcFormBPartBPatchWiseDetails(
//			@RequestBody List<FcFormBPartBPatchWiseDetails> fcFormBPartBPatchWiseDetails,
//			@RequestParam Integer patch_id) throws PariveshException {
//		return fcFormBPartBService.saveFcFormBPartBPatchWiseDetails(fcFormBPartBPatchWiseDetails, patch_id);
//
//	}

	@PostMapping("/savePatch")
	public ResponseEntity<Object> saveFcFormBPartBPatch(@RequestBody List<FcFormBPartBPatches> fcFormBPartBPatches,
			@RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFcFormBPartBPatch(fcFormBPartBPatches, fc_part_b_basic_details_id);
	}

	@PostMapping("/saveCaLand")
	public ResponseEntity<Object> saveFCFormBPartBCaLand(@RequestBody List<FcFormBPartBCaLand> forestClearanceBCaLands,
													@RequestParam Integer fc_form_b_part_b_basic_details_id) throws PariveshException {
		return fcFormBPartBService.saveFCFormBPartBCaLand(forestClearanceBCaLands, fc_form_b_part_b_basic_details_id);
	}

	@PostMapping("/deleteCaLand")
	public ResponseEntity<Object> deleteFCFormBPartBCaLand(@RequestParam Integer ca_id) throws PariveshException {
		return fcFormBPartBService.deleteFCFormBPartBCaLand(ca_id);
	}
}
