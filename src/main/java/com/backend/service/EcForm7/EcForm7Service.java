package com.backend.service.EcForm7;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm7.*;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm7.*;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.UpdateOtherPropertyService;
import com.backend.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class EcForm7Service {

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private EcForm7Repository ecForm7Repository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private EcForm7MinorActivityRepository ecForm7MinorActivityRepository;

    @Autowired
    private EcForm7ObtainedRepository ecForm7ObtainedRepository;

    @Autowired
    private DocumentDetailsRepository documentdetailsRepository;

    @Autowired
    private EcForm7ActivityStatusRepository ecForm7ActivityStatusRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private ServerUtil util;

    @Autowired
    private EcForm7UndertakingRepository ecForm7UndertakingRepository;

    @Autowired
    private EcForm7AttachedDocumentsRepository ecForm7AttachedDocumentsRepository;

    @Autowired
    private EcForm7ProjectActivityRepository ecForm7ProjectActivityRepository;

    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;

    @Autowired
    private NotificationService notificationSevice;

    @Autowired
    private EcForm7ProjectActivityDetailsRepository ecForm7ProjectActivityDetailsRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private SubActivityRepository subActivityRepository;

    @Autowired
    private EcForm7CafKMLRepository ecForm7CafKMLRepository;

    public ResponseEntity<Object> saveEcForm7(EcForm7 ecForm7, Integer ec_id, Integer caf_id,
                                              HttpServletRequest request) throws PariveshException {
        EcForm7 temp2 = null;
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            EnvironmentClearence ecClearence = new EnvironmentClearence();
            if (ec_id != null) {
                ecClearence = environmentClearenceRepository.findByFormId(ec_id).orElse(null);
                ecForm7.setEnvironmentClearence(ecClearence);
                ecForm7.setEc_id(ec_id);
            }
            CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
            ecForm7.setCommonFormDetail(cafDetail);

            if (ecForm7.getId() != null && ecForm7.getId() != 0) {
                EcForm7 form = ecForm7Repository.getFormById(ecForm7.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
                ecForm7.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                return ResponseHandler.generateResponse("Update Ec Form 7", HttpStatus.OK, "",
                        ecForm7Repository.save(ecForm7));
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            ProponentApplications applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {
                String proposal_no = null;
                if (ecForm7.getProject_category().equalsIgnoreCase("A")) {
                    proposal_no = "IA/"
                            + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                            .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                            .getState_abbr()
                            + "/"
                            + activitySectorRepository
                            .getSector(ecForm7.getMajor_activity_id(), ecForm7.getMajor_sub_activity_id())
                            .getSector_code()
                            + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                } else {
                    if (ecForm7.getIs_proposed_required()) {
                        proposal_no = "IA/"
                                + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                                .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                                .getState_abbr()
                                + "/"
                                + activitySectorRepository
                                .getSector(ecForm7.getMajor_activity_id(), ecForm7.getMajor_sub_activity_id())
                                .getSector_code()
                                + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                    } else {
//						ResponseEntity<List<sieea>> resp = sieaaStatusAPI.getSieeaStatus(projectDetailRepository
//								.findById(cafDetail.getProject_id())
//								.orElseThrow(() -> new ProjectNotFoundException("project not found")).getMain_state());
//						if (resp.getBody().get(0).getStatus().equals("active")) {
                        proposal_no = "SIA/"
                                + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                                .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                                .getState_abbr()
                                + "/"
                                + activitySectorRepository
                                .getSector(ecForm7.getMajor_activity_id(), ecForm7.getMajor_sub_activity_id())
                                .getSector_code()
                                + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);

                    }

                }
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                if (ecClearence != null)
                    ecClearence.setProposal_no(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, cafDetail, ecForm7));
                    ecForm7.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }
            /*
             * if (tempClearances.isEmpty()) { String proposal_no = "IA/" + stateRepository
             * .getStateByCode(projectDetailRepository.findById(ecClearence.
             * getCommonFormDetail().getProject_id()) .orElseThrow(() -> new
             * ProjectNotFoundException("project not found")).getMain_state())
             * .orElseThrow(() -> new
             * ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
             * + activityRepository
             * .findById(ecClearence.getEnvironmentClearanceProjectActivityDetails().get(0).
             * getActivities() .getId()) .orElseThrow(() -> new
             * ProjectNotFoundException("activity not found with id"))
             * .getSectorEntity().getSectorCode() + "/" + 100000 + "/" +
             * String.valueOf(LocalDate.now().getYear());
             * applications.setProposal_sequence(100000);
             * applications.setProposal_no(proposal_no);
             * ecPartC.setProposal_no(proposal_no); } else { Integer maxCount =
             * tempClearances.stream().map(e -> e.getProposal_sequence())
             * .max(Comparator.comparing(Integer::valueOf)).get(); if (maxCount != null) {
             * applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(
             * maxCount))); applications.setProposal_no(generateProposalNo(maxCount,
             * ecClearence)); ecPartC.setProposal_no(applications.getProposal_no()); } }
             */
//			if (ecPartC.getAction_plan_raised() != null) {
//				ecPartC.getAction_plan_raised().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getEac_recommendation() != null) {
//				ecPartC.getEac_recommendation().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getTor_letter() != null) {
//				ecPartC.getTor_letter().setProposal_no(ecPartC.getProposal_no());
//			}
//			if (ecPartC.getTor_letter_copy() != null) {
//				ecPartC.getTor_letter_copy().setProposal_no(ecPartC.getProposal_no());
//			}
            temp2 = ecForm7Repository.save(ecForm7);
            Applications app = applicationsRepository.findById(38).get();

            if (ec_id == null) {
                ec_id = 0;
            } else {
                String MoefccFileNo = proponentApplicationRepository.getMoefccFileNo(ec_id);
                applications.setMoefccFileNumber(MoefccFileNo);
            }
            applications.setCaf_id(cafDetail.getId());
            applications.setProposal_id(temp2.getId());
            applications.setState(stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getName());
            /*
             * applications.setState_id(stateRepository.getStateByCode(cafDetail.
             * getProjectDetails().getMain_state()) .orElseThrow(() -> new
             * ProjectNotFoundException("state not found with code")).getId());
             */

            applications.setState_id(stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getCode());
            applications.setProjectDetails(cafDetail.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            proponentApplicationRepository.save(applications);

            /*
             * Updating the Proponent Application JSON String
             */

//            OtherPropString.put("Proposal For", app.getProposal_for());
            OtherPropString.put("Proposal For", app.getGeneral_name());
            String activity_name = ecForm7Repository.getMajorActivityName(temp2.getId());
            String itemNo = ecForm7Repository.getItemNo(temp2.getId());
            OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
            OtherPropString.put("Sector", activitySectorRepository
                    .getSector(temp2.getMajor_activity_id(), temp2.getMajor_sub_activity_id()).getSector_code());

            updateOtherPropertyService.updateOtherProperty(temp2.getId(), OtherPropString);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec form 7 for ec_id-" + ec_id + " & caf_id-" + caf_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec form 7", HttpStatus.OK, null, temp2);
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail form, EcForm7 ecForm7) {
        if (ecForm7.getProject_category().equalsIgnoreCase("A")) {
            String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
                    + activitySectorRepository
                    .getSector(ecForm7.getMajor_activity_id(), ecForm7.getMajor_sub_activity_id())
                    .getSector_code()
                    + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
            cafId = cafId.replaceAll("\\s", "");
            return cafId;
        } else {
            if (ecForm7.getIs_proposed_required()) {
                String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(ecForm7.getMajor_activity_id(), ecForm7.getMajor_sub_activity_id())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
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
                        .getSector(ecForm7.getMajor_activity_id(), ecForm7.getMajor_sub_activity_id())
                        .getSector_code()
                        + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
                // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
                cafId = cafId.replaceAll("\\s", "");
                return cafId;

            }
        }
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
    }
//
//	public ResponseEntity<Object> saveMinorActivity(List<EcForm7MinorActivity> ecForm7MinorActivity, Integer ec_id)
//			throws PariveshException {
//
//		List<EcForm7MinorActivity> list;
//		try {
//			EcForm7 ecForm7 = ecForm7Repository.getFormById(ec_id)
//					.orElseThrow(() -> new ProjectNotFoundException("Ec form 7 not found"));
//			list = ecForm7MinorActivity.stream().map(value -> {
//				value.setEcForm7(ecForm7);
//				return value;
//			}).collect(Collectors.toList());
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Saving Ec minor activity form 7 project activity -----" + ec_id, e);
//		}
//		return ResponseHandler.generateResponse("Save Ec minor activity form 7 project activity", HttpStatus.OK, null,
//				ecForm7MinorActivityRepository.saveAll(list));
//	}

    public ResponseEntity<Object> saveObtained(List<EcForm7Obtained> ecForm7Obtained, Integer ec_id)
            throws PariveshException {

        List<EcForm7Obtained> list;
        try {
            EcForm7ProjectActivity ecForm7ProjectActivity = ecForm7ProjectActivityRepository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 7 project activity not found"));
            list = ecForm7Obtained.stream().map(value -> {
                value.setEcForm7ProjectActivity(ecForm7ProjectActivity);
                return value;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Obtained form 7 project Activity -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec Obtained form 7", HttpStatus.OK, null,
                ecForm7ObtainedRepository.saveAll(list));
    }

    public ResponseEntity<Object> saveActivityStatus(EcForm7ActivityStatus ecForm7ActivityStatus, Integer ec_id)
            throws PariveshException {
        try {

            if (ecForm7ActivityStatus.getId() == null || ecForm7ActivityStatus.getId() == 0) {
                EcForm7ActivityStatus activityStatus = ecForm7ActivityStatusRepository.getDataByEcId(ec_id);
                if (activityStatus != null) {
                    ecForm7ActivityStatus.setId(activityStatus.getId());
                }
            }

            EcForm7 ecForm7 = ecForm7Repository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 7 not found"));
            ecForm7ActivityStatus.setEcForm7(ecForm7);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec activity status form 7 -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec activity status form 7", HttpStatus.OK, null,
                ecForm7ActivityStatusRepository.save(ecForm7ActivityStatus));
    }

    public ResponseEntity<Object> saveAttachedDocuments(EcForm7AttachedDocuments ecForm7AttachedDocuments,
                                                        Integer ec_id) throws PariveshException {
        try {

            if (ecForm7AttachedDocuments.getId() == null || ecForm7AttachedDocuments.getId() == 0) {
                EcForm7AttachedDocuments attachedDocuments = ecForm7AttachedDocumentsRepository.getDataByEcId(ec_id);
                if (attachedDocuments != null) {
                    ecForm7AttachedDocuments.setId(attachedDocuments.getId());
                }
            }
            EcForm7 ecForm7 = ecForm7Repository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 7 not found"));
            ecForm7AttachedDocuments.setEcForm7(ecForm7);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec attached Documents form 7 -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec attached Documents form 7", HttpStatus.OK, null,
                ecForm7AttachedDocumentsRepository.save(ecForm7AttachedDocuments));
    }

    public ResponseEntity<Object> saveEcForm7CafKML(List<EcForm7CafKML> ecForm7CafKML, Integer ec_id) throws PariveshException {
        try {

//            if (ecForm7CafKML.getId() == null || ecForm7CafKML.getId() == 0) {
//                List<EcForm7CafKML> cafKML = ecForm7CafKMLRepository.getDataByEcId(ec_id);
//                if (cafKML != null) {
//                    ecForm7CafKML.setId(cafKML.getId());
//                }
//            }
            return ResponseHandler.generateResponse("Save Ec Form form 7 CAF KML", HttpStatus.OK, null,
                    ecForm7CafKMLRepository.saveAll(ecForm7CafKML));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec form 7 CAF KML-----" + ec_id, e);
        }

    }

    public ResponseEntity<Object> saveProjectActivity(EcForm7ProjectActivity ecForm7ProjectActivity, Integer ec_id)
            throws PariveshException {
        try {

            if (ecForm7ProjectActivity.getId() == null || ecForm7ProjectActivity.getId() == 0) {
                EcForm7ProjectActivity projectActivity = ecForm7ProjectActivityRepository.getFormByIdDet(ec_id);
                if (projectActivity != null) {
                    ecForm7ProjectActivity.setId(projectActivity.getId());
                }
            }
            EcForm7 ecForm7 = ecForm7Repository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 7 not found"));
            ecForm7ProjectActivity.setEcForm7(ecForm7);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Project Activity form 7 -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec projectActivity form 7", HttpStatus.OK, null,
                ecForm7ProjectActivityRepository.save(ecForm7ProjectActivity));
    }
//	public ResponseEntity<Object> deleteMinorActivity(Integer id) {
//		try {
//
//			Integer upadate = ecForm7MinorActivityRepository.delete(id);
//			if (upadate == 0) {
//				throw new PariveshException("ID NOT FOUND - " + id);
//			}
//			return ResponseHandler.generateResponse("Delete EC Form 7 Minor Activity", HttpStatus.OK, "", "Deleted");
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Deleting EC Form 7 Minor Activity", e);
//		}
//	}

    public ResponseEntity<Object> deleteObtained(Integer id) {
        try {

            Integer upadate = ecForm7ObtainedRepository.delete(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Delete EC Form 7 Obtained", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC Form 7 obtained", e);
        }
    }

    public ResponseEntity<Object> deleteActivityStatus(Integer id) {
        try {

            Integer upadate = ecForm7ActivityStatusRepository.delete(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Delete EC Form 7 Activity Status", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC Form 7 Activity Status", e);
        }
    }

    public ResponseEntity<Object> deleteAttachedDocuments(Integer id) {
        try {

            Integer upadate = ecForm7AttachedDocumentsRepository.delete(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Delete EC Form 7 Attached Documents", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC Form 7 Attached Documents", e);
        }
    }

    public ResponseEntity<Object> saveUndertaking(EcForm7Undertaking ecForm7Undertaking, Integer ec_id,
                                                  Boolean is_submit, HttpServletRequest request) {

        try {
            if (ecForm7Undertaking.getId() == null || ecForm7Undertaking.getId() == 0) {
                EcForm7Undertaking undertaking = ecForm7UndertakingRepository.getRecordExist(ec_id).orElse(null);
                if (undertaking != null) {
                    ecForm7Undertaking.setId(undertaking.getId());
                }
            }

            String proposalNo = proponentApplicationRepository.getProposalNo(ec_id);
            String identificationNo = ecForm7UndertakingRepository.getIdentificationNo(proposalNo);

            ecForm7Undertaking.setIdentification_no(identificationNo);
            log.info(identificationNo);

            EcForm7 temp = ecForm7Repository.findById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form 7 form not found"));

            ecForm7Undertaking.setEcForm7(temp);

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());

            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
                    .findByCaf(temp.getCommonFormDetail().getId());
//			Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
//					.findByCaf((Integer) ecForm7Repository.getCafByEcId(temp.getId()).get(0)[0]);

            proponentApplications.setProposal_no(
                    generateProposalNo(proponentApplications.getProposal_sequence() - 1, cafDetail.get(), temp));
			/*
			if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
					|| proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
				if (ecForm7Undertaking.getSubmission_date() != null)
					proponentApplications.setLast_submission_date(ecForm7Undertaking.getSubmission_date());
				else {
					proponentApplications.setLast_submission_date(new Date());
				}
			}
			if (is_submit == true)
				proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());*/

            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString()) || proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
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

            commonFormDetailService.saveCommonForm(cafDetail.get());
            proponentApplicationRepository.save(proponentApplications);
			/*if (is_submit == true) {
				notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

				notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
			}
			*/
            return ResponseHandler.generateResponse("Save Ec Undertaking details form", HttpStatus.OK, null,
                    ecForm7UndertakingRepository.save(ecForm7Undertaking));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec form 7 Undertaking details form id-" + ec_id, e);
        }

    }

    public EcForm7 getEcForm7(Integer id) throws PariveshException {
        try {
            EcForm7 ecForm7 = ecForm7Repository.getFormByIdDet(id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form 7 not found with id-" + id));
            EcForm7ProjectActivity ecForm7ProjectActivity = ecForm7ProjectActivityRepository.getDataByEcFrom7(id);
//			ecForm7.setEcForm7MinorActivities(ecForm7MinorActivityRepository.getByEcId(id));
            if (ecForm7ProjectActivity != null) {
                ecForm7ProjectActivity
                        .setEcForm7Obtaineds(ecForm7ObtainedRepository.getByEcId(ecForm7ProjectActivity.getId()));

                List<Object[]> docData = ecForm7ProjectActivityRepository.getDocuments(ecForm7ProjectActivity.getId());
                for (Object[] obj : docData) {
                    if (obj != null) {
                        ecForm7ProjectActivity.setEc_letter_copy(
                                obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
                        ecForm7ProjectActivity.setChronology_of_clearances(
                                obj[1] != null ? documentdetailsRepository.findById((Integer) obj[1]).get() : null);
                    }
                }
                ecForm7.setEcForm7ProjectActivity(ecForm7ProjectActivity);
            }
            EcForm7ActivityStatus ecForm7ActivityStatus= ecForm7ActivityStatusRepository.getDataByEcFrom7(id);
            log.info("getEcForm7-> ecForm7ActivityStatus: {}",ecForm7ActivityStatus);
            ecForm7.setEcForm7ActivityStatus(ecForm7ActivityStatus);
            ecForm7.setEcForm7AttachedDocuments(ecForm7AttachedDocumentsRepository.getDataByEcFrom7(id));
            ecForm7.setEcForm7Undertaking(ecForm7UndertakingRepository.getDataByEcFrom7(id));
            ecForm7.setEcForm7CafKML(ecForm7CafKMLRepository.getDataByEcId(ecForm7.getId()));
            ecForm7.setProponentApplications(
                    proponentApplicationRepository.getApplicationByProposalId(ecForm7.getId()));
            log.info("INFO ------------ getEnvironmentClearence WITH EcID ---->" + id
                    + " ---- FOUND and RETRIEVED - SUCCESS");
            return ecForm7;
        } catch (Exception e) {
            log.error("exception: ",e);
            throw new PariveshException("Error in Getting EC getEnvironmentClearence for Id- " + id, e);
        }
    }

    /*
     * EcForm7ProjectActivityDetails
     */

    public List<EcForm7ProjectActivityDetails> saveEcForm7ProjectActivityDetails(Integer ecForm7Id,
                                                                                 List<EcForm7ProjectActivityDetails> environmentClearanceProjectActivityDetails) {

        EcForm7 temp = ecForm7Repository.findById(ecForm7Id).get();

        List<EcForm7ProjectActivityDetails> projectActivityDetails = environmentClearanceProjectActivityDetails.stream()
                .map(value -> {
                    value.setEcForm7(temp);
                    value.setProposalNo(temp.getProposal_no());
                    value.setActivities(activityRepository.findById(value.getActivityId())
                            .orElseThrow(() -> new ProjectNotFoundException("activity not found for activity ID")));
                    value.setSubActivities(subActivityRepository.findById(value.getSubActivityId()).orElseThrow(
                            () -> new ProjectNotFoundException("subactivity not found for subactivity ID")));

                    return value;
                }).collect(Collectors.toList());
        List<EcForm7ProjectActivityDetails> environmentDetails = ecForm7ProjectActivityDetailsRepository
                .saveAll(projectActivityDetails);
        log.info(
                "INFO ------------ saveEnvironmentClearanceProjectActivityDetails Environment Clearance Project Activity Details WITH ecId ----> "
                        + ecForm7Id + " ----SAVED - SUCCESS");
        return environmentDetails;
    }

    public ResponseEntity<Object> getECProjectActivityData(int id) {
        log.info(
                "INFO ------------ getECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "
                        + id + " ---- FOUND and RETRIEVED - SUCCESS");
        return ResponseHandler.generateResponse("get environment data list by ec_id", HttpStatus.OK, "",
                ecForm7ProjectActivityDetailsRepository.findDetailByEcId(id));

    }

    public ResponseEntity<Object> deleteECProjectActivityData(int id) {

        EcForm7ProjectActivityDetails environmentClearanceProjectActivityDetails = ecForm7ProjectActivityDetailsRepository
                .findById(id).get();
        environmentClearanceProjectActivityDetails.setIsDelete(true);
        ecForm7ProjectActivityDetailsRepository.save(environmentClearanceProjectActivityDetails);
        log.info(
                "INFO ------------ deleteECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "
                        + id + " ---- FOUND and DELETED - SUCCESS");
        return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
                "deleted successfully");

    }

}
