package com.backend.service.FcFactsheet;

import com.backend.constant.AppConstant;
import com.backend.dto.DfoDataDto;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.FcFactsheet.FcFactsheet;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.FcFactsheet.FcFactsheetRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.ForestClearancePartB.ForestClearancePartBService;
import com.backend.service.ProponentApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FcFactsheetService {
    @Autowired
    private FcFactsheetRepository factsheetRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;


    public ResponseEntity<Object> saveFactsheet(FcFactsheet factsheet, Integer clearanceId) {
        try {
            if (factsheet.getId() == null || factsheet.getId() == 0) {
                FcFactsheet temp = factsheetRepository.findByApplicationId(factsheet.getApplicationId()).orElse(null);
                if (temp != null) {
                    factsheet.setId(temp.getId());
                }
            }

            FcFactsheet response = factsheetRepository.save(factsheet);

            ProponentApplications proponentApplications = proponentApplicationRepository.findById(factsheet.getApplicationId())
                    .orElseThrow(() -> new ProjectNotFoundException("proponent application not found with id:" + factsheet.getApplicationId()));

            if (departmentApplicationRepository
                    .getDepartmentApplicationByProposalId(response.getId()) == null) {
                DepartmentApplication applications = new DepartmentApplication();
                Applications app = applicationsRepository.findById(clearanceId).get();
                applications.setApplications(app);
                applications.setProponentApplications(proponentApplications);
                applications.setProposal_sequence(proponentApplications.getProposal_sequence());
                applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
                applications.setProposal_id(response.getId());
                setStatus(response.getStatus(), applications);

                departmentApplicationRepository.save(applications);
            } else {
                DepartmentApplication application = departmentApplicationRepository
                        .getDepartmentApplicationByProposalId(response.getId());
                setStatus(response.getStatus(), application);
                departmentApplicationRepository.save(application);
            }

            return ResponseHandler.generateResponse("saving fc factsheet", HttpStatus.OK, "", response);

        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("error in saving factsheet", e);
        }
    }

    public ResponseEntity<Object> getFactsheet(Integer applicationId) {
        try {
            FcFactsheet response = factsheetRepository.findByApplicationId(applicationId)
                    .orElseThrow(() -> new ProjectNotFoundException("factsheet not found for application id:" + applicationId));

            response.setDepartmentApplication(departmentApplicationRepository
                    .getDepartmentApplicationByProposalId(response.getId()));

            return ResponseHandler.generateResponse("getting fc factsheet", HttpStatus.OK, "", response);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("error in getting fc factsheet", e);
        }
    }

    public void setStatus(String status, DepartmentApplication applications) {
        if (status != null && status.equalsIgnoreCase("submitted")) {
            applications.setStatus(AppConstant.Caf_Status.SUBMITTED.toString());
        } else {
            applications.setStatus(AppConstant.Caf_Status.DRAFT.toString());
        }
    }

    public ResponseEntity<Object> getDfoData(Integer applicationId) {
        try {
            DfoDataDto response = new DfoDataDto();

            Integer length= proponentApplicationRepository.getApplicationDivision(applicationId).size();
            if (length>1){
                response.setIsMultiDivision(true);
            } else if (length==1) {
                response.setIsMultiDivision(false);
            }

            response.setClearanceId(factsheetRepository.getClearance(applicationId));
            if (response.getClearanceId() == 1) {
                response.setPart2Id(factsheetRepository.getFormADfoPart2Id(applicationId).orElse(null));
            } else if (response.getClearanceId()==8) {
                response.setPart2Id(factsheetRepository.getFormBDfoPart2Id(applicationId).orElse(null));;
            } else if (response.getClearanceId()==7) {
                response.setPart2Id(factsheetRepository.getFormCDfoPart2Id(applicationId).orElse(null));;
            } else if (response.getClearanceId()==9) {
                response.setPart2Id(factsheetRepository.getFormDDfoPart2Id(applicationId).orElse(null));;
            } else if (response.getClearanceId()==12) {
                response.setPart2Id(factsheetRepository.getFormEDfoPart2Id(applicationId).orElse(null));;
            }

            return ResponseHandler.generateResponse("getting data",HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in gettin data",e);
        }
    }
}
