package com.backend.service.FcSIR;

import com.backend.constant.AppConstant;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.FcSIR.FcSIRLegalStatusDetails;
import com.backend.model.FcSIR.FcSIROtherDetails;
import com.backend.model.FcSIR.FcSiteInspectionReport;
import com.backend.model.FcSIR.FcSiteVisitDetails;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.FcSIR.FcSIRLegalStatusDetailsRepository;
import com.backend.repository.postgres.FcSIR.FcSIROtherDetailsRepository;
import com.backend.repository.postgres.FcSIR.FcSiteInspectionReportRepository;
import com.backend.repository.postgres.FcSIR.FcSiteVisitDetailsRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FcSIRService {

    @Autowired
    private FcSiteInspectionReportRepository siteInspectionReportRepository;

    @Autowired
    private FcSiteVisitDetailsRepository siteVisitDetailsRepository;

    @Autowired
    private FcSIRLegalStatusDetailsRepository legalStatusDetailsRepository;

    @Autowired
    private FcSIROtherDetailsRepository otherDetailsRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    public ResponseEntity saveSIRDetails(FcSiteInspectionReport siteInspectionReport,Integer clearanceId){
        try{
            if (siteInspectionReport.getId()==null || siteInspectionReport.getId()==0){
                FcSiteInspectionReport temp= siteInspectionReportRepository.getDetailsByFcId(siteInspectionReport.getFcId()).orElse(null);
                if (temp!=null){
                    siteInspectionReport.setId(temp.getId());
                }
            }

            FcSiteInspectionReport response= siteInspectionReportRepository.save(siteInspectionReport);

            ProponentApplications proponentApplications= proponentApplicationRepository.getApplicationByProposalId_7(siteInspectionReport.getFcId())
                    .orElseThrow(() -> new ProjectNotFoundException("proponent application not found with fcId:"+siteInspectionReport.getFcId()));

            if (departmentApplicationRepository
                    .getDepartmentApplicationByProposalId(response.getId()) == null) {
                DepartmentApplication applications = new DepartmentApplication();
                Applications app = applicationsRepository.findById(clearanceId).get();
                applications.setApplications(app);
                applications.setProponentApplications(proponentApplications);
                applications.setProposal_sequence(proponentApplications.getProposal_sequence());
                applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
                applications.setProposal_id(response.getId());
                applications.setStatus(AppConstant.Caf_Status.DRAFT.toString());

                departmentApplicationRepository.save(applications);
            }

            return ResponseHandler.generateResponse("saving sir details", HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in saving sir details",e);
        }
    }

    public ResponseEntity<Object> getSirDetails(Integer sirId){
        try{
            FcSiteInspectionReport response= siteInspectionReportRepository.findById(sirId)
                    .orElseThrow(() -> new ProjectNotFoundException("sir details not found with Id:"+sirId));
            response.setDepartmentApplication(departmentApplicationRepository.getDepartmentApplicationByProposalId(sirId));

            return ResponseHandler.generateResponse("getting sir details",HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in getting sir details",e);
        }
    }

    public ResponseEntity<Object> saveSiteVisitDetails(List<FcSiteVisitDetails> siteVisitDetails,Integer sirId){
        try {
            List<FcSiteVisitDetails> response= new ArrayList<FcSiteVisitDetails>();
            FcSiteInspectionReport sir= siteInspectionReportRepository.findById(sirId)
                    .orElseThrow(() -> new ProjectNotFoundException("sir details not found with Id:"+sirId));
            response=siteVisitDetails.stream().map(value ->{
                value.setFcSiteInspectionReport(sir);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saving site visit details list", HttpStatus.OK,"",
                    siteVisitDetailsRepository.saveAll(response));

        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in saving site visit details details",e);
        }
    }

    public ResponseEntity<Object> deleteSiteVisitDetails(Integer id){
        try {
            FcSiteVisitDetails temp= siteVisitDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("site visit details not found with Id:"+id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);
            siteVisitDetailsRepository.save(temp);

            return ResponseHandler.generateResponse("deleting site visit details",HttpStatus.OK,"", null);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in deleting site visit details details",e);
        }
    }

    public ResponseEntity<Object> saveLegalStatusDetails(FcSIRLegalStatusDetails legalStatusDetails, Integer sirId){
        try{
            if (legalStatusDetails.getId()==null || legalStatusDetails.getId()==0){
                FcSIRLegalStatusDetails temp= legalStatusDetailsRepository.getDetailsBySirId(sirId);
                if (temp!=null){
                    legalStatusDetails.setId(temp.getId());
                }
            }

            FcSiteInspectionReport sir= siteInspectionReportRepository.findById(sirId)
                    .orElseThrow(() -> new ProjectNotFoundException("sir details not found with Id:"+sirId));
            legalStatusDetails.setFcSiteInspectionReport(sir);

            return ResponseHandler.generateResponse("saving legal status details",HttpStatus.OK,"",
                    legalStatusDetailsRepository.save(legalStatusDetails));

        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in saving legal status details",e);
        }
    }

    public ResponseEntity<Object> deleteLegalStatusDetails(Integer id){
        try {
            FcSIRLegalStatusDetails temp= legalStatusDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("legal status details not found with Id:"+id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);
            legalStatusDetailsRepository.save(temp);

            return ResponseHandler.generateResponse("deleting legal status details",HttpStatus.OK,"", null);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in deleting legal status details details",e);
        }
    }

    public ResponseEntity<Object> saveOtherDetails(FcSIROtherDetails otherDetails,Integer sirId){
        try {
            if (otherDetails.getId()==null || otherDetails.getId()==0){
                FcSIROtherDetails temp= otherDetailsRepository.getDetailsBySirId(sirId);
                if (temp!=null){
                    otherDetails.setId(temp.getId());
                }
            }

            FcSiteInspectionReport sir= siteInspectionReportRepository.findById(sirId)
                    .orElseThrow(() -> new ProjectNotFoundException("sir details not found with Id:"+sirId));
            otherDetails.setFcSiteInspectionReport(sir);

            DepartmentApplication departmentApplication = departmentApplicationRepository.getApplicationByProposalId2(sirId)
                    .orElseThrow(() -> new ProjectNotFoundException("department application not found with Id:"+sirId));
            departmentApplication.setStatus(AppConstant.Caf_Status.SUBMITTED.toString());
            departmentApplicationRepository.save(departmentApplication);

            return ResponseHandler.generateResponse("saving other details",HttpStatus.OK,"",
                    otherDetailsRepository.save(otherDetails));
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in saving other details",e);
        }
    }

    public ResponseEntity<Object> deleteOtherDetails(Integer id){
        try {
            FcSIROtherDetails temp= otherDetailsRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("other details not found with Id:"+id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);
            otherDetailsRepository.save(temp);

            return ResponseHandler.generateResponse("deleting other  details",HttpStatus.OK,"", null);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in deleting other details details",e);
        }
    }


}
