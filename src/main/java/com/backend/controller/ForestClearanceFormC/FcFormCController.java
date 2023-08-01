package com.backend.controller.ForestClearanceFormC;

import com.backend.model.ForestClearanceFormC.*;
import com.backend.service.ForestClearanceFormC.FcFormCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fc/formC")
public class FcFormCController {

    @Autowired
    FcFormCService fcFormCService;

    @PostMapping("/saveFormC")
    public ResponseEntity<Object> saveFCPartBBasicDetails(@RequestBody FcFormC fcFormC, @RequestParam Integer caf_Id, @RequestParam Integer clearance_id,
                                                          HttpServletRequest request) {
        return fcFormCService.saveFCFormC(fcFormC, caf_Id, clearance_id, request);
    }

    @PostMapping("/saveProposedLand")
    public ResponseEntity<Object> saveFCFormCProposedLand(@RequestBody FcFormCProposedLand fcFormCProposedLand, @RequestParam Integer fc_form_c_Id, HttpServletRequest request) {
        return fcFormCService.saveFcFormCProposedLand(fcFormCProposedLand, fc_form_c_Id, request);
    }

    @PostMapping("/saveProposedLandN")
    public ResponseEntity<Object> saveFCFormCProposedLandN(@RequestBody FcFormCProposedLandN fcFormCProposedLandN, @RequestParam Integer fc_form_c_Id, HttpServletRequest request) {
        return fcFormCService.saveFcFormCProposedLandN(fcFormCProposedLandN, fc_form_c_Id, request);
    }

    @PostMapping("/saveOtherDetails")
    public ResponseEntity<Object> saveFCFormCOtherDetails(@RequestBody FcFormCOtherDetails fcFormCOtherDetails, @RequestParam Integer fc_form_c_Id, HttpServletRequest request) {
        return fcFormCService.saveFcFormCOtherDetails(fcFormCOtherDetails, fc_form_c_Id, request);
    }

    @PostMapping("/saveAfforestationDetails")
    public ResponseEntity<Object> saveFCFormCAfforestationDetails(@RequestBody FcFormCAfforestationDetails fcFormCAfforestationDetails, @RequestParam Integer fc_form_c_Id, HttpServletRequest request) {
        return fcFormCService.saveFcFormCAfforestationDetails(fcFormCAfforestationDetails, fc_form_c_Id, request);
    }

    @PostMapping("/saveActivityDetails")
    public ResponseEntity<Object> saveFCFormCActivityDetails(@RequestBody FcFormCActivitiesDetails fcFormCActivitiesDetails, @RequestParam Integer fc_form_c_Id, HttpServletRequest request) {
        return fcFormCService.saveFcFormCActivityDetails(fcFormCActivitiesDetails, fc_form_c_Id, request);
    }

    @PostMapping("/saveLandDetails")
    public ResponseEntity<Object> saveFCFormCLandDetails(@RequestBody FcFormCLandDetails fcFormCLandDetails, @RequestParam Integer fc_form_c_Id, HttpServletRequest request) {
        return fcFormCService.saveFcFormCLandDetails(fcFormCLandDetails, fc_form_c_Id, request);
    }


    @PostMapping("/saveLandDetailsN")
    public ResponseEntity<Object> saveFCFormCLandDetailsN(@RequestBody FcFormCLandDetailsN fcFormCLandDetails, @RequestParam Integer fc_form_c_Id, HttpServletRequest request) {
        return fcFormCService.saveFcFormCLandDetailsN(fcFormCLandDetails, fc_form_c_Id, request);
    }


    @PostMapping("/saveProposedDiversion")
    public ResponseEntity<Object> saveFCFormCProposedDiversion(@RequestBody List<FcFormCProposedDiversions> fcFormCProposedDiversions, @RequestParam Integer fc_form_c_Id) {
        return fcFormCService.saveFcFormCProposedDiversion(fcFormCProposedDiversions, fc_form_c_Id);
    }

    @PostMapping("/getProposedDiversion")
    public ResponseEntity<Object> getFCFormCProposedDiversion(@RequestParam Integer fc_form_c_Id) {
        return fcFormCService.getFCFormCPropesdDiversion(fc_form_c_Id);
    }

    @PostMapping("/deleteProposedDiversion")
    public ResponseEntity<Object> deleteFCFormCProposedDiversion(@RequestParam Integer fc_form_c_proposed_diversion_id) {
        return fcFormCService.deleteForestClearancePropesdDiversion(fc_form_c_proposed_diversion_id);
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getFCFormC(@RequestParam Integer fc_form_c_Id, @RequestParam Integer step) {
        return fcFormCService.getFcFormC(fc_form_c_Id, step);
    }


    @PostMapping("/savePriorApproval")
    public ResponseEntity<Object> savePriorApprovalDetails(@RequestParam(value = "fc_form_c_id") Integer id,
                                                           @RequestBody List<FcFormCPriorApproval> fcFormCPriorApprovals) {
        ResponseEntity<Object> status = fcFormCService.saveFcFormCApprovalDeatails(id, fcFormCPriorApprovals);
        return status;
    }

    @PostMapping("/getPriorApproval")
    public ResponseEntity<Object> getPriorApprovalDetails(@RequestParam(value = "fc_form_c_id") Integer id) {
        ResponseEntity<Object> status = fcFormCService.getFcFormCApprovalData(id);
        return status;
    }

    @PostMapping("/deletePriorApproval")
    public ResponseEntity<Object> deletePriorApprovalDetails(@RequestParam Integer id) {
        ResponseEntity<Object> status = fcFormCService.deleteFcFormCApprovalData(id);
        return status;
    }

    @PostMapping("/saveUndertaking")
    public ResponseEntity<Object> deletePriorApprovalDetails(@RequestBody FcFormCUndertaking fcFormCUndertaking, @RequestParam Integer fc_form_c_Id, @RequestParam(required = false) Boolean is_submit, HttpServletRequest request) {
        ResponseEntity<Object> status = fcFormCService.saveFcFormCUndertaking(fcFormCUndertaking, fc_form_c_Id, is_submit, request);
        return status;
    }

    @PostMapping("/saveFcFormCPatchKmls")
    public ResponseEntity<Object> saveFcFormCPtachKML(@RequestParam("fc_form_c_id") Integer id,
                                                      @RequestBody List<FcFormCPatchKmls> fcFormCPatchKmls) {
        return fcFormCService.saveFCFormCPatchKML(id, fcFormCPatchKmls);
    }

    @PostMapping("/getFcFormCPatchKmls")
    public ResponseEntity<Object> getFcFormCPtachKML(@RequestParam("fc_form_c_id") Integer id) {
        return fcFormCService.getFcFormCPatchKML(id);
    }

    @PostMapping("/deleteFcFormCPatchKmls")
    public ResponseEntity<Object> deleteFcFormCPtachKML(@RequestParam("id") Integer id) {
        return fcFormCService.deleteFcFormCPatchKML(id);
    }

    //-------------------Changes------------------

    /*
     * Add Row for Surface Sampling
     */

    @PostMapping("/saveSurfaceSampling")
    public ResponseEntity<Object> saveSurfaceSampling(@RequestBody List<FcFormCSurfaceSampling> fcFormCSurfaceSamplings, @RequestParam Integer fc_form_c_Id) {
        return fcFormCService.saveFcFormCSurfaceSampling(fcFormCSurfaceSamplings, fc_form_c_Id);
    }

    @PostMapping("/getSurfaceSampling")
    public ResponseEntity<Object> getSurfaceSampling(@RequestParam Integer fc_form_c_Id) {
        return fcFormCService.getFCFormCSurfaceSampling(fc_form_c_Id);
    }

    @PostMapping("/deleteSurfaceSampling")
    public ResponseEntity<Object> deleteSurfaceSampling(@RequestParam Integer fc_form_c_surface_sampling_id) {
        return fcFormCService.deleteFCFormCSurfaceSampling(fc_form_c_surface_sampling_id);
    }


    /*
     * Add Row for Compliance Report
     */

    @PostMapping("/saveComplianceReport")
    public ResponseEntity<Object> saveComplianceReport(@RequestBody List<FcFormCComplianceReport> fcFormCComplianceReports, @RequestParam Integer fc_form_c_Id) {
        return fcFormCService.saveFcFormCComplianceReport(fcFormCComplianceReports, fc_form_c_Id);
    }

    @PostMapping("/getComplianceReport")
    public ResponseEntity<Object> getComplianceReport(@RequestParam Integer fc_form_c_Id) {
        return fcFormCService.getFCFormCComplianceReport(fc_form_c_Id);
    }

    @PostMapping("/deleteComplianceReport")
    public ResponseEntity<Object> deleteComplianceReport(@RequestParam Integer fc_form_c_surface_sampling_id) {
        return fcFormCService.deleteFCFormCComplianceReport(fc_form_c_surface_sampling_id);
    }

    /*
     * Add Row for ExploredForestLand
     */

    @PostMapping("/saveExploredForestLand")
    public ResponseEntity<Object> saveExploredForestLand(@RequestBody List<FcFormCExploredForestLand> fcFormCExploredForestLands, @RequestParam Integer fc_form_c_Id) {
        return fcFormCService.saveFcFormCExploredForestReport(fcFormCExploredForestLands, fc_form_c_Id);
    }

    @PostMapping("/getExploredForestLand")
    public ResponseEntity<Object> getExploredForestLand(@RequestParam Integer fc_form_c_Id) {
        return fcFormCService.getFCFormCExploredForestReport(fc_form_c_Id);
    }

    @PostMapping("/deleteExploredForestLand")
    public ResponseEntity<Object> deleteExploredForestLand(@RequestParam Integer fc_form_c_surface_sampling_id) {
        return fcFormCService.deleteFCFormCExploredForestReport(fc_form_c_surface_sampling_id);
    }

    /*
     * Details of Machinery
     */

    @PostMapping("/saveDetailsOfMachinery")
    public ResponseEntity<Object> saveDetailsOfMachinery(@RequestBody List<FcFormCDetailsOfMachinery> fcFormCDetailsOfMachineries, @RequestParam Integer fc_form_c_Id) {
        return fcFormCService.saveFcFormCDetailsOfMachinery(fcFormCDetailsOfMachineries, fc_form_c_Id);
    }

    @PostMapping("/getDetailsOfMachinery")
    public ResponseEntity<Object> getDetailsOfMachinery(@RequestParam Integer fc_form_c_Id) {
        return fcFormCService.getFCFormCDetailsOfMachinery(fc_form_c_Id);
    }

    @PostMapping("/deleteDetailsOfMachinery")
    public ResponseEntity<Object> deleteDetailsOfMachinery(@RequestParam Integer fc_form_c_details_of_machinery_id) {
        return fcFormCService.deleteFCFormCDetailsOfMachinery(fc_form_c_details_of_machinery_id);
    }

}
