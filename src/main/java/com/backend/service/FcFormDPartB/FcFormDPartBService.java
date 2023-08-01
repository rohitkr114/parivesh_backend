package com.backend.service.FcFormDPartB;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.FcFormBPartB.FcFormBPartBBasicDetails;
import com.backend.model.FcFormBPartB.FcFormBPartBTreeDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBAfforestationDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBBasicDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBDensityOfVegetation;
import com.backend.model.FcFormDPartB.FcFormDPartBDistrictWise;
import com.backend.model.FcFormDPartB.FcFormDPartBEnumerationDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBLegalStatus;
import com.backend.model.FcFormDPartB.FcFormDPartBOtherDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBTreeDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBViolationDetails;
import com.backend.model.FcFormDPartB.FcFormDPartBWLSpecificDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.FcFormBPartB.FcFormBPartBBasicDetailsRepository;
import com.backend.repository.postgres.FcFormBPartB.FcFormBPartBTreeDetailsRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBAfforestationDetailsRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBBasicDetailsRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBDensityOfVegetationRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBDistrictWiseRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBEnumerationDetailsRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBLegalStatusRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBOtherDetailsRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBTreeDetailsRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBViolationDetailsRepository;
import com.backend.repository.postgres.FcFormDPartB.FcFormDPartBWLSpecificDetailsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class FcFormDPartBService {
	
	@Autowired
	private FcFormDPartBBasicDetailsRepository fcFormDPartBBasicDetailsRepository;
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;
	
	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;
	
	@Autowired
	private ApplicationsRepository applicationsRepository;
	
	@Autowired
	private FcFormDPartBViolationDetailsRepository bViolationDetailsRepository;

	@Autowired
	private FcFormDPartBDensityOfVegetationRepository bDensityOfVegetationRepository;
	
	@Autowired
	private FcFormDPartBWLSpecificDetailsRepository forestClearanceBWLSpecificDetailsRepository;
	
	@Autowired
	private FcFormDPartBOtherDetailsRepository forestClearanceBOtherDetailsRepository;

	@Autowired
	private FcFormDPartBAfforestationDetailsRepository bAfforestationDetailsRepository;	

	@Autowired
	private FcFormDPartBEnumerationDetailsRepository forestClearanceBEnumerationDetailsRepository;
	
	@Autowired
	private FcFormDPartBDistrictWiseRepository forestClearanceBDistrictWiseRepository;
	
	@Autowired
	private FcFormDPartBLegalStatusRepository forestClearanceBLegalStatusRepository;
	

	@Autowired
	private FcFormDPartBTreeDetailsRepository fcFormDPartBTreeDetailsRepository;

	
	//Save Basic Details
	public ResponseEntity<Object> saveFCformDPartBBasicDetails(FcFormDPartBBasicDetails fcFormDPartBBasicDetails,
			HttpServletRequest request) {
		try {
			FcFormDPartBBasicDetails basicDetails;
			basicDetails = fcFormDPartBBasicDetailsRepository.save(fcFormDPartBBasicDetails);

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(fcFormDPartBBasicDetails.getFc_id());

			if (departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(6).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
				applications.setProposal_id(basicDetails.getId());
				applications.setStatus(Caf_Status.DRAFT.toString());
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			}
			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
					basicDetails);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Part B saveFCPartBBasicDetails id- ", e);
		}

	}// end of Save basic details 


	// Get Basic Details 
	public ResponseEntity<Object> getFCformDPartBBasicDetails(Integer Fc_part_b_basic_details_id) {
		try {
			FcFormDPartBBasicDetails basicDetails;
			basicDetails = fcFormDPartBBasicDetailsRepository.findByIdActive(Fc_part_b_basic_details_id);
			basicDetails.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));

			return ResponseHandler.generateResponse("Fetched FC Part B BasicDetails form", HttpStatus.OK, null,
					basicDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in fetching FC Part B getFCPartBBasicDetails id- " + Fc_part_b_basic_details_id, e);
		}
	}	//end of get basic details 
	
	//Save of District wise Details
	public ResponseEntity<Object> saveFcFormDPartBDistrictWise(
			List<FcFormDPartBDistrictWise> fcFormDPartBDistrictWises, Integer fc_part_b_basic_details_id) {
		try {
			List<FcFormDPartBDistrictWise> bDistrictWise;
			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
					.findById(fc_part_b_basic_details_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
			bDistrictWise = fcFormDPartBDistrictWises.stream().map(value -> {
				value.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC D Part B District Wise form", HttpStatus.OK, null,
					forestClearanceBDistrictWiseRepository.saveAll(bDistrictWise));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC B Part B District Wise saveFcFormDPartBDistrictWise id- " + fc_part_b_basic_details_id, e);
		}
	}//End of Save of District wise Details

	//Delete of District wise Details
	public ResponseEntity<Object> deleteFcFormDPartBDistritwise(Integer districtWise_id) {

		try {
			FcFormDPartBDistrictWise fcFormDPartBDistrictWise = forestClearanceBDistrictWiseRepository
					.findById(districtWise_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B DistrictWise not found with ID"));
			fcFormDPartBDistrictWise.setIs_active(false);
			fcFormDPartBDistrictWise.setIs_deleted(true);
			forestClearanceBDistrictWiseRepository.save(fcFormDPartBDistrictWise);
			return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
					fcFormDPartBDistrictWise);
		} catch (Exception e) {
			log.error(e.toString() + "" + e.getStackTrace()[0]);
			throw new ProjectNotFoundException("Project Not found for id- " + districtWise_id);
		}
	}//End of Delete of District wise Details


	//Save of Legal status Details
//	public ResponseEntity<Object> saveFcFormDPartBLegalStatus(List<FcFormDPartBLegalStatus> fcFormDPartBLegalStatus,
//			Integer fc_part_b_basic_details_id) {
//		try {
//			List<FcFormDPartBLegalStatus> bLegalStatus;
//			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
//					.findById(fc_part_b_basic_details_id)
//					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
//			bLegalStatus = fcFormDPartBLegalStatus.stream().map(value -> {
//				value.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);
//				return value;
//			}).collect(Collectors.toList());
//
//			return ResponseHandler.generateResponse("Save FC D Part B District Wise form", HttpStatus.OK, null,
//					forestClearanceBLegalStatusRepository.saveAll(bLegalStatus));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException(
//					"Error in Saving FC B Part B District Wise saveFcFormDPartBDistrictWise id- " + fc_part_b_basic_details_id, e);
//		}
//	}//End of Save of Legal status Details


	//Delete of Legal status Details
	public ResponseEntity<Object> deleteFcFormDPartBLegalStatus(Integer legalStstus_id) {

		try {
			FcFormDPartBLegalStatus fcFormDPartBLegalStatus = forestClearanceBLegalStatusRepository
					.findById(legalStstus_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B LegalStatus not found with ID"));
			fcFormDPartBLegalStatus.setIs_active(false);
			fcFormDPartBLegalStatus.setIs_deleted(true);
			forestClearanceBLegalStatusRepository.save(fcFormDPartBLegalStatus);
			return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
					fcFormDPartBLegalStatus);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Part B saveEnumerationDetails id- " + e);
		}
	}//End of Delete of Legal status Details
	
	//Save Density of vegetation Details 
	public ResponseEntity<Object> saveFcFormDPartBDensityOfVegetation(
			List<FcFormDPartBDensityOfVegetation> fcFormDPartBDensityOfVegetation, Integer fc_part_b_basic_details_id) {
		try {
			List<FcFormDPartBDensityOfVegetation> fcFormDPartBDensityOfVegetation2;

			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
					.findById(fc_part_b_basic_details_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

			fcFormDPartBDensityOfVegetation2 = fcFormDPartBDensityOfVegetation.stream().map(value -> {
				value.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
					bDensityOfVegetationRepository.saveAll(fcFormDPartBDensityOfVegetation2));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B saveFCPartBDensityOfVegetation id- " + fc_part_b_basic_details_id, e);
		}
	}//End of save Density of vegetation Details 


	//Delete Density of vegetation Details 
	public ResponseEntity<Object> deleteFcFormDPartBDensityOfVegetation(Integer fc_part_b_density_of_vegetation_id) {
		try {
			FcFormDPartBDensityOfVegetation fcFormDPartBDensityOfVegetation;

			fcFormDPartBDensityOfVegetation = bDensityOfVegetationRepository
					.findById(fc_part_b_density_of_vegetation_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B Vegetation Details not found with ID"));

			fcFormDPartBDensityOfVegetation.setIs_active(false);
			fcFormDPartBDensityOfVegetation.setIs_deleted(true);

			return ResponseHandler.generateResponse("Delete FC Part B density of vegetation form", HttpStatus.OK, null,
					bDensityOfVegetationRepository.save(fcFormDPartBDensityOfVegetation));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting FC Part B deleteFCPartBDensityOfVegetation id- "
					+ fc_part_b_density_of_vegetation_id, e);
		}
	}//End of delete Density of vegetation Details 

	//Save of Enumeration Details
//	public ResponseEntity<Object> saveFcFormDPartBEnumerationDetails(
//			FcFormDPartBEnumerationDetails fcFormDPartBEnumerationDetails, Integer fc_part_b_basic_details_id) {
//		try {
//			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
//					.findById(fc_part_b_basic_details_id)
//					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
//			fcFormDPartBEnumerationDetails.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);
//
//			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
//					forestClearanceBEnumerationDetailsRepository.save(fcFormDPartBEnumerationDetails));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException(
//					"Error in Saving FC Part B saveFCPartBEnumerationDetails id- " + fc_part_b_basic_details_id, e);
//		}
//	}//End of save of Enumeration Details
	
	// save of Tree Details
	
	public ResponseEntity<Object> saveFCFormDPartBTreeDetails(List<FcFormDPartBTreeDetails> fcFormDPartBTreeDetails,
			Integer fc_form_d_part_b_basic_details_id) {

		try {
			List<FcFormDPartBTreeDetails> fcFormDPartBTreeDetails2;

			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
					.findById(fc_form_d_part_b_basic_details_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

			fcFormDPartBTreeDetails2 = fcFormDPartBTreeDetails.stream().map(value -> {
				value.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Part B Tree Details form", HttpStatus.OK, null,
					fcFormDPartBTreeDetailsRepository.saveAll(fcFormDPartBTreeDetails2));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B saveFCPartBTreeDetails id- " + fc_form_d_part_b_basic_details_id, e);
		}
	}//End of save of Enumeration Details
	
	//Delete of Tree Details
	
	public ResponseEntity<Object> deleteFCformDPartBTreeDetails(Integer fC_Tree_Details_Id) {
		try {
			FcFormDPartBTreeDetails fcFormDPartBTreeDetails;

			fcFormDPartBTreeDetails = fcFormDPartBTreeDetailsRepository.findById(fC_Tree_Details_Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B Tree Deatils not found with ID"));

			fcFormDPartBTreeDetails.setIs_active(false);
			fcFormDPartBTreeDetails.setIs_deleted(true);

			return ResponseHandler.generateResponse("Delete FC Part B Tree Details form", HttpStatus.OK, null,
					fcFormDPartBTreeDetailsRepository.save(fcFormDPartBTreeDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting FC Part B deleteFCPartBTreeDetails id- " + fC_Tree_Details_Id, e);
		}
	}//End of delete of tree Details


	//Delete of Enumeration Details
	public ResponseEntity<Object> deleteFcFormDPartBEnumerationDetails(Integer enumerationDetails_id) {

		try {
			FcFormDPartBEnumerationDetails fcFormDPartBEnumerationDetails = forestClearanceBEnumerationDetailsRepository
					.findById(enumerationDetails_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B Enumeration Details not found with ID"));
			fcFormDPartBEnumerationDetails.setIs_active(false);
			fcFormDPartBEnumerationDetails.setIs_deleted(true);
			forestClearanceBEnumerationDetailsRepository.save(fcFormDPartBEnumerationDetails);
			return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
					fcFormDPartBEnumerationDetails); 
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new ProjectNotFoundException("Project Not found for id- " + enumerationDetails_id);
		}
	}//End of Delete of Enumeration Details
	
	//Save of WL specific Details
	public ResponseEntity<Object> saveFcFormDPartBWLSpecificDetails(
			FcFormDPartBWLSpecificDetails fcFormDPartBWLSpecificDetails, Integer fc_part_b_basic_details_id) {
		try {

			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
					.findById(fc_part_b_basic_details_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

			fcFormDPartBWLSpecificDetails.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);

			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
					forestClearanceBWLSpecificDetailsRepository.save(fcFormDPartBWLSpecificDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B saveFCPartBWLSpecificDetails id- " + fc_part_b_basic_details_id, e);
		}
	}//End of save of WL specific Details 


	//save Violation Details 
	public ResponseEntity<Object> saveFcFormDPartBViolationDetails(
			List<FcFormDPartBViolationDetails> fcFormDPartBViolationDetails, Integer fc_part_b_basic_details_id) {
		try {
			List<FcFormDPartBViolationDetails> bViolationDetails;
			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
					.findById(fc_part_b_basic_details_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

			bViolationDetails = fcFormDPartBViolationDetails.stream().map(value -> {
				value.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Part B FCPartBViolationDetails form", HttpStatus.OK, null,
					bViolationDetailsRepository.saveAll(bViolationDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Part B BasicDetails for saveFCPartBViolationDetails id- "
					+ fc_part_b_basic_details_id, e);
		}
	}//End of Save Violation Details 

	//Delete Violation Details 
	public ResponseEntity<Object> deleteFcFormDPartBViolationDetails(Integer vd_id) {

		try {
			FcFormDPartBViolationDetails temp = bViolationDetailsRepository.findById(vd_id)
					.orElseThrow(() -> new ProjectNotFoundException("Violation Not Found"));
			temp.set_active(false);
			temp.set_deleted(true);

			return ResponseHandler.generateResponse("Violation Deletion", HttpStatus.OK, "",
					bViolationDetailsRepository.save(temp));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting FC Part B deleteFCPartBViolationDetails id- " + vd_id, e);
		}
	}//Delete Violation Details 
	
	//Save of Afforestation Details
	public ResponseEntity<Object> saveFcFormDPartBAfforestationDetails(
			FcFormDPartBAfforestationDetails fcFormDPartBAfforestationDetails, Integer fc_part_b_basic_details_id) {
		try {
			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
					.findById(fc_part_b_basic_details_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
			fcFormDPartBAfforestationDetails.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);

			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
					bAfforestationDetailsRepository.save(fcFormDPartBAfforestationDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B saveFCPartBAfforestationDetails id- " + fc_part_b_basic_details_id, e);
		}
	}//End of save of Afforestation Details

	
	//Save of Other Details like recommendation site inspection report
	public ResponseEntity<Object> saveFcFormDPartBOtherDetails(FcFormDPartBOtherDetails fcFormDPartBOtherDetails,
			Integer fc_part_b_patch_id, HttpServletRequest request) {

		try {
			FcFormDPartBBasicDetails fcFormDPartBBasicDetails = fcFormDPartBBasicDetailsRepository
					.findById(fc_part_b_patch_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
			fcFormDPartBOtherDetails.setFcFormDPartBBasicDetails(fcFormDPartBBasicDetails);

			DepartmentApplication departmentApplication = departmentApplicationRepository
					.getApplicationByProposalId(fc_part_b_patch_id);
			if (departmentApplication != null) {
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}
			return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
					forestClearanceBOtherDetailsRepository.save(fcFormDPartBOtherDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Part B saveFCPartBOtherDetails id- " + fc_part_b_patch_id,
					e);
		}
	}//End of save of Other Details like recommendation site inspection report

	
	

}
