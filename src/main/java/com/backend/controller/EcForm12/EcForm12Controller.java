package com.backend.controller.EcForm12;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm12.*;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm12.EcForm12Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/ecForm12/")
public class EcForm12Controller {

    @Autowired
    private EcForm12Service ecForm12Service;

    @PostMapping("/save")
    public ResponseEntity<Object> saveEcForm12(@RequestBody EcForm12 ecForm12, @RequestParam(required = false) Integer ec_id, @RequestParam Integer caf_id,
                                               HttpServletRequest request) throws PariveshException {
        return ecForm12Service.saveEcForm12(ecForm12, ec_id, caf_id, request);
    }

//	@PostMapping("/saveEcForm12MinorActivity")
//	public ResponseEntity<Object> saveEcForm12MinorActivity(@RequestBody List<EcForm12MinorActivity> ecForm12MinorActivities, @RequestParam Integer ec_id) throws PariveshException {
//		return ecForm12Service.saveMinorActivity(ecForm12MinorActivities, ec_id);
//	}

    @PostMapping("/saveEcForm12Obtained")
    public ResponseEntity<Object> saveEcForm12Obtained(@RequestBody List<EcForm12Obtained> ecForm12Obtaineds, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm12Service.saveObtained(ecForm12Obtaineds, ec_id);
    }

    @PostMapping("/saveEcForm12TransfreeDetail")
    public ResponseEntity<Object> saveEcForm12TransfreeDetail(@RequestBody List<EcForm12TransfreeDetails> ecForm12TransfreeDetails, @RequestParam("ec_form12_id") Integer ecform12Id) throws PariveshException {
        return ecForm12Service.saveEcForm12TransfreeDetail(ecForm12TransfreeDetails, ecform12Id);
    }

    @PostMapping("/saveEcForm12DetailsOfComponents")
    public ResponseEntity<Object> saveEcForm12DetailsOfComponents(@RequestBody ECForm12DetailsOfComponents ecForm12DetailsOfComponents, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm12Service.saveEcForm12DetailsOfComponents(ecForm12DetailsOfComponents, ec_id);
    }

    @PostMapping("/saveEcForm12AddendumOfTransferor")
    public ResponseEntity<Object> saveEcForm12AddendumOfTransferor(@RequestBody ECForm12AddendumOfTransferor ecForm12AddendumOfTransferors, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm12Service.saveEcForm12AddendumOfTransferor(ecForm12AddendumOfTransferors, ec_id);
    }

    @PostMapping("/saveEcForm12AddendumOfTransferee")
    public ResponseEntity<Object> saveEcForm12AddendumOfTransferee(@RequestBody List<ECForm12AddendumOfTransferee> ecForm12AddendumOfTransferees, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm12Service.saveEcForm12AddendumOfTransferee(ecForm12AddendumOfTransferees, ec_id);
    }

    @PostMapping("/saveEcForm12ActivityStatus")
    public ResponseEntity<Object> saveEcForm12ActivityStatus(@RequestBody EcForm12ActivityStatus ecForm12ActivityStatus, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm12Service.saveActivityStatus(ecForm12ActivityStatus, ec_id);
    }

    @PostMapping("/saveEcForm12AttachedDocuments")
    public ResponseEntity<Object> saveEcForm12AttachedDocuments(@RequestBody EcForm12AttachedDocuments ecForm12AttachedDocuments, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm12Service.saveAttachedDocuments(ecForm12AttachedDocuments, ec_id);
    }

    @PostMapping("/saveEcForm12CafKML")
    public ResponseEntity<Object> saveEcForm12CafKML(@RequestBody List<EcForm12CafKML> ecForm12CafKML, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm12Service.saveEcForm12CafKML(ecForm12CafKML, ec_id);
    }

    @PostMapping("/saveEcForm12ProjectActivity")
    public ResponseEntity<Object> saveEcForm12ProjectActivity(@RequestBody EcForm12ProjectActivity ecForm12ProjectActivity, @RequestParam Integer ec_id) throws PariveshException {
        return ecForm12Service.saveProjectActivity(ecForm12ProjectActivity, ec_id);
    }

    @PostMapping("/saveEcForm12Undertaking")
    public ResponseEntity<Object> saveEcForm12Undertaking(@RequestBody EcForm12Undertaking ecForm12Undertaking, @RequestParam Integer ec_id, @RequestParam(required = false) Boolean is_submit, HttpServletRequest request) throws PariveshException {
        return ecForm12Service.saveUndertaking(ecForm12Undertaking, ec_id, is_submit, request);
    }

//	@PostMapping("/deleteEcForm12MinorActivity")
//	public ResponseEntity<Object> deleteEcForm12MinorActivity(@RequestParam Integer id) throws PariveshException {
//		return ecForm12Service.deleteMinorActivity(id);
//	}

    @PostMapping("/deleteEcForm12Obtained")
    public ResponseEntity<Object> deleteEcForm12Obtained(@RequestParam Integer id) throws PariveshException {
        return ecForm12Service.deleteObtained(id);
    }

    @PostMapping("/deleteEcForm12TransfreeDetails")
    public ResponseEntity<Object> deleteEcForm12TransfreeDetails(@RequestParam Integer id) throws PariveshException {
        return ecForm12Service.deleteEcForm12TransfreeDetails(id);
    }

    @PostMapping("/deleteEcForm12DetailsOfComponent")
    public ResponseEntity<Object> deleteEcForm12DetailsOfComponent(@RequestParam Integer id) throws PariveshException {
        return ecForm12Service.deleteEcForm12DetailsOfComponent(id);
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getEcForm12(@RequestParam("id") Integer id) throws PariveshException {
        return ResponseHandler.generateResponse("Get Ec Form 12", HttpStatus.OK, "",
                ecForm12Service.getEcForm12(id));
    }


    /*
     * EcForm12ProjectActivityDetails
     */

    @PostMapping("/save/ProjectActivityDetails")
    public ResponseEntity<Object> saveECprojectActivityDetails(@RequestParam("ec_form12_id") Integer ecform12Id, @RequestBody List<EcForm12ProjectActivityDetails> environmentClearanceProjectActivityDetails) {
        return ResponseHandler.generateResponse("Save EC Form 12 Project Activity Data", HttpStatus.OK, "", ecForm12Service.saveEcForm12ProjectActivityDetails(ecform12Id, environmentClearanceProjectActivityDetails));
    }


    @PostMapping("/list/ProjectActivityDetails")
    public ResponseEntity<Object> getECProjectActivityDetails(@RequestParam(value = "ec_form12_id") Integer id) {
        ResponseEntity<Object> status = ecForm12Service.getECProjectActivityData(id);
        return status;

    }

    @PostMapping("/delete/ProjectActivityDetails")
    public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id) {
        ResponseEntity<Object> status = ecForm12Service.deleteECProjectActivityData(id);
        return status;

    }

    @PostMapping("/delete/EcForm12CafKMLSplitted")
    public ResponseEntity<Object> deleteEcForm12CafKMLSplitted(@RequestParam(value = "id") Integer id) {
        ResponseEntity<Object> status = ecForm12Service.deleteEcForm12CafKMLSplitted(id);
        return status;

    }


}
