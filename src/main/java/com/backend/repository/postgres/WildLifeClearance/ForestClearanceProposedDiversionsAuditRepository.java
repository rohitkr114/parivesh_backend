package com.backend.repository.postgres.WildLifeClearance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearancePatchKmls;
import com.backend.model.ForestClearanceProposedDiversions;
import com.backend.model.ForestClearanceProposedDiversionsAudit;

public interface ForestClearanceProposedDiversionsAuditRepository extends JpaRepository<ForestClearanceProposedDiversionsAudit, Integer>  {
	/*
	 * @Query("SELECT fc from ForestClearanceProposedDiversions fc where fc.isDelete ='false' and fc.forestClearance.id=?1"
	 * ) List<ForestClearanceProposedDiversions> findByFCID(Integer id);
	 * 
	 * @Query("SELECT fc from ForestClearanceProposedDiversionsAudit fc where fc.isDelete ='false' and fc.wildLifeClearance.id=?1"
	 * ) List<ForestClearanceProposedDiversionsAudit> findByWLID(Integer id);
	 */
}
