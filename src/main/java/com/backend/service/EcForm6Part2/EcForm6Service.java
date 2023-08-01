package com.backend.service.EcForm6Part2;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.ProponentApplications;
import com.backend.model.EcForm6Part1.EcForm6BasicDetails;
import com.backend.model.EcForm6Part2.EcForm6ProductAmendmentConfDetails;
import com.backend.model.EcForm6Part2.EcForm6ProductAmendmentRequiredApproved;
import com.backend.model.EcForm6Part2.EcForm6ProductProByprodetails;
import com.backend.model.EcForm6Part2.EcForm6ProductStatusdetails;
import com.backend.model.EcForm6Part2.EcForm6Productdetails;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.EcForm6Part1.EcForm6BasicDetailsRepository;
import com.backend.repository.postgres.EcForm6Part2.EcForm6ProductAmendmentConfDetailsRepository;
import com.backend.repository.postgres.EcForm6Part2.EcForm6ProductAmendmentRequiredApprovedRepository;
import com.backend.repository.postgres.EcForm6Part2.EcForm6ProductProByprodetailsRepository;
import com.backend.repository.postgres.EcForm6Part2.EcForm6ProductStatusdetailsRepository;
import com.backend.repository.postgres.EcForm6Part2.EcForm6ProductdetailsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EcForm6Service {

	@Autowired
	private EcForm6ProductdetailsRepository ecForm6ProductdetailsRepository;

	@Autowired
	private EcForm6BasicDetailsRepository ecForm6BasicDetailsRepository;
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;
	
	@Autowired
	private EcForm6ProductStatusdetailsRepository ecForm6ProductStatusdetailsRepository;
	
	@Autowired
	private EcForm6ProductProByprodetailsRepository ecForm6ProductProByprodetailsRepository;
	
	@Autowired
	private EcForm6ProductAmendmentRequiredApprovedRepository ecForm6ProductAmendmentRequiredApprovedRepository;
	
    @Autowired
    private EcForm6ProductAmendmentConfDetailsRepository ecForm6ProductAmendmentConfDetailsRepository;
	
	
	public ResponseEntity<Object> saveEcForm6(EcForm6Productdetails ecForm6, Integer ec_id,
			HttpServletRequest request) {
		EcForm6Productdetails ecForm6Productdetails = null;
		try {
			
			
		// If form is exist then delete its child
		//System.out.print("id is>>>>>>>"+ecForm6.getId());
		//ecForm6.setId(4);
			if(ecForm6!=null)
			if(ecForm6.getId()>0)
			{
				//System.out.println("Delete block");
				
			// get the existing record of product detail
			//EcForm6Productdetails ecForm6Productdetails1 = ecForm6ProductdetailsRepository.getById(ecForm6.getId());
				EcForm6Productdetails ecForm6Productdetails1 = ecForm6ProductdetailsRepository.getProductDetailsById(ec_id);
				//getProductDetailsById
						//ecForm6.get
				
				// delete all the childs corresponding to this id.
				/*
				 * List<EcForm6ProductStatusdetails> ecForm6EcStatus
                 List<EcForm6ProductProByprodetails> product
				 List<EcForm6ProductAmendmentRequiredApproved> product_otheramendment
                 List<EcForm6ProductAmendmentConfDetails> product_exist
				 */
				
				
			// Delete of List<EcForm6ProductStatusdetails> ecForm6EcStatus
			
			if(ecForm6Productdetails1!=null)
			{
			List<EcForm6ProductStatusdetails> lfcForm6ProductStatusdetails = ecForm6Productdetails1.getEcForm6EcStatus();
			
			if(lfcForm6ProductStatusdetails!=null)
			{
			for(int i=0;i<lfcForm6ProductStatusdetails.size();i++)
			{
				int ii  = lfcForm6ProductStatusdetails.get(i).getId();
				ecForm6ProductStatusdetailsRepository.deleteById(ii);
			}
			}
			// Delete of List<EcForm6ProductProByprodetails> product
			
			List<EcForm6ProductProByprodetails> lfcForm6ProductProByprodetails = ecForm6Productdetails1.getProduct();
					
			if(lfcForm6ProductProByprodetails!=null)
			{
			for (int i = 0; i < lfcForm6ProductProByprodetails.size(); i++) {
				int ii = lfcForm6ProductProByprodetails.get(i).getId();
				ecForm6ProductProByprodetailsRepository.deleteById(ii);
			}
			}			

						// Delete of List<EcForm6ProductAmendmentRequiredApproved> product_otheramendment
			//System.out.println("Delete block--222");
						List<EcForm6ProductAmendmentRequiredApproved> lproduct_otheramendment = ecForm6Productdetails1
								.getProduct_otheramendment();

						if(lproduct_otheramendment!=null)
						{
						for (int i = 0; i < lproduct_otheramendment.size(); i++) {
							int ii = lproduct_otheramendment.get(i).getId();
							ecForm6ProductAmendmentRequiredApprovedRepository.deleteById(ii);
						}
						}			
									
									
									
									// Delete of List<EcForm6ProductAmendmentConfDetails> product_exist
									
									List<EcForm6ProductAmendmentConfDetails> lfcForm6ProductAmendmentConfDetails = ecForm6Productdetails1
											.getProduct_exist();
									
									if(lfcForm6ProductAmendmentConfDetails!=null)
									{
									for (int i = 0; i < lfcForm6ProductAmendmentConfDetails.size(); i++) {
										int ii = lfcForm6ProductAmendmentConfDetails.get(i).getId();
										ecForm6ProductAmendmentConfDetailsRepository.deleteById(ii);

									}									}
										
				}
			//System.out.println("Delete block--333");
			}
			
			
			
			
			
			

			EcForm6BasicDetails ecForm6BasicDetails = ecForm6BasicDetailsRepository.getFormById(ec_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ec form 6 Basic Detail is not found"));
			
			//System.out.println("Delete block--444");

			if (ecForm6BasicDetails != null) {
				ecForm6.setEcForm6BasicDetails(ecForm6BasicDetails);

				/*--------------------Set Proposal no of Basic Detals-----------------------*/
				if (ecForm6.getBrief_note_status_of_implementation_copy()!= null) {
					ecForm6.getBrief_note_status_of_implementation_copy()
							.setProposal_no(ecForm6BasicDetails.getProposal_no());
				}
				if (ecForm6.getConsent_order_including_renewal()!= null) {
					ecForm6.getConsent_order_including_renewal()
							.setProposal_no(ecForm6BasicDetails.getProposal_no());
				}
				if (ecForm6.getLatest_consent_order_copy() != null) {
					ecForm6.getLatest_consent_order_copy().setProposal_no(ecForm6BasicDetails.getProposal_no());
				}
				if (ecForm6.getSubstantiating_reasons_of_delay() != null) {
					ecForm6.getSubstantiating_reasons_of_delay().setProposal_no(ecForm6BasicDetails.getProposal_no());
				}
				if (ecForm6.getBar_chart_of_implementation() != null) {
					ecForm6.getBar_chart_of_implementation().setProposal_no(ecForm6BasicDetails.getProposal_no());
				}

				//System.out.println("Delete block--555");
				/*--------------------Set Proposal no of Basic Detals-----------------------*/

				ecForm6Productdetails = ecForm6ProductdetailsRepository.save(ecForm6); 
				//System.out.println("Delete block--666");
			}
			return ResponseHandler.generateResponse("Save Ec form 6 Product Details", HttpStatus.OK, null,
					ecForm6Productdetails);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC form 6 Product Detail for ecForm6ProductDetails Id- " + ecForm6.getId(), e);
		}

	}

	public Optional<EcForm6Productdetails> getProductDetails1(Integer ecId, HttpServletRequest request) {
		Optional<EcForm6Productdetails> ecForm6ProductDetails = null;
		try {
			ecForm6ProductDetails = ecForm6ProductdetailsRepository.findById(ecId);
		
			if (ecForm6ProductDetails.isEmpty()) {
				throw new UserNotFoundException("Data not found");
			}
			return ecForm6ProductDetails;

		} catch (Exception e) {
			log.error("===========EcForm6Product Detail get() method============>>>>>>>>>>>" + e.toString() + " "
					+ e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in View EC form 6 Product Details - " + ecForm6ProductDetails.get().getId(), e);
		}
	}
	
	public ResponseEntity<Object> deleteProductDetails(Integer id) {
		try {

			Integer upadate = ecForm6ProductdetailsRepository.delete(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC Form 6 Product Details", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Form 6 Product Details", e);
		}
	}
	
	// [Method to return the result of Product Details by ID]
	public Object getProductDetails(Integer ecId) {
		EcForm6Productdetails ecForm6ProductDetails = null;
		ProponentApplications proponentApplications = null;
		try {
			ecForm6ProductDetails = ecForm6ProductdetailsRepository.getProductDetailsById(ecId);
			proponentApplications = proponentApplicationRepository.getApplicationByProposalId_6(ecId);
			if (ecForm6ProductDetails == null) {
				throw new UserNotFoundException("Data not found");
			}
			if (proponentApplications == null) {
				ecForm6ProductDetails.setLastStatus(Caf_Status.DRAFT.toString());
			} else {
				ecForm6ProductDetails.setLastStatus(proponentApplications.getLast_status());
			}
			return ecForm6ProductDetails;

		} catch (Exception e) {
			log.error("===========EcForm6Product Detail get() method============>>>>>>>>>>>" + e.toString() + " "
					+ e.getStackTrace()[0]);

			throw new PariveshException("Error in View EC form 6 Product Details - " + ecForm6ProductDetails, e);
		}
	}
}