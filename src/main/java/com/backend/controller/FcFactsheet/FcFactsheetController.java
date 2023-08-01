package com.backend.controller.FcFactsheet;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcFactsheet.FcFactsheet;
import com.backend.service.FcFactsheet.FcFactsheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("fcFactsheet")
public class FcFactsheetController {

    @Autowired
    private FcFactsheetService factsheetService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveFactsheet(@RequestBody FcFactsheet factsheet, @RequestParam Integer clearanceId) throws PariveshException {
        return factsheetService.saveFactsheet(factsheet,clearanceId);
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getFactsheet(@RequestParam Integer applicationId)throws PariveshException{
        return factsheetService.getFactsheet(applicationId);
    }

    @PostMapping("/getDfoData")
    public ResponseEntity<Object> getDfoData(@RequestParam Integer applicationId)throws PariveshException{
        return factsheetService.getDfoData(applicationId);
    }

    }

