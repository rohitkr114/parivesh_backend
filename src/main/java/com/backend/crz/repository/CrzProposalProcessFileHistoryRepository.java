package com.backend.crz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CrzProposalProcessFileHistoryDto;

public interface CrzProposalProcessFileHistoryRepository  extends JpaRepository<CrzProposalProcessFileHistoryDto, Integer> {

	@Query(value = "SELECT * FROM master.crz_proposal_process_file_history u WHERE u.proposal_id =?l ORDER BY u.updated_on ASC ", nativeQuery = true)
	Optional<List<CrzProposalProcessFileHistoryDto>> findByProposalId(Integer proposal_id);
	
}
