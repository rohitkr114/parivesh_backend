package com.backend.service.EcFormV;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcFormVModel.EcFormV;
import com.backend.model.EcFormVModel.EcFormVUndertaking;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcFormV.EcFormVRepository;
import com.backend.repository.postgres.EcFormV.EcFormVUndertakingRepository;
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
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class EcFormVService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private EcFormVRepository ecFormVRepository;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private EcFormVUndertakingRepository ecFormVUndertakingRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;
    @Autowired
    private ServerUtil util;

    @Autowired
    private NotificationService notificationSevice;

    public EcFormV saveEcFormV(EcFormV ecFormV, Integer caf_id, HttpServletRequest request) {
        try {
            CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
            ecFormV.setCommonFormDetail(commonForm);
            if (ecFormV.getId() != null && ecFormV.getId() != 0) {
                EcFormV form = ecFormVRepository.findByFormId(ecFormV.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
                ecFormV.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                return ecFormVRepository.save(ecFormV);
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            ProponentApplications applications = new ProponentApplications();

            EnvironmentClearence clearence = environmentClearenceRepository.findById(ecFormV.getEc_id())
                    .orElseThrow(() -> new ProjectNotFoundException("EC not found for the id-" + ecFormV.getEc_id()));

            if (tempClearances.isEmpty()) {
                String proposal_no = null;
                proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(clearence.getMajor_activity_id(), clearence.getMajor_sub_activity_id())
                        .getSector_code()
                        + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());

                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                ecFormV.setProposal_no(proposal_no);

            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, ecFormV, commonForm));
                    ecFormV.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }
            EcFormV temp2 = ecFormVRepository.save(ecFormV);
            Applications app;
            app = applicationsRepository.findById(34).get();
            Integer ec_id = ecFormV.getEc_id();
            if (ec_id == null) {
                ec_id = 0;
            } else {
                String MoefccFileNo = proponentApplicationRepository.getMoefccFileNo(ec_id);
                applications.setMoefccFileNumber(MoefccFileNo);
            }
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

            applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(commonForm.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

            proponentApplicationRepository.save(applications);
            return temp2;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving EC form V for ecFormVId- " + ecFormV.getId(), e);
        }

    }

    public EcFormV getEcFormV(Integer id) {
        try {
            EcFormV ecFormV = ecFormVRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form V not found with id"));
            ecFormV.setProponentApplications(
                    proponentApplicationRepository.getApplicationByProposalId(ecFormV.getId()));
            ecFormV.setEcFormVUndertaking(ecFormVUndertakingRepository.getDataByEcId(id).orElse(null));

            return ecFormV;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting EC form V for Id- " + id, e);
        }
    }

    public ResponseEntity<Object> saveUndertaking(EcFormVUndertaking ecFormVUndertaking, Integer ecFormVId,
                                                  Boolean is_submit, HttpServletRequest request) {
        try {

            EcFormV temp = ecFormVRepository.findById(ecFormVId)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form V form not found"));

            ecFormVUndertaking.setEcFormV(temp);

            String proposalNo = proponentApplicationRepository.getProposalNo(ecFormVId);
            String identificationNo = ecFormVUndertakingRepository.getIdentificationNo(proposalNo);
            ecFormVUndertaking.setIdentification_no(identificationNo);
            log.info(identificationNo);

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());

            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) 
            		|| proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
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
                if (ecFormVUndertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(ecFormVUndertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }

            if (is_submit == true)
                proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
             */
            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
                    .findByCaf((Integer) ecFormVRepository.getCafByEcId(temp.getId()).get(0)[0]);
            proponentApplications.setProposal_no(
                    generateProposalNo(proponentApplications.getProposal_sequence() - 1, temp, cafDetail.get()));

            commonFormDetailService.saveCommonForm(cafDetail.get());
            proponentApplicationRepository.save(proponentApplications);

            /*if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }*/

            return ResponseHandler.generateResponse("Save Ec Undertaking details form", HttpStatus.OK, null,
                    ecFormVUndertakingRepository.save(ecFormVUndertaking));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Fc FormA PartB DC Undertaking details form id-" + ecFormVId,
                    e);
        }

    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);

        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, EcFormV form, CommonFormDetail commonForm) {

        EnvironmentClearence clearence = environmentClearenceRepository.findById(form.getEc_id())
                .orElseThrow(() -> new ProjectNotFoundException("EC not found for the id-" + form.getEc_id()));

        String cafId = "IA/"
                + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                + "/"
                + activitySectorRepository
                .getSector(clearence.getMajor_activity_id(), clearence.getMajor_sub_activity_id())
                .getSector_code()
                + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
        cafId = cafId.replaceAll("\\s", "");
        return cafId;

    }

}
