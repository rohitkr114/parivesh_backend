package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcMiningProposals;

public interface EcMiningProposalRepository extends JpaRepository<EcMiningProposals, Integer> {

	@Query("select ec from EcMiningProposals ec where ec_partb_id=?1")
	public Optional<EcMiningProposals> getRecordExist(Integer id);
}
