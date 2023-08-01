package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ClearanceFactSheet;

public interface ClearanceFactSheetRepository extends JpaRepository<ClearanceFactSheet, Long> {

	@Query("select ch from ClearanceFactSheet ch where ch.proposal_no = ?1")
	List<ClearanceFactSheet> findAllByProposalId(String proposal_no);
}
