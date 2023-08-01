package com.backend.service.FcFormAPartBDc;

import java.util.ArrayList;
import java.util.List;

import com.backend.model.ForestClearanceFormB.FcFormBProjectDetails;
import com.backend.model.ForestClearanceFormC.FcFormC;
import com.backend.model.ForestClearanceFormD.FcFormD;
import com.backend.repository.postgres.FcFormB.FcFormBProjectDetailsRepository;
import com.backend.repository.postgres.ForestClearanceFormC.FcFormCRepository;
import com.backend.repository.postgres.ForestClearanceFormD.FcFormDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ForestClearance;
import com.backend.model.ProponentApplications;
import com.backend.model.FcFormAPartBDc.FcFormAPartBDC;
import com.backend.model.FcFormAPartBDc.FcFormAPartBDCUndertaking;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.FcFormAPartBDc.FcFormAPartBDCRepository;
import com.backend.repository.postgres.FcFormAPartBDc.FcFormAPartBDCUndertakingRepository;
import com.backend.repository.postgres.FcFormBPartB.FcFormBPartBOtherDetailsRepository;
import com.backend.repository.postgres.ForestClearanceFormCPartB.FcFormCPartBOtherDetailsRepository;
import com.backend.repository.postgres.ForestClearancePartB.ForestClearanceBOtherDetailsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class FcFormAPartBDCService {

	@Autowired
	private FcFormAPartBDCRepository fcFormAPartBDCRepository;

	@Autowired
	private FcFormDRepository fcFormDRepository;

	@Autowired
	private FcFormAPartBDCUndertakingRepository fcFormAPartBDCUndertakingRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ForestClearanceRepository forestClearanceRepository;
	
	@Autowired
	private FcFormBProjectDetailsRepository fcFormBProjectDetailsRepository;
	
	@Autowired
	private FcFormCRepository fcFormCRepository;

	@Autowired
	private ForestClearanceBOtherDetailsRepository fcFormAPartBOtherDetailsRepository;

	@Autowired
	private FcFormBPartBOtherDetailsRepository fcFormBPartBOtherDetailsRepository;

	@Autowired
	private FcFormCPartBOtherDetailsRepository fcFormCPartBOtherDetailsRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	public FcFormAPartBDC savefcFormAPartBDC(FcFormAPartBDC fcFormAPartBDC,Integer clearanceId) throws PariveshException {
		try {
			if (fcFormAPartBDC.getId()==null || fcFormAPartBDC.getId()==0){
				FcFormAPartBDC temp= fcFormAPartBDCRepository.getDetailsByFcId(fcFormAPartBDC.getFc_id()).orElse(null);
				if (temp!=null){
					fcFormAPartBDC.setId(temp.getId());
				}
			}

			FcFormAPartBDC fcFormAPartBDC2 = fcFormAPartBDCRepository.save(fcFormAPartBDC);
			ProponentApplications proponentApplications=null;

			if (departmentApplicationRepository.getDepartmentApplicationByProposalId(fcFormAPartBDC2.getId()) == null) {
				DepartmentApplication applications = new DepartmentApplication();
				Applications app = applicationsRepository.findById(clearanceId).get();
				applications.setApplications(app);

				if (clearanceId==23){
					ForestClearance forestTemp = forestClearanceRepository.findDetailByFcId(fcFormAPartBDC.getFc_id());
					proponentApplications = proponentApplicationRepository
							.getApplicationByProposalId(forestTemp.getId());
				    applications.setProponentApplications(proponentApplications);
					applications.setProposal_sequence(proponentApplications.getProposal_sequence());
					applications.setProposal_no(forestTemp.getProposal_no().replaceAll("\\s", ""));
				} else if (clearanceId==24){
					FcFormBProjectDetails forestTemp = fcFormBProjectDetailsRepository.getDetailsByFcId2(fcFormAPartBDC.getFc_id());
					proponentApplications = proponentApplicationRepository
							.getApplicationByProposalId(forestTemp.getId());
					applications.setProponentApplications(proponentApplications);
					applications.setProposal_sequence(proponentApplications.getProposal_sequence());
					applications.setProposal_no(forestTemp.getProposal_no().replaceAll("\\s", ""));
				} else if (clearanceId==25) {
					FcFormC forestTemp= fcFormCRepository.getDetailsByFormCId(fcFormAPartBDC.getFc_id());
					proponentApplications = proponentApplicationRepository
							.getApplicationByProposalId(forestTemp.getId());
					applications.setProponentApplications(proponentApplications);
					applications.setProposal_sequence(proponentApplications.getProposal_sequence());
					applications.setProposal_no(forestTemp.getProposal_no().replaceAll("\\s", ""));
				}else if (clearanceId==43) {
					FcFormD forestTemp=fcFormDRepository.getFormDDetailsById(fcFormAPartBDC.getFc_id());
					proponentApplications = proponentApplicationRepository
							.getApplicationByProposalId(forestTemp.getId());
					applications.setProponentApplications(proponentApplications);
					applications.setProposal_sequence(proponentApplications.getProposal_sequence());
					applications.setProposal_no(forestTemp.getProposal_no().replaceAll("\\s", ""));
				}

				applications.setProposal_id(fcFormAPartBDC2.getId());
				applications.setStatus(Caf_Status.DRAFT.toString());

				departmentApplicationRepository.save(applications);
			}

			return fcFormAPartBDC2;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Fc FormA PartB DC ", e);
		}
	}


	public FcFormAPartBDC getfcFormAPartBDC(Integer id) {

		try {
			FcFormAPartBDC fcFormAPartBDC = fcFormAPartBDCRepository.getDetailsById(id)
					.orElseThrow(() -> new ProjectNotFoundException("Fc FormA PartB DC not found for id"));

			fcFormAPartBDC.setDepartmentApplication(
					departmentApplicationRepository.getDepartmentApplicationByProposalId(fcFormAPartBDC.getId()));

			return fcFormAPartBDC;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting Fc FormA PartB DC- " + id, e);
		}
	}

	public ResponseEntity<Object> saveUndertaking(FcFormAPartBDCUndertaking fcFormAPartBDCUndertaking,
			Integer fcFormAPartBDCId) {
		try {

			FcFormAPartBDC fcFormAPartBDC = fcFormAPartBDCRepository.findById(fcFormAPartBDCId)
					.orElseThrow(() -> new ProjectNotFoundException("Fc FormA PartB DC form not found"));

			fcFormAPartBDCUndertaking.setFcFormAPartBDC(fcFormAPartBDC);

			DepartmentApplication departmentApplication = departmentApplicationRepository
					.getDepartmentApplicationByProposalId(fcFormAPartBDC.getId());

			if (departmentApplication != null) {
				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
				departmentApplicationRepository.save(departmentApplication);
			}

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving Fc FormA PartB DC Undertaking details form id-" + fcFormAPartBDCId, e);
		}
		return ResponseHandler.generateResponse("Save Ec Undertaking details form", HttpStatus.OK, null,
				fcFormAPartBDCUndertakingRepository.save(fcFormAPartBDCUndertaking));
	}

	public ResponseEntity<Object> getSiteInspectionReport(String proposal_no) {
		try {
			List<DepartmentApplication> departmentApplications = departmentApplicationRepository
					.getApplicationsByProposalNo(proposal_no);
			System.out.println("Report...");
			List<Object[]> docData=new ArrayList<Object[]>();
			for (DepartmentApplication departmentApplication : departmentApplications) {
				String abbr = departmentApplication.getApplications().getAbbr();
				Integer Id = departmentApplication.getProposal_id();
				Object[] doc=new Object[1];

				if (abbr.equals("FC_FORM_A")) {
					doc = fcFormAPartBOtherDetailsRepository.getDocuments(Id);
					log.info("--------------->For Clearance ID Form FC A Part B" + doc);
				} else if (abbr == "FC_FORM_B") {
					doc = fcFormBPartBOtherDetailsRepository.getDocuments(Id);
					log.info("--------------->For Clearance ID Form FC B Part B" + doc);
				} else if (abbr.equals("FC_FORM_C")) {
					doc = fcFormCPartBOtherDetailsRepository.getDocuments(Id);
					log.info("--------------->For Clearance ID Form FC C Part B" + doc);
				} else {
					log.info("--------------->No Clearance ID ");
				}
				docData.add(doc);
			}
			return ResponseHandler.generateResponse("Get Site Inspection Report ", HttpStatus.OK, null, docData);
		} catch (Exception ex) {
			log.error("=======================>>>>>>>>>>>" + ex.toString() + " " + ex.getStackTrace()[0]);
			throw new PariveshException("Get Site Inspection Report with proposal No-" + proposal_no, ex);
		}
	}

}
