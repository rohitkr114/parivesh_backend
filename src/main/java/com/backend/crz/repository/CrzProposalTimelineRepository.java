package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.CrzProposalTimelineDto;

public interface CrzProposalTimelineRepository  extends JpaRepository<CrzProposalTimelineDto, Integer> {

//	@Query(value = "SELECT * FROM master.crz_proposal_timeline u WHERE u.proposal_app_id = :proposal_app_id order by 1", nativeQuery = true)
	@Query(value = "SELECT t.* FROM (SELECT id, proposal_app_id, mom_name, agenda_name, status_id, remarks, created_by, created_on, updated_by, updated_on, ROW_NUMBER() OVER (PARTITION BY status_id  order by status_id) rn FROM master.crz_proposal_timeline ) t where t.rn = 1 and proposal_app_id = :proposal_app_id order by 1", nativeQuery = true)
	List<CrzProposalTimelineDto> findByProposalAppId(@Param("proposal_app_id") Integer proposal_app_id);

}
