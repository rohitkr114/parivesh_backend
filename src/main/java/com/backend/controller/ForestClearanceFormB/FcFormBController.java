package com.backend.controller.ForestClearanceFormB;

import com.backend.exceptions.PariveshException;
import com.backend.model.ForestClearanceFormB.*;
import com.backend.service.ForestClearanceFormB.FcFormBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fc/formb/")
public class FcFormBController {
    @Autowired
    private FcFormBService fcFormBService;

    @PostMapping("/saveProjectDetails")
    public ResponseEntity<Object> saveFcFormBProjectDetails(@RequestParam(required = false) Integer fc_id,
                                                            @RequestParam(required = true) Integer caf_id, @RequestParam(required = true) Integer clearance_id, @RequestBody FcFormBProjectDetails fcFormBProjectDetails, HttpServletRequest request)
            throws PariveshException {
        return fcFormBService.saveFcFormBProjectdetails(fcFormBProjectDetails, fc_id, caf_id, clearance_id, request);
    }

    @PostMapping("/getProjectDetails")
    public ResponseEntity<Object> getFcFormBProjectdetails(@RequestParam("fc_form_b_id") Integer fc_form_b_id,
                                                           @RequestParam(value = "step", required = false) Integer step) {
        return fcFormBService.getFcFormBProjectDetails(fc_form_b_id, step);
    }

    @PostMapping("/getProjectDetailsByProposalNo")
    public ResponseEntity<Object> getForestClearanceByProposalNo(@RequestParam("proposal_no") String proposal_no) {
        /*--------------------------------------------------------------------------------------*/
        return fcFormBService.getFcFormBByProposalNo(proposal_no);
    }

    @PostMapping("/saveMiningProposals")
    public ResponseEntity<Object> saveFcFormBMiningProposals(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                             @RequestBody FcFormBMiningProposals fcFormBMiningProposals) {
        return fcFormBService.saveFcFormBMiningProposals(fc_form_b_id, fcFormBMiningProposals);
    }

    @PostMapping("/savePriorProposals")
    public ResponseEntity<Object> saveFcFormBPriorProposals(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                            @RequestBody List<FcFormBPriorProposals> priorProposals) {
        return fcFormBService.saveFcFormBPriorProposals(fc_form_b_id, priorProposals);
    }

    @PostMapping("/saveComplianceDetails")
    public ResponseEntity<Object> saveFcFormBComplianceDetails(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                               @RequestBody List<FcFormBComplianceDetails> complianceDetails) {
        return fcFormBService.saveFcFormBComplianceDetails(fc_form_b_id, complianceDetails);
    }

    @PostMapping("/savePaymentDetails")
    public ResponseEntity<Object> saveFcFormBPaymentDetails(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                            @RequestBody List<FcFormBPaymentDetails> paymentDetails) {
        return fcFormBService.saveFcFormBPaymentDetails(fc_form_b_id, paymentDetails);
    }

    @PostMapping("/saveProposedLand")
    public ResponseEntity<Object> saveFcFormBProposedLand(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                          @RequestBody FcFormBProposedLand proposedLand) {
        return fcFormBService.saveFcFormBProposedLand(fc_form_b_id, proposedLand);
    }

    @PostMapping("/saveApprovalDetails")
    public ResponseEntity<Object> saveFcFormBApprovalDetails(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                             @RequestBody FcFormBApprovalDetails approvalDetails) {
        return fcFormBService.saveFcFormBApprovalDetails(fc_form_b_id, approvalDetails);
    }

    @PostMapping("/savePatchAreaDetails")
    public ResponseEntity<Object> saveFcFormBPatchAreaDetails(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                              @RequestBody List<FcFormBPatchAreaDetails> patchAreaDetails) {
        return fcFormBService.saveFcFormBPatchAreaDetails(fc_form_b_id, patchAreaDetails);
    }

    @PostMapping("/saveLeaseDetails")
    public ResponseEntity<Object> saveFcFormBLeaseDetails(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                          @RequestBody List<FcFormBLeaseDetails> leaseDetails) {
        return fcFormBService.saveFcFormBLeaseDetails(fc_form_b_id, leaseDetails);
    }

    @PostMapping("/saveAfforestationDetails")
    public ResponseEntity<Object> saveFcFormBAfforestationDetails(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                                  @RequestBody FcFormBAfforestationDetails afforestationDetails) {
        return fcFormBService.saveFcFormBAfforestationDetails(fc_form_b_id, afforestationDetails);
    }

    @PostMapping("/saveOthersDetail")
    public ResponseEntity<Object> saveFcFormBOthersDetail(@RequestParam(name = "fc_form_b_id", required = false) Integer fc_form_b_id,
                                                          @RequestBody FcFormBOthersDetail othersDetail) {
        return fcFormBService.saveFcFormBOthersDetail(fc_form_b_id, othersDetail);
    }

    @PostMapping("/saveComponentDetails")
    public ResponseEntity<Object> saveFCFormBComponentDetails(
            @RequestBody List<FcFormBComponentDetails> fcFormBComponentDetails,
            @RequestParam Integer fc_form_b_id) {
        return fcFormBService.saveFcFormBComponentDetails(fc_form_b_id, fcFormBComponentDetails);
    }

    @PostMapping("/addUndertaking")
    public ResponseEntity<Object> addUndertaking(@RequestParam("fc_form_b_id") Integer fc_form_b_id, @RequestBody FcFormBUndertaking fcFormBUndertaking, @RequestParam(required = false) Boolean is_submit, HttpServletRequest request) {
        return fcFormBService.addUndertaking(fc_form_b_id, fcFormBUndertaking, is_submit, request);
    }

    @PostMapping("/saveProposedDiversion")
    public ResponseEntity<Object> saveFcFormBProposedDiversion(@RequestBody List<FcFormBProposedDiversions> fcFormBProposedDiversions, @RequestParam Integer fc_form_b_id) {
        return fcFormBService.saveFcFormBProposedDiversion(fcFormBProposedDiversions, fc_form_b_id);
    }

    @PostMapping("/getProposedDiversion")
    public ResponseEntity<Object> getFcFormBProposedDiversion(@RequestParam Integer fc_form_b_id) {
        return fcFormBService.getFcFormBPropesdDiversion(fc_form_b_id);
    }

    @PostMapping("/deleteProposedDiversion")
    public ResponseEntity<Object> deleteFcFormBProposedDiversion(@RequestParam Integer fc_form_b_proposed_diversion_id) {
        return fcFormBService.deleteFcFormBPropesdDiversion(fc_form_b_proposed_diversion_id);
    }

    @PostMapping("/deleteComplianceDetails")
    public ResponseEntity<Object> deleteFcFormBComplianceDetails(@RequestParam Integer fc_form_b_compliance_details_id) {
        return fcFormBService.deleteFcFormBComplianceDetails(fc_form_b_compliance_details_id);
    }

    @PostMapping("/deleteComponentDetails")
    public ResponseEntity<Object> deleteFcFormBComponentDetails(@RequestParam Integer fc_form_b_component_details_id) {
        return fcFormBService.deleteFcFormBComponentDetails(fc_form_b_component_details_id);
    }

    @PostMapping("/deletePaymentDetails")
    public ResponseEntity<Object> deleteFcFormBPaymentDetails(@RequestParam Integer fc_form_b_payment_details_id) {
        return fcFormBService.deleteFcFormBPaymentDetails(fc_form_b_payment_details_id);
    }

    @PostMapping("/deleteApprovalDetails")
    public ResponseEntity<Object> deleteFcFormBApprovalDetails(@RequestParam Integer fc_form_b_approval_details_id) {
        return fcFormBService.deleteFcFormBApprovalDetails(fc_form_b_approval_details_id);
    }

    @PostMapping("/deletePriorProposals")
    public ResponseEntity<Object> deleteFcFormBPriorProposals(@RequestParam Integer fc_form_b_prior_proposals_id) {
        return fcFormBService.deleteFcFormBPriorProposals(fc_form_b_prior_proposals_id);
    }

    @PostMapping("/deletePatchAreaDetails")
    public ResponseEntity<Object> deleteFcFormBPatchAreaDetails(@RequestParam Integer fc_form_b_patch_area_details_id) {
        return fcFormBService.deleteFcFormBPatchAreaDetails(fc_form_b_patch_area_details_id);
    }

    @PostMapping("/saveFcFormBPatchKmls")
    public ResponseEntity<Object> saveFcFormBPtachKML(@RequestParam("fc_form_b_id") Integer id,
                                                      @RequestBody List<FcFormBPatchKmls> fcFormBPatchKmls) {
        return fcFormBService.saveFCFormBPatchKML(id, fcFormBPatchKmls);
    }

    @PostMapping("/getFcFormBPatchKmls")
    public ResponseEntity<Object> getFcFormBPtachKML(@RequestParam("fc_form_b_id") Integer id) {
        return fcFormBService.getFcFormBPatchKML(id);
    }

    @PostMapping("/deleteFcFormBPatchKmls")
    public ResponseEntity<Object> deleteFcFormBPtachKML(@RequestParam("id") Integer id) {
        return fcFormBService.deleteFcFormBPatchKML(id);
    }


}
