package com.backend.controller.EcFactsheet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcFactsheet.EcFactsheet;
import com.backend.model.EcFactsheet.EcFactsheetOtherDetails;
import com.backend.model.EcFactsheet.EcFactsheetProductdetails;
import com.backend.model.EcFactsheet.EcfactsheetPublichearingdetails;
import com.backend.service.EcFactsheet.EcFactsheetService;

@RestController
@RequestMapping("/ecFactsheet")
public class EcFactsheetController {

    @Autowired
    private EcFactsheetService ecFactsheetService;


    @PostMapping("/save")
    public ResponseEntity<Object> saveEcFactsheet(@RequestBody EcFactsheet ecFactsheet, @RequestParam Integer id)
            throws PariveshException {
        return ecFactsheetService.saveEcFactsheet(ecFactsheet, id);
    }


    @PostMapping("/get")
    public ResponseEntity<Object> getEcFactsheet(@RequestParam Integer id) throws PariveshException {
        return ecFactsheetService.getEcFactsheet(id);
    }

    @PostMapping("/saveOtherDetails")
    public ResponseEntity<Object> saveOtherDetails(@RequestParam Integer id,
                                                   @RequestBody EcFactsheetOtherDetails factsheetOtherdetails) throws PariveshException {
        return ecFactsheetService.saveOtherDetails(id, factsheetOtherdetails);
    }

    @PostMapping("/savePublicHearingdetails")
    public ResponseEntity<Object> saveEcfactsheetPublichearingdetails(@RequestParam Integer id,
                                                                      @RequestBody EcfactsheetPublichearingdetails factsheetPublichearingdetails)
            throws PariveshException {
        return ecFactsheetService.saveEcfactsheetPublichearingdetails(id,
                factsheetPublichearingdetails);
    }

    @PostMapping("/deletePublicHearingdetails")
    public ResponseEntity<Object> deleteEcfactsheetPublichearingdetails(@RequestParam Integer id) throws PariveshException {
        return ecFactsheetService.deleteEcfactsheetPublichearingdetails(id);
    }

//    @PostMapping("/saveProductdetails")
//    public ResponseEntity<Object> saveEcFactsheetProductDetails(@RequestParam Integer id,
//                                                                @RequestBody List<EcFactsheetProductdetails> listfactsheetProductdetails) throws PariveshException {
//        return ecFactsheetService.saveEcFactsheetProductDetails(id, listfactsheetProductdetails);
//    }

    @PostMapping("/deleteProductdetails")
    public ResponseEntity<Object> deleteEcFactsheetProductdetails(@RequestParam Integer id) throws PariveshException {
        return ecFactsheetService.deleteEcFactsheetProductdetails(id);
    }

    @PostMapping("/getAuthorityDetails")
    public ResponseEntity<Object> getAuthorityDetails(@RequestParam Integer proposalId,@RequestParam String type) throws PariveshException {
        return ecFactsheetService.getAuthorityDetails(proposalId,type);
    }
}


