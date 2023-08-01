package com.backend.controller.ForestClearancePartB;

import com.backend.exceptions.PariveshException;
import com.backend.model.ForestClearancePartB.*;
import com.backend.service.ForestClearancePartB.ForestClearancePartBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fc/partb")
public class ForestClearance_PartBController {

    @Autowired
    private ForestClearancePartBService forestClearancePartBService;

    @PostMapping("/saveBasicDetails")
    public ResponseEntity<Object> saveFCPartBBasicDetails(
            @RequestBody ForestClearanceBBasicDetails forestClearanceBBasicDetails, HttpServletRequest request)
            throws PariveshException {
        return forestClearancePartBService.saveFCPartBBasicDetails(forestClearanceBBasicDetails, request);

    }

    @PostMapping("/getBasicDetails")
    public ResponseEntity<Object> getFCPartBBasicDetails(@RequestParam Integer fc_part_b_basic_details_id)
            throws PariveshException {
        return forestClearancePartBService.getFCPartBBasicDetails(fc_part_b_basic_details_id);
    }

    @PostMapping("/saveTreeDetails")
    public ResponseEntity<Object> saveFCPartBBasicDetails(
            @RequestBody List<ForestClearanceBTreeDetails> forestClearanceBTreeDetails,
            @RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBTreeDetails(forestClearanceBTreeDetails,
                fc_part_b_basic_details_id);
    }

    @PostMapping("/deleteTreeDetails")
    public ResponseEntity<Object> saveFCPartBBasicDetails(@RequestParam Integer FC_Tree_Details_Id)
            throws PariveshException {
        return forestClearancePartBService.deleteFCPartBTreeDetails(FC_Tree_Details_Id);
    }

    @PostMapping("/saveViolationDetails")
    public ResponseEntity<Object> saveFCPartBViolationDetails(
            @RequestBody List<ForestClearanceBViolationDetails> forestClearanceBViolationDetails,
            @RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBViolationDetails(forestClearanceBViolationDetails,
                fc_part_b_basic_details_id);
    }

    @PostMapping("/deleteViolationDetails")
    public ResponseEntity<Object> deleteFCPartBViolationDetails(@RequestParam Integer vd_id) throws PariveshException {
        return forestClearancePartBService.deleteFCPartBViolationDetails(vd_id);
    }

    @PostMapping("/saveComponentDetails")
    public ResponseEntity<Object> saveFCPartBComponentDetails(
            @RequestBody List<ForestClearanceBComponentDetails> forestClearanceBComponentDetails,
            @RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBComponentDetails(forestClearanceBComponentDetails,
                fc_part_b_basic_details_id);
    }

    @PostMapping("/deleteComponentDetails")
    public ResponseEntity<Object> deleteFCPartBComponentDetails(@RequestParam Integer cd_id) throws PariveshException {
        return forestClearancePartBService.deleteFCPartBComponentDetails(cd_id);
    }

    @PostMapping("/deleteAllComponentDetails")
    public ResponseEntity<Object> deleteFCPartBAllComponentDetails(@RequestParam Integer fc_part_b_basic_details_id)
            throws PariveshException {
        return forestClearancePartBService.deleteFCPartBAllComponentDetails(fc_part_b_basic_details_id);
    }

    @PostMapping("/saveAfforestationDetails")
    public ResponseEntity<Object> saveFCPartBAfforestationDetails(
            @RequestBody ForestClearanceBAfforestationDetails forestClearanceBAfforestationDetails,
            @RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBAfforestationDetails(forestClearanceBAfforestationDetails,
                fc_part_b_basic_details_id);
    }

    @PostMapping("/deleteAfforestationDetails")
    public ResponseEntity<Object> deleteFCPartBAfforestationDetails(@RequestParam Integer ad_id)
            throws PariveshException {
        return forestClearancePartBService.deleteFCPartBAfforestationDetails(ad_id);
    }

    @PostMapping("/savePatch")
    public ResponseEntity<Object> saveFCPartBPatch(@RequestBody List<ForestClearanceBPatches> forestClearanceBPatches,
                                                   @RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBPatch(forestClearanceBPatches, fc_part_b_basic_details_id);
    }

    @PostMapping("/deletePatch")
    public ResponseEntity<Object> deleteFCPartBPatch(@RequestParam Integer p_id) throws PariveshException {
        return forestClearancePartBService.deleteFCPartBPatch(p_id);
    }

    @PostMapping("/saveWLSpecificDetails")
    public ResponseEntity<Object> FCPartBWLSpecificDetails(
            @RequestBody ForestClearanceBWLSpecificDetails forestClearanceBWLSpecificDetails,
            @RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBWLSpecificDetails(forestClearanceBWLSpecificDetails,
                fc_part_b_basic_details_id);
    }

    @PostMapping("/saveOtherDetails")
    public ResponseEntity<Object> FCPartBOtherDetails(
            @RequestBody ForestClearanceBOtherDetails forestClearanceBOtherDetails,
            @RequestParam Integer fc_part_b_patch_id, HttpServletRequest request) throws PariveshException {
        return forestClearancePartBService.saveFCPartBOtherDetails(forestClearanceBOtherDetails, fc_part_b_patch_id,
                request);

    }

    @PostMapping("/saveDensityOfVegetation")
    public ResponseEntity<Object> saveFCPartBDensityOfVegetation(
            @RequestBody List<ForestClearanceBDensityOfVegetation> forestClearanceBDensityOfVegetations,
            @RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBDensityOfVegetation(forestClearanceBDensityOfVegetations,
                fc_part_b_basic_details_id);
    }

    @PostMapping("/deleteDensityOfVegetation")
    public ResponseEntity<Object> deleteFCPartBDensityOfVegetation(
            @RequestParam Integer fc_part_b_density_of_vegetation_id) throws PariveshException {
        return forestClearancePartBService.deleteFCPartBDensityOfVegetation(fc_part_b_density_of_vegetation_id);
    }

    @PostMapping("/savePatchWiseDetails")
    public ResponseEntity<Object> saveFCPartBPatchWiseDetails(
            @RequestBody List<ForestClearanceBPatchWiseDetails> forestClearanceBPatchWiseDetails,
            @RequestParam Integer patch_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBPatchWiseDetails(forestClearanceBPatchWiseDetails, patch_id);

    }

//    @PostMapping("/deletePatchWiseDetails")
//    public ResponseEntity<Object> deleteFCPartBPatchWiseDetails(@RequestParam Integer patch_id)
//            throws PariveshException {
//        return forestClearancePartBService.deleteFCPartBPatchWiseDetails(patch_id);
//
//    }

    @PostMapping("/deleteEnumerationDetails")
    public ResponseEntity<Object> deleteFCPartBEnumerationDetails(@RequestParam Integer enumerationDetails_id) {
        return forestClearancePartBService.deleteFCPartBEnumerationDetails(enumerationDetails_id);
    }

    @PostMapping("/deleteDistrictWise")
    public ResponseEntity<Object> deleteFCPartBDistritwise(@RequestParam Integer districtWise_id) {
        return forestClearancePartBService.deleteFCPartBDistrictwise(districtWise_id);
    }

    @PostMapping("/deleteLegalStatus")
    public ResponseEntity<Object> deleteFCPartBLegalStatus(@RequestParam Integer legalStstus_id) {
        return forestClearancePartBService.deleteFCPartBLegalStatus(legalStstus_id);
    }

//	@PostMapping("/saveEnumerationDetails")
//	public ResponseEntity<Object> saveEnumerationDetails(
//			@RequestBody List<ForestClearanceBEnumerationDetails> forestClearanceBEnumerationDetails,
//			@RequestParam Integer fc_part_b_tree_details_id) {
//		return forestClearancePartBService.saveEnumerationDetails(forestClearanceBEnumerationDetails,
//				fc_part_b_tree_details_id);
//	}

    @PostMapping("/saveCaLand")
    public ResponseEntity<Object> saveFCPartBCaLand(@RequestBody List<ForestClearanceBCaLand> forestClearanceBCaLands,
                                                    @RequestParam Integer fc_part_b_basic_details_id) throws PariveshException {
        return forestClearancePartBService.saveFCPartBCaLand(forestClearanceBCaLands, fc_part_b_basic_details_id);
    }

    @PostMapping("/deleteCaLand")
    public ResponseEntity<Object> deleteFCPartBCaLand(@RequestParam Integer ca_id) throws PariveshException {
        return forestClearancePartBService.deleteFCPartBCaLand(ca_id);
    }
}
