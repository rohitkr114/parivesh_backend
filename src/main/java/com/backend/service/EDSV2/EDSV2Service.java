package com.backend.service.EDSV2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.EDSQueriesDTO;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.EDSV2.EDSFormV2;
import com.backend.model.EDSV2.EDSQueries;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.DocumentDetailsRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.EDSV2.EDSFormV2Repository;
import com.backend.repository.postgres.EDSV2.EDSQueriesRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EDSV2Service {

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private DepartmentApplicationRepository departmentApplicationRepository;

	@Autowired
	private ApplicationsRepository applicationsRepository;

	@Autowired
	private EDSFormV2Repository edsFormV2Repository;

	@Autowired
	private EDSQueriesRepository edsQueriesRepository;

	@Autowired
	private DocumentDetailsRepository detailsRepository;

	public ResponseEntity<Object> saveEDSFormV2(EDSFormV2 edsFormV2, Integer clearance_id, UserPrincipal principal,
			HttpServletRequest request) {
		try {

//			EDSFormV2 eds = edsFormV2Repository.save(edsFormV2);
//			if(edsFormV2.getId() != null && edsFormV2.getId() != 0) {
//				EDSFormV2 form = edsFormV2Repository.findById(edsFormV2.getId()).orElseThrow(()->new ProjectNotFoundException("CampaPayment Not Found"));
//				form.setId(edsFormV2.getId());
//			}
			Integer entity_id = principal.getId();
			Integer role_id = edsFormV2Repository.getEDSByRole(entity_id);
			Integer office_id = edsFormV2Repository.getEDSByOffice(entity_id);

			edsFormV2.setEds_by(entity_id);
			edsFormV2.setEds_by_role_id(role_id);
			edsFormV2.setEds_by_office_id(office_id);
			edsFormV2.setEds_to(edsFormV2Repository.getEDSTo(edsFormV2.getApplication_id()));
//			edsFormV2.setEds_to_role_id(edsFormV2Repository.getEDSToRole(edsFormV2.getEds_to()));
			edsFormV2.setEds_to_office_id(edsFormV2Repository.getEDSToOffice(edsFormV2.getEds_to()));

			edsFormV2 = edsFormV2Repository.save(edsFormV2);
//			System.out.println(edsFormV2);
//			System.out.println("form id:"+edsFormV2.getId());
//			System.out.println("queries:"+ edsFormV2.getEdsQueries());

			ProponentApplications proponentApplications = proponentApplicationRepository
					.getProponentApplicationById(edsFormV2.getApplication_id());
//					.findById(edsFormV2.getApplication_id()).orElseThrow(() -> new ProjectNotFoundException(
//							"proponent application not found with ID" + edsFormV2.getApplication_id()));

//			if (departmentApplicationRepository.getDepartmentApplicationByProposalId(edsFormV2.getId()) == null) {
			DepartmentApplication applications = new DepartmentApplication();
			Applications app = applicationsRepository.findById(clearance_id).get();
			applications.setApplications(app);
			applications.setProponentApplications(proponentApplications);
			applications.setProposal_sequence(proponentApplications.getProposal_sequence());
			applications.setProposal_no(proponentApplications.getProposal_no());
			applications.setProposal_id(edsFormV2.getId());
			applications.setStatus(Caf_Status.DRAFT.toString());

			departmentApplicationRepository.save(applications);
//			}
			return ResponseHandler.generateResponse("saving eds details", HttpStatus.OK, "", edsFormV2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>", e);
			throw new PariveshException("Error in Saving eds Details - ", e);
		}
	}

	public ResponseEntity<Object> getEdsFormV2(Integer id) {
		try {
			EDSFormV2 eds = edsFormV2Repository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("eds query not found with ID" + id));
			eds.setEdsQueries(eds.getEdsQueries().stream().map(value -> {
				value.setEds_raised_by(edsFormV2Repository.getEDSByRoleName(eds.getEds_by_role_id()));
				return value;
			}).collect(Collectors.toList()));
			System.out.println(eds);
			// eds.setDepartmentApplication(departmentApplicationRepository.getApplicationByProposalId(eds.getId()));

			return ResponseHandler.generateResponse("getting eds form details", HttpStatus.OK, "", eds);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting eds Details - ", e);
		}
	}

	public ResponseEntity<Object> deleteEDSQueries(Integer id) {
		try {
			EDSQueries query = edsQueriesRepository.findById(id)
					.orElseThrow(() -> new ProjectNotFoundException("eds query not found with ID" + id));

			query.setIs_active(false);
			query.setIs_deleted(true);

			return ResponseHandler.generateResponse("deleting eds query", HttpStatus.OK, "",
					edsQueriesRepository.save(query));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in deleting eds query - ", e);
		}
	}

	public ResponseEntity<Object> updateEDSQueries(EDSQueries query) {
		try {
//			EDSFormV2 eds = edsFormV2Repository.findById(id)
//					.orElseThrow(() -> new ProjectNotFoundException("eds query not found with ID" + id));
//			query.setEdsFormV2(eds);
//			query.setId(id);
			return ResponseHandler.generateResponse("saving eds query", HttpStatus.OK, "",
					edsQueriesRepository.save(query));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in saving eds query - ", e);
		}
	}

	public ResponseEntity<Object> getEDSQueriesByHistoryId(Integer application_id, Integer eds_form_id) {
		try {
			List<EDSFormV2> eds = new ArrayList<>();
			if (eds_form_id != null) {
				eds.add(edsFormV2Repository.findById(eds_form_id)
						.orElseThrow(() -> new ProjectNotFoundException("eds query not found with ID" + eds_form_id)));
			} else {
				eds = edsFormV2Repository.getEdsByIds(application_id);
				eds.forEach(item -> {
					Integer role = item.getEds_by_role_id();
					item.setEdsQueries(item.getEdsQueries().stream()
							.map(value -> {
								value.setEds_raised_by(edsFormV2Repository.getEDSByRoleName(role));
								return value;
							})
							.filter(child -> child.getIs_active()==true)
							.collect(Collectors.toList()));

				});
			}
			// System.out.println(queries);
			return ResponseHandler.generateResponse("getting queries", HttpStatus.OK, "", eds);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>", e);
			throw new PariveshException("Error in getting eds Details - ", e);
		}
	}

	public ResponseEntity<Object> getEDSQueriesById(Integer eds_form_id) {
		try {
			EDSFormV2 eds = edsFormV2Repository.findById(eds_form_id).orElseThrow(() -> new ProjectNotFoundException("Eds form not found"));
			log.debug("--------------" + eds.getId());
			eds.setEdsQueries(eds.getEdsQueries().stream().map(value -> {
				List<Object[]> docData = edsQueriesRepository.getDocuments(value.getId());
				for (Object[] obj : docData) {
					if (obj != null) {
						value.setQuery_document(
								obj[0] != null ? detailsRepository.findById((Integer) obj[0]).get() : null);
						value.setResponse_document(
								obj[1] != null ? detailsRepository.findById((Integer) obj[1]).get() : null);

					}
				}
				value.setEds_raised_by(edsFormV2Repository.getEDSByRoleName(eds.getEds_by_role_id()));
				return value;
			}

			).collect(Collectors.toList()));
			log.debug("--------------" + eds.getId());
			return ResponseHandler.generateResponse("getting queries", HttpStatus.OK, "", eds);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>", e);
			throw new PariveshException("Error in getting eds Details - ", e);
		}
	}

	public ResponseEntity<Object> getEdsQueriesByRole(Integer application_id, UserPrincipal principal) {
		try {
			List<EDSFormV2> eds = new ArrayList<EDSFormV2>();

			Integer entity_id = principal.getId();
			Integer role_id = edsFormV2Repository.getEDSByRole(entity_id);

//			if(role_id==222){
//				eds= edsFormV2Repository.getEdsByIds(application_id);
//			}else {
			eds = edsFormV2Repository.getEdsByIdsandRole(application_id, role_id);
//			}

			return ResponseHandler.generateResponse("getting eds", HttpStatus.OK, "", eds);
		} catch (Exception e) {
			log.error("Encountered Exception", e);
			throw new PariveshException("Error in getting eds - ", e);
		}
	}
}
