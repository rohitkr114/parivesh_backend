package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcWasteProduction;

public interface EcWasteProductionRepository extends JpaRepository<EcWasteProduction, Integer> {

	@Query("select ec from EcWasteProduction ec where ec_partb_id=?1")
	public Optional<EcWasteProduction> getRecordExist(Integer id);
}
