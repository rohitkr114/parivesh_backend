package com.backend.controller.FcSIR;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcSIR.FcSIRLegalStatusDetails;
import com.backend.model.FcSIR.FcSIROtherDetails;
import com.backend.model.FcSIR.FcSiteInspectionReport;
import com.backend.model.FcSIR.FcSiteVisitDetails;
import com.backend.service.FcSIR.FcSIRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fc/SIR")
public class FcSIRController {

    @Autowired
    private FcSIRService sirService;

    @PostMapping("/saveSirDetails")
    public ResponseEntity saveSIRDetails(@RequestBody FcSiteInspectionReport siteInspectionReport,@RequestParam Integer clearanceId)throws PariveshException{
        return sirService.saveSIRDetails(siteInspectionReport,clearanceId);
    }

    @PostMapping("get")
    public ResponseEntity<Object> getSirDetails(@RequestParam Integer sirId)throws PariveshException{
        return sirService.getSirDetails(sirId);
    }

    @PostMapping("saveSiteVisitDetails")
    public ResponseEntity<Object> saveSiteVisitDetails(@RequestBody List<FcSiteVisitDetails> siteVisitDetails,@RequestParam Integer sirId)throws PariveshException{
        return sirService.saveSiteVisitDetails(siteVisitDetails,sirId);
    }

    @PostMapping("deleteSiteVisitDetails")
    public ResponseEntity<Object> deleteSiteVisitDetails(@RequestParam Integer id)throws PariveshException{
        return sirService.deleteSiteVisitDetails(id);
    }

    @PostMapping("saveLegalStatus")
    public ResponseEntity<Object> saveLegalStatusDetails(@RequestBody FcSIRLegalStatusDetails legalStatusDetails,@RequestParam Integer sirId)throws PariveshException{
        return sirService.saveLegalStatusDetails(legalStatusDetails,sirId);
    }

    @PostMapping("deleteLegalStatus")
    public ResponseEntity<Object> deleteLegalStatusDetails(@RequestParam Integer id)throws PariveshException{
        return sirService.deleteLegalStatusDetails(id);
    }

    @PostMapping("saveOtherDetails")
    public ResponseEntity<Object> saveOtherDetails(@RequestBody FcSIROtherDetails otherDetails, @RequestParam Integer sirId)throws PariveshException{
        return sirService.saveOtherDetails(otherDetails,sirId);
    }

    @PostMapping("deleteOtherDetails")
    public ResponseEntity<Object> deleteOtherDetails(@RequestParam Integer id)throws PariveshException {
        return sirService.deleteOtherDetails(id);
    }
}
