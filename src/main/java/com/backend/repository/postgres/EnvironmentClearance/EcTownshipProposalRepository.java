package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcTownshipProposals;

public interface EcTownshipProposalRepository extends JpaRepository<EcTownshipProposals, Integer> {

	@Query("select ec from EcTownshipProposals ec where ec_partb_id=?1")
	public Optional<EcTownshipProposals> getRecordExist(Integer id);
}
