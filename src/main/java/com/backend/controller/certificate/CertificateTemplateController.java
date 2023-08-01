package com.backend.controller.certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.certificate.CertificateTemplate;
import com.backend.response.ResponseHandler;
import com.backend.service.certificate.CertificateTemplateService;

@RestController
@RequestMapping("/certificateTemplate")
public class CertificateTemplateController {

	@Autowired
	private CertificateTemplateService certificateTemplateService;

	@PostMapping("/save")
	public ResponseEntity<Object> saveCertificateTemplate(@RequestBody CertificateTemplate certificateTemplate)
			throws PariveshException {
		return ResponseHandler.generateResponse("Save ClearanceMatrix", HttpStatus.OK, "",
				certificateTemplateService.saveCertificateTemplate(certificateTemplate));
	}

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteCertificateTemplate(@RequestParam Integer id) throws PariveshException {
		return certificateTemplateService.deleteCertificateTemplate(id);
	}

	@PostMapping("/get")
	public ResponseEntity<Object> getCertificateTemplate(@RequestParam(required = false) String application_id,
			@RequestParam(required = false) String category, @RequestParam(required = false) String sub_category)
			throws PariveshException {

		return ResponseHandler.generateResponse("Get ClearanceMatrix data", HttpStatus.OK, "",
				certificateTemplateService.getCertificateTemplate(application_id, category, sub_category));
	}

}
