package com.backend.service.ForestClearancePartB;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ForestClearance;
import com.backend.model.ForestClearancePartB.*;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.ForestClearancePartB.*;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ForestClearancePartBService {

    @Autowired
    private ForestClearanceBViolationDetailsRepository bViolationDetailsRepository;

    @Autowired
    private ForestClearanceBComponentDetailsRepository bComponentDetailsRepository;

    @Autowired
    private ForestClearanceBAfforestationDetailsRepository bAfforestationDetailsRepository;

    @Autowired
    private ForestClearanceBPatchesRepository bPatchesRepository;

    @Autowired
    private ForestClearanceBPatchWiseDetailsRepository bPatchWiseDetailsRepository;

    @Autowired
    private ForestClearanceBBasicDetailsRepository forestClearanceBBasicDetailsRepository;

    @Autowired
    private ForestClearanceBTreeDetailsRepository forestClearanceBTreeDetailsRepository;
    @Autowired
    private ForestClearanceBWLSpecificDetailsRepository forestClearanceBWLSpecificDetailsRepository;

    @Autowired
    private ForestClearanceBOtherDetailsRepository forestClearanceBOtherDetailsRepository;

    @Autowired
    private ForestClearanceBDensityOfVegetationRepository bDensityOfVegetationRepository;

    @Autowired
    private ForestClearanceBEnumerationDetailsRepository forestClearanceBEnumerationDetailsRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ForestClearanceRepository forestClearanceRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private ClearanceHistoryRepository clearanceHistoryRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private ForestClearanceBDistrictWiseRepository forestClearanceBDistrictWiseRepository;

    @Autowired
    private ForestClearanceBLegalStatusRepository forestClearanceBLegalStatusRepository;

    @Autowired
    private ForestClearanceBCaLandRepository caLandRepository;

    public ResponseEntity<Object> saveFCPartBViolationDetails(List<ForestClearanceBViolationDetails> violationsList,
                                                              Integer FC_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceBViolationDetails> bViolationDetails;
            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            bViolationDetails = violationsList.stream().map(value -> {
                value.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B FCPartBViolationDetails form", HttpStatus.OK, null,
                    bViolationDetailsRepository.saveAll(bViolationDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B BasicDetails for saveFCPartBViolationDetails id- "
                    + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartBViolationDetails(Integer vd_id) throws PariveshException {
        try {
            ForestClearanceBViolationDetails temp = bViolationDetailsRepository.findById(vd_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Violation Not Found"));
            temp.set_active(false);
            temp.set_deleted(true);

            return ResponseHandler.generateResponse("Violation Deletion", HttpStatus.OK, "",
                    bViolationDetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBViolationDetails id- " + vd_id, e);
        }
    }

    public ResponseEntity<Object> saveFCPartBComponentDetails(List<ForestClearanceBComponentDetails> componentList,
                                                              Integer FC_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceBComponentDetails> bComponentDetails;
            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            bComponentDetails = componentList.stream().map(value -> {
                value.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    bComponentDetailsRepository.saveAll(bComponentDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBComponentDetails id- " + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartBComponentDetails(Integer cd_id) throws PariveshException {
        try {
            ForestClearanceBComponentDetails temp = bComponentDetailsRepository.findById(cd_id)
                    .orElseThrow(() -> new ProjectNotFoundException("component Not Found"));
            temp.set_active(false);
            temp.set_deleted(true);

            return ResponseHandler.generateResponse("Component Deletion", HttpStatus.OK, "",
                    bComponentDetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBComponentDetails id- " + cd_id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartBAllComponentDetails(Integer FC_PartB_BasicDetails_Id)
            throws PariveshException {
        try {
            List<ForestClearanceBComponentDetails> bComponentDetails;
            List<ForestClearanceBComponentDetails> temp = bComponentDetailsRepository
                    .findAllByBasicDetailsId(FC_PartB_BasicDetails_Id);
            bComponentDetails = temp.stream().map(value -> {
                value.set_active(false);
                value.set_deleted(true);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Component Deletion", HttpStatus.OK, "",
                    bComponentDetailsRepository.saveAll(bComponentDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Deleting FC Part B deleteFCPartBAllComponentDetails id- " + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> saveFCPartBAfforestationDetails(ForestClearanceBAfforestationDetails afforestation,
                                                                  Integer FC_PartB_BasicDetails_Id) throws PariveshException {
        try {
            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            afforestation.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    bAfforestationDetailsRepository.save(afforestation));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBAfforestationDetails id- " + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartBAfforestationDetails(Integer ad_id) throws PariveshException {
        try {
            ForestClearanceBAfforestationDetails temp = bAfforestationDetailsRepository.findById(ad_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Afforestation Not Found"));
            temp.set_active(false);
            temp.set_deleted(true);
            log.info("INFO ------------ deleteFCPartBAfforestationDetails AFFORESTATION WITH AFFORESTATION ID ---->"
                    + ad_id + " ---- SAVED - SUCCESS");
            return ResponseHandler.generateResponse("Afforestation Deletion", HttpStatus.OK, "",
                    bAfforestationDetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBAfforestationDetails id- " + ad_id,
                    e);
        }
    }

    public ResponseEntity<Object> saveFCPartBPatch(List<ForestClearanceBPatches> patchList,
                                                   Integer FC_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceBPatches> bPatches;
            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            bPatches = patchList.stream().map(value -> {
//				List<ForestClearanceBPatchWiseDetails> forestClearanceBPatchWiseDetails=value.getForestClearanceBPatchWiseDetails();
//				forestClearanceBPatchWiseDetails = forestClearanceBPatchWiseDetails.stream().map(temp -> {
//					ForestClearanceBPatches clearanceBPatches = value;
//					temp.setForestClearanceBPatches(clearanceBPatches);
//					return temp;
//				}).collect(Collectors.toList());
                value.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    bPatchesRepository.saveAll(bPatches));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B saveFCPartBPatch id- " + FC_PartB_BasicDetails_Id,
                    e);
        }
    }

    public ResponseEntity<Object> deleteFCPartBPatch(Integer p_id) throws PariveshException {
        try {
            ForestClearanceBPatches patch = bPatchesRepository.findById(p_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Patch found with ID"));
            patch.set_active(false);
            patch.set_deleted(true);

            return ResponseHandler.generateResponse("Patch Deletion", HttpStatus.OK, "",
                    bPatchesRepository.save(patch));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBPatch id- " + p_id, e);
        }
    }

    public ResponseEntity<Object> saveFCPartBBasicDetails(ForestClearanceBBasicDetails forestClearanceBBasicDetails,
                                                          HttpServletRequest request) throws PariveshException {
        try {
            ForestClearanceBBasicDetails basicDetails;
            basicDetails = forestClearanceBBasicDetailsRepository.save(forestClearanceBBasicDetails);

            ForestClearance forestTemp = forestClearanceRepository
                    .findDetailByFcId(forestClearanceBBasicDetails.getFc_id());
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(forestTemp.getId());

            if (departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()) == null) {
                DepartmentApplication applications = new DepartmentApplication();
                Applications app = applicationsRepository.findById(6).get();
                applications.setApplications(app);
                applications.setProponentApplications(proponentApplications);
                applications.setProposal_sequence(proponentApplications.getProposal_sequence());
                applications.setProposal_no(forestTemp.getProposal_no());
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
    }

    public ResponseEntity<Object> getFCPartBBasicDetails(Integer FC_PartB_BasicDetails_Id) throws PariveshException {
        try {
            ForestClearanceBBasicDetails basicDetails;
            basicDetails = forestClearanceBBasicDetailsRepository.findByIdActive(FC_PartB_BasicDetails_Id);
            basicDetails.setDepartmentApplication(
                    departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));

            return ResponseHandler.generateResponse("Get FC Part B BasicDetails form", HttpStatus.OK, null,
                    basicDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B getFCPartBBasicDetails id- " + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> saveFCPartBTreeDetails(List<ForestClearanceBTreeDetails> forestClearanceBTreeDetails,
                                                         Integer FC_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceBTreeDetails> forestClearanceBTreeDetails2;

            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            forestClearanceBTreeDetails2 = forestClearanceBTreeDetails.stream().map(value -> {
                value.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    forestClearanceBTreeDetailsRepository.saveAll(forestClearanceBTreeDetails2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBTreeDetails id- " + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartBTreeDetails(Integer FC_PartB_Tree_Details_Id) throws PariveshException {
        try {
            ForestClearanceBTreeDetails forestClearanceBTreeDetails;

            forestClearanceBTreeDetails = forestClearanceBTreeDetailsRepository.findById(FC_PartB_Tree_Details_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Tree Deatils not found with ID"));

            forestClearanceBTreeDetails.setIs_active(false);
            forestClearanceBTreeDetails.setIs_deleted(true);
            log.info("INFO ------------ deleteFCPartBTreeDetails WITH TREE DETAILS ID ---->" + FC_PartB_Tree_Details_Id
                    + " ------ SAVING - SUCCESS");
            return ResponseHandler.generateResponse("Delete FC Part B Tree Details form", HttpStatus.OK, null,
                    forestClearanceBTreeDetailsRepository.save(forestClearanceBTreeDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Deleting FC Part B deleteFCPartBTreeDetails id- " + FC_PartB_Tree_Details_Id, e);
        }
    }

    public ResponseEntity<Object> saveFCPartBWLSpecificDetails(
            ForestClearanceBWLSpecificDetails forestClearanceBWLSpecificDetails, Integer FC_PartB_BasicDetails_Id)
            throws PariveshException {
        try {

            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            forestClearanceBWLSpecificDetails.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
            log.info("INFO ------------ setForestClearanceBBasicDetails WITH BASIC DETAILS ID ---->"
                    + FC_PartB_BasicDetails_Id + " ------ SAVING - SUCCESS");
            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    forestClearanceBWLSpecificDetailsRepository.save(forestClearanceBWLSpecificDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBWLSpecificDetails id- " + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> saveFCPartBOtherDetails(ForestClearanceBOtherDetails forestClearanceBOtherDetails,
                                                          Integer FC_PartB_BasicDetails_Id, HttpServletRequest request) throws PariveshException {
        try {

            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            forestClearanceBOtherDetails.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);

            ForestClearance forestTemp = forestClearanceRepository
                    .findDetailByFcId(forestClearanceBBasicDetails.getFc_id());

            DepartmentApplication departmentApplication = departmentApplicationRepository
                    .getDepartmentApplicationByProposalId(FC_PartB_BasicDetails_Id);
            if (departmentApplication != null) {
                departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
                departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
                departmentApplicationRepository.save(departmentApplication);
            }

            // forestClearanceBOtherDetails.setForestClearanceBPatches(forestClearanceBPatches);

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    forestClearanceBOtherDetailsRepository.save(forestClearanceBOtherDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBOtherDetails id- " + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> saveFCPartBDensityOfVegetation(
            List<ForestClearanceBDensityOfVegetation> forestClearanceBDensityOfVegetations,
            Integer FC_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceBDensityOfVegetation> forestClearanceBDensityOfVegetations2;

            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            forestClearanceBDensityOfVegetations2 = forestClearanceBDensityOfVegetations.stream().map(value -> {
                value.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    bDensityOfVegetationRepository.saveAll(forestClearanceBDensityOfVegetations2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBDensityOfVegetation id- " + FC_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartBDensityOfVegetation(Integer FC_PartB_density_of_vegetation_id)
            throws PariveshException {
        try {
            ForestClearanceBDensityOfVegetation forestClearanceBDensityOfVegetation;

            forestClearanceBDensityOfVegetation = bDensityOfVegetationRepository
                    .findById(FC_PartB_density_of_vegetation_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Vegetation Details not found with ID"));

            forestClearanceBDensityOfVegetation.setIs_active(false);
            forestClearanceBDensityOfVegetation.setIs_deleted(true);

            return ResponseHandler.generateResponse("Delete FC Part B density of vegetation form", HttpStatus.OK, null,
                    bDensityOfVegetationRepository.save(forestClearanceBDensityOfVegetation));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBDensityOfVegetation id- "
                    + FC_PartB_density_of_vegetation_id, e);
        }
    }

    public ResponseEntity<Object> saveFCPartBPatchWiseDetails(
            List<ForestClearanceBPatchWiseDetails> forestClearanceBPatchWiseDetails, Integer patch_id)
            throws PariveshException {
        try {
            List<ForestClearanceBPatchWiseDetails> forestClearanceBPatchWiseDetails2;
            ForestClearanceBPatches patches = bPatchesRepository.getById(patch_id);

            forestClearanceBPatchWiseDetails2 = forestClearanceBPatchWiseDetails.stream().map(value -> {
                value.setForestClearanceBPatches(patches);
                return value;
            }).collect(Collectors.toList());
            return ResponseHandler.generateResponse("Save patch wise details with patch id -------> " + patch_id,
                    HttpStatus.OK, null, bPatchWiseDetailsRepository.saveAll(forestClearanceBPatchWiseDetails2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B saveFCPartBPatchWiseDetails id- " + patch_id, e);
        }
    }

//    public ResponseEntity<Object> deleteFCPartBPatchWiseDetails(Integer patch_id) throws PariveshException {
//        try {
//            List<ForestClearanceBPatchWiseDetails> bPatchWiseDetails;
//            List<ForestClearanceBPatchWiseDetails> temp = bPatchWiseDetailsRepository.findAllByPatchId(patch_id);
//            bPatchWiseDetails = temp.stream().map(value -> {
//                value.set_active(false);
//                value.set_deleted(true);
//                return value;
//            }).collect(Collectors.toList());
//
//            log.info("INFO ------------ deleteFCPartBPatchWiseDetails WITH patch id ---->" + patch_id
//                    + " ---- DELETING");
//            return ResponseHandler.generateResponse("Component Deletion", HttpStatus.OK, "",
//                    bPatchWiseDetailsRepository.saveAll(bPatchWiseDetails));
//        } catch (Exception e) {
//            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBPatchWiseDetails id- " + patch_id, e);
//        }
//    }

    public ResponseEntity<Object> deleteFCPartBEnumerationDetails(Integer id) throws ProjectNotFoundException {
        try {
            ForestClearanceBEnumerationDetails forestClearanceBEnumerationDetails = forestClearanceBEnumerationDetailsRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Enumeration Details not found with ID"));
            forestClearanceBEnumerationDetails.setIs_active(false);
            forestClearanceBEnumerationDetails.setIs_deleted(true);
            forestClearanceBEnumerationDetailsRepository.save(forestClearanceBEnumerationDetails);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    forestClearanceBEnumerationDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new ProjectNotFoundException("Project Not found for id- " + id);
        }
    }

    public ResponseEntity<Object> deleteFCPartBDistrictwise(Integer id) throws ProjectNotFoundException {
        try {
            ForestClearanceBDistrictWise forestClearanceBDistrictWise = forestClearanceBDistrictWiseRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B DistrictWise not found with ID"));
            forestClearanceBDistrictWise.setIs_active(false);
            forestClearanceBDistrictWise.setIs_deleted(true);
            forestClearanceBDistrictWiseRepository.save(forestClearanceBDistrictWise);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    forestClearanceBDistrictWise);
        } catch (Exception e) {
            log.error(e.toString() + "" + e.getStackTrace()[0]);
            throw new ProjectNotFoundException("Project Not found for id- " + id);
        }
    }

    public ResponseEntity<Object> deleteFCPartBLegalStatus(Integer id) throws ProjectNotFoundException {
        try {
            ForestClearanceBLegalStatus forestClearanceBLegalStatus = forestClearanceBLegalStatusRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B LegalStatus not found with ID"));
            forestClearanceBLegalStatus.setIs_active(false);
            forestClearanceBLegalStatus.setIs_deleted(true);
            forestClearanceBLegalStatusRepository.save(forestClearanceBLegalStatus);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    forestClearanceBLegalStatus);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveEnumerationDetails id- " + e);
        }
    }

//	public ResponseEntity<Object> saveEnumerationDetails(
//			List<ForestClearanceBEnumerationDetails> forestClearanceBEnumerationDetails, Integer fc_part_b_tree_details)
//			throws PariveshException {
//		try {
//			List<ForestClearanceBEnumerationDetails> forestClearanceBEnumerationDetails2;
//			ForestClearanceBTreeDetails forestClearanceBTreeDetails = forestClearanceBTreeDetailsRepository
//					.findById(fc_part_b_tree_details).orElseThrow();
//
//			forestClearanceBEnumerationDetails2 = forestClearanceBEnumerationDetails.stream().map(value -> {
//				value.setForestClearanceBTreeDetails(forestClearanceBTreeDetails);
//				return value;
//			}).collect(Collectors.toList());
//			return ResponseHandler.generateResponse("Saving Enumeration Details", HttpStatus.OK, "NO ERROR",
//					forestClearanceBEnumerationDetailsRepository.saveAll(forestClearanceBEnumerationDetails2));
//		} catch (Exception e) {
//			log.error(e.toString() + "-------------------------->>>> " + e.getStackTrace()[0]);
//			throw new PariveshException(
//					"Error in Saving FC Part B saveEnumerationDetails id- " + fc_part_b_tree_details);
//		}
//	}

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, ForestClearance forestClearanceForm) {

        String proposalId = "FP/" + stateRepository.getStateByCode(forestClearanceForm.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/" + forestClearanceForm.getProject_activity_id() + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());

        /*
         * String cafId = "FP/" +
         * stateRepository.getStateById(forestClearanceForm.getState()).getState_abbr()
         * + "/" + forestClearanceForm.getProject_activity_id() + "/" +
         * generateSequenceNumber(maxcount) + "/" +
         * String.valueOf(LocalDate.now().getYear());
         */
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
        return proposalId;
    }

    public ResponseEntity<Object> saveFCPartBCaLand(List<ForestClearanceBCaLand> caLandList,
                                                    Integer FC_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceBCaLand> caLands;
            ForestClearanceBBasicDetails forestClearanceBBasicDetails = forestClearanceBBasicDetailsRepository
                    .findById(FC_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            caLands = caLandList.stream().map(value -> {
                value.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B CA Land Details form", HttpStatus.OK, null,
                    caLandRepository.saveAll(caLands));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B saveFCPartBCaLand id- " + FC_PartB_BasicDetails_Id,
                    e);
        }
    }


    public ResponseEntity<Object> deleteFCPartBCaLand(Integer ca_id) throws PariveshException {
        try {
            ForestClearanceBCaLand caLand = caLandRepository.findById(ca_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ca Land found with ID"));
            caLand.set_active(false);
            caLand.set_deleted(true);

            return ResponseHandler.generateResponse("Ca Land Deletion", HttpStatus.OK, "",
                    caLandRepository.save(caLand));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBCaLand id- " + ca_id, e);
        }
    }

}
