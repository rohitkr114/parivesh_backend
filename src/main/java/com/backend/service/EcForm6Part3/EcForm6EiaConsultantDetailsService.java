package com.backend.service.EcForm6Part3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.ProponentApplications;
import com.backend.model.EcForm6Part2.EcForm6Productdetails;
import com.backend.model.EcForm6Part3.EcForm6EiaConsultantDetails;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.EcForm6Part1.EcForm6BasicdetailsEarlierEcRepository;
import com.backend.repository.postgres.EcForm6Part3.EcForm6EiaConsultantDetailsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcForm6EiaConsultantDetailsService {
	
	@Autowired
	EcForm6BasicdetailsEarlierEcRepository ecForm6BasicdetailsEarlierEcRepository;
	
	@Autowired
	EcForm6EiaConsultantDetailsRepository ecForm6EiaConsultantDetailsRepository;
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	// [Method to Save Ec Form 6 Eia Consultant Details]
	public Object saveEiaConsultantDetails(EcForm6EiaConsultantDetails ecForm6EiaConsultantDetails,Integer ecId,
			HttpServletRequest request) {
		try {
			if (ecForm6EiaConsultantDetails == null) {

				return ResponseHandler.generateResponse("ecForm 6 EiaConsultantDetails Values should not be null",
						HttpStatus.OK, "Data Not found", request);
			}
			ecForm6EiaConsultantDetails.setEcForm6BasicDetailId(ecId);
			ecForm6EiaConsultantDetailsRepository.save(ecForm6EiaConsultantDetails);
			return ecForm6EiaConsultantDetails;

		} catch (Exception e) {
			log.error("===========EcForm6EiaConsultantDetails Service============>>>>>>>>>>>" + e.toString() + " "
					+ e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving EC form 6 Eia Consultant Details - " + ecForm6EiaConsultantDetails.getId(), e);
		}
	}

	// [Method to get Consultant Details data by ID]
	public Object getConsultantDetails(Integer ecId) {
		EcForm6EiaConsultantDetails ecForm6EiaConsultantDetails = null;
		ProponentApplications proponentApplications = null;
		try {
			ecForm6EiaConsultantDetails = ecForm6EiaConsultantDetailsRepository.getConsultantDetailsById(ecId);
			proponentApplications = proponentApplicationRepository.getApplicationByProposalId_6(ecId);
			if (ecForm6EiaConsultantDetails == null) {
				throw new UserNotFoundException("Data not found");
			}
			if (proponentApplications == null) {
				ecForm6EiaConsultantDetails.setLastStatus(Caf_Status.DRAFT.toString());
			} else {
				ecForm6EiaConsultantDetails.setLastStatus(proponentApplications.getLast_status());
			}
			return ecForm6EiaConsultantDetails;

		} catch (Exception e) {
			log.error("===========EcForm6Consultant Detail get() method============>>>>>>>>>>>" + e.toString() + " "
					+ e.getStackTrace()[0]);

			throw new PariveshException("Error in View EC form 6 Conultant Details - " + ecForm6EiaConsultantDetails,
					e);
		}
	}
}