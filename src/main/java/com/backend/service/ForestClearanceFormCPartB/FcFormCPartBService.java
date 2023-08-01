package com.backend.service.ForestClearanceFormCPartB;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.backend.model.FcFormBPartB.FcFormBPartBBasicDetails;
import com.backend.model.FcFormBPartB.FcFormBPartBCaLand;
import com.backend.model.ForestClearanceFormCPartB.*;
import com.backend.repository.postgres.FcFormBPartB.FcFormBPartBCaLandRepository;
import com.backend.repository.postgres.ForestClearanceFormCPartB.*;
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
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FcFormCPartBService {
	
	@Autowired
	private FcFormCPartBRepository fcFormCPartBRepository;
	
	@Autowired
	private FcFormCPartBTreeDetailsRepository fcFormCPartBTreeDetailsRepository;
	
	@Autowired
	private FcFormCPartBDensityOfVegetationRepository fcFormCPartBDensityOfVegetationRepository;
	
	@Autowired
	private FcFormCPartBViolationDetailsRepository fcFormCPartBViolationDetailsRepository;
	
	@Autowired
	FcFormCPartBLegalStatusRepository fcFormCPartBLegalStatusRepository;
	
	@Autowired
	FcFormCPartBDistrictWiseRepository fcFormCPartBDistrictWiseRepository;
	
	@Autowired
	FcFormCPartBEnumerationDetailsRepository fcFormCPartBEnumerationDetailsRepository;
	
	@Autowired
	FcFormCPartBComponentDetailsRepository fcFormCPartBComponentDetailsRepository;
	
	@Autowired
	FcFormCPartBWLSpecificDetailsRepository fcFormCPartBWLSpecificDetailsRepository;
	
	@Autowired
	FcFormCPartBAfforestationDetailsRepository fcFormCPartBAfforestationDetailsRepository;
	
	@Autowired
	FcFormCPartBOtherDetailsRepository fcFormCPartBOtherDetailsRepository;
	
	@Autowired
	private FcFormCPartBPatchesRepository fcFormCPartBPatchesRepository;
	
	@Autowired
	private FcFormCPartBPatchWiseDetailsRepository fcFormCPartBPatchWiseDetailsRepository;
	
	@Autowired
	private FcFormCPartBLegalStatusRepository fcBLegalStatusRepository;
	
	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;
	
	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;
	
	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private FcFormCPartBCaLandRepository caLandRepository;

	
	public ResponseEntity<Object> saveFcFormCPartB(FcFormCPartB fcFormCPartB,
			HttpServletRequest request) throws PariveshException {
		try {
			FcFormCPartB formCPartB;
			
			formCPartB = fcFormCPartBRepository.save(fcFormCPartB);
			
			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(formCPartB.getFc_id());


			if (departmentApplicationRepository.getDepartmentApplicationByProposalId(formCPartB.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(6).get();
				applications.setApplications(app);
				applications.setProponentApplications(proponentApplications);
				applications.setProposal_sequence(proponentApplications.getProposal_sequence());
				applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
				applications.setProposal_id(formCPartB.getId());
				applications.setStatus(Caf_Status.DRAFT.toString());
				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

				departmentApplicationRepository.save(applications);
			}

			return ResponseHandler.generateResponse("FC Form C Part B BasicDetails form", HttpStatus.OK, null,
					formCPartB);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			log.error(e.getLocalizedMessage());
			log.error(e.getMessage());
			throw new PariveshException("Error in Saving FC Part B saveFCPartBBasicDetails id- " , e);

		}
	}
	
	public ResponseEntity<Object> getFCFormCPartB(Integer FC_FormC_PartB_Id) throws PariveshException {
		try {
			FcFormCPartB fcFormCPartB;
			fcFormCPartB = fcFormCPartBRepository.findByIdActive(FC_FormC_PartB_Id);	
			fcFormCPartB.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(fcFormCPartB.getId()));

			return ResponseHandler.generateResponse("Get FC Form C Part B BasicDetails form", HttpStatus.OK, null,
					fcFormCPartB);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B getFCPartBBasicDetails id- " + FC_FormC_PartB_Id, e);
		}
	}
	
	public ResponseEntity<Object> saveFcFormCPartBTreeDetails(List<FcFormCPartBTreeDetails> fcFormCPartBTreeDetails,
			Integer FC_PartB_BasicDetails_Id) throws PariveshException {
		try {
			List<FcFormCPartBTreeDetails> forestClearanceBTreeDetails2;

			FcFormCPartB forestClearanceBBasicDetails = fcFormCPartBRepository
					.findById(FC_PartB_BasicDetails_Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

			forestClearanceBTreeDetails2 = fcFormCPartBTreeDetails.stream().map(value -> {
				value.setFcFormCPartB(forestClearanceBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Form C Part B Tree Details form", HttpStatus.OK, null,
					fcFormCPartBTreeDetailsRepository.saveAll(forestClearanceBTreeDetails2));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B saveFcFormCPartBTreeDetails id- " + FC_PartB_BasicDetails_Id, e);
		}
	}

	public ResponseEntity<Object> deleteFCFormCPartBTreeDetails(Integer FC_PartB_Tree_Details_Id) throws PariveshException {
		try {
			FcFormCPartBTreeDetails forestClearanceBTreeDetails;

			forestClearanceBTreeDetails = fcFormCPartBTreeDetailsRepository.findById(FC_PartB_Tree_Details_Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B Tree Deatils not found with ID"));

			forestClearanceBTreeDetails.setIs_active(false);
			forestClearanceBTreeDetails.setIs_deleted(true);
			log.info("INFO ------------ deleteFCPartBTreeDetails WITH TREE DETAILS ID ---->" + FC_PartB_Tree_Details_Id
					+ " ------ SAVING - SUCCESS");
			return ResponseHandler.generateResponse("Delete FC Form C Part B Tree Details form", HttpStatus.OK, null,
					fcFormCPartBTreeDetailsRepository.save(forestClearanceBTreeDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting FC Part B deleteFCFormCPartBTreeDetails id- " + FC_PartB_Tree_Details_Id, e);
		}
	}
	
	
	public ResponseEntity<Object> saveFCFormCPartBDensityOfVegetation(
			List<FcFormCPartBDensityOfVegetation> fcFormCPartBDensityOfVegetations,
			Integer FC_PartB_BasicDetails_Id) throws PariveshException {
		try {
			List<FcFormCPartBDensityOfVegetation> fcFormCPartBDensityOfVegetations2;

			FcFormCPartB forestClearanceBBasicDetails = fcFormCPartBRepository
					.findById(FC_PartB_BasicDetails_Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

			fcFormCPartBDensityOfVegetations2 = fcFormCPartBDensityOfVegetations.stream().map(value -> {
				value.setFcFormCPartB(forestClearanceBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Form C Part B BasicDetails form", HttpStatus.OK, null,
					fcFormCPartBDensityOfVegetationRepository.saveAll(fcFormCPartBDensityOfVegetations2));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B saveFCFormCPartBDensityOfVegetation id- " + FC_PartB_BasicDetails_Id, e);
		}
	}

	public ResponseEntity<Object> deleteFCFormCPartBDensityOfVegetation(Integer FC_FormC_PartB_density_of_vegetation_id)
			throws PariveshException {
		try {
			FcFormCPartBDensityOfVegetation fcFormCPartBDensityOfVegetation;

			fcFormCPartBDensityOfVegetation = fcFormCPartBDensityOfVegetationRepository
					.findById(FC_FormC_PartB_density_of_vegetation_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B Vegetation Details not found with ID"));

			fcFormCPartBDensityOfVegetation.setIs_active(false);
			fcFormCPartBDensityOfVegetation.setIs_deleted(true);

			return ResponseHandler.generateResponse("Delete FC Form C Part B density of vegetation form", HttpStatus.OK, null,
					fcFormCPartBDensityOfVegetationRepository.save(fcFormCPartBDensityOfVegetation));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting FC Form C Part B deleteFCFormCPartBDensityOfVegetation id- "
					+ FC_FormC_PartB_density_of_vegetation_id, e);
		}
	}
	
	public ResponseEntity<Object> saveFCFormCPartBViolationDetails(List<FcFormCPartBViolationDetails> violationsList,
			Integer FC_FormC_PartB__Id) throws PariveshException {
		try {
			List<FcFormCPartBViolationDetails> bViolationDetails;
			FcFormCPartB forestClearanceBBasicDetails = fcFormCPartBRepository
					.findById(FC_FormC_PartB__Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B not found with ID"));

			bViolationDetails = violationsList.stream().map(value -> {
				value.setFcFormCPartB(forestClearanceBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Form C Part B FCFormCPartBViolationDetails form", HttpStatus.OK, null,
					fcFormCPartBViolationDetailsRepository.saveAll(bViolationDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Part B BasicDetails for saveFCFormCPartBViolationDetails id- "
					+ FC_FormC_PartB__Id, e);
		}
	}

	public ResponseEntity<Object> deleteFCFormCPartBViolationDetails(Integer vd_id) throws PariveshException {
		try {
			FcFormCPartBViolationDetails temp = fcFormCPartBViolationDetailsRepository.findById(vd_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B Violation Not Found"));
			temp.setIs_active(false);
			temp.setIs_deleted(true);

			return ResponseHandler.generateResponse("Violation Deletion", HttpStatus.OK, "",
					fcFormCPartBViolationDetailsRepository.save(temp));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting FC Part B deleteFCFormCPartBViolationDetails id- " + vd_id, e);
		}
	}
	
	public ResponseEntity<Object> deleteFcFormCPartBLegalStatus(Integer id) throws ProjectNotFoundException {
		try {
			FcFormCPartBLegalStatus forestClearanceBLegalStatus = fcFormCPartBLegalStatusRepository
					.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B LegalStatus not found with ID"));
			forestClearanceBLegalStatus.setIs_active(false);
			forestClearanceBLegalStatus.setIs_deleted(true);
			fcFormCPartBLegalStatusRepository.save(forestClearanceBLegalStatus);
			return ResponseHandler.generateResponse("FC Form C Part B LegalStatus Deleted successfully", HttpStatus.OK, "NO ERROR",
					forestClearanceBLegalStatus);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B deleteFcFormCPartBLegalStatus id- " + e);
		}
	}
	
	public ResponseEntity<Object> deleteFCFormCPartBDistrictwise(Integer id) throws ProjectNotFoundException {
		try {
			FcFormCPartBDistrictWise forestClearanceBDistrictWise = fcFormCPartBDistrictWiseRepository
					.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B DistrictWise not found with ID"));
			forestClearanceBDistrictWise.setIs_active(false);
			forestClearanceBDistrictWise.setIs_deleted(true);
			fcFormCPartBDistrictWiseRepository.save(forestClearanceBDistrictWise);
			return ResponseHandler.generateResponse("FC Form C Part B DistrictWise Deleted successfully", HttpStatus.OK, "NO ERROR",
					forestClearanceBDistrictWise);
		} catch (Exception e) {
			 log.error(e.toString() + "" + e.getStackTrace()[0]);
			throw new ProjectNotFoundException("Project Not found for id- " + id);
		}
	}
	
	public ResponseEntity<Object> saveFCFormCPartBDistrictwise(List<FcFormCPartBDistrictWise> fcFormCPartBDistrictWises,Integer fc_form_c_part_b_id) throws ProjectNotFoundException {
		try {
			List<FcFormCPartBDistrictWise> bDistrictWise;
			FcFormCPartB fcFormBPartBBasicDetails = fcFormCPartBRepository
					.findById(fc_form_c_part_b_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B not found with ID"));
			bDistrictWise = fcFormCPartBDistrictWises.stream().map(value -> {
				value.setFcFormCPartB(fcFormBPartBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Form C Part B District Wise form", HttpStatus.OK, null,
					fcFormCPartBDistrictWiseRepository.saveAll(bDistrictWise));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Form C Part B saveFcFormCPartBDistrictWise id- " + fc_form_c_part_b_id, e);
		}
	}
	
	public ResponseEntity<Object> deleteFCFormCPartBEnumerationDetails(Integer id) throws ProjectNotFoundException {
		try {
			FcFormCPartBEnumerationDetails forestClearanceBEnumerationDetails = fcFormCPartBEnumerationDetailsRepository
					.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B Enumeration Details not found with ID"));
			forestClearanceBEnumerationDetails.setIs_active(false);
			forestClearanceBEnumerationDetails.setIs_deleted(true);
			fcFormCPartBEnumerationDetailsRepository.save(forestClearanceBEnumerationDetails);
			return ResponseHandler.generateResponse("FC Form C Part B Enumeration Details Deleted successfully", HttpStatus.OK, "NO ERROR",
					forestClearanceBEnumerationDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>deleteFCFormCPartBEnumerationDetails" + e.toString() + " " + e.getStackTrace()[0]);
			throw new ProjectNotFoundException("Project Not found for id- " + id);
		}
	}
	
	public ResponseEntity<Object> saveFcFormCPartBComponentDetails(
			List<FcFormCPartBComponentDetails> fcFormBPartBComponentDetails, Integer fc_form_c_part_b_id) {
		try {
			List<FcFormCPartBComponentDetails> bComponentDetails;
			FcFormCPartB fcFormBPartBBasicDetails = fcFormCPartBRepository
					.findById(fc_form_c_part_b_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B not found with ID"));
			bComponentDetails = fcFormBPartBComponentDetails.stream().map(value -> {
				value.setFcFormCPartB(fcFormBPartBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Form C Part B BasicDetails form", HttpStatus.OK, null,
					fcFormCPartBComponentDetailsRepository.saveAll(bComponentDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Form C Part B saveFcFormCPartBComponentDetails id- " + fc_form_c_part_b_id, e);
		}
	}

	public ResponseEntity<Object> deleteFcFormBPartBComponentDetails(Integer cd_id) {

		try {
			FcFormCPartBComponentDetails temp = fcFormCPartBComponentDetailsRepository.findById(cd_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B component Not Found"));
			temp.setIs_active(false);
			temp.setIs_deleted(true);

			return ResponseHandler.generateResponse("FC Form C Part B Component Deletion", HttpStatus.OK, "",
					fcFormCPartBComponentDetailsRepository.save(temp));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting FC Part B deleteFcFormBPartBComponentDetails id- " + cd_id, e);
		}
	}
	

	public ResponseEntity<Object> deleteFCPartBAllComponentDetails(Integer FC_PartB_BasicDetails_Id)
			throws PariveshException {
		try {
			List<FcFormCPartBComponentDetails> bComponentDetails;
			List<FcFormCPartBComponentDetails> temp = fcFormCPartBComponentDetailsRepository
					.findAllByBasicDetailsId(FC_PartB_BasicDetails_Id);
			bComponentDetails = temp.stream().map(value -> {
				value.setIs_active(false);
				value.setIs_deleted(true);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("FC Form C Part B All Component Deletion", HttpStatus.OK, "",
					fcFormCPartBComponentDetailsRepository.saveAll(bComponentDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting FC Part B deleteFCPartBAllComponentDetails id- " + FC_PartB_BasicDetails_Id, e);
		}
	}
	
	public ResponseEntity<Object> saveFCPartBWLSpecificDetails(
			FcFormCPartBWLSpecificDetails fcFormCPartBWLSpecificDetails, Integer FC_FormC_PartB_BasicDetails_Id)
			throws PariveshException {
		try {

			FcFormCPartB forestClearanceBBasicDetails = fcFormCPartBRepository
					.findById(FC_FormC_PartB_BasicDetails_Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B not found with ID"));

			fcFormCPartBWLSpecificDetails.setFcFormCPartB(forestClearanceBBasicDetails);
			log.info("INFO ------------ setForestClearanceBBasicDetails WITH BASIC DETAILS ID ---->"
					+ FC_FormC_PartB_BasicDetails_Id + " ------ SAVING - SUCCESS");
			return ResponseHandler.generateResponse("Save FC Form C Part B BasicDetails form", HttpStatus.OK, null,
					fcFormCPartBWLSpecificDetailsRepository.save(fcFormCPartBWLSpecificDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Form C Part B saveFCPartBWLSpecificDetails id- " + FC_FormC_PartB_BasicDetails_Id, e);
		}
	}

	public ResponseEntity<Object> saveFCFormCPartBAfforestationDetails(FcFormCPartBAfforestationDetails afforestation,
			Integer FC_FormC_PartB_Id) throws PariveshException {
		try {
			FcFormCPartB forestClearanceBBasicDetails = fcFormCPartBRepository
					.findById(FC_FormC_PartB_Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B not found with ID"));
			afforestation.setFcFormCPartB(forestClearanceBBasicDetails);

			return ResponseHandler.generateResponse("Save FC Form C Part B BasicDetails form", HttpStatus.OK, null,
					fcFormCPartBAfforestationDetailsRepository.save(afforestation));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Form C Part B saveFCFormCPartBAfforestationDetails id- " + FC_FormC_PartB_Id, e);
		}
	}

	public ResponseEntity<Object> deleteFCFormCPartBAfforestationDetails(Integer ad_id) throws PariveshException {
		try {
			FcFormCPartBAfforestationDetails temp = fcFormCPartBAfforestationDetailsRepository.findById(ad_id)
					.orElseThrow(() -> new ProjectNotFoundException("Afforestation Not Found"));
			temp.setIs_active(false);
			temp.setIs_deleted(true);
			log.info("INFO ------------ deleteFCPartBAfforestationDetails AFFORESTATION WITH AFFORESTATION ID ---->"
					+ ad_id + " ---- SAVED - SUCCESS");
			return ResponseHandler.generateResponse("FC Form C Part B Afforestation Deletion", HttpStatus.OK, "",
					fcFormCPartBAfforestationDetailsRepository.save(temp));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting FC Form C Part B deleteFCFormCPartBAfforestationDetails id- " + ad_id,
					e);
		}
	}
	
	public ResponseEntity<Object> saveFCFormCPartBOtherDetails(FcFormCPartBOtherDetails forestClearanceBOtherDetails,
			Integer FC_FormC_PartB_Id, HttpServletRequest request) throws PariveshException {
		try {

			FcFormCPartB forestClearanceBBasicDetails = fcFormCPartBRepository
					.findById(FC_FormC_PartB_Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B not found with ID"));

			forestClearanceBOtherDetails.setFcFormCPartB(forestClearanceBBasicDetails);

			DepartmentApplication departmentApplication = departmentApplicationRepository
					.getApplicationByProposalId(FC_FormC_PartB_Id);
			if (departmentApplication != null) {
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
				departmentApplicationRepository.save(departmentApplication);
			}

			return ResponseHandler.generateResponse("Save FC Form C Part B OtherDetails form", HttpStatus.OK, null,
					fcFormCPartBOtherDetailsRepository.save(forestClearanceBOtherDetails));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FC Part B saveFCFormCPartBOtherDetails id- " + FC_FormC_PartB_Id, e);
		}
	}
	
	/*
	 * 
	fcFormCPartBPatchWiseDetailsRepository;
	*/
	
	public ResponseEntity<Object> saveFCFormCPartBPatch(List<FcFormCPartBPatches> patchList,
			Integer FC_FormC_PartB_Id) throws PariveshException {
		try {
			List<FcFormCPartBPatches> bPatches;
			FcFormCPartB forestClearanceBBasicDetails = fcFormCPartBRepository
					.findById(FC_FormC_PartB_Id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B not found with ID"));
			bPatches = patchList.stream().map(value -> {
				value.setFcFormCPartB(forestClearanceBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC Form C Part B Patch form", HttpStatus.OK, null,
					fcFormCPartBPatchesRepository.saveAll(bPatches));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC Form C Part B saveFCFormCPartBPatch id- " + FC_FormC_PartB_Id,
					e);
		}
	}

	public ResponseEntity<Object> deleteFCFormCPartBPatch(Integer p_id) throws PariveshException {
		try {
			FcFormCPartBPatches patch = fcFormCPartBPatchesRepository.findById(p_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B Patch found with ID"));
			patch.setIs_active(false);
			patch.setIs_deleted(true);
			return ResponseHandler.generateResponse("FC Form C Part B Patch Deletion", HttpStatus.OK, "",
					fcFormCPartBPatchesRepository.save(patch));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting FC Form C Part B deleteFCFormCPartBPatch id- " + p_id, e);
		}
	}
	
//	public ResponseEntity<Object> saveFCFormCPartBPatchWiseDetails(
//			List<FcFormCPartBPatchWiseDetails> forestClearanceBPatchWiseDetails, Integer patch_id)
//			throws PariveshException {
//		try {
//			List<FcFormCPartBPatchWiseDetails> forestClearanceBPatchWiseDetails2;
//			FcFormCPartBPatches patches = fcFormCPartBPatchesRepository.getById(patch_id);
//
//			forestClearanceBPatchWiseDetails2 = forestClearanceBPatchWiseDetails.stream().map(value -> {
//				value.setFcFormCPartBPatches(patches);
//				return value;
//			}).collect(Collectors.toList());
//			return ResponseHandler.generateResponse("Save FC Form C Part B patch wise details with patch id -------> " + patch_id,
//					HttpStatus.OK, null, fcFormCPartBPatchWiseDetailsRepository.saveAll(forestClearanceBPatchWiseDetails2));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Saving FC Form C Part B saveFCFormCPartBPatchWiseDetails id- " + patch_id, e);
//		}
//	}

//	public ResponseEntity<Object> deleteFCFormCPartBPatchWiseDetails(Integer patch_id) throws PariveshException {
//		try {
//			List<FcFormCPartBPatchWiseDetails> bPatchWiseDetails;
//			List<FcFormCPartBPatchWiseDetails> temp = fcFormCPartBPatchWiseDetailsRepository.findAllByPatchId(patch_id);
//			bPatchWiseDetails = temp.stream().map(value -> {
//				value.setIs_active(false);
//				value.setIs_deleted(true);
//				return value;
//			}).collect(Collectors.toList());
//
//			log.info("INFO ------------ deleteFCPartBPatchWiseDetails WITH patch id ---->" + patch_id
//					+ " ---- DELETING");
//			return ResponseHandler.generateResponse("FC Form C Part B PatchWise Deletion", HttpStatus.OK, "",
//					fcFormCPartBPatchWiseDetailsRepository.saveAll(bPatchWiseDetails));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Deleting FC Form C Part B deleteFCFormCPartBPatchWiseDetails id- " + patch_id, e);
//		}
//	}
	
	public ResponseEntity<Object> deleteFCFormCPartBLegalStatus(Integer id) throws ProjectNotFoundException {
		try {
			FcFormCPartBLegalStatus forestClearanceBLegalStatus = fcBLegalStatusRepository
					.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Part B LegalStatus not found with ID"));
			forestClearanceBLegalStatus.setIs_active(false);
			forestClearanceBLegalStatus.setIs_deleted(true);
			fcBLegalStatusRepository.save(forestClearanceBLegalStatus);
			return ResponseHandler.generateResponse("FCFormC Deleted successfully", HttpStatus.OK, "NO ERROR",
					forestClearanceBLegalStatus);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving FCFormC Part B deleteFCFormCPartBLegalStatus id- " + e);
		}
	}

	public ResponseEntity<Object> saveFCFormCPartBCaLand(List<FcFormCPartBCaLand> caLandList,
														 Integer fc_form_c_part_b_basic_details_id) throws PariveshException {
		try {
			List<FcFormCPartBCaLand> caLands;
			FcFormCPartB fcFormCPartBBasicDetails = fcFormCPartBRepository
					.findById(fc_form_c_part_b_basic_details_id)
					.orElseThrow(() -> new ProjectNotFoundException("FC Form C Part B not found with ID"));
			caLands = caLandList.stream().map(value -> {
				value.setFcFormCPartB(fcFormCPartBBasicDetails);
				return value;
			}).collect(Collectors.toList());

			return ResponseHandler.generateResponse("Save FC From C Part B CA Land Details form", HttpStatus.OK, null,
					caLandRepository.saveAll(caLands));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving FC From C Part B saveFCFormCPartBCaLand id- " + fc_form_c_part_b_basic_details_id,
					e);
		}
	}


	public ResponseEntity<Object> deleteFCFormCPartBCaLand(Integer ca_id) throws PariveshException {
		try {
			FcFormCPartBCaLand caLand = caLandRepository.findById(ca_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ca Land found with ID"));
			caLand.set_active(false);
			caLand.set_deleted(true);

			return ResponseHandler.generateResponse("Ca Land Deletion", HttpStatus.OK, "",
					caLandRepository.save(caLand));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting FC Form C Part B deleteFCFormCPartBCaLand id- " + ca_id, e);
		}
	}

}
