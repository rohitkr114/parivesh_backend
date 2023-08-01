package com.backend.controller.FcFormDPartIV;

import com.backend.exceptions.PariveshException;
import com.backend.model.FormDPartIV.FcFormDPartIVBasicDetails;
import com.backend.service.FcFormDPartIV.FcFormDPartIVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/fc/formd/part4")
public class FcFormDPartIVController {

    @Autowired
    private FcFormDPartIVService fcFormDPartDService;

    // Section Basic details
    // for saving the basic details
    @PostMapping("/saveBasicDetails")
    public ResponseEntity<Object> saveFCformDPartIVBasicDetails(
            @RequestBody FcFormDPartIVBasicDetails fcFormDPartIVBasicDetails, @RequestParam Integer clearenceID,
            HttpServletRequest request) throws PariveshException {
        return fcFormDPartDService.saveFCformDPartIVBasicDetails(fcFormDPartIVBasicDetails, clearenceID, request);
    }

    // Section Basic details
    // for fetching the basic details
    @PostMapping("/getBasicDetails")
    public ResponseEntity<Object> getFCformDPartIVBasicDetails(@RequestParam Integer fcFormDPartIVBasicDetailsId)
            throws PariveshException {
        return fcFormDPartDService.getFCformDPartIVBasicDetails(fcFormDPartIVBasicDetailsId);
    }

}
