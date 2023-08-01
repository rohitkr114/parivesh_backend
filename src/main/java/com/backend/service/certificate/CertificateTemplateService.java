package com.backend.service.certificate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.model.certificate.CertificateTemplate;
import com.backend.repository.postgres.certificate.CertificateTemplateRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CertificateTemplateService {

	@Autowired
	private CertificateTemplateRepository certificateTemplateRepository;

	public CertificateTemplate saveCertificateTemplate(CertificateTemplate certificateTemplate) {

		return certificateTemplateRepository.save(certificateTemplate);

	}

	public ResponseEntity<Object> deleteCertificateTemplate(Integer id) {
		try {

			Integer upadate = certificateTemplateRepository.deleteCertificateTemplate(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting Certificate Template id-" + id, e);
		}
		return ResponseHandler.generateResponse("delete Certificate Template data", HttpStatus.OK, null,
				"Record deleted Successfully");
	}

	public List<CertificateTemplate> getCertificateTemplate(String application_id, String category,
			String sub_category) {

		List<CertificateTemplate> list = new ArrayList<CertificateTemplate>();

		try {

			list = certificateTemplateRepository.getCertificateTemplate(application_id, category, sub_category);

			return list;

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting Certificate Template " + e);
		}

	}

}
