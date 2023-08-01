package com.backend.controller.EcForm7;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm7.*;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm7.EcForm7Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/ecForm7/")
public class EcForm7Controller {

    @Autowired
    private EcForm7Service ecForm7Service;

    @PostMapping("/save")
    public ResponseEntity<Object> saveEcForm7(@RequestBody EcForm7 ecForm7, @RequestParam(required = false) Integer ec_id, @RequestParam Integer caf_id,
                                              HttpServletRequest request) throws PariveshException {
        return ecForm7Service.saveEcForm7(ecForm7, ec_id, caf_id, request);
    }

//	@PostMapping("/saveEcForm7MinorActivity")
//	public ResponseEntity<Object> saveEcForm7MinorActivity(@RequestBody List<EcForm7MinorActivity> ecForm7MinorActivities, @RequestParam Integer ec_id) throws PariveshException {
//		return ecForm7Service.saveMinorActivity(ecForm7MinorActivities, ec_id);
//	}

    @PostMapping("/saveEcForm7Obtained")
    public ResponseEntity<Object> saveEcForm7Obtained(@RequestBody List<EcForm7Obtained> ecForm7Obtaineds, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm7Service.saveObtained(ecForm7Obtaineds, ec_id);
    }

    @PostMapping("/saveEcForm7ActivityStatus")
    public ResponseEntity<Object> saveEcForm7ActivityStatus(@RequestBody EcForm7ActivityStatus ecForm7ActivityStatus, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm7Service.saveActivityStatus(ecForm7ActivityStatus, ec_id);
    }

    @PostMapping("/saveEcForm7AttachedDocuments")
    public ResponseEntity<Object> saveEcForm7AttachedDocuments(@RequestBody EcForm7AttachedDocuments ecForm7AttachedDocuments, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm7Service.saveAttachedDocuments(ecForm7AttachedDocuments, ec_id);
    }

    @PostMapping("/saveEcForm7CafKML")
    public ResponseEntity<Object> saveEcForm7CafKML(@RequestBody List<EcForm7CafKML> ecForm7CafKML, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm7Service.saveEcForm7CafKML(ecForm7CafKML, ec_id);
    }

    @PostMapping("/saveEcForm7ProjectActivity")
    public ResponseEntity<Object> saveEcForm7ProjectActivity(@RequestBody EcForm7ProjectActivity ecForm7ProjectActivity, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm7Service.saveProjectActivity(ecForm7ProjectActivity, ec_id);
    }

    @PostMapping("/saveEcForm7Undertaking")
    public ResponseEntity<Object> saveEcForm7Undertaking(@RequestBody EcForm7Undertaking ecForm7Undertaking, @RequestParam Integer ec_id, @RequestParam(required = false) Boolean is_submit, HttpServletRequest request) throws PariveshException {
        return ecForm7Service.saveUndertaking(ecForm7Undertaking, ec_id, is_submit, request);
    }

//	@PostMapping("/deleteEcForm7MinorActivity")
//	public ResponseEntity<Object> deleteEcForm7MinorActivity(@RequestParam Integer id) throws PariveshException {
//		return ecForm7Service.deleteMinorActivity(id);
//	}

    @PostMapping("/deleteEcForm7Obtained")
    public ResponseEntity<Object> deleteEcForm7Obtained(@RequestParam Integer id) throws PariveshException {
        return ecForm7Service.deleteObtained(id);
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getEcForm7(@RequestParam("id") Integer id) throws PariveshException {
        return ResponseHandler.generateResponse("Get Ec Form 7", HttpStatus.OK, "",
                ecForm7Service.getEcForm7(id));
    }


    /*
     * EcForm7ProjectActivityDetails
     */

    @PostMapping("/save/ProjectActivityDetails")
    public ResponseEntity<Object> saveECprojectActivityDetails(@RequestParam("ec_form7_id") Integer ecform7Id, @RequestBody List<EcForm7ProjectActivityDetails> environmentClearanceProjectActivityDetails) {
        return ResponseHandler.generateResponse("Save EC Form 7 Project Activity Data", HttpStatus.OK, "", ecForm7Service.saveEcForm7ProjectActivityDetails(ecform7Id, environmentClearanceProjectActivityDetails));
    }


    @PostMapping("/list/ProjectActivityDetails")
    public ResponseEntity<Object> getECProjectActivityDetails(@RequestParam(value = "ec_form7_id") Integer id) {
        ResponseEntity<Object> status = ecForm7Service.getECProjectActivityData(id);
        return status;

    }

    @PostMapping("/delete/ProjectActivityDetails")
    public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id) {
        ResponseEntity<Object> status = ecForm7Service.deleteECProjectActivityData(id);
        return status;

    }


}
