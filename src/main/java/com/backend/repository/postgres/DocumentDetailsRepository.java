package com.backend.repository.postgres;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.dto.AgendaDocumentDetailsDTO;
import com.backend.dto.CafDocDetailsDTO;
import com.backend.dto.DocumentDetailsDTO;
import com.backend.dto.MomDocumentDetailsDTO;
import com.backend.model.DocumentDetails;

@Transactional
public interface DocumentDetailsRepository extends JpaRepository<DocumentDetails, Integer>{

	@Modifying
	@Query("update DocumentDetails set is_active='false',is_deleted='true' where id=?1")
	public void deletedocument(Integer id);
	
	@Query(value = "select * from master.get_document_dtls_fnc(:proposalNo) ", nativeQuery = true)
	List<DocumentDetailsDTO> getDocumentDetailsbyProposalNo(String proposalNo);
	
	@Query(value = "select * from master.get_agenda_documents_by_proposal(:proposalNo) ", nativeQuery = true)
	List<AgendaDocumentDetailsDTO> getAgendaDocumentDetailsbyProposalNo(String proposalNo);
	
	@Query(value = "select * from master.get_mom_documents_by_proposal(:proposalNo) ", nativeQuery = true)
	List<MomDocumentDetailsDTO> getMomDocumentDetailsbyProposalNo(String proposalNo);
	
	@Query(value = "select * from master.get_caf_document_dtls_fnc(:proposalNo) ", nativeQuery = true)
	List<CafDocDetailsDTO> getCafDocumentDetailsbyProposalNo(String proposalNo);

	@Query(value = "select id from master.Document_Details where proposal_no=?1 and type='CERTIFICATE' and is_active='true' and is_deleted='false' order by id desc limit 1", nativeQuery = true)
	Integer getDocIdByProposalNo(String proposalNO);


}
