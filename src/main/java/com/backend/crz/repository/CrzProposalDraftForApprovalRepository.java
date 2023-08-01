package com.backend.crz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CrzProposalDraftForApprovalDto;

public interface CrzProposalDraftForApprovalRepository extends JpaRepository<CrzProposalDraftForApprovalDto, Integer> {

	@Query(value = "SELECT * FROM master.crz_proposal_draft_for_approval u WHERE u.proposal_id =?1", nativeQuery = true)
	CrzProposalDraftForApprovalDto findByProposalId(Integer proposal_id);

	@Query(value = "select count(*) from master.crz_proposal_draft_for_approval where proposal_id =?1 ", nativeQuery = true)
	public Integer checkDuplicateProposalDraft(Integer proposal_id);

}
