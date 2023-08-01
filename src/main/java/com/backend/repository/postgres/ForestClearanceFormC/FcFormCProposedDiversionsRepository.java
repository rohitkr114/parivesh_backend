package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.ForestClearanceFormC.FcFormCProposedDiversions;

@Transactional
public interface FcFormCProposedDiversionsRepository extends JpaRepository<FcFormCProposedDiversions, Integer>{

	@Query("SELECT fc from FcFormCProposedDiversions fc where fc.is_delete ='false' and fc.fcFormC.id=?1")
	List<FcFormCProposedDiversions> findByFcFormCByID(Integer id);

	@Modifying
	@Query("update  FcFormCProposedDiversions set is_delete='true' where id=?1")
	Integer updateFcFormCById(int id);
}
