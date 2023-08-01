package com.backend.service.CampaPayment;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.CampaDashboardDto;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CampaPayment.CampaPaymentDetails;
import com.backend.model.CampaPayment.CampaPaymentOtherCharges;
import com.backend.model.CampaPayment.CampaPaymentOtherDetails;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CampaPayment.CampaPaymentDetailsRepository;
import com.backend.repository.postgres.CampaPayment.CampaPaymentOtherChargesRepository;
import com.backend.repository.postgres.CampaPayment.CampaPaymentOtherDetailsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CampaPaymentService {

    @Autowired
    private CampaPaymentDetailsRepository campaPaymentDetailsRepository;

    @Autowired
    private CampaPaymentOtherChargesRepository campaPaymentOtherChargesRepository;

    @Autowired
    private CampaPaymentOtherDetailsRepository campaPaymentOtherDetailsRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    public ResponseEntity<Object> saveCampaPaymentDetails(CampaPaymentDetails campaPaymentDetails, Integer clearance_id, HttpServletRequest request) {
        try {
                if ((campaPaymentDetails.getId() == null || campaPaymentDetails.getId() == 0) && campaPaymentDetails.getIsMultipleDemand()==false) {
                    CampaPaymentDetails campaPaymentDetails2 = campaPaymentDetailsRepository.
                            getByFcId(campaPaymentDetails.getFc_id()).orElse(null);
                    if (campaPaymentDetails2 != null) {
                        campaPaymentDetails.setId(campaPaymentDetails2.getId());
                    }
                }

            CampaPaymentDetails paymentDetails = campaPaymentDetailsRepository.save(campaPaymentDetails);

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(paymentDetails.getFc_id());

            if (departmentApplicationRepository.getDepartmentApplicationByProposalId(paymentDetails.getId()) == null) {
                DepartmentApplication applications = new DepartmentApplication();
                Applications app = applicationsRepository.findById(clearance_id).get();
                applications.setApplications(app);
                applications.setProponentApplications(proponentApplications);
                applications.setProposal_sequence(proponentApplications.getProposal_sequence());
                applications.setProposal_no(proponentApplications.getProposal_no());
                applications.setProposal_id(paymentDetails.getId());
                applications.setStatus(Caf_Status.DRAFT.toString());
                applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

                departmentApplicationRepository.save(applications);
            }

            return ResponseHandler.generateResponse("saving Ec Factsheet", HttpStatus.OK, "", paymentDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Campa Payment Details - ", e);
        }
    }

    public ResponseEntity<Object> getCampaPaymentDetails(Integer id) {
        try {
            CampaPaymentDetails paymentDetails = campaPaymentDetailsRepository.getDetailsByCampaPaymentId(id);
            paymentDetails.setDepartmentApplication(
                    departmentApplicationRepository.getApplicationByProposalId(paymentDetails.getId()));
            paymentDetails.setCampaPaymentOtherDetails(campaPaymentOtherDetailsRepository.getDatabyCampaPaymentId(id));

            return ResponseHandler.generateResponse("getting campa Payment Details", HttpStatus.OK, "", paymentDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting campa Payment Details for Id- " + id, e);
        }
    }

    @Transactional
    public ResponseEntity<Object> saveCampaPaymentOtherDetails(Integer campa_payment_id,
                                                               CampaPaymentOtherDetails campaPaymentOtherDetails, HttpServletRequest request) {
        try {
            if (campaPaymentOtherDetails.getId() == null || campaPaymentOtherDetails.getId() == 0) {

                CampaPaymentOtherDetails otherDetails = campaPaymentOtherDetailsRepository
                        .getDatabyCampaPaymentId(campa_payment_id);

                if (otherDetails != null) {
                    campaPaymentOtherDetails.setId(otherDetails.getId());
                }
            }

            CampaPaymentDetails paymentDetails = campaPaymentDetailsRepository.getDetailsByCampaPaymentId(campa_payment_id);
            campaPaymentOtherDetails.setCampaPaymentDetails(paymentDetails);

            DepartmentApplication departmentApplication = departmentApplicationRepository
                    .getDepartmentApplicationByProposalId(campa_payment_id);
            if (departmentApplication != null) {
                departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
                departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
                departmentApplicationRepository.save(departmentApplication);
            }

            return ResponseHandler.generateResponse("saving Campa Payment Other Details", HttpStatus.OK, "",
                    campaPaymentOtherDetailsRepository.save(campaPaymentOtherDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException(
                    "Error in saving Campa Payment other details  for factsheet id- " + campa_payment_id, e);
        }
    }

    public ResponseEntity<Object> saveCampaPaymentOtherCharges(Integer campa_payment_id,
                                                               List<CampaPaymentOtherCharges> listOtherCharges) {
        try {
            CampaPaymentDetails paymentDetails = campaPaymentDetailsRepository.getDetailsByCampaPaymentId(campa_payment_id);
            List<CampaPaymentOtherCharges> otherCharges = new ArrayList<CampaPaymentOtherCharges>();
            otherCharges = listOtherCharges.stream().map(value -> {
                value.setCampaPaymentDetails(paymentDetails);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("saving campa payment other charges", HttpStatus.OK, "",
                    campaPaymentOtherChargesRepository.saveAll(otherCharges));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving campa payment other charges - ", e);
        }
    }

    public ResponseEntity<Object> deleteCampaPaymentOtherCharges(Integer other_charges_id) {
        try {
            CampaPaymentOtherCharges temp = campaPaymentOtherChargesRepository.getDataById(other_charges_id)
                    .orElseThrow(() -> new ProjectNotFoundException(
                            "campa payment other charges not found with ID" + other_charges_id));

            temp.setIs_active(false);
            temp.setIs_deleted(true);

            return ResponseHandler.generateResponse("deleting other charges", HttpStatus.OK, "",
                    campaPaymentOtherChargesRepository.save(temp));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleting campa payment other charges - " + other_charges_id, e);
        }

    }

    public ResponseEntity<Object> getCampaPaymentByApplicationId(Integer application_id) {
        try {
            ProponentApplications proponentApplications = proponentApplicationRepository.getById(application_id);
            Integer proposal_id = proponentApplications.getProposal_id();
            List<CampaPaymentDetails> paymentDetails = campaPaymentDetailsRepository.getDetailsByFcId(proposal_id);
            paymentDetails.stream().forEach(item -> {
                item.setDepartmentApplication(
                        departmentApplicationRepository.getApplicationByProposalId(item.getId()));
                item.setCampaPaymentOtherDetails(campaPaymentOtherDetailsRepository.getDatabyCampaPaymentId(item.getId()));

            });

            return ResponseHandler.generateResponse("getting campa Payment Details", HttpStatus.OK, "", paymentDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting campa Payment Details for Id- " + application_id, e);
        }
    }


    public ResponseEntity<Object> getCampaDashboardDetails(UserPrincipal userPrincipal) {
        try {
            List<CampaDashboardDto> response = campaPaymentDetailsRepository.getCampaDashboardDetails(userPrincipal.getId());
            return ResponseHandler.generateResponse("getting campa dashboard details", HttpStatus.OK, "", response);
        } catch (Exception e) {
            log.error("encountered exception", e);
            throw new PariveshException("error in getting campa dashboard list");
        }
    }

    public ResponseEntity<Object> deleteAllCampaOtherCharges(Integer campaId){
        try {
            List<CampaPaymentOtherCharges> otherCharges= campaPaymentOtherChargesRepository.getOtherChargesList(campaId);
            otherCharges.stream().map(value->{
                value.setIs_active(false);
                value.setIs_deleted(true);
                return value;
            }).collect(Collectors.toList());

            return ResponseHandler.generateResponse("all other charges deleted successfully",HttpStatus.OK,"",campaPaymentOtherChargesRepository.saveAll(otherCharges));
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in deleting other charges",e);
        }
    }


}
