package com.backend.service.EcSVReport;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.EcSVReport.EcSiteVisitCommittee;
import com.backend.model.EcSVReport.EcSiteVisitReport;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.model.Crz.CrzBasicDetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.EcForm10Repository.EcForm10BasicInformationRepository;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.repository.postgres.EcSVReport.EcSVRCommitteeRepository;
import com.backend.repository.postgres.EcSVReport.EcSVReportRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EcSiteVisitReportService {
	@Autowired
	private EcForm10BasicInformationRepository ecForm10BasicInformationRepository;
	@Autowired
	private EnvironmentClearenceRepository environmentClearenceRepository;

	@Autowired
	private EcSVReportRepository ecSVReportRepository;

	@Autowired
	private EcSVRCommitteeRepository ecSVRCommitteeRepository;

	@Autowired
	private EcPartCRepository ecPartCRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	public EcSiteVisitReport saveEcSVReport(EcSiteVisitReport ecSiteVisitForm, Integer caf_id)
			throws PariveshException {
		try {
			EcSiteVisitReport temp = ecSVReportRepository.save(ecSiteVisitForm);

			Integer proposalId = null;
			String proposalNo = null;
			if (ecSiteVisitForm.getCrz_id() != null) {
				CrzBasicDetails crzBasicDetails = ecPartCRepository.getBasicDetails(ecSiteVisitForm.getCrz_id());
				proposalId = crzBasicDetails.getId();
				proposalNo= crzBasicDetails.getProposal_no();
			} else {
				EcPartC environmentTemp = ecPartCRepository.getFormPartC(ecSiteVisitForm.getEc_id());
				if (environmentTemp != null) {
					proposalId = environmentTemp.getId();
					proposalNo = environmentTemp.getProposal_no();
				} else {
					EnvironmentClearence environmentClearence = environmentClearenceRepository
							.findById(ecSiteVisitForm.getEc_id()).orElseThrow(() -> new ProjectNotFoundException(
									"Ec Form not found id-" + ecSiteVisitForm.getEc_id()));
					proposalId = environmentClearence.getId();
					proposalNo = environmentClearence.getProposal_no();
				}
			}

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getApplicationByProposalId(proposalId);

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

	public EcSiteVisitReport getEcSVReport(Integer ecSVRid) throws PariveshException {

		try {
			EcSiteVisitReport temp = ecSVReportRepository.getDetailsById(ecSVRid)
					.orElseThrow(() -> new ProjectNotFoundException("Ec Site Visit Report Form not found for id"));

			temp.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(ecSVRid));

			return temp;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting getEcSIRForm- " + ecSVRid, e);
		}
	}

	public EcSiteVisitCommittee saveEcSVCommittee(EcSiteVisitCommittee ecSVRCommittee) {

		EcSiteVisitCommittee temp = ecSVRCommitteeRepository.save(ecSVRCommittee);

		return temp;
	}

	public EcSiteVisitCommittee getEcSVCommittee(Integer id) throws PariveshException {

		EcSiteVisitCommittee temp = ecSVRCommitteeRepository.findById(id)
				.orElseThrow(() -> new ProjectNotFoundException("Ec Site Visit Report not found for id" + id));

		return temp;
	}

	public String deleteEcSVCommittee(Integer id) throws PariveshException {

		try {

			Integer upadate = ecSVRCommitteeRepository.ecSVRCommittee(id);

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
