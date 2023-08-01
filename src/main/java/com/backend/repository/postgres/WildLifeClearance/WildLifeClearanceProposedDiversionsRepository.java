package com.backend.repository.postgres.WildLifeClearance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.WildLifeClearance.WildLifeClearanceProposedDiversions;

public interface WildLifeClearanceProposedDiversionsRepository extends JpaRepository<WildLifeClearanceProposedDiversions, Integer>  {
	
	@Query("SELECT fc from WildLifeClearanceProposedDiversions fc where fc.isDelete ='false' and fc.wildLifeClearance.id=?1")
	List<WildLifeClearanceProposedDiversions> findByWLID(Integer id);
}
