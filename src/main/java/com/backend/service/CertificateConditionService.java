package com.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.dto.CertificateConditionDTO;
import com.backend.dto.ComplianceConditionDto;
import com.backend.exceptions.PariveshException;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CertificateConditionService {

	@Autowired
	private ProponentApplicationRepository certificateConditionRepository;

	public ResponseEntity<Object> getConditionByApplicationId(Integer applicationId) {
		try {
			Map<String, List<CertificateConditionDTO>> certificateConditionDTO = new HashMap<String, List<CertificateConditionDTO>>();
			Map<String, List<ComplianceConditionDto>> complianceConditionDto = new HashMap<String, List<ComplianceConditionDto>>();
			List<CertificateConditionDTO> generalConditionDTO;
			List<CertificateConditionDTO> otherSpecificConditionDTO;
			List<CertificateConditionDTO> specificConditionDTO;
			List<CertificateConditionDTO> standardConditionDTO;

			List<ComplianceConditionDto> generalConditionDTO2;
			List<ComplianceConditionDto> specificConditionDTO2;
			List<ComplianceConditionDto> standardConditionDTO2;

			generalConditionDTO = certificateConditionRepository.findGeneralCondition(applicationId);
			otherSpecificConditionDTO = certificateConditionRepository.findOtherSpecificCondition(applicationId);
			specificConditionDTO = certificateConditionRepository.findSpecificCondition(applicationId);
			standardConditionDTO = certificateConditionRepository.findStandardCondition(applicationId);

			generalConditionDTO2 = certificateConditionRepository.getGeneralCondition(applicationId);
			specificConditionDTO2 = certificateConditionRepository.getSpecificCondition(applicationId);
			standardConditionDTO2 = certificateConditionRepository.getStandardCondition(applicationId);

			if (!generalConditionDTO.isEmpty()) {
				log.info("getting from old table");
				certificateConditionDTO.put("generalConditionDTO", generalConditionDTO);
				certificateConditionDTO.put("otherSpecificConditionDTO", otherSpecificConditionDTO);
				certificateConditionDTO.put("specificConditionDTO", specificConditionDTO);
				certificateConditionDTO.put("standardConditionDTO", standardConditionDTO);
				
				return ResponseHandler.generateResponse("fetched Conditions for application id - " + applicationId,
						HttpStatus.OK, null, certificateConditionDTO);
			}else {
				log.info("getting from new table");
				complianceConditionDto.put("generalConditionDTO", generalConditionDTO2);
				complianceConditionDto.put("specificConditionDTO", specificConditionDTO2);
				complianceConditionDto.put("standardConditionDTO", standardConditionDTO2);
				
				return ResponseHandler.generateResponse("fetched Conditions for application id - " + applicationId,
						HttpStatus.OK, null, complianceConditionDto);
			}


		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in finding Condition for application id- " + applicationId, e);
		}

	}
}
