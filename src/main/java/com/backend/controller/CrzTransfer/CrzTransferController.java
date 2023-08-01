package com.backend.controller.CrzTransfer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.exceptions.PariveshException;
import com.backend.model.CrzTransfer.CrzTransferDetails;
import com.backend.model.CrzTransfer.CrzTransferUndertaking;
import com.backend.service.CrzTransfer.CrzTransferService;

@RestController
@RequestMapping("/crzTransfer")
public class CrzTransferController {
	
	@Autowired
	private CrzTransferService crzTransferService;
	
	@PostMapping("/saveTransferDetails")
	public ResponseEntity<Object> saveCrzTransferDetails(@RequestParam Integer caf_id, @RequestBody CrzTransferDetails crz,
			HttpServletRequest request) throws PariveshException {
		return crzTransferService.saveCRZTransferDetails(crz, caf_id, request);
	}
	
	@PostMapping("/saveTransferUndertaking")
	public ResponseEntity<Object> saveCrzTransferUndertaking(@RequestBody CrzTransferUndertaking crzTransferUndertaking, @RequestParam Integer id,
			HttpServletRequest request) throws PariveshException {
		return crzTransferService.saveCrzTransferUndertaking(crzTransferUndertaking, id, request);
	}

	@PostMapping("/get")
	public ResponseEntity<Object> get(@RequestParam Integer id) throws PariveshException {
		return crzTransferService.getCrzTransfer(id);
	}
}