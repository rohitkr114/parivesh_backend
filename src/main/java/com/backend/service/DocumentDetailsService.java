package com.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.dto.AgendaDocumentDetailsDTO;
import com.backend.dto.CafDocDetailsDTO;
import com.backend.dto.DocDetailsDTO;
import com.backend.dto.DocumentDetailsDTO;
import com.backend.dto.MomDocumentDetailsDTO;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.backend.repository.postgres.DocumentDetailsRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class DocumentDetailsService {

	@Autowired
	DocumentDetailsRepository documentDetailsRepository;

	@Autowired
	ProponentApplicationRepository proponentApplicationRepository;

	public ResponseEntity<Object> addDocumentDetails(DocumentDetails documentDetails) {
		try {
			DocumentDetails temp = documentDetailsRepository.save(documentDetails);
			documentDetailsRepository.flush();
			log.info("INFO ------------ addDocumentDetails Document details added - SUCCESS");
			return ResponseHandler.generateResponse("Add Document", HttpStatus.OK, "", temp.getId());
		} catch (Exception ex) {
			log.info(
					"ERROR ------------ EXPECTATION_FAILED: addDocumentDetails Document details CAN'T be added - FAILURE");
			return ResponseHandler.generateResponse("Add Document", HttpStatus.EXPECTATION_FAILED, "Exception",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> getDocumentDetails(Integer proposal_id) {
		log.info("getDocumentDetails -> STARTED: for ProposalId:{}", proposal_id);
		try {
			ProponentApplications app = null;
			List<DocumentDetailsDTO> documentDetails = new ArrayList<DocumentDetailsDTO>();
			List<AgendaDocumentDetailsDTO> agendaDocumDetailsDTOs = new ArrayList<AgendaDocumentDetailsDTO>();
			List<MomDocumentDetailsDTO> momDocumentDetailsDTO = new ArrayList<MomDocumentDetailsDTO>();
			List<CafDocDetailsDTO> cafDocumentDetailsDTO = new ArrayList<CafDocDetailsDTO>();
			app = proponentApplicationRepository.getApplicationByProposalId(proposal_id);

			if (app != null) {

				documentDetails = documentDetailsRepository.getDocumentDetailsbyProposalNo(app.getProposal_no());

				agendaDocumDetailsDTOs = documentDetailsRepository
						.getAgendaDocumentDetailsbyProposalNo(app.getProposal_no());

				momDocumentDetailsDTO = documentDetailsRepository
						.getMomDocumentDetailsbyProposalNo(app.getProposal_no());

				cafDocumentDetailsDTO = documentDetailsRepository
						.getCafDocumentDetailsbyProposalNo(app.getProposal_no());

			}
			DocDetailsDTO docs = new DocDetailsDTO();
			docs.setDocumentDetails(documentDetails);
			docs.setAgendaDetails(agendaDocumDetailsDTOs);
			docs.setMomDetails(momDocumentDetailsDTO);
			docs.setCafDetails(cafDocumentDetailsDTO);

			log.info("getDocumentDetails -> ENDED: success for ProposalId: {}", proposal_id);
			return ResponseHandler.generateResponse("Get DocumentDetails", HttpStatus.OK, "", docs);
		} catch (Exception ex) {
			log.info("getDocumentDetails -> ENDED with exception :Document details CAN'T be added - FAILURE");

			return ResponseHandler.generateResponse("Add Document", HttpStatus.EXPECTATION_FAILED, "Exception",
					ex.getMessage());
		}
	}

	public ResponseEntity<Object> deleteDocumentDetails(Integer id) {
		try {
			documentDetailsRepository.deletedocument(id);
			return ResponseHandler.generateResponse("Delete Document", HttpStatus.OK, "", "SUCCESS");
		} catch (Exception ex) {
			return ResponseHandler.generateResponse("Delete Document", HttpStatus.EXPECTATION_FAILED, "Exception",
					ex.getMessage());
		}
	}

}
