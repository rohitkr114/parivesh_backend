package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.ProposedLandUse;

public interface ProposedLandUseRepository extends JpaRepository<ProposedLandUse, Integer> {
	
	@Modifying
	@Query("update ProposedLandUse set is_deleted='true' where id=?1")
	public Integer updateDeleteFlag(Integer id);

}

