package com.backend.service.EcForm6DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm6Data.EcForm6Data;
import com.backend.model.EcForm6Part1.EcForm6BasicDetails;
import com.backend.model.EcForm6Part2.EcForm6Productdetails;
import com.backend.model.EcForm6Part3.EcForm6EiaConsultantDetails;
import com.backend.model.EcForm6Part5.EcForm6Undertaking;
import com.backend.repository.postgres.AdditionalInformationRepository;
import com.backend.repository.postgres.EcForm6Part1.EcForm6BasicDetailsRepository;
import com.backend.repository.postgres.EcForm6Part2.EcForm6ProductdetailsRepository;
import com.backend.repository.postgres.EcForm6Part3.EcForm6EiaConsultantDetailsRepository;
import com.backend.repository.postgres.EcForm6Part5.EcForm6UndertakingRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcForm6DataService {

	@Autowired
	private EcForm6BasicDetailsRepository ecForm6BasicDetailsRepository;

	@Autowired
	private EcForm6ProductdetailsRepository ecForm6ProductdetailsRepository;

	@Autowired
	private EcForm6EiaConsultantDetailsRepository ecForm6EiaConsultantDetailsRepository;

	@Autowired
	private EcForm6UndertakingRepository ecForm6UndertakingRepository;

	/*@Autowired
	private AdditionalInformationRepository additionalInformationRepository;*/

	public Object getEcForm6Data(Integer ecId) {
		try {

			EcForm6Data ecForm6Data = new EcForm6Data();
			EcForm6BasicDetails ecForm6BasicDetails = ecForm6BasicDetailsRepository.getBasicDetailsById(ecId);
			EcForm6Productdetails ecForm6Productdetails = ecForm6ProductdetailsRepository.getProductDetailsById(ecId);
			EcForm6EiaConsultantDetails ecForm6EiaConsultantDetails = ecForm6EiaConsultantDetailsRepository
					.getConsultantDetailsById(ecId);
			EcForm6Undertaking ecForm6Undertaking = ecForm6UndertakingRepository.getUndertaking(ecId);
			// additionalInformationRepository.

			ecForm6Data.setEcForm6BasicDetails(ecForm6BasicDetails);
			ecForm6Data.setEcForm6Productdetails(ecForm6Productdetails);
			ecForm6Data.setEcForm6EiaConsultantDetails(ecForm6EiaConsultantDetails);
			ecForm6Data.setEcForm6Undertaking(ecForm6Undertaking);

			return ecForm6Data;

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getEcForm6Data EC form 6 Details- " + e);
		}
	}

}