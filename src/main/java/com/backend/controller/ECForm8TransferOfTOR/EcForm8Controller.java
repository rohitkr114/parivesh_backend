package com.backend.controller.ECForm8TransferOfTOR;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm8TransferOfTOR.ECForm8TransferCOP;
import com.backend.model.EcForm8TransferOfTOR.EcForm8AdditionalDocument;
import com.backend.model.EcForm8TransferOfTOR.EcForm8DetailOfTOR;
import com.backend.model.EcForm8TransferOfTOR.EcForm8DocumentAttached;
import com.backend.model.EcForm8TransferOfTOR.EcForm8LocationOfProject;
import com.backend.model.EcForm8TransferOfTOR.EcForm8ProjectActivityDetails;
import com.backend.model.EcForm8TransferOfTOR.EcForm8TransferOfTOR;
import com.backend.model.EcForm8TransferOfTOR.EcForm8Undertaking;
import com.backend.response.ResponseHandler;
import com.backend.service.EcForm8.EcForm8Service;

/**
 * EcForm8 : Application for Transfer of Terms of Reference
 */
@RestController
@RequestMapping("/ecForm8")
public class EcForm8Controller {

	@Autowired
	private EcForm8Service ecForm8Service;

	/**
	 * Form 8: Section 1,2,3 1- Details of Project 2- Details of the
	 * Company/Organization/User Agency making application 3- Details of the person
	 * making application
	 *
	 * @param ecForm8TransferOfTOR
	 * @param request
	 * @return
	 * @throws PariveshException
	 */
	@PostMapping("/save")
	public ResponseEntity<Object> saveEcForm8(@RequestBody EcForm8TransferOfTOR ecForm8TransferOfTOR,
			@RequestParam(required = false) Integer caf_id, @RequestParam(required = false) Integer ec_id,
			HttpServletRequest request) throws PariveshException {

		return ResponseHandler.generateResponse("Save Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.saveEcForm8TransferOfTOR(ecForm8TransferOfTOR, caf_id, ec_id, request));
	}

	/**
	 * Form 8: Section 4- Location of the Project or Activity
	 *
	 * @param ecForm8LocationOfProject
	 * @param ecForm8Id
	 * @param request
	 * @return
	 * @throws PariveshException
	 */
	@PostMapping("/saveLocation")
	public ResponseEntity<Object> saveEcForm8Location(@RequestBody EcForm8LocationOfProject ecForm8LocationOfProject,
			@RequestParam Integer ecForm8Id, HttpServletRequest request) throws PariveshException {

		return ResponseHandler.generateResponse("Save Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.saveEcForm8LocationOfProject(ecForm8LocationOfProject, ecForm8Id, request));
	}

	/**
	 * Form 8: Section 5 - Category of the Project/Activity 5.1.3 is multiple save.
	 *
	 * @param ecForm8TransferCOP
	 * @param ecForm8Id
	 * @param request
	 * @return
	 * @throws PariveshException
	 */
	@PostMapping("/saveECForm8TransferCOP")
	public ResponseEntity<Object> saveECForm8TransferCOP(@RequestBody ECForm8TransferCOP ecForm8TransferCOP,
			@RequestParam Integer ecForm8Id, HttpServletRequest request) throws PariveshException {

		return ResponseHandler.generateResponse("Save Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.saveECForm8TransferCOP(ecForm8TransferCOP, ecForm8Id, request));
	}

	/**
	 * Form 8: section 6 - Details of Terms of Reference 6.4 is Add row function
	 * (work on it)
	 *
	 * @param ecForm8DetailOfTOR
	 * @param ecForm8Id
	 * @param request
	 * @return
	 * @throws PariveshException
	 */
	@PostMapping("/saveEcForm8DetailOfTOR")
	public ResponseEntity<Object> saveEcForm8DetailOfTOR(@RequestBody EcForm8DetailOfTOR ecForm8DetailOfTOR,
			@RequestParam Integer ecForm8Id, HttpServletRequest request) throws PariveshException {

		return ResponseHandler.generateResponse("Save Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.saveEcForm8DetailOfTOR(ecForm8DetailOfTOR, ecForm8Id, request));
	}

	/**
	 * Form 8: section 7 - Documents to be attached
	 *
	 * @param ecForm8DocumentAttached
	 * @param ecForm8Id
	 * @return
	 * @throws PariveshException
	 */
	@PostMapping("/saveEcForm8Document")
	public ResponseEntity<Object> saveEcForm8UploadDoc(@RequestBody EcForm8DocumentAttached ecForm8DocumentAttached,
			@RequestParam Integer ecForm8Id, HttpServletRequest request) throws PariveshException {

		return ResponseHandler.generateResponse("Save Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.saveEcForm8DocumentAttached(ecForm8DocumentAttached, ecForm8Id, request));
	}

	/**
	 * Add rows -- doc under sec 7. Need to check this one. for additional document
	 *
	 * @param ecForm8AdditionalDocument
	 * @param ecForm8Id
	 * @return
	 * @throws PariveshException
	 */
	@PostMapping("/saveAdditionDoc")
	public ResponseEntity<Object> SaveEcForm8UploadAdditionalDoc(
			@RequestBody EcForm8AdditionalDocument ecForm8AdditionalDocument, @RequestParam Integer ecForm8Id,
			HttpServletRequest request) throws PariveshException {

		return ResponseHandler.generateResponse("Save Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.saveEcForm8AdditionalDocument(ecForm8AdditionalDocument, ecForm8Id, request));
	}

	/**
	 * Form 8: Section 8 - Undertaking
	 *
	 * @param ecForm8Undertaking
	 * @param ecForm8Id
	 * @param request
	 * @return
	 * @throws PariveshException
	 */
	@PostMapping("/saveUndertaking")
	public ResponseEntity<Object> saveEcForm8UnderTaking(@RequestBody EcForm8Undertaking ecForm8Undertaking,
			@RequestParam(required = false) Integer caf_id, @RequestParam Integer ecForm8Id,
			@RequestParam(required = false) Boolean is_submit, HttpServletRequest request) throws PariveshException {

		return ResponseHandler.generateResponse("Save Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.saveEcForm8Undertaking(ecForm8Undertaking, ecForm8Id, caf_id, is_submit, request));

	}

	@PostMapping("/getDetailOfTorByMoefcc")
	public ResponseEntity<Object> getDetailsOfTorByMofeOrSeiaa(@RequestParam Integer moefNo,
			@RequestParam String seiaaNo) throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.getTorDetailByMofeCC(moefNo));
	}

	@PostMapping("/get")
	public ResponseEntity<Object> getEcForm8(@RequestParam("id") Integer id) throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Form 8", HttpStatus.OK, "", ecForm8Service.getEcForm8(id));
	}

	@PostMapping("/getAdditionalInformation")
	public ResponseEntity<Object> getAdditionalInformation(@RequestParam("ecForm8Id") Integer ecForm8Id)
			throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.getEcForgetAdditionalInformationm8(ecForm8Id));
	}

	@PostMapping("/deleteAdditionalInformation")
	public ResponseEntity<Object> deleteAdditionalInformation(@RequestParam("ecForm8Id") Integer ecForm8Id)
			throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.deleteAdditionalInformation(ecForm8Id));
	}

	@PostMapping("/getOrganizationDetails")
	public ResponseEntity<Object> getOrganizationDetails(@RequestParam("name") String name) throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.getOrganizationDetails(name));
	}

	@PostMapping("/getOrganizationDetailsByName")
	public ResponseEntity<Object> getOrganizationDetailsByName(@RequestParam("name") String name)
			throws PariveshException {
		return ResponseHandler.generateResponse("Get Ec Form 8", HttpStatus.OK, "",
				ecForm8Service.getOrganizationDetailsByName(name));
	}

	/*
	 * EcForm8ProjectActivityDetails
	 */

	@PostMapping("/save/ProjectActivityDetails")
	public ResponseEntity<Object> saveECprojectActivityDetails(@RequestParam("ec_form8_id") Integer ecform8Id,
			@RequestBody List<EcForm8ProjectActivityDetails> environmentClearanceProjectActivityDetails) {
		return ResponseHandler.generateResponse("Save EC Form 7 Project Activity Data", HttpStatus.OK, "",
				ecForm8Service.saveEcForm8ProjectActivityDetails(ecform8Id,
						environmentClearanceProjectActivityDetails));
	}

	@PostMapping("/list/ProjectActivityDetails")
	public ResponseEntity<Object> getECProjectActivityDetails(@RequestParam(value = "ec_form8_id") Integer id) {
		ResponseEntity<Object> status = ecForm8Service.getECProjectActivityData(id);
		return status;

	}

	@PostMapping("/delete/ProjectActivityDetails")
	public ResponseEntity<Object> deleteECProjectActivityDetails(@RequestParam(value = "id") Integer id) {
		ResponseEntity<Object> status = ecForm8Service.deleteECProjectActivityData(id);
		return status;

	}

}
