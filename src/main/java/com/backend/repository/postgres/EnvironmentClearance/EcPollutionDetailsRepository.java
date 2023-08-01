package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcPollutionDetails;

public interface EcPollutionDetailsRepository extends JpaRepository<EcPollutionDetails, Integer> {

	@Modifying
	@Query("update EcPollutionDetails set is_deleted='true'  where id=?1")
	Integer updateEcPollutionDetails(Integer pollutionId);

	@Query("select ec from EcPollutionDetails ec where ec_part_b_id=?1")
	public Optional<EcPollutionDetails> getRecordExist(Integer id);
}
