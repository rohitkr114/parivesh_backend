package com.backend.service.EcForm12;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.EcForm12.*;
import com.backend.model.EnvironmentClearence;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.EcForm12.*;
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
public class EcForm12Service {

    @Autowired
    EcForm12DetailsOfComponentsRepository ecForm12DetailsOfComponentsRepository;

    @Autowired
    ECForm12AddendumOfTransferorRepository ecForm12AddendumOfTransferorRepository;

    @Autowired
    ECForm12AddendumOfTransfereeRepository ecForm12AddendumOfTransfereeRepository;

    @Autowired
    private EnvironmentClearenceRepository environmentClearenceRepository;
    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;
    @Autowired
    private EcForm12Repository ecForm12Repository;
    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private ActivitySubActivitySectorRepository activitySectorRepository;
    @Autowired
    private ApplicationsRepository applicationsRepository;
    @Autowired
    private EcForm12MinorActivityRepository ecForm12MinorActivityRepository;
    @Autowired
    private EcForm12ObtainedRepository ecForm12ObtainedRepository;
    @Autowired
    private DocumentDetailsRepository documentdetailsRepository;
    @Autowired
    private EcForm12ActivityStatusRepository ecForm12ActivityStatusRepository;
    @Autowired
    private CommonFormDetailService commonFormDetailService;
    @Autowired
    private ServerUtil util;
    @Autowired
    private EcForm12UndertakingRepository ecForm12UndertakingRepository;
    @Autowired
    private EcForm12AttachedDocumentsRepository ecForm12AttachedDocumentsRepository;
    @Autowired
    private EcForm12ProjectActivityRepository ecForm12ProjectActivityRepository;
    @Autowired
    private UpdateOtherPropertyService updateOtherPropertyService;
    @Autowired
    private NotificationService notificationSevice;
    @Autowired
    private EcForm12ProjectActivityDetailsRepository ecForm12ProjectActivityDetailsRepository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private SubActivityRepository subActivityRepository;
    @Autowired
    private EcForm12CafKMLRepository ecForm12CafKMLRepository;

    @Autowired
    private EcForm12CafKMLSplittedRepository ecForm12CafKMLSplittedRepository;
    @Autowired
    private EcForm12TransfreeDetailsRepository ecForm12TransfreeDetailsRepository;

    public ResponseEntity<Object> saveEcForm12(EcForm12 ecForm12, Integer ec_id, Integer caf_id,
                                               HttpServletRequest request) throws PariveshException {
        EcForm12 temp2 = null;
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            EnvironmentClearence ecClearence = new EnvironmentClearence();
            if (ec_id != null) {
                ecClearence = environmentClearenceRepository.findByFormId(ec_id).orElse(null);
                ecForm12.setEnvironmentClearence(ecClearence);
                ecForm12.setEc_id(ec_id);
            }
            CommonFormDetail cafDetail = commonFormDetailRepository.findByCaf(caf_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Caf form not found with id"));
            ecForm12.setCommonFormDetail(cafDetail);

            if (ecForm12.getId() != null && ecForm12.getId() != 0) {
                EcForm12 form = ecForm12Repository.getFormById(ecForm12.getId())
                        .orElseThrow(() -> new ProjectNotFoundException("EC form not found with id"));
                ecForm12.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));
                return ResponseHandler.generateResponse("Update Ec Form 12", HttpStatus.OK, "",
                        ecForm12Repository.save(ecForm12));
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();

            ProponentApplications applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {
                String proposal_no = null;
                if (ecForm12.getProject_category().equalsIgnoreCase("A")) {
                    proposal_no = "IA/"
                            + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                            .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                            .getState_abbr()
                            + "/"
                            + activitySectorRepository
                            .getSector(ecForm12.getMajor_activity_id(), ecForm12.getMajor_sub_activity_id())
                            .getSector_code()
                            + "/" + 400000 + "/" + String.valueOf(LocalDate.now().getYear());
                } else {
                    if (ecForm12.getIs_proposed_required()) {
                        proposal_no = "IA/"
                                + stateRepository.getStateByCode(cafDetail.getProjectDetails().getMain_state())
                                .orElseThrow(() -> new ProjectNotFoundException("state not found with code"))
                                .getState_abbr()
                                + "/"
                                + activitySectorRepository
                                .getSector(ecForm12.getMajor_activity_id(), ecForm12.getMajor_sub_activity_id())
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
                                .getSector(ecForm12.getMajor_activity_id(), ecForm12.getMajor_sub_activity_id())
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
                    applications.setProposal_no(generateProposalNo(maxCount, cafDetail, ecForm12));
                    ecForm12.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
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
            ecForm12.getTransfereeDetails().stream().forEach(t->t.setEcForm12(ecForm12));            
            temp2 = ecForm12Repository.save(ecForm12);
            Applications app = applicationsRepository.findById(226).get();

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
            String activity_name = ecForm12Repository.getMajorActivityName(temp2.getId());
            String itemNo = ecForm12Repository.getItemNo(temp2.getId());
            OtherPropString.put("Activity", itemNo.concat(" " + activity_name));
            OtherPropString.put("Sector", activitySectorRepository
                    .getSector(temp2.getMajor_activity_id(), temp2.getMajor_sub_activity_id()).getSector_code());

            updateOtherPropertyService.updateOtherProperty(temp2.getId(), OtherPropString);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec form 12 for ec_id-" + ec_id + " & caf_id-" + caf_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec form 12", HttpStatus.OK, null, temp2);
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, CommonFormDetail form, EcForm12 ecForm12) {
        if (ecForm12.getProject_category().equalsIgnoreCase("A")) {
            String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                    .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr() + "/"
                    + activitySectorRepository
                    .getSector(ecForm12.getMajor_activity_id(), ecForm12.getMajor_sub_activity_id())
                    .getSector_code()
                    + "/" + generateSequenceNumber(maxcount) + "/" + String.valueOf(LocalDate.now().getYear());
            cafId = cafId.replaceAll("\\s", "");
            return cafId;
        } else {
            if (ecForm12.getIs_proposed_required()) {
                String cafId = "IA/" + stateRepository.getStateByCode(form.getProjectDetails().getMain_state())
                        .orElseThrow(() -> new ProjectNotFoundException("state not found with code")).getState_abbr()
                        + "/"
                        + activitySectorRepository
                        .getSector(ecForm12.getMajor_activity_id(), ecForm12.getMajor_sub_activity_id())
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
                        .getSector(ecForm12.getMajor_activity_id(), ecForm12.getMajor_sub_activity_id())
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
//	public ResponseEntity<Object> saveMinorActivity(List<EcForm12MinorActivity> ecForm12MinorActivity, Integer ec_id)
//			throws PariveshException {
//
//		List<EcForm12MinorActivity> list;
//		try {
//			EcForm12 ecForm12 = ecForm12Repository.getFormById(ec_id)
//					.orElseThrow(() -> new ProjectNotFoundException("Ec form 12 not found"));
//			list = ecForm12MinorActivity.stream().map(value -> {
//				value.setEcForm12(ecForm12);
//				return value;
//			}).collect(Collectors.toList());
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Saving Ec minor activity form 12 project activity -----" + ec_id, e);
//		}
//		return ResponseHandler.generateResponse("Save Ec minor activity form 12 project activity", HttpStatus.OK, null,
//				ecForm12MinorActivityRepository.saveAll(list));
//	}

    public ResponseEntity<Object> saveObtained(List<EcForm12Obtained> ecForm12Obtained, Integer ec_id)
            throws PariveshException {

        List<EcForm12Obtained> list;
        try {
            EcForm12ProjectActivity ecForm12ProjectActivity = ecForm12ProjectActivityRepository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 12 project activity not found"));
            list = ecForm12Obtained.stream().map(value -> {
                value.setEcForm12ProjectActivity(ecForm12ProjectActivity);
                return value;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Obtained form 12 project Activity -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec Obtained form 12", HttpStatus.OK, null,
                ecForm12ObtainedRepository.saveAll(list));
    }

    public ResponseEntity<Object> saveActivityStatus(EcForm12ActivityStatus ecForm12ActivityStatus, Integer ec_id)
            throws PariveshException {
        try {

            if (ecForm12ActivityStatus.getId() == null || ecForm12ActivityStatus.getId() == 0) {
                EcForm12ActivityStatus activityStatus = ecForm12ActivityStatusRepository.getDataByEcId(ec_id);
                if (activityStatus != null) {
                    ecForm12ActivityStatus.setId(activityStatus.getId());
                }
            }

            EcForm12 ecForm12 = ecForm12Repository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 12 not found"));
            ecForm12ActivityStatus.setEcForm12(ecForm12);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec activity status form 12 -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec activity status form 12", HttpStatus.OK, null,
                ecForm12ActivityStatusRepository.save(ecForm12ActivityStatus));
    }

    public ResponseEntity<Object> saveAttachedDocuments(EcForm12AttachedDocuments ecForm12AttachedDocuments,
                                                        Integer ec_id) throws PariveshException {
        try {

            if (ecForm12AttachedDocuments.getId() == null || ecForm12AttachedDocuments.getId() == 0) {
                EcForm12AttachedDocuments attachedDocuments = ecForm12AttachedDocumentsRepository.getDataByEcId(ec_id);
                if (attachedDocuments != null) {
                    ecForm12AttachedDocuments.setId(attachedDocuments.getId());
                }
            }
            EcForm12 ecForm12 = ecForm12Repository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 12 not found"));
            ecForm12AttachedDocuments.setEcForm12(ecForm12);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec attached Documents form 12 -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec attached Documents form 12", HttpStatus.OK, null,
                ecForm12AttachedDocumentsRepository.save(ecForm12AttachedDocuments));
    }

    public ResponseEntity<Object> saveEcForm12CafKML(List<EcForm12CafKML> ecForm12CafKML, Integer ec_id) throws PariveshException {
        try {

//            if (ecForm12CafKML.getId() == null || ecForm12CafKML.getId() == 0) {
//                List<EcForm12CafKML> cafKML = ecForm12CafKMLRepository.getDataByEcId(ec_id);
//                if (cafKML != null) {
//                    ecForm12CafKML.setId(cafKML.getId());
//                }
//            }
            return ResponseHandler.generateResponse("Save Ec Form form 12 CAF KML", HttpStatus.OK, null,
                    ecForm12CafKMLRepository.saveAll(ecForm12CafKML));

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec form 12 CAF KML-----" + ec_id, e);
        }

    }

    public ResponseEntity<Object> saveProjectActivity(EcForm12ProjectActivity ecForm12ProjectActivity, Integer ec_id)
            throws PariveshException {
        try {

            if (ecForm12ProjectActivity.getId() == null || ecForm12ProjectActivity.getId() == 0) {
                EcForm12ProjectActivity projectActivity = ecForm12ProjectActivityRepository.getFormByIdDet(ec_id);
                if (projectActivity != null) {
                    ecForm12ProjectActivity.setId(projectActivity.getId());
                }
            }
            EcForm12 ecForm12 = ecForm12Repository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 12 not found"));
            ecForm12ProjectActivity.setEcForm12(ecForm12);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Project Activity form 12 -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save Ec projectActivity form 12", HttpStatus.OK, null,
                ecForm12ProjectActivityRepository.save(ecForm12ProjectActivity));
    }
//	public ResponseEntity<Object> deleteMinorActivity(Integer id) {
//		try {
//
//			Integer upadate = ecForm12MinorActivityRepository.delete(id);
//			if (upadate == 0) {
//				throw new PariveshException("ID NOT FOUND - " + id);
//			}
//			return ResponseHandler.generateResponse("Delete EC Form 12 Minor Activity", HttpStatus.OK, "", "Deleted");
//		} catch (Exception e) {
//			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
//			throw new PariveshException("Error in Deleting EC Form 12 Minor Activity", e);
//		}
//	}

    public ResponseEntity<Object> deleteObtained(Integer id) {
        try {

            Integer upadate = ecForm12ObtainedRepository.delete(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Delete EC Form 12 Obtained", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC Form 12 obtained", e);
        }
    }

    public ResponseEntity<Object> deleteActivityStatus(Integer id) {
        try {

            Integer upadate = ecForm12ActivityStatusRepository.delete(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Delete EC Form 12 Activity Status", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC Form 12 Activity Status", e);
        }
    }

    public ResponseEntity<Object> deleteAttachedDocuments(Integer id) {
        try {

            Integer upadate = ecForm12AttachedDocumentsRepository.delete(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("Delete EC Form 12 Attached Documents", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Deleting EC Form 12 Attached Documents", e);
        }
    }

    public ResponseEntity<Object> saveUndertaking(EcForm12Undertaking ecForm12Undertaking, Integer ec_id,
                                                  Boolean is_submit, HttpServletRequest request) {

        try {
            if (ecForm12Undertaking.getId() == null || ecForm12Undertaking.getId() == 0) {
                EcForm12Undertaking undertaking = ecForm12UndertakingRepository.getRecordExist(ec_id).orElse(null);
                if (undertaking != null) {
                    ecForm12Undertaking.setId(undertaking.getId());
                }
            }

            String proposalNo = proponentApplicationRepository.getProposalNo(ec_id);
            String identificationNo = ecForm12UndertakingRepository.getIdentificationNo(proposalNo);

            ecForm12Undertaking.setIdentification_no(identificationNo);
            log.info(identificationNo);

            EcForm12 temp = ecForm12Repository.findById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form 12 form not found"));

            ecForm12Undertaking.setEcForm12(temp);

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());

            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
                    .findByCaf(temp.getCommonFormDetail().getId());
//			Optional<CommonFormDetail> cafDetail = commonFormDetailRepository
//					.findByCaf((Integer) ecForm12Repository.getCafByEcId(temp.getId()).get(0)[0]);

            proponentApplications.setProposal_no(
                    generateProposalNo(proponentApplications.getProposal_sequence() - 1, cafDetail.get(), temp));
			/*
			if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())
					|| proponentApplications.getLast_status().equals(Caf_Status.EDS_REPLIED.toString())) {
				if (ecForm12Undertaking.getSubmission_date() != null)
					proponentApplications.setLast_submission_date(ecForm12Undertaking.getSubmission_date());
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
                    ecForm12UndertakingRepository.save(ecForm12Undertaking));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec form 12 Undertaking details form id-" + ec_id, e);
        }

    }

    public EcForm12 getEcForm12(Integer id) throws PariveshException {
        try {
            EcForm12 ecForm12 = ecForm12Repository.getFormByIdDet(id)
                    .orElseThrow(() -> new ProjectNotFoundException("EC form 12 not found with id-" + id));
            EcForm12ProjectActivity ecForm12ProjectActivity = ecForm12ProjectActivityRepository.getDataByEcFrom12(id);
//			ecForm12.setEcForm12MinorActivities(ecForm12MinorActivityRepository.getByEcId(id));
            if (ecForm12ProjectActivity != null) {
                ecForm12ProjectActivity
                        .setEcForm12Obtaineds(ecForm12ObtainedRepository.getByEcId(ecForm12ProjectActivity.getId()));

                List<Object[]> docData = ecForm12ProjectActivityRepository.getDocuments(ecForm12ProjectActivity.getId());
                for (Object[] obj : docData) {
                    if (obj != null) {
                        ecForm12ProjectActivity.setEc_letter_copy(
                                obj[0] != null ? documentdetailsRepository.findById((Integer) obj[0]).get() : null);
                        ecForm12ProjectActivity.setChronology_of_clearances(
                                obj[1] != null ? documentdetailsRepository.findById((Integer) obj[1]).get() : null);
                    }
                }
                ecForm12.setEcForm12ProjectActivity(ecForm12ProjectActivity);
            }
            EcForm12ActivityStatus ecForm12ActivityStatus = ecForm12ActivityStatusRepository.getDataByEcFrom12(id);
            log.info("getEcForm12-> ecForm12ActivityStatus: {}", ecForm12ActivityStatus);
            ecForm12.setEcForm12ActivityStatus(ecForm12ActivityStatus);
            ecForm12.setEcForm12AttachedDocuments(ecForm12AttachedDocumentsRepository.getDataByEcFrom12(id));
            ecForm12.setEcForm12Undertaking(ecForm12UndertakingRepository.getDataByEcFrom12(id));
            ecForm12.setEcForm12CafKML(ecForm12CafKMLRepository.getDataByEcId(ecForm12.getId()));
            ecForm12.setProponentApplications(
                    proponentApplicationRepository.getApplicationByProposalId(ecForm12.getId()));
            log.info("INFO ------------ getEnvironmentClearence WITH EcID ---->" + id
                    + " ---- FOUND and RETRIEVED - SUCCESS");
            return ecForm12;
        } catch (Exception e) {
            log.error("exception: ", e);
            throw new PariveshException("Error in Getting EC getEnvironmentClearence for Id- " + id, e);
        }
    }

    /*
     * EcForm12ProjectActivityDetails
     */

    public List<EcForm12ProjectActivityDetails> saveEcForm12ProjectActivityDetails(Integer ecForm12Id,
                                                                                   List<EcForm12ProjectActivityDetails> environmentClearanceProjectActivityDetails) {

        EcForm12 temp = ecForm12Repository.findById(ecForm12Id).get();

        List<EcForm12ProjectActivityDetails> projectActivityDetails = environmentClearanceProjectActivityDetails.stream()
                .map(value -> {
                    value.setEcForm12(temp);
                    value.setProposalNo(temp.getProposal_no());
                    value.setActivities(activityRepository.findById(value.getActivityId())
                            .orElseThrow(() -> new ProjectNotFoundException("activity not found for activity ID")));
                    value.setSubActivities(subActivityRepository.findById(value.getSubActivityId()).orElseThrow(
                            () -> new ProjectNotFoundException("subactivity not found for subactivity ID")));

                    return value;
                }).collect(Collectors.toList());
        List<EcForm12ProjectActivityDetails> environmentDetails = ecForm12ProjectActivityDetailsRepository
                .saveAll(projectActivityDetails);
        log.info(
                "INFO ------------ saveEnvironmentClearanceProjectActivityDetails Environment Clearance Project Activity Details WITH ecId ----> "
                        + ecForm12Id + " ----SAVED - SUCCESS");
        return environmentDetails;
    }

    public ResponseEntity<Object> getECProjectActivityData(int id) {
        log.info(
                "INFO ------------ getECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "
                        + id + " ---- FOUND and RETRIEVED - SUCCESS");
        return ResponseHandler.generateResponse("get environment data list by ec_id", HttpStatus.OK, "",
                ecForm12ProjectActivityDetailsRepository.findDetailByEcId(id));

    }

    public ResponseEntity<Object> deleteECProjectActivityData(int id) {

        EcForm12ProjectActivityDetails environmentClearanceProjectActivityDetails = ecForm12ProjectActivityDetailsRepository
                .findById(id).get();
        environmentClearanceProjectActivityDetails.setIsDelete(true);
        environmentClearanceProjectActivityDetails.setIsDelete(false);
        ecForm12ProjectActivityDetailsRepository.save(environmentClearanceProjectActivityDetails);
        log.info(
                "INFO ------------ deleteECProjectActivityData Environment Clearance Project Activity Data WITH EcID ----> "
                        + id + " ---- FOUND and DELETED - SUCCESS");
        return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
                "deleted successfully");

    }

    public ResponseEntity<Object> saveEcForm12TransfreeDetail(List<EcForm12TransfreeDetails> ecForm12TransfreeDetails, Integer ecform12Id) {
        try {
            EcForm12 temp = ecForm12Repository.findById(ecform12Id).get();
            List<EcForm12TransfreeDetails> ecForm12TransfreeDetails1 = ecForm12TransfreeDetails.stream()
                    .map(value -> {
                        value.setEcForm12(temp);
                        return value;
                    }).collect(Collectors.toList());
            List<EcForm12TransfreeDetails> ecForm12TransfreeDetails2 = ecForm12TransfreeDetailsRepository
                    .saveAll(ecForm12TransfreeDetails1);
            return ResponseHandler.generateResponse("Save Ec Form Transfree Details", HttpStatus.OK, null,
                    ecForm12TransfreeDetails2);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Form Transfree Details-----" + ecform12Id, e);
        }
    }

    public ResponseEntity<Object> deleteEcForm12TransfreeDetails(Integer id) {
        EcForm12TransfreeDetails ecForm12TransfreeDetails = ecForm12TransfreeDetailsRepository
                .findById(id).get();
        ecForm12TransfreeDetails.setIs_deleted(true);
        ecForm12TransfreeDetails.setIs_active(false);
        ecForm12TransfreeDetailsRepository.save(ecForm12TransfreeDetails);
        log.info(
                "INFO ------------ ecForm12TransfreeDetailsRepository Data WITH EcID ----> "
                        + id + " ---- FOUND and DELETED - SUCCESS");
        return ResponseHandler.generateResponse("delete environment data list", HttpStatus.OK, "",
                "deleted successfully");
    }

    public ResponseEntity<Object> saveEcForm12DetailsOfComponents(ECForm12DetailsOfComponents ecForm12DetailsOfComponents, Integer ec_id) {
        try {
            if (ecForm12DetailsOfComponents.getId() == null || ecForm12DetailsOfComponents.getId() == 0) {
                ECForm12DetailsOfComponents detailsOfComponents = ecForm12DetailsOfComponentsRepository.getFormById(ec_id);
                if (detailsOfComponents != null) {
                    ecForm12DetailsOfComponents.setId(detailsOfComponents.getId());
                }
            }
            EcForm12 ecForm12 = ecForm12Repository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 12 not found"));
            ecForm12DetailsOfComponents.setEcForm12(ecForm12);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Details of Component form 12 -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save EC Details of Component form 12", HttpStatus.OK, null,
                ecForm12DetailsOfComponentsRepository.save(ecForm12DetailsOfComponents));

    }

    public ResponseEntity<Object> saveEcForm12AddendumOfTransferor(ECForm12AddendumOfTransferor ecForm12AddendumOfTransferors, Integer ec_id) {
        try {
            if (ecForm12AddendumOfTransferors.getId() == null || ecForm12AddendumOfTransferors.getId() == 0) {
                ECForm12AddendumOfTransferor addendumOfTransferor = ecForm12AddendumOfTransferorRepository.getFormById(ec_id);
                if (addendumOfTransferor != null) {
                    ecForm12AddendumOfTransferors.setId(addendumOfTransferor.getId());
                }
            }
            EcForm12 ecForm12 = ecForm12Repository.getFormById(ec_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Ec form 12 not found"));
            ecForm12AddendumOfTransferors.setEcForm12(ecForm12);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Details of Component form 12 -----" + ec_id, e);
        }
        return ResponseHandler.generateResponse("Save EC Details of Component form 12", HttpStatus.OK, null,
                ecForm12AddendumOfTransferorRepository.save(ecForm12AddendumOfTransferors));

    }

    public ResponseEntity<Object> saveEcForm12AddendumOfTransferee(List<ECForm12AddendumOfTransferee> ecForm12AddendumOfTransferees, Integer ecId) {

        EcForm12 temp = ecForm12Repository.findById(ecId).get();

        List<ECForm12AddendumOfTransferee> addendumOfTransferees = ecForm12AddendumOfTransferees.stream()
                .map(value -> {
                    value.setEcForm12(temp);
                    return value;
                }).collect(Collectors.toList());
        List<ECForm12AddendumOfTransferee> addendumOfTransferees1 = ecForm12AddendumOfTransfereeRepository
                .saveAll(addendumOfTransferees);
        log.info(
                "INFO ------------ saveEnvironmentClearanceProjectActivityDetails Environment Clearance Project Activity Details WITH ecId ----> "
                        + ecId + " ----SAVED - SUCCESS");

        return ResponseHandler.generateResponse("Save EC Form 12", HttpStatus.OK, "", addendumOfTransferees1);

    }

    public ResponseEntity<Object> deleteEcForm12DetailsOfComponent(Integer id) {
        ECForm12DetailsOfComponents ecForm12DetailsOfComponents = ecForm12DetailsOfComponentsRepository
                .findById(id).get();
        ecForm12DetailsOfComponents.set_deleted(true);
        ecForm12DetailsOfComponents.set_active(false);
        ecForm12DetailsOfComponentsRepository.save(ecForm12DetailsOfComponents);
        log.info(
                "INFO ------------ ecForm12Details Of Components  ----> "
                        + id + " ---- FOUND and DELETED - SUCCESS");
        return ResponseHandler.generateResponse("delete ", HttpStatus.OK, "",
                "deleted successfully");
    }


    public ResponseEntity<Object> deleteEcForm12CafKMLSplitted(Integer id) {

        EcForm12CafKMLSplitted ecForm12CafKMLSplitted = ecForm12CafKMLSplittedRepository
                .findById(id).get();
        ecForm12CafKMLSplitted.set_deleted(true);
        ecForm12CafKMLSplitted.set_active(false);
        ecForm12CafKMLSplittedRepository.save(ecForm12CafKMLSplitted);
        log.info(
                "INFO ------------ delete ecForm12CafKMLSplitted Environment Clearance Project Activity Data WITH EcID ----> "
                        + id + " ---- FOUND and DELETED - SUCCESS");
        return ResponseHandler.generateResponse("delete ecForm12CafKMLSplitted", HttpStatus.OK, "",
                "deleted successfully");

    }
}
