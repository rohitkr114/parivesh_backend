package com.backend.controller.WildLifeClearance;

import com.backend.dto.UserPrincipal;
import com.backend.model.AdditionalInformation;
import com.backend.model.WildLifeClearance.*;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.WildLifeService.WildLifeClearanceAuditService;
import com.backend.service.WildLifeService.WildLifeClearanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/wl")
public class WildLifeClearanceController {

    @Autowired
    WildLifeClearanceService wildLifeClearanceService;

    @Autowired
    WildLifeClearanceAuditService wildLifeClearanceAuditService;

    @PostMapping("/addForm")
    public ResponseEntity<Object> addWildlifeClearanceForm(@RequestParam("caf_id") Integer caf_id, @RequestBody WildLifeClearance wildLifeClearanceForm, HttpServletRequest request) {
        try {
            return ResponseHandler.generateResponse("Add WildLife Clearance Form", HttpStatus.OK, "", wildLifeClearanceService.addWildLifeClearanceForm(caf_id, wildLifeClearanceForm, request));
        } catch (Exception ex) {

            return ResponseHandler.generateResponse("Add WildLife Clearance Form", HttpStatus.EXPECTATION_FAILED, "", ex.getMessage());
        }
    }

    @PostMapping("/getForm")
    public ResponseEntity<Object> getForm(@RequestParam("wl_id") Integer wl_id) {
        try {
            return ResponseHandler.generateResponse("Get WildLife Form", HttpStatus.OK, "", wildLifeClearanceService.getWilLifeClearanceForm(wl_id));
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Get WildLife Form", HttpStatus.EXPECTATION_FAILED, "", ex.getMessage());
        }
    }

    @PostMapping("/addWLProposedLand")
    public ResponseEntity<Object> addWLProposedLand(@RequestParam("wlc_id") Integer wlc_id, @RequestBody WLProposedLand wlProposedLand) {
        return wildLifeClearanceService.addWLProposedLand(wlc_id, wlProposedLand);
    }

    @PostMapping("/addOtherDetails")
    public ResponseEntity<Object> addOtherDetails(@RequestParam("wlc_id") Integer wlc_id, @RequestBody WLOtherDetails wlOtherDetails) {
        return wildLifeClearanceService.addOthersDetail(wlc_id, wlOtherDetails);
    }

    @PostMapping("/addundertaking")
    public ResponseEntity<Object> addUndertaking(@RequestParam("wlc_id") Integer wlc_id, @RequestBody WLUndertaking wlUndertaking, @RequestParam(required = false) Boolean is_submit, HttpServletRequest request) {
        return wildLifeClearanceService.addUndertaking(wlc_id, wlUndertaking, is_submit, request);
    }

    @PostMapping("/addDivisionLandDetails")
    public ResponseEntity<Object> addDivisionLand(@RequestParam("wlc_id") Integer wlc_id, @RequestBody List<WLDivisionLandDetail> wlDivisionLandDetail) {
        return wildLifeClearanceService.addDivisionLandDetails(wlc_id, wlDivisionLandDetail);
    }

    @PostMapping("/deleteDivisionLandDetail")
    public ResponseEntity<Object> deleteDivisionLand(@RequestBody List<WLDivisionLandDetail> wlDivisionLandDetail) {
        return wildLifeClearanceService.deleteDivisionLandDetails(wlDivisionLandDetail);
    }

    @PostMapping("/addComponentWiseDetails")
    public ResponseEntity<Object> addComponentWise(@RequestParam("wlc_id") Integer wlc_id, @RequestBody List<WLComponentWiseDetails> wlComponentWiseDetails) {
        return wildLifeClearanceService.addComponentWiseDetails(wlc_id, wlComponentWiseDetails);
    }

    @PostMapping("/deleteComponentWiseDetails")
    public ResponseEntity<Object> deleteComponentWise(@RequestBody List<WLComponentWiseDetails> wlComponentWiseDetails) {
        return wildLifeClearanceService.deleteComponentWiseDetails(wlComponentWiseDetails);
    }

    @PostMapping("/addEnclosuresDetail")
    public ResponseEntity<Object> addEnclosuresDetail(@RequestParam("wl_id") Integer wl_id, @RequestBody WLEnclosures wlEnclosures) {
        return wildLifeClearanceService.addEnclosuresDetail(wl_id, wlEnclosures);
    }

    //added on 02122022
    @PostMapping("/addComponentWiseDetailsLinear")
    public ResponseEntity<Object> addLinearProjectLandDetails(@RequestParam("wlc_id") Integer wlc_id, @RequestBody List<WLLinearProjectLandDetails> wlLinearProjectLandDetails) {
        return wildLifeClearanceService.addLinearProjectLandDetails(wlc_id, wlLinearProjectLandDetails);
    }

    @PostMapping("/deleteComponentWiseLinearDetails")
    public ResponseEntity<Object> deleteLinearProjectLandDetails(@RequestBody List<WLLinearProjectLandDetails> wlLinearProjectLandDetails) {
        return wildLifeClearanceService.deleteLinearProjectLandDetails(wlLinearProjectLandDetails);
    }

    //End added on 02122022

    //Added Rishabh Sharma on 02-01-2023 for Auto fetch data for FC Granted.
    @PostMapping("/get-FC-Granted")
    public ResponseEntity<Object> getFCGranted(@RequestParam("proposalNo") String proposalNo, @RequestParam("proposalStatus") String proposalStatus,
                                               @CurrentUser UserPrincipal principal) {
        Object o = wildLifeClearanceService.getFCGranted(proposalNo, proposalStatus, principal.getId());
        return ResponseEntity.ok(o);

    }

    //Added Rishabh Sharma on 02-01-2023 for Auto fetch data for EC Granted.
    @PostMapping("/get-EC-Obtained")
    public ResponseEntity<Object> getECObtained(@RequestParam("proposalNo") String proposalNo, @CurrentUser UserPrincipal principal) {
        Object o = wildLifeClearanceService.getECObtained(proposalNo, principal.getId());
        return ResponseEntity.ok(o);

    }

    //  Added on 01-03-2022 For WL Form Part-1&2 Audit History
    @PostMapping("/addAuditForm")
    public ResponseEntity<Object> addAuditForm(@RequestParam("wl_id") Integer wl_id) {
        try {
            return ResponseHandler.generateResponse("Get WildLife Form", HttpStatus.OK, "", wildLifeClearanceAuditService.AddWilLifeClearanceAuditForm(wl_id));
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Get WildLife Form", HttpStatus.EXPECTATION_FAILED, "", ex.getMessage());
        }
    }

    // Added on 01-03-2022 For WL Form Part-1&2 Audit History
    @PostMapping("/getAuditForm")
    public ResponseEntity<Object> getAuditForm(@RequestParam("id") Integer id) {
        try {
            return ResponseHandler.generateResponse("Get WildLife Form", HttpStatus.OK, "", wildLifeClearanceAuditService.getWilLifeClearanceAuditForm(id));
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Get WildLife Form", HttpStatus.EXPECTATION_FAILED, "", ex.getMessage());
        }
    }
    
    // Added on 21-03-2022 
    @PostMapping("/RecentBuildDeployed")
    public String RecentBuildDeveloped() {        
        return "28032023V1";
    }
    
    
    // added on 27-03-2023
    
    @PostMapping("getAdditionalInformation")
    public ResponseEntity<Object> getAdditionalInformation(
            @RequestParam(name = "ref_id", required = false) String ref_id) {
        return wildLifeClearanceService.getAdditionalInformationbyWLId(ref_id);

    }
    
    @PostMapping("saveAddtionalInfomation")
    public ResponseEntity<Object> saveAddtionalInfomation(
            @RequestBody List<WLAdditionalInformation> wlAdditionalInformation) {
        return wildLifeClearanceService.saveWLAdditionalInformationArea(wlAdditionalInformation);
    }
    

    @PostMapping("deleteAdditionalInformation")
    public ResponseEntity<Object> deleteAdditionalInformation(@RequestParam Integer id) {
        return wildLifeClearanceService.deleteAdditionalInformation(id);

    }

    

}
