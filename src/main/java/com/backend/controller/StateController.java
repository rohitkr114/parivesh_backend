package com.backend.controller;

import com.backend.dto.UserPrincipal;
import com.backend.model.State;
import com.backend.security.CurrentUser;
import com.backend.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kyc/")
public class StateController {

    @Autowired
    StateService stateService;

    @GetMapping("/getstates")
    public ResponseEntity<Object> getStates(@RequestParam(value = "active", required = false) String active) {

        ResponseEntity<Object> stateList = stateService.getAllStates(active);
        return stateList;
    }

    @GetMapping("/getstateById")
    public ResponseEntity<Object> getStateById(@RequestParam Integer Id) {
        return (stateService.getState(Id));
    }

    @PostMapping("/addStates")
    public ResponseEntity<Object> addStates(@RequestBody List<State> states) {
        return (stateService.addStates(states));
    }

    @PostMapping("/deleteStates")
    public ResponseEntity<Object> deleteStates(@RequestParam Integer stateId) {
        return stateService.deleteState(stateId);
    }

    @PostMapping("/getVillages")
    public ResponseEntity<Object> getVillages(@RequestParam(name = "count", required = true) Integer No_of_village) {
        return stateService.getVillages(No_of_village);
    }

    @PostMapping("/getVillagesBySubDistrictCode")
    public ResponseEntity<Object> getVillagesbySubDistrict(@RequestParam(name = "sub_district_code") Integer sub_district_code) {
        return stateService.getVillagesBySubDistrictCode(sub_district_code);
    }

    @PostMapping("/getVillagesBySubDistrictCodes")
    public ResponseEntity<Object> getVillagesbySubDistricts(@RequestBody List<Integer> sub_district_codes) {
        return stateService.getVillagesBySubDistrictCodes(sub_district_codes);
    }

    @PostMapping("/getVillagesByDistrictCode")
    public ResponseEntity<Object> getVillagesbyDistrict(@RequestParam(name = "district_code") Integer district_code) {
        return stateService.getVillagesByDistrictCode(district_code);
    }

    @PostMapping("/getSubDistrictsByDistrictCode")
    public ResponseEntity<Object> getSubDistrictsbyDistrict(@RequestParam(name = "district_code") Integer district_code) {
        return stateService.getSubDistrictsByDistrictCode(district_code);
    }

    @PostMapping("/getUserStateDetails")
    public ResponseEntity<Object> getUserStateDetails(@CurrentUser UserPrincipal userPrincipal) {

        return stateService.getUserStateDetails(userPrincipal.getUser().getEntityid());
    }

}