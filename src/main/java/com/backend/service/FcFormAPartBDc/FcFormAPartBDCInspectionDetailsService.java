package com.backend.service.FcFormAPartBDc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.FcFormAPartBDc.FcFormAPartBDC;
import com.backend.model.FcFormAPartBDc.FcFormAPartBDCInspectionDetails;
import com.backend.repository.postgres.FcFormAPartBDc.FcFormAPartBDCInspectionDetailsRepository;
import com.backend.repository.postgres.FcFormAPartBDc.FcFormAPartBDCRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FcFormAPartBDCInspectionDetailsService {

	@Autowired
	private FcFormAPartBDCInspectionDetailsRepository inspectionDetailsRepository;

	@Autowired
	private FcFormAPartBDCRepository fcFormAPartBDCRepository;

	public FcFormAPartBDCInspectionDetails savefcFormAPartBDC(
			FcFormAPartBDCInspectionDetails fcFormAPartBDCInspectionDetails, Integer fc_formA_DC_id) {
		try {

			FcFormAPartBDC fcFormAPartBDC = fcFormAPartBDCRepository.findById(fc_formA_DC_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form A Part B DC not found with ID"));

			fcFormAPartBDCInspectionDetails.setFcFormAPartBDC(fcFormAPartBDC);

			return inspectionDetailsRepository.save(fcFormAPartBDCInspectionDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Fc FormA PartB DC Inspection Details ", e);
		}
	}

	public FcFormAPartBDCInspectionDetails getfcFormAPartBDC(Integer id) {

		try {
			FcFormAPartBDCInspectionDetails fcFormAPartBDCInspectionDetails = inspectionDetailsRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException(
							"Fc FormA PartB DC Inspection Details not found for id" + id));

			return fcFormAPartBDCInspectionDetails;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting Fc FormA PartB DC Inspection Details -" + id, e);
		}
	}

	public String deletefcFormAPartBDC(Integer id) {
		try {
			Integer upadate = inspectionDetailsRepository.updatefcFormAPartBDC(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return "Deleted";
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting Fc FormA PartB DC Inspection Details -" + id, e);
		}
	}

}
