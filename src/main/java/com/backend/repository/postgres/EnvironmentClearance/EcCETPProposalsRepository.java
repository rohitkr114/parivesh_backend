package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcCETPProposals;

public interface EcCETPProposalsRepository extends JpaRepository<EcCETPProposals, Integer> {

	@Modifying
	@Query("update EcCETPProposals set is_deleted='true'  where id=?1")
	Integer updateEcCETPProposals(Integer id);

	@Query("select ec from EcCETPProposals ec where ec_partb_id=?1")
	public Optional<EcCETPProposals> getRecordExist(Integer id);
}
