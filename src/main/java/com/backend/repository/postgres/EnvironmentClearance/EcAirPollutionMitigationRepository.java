package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcAirPollutionMitigation;

public interface EcAirPollutionMitigationRepository extends JpaRepository<EcAirPollutionMitigation, Integer> {

	@Modifying
	@Query("update EcAirPollutionMitigation set is_deleted='true' , is_active='false' where id=?1")
	Integer updateEcAirPollutionMitigation(Integer ecMitigationId);

}
