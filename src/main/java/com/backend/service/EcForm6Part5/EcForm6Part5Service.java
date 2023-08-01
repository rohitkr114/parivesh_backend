package com.backend.service.EcForm6Part5;

import com.backend.constant.AppConstant;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.exceptions.UserNotFoundException;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm6CafDetails.EcForm6CafDetails;
import com.backend.model.EcForm6Part1.EcForm6BasicDetails;
import com.backend.model.EcForm6Part5.EcForm6Undertaking;
import com.backend.model.EcPartC.EcPartC;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ActivitySubActivitySectorRepository;
import com.backend.repository.postgres.CommonFormDetailRepository;
import com.backend.repository.postgres.EcForm6CafDetails.EcForm6CafDetailsRepository;
import com.backend.repository.postgres.EcForm6Part1.EcForm6BasicDetailsRepository;
import com.backend.repository.postgres.EcForm6Part5.EcForm6UndertakingRepository;
import com.backend.repository.postgres.EcPartC.EcPartCRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.StateRepository;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class EcForm6Part5Service {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private EcForm6UndertakingRepository ecForm6UndertakingRepository;

    @Autowired
    private EcForm6BasicDetailsRepository ecForm6BasicDetailsRepository;

    @Autowired
    private EcPartCRepository ecPartCRepository;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private EcForm6CafDetailsRepository ecForm6CafDetailsRepository;

    @Autowired
    private ServerUtil util;

    @Autowired
    private NotificationService notificationSevice;

    // public ResponseEntity<Object> saveUndertaking(EcForm6Undertaking
    // ecForm6Undertaking, Integer ec_basic_id,Integer ecId,
    // HttpServletRequest request) {

    public ResponseEntity<Object> saveUndertaking(EcForm6Undertaking ecForm6Undertaking, Integer ec_id,
                                                  Boolean is_submit, HttpServletRequest request) {

        try {
            if (ecForm6Undertaking.getId() == null || ecForm6Undertaking.getId() == 0) {
                // EcForm6Undertaking undertaking =
                // ecForm6UndertakingRepository.getRecordExist(ec_basic_id)
                // .orElse(null);
                EcForm6Undertaking undertaking = ecForm6UndertakingRepository.getRecordExist(ec_id).orElse(null);

                if (undertaking != null) {
                    ecForm6Undertaking.setId(undertaking.getId());
                }
            }

            // EcForm6BasicDetails temp =
            // ecForm6BasicDetailsRepository.findById(ec_basic_id)
            // .orElseThrow(() -> new ProjectNotFoundException("EC form 6 Basic details not
            // found"));

            EcForm6BasicDetails temp = ecForm6BasicDetailsRepository.findById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form 6 Basic details not found"));

            CommonFormDetail commonFormDetail = temp.getCommonFormDetail();

            commonFormDetailRepository.save(commonFormDetail);

            ecForm6Undertaking.setEcForm6BasicDetails(temp);

            // update the status of the generated proposal
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());

            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) ||
            		proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
            	proponentApplications.setLast_submission_date(new Date());
            }
            if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);
            } else if (is_submit == true) {
                proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
                
                /*
                 * Sending Notification For First Submit
                 */
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());
                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }
            /*
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                    || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (ecForm6Undertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(ecForm6Undertaking.getSubmission_date());
                else {
					proponentApplications.setLast_submission_date(new Date());
                }
            }

            if (is_submit == true)
                proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
			*/
            proponentApplicationRepository.save(proponentApplications);

            // Change the status of ECFORM6 CAF DETAIL

            EcForm6CafDetails ecForm6CafDetails = ecForm6CafDetailsRepository
                    .getEcForm6CafDetailsByNewCafId(temp.getCommonFormDetail().getId());
            if (ecForm6CafDetails != null)
                ecForm6CafDetails.setStatus(1);
            ecForm6CafDetailsRepository.save(ecForm6CafDetails);

            // Provision for the form6 caf

            // ecForm6UndertakingRepository.save(ecForm6Undertaking);
            // proponentApplications.setProposal_no(temp.getProposal_no());

            /*
             * // to get the category of a project we are get the FORMPARTC with respect to
             * perticular EC. EcPartC ecPartC = ecPartCRepository.getFormPartC(ecId);
             *
             *
             * ProponentApplications proponentApplications = proponentApplicationRepository
             * .getApplicationByProposalNo(temp.getProposal_no());
             *
             * //proponentApplications.setClearance_id(65);
             * proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
             *
             * proponentApplications.setIp_address(request.getRemoteAddr() != null ?
             * request.getRemoteAddr() : null);
             *
             * //Optional<CommonFormDetail> cafDetail = commonFormDetailRepository //
             * .findByCaf(temp.getCommonFormDetail().getId());
             *
             * CommonFormDetail commonForm =
             * commonFormDetailRepository.findByCaf(temp.getCommonFormDetail().getId())
             * .orElseThrow(() -> new
             * ProjectNotFoundException("Caf form not found for caf id ----> " +
             * temp.getCommonFormDetail().getId()));
             *
             *
             * int maxCount = proponentApplications.getProposal_sequence()- 1;
             * proponentApplications.setProposal_no(generateProposalNo(maxCount, commonForm,
             * ecPartC)); // ecPartC TAKEN FOR PROPOSAL GENERATION
             * //proponentApplications.setProposal_no( //
             * generateProposalNo(proponentApplications.getProposal_sequence() - 1,
             * cafDetail.get(), temp));
             *
             * if
             * (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()))
             * { if (ecForm6Undertaking.getSubmission_date() != null)
             * proponentApplications.setLast_submission_date(ecForm6Undertaking.
             * getSubmission_date()); else {
             * proponentApplications.setLast_submission_date(new Date()); } }
             * commonFormDetailService.saveCommonForm(commonForm);
             * proponentApplicationRepository.save(proponentApplications);
             *
             */
            /*if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }*/
        } catch (Exception e) {
            log.error(e.toString() + " " + e.getStackTrace()[0]);
        }
        return ResponseHandler.generateResponse("Save Ec Undertaking details form 6", HttpStatus.OK, null,
                ecForm6UndertakingRepository.save(ecForm6Undertaking));

    }

    /*
     * return ResponseHandler.generateResponse("Save Ec Undertaking details form6",
     * HttpStatus.OK, null, ecForm6UndertakingRepository.save(ecForm6Undertaking));
     * } catch (Exception e) { log.error("=======================>>>>>>>>>>>" +
     * e.toString() + " " + e.getStackTrace()[0]); throw new
     * PariveshException("Error in Saving Ec form 6 Undertaking details form id-" +
     * ec_id, e); }
     *
     * }
     */

    // VIEW

    public EcForm6Undertaking getUndertaking(Integer ec_id) {

        EcForm6Undertaking ecForm6Undertaking = null;
        ProponentApplications proponentApplications = null;
        try {

            ecForm6Undertaking = ecForm6UndertakingRepository.getUndertaking(ec_id);
            if (ecForm6Undertaking == null) {
                throw new UserNotFoundException("Data not found");
            }
            proponentApplications = proponentApplicationRepository.getApplicationByProposalId_6(ec_id);

            // if (ecForm6Undertaking != null) {
            if (proponentApplications == null) { // This is write on 15 Dec. 2022
                ecForm6Undertaking.setLastStatus(Caf_Status.DRAFT.toString());
            } else {
                ecForm6Undertaking.setLastStatus(proponentApplications.getLast_status());
            }
            // }

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec form 6 Undertaking details form id-" + ec_id, e);
        }
        return ecForm6Undertaking;
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);

        return String.format("%06d", sequence.addAndGet(1));
    }

    /*
     * private String generateProposalNo(int maxcount, CommonFormDetail
     * commonForm,EcForm6BasicDetails form) { String cafId = "IA/" +
     * stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()
     * ) .orElseThrow(() -> new
     * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
     * + generateSequenceNumber(maxcount) + "/" +
     * String.valueOf(LocalDate.now().getYear()); return cafId;
     *
     * }
     */

    private String generateProposalNo(int maxcount, CommonFormDetail form, EcPartC ecPartC) {
        if (ecPartC.getProject_category().equalsIgnoreCase("A")) {
            String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
                    + activitySectorRepository
                    .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
                    .getSector_code()
                    + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
            return cafId;
        } else {
            if (ecPartC.getIs_proposed_required()) {
                String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                return cafId;
            } else {
//				ResponseEntity<List<sieea>> resp = sieaaStatusAPI
//						.getSieeaStatus(projectDetailRepository.findById(form.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//				if (resp.getBody().get(0).getStatus().equals("active")) {
                String cafId = "SIA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(ecPartC.getMajor_activity_id(), ecPartC.getMajor_sub_activity_id())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                return cafId;

            }
        }
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
    }

}