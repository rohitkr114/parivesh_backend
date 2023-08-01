package com.backend.controller.CrzValidityExtension;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.CrzValidityExtension.CRZValidityExtensionStepI;
import com.backend.service.CrzValidityExtension.CrzValidityExtensionService;

@RestController
@RequestMapping("/crzValidityofExtension")
public class CrzValidityExtensionController {

	@Autowired
	private CrzValidityExtensionService crzValidityExtensionService;

	@PostMapping("/save/StepI")
	public ResponseEntity<Object> saveStepI(@RequestParam Integer caf_id, @RequestBody CRZValidityExtensionStepI crz,
			HttpServletRequest request) throws PariveshException {

		return crzValidityExtensionService.saveCrzValidityExtensionI(crz, caf_id, request);
	}
}
