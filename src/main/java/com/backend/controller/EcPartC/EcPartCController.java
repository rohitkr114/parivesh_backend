package com.backend.controller.EcPartC;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcPartC.EcAirQualityImpacts;
import com.backend.model.EcPartC.EcAmbientAirQuality;
import com.backend.model.EcPartC.EcBaseLineCollections;
import com.backend.model.EcPartC.EcBaseLineDetails;
import com.backend.model.EcPartC.EcChemicalProperties;
import com.backend.model.EcPartC.EcEnclosures;
import com.backend.model.EcPartC.EcGroundWaterLevel;
import com.backend.model.EcPartC.EcGroundWaterQuality;
import com.backend.model.EcPartC.EcNoiseLevel;
import com.backend.model.EcPartC.EcOtherDetails;
import com.backend.model.EcPartC.EcParameterMonitor;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EcPartC.EcSoilQuality;
import com.backend.model.EcPartC.EcSummaryAllocations;
import com.backend.model.EcPartC.EcSurfaceWaterQuality;
import com.backend.model.EcPartC.EcUndertaking;
import com.backend.service.EcPartC.EcPartCService;

@RestController
@RequestMapping("/ecPartC")
public class EcPartCController {

	@Autowired
	public EcPartCService ecPartCService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveEcPartC(@RequestParam(required = false) Integer ec_id,
			@RequestParam(required = false) Integer caf_id, @RequestBody EcPartC ecPartC, HttpServletRequest request,@RequestParam(required = false)  Integer clearance_id)
			throws PariveshException {
		return ecPartCService.saveEcPartC(ecPartC, ec_id, caf_id, request, clearance_id);
	}

	@PostMapping("/saveBaseLineDetails")
	public ResponseEntity<Object> saveBaseLineDetails(@RequestParam Integer ec_partc_id,
			@RequestBody EcBaseLineDetails ecBaseLineDetails) throws PariveshException {
		return ecPartCService.saveBaseLineDetails(ecBaseLineDetails, ec_partc_id);
	}

	@PostMapping("/saveOtherDetails")
	public ResponseEntity<Object> saveOtherDetails(@RequestParam Integer ec_partc_id,
			@RequestBody EcOtherDetails ecOtherDetails) throws PariveshException {
		return ecPartCService.saveOtherDetails(ecOtherDetails, ec_partc_id);
	}

	@PostMapping("/saveEnclosures")
	public ResponseEntity<Object> saveEnclosures(@RequestParam Integer ec_partc_id,
			@RequestBody EcEnclosures ecEnclosures) throws PariveshException {
		return ecPartCService.saveEnclosures(ecEnclosures, ec_partc_id);
	}

	@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveUndertaking(@RequestParam Integer ec_partc_id,
			@RequestParam(required = false) Integer caf_id, @RequestBody EcUndertaking ecUndertaking,@RequestParam(required = false) Boolean is_submit,
			HttpServletRequest request) throws PariveshException {
		return ecPartCService.saveUndertaking(ecUndertaking, ec_partc_id, caf_id,is_submit,request);
	}

	@PostMapping("/deletePublicHearing")
	public ResponseEntity<Object> deletePublicHearing(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deletePublicHearing(id);
	}

	@PostMapping("/deleteMajorIssues")
	public ResponseEntity<Object> deleteMajorIssues(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteMajorIssues(id);
	}

	@PostMapping("/deleteAmbientAirQuality")
	public ResponseEntity<Object> deleteAmbientAirQuality(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteAmbientAirQuality(id);
	}

	@PostMapping("/deleteBaseLineCollection")
	public ResponseEntity<Object> deleteBaseLineCollection(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteBaseLineCollection(id);
	}

	@PostMapping("/deleteChemicalProperties")
	public ResponseEntity<Object> deleteChemicalProperties(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteChemicalProperties(id);
	}

	@PostMapping("/deleteGroundWaterLevel")
	public ResponseEntity<Object> deleteGroundWaterLevel(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteGroundWaterLevel(id);
	}

	@PostMapping("/deleteGroundWaterQuality")
	public ResponseEntity<Object> deleteGroundWaterQuality(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteGroundWaterQuality(id);
	}

	@PostMapping("/deleteNoiseLevel")
	public ResponseEntity<Object> deleteNoiseLevel(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteNoiseLevel(id);
	}

	@PostMapping("/deleteSoilQuality")
	public ResponseEntity<Object> deleteSoilQuality(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteSoilQuality(id);
	}

	@PostMapping("/deleteSurfaceWaterQuality")
	public ResponseEntity<Object> deleteSurfaceWaterQuality(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteSurfaceWaterQuality(id);
	}

	@PostMapping("/deleteAirQualityImpact")
	public ResponseEntity<Object> deleteAirQualityImpact(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteAirQualityImpact(id);
	}

	@PostMapping("/deleteSummaryAllocation")
	public ResponseEntity<Object> deleteSummaryAllocation(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteSummaryAllocation(id);
	}

	@PostMapping("/deleteParameterMonitor")
	public ResponseEntity<Object> deleteParameterMonitor(@RequestParam Integer id) throws PariveshException {
		return ecPartCService.deleteParameterMonitor(id);
	}

	@PostMapping("/getForm")
	public ResponseEntity<Object> getEcPartCForm(@RequestParam Integer ec_partc_id, @RequestParam(value = "step", required = false) Integer step)
			throws PariveshException {
		return ecPartCService.getEcPartCForm(ec_partc_id, step);
	}

	@PostMapping("/saveAmbientAirQuality")
	public ResponseEntity<Object> saveAmbientAirQuality(@RequestBody EcAmbientAirQuality ecAmbientAirQuality,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveAmbientAirQuality(ecAmbientAirQuality, ec_partc_id);
	}

	@PostMapping("/saveBaseLineCollection")
	public ResponseEntity<Object> saveBaseLineCollection(@RequestBody EcBaseLineCollections ecBaseLineCollections,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveBaseLineCollection(ecBaseLineCollections, ec_partc_id);
	}

	@PostMapping("/saveChemicalProperties")
	public ResponseEntity<Object> saveChemicalProperties(@RequestBody EcChemicalProperties ecChemicalProperties,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveChemicalProperties(ecChemicalProperties, ec_partc_id);
	}

	@PostMapping("/saveGroundWaterLevel")
	public ResponseEntity<Object> saveGroundWaterLevel(@RequestBody EcGroundWaterLevel ecGroundWaterLevel,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveGroundWaterLevel(ecGroundWaterLevel, ec_partc_id);
	}

	@PostMapping("/saveGroundWaterQuality")
	public ResponseEntity<Object> saveGroundWaterQuality(@RequestBody EcGroundWaterQuality ecGroundWaterQuality,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveGroundWaterQuality(ecGroundWaterQuality, ec_partc_id);
	}

	@PostMapping("/saveNoiseLevel")
	public ResponseEntity<Object> saveNoiseLevel(@RequestBody EcNoiseLevel ecNoiseLevel,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveNoiseLevel(ecNoiseLevel, ec_partc_id);
	}

	@PostMapping("/saveSoilQuality")
	public ResponseEntity<Object> saveSoilQuality(@RequestBody EcSoilQuality ecSoilQuality,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveSoilQuality(ecSoilQuality, ec_partc_id);
	}

	@PostMapping("/saveSurfaceWaterQuality")
	public ResponseEntity<Object> saveSurfaceWaterQuality(@RequestBody EcSurfaceWaterQuality ecSurfaceWaterQuality,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveSurfaceWaterQuality(ecSurfaceWaterQuality, ec_partc_id);
	}

	@PostMapping("/saveAirQualityImpacts")
	public ResponseEntity<Object> saveAirQualityImpacts(@RequestBody EcAirQualityImpacts ecAirQualityImpacts,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveAirQualityImpacts(ecAirQualityImpacts, ec_partc_id);
	}

	@PostMapping("/saveSummaryAllocations")
	public ResponseEntity<Object> saveSummaryAllocations(@RequestBody EcSummaryAllocations ecSummaryAllocations,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveSummaryAllocations(ecSummaryAllocations, ec_partc_id);
	}

	@PostMapping("/saveParameterMonitor")
	public ResponseEntity<Object> saveParameterMonitor(@RequestBody EcParameterMonitor ecParameterMonitor,
			@RequestParam Integer ec_partc_id) throws PariveshException {
		return ecPartCService.saveParameterMonitor(ecParameterMonitor, ec_partc_id);
	}

}
