package com.backend.service.CampPaymentCompletion;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CampaPaymentCompletion.FcCampaPaymentCompletionDetails;
import com.backend.model.CampaPaymentCompletion.FcCampaPaymentProposalDetails;
import com.backend.model.CampaPaymentCompletion.FcCampaTransactionDetails;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.CampaPaymentCompletion.FcCampaPaymentBankDetailsRepository;
import com.backend.repository.postgres.CampaPaymentCompletion.FcCampaPaymentCompletionDetailsRepository;
import com.backend.repository.postgres.CampaPaymentCompletion.FcCampaPaymentProposalDetailsRepository;
import com.backend.repository.postgres.CampaPaymentCompletion.FcCampaTransactionDetailsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CampaPaymentCompletionService {

    @Autowired
    private FcCampaPaymentBankDetailsRepository fcCampaPaymentBankDetailsRepository;

    @Autowired
    private FcCampaPaymentCompletionDetailsRepository fcCampaPaymentCompletionDetailsRepository;

    @Autowired
    private FcCampaPaymentProposalDetailsRepository fcCampaPaymentProposalDetailsRepository;

    @Autowired
    private FcCampaTransactionDetailsRepository fcCampaTransactionDetailsRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private NotificationService notificationSevice;

    public ResponseEntity<Object> saveFcCampaPaymentCompletionDetails(FcCampaPaymentCompletionDetails fcCampaPaymentCompletionDetails, Integer clearance_id, HttpServletRequest request) {
        try {

            if (fcCampaPaymentCompletionDetails.getId() != null && fcCampaPaymentCompletionDetails.getId() != 0) {
                FcCampaPaymentCompletionDetails campaPaymentDetails2 = fcCampaPaymentCompletionDetailsRepository.findById(fcCampaPaymentCompletionDetails.getId()).orElseThrow(() -> new ProjectNotFoundException("FC Campa Payment Completion Not Found"));
                fcCampaPaymentCompletionDetails.setId(campaPaymentDetails2.getId());
            }

            FcCampaPaymentCompletionDetails paymentDetails = fcCampaPaymentCompletionDetailsRepository.save(fcCampaPaymentCompletionDetails);

            ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalId(paymentDetails.getFc_id());

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


            return ResponseHandler.generateResponse("saveFcCampaPaymentCompletionDetails ", HttpStatus.OK, "", paymentDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcCampaPaymentCompletionDetails - ", e);
        }
    }

    public ResponseEntity<Object> getFcCampaPaymentCompletionDetails(Integer id) {
        try {
            FcCampaPaymentCompletionDetails fcCampaPaymentCompletionDetails = fcCampaPaymentCompletionDetailsRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("FcCampaPaymentCompletionDetails not found"));
            fcCampaPaymentCompletionDetails.setDepartmentApplication(departmentApplicationRepository.getApplicationByProposalId(fcCampaPaymentCompletionDetails.getId()));
            return ResponseHandler.generateResponse("getFcCampaPaymentCompletionDetails", HttpStatus.OK, "", fcCampaPaymentCompletionDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getFcCampaPaymentCompletionDetails - ", e);
        }
    }

    //	public ResponseEntity<Object> saveFcCampaPaymentBankDetails(Integer id,
//			FcCampaPaymentBankDetails fcCampaPaymentBankDetails,HttpServletRequest request) {
//		try {
//			if (fcCampaPaymentBankDetails.getId() == null || fcCampaPaymentBankDetails.getId() == 0) {
//
//				FcCampaPaymentBankDetails fcCampaPaymentBankDetails2 = fcCampaPaymentBankDetailsRepository
//						.getDatabyCampaPaymentId(id);
//
//				if (fcCampaPaymentBankDetails2 != null) {
//					fcCampaPaymentBankDetails.setId(fcCampaPaymentBankDetails2.getId());
//				}
//			}
//			FcCampaPaymentCompletionDetails fcCampaPaymentCompletionDetails = fcCampaPaymentCompletionDetailsRepository
//					.findById(id)
//					.orElseThrow(() -> new ProjectNotFoundException("FcCampaPaymentCompletionDetails not found"));
//			fcCampaPaymentBankDetails.setFcCampaPaymentCompletionDetails(fcCampaPaymentCompletionDetails);
//			
//			DepartmentApplication departmentApplication = departmentApplicationRepository
//					.getDepartmentApplicationByProposalId(id);
//			if (departmentApplication != null) {
//				departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
//				departmentApplication.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
//				departmentApplicationRepository.save(departmentApplication);
//			}
//
//			return ResponseHandler.generateResponse("saveFcCampaPaymentBankDetails ", HttpStatus.OK, "",
//					fcCampaPaymentBankDetailsRepository.save(fcCampaPaymentBankDetails));
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in saveFcCampaPaymentBankDetails  for id- " + id, e);
//		}
//	}
    @Transactional
    public ResponseEntity<Object> saveFcCampaPaymentproposalDetails(Integer id, FcCampaPaymentProposalDetails fcCampaPaymentProposalDetails) {
        try {
            if (fcCampaPaymentProposalDetails.getId() == null || fcCampaPaymentProposalDetails.getId() == 0) {

                FcCampaPaymentProposalDetails fcCampaPaymentBankDetails2 = fcCampaPaymentProposalDetailsRepository.getDatabyCampaPaymentId(id);

                if (fcCampaPaymentBankDetails2 != null) {
                    fcCampaPaymentProposalDetails.setId(fcCampaPaymentBankDetails2.getId());
                }
            }

            FcCampaPaymentCompletionDetails fcCampaPaymentCompletionDetails = fcCampaPaymentCompletionDetailsRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("FcCampaPaymentCompletionDetails not found"));
            fcCampaPaymentProposalDetails.setFcCampaPaymentCompletionDetails(fcCampaPaymentCompletionDetails);

            DepartmentApplication departmentApplication = departmentApplicationRepository.getDepartmentApplicationByProposalId(id);
            if (departmentApplication != null) {
                departmentApplication.setStatus(Caf_Status.SUBMITTED.toString());
                departmentApplicationRepository.save(departmentApplication);
            }
            FcCampaPaymentProposalDetails temp = fcCampaPaymentProposalDetailsRepository.save(fcCampaPaymentProposalDetails);

            return ResponseHandler.generateResponse("saveFcCampaPaymentproposalDetails", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcCampaPaymentproposalDetails  for id- " + id, e);
        }
    }


    public ResponseEntity<Object> saveFcCampaPaymentTransactionDetails(List<FcCampaTransactionDetails> fcCampaTransactionDetails, Integer id) {
        try {
            FcCampaPaymentCompletionDetails campaPaymentCompletionDetails = fcCampaPaymentCompletionDetailsRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("FcCampaPaymentCompletionDetails not found "));
            List<FcCampaTransactionDetails> fcDetails = new ArrayList<FcCampaTransactionDetails>();
            fcDetails = fcCampaTransactionDetails.stream().map(value -> {
                value.setFcCampaPaymentCompletionDetails(campaPaymentCompletionDetails);
                return value;
            }).collect(Collectors.toList());

            List<FcCampaTransactionDetails> temp = fcCampaTransactionDetailsRepository.saveAll(fcDetails);

            notificationSevice.sendPaymentNotification(temp.get(0).getFcCampaPaymentCompletionDetails().getId());

            return ResponseHandler.generateResponse("saveFcCampaPaymentTransactionDetails", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcCampaPaymentTransactionDetails- ", e);
        }
    }

    public ResponseEntity<Object> deleteFcCampaPaymentTransactionDetails(Integer id) {
        try {
            FcCampaTransactionDetails fcCampaTransactionDetails = fcCampaTransactionDetailsRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("FcCampaPaymentTransactionDetails"));

            fcCampaTransactionDetails.setIs_deleted(true);
            fcCampaTransactionDetails.setIs_active(false);

            return ResponseHandler.generateResponse("deleteFcCampaPaymentTransactionDetails", HttpStatus.OK, "", fcCampaTransactionDetailsRepository.save(fcCampaTransactionDetails));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteFcCampaPaymentTransactionDetails - ", e);
        }
    }

    public ResponseEntity<Object> getFcCampaPaymentTransactionDetails(Integer id) {
        try {
            FcCampaTransactionDetails fcCampaPaymentCompletionDetails = fcCampaTransactionDetailsRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("FcCampaPaymentTransactionDetails not found"));
            return ResponseHandler.generateResponse("getFcCampaPaymentTransactionDetails", HttpStatus.OK, "", fcCampaPaymentCompletionDetails);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getFcCampaPaymentTransactionDetails - ", e);
        }
    }

    public ResponseEntity<Object> checkTransactionExist(String transaction_id) {
        Boolean temp = null;
        try {
            Integer count = fcCampaTransactionDetailsRepository.getDetailsByTransactionId(transaction_id);

            if (count != 0) {
                temp = true;
            } else {
                temp = false;
            }

        } catch (Exception e) {
            log.error("Encountered exception", e);
            throw new PariveshException("error in getting transaction status", e);
        }
        return ResponseHandler.generateResponse("getting transaction status", HttpStatus.OK, "", temp);
    }
}
