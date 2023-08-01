package com.backend.controller.ForestClearanceFormD;

import com.backend.exceptions.PariveshException;
import com.backend.model.ForestClearanceFormD.*;
import com.backend.service.ForestClearanceFormD.FcFormDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/fc/formD")
public class FcFormDController {

    @Autowired
    private FcFormDService fcFormDService;

    /*
     * Step 1
     */
    @PostMapping("/saveFormD")
    public ResponseEntity<Object> saveFCFormDBasicDetails(@RequestBody FcFormD fcFormD, @RequestParam Integer caf_Id,
                                                          @RequestParam Integer clearance_id, HttpServletRequest request) throws PariveshException {
        return fcFormDService.saveFCFormD(fcFormD, caf_Id, clearance_id, request);
    }

    /*
     * Step 2
     */
    @PostMapping("/saveProposedLand")
    public ResponseEntity<Object> saveFCFormDProposedLand(@RequestBody FcFormDProposedLand fcFormDProposedLand,
                                                          @RequestParam Integer fc_form_d_id) throws PariveshException {
        return fcFormDService.saveFcFormDProposedLand(fcFormDProposedLand, fc_form_d_id);
    }

    /*
     * Step 3
     */
    @PostMapping("/saveMiningPlan")
    public ResponseEntity<Object> saveFCFormDMiningPlan(@RequestBody FcFormDMiningPlan fcFormDMiningPlan,
                                                        @RequestParam Integer fc_form_d_id) throws PariveshException {
        return fcFormDService.saveFcFormDMiningPlan(fcFormDMiningPlan, fc_form_d_id);
    }

    /*
     * Step 5
     */
    @PostMapping("/saveUndertaking")
    public ResponseEntity<Object> saveFCFormDUndertaking(@RequestBody FcFormDUndertaking fcFormDUndertaking,
                                                         @RequestParam Integer fc_form_d_id, @RequestParam(required = false) Boolean is_submit) throws PariveshException {
        return fcFormDService.saveFcFormDUndertaking(fcFormDUndertaking, fc_form_d_id, is_submit);
    }

    /*
     * FcFormDProposedDiversion
     */
    @PostMapping("/saveProposedDiversion")
    public ResponseEntity<Object> saveFcFormDProposedDiversion(
            @RequestBody List<FcFormDProposedDiversion> fcFormDProposedDiversions,
            @RequestParam(name = "fc_d_id", required = false) Integer fc_form_d_id) {
        return fcFormDService.saveFcFormDProposedDiversion(fcFormDProposedDiversions, fc_form_d_id);
    }

    @GetMapping("/listProposedDiversion")
    public ResponseEntity<Object> getFcFormDProposedDiversion(
            @RequestParam(value = "fc_d_id", required = false) Integer fc_d_id) {
        return fcFormDService.getFcFormDProposedDiversion(fc_d_id);
    }

    @PostMapping("/deleteProposedDiversion")
    public ResponseEntity<Object> deleteFcFormDProposedDiversion(@RequestParam(value = "id") Integer id) {
        return fcFormDService.deleteFcFormDProposedDiversion(id);
    }

    @PostMapping("/intersection/deleteProposedDiversionDetails")
    public ResponseEntity<Object> deleteFCFormDProposedDiversionDetails(
            @RequestParam(value = "fcProposedDiversionsDetails_id") Integer id) {
        return fcFormDService.deleteFCFormDProposedDiversionDetails(id);
    }

    @PostMapping("/getProjectDetails")
    public ResponseEntity<Object> getFcFormD(@RequestParam("fc_form_d_id") Integer fc_form_d_id,
                                             @RequestParam(value = "step", required = false) Integer step) {
        return fcFormDService.getFcFormD(fc_form_d_id, step);
    }

    @PostMapping("/saveLegalStatus")
    public ResponseEntity<Object> saveLegalStatus(@RequestBody List<FcFormDLegalStatus> legalStatus, Integer id) {
        return fcFormDService.addFCFormDLegalStatus(legalStatus, id);
    }

    @PostMapping("/deleteLegalStatus")
    public ResponseEntity<Object> deleteLegalStatus(@RequestParam Integer id) {
        return fcFormDService.deleteFCFormDLegalStatus(id);
    }

    @PostMapping("/getLegalStatus")
    public ResponseEntity<Object> getLegalStatus(@RequestParam Integer id) {
        return fcFormDService.getFcFormDLegalStatus(id);
    }


}
