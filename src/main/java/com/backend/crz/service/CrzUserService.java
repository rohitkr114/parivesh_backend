package com.backend.crz.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.crz.constant.CrzAppConstant.Condition_Status;
import com.backend.crz.constant.CrzAppConstant.Condition_Type;
import com.backend.crz.repository.CrzAgendaAttachmentsMomRepository;
import com.backend.crz.repository.CrzAgendaAttachmentsRepository;
import com.backend.crz.repository.CrzAgendaDetailsRepository;
import com.backend.crz.repository.CrzAgendaMomDelibrationRepository;
import com.backend.crz.repository.CrzAgendaMomDetailRepository;
import com.backend.crz.repository.CrzAgendaOtherItemsRepository;
import com.backend.crz.repository.CrzAgendaParticipantsRepository;
import com.backend.crz.repository.CrzAgendaProposalsRepository;
import com.backend.crz.repository.CrzAttachmentsDetailRepository;
import com.backend.crz.repository.CrzCommitteeMembersRepository;
import com.backend.crz.repository.CrzDashboardDtoRepoitory;
import com.backend.crz.repository.CrzMasterConditionsRepository;
import com.backend.crz.repository.CrzMasterStatusRepository;
import com.backend.crz.repository.CrzMinisterActionStatusRepository;
import com.backend.crz.repository.CrzMomChairmanQueriesRepository;
import com.backend.crz.repository.CrzMomRecommendationRepository;
import com.backend.crz.repository.CrzProponentApplicationRepository;
import com.backend.crz.repository.CrzProposalConditionMappingRepository;
import com.backend.crz.repository.CrzProposalDraftForApprovalRepository;
import com.backend.crz.repository.CrzProposalProcessFileHistoryRepository;
import com.backend.crz.repository.CrzProposalProcessFileRepository;
import com.backend.crz.repository.CrzProposalTimelineRepository;
import com.backend.crz.repository.CrzQueryDetailsRepository;
import com.backend.crz.repository.CrzResponseDetailsRepository;
import com.backend.exceptions.PariveshException;
import com.backend.model.CrzAgendaAttachmentDto;
import com.backend.model.CrzAgendaAttachmentMomDto;
import com.backend.model.CrzAgendaDetailEntity;
import com.backend.model.CrzAgendaDetailsDto;
import com.backend.model.CrzAgendaMomDetailEntity;
import com.backend.model.CrzAgendaMomDto;
import com.backend.model.CrzAgendaOtherItemsDto;
import com.backend.model.CrzAgendaParticipantDto;
import com.backend.model.CrzAgendaProposalsDto;
import com.backend.model.CrzAttachmentsDetail;
import com.backend.model.CrzCommitteeMembersDto;
import com.backend.model.CrzDashboardDtoVersion;
import com.backend.model.CrzMasterConditionsDto;
import com.backend.model.CrzMasterStatusDto;
import com.backend.model.CrzMomChairmanQueriesDto;
import com.backend.model.CrzMomRecommendations;
import com.backend.model.CrzProponentApplicationEntity;
import com.backend.model.CrzProposalConditionMappingDto;
import com.backend.model.CrzProposalConditionMappingEntity;
import com.backend.model.CrzProposalDraftForApprovalDto;
import com.backend.model.CrzProposalProcessFileDto;
import com.backend.model.CrzProposalProcessFileHistoryDto;
import com.backend.model.CrzProposalTimelineDto;
import com.backend.model.CrzQueryDetailsDto;
import com.backend.model.CrzResponseDetailsDto;
import com.backend.model.Crz.CrzMinisterActionStatus;
import com.backend.model.EDSV2.EDSFormV2;
import com.backend.model.certificate.ClearanceMatrix;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.EDSV2.EDSFormV2Repository;
import com.backend.response.ResponseHandler;
import com.backend.service.UpdateOtherPropertyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class CrzUserService {

	@Autowired
	private UpdateOtherPropertyService updateOtherPropertyService;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private CrzCommitteeMembersRepository crzCommitteeMembersRepository;

	@Autowired
	private CrzAgendaParticipantsRepository crzAgendaParticipantsRepository;

	@Autowired
	private CrzAgendaDetailsRepository crzAgendaDetailsRepository;

	@Autowired
	private CrzAgendaProposalsRepository crzAgendaProposalsRepository;

	@Autowired
	private CrzAgendaMomDetailRepository crzAgendaMomDetailRepository;

	@Autowired
	private CrzAgendaMomDelibrationRepository crzAgendaMomDelibrationRepository;

	@Autowired
	private CrzDashboardDtoRepoitory dashboardDtoRepoitory;

	@Autowired
	private CrzAgendaOtherItemsRepository crzAgendaOtherItemsRepository;

	@Autowired
	private CrzProponentApplicationRepository crzProponentApplicationRepository;

	@Autowired
	private CrzMomChairmanQueriesRepository crzMomChairmanQueriesRepository;

	@Autowired
	private CrzProposalProcessFileHistoryRepository crzProposalProcessFileHistoryRepository;

	@Autowired
	private CrzProposalProcessFileRepository crzProposalProcessFileDtoRepository;

	@Autowired
	private CrzProposalDraftForApprovalRepository crzProposalDraftForApprovalRepository;

	@Autowired
	private EDSFormV2Repository eDSFormV2Repository;

	@Autowired
	private CrzAgendaAttachmentsRepository agendaAttachmentsRepository;

	@Autowired
	private CrzMasterConditionsRepository crzMasterConditionsRepository;

	@Autowired
	private CrzProposalConditionMappingRepository crzProposalConditionMappingRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CrzAgendaAttachmentsMomRepository agendaAttachmentsMomRepository;

	@Autowired
	private CrzAttachmentsDetailRepository crzAttachmentsDetailRepository;

	@Autowired
	CrzMomRecommendationRepository crzMomRecommendationRepository;

	@Autowired
	private CrzMinisterActionStatusRepository crzMinisterActionStatusRepository;

	@Autowired
	private CrzQueryDetailsRepository crzQueryDetailsRepository;

	@Autowired
	private CrzResponseDetailsRepository crzResponseDetailsRepository;

	@Autowired
	private CrzProposalTimelineRepository crzProposalTimelineRepository;

	@Autowired
	private CrzMasterStatusRepository crzMasterStatusRepository;

	public List<CrzDashboardDtoVersion> getCrzApplicationsListView(Integer user_id, String crzView)
			throws ParseException {

		List<CrzDashboardDtoVersion> applicationProcessHistoriesV2 = new ArrayList<>();
		log.info(user_id.toString());
		String rawJson = "";
		try {
			if (crzView.equals("crzMyTask")) {
				rawJson = dashboardDtoRepoitory.getCrzApplicationsListMyTask(user_id);
			} else if (crzView.equals("crzAgenda")) {
				rawJson = dashboardDtoRepoitory.getCrzApplicationsListAgenda(user_id);
			} else if (crzView.equals("raisedEDS")) {
				rawJson = dashboardDtoRepoitory.getCrzApplicationsListRaisedEDS(user_id);
			} else if (crzView.equals("forwardedProposals")) {
				rawJson = dashboardDtoRepoitory.getCrzApplicationsListForwarded(user_id);
			} else if (crzView.equals("approvedProposals")) {
				rawJson = dashboardDtoRepoitory.getCrzApplicationsListApproved(user_id);
			} else
				rawJson = dashboardDtoRepoitory.getApplicationsListPorposalHistory(user_id);

//			log.info("------------------------------------>", rawJson);
			JSONArray result = new JSONArray(rawJson);
//			log.info("------------------------------------>", result);
			for (int i = 0; i < result.length(); i++) {

				JSONObject jsonObj = result.getJSONObject(i);

//				log.info("------------------------------------>", jsonObj);

				CrzDashboardDtoVersion dto = new CrzDashboardDtoVersion();
				if (jsonObj.isNull("id")) {
					dto.setId(0);
				} else {
					dto.setId(jsonObj.getInt("id"));
				}
				if (jsonObj.isNull("application_id")) {
					dto.setApplication_id(0);
				} else {
					dto.setApplication_id(jsonObj.getInt("application_id"));
				}
				if (jsonObj.isNull("proposal_app_id")) {
					dto.setProposal_app_id(0);
				} else {
					dto.setProposal_app_id(jsonObj.getInt("proposal_app_id"));
				}
				if (jsonObj.isNull("proposal_no")) {
					dto.setProposal_no("");
				} else {
					dto.setProposal_no(jsonObj.getString("proposal_no"));
				}
				if (jsonObj.isNull("status")) {
					dto.setStatus("");
				} else {
					dto.setStatus(jsonObj.getString("status"));
				}
				if (jsonObj.isNull("project_name")) {
					dto.setProject_name("");
				} else {
					dto.setProject_name(jsonObj.getString("project_name"));
				}
				if (jsonObj.isNull("workgroup_name")) {
					dto.setWorkgroup_name("");
				} else {
					dto.setWorkgroup_name(jsonObj.getString("workgroup_name"));
				}
				if (jsonObj.isNull("officename")) {
					dto.setOfficename("");
				} else {
					dto.setOfficename(jsonObj.getString("officename"));
				}
				if (jsonObj.isNull("rolename")) {
					dto.setRolename("");
				} else {
					dto.setRolename(jsonObj.getString("rolename"));
				}
				if (jsonObj.isNull("current_step_id")) {
					dto.setCurrent_step_id(0);
				} else {
					dto.setCurrent_step_id(jsonObj.getInt("current_step_id"));
				}
				if (jsonObj.isNull("process_step_mapping_id")) {
					dto.setProcess_step_mapping_id(0);
				} else {
					dto.setProcess_step_mapping_id(jsonObj.getInt("process_step_mapping_id"));
				}
				if (jsonObj.isNull("role_id")) {
					dto.setRole_id(0);
				} else {
					dto.setRole_id(jsonObj.getInt("role_id"));
				}
				if (jsonObj.isNull("office_id")) {
					dto.setOffice_id(0);
				} else {
					dto.setOffice_id(jsonObj.getInt("office_id"));
				}
				if (jsonObj.isNull("start_date")) {
					dto.setStart_date("");
				} else {
					dto.setStart_date(jsonObj.getString("start_date"));
				}
				if (jsonObj.isNull("proposal_json")) {
					dto.setProposal_json("");
				} else {
					dto.setProposal_json(jsonObj.getString("proposal_json"));
				}
				if (jsonObj.isNull("process_id")) {
					dto.setProcess_id(0);
				} else {
					dto.setProcess_id(jsonObj.getInt("process_id"));
				}
				if (jsonObj.isNull("other_property")) {
					dto.setOther_property("");
				} else {
					dto.setOther_property(jsonObj.getString("other_property"));
				}
				if (jsonObj.isNull("workgroup_id")) {
					dto.setWorkgroup_id(0);
				} else {
					dto.setWorkgroup_id(jsonObj.getInt("workgroup_id"));
				}
				if (jsonObj.isNull("main_state")) {
					dto.setMain_state(0);
				} else {
					dto.setMain_state(jsonObj.getInt("main_state"));
				}
				if (jsonObj.isNull("main_district")) {
					dto.setMain_district(0);
				} else {
					dto.setMain_district(jsonObj.getInt("main_district"));
				}
				if (jsonObj.isNull("state_name")) {
					dto.setState_name("");
				} else {
					dto.setState_name(jsonObj.getString("state_name"));
				}
				if (jsonObj.isNull("district_name")) {
					dto.setDistrict_name("");
				} else {
					dto.setDistrict_name(jsonObj.getString("district_name"));
				}
				if (jsonObj.isNull("name_of_proponent")) {
					dto.setProponent_name("");
				} else {
					dto.setProponent_name(jsonObj.getString("name_of_proponent"));
				}
				applicationProcessHistoriesV2.add(dto);

//				log.info("------------------------------------>", dto);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
//		log.info("------------------------------------>", applicationProcessHistoriesV2.toString());
		return applicationProcessHistoriesV2;

	}

	public ResponseEntity<Object> getAllCommitteeMembersDetails() {
		try {
			Set<CrzCommitteeMembersDto> crzCommitteeMembers = crzCommitteeMembersRepository.findAllMemberByOrder();

			for (CrzCommitteeMembersDto crzCommitteeMembersDto : crzCommitteeMembers) {

				if (crzCommitteeMembersDto.getRemarks() == null || crzCommitteeMembersDto.getRemarks() == "")

					crzCommitteeMembersDto.setRemarks("Present");

			}

			return ResponseHandler.generateResponse("Crz All Committee Members List", HttpStatus.OK, "",
					crzCommitteeMembers);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting All Committee Members Details - ", e);
		}
	}

	// Additional method (not used in UI side, need to remove before UAT)
	public ResponseEntity<Object> getAllAgendaParticipants() {
		try {
			List<CrzAgendaParticipantDto> crzAllAgendaParticipants = crzAgendaParticipantsRepository.findAll();
			return ResponseHandler.generateResponse("Crz All Agenda Participants List", HttpStatus.OK, "",
					crzAllAgendaParticipants);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting All Agenda Participants - ", e);
		}
	}

	// Additional method (not used in UI side, need to remove before UAT)
	public Object saveAllAgendaParticipants(List<CrzAgendaParticipantDto> crzAgendaParticipantDTO,
			HttpServletRequest request) throws PariveshException {
		try {
			return crzAgendaParticipantsRepository.saveAll(crzAgendaParticipantDTO);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Crz Agenda Participants-", e);
		}
	}

	public Object saveAgendaDetails(CrzAgendaDetailsDto crzAgendaDetailsDto, HttpServletRequest request)
			throws PariveshException {
		try {
			return crzAgendaDetailsRepository.save(crzAgendaDetailsDto);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Crz Agenda Participants-", e);
		}
	}

	public ResponseEntity<Object> getAgendaDetailsEntity(Integer agendaId) {
		try {
			Optional<CrzAgendaDetailsDto> crzAgendaDetailsDto = crzAgendaDetailsRepository.findById(agendaId);
			CrzAgendaDetailsDto dto = crzAgendaDetailsDto.orElse(null);
			List<CrzAgendaParticipantDto> crzAgendaParticipantDTO = crzAgendaParticipantsRepository
					.findByAgenda_id(agendaId);
			List<CrzAgendaProposalsDto> crzAgendaProposalsDto = crzAgendaProposalsRepository.findByAgenda_id(agendaId);
			List<CrzAgendaAttachmentDto> agendaAttachmentDto = agendaAttachmentsRepository.findByAgendaId(agendaId);
			List<CrzAgendaOtherItemsDto> crzAgendaOtherItemsDto = crzAgendaOtherItemsRepository
					.findByAgenda_id(agendaId);
			CrzAgendaDetailEntity crzAgendaDetailEntity = new CrzAgendaDetailEntity();
			crzAgendaDetailEntity.setCrzAgendaDetails(dto);
			crzAgendaDetailEntity.setCrzAgendaParticipants(crzAgendaParticipantDTO);
			crzAgendaDetailEntity.setCrzAgendaProposals(crzAgendaProposalsDto);
			crzAgendaDetailEntity.setCrzAgendaAttachments(agendaAttachmentDto);
			crzAgendaDetailEntity.setCrzAgendaOtherItems(crzAgendaOtherItemsDto);
			return ResponseHandler.generateResponse("getting Crz Agenda Details", HttpStatus.OK, "",
					crzAgendaDetailEntity);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz Agenda Details - ", e);
		}
	}

	public Object saveAgendaDetails(CrzAgendaDetailEntity crzAgendaDetailEntity, String buttonType,
			HttpServletRequest request) throws PariveshException {
		try {
			if (crzAgendaDetailEntity.getCrzAgendaDetails().getAgenda_id() != null) {
				crzAgendaDetailsRepository.updateAgendaStatus(
						crzAgendaDetailEntity.getCrzAgendaDetails().getAgenda_id(),
						crzAgendaDetailEntity.getCrzAgendaDetails().getStatus());

				CrzAgendaDetailsDto updatedCrzAgendaDetailsDto = crzAgendaDetailsRepository
						.save(crzAgendaDetailEntity.getCrzAgendaDetails());

				if (crzAgendaParticipantsRepository
						.findByAgenda_id(updatedCrzAgendaDetailsDto.getAgenda_id()) != null) {
					crzAgendaParticipantsRepository.deleteByAgendaId(updatedCrzAgendaDetailsDto.getAgenda_id());
				}
				List<CrzAgendaParticipantDto> crzAgendaParticipantDTOSet = new ArrayList<>();
				for (CrzAgendaParticipantDto tempParticipant : crzAgendaDetailEntity.getCrzAgendaParticipants()) {
					tempParticipant.setAgenda_id(updatedCrzAgendaDetailsDto.getAgenda_id());
					crzAgendaParticipantDTOSet.add(tempParticipant);
//					crzAgendaParticipantsRepository.save(tempParticipant);
				}
				crzAgendaParticipantsRepository.saveAll(crzAgendaParticipantDTOSet);

				if (crzAgendaProposalsRepository.findByAgenda_id(updatedCrzAgendaDetailsDto.getAgenda_id()) != null)
					crzAgendaProposalsRepository.deleteByAgendaId(updatedCrzAgendaDetailsDto.getAgenda_id());

				List<CrzAgendaProposalsDto> crzAgendaProposalsDtoSet = new ArrayList<>();
				for (CrzAgendaProposalsDto tempAgendaProposal : crzAgendaDetailEntity.getCrzAgendaProposals()) {
					tempAgendaProposal.setAgenda_id(updatedCrzAgendaDetailsDto.getAgenda_id());
					crzAgendaProposalsDtoSet.add(tempAgendaProposal);

					updateAgendaProposalStatus(tempAgendaProposal.getApp_history_id(), tempAgendaProposal.getStatus(),
							buttonType);
				}
				crzAgendaProposalsRepository.saveAll(crzAgendaProposalsDtoSet);

				if (agendaAttachmentsRepository.findByAgendaId(updatedCrzAgendaDetailsDto.getAgenda_id()) != null) {
					agendaAttachmentsRepository.deleteByAgendaId(updatedCrzAgendaDetailsDto.getAgenda_id());
				}
				List<CrzAgendaAttachmentDto> crzAgendaAttachment = new ArrayList<>();
				for (CrzAgendaAttachmentDto tempAgendaAttachment : crzAgendaDetailEntity.getCrzAgendaAttachments()) {
					tempAgendaAttachment.setAgendaId(updatedCrzAgendaDetailsDto.getAgenda_id());
					crzAgendaAttachment.add(tempAgendaAttachment);
				}
				agendaAttachmentsRepository.saveAll(crzAgendaAttachment);

				if (crzAgendaOtherItemsRepository.findByAgenda_id(updatedCrzAgendaDetailsDto.getAgenda_id()) != null) {
					crzAgendaOtherItemsRepository.deleteByAgendaId(updatedCrzAgendaDetailsDto.getAgenda_id());
				}
				Set<CrzAgendaOtherItemsDto> crzAgendaOtherItemsDtoSet = new HashSet<>();
				for (CrzAgendaOtherItemsDto tempAgendaOtherItems : crzAgendaDetailEntity.getCrzAgendaOtherItems()) {
					tempAgendaOtherItems.setAgenda_id(updatedCrzAgendaDetailsDto.getAgenda_id());
					crzAgendaOtherItemsDtoSet.add(tempAgendaOtherItems);
				}
				crzAgendaOtherItemsRepository.saveAll(crzAgendaOtherItemsDtoSet);

				return ResponseHandler.generateResponse("Agenda uploaded successfully", HttpStatus.OK, null,
						"Agenda Id is " + crzAgendaDetailEntity.getCrzAgendaDetails().getAgenda_name());
			} else {
				CrzAgendaDetailsDto savedCrzAgendaDetailsDto = crzAgendaDetailsRepository
						.save(crzAgendaDetailEntity.getCrzAgendaDetails());

//				Set<CrzAgendaParticipantDto> crzAgendaParticipantDTOSet = new HashSet<>();
				for (CrzAgendaParticipantDto tempParticipant : crzAgendaDetailEntity.getCrzAgendaParticipants()) {
					tempParticipant.setAgenda_id(savedCrzAgendaDetailsDto.getAgenda_id());
//					crzAgendaParticipantDTOSet.add(tempParticipant);
					crzAgendaParticipantsRepository.save(tempParticipant);
				}
//				crzAgendaParticipantsRepository.saveAll(crzAgendaParticipantDTOSet);
				Set<CrzAgendaProposalsDto> crzAgendaProposalsDtoSet = new HashSet<>();
				for (CrzAgendaProposalsDto tempAgendaProposal : crzAgendaDetailEntity.getCrzAgendaProposals()) {
					tempAgendaProposal.setAgenda_id(savedCrzAgendaDetailsDto.getAgenda_id());
					crzAgendaProposalsDtoSet.add(tempAgendaProposal);

					updateAgendaProposalStatus(tempAgendaProposal.getApp_history_id(), tempAgendaProposal.getStatus(),
							buttonType);
				}
				crzAgendaProposalsRepository.saveAll(crzAgendaProposalsDtoSet);

				Set<CrzAgendaAttachmentDto> crzAgendaAttachmentDtoSet = new HashSet<>();
				for (CrzAgendaAttachmentDto tempAgendaAttachments : crzAgendaDetailEntity.getCrzAgendaAttachments()) {
					tempAgendaAttachments.setAgendaId(savedCrzAgendaDetailsDto.getAgenda_id());
					crzAgendaAttachmentDtoSet.add(tempAgendaAttachments);
				}
				agendaAttachmentsRepository.saveAll(crzAgendaAttachmentDtoSet);

				Set<CrzAgendaOtherItemsDto> crzAgendaOtherItemsDtoSet = new HashSet<>();
				for (CrzAgendaOtherItemsDto tempAgendaOtherItems : crzAgendaDetailEntity.getCrzAgendaOtherItems()) {
					tempAgendaOtherItems.setAgenda_id(savedCrzAgendaDetailsDto.getAgenda_id());
					crzAgendaOtherItemsDtoSet.add(tempAgendaOtherItems);
				}
				crzAgendaOtherItemsRepository.saveAll(crzAgendaOtherItemsDtoSet);

				return ResponseHandler.generateResponse("Agenda uploaded successfully", HttpStatus.OK, null,
						"Agenda Id is " + savedCrzAgendaDetailsDto.getAgenda_name());
			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Updating Agenda status-", e);
		}
	}

	public ResponseEntity<Object> getAllAgendaDetailsEntity() {
		try {
			Set<CrzAgendaDetailsDto> crzGetAllAgendaDetailsEntity = crzAgendaDetailsRepository.findByStatus();
			return ResponseHandler.generateResponse("getting Crz All Agenda Details", HttpStatus.OK, "",
					crzGetAllAgendaDetailsEntity);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz All Agenda Details - ", e);
		}
	}

	public ResponseEntity<Object> getAllAgendaDetailsHistoryEntity() {
		try {
			Set<CrzAgendaDetailsDto> crzGetAllAgendaDetailsEntity = crzAgendaDetailsRepository
					.findAllAgendaHistoryList();
			return ResponseHandler.generateResponse("getting Crz All Agenda Details", HttpStatus.OK, "",
					crzGetAllAgendaDetailsEntity);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz All Agenda Details - ", e);
		}
	}

	public ResponseEntity<Object> getAllAgendaListForMomEntity() {
		try {
			Set<CrzAgendaDetailsDto> crzGetAllAgendaDetailsEntity = crzAgendaDetailsRepository.findAgendaListForMom();
			return ResponseHandler.generateResponse("getting Crz All Agenda Details", HttpStatus.OK, "",
					crzGetAllAgendaDetailsEntity);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz All Agenda Details - ", e);
		}
	}

	public ResponseEntity<Object> getAllDraftMomListEntity() {
		try {
			Set<CrzAgendaMomDto> crzAgendaMomDto = crzAgendaMomDetailRepository.findAllDraftMomList();

			return ResponseHandler.generateResponse("getting Crz All Mom in Draft Status", HttpStatus.OK, "",
					crzAgendaMomDto);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz All Mom in Draft Status - ", e);
		}
	}

	public ResponseEntity<Object> getAllMomHistoryEntity() {
		try {
			Set<CrzAgendaMomDto> crzAgendaMomDto = crzAgendaMomDetailRepository.findAllMomHistoryList();
			return ResponseHandler.generateResponse("getting Crz All Mom History List", HttpStatus.OK, "",
					crzAgendaMomDto);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz All Mom History List- ", e);
		}
	}

	public ResponseEntity<Object> getAllPublishMomList() {
		try {
			Set<CrzAgendaMomDto> crzAgendaMomDto = crzAgendaMomDetailRepository.findAllPublishMomList();

			return ResponseHandler.generateResponse("getting Crz All Mom in Publish Status", HttpStatus.OK, "",
					crzAgendaMomDto);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz All Mom in Publish Status - ", e);
		}
	}

	public Object saveAgendaMomDetailEntity(CrzAgendaMomDetailEntity crzMomDetailEntity, HttpServletRequest request)
			throws PariveshException {
		try {

			if (crzMomDetailEntity.getCrzAgendaMom().getMom_id() != null) {
//				Integer momId = crzMomDetailEntity.getCrzAgendaMom().getMom_id();
				CrzAgendaMomDto savedCrzAgendaMomDto = crzAgendaMomDetailRepository
						.save(crzMomDetailEntity.getCrzAgendaMom());

				for (CrzAgendaParticipantDto crzAgendaParticipantDto : crzMomDetailEntity.getCrzAgendaParticipants()) {
					crzAgendaParticipantsRepository.updateAgendaParticipantRemarks(crzAgendaParticipantDto.getId(),
							crzAgendaParticipantDto.getRemarks());
				}

				List<CrzProposalConditionMappingDto> crzProposalConditionMappingDtoList = new ArrayList<>();
				List<CrzMasterConditionsDto> crzMasterConditionsDtoList = new ArrayList<>();
				for (CrzAgendaProposalsDto crzAgendaProposals : crzMomDetailEntity.getCrzAgendaProposals()) {
					crzAgendaProposalsRepository.updateAgendaProposalForMom(
							crzAgendaProposals.getMom_previous_delibration(),
							crzAgendaProposals.getMom_current_delibration(), crzAgendaProposals.getMom_recommendation(),
							crzAgendaProposals.getChairman_remarks(), crzAgendaProposals.getSalient_feature(),
							crzAgendaProposals.getId());

					CrzProposalConditionMappingEntity crzProposalConditionMappings = crzAgendaProposals
							.getCrzProposalConditionMappings();
					crzProposalConditionMappingDtoList.addAll(crzProposalConditionMappings.getGeneralConditions());
					crzProposalConditionMappingDtoList.addAll(crzProposalConditionMappings.getSpecialConditions());
//					crzProposalConditionMappingDtoList.forEach(condition -> condition.setMom_id(momId));
					for (CrzProposalConditionMappingDto crzProposalConditionMappingDto : crzProposalConditionMappings
							.getSpecialConditions()) {
//						crzProposalConditionMappingDto.setMom_id(crzMomDetailEntity.getCrzAgendaMom().getMom_id());
						if ((crzProposalConditionMappingDto.getCondition_status() == Condition_Status.NEW)
								&& (crzProposalConditionMappingDto.getType() == Condition_Type.SPECIFIC)) {
							CrzMasterConditionsDto crzMasterConditionsDto = this.modelMapper
									.map(crzProposalConditionMappingDto, CrzMasterConditionsDto.class);
							crzMasterConditionsDtoList.add(crzMasterConditionsDto);
						}
					}
				}
				crzProposalConditionMappingRepository.saveAll(crzProposalConditionMappingDtoList);
				crzMasterConditionsRepository.saveAll(crzMasterConditionsDtoList);

				if (agendaAttachmentsMomRepository.findByAgendaId(savedCrzAgendaMomDto.getAgenda_id()) != null) {
					agendaAttachmentsMomRepository.deleteByAgendaId(savedCrzAgendaMomDto.getAgenda_id());
				}

				List<CrzAgendaAttachmentMomDto> crzAgendaMomAttachment = new ArrayList<>();
				for (CrzAgendaAttachmentMomDto tempAgendaMomAttachment : crzMomDetailEntity
						.getCrzAgendaAttachmentsMom()) {
					tempAgendaMomAttachment.setAgendaId(savedCrzAgendaMomDto.getAgenda_id());
					crzAgendaMomAttachment.add(tempAgendaMomAttachment);
				}

				agendaAttachmentsMomRepository.saveAll(crzAgendaMomAttachment);

				if (crzAgendaOtherItemsRepository
						.findByAgenda_id(crzMomDetailEntity.getCrzAgendaMom().getAgenda_id()) != null)
					crzAgendaOtherItemsRepository.deleteByAgendaId(crzMomDetailEntity.getCrzAgendaMom().getAgenda_id());

				Set<CrzAgendaOtherItemsDto> crzAgendaOtherItemsDtoSet = new HashSet<>();
				for (CrzAgendaOtherItemsDto tempAgendaOtherItems : crzMomDetailEntity.getCrzAgendaOtherItems()) {
					tempAgendaOtherItems.setAgenda_id(savedCrzAgendaMomDto.getAgenda_id());
					crzAgendaOtherItemsDtoSet.add(tempAgendaOtherItems);
				}
				crzAgendaOtherItemsRepository.saveAll(crzAgendaOtherItemsDtoSet);

				crzAgendaDetailsRepository.updateAgendaStatus(crzMomDetailEntity.getCrzAgendaMom().getAgenda_id(),
						crzMomDetailEntity.getCrzAgendaMom().getStatus());

				crzAgendaMomDetailRepository.updateMomStatus(crzMomDetailEntity.getCrzAgendaMom().getMom_id(),
						crzMomDetailEntity.getCrzAgendaMom().getStatus());

				return ResponseHandler.generateResponse("Mom submitted successfully", HttpStatus.OK, null,
						crzMomDetailEntity.getCrzAgendaMom().getMom_name() + "_"
								+ crzMomDetailEntity.getCrzAgendaMom().getAgenda_name());
			} else {

				CrzAgendaMomDto savedCrzAgendaMomDto = crzAgendaMomDetailRepository
						.save(crzMomDetailEntity.getCrzAgendaMom());
				Integer momId = savedCrzAgendaMomDto.getMom_id();
				LocalDate date = LocalDate.now();
				String momName = "CRZ/MOM/" + momId + "/" + date.getMonthValue() + "/" + date.getYear();
				crzAgendaMomDetailRepository.updateMomName(momName, momId);
				for (CrzAgendaParticipantDto crzAgendaParticipantDto : crzMomDetailEntity.getCrzAgendaParticipants()) {
					crzAgendaParticipantsRepository.updateAgendaParticipantRemarks(crzAgendaParticipantDto.getId(),
							crzAgendaParticipantDto.getRemarks());
				}

				List<CrzProposalConditionMappingDto> crzProposalConditionMappingDtoList = new ArrayList<>();
				List<CrzMasterConditionsDto> crzMasterConditionsDtoList = new ArrayList<>();
				for (CrzAgendaProposalsDto crzAgendaProposals : crzMomDetailEntity.getCrzAgendaProposals()) {
					crzAgendaProposalsRepository.updateAgendaProposalForMom(
							crzAgendaProposals.getMom_previous_delibration(),
							crzAgendaProposals.getMom_current_delibration(), crzAgendaProposals.getMom_recommendation(),
							crzAgendaProposals.getChairman_remarks(), crzAgendaProposals.getSalient_feature(),
							crzAgendaProposals.getId());

					CrzProposalConditionMappingEntity crzProposalConditionMappings = crzAgendaProposals
							.getCrzProposalConditionMappings();
					crzProposalConditionMappingDtoList.addAll(crzProposalConditionMappings.getGeneralConditions());
					crzProposalConditionMappingDtoList.addAll(crzProposalConditionMappings.getSpecialConditions());
					crzProposalConditionMappingDtoList.forEach(condition -> condition.setMom_id(momId));

					for (CrzProposalConditionMappingDto crzProposalConditionMappingDto : crzProposalConditionMappings
							.getSpecialConditions()) {
//						crzProposalConditionMappingDto.setMom_id(crzMomDetailEntity.getCrzAgendaMom().getMom_id());
						if ((crzProposalConditionMappingDto.getCondition_status() == Condition_Status.NEW)
								&& (crzProposalConditionMappingDto.getType() == Condition_Type.SPECIFIC)) {
							CrzMasterConditionsDto crzMasterConditionsDto = this.modelMapper
									.map(crzProposalConditionMappingDto, CrzMasterConditionsDto.class);
							crzMasterConditionsDtoList.add(crzMasterConditionsDto);
						}
					}
				}
				crzProposalConditionMappingRepository.saveAll(crzProposalConditionMappingDtoList);
				crzMasterConditionsRepository.saveAll(crzMasterConditionsDtoList);

				Set<CrzAgendaAttachmentMomDto> crzAgendaAttachmentMomDtoSet = new HashSet<>();
				for (CrzAgendaAttachmentMomDto tempAgendaAttachmentsMom : crzMomDetailEntity
						.getCrzAgendaAttachmentsMom()) {
					tempAgendaAttachmentsMom.setAgendaId(savedCrzAgendaMomDto.getAgenda_id());
					crzAgendaAttachmentMomDtoSet.add(tempAgendaAttachmentsMom);
				}
				agendaAttachmentsMomRepository.saveAll(crzAgendaAttachmentMomDtoSet);

				if (crzAgendaOtherItemsRepository
						.findByAgenda_id(crzMomDetailEntity.getCrzAgendaMom().getAgenda_id()) != null)
					crzAgendaOtherItemsRepository.deleteByAgendaId(crzMomDetailEntity.getCrzAgendaMom().getAgenda_id());

				Set<CrzAgendaOtherItemsDto> crzAgendaOtherItemsDtoSet = new HashSet<>();
				for (CrzAgendaOtherItemsDto tempAgendaOtherItems : crzMomDetailEntity.getCrzAgendaOtherItems()) {
					tempAgendaOtherItems.setAgenda_id(savedCrzAgendaMomDto.getAgenda_id());
					crzAgendaOtherItemsDtoSet.add(tempAgendaOtherItems);
				}
				crzAgendaOtherItemsRepository.saveAll(crzAgendaOtherItemsDtoSet);

				crzAgendaDetailsRepository.updateAgendaStatus(crzMomDetailEntity.getCrzAgendaMom().getAgenda_id(),
						crzMomDetailEntity.getCrzAgendaMom().getStatus());

				return ResponseHandler.generateResponse("Mom submitted successfully", HttpStatus.OK, null,
						momName + "_" + savedCrzAgendaMomDto.getAgenda_name());
			}
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Crz Mom Details -", e);
		}
	}

	private String saveOtherProperties(Integer proposal_id, String File_no) throws PariveshException, JSONException {
		JSONArray updatedOtherPropertyJSONArray = null;
		if (proponentApplicationRepository.getOtherPropertyValues(proposal_id) != null) {
			List<String> propertyResult = Arrays
					.asList(proponentApplicationRepository.getOtherPropertyValues(proposal_id).split(","));
			HashMap<String, Object> OtherPropString = new HashMap<String, Object>();
			OtherPropString.put("Proposal For", propertyResult.get(0));
			OtherPropString.put("Project Name", propertyResult.get(1));
			if (!propertyResult.get(2).equals(null))
				OtherPropString.put("File No", File_no);
			Map<String, Object> propertyData = new HashMap<String, Object>();
			propertyData.putAll(OtherPropString);
			updatedOtherPropertyJSONArray = updateOtherPropertyService.convertMapToJSONArray(propertyData);
			return updatedOtherPropertyJSONArray.toString();
		}
		return null;
	}

	public Object saveProponentApplicationFileNo(CrzProponentApplicationEntity crzProponentApplications,
			HttpServletRequest request) throws PariveshException {
		try {
			String moefccFileNumber = crzProponentApplications.getMoefccFileNumber();
			if (moefccFileNumber != null || crzProponentApplications.isRoleIsMS() == true) {
				Integer count = crzProponentApplicationRepository.checkDuplicateFileNo(moefccFileNumber);
				if (count == 0 || crzProponentApplications.isRoleIsMS() == true) {
					Integer upd = crzProponentApplicationRepository.updateFileNo(crzProponentApplications.getId(),
							crzProponentApplications.getMoefccFileNumber(), crzProponentApplications.getLast_remarks(),
							saveOtherProperties(crzProponentApplications.getProposal_id(),
									crzProponentApplications.getMoefccFileNumber()));
				} else {
					return ResponseHandler.generateResponse("Moefcc File name already exists.", HttpStatus.BAD_REQUEST,
							"Exception", "");
				}
			}
			return ResponseHandler.generateResponse("Moefcc File name saved successfully", HttpStatus.OK, null,
					"Moefcc File name saved successfully for application " + crzProponentApplications.getId());
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Updating Agenda status-", e);
		}
	}

	public Object updateProponentApplicationStatus(Integer app_history_id, String status, HttpServletRequest request)
			throws PariveshException {
		try {
//			String moefccFileNumber = crzProponentApplications.getMoefccFileNumber();
			dashboardDtoRepoitory.updateStatusForProposalApplication(status, app_history_id);
			return ResponseHandler.generateResponse("updated Proponent Application Status", HttpStatus.OK, null,
					"updated Proponent Application Status");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Updating Proponent Application Status-", e);
		}
	}

	public ResponseEntity<Object> getNewAgendaId() {
		try {

//			Optional<Integer> crzAgendaMomDto = crzAgendaDetailsRepository.getNewAgendaId();
			// CrzAgendaMomDto momDetailDto = crzAgendaMomDto.orElse(null);
			Optional<Integer> optionalValue = Optional.ofNullable(crzAgendaDetailsRepository.getNewAgendaId());
			Integer nextAgendaId = optionalValue.orElse(0);

//			Integer nextAgendaId = crzAgendaDetailsRepository.getNewAgendaId() + 1;
			return ResponseHandler.generateResponse("Creating agenda for below Agenda Name", HttpStatus.OK, "",
					(nextAgendaId + 1));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Creating agenda ID- ", e);
		}
	}

	public ResponseEntity<Object> getNewMomId() {
		try {
			Optional<Integer> optionalValue = Optional.ofNullable(crzAgendaMomDetailRepository.getNewMomId());
			Integer nextMomId = optionalValue.orElse(0);

			return ResponseHandler.generateResponse("Creating Mom ID ", HttpStatus.OK, "", (nextMomId + 1));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Creating Mom ID- ", e);
		}
	}

	public ResponseEntity<Object> getMomDetailsEntity(Integer momId) {
		try {

			CrzAgendaMomDetailEntity crzAgendMomDetailEntity = new CrzAgendaMomDetailEntity();
			Optional<CrzAgendaMomDto> crzAgendaMomDto = crzAgendaMomDetailRepository.findById(momId);
			CrzAgendaMomDto momDetailDto = crzAgendaMomDto.orElse(null);

			Optional<CrzAgendaDetailsDto> crzAgendaDetailsDto = crzAgendaDetailsRepository
					.findById(momDetailDto.getAgenda_id());
			CrzAgendaDetailsDto agendaDetalDto = crzAgendaDetailsDto.orElse(null);
			List<CrzAgendaParticipantDto> crzAgendaParticipantDTO = crzAgendaParticipantsRepository
					.findByAgenda_id(momDetailDto.getAgenda_id());
			List<CrzAgendaProposalsDto> crzAgendaProposalsDto = crzAgendaProposalsRepository
					.findByAgenda_id(momDetailDto.getAgenda_id());
			List<CrzAgendaAttachmentMomDto> agendaAttachmentMomDto = agendaAttachmentsMomRepository
					.findByAgendaId(momDetailDto.getAgenda_id());
			List<CrzAgendaOtherItemsDto> crzAgendaOtherItemsDto = crzAgendaOtherItemsRepository
					.findByAgenda_id(momDetailDto.getAgenda_id());

//			List<CrzProposalConditionMappingDto> crzProposalConditionMappingDto = crzProposalConditionMappingRepository
//					.findByMomId(momId);

			for (CrzAgendaProposalsDto crzAgendaProposals : crzAgendaProposalsDto) {
				CrzProposalConditionMappingEntity crzProposalConditionMappings = new CrzProposalConditionMappingEntity();
				List<CrzProposalConditionMappingDto> crzProposalConditionMappingList = crzProposalConditionMappingRepository
						.findByProposalId(crzAgendaProposals.getId(), momId);

				List<CrzProposalConditionMappingDto> specialConditions = new ArrayList<>();
				List<CrzProposalConditionMappingDto> generalConditions = new ArrayList<>();
				specialConditions = crzProposalConditionMappingList.stream()
						.filter(specific -> specific.getType().ordinal() == 1).collect(Collectors.toList());
				generalConditions = crzProposalConditionMappingList.stream()
						.filter(general -> general.getType().ordinal() == 0).collect(Collectors.toList());
				crzProposalConditionMappings.setGeneralConditions(generalConditions);
				crzProposalConditionMappings.setSpecialConditions(specialConditions);
				crzAgendaProposals.setCrzProposalConditionMappings(crzProposalConditionMappings);
//				tempAgendaOtherItems.setAgenda_id(savedCrzAgendaMomDto.getAgenda_id());
//				crzAgendaOtherItemsDtoSet.add(tempAgendaOtherItems);
			}

			crzAgendMomDetailEntity.setCrzAgendaMom(momDetailDto);
			crzAgendMomDetailEntity.setCrzAgendaDetails(agendaDetalDto);
			crzAgendMomDetailEntity.setCrzAgendaParticipants(crzAgendaParticipantDTO);
			crzAgendMomDetailEntity.setCrzAgendaProposals(crzAgendaProposalsDto);
			crzAgendMomDetailEntity.setCrzAgendaAttachmentsMom(agendaAttachmentMomDto);
			crzAgendMomDetailEntity.setCrzAgendaOtherItems(crzAgendaOtherItemsDto);
//			crzAgendMomDetailEntity.setCrzProposalConditionMappings(crzProposalConditionMappingDto);
			return ResponseHandler.generateResponse("getting Crz Mom Details", HttpStatus.OK, "",
					crzAgendMomDetailEntity);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz Mom Details - ", e);
		}
	}

	public ResponseEntity<Object> getAgendaDetailsForMomEntity(Integer agendaId) {
		try {
			Optional<CrzAgendaDetailsDto> crzAgendaDetailsDto = crzAgendaDetailsRepository.findById(agendaId);
			CrzAgendaDetailsDto dto = crzAgendaDetailsDto.orElse(null);
			List<CrzAgendaParticipantDto> crzAgendaParticipantDTO = crzAgendaParticipantsRepository
					.findByAgenda_id(agendaId);

			List<CrzAgendaProposalsDto> crzAgendaProposalsDto = crzAgendaProposalsRepository.findByAgenda_id(agendaId);
			List<CrzAgendaOtherItemsDto> crzAgendaOtherItemsDto = crzAgendaOtherItemsRepository
					.findByAgenda_id(agendaId);

			for (CrzAgendaProposalsDto crzAgendaProposals : crzAgendaProposalsDto) {
				if (crzAgendaProposals.getMom_recommendation() == null
						|| crzAgendaProposals.getMom_recommendation() == "") {
					crzAgendaProposals.setMom_recommendation("NA");
				}
				CrzProposalConditionMappingEntity crzProposalConditionMappings = new CrzProposalConditionMappingEntity();
				crzProposalConditionMappings.setGeneralConditions(new ArrayList<>());
				crzProposalConditionMappings.setSpecialConditions(new ArrayList<>());
				crzAgendaProposals.setCrzProposalConditionMappings(crzProposalConditionMappings);
			}
			CrzAgendaMomDetailEntity crzAgendMomDetailEntity = new CrzAgendaMomDetailEntity();
			CrzAgendaMomDto crzAgendaMom = new CrzAgendaMomDto();
			crzAgendMomDetailEntity.setCrzAgendaMom(crzAgendaMom);
			crzAgendMomDetailEntity.setCrzAgendaDetails(dto);
			for (CrzAgendaParticipantDto crzAgendaParticipant : crzAgendaParticipantDTO) {
				if (crzAgendaParticipant.getRemarks() == null || crzAgendaParticipant.getRemarks() == "") {
					crzAgendaParticipant.setRemarks("Present");
				}
			}
			crzAgendMomDetailEntity.setCrzAgendaParticipants(crzAgendaParticipantDTO);
			crzAgendMomDetailEntity.setCrzAgendaProposals(crzAgendaProposalsDto);
			crzAgendMomDetailEntity.setCrzAgendaOtherItems(crzAgendaOtherItemsDto);
			return ResponseHandler.generateResponse("getting Crz Agenda Details", HttpStatus.OK, "",
					crzAgendMomDetailEntity);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz Agenda Details - ", e);
		}
	}

	public ResponseEntity<Object> getAllSubmitMomListEntity() {
		try {
			Set<CrzAgendaMomDto> crzAgendaMomDto = crzAgendaMomDetailRepository.findAllSubmitMomList();

			return ResponseHandler.generateResponse("getting Crz All Mom in Submit Status", HttpStatus.OK, "",
					crzAgendaMomDto);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz All Mom in Submit Status - ", e);
		}
	}

	public ResponseEntity<Object> getAllChairmanApprovedMomListEntity() {
		try {
			Set<CrzAgendaMomDto> crzAgendaMomDto = crzAgendaMomDetailRepository.findAllChairmanApprovedMomList();

			return ResponseHandler.generateResponse("getting Crz All Mom in Chairman Approved Status", HttpStatus.OK,
					"", crzAgendaMomDto);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Crz All Mom in Chairman Approved Status - ", e);
		}
	}

	public Object updateMomStatusByChairman(Integer mom_id, String status, HttpServletRequest request)
			throws PariveshException {
		try {
			crzAgendaMomDetailRepository.updateMomStatus(mom_id, status);
			return ResponseHandler.generateResponse("Mom Status Updated By Chairman", HttpStatus.OK, null,
					"Mom Status Updated By Chairman");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Updating Mom Status By Chairman-", e);
		}
	}

	public Object updateApplicationStatusForEDS(Integer application_id, Integer app_history_id, String status,
			HttpServletRequest request) throws PariveshException {
		try {
			Integer cafId = dashboardDtoRepoitory.getCafIdByProposalId(application_id);
			dashboardDtoRepoitory.updateStatusForProponentApplicationsEDS(application_id, status);
			dashboardDtoRepoitory.updateStatusForCafDetailsEDS(cafId, 5); // 7 for reply
			dashboardDtoRepoitory.updateStatusForProposalApplication(status, app_history_id);
			return ResponseHandler.generateResponse("updated Proponent Application Status", HttpStatus.OK, null,
					"updated Proponent Application Status");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Updating Proponent Application Status-", e);
		}
	}

	public ResponseEntity<Object> getEDSQueriesForProposals(Integer application_id) {

		List<EDSFormV2> list = eDSFormV2Repository.getEdsByIds(application_id);
		return ResponseHandler.generateResponse("getting Crz EDS Details", HttpStatus.OK, "", list);
	}

	public ResponseEntity<Object> getMomChairmanQueriesEntity(Integer mom_id) {
		try {
			Optional<List<CrzMomChairmanQueriesDto>> crzMomChairmanQueriesDtoList = crzMomChairmanQueriesRepository
					.findByMomId(mom_id);
			if (crzMomChairmanQueriesDtoList.get().isEmpty()) {
				return ResponseHandler.generateResponse("No Queries found for Mom Id " + mom_id, HttpStatus.NOT_FOUND,
						"", crzMomChairmanQueriesDtoList);
			}
			return ResponseHandler.generateResponse("Getting Crz Chairman Queries", HttpStatus.OK, "",
					crzMomChairmanQueriesDtoList);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting Chairman Queries - ", e);
		}
	}

	public Object saveMomChairmanQuery(List<CrzMomChairmanQueriesDto> crzMomChairmanQueriesDto,
			HttpServletRequest request) throws PariveshException {
		try {
			crzAgendaMomDetailRepository.updateMomStatus(crzMomChairmanQueriesDto.get(0).getMom_id(), "QueryRaised");
			return crzMomChairmanQueriesRepository.saveAll(crzMomChairmanQueriesDto);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Chairman Query -", e);
		}
	}

	public ResponseEntity<Object> getAllConditionDetails() {
		try {
			Set<CrzMasterConditionsDto> crzMasterConditionsDtoSet = crzMasterConditionsRepository
					.findAllConditionsByOrder();
			return ResponseHandler.generateResponse("Crz All Conditions List", HttpStatus.OK, "",
					crzMasterConditionsDtoSet);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting All Conditions - ", e);
		}
	}

	public ResponseEntity<Object> getConditionDetailsByType(String type) {
		try {
			int Ctype = type.equalsIgnoreCase("General") ? 0 : 1;

			Set<CrzMasterConditionsDto> crzMasterConditionsDtoSet = crzMasterConditionsRepository
					.findConditionsByType(Ctype);
			return ResponseHandler.generateResponse("Crz Conditions List", HttpStatus.OK, "",
					crzMasterConditionsDtoSet);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Conditions - ", e);
		}
	}

	
	public ResponseEntity<Object> getConditionDetailsByStateIdAndType(int stateId, String type) {
		try {
			int Ctype = type.equalsIgnoreCase("General") ? 0 : 1;
			
			Set<CrzMasterConditionsDto> crzMasterConditionsDtoSet = crzMasterConditionsRepository
					.findConditionsByStateIdAndType(Ctype, stateId);
			return ResponseHandler.generateResponse("Crz Conditions List", HttpStatus.OK, "",
					crzMasterConditionsDtoSet);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in getting Conditions - ", e);
		}
	}
	

	public CrzMasterConditionsDto saveConditions(CrzMasterConditionsDto Conditions) {

		return crzMasterConditionsRepository.save(Conditions);
	}

	private CrzProposalProcessFileDto saveStatus(CrzProposalProcessFileDto crzProposalProcessFileDto) {
		switch (crzProposalProcessFileDto.getRole_id()) {
		case 54:
			crzProposalProcessFileDto.setStatus("Forwarded to MS");
			saveProcessStepAuthorityStatus(crzProposalProcessFileDto);
			return crzProposalProcessFileDto;
		case 56:
			crzProposalProcessFileDto.setStatus("Forwarded to JS");
			saveProcessStepAuthorityStatus(crzProposalProcessFileDto);
			return crzProposalProcessFileDto;
		case 57:
			crzProposalProcessFileDto.setStatus("Forwarded to AS");
			saveProcessStepAuthorityStatus(crzProposalProcessFileDto);
			return crzProposalProcessFileDto;
		case 45:
			crzProposalProcessFileDto.setStatus("Forwarded to Minister");
			saveProcessStepAuthorityStatus(crzProposalProcessFileDto);
			return crzProposalProcessFileDto;
		case 43:
			crzProposalProcessFileDto.setStatus("Forwarded to SEC");
			saveProcessStepAuthorityStatus(crzProposalProcessFileDto);
			return crzProposalProcessFileDto;
		case 10:
			crzProposalProcessFileDto.setStatus("Forwarded to MoS");
			saveProcessStepAuthorityStatus(crzProposalProcessFileDto);
			return crzProposalProcessFileDto;
		case 10064:
			crzProposalProcessFileDto.setStatus("Forwarded to MoS");
			saveProcessStepAuthorityStatus(crzProposalProcessFileDto);
			return crzProposalProcessFileDto;
		default:
			return crzProposalProcessFileDto;
		}

	}

	private void saveProcessStepAuthorityStatus(CrzProposalProcessFileDto crzProposalProcessFileDto) {
		dashboardDtoRepoitory.updateLastStatus(crzProposalProcessFileDto.getProposal_id(),
				crzProposalProcessFileDto.getStatus());
	}

	private CrzProposalProcessFileDto setStatusByMinister(CrzProposalProcessFileDto crzProposalProcessFileDto) {

		Optional<CrzMinisterActionStatus> status = crzMinisterActionStatusRepository
				.findById(crzProposalProcessFileDto.getAction());
		crzProposalProcessFileDto.setStatus(status.get().getStatus());
		saveProcessStepAuthorityStatus(crzProposalProcessFileDto);
		return crzProposalProcessFileDto;
	}

	public Object saveProposalProcessFile(CrzProposalProcessFileDto crzProposalProcessFileDto,
			HttpServletRequest request) throws PariveshException {
		try {
			Integer count = crzProposalDraftForApprovalRepository
					.checkDuplicateProposalDraft(crzProposalProcessFileDto.getProposal_id());
			if (count == 0) {
				return ResponseHandler.generateResponse(
						"Proposal Draft For Approval Data is mandatory to proceed further for proposal_id "
								+ crzProposalProcessFileDto.getProposal_id(),
						HttpStatus.BAD_REQUEST, "", null);
			}
			CrzProposalProcessFileDto savedCrzProposalProcessFile = new CrzProposalProcessFileDto();
			CrzProposalProcessFileHistoryDto crzProposalProcessFileHistoryDto = new CrzProposalProcessFileHistoryDto();
			CrzProposalProcessFileHistoryDto savedcrzProposalProcessFileHistoryDto = new CrzProposalProcessFileHistoryDto();
			if (crzProposalProcessFileDto.getAction() != null)
				setStatusByMinister(crzProposalProcessFileDto);
			else
				saveStatus(crzProposalProcessFileDto);
			BeanUtils.copyProperties(crzProposalProcessFileDto, crzProposalProcessFileHistoryDto);

			if (crzProposalProcessFileDtoRepository
					.findByProposalId(crzProposalProcessFileDto.getProposal_id()) == null) {

				savedCrzProposalProcessFile = crzProposalProcessFileDtoRepository.save(crzProposalProcessFileDto);
			} else {
				crzProposalProcessFileDtoRepository.updateProposalProcessFile(
						crzProposalProcessFileDto.getProposal_id(), crzProposalProcessFileDto.getCreated_by(),
						crzProposalProcessFileDto.getCreated_on(), crzProposalProcessFileDto.getForward_to_user_id(),
						crzProposalProcessFileDto.getProposal_process_file_document_id(),
						crzProposalProcessFileDto.getForward_to_name(), crzProposalProcessFileDto.getStatus(),
						crzProposalProcessFileDto.getRole_id(), crzProposalProcessFileDto.getUpdated_by(),
						crzProposalProcessFileDto.getUpdated_on(), crzProposalProcessFileDto.getRemarks(),
						crzProposalProcessFileDto.getRemarks_by(), crzProposalProcessFileDto.getAction());
			}

			savedcrzProposalProcessFileHistoryDto = crzProposalProcessFileHistoryRepository
					.save(crzProposalProcessFileHistoryDto);

			if (crzProposalProcessFileDto.getCrzProcessFileAttachment() != null) {
				CrzAttachmentsDetail crzProcessFileAttachment = crzProposalProcessFileDto.getCrzProcessFileAttachment();
				crzProcessFileAttachment.setForeign_reference_key(savedcrzProposalProcessFileHistoryDto.getId());
				crzAttachmentsDetailRepository.save(crzProcessFileAttachment);
			}
			return ResponseHandler.generateResponse("Saving  Crz Proposal Process File", HttpStatus.OK, "",
					savedcrzProposalProcessFileHistoryDto);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving crz Proposal Process File -", e);
		}
	}

	public ResponseEntity<Object> getProposalProcessFile(Integer proposal_id) {
		try {
			Optional<List<CrzProposalProcessFileHistoryDto>> crzProposalProcessFileDtoList = crzProposalProcessFileHistoryRepository
					.findByProposalId(proposal_id);
			crzProposalProcessFileDtoList.stream().forEach(ele -> ele.forEach(pro -> pro
					.setRemarks_by(crzProposalProcessFileDtoRepository.getNameByProposalId(pro.getCreated_by()))));
			crzProposalProcessFileDtoList.stream().forEach(ele -> ele.forEach(pro -> pro
					.setCrzProcessFileAttachment(crzAttachmentsDetailRepository.findByFkId(pro.getId(), "NOTING"))));

			if (crzProposalProcessFileDtoList.get().isEmpty()) {
				return ResponseHandler.generateResponse(
						"No data found for Crz Proposal Process File for proposal_id " + proposal_id, HttpStatus.OK, "",
						crzProposalProcessFileDtoList);
			}
			return ResponseHandler.generateResponse("Getting Crz Proposal Process File", HttpStatus.OK, "",
					crzProposalProcessFileDtoList);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting crz Proposal Process File - ", e);
		}
	}

	public Object saveProposalDraftForApproval(CrzProposalDraftForApprovalDto crzProposalDraftForApprovalDto,
			HttpServletRequest request) throws PariveshException {
		try {

			Integer count = crzProposalDraftForApprovalRepository
					.checkDuplicateProposalDraft(crzProposalDraftForApprovalDto.getProposal_id());
			if (count > 1) {
				return ResponseHandler
						.generateResponse(
								"Data is already present for Proposal Draft For Approval for proposal_id "
										+ crzProposalDraftForApprovalDto.getProposal_id(),
								HttpStatus.BAD_REQUEST, "", null);
			} else if (count == 1) {
				if (!(crzProposalDraftForApprovalDto.getId() == null)) {
					crzProposalDraftForApprovalRepository.save(crzProposalDraftForApprovalDto);
					return ResponseHandler.generateResponse(
							"Data is updated present for Proposal Draft For Approval for proposal_id "
									+ crzProposalDraftForApprovalDto.getProposal_id(),
							HttpStatus.CREATED, "", crzProposalDraftForApprovalDto);
				}
			}

			return crzProposalDraftForApprovalRepository.save(crzProposalDraftForApprovalDto);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving crz Proposal Draft For Approval -", e);
		}
	}

	public ResponseEntity<Object> getProposalDraftForApproval(Integer proposal_id) {
		try {
			CrzProposalDraftForApprovalDto crzProposalDraftForApproval = crzProposalDraftForApprovalRepository
					.findByProposalId(proposal_id);
			if (crzProposalDraftForApproval == null) {
				return ResponseHandler.generateResponse(
						"No data found Proposal Draft For Approval for proposal_id " + proposal_id, HttpStatus.OK, "",
						crzProposalDraftForApproval);
			}
			return ResponseHandler.generateResponse("Getting Crz Proposal Draft For Approval", HttpStatus.OK, "",
					crzProposalDraftForApproval);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting crz Proposal Draft For Approval - ", e);
		}
	}

	public ResponseEntity<Object> getMomApprovedProposal(Integer user_id) {
		List<CrzAgendaProposalsDto> crzAgendaProposalsDto = crzAgendaProposalsRepository
				.findAllMomListApprovedByChairman();

		List<CrzAgendaProposalsDto> crzAgendaProposalsDtoFiltered = new ArrayList<>();

		for (CrzAgendaProposalsDto crzAgendaProposal : crzAgendaProposalsDto) {
			Integer proposal_id = crzProponentApplicationRepository.findProposalId(crzAgendaProposal.getProposal_no());
//			Optional<Integer> forward_to_user_id = crzProposalProcessFileDtoRepository.findUserIdByProposalId(proposal_id);
//			if(forward_to_user_id.isEmpty() || forward_to_user_id.get().equals(user_id)) {
//				crzAgendaProposalsDtoFiltered.add(crzAgendaProposal);
//			}  
			CrzProposalProcessFileDto crzProposalProcessFileDto = crzProposalProcessFileDtoRepository
					.findByProposalId(proposal_id);
			if (crzProposalProcessFileDto != null) {
				if (crzProposalProcessFileDto.getForward_to_user_id().equals(user_id)
						&& (crzProposalProcessFileDto.getAction() == null
								|| crzProposalProcessFileDto.getAction() == 3)) {
					crzAgendaProposalsDtoFiltered.add(crzAgendaProposal);

				}
			} else {
				crzAgendaProposalsDtoFiltered.add(crzAgendaProposal);
			}
		}

		return ResponseHandler.generateResponse("getting Proposal which is approved by chairman ", HttpStatus.OK, "",
				crzAgendaProposalsDtoFiltered);
	}

	public ResponseEntity<Object> getMomRecommendation() {
		List<CrzMomRecommendations> momRecommendation = crzMomRecommendationRepository.findAllMomRecommendationList();

		return ResponseHandler.generateResponse("getting details of mom recommendation", HttpStatus.OK, "",
				momRecommendation);
	}

	public ResponseEntity<Object> getListOfMomStatusQueryReplied() {
		Set<CrzAgendaMomDto> crzAgendaMomDto = crzAgendaMomDetailRepository.findAllListOfMomStatusQueryReplied();

		return ResponseHandler.generateResponse("getting Proposal which is approved by chairman ", HttpStatus.OK, "",
				crzAgendaMomDto);
	}

	public ResponseEntity<Object> getMinisterActionStatus() {
		List<CrzMinisterActionStatus> crzMinisterActionStatus = crzMinisterActionStatusRepository
				.findAllMinisterActionStatus();

		return ResponseHandler.generateResponse("getting minister action status ", HttpStatus.OK, "",
				crzMinisterActionStatus);
	}

	private void updateAgendaProposalStatus(Integer app_history_id, String proposalStatus, String buttonType) {
		if (app_history_id != null && buttonType.equals("DRAFT")) {
			dashboardDtoRepoitory.updateStatusForProposalApplication("Under Processing", app_history_id);
		} else if (app_history_id != null && buttonType.equals("PUBLISH")) {
			dashboardDtoRepoitory.updateStatusForProposalApplication("Referred To EAC", app_history_id);
		}
	}

	public ResponseEntity<Object> getQueryDetails(Integer application_id) {

		List<CrzQueryDetailsDto> crzQueryDetailsDtoList = crzQueryDetailsRepository.findByApplicationId(application_id);
		List<CrzQueryDetailsDto> crzQueryDetailsDtoListNew = new ArrayList<>();
		for (CrzQueryDetailsDto crzQueryDetailsDto : crzQueryDetailsDtoList) {
			// Adding query attachment
			CrzAttachmentsDetail crzQueryAttachmentsDetail = crzAttachmentsDetailRepository
					.findByFkId(crzQueryDetailsDto.getId(), crzQueryDetailsDto.getType().name());
			if (crzQueryAttachmentsDetail != null) {
				crzQueryDetailsDto.setCrzQueryDetailsAttachment(crzQueryAttachmentsDetail);
			}

			// Adding response
			List<CrzResponseDetailsDto> crzResponseDetailsDtoList = crzResponseDetailsRepository
					.findByQueryId(crzQueryDetailsDto.getId());
			List<CrzResponseDetailsDto> crzResponseDetailsDtoListNew = new ArrayList<>();
			for (CrzResponseDetailsDto crzResponseDetailsDto : crzResponseDetailsDtoList) {
				CrzAttachmentsDetail crzResponseAttachmentsDetail = crzAttachmentsDetailRepository
						.findByFkId(crzResponseDetailsDto.getId(), crzQueryDetailsDto.getType().name());
				if (crzResponseAttachmentsDetail != null) {
					crzResponseDetailsDto.setCrzResponseDetailsAttachment(crzResponseAttachmentsDetail);
					crzResponseDetailsDtoListNew.add(crzResponseDetailsDto);
				} else {
					crzResponseDetailsDtoListNew.add(crzResponseDetailsDto);
				}
			}

			crzQueryDetailsDto.setCrzResponseDetailsDto(crzResponseDetailsDtoListNew);
			crzQueryDetailsDtoListNew.add(crzQueryDetailsDto);
		}
		return ResponseHandler.generateResponse("getting Crz Query Details", HttpStatus.OK, "",
				crzQueryDetailsDtoListNew);
	}

	public Object saveQueryDetails(List<CrzQueryDetailsDto> crzQueryDetailsDtoList, HttpServletRequest request)
			throws PariveshException {
		try {

			List<CrzQueryDetailsDto> savedCrzQueryDetailsDtoList = crzQueryDetailsRepository
					.saveAll(crzQueryDetailsDtoList);

			for (CrzQueryDetailsDto crzQueryDetailsDto : savedCrzQueryDetailsDtoList) {

				if (crzQueryDetailsDto.getCrzQueryDetailsAttachment() != null) {
					CrzAttachmentsDetail crzAttachmentsDetail = crzQueryDetailsDto.getCrzQueryDetailsAttachment();
					crzAttachmentsDetail.setForeign_reference_key(crzQueryDetailsDto.getId());
					crzAttachmentsDetailRepository.save(crzAttachmentsDetail);
				}
			}

			return savedCrzQueryDetailsDtoList;

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Crz Query Details-", e);
		}
	}

	public Object saveResponseDetails(List<CrzResponseDetailsDto> crzResponseDetailsDtoList, HttpServletRequest request)
			throws PariveshException {
		try {

			List<CrzResponseDetailsDto> savedCrzResponseDetailsDtoList = crzResponseDetailsRepository
					.saveAll(crzResponseDetailsDtoList);

			for (CrzResponseDetailsDto crzResponseDetailsDto : savedCrzResponseDetailsDtoList) {

				if (crzResponseDetailsDto.getCrzResponseDetailsAttachment() != null) {
					CrzAttachmentsDetail crzAttachmentsDetail = crzResponseDetailsDto.getCrzResponseDetailsAttachment();
					crzAttachmentsDetail.setForeign_reference_key(crzResponseDetailsDto.getId());
					crzAttachmentsDetailRepository.save(crzAttachmentsDetail);
				}
			}

			return savedCrzResponseDetailsDtoList;

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving Crz Response Details-", e);
		}
	}

	public Object saveProposalHistoryTimeline(List<CrzProposalTimelineDto> crzProposalTimelineDto,
			HttpServletRequest request) throws PariveshException {
		return crzProposalTimelineRepository.saveAll(crzProposalTimelineDto);
	}

	public ResponseEntity<Object> getProposalHistoryTimeline(Integer proposal_app_id) {
		List<CrzProposalTimelineDto> crzProposalTimelineDtoList = crzProposalTimelineRepository
				.findByProposalAppId(proposal_app_id);
		return ResponseHandler.generateResponse("getting Proposal HistoryTimeline for that proposal_app_id ",
				HttpStatus.OK, "", crzProposalTimelineDtoList);
	}

	public ResponseEntity<Object> getMasterStatus() {
		List<CrzMasterStatusDto> crzMasterStatusDtoList = crzMasterStatusRepository.findAll();

		return ResponseHandler.generateResponse("getting all master status for CRZ module", HttpStatus.OK, "",
				crzMasterStatusDtoList);
	}

}
