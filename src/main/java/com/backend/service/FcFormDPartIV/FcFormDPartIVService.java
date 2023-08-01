package com.backend.service.FcFormDPartIV;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.FcFormCPartIV.FcFormCPartIVBasicDetails;
import com.backend.model.FormDPartIV.FcFormDPartIVBasicDetails;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ForestClearanceFormDPartIV.FcFormDPartIVBasicDetailsRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

@Slf4j
@Service
@Transactional
public class FcFormDPartIVService {
    @Autowired
    private FcFormDPartIVBasicDetailsRepository fcFormDPartIVBasicDetailsRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    // Save Basic Details
    public ResponseEntity<Object> saveFCformDPartIVBasicDetails(FcFormDPartIVBasicDetails fcFormDPartIVBasicDetails,
                                                                Integer clearenceID, HttpServletRequest request) {
        try {
            FcFormDPartIVBasicDetails returnBasicDetails;
            returnBasicDetails = fcFormDPartIVBasicDetailsRepository.save(fcFormDPartIVBasicDetails);

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(fcFormDPartIVBasicDetails.getFc_id());

            if (departmentApplicationRepository
                    .getDepartmentApplicationByProposalId(returnBasicDetails.getId()) == null) {
                DepartmentApplication applications = new DepartmentApplication();
                Applications app = applicationsRepository.findById(clearenceID).get();
                applications.setApplications(app);
                applications.setProponentApplications(proponentApplications);
                applications.setProposal_sequence(proponentApplications.getProposal_sequence());
                applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
                applications.setProposal_id(returnBasicDetails.getId());
                applications.setStatus(Caf_Status.SUBMITTED.toString());
                applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

                departmentApplicationRepository.save(applications);
            }
            return ResponseHandler.generateResponse("Save FC Form D Part IV BasicDetails form", HttpStatus.OK, null,
                    returnBasicDetails);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving FC Form D Part IV saveFCPartBBasicDetails id- ", e);
        }

    }// end of Save basic details

    // Get Basic Details
    public ResponseEntity<Object> getFCformDPartIVBasicDetails(Integer fcFormDPartIVBasicDetailsId) {
        try {
            FcFormDPartIVBasicDetails basicDetails;
            basicDetails = fcFormDPartIVBasicDetailsRepository.findByIdActive(fcFormDPartIVBasicDetailsId);
            basicDetails.setDepartmentApplication(
                    departmentApplicationRepository.getDepartmentApplicationByProposalId(basicDetails.getId()));

            return ResponseHandler.generateResponse("Fetched FC Form C Part IV BasicDetails form", HttpStatus.OK, null,
                    basicDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in fetching FC Form D Part IV getFCPartBBasicDetails id- " + fcFormDPartIVBasicDetailsId, e);
        }
    } // end of get basic details
}
