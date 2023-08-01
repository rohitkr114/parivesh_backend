package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.MiningProductionDetails;

public interface MiningProductionDetailsRepository extends JpaRepository<MiningProductionDetails, Integer> {

	@Modifying
	@Query("update MiningProductionDetails set is_deleted='true' , is_active='false' where id=?1")
	Integer updateMiningProductionDetails(Integer id);

}
