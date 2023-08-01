package com.backend.controller.Crz;

import com.backend.exceptions.PariveshException;
import com.backend.model.Crz.*;
import com.backend.service.Crz.CrzService;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/crz")
public class CrzController {

    @Autowired
    public CrzService crzService;

    @PostMapping("/save")
    public ResponseEntity<Object> saveCrzBasicDetails(@RequestParam Integer caf_id, @RequestBody CrzBasicDetails crz,
                                                      HttpServletRequest request) throws PariveshException {
        return crzService.saveCRZBasicDetails(crz, caf_id, request);
    }

    @PostMapping("/saveProjectDetails")
    public ResponseEntity<Object> saveProjectDetails(@RequestParam Integer crz_id,
                                                     @RequestBody CrzProjectDetails crzProjectDetails) throws PariveshException {
        return crzService.saveProjectDetails(crzProjectDetails, crz_id);
    }

    @PostMapping("/saveOtherDetails")
    public ResponseEntity<Object> saveOtherDetails(@RequestParam Integer crz_id,
                                                   @RequestBody CrzOtherDetails crzOtherDetails) throws PariveshException {
        return crzService.saveOtherDetails(crzOtherDetails, crz_id);
    }

    @PostMapping("/saveUndertaking")
    public ResponseEntity<Object> saveUndertaking(@RequestParam Integer crz_id,
                                                  @RequestBody CrzUndertaking crzUndertaking, @RequestParam(required = false) Boolean is_submit, HttpServletRequest request) throws PariveshException {
        return crzService.saveUndertaking(crzUndertaking, crz_id, is_submit, request);
    }

    @PostMapping("/getBasicDetails")
    public ResponseEntity<Object> getBasicDetails(@RequestParam Integer crz_id) throws PariveshException {
        return crzService.getBasicDetails(crz_id);
    }

    @PostMapping("/deleteProjectDetails")
    public ResponseEntity<Object> deleteProjectDetails(@RequestParam Integer id) throws PariveshException {
        return crzService.deleteProjectDetails(id);
    }

    @PostMapping("/deleteOtherDetails")
    public ResponseEntity<Object> deleteOtherDetails(@RequestParam Integer id) throws PariveshException {
        return crzService.deleteOtherDetails(id);
    }

    @PostMapping("/saveFacilityStorageGoods")
    public ResponseEntity<Object> saveFacilityStorageGoods(@RequestParam Integer id,
                                                           @RequestBody CrzFacilityStorageGoods crzFacilityStorageGoods) throws PariveshException {
        return crzService.saveFacilityStorageGoods(id, crzFacilityStorageGoods);
    }

    @PostMapping("/deleteFacilityStorageGoods")
    public ResponseEntity<Object> deleteFacilityStorageGoods(@RequestParam Integer id) throws PariveshException {
        return crzService.deleteFacilityStorageGoods(id);
    }

    @PostMapping("/deleteOtherEffluent")
    public ResponseEntity<Object> deleteOtherEffluent(@RequestParam Integer id) throws PariveshException {
        return crzService.deleteOtherEffluent(id);
    }

    /**
     * Save/Get/Delete for Add Row
     */

    @PostMapping("/saveCrzSolidWaste")
    public ResponseEntity<Object> saveCrzSolidWaste(@RequestParam Integer id, @RequestBody List<CrzSolidWaste> crzSolidwaste) {
        return crzService.saveCrzSolidWaste(crzSolidwaste, id);
    }

    @PostMapping("/deleteCrzSolidWaste")
    public ResponseEntity<Object> deleteCrzSolidWaste(@RequestParam Integer id) {
        return crzService.deleteCrzSolidWaste(id);
    }

    @PostMapping("/getCrzSolidWaste")
    public ResponseEntity<Object> getCrzSolidWaste(@RequestParam Integer id) {
        return crzService.getCrzSolidWaste(id);
    }

    @PostMapping("/saveCrzWaterRequirement")
    public ResponseEntity<Object> saveCrzWaterRequirement(@RequestParam Integer id, @RequestBody List<CrzWaterRequirement> crzWaterRequirements) {
        return crzService.saveCrzWaterRequirement(crzWaterRequirements, id);
    }

    @PostMapping("/deleteCrzWaterRequirement")
    public ResponseEntity<Object> deleteCrzWaterRequirement(@RequestParam Integer id) {
        return crzService.deleteCrzWaterRequirement(id);
    }

    @PostMapping("/getCrzWaterRequirement")
    public ResponseEntity<Object> getCrzWaterRequirement(@RequestParam Integer id) {
        return crzService.getCrzWaterRequirement(id);
    }

    @PostMapping("/saveCrzDisposal")
    public ResponseEntity<Object> saveCrzDisposal(@RequestParam Integer id, @RequestBody List<CrzDisposal> crzDisposals) {
        return crzService.saveCrzDisposal(crzDisposals, id);
    }

    @PostMapping("/deleteCrzDisposal")
    public ResponseEntity<Object> deleteCrzDisposal(@RequestParam Integer id) {
        return crzService.deleteCrzDisposal(id);
    }

    @PostMapping("/getCrzDisposal")
    public ResponseEntity<Object> getCrzDisposal(@RequestParam Integer id) {
        return crzService.getCrzDisposal(id);
    }
    
    @GetMapping("/getOfficeHierarchy")
    public Object getCrzStateOfficeHierarchy(@RequestParam Integer state_id) throws JSONException {
        return crzService.getCrzStateOfficeHierarchy(state_id);
    }
}