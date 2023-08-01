package com.backend.controller.EcForm13;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm13.*;
import com.backend.service.EcForm13.EcForm13Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ecForm13")
public class EcForm13Controller {

    @Autowired
    private EcForm13Service ecForm13Service;

    @PostMapping("/saveProjectActivityDetails")
    public ResponseEntity<Object> saveProjectActivityDetails(@RequestParam Integer ecForm13Id, @RequestBody List<EcForm13ProjectActivityDetails> activityDetails) throws PariveshException {
        return ecForm13Service.saveProjectActivityDetails(ecForm13Id,activityDetails);
    }

    @PostMapping("/saveSpecificDetails")
    public ResponseEntity<Object> saveSpecificDetails(@RequestParam Integer ecForm13Id, @RequestBody EcForm13ProjectSpecificDetails specificDetails)throws PariveshException{
        return ecForm13Service.saveSpecificDetails(ecForm13Id,specificDetails);
    }

    @PostMapping("/saveConsultantDetails")
    public ResponseEntity<Object> saveConsultantDetails(@RequestParam Integer ecForm13Id,@RequestBody EcForm13ConsultantDetails consultantDetails)throws PariveshException{
        return ecForm13Service.saveConsultantDetails(ecForm13Id,consultantDetails);
    }

    @PostMapping("/saveEnclosureDetails")
    public ResponseEntity<Object> saveEnclosureDetails(@RequestParam Integer ecForm13Id,@RequestBody EcForm13EnclosureDetails enclosureDetails)throws PariveshException{
        return ecForm13Service.saveEnclosureDetails(ecForm13Id,enclosureDetails);
    }

    @PostMapping("/saveUndertaking")
    public ResponseEntity<Object> saveUndertaking(@RequestParam Integer ecForm13Id,@RequestBody EcForm13Undertaking undertaking, @RequestParam Boolean isSubmit)throws PariveshException{
        return ecForm13Service.saveUndertaking(ecForm13Id,undertaking,isSubmit);
    }
}
