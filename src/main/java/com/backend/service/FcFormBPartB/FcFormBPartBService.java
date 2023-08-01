package com.backend.service.FcFormBPartB;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.FcFormBPartB.*;
import com.backend.model.ForestClearanceFormB.FcFormBProjectDetails;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.FcFormB.FcFormBProjectDetailsRepository;
import com.backend.repository.postgres.FcFormBPartB.*;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class FcFormBPartBService {

    @Autowired
    private FcFormBPartBBasicDetailsRepository fcFormBPartBBasicDetailsRepository;

    @Autowired
    private FcFormBProjectDetailsRepository forestClearanceRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private FcFormBPartBTreeDetailsRepository fcFormBPartBTreeDetailsRepository;

    @Autowired
    private FcFormBPartBViolationDetailsRepository bViolationDetailsRepository;

    @Autowired
    private FcFormBPartBDensityOfVegetationRepository bDensityOfVegetationRepository;

    @Autowired
    private FcFormBPartBComponentDetailsRepository bComponentDetailsRepository;

    @Autowired
    private FcFormBPartBAfforestationDetailsRepository bAfforestationDetailsRepository;

    @Autowired
    private FcFormBPartBWLSpecificDetailsRepository forestClearanceBWLSpecificDetailsRepository;

    @Autowired
    private FcFormBPartBOtherDetailsRepository forestClearanceBOtherDetailsRepository;

    @Autowired
    private FcFormBPartBEnumerationDetailsRepository forestClearanceBEnumerationDetailsRepository;

    @Autowired
    private FcFormBPartBDistrictWiseRepository forestClearanceBDistrictWiseRepository;

    @Autowired
    private FcFormBPartBLegalStatusRepository forestClearanceBLegalStatusRepository;

    @Autowired
    private FcFormBPartBPatchesRepository bPatchesRepository;

    @Autowired
    private FcFormBPartBPatchWiseDetailsRepository bPatchWiseDetailsRepository;

    @Autowired
    private FcFormBPartBCaLandRepository caLandRepository;

    public ResponseEntity<Object> saveFCformBPartBBasicDetails(FcFormBPartBBasicDetails fcFormBPartBBasicDetails,
                                                               HttpServletRequest request) {

        try {
            FcFormBPartBBasicDetails basicDetails;
            basicDetails = fcFormBPartBBasicDetailsRepository.save(fcFormBPartBBasicDetails);

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(fcFormBPartBBasicDetails.getFc_id());

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

    }

    public ResponseEntity<Object> getFCformBPartBBasicDetails(Integer Fc_part_b_basic_details_id) {
        try {
            FcFormBPartBBasicDetails basicDetails;
            basicDetails = fcFormBPartBBasicDetailsRepository.findByIdActive(Fc_part_b_basic_details_id);
            basicDetails.setDepartmentApplication(
                    departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));

            return ResponseHandler.generateResponse("Get FC Part B BasicDetails form", HttpStatus.OK, null,
                    basicDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B getFCPartBBasicDetails id- " + Fc_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> saveFCFormBPartBTreeDetails(List<FcFormBPartBTreeDetails> fcFormBPartBTreeDetails,
                                                              Integer fc_form_b_part_b_basic_details_id) {

        try {
            List<FcFormBPartBTreeDetails> fcFormBPartBTreeDetails2;

            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_form_b_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            fcFormBPartBTreeDetails2 = fcFormBPartBTreeDetails.stream().map(value -> {
                value.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    fcFormBPartBTreeDetailsRepository.saveAll(fcFormBPartBTreeDetails2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBTreeDetails id- " + fc_form_b_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> deleteFCformBPartBTreeDetails(Integer fC_Tree_Details_Id) {
        try {
            FcFormBPartBTreeDetails fcFormBPartBTreeDetails;

            fcFormBPartBTreeDetails = fcFormBPartBTreeDetailsRepository.findById(fC_Tree_Details_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Tree Deatils not found with ID"));

            fcFormBPartBTreeDetails.setIs_active(false);
            fcFormBPartBTreeDetails.setIs_deleted(true);

            return ResponseHandler.generateResponse("Delete FC Part B Tree Details form", HttpStatus.OK, null,
                    fcFormBPartBTreeDetailsRepository.save(fcFormBPartBTreeDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Deleting FC Part B deleteFCPartBTreeDetails id- " + fC_Tree_Details_Id, e);
        }
    }

    public ResponseEntity<Object> saveFcFormBPartBViolationDetails(
            List<FcFormBPartBViolationDetails> fcFormBPartBViolationDetails, Integer fc_part_b_basic_details_id) {
        try {
            List<FcFormBPartBViolationDetails> bViolationDetails;
            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            bViolationDetails = fcFormBPartBViolationDetails.stream().map(value -> {
                value.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B FCPartBViolationDetails form", HttpStatus.OK, null,
                    bViolationDetailsRepository.saveAll(bViolationDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B BasicDetails for saveFCPartBViolationDetails id- "
                    + fc_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> deleteFcFormBPartBViolationDetails(Integer vd_id) {

        try {
            FcFormBPartBViolationDetails temp = bViolationDetailsRepository.findById(vd_id)
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

    public ResponseEntity<Object> saveFcFormBPartBDensityOfVegetation(
            List<FcFormBPartBDensityOfVegetation> fcFormBPartBDensityOfVegetation, Integer fc_part_b_basic_details_id) {
        try {
            List<FcFormBPartBDensityOfVegetation> fcFormBPartBDensityOfVegetation2;

            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            fcFormBPartBDensityOfVegetation2 = fcFormBPartBDensityOfVegetation.stream().map(value -> {
                value.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    bDensityOfVegetationRepository.saveAll(fcFormBPartBDensityOfVegetation2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBDensityOfVegetation id- " + fc_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> deleteFcFormBPartBDensityOfVegetation(Integer fc_part_b_density_of_vegetation_id) {
        try {
            FcFormBPartBDensityOfVegetation fcFormBPartBDensityOfVegetation;

            fcFormBPartBDensityOfVegetation = bDensityOfVegetationRepository
                    .findById(fc_part_b_density_of_vegetation_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Vegetation Details not found with ID"));

            fcFormBPartBDensityOfVegetation.setIs_active(false);
            fcFormBPartBDensityOfVegetation.setIs_deleted(true);

            return ResponseHandler.generateResponse("Delete FC Part B density of vegetation form", HttpStatus.OK, null,
                    bDensityOfVegetationRepository.save(fcFormBPartBDensityOfVegetation));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBDensityOfVegetation id- "
                    + fc_part_b_density_of_vegetation_id, e);
        }
    }

    public ResponseEntity<Object> saveFcFormBPartBComponentDetails(
            List<FcFormBPartBComponentDetails> fcFormBPartBComponentDetails, Integer fc_part_b_basic_details_id) {
        try {
            List<FcFormBPartBComponentDetails> bComponentDetails;
            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            bComponentDetails = fcFormBPartBComponentDetails.stream().map(value -> {
                value.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    bComponentDetailsRepository.saveAll(bComponentDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBComponentDetails id- " + fc_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> deleteFcFormBPartBComponentDetails(Integer cd_id) {

        try {
            FcFormBPartBComponentDetails temp = bComponentDetailsRepository.findById(cd_id)
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


    /*
     * District Wise Data
     */

    public ResponseEntity<Object> saveFcFormBPartBDistrictWise(
            List<FcFormBPartBDistrictWise> fcFormBPartBDistrictWises, Integer fc_part_b_basic_details_id) {
        try {
            List<FcFormBPartBDistrictWise> bDistrictWise;
            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            bDistrictWise = fcFormBPartBDistrictWises.stream().map(value -> {
                value.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC B Part B District Wise form", HttpStatus.OK, null,
                    forestClearanceBDistrictWiseRepository.saveAll(bDistrictWise));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC B Part B District Wise saveFcFormBPartBDistrictWise id- " + fc_part_b_basic_details_id, e);
        }
    }
    /*
     * public ResponseEntity<Object> deleteFcFormBPartBDistrictWise(Integer cd_id) {
     *
     * try { FcFormBPartBDistrictWise temp =
     * forestClearanceBDistrictWiseRepository.findById(cd_id) .orElseThrow(() -> new
     * ProjectNotFoundException("District Not Found")); temp.setIs_active(false);
     * temp.setIs_deleted(true);
     *
     * return ResponseHandler.generateResponse("Delete District Wise",
     * HttpStatus.OK, "", forestClearanceBDistrictWiseRepository.save(temp)); }
     * catch (Exception e) { log.error("=======================>>>>>>>>>>>" +
     * e.toString() + " " + e.getStackTrace()[0]); throw new
     * PariveshException("Error in Deleting FC Part B deleteFCPartBComponentDetails id- "
     * + cd_id, e); } }
     */

    public ResponseEntity<Object> saveFcFormBPartBWLSpecificDetails(
            FcFormBPartBWLSpecificDetails fcFormBPartBWLSpecificDetails, Integer fc_part_b_basic_details_id) {
        try {

            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            fcFormBPartBWLSpecificDetails.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    forestClearanceBWLSpecificDetailsRepository.save(fcFormBPartBWLSpecificDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBWLSpecificDetails id- " + fc_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> saveFcFormBPartBOtherDetails(FcFormBPartBOtherDetails fcFormBPartBOtherDetails,
                                                               Integer fc_part_b_patch_id, HttpServletRequest request) {

        try {

            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_part_b_patch_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            fcFormBPartBOtherDetails.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);

            FcFormBProjectDetails forestTemp = forestClearanceRepository
                    .getFormById(fcFormBPartBBasicDetails.getFc_id());

            DepartmentApplication departmentApplication = departmentApplicationRepository
                    .getApplicationByProposalId(fc_part_b_patch_id);
            if (departmentApplication != null) {
                departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
                departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
                departmentApplicationRepository.save(departmentApplication);
            }

            // forestClearanceBOtherDetails.setForestClearanceBPatches(forestClearanceBPatches);

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    forestClearanceBOtherDetailsRepository.save(fcFormBPartBOtherDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B saveFCPartBOtherDetails id- " + fc_part_b_patch_id,
                    e);
        }

    }

    public ResponseEntity<Object> saveFcFormBPartBAfforestationDetails(
            FcFormBPartBAfforestationDetails fcFormBPartBAfforestationDetails, Integer fc_part_b_basic_details_id) {
        try {
            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            fcFormBPartBAfforestationDetails.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    bAfforestationDetailsRepository.save(fcFormBPartBAfforestationDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBAfforestationDetails id- " + fc_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> deleteFcFormBPartBEnumerationDetails(Integer enumerationDetails_id) {

        try {
            FcFormBPartBEnumerationDetails fcFormBPartBEnumerationDetails = forestClearanceBEnumerationDetailsRepository
                    .findById(enumerationDetails_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Enumeration Details not found with ID"));
            fcFormBPartBEnumerationDetails.setIs_active(false);
            fcFormBPartBEnumerationDetails.setIs_deleted(true);
            forestClearanceBEnumerationDetailsRepository.save(fcFormBPartBEnumerationDetails);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    fcFormBPartBEnumerationDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new ProjectNotFoundException("Project Not found for id- " + enumerationDetails_id);
        }
    }

    public ResponseEntity<Object> deleteFcFormBPartBDistritwise(Integer districtWise_id) {

        try {
            FcFormBPartBDistrictWise fcFormBPartBDistrictWise = forestClearanceBDistrictWiseRepository
                    .findById(districtWise_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B DistrictWise not found with ID"));
            fcFormBPartBDistrictWise.setIs_active(false);
            fcFormBPartBDistrictWise.setIs_deleted(true);
            forestClearanceBDistrictWiseRepository.save(fcFormBPartBDistrictWise);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    fcFormBPartBDistrictWise);
        } catch (Exception e) {
            log.error(e.toString() + "" + e.getStackTrace()[0]);
            throw new ProjectNotFoundException("Project Not found for id- " + districtWise_id);
        }
    }

    public ResponseEntity<Object> deleteFcFormBPartBLegalStatus(Integer legalStstus_id) {

        try {
            FcFormBPartBLegalStatus fcFormBPartBLegalStatus = forestClearanceBLegalStatusRepository
                    .findById(legalStstus_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B LegalStatus not found with ID"));
            fcFormBPartBLegalStatus.setIs_active(false);
            fcFormBPartBLegalStatus.setIs_deleted(true);
            forestClearanceBLegalStatusRepository.save(fcFormBPartBLegalStatus);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    fcFormBPartBLegalStatus);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B saveEnumerationDetails id- " + e);
        }
    }

    public ResponseEntity<Object> deleteFcFormBPartBPatch(Integer p_id) {

        try {
            FcFormBPartBPatches patch = bPatchesRepository.findById(p_id)
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

    // Commented coz FcFormBPartBPatchWiseDetails is saving in  FcFormBPartBPatch as TargetEntity

//    public ResponseEntity<Object> saveFcFormBPartBPatchWiseDetails(
//            List<FcFormBPartBPatchWiseDetails> fcFormBPartBPatchWiseDetails, Integer patch_id) {
//
//        try {
//            List<FcFormBPartBPatchWiseDetails> forestClearanceBPatchWiseDetails2;
//            FcFormBPartBPatches patches = bPatchesRepository.getById(patch_id);
//
//            forestClearanceBPatchWiseDetails2 = fcFormBPartBPatchWiseDetails.stream().map(value -> {
//                value.setFcFormBPartBPatches(patches);
//                return value;
//            }).collect(Collectors.toList());
//            return ResponseHandler.generateResponse("Save patch wise details with patch id -------> " + patch_id,
//                    HttpStatus.OK, null, bPatchWiseDetailsRepository.saveAll(forestClearanceBPatchWiseDetails2));
//        } catch (Exception e) {
//            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//            throw new PariveshException("Error in Saving FC Part B saveFCPartBPatchWiseDetails id- " + patch_id, e);
//        }
//    }

    public ResponseEntity<Object> saveFcFormBPartBPatch(List<FcFormBPartBPatches> fcFormBPartBPatches,
                                                        Integer fc_part_b_basic_details_id) {
        try {
            List<FcFormBPartBPatches> bPartBPatches;
            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            log.info("FCFORMBPARTBSERVICE::SAVEFCFORMBPARTBPATCH::fcFormBPartBBasicDetails:{}", fcFormBPartBBasicDetails.toString());

            bPartBPatches = fcFormBPartBPatches.stream().map(value -> {
                value.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());
            log.info("FCFORMBPARTBSERVICE::SAVEFCFORMBPARTBPATCH::bPartBPatches:{}", bPartBPatches.toString());

            return ResponseHandler.generateResponse("Saving Patch Details", HttpStatus.OK, "",
                    bPatchesRepository.saveAll(bPartBPatches));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFcFormBPartBPatch id- " + fc_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> saveFCFormBPartBCaLand(List<FcFormBPartBCaLand> caLandList,
                                                         Integer fc_form_b_part_b_basic_details_id) throws PariveshException {
        try {
            List<FcFormBPartBCaLand> caLands;
            FcFormBPartBBasicDetails fcFormBPartBBasicDetails = fcFormBPartBBasicDetailsRepository
                    .findById(fc_form_b_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Form B Part B not found with ID"));
            caLands = caLandList.stream().map(value -> {
                value.setFcFormBPartBBasicDetails(fcFormBPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC From B Part B CA Land Details form", HttpStatus.OK, null,
                    caLandRepository.saveAll(caLands));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC From B Part B saveFCPartBCaLand id- " + fc_form_b_part_b_basic_details_id,
                    e);
        }
    }


    public ResponseEntity<Object> deleteFCFormBPartBCaLand(Integer ca_id) throws PariveshException {
        try {
            FcFormBPartBCaLand caLand = caLandRepository.findById(ca_id)
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
