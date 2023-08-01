package com.backend.scheduler;

import com.backend.model.Applications;
import com.backend.model.ForestClearance;
import com.backend.model.ForestClearanceE.FcFormE;
import com.backend.model.ForestClearanceFormB.FcFormBProjectDetails;
import com.backend.model.ForestClearanceFormC.FcFormC;
import com.backend.model.ForestClearanceFormD.FcFormD;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.FcFormB.FcFormBProjectDetailsRepository;
import com.backend.repository.postgres.ForestClearanceFormC.FcFormCRepository;
import com.backend.repository.postgres.ForestClearanceFormD.FcFormDRepository;
import com.backend.repository.postgres.ForestClearanceFormE.FcFormERepository;
import com.backend.repository.postgres.ForestClearanceRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.service.ProponentApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UpdateOtherPropertyScheduler {

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private ForestClearanceRepository forestClearanceRepository;

    @Autowired
    private FcFormBProjectDetailsRepository fcFormBProjectDetailsRepository;

    @Autowired
    private FcFormCRepository fcFormCRepository;

    @Autowired
    private FcFormDRepository fcFormDRepository;

    @Autowired
    private FcFormERepository fcFormERepository;

    @Autowired
    private ProponentApplicationService proponentApplicationService;

    @Scheduled(cron = "0 0 3 * * ?")
    public void scheduleUpdate(){
        log.info("inside update other property scheduler");
        LocalDateTime date= LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<String> proposals= updateOtherProp();

        log.info(" other property updated at"+date.format(format)+" for following proposals :"+proposals);
    }


    public List<String> updateOtherProp() {
        List<String> updatedProposals= new ArrayList<String>();
        List<String> errorProposals= new ArrayList<String>();
        List<ProponentApplications> proponentApplications = proponentApplicationRepository.getApplicationWithNullOtherProperty();
        proponentApplications.stream().map(value -> {
            Applications app = applicationsRepository.findById(value.getClearance_id()).orElse(null);

            try {
                if (value.getClearance_id() == 1) {
                    log.info("updating other property for FC form A");
                    HashMap<String, Object> OtherPropStringFormA = new HashMap<String, Object>();
                    ForestClearance forestClearance = forestClearanceRepository.findById(value.getProposal_id()).get();

                    OtherPropStringFormA.put("Form", app.getDd_name());
                    String activity = forestClearance.getProject_activity_id();
                    if (activity.equalsIgnoreCase("Others")) {
                        String fullActivityName = activity + "(" + forestClearance.getProject_activity_id_other() + ")";
                        OtherPropStringFormA.put("Project Category", fullActivityName);
                    } else {
                        OtherPropStringFormA.put("Project Category", proponentApplicationRepository.getProjectCategoryName(Integer.parseInt(forestClearance.getProject_category_id())));
                    }
                    OtherPropStringFormA.put("Forest Area", forestClearance.getForestProposedLand().getTotal_proposed_diversion_area().toString());

                   proponentApplicationService.updateOtherProperty(value.getProposal_id(), OtherPropStringFormA);
                    updatedProposals.add(value.getProposal_no());
                    log.info("other property updated for:" + value.getProposal_no());

                } else if (value.getClearance_id() == 7) {

                    log.info("updating other property for FC form C");
                    HashMap<String, Object> OtherPropStringFormC = new HashMap<String, Object>();
                    FcFormC fcFormC = fcFormCRepository.findById(value.getProposal_id()).get();

                    OtherPropStringFormC.put("Exploration Area", fcFormC.getForest_proposed_exploration_area());
                    OtherPropStringFormC.put("Project Category", "Exploration & Survey");
                    OtherPropStringFormC.put("Form", app.getDd_name());

                    proponentApplicationService.updateOtherProperty(value.getProposal_id(), OtherPropStringFormC);
                    updatedProposals.add(value.getProposal_no());
                    log.info("other property updated for:" + value.getProposal_no());

                } else if (value.getClearance_id() == 8) {
                    log.info("updating other property for FC form B");
                    HashMap<String, Object> OtherPropStringFormB = new HashMap<String, Object>();
                    FcFormBProjectDetails fcFormB = fcFormBProjectDetailsRepository.findById(value.getProposal_id()).get();

                    OtherPropStringFormB.put("Form", app.getDd_name());
                    OtherPropStringFormB.put("Project Category", fcFormB.getProject_category_code());
                    OtherPropStringFormB.put("Forest Area", fcFormB.getFormBProposedLands().getTotal_proposed_diversion_area().toString());

                    proponentApplicationService.updateOtherProperty(value.getProposal_id(), OtherPropStringFormB);
                    updatedProposals.add(value.getProposal_no());
                    log.info("other property updated for:" + value.getProposal_no());
                } else if (value.getClearance_id() == 9) {

                    log.info("updating other property for FC form D");
                    HashMap<String, Object> OtherPropStringFormD = new HashMap<String, Object>();
                    FcFormD fcFormD = fcFormDRepository.findById(value.getProposal_id()).get();

                    OtherPropStringFormD.put("Form", app.getDd_name());
                    if (fcFormD.getProject_category() == "MIND") {
                        OtherPropStringFormD.put("Project Category", "Mining");
                    } else {
                        OtherPropStringFormD.put("Project Category", "Non-Mining");
                    }
                    OtherPropStringFormD.put("Forest Area", fcFormD.getFcFormDProposedLand().getTotal_proposed_diversion_area().toString());

                    proponentApplicationService.updateOtherProperty(value.getProposal_id(), OtherPropStringFormD);
                    updatedProposals.add(value.getProposal_no());
                    log.info("other property updated for:" + value.getProposal_no());

                } else if (value.getClearance_id() == 12) {

                    log.info("updating other property for FC form E");
                    HashMap<String, Object> OtherPropStringFormE = new HashMap<String, Object>();
                    FcFormE fcFormE = fcFormERepository.findById(value.getProposal_id()).get();

                    OtherPropStringFormE.put("Form", app.getDd_name());
                    OtherPropStringFormE.put("Project Category", "Re-Diversion");
                    OtherPropStringFormE.put("Forest Area", fcFormE.getFcFormEProposedLand().getTotal_forest_land_proposed().toString());

                    proponentApplicationService.updateOtherProperty(value.getProposal_id(), OtherPropStringFormE);
                    updatedProposals.add(value.getProposal_no());
                    log.info("other property updated for:" + value.getProposal_no());
                }
            } catch (Exception e) {
                value.setOther_property("N/A");
                errorProposals.add(value.getProposal_no());
                log.error("encountered exception for proposal:"+value.getProposal_no(), e);
            }
            proponentApplicationRepository.save(value);
            return value;
        }).collect(Collectors.toList());
        log.info("proposal with error while updating property:"+errorProposals);
//        log.info("proposals with updated property:"+updatedProposals);
        return updatedProposals;
    }
}
