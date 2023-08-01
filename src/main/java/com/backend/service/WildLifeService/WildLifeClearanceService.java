package com.backend.service.WildLifeService;

import com.backend.client.NotifyClient;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.dto.ECObtainedDTO;
import com.backend.dto.FCGratedDTO;
import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.AdditionalInformation;
import com.backend.model.Applications;
import com.backend.model.CommonFormDetail;
import com.backend.model.ProponentApplications;
import com.backend.model.State;
import com.backend.model.WildLifeClearance.*;
import com.backend.repository.postgres.*;
import com.backend.repository.postgres.WildLifeClearance.*;
import com.backend.response.ResponseHandler;
import com.backend.service.CommonFormDetailService;
import com.backend.service.NotificationService;
import com.backend.service.UserService;
import com.backend.util.ServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class WildLifeClearanceService {

    @Autowired
    private CommonFormDetailRepository commonFormDetailRepository;

    @Autowired
    private WildLifeClearanceRepository wildLifeClearanceRepository;

    @Autowired
    private WLProposedLandRepository wlProposedLandRepository;

    @Autowired
    private WLOtherDetailsRepository wlOtherDetailsRepository;

    @Autowired
    private WLEnclosuresRepository wlEnclosuresRepository;

    @Autowired
    private WLUndertakingRepository wlUndertakingRepository;

    @Autowired
    private WLDivisionLandDetailRepository wlandDetailRepository;

    @Autowired
    private WLComponentWiseDetailsRepository wlComponentWiseDetailsRepository;

    @Autowired
    private CommonFormDetailService commonFormDetailService;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private NotifyClient notifyClient;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerUtil util;

    @Autowired
    private NotificationService notificationSevice;

    @Autowired
    EntityManager entityManager;

    @Autowired
    WLLinearProjectLandDetailsRepository wlLinearProjectLandDetailsRepository;

    @Autowired
    UserService userService;

    @Autowired
    WildLifeClearanceAuditService wildLifeClearanceAuditService;

    @Autowired
    WildLifeClearanceAuditRepository wildLifeClearanceAuditRepository;
    
    @Autowired
    WLAdditionalInformationRepository wlAdditionalInformationRepository;
  

    public WildLifeClearance addWildLifeClearanceForm(Integer caf_id, WildLifeClearance wildLifeClearanceForm,
                                                      HttpServletRequest request) {

        System.out.println("cafid" + caf_id + "wls for " + wildLifeClearanceForm);
        CommonFormDetail temp = commonFormDetailRepository.findDetailByCafId(caf_id);
        wildLifeClearanceForm.setCommonFormDetail(temp);

        int size = 18;
        int stateCode = wildLifeClearanceForm.getState();
        State state = stateRepository.getByStateCode(stateCode);

        String stateAbbr = state.getState_abbr();
        String uniqueClearanceNo = generateUniqueClearanceNo(size, stateAbbr);
        wildLifeClearanceForm.setUnique_clearance_no(uniqueClearanceNo);


        String greenClearanceNo = generateGreenClearanceNo();
        wildLifeClearanceForm.setGreen_clearance_no(greenClearanceNo);


        if (wildLifeClearanceForm.getId() != null && wildLifeClearanceForm.getId() != 0) {
            WildLifeClearance form = wildLifeClearanceRepository.findById(wildLifeClearanceForm.getId())
                    .orElseThrow(() -> new ProjectNotFoundException("Forest clearance form not found"));
            wildLifeClearanceForm.setProposal_no(form.getProposal_no().replaceAll("\\s", ""));


            return wildLifeClearanceRepository.save(wildLifeClearanceForm);
        }
        List<ProponentApplications> tempClearances = proponentApplicationRepository.findAll();
        ProponentApplications applications = new ProponentApplications();
        if (tempClearances.isEmpty()) {

//			String proposal_no = "FP/"
//					+ stateRepository.getStateByCode(wildLifeClearanceForm.getState())
//							.orElseThrow(() -> new ProjectNotFoundException("state data not found for state id"))
//							.getState_abbr()
//					+ "/" + wildLifeClearanceForm.getCategory_of_project() + "/" + 400000 + "/"
//					+ String.valueOf(LocalDate.now().getYear());

            String proposal_no = null;
            if (wildLifeClearanceForm.getCategory_of_project() != null) {
                proposal_no = "FP/"
                        + stateRepository.getStateByCode(wildLifeClearanceForm.getState())
                        .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id"))
                        .getState_abbr()
                        + "/" + wildLifeClearanceForm.getCategory_of_project() + "/" + 400000 + "/"
                        + String.valueOf(LocalDate.now().getYear());
            } else {
                proposal_no = "FP/"
                        + stateRepository.getStateByCode(wildLifeClearanceForm.getState())
                        .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id"))
                        .getState_abbr()
                        + "/" + "SRY" + "/" + 400000 + "/"
                        + String.valueOf(LocalDate.now().getYear());
            }


            /*
             * String proposal_no = "FP/" +
             * stateRepository.getStateById(wildLifeClearanceForm.getState()) + "/" +
             * wildLifeClearanceForm.getCategory_of_project() + "/" + 100000 + "/" +
             * String.valueOf(LocalDate.now().getYear());
             */
            applications.setProposal_sequence(400000);
            proposal_no = proposal_no.replaceAll("\\s", "");
            applications.setProposal_no(proposal_no);
            wildLifeClearanceForm.setProposal_no(proposal_no);
        } else {
            Integer maxCount = tempClearances.stream().map(e -> e.getProposal_sequence())
                    .max(Comparator.comparing(Integer::valueOf)).get();
            if (maxCount != null) {
                applications.setProposal_sequence(Integer.valueOf(generateSequenceNumber(maxCount)));
                applications.setProposal_no(generateProposalNo(maxCount, wildLifeClearanceForm));
                wildLifeClearanceForm.setProposal_no(applications.getProposal_no().replaceAll("\\s", ""));
            }
        }

        //added on 25112022
        System.out.println("------- " + wildLifeClearanceForm.getFor_investigation_survey_new());


        System.out.println("cafid" + caf_id + "wls for after " + wildLifeClearanceForm);

        WildLifeClearance temp2 = wildLifeClearanceRepository.save(wildLifeClearanceForm);
        Applications app = applicationsRepository.findById(3).get();
        applications.setCaf_id(caf_id);
        applications.setProposal_id(temp2.getId());
        applications.setState(stateRepository.getStateByCode(wildLifeClearanceForm.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getName());
        /*
         * applications.setState_id(stateRepository.getStateByCode(wildLifeClearanceForm
         * .getState()) .orElseThrow(() -> new
         * ProjectNotFoundException("state data not found for state id")).getId());
         */

        applications.setState_id(stateRepository.getStateByCode(wildLifeClearanceForm.getState())
                .orElseThrow(() -> new ProjectNotFoundException("state data not found for state code")).getCode());
        applications.setProjectDetails(temp.getProjectDetails());
        applications.setApplications(app);
        applications.setLast_status(Caf_Status.DRAFT.toString());
        applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
        proponentApplicationRepository.save(applications);
        return temp2;
    }

    public ResponseEntity<Object> addWLProposedLand(Integer wl_id, WLProposedLand wlProposedLand) {
        try {
        	
        
            WildLifeClearance temp = wildLifeClearanceRepository.findById(wl_id)
                    .orElseThrow(() -> new ProjectNotFoundException("WildLife clearance form not found"));
            wlProposedLand.setWildLifeClearance(temp);
            
            WLProposedLand proposedLand=wlProposedLandRepository.findDetailByWlId(wl_id);
            if(proposedLand!=null)
            {
            	wlProposedLand.setId(proposedLand.getId());
            	   List<WLDivisionLandDetail> wldivisionLandDetails = wlProposedLand.getWldivisionLandDetails();
                   for (WLDivisionLandDetail wldivisionLandDetail : wldivisionLandDetails) {
                       wldivisionLandDetail.setWlclearance(temp);
                       wldivisionLandDetail.setWlProposedLand(wlProposedLand);
                   }

                   List<WLComponentWiseDetails> wlComponentWiseDetails = wlProposedLand.getWlComponentWiseDetails();
                   for (WLComponentWiseDetails wlComponentWiseDetail : wlComponentWiseDetails) {
                       wlComponentWiseDetail.setWlclearance(temp);
                       wlComponentWiseDetail.setWlProposedLand(wlProposedLand);
                   }
                   List<WLLinearProjectLandDetails> wlLinearProjectLandDetails = wlProposedLand.getWlComponentWiseDetailsLinear();
                   for (WLLinearProjectLandDetails wlLinearProjectLandDetail : wlLinearProjectLandDetails) {
                       wlLinearProjectLandDetail.setWlclearance(temp);
                       wlLinearProjectLandDetail.setWlProposedLand(wlProposedLand);
                   }

                   if (wlProposedLand.getAnimal_passage_plan() != null) {
                       wlProposedLand.getAnimal_passage_plan().setProposal_no(temp.getProposal_no());
                   }
   	
            }
            else
            {
                List<WLDivisionLandDetail> wldivisionLandDetails = wlProposedLand.getWldivisionLandDetails();
                for (WLDivisionLandDetail wldivisionLandDetail : wldivisionLandDetails) {
                    wldivisionLandDetail.setWlclearance(temp);
                    wldivisionLandDetail.setWlProposedLand(wlProposedLand);
                }

                List<WLComponentWiseDetails> wlComponentWiseDetails = wlProposedLand.getWlComponentWiseDetails();
                for (WLComponentWiseDetails wlComponentWiseDetail : wlComponentWiseDetails) {
                    wlComponentWiseDetail.setWlclearance(temp);
                    wlComponentWiseDetail.setWlProposedLand(wlProposedLand);
                }
                List<WLLinearProjectLandDetails> wlLinearProjectLandDetails = wlProposedLand.getWlComponentWiseDetailsLinear();
                for (WLLinearProjectLandDetails wlLinearProjectLandDetail : wlLinearProjectLandDetails) {
                    wlLinearProjectLandDetail.setWlclearance(temp);
                    wlLinearProjectLandDetail.setWlProposedLand(wlProposedLand);
                }

                if (wlProposedLand.getAnimal_passage_plan() != null) {
                    wlProposedLand.getAnimal_passage_plan().setProposal_no(temp.getProposal_no());
                }
	
            }
        
      
            wlProposedLandRepository.save(wlProposedLand);
            return ResponseHandler.generateResponse("Save WL Proposed Land", HttpStatus.OK, "",
                    wildLifeClearanceRepository.findById(wl_id).get());
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Save WL Proposed Land", HttpStatus.BAD_REQUEST, "",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> addOthersDetail(Integer wl_id, WLOtherDetails wlOtherDetails) {
        try {
            WildLifeClearance temp = wildLifeClearanceRepository.findById(wl_id)
                    .orElseThrow(() -> new ProjectNotFoundException("WildLife clearance form not found"));
            wlOtherDetails.setWildLifeClearance(temp);

            if (wlOtherDetails.getLocation_and_map() != null) {
                wlOtherDetails.getLocation_and_map().setProposal_no(temp.getProposal_no());
            }

            wlOtherDetailsRepository.save(wlOtherDetails);

            return ResponseHandler.generateResponse("Save WL Other Details", HttpStatus.OK, "",
                    wildLifeClearanceRepository.findById(wl_id).get());
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Save WL Other Details", HttpStatus.BAD_REQUEST, "",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> addEnclosuresDetail(Integer wl_id, WLEnclosures wlEnclosures) {
        try {
            WildLifeClearance temp = wildLifeClearanceRepository.findById(wl_id)
                    .orElseThrow(() -> new ProjectNotFoundException("WildLife clearance form not found"));
            wlEnclosures.setWildLifeClearance(temp);
            if (wlEnclosures.getCopy_additional_information() != null) {
                wlEnclosures.getCopy_additional_information().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getProponent_undertaken_report() != null) {
                wlEnclosures.getProponent_undertaken_report().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getSector_specific_details() != null) {
                wlEnclosures.getSector_specific_details().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getCopy_of_mining_plan() != null) {
                wlEnclosures.getCopy_of_mining_plan().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getCopy_of_mining_lease() != null) {
                wlEnclosures.getCopy_of_mining_lease().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getCopy_detail_land() != null) {
                wlEnclosures.getCopy_detail_land().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getCopy_perspecting_licence() != null) {
                wlEnclosures.getCopy_perspecting_licence().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getBiodiversity_impact_assessment() != null) {
                wlEnclosures.getBiodiversity_impact_assessment().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getApproval_moefcc() != null) {
                wlEnclosures.getApproval_moefcc().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getTransportation_minerals_proposed() != null) {
                wlEnclosures.getTransportation_minerals_proposed().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getCopy_approval_competent_cat() != null) {
                wlEnclosures.getCopy_approval_competent_cat().setProposal_no(temp.getProposal_no());
            }
            if (wlEnclosures.getCopy_approved_CAT() != null) {
                wlEnclosures.getCopy_approved_CAT().setProposal_no(temp.getProposal_no());
            }
            // Added on 18-01-2023 by Rishabh Sharma
            if (wlEnclosures.getCost_benefit_analysis() != null) {
                wlEnclosures.getCost_benefit_analysis().setProposal_no(temp.getProposal_no());
            }
            wlEnclosuresRepository.save(wlEnclosures);

            return ResponseHandler.generateResponse("Save WL Enclosure Form Details", HttpStatus.OK, "",
                    wildLifeClearanceRepository.findById(wl_id).get());
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Save WL Enclosure Form Details", HttpStatus.BAD_REQUEST, "",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> addUndertaking(Integer wl_id, WLUndertaking wlUndertaking,Boolean is_submit,
                                                 HttpServletRequest request) {
        try {
            WildLifeClearance temp = wildLifeClearanceRepository.findById(wl_id)
                    .orElseThrow(() -> new ProjectNotFoundException("WildLife clearance form not found"));
            wlUndertaking.setWildLifeClearance(temp);
            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalNo(temp.getProposal_no());
            if (proponentApplications.getLast_status().equals(Caf_Status.EDS_RAISED.name())) {
                proponentApplications.setLast_status(Caf_Status.EDS_REPLIED.toString());
                proponentApplications.setMigration_status(false);
            } else {
                proponentApplications.setLast_status(Caf_Status.SUBMITTED.toString());
                //Added on 02122022 on requirement
                proponentApplications.setLast_submission_date(new Date());
                //End Added on 02122022
            }
            proponentApplications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);
            if (proponentApplications.getLast_status().equals(Caf_Status.DRAFT.toString())) {
                if (wlUndertaking.getSubmission_date() != null)
                    proponentApplications.setLast_submission_date(wlUndertaking.getSubmission_date());
                else {
                    proponentApplications.setLast_submission_date(new Date());
                }
            }
            commonFormDetailService.saveCommonForm(temp.getCommonFormDetail());
            proponentApplicationRepository.save(proponentApplications);

            wlUndertakingRepository.save(wlUndertaking);
            
            //Below if block is commented for now, we need to check why is_submit is throwing error - Subhash

//            if (is_submit == true) {
//                notificationSevice.sendProposalSMS(proponentApplications.getProposal_no());
//                util.sendProposalEmail(proponentApplications);
//            }

            return ResponseHandler.generateResponse("Save Division Land Details", HttpStatus.OK, "",
                    wildLifeClearanceRepository.findById(wl_id).get());
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("Save Division Land Details", HttpStatus.BAD_REQUEST, "",
                    ex.getMessage());
        }
    }

    public ResponseEntity<Object> addDivisionLandDetails(Integer wl_id,
                                                         List<WLDivisionLandDetail> wlDivisionLandDetail) {
        WildLifeClearance temp = wildLifeClearanceRepository.findById(wl_id)
                .orElseThrow(() -> new ProjectNotFoundException("Wildlife clearance form not found"));

        List<WLDivisionLandDetail> WLDivisionLandDetail1 = wlDivisionLandDetail.stream().map(val -> {
            val.setWlclearance(temp);
            return val;
        }).collect(Collectors.toList());
        
        List<WLDivisionLandDetail> WLDivisionLandDetail2 = wlandDetailRepository.saveAll(WLDivisionLandDetail1);

        return ResponseHandler.generateResponse("Save Division Land Details", HttpStatus.OK, "", WLDivisionLandDetail2);
    }

    public ResponseEntity<Object> deleteDivisionLandDetails(List<WLDivisionLandDetail> wlDivisionLandDetail) {
        List<WLDivisionLandDetail> WLDivisionLandDetail1 = wlDivisionLandDetail.stream().map(val -> {
//        	Double outside_protected_area= val.getTotal_protected_area()+val.getTotal_protected_outside_area();
//            val.getWlProposedLand().setTotal_component_land(outside_protected_area);
            val.setIs_active(false);
            val.setIs_delete(true);
            return val;
        }).collect(Collectors.toList());
        return ResponseHandler.generateResponse("Delete Division Land Details ", HttpStatus.OK, "",
                wlandDetailRepository.saveAll(WLDivisionLandDetail1));
    }

    public ResponseEntity<Object> addComponentWiseDetails(Integer wl_id,
                                                          List<WLComponentWiseDetails> wlComponentWiseDetails) {
        WildLifeClearance temp = wildLifeClearanceRepository.findById(wl_id)
                .orElseThrow(() -> new ProjectNotFoundException("Wildlife clearance form not found"));

        List<WLComponentWiseDetails> wlComponentWiseDetails2 = wlComponentWiseDetails.stream().map(val -> {
            val.setWlclearance(temp);
            return val;
        }).collect(Collectors.toList());

        List<WLComponentWiseDetails> wlComponentWiseDetails3 = wlComponentWiseDetailsRepository
                .saveAll(wlComponentWiseDetails2);

        return ResponseHandler.generateResponse("Save Component Wise Details", HttpStatus.OK, "",
                wlComponentWiseDetails3);
    }

    public Object getWilLifeClearanceForm(Integer id) {
        WildLifeClearanceAudit WildLifeClearanceAudit = new WildLifeClearanceAudit();
        String abbreviation="";

        WildLifeClearanceAudit = wildLifeClearanceAuditRepository.findDetailByWlId(id);
        if (WildLifeClearanceAudit != null) {
            return WildLifeClearanceAudit;
        } else {
            WildLifeClearance form = wildLifeClearanceRepository.findDetailByWlId(id);
            form.setProponentApplications(proponentApplicationRepository.getApplicationByProposalId(form.getId()) != null
                    ? proponentApplicationRepository.getApplicationByProposalId(form.getId())
                    : null);

       // added on 15-03-2023 to get division name 
            abbreviation = form.getDivision();   
            if(abbreviation!=null)
            {
            Object divisionName = "";
            try {
                Query query2 = entityManager.
                        createNativeQuery("select officename from ua.office_entity where abbreviation=?1");
                query2.setParameter(1, abbreviation);
                if(query2!=null)
                {divisionName = query2.getSingleResult();
                
                form.setDivision_name((String) divisionName);
                }
                          
            } catch (Exception e) {
                System.err.println("No Data Found" + e.getMessage());
                form.setDivision_name("NA");
            }
            }
            else
            {
                for( WLDivisionLandDetail divisionObj:form.getWldivisionLandDetails())
                {
                	abbreviation= divisionObj.getDivision();
                	
                    Object divisionName = "";
                    try {
                        Query query2 = entityManager.
                                createNativeQuery("select officename from ua.office_entity where abbreviation=?1");
                        query2.setParameter(1, abbreviation);
                        if(query2!=null)
                        {divisionName = query2.getSingleResult();
                        divisionObj.setDivision_name((String) divisionName);
                        }
                                  
                    } catch (Exception e) {
                        System.err.println("No Data Found " + e.getMessage());
                        divisionObj.setDivision_name((String) divisionName);
                    }

            }
            }
            return form;
        }
    }

    public ResponseEntity<Object> deleteComponentWiseDetails(List<WLComponentWiseDetails> wlComponentWiseDetails) {
        List<WLComponentWiseDetails> wlComponentWiseDetails1 = wlComponentWiseDetails.stream().map(val -> {
//        	Double outside_protected_area= val.getTotal_land_outside_protected_area()+val.getTotal_land_under_protected_area();
//            WLProposedLand wlpl = new WLProposedLand();
//            wlpl.setTotal_component_land(outside_protected_area);
//            val.setWlProposedLand(wlpl);
        	//val.getWlProposedLand().setTotal_component_land(outside_protected_area);
            val.setIs_active(false);
            val.setIs_delete(true);
            return val;
        }).collect(Collectors.toList());
        return ResponseHandler.generateResponse("Delete Component Wise Details", HttpStatus.OK, "",
                wlComponentWiseDetailsRepository.saveAll(wlComponentWiseDetails1));
    }

    public String generateSequenceNumber(int maxcount) {
        // this will convert any number sequence into 6 character.
        AtomicInteger sequence = new AtomicInteger(maxcount);
        // return ResponseHandler.generateResponse("CAF
        // Others",HttpStatus.OK,"",String.format("%06d", sequence.addAndGet(1)));
        return String.format("%06d", sequence.addAndGet(1));
    }

    private String generateProposalNo(int maxcount, WildLifeClearance wildLifeClearanceForm) {
//		String cafId = "WL/" + stateRepository.getStateByCode(wildLifeClearanceForm.getState())
//				.orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
//				+ "/" + wildLifeClearanceForm.getCategory_of_project() + "/" + generateSequenceNumber(maxcount) + "/"
//				+ String.valueOf(LocalDate.now().getYear());
//		// return ResponseHandler.generateResponse("CAF Others",HttpStatus.OK,"",cafId);
//		cafId = cafId.replaceAll("\\s", "");
        String cafId = null;
        if ("" != wildLifeClearanceForm.getCategory_of_project() && wildLifeClearanceForm.getCategory_of_project() != null) {
            cafId = "WL/" + stateRepository.getStateByCode(wildLifeClearanceForm.getState())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                    + "/" + wildLifeClearanceForm.getCategory_of_project() + "/" + generateSequenceNumber(maxcount) + "/"
                    + String.valueOf(LocalDate.now().getYear());
        } else {
            cafId = "WL/" + stateRepository.getStateByCode(wildLifeClearanceForm.getState())
                    .orElseThrow(() -> new ProjectNotFoundException("state data not found for state id")).getState_abbr()
                    + "/" + "SRY" + "/" + generateSequenceNumber(maxcount) + "/"
                    + String.valueOf(LocalDate.now().getYear());
        }
        return cafId;
    }

    public ResponseEntity<Object> addLinearProjectLandDetails(Integer wlc_id,
                                                              List<WLLinearProjectLandDetails> wlLinearProjectLandDetails) {
        // TODO Auto-generated method stub
        WildLifeClearance temp = wildLifeClearanceRepository.findById(wlc_id)
                .orElseThrow(() -> new ProjectNotFoundException("Wildlife clearance form not found"));

        List<WLLinearProjectLandDetails> wlLinearProjectLandDetail1 = wlLinearProjectLandDetails.stream().map(val -> {
            val.setWlclearance(temp);
            return val;
        }).collect(Collectors.toList());

        List<WLLinearProjectLandDetails> wlLinearProjectLandDetail2 = wlLinearProjectLandDetailsRepository.saveAll(wlLinearProjectLandDetail1);

        return ResponseHandler.generateResponse("Save Linear Project Land Details", HttpStatus.OK, "", wlLinearProjectLandDetail2);
    }

    public ResponseEntity<Object> deleteLinearProjectLandDetails(List<WLLinearProjectLandDetails> wlLinearProjectLandDetails) {
        List<WLLinearProjectLandDetails> wlLinearProjectLandDetail1 = wlLinearProjectLandDetails.stream().map(val -> {
            val.setIs_active(false);
            val.setIs_delete(true);
            return val;
        }).collect(Collectors.toList());
        return ResponseHandler.generateResponse("Delete Linear Project Land Details ", HttpStatus.OK, "",
                wlLinearProjectLandDetailsRepository.saveAll(wlLinearProjectLandDetail1));
    }

    // Added Rishabh Sharma on 02-01-2023 for Auto fetch data for FC Granted.
    public Object getFCGranted(String proposalNo, String proposalStatus, Integer id) {
        FCGratedDTO fcGratedDTO = new FCGratedDTO();
        ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalNo(proposalNo);

        if (!proposalNo.startsWith("FP")) {
            fcGratedDTO.setMessage("Not a valid Forest Clearance Proposal No.: " + proposalNo);
            return fcGratedDTO.getMessage();
            //throw new RuntimeException ("Not a valid Forest Clearance Proposal No.");

        }

        /*
         * if(proponentApplications.getCreated_by()!=id) {
         * fcGratedDTO.setMessage("Not a valid User For Forest Clearance Proposal No.: "
         * + proposalNo); }
         */

        if (proponentApplications != null && proposalNo.startsWith("FP"))
        //if(proponentApplications!=null && proposalNo.startsWith("FP") && proponentApplications.getCreated_by()==id)

        {

            Object AreaProposedForDiversion = 0.0d;
            try {
                Query query5 = entityManager.createNativeQuery("select fp.total_proposed_diversion_area from master.fc_proposed_land fp join master.forest_clearance fc on fc.id=fp.fc_id where fc.proposal_no= ?");
                query5.setParameter(1, proposalNo);
                AreaProposedForDiversion = query5.getSingleResult();
            } catch (Exception e) {
                System.err.println("No Data Found for AreaProposedForDiversion - " + e.getMessage());
            }

            Object AreaRecommendedForDiversion = 0.0d;
            try {
                Query query2 = entityManager.createNativeQuery("select recommendedarea from ua.clearance_letter_entity where proposal_no= ?");
                query2.setParameter(1, proposalNo);
                AreaRecommendedForDiversion = query2.getSingleResult();

                if (AreaRecommendedForDiversion == null) {
                    AreaRecommendedForDiversion = 0.0d;
                }
            } catch (Exception e) {
                System.err.println("No Data Found for AreaRecommendedForDiversion - " + e.getMessage());
            }


            Object AreaDiverted = 0.0d;
            try {
                Query query2 = entityManager.createNativeQuery("select divertedarea from master.prior_approvals where proposalno= ?1 and proposal_status=?2");
                query2.setParameter(1, proposalNo);
                query2.setParameter(2, proposalStatus);
                AreaDiverted = query2.getSingleResult();
            } catch (Exception e) {
                System.err.println("No Data Found for AreaDiverted - " + e.getMessage());
            }


            Object moeffccFileNo = "";
            try {
                Query query2 = entityManager.
                        createNativeQuery("select moefccfileno from master.prior_approvals where proposalno= ?1 and proposal_status=?2 ");
                query2.setParameter(1, proposalNo);
                query2.setParameter(2, proposalStatus);
                moeffccFileNo = query2.getSingleResult();
            } catch (Exception e) {
                System.err.println("No Data Found for ProposalStatus - " + e.getMessage());
                fcGratedDTO.setMoefccFileNo("NA");
            }


            Object approvaldate = "";
            try {
                Query query3 = entityManager.createNativeQuery("select inprincipleapprovaldate from master.prior_approvals where proposalno= ?1 and proposal_status=?2");
                query3.setParameter(1, proposalNo);
                query3.setParameter(2, proposalStatus);
                approvaldate = query3.getSingleResult();

                if (approvaldate == null || approvaldate == "") {
                    fcGratedDTO.setDateOfApproval("");
                } else {
                    //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date date2 = (Date) approvaldate;
                    String approval_date = formatter.format(date2);
                    fcGratedDTO.setDateOfApproval(approval_date);
                }


            } catch (Exception e) {
                System.err.println("No Data Found for approval date - " + e.getMessage());
                fcGratedDTO.setDateOfApproval("");
            }

            Date date = proponentApplications.getLast_submission_date();
            if (date == null) {
                date = proponentApplications.getUpdated_on();
            }

            //SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            //String subDate1 = formatter.format(date);

            SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
            String subDate2 = formatter2.format(date);
            fcGratedDTO.setMoefccFileNo((String) moeffccFileNo);
            fcGratedDTO.setProjectName(proponentApplications.getProjectDetails().getName());
            fcGratedDTO.setAreaProposedForDiversion((Double) AreaProposedForDiversion);
            fcGratedDTO.setAreaRecommendedForDiversion((Double) AreaRecommendedForDiversion);
            fcGratedDTO.setAreaDiverted((Double) AreaDiverted);
            fcGratedDTO.setDateOfApplication(subDate2);
            fcGratedDTO.setMessage(" Valid Forest Clearance Proposal No.: " + proposalNo);

        } else {
            fcGratedDTO.setMessage("No Data Found for Forest Clearance Proposal No.: " + proposalNo);
            return fcGratedDTO.getMessage();
            //throw new RuntimeException ("Forest Clearance Proposal No. does not exists");
        }

        return fcGratedDTO;
    }

    // Added Rishabh Sharma on 02-01-2023 for Auto fetch data for EC Granted.
    public Object getECObtained(String proposalNo, Integer id) {
        ECObtainedDTO ecObtainedDTO = new ECObtainedDTO();
        String moeffccFileNo = "";

        if (!(proposalNo.startsWith("IA") || proposalNo.startsWith("SIA"))) {
            ecObtainedDTO.setMessage("Not a valid  Environment Clearance Proposal No.:  " + proposalNo);
            return ecObtainedDTO.getMessage();


        }

        ProponentApplications proponentApplications = proponentApplicationRepository.getApplicationByProposalNo(proposalNo);

        /*
         * if(proponentApplications.getCreated_by()!=id) {
         * ecObtainedDTO.setMessage("Not a valid User For Environment Clearance Proposal No.: "
         * + proposalNo); }
         */

        //&& proponentApplications.getCreated_by()==id
        if (proponentApplications != null && (proposalNo.startsWith("IA") || proposalNo.startsWith("SIA"))) {
            Object date = null;
            try {
                Query query3 = entityManager.createNativeQuery("select date from master.ec_fresh_letter_template_certificate where proposal_no= ?");
                query3.setParameter(1, proposalNo);
                date = query3.getSingleResult();
                /*
                 * SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); Date date2 =
                 * (Date) date; String issueDate = formatter.format(date2);
                 */
                ecObtainedDTO.setDateOfIssueEC((String) date);
            } catch (Exception e) {
                System.err.println("No Data Found for issue date - " + e.getMessage());
                ecObtainedDTO.setDateOfIssueEC("");
            }

            Date dateOfApplication = proponentApplications.getLast_submission_date();

            if (dateOfApplication == null) {
                dateOfApplication = proponentApplications.getUpdated_on();

            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String subDate = formatter.format(dateOfApplication);


            moeffccFileNo = proponentApplications.getMoefccFileNumber();
            if (moeffccFileNo == null) {
                moeffccFileNo = "NA";
            }
            ecObtainedDTO.setMoefccFileNo(moeffccFileNo);
            ecObtainedDTO.setDateOfApplication(subDate);
            ecObtainedDTO.setMessage(" Valid Environment Clearance Proposal No.: " + proposalNo);

        } else {
            ecObtainedDTO.setMessage("No Data Found for Environment Clearance Proposal No.: " + proposalNo);
            return ecObtainedDTO.getMessage();
            //throw new RuntimeException ("No Data Found for this Proposal No. : " + proposalNo);
        }

        return ecObtainedDTO;
    }


    public String generateUniqueClearanceNo(Integer size, String state) {
        int yearCode = Calendar.getInstance().get(Calendar.YEAR) % 100;
        StringBuilder result = new StringBuilder(size);
        String firstTwoDigit = "WL";
        String checksum = "5";
        String issuingAuthorityCode = "A0102";
        String LastDigit = "N";
        result.append(firstTwoDigit);
        result.append(yearCode);
        result.append(issuingAuthorityCode);
        result.append(state);
        result.append(checksum);
        int randomNo = generateFiveDigitRandomNo();
        result.append(randomNo);
        result.append(LastDigit);
        return result.toString();
    }

    public String generateGreenClearanceNo() {
        StringBuilder result = new StringBuilder();
        String firstThreeDigit = "GCN";
        result.append(firstThreeDigit);

        int randomNo = generateEightDigitRandomNo();
        result.append(randomNo);
        result.append("-");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        String strDate = formatter.format(date);
        result.append(strDate);

        return result.toString();
    }


    public int generateFiveDigitRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
    }

    public int generateEightDigitRandomNo() {
        Random r = new Random(System.currentTimeMillis());
        return ((1 + r.nextInt(2)) * 10000000 + r.nextInt(10000000));
    }
    
    
    // added on 27-03-2023 for WL Additional Information
    
    public ResponseEntity<Object> getAdditionalInformationbyWLId(String ref_id) throws PariveshException {

        try {
            List<WLAdditionalInformation> wlAdditionalInformations;

            wlAdditionalInformations = wlAdditionalInformationRepository.findAdditionalInformationByRefId(ref_id);

            return ResponseHandler.generateResponse("Get WL Additional Information", HttpStatus.OK, "", wlAdditionalInformations);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in getAdditionalInformationbyFCId for ref_Id- " + ref_id, e);
        }

    }

    public ResponseEntity<Object> saveWLAdditionalInformationArea(List<WLAdditionalInformation> wlAdditionalInformation) throws PariveshException {
        try {

            List<WLAdditionalInformation> additionalInformations3 = wlAdditionalInformationRepository.saveAll(wlAdditionalInformation);

            return ResponseHandler.generateResponse("Save WL Additional Information", HttpStatus.OK, "", additionalInformations3);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in saveAdditionalInformationArea ", e);
        }
    }
    
    public ResponseEntity<Object> deleteAdditionalInformation(Integer id) throws PariveshException {
        try {
        	WLAdditionalInformation wlAdditionalInformation = wlAdditionalInformationRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Additional Information not found"));

        	wlAdditionalInformation.setIs_deleted(true);
        	wlAdditionalInformation.setIs_active(false);
//			log.info("INFO ------------ deleteAdditionalInformation WITH forestAdditionalInformation_ID ---->" + id
//					+ "------ DELETING FC ADDITIONAL INFORMATION- SUCCESS");
        	WLAdditionalInformation temp = wlAdditionalInformationRepository.save(wlAdditionalInformation);

            return ResponseHandler.generateResponse("Delete WL Additional Information", HttpStatus.OK, "", temp);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in WL Additional Information for Id- " + id, e);
        }
    }

    
    
   
}