package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcSubmergedArea;

public interface EcSubmergedAreaRepository extends JpaRepository<EcSubmergedArea, Integer> {

	@Modifying
	@Query("update EcSubmergedArea set is_deleted='true'  where id=?1")
	Integer updateEcSubmergedArea(Integer id);

}
