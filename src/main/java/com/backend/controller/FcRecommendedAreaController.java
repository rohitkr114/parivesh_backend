package com.backend.controller;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcRecommendedArea;
import com.backend.service.FcRecommendedAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fc/recommendedArea")
public class FcRecommendedAreaController {

    @Autowired
    private FcRecommendedAreaService fcRecommendedAreaService;
    @PostMapping("/save")
    public ResponseEntity<Object> saveArea(@RequestBody FcRecommendedArea fcRecommendedArea) throws PariveshException{
        return fcRecommendedAreaService.saveArea(fcRecommendedArea);
    }
    @PostMapping("/get")
    public ResponseEntity<Object> getRecommendedArea(@RequestParam Integer fc_id) throws PariveshException{
        return fcRecommendedAreaService.getRecommendedArea(fc_id);
    }
}
