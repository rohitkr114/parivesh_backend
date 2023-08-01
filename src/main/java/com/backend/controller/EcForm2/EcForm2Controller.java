package com.backend.controller.EcForm2;

import com.backend.model.EcForm2.*;
import com.backend.service.EcForm2.EcForm2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecForm2")
public class EcForm2Controller {

    @Autowired
    private EcForm2Service ecForm2Service;

    @PostMapping("/saveProjectDetails")
    public ResponseEntity<Object> saveProjectDetails(@RequestParam(required = false) Integer ecId, @RequestParam(required = false) Integer ecPartAId,
                                                     @RequestParam Integer clearanceId, @RequestParam Integer cafId, @RequestBody EcForm2ProjectDetails ecForm2ProjectDetails) {
        return ecForm2Service.saveProjectDetails(ecId, ecPartAId, clearanceId, cafId, ecForm2ProjectDetails);
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getForm(@RequestParam Integer ecForm2Id) {
        return ecForm2Service.getForm(ecForm2Id);
    }

    @PostMapping("/saveProjectActivityDetails")
    public ResponseEntity<Object> saveProjectActivityDetails(@RequestParam Integer ecForm2Id, @RequestBody List<EcForm2ProjectActivityDetails> activityDetails) {
        return ecForm2Service.saveProjectActivityDetails(ecForm2Id, activityDetails);
    }

    @PostMapping("/deleteProjectActivityDetails")
    public ResponseEntity<Object> deleteProjectActivityDetails(@RequestParam Integer id) {
        return ecForm2Service.deleteProjectActivityDetails(id);
    }

    @PostMapping("/deleteCafKml")
    public ResponseEntity<Object> deleteCafKml(@RequestParam Integer id) {
        return ecForm2Service.deleteCafKml(id);
    }


    @PostMapping("/saveProjectImplementationStatus")
    public ResponseEntity<Object> saveProjectImplementationStatus(@RequestParam Integer ecForm2Id, @RequestBody EcForm2ProjectImplementationStatus projectImplementationStatus) {
        return ecForm2Service.saveProjectImplementationStatus(ecForm2Id, projectImplementationStatus);
    }

    @PostMapping("/saveImplementationStatus")
    public ResponseEntity<Object> saveImplementationStatus(@RequestParam Integer ecForm2Id,@RequestBody List<EcForm2ImplementationStatus> ecForm2ImplementationStatus){
        return ecForm2Service.saveImplementationStatus(ecForm2Id,ecForm2ImplementationStatus);
    }

    @PostMapping("/deleteImplementationStatus")
    public ResponseEntity<Object> deleteImplementationStatus(@RequestParam Integer id) {
        return ecForm2Service.deleteImplementationStatus(id);
    }

    @PostMapping("/saveCorrigendumDescription")
    public ResponseEntity<Object> saveCorrigendumDescription(@RequestParam Integer ecForm2Id,@RequestBody List<EcForm2CorrigendumDescription> ecForm2CorrigendumDescription){
        return ecForm2Service.saveCorrigendumDescription(ecForm2Id,ecForm2CorrigendumDescription);
    }

    @PostMapping("/deleteCorrigendumDescription")
    public ResponseEntity<Object> deleteCorrigendumDescription(@RequestParam Integer id) {
        return ecForm2Service.deleteCorrigendumDescription(id);
    }

    @PostMapping("/saveEnclosureDetails")
    public ResponseEntity<Object> saveEnclosureDetails(@RequestParam Integer ecForm2Id, @RequestBody EcForm2EnclosureDetails enclosureDetails) {
        return ecForm2Service.saveEnclosureDetails(ecForm2Id, enclosureDetails);
    }

    @PostMapping("/saveUndertaking")
    public ResponseEntity<Object> saveUndertaking(@RequestParam Integer ecForm2Id, @RequestBody EcForm2Undertaking ecForm2Undertaking, @RequestParam Boolean is_submit) {
        return ecForm2Service.saveUndertaking(ecForm2Id, ecForm2Undertaking, is_submit);
    }
}
