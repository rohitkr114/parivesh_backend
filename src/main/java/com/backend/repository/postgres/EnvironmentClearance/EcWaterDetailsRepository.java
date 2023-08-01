package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcWaterDetails;

public interface EcWaterDetailsRepository extends JpaRepository<EcWaterDetails, Integer> {

	@Query("select ec from EcWaterDetails ec where ec_partb_id=?1")
	public Optional<EcWaterDetails> getRecordExist(Integer id);
}
