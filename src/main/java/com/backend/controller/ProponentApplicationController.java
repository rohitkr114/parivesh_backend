package com.backend.controller;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.AddCommittee;
import com.backend.scheduler.UpdateOtherPropertyScheduler;
import com.backend.scheduler.UpdateProposalDurationScheduler;
import com.backend.security.CurrentUser;
import com.backend.service.ProponentApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/proponentApplicant")
public class ProponentApplicationController {

	@Autowired
	ProponentApplicationService proponentApplicationService;

	@Autowired
	private UpdateProposalDurationScheduler proposalDurationScheduler;

	@Autowired
	private UpdateOtherPropertyScheduler updateOtherPropertyScheduler;

	@PostMapping("/getCafDataByProposalNo")
	public ResponseEntity<Object> getCafDataByProposalNo(
			@RequestParam(name = "proposal_no", required = false) String proposalNo,
			@RequestParam(name = "proposal_id", required = false) Integer proposal_id,
			@RequestParam(name = "file_no", required = false) String fileNo) {

		return proponentApplicationService.getCafDataByProposalNo(proposalNo, proposal_id, fileNo);

	}

	@PostMapping("/getClearancesByProjectId")
	public ResponseEntity<Object> getProponentDataByProjectId(@RequestParam(name = "project_id") Integer projectId) {

		return proponentApplicationService.getProponentDataByProjectId(projectId);

	}

	@PostMapping("/getClearancesCount")
	public ResponseEntity<Object> getClearancesCount(
			@RequestParam(defaultValue = "0", name = "project_id", required = false) Integer projectId,
			@CurrentUser UserPrincipal principal) {

		return proponentApplicationService.getClearancesCount(projectId, principal);

	}

	@Transactional
	@PostMapping("/updateByProposalNo/V3")
	public ResponseEntity<Object> updateProponentApplicationByProposalNo(@RequestParam String proposalNo,
			@RequestParam(value = "SectorId", required = false) Long Sector_Id,
			@RequestParam(value = "OfficeEntityId", required = false) Long Office_Entity,
			@RequestParam(value = "WorkGroupEntityId", required = false) Long workGroupEntity,
			@RequestParam(value = "RoleId", required = false) Long role,
			@RequestParam(value = "UserId", required = false) Integer userId,
			@RequestParam(value = "Status", required = false) String status,
			@RequestBody(required = false) String last_remarks,
			@RequestParam(value = "moefccFileNumber", required = false) String moefccFileNumber,
			@RequestParam(value = "isState", required = false, defaultValue = "false") Boolean isState) {
		return proponentApplicationService.updateProponentApplication(proposalNo, Sector_Id, Office_Entity,
				workGroupEntity, role, userId, status, last_remarks, moefccFileNumber, isState);
	}

	@Transactional
	@PostMapping("/updateByProposalNo/V2")
	public ResponseEntity<Object> updateProponentApplicationByProposalNoV2(@RequestParam String proposalNo,
//			@RequestParam(value = "SectorId", required = false) Long Sector_Id,
//			@RequestParam(value = "OfficeEntityId", required = false) Long Office_Entity,
//			@RequestParam(value = "WorkGroupEntityId", required = false) Long workGroupEntity,
//			@RequestParam(value = "RoleId", required = false) Long role,
//			@RequestParam(value = "UserId", required = false) Integer userId,
//			@RequestParam(value = "Status", required = false) String status,
			@RequestBody(required = false) String other_property
//			@RequestParam(value = "moefccFileNumber", required = false) String moefccFileNumber
	) {

//		return proponentApplicationService.updateProponentApplication(proposalNo, Sector_Id, Office_Entity,
//				workGroupEntity, role, userId, status, last_remarks, moefccFileNumber);
		return proponentApplicationService.updateProponentApplicationV2(proposalNo, other_property);
	}

	@Transactional
	@PostMapping("/updateByProposalNo")
	public ResponseEntity<Object> updateProponentApplicationByProposalNoV3(@RequestParam String proposalNo,
			@RequestParam(value = "Status", required = false) String status,
			@RequestBody(required = false) String last_remarks) {

//		return proponentApplicationService.updateProponentApplication(proposalNo, Sector_Id, Office_Entity,
//				workGroupEntity, role, userId, status, last_remarks, moefccFileNumber);
		return proponentApplicationService.updateProponentApplicationV3(proposalNo, status, last_remarks);
	}

	@PostMapping("/list")
	public ResponseEntity<Object> getProponentApplication(
			@RequestParam(name = "status", required = false) String status, @CurrentUser UserPrincipal principal) {
		return proponentApplicationService.getProponentApplication(status, principal.getId());
	}

	@PostMapping("/edsList")
	public ResponseEntity<Object> getEDSRaisedApplications(@CurrentUser UserPrincipal principal) {
		return proponentApplicationService.getEDSRaisedApplications(principal.getId());
	}

	@PostMapping("addTransferProposalNumber")
	public ResponseEntity<Object> setTransferProposalNumber(@RequestParam(value = "proposal_no") String proposal_no,
			@RequestBody String remarks, @RequestBody Date date, @RequestBody String transfer_proposal_no) {
		return proponentApplicationService.setTransferProposalNumber(proposal_no, remarks, date, transfer_proposal_no);
	}

	@PostMapping("/copyProposal")
	public ResponseEntity<Object> copyProposal(@RequestParam Integer proposalId) throws PariveshException {
		return proponentApplicationService.copyProposal(proposalId);
	}

	@PostMapping("/copyProposalCrz")
	public ResponseEntity<Object> copyProposalCrz(@RequestParam Integer proposalId) throws PariveshException {
		return proponentApplicationService.copyProposalCrz(proposalId);
	}

	@PostMapping("/copyProposalEcPartC")
	public ResponseEntity<Object> copyProposalEcPartC(@RequestParam Integer proposalId) throws PariveshException {
		return proponentApplicationService.copyProposalEcPartC(proposalId);
	}

	@PostMapping("/getOfficeJsonFc")
	public ResponseEntity<Object> getOfficeJsonFc(@RequestParam Integer proposalId) throws PariveshException {

		return proponentApplicationService.getOfficeJsonFc(proposalId);

	}

	@PostMapping("/updateCertificateURL")
	public ResponseEntity<Object> updateCertificateURL(@RequestParam Integer id, @RequestParam String url,
			@RequestParam String type) throws PariveshException {

		return proponentApplicationService.updateCertificateURL(id, url, type);

	}

	@PostMapping("/getProposalList")
	public ResponseEntity<Object> getProposalList(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size, @CurrentUser UserPrincipal user,
			@RequestParam String status) {

		return proponentApplicationService.getProposalList(page, size, user, status);

	}

	@PostMapping("/getECProposalList")
	public ResponseEntity<Object> getECProposalList(@CurrentUser UserPrincipal principal,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size,
			@RequestParam String status) {

		return proponentApplicationService.getECProposalList(principal.getId(), page, size, status);

	}

	@PostMapping("/getApplicationDivision")
	public ResponseEntity<Object> getApplicationDivision(@RequestParam Integer application_id) {

		return proponentApplicationService.getApplicationDivision(application_id);
	}

	@PostMapping("/getStage1CompletedProposal")
	public ResponseEntity<Object> getStage1CompletedProposal(@CurrentUser UserPrincipal userPrincipal) {
		return proponentApplicationService.getStage1CompletedProposal(userPrincipal);
	}

	@PostMapping("/addCommittee")
	public ResponseEntity<Object> addCommittee(@RequestBody AddCommittee committee) {
		return proponentApplicationService.addCommittee(committee);
	}

	@PostMapping("/getCommitteeList")
	public ResponseEntity<Object> getCommitteeList(@RequestParam String committeeType,
			@RequestParam(required = false) Integer id) {
		return proponentApplicationService.getCommitteeList(committeeType, id);
	}

	@PostMapping("/deleteCommittee")
	public ResponseEntity<Object> deleteCommittee(@RequestParam Integer id) {
		return proponentApplicationService.deleteCommittee(id);
	}

	@PostMapping(value = "/updateProposalDuration")
	public String updateProposalDuration() {
		proposalDurationScheduler.scheduleUpdate();
		return "scheduler worked";
	}

	@PostMapping("/updateSelectedSector")
	public ResponseEntity<Object> updateSelectedSector(@CurrentUser UserPrincipal userPrincipal,
			@RequestParam Integer selectedSector) {
		return proponentApplicationService.updateSelectedSector(userPrincipal, selectedSector);
	}

	@PostMapping("/copyProposalFcFormA")
	public ResponseEntity<Object> copyProposalFcFormA(@RequestParam Integer proposalId) throws PariveshException {
		return proponentApplicationService.copyProposalFcFormA(proposalId);
	}

	@PostMapping("/getFcCertificateConditions")
	public ResponseEntity<Object> getFcCertificateConditions(@RequestParam Integer applicationId,
			@RequestParam String type) {
		return proponentApplicationService.getFcCertificateConditions(applicationId, type);
	}

	@PostMapping("/copyProposalFcFormB")
	public ResponseEntity<Object> copyProposalFcFormB(@RequestParam Integer proposalId) throws PariveshException {
		return proponentApplicationService.copyProposalFcFormB(proposalId);
	}

	@PostMapping("/updateOtherProperty")
	public List<String> updateOtherProp(){
		return updateOtherPropertyScheduler.updateOtherProp();
	}

	@PostMapping("/getProposalsOfProjects")
	public ResponseEntity<Object> getProjectProposalDetails(@RequestParam Integer applicationId){
		return proponentApplicationService.getProjectProposalDetails(applicationId);
	}

	@PostMapping("/copyProposalFcFormC")
	public ResponseEntity<Object> copyProposalFcFormC(@RequestParam Integer proposalId) throws PariveshException{
		return proponentApplicationService.copyProposalFcFormC(proposalId);
	}

	@PostMapping("/copyProposalFcFormD")
	public ResponseEntity<Object> copyProposalFcFormD(@RequestParam Integer proposalId) throws PariveshException{
		return proponentApplicationService.copyProposalFcFormD(proposalId);
	}

	@PostMapping("/copyProposalFcFormE")
	public ResponseEntity<Object> copyProposalFcFormE(@RequestParam Integer proposalId) throws PariveshException{
		return proponentApplicationService.copyProposalFcFormE(proposalId);
	}
}
