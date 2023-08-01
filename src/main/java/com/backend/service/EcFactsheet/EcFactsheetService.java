package com.backend.service.EcFactsheet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.AuthorityName;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.EcFactsheet.EcFactsheet;
import com.backend.model.EcFactsheet.EcFactsheetOtherDetails;
import com.backend.model.EcFactsheet.EcFactsheetProductdetails;
import com.backend.model.EcFactsheet.EcfactsheetPublichearingdetails;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.EcFactsheet.EcFactsheetOtherDetailsRepository;
import com.backend.repository.postgres.EcFactsheet.EcFactsheetProductdetailsRepository;
import com.backend.repository.postgres.EcFactsheet.EcFactsheetRepository;
import com.backend.repository.postgres.EcFactsheet.EcfactsheetPublichearingdetailsRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class EcFactsheetService {

    @Autowired
    private EcFactsheetRepository ecFactsheetRepository;

    @Autowired
    private EcfactsheetPublichearingdetailsRepository ecfactsheetPublichearingdetailsRepository;

    @Autowired
    private EcFactsheetProductdetailsRepository ecFactsheetProductdetailsRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private EcFactsheetOtherDetailsRepository ecFactsheetOtherDetailsRepository;

    public ResponseEntity<Object> saveEcFactsheet(EcFactsheet ecFactsheet, Integer clearance_id) {
        try {
            EcFactsheet factsheet = ecFactsheetRepository.save(ecFactsheet);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(factsheet.getEc_id());

            if (departmentApplicationRepository.getDepartmentApplicationByProposalId(factsheet.getEc_id()) == null) {
                DepartmentApplication applications = new DepartmentApplication();
                Applications app = applicationsRepository.findById(clearance_id).get();
                applications.setApplications(app);
                applications.setProponentApplications(proponentApplications);
                applications.setProposal_sequence(proponentApplications.getProposal_sequence());
                applications.setProposal_no(proponentApplications.getProposal_no().replaceAll("\\s", ""));
                applications.setProposal_id(factsheet.getEc_id());
                applications.setStatus(Caf_Status.DRAFT.toString());
//				applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

                departmentApplicationRepository.save(applications);
            }
            return ResponseHandler.generateResponse("saving Ec Factsheet", HttpStatus.OK, "", factsheet);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC Factsheet - ", e);
        }
    }

    public ResponseEntity<Object> getEcFactsheet(Integer id) {
        try {
            EcFactsheet factsheet = ecFactsheetRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC Factsheet not found with id" + id));
            factsheet.setDepartmentApplication(
                    departmentApplicationRepository.getApplicationByProposalId(factsheet.getEc_id()));
            factsheet.setEcFactsheetOtherDetails(ecFactsheetOtherDetailsRepository.getDatabyFactsheetId(id));

            return ResponseHandler.generateResponse("getting EcFactsheet", HttpStatus.OK, "", factsheet);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting EC factsheet for Id- " + id, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> saveOtherDetails(Integer ec_factsheet_id,
                                                   EcFactsheetOtherDetails factsheetOtherdetails) {
        try {
            if (factsheetOtherdetails.getId() == null || factsheetOtherdetails.getId() == 0) {

                EcFactsheetOtherDetails otherDetails = ecFactsheetOtherDetailsRepository
                        .getDatabyFactsheetId(ec_factsheet_id);

                if (otherDetails != null) {
                    factsheetOtherdetails.setId(otherDetails.getId());
                }
            }

            EcFactsheet factsheet = ecFactsheetRepository.getDetailsByFactsheetId(ec_factsheet_id);
            factsheetOtherdetails.setEcFactsheet(factsheet);

            DepartmentApplication departmentApplication = departmentApplicationRepository
                    .getApplicationByProposalId(ec_factsheet_id);
            if (departmentApplication != null) {
                departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
                departmentApplicationRepository.save(departmentApplication);
            }

            return ResponseHandler.generateResponse("Saving Ec factsheet other details", HttpStatus.OK, "",
                    ecFactsheetOtherDetailsRepository.save(factsheetOtherdetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in saving EC factsheet other details  for factsheet id- " + ec_factsheet_id, e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> saveEcfactsheetPublichearingdetails(Integer ec_factsheet_id,
                                                                      EcfactsheetPublichearingdetails factsheetPublichearingdetails) {

        try {
            EcFactsheet factsheet = ecFactsheetRepository.getFormById(ec_factsheet_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec factsheet not found"));
            factsheetPublichearingdetails.setEcFactsheet(factsheet);
            return ResponseHandler.generateResponse("saving factsheet hearing details", HttpStatus.OK, null,
                    ecfactsheetPublichearingdetailsRepository.save(factsheetPublichearingdetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in saving EC factsheet public hearing details for factsheet id- " + ec_factsheet_id, e);
        }

    }

    public ResponseEntity<Object> deleteEcfactsheetPublichearingdetails(Integer ph_id) {
        try {
            EcfactsheetPublichearingdetails temp = ecfactsheetPublichearingdetailsRepository.findById(ph_id)
                    .orElseThrow(() -> new ProjectNotFoundException(
                            "Ec factsheet public hearing details not found with ID" + ph_id));

            temp.setIs_active(false);
            temp.setIs_deleted(true);

            return ResponseHandler.generateResponse("deleted hearing details", HttpStatus.OK, "",
                    ecfactsheetPublichearingdetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC factsheet  deletePublicHearingdetails id- " + ph_id, e);
        }

    }

//	public ResponseEntity<Object> saveEcFactsheetProductDetails(Integer ec_factsheet_id,
//			List<EcFactsheetProductdetails> listfactsheetProductdetails) {
//		try {
//			List<EcFactsheetProductdetails> productdetails = new ArrayList();
//			EcFactsheet factsheet = ecFactsheetRepository.findById(ec_factsheet_id)
//					.orElseThrow(() -> new ProjectNotFoundException("EC factsheet not found with id"));
//			productdetails = listfactsheetProductdetails.stream().map(value -> {
//				value.setEcFactsheet(factsheet);
//				return value;
//			}).collect(Collectors.toList());
//			return ResponseHandler.generateResponse("saving ec factsheet product details", HttpStatus.OK, "",
//					ecFactsheetProductdetailsRepository.saveAll(productdetails));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException(
//					"Error in saving EC factsheet product details for factsheet id- " + ec_factsheet_id, e);
//		}
//
//	}

    public ResponseEntity<Object> deleteEcFactsheetProductdetails(Integer pd_id) {
        try {
            EcFactsheetProductdetails temp = ecFactsheetProductdetailsRepository.findById(pd_id).orElseThrow(
                    () -> new ProjectNotFoundException("Ec factsheet product details not found with ID" + pd_id));

            temp.setIs_active(false);
            temp.setIs_deleted(true);

            return ResponseHandler.generateResponse("deleted hearing details", HttpStatus.OK, "",
                    ecFactsheetProductdetailsRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC factsheet  product details id- " + pd_id, e);
        }

    }

    public ResponseEntity<Object> getAuthorityDetails(Integer proposalId, String type) {
        String roleIds = "";
        if (type.equalsIgnoreCase("MS")) {
            roleIds = "5,13";
        } else {
            roleIds = "327629,869307";
        }
        try {
            AuthorityName authorityName = ecFactsheetRepository.getAuthorityDetails(proposalId, roleIds);
            return ResponseHandler.generateResponse("getting getAuthorityDetails", HttpStatus.OK, "", authorityName);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting getAuthorityDetails for proposalId- " + proposalId, e);
        }
    }
}
