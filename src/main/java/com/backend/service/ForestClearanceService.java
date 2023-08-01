package com.backend.service;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.AdditionalInformationDTO;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.*;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.WildLifeClearance.WildLifeClearanceRepository;
import com.backend.response.ResponseHandler;
import com.backend.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class ForestClearanceService {

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private ForestClearanceRepository forestClearanceRepository;

    @Autowired
    private ForestProposedLandRepository forestProposedLandRepository;

    @Autowired
    private FcAforestationDetailRepository fcAforestationDetailRepository;

    @Autowired
    private FcOthersDetailRepository fcOthersDetailRepository;

    @Autowired
    private FcEnclosuresRepository fcEnclosuresRepository;

    @Autowired
    private FcAdditionalInformationRepository fcAdditionalInformationRepository;

    @Autowired
    private ForestClearancePatchKmlsRepository forestClearancePatchKmlsRepository;

    @Autowired
    private ForestClearancePatchKmlDetailsRepository forestClearancePatchKmlDetailsRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private ForestClearanceAirportProposalRepository forestClearanceAirportProposalRepository;

    @Autowired
    private ForestClearanceRiverValleyRepository forestClearanceRiverValleyRepository;

    @Autowired
    private ForestClearanceMiningProposalsRepository forestClearanceMiningProposalsRepository;

    @Autowired
    private FcCroppingPatternRepository fcCroppingPatternRepository;

    @Autowired
    private FcIrrigationProjectCapacityVillagesRepository fcIrrigationProjectCapacityVillagesRepository;

    @Autowired
    private FcSubmergedAreaRepository fcSubmergedAreaRepository;

    @Autowired
    private AdditionalInformationRepository additionalInformationRepository;

    @Autowired
    private FCUndertakingRepository fcUndertakingRepository;

    @Autowired
    private WildLifeClearanceRepository wildLifeClearanceRepository;

    @Autowired
    private ForestClearanceLegalStatusRepository forestClearanceLegalStatusRepository;

    @Autowired
    private MiningProposalsRepository miningProposalsRepository;

    @Autowired
    private AirportProposalRepository airportProposalRepository;

    @Autowired
    private IrrigationProjectCapacityVillageRepository irrigationRepository;

    @Autowired
    private RiverValleyProjectRepository riverValleyProjectRepository;

    @Autowired
    private NotifyClient notifyClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerUtil util;

    @Autowired
    private NotificationService notificationSevice;

    @Autowired
    private ForestClearanceProposedDiversionsRepository forestClearanceProposedDiversionsRepository;

    @Autowired
    private ForestClearanceDivisionPatchDetailsRepository forestClearanceDivisionPatchDetailsRepository;

    @Autowired
    private ProponentApplicationService proponentApplicationService;

    @Autowired
    private MiningMineralOilProposalRepository miningMineralOilProposalRepository;

    public ForestClearance addForestClearanceForm(Integer caf_id, ForestClearance forestClearanceForm,
                                                  HttpServletRequest request) throws PariveshException {
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            CommonFormDetail temp = commonFormDetailRepository.findDetailByCafId(caf_id);
            forestClearanceForm.setCommonFormDetail(temp);
            if (forestClearanceForm.getId() != null && forestClearanceForm.getId() != 0) {
                ForestClearance form = forestClearanceRepository.findDetailByFcId(forestClearanceForm.getId());
                forestClearanceForm.setProposal_no(form.getProposal_no());
                log.info("INFO ------------ addForestClearanceForm WITH caf_id ----> " + caf_id
                        + " ---- ADDING FC FORM- SUCCESS");
//			return ResponseHandler.generateResponse("Save Common Form ",HttpStatus.OK,"",commonFormDetailRepository.save(commonFormDetail));
                return forestClearanceRepository.save(forestClearanceForm);
            }
            List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
            ProponentApplications applications = new ProponentApplications();
            if (tempClearances.isEmpty()) {

                String proposal_no = "FP/"
                        + stateRepository.getStateByCode(forestClearanceForm.getState())
                        .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id"))
                        .getState_abbr()
                        + "/" + forestClearanceForm.getProject_activity_id() + "/" + 400000 + "/"
                        + String.valueOf(LocalDate.now().getYear());

                /*
                 * String proposal_no = "FP/" +
                 * stateRepository.getStateById(forestClearanceForm.getState()).getState_abbr()
                 * + "/" + forestClearanceForm.getProject_activity_id() + "/" + 100000 + "/" +
                 * String.valueOf(LocalDate.now().getYear());
                 */
                applications.setProposal_sequence(400000);
                proposal_no = proposal_no.replaceAll("\\s", "");
                applications.setProposal_no(proposal_no);
                forestClearanceForm.setProposal_no(proposal_no);
            } else {
                Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                        .max(Comparator.comparing(Integer::valueOf)).get();
                if (maxCount != null) {
                    applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                    applications.setProposal_no(generateProposalNo(maxCount, forestClearanceForm));
                    forestClearanceForm.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
                }
            }
            ForestClearance temp2 = forestClearanceRepository.save(forestClearanceForm);
            Applications app = applicationsRepository.findById(1).get();
            applications.setCaf_id(caf_id);
            applications.setProposal_id(temp2.getId());

            /*
             * applications.setState(stateRepository.getStateById(forestClearanceForm.
             * getState()).getName());
             * applications.setState_id(stateRepository.getStateById(forestClearanceForm.
             * getState()).getId());
             */

            applications.setState(stateRepository.getStateByCode(forestClearanceForm.getState())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getName());
            applications.setState_id(stateRepository.getStateByCode(forestClearanceForm.getState())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state code")).getCode());
            applications.setProjectDetails(temp.getProjectDetails());
            applications.setApplications(app);
            applications.setLast_status(Caf_Status.DRAFT.toString());
            applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

            proponentApplicationRepository.save(applications);

//            /*
//             * Updating the Proponent Application JSON String
//             */
//
//            OtherPropString.put("Form", app.getDd_name());
//            OtherPropString.put("Project Category", forestClearanceForm.getProject_activity_id());
//            proponentApplicationService.updateOtherProperty(temp2.getId(), OtherPropString);

            return temp2;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in adding Forest Clearance Form for caf_id- " + caf_id, e);
        }
    }

    public ForestClearance addForestProposedLand(Integer fc_id, ForestProposedLand forestProposedLand)
            throws PariveshException {
        try {
            HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
            ForestClearance temp = forestClearanceRepository.findById(fc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Forest clearance form not found"));
            forestProposedLand.setForestClearance(temp);
            forestProposedLandRepository.save(forestProposedLand);

            /*
             * Updating the Proponent Application JSON String
             */
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());
            Applications app = proponentApplications.getApplications();
            OtherPropString.put("Form", app.getDd_name());

            String activity = temp.getProject_activity_id();
            if (activity.equalsIgnoreCase("Others")) {
                String fullActivityName = activity + "(" + temp.getProject_activity_id_other() + ")";
                OtherPropString.put("Project Category", fullActivityName);
            } else {
//                Integer entityId=Integer.parseInt(temp.getProject_category_id());
                OtherPropString.put("Project Category", proponentApplicationRepository.getProjectCategoryName(Integer.parseInt(temp.getProject_category_id())));
            }

            OtherPropString.put("Forest Area", forestProposedLand.getTotal_proposed_diversion_area().toString());
            proponentApplicationService.updateOtherProperty(fc_id, OtherPropString);

            return forestClearanceRepository.findById(fc_id).get();
        } catch (Exception e) {
            // log.error("=======================>>>>>>>>>>>{}" + e.toString() + " " +
            // e.getStackTrace()[0]);
            log.error("=======================>>>>>>>>>>>{}, {}", e.toString(), e.getStackTrace()[0]);
            throw new PariveshException("Error in adding Forest Proposed Land for fc_id- " + fc_id, e);
        }
    }

    public ForestClearance addAForestationDetail(Integer fc_id, FcAforestationDetails fcAforestationDetails)
            throws PariveshException {
        try {
            ForestClearance temp = forestClearanceRepository.findById(fc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Forest clearance form not found"));
            fcAforestationDetails.setForestClearance(temp);
            if (fcAforestationDetails.getCa_map_copy() != null) {
                fcAforestationDetails.getCa_map_copy().setProposal_no(temp.getProposal_no());
            }
            fcAforestationDetailRepository.save(fcAforestationDetails);
            return forestClearanceRepository.findById(fc_id).get();
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in addAForestationDetail for fc_id- " + fc_id, e);
        }
    }

    public ForestClearance addOthersDetail(Integer fc_id, FcOthersDetail fcOthersDetail) throws PariveshException {
        try {
            ForestClearance temp = forestClearanceRepository.findById(fc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Forest clearance form not found"));
            fcOthersDetail.setForestClearance(temp);
            if (fcOthersDetail.getCost_benefit_report() != null) {
                fcOthersDetail.getCost_benefit_report().setProposal_no(temp.getProposal_no());
            }
            if (fcOthersDetail.getPa_report() != null) {
                fcOthersDetail.getPa_report().setProposal_no(temp.getProposal_no());
            }
            if (fcOthersDetail.getEc_letter() != null) {
                fcOthersDetail.getEc_letter().setProposal_no(temp.getProposal_no());
            }
            if (fcOthersDetail.getEsz_report() != null) {
                fcOthersDetail.getEsz_report().setProposal_no(temp.getProposal_no());
            }
            fcOthersDetailRepository.save(fcOthersDetail);
            return forestClearanceRepository.findById(fc_id).get();
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in adding Others Detail for fc_id- " + fc_id, e);
        }
    }

    /*
     * public ForestClearance addAdditionalInformation(Integer fc_id,
     * FcAdditionalInformation fcAdditionalInformation) { ForestClearance temp =
     * forestClearanceRepository.findById(fc_id) .orElseThrow(() -> new
     * ProjectNotFoundException("Forest clearance form not found"));
     * fcAdditionalInformation.setForestClearance(temp); if
     * (fcAdditionalInformation.getCopy_additional_information() != null) {
     * fcAdditionalInformation.getCopy_additional_information().setProposal_no(temp.
     * getProposal_no()); }
     * fcAdditionalInformationRepository.save(fcAdditionalInformation);
     * log.info("INFO ------------ addAdditionalInformation WITH fc_id ----> " +
     * fc_id + " ---- ADDING ADDITIONAL INFO - SUCCESS"); return
     * forestClearanceRepository.findById(fc_id).get(); }
     */

    public ForestClearance addEnclosuresDetail(Integer fc_id, FcEnclosures fcEnclosures, HttpServletRequest request)
            throws PariveshException {
        try {
            if (fcEnclosures.getId() == null || fcEnclosures.getId() == 0) {
                FcEnclosures fcEnclosures2 = fcEnclosuresRepository.getRecordExist(fc_id).orElse(null);
                if (fcEnclosures2 != null) {
                    fcEnclosures.setId(fcEnclosures2.getId());
                }
            }
            ForestClearance temp = forestClearanceRepository.findById(fc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Forest clearance form not found"));
            fcEnclosures.setForestClearance(temp);
            if (fcEnclosures.getCopy_additional_information() != null) {
                fcEnclosures.getCopy_additional_information().setProposal_no(temp.getProposal_no());
            }
            if (fcEnclosures.getCopy_approval_accorded_moefcc() != null) {
                fcEnclosures.getCopy_approval_accorded_moefcc().setProposal_no(temp.getProposal_no());
            }
            if (fcEnclosures.getCopy_approved_mining_plan() != null) {
                fcEnclosures.getCopy_approved_mining_plan().setProposal_no(temp.getProposal_no());
            }
            if (fcEnclosures.getCopy_detailed_land_use_plan() != null) {
                fcEnclosures.getCopy_detailed_land_use_plan().setProposal_no(temp.getProposal_no());
            }
            if (fcEnclosures.getCopy_map_outer_boundary() != null) {
                fcEnclosures.getCopy_map_outer_boundary().setProposal_no(temp.getProposal_no());
            }
            if (fcEnclosures.getCopy_perspecting_licence() != null) {
                fcEnclosures.getCopy_perspecting_licence().setProposal_no(temp.getProposal_no());
            }
            if (fcEnclosures.getCopy_transportation_minerals_proposed() != null) {
                fcEnclosures.getCopy_transportation_minerals_proposed().setProposal_no(temp.getProposal_no());
            }

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());
            // proponentApplications.setLast_status(Caf_Status.SUBMITTED);
            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

            commonFormDetailService.saveCommonForm(temp.getCommonFormDetail());

            proponentApplicationRepository.save(proponentApplications);

            fcEnclosuresRepository.save(fcEnclosures);
            return forestClearanceRepository.findById(fc_id).get();
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in addEnclosuresDetail for fc_id- " + fc_id, e);
        }
    }

    public ForestClearance getForestClearanceForm(Integer id) throws PariveshException {
        try {
            ForestClearance form = forestClearanceRepository.findDetailByFcId(id);
            form.setProponentApplications(
                    proponentApplicationRepository.getApplicationByProposalId(form.getId()) != null
                            ? proponentApplicationRepository.getApplicationByProposalId(form.getId())
                            : null);
            return form;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting Forest Clearance Form for id- " + id, e);
        }
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, ForestClearance forestClearanceForm) {

        String cafId = "FP/" + stateRepository.getStateByCode(forestClearanceForm.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                + "/" + forestClearanceForm.getProject_activity_id() + "/" + generateSequenceNumber(maxcount) + "/"
                + String.valueOf(LocalDate.now().getYear());

        /*
         * String cafId = "FP/" +
         * stateRepository.getStateById(forestClearanceForm.getState()).getState_abbr()
         * + "/" + forestClearanceForm.getProject_activity_id() + "/" +
         * generateSequenceNumber(maxcount) + "/" +
         * String.valueOf(LocalDate.now().getYear());
         */
        // return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
        cafId = cafId.replaceAll("\\s", "");
        return cafId;
    }

    public ForestClearance getForestClearanceFormByProposalNo(String proposal_no) throws PariveshException {
        try {
            ProponentApplications applications = proponentApplicationRepository.getApplicationByProposalNo(proposal_no);
            ForestClearance form = forestClearanceRepository.findById(applications.getProposal_id()).get();
            return form;
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getForestClearanceFormByProposalNo for proposal_no- " + proposal_no,
                    e);
        }
    }

    // Forest Clearance Patch KML

    public ResponseEntity<Object> saveForestClearancePatchKML(Integer id,
                                                              List<ForestClearancePatchKmls> forestClearancePatchKmls) throws PariveshException {
        List<ForestClearancePatchKmls> listtemp = new ArrayList<>();
        try {
            ForestClearance temp = forestClearanceRepository.findDetailByFcId(id);
            for (ForestClearancePatchKmls patchkml : forestClearancePatchKmls) {
                patchkml.setForestClearance(temp);
                if (patchkml.getPatch_kml() != null) {
                    patchkml.getPatch_kml().setProposal_no(temp.getProposal_no());
                }
                ForestClearancePatchKmls temp1 = forestClearancePatchKmlsRepository.save(patchkml);
                listtemp.add(temp1);
            }
            return ResponseHandler.generateResponse("Save Patch KML", HttpStatus.OK, "", listtemp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in save Forest Clearance Patch KML for id- " + id, e);
        }
    }

    public ResponseEntity<Object> getForestClearancePatchKML(Integer id) throws PariveshException {
        try {

            return ResponseHandler.generateResponse("Get CAF KML", HttpStatus.OK, "",
                    forestClearancePatchKmlsRepository.getById(id));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getForestClearancePatchKML for caf_id- " + id, e);
        }
    }

    public ResponseEntity<Object> getForestClearancePatchKMLbyFCId(Integer id) throws PariveshException {

        try {
            List<ForestClearancePatchKmls> forestClearancePatchKmls = forestClearancePatchKmlsRepository.findByFCID(id);

            return ResponseHandler.generateResponse("Get Patch KML", HttpStatus.OK, "", forestClearancePatchKmls);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getForestClearancePatchKMLbyFCId for id- " + id, e);
        }
    }

    public ResponseEntity<Object> getForestClearancePatchKMLDetails(Integer id) throws PariveshException {
        try {

            return ResponseHandler.generateResponse("Save Patch KML Details", HttpStatus.OK, "",
                    forestClearancePatchKmlDetailsRepository.getById(id));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getForestClearancePatchKMLDetails for id- " + id, e);
        }
    }

    /*
     * From EC Part B
     */

    public ResponseEntity<Object> saveFcAirportProposalDetails(ForestClearanceAirportProposal fcAirportProposal,
                                                               Integer fcId, Integer wlcId) throws PariveshException {
        try {
            if (fcId != null) {
                ForestClearance fc = forestClearanceRepository.getById(fcId);
                fcAirportProposal.setForestClearance(fc);
            } else {
                WildLifeClearance wl = wildLifeClearanceRepository.getById(wlcId);
                fcAirportProposal.setWildLifeClearance(wl);
            }
//			AirportProposal airportProposal = new AirportProposal(fcAirportProposal);
//			airportProposalRepository.save(airportProposal);
            ForestClearanceAirportProposal fcAirportProposal2 = forestClearanceAirportProposalRepository
                    .save(fcAirportProposal);

            return ResponseHandler.generateResponse("Save Airport Proposal", HttpStatus.OK, "", fcAirportProposal2);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcAirportProposalDetails for fcId- " + fcId, e);
        }
    }

    public ResponseEntity<Object> deleteFcAirportProposalDetails(Integer airportId) throws PariveshException {
        try {
            ForestClearanceAirportProposal fcAirportProposal = forestClearanceAirportProposalRepository
                    .findById(airportId)
                    .orElseThrow(() -> new ProjectNotFoundException("Fc Airport Proposal not found"));

            fcAirportProposal.setIs_deleted(true);
//			AirportProposal airportProposal = new AirportProposal(fcAirportProposal);
//			airportProposalRepository.save(airportProposal);
            ForestClearanceAirportProposal temp = forestClearanceAirportProposalRepository.save(fcAirportProposal);

            return ResponseHandler.generateResponse("Delete Airport Proposal", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteFcAirportProposalDetails for airportId- " + airportId, e);
        }
    }

    public ResponseEntity<Object> saveFcRiverValleyProject(ForestClearanceRiverValleyProject fcRiverValleyProject,
                                                           Integer fcId, Integer wlcId) throws PariveshException {
        try {
            if (fcId != null) {
                ForestClearance fc = forestClearanceRepository.getById(fcId);
                fcRiverValleyProject.setForestClearance(fc);
            } else {
                WildLifeClearance wl = wildLifeClearanceRepository.getById(wlcId);
                fcRiverValleyProject.setWildLifeClearance(wl);
            }

//			RiverValleyProject riverValleyProject = new RiverValleyProject(fcRiverValleyProject);
//			riverValleyProjectRepository.save(riverValleyProject);

            ForestClearanceRiverValleyProject fcRiverValleyProject2 = forestClearanceRiverValleyRepository
                    .save(fcRiverValleyProject);

            return ResponseHandler.generateResponse("Save River Valley Project", HttpStatus.OK, "",
                    fcRiverValleyProject2);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcRiverValleyProject for fcId- " + fcId, e);
        }
    }

    public ResponseEntity<Object> deleteFcRiverValleyProject(Integer id) throws PariveshException {
        try {
            ForestClearanceRiverValleyProject fcRiverValleyProject = forestClearanceRiverValleyRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("River Valley Project not found"));

            fcRiverValleyProject.setIs_deleted(true);
            log.info("INFO ------------ deleteFcRiverValleyProject WITH forestClearanceRiverValley_ID ----> " + id
                    + "--- DELETING FC RIVER VALLEY PROJECT- SUCCESS");
            ForestClearanceRiverValleyProject temp = forestClearanceRiverValleyRepository.save(fcRiverValleyProject);

//			RiverValleyProject riverValleyProject = new RiverValleyProject(fcRiverValleyProject);
//			riverValleyProjectRepository.save(riverValleyProject);

            return ResponseHandler.generateResponse("Delete River Valley Project", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteFcRiverValleyProject for Id- " + id, e);
        }
    }

    public ResponseEntity<Object> saveFcMiningProposals(Integer fcId, Integer wlcId,
                                                        ForestClearanceMiningProposals fcMiningProposals) throws PariveshException {
        try {
            if (fcId != null) {
                ForestClearance fc = forestClearanceRepository.findById(fcId)
                        .orElseThrow(() -> new ProjectNotFoundException("Fc form not found"));
                fcMiningProposals.setForestClearance(fc);
            } else {
                WildLifeClearance wlc = wildLifeClearanceRepository.findById(wlcId)
                        .orElseThrow(() -> new ProjectNotFoundException("Fc part b form not found"));
                fcMiningProposals.setWildLifeClearance(wlc);
            }
//			MiningProposals miningProposals = new MiningProposals(fcMiningProposals);
//			miningProposalsRepository.save(miningProposals);
            return ResponseHandler.generateResponse("Save Mining Proposals Project", HttpStatus.OK, "",
                    forestClearanceMiningProposalsRepository.save(fcMiningProposals));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcMiningProposals for fcId- " + fcId, e);
        }
    }

    public ResponseEntity<Object> saveFcCroppingPattern(List<ForestClearanceCroppingPattern> fcCroppingPatterns,
                                                        Integer fcId, Integer wlcId) throws PariveshException {
        try {
            List<ForestClearanceCroppingPattern> fcCroppingPattern2;

            if (fcId != null) {
                ForestClearance forestClearance = forestClearanceRepository.getById(fcId);

                fcCroppingPattern2 = fcCroppingPatterns.stream().map(val -> {
                    val.setForestClearance(forestClearance);
                    return val;
                }).collect(Collectors.toList());
            } else {
                WildLifeClearance wildLifeClearance = wildLifeClearanceRepository.getById(wlcId);

                fcCroppingPattern2 = fcCroppingPatterns.stream().map(val -> {
                    val.setWildLifeClearance(wildLifeClearance);
                    return val;
                }).collect(Collectors.toList());
            }

            List<ForestClearanceCroppingPattern> fcCroppingPattern3 = fcCroppingPatternRepository
                    .saveAll(fcCroppingPattern2);

            return ResponseHandler.generateResponse("Save Cropping Pattern", HttpStatus.OK, "", fcCroppingPattern3);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcCroppingPattern for fcId- " + fcId, e);
        }
    }

    public ResponseEntity<Object> deleteFcCroppingPattern(Integer id) throws PariveshException {
        try {
            ForestClearanceCroppingPattern fcCroppingPattern = fcCroppingPatternRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Fc  CMSWMF Proposals not found"));

            fcCroppingPattern.setIs_active(false);
            fcCroppingPattern.setIs_deleted(true);

            ForestClearanceCroppingPattern temp = fcCroppingPatternRepository.save(fcCroppingPattern);
            return ResponseHandler.generateResponse("Delete Cropping Pattern", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteFcCroppingPattern for Id- " + id, e);
        }
    }

    public ResponseEntity<Object> saveFcIrrigationProjectCapacityVillage(
            List<ForestClearanceIrrigationProjectCapacityVillages> fcIrrigationProjectCapacityVillages, Integer fcId,
            Integer wlcId) throws PariveshException {
        try {
            List<ForestClearanceIrrigationProjectCapacityVillages> fcIrrigationProjectCapacity2;

            if (fcId != null) {
                ForestClearance forestClearance = forestClearanceRepository.getById(fcId);

                fcIrrigationProjectCapacity2 = fcIrrigationProjectCapacityVillages.stream().map(val -> {
                    val.setForestClearance(forestClearance);
                    return val;
                }).collect(Collectors.toList());
            } else {
                WildLifeClearance wildLifeClearance = wildLifeClearanceRepository.getById(wlcId);

                fcIrrigationProjectCapacity2 = fcIrrigationProjectCapacityVillages.stream().map(val -> {
                    val.setWildLifeClearance(wildLifeClearance);
                    return val;
                }).collect(Collectors.toList());
            }

            List<ForestClearanceIrrigationProjectCapacityVillages> fcIrrigationProjectCapacityVillage3 = fcIrrigationProjectCapacityVillagesRepository
                    .saveAll(fcIrrigationProjectCapacity2);

//			List<IrrigationProjectCapacityVillage> irrigationProjectCapacityVillage = (List<IrrigationProjectCapacityVillage>) new IrrigationProjectCapacityVillage(
//					fcIrrigationProjectCapacity2, fcId);
//
//			irrigationRepository.saveAll(irrigationProjectCapacityVillage);

            return ResponseHandler.generateResponse("Save Irrigation project capacity village", HttpStatus.OK, "",
                    fcIrrigationProjectCapacityVillage3);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcIrrigationProjectCapacityVillage for fcId- " + fcId, e);
        }
    }

    public ResponseEntity<Object> deleteFcIrrigationProjectCapacityVillage(Integer id) throws PariveshException {
        try {
            ForestClearanceIrrigationProjectCapacityVillages fcIrrigationProjectCapacityVillage = fcIrrigationProjectCapacityVillagesRepository
                    .findById(id).orElseThrow(() -> new ProjectNotFoundException("Fc  Capacity Village  not found"));

            fcIrrigationProjectCapacityVillage.setIs_deleted(true);
            fcIrrigationProjectCapacityVillage.setIs_active(false);

            ForestClearanceIrrigationProjectCapacityVillages temp = fcIrrigationProjectCapacityVillagesRepository
                    .save(fcIrrigationProjectCapacityVillage);

//			IrrigationProjectCapacityVillage irrigationProjectCapacityVillage = new IrrigationProjectCapacityVillage(
//					fcIrrigationProjectCapacityVillage);
//
//			irrigationRepository.save(irrigationProjectCapacityVillage);

            return ResponseHandler.generateResponse("Delete Irrigation project capacity village", HttpStatus.OK, "",
                    temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteFcIrrigationProjectCapacityVillage for Id- " + id, e);
        }
    }

    public ResponseEntity<Object> saveFcSubmergedArea(List<ForestClearanceSubmergedArea> fcSubmergedAreas, Integer fcId,
                                                      Integer wlcId) throws PariveshException {
        try {
            List<ForestClearanceSubmergedArea> fcSubmergedArea2;

            if (fcId != null) {
                ForestClearance forestClearance = forestClearanceRepository.getById(fcId);

                fcSubmergedArea2 = fcSubmergedAreas.stream().map(val -> {
                    val.setForestClearance(forestClearance);
                    return val;
                }).collect(Collectors.toList());
            } else {
                WildLifeClearance wildLifeClearance = wildLifeClearanceRepository.getById(wlcId);

                fcSubmergedArea2 = fcSubmergedAreas.stream().map(val -> {
                    val.setWildLifeClearance(wildLifeClearance);
                    return val;
                }).collect(Collectors.toList());
            }

            List<ForestClearanceSubmergedArea> fcSubmergedArea3 = fcSubmergedAreaRepository.saveAll(fcSubmergedArea2);

            return ResponseHandler.generateResponse("Save Submerged Area", HttpStatus.OK, "", fcSubmergedArea3);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcSubmergedArea for fcId- " + fcId, e);
        }
    }

    public ResponseEntity<Object> deleteFcSubmergedArea(Integer id) throws PariveshException {
        try {
            ForestClearanceSubmergedArea fcSubmergedArea = fcSubmergedAreaRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Submerged Area  not found"));

            fcSubmergedArea.setIs_deleted(true);
            fcSubmergedArea.setIs_active(false);

            ForestClearanceSubmergedArea temp = fcSubmergedAreaRepository.save(fcSubmergedArea);

            return ResponseHandler.generateResponse("Delete Submerged Area", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteFcSubmergedArea for Id- " + id, e);
        }
    }
    /*
     * public ResponseEntity<Object> saveAdditionalInformationArea(
     * List<AdditionalInformation> additionalInformations, Integer fcId, Integer
     * wlcId) { try { List<AdditionalInformation> additionalInformations2 = new
     * ArrayList<>(); if (fcId != null || wlcId != null) { if (fcId != null) {
     * ForestClearance forestClearance = forestClearanceRepository.findById(fcId)
     * .orElseThrow(() -> new ProjectNotFoundException("FC Id not found"));
     *
     * additionalInformations2 = additionalInformations.stream().map(val -> {
     * val.setForestClearance(forestClearance); return val;
     * }).collect(Collectors.toList()); } else { WildLifeClearance wildLifeClearance
     * = wildLifeClearanceRepository.findById(wlcId) .orElseThrow(() -> new
     * ProjectNotFoundException("FC Id not found"));
     *
     * additionalInformations2 = additionalInformations.stream().map(val -> {
     * val.setWildLifeClearance(wildLifeClearance); return val;
     * }).collect(Collectors.toList()); } } else { additionalInformations2 =
     * additionalInformations; }
     * log.info("INFO ------------ saveAdditionalInformationArea WITH fcId ----> " +
     * fcId + " ----wlcId---->" + wlcId +
     * "------ SAVING FC ADDITIONAL INFORMATION AREA- SUCCESS");
     * List<AdditionalInformation> additionalInformations3 =
     * forestAdditionalInformationRepository .saveAll(additionalInformations2);
     *
     * return ResponseHandler.generateResponse("Save Additional Information",
     * HttpStatus.OK, "", additionalInformations3); } catch (Exception ex) { log.
     * info("ERROR ------------ INTERNAL_SERVER_ERROR: saveAdditionalInformationArea WITH fcId ----> "
     * + fcId + " ----wlcId---->" + wlcId +
     * "------ SAVING FC ADDITIONAL INFORMATION AREA- FAILURE"); return
     * ResponseHandler.generateResponse("Save Additional Information",
     * HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage()); } }
     *
     * public ResponseEntity<Object> deleteAdditionalInformation(Integer id) { try {
     * AdditionalInformation forestAdditionalInformation =
     * forestAdditionalInformationRepository.findById(id) .orElseThrow(() -> new
     * ProjectNotFoundException("Additional Information not found"));
     *
     * forestAdditionalInformation.setIs_deleted(true);
     * forestAdditionalInformation.setIs_active(false); log.
     * info("INFO ------------ deleteAdditionalInformation WITH forestAdditionalInformation_ID ---->"
     * + id + "------ DELETING FC ADDITIONAL INFORMATION- SUCCESS");
     * AdditionalInformation temp =
     * forestAdditionalInformationRepository.save(forestAdditionalInformation);
     *
     * return ResponseHandler.generateResponse("Delete Submerged Area",
     * HttpStatus.OK, "", temp); } catch (Exception ex) { log.info(
     * "ERROR ------------ INTERNAL_SERVER_ERROR: deleteAdditionalInformation WITH forestAdditionalInformation_ID ---->"
     * + id + "------ DELETING FC ADDITIONAL INFORMATION- FAILURE"); return
     * ResponseHandler.generateResponse("Delete Submerged Area",
     * HttpStatus.INTERNAL_SERVER_ERROR, "", ex.getMessage()); } }
     */

    public ResponseEntity<Object> addUndertaking(Integer fc_id, FCUndertaking fcUndertaking, Boolean is_submit,
                                                 HttpServletRequest request) throws PariveshException {
        try {
            if (fcUndertaking.getId() == null || fcUndertaking.getId() == 0) {
                FCUndertaking fcUndertaking2 = fcUndertakingRepository.getRecordExist(fc_id).orElse(null);
                if (fcUndertaking2 != null) {
                    fcUndertaking.setId(fcUndertaking2.getId());
                }
            }
            ForestClearance temp = forestClearanceRepository.findById(fc_id)
                    .orElseThrow(() -> new ProjectNotFoundException("Forest clearance form not found"));
            fcUndertaking.setForestClearance(temp);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());
//			if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.toString())) {
//				proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
//				proponentApplications.setMigration_status(false);
//			} else {
//				proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
//			}
            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())) {
                if (fcUndertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(fcUndertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }
            commonFormDetailService.saveCommonForm(temp.getCommonFormDetail());

            proponentApplicationRepository.save(proponentApplications);

            fcUndertakingRepository.save(fcUndertaking);

            ForestClearance forestClearance = forestClearanceRepository.findById(fc_id).get();

            if (is_submit == true) {
                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());

                notificationSevice.sendProposalEmail(proponentApplications.getProposal_no());
            }

            return ResponseHandler.generateResponse("Save Forest Clearance ", HttpStatus.OK, "", forestClearance);

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in addUndertaking for fcId- " + fc_id, e);
        }
    }

    /*
     * public ResponseEntity<Object> getAdditionalInformationbyFCId(Integer fcId,
     * Integer wlcId, String ref_id) {
     *
     * try { List<AdditionalInformation> forestAdditionalInformations; if (fcId !=
     * null || wlcId != null) { if (fcId != null) { forestAdditionalInformations =
     * forestAdditionalInformationRepository .findAdditionalInformationByFCID(fcId);
     * } else { forestAdditionalInformations = forestAdditionalInformationRepository
     * .findAdditionalInformationByWLCID(wlcId); } } else {
     * forestAdditionalInformations = forestAdditionalInformationRepository
     * .findAdditionalInformationByRefId(ref_id); }
     * log.info("INFO ------------ getAdditionalInformationbyFCId WITH fcId ----> "
     * + fcId + " ----wlcId---->" + wlcId +
     * "------ RETRIEVING FC ADDITIONAL INFORMATION- SUCCESS"); return
     * ResponseHandler.generateResponse("Get FC Additional Information",
     * HttpStatus.OK, "", forestAdditionalInformations); } catch (Exception ex) {
     * log.info("ERROR ------------ getAdditionalInformationbyFCId WITH fcId ----> "
     * + fcId + " ----wlcId---->" + wlcId +
     * "------ RETRIEVING FC ADDITIONAL INFORMATION- FAILURE"); return
     * ResponseHandler.generateResponse("Get FC Additional Information",
     * HttpStatus.EXPECTATION_FAILED, "Exception", ex.getMessage()); }
     *
     * }
     */

    public ResponseEntity<Object> getAdditionalInformationbyFCId(String ref_id, Boolean isSpecialDocument)
            throws PariveshException {

        try {
            List<AdditionalInformation> forestAdditionalInformations;

            forestAdditionalInformations = additionalInformationRepository.findAdditionalInformationByRefId(ref_id,
                    isSpecialDocument);

            return ResponseHandler.generateResponse("Get FC Additional Information", HttpStatus.OK, "",
                    forestAdditionalInformations);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getAdditionalInformationbyFCId for ref_Id- " + ref_id, e);
        }

    }

    public ResponseEntity<Object> getAdditionalInformationbyFCIdv2(String ref_id, Boolean isSpecialDocument)
            throws PariveshException {

        try {
            List<AdditionalInformation> forestAdditionalInformations;

            forestAdditionalInformations = additionalInformationRepository.findAdditionalInformationByRefId(ref_id,
                    isSpecialDocument);

            List<AdditionalInformationDTO> additionalInformationDTO=new ArrayList<>();
            for (AdditionalInformation item: forestAdditionalInformations) {
                AdditionalInformationDTO additionalInformationDTO1 = new AdditionalInformationDTO();
                additionalInformationDTO1.setAdditionalInformation(item);
                additionalInformationDTO1.setUsername(additionalInformationRepository.findusername(item.getCreated_by()));
                additionalInformationDTO.add(additionalInformationDTO1);
            }



            return ResponseHandler.generateResponse("Get FC Additional Information", HttpStatus.OK, "",
                    additionalInformationDTO);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getAdditionalInformationbyFCId for ref_Id- " + ref_id, e);
        }

    }

    public ResponseEntity<Object> saveAdditionalInformationArea(List<AdditionalInformation> additionalInformations)
            throws PariveshException {
        try {

            List<AdditionalInformation> additionalInformations3 = additionalInformationRepository
                    .saveAll(additionalInformations);

            return ResponseHandler.generateResponse("Save Additional Information", HttpStatus.OK, "",
                    additionalInformations3);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveAdditionalInformationArea ", e);
        }
    }

    public ResponseEntity<Object> deleteAdditionalInformation(Integer id) throws PariveshException {
        try {
            AdditionalInformation forestAdditionalInformation = additionalInformationRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Additional Information not found"));

            forestAdditionalInformation.setIs_deleted(true);
            forestAdditionalInformation.setIs_active(false);
//			log.info("INFO ------------ deleteAdditionalInformation WITH forestAdditionalInformation_ID ---->" + id
//					+ "------ DELETING FC ADDITIONAL INFORMATION- SUCCESS");
            AdditionalInformation temp = additionalInformationRepository.save(forestAdditionalInformation);

            return ResponseHandler.generateResponse("Delete Submerged Area", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveFcAirportProposalDetails for Id- " + id, e);
        }
    }

    public ResponseEntity<Object> updateDivisionPatchDetails(Integer id,
                                                             List<ForestClearanceDivisionPatchDetails> forestClearanceDivisionPatchDetails) {
        try {
            ForestClearanceProposedDiversions forestClearanceProposedDiversions = forestClearanceProposedDiversionsRepository
                    .findById(id).orElseThrow();
            List<ForestClearanceDivisionPatchDetails> forestClearanceDivisionPatchDetails2 = forestClearanceDivisionPatchDetails
                    .stream().map(value -> {
                        value.setForestClearanceProposedDiversions(forestClearanceProposedDiversions);
                        return value;
                    }).collect(Collectors.toList());
            return ResponseHandler.generateResponse("updateDivisionPatchDetails", HttpStatus.OK, null,
                    forestClearanceDivisionPatchDetailsRepository.saveAll(forestClearanceDivisionPatchDetails2));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in updateDivisionPatchDetails id- " + id, e);
        }
    }

    public ResponseEntity<Object> deleteLegalStatus(Integer id) {

        try {
            Integer upadate = forestClearanceLegalStatusRepository.updateFcLegalStatusById(id);
            if (upadate == 0) {
                throw new PariveshException("ID NOT FOUND - " + id);
            }
            return ResponseHandler.generateResponse("deleteLegalStatus", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteLegalStatus id- " + id, e);
        }

    }

    public ResponseEntity<Object> saveMiningMineralOilProposal(Integer fcId,
                                                               MiningMineralOilProposal miningMineralOilProposal) {
        try {
            ForestClearance fc = forestClearanceRepository.findById(fcId)
                    .orElseThrow(() -> new ProjectNotFoundException("Fc form not found"));

            Integer mmop_id = forestClearanceRepository.getMiningMineralOilProposal(fcId);
            if (mmop_id != null) {
                miningMineralOilProposal.setId(mmop_id);
            }
            miningMineralOilProposal.setForestClearance(fc);
            return ResponseHandler.generateResponse("Save Mining Proposals Project", HttpStatus.OK, "",
                    miningMineralOilProposalRepository.save(miningMineralOilProposal));
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in miningMineralOilProposal for fcId- " + fcId, e);
        }
    }

    public ResponseEntity<Object> deleteMiningMineralOilReserve(Integer id) {

        try {
            miningMineralOilProposalRepository.deleteReserve(id);

            return ResponseHandler.generateResponse("deleteMiningMineralOilReserve", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteMiningMineralOilReserve id- " + id, e);
        }

    }

    public ResponseEntity<Object> deleteMiningMineralOilExtracted(Integer id) {

        try {
            miningMineralOilProposalRepository.deleteExtracted(id);

            return ResponseHandler.generateResponse("deleteMiningMineralOilExtracted", HttpStatus.OK, "", "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteMiningMineralOilExtracted id- " + id, e);
        }

    }

    public ResponseEntity<Object> deleteMiningMineralOilEstimatedReserve(Integer id) {

        try {
            miningMineralOilProposalRepository.deleteEstimatedReserve(id);

            return ResponseHandler.generateResponse("deleteMiningMineralOilEstimatedReserve", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteMiningMineralOilEstimatedReserve id- " + id, e);
        }

    }

    public ResponseEntity<Object> deleteMiningMineralOilProductionDetail(Integer id) {

        try {
            miningMineralOilProposalRepository.deleteProductionDetail(id);

            return ResponseHandler.generateResponse("deleteMiningMineralOilProductionDetail", HttpStatus.OK, "",
                    "Deleted");
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in deleteMiningMineralOilProductionDetail id- " + id, e);
        }

    }

    public ResponseEntity<Object> getMiningMineralOilProposal(Integer id) {

        try {
            MiningMineralOilProposal miningMineralOilProposal = miningMineralOilProposalRepository.getByFc(id);
            return ResponseHandler.generateResponse("Retrieved", HttpStatus.OK, "no error", miningMineralOilProposal);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseHandler.generateResponse("Retrieved", HttpStatus.NO_CONTENT, "error",
                    e.getLocalizedMessage());
        }

    }

}