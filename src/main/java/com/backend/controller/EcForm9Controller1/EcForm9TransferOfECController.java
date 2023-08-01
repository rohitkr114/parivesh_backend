package com.backend.controller.EcForm9Controller1;


import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm9TransferOfEC.*;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm9Service.Ec9Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * EcForm9 : Application for Transfer of Terms of Reference
 */
@RestController
@RequestMapping("/ecForm9")
@Slf4j
public class EcForm9TransferOfECController {


    @Autowired
    private Ec9Service ec9Service;

    /**
     * Form 9: Section 1,2,3 1- Details of Project 2- Details of the Company/Organization/User Agency
     * making application 3- Details of the person making application
     *
     * @param ecForm9TransferOfEC
     * @param request
     * @return
     * @throws PariveshException
     */
    /*@PostMapping("/save")
    public ResponseEntity<Object> saveEcForm9(@RequestBody EcForm9TransferOfEC ecForm9TransferOfEC,
                                              @RequestParam(required = false) Integer caf_id,@RequestParam(required = false) Integer ec_id,
                                              HttpServletRequest request) throws PariveshException {

        return ResponseHandler.generateResponse("Save Ec Form 9", HttpStatus.OK, "",
                ec9Service.saveEcForm9TransferOfEC(ecForm9TransferOfEC, caf_id,ec_id,request));
    }*/
    @PostMapping("/save")
    public ResponseEntity<Object> saveEcForm9(@RequestBody EcForm9TransferOfEC ecForm9TransferOfEC,
                                              @RequestParam(required = false) Integer caf_id,
                                              @RequestParam(required = false) Integer activity_id,
                                              @RequestParam(required = false) Integer subActivity_id,
                                              HttpServletRequest request) throws PariveshException {

        return ResponseHandler.generateResponse("Save Ec Form 9", HttpStatus.OK, "",
                ec9Service.saveEcForm9TransferOfEC(ecForm9TransferOfEC, caf_id, request));
    }

    /**
     * Form 9: Section 4- Location of the Project or Activity
     *
     * @param ecForm9LocationOfProject
     * @param ecForm9Id
     * @param request
     * @return
     * @throws PariveshException
     */
    @PostMapping("/saveLocation")
    public ResponseEntity<Object> saveEcForm9Location(@RequestBody EcForm9LocationOfProject ecForm9LocationOfProject,
                                                      @RequestParam Integer ecForm9Id, HttpServletRequest request) throws PariveshException {

        return ResponseHandler.generateResponse("Save Ec Form 9", HttpStatus.OK, "",
                ec9Service.saveEcForm9LocationOfProject(ecForm9LocationOfProject, ecForm9Id, request));
    }


    /**
     * Form 9: Section 5,6,7- Legal Details
     *
     * @param ecForm9LegalDetails
     * @param ecForm9Id
     * @param request
     * @return
     * @throws PariveshException
     */

    @PostMapping("/saveLegalDetails")
    public ResponseEntity<Object> saveEcFormLegalDetails(@RequestBody EcForm9LegalDetails ecForm9LegalDetails,
                                                         @RequestParam Integer ecForm9Id, HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Save Ec Form 9", HttpStatus.OK, "",
                ec9Service.saveEcForm9LegalDetails(ecForm9LegalDetails, ecForm9Id, request));
    }


    /**
     * Form 9: Section 8,9,10,11,12- Proposal Details
     *
     * @param ecForm9ProposalDetails
     * @param ecForm9Id
     * @param request
     * @return
     * @throws PariveshException
     */
    @PostMapping("/saveProposalDetails")
    public ResponseEntity<Object> saveEcForm9ProposalDetails(@RequestBody EcForm9ProposalDetails ecForm9ProposalDetails,
                                                             @RequestParam Integer ecForm9Id, HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Save Ec Form 9", HttpStatus.OK, "",
                ec9Service.saveEcForm9ProposalDetails(ecForm9ProposalDetails, ecForm9Id, request));
    }
    
    @PostMapping("/deleteProductionCapacity")
    public ResponseEntity<Object> deleteProductionCapacity(@RequestParam Integer id)throws PariveshException{
    	
    	return ec9Service.deleteProductionCapacity(id);
    }

    /**
     * Form 9: Section 13,14- Undertaking
     *
     * @param
     * @param ecForm9Id
     * @param request
     * @return
     * @throws PariveshException
     */

    @PostMapping("/saveUndertaking")
    public ResponseEntity<Object> saveEcForm9UnderTaking(
            @RequestBody EcForm9Undertaking1 ecForm9Undertaking,
            @RequestParam(required = false) Integer caf_id,
            @RequestParam Integer ecForm9Id,
            @RequestParam(required = false)Boolean is_submit,
            HttpServletRequest request) throws PariveshException {

        return ResponseHandler.generateResponse("Save Ec Form 9", HttpStatus.OK, "",
                ec9Service.saveEcForm9Undertaking1(ecForm9Undertaking, ecForm9Id, caf_id,is_submit,request));

    }

    @PostMapping("/get")
    public ResponseEntity<Object> getEcForm9(@RequestParam("id") Integer id) throws PariveshException {
        return ResponseHandler.generateResponse("Get Ec Form 9", HttpStatus.OK, "",
                ec9Service.getEcForm9(id));
    }


}
