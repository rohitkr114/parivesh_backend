package com.backend.controller;

import com.backend.model.*;
import com.backend.response.ResponseHandler;
import com.backend.service.ForestClearanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fc")
public class ForestClearanceController {

    @Autowired
    private ForestClearanceService forestClearanceService;

    @PostMapping("/addForm")
    public ResponseEntity<Object> addForestClearanceForm(@RequestParam("caf_id") Integer caf_id,
                                                         @RequestBody ForestClearance forestClearanceForm, HttpServletRequest request) {
        // return forestClearanceService.addForestClearanceForm(caf_id,
        // forestClearanceForm);
        return ResponseHandler.generateResponse("Save Forest Patch KML", HttpStatus.OK, "",
                forestClearanceService.addForestClearanceForm(caf_id, forestClearanceForm, request));
    }

    @PostMapping("/addProposedLand")
    public ResponseEntity<Object> addForestProposedLand(@RequestParam("fc_id") Integer fc_id,
                                                        @RequestBody ForestProposedLand forestProposedLand) {
        // return forestClearanceService.addForestProposedLand(fc_id,
        // forestProposedLand);
        return ResponseHandler.generateResponse("Save Forest Patch KML", HttpStatus.OK, "",
                forestClearanceService.addForestProposedLand(fc_id, forestProposedLand));
    }

    @PostMapping("/addAforestationDetail")
    public ResponseEntity<Object> addAforestationDetail(@RequestParam("fc_id") Integer fc_id,
                                                        @RequestBody FcAforestationDetails fcAforestationDetails) {
        // return forestClearanceService.addAForestationDetail(fc_id,
        // fcAforestationDetails);
        return ResponseHandler.generateResponse("Save Forest Patch KML", HttpStatus.OK, "",
                forestClearanceService.addAForestationDetail(fc_id, fcAforestationDetails));
    }

    @PostMapping("/addOthersDetail")
    public ResponseEntity<Object> addOthersDetail(@RequestParam("fc_id") Integer fc_id,
                                                  @RequestBody FcOthersDetail fcOthersDetail) {
        // return forestClearanceService.addOthersDetail(fc_id, fcOthersDetail);
        return ResponseHandler.generateResponse("Save Forest Patch KML", HttpStatus.OK, "",
                forestClearanceService.addOthersDetail(fc_id, fcOthersDetail));
    }

    @PostMapping("/addEnclosuresDetail")
    public ResponseEntity<Object> addEnclosuresDetail(@RequestParam("fc_id") Integer fc_id,
                                                      @RequestBody FcEnclosures fcEnclosures, HttpServletRequest request) {
        // return forestClearanceService.addEnclosuresDetail(fc_id, fcEnclosures);
        return ResponseHandler.generateResponse("Save Forest Patch KML", HttpStatus.OK, "",
                forestClearanceService.addEnclosuresDetail(fc_id, fcEnclosures, request));
    }

    /*
     * @PostMapping("/addAdditionalInformation") public ResponseEntity<Object>
     * addAdditionalInformation(@RequestParam("fc_id") Integer fc_id,
     *
     * @RequestBody FcAdditionalInformation fcAdditionalInformation) { // return
     * forestClearanceService.addEnclosuresDetail(fc_id, fcEnclosures); return
     * ResponseHandler.generateResponse("Save FC Additional Information ",
     * HttpStatus.OK, "", forestClearanceService.addAdditionalInformation(fc_id,
     * fcAdditionalInformation)); }
     */

    @PostMapping("/getForm")
    public ResponseEntity<Object> getForestClearanceData(@RequestParam("fc_id") Integer fc_id) {
        // return forestClearanceService.getForestClearanceForm(fc_id);
        return ResponseHandler.generateResponse("Save Forest Patch KML", HttpStatus.OK, "",
                forestClearanceService.getForestClearanceForm(fc_id));
    }

    @PostMapping("/getFormByProposalNo")
    public ResponseEntity<Object> getForestClearanceByProposalNo(@RequestParam("proposal_no") String proposal_no) {
        // return
        // forestClearanceService.getForestClearanceFormByProposalNo(proposal_no);
        return ResponseHandler.generateResponse("Save Forest Patch KML", HttpStatus.OK, "",
                forestClearanceService.getForestClearanceFormByProposalNo(proposal_no));
    }

    @PostMapping("/saveForestClearancePatchKmls")
    public ResponseEntity<Object> saveCafKML(@RequestParam("forest_clearance_id") Integer id,
                                             @RequestBody List<ForestClearancePatchKmls> forestClearancePatchKmls) {
        // return commonFormDetailService.saveCafKML(id, cafKML);
        return forestClearanceService.saveForestClearancePatchKML(id, forestClearancePatchKmls);
    }

    @PostMapping("/getForestClearancePatchKml")
    public ResponseEntity<Object> getCafKML(@RequestParam("id") Integer id) {
        return forestClearanceService.getForestClearancePatchKML(id);
        // return commonFormDetailService.getCafKML(id);
    }

    @PostMapping("/getForestClearancePatchKmlbyFCId")
    public ResponseEntity<Object> getCafKMLbyCAFID(@RequestParam("fc_id") Integer id) {
        // return commonFormDetailService.getCafKMLbyCAFId(id);
        return forestClearanceService.getForestClearancePatchKMLbyFCId(id);
    }

    @PostMapping("/getforestClearancePatchKmlDetails")
    public ResponseEntity<Object> getCafKMLPlots(@RequestParam("id") Integer id) {
        // return commonFormDetailService.getCafKMLPlot(id);
        return forestClearanceService.getForestClearancePatchKMLDetails(id);
    }

    @PostMapping("saveFcAirportProposal")
    public ResponseEntity<Object> saveEcAirportProposal(@RequestBody ForestClearanceAirportProposal fcAirportProposal,
                                                        @RequestParam(name = "fcId", required = false) Integer fcId,
                                                        @RequestParam(name = "wlcId", required = false) Integer wlcId) {
        return forestClearanceService.saveFcAirportProposalDetails(fcAirportProposal, fcId, wlcId);
    }

    @PostMapping("deleteFcAirportProposal")
    public ResponseEntity<Object> deleteEcAirportProposal(@RequestParam Integer airportId) {
        return forestClearanceService.deleteFcAirportProposalDetails(airportId);

    }

    @PostMapping("saveFcRiverValleyProject")
    public ResponseEntity<Object> saveEcRiverValleyProject(
            @RequestBody ForestClearanceRiverValleyProject fcRiverValleyProject,
            @RequestParam(name = "fcId", required = false) Integer fcId,
            @RequestParam(name = "wlcId", required = false) Integer wlcId) {
        return forestClearanceService.saveFcRiverValleyProject(fcRiverValleyProject, fcId, wlcId);
    }

    @PostMapping("deleteFcRiverValleyProject")
    public ResponseEntity<Object> deleteEcRiverValleyProject(@RequestParam Integer id) {
        return forestClearanceService.deleteFcRiverValleyProject(id);
    }

    @PostMapping("/saveFcMiningProposals")
    public ResponseEntity<Object> saveEcMiningProposals(@RequestParam(name = "fcId", required = false) Integer fcId,
                                                        @RequestParam(name = "wlcId", required = false) Integer wlcId,
                                                        @RequestBody ForestClearanceMiningProposals fcMiningProposals) {
        return forestClearanceService.saveFcMiningProposals(fcId, wlcId, fcMiningProposals);
    }

    @PostMapping("saveFcCroppingPattern")
    public ResponseEntity<Object> saveFcCroppingPattern(
            @RequestBody List<ForestClearanceCroppingPattern> fcCroppingPatterns,
            @RequestParam(name = "fcId", required = false) Integer fcId,
            @RequestParam(name = "wlcId", required = false) Integer wlcId) {
        return forestClearanceService.saveFcCroppingPattern(fcCroppingPatterns, fcId, wlcId);
    }

    @PostMapping("deleteFcCroppingPattern")
    public ResponseEntity<Object> deleteEcCroppingPattern(@RequestParam Integer id) {
        return forestClearanceService.deleteFcCroppingPattern(id);

    }

    @PostMapping("saveFcIrrigationProjectCapacityVillage")
    public ResponseEntity<Object> saveFcIrrigationProjectCapacityVillage(
            @RequestBody List<ForestClearanceIrrigationProjectCapacityVillages> fcIrrigationProjectCapacityVillages,
            @RequestParam(name = "fcId", required = false) Integer fcId,
            @RequestParam(name = "wlcId", required = false) Integer wlcId) {
        return forestClearanceService.saveFcIrrigationProjectCapacityVillage(fcIrrigationProjectCapacityVillages, fcId,
                wlcId);
    }

    @PostMapping("deleteFcIrrigationProjectCapacityVillage")
    public ResponseEntity<Object> deleteEcIrrigationProjectCapacityVillage(@RequestParam Integer id) {
        return forestClearanceService.deleteFcIrrigationProjectCapacityVillage(id);
    }

    @PostMapping("saveSubmergedArea")
    public ResponseEntity<Object> saveSubmergedArea(@RequestBody List<ForestClearanceSubmergedArea> fcSubmergedAreas,
                                                    @RequestParam(name = "fcId", required = false) Integer fcId,
                                                    @RequestParam(name = "wlcId", required = false) Integer wlcId) {
        return forestClearanceService.saveFcSubmergedArea(fcSubmergedAreas, fcId, wlcId);
    }

    @PostMapping("deleteSubmergedArea")
    public ResponseEntity<Object> deleteSubmergedArea(@RequestParam Integer id) {
        return forestClearanceService.deleteFcSubmergedArea(id);

    }

    /*
     * @PostMapping("saveAddtionalInfomation") public ResponseEntity<Object>
     * saveAddtionalInfomation(@RequestBody List<AdditionalInformation>
     * additionalInformation,
     *
     * @RequestParam(name = "fcId", required = false) Integer fcId,
     *
     * @RequestParam(name = "wlcId", required = false) Integer wlcId) { return
     * forestClearanceService.saveAdditionalInformationArea(additionalInformation,
     * fcId, wlcId); }
     *
     * @PostMapping("deleteAdditionalInformation") public ResponseEntity<Object>
     * deleteAdditionalInformation(@RequestParam Integer id) { return
     * forestClearanceService.deleteAdditionalInformation(id);
     *
     * }
     *
     * @PostMapping("getAdditionalInformation") public ResponseEntity<Object>
     * getAdditionalInformation(@RequestParam(name = "fcId", required = false)
     * Integer fcId,
     *
     * @RequestParam(name = "wlcId", required = false) Integer
     * wlcId,@RequestParam(name="ref_id",required = false)String ref_id) { return
     * forestClearanceService.getAdditionalInformationbyFCId(fcId,wlcId,ref_id);
     *
     * }
     */

    @PostMapping("saveAddtionalInfomation")
    public ResponseEntity<Object> saveAddtionalInfomation(
            @RequestBody List<AdditionalInformation> additionalInformation) {
        return forestClearanceService.saveAdditionalInformationArea(additionalInformation);
    }

    @PostMapping("deleteAdditionalInformation")
    public ResponseEntity<Object> deleteAdditionalInformation(@RequestParam Integer id) {
        return forestClearanceService.deleteAdditionalInformation(id);

    }

    @PostMapping("getAdditionalInformation")
    public ResponseEntity<Object> getAdditionalInformation(
            @RequestParam(name = "ref_id", required = false) String ref_id, @RequestParam(name = "is_special_document",
            required = true) boolean isSpecialDocument) {
        return forestClearanceService.getAdditionalInformationbyFCId(ref_id,isSpecialDocument);

    }

    @PostMapping("getAdditionalInformationv2")
    public ResponseEntity<Object> getAdditionalInformationV2(
            @RequestParam(name = "ref_id", required = false) String ref_id, @RequestParam(name = "is_special_document",
            required = true) boolean isSpecialDocument) {
        return forestClearanceService.getAdditionalInformationbyFCIdv2(ref_id,isSpecialDocument);

    }

    @PostMapping("/addundertaking")
    public ResponseEntity<Object> addUndertaking(@RequestParam("fc_id") Integer fc_id,
                                                 @RequestBody FCUndertaking fcUndertaking, @RequestParam(required = false) Boolean is_submit, HttpServletRequest request) {
        return forestClearanceService.addUndertaking(fc_id, fcUndertaking, is_submit, request);
    }

    @PostMapping("/updateDivisionPatchDetails")
    public ResponseEntity<Object> updateDivisionPatchDetails(@RequestParam("id") Integer id,
                                                             @RequestBody List<ForestClearanceDivisionPatchDetails> forestClearanceDivisionPatchDetails) {
        return forestClearanceService.updateDivisionPatchDetails(id, forestClearanceDivisionPatchDetails);
    }

    @PostMapping("deleteLegalStatus")
    public ResponseEntity<Object> deleteLegalStatus(@RequestParam("fc_legal_status_id") Integer id) {
        return forestClearanceService.deleteLegalStatus(id);

    }

    @PostMapping("/saveMiningMineralOilProposal")
    public ResponseEntity<Object> saveMiningMineralOilProposal(@RequestParam(name = "fcId") Integer fcId,
                                                               @RequestBody MiningMineralOilProposal miningMineralOilProposal) {
        return forestClearanceService.saveMiningMineralOilProposal(fcId, miningMineralOilProposal);
    }

    @PostMapping("deleteMiningMineralOilEstimatedReserve")
    public ResponseEntity<Object> deleteMiningMineralOilEstimatedReserve(@RequestParam("id") Integer id) {
        return forestClearanceService.deleteMiningMineralOilEstimatedReserve(id);

    }

    @PostMapping("deleteMiningMineralOilExtracted")
    public ResponseEntity<Object> deleteMiningMineralOilExtracted(@RequestParam("id") Integer id) {
        return forestClearanceService.deleteMiningMineralOilExtracted(id);

    }

    @PostMapping("deleteMiningMineralOilProductionDetail")
    public ResponseEntity<Object> deleteMiningMineralOilProductionDetail(@RequestParam("id") Integer id) {
        return forestClearanceService.deleteMiningMineralOilProductionDetail(id);

    }

    @PostMapping("deleteMiningMineralOilReserve")
    public ResponseEntity<Object> deleteMiningMineralOilReserve(@RequestParam("id") Integer id) {
        return forestClearanceService.deleteMiningMineralOilReserve(id);

    }

    @PostMapping("getMiningMineralOilProposal")
    public ResponseEntity<Object> getMiningMineralOilProposal(@RequestParam("fcId") Integer id) {
        return forestClearanceService.getMiningMineralOilProposal(id);

    }
}
