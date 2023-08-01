package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.WaterRequirementBreakup;

public interface WaterRequirementBreakupRepository extends JpaRepository<WaterRequirementBreakup, Integer> {

	@Modifying
	@Query("update EcPollutantDetails set is_deleted='true' , is_active='false' where id=?1")
	Integer updateWaterRequirementBreakup(Integer id);

}
