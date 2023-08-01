package com.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.backend.exceptions.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.constant.AppConstant;
import com.backend.constant.AppConstant.Caf_Status;
import com.backend.exceptions.PariveshException;
import com.backend.model.Applications;
import com.backend.model.DepartmentApplication;
import com.backend.model.ProponentApplications;
import com.backend.model.EcFrom5AcceptProposal.EcForm5AcceptProposals;
import com.backend.repository.postgres.ApplicationsRepository;
import com.backend.repository.postgres.DepartmentApplicationRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.EcForm5AcceptProposal.EcForm5AcceptProposalRepository;
import com.backend.response.ResponseHandler;
import com.backend.util.ServerUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EcForm5AcceptProposalsService {

    @Autowired
    private EcForm5AcceptProposalRepository ecForm5AcceptProposalRepository;

    @Autowired
    private ProponentApplicationRepository proponentApplicationRepository;

    @Autowired
    private DepartmentApplicationRepository departmentApplicationRepository;

    @Autowired
    private ApplicationsRepository applicationsRepository;

    @Autowired
    private ServerUtil serverUtil;

    public EcForm5AcceptProposals saveEcForm5AcceptProposal(EcForm5AcceptProposals ecForm5AcceptProposals,
                                                            HttpServletRequest request) {

        try {
            EcForm5AcceptProposals temp;
            temp = ecForm5AcceptProposalRepository.save(ecForm5AcceptProposals);

            ProponentApplications proponentApplications = proponentApplicationRepository
                    .getApplicationByProposalId(ecForm5AcceptProposals.getEc_id());

            if (departmentApplicationRepository.getDepartmentApplicationByProposalId(temp.getEc_id()) == null) {
                DepartmentApplication applications = new DepartmentApplication();
                Applications app = applicationsRepository.findById(6).get();
                applications.setApplications(app);
                applications.setProponentApplications(proponentApplications);
                applications.setProposal_sequence(proponentApplications.getProposal_sequence());
                applications.setProposal_no(proponentApplications.getProposal_no());
                applications.setProposal_id(temp.getId());
                applications.setStatus(Caf_Status.SUBMITTED.toString());
                applications.setIp_address(request.getRemoteAddr() != null ? request.getRemoteAddr() : null);

                departmentApplicationRepository.save(applications);
            }
            try {
                String message = " Public hearing has been scheduled for your proposal "
                        + proponentApplications.getProposal_no() + " on below mentioned dates: "
                        + ecForm5AcceptProposals.getPh_date();

                Map<String, String> body = new HashMap<>();
                body.put("simple_content", message);

                serverUtil.sendEmailNotification(proponentApplications.getProposal_no(), body, AppConstant.publicHearing,
                        AppConstant.simpleTemplate);

            } catch (Exception e) {
                log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
                return temp;

            }
            return temp;

        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Saving Ec Form 5 Accept Proposal ", e);
        }
    }

    public ResponseEntity<Object> getEcForm5AcceptProposal(Integer id) {
        try {
            EcForm5AcceptProposals ecForm5AcceptProposals = ecForm5AcceptProposalRepository.findById(id)
                    .orElseThrow(null);

            ecForm5AcceptProposals.setDepartmentApplication(departmentApplicationRepository
                    .getDepartmentApplicationByProposalId(ecForm5AcceptProposals.getId()));

            return ResponseHandler.generateResponse("Get Ec Form 5 Accept Proposal Data ", HttpStatus.OK, "",
                    ecForm5AcceptProposals);
        } catch (Exception e) {
            log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
            throw new PariveshException("Error in Getting Ec Form 5 Accept Proposals id- " + id, e);
        }
    }

    public ResponseEntity<Object> getFormVByEcId(Integer ecId){
        try{
            List<EcForm5AcceptProposals> response=ecForm5AcceptProposalRepository.getByEcId(ecId);


            response.forEach(value ->{
                value.setDepartmentApplication(departmentApplicationRepository
                        .getDepartmentApplicationByProposalId(value.getId()));
            });

            return ResponseHandler.generateResponse("getting form 5",HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in getting form 5",e);
        }
    }

    public ResponseEntity<Object> deleteEcForm5Proposal(Integer id){
        try {
            EcForm5AcceptProposals temp= ecForm5AcceptProposalRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("proposal not found with id:"+id));

            temp.setIsActive(false);
            ecForm5AcceptProposalRepository.save(temp);
            return ResponseHandler.generateResponse("deleting proposal form 5 proposal for id:"+id,HttpStatus.OK,"",null);
        }catch (Exception e){
            log.error("encountered exception:",e);
            throw new PariveshException("error in deleting proposal",e);
        }
    }

}
