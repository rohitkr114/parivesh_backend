package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.CurrentLandUse;

public interface CurrentLandUseRepository extends JpaRepository<CurrentLandUse, Integer> {

	@Modifying
	@Query("update CurrentLandUse set is_deleted='true'  where id=?1")
	Integer updateCurrentLandUse(Integer id);

}
