package com.backend.crz.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzProposalProcessFileDto;

@Repository
public interface CrzProposalProcessFileRepository  extends JpaRepository<CrzProposalProcessFileDto, Integer> {

	@Query(value = "SELECT * FROM master.crz_proposal_process_file u WHERE u.proposal_id =?1", nativeQuery = true)
	CrzProposalProcessFileDto findByProposalId(Integer proposal_id);
	
	@Query(value = "SELECT name FROM  authentication.user_entity WHERE entityid = :created_by", nativeQuery = true)
	String getNameByProposalId(@Param(value = "created_by") long created_by);
	
	@Modifying
	@Query(value ="update master.crz_proposal_process_file set forward_to_name = :forward_to_name, status = :status, updated_by = :updated_by, updated_on = :updated_on,role_id = :role_id, created_by = :created_by, created_on = :created_on, forward_to_user_id = :forward_to_user_id, proposal_process_file_document_id = :proposal_process_file_document_id, remarks = :remarks, remarks_by = :remarks_by, action = :action where proposal_id = :proposal_id", nativeQuery = true)
	void updateProposalProcessFile(@Param(value = "proposal_id") long proposal_id, 
	@Param(value = "created_by") long created_by,
	@Param(value = "created_on")Date created_on,
	@Param(value = "forward_to_user_id") long forward_to_user_id,
	@Param(value = "proposal_process_file_document_id") String proposal_process_file_document_id,
	@Param(value = "forward_to_name") String forward_to_name,
	@Param(value = "status") String status,
	@Param(value = "role_id") Integer role_id,
	@Param(value = "updated_by") Integer updated_by,
	@Param(value = "updated_on") Date updated_on,
	@Param(value = "remarks") String remarks,
	@Param(value = "remarks_by") String remarks_by,
	@Param(value = "action") Integer action
	);
	
	@Query(value = "SELECT u.forward_to_user_id FROM master.crz_proposal_process_file u WHERE u.proposal_id =?1", nativeQuery = true)
	Optional<Integer> findUserIdByProposalId(Integer proposal_id);

	


}
