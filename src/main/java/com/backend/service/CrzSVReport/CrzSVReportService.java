package com.backend.service.CrzSVReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CrzSVReport;
import com.backend.model.CrzSiteVisitCommittee;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.CrzSVReport.CrzSVRCommitteeRepository;
import com.backend.repository.postgres.CrzSVReport.CrzSVRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CrzSVReportService {

    @Autowired
    private CrzSVRepository crzSVReportRepository;

    @Autowired
    private CrzSVRCommitteeRepository crzSVRCommitteeRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    public CrzSVReport saveCrzSVReport(CrzSVReport crzSiteVisitForm, Integer caf_id) throws PariveshException {
        try {
            CrzSVReport temp = crzSVReportRepository.save(crzSiteVisitForm);

            Integer proposalId = null;
            String proposalNo = null;

//            EcPartC environmentTemp = ecPartCRepository.getFormPartC(ecSiteVisitForm.getEc_id());
//            if (environmentTemp != null) {
//                proposalId = environmentTemp.getId();
//                proposalNo = environmentTemp.getProposal_no();
//            } else {
//                EnvironmentClearence environmentClearence = environmentClearenceRepository.findById(ecSiteVisitForm.getEc_id()).orElseThrow(() -> new ProjectNotFoundException("Ec Form not found id-" + ecSiteVisitForm.getEc_id()));
//                proposalId = environmentClearence.getId();
//                proposalNo = environmentClearence.getProposal_no();
//            }

            ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalId(proposalId);

            if (departmentApplicationRepository.getDepartmentApplicationByProposalId(temp.getId()) == null) {
                DepartmentApplication applications = new DepartmentApplication();
                Applications app = applicationsRepository.findById(6).get();
                applications.setApplications(app);
                applications.setProponentApplications(proponentApplications);
                applications.setProposal_sequence(proponentApplications.getProposal_sequence());
                applications.setProposal_no(proposalNo.replaceAll("\\s", ""));
                applications.setProposal_id(temp.getId());
                applications.setStatus(Caf_Status.DRAFT.toString());

                departmentApplicationRepository.save(applications);
            }

            return temp;

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EcSVRForm ", e);
        }

    }

    public CrzSVReport getCrzSVReport(Integer crzSVRid) throws PariveshException {

        try {
            CrzSVReport temp = crzSVReportRepository.getDetailsById(crzSVRid).orElseThrow(() -> new ProjectNotFoundException("Crz Site Visit Report Form not found for id"));

            temp.setDepartmentApplication(departmentApplicationRepository.getDepartmentApplicationByProposalId(crzSVRid));

            return temp;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting getEcSIRForm- " + crzSVRid, e);
        }
    }

    public CrzSiteVisitCommittee saveCrzSVCommittee(CrzSiteVisitCommittee crzSVRCommittee) {

        CrzSiteVisitCommittee temp = crzSVRCommitteeRepository.save(crzSVRCommittee);

        return temp;
    }

    public CrzSiteVisitCommittee getCrzSVCommittee(Integer id) throws PariveshException {

        CrzSiteVisitCommittee temp = crzSVRCommitteeRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Crz Site Visit Report not found for id" + id));

        return temp;
    }

    public String deleteCrzSVCommittee(Integer id) throws PariveshException {

        try {

            Integer upadate = crzSVRCommitteeRepository.crzSVRCommittee(id);

            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return "Deleted";

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting Public Hearing data Id-" + id, e);
        }
    }

}

