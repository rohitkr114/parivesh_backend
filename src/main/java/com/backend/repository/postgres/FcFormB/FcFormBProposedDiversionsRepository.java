package com.backend.repository.postgres.FcFormB;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBDivisionPatchDetails;
import com.backend.model.ForestClearanceFormB.FcFormBProposedDiversions;

public interface FcFormBProposedDiversionsRepository extends JpaRepository<FcFormBProposedDiversions, Integer>{
	
	@Query("SELECT fc from FcFormBProposedDiversions fc where fc.is_delete ='false' and fc.fcFormBProjectDetails.id=?1")
	List<FcFormBProposedDiversions> findByFcFormBByID(Integer id);

	@Modifying
	@Query("update FcFormBProposedDiversions set is_delete='true' where id=?1")
	Integer updateFcFormBById(int id);

	@Query("SELECT fc from FcFormBProposedDiversions fc where fc.is_delete ='false' and fc.fcFormBProjectDetails.id=?1")
	List<FcFormBProposedDiversions> getByFcID(Integer id);
}
