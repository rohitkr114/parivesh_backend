package com.backend.service;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.exceptions.PariveshException;
import com.backend.model.FcChallanDetails;
import com.backend.model.ClientCode.ClientCode;
import com.backend.repository.postgres.FcChallanDetailsRepository;
import com.backend.repository.postgres.ClientCode.ClientCodeRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FcChallanDetailsService {
	@Autowired
	private FcChallanDetailsRepository fcChallanDetailsRepository;

	@Autowired
	private ClientCodeRepository clientCodeRepository;

	public ResponseEntity<Object> saveFcChallanDetails(FcChallanDetails fcChallanDetails, HttpServletRequest request) {
		try {
		
				FcChallanDetails fcChallanDetailsReturn;
				
				if(fcChallanDetailsRepository.findByApplicationIdActive(fcChallanDetails.getApplicationId())==null)
				{
					Random random = new Random();
					// Fetching Proposal number
					Integer proposalNumber = fcChallanDetailsRepository
							.findProposalNumberByApplicationId(fcChallanDetails.getApplicationId());
					// Generated Challan Number
					int twoDigitRandom=random.nextInt(90)+10;
					int threeDigitRandom=random.nextInt(900)+100;
					String challanNo = "" + twoDigitRandom + proposalNumber + threeDigitRandom;
					fcChallanDetails.setChallanNo(challanNo);

					// Fetched Client Code
					ClientCode clientCode = clientCodeRepository.getClientCodeByStateCode(fcChallanDetails.getState());

					// ClientCode clientCode=
					// fcChallanDetailsRepository.getClientCodeByStateCode(fcChallanDetails.getState());
					String fetchedClientCode = clientCode.getClient_code();
					// String fetchedClientCode="CAM5054";

					String[] part = fetchedClientCode.split("(?<=\\D)(?=\\d)");
					String clientCodeNumber = part[1];

					fcChallanDetails.setClientCode(fetchedClientCode);
					fcChallanDetails.setClientLocation(clientCode.getClient_name());

					// Generated Virtual Account number
					// Virtual Account No. - 1(NEFT Code) + 4 digit(Client Code) + Application No.aka Challan Number
					String virtualAccountNo = "" + 1 + clientCodeNumber + challanNo;
					fcChallanDetails.setBeneficiaryAccountNumber(virtualAccountNo);
					fcChallanDetails.setBeneficiaryName(clientCode.getClient_name() + " CAMPA");
					//setting MoEFCC file Number
					fcChallanDetails.setMoefFileNo(fcChallanDetailsRepository.findMoefccFileNoByApplicationId(fcChallanDetails.getApplicationId()));
					fcChallanDetailsReturn = fcChallanDetailsRepository.save(fcChallanDetails);
				}
				else
				{
					FcChallanDetails fcChallanDetailsTemp =fcChallanDetailsRepository.findByApplicationIdActive(fcChallanDetails.getApplicationId());
					fcChallanDetails.setId(fcChallanDetailsTemp.getId());
					fcChallanDetailsReturn = fcChallanDetailsRepository.save(fcChallanDetails);
				}
						
				return ResponseHandler.generateResponse("Save FC Challan Details", HttpStatus.OK, null,
						fcChallanDetailsReturn);
			
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Challan Details ", e);
		}
	}

	public ResponseEntity<Object> getFcChallanDetailsByApplicationId(Integer applicationId) {
		try {
			FcChallanDetails fcChallanDetailsReturn;
			fcChallanDetailsReturn = fcChallanDetailsRepository.findByApplicationIdActive(applicationId);

			return ResponseHandler.generateResponse("Challan Details by application id ", HttpStatus.OK, null,
					fcChallanDetailsReturn);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in fetching Challan Details by application id- " + applicationId, e);
		}
	}

}
