package com.backend.service.TrackYourProposal;

import com.backend.dto.GetDataDto;
import com.backend.dto.TrackYourProposal.ApplicationProcessHistoryDto;
import com.backend.dto.TrackYourProposal.TrackYourProposalDto;
import com.backend.model.reports.TrackYourProposalModel;
import com.backend.repository.postgres.StateRepository;
import com.backend.repository.postgres.TrackYourProposal.TrackYourProposalRepository;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TrackYourProposalService {

    @Autowired
    TrackYourProposalRepository trackYourProposalRepository;


    @Autowired
    StateRepository stateRepository;

    public List<TrackYourProposalModel> getDataOnBasesOfProposalNo(String proposalNo) {
        log.info("============================== getting Data On Bases Of Proposal No {} ===========================", proposalNo);

        List<String> workGroupIds = trackYourProposalRepository.getWorkGroupId(proposalNo);
        List<TrackYourProposalModel> trackYourProposalModels = new ArrayList<>();

        if (workGroupIds.size() == 1) {

            if (workGroupIds.get(0).equals("4")) {

                List<TrackYourProposalModel> trackYourProposalModel1 = trackYourProposalRepository.searchCrzProposal(workGroupIds.get(0), "", "", "", "", "", "", proposalNo, "", null);
                if (trackYourProposalModel1.size() >= 1) {
                    //List<TrackYourProposalModel> trackYourProposalModels1=new ArrayList<>();
                    //trackYourProposalModels.add(trackYourProposalModel1.get(0));
                    trackYourProposalModels.addAll(trackYourProposalModel1);
                }
            } else {

                List<TrackYourProposalModel> trackYourProposalModel1 = trackYourProposalRepository.searchProposal(workGroupIds.get(0), "", "", "", "", "", "", "", "", proposalNo, "", "", "", null, null);
                if (trackYourProposalModel1.size() >= 1) {
                    //List<TrackYourProposalModel> trackYourProposalModels1=new ArrayList<>();
                    //trackYourProposalModels.add(trackYourProposalModel1.get(0));
                    trackYourProposalModels.addAll(trackYourProposalModel1);
                }
            }

        } else {
            for (String wgId : workGroupIds) {

                if (wgId.equals("4")) {

                    List<TrackYourProposalModel> trackYourProposalModel1 = trackYourProposalRepository.searchCrzProposal(wgId, "", "", "", "", "", "", proposalNo, "", null);
                    if (trackYourProposalModel1.size() >= 1) {
                        //List<TrackYourProposalModel> trackYourProposalModels1=new ArrayList<>();
                        //trackYourProposalModels.add(trackYourProposalModel1.get(0));
                        trackYourProposalModels.addAll(trackYourProposalModel1);
                    }
                } else {

                    List<TrackYourProposalModel> trackYourProposalModel1 = trackYourProposalRepository.searchProposal(wgId, "", "", "", "", "", "", "", "", proposalNo, "","", "", null, null);
                    log.info(wgId + ":" + String.valueOf(trackYourProposalModel1.size()));
                    if (trackYourProposalModel1.size() >= 1) {
                        trackYourProposalModels.addAll(trackYourProposalModel1);
                    }
                }

            }
            log.info(String.valueOf(trackYourProposalModels.size()));
        }

        return trackYourProposalModels;
    }

    /*public List<TrackYourProposalDto> getDataOnBasesOfSingleWindowNo(String swno) {
        log.info("============================== getting Data On Bases Of Single Window No ===========================");

        return trackYourProposalRepository.getDataOnBasesOfSingleWindowNo(swno);
    }*/

    public List<TrackYourProposalModel> getDataOnBasesOfSingleWindowNo(String swno, String workgroupId) {
        log.info("============================== getting Data On Bases Of Single Window No ===========================");
        return trackYourProposalRepository.searchProposal(workgroupId, "", "", "", "", "", "", "", "", "", "","", swno, null, null);

    }


    public List<TrackYourProposalDto> getDataOfProposalNo(String proposalNo) {
        log.info("============================== getting Data Of Proposal No ===========================");

        return trackYourProposalRepository.getDataOfProposalNo(proposalNo);
    }


    public List<ApplicationProcessHistoryDto> getHistoryDataOnProposal(String proposalNo) {
        log.info("============================== getting Data Of Proposal No ===========================");

        return trackYourProposalRepository.getHistoryDataOnProposal(proposalNo);
    }

    public List<TrackYourProposalModel> getAdvanceSearchData(String clearanceId, String stateId, String sectorId, String proposal_Status, String proposal_type, String issuingAuthority, String cat, String startDate, String endDate, String areaMin, String areaMax, String activityId, String text) {
        log.info("============================== getting Data for Advance Search ===========================");

        String catCode = "";
        String proposalNo = null;
        String status = null;

        if (proposal_Status != null) {
        
        	status = getStatusString(proposal_Status, clearanceId);
        	log.info(status);
        }
        
        if (clearanceId.equals("4")) {
            return trackYourProposalRepository.searchCrzProposal(clearanceId, stateId, proposal_Status, proposal_type, issuingAuthority, startDate, endDate, proposalNo, "", text);
        } else {
            if (proposal_type.equals("9") && !(cat.isEmpty())) {
                if (cat.equals("Mining")) {
                    catCode = "MIND";
                } else {
                    catCode = "NONMIND";
                }

                return trackYourProposalRepository.searchProposal(clearanceId, stateId, sectorId, status, proposal_type, issuingAuthority, catCode, startDate, endDate, proposalNo, areaMin, areaMax, "", null, text);
            }
        }


        return trackYourProposalRepository.searchProposal(clearanceId, stateId, sectorId, status, proposal_type, issuingAuthority, trackYourProposalRepository.getCategoryCode(cat), startDate, endDate, proposalNo, areaMin, areaMax,"", activityId == "" ? null : Integer.valueOf(activityId), text);
    }


    public List<GetDataDto> getAllClearanceType() {
        log.info("============================== Getting Data From Clearance Type ===========================");

        return trackYourProposalRepository.getAllClearanceType();
    }

    public List<GetDataDto> getProposalTypeOnBasesOfClearanceType(Integer id) {
        log.info("============================== Get Proposal Type On Bases Of Clearance Type ===========================");

        return trackYourProposalRepository.getProposalTypeOnBasesOfClearanceType(id);
    }

    public Object getListOfAllState() {
        log.info("============================== Get List of All State ===========================");

        return stateRepository.findAllStates();
    }

    public Object getListOfCrzState() {
        log.info("============================== Get List of Crz State ===========================");

        return stateRepository.findCrzStates();
    }

    public Object getListOfIssuingAuthority() {
        log.info("============================== Get List Of Issuing Authority ===========================");

        return trackYourProposalRepository.getListOfIssuingAuthority();
    }

    public Object getListOfSector() {
        log.info("============================== Get List Of Sector ===========================");

        return trackYourProposalRepository.getListOfSector();
    }

    public Object getListOfCategoryForFC() {
        log.info("============================== Get List Of Category For FC ===========================");

        return trackYourProposalRepository.getListOfCategoryForFC();
    }

    public Object getListOfStatus(String workgroupId, String clearanceType) {
        log.info("============================== Get List Of Status ===========================");


        if (workgroupId == null || workgroupId.equals("0") || workgroupId.equals("")) {
            return trackYourProposalRepository.getListOfStatus();

        } else {
            //return trackYourProposalRepository.getListOfECStatusById(Integer.parseInt(workgroupId), clearanceType);
        	return trackYourProposalRepository.getAllStatusbyId(Integer.parseInt(workgroupId));
        }

        //if (clearanceType == null || clearanceType == "")
        //return trackYourProposalRepository.getListOfStatus();
        //else {
        //if(clearanceType.equals("2")){
//        if (workgroupId.equals("2")) {
//            //log.info("FC" + workgroupId);
//
//            if (clearanceType == null || clearanceType == "")
//                return trackYourProposalRepository.getListOfStatus();
//
//            String wgId = "";
//            if (clearanceType.equals("1") || clearanceType.equals("12"))
//                wgId = "1";
//            else if (clearanceType.equals("8"))
//                wgId = "2";
//            else if (clearanceType.equals("7"))
//                wgId = "3";
//            else if (clearanceType.equals("9"))
//                wgId = "4";
//
//            //log.info(wgId);
//            return trackYourProposalRepository.getListOfFCStatusById(Integer.parseInt(wgId));
//        }
//        if (workgroupId.equals("4")) {
//            //CRZ
//            log.info("Getting CRZ Status");
//            return trackYourProposalRepository.getListOfCRZStatusById(Integer.parseInt(workgroupId));
//
//        } else {
//            log.info("getting ec status");
//            return trackYourProposalRepository.getListOfECStatusById(Integer.parseInt(workgroupId),clearanceType);
//
//        }

        //}
        //return trackYourProposalRepository.getListOfStatusById(Integer.parseInt(workgroupId));
    }

    public List<TrackYourProposalModel> getCrzDataOnBasesOfSingleWindowNo(String swno, String workgroupId) {
        log.info("============================== getting CRZ Data On Bases Of Single Window No ===========================");
        return trackYourProposalRepository.searchCrzProposal(workgroupId, "", "", "", "", "", "", "", swno, null);

    }
    
    public String getStatusString(String status, String clearanceId) {
    	
    	List<String> statusStr = new ArrayList<String>();
    	
    	statusStr = trackYourProposalRepository.getPendingStatus(status, Integer.valueOf(clearanceId));
    	
    	return StringUtils.join(statusStr);
    }
}
