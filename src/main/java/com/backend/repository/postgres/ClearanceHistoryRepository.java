package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ClearanceHistory;

public interface ClearanceHistoryRepository extends JpaRepository<ClearanceHistory, Long> {

	@Query("select ch from ClearanceHistory ch where ch.proponentApplications.id = ?1")
	List<ClearanceHistory> findAllByProposalId(Integer proposal_id);
	
	@Query("select ch from ClearanceHistory ch where ch.proposal_no = ?1")
	List<ClearanceHistory> findAllByProposalNo(String proposal_no);

}
