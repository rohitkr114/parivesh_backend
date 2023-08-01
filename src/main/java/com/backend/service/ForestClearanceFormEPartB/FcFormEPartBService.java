package com.backend.service.ForestClearanceFormEPartB;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.FcFormEPartB.*;
import com.backend.model.ForestClearance;
import com.backend.model.ForestClearanceE.FcFormE;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.FcFormEPartB.*;
import com.backend.repository.postgres.ForestClearanceFormE.FcFormEPartBCaLandRepository;
import com.backend.repository.postgres.ForestClearanceFormE.FcFormERepository;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FcFormEPartBService {

    @Autowired
    private ForestClearanceEViolationDetailsRepository eViolationDetailsRepository;

    @Autowired
    private ForestClearanceEComponentDetailsRepository eComponentDetailsRepository;

    @Autowired
    private ForestClearanceEAfforestationDetailsRepository eAfforestationDetailsRepository;

    @Autowired
    private ForestClearanceEPatchesRepository ePatchesRepository;

    @Autowired
    private ForestClearanceEPatchWiseDetailsRepository ePatchWiseDetailsRepository;

    @Autowired
    private ForestClearanceEBasicDetailsRepository forestClearanceEBasicDetailsRepository;

    @Autowired
    private ForestClearanceETreeDetailsRepository forestClearanceETreeDetailsRepository;

    @Autowired
    private ForestClearanceEWLSpecificDetailsRepository forestClearanceEWLSpecificDetailsRepository;

    @Autowired
    private ForestClearanceEOtherDetailsRepository forestClearanceEOtherDetailsRepository;

    @Autowired
    private ForestClearanceEDensityOfVegetationRepository eDensityOfVegetationRepository;

    @Autowired
    private ForestClearanceEEnumerationDetailsRepository forestClearanceEEnumerationDetailsRepository;

    @Autowired
    private ForestClearanceEDistrictWiseRepository forestClearanceEDistrictWiseRepository;

    @Autowired
    private ForestClearanceELegalStatusRepository forestClearanceELegalStatusRepository;

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
    private FcFormERepository fcFormERepository;

    @Autowired
    private FcFormEPartBCaLandRepository caLandRepository;

    /*
     * ViolationDetails
     */

    public ResponseEntity<Object> saveFCPartEViolationDetails(List<ForestClearanceEViolationDetails> violationsList,
                                                              Integer FC_FormE_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceEViolationDetails> eViolationDetails;
            ForestClearanceEBasicDetails forestClearanceBBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(FC_FormE_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            eViolationDetails = violationsList.stream().map(value -> {
                // value.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
                value.setForestClearanceEBasicDetails(forestClearanceBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Form E Part B FCPartEViolationDetails form", HttpStatus.OK, null,
                    eViolationDetailsRepository.saveAll(eViolationDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Form E Part B BasicDetails for saveFCPartEViolationDetails id- "
                    + FC_FormE_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartEViolationDetails(Integer vd_id) throws PariveshException {
        try {
            ForestClearanceEViolationDetails temp = eViolationDetailsRepository.findById(vd_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Violation Not Found"));
            temp.set_active(false);
            temp.set_deleted(true);

            return ResponseHandler.generateResponse("FC Form E Part B Violation Deletion", HttpStatus.OK, "",
                    eViolationDetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form E Part B deleteFCPartEViolationDetails id- " + vd_id, e);
        }
    }

    /*
     * Component Details
     */
    public ResponseEntity<Object> saveFCPartEComponentDetails(List<ForestClearanceEComponentDetails> componentList,
                                                              Integer FC_FormE_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceEComponentDetails> eComponentDetails;
            ForestClearanceEBasicDetails forestClearanceEBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(FC_FormE_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Form E Part B not found with ID"));
            eComponentDetails = componentList.stream().map(value -> {
                value.setForestClearanceEBasicDetails(forestClearanceEBasicDetails);
                // value.setForestClearanceBBasicDetails(forestClearanceBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Form E Part B BasicDetails form", HttpStatus.OK, null,
                    eComponentDetailsRepository.saveAll(eComponentDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Form E Part B saveFCPartEComponentDetails id- " + FC_FormE_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartEComponentDetails(Integer cd_id) throws PariveshException {
        try {
            ForestClearanceEComponentDetails temp = eComponentDetailsRepository.findById(cd_id)
                    .orElseThrow(() -> new ProjectNotFoundException("component Not Found"));
            temp.set_active(false);
            temp.set_deleted(true);

            return ResponseHandler.generateResponse("Component Deletion", HttpStatus.OK, "",
                    eComponentDetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form E Part B deleteFCPartEComponentDetails id- " + cd_id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartEAllComponentDetails(Integer FC_FormE_PartB_BasicDetails_Id)
            throws PariveshException {
        try {
            List<ForestClearanceEComponentDetails> eComponentDetails;
            List<ForestClearanceEComponentDetails> temp = eComponentDetailsRepository
                    .findAllByBasicDetailsId(FC_FormE_PartB_BasicDetails_Id);
            eComponentDetails = temp.stream().map(value -> {
                value.set_active(false);
                value.set_deleted(true);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Component Deletion", HttpStatus.OK, "",
                    eComponentDetailsRepository.saveAll(eComponentDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form E Part B deleteFCPartEAllComponentDetails id- "
                    + FC_FormE_PartB_BasicDetails_Id, e);
        }
    }

    /*
     * Afforestation Details
     */

    public ResponseEntity<Object> saveFCPartEAfforestationDetails(ForestClearanceEAfforestationDetails afforestation,
                                                                  Integer FC_FormE_PartB_BasicDetails_Id) throws PariveshException {
        try {

            if (afforestation.getId() == null || afforestation.getId() == 0) {
                ForestClearanceEAfforestationDetails forestClearanceEAfforestationDetails = eAfforestationDetailsRepository
                        .getDataByFcFormEPartBId(FC_FormE_PartB_BasicDetails_Id);

                if (forestClearanceEAfforestationDetails != null) {
                    afforestation.setId(forestClearanceEAfforestationDetails.getId());
                }
            }

            ForestClearanceEBasicDetails forestClearanceEBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(FC_FormE_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Form E Part B not found with ID"));
            afforestation.setForestClearanceEBasicDetails(forestClearanceEBasicDetails);

            return ResponseHandler.generateResponse("Save FC Form E Part B BasicDetails form", HttpStatus.OK, null,
                    eAfforestationDetailsRepository.save(afforestation));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Form E Part B saveFCPartEAfforestationDetails id- " + FC_FormE_PartB_BasicDetails_Id,
                    e);
        }
    }

    public ResponseEntity<Object> deleteFCPartEAfforestationDetails(Integer ad_id) throws PariveshException {
        try {
            ForestClearanceEAfforestationDetails temp = eAfforestationDetailsRepository.findById(ad_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Afforestation Not Found"));
            temp.set_active(false);
            temp.set_deleted(true);
            log.info("INFO ------------ deleteFCPartEAfforestationDetails AFFORESTATION WITH AFFORESTATION ID ---->"
                    + ad_id + " ---- SAVED - SUCCESS");
            return ResponseHandler.generateResponse("Afforestation Deletion", HttpStatus.OK, "",
                    eAfforestationDetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form E Part B deleteFCPartEAfforestationDetails id- " + ad_id,
                    e);
        }
    }

    /*
     * Patch
     */
    public ResponseEntity<Object> saveFCPartEPatch(List<ForestClearanceEPatches> patchList,
                                                   Integer FC_FormE_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceEPatches> ePatches;
            ForestClearanceEBasicDetails forestClearanceEBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(FC_FormE_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            ePatches = patchList.stream().map(value -> {
                value.setForestClearanceEBasicDetails(forestClearanceEBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    ePatchesRepository.saveAll(ePatches));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBPatch id- " + FC_FormE_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartEPatch(Integer p_id) throws PariveshException {
        try {
            ForestClearanceEPatches patch = ePatchesRepository.findById(p_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Patch found with ID"));
            patch.set_active(false);
            patch.set_deleted(true);

            return ResponseHandler.generateResponse("Patch Deletion", HttpStatus.OK, "",
                    ePatchesRepository.save(patch));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBPatch id- " + p_id, e);
        }
    }

    /*
     * Basic Details
     */

    public ResponseEntity<Object> saveFCPartEBasicDetails(ForestClearanceEBasicDetails forestClearanceEBasicDetails,
                                                          HttpServletRequest request) throws PariveshException {
        try {
            ForestClearanceEBasicDetails basicDetails;
            basicDetails = forestClearanceEBasicDetailsRepository.save(forestClearanceEBasicDetails);

			/*
			 * ForestClearance forestTemp = forestClearanceRepository
					.findDetailByFcId(forestClearanceEBasicDetails.getFc_id());
			*/

            FcFormE forestTemp = fcFormERepository
                    .getFcFormEById(forestClearanceEBasicDetails.getFc_id());

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

            return ResponseHandler.generateResponse("Save FC Form E Part B BasicDetails form", HttpStatus.OK, null,
                    basicDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Form E Part B saveFCPartBBasicDetails id- ", e);

        }
    }

    public ResponseEntity<Object> getFCPartEBasicDetails(Integer FC_FormE_PartB_BasicDetails_Id)
            throws PariveshException {
        try {
            ForestClearanceEBasicDetails basicDetails;
            basicDetails = forestClearanceEBasicDetailsRepository.findByIdActive(FC_FormE_PartB_BasicDetails_Id);
            basicDetails.setDepartmentApplication(
                    departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));

            return ResponseHandler.generateResponse("Get FC Form E Part B BasicDetails form", HttpStatus.OK, null,
                    basicDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B getFCPartBBasicDetails id- " + FC_FormE_PartB_BasicDetails_Id, e);
        }
    }

    /*
     * Tree Details
     */
    public ResponseEntity<Object> saveFCPartETreeDetails(List<ForestClearanceETreeDetails> forestClearanceETreeDetails,
                                                         Integer FC_FormE_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceETreeDetails> forestClearanceETreeDetails2;

            ForestClearanceEBasicDetails forestClearanceEBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(FC_FormE_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            forestClearanceETreeDetails2 = forestClearanceETreeDetails.stream().map(value -> {
                value.setForestClearanceEBasicDetails(forestClearanceEBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    forestClearanceETreeDetailsRepository.saveAll(forestClearanceETreeDetails2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBTreeDetails id- " + FC_FormE_PartB_BasicDetails_Id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartETreeDetails(Integer FC_FormE_PartB_Tree_Details_Id)
            throws PariveshException {
        try {
            ForestClearanceETreeDetails forestClearanceETreeDetails;

            forestClearanceETreeDetails = forestClearanceETreeDetailsRepository.findById(FC_FormE_PartB_Tree_Details_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Tree Deatils not found with ID"));

            forestClearanceETreeDetails.setIs_active(false);
            forestClearanceETreeDetails.setIs_deleted(true);
            log.info("INFO ------------ deleteFCPartBTreeDetails WITH TREE DETAILS ID ---->"
                    + FC_FormE_PartB_Tree_Details_Id + " ------ SAVING - SUCCESS");
            return ResponseHandler.generateResponse("Delete FC Part B Tree Details form", HttpStatus.OK, null,
                    forestClearanceETreeDetailsRepository.save(forestClearanceETreeDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Deleting FC Part B deleteFCPartBTreeDetails id- " + FC_FormE_PartB_Tree_Details_Id, e);
        }
    }

    /*
     * WL Details
     */
    public ResponseEntity<Object> saveFCPartEWLSpecificDetails(
            ForestClearanceEWLSpecificDetails forestClearanceEWLSpecificDetails, Integer FC_FormE_PartB_BasicDetails_Id)
            throws PariveshException {
        try {

            if (forestClearanceEWLSpecificDetails.getId() == null || forestClearanceEWLSpecificDetails.getId() == 0) {
                ForestClearanceEWLSpecificDetails forestClearanceEWLSpecificDetails2 = forestClearanceEWLSpecificDetailsRepository
                        .getDataByFcFormEPartBId(FC_FormE_PartB_BasicDetails_Id);
                if (forestClearanceEWLSpecificDetails2 != null) {
                    forestClearanceEWLSpecificDetails.setId(forestClearanceEWLSpecificDetails2.getId());
                }
            }

            ForestClearanceEBasicDetails forestClearanceEBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(FC_FormE_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            forestClearanceEWLSpecificDetails.setForestClearanceEBasicDetails(forestClearanceEBasicDetails);
            log.info("INFO ------------ setForestClearanceBBasicDetails WITH BASIC DETAILS ID ---->"
                    + FC_FormE_PartB_BasicDetails_Id + " ------ SAVING - SUCCESS");
            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    forestClearanceEWLSpecificDetailsRepository.save(forestClearanceEWLSpecificDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBWLSpecificDetails id- " + FC_FormE_PartB_BasicDetails_Id, e);
        }
    }

    /*
     * Other Details
     */

    public ResponseEntity<Object> saveFCPartEOtherDetails(ForestClearanceEOtherDetails forestClearanceEOtherDetails,
                                                          Integer FC_FormE_PartB_BasicDetails_Id, HttpServletRequest request) throws PariveshException {
        try {

            if (forestClearanceEOtherDetails.getId() == null || forestClearanceEOtherDetails.getId() == 0) {
                ForestClearanceEOtherDetails forestClearanceEOtherDetails2 = forestClearanceEOtherDetailsRepository
                        .getDataByFcFormEPartBId(FC_FormE_PartB_BasicDetails_Id);

                if (forestClearanceEOtherDetails2 != null) {
                    forestClearanceEOtherDetails.setId(forestClearanceEOtherDetails2.getId());
                }
            }

            ForestClearanceEBasicDetails forestClearanceEBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(FC_FormE_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            forestClearanceEOtherDetails.setForestClearanceEBasicDetails(forestClearanceEBasicDetails);

            ForestClearance forestTemp = forestClearanceRepository
                    .findDetailByFcId(forestClearanceEBasicDetails.getFc_id());

            DepartmentApplication departmentApplication = departmentApplicationRepository
                    .getDepartmentApplicationByProposalId(FC_FormE_PartB_BasicDetails_Id);
            if (departmentApplication != null) {
                departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
                departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
                departmentApplicationRepository.save(departmentApplication);
            }

            // forestClearanceBOtherDetails.setForestClearanceBPatches(forestClearanceBPatches);

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    forestClearanceEOtherDetailsRepository.save(forestClearanceEOtherDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBOtherDetails id- " + FC_FormE_PartB_BasicDetails_Id, e);
        }
    }

    /*
     * Density of Vegetation
     */
    public ResponseEntity<Object> saveFCPartEDensityOfVegetation(
            List<ForestClearanceEDensityOfVegetation> forestClearanceEDensityOfVegetations,
            Integer FC_FormE_PartB_BasicDetails_Id) throws PariveshException {
        try {
            List<ForestClearanceEDensityOfVegetation> forestClearanceEDensityOfVegetations2;

            ForestClearanceEBasicDetails forestClearanceEBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(FC_FormE_PartB_BasicDetails_Id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));

            forestClearanceEDensityOfVegetations2 = forestClearanceEDensityOfVegetations.stream().map(value -> {
                value.setForestClearanceEBasicDetails(forestClearanceEBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC Part B BasicDetails form", HttpStatus.OK, null,
                    eDensityOfVegetationRepository.saveAll(forestClearanceEDensityOfVegetations2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in Saving FC Part B saveFCPartBDensityOfVegetation id- " + FC_FormE_PartB_BasicDetails_Id,
                    e);
        }
    }

    public ResponseEntity<Object> deleteFCPartEDensityOfVegetation(Integer FC_FormE_PartB_density_of_vegetation_id)
            throws PariveshException {
        try {
            ForestClearanceEDensityOfVegetation forestClearanceEDensityOfVegetation;

            forestClearanceEDensityOfVegetation = eDensityOfVegetationRepository
                    .findById(FC_FormE_PartB_density_of_vegetation_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Vegetation Details not found with ID"));

            forestClearanceEDensityOfVegetation.setIs_active(false);
            forestClearanceEDensityOfVegetation.setIs_deleted(true);

            return ResponseHandler.generateResponse("Delete FC Part B density of vegetation form", HttpStatus.OK, null,
                    eDensityOfVegetationRepository.save(forestClearanceEDensityOfVegetation));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBDensityOfVegetation id- "
                    + FC_FormE_PartB_density_of_vegetation_id, e);
        }
    }

    /*
     * Patch Wise Details
     */
    public ResponseEntity<Object> saveFCPartEPatchWiseDetails(
            List<ForestClearanceEPatchWiseDetails> forestClearanceEPatchWiseDetails, Integer patch_id)
            throws PariveshException {
        try {
            List<ForestClearanceEPatchWiseDetails> forestClearanceEPatchWiseDetails2;
            ForestClearanceEPatches patches = ePatchesRepository.getById(patch_id);

            forestClearanceEPatchWiseDetails2 = forestClearanceEPatchWiseDetails.stream().map(value -> {
                value.setForestClearanceEPatches(patches);
                return value;
            }).collect(Collectors.toList());
            return ResponseHandler.generateResponse("Save patch wise details with patch id -------> " + patch_id,
                    HttpStatus.OK, null, ePatchWiseDetailsRepository.saveAll(forestClearanceEPatchWiseDetails2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B saveFCPartBPatchWiseDetails id- " + patch_id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartEPatchWiseDetails(Integer patch_id) throws PariveshException {
        try {
            List<ForestClearanceEPatchWiseDetails> ePatchWiseDetails;
            List<ForestClearanceEPatchWiseDetails> temp = ePatchWiseDetailsRepository.findAllByPatchId(patch_id);
            ePatchWiseDetails = temp.stream().map(value -> {
                value.set_active(false);
                value.set_deleted(true);
                return value;
            }).collect(Collectors.toList());

            log.info("INFO ------------ deleteFCPartBPatchWiseDetails WITH patch id ---->" + patch_id
                    + " ---- DELETING");
            return ResponseHandler.generateResponse("Component Deletion", HttpStatus.OK, "",
                    ePatchWiseDetailsRepository.saveAll(ePatchWiseDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Part B deleteFCPartBPatchWiseDetails id- " + patch_id, e);
        }
    }

    public ResponseEntity<Object> deleteFCPartEEnumerationDetails(Integer id) throws ProjectNotFoundException {
        try {
            ForestClearanceEEnumerationDetails forestClearanceEEnumerationDetails = forestClearanceEEnumerationDetailsRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B Enumeration Details not found with ID"));
            forestClearanceEEnumerationDetails.setIs_active(false);
            forestClearanceEEnumerationDetails.setIs_deleted(true);
            forestClearanceEEnumerationDetailsRepository.save(forestClearanceEEnumerationDetails);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    forestClearanceEEnumerationDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new ProjectNotFoundException("Project Not found for id- " + id);
        }
    }

    public ResponseEntity<Object> deleteFCPartEDistrictwise(Integer id) throws ProjectNotFoundException {
        try {
            ForestClearanceEDistrictWise forestClearanceEDistrictWise = forestClearanceEDistrictWiseRepository
                    .findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B DistrictWise not found with ID"));
            forestClearanceEDistrictWise.setIs_active(false);
            forestClearanceEDistrictWise.setIs_deleted(true);
            forestClearanceEDistrictWiseRepository.save(forestClearanceEDistrictWise);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    forestClearanceEDistrictWise);
        } catch (Exception e) {
            log.error(e.toString() + "" + e.getStackTrace()[0]);
            throw new ProjectNotFoundException("Project Not found for id- " + id);
        }
    }

    public ResponseEntity<Object> deleteFCPartELegalStatus(Integer id) throws ProjectNotFoundException {
        try {
            ForestClearanceELegalStatus forestClearanceELegalStatus = forestClearanceELegalStatusRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B LegalStatus not found with ID"));
            forestClearanceELegalStatus.setIs_active(false);
            forestClearanceELegalStatus.setIs_deleted(true);
            forestClearanceELegalStatusRepository.save(forestClearanceELegalStatus);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    forestClearanceELegalStatus);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Part B saveEnumerationDetails id- " + e);
        }
    }

    public ResponseEntity<Object> saveFcFormBPartBDistrictWise(
            List<ForestClearanceEDistrictWise> fcFormEPartBDistrictWises, Integer fc_form_e_part_b_basic_details_id) {
        try {
            List<ForestClearanceEDistrictWise> eDistrictWise;
            ForestClearanceEBasicDetails fcFormEPartBBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(fc_form_e_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            eDistrictWise = fcFormEPartBDistrictWises.stream().map(value -> {
                value.setFcFormEPartBBasicDetails(fcFormEPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC B Part B District Wise form", HttpStatus.OK, null,
                    forestClearanceEDistrictWiseRepository.saveAll(eDistrictWise));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC B Part B District Wise saveFcFormBPartBDistrictWise id- "
                    + fc_form_e_part_b_basic_details_id, e);
        }
    }

    public ResponseEntity<Object> deleteFcFormEPartBDistrictwise(Integer districtWise_id) {

        try {
            ForestClearanceEDistrictWise fcFormEPartBDistrictWise = forestClearanceEDistrictWiseRepository
                    .findById(districtWise_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B DistrictWise not found with ID"));
            fcFormEPartBDistrictWise.setIs_active(false);
            fcFormEPartBDistrictWise.setIs_deleted(true);
            forestClearanceEDistrictWiseRepository.save(fcFormEPartBDistrictWise);
            return ResponseHandler.generateResponse("Deleted successfully", HttpStatus.OK, "NO ERROR",
                    fcFormEPartBDistrictWise);
        } catch (Exception e) {
            log.error(e.toString() + "" + e.getStackTrace()[0]);
            throw new ProjectNotFoundException("Project Not found for id- " + districtWise_id);
        }
    }

    public String generateSequenceNumber(int maxcount) {
        AtomicInteger sequence = new AtomicInteger(maxcount);
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, ForestClearance forestClearanceForm) {

        String proposalId = "FP/" + stateRepository.getStateByCode(forestClearanceForm.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/" + forestClearanceForm.getProject_activity_id() + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());
        return proposalId;
    }

    public ResponseEntity<Object> saveFCFormEPartBCaLand(List<FcFormEPartBCaLand> caLandList,
                                                         Integer fc_form_e_part_b_basic_details_id) throws PariveshException {
        try {
            List<FcFormEPartBCaLand> caLands;
            ForestClearanceEBasicDetails fcFormEPartBBasicDetails = forestClearanceEBasicDetailsRepository
                    .findById(fc_form_e_part_b_basic_details_id)
                    .orElseThrow(() -> new ProjectNotFoundException("FC Part B not found with ID"));
            caLands = caLandList.stream().map(value -> {
                value.setFcFormEPartBBasicDetails(fcFormEPartBBasicDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("Save FC From E Part B CA Land Details form", HttpStatus.OK, null,
                    caLandRepository.saveAll(caLands));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC From E Part B saveFCFormCPartBCaLand id- " + fc_form_e_part_b_basic_details_id,
                    e);
        }
    }


    public ResponseEntity<Object> deleteFCFormEPartBCaLand(Integer ca_id) throws PariveshException {
        try {
            FcFormEPartBCaLand caLand = caLandRepository.findById(ca_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ca Land found with ID"));
            caLand.set_active(false);
            caLand.set_deleted(true);

            return ResponseHandler.generateResponse("Ca Land Deletion", HttpStatus.OK, "",
                    caLandRepository.save(caLand));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting FC Form E Part B deleteFCFormCPartBCaLand id- " + ca_id, e);
        }
    }
}
