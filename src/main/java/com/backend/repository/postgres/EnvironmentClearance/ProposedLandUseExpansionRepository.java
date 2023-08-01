package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.ProposedLandUseExpansion;

public interface ProposedLandUseExpansionRepository extends JpaRepository<ProposedLandUseExpansion, Integer> {

	@Modifying
	@Query("update ProposedLandUseExpansion set is_deleted='true' where id=?1")
	Integer updateProposedLandUseExpansion(Integer id);

}

