package com.backend.controller.ForestClearanceFormEPartB;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.backend.model.FcFormEPartB.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFormBPartB.FcFormBPartBDistrictWise;
import com.backend.service.ForestClearanceFormEPartB.FcFormEPartBService;


@RestController
@RequestMapping("/fc/forme/partb")
public class FcFormEPartBController {

	@Autowired
	private FcFormEPartBService fcFormEPartBService;

	@PostMapping("/saveBasicDetails")
	public ResponseEntity<Object> saveFCPartBBasicDetails(
			@RequestBody ForestClearanceEBasicDetails forestClearanceEBasicDetails,HttpServletRequest request)
			throws PariveshException {
		return fcFormEPartBService.saveFCPartEBasicDetails(forestClearanceEBasicDetails,request);

	}

	@PostMapping("/getBasicDetails")
	public ResponseEntity<Object> getFCPartBBasicDetails(@RequestParam Integer id)
			throws PariveshException {
		return fcFormEPartBService.getFCPartEBasicDetails(id);
	}

	@PostMapping("/saveTreeDetails")
	public ResponseEntity<Object> saveFCPartBBasicDetails(
			@RequestBody List<ForestClearanceETreeDetails> forestClearanceETreeDetails,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFCPartETreeDetails(forestClearanceETreeDetails,
				id);
	}

	@PostMapping("/deleteTreeDetails")
	public ResponseEntity<Object> saveFCPartBBasicDetails(@RequestParam Integer id)
			throws PariveshException {
		return fcFormEPartBService.deleteFCPartETreeDetails(id);
	}

	@PostMapping("/saveViolationDetails")
	public ResponseEntity<Object> saveFCPartBViolationDetails(
			@RequestBody List<ForestClearanceEViolationDetails> forestClearanceEViolationDetails,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFCPartEViolationDetails(forestClearanceEViolationDetails,
				id);
	}

	@PostMapping("/deleteViolationDetails")
	public ResponseEntity<Object> deleteFCPartBViolationDetails(@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.deleteFCPartEViolationDetails(id);
	}

	@PostMapping("/saveComponentDetails")
	public ResponseEntity<Object> saveFCPartBComponentDetails(
			@RequestBody List<ForestClearanceEComponentDetails> forestClearanceEComponentDetails,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFCPartEComponentDetails(forestClearanceEComponentDetails,
				id);
	}

	@PostMapping("/deleteComponentDetails")
	public ResponseEntity<Object> deleteFCPartBComponentDetails(@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.deleteFCPartEComponentDetails(id);
	}

	@PostMapping("/deleteAllComponentDetails")
	public ResponseEntity<Object> deleteFCPartBAllComponentDetails(@RequestParam Integer id)
			throws PariveshException {
		return fcFormEPartBService.deleteFCPartEAllComponentDetails(id);
	}

	@PostMapping("/saveAfforestationDetails")
	public ResponseEntity<Object> saveFCPartBAfforestationDetails(
			@RequestBody ForestClearanceEAfforestationDetails forestClearanceEAfforestationDetails,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFCPartEAfforestationDetails(forestClearanceEAfforestationDetails,
				id);
	}

	@PostMapping("/deleteAfforestationDetails")
	public ResponseEntity<Object> deleteFCPartBAfforestationDetails(@RequestParam Integer id)
			throws PariveshException {
		return fcFormEPartBService.deleteFCPartEAfforestationDetails(id);
	}

	@PostMapping("/savePatch")
	public ResponseEntity<Object> saveFCPartEPatch(@RequestBody List<ForestClearanceEPatches> forestClearanceEPatches,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFCPartEPatch(forestClearanceEPatches, id);
	}

	@PostMapping("/deletePatch")
	public ResponseEntity<Object> deleteFCPartEPatch(@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.deleteFCPartEPatch(id);
	}

	@PostMapping("/saveWLSpecificDetails")
	public ResponseEntity<Object> FCPartEWLSpecificDetails(
			@RequestBody ForestClearanceEWLSpecificDetails forestClearanceEWLSpecificDetails,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFCPartEWLSpecificDetails(forestClearanceEWLSpecificDetails,
				id);
	}

	@PostMapping("/saveOtherDetails")
	public ResponseEntity<Object> FCPartEOtherDetails(
			@RequestBody ForestClearanceEOtherDetails forestClearanceEOtherDetails,
			@RequestParam Integer id, HttpServletRequest request) throws PariveshException {
		return fcFormEPartBService.saveFCPartEOtherDetails(forestClearanceEOtherDetails, id,
				request);

	}

	@PostMapping("/saveDensityOfVegetation")
	public ResponseEntity<Object> saveFCPartEDensityOfVegetation(
			@RequestBody List<ForestClearanceEDensityOfVegetation> forestClearanceEDensityOfVegetations,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFCPartEDensityOfVegetation(forestClearanceEDensityOfVegetations,
				id);
	}

	@PostMapping("/deleteDensityOfVegetation")
	public ResponseEntity<Object> deleteFCPartEDensityOfVegetation(
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.deleteFCPartEDensityOfVegetation(id);
	}

	@PostMapping("/savePatchWiseDetails")
	public ResponseEntity<Object> saveFCPartEPatchWiseDetails(
			@RequestBody List<ForestClearanceEPatchWiseDetails> forestClearanceEPatchWiseDetails,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFCPartEPatchWiseDetails(forestClearanceEPatchWiseDetails, id);

	}

	@PostMapping("/deletePatchWiseDetails")
	public ResponseEntity<Object> deleteFCPartEPatchWiseDetails(@RequestParam Integer id)
			throws PariveshException {
		return fcFormEPartBService.deleteFCPartEPatchWiseDetails(id);

	}

	@PostMapping("/deleteEnumerationDetails")
	public ResponseEntity<Object> deleteFCPartEEnumerationDetails(@RequestParam Integer id) {
		return fcFormEPartBService.deleteFCPartEEnumerationDetails(id);
	}

	/*@PostMapping("/deleteDistrictWise")
	public ResponseEntity<Object> deleteFCPartEDistritwise(@RequestParam Integer districtWise_id) {
		return fcFormEPartBService.deleteFCPartEDistrictwise(districtWise_id);
	}*/
	
	@PostMapping("/saveDistrictWise")
	public ResponseEntity<Object> saveFcFormBPartBDistrictWise(
			@RequestBody List<ForestClearanceEDistrictWise> fcFormEPartBDistrictWises,
			@RequestParam Integer id) throws PariveshException {
		return fcFormEPartBService.saveFcFormBPartBDistrictWise(fcFormEPartBDistrictWises,
				id);
	}
	
	@PostMapping("/deleteDistrictWise")
	public ResponseEntity<Object> deleteFcFormBPartBDistritwise(@RequestParam Integer id) {
		return fcFormEPartBService.deleteFcFormEPartBDistrictwise(id);
	}

	@PostMapping("/deleteLegalStatus")
	public ResponseEntity<Object> deleteFCPartELegalStatus(@RequestParam Integer id) {
		return fcFormEPartBService.deleteFCPartELegalStatus(id);
	}


	@PostMapping("/saveCaLand")
	public ResponseEntity<Object> saveFCFormDPartBCaLand(@RequestBody List<FcFormEPartBCaLand> forestClearanceBCaLands,
														 @RequestParam Integer fc_form_e_part_b_basic_details_id) throws PariveshException {
		return fcFormEPartBService.saveFCFormEPartBCaLand(forestClearanceBCaLands, fc_form_e_part_b_basic_details_id);
	}

	@PostMapping("/deleteCaLand")
	public ResponseEntity<Object> deleteFCFormCPartBCaLand(@RequestParam Integer ca_id) throws PariveshException {
		return fcFormEPartBService.deleteFCFormEPartBCaLand(ca_id);
	}
}

