package com.backend.service.EcForm6CafDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.PariveshException;
import com.backend.model.EcForm6CafDetails.EcForm6CafDetails;
import com.backend.repository.postgres.EcForm6CafDetails.EcForm6CafDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class EcForm6CafDetailsService {

	@Autowired
	private EcForm6CafDetailsRepository ecForm6CafDetailsRepository;

	public Object saveEcForm6CafDetails(EcForm6CafDetails ecForm6CafDetails) {
		try {
			return ecForm6CafDetailsRepository.save(ecForm6CafDetails);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC form 6 CafDetails- " + e);
		}
	}

	public Object getEcForm6CafDetailsByNewCafId(Integer new_caf_id) {
		try {
			EcForm6CafDetails ecForm6CafDetails = ecForm6CafDetailsRepository
					.getEcForm6CafDetailsByNewCafId(new_caf_id);
			return ecForm6CafDetails;
 
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getEcForm6CafDetailsByNewCafId EC form 6 CafDetails- " + e);
		}
	}

//updateEcForm6CafDetailsByNewCafId(new_caf_id))
	public Object updateEcForm6CafDetailsByNewCafId(Integer new_caf_id,Integer ec_id,Integer status) {
		try {
			
			EcForm6CafDetails ecForm6CafDetails=ecForm6CafDetailsRepository.getEcForm6CafDetailsByNewCafId(new_caf_id);
			ecForm6CafDetails.setStatus(status);
			ecForm6CafDetails.setEcId(ec_id);
			
			EcForm6CafDetails ecForm6CafDetails1=ecForm6CafDetailsRepository.save(ecForm6CafDetails);
			
			return ecForm6CafDetails1;
			
			//return ecForm6CafDetailsRepository.updateEcForm6CafDetailsByNewCafId(new_caf_id);
					
			//return ecForm6CafDetails;

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getEcForm6CafDetailsByNewCafId EC form 6 CafDetails- " + e);
		}
	}

}