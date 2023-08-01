package com.backend.controller.certificate;

import com.backend.exceptions.PariveshException;
import com.backend.model.certificate.ClearanceMatrix;
import com.backend.response.ResponseHandler;
import com.backend.service.certificate.ClearanceMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clearanceMatrix")
public class ClearanceMatrixController {

    @Autowired
    private ClearanceMatrixService clearanceMatrixService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveClearanceMatrix(@RequestBody ClearanceMatrix clearanceMatrix) throws PariveshException {
        return ResponseHandler.generateResponse("Save ClearanceMatrix", HttpStatus.OK, "", clearanceMatrixService.saveClearanceMatrix(clearanceMatrix));
    }
    
    @PostMapping("/getByActivityId")
    public ResponseEntity<Object> getClearanceMatrixByActivityId(@RequestParam(required=false) Integer activityId) throws PariveshException{
    	
    	return clearanceMatrixService.getClearanceMatrixByActivityId(activityId);
    }

    @PostMapping("/delete")
    public ResponseEntity<Object> deleteClearanceMatrix(@RequestParam Integer id) throws PariveshException {
        return clearanceMatrixService.deleteClearanceMatrix(id);
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getClearanceMatrix(@RequestParam(required = false) Integer application_id, @RequestParam(required = false) String category, @RequestParam(required = false) String sub_category, @RequestParam(required = false) Integer sub_activity_id, @RequestParam(required = false) String activity_id, @RequestParam(required = false) String sector) throws PariveshException {

        return ResponseHandler.generateResponse("Get ClearanceMatrix data", HttpStatus.OK, "", clearanceMatrixService.getClearanceMatrix(application_id, category, sub_category, sub_activity_id, activity_id, sector));
    }

    @PostMapping("/getV2")
    public ResponseEntity<Object> getClearanceMatrixV2(@RequestParam String activity_id, @RequestParam String category, @RequestParam String typeProposal) throws PariveshException {

        return ResponseHandler.generateResponse("Get ClearanceMatrix data", HttpStatus.OK, "", clearanceMatrixService.getClearanceMatrixV2(activity_id, category,typeProposal));
    }

    @PostMapping("/getV3")
    public ResponseEntity<Object> getClearanceMatrixV3(@RequestParam String activity_id,@RequestParam String sub_activity_id, @RequestParam String category, @RequestParam String typeProposal) throws PariveshException {

        return ResponseHandler.generateResponse("Get ClearanceMatrix data", HttpStatus.OK, "", clearanceMatrixService.getClearanceMatrixV3(activity_id,sub_activity_id, category,typeProposal));
    }

    @PostMapping("/getWithLimit")
    public ResponseEntity<Object> getClearanceMatrixWithLimit(@RequestParam Integer page, @RequestParam Integer size, @RequestParam(required = false) Integer application_id, @RequestParam(required = false) String category, @RequestParam(required = false) String sub_category, @RequestParam(required = false) Integer sub_activity_id, @RequestParam(required = false) String activity_id, @RequestParam(required = false) String sector) throws PariveshException {

        return ResponseHandler.generateResponse("Get ClearanceMatrix data", HttpStatus.OK, "", clearanceMatrixService.getClearanceMatrixWithLimit(page, size, application_id, category, sub_category, sub_activity_id, activity_id, sector));
    }

    @PostMapping("/getCategory")
    public ResponseEntity<Object> getCategory() throws PariveshException {

        return ResponseHandler.generateResponse("Get ClearanceMatrix Category", HttpStatus.OK, "", clearanceMatrixService.getCategory());
    }

}
