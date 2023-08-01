package com.backend.crz.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.crz.service.CrzUserService;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.CrzAgendaDetailEntity;
import com.backend.model.CrzAgendaMomDetailEntity;
import com.backend.model.CrzAgendaParticipantDto;
import com.backend.model.CrzMasterConditionsDto;
import com.backend.model.CrzMomChairmanQueriesDto;
import com.backend.model.CrzProponentApplicationEntity;
import com.backend.model.CrzProposalDraftForApprovalDto;
import com.backend.model.CrzProposalProcessFileDto;
import com.backend.model.CrzProposalTimelineDto;
import com.backend.model.CrzQueryDetailsDto;
import com.backend.model.CrzResponseDetailsDto;
import com.backend.model.certificate.ClearanceMatrix;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;

@RestController
@RequestMapping("/crz")
public class CrzUserController {

    @Autowired
    public CrzUserService crzService;

    @GetMapping("/getApplicationsListMyTask")
    public ResponseEntity<Object> getApplicationsListMyTask(@RequestParam("user_id") Integer user_id,
                                                            @RequestParam(required = false, name = "role") String role) throws ParseException {
        return ResponseHandler.generateResponse("Get Crz Applications ListView for MyTask -------> with id " + user_id,
                HttpStatus.OK, "", crzService.getCrzApplicationsListView(user_id, "crzMyTask"));
    }

    @GetMapping("/getApplicationsListAgenda")
    public ResponseEntity<Object> getApplicationsListAgenda(@RequestParam("user_id") Integer user_id,
                                                            @RequestParam(required = false, name = "role") String role) throws ParseException {
        return ResponseHandler.generateResponse("Get Crz Applications ListView for Agenda-------> with id " + user_id,
                HttpStatus.OK, "", crzService.getCrzApplicationsListView(user_id, "crzAgenda"));
    }

    @GetMapping("/getApplicationsListPorposalHistory")
    public ResponseEntity<Object> getApplicationsListPorposalHistory(@RequestParam("user_id") Integer user_id,
                                                                     @RequestParam(required = false, name = "role") String role) throws ParseException {
        return ResponseHandler.generateResponse(
                "Get Crz Applications ListView for Porposal History-------> with id " + user_id, HttpStatus.OK, "",
                crzService.getCrzApplicationsListView(user_id, "crzPorposalHistory"));
    }

    @GetMapping("/getApplicationsListRaisedEDS")
    public ResponseEntity<Object> getApplicationsListRaisedEDS(@RequestParam("user_id") Integer user_id,
                                                               @RequestParam(required = false, name = "role") String role) throws ParseException {
        return ResponseHandler.generateResponse(
                "Get Crz Applications ListView for EDS Raised-------> with id " + user_id, HttpStatus.OK, "",
                crzService.getCrzApplicationsListView(user_id, "raisedEDS"));
    }

    @PostMapping("/updateApplicationStatus")
    public ResponseEntity<Object> updateProponentApplicationStatus(
            @RequestParam("app_history_id") Integer app_history_id, @RequestParam("status") String status,
            HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("updated Proponent Application Status", HttpStatus.OK, "",
                crzService.updateProponentApplicationStatus(app_history_id, status, request));
    }

    @GetMapping("/getCommitteeMembers")
    public ResponseEntity<Object> getAllCommitteeMembersDetails() {
        return crzService.getAllCommitteeMembersDetails();
    }

    // Additional method (not used in UI side, need to remove before UAT)
    @GetMapping("/getAgendaParticipants")
    public ResponseEntity<Object> getAllAgendaParticipants() {
        return crzService.getAllAgendaParticipants();
    }

    // Additional method (not used in UI side, need to remove before UAT)
    @PostMapping("/saveAgendaParticipants")
    public ResponseEntity<Object> saveAllAgendaParticipants(
            @RequestBody List<CrzAgendaParticipantDto> crzAgendaParticipantDTO, HttpServletRequest request)
            throws PariveshException {
        return ResponseHandler.generateResponse("Saved Agenda Participant", HttpStatus.OK, "",
                crzService.saveAllAgendaParticipants(crzAgendaParticipantDTO, request));
    }

    @PostMapping("/saveApplicationFileNo")
    public ResponseEntity<Object> saveProponentApplicationFileNo(
            @RequestBody CrzProponentApplicationEntity crzProponentApplications, HttpServletRequest request)
            throws PariveshException {
        return ResponseHandler.generateResponse("Saved Agenda Participant", HttpStatus.OK, "",
                crzService.saveProponentApplicationFileNo(crzProponentApplications, request));
    }

    @PostMapping("/saveAgendaDetails")
    public ResponseEntity<Object> saveAgendaDetails(@RequestBody CrzAgendaDetailEntity crzAgendaDetailEntity, @RequestParam("buttonType") String buttonType,
                                                    HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Saved Agenda Details", HttpStatus.OK, "",
                crzService.saveAgendaDetails(crzAgendaDetailEntity, buttonType, request));
    }

    @GetMapping("/getAgendaDetails")
    public ResponseEntity<Object> getAgendaDetailsEntity(@RequestParam("agendaId") Integer agendaId) {

        return crzService.getAgendaDetailsEntity(agendaId);
    }

    @GetMapping("/getAllAgendaDetailsList")
    public ResponseEntity<Object> getAllAgendaDetailsEntity() {
        return crzService.getAllAgendaDetailsEntity();
    }

    @GetMapping("/getAllAgendaDetailsHistoryList")
    public ResponseEntity<Object> getAllAgendaDetailsHistoryEntity() {
        return crzService.getAllAgendaDetailsHistoryEntity();
    }

    @GetMapping("/getAllAgendaListForMom")
    public ResponseEntity<Object> getAllAgendaListForMomEntity() {
        return crzService.getAllAgendaListForMomEntity();
    }

    @GetMapping("/getAllDraftMomList")
    public ResponseEntity<Object> getAllDraftMomListEntity() {
        return crzService.getAllDraftMomListEntity();
    }

    @GetMapping("/getAllMomHistoryList")
    public ResponseEntity<Object> getAllMomHistoryEntity() {
        return crzService.getAllMomHistoryEntity();
    }

    @GetMapping("/getAllPublishMomList")
    public ResponseEntity<Object> getAllPublishMomList() {
        return crzService.getAllPublishMomList();
    }

    @PostMapping("/saveMomDetails")
    public ResponseEntity<Object> saveAgendaMomDetailsEntity(
            @RequestBody CrzAgendaMomDetailEntity crzAgendaDetailEntity, HttpServletRequest request)
            throws PariveshException {
        return ResponseHandler.generateResponse("Saved Agenda Details", HttpStatus.OK, "",
                crzService.saveAgendaMomDetailEntity(crzAgendaDetailEntity, request));
    }

    @GetMapping("/getNewAgendaId")
    public ResponseEntity<Object> getNewAgendaId() {
        return crzService.getNewAgendaId();
    }

    @GetMapping("/getNewMomId")
    public ResponseEntity<Object> getNewMomId() {
        return crzService.getNewMomId();
    }

    // Additional method (not used in UI side, need to remove before UAT)
    @PostMapping("/saveFileNoEntity")
    public ResponseEntity<Object> saveFileNoEntity(@RequestBody CrzProponentApplicationEntity crzAgendaDetailEntity,
                                                   HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Saved Agenda Details", HttpStatus.OK, "", "");
    }

    @GetMapping("/getMomDetails")
    public ResponseEntity<Object> getMomDetailsEntity(@RequestParam("momId") Integer momId) {
        return crzService.getMomDetailsEntity(momId);
    }

    // This API is to used to map data while creating fresh MOM from the Agenda Id
    @GetMapping("/getAgendaDetailsForMom")
    public ResponseEntity<Object> getAgendaDetailsForMomEntity(@RequestParam("agendaId") Integer agendaId) {

        return crzService.getAgendaDetailsForMomEntity(agendaId);
    }

    @GetMapping("/getAllSubmitMomList")
    public ResponseEntity<Object> getAllSubmitMomListEntity() {
        return crzService.getAllSubmitMomListEntity();
    }

    @GetMapping("/getAllMomListReceivedFromChairman")
    public ResponseEntity<Object> getAllMomListReceivedFromChairman() {
        return crzService.getAllChairmanApprovedMomListEntity();
    }

    @PostMapping("/updateMomStatusByChairman")
    public ResponseEntity<Object> updateMomStatusByChairman(@RequestParam("mom_id") Integer mom_id,
                                                            @RequestParam("status") String status, HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Mom Status Updated By Chairman", HttpStatus.OK, "",
                crzService.updateMomStatusByChairman(mom_id, status, request));
    }

    // This API is to used to update status of table proponent_applications and
    // caf_details
    @PostMapping("/updateApplicationStatusForEDS")
    public ResponseEntity<Object> updateApplicationStatusForEDS(@RequestParam("application_id") Integer application_id,
                                                                @RequestParam("app_history_id") Integer app_history_id, @RequestParam("status") String status,
                                                                HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("updated Proponent Application Status", HttpStatus.OK, "",
                crzService.updateApplicationStatusForEDS(application_id, app_history_id, status, request));
    }

    @GetMapping("/getEDSQueries")
    public ResponseEntity<Object> getEDSQueries(@RequestParam("application_id") Integer application_id) {
        return crzService.getEDSQueriesForProposals(application_id);
    }

    @GetMapping("/getMomChairmanQueries")
    public ResponseEntity<Object> getMomChairmanQueriesEntity(@RequestParam("mom_id") Integer mom_id) {
        return crzService.getMomChairmanQueriesEntity(mom_id);
    }

    @PostMapping("/saveMomChairmanQuery")
    public ResponseEntity<Object> saveMomChairmanQuery(
            @Valid @RequestBody List<CrzMomChairmanQueriesDto> crzMomChairmanQueriesDto, HttpServletRequest request)
            throws PariveshException {
        return ResponseHandler.generateResponse("Chairman Query Saved Successfully", HttpStatus.OK, "",
                crzService.saveMomChairmanQuery(crzMomChairmanQueriesDto, request));
    }

    @GetMapping("/getConditons")
    public ResponseEntity<Object> getAllConditionDetails() {
        return crzService.getAllConditionDetails();
    }
    
    @GetMapping("/getConditonsByType")
    public ResponseEntity<Object> getConditionDetailsByType(@RequestParam("category") String type) {
        return crzService.getConditionDetailsByType(type);
    }
    
    @GetMapping("/getConditonsByStateId")
    public ResponseEntity<Object> getConditionDetailsByStateAndType(@RequestParam("state_id") int stateId, @RequestParam("category") String type) {
        return crzService.getConditionDetailsByStateIdAndType(stateId, type);
    }
    
    @PostMapping("/saveConditons")
    public ResponseEntity<Object> saveConditons(@RequestBody CrzMasterConditionsDto Conditions) throws PariveshException {
        return ResponseHandler.generateResponse("Save Conditons", HttpStatus.OK, "", crzService.saveConditions(Conditions));
    }


    @PostMapping("/saveProposalProcessFile")
    public ResponseEntity<Object> saveProposalProcessFile(
            @Valid @RequestBody CrzProposalProcessFileDto crzProposalProcessFileDto, HttpServletRequest request)
            throws PariveshException {
        return ResponseHandler.generateResponse("Proposal Process File Saved Successfully", HttpStatus.OK, "",
                crzService.saveProposalProcessFile(crzProposalProcessFileDto, request));
    }

    @GetMapping("/getProposalProcessFile")
    public ResponseEntity<Object> getProposalProcessFile(@RequestParam("proposal_id") Integer proposal_id) {
        return crzService.getProposalProcessFile(proposal_id);
    }

    @PostMapping("/saveProposalDraftForApproval")
    public ResponseEntity<Object> saveProposalDraftForApproval(
            @Valid @RequestBody CrzProposalDraftForApprovalDto crzProposalDraftForApprovalDto,
            HttpServletRequest request) throws PariveshException {
        return ResponseHandler.generateResponse("Proposal Process File Saved Successfully", HttpStatus.OK, "",
                crzService.saveProposalDraftForApproval(crzProposalDraftForApprovalDto, request));
    }

    @GetMapping("/getProposalDraftForApproval")
    public ResponseEntity<Object> getProposalDraftForApproval(@RequestParam("proposal_id") Integer proposal_id) {
        return crzService.getProposalDraftForApproval(proposal_id);
    }

    @GetMapping("/getMomApprovedProposal")
    public ResponseEntity<Object> getMomApprovedProposal(@RequestParam("user_id") Integer user_id) {
        return crzService.getMomApprovedProposal(user_id);
    }

    @GetMapping("/getMomRecommendation")
    public ResponseEntity<Object> getMomRecommendation() {
        return crzService.getMomRecommendation();

    }
    @GetMapping("/getListOfMomStatusQueryReplied")
    public ResponseEntity<Object> getListOfMomStatusQueryReplied() {

        return crzService.getListOfMomStatusQueryReplied();

    }
    @GetMapping("/getApplicationsForwarded")
    public ResponseEntity<Object> getApplicationsForwarded(@RequestParam("user_id") Integer user_id,
                                                           @RequestParam(required = false, name = "role") String role) throws ParseException {
        return ResponseHandler.generateResponse("Get Crz Applications ListView of Forwarded Proposals -------> to id " + user_id,
                HttpStatus.OK, "", crzService.getCrzApplicationsListView(user_id, "forwardedProposals"));
    }
    @GetMapping("/getMinisterActionStatus")
    public ResponseEntity<Object> getMinisterActionStatus() {
        return crzService.getMinisterActionStatus();
    }

    @GetMapping("/getAcceptedProposals")
    public ResponseEntity<Object>  getApprovedProposalByMinister(@RequestParam("user_id") Integer user_id)throws ParseException
    {
        return ResponseHandler.generateResponse("Get Crz Applications ListView of Approved Proposals -------> to id " + user_id,
                HttpStatus.OK, "", crzService.getCrzApplicationsListView(user_id,"approvedProposals"));
    }

	@GetMapping("/getQueryDetails")
	public ResponseEntity<Object> getQueryDetails(@RequestParam("application_id") Integer application_id) {
		return crzService.getQueryDetails(application_id);
	}
	
	@PostMapping("/saveQueryDetails")
	public ResponseEntity<Object> saveQueryDetails(
			@Valid @RequestBody List<CrzQueryDetailsDto> crzQueryDetailsDtoList, HttpServletRequest request)
			throws PariveshException {
		return ResponseHandler.generateResponse("Crz Query Details Saved Successfully", HttpStatus.OK, "",
				crzService.saveQueryDetails(crzQueryDetailsDtoList, request));
	}
	
	@PostMapping("/saveResponseDetails")
	public ResponseEntity<Object> saveResponseDetails(
			@Valid @RequestBody List<CrzResponseDetailsDto> crzResponseDetailsDtoList, HttpServletRequest request)
			throws PariveshException {
		return ResponseHandler.generateResponse("Crz Response Details Saved Successfully", HttpStatus.OK, "",
				crzService.saveResponseDetails(crzResponseDetailsDtoList, request));
	}
	
	@PostMapping("/saveProposalHistoryTimeline")
	public ResponseEntity<Object> saveProposalHistoryTimeline(
			@Valid @RequestBody List<CrzProposalTimelineDto> crzProposalTimelineDto, HttpServletRequest request)
			throws PariveshException {
		return ResponseHandler.generateResponse("Proposal History Timeline Saved Successfully", HttpStatus.OK, "",
				crzService.saveProposalHistoryTimeline(crzProposalTimelineDto, request));
	}

	@GetMapping("/getProposalHistoryTimeline")
	public ResponseEntity<Object> getProposalHistoryTimeline(@RequestParam("proposal_app_id") Integer proposal_app_id) {

		return crzService.getProposalHistoryTimeline(proposal_app_id);
	}
	
	@GetMapping("/getMasterStatus")
	public ResponseEntity<Object> getMasterStatus() {

		return crzService.getMasterStatus();
	}

}