package com.backend.service;

import com.backend.client.NotificationTemplateClient;
import com.backend.constant.AppConstant;
import com.backend.dto.*;
import com.backend.exceptions.PariveshException;
import com.backend.model.NotificationTemplate;
import com.backend.model.RemovedProposals;
import com.backend.repository.postgres.CampaPaymentCompletion.FcCampaPaymentProposalDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.repository.postgres.UserRepository;
import com.backend.util.ServerUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    private ServerUtil serverUtil;

    @Autowired
    private NotificationTemplateClient notificationTemplateClient;

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    private ForestClearanceRepository forestClearanceRepository;


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private FcCampaPaymentProposalDetailsRepository fcCampaPaymentProposalDetailsRepository;

    public void sendProposalEmail(String proposal_No) {
        try {
            log.info("PARIVESHBACKEND::NOTIFICATIONSERVICE::SENDPROPOSALEMAIL- proposal_No-{}", proposal_No);
            String subject = "N/A";
            String vmName = "N/A";
            Map<String, String> body = new HashMap<>();
            NotificationTemplate notificationTemplate = new NotificationTemplate();

            if (proposal_No.contains("IA") || proposal_No.contains("SIA")) {
                body = getEcEmailDto(proposal_No);

                notificationTemplate = notificationTemplateClient.getNotificationTemplate(AppConstant.APPLICATION_SUBMISSION_EC);
                subject = notificationTemplate.getMessage_type();
                vmName = notificationTemplate.getTemplateId();
            } else {
                body = getFcEamilDto(proposal_No);

                notificationTemplate = notificationTemplateClient.getNotificationTemplate(AppConstant.APPLICATION_SUBMISSION_FC);
                subject = notificationTemplate.getMessage_type();
                vmName = notificationTemplate.getTemplateId();
            }

            String email = body.get("emailId");

            log.info("Sending Submission Mail to client.");
            serverUtil.sendEmailNotification(email, body, subject, vmName);

            log.info("=============>>>>>>>>>>>>>Application Submission- Acknowledgment Mail Sent Successfully");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.getStackTrace());
        }
    }

    public void sendAcknowledgement(String proposal_No) {
        try {
            log.info("PARIVESHBACKEND::NOTIFICATIONSERVICE::SENDACKNOWLEDGEMENT- proposal_No-{}", proposal_No);
            String subject = "N/A";
            String vmName = "N/A";
            Map<String, String> body = new HashMap<>();
            NotificationTemplate notificationTemplate = new NotificationTemplate();

            if (proposal_No.contains("IA") || proposal_No.contains("SIA")) {
                body = getEcEmailDto(proposal_No);

                notificationTemplate = notificationTemplateClient.getNotificationTemplate(AppConstant.ACKNOWLEDGEMENT_EC);
                subject = notificationTemplate.getMessage_type();
                vmName = notificationTemplate.getTemplateId();
            } else {
                body = getFcEamilDto(proposal_No);

                notificationTemplate = notificationTemplateClient.getNotificationTemplate(AppConstant.APPLICATION_SUBMISSION_FC);
                subject = notificationTemplate.getMessage_type();
                vmName = notificationTemplate.getTemplateId();
            }

            String email = body.get("emailId");

            log.info("Sending Submission Mail to client.");
            serverUtil.sendEmailNotification(email, body, subject, vmName);

            log.info("=============>>>>>>>>>>>>>Application Submission- Acknowledgment Mail Sent Successfully");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.getStackTrace());
        }
    }

    private Map<String, String> getEcEmailDto(String proposal_no) {
        Map<String, String> body = new HashMap<>();
        try {
            EcEmailDto dto = environmentClearenceRepository.getEcEmailData(proposal_no);

            String swNo = "N/A";
            String proposalNo = "N/A";
            String proposalName = "N/A";
            String location = "N/A";
            String orgName = "N/A";
            String category = "N/A";
            String appraisedBy = "N/A";
            String scheduleNo = "N/A";
            String clearance = "N/A;";
            String emailId = "N/A";
            String Emblem_of_India = environment.getProperty("Emblem_of_India.svg");
            String Parivesh_Background = environment.getProperty("Parivesh_Background.png");
            String Parivesh_logo = environment.getProperty("Parivesh_logo.svg");
            String barcode = " ";

            if (dto.getSw_no() != null) {
                swNo = dto.getSw_no();
            }
            if (dto.getProposal_no() != null) {
                proposalNo = dto.getProposal_no();
            }
            if (dto.getProject_name() != null) {
                proposalName = dto.getProject_name().replace(",", "-");
            }
            if (dto.getLocation() != null) {
                location = dto.getLocation();
            }
            if (dto.getOrgname() != null) {
                orgName = dto.getOrgname().replace(",", "-");
            }
            if (dto.getCategory() != null) {
                category = dto.getCategory().replace(",", "-");
            }
            if (dto.getAppraised_by() != null) {
                appraisedBy = dto.getAppraised_by().replace(",", "-");
            }
            if (dto.getProject_activity() != null) {
                scheduleNo = dto.getProject_activity().replace(",", "-");
            }
            if (dto.getClearance_type() != null) {
                clearance = dto.getClearance_type();
            }
            if (dto.getEmailid() != null) {
                emailId = dto.getEmailid();
            }

            body.put("Emblem_of_India", Emblem_of_India);
            body.put("Parivesh_Background", Parivesh_Background);
            body.put("Parivesh_logo", Parivesh_logo);
            body.put("barcode", barcode);
            body.put("swNo", swNo);
            body.put("proposalNo", proposalNo);
            body.put("proposalName", proposalName);
            body.put("location", location);
            body.put("orgName", orgName);
            body.put("category", category);
            body.put("appraisedBy", appraisedBy);
            body.put("scheduleNo", scheduleNo);
            body.put("clearance", clearance);
            body.put("emailId", emailId);
            body.put("date", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            if (proposalNo.contains("SIA")) {
                body.put("eac_seac", "SEAC");
            } else {
                body.put("eac_seac", "EAC");
            }

        } catch (PariveshException e) {
            throw new PariveshException("================>>>>>>>>>>>>>>>>EcEmailDto data not found for proposal_no" + proposal_no, e);
        }
        return body;
    }

    private Map<String, String> getFcEamilDto(String proposal_no) {
        Map<String, String> body = new HashMap<>();
        try {
            FcEmailDto dto = forestClearanceRepository.getFcEmailData(proposal_no);

            String clearanceType = "N/A";
            String proposalNo = "N/A";
            String projectName = "N/A";
            String orgName = "N/A";
            String forestLand = "N/A";
            String shapeOfProject = "N/A";
            String proposalApplied = "N/A";
            String approvalAuthority = "N/A";
            String location = "N/A";
            String category = "N/A";
            String status = "N/A";
            String meetingDate = "N/A";
            String meetingMode = "N/A";
            String meetingVenue = "N/A";
            String emailId = "N/A";
            String Emblem_of_India = environment.getProperty("Emblem_of_India.svg");
            String Parivesh_Background = environment.getProperty("Parivesh_Background.png");
            String Parivesh_logo = environment.getProperty("Parivesh_logo.svg");
            String barcode = " ";
            String acknowledgementMessage = "";

            if (dto.getClearance_type() != null) {
                clearanceType = dto.getClearance_type();
            }
            if (dto.getProposal_no() != null) {
                proposalNo = dto.getProposal_no();
            }
            if (dto.getProject_name() != null) {
                projectName = dto.getProject_name().replace(",", "-");
            }
            if (dto.getOrgname() != null) {
                orgName = dto.getOrgname().replace(",", "-");
            }
            if (dto.getForest_land() != null) {
                forestLand = dto.getForest_land().toString();
            }
            if (dto.getShape_of_project() != null) {
                shapeOfProject = dto.getShape_of_project();
            }
            if (dto.getProposal_applied_for() != null) {
                proposalApplied = dto.getProposal_applied_for().replace(",", "-");
            }
            if (dto.getApproval_granting_authority() != null) {
                approvalAuthority = dto.getApproval_granting_authority().replace(",", "-");
            }
            if (dto.getLocation() != null) {
                location = dto.getLocation();
            }
            if (dto.getProposal_category() != null) {
                category = dto.getProposal_category().replace(",", "-");
            }
            if (dto.getApplication_status() != null) {
                status = dto.getApplication_status();
            }
            if (dto.getMeeting_start_date() != null) {
                meetingDate = dto.getMeeting_start_date();
            }
            if (dto.getMeeting_mode() != null) {
                meetingMode = dto.getMeeting_mode();
            }
            if (dto.getMeeting_venue() != null) {
                meetingVenue = dto.getMeeting_venue();
            }
            if (dto.getEmailid() != null) {
                emailId = dto.getEmailid();
            }

            body.put("Emblem_of_India", Emblem_of_India);
            body.put("Parivesh_Background", Parivesh_Background);
            body.put("Parivesh_logo", Parivesh_logo);
            body.put("barcode", barcode);
            body.put("clearanceType", clearanceType);
            body.put("proposalNo", proposalNo);
            body.put("proposalName", projectName);
            body.put("orgName", orgName);
            body.put("forestLand", forestLand);
            body.put("shapeOfProject", shapeOfProject);
            body.put("proposalApplied", proposalApplied);
            body.put("approvalAuthority", approvalAuthority);
            body.put("location", location);
            body.put("category", category);
            body.put("status", status);
            body.put("meetingDate", meetingDate);
            body.put("meetingMode", meetingMode);
            body.put("meetingVenue", meetingVenue);
            body.put("emailId", emailId);
            body.put("date", new SimpleDateFormat("dd/MM/YYYY").format(new Date()));
            if (dto.getForest_land() < 5) {
                body.put("acknowledgementMessage", "The proposal will be examined by the DFO on its merits. You will be intimated about the various development while processing your case through email or SMS alert.");
            }
            if (dto.getForest_land() > 5) {
                body.put("acknowledgementMessage", "The proposal will be scrutinized by the PSC to access the completeness of the application. You will be intimated about the scheduling of the PSC meeting shortly.");
            }

        } catch (PariveshException e) {
            throw new PariveshException("================>>>>>>>>>>>>>>>>FcEmailDto data not found for proposal_no" + proposal_no, e);
        }
        return body;
    }

    public void sendProposalSMS(String proposalNo) {
        try {
            log.info("PARIVESHBACKEND::NOTIFICATIONSERVICE::SENDPROPOSALSMS- proposal_No-{}", proposalNo);
            NotificationTemplate notificationTemplate = notificationTemplateClient.getNotificationTemplate(AppConstant.SubmissionOfApplication);
            String message = notificationTemplate.getMessage().replace("{#var#}", proposalNo);

            String mobile = userRepository.getMobileNoByProposalNo(proposalNo);

            MessageDto dto = new MessageDto();
            dto.setMessage(message);
            dto.setTemplateId(notificationTemplate.getTemplateId());
            dto.setMnumber(mobile);

            serverUtil.sendSMSNotification(dto);
            log.info("Sending Proposal Submission SMS to client.");

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
        }
    }

    public void sendPaymentNotification(Integer id) {
        try {
            log.info("PARIVESHBACKEND::NOTIFICATIONSERVICE::SENDPAYMENTNOTIFICATION- FcCampaPaymentCompletionDetailsID-{}", id);
            String subject = AppConstant.paymentNotificationSubject;
            String vmName = AppConstant.paymentNotificationVm;

            List<PaymentNotificationDto> paymentNotificationDto = fcCampaPaymentProposalDetailsRepository.getPaymentNotificationDetails(id);

            List<PaymentEmailDto> paymentEmailDto = fcCampaPaymentProposalDetailsRepository.getPaymentEmailDetails(paymentNotificationDto.get(0).getCreated_by());

            for (PaymentNotificationDto paymentNotification : paymentNotificationDto) {

                Map<String, String> body = new HashMap<>();

                body = getFcEamilDto(paymentNotification.getProposal_no());

                body = setPaymentNotificationDetails(paymentNotification, body);

                for (PaymentEmailDto emailDto : paymentEmailDto) {

                    serverUtil.sendEmailNotification(emailDto.getEmailid(), body, subject, vmName);
                }
            }

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
        }
    }

    private Map<String, String> setPaymentNotificationDetails(PaymentNotificationDto paymentNotification, Map<String, String> body) {
        try {
            String transactionId = "N/A";
            String chequeNo = "N/A";
            String transactionDate = "N/A";
            String amountPaid = "N/A";
            String bankName = "N/A";
            String branchName = "N/A";
            String accountName = "N/A";
            String paymentMode = "N/A";

            if (paymentNotification.getTransaction_id() != null) {
                transactionId = paymentNotification.getTransaction_id();
            }
            if (paymentNotification.getTransaction_id() != null) {
                chequeNo = paymentNotification.getTransaction_id();
            }
            if (paymentNotification.getTransaction_id() != null) {
                transactionDate = dateCorrection(paymentNotification.getTransaction_date());
            }
            if (paymentNotification.getTransaction_id() != null) {
                amountPaid = paymentNotification.getAmount();
            }
            if (paymentNotification.getTransaction_id() != null) {
                bankName = paymentNotification.getBank_name().replace(",", "-");
            }
            if (paymentNotification.getTransaction_id() != null) {
                branchName = paymentNotification.getBranch_name().replace(",", "-");
            }
            if (paymentNotification.getTransaction_id() != null) {
                accountName = paymentNotification.getAccount_name();
            }
            if (paymentNotification.getTransaction_id() != null) {
                paymentMode = paymentNotification.getPayment_mode();
            }

            body.put("transactionId", transactionId);
            body.put("chequeNo", chequeNo);
            body.put("transactionDate", transactionDate);
            body.put("amountPaid", amountPaid);
            body.put("bankName", bankName);
            body.put("branchName", branchName);
            body.put("accountName", accountName);
            body.put("paymentMode", paymentMode);
        } catch (PariveshException e) {
            throw new PariveshException("============>>>>>>>>>>>>SETPAYMENTNOTIFICATIONDETAILS", e);
        }
        return body;
    }

    public String dateCorrection(String in_date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd");
        if (in_date == null || in_date.equals("null")) {
            return "N/A";
        }
        Date date = null;
        try {
            date = formatter.parse(in_date);
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
            String out_date = formatter2.format(date);
            return out_date;
        } catch (ParseException e) {
            e.printStackTrace();
            return "N/A";
        }
    }
    
    public void sendRemoveProposalsNotification(RemovedProposals removedProposals,List<FcEmailDto> emailDtoListData) {
		try {
			
			NotificationTemplate notificationTemplate = notificationTemplateClient
					.getNotificationTemplate(AppConstant.REMOVE_PROPOSAL_TEMPLATE_ID);
			String subject = notificationTemplate.getMessage_type();
			String vmName = notificationTemplate.getTemplateId();
			
			Map<String, String> body = getFcEamilDto(removedProposals.getProposalNo());
			for (FcEmailDto emailDto : emailDtoListData) {
				serverUtil.sendEmailNotification(emailDto.getEmailid(), body, subject, vmName);
				log.info("sendRemoveProposalsNotification-> Notification sent sucessfully");
			}
		} catch (PariveshException e) {
			e.printStackTrace();
			throw new PariveshException("================>>>>>>>>>>>>>>>>Notification not Generated", e);
		}

	}
    
}
