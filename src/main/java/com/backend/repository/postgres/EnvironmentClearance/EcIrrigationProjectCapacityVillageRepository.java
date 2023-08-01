package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcIrrigationProjectCapacityVillage;

public interface EcIrrigationProjectCapacityVillageRepository
		extends JpaRepository<EcIrrigationProjectCapacityVillage, Integer> {

	@Modifying
	@Query("update EcIrrigationProjectCapacityVillage set is_deleted='true'  where id=?1")
	Integer updateEcIrrigationProjectCapacityVillage(Integer id);

}
