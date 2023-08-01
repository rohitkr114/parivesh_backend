package com.backend.controller.EnvironmentClearance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EnvironmentClearance.ConstructionDetails;
import com.backend.model.EnvironmentClearance.CurrentLandUse;
import com.backend.model.EnvironmentClearance.DemolitionDetails;
import com.backend.model.EnvironmentClearance.EcAirPollutionMitigation;
import com.backend.model.EnvironmentClearance.EcAirportProposal;
import com.backend.model.EnvironmentClearance.EcCBWTFProposals;
import com.backend.model.EnvironmentClearance.EcCETPProposals;
import com.backend.model.EnvironmentClearance.EcCMSWMFProposals;
import com.backend.model.EnvironmentClearance.EcChecklistDetails;
import com.backend.model.EnvironmentClearance.EcConstructionDetail;
import com.backend.model.EnvironmentClearance.EcCroppingPattern;
import com.backend.model.EnvironmentClearance.EcDemolitionTempConstruction;
import com.backend.model.EnvironmentClearance.EcGreenBelt;
import com.backend.model.EnvironmentClearance.EcIndustryProposal;
import com.backend.model.EnvironmentClearance.EcIrrigationProjectCapacityVillage;
import com.backend.model.EnvironmentClearance.EcMiningProposals;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.EnvironmentClearance.EcPhysicalChanges;
import com.backend.model.EnvironmentClearance.EcPollutantDetails;
import com.backend.model.EnvironmentClearance.EcPollutionDetails;
import com.backend.model.EnvironmentClearance.EcProposedProjectLandDetails;
import com.backend.model.EnvironmentClearance.EcResource;
import com.backend.model.EnvironmentClearance.EcRiskFactor;
import com.backend.model.EnvironmentClearance.EcRiverValleyProject;
import com.backend.model.EnvironmentClearance.EcStreamCrossing;
import com.backend.model.EnvironmentClearance.EcSubmergedArea;
import com.backend.model.EnvironmentClearance.EcTSDFProposals;
import com.backend.model.EnvironmentClearance.EcTownshipProposals;
import com.backend.model.EnvironmentClearance.EcWasteDetails;
import com.backend.model.EnvironmentClearance.EcWasteProduction;
import com.backend.model.EnvironmentClearance.EcWaterDetails;
import com.backend.model.EnvironmentClearance.ProposedLandUse;
import com.backend.model.EnvironmentClearance.ProposedLandUseExpansion;
import com.backend.response.ResponseHandler;
import com.backend.service.EnvironmentClearance.EcPartBService;

@RestController
@RequestMapping("/ecpartB")
public class EcPartBController {

	@Autowired
	EcPartBService ecPartBService;

	@PostMapping("/saveEcPartB")
	public ResponseEntity<Object> saveEcDemolitionTemp(@RequestParam Integer ec_id, @RequestBody EcPartB ecPartB)
			throws PariveshException {
		return ResponseHandler.generateResponse("Save EC Project Activity Data", HttpStatus.OK, "",
				ecPartBService.saveEcPartB(ec_id, ecPartB));
	}

	@PostMapping("/saveEcConstructionDetail")
	public ResponseEntity<Object> saveEcConstructionDetail(@RequestParam Integer ec_partb_id,
			@RequestBody EcConstructionDetail ecConstructionDetail) throws PariveshException {
		return ResponseHandler.generateResponse("Save EC Construction Detail Data", HttpStatus.OK, "",
				ecPartBService.saveConstructionDetail(ec_partb_id, ecConstructionDetail));
	}

	@PostMapping("/saveEcWaterDetails")
	public ResponseEntity<Object> saveEcWaterDetails(@RequestParam Integer ec_partb_id,
			@RequestBody EcWaterDetails ecWaterDetails) throws PariveshException {
		return ResponseHandler.generateResponse("Save EC Water Details", HttpStatus.OK, "",
				ecPartBService.saveEcWaterDetails(ec_partb_id, ecWaterDetails));
	}

	@PostMapping("/saveEcWasteProduction")
	public ResponseEntity<Object> saveEcWasteProduction(@RequestParam Integer ec_partb_id,
			@RequestBody EcWasteProduction ecWasteDetails) throws PariveshException {
		return ResponseHandler.generateResponse("save Ec Waste Production", HttpStatus.OK, "",
				ecPartBService.saveEcWasteProduction(ec_partb_id, ecWasteDetails));
	}

	@PostMapping("/saveEcRiskFactor")
	public ResponseEntity<Object> saveEcRiskFactor(@RequestParam Integer ec_partb_id,
			@RequestBody EcRiskFactor ecRiskFactor) throws PariveshException {
		return ResponseHandler.generateResponse("Save EC Risk Factor", HttpStatus.OK, "",
				ecPartBService.saveEcRiskFactor(ec_partb_id, ecRiskFactor));
	}

	@PostMapping("/saveEcMiningProposals")
	public ResponseEntity<Object> saveEcMiningProposals(@RequestParam Integer ec_partb_id,
			@RequestBody EcMiningProposals ecMiningProposals) throws PariveshException {
		return ResponseHandler.generateResponse("save Ec Mining Proposals", HttpStatus.OK, "",
				ecPartBService.saveEcMiningProposals(ec_partb_id, ecMiningProposals));
	}

	@PostMapping("/saveEcTownshipProposals")
	public ResponseEntity<Object> saveEcTownshipProposals(@RequestParam Integer ec_partb_id,
			@RequestBody EcTownshipProposals ecTownshipProposals) throws PariveshException {
		return ResponseHandler.generateResponse("save Ec Township Proposals", HttpStatus.OK, "",
				ecPartBService.saveEcTownshipProposals(ec_partb_id, ecTownshipProposals));
	}

	@PostMapping("/saveEcIndustryProposal")
	public ResponseEntity<Object> saveIndustryProposal(@RequestParam Integer ec_partb_id,
			@RequestBody EcIndustryProposal ecIndustryProposal) throws PariveshException {
		return ResponseHandler.generateResponse("save Ec Industry Proposal", HttpStatus.OK, "",
				ecPartBService.saveEcIndustryProposal(ec_partb_id, ecIndustryProposal));
	}

	@PostMapping("/getEcPartB")
	public ResponseEntity<Object> getEcPartB(@RequestParam("ec_partb_id") Integer ec_partb_id)
			throws PariveshException {
		return ResponseHandler.generateResponse("get EcPartB", HttpStatus.OK, "",
				ecPartBService.getEcPartB(ec_partb_id));
	}

	@PostMapping("/getEcPartBbyEcId")
	public ResponseEntity<Object> getEcPartBbyEcId(@RequestParam("ec_id") Integer ec_id) throws PariveshException {
		return ResponseHandler.generateResponse("get EcPartB", HttpStatus.OK, "",
				ecPartBService.getEcPartBbyEcId(ec_id));
	}

	@PostMapping("saveDemolitionTempConstruction")
	public ResponseEntity<Object> saveEcDemolitionTemp(
			@RequestBody List<EcDemolitionTempConstruction> ecDemolitionTempConstruction,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcDemolitionTemp(ecDemolitionTempConstruction, ecPartBId);
	}

	@PostMapping("deleteDemolitionTempConstruction")
	public ResponseEntity<Object> deleteEcDemolitionTemp(@RequestParam Integer EcDemolitionTempId)
			throws PariveshException {
		return ecPartBService.DeleteEcDemolitionTemp(EcDemolitionTempId);
	}

	@PostMapping("saveStreamCrossing")
	public ResponseEntity<Object> saveEcStreamCrossing(@RequestBody List<EcStreamCrossing> ecStreamCrossings,
			@RequestParam Integer ecPhysicalChangesId) throws PariveshException {
		return ecPartBService.saveEcStreamCrossings(ecStreamCrossings, ecPhysicalChangesId);
	}

	@PostMapping("deleteStreamCrossing")
	public ResponseEntity<Object> deleteEcStreamCrossing(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.DeleteEcStreamCrossings(id);
	}

	@PostMapping("saveEcResource")
	public ResponseEntity<Object> saveEcResource(@RequestBody List<EcResource> EcResource,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcResource(EcResource, ecPartBId);
	}

	@PostMapping("deleteEcResource")
	public ResponseEntity<Object> deleteEcResource(@RequestParam Integer EcResourceId) throws PariveshException {
		return ecPartBService.deleteEcResource(EcResourceId);
	}

	@PostMapping("saveGreenBelt")
	public ResponseEntity<Object> saveGreenBelt(@RequestBody EcGreenBelt ecGreenBelts, @RequestParam Integer ecPartBId)
			throws PariveshException {
		return ecPartBService.saveEcGreenBelt(ecGreenBelts, ecPartBId);
	}

	@PostMapping("deleteGreenBelt")
	public ResponseEntity<Object> deleteGreenBelt(@RequestParam Integer ecGreenBeltId) throws PariveshException {
		return ecPartBService.deleteEcGreenBelt(ecGreenBeltId);
	}

	@PostMapping("saveWasteDetails")
	public ResponseEntity<Object> saveWasteDetails(@RequestBody List<EcWasteDetails> ecWasteDetails,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcWasteDetails(ecWasteDetails, ecPartBId);
	}

	@PostMapping("deleteWasteDetails")
	public ResponseEntity<Object> deleteWasteDetails(@RequestParam Integer ecWasteDetailId) throws PariveshException {
		return ecPartBService.deleteEcWasteDetails(ecWasteDetailId);
	}

	@PostMapping("saveAirPollutionMitigation")
	public ResponseEntity<Object> saveAirPollutionMitigation(
			@RequestBody List<EcAirPollutionMitigation> ecAirPollutionMitigation, @RequestParam Integer ecPartBId)
			throws PariveshException {
		return ecPartBService.saveEcAirPollutionMitigation(ecAirPollutionMitigation, ecPartBId);
	}

	@PostMapping("deleteAirPollutionMitigation")
	public ResponseEntity<Object> deleteAirPollutionMitigation(@RequestParam Integer ecMitigationId)
			throws PariveshException {
		return ecPartBService.deleteEcAirPollutionMitigation(ecMitigationId);
	}

	@PostMapping("saveCheckListDetails")
	public ResponseEntity<Object> saveEcChecklistDetails(@RequestBody List<EcChecklistDetails> ecChecklistDetails,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveCheckDetails(ecChecklistDetails, ecPartBId);
	}

	@PostMapping("deleteCheckListDetails")
	public ResponseEntity<Object> deleteEcCheckListDetails(@RequestParam Integer checkListId) throws PariveshException {
		return ecPartBService.deleteCheckDetails(checkListId);
	}

	@PostMapping("saveEcPollutionDetails")
	public ResponseEntity<Object> saveEcPollutionDetails(@RequestBody EcPollutionDetails ecPollutionDetails,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcPollutionDetails(ecPollutionDetails, ecPartBId);
	}

	@PostMapping("deleteEcPollutionDetails")
	public ResponseEntity<Object> deleteEcPollutionDetails(@RequestParam Integer pollutionDetailsId)
			throws PariveshException {
		return ecPartBService.deleteEcPollutionDetails(pollutionDetailsId);
	}

	@PostMapping("savePollutantDetails")
	public ResponseEntity<Object> savePollutantDetails(@RequestBody List<EcPollutantDetails> ecPollutantDetails,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcPollutantDetails(ecPollutantDetails, ecPartBId);
	}

	@PostMapping("deletePollutantDetails")
	public ResponseEntity<Object> deletePollutantDetails(@RequestParam Integer ecPollutantDetailId)
			throws PariveshException {
		return ecPartBService.deleteEcPollutantDetails(ecPollutantDetailId);

	}

	@PostMapping("saveProposedProjectLandDetails")
	public ResponseEntity<Object> saveProposedProjectLandDetails(
			@RequestBody List<EcProposedProjectLandDetails> ecProposedProjectLandDetails,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcProposedProjectLandDetails(ecProposedProjectLandDetails, ecPartBId);
	}

	@PostMapping("deleteProposedProjectLandDetails")
	public ResponseEntity<Object> deleteProposedProjectLandDetails(@RequestParam Integer ecProposedProjectLandDetailId)
			throws PariveshException {
		return ecPartBService.deleteEcProposedProjectLandDetails(ecProposedProjectLandDetailId);
	}

	@PostMapping("saveEcAirportProposal")
	public ResponseEntity<Object> saveEcAirportProposal(@RequestBody EcAirportProposal ecAirportProposal,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcAirportProposalDetails(ecAirportProposal, ecPartBId);
	}

	@PostMapping("deleteEcAirportProposal")
	public ResponseEntity<Object> deleteEcAirportProposal(@RequestParam Integer airportId) throws PariveshException {
		return ecPartBService.deleteEcAirportProposalDetails(airportId);

	}

	@PostMapping("saveEcRiverValleyProject")
	public ResponseEntity<Object> saveEcRiverValleyProject(@RequestBody EcRiverValleyProject ecRiverValleyProject,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcRiverValleyProject(ecRiverValleyProject, ecPartBId);
	}

	@PostMapping("deleteEcRiverValleyProject")
	public ResponseEntity<Object> deleteEcRiverValleyProject(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteEcRiverValleyProject(id);

	}

	@PostMapping("saveEcTSDFProposals")
	public ResponseEntity<Object> saveEcTSDFProposals(@RequestBody EcTSDFProposals ecTSDFProposals,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcTSDFProposals(ecTSDFProposals, ecPartBId);
	}

	@PostMapping("deleteEcTSDFProposals")
	public ResponseEntity<Object> deleteEcTSDFProposals(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteEcTSDFProposal(id);

	}

	@PostMapping("saveEcCBWTFProposals")
	public ResponseEntity<Object> saveEcCBWTFProposals(@RequestBody EcCBWTFProposals ecCBWTFProposals,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcCBWTFProposals(ecCBWTFProposals, ecPartBId);
	}

	@PostMapping("deleteEcCBWTFProposals")
	public ResponseEntity<Object> deleteEcCBWTFProposals(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteEcCBWTFProposal(id);

	}

	@PostMapping("saveEcCETPProposals")
	public ResponseEntity<Object> saveEcCETPProposals(@RequestBody EcCETPProposals ecCETPProposals,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcCETPProposals(ecCETPProposals, ecPartBId);
	}

	@PostMapping("deleteEcCETPProposals")
	public ResponseEntity<Object> deleteEcCETPProposals(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteEcCETPProposal(id);

	}

	@PostMapping("saveEcCMSWMFProposals")
	public ResponseEntity<Object> saveEcCMSWMFProposals(@RequestBody EcCMSWMFProposals ecCMSWMFProposals,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcCMSWMFProposals(ecCMSWMFProposals, ecPartBId);
	}

	@PostMapping("deleteEcCMSWMFProposals")
	public ResponseEntity<Object> deleteEcCMSWMFProposals(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteEcCMSWMFProposal(id);

	}

	@PostMapping("saveEcCroppingPattern")
	public ResponseEntity<Object> saveEcCroppingPattern(@RequestBody List<EcCroppingPattern> ecCroppingPatterns,
			@RequestParam Integer ecRiverValleyId) throws PariveshException {
		return ecPartBService.saveEcCroppingPattern(ecCroppingPatterns, ecRiverValleyId);
	}

	@PostMapping("deleteEcCroppingPattern")
	public ResponseEntity<Object> deleteEcCroppingPattern(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteEcCroppingPattern(id);

	}

	@PostMapping("saveEcIrrigationProjectCapacityVillage")
	public ResponseEntity<Object> saveEcIrrigationProjectCapacityVillage(
			@RequestBody List<EcIrrigationProjectCapacityVillage> ecIrrigationProjectCapacityVillages,
			@RequestParam Integer ecRiverValleyId) throws PariveshException {
		return ecPartBService.saveEcIrrigationProjectCapacityVillage(ecIrrigationProjectCapacityVillages,
				ecRiverValleyId);
	}

	@PostMapping("deleteMiningMineralsMined")
	public ResponseEntity<Object> deleteMiningMineralsMined(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteMiningMineralMined(id);

	}

	@PostMapping("deleteMiningMineralsReserves")
	public ResponseEntity<Object> deleteMiningMineralsReserves(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteMiningMineralReserves(id);

	}

	@PostMapping("deleteMiningproductionDetails")
	public ResponseEntity<Object> deleteMiningproductionDetails(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteMiningProductionDetails(id);

	}

	@PostMapping("deleteMajorProjectRequirement")
	public ResponseEntity<Object> deleteMajorProjectRequirement(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteMajorProjectRequirement(id);

	}

	@PostMapping("deleteWaterRequirementBreakup")
	public ResponseEntity<Object> deleteWaterRequirementBreakup(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteWaterRequirementBreakup(id);
	}

	@PostMapping("deleteEcIrrigationProjectCapacityVillage")
	public ResponseEntity<Object> deleteEcIrrigationProjectCapacityVillage(@RequestParam Integer id)
			throws PariveshException {
		return ecPartBService.deleteEcIrrigationProjectCapacityVillage(id);

	}

	@PostMapping("saveSubmergedArea")
	public ResponseEntity<Object> saveSubmergedArea(@RequestBody List<EcSubmergedArea> ecSubmergedAreas,
			@RequestParam Integer ecRiverValleyId) throws PariveshException {
		return ecPartBService.saveEcSubmergedArea(ecSubmergedAreas, ecRiverValleyId);
	}

	@PostMapping("deleteSubmergedArea")
	public ResponseEntity<Object> deleteSubmergedArea(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteEcSubmergedArea(id);

	}

	@PostMapping("saveCurrentLandUse")
	public ResponseEntity<Object> saveCurrentLandUse(@RequestBody List<CurrentLandUse> currentLandUse,
			@RequestParam Integer ecPhysicalChangesId) throws PariveshException {
		return ecPartBService.saveCurrentLandUse(currentLandUse, ecPhysicalChangesId);
	}

	@PostMapping("deleteCurrentLandUse")
	public ResponseEntity<Object> deleteCurrentLandUse(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteCurrentLandUse(id);

	}

	@PostMapping("saveProposedLandUse")
	public ResponseEntity<Object> saveProposedLandUse(@RequestBody List<ProposedLandUse> proposedLandUse,
			@RequestParam Integer ecPhysicalChangesId) throws PariveshException {
		return ecPartBService.saveProposedLandUse(proposedLandUse, ecPhysicalChangesId);
	}

	@PostMapping("deleteProposedLandUse")
	public ResponseEntity<Object> deleteProposedLandUse(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteProposedLandUse(id);

	}

	@PostMapping("saveProposedLandUseExpansion")
	public ResponseEntity<Object> saveProposedLandUseExpansion(
			@RequestBody List<ProposedLandUseExpansion> proposedLandUseExpansion,
			@RequestParam Integer ecPhysicalChangesId) throws PariveshException {
		return ecPartBService.saveProposedLandUseExpansion(proposedLandUseExpansion, ecPhysicalChangesId);
	}

	@PostMapping("deleteProposedLandUseExpansion")
	public ResponseEntity<Object> deleteProposedLandUseExpansion(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteProposedLandUseExpansion(id);

	}

	@PostMapping("saveDemolitionDetails")
	public ResponseEntity<Object> saveDemolitionDetails(@RequestBody List<DemolitionDetails> demolitionDetails,
			@RequestParam Integer ecPhysicalChangesId) throws PariveshException {
		return ecPartBService.saveDemolitionDetails(demolitionDetails, ecPhysicalChangesId);
	}

	@PostMapping("deleteDemolitionDetails")
	public ResponseEntity<Object> deleteDemolitionDetails(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteDemolitionDetails(id);

	}

	@PostMapping("saveConstructionDetails")
	public ResponseEntity<Object> saveConstructionDetails(@RequestBody List<ConstructionDetails> constructionDetails,
			@RequestParam Integer ecPhysicalChangesId) throws PariveshException {
		return ecPartBService.saveConstructionDetails(constructionDetails, ecPhysicalChangesId);
	}

	@PostMapping("deleteConstructionDetails")
	public ResponseEntity<Object> deleteConstructionDetails(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteConstructionDetails(id);

	}

	@PostMapping("saveEcPhysicalChanges")
	public ResponseEntity<Object> saveEcPhysicalChanges(@RequestBody EcPhysicalChanges ecPhysicalChanges,
			@RequestParam Integer ecPartBId) throws PariveshException {
		return ecPartBService.saveEcPhysicalChanges(ecPhysicalChanges, ecPartBId);
	}

	@PostMapping("deleteEcPhysicalChanges")
	public ResponseEntity<Object> deleteEcPhysicalChanges(@RequestParam Integer id) throws PariveshException {
		return ecPartBService.deleteEcPhysicalChanges(id);

	}

}
