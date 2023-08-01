package com.backend.service.EcForm9Service;

import com.backend.constant.AppConstant;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm9TransferOfEC.*;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm9Repository.*;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.UpdateOtherPropertyService;
import com.backend.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class Ec9Service {

    @Autowired
    EcForm9TransferOfECRepository ecForm9TransferOfECRepository;

    @Autowired
    EcForm9LocationOfProjectRepository ecForm9LocationOfProjectRepository;

    @Autowired
    private EcForm9ProductionCapacityRepository ecForm9ProductionCapacityRepository;

    @Autowired
    EcForm9ProposalDetailsRepository ecForm9ProposalDetailsRepository;

    @Autowired
    EcForm9CUndertakingRepository1 ecForm9CUndertakingRepository1;

    @Autowired
    EcForm9LegalDetailsRepository ecForm9LegalDetailsRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    @Autowired
    CommonFormDetailRepository commonFormDetailRepository;

    // @Autowired
    // EnvironmentClearenceRepository environmentClearenceRepository;
    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private ServerUtil util;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;

    @Autowired
    private NotificationService notificationSevice;

    /*
     * public Object saveEcForm9TransferOfEC(EcForm9TransferOfEC
     * ecForm9TransferOfEC, Integer caf_id, Integer ec_id, HttpServletRequest
     * request) throws PariveshException { try { HashMap<String, Object>
     * OtherPropString = new HashMap<String, Object>(); EnvironmentClearence
     * ecClearence =
     * environmentClearenceRepository.findByFormId(ec_id).orElse(null); if
     * (ObjectUtils.isEmpty(ecClearence.getMajor_activity_id())) { throw new
     * PariveshException("Major Activity in Environment Clearance is Null"); }
     * CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
     * .orElseThrow(() -> new
     * ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
     * ecForm9TransferOfEC.setCommonFormDetail(commonForm);
     * ecForm9TransferOfEC.setEnvironmentClearence(ecClearence);
     * ecForm9TransferOfEC.setEc_id(ec_id); if (ecForm9TransferOfEC.getId() != null
     * && ecForm9TransferOfEC.getId() != 0) { EcForm9TransferOfEC form =
     * ecForm9TransferOfECRepository.getFormById(ecForm9TransferOfEC.getId())
     * .orElseThrow(() -> new
     * ProjectNotFoundException("EC form not found with id"));
     * ecForm9TransferOfEC.setProposal_no(form.getProposal_no().replaceAll("\\s",
     * "")); return ecForm9TransferOfECRepository.save(ecForm9TransferOfEC); }
     * List<ProponentApplications> tempClearances =
     * proponentApplicationRepository.findAll();
     *
     * ProponentApplications applications = new ProponentApplications(); if
     * (tempClearances.isEmpty()) { String proposal_no = null; proposal_no = "IA/" +
     * stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state()
     * ) .orElseThrow(() -> new
     * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
     * + activitySectorRepository .getSector(ecClearence.getMajor_activity_id(),
     * ecClearence.getMajor_sub_activity_id()) .getSector_code() + "/" + 400000 +
     * "/" + String.valueOf(LocalDate.now().getYear());
     * applications.setProposal_sequence(400000); proposal_no =
     * proposal_no.replaceAll("\\s", ""); applications.setProposal_no(proposal_no);
     * ecClearence.setProposal_no(proposal_no);
     * ecForm9TransferOfEC.setProposal_no(proposal_no); } else { Integer maxCount =
     * tempClearances.stream().map(e -> e.getProposal_sequence())
     * .max(Comparator.comparing(Integer::valueOf)).get(); if (maxCount != null) {
     * applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(
     * maxCount))); applications.setProposal_no(generateProposalNo(maxCount,
     * ecForm9TransferOfEC, activitySectorRepository
     * .getSector(ecClearence.getMajor_activity_id(),
     * ecClearence.getMajor_sub_activity_id()) .getSector_code(), commonForm));
     * ecForm9TransferOfEC.setProposal_no(applications.getProposal_no().replaceAll(
     * "\\s", "")); } } EcForm9TransferOfEC temp2 =
     * ecForm9TransferOfECRepository.save(ecForm9TransferOfEC); Applications app =
     * applicationsRepository.findById(44).get(); applications.setCaf_id(caf_id);
     * applications.setProposal_id(temp2.getId());
     * applications.setState(stateRepository.getStateByCode(commonForm.
     * getProjectDetails().getMain_state()) .orElseThrow(() -> new
     * ProjectNotFoundException("state not found with code")).getName());
     *
     * applications.setState_id(stateRepository.getStateByCode(commonForm.
     * getProjectDetails().getMain_state()) .orElseThrow(() -> new
     * ProjectNotFoundException("state not found with code")).getCode());
     * applications.setProjectDetails(commonForm.getProjectDetails());
     * applications.setApplications(app);
     * applications.setLast_status(AppConstant.Caf_Status.DRAFT.toString());
     * applications.setIp_address(request.getRemoteAddr() != null ?
     * request.getRemoteAddr() : null);
     *
     * proponentApplicationRepository.save(applications);
     *
     * /* Updating the Proponent Application JSON String
     */

//            OtherPropString.put("Proposal For", app.getProposal_for());
    /*
     * OtherPropString.put("Proposal For", app.getGeneral_name()); String
     * activity_name =
     * environmentClearenceRepository.getMajorActivityName(temp2.getEc_id()); String
     * itemNo= environmentClearenceRepository.getItemNo(temp2.getEc_id());
     * OtherPropString.put("Activity", itemNo.concat(" "+activity_name));
     * OtherPropString.put("Sector",
     * activitySectorRepository.getSector(ecClearence.getMajor_activity_id(),
     * ecClearence.getMajor_sub_activity_id()).getSector_code());
     *
     * updateOtherPropertyService.updateOtherProperty(temp2.getId(),
     * OtherPropString);
     *
     * return temp2; } catch (Exception e) {
     * log.error("=======================>>>>>>>>>>>" + e.toString() + " " +
     * e.getStackTrace()[0]); throw new
     * PariveshException("Error in Saving Ec Other details form ec_id-", e); } }
     */

    public Object saveEcForm9TransferOfEC(EcForm9TransferOfEC ecForm9TransferOfEC, Integer caf_id,
                                          HttpServletRequest request) throws PariveshException {
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            // EnvironmentClearence ecClearence =
            // environmentClearenceRepository.findByFormId(ec_id).orElse(null);

            /*
             * if (ObjectUtils.isEmpty(ecClearence.getMajor_activity_id())) { throw new
             * PariveshException("Major Activity in Environment Clearance is Null"); }
             */

            CommonFormDetail commonForm = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found for caf id ----> " + caf_id));
            ecForm9TransferOfEC.setCommonFormDetail(commonForm);
            // ecForm9TransferOfEC.setEnvironmentClearence(ecClearence);
            // ecForm9TransferOfEC.setEc_id(ec_id);
            if (ecForm9TransferOfEC.getId() != null && ecForm9TransferOfEC.getId() != 0) {
                EcForm9TransferOfEC form = ecForm9TransferOfECRepository.getFormById(ecForm9TransferOfEC.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
                ecForm9TransferOfEC.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                return ecForm9TransferOfECRepository.save(ecForm9TransferOfEC);
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            ProponentApplications applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {
                String proposal_no = null;
                proposal_no = "IA/" + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository.getSector(ecForm9TransferOfEC.getActivity_id(),
                        ecForm9TransferOfEC.getSubActivity_id()).getSector_code()
                        + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                // ecClearence.setProposal_no(proposal_no);
                ecForm9TransferOfEC.setProposal_no(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications
                            .setProposal_no(
                                    generateProposalNo(maxCount, ecForm9TransferOfEC,
                                            activitySectorRepository.getSector(ecForm9TransferOfEC.getActivity_id(),
                                                    ecForm9TransferOfEC.getSubActivity_id()).getSector_code(),
                                            commonForm));
                    ecForm9TransferOfEC.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }
            EcForm9TransferOfEC temp2 = ecForm9TransferOfECRepository.save(ecForm9TransferOfEC);
            Applications app = applicationsRepository.findById(44).get();
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());

            applications.setState_id(stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(commonForm.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(AppConstant.Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

            proponentApplicationRepository.save(applications);

            /*
             * Updating the Proponent Application JSON String
             */

            // OtherPropString.put("Proposal For", app.getProposal_for());
            OtherPropString.put("Proposal For", app.getGeneral_name());
            String activity_name = ecForm9TransferOfECRepository
                    .getMajorActivityNameById(ecForm9TransferOfEC.getActivity_id());
            String itemNo = ecForm9TransferOfECRepository.getItemNoById(ecForm9TransferOfEC.getActivity_id());
            OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
            OtherPropString.put("Sector",
                    activitySectorRepository
                            .getSector(ecForm9TransferOfEC.getActivity_id(), ecForm9TransferOfEC.getSubActivity_id())
                            .getSector_code());

            updateOtherPropertyService.updateOtherProperty(temp2.getId(), OtherPropString);

            return temp2;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
    }

    public Object saveEcForm9LocationOfProject(EcForm9LocationOfProject ecForm9LocationOfProject, Integer ecForm9Id,
                                               HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm9LocationOfProject == null) {
                throw new ProjectNotFoundException("EC Form 9 Location Of Project is NULL");
            } else {
                EcForm9TransferOfEC ecForm9TransferOfEC = ecForm9TransferOfECRepository
                        .getEcForm9TransferOfEcByid(ecForm9Id)
                        .orElseThrow(() -> new ProjectNotFoundException("Ec form 9 transfer of EC not found"));
                ecForm9LocationOfProject.setEcForm9TransferOfEC(ecForm9TransferOfEC);

            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
        return ecForm9LocationOfProjectRepository.save(ecForm9LocationOfProject);
    }

    public Object saveEcForm9LegalDetails(EcForm9LegalDetails ecForm9LegalDetails, Integer ecForm9Id,
                                          HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm9LegalDetails == null) {
                throw new ProjectNotFoundException("EC Form 9 Legal Details is NULL");
            } else {
                EcForm9TransferOfEC ecForm9TransferOfEC = ecForm9TransferOfECRepository
                        .getEcForm9TransferOfEcByid(ecForm9Id)
                        .orElseThrow(() -> new ProjectNotFoundException("Ec form 9 transfer of EC not found"));
                ecForm9LegalDetails.setEcForm9TransferOfEC(ecForm9TransferOfEC);

            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Other details form ec_id-", e);
        }
        return ecForm9LegalDetailsRepository.save(ecForm9LegalDetails);
    }

    public Object saveEcForm9ProposalDetails(EcForm9ProposalDetails ecForm9ProposalDetails, Integer ecForm9Id,
                                             HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm9ProposalDetails == null) {
                throw new ProjectNotFoundException(
                        "EC Form 9 Proposal Details is NULL Or Proposal Details exists for Form 9 ID --->" + ecForm9Id);
            } else {
                EcForm9TransferOfEC ecForm9TransferOfEC = ecForm9TransferOfECRepository
                        .getEcForm9TransferOfEcByid(ecForm9Id)
                        .orElseThrow(() -> new ProjectNotFoundException("Ec form 9 Transfer of EC not found"));
                ecForm9ProposalDetails.setEcForm9TransferOfEC(ecForm9TransferOfEC);

                return ecForm9ProposalDetailsRepository.save(ecForm9ProposalDetails);
            }
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec proposal details for ecForm9Id-" + ecForm9Id, e);
        }
//        DocumentDetails docKML = ecForm9ProposalDetails.getForm9_kml();
//        if (docKML.getId().equals(null)) {
//            docKML.setId(null);
//            ecForm9ProposalDetails.setForm9_kml(docKML);
//        }

    }

    public ResponseEntity<Object> deleteProductionCapacity(Integer id) {
        try {
            EcForm9ProductionCapacity temp = ecForm9ProductionCapacityRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("production capacity not found with ID" + id));

            temp.setIsActive(false);
            temp.setIsDeleted(true);

            return ResponseHandler.generateResponse("deleting production capacity", HttpStatus.OK, "",
                    ecForm9ProductionCapacityRepository.save(temp));
        } catch (Exception e) {
            log.error("Encountered Exception", e);
            throw new PariveshException("error in deleting production capacity", e);
        }
    }

    public Object saveEcForm9Undertaking1(EcForm9Undertaking1 ecForm9Undertaking1, Integer ecForm9Id, Integer caf_id,
                                          Boolean is_submit, HttpServletRequest request) throws PariveshException {
        try {
            if (ecForm9Undertaking1.getId() == null || ecForm9Undertaking1.getId() == 0) {
                EcForm9Undertaking1 ecUndertaking2 = ecForm9CUndertakingRepository1.getByEc9Id(ecForm9Id);
                if (ecUndertaking2 != null) {
                    ecForm9Undertaking1.setId(ecUndertaking2.getId());
                }
            }
            EcForm9TransferOfEC ecForm9TransferOfEC = ecForm9TransferOfECRepository.getEcForm9TransferOfEcByid(ecForm9Id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 9 transfer of EC not found"));

            // EnvironmentClearence ecClearence =
            // environmentClearenceRepository.findByFormId(ecForm9TransferOfEC.getEc_id()).orElse(null);
            ecForm9Undertaking1.setEcForm9TransferOfEC(ecForm9TransferOfEC);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(ecForm9TransferOfEC.getProposal_no());

            String proposalNo = proponentApplicationRepository.getProposalNo(ecForm9Id);
            String identificationNo = ecForm9TransferOfECRepository.getIdentificationNo(proposalNo);
            ecForm9Undertaking1.setIdentification_no(identificationNo);
            log.info(identificationNo);

            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
                    .findByCaf((Integer) ecForm9TransferOfECRepository.getCafByEcId(ecForm9TransferOfEC.getId()).get(0)[0]);
            /*
             * proponentApplications.setProposal_no(
             * generateProposalNo(proponentApplications.getProposal_sequence() - 1,
             * ecForm9TransferOfEC, activitySectorRepository
             * .getSector(ecClearence.getMajor_activity_id(),
             * ecClearence.getMajor_sub_activity_id()) .getSector_code(), cafDetail.get()));
             */

            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
                    || proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
                if (ecForm9Undertaking1.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(ecForm9Undertaking1.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }
            if (is_submit == true)
                proponentApplications.setLast_status(AppConstant.Caf_Status.SUBMITTED.toString());

            commonFormDetailService.saveCommonForm(cafDetail.get());
            proponentApplicationRepository.save(proponentApplications);

            if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }

            return ecForm9CUndertakingRepository1.save(ecForm9Undertaking1);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec form 9 Undertaking details form ec_9_id-" + ecForm9Id, e);
        }
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);

        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, EcForm9TransferOfEC form, String sector_code,
                                      CommonFormDetail commonForm) {
        String cafId = "IA/"
                + stateRepository.getStateByCode(commonForm.getProjectDetails().getMain_state())
                .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                + "/" + sector_code + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }

    public Object getEcForm9(Integer id) {
        EcForm9TransferOfEC ecForm9dto = ecForm9TransferOfECRepository.findById(id)
                .orElseThrow(() -> new ProjectNotFoundException("Form 9 not found with id  ----> " + id));

        Integer caf_id = ecForm9TransferOfECRepository.getCafIdByFormId(ecForm9dto.getId());

        CommonFormDetail commonFormDetail = commonFormDetailRepository.findById(caf_id).orElseThrow(
                () -> new ProjectNotFoundException("Comman Form Detail not found for caf_id----> " + caf_id));
        // Optional<EnvironmentClearence> environmentClearence =
        // environmentClearenceRepository.findById(ecForm9dto.getEc_id());
        ProponentApplications proponentApplications = proponentApplicationRepository
                .getApplicationByProposalId(ecForm9dto.getId());
        EcForm9Undertaking1 ecForm9Undertaking1 = ecForm9CUndertakingRepository1.getByEc9Id(ecForm9dto.getId());
        EcForm9LocationOfProject ecForm9LocationOfProject = ecForm9LocationOfProjectRepository
                .getByEc9Id(ecForm9dto.getId());
        EcForm9LegalDetails ecForm9LegalDetails = ecForm9LegalDetailsRepository.getByEc9Id(ecForm9dto.getId());
        EcForm9ProposalDetails ecForm9ProposalDetails = ecForm9ProposalDetailsRepository.getByEc9Id(ecForm9dto.getId());

        EcForm9TransferOfEC ecForm9TransferOfEC = new EcForm9TransferOfEC();

        BeanUtils.copyProperties(ecForm9dto, ecForm9TransferOfEC);
        ecForm9TransferOfEC.setCommonFormDetail(commonFormDetail);
        ecForm9TransferOfEC.setProponentApplications(proponentApplications);
        ecForm9TransferOfEC.setEcForm9LegalDetails(ecForm9LegalDetails);
        ecForm9TransferOfEC.setEcForm9Undertaking1(ecForm9Undertaking1);
        ecForm9TransferOfEC.setEcForm9LocationOfProject(ecForm9LocationOfProject);
        ecForm9TransferOfEC.setEcForm9ProposalDetails(ecForm9ProposalDetails);
        /*
         * if (!ObjectUtils.isEmpty(environmentClearence)) {
         * ecForm9TransferOfEC.setEnvironmentClearence(environmentClearence.get()); }
         */
        return ResponseHandler.generateResponse("Save Ec Other Details form", HttpStatus.OK, null, ecForm9TransferOfEC);
    }

}
