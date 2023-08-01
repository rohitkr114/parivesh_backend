package com.backend.controller.ForestClearanceFormE;

import com.backend.model.ForestClearanceE.*;
import com.backend.service.ForestClearanceFormE.FcFormEService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fc/formE")
public class FcFormEController {

    @Autowired
    FcFormEService fcFormEService;

    @PostMapping("/saveFormE")
    public ResponseEntity<Object> saveFcFormE(@RequestBody FcFormE fcFormE, @RequestParam Integer caf_id,
                                              @RequestParam Integer clearance_id, HttpServletRequest request) {
        return fcFormEService.saveFCFormE(fcFormE, caf_id, clearance_id, request);
    }

    @PostMapping("/savePriorProposals")
    public ResponseEntity<Object> saveFcFormEPriorProposal(@RequestParam Integer fc_form_e_id,
                                                           @RequestBody List<FcFormEPriorProposal> fcFormEPriorProposal) {
        return fcFormEService.saveFcFormEPriorProposal(fc_form_e_id, fcFormEPriorProposal);
    }

    @PostMapping("/deletePriorProposals")
    public ResponseEntity<Object> deletePriorProposals(@RequestParam Integer id) {
        return fcFormEService.deletePriorProposals(id);
    }

//	@PostMapping("/saveApprovalDetails")
//	public ResponseEntity<Object> saveFcFormEApprovalDetails(@RequestParam Integer fc_form_e_kmls_id,
//			@RequestBody List<FcFormEApprovalDetails> fcFormEApprovalDetails) {
//		return fcFormEService.saveFcFormEApprovalDetails(fc_form_e_kmls_id, fcFormEApprovalDetails);
//	}

    @PostMapping("/deleteApprovalDetails")
    public ResponseEntity<Object> deleteApprovalDetails(@RequestParam Integer id) {
        return fcFormEService.deleteApprovalDetails(id);
    }

    @PostMapping("/saveComplianceDetails")
    public ResponseEntity<Object> saveFcFormECompliance(@RequestParam Integer fc_form_e_id,
                                                        @RequestBody List<FcFormECompliance> fcFormECompliances) {
        return fcFormEService.saveFcFormECompliance(fc_form_e_id, fcFormECompliances);
    }

    @PostMapping("/deleteComplianceDetails")
    public ResponseEntity<Object> deleteComplianceDetails(@RequestParam Integer id) {
        return fcFormEService.deleteComplianceDetails(id);
    }

    @PostMapping("/saveKmls")
    public ResponseEntity<Object> saveFcFormEKmls(@RequestParam Integer fc_form_e_id,
                                                  @RequestBody List<FcFormEKmls> fcFormEKmls) {
        return fcFormEService.saveFcFormEKmls(fc_form_e_id, fcFormEKmls);
    }

    @PostMapping("/deleteKmls")
    public ResponseEntity<Object> deleteKmls(@RequestParam Integer id) {
        return fcFormEService.deleteKmls(id);
    }

    @PostMapping("/saveProposedLand")
    public ResponseEntity<Object> saveFcFormEProposedLand(@RequestParam Integer fc_form_e_id,
                                                          @RequestBody FcFormEProposedLand fcFormEProposedLands) {
        return fcFormEService.saveFcFormEProposedLand(fcFormEProposedLands, fc_form_e_id);
    }

    @PostMapping("/saveOtherDetails")
    public ResponseEntity<Object> saveFcFormEOtherDetails(@RequestBody FcFormEOtherDetails fcFormEOtherDetails,
                                                          @RequestParam Integer fc_form_e_id) {
        return fcFormEService.saveFcFormEOtherDetails(fcFormEOtherDetails, fc_form_e_id);
    }

    @PostMapping("/savePatchDetails")
    public ResponseEntity<Object> saveFcFormEPatchDetails(@RequestBody FcFormEPatchDetails fcFormEPatchDetails,
                                                          @RequestParam Integer fc_form_e_id) {
        return fcFormEService.saveFcFormEPatchDetails(fcFormEPatchDetails, fc_form_e_id);
    }

    @PostMapping("/deletePatchDetails")
    public ResponseEntity<Object> deletePatchDetails(@RequestParam Integer id) {
        return fcFormEService.deletePatchDetails(id);
    }

    @PostMapping("/saveUndertaking")
    public ResponseEntity<Object> saveFcFormEUndertaking(@RequestBody FcFormEUndertaking fcFormEUndertaking,
                                                         @RequestParam Integer fc_form_e_id, @RequestParam(required = false) Boolean is_submit, HttpServletRequest request) {
        return fcFormEService.saveFcFormEUndertaking(fcFormEUndertaking, fc_form_e_id, is_submit, request);
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getFCFormE(@RequestParam Integer fc_form_e_id, @RequestParam Integer step) {
        return fcFormEService.getFcFormE(fc_form_e_id, step);
    }
}
