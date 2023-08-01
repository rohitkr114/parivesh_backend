package com.backend.controller.EcForm10Controller;


import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm10NoIncreaseInPL.*;
import com.backend.model.EcForm7.EcForm7ProjectActivityDetails;
import com.backend.model.EcForm8TransferOfTOR.EcForm8AdditionalDocument;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm10Service.EcForm10Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * EcForm9 : Application for Transfer of Terms of Reference
 */
@RestController
@RequestMapping("/ecForm10")
@Slf4j
public class EcForm10NoIncreaseInPLController {

    @Autowired
    private EcForm10Service ecForm10Service;

    /**
     * Form 10: Section 1,2,3 1- Details of Project 2- Details of the Company/Organization/User Agency
     * making application 3- Details of the person making application
     *
     * @param ecForm10NoIncreaseInPL
     * @param request
     * @return
     * @throws PariveshException
     */
    @PostMapping("/save")
    public ResponseEntity<Object> saveEcForm10(@RequestBody EcForm10ProjectDetails ecForm10NoIncreaseInPL,
                                              @RequestParam(required = false) Integer caf_id,@RequestParam(required = false) Integer ec_id,
                                              HttpServletRequest request) throws PariveshException {

        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10ProjectDetails(ecForm10NoIncreaseInPL, caf_id,ec_id,request));

    }

    /**
     * Form 9: Section 4- Location of the Project or Activity
     *
     * @param ecForm10LocationOfProject
     * @param ecForm10Id
     * @param request
     * @return
     * @throws PariveshException
     */
    @PostMapping("/saveLocation")
    public ResponseEntity<Object> saveEcForm10Location(@RequestBody EcForm10LocationOfProject ecForm10LocationOfProject,
                                                      @RequestParam Integer ecForm10Id, HttpServletRequest request) throws PariveshException {

        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10LocationOfProject(ecForm10LocationOfProject,ecForm10Id, request));
    }

    /**
     * Form 10: Section 5,6,7- Legal Details
     *
     * @param
     * @param
     * @param request
     * @return
     * @throws PariveshException
     */
    @PostMapping("/saveLegalDetails")
    public ResponseEntity<Object> saveEcForm10LegalDetails(@RequestBody EcForm10LitigationAndViolationDetails ecForm10LitigationAndViolationDetails,
                                                           @RequestParam Integer ecForm10Id, HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10LitigationAndViolationDetails(ecForm10LitigationAndViolationDetails,ecForm10Id, request));
    }


    @PostMapping("/saveBasicInformation")
    public ResponseEntity<Object> saveEcForm10BasicInformation(@RequestBody EcForm10BasicInformation  ecForm10BasicInformation,
                                                         @RequestParam Integer caf_id, HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10BasicInformation(ecForm10BasicInformation,caf_id, request));
    }

    @PostMapping("/saveProductDetails")
    public ResponseEntity<Object> saveEcForm10ProductDetails(@RequestBody EcForm10ProductDetails ecForm10ProductDetails,
                                                           @RequestParam Integer ecForm10Id, HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10ProductDetails(ecForm10ProductDetails,ecForm10Id, request));
    }

    @PostMapping("/saveEmissionGeneration")
    public ResponseEntity<Object> saveEcForm10EmissionGeneration(@RequestBody EcForm10EmissionGeneration ecForm10EmissionGeneration,
                                                             @RequestParam Integer ecForm10Id, HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10EmissionGeneration(ecForm10EmissionGeneration,ecForm10Id, request));
    }

    @PostMapping("/saveHazardousWasteGeneration")
    public ResponseEntity<Object> saveEcForm10HazardousWasteGeneration(@RequestBody EcForm10HazardousWasteGeneration ecForm10HazardousWasteGeneration,
                                                                 @RequestParam Integer ecForm10Id, HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10HazardousWasteGeneration(ecForm10HazardousWasteGeneration,ecForm10Id, request));
    }

    @PostMapping("/saveAdditionDoc10")
    public ResponseEntity<Object> SaveEcForm10UploadAdditionalDoc(
            @RequestBody EcForm10AdditionalDocument ecForm10AdditionalDocument,
            @RequestParam Integer ecForm10Id,
            HttpServletRequest request) throws PariveshException {

        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10AdditionalDocument(ecForm10AdditionalDocument,ecForm10Id, request));
    }


    @PostMapping("/saveUndertaking")
    public ResponseEntity<Object> saveEcForm10UnderTaking(
            @RequestBody EcForm10Undertaking ecForm10Undertaking,
            @RequestParam(required = false) Integer caf_id,
            @RequestParam Integer ecForm10Id,
            @RequestParam(required = false) Boolean is_submit,
            HttpServletRequest request) throws PariveshException {

        return ResponseHandler.generateResponse("Save Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.saveEcForm10Undertaking(ecForm10Undertaking,ecForm10Id,caf_id,is_submit,request));
    }

    @PostMapping("/get")
    public ResponseEntity<Object> getEcForm10(@RequestParam("id") Integer id) throws PariveshException {
        return ResponseHandler.generateResponse("Get Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.getEcForm10(id));
    }
    /*
    @PostMapping("/get")
    public ResponseEntity<Object> getEcForm10(@RequestParam("id") Integer id) throws PariveshException {
        return ResponseHandler.generateResponse("Get Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.getEcForm10(id));
    }*/


    @PostMapping("/getAdditionalInformation")
    public ResponseEntity<Object> getAdditionalInformation(@RequestParam("ecForm10Id") Integer ecForm10Id) throws PariveshException {
        return ResponseHandler.generateResponse("Get Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.getEcForgetAdditionalInformation10(ecForm10Id));
    }

    @PostMapping("/deleteAdditionalInformation")
    public ResponseEntity<Object> deleteAdditionalInformation(@RequestParam("ecForm10Id") Integer ecForm10Id) throws PariveshException {
        return ResponseHandler.generateResponse("Get Ec Form 10", HttpStatus.OK, "",
                ecForm10Service.deleteAdditionalInformation(ecForm10Id));
    }

    @PostMapping("/save/ProjectActivityDetails")
    public ResponseEntity<Object> saveECprojectActivityDetails(@RequestParam("ec_form10_id") Integer ecform10Id, @RequestBody List<EcForm10ProjectActivityDetails> environmentClearanceProjectActivityDetails) {
        return ResponseHandler.generateResponse("Save EC Form 10 Project Activity Data", HttpStatus.OK, "", ecForm10Service.saveEcForm10ProjectActivityDetails(ecform10Id, environmentClearanceProjectActivityDetails));
    }


    @PostMapping("/list/ProjectActivityDetails")
    public ResponseEntity<Object> getECProjectActivityDetails(@RequestParam(value = "ec_form10_id") Integer id) {
        ResponseEntity<Object> status = ecForm10Service.getECProjectActivityData(id);
        return status;

    }

    @PostMapping("/delete/ProjectActivityDetails")
    public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id) {
        ResponseEntity<Object> status = ecForm10Service.deleteECProjectActivityData(id);
        return status;

    }

    @PostMapping("/saveConsentUnderAirDetails")
    public ResponseEntity<Object> saveConsentUnderAirDetails(@RequestBody List<EcForm10ConsentUnderAirDetails> consentUnderAirDetails,@RequestParam Integer ecForm10Id){
        return ecForm10Service.saveConsentUnderAirDetails(consentUnderAirDetails, ecForm10Id);
    }

    @PostMapping("/deleteConsentUnderAirDetails")
    public ResponseEntity<Object> deleteConsentUnderAirDetails(@RequestParam Integer id){
        return ecForm10Service.deleteConsentUnderAirDetails(id);
    }

    @PostMapping("/saveAuthorizationHazardousDetails")
    public ResponseEntity<Object> saveAuthorizationHazardousDetails(@RequestBody List<EcForm10AuthorizationHazardousDetails> hazardousDetails, @RequestParam Integer ecForm10Id){
        return ecForm10Service.saveAuthorizationHazardousDetails(hazardousDetails, ecForm10Id);
    }

    @PostMapping("/deleteAuthorizationHazardousDetails")
    public ResponseEntity<Object> deleteAuthorizationHazardousDetails(@RequestParam Integer id){
        return ecForm10Service.deleteAuthorizationHazardousDetails(id);
    }

}
