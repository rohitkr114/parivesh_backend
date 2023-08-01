package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcAirportProposal;

public interface EcAirportProposalRepository extends JpaRepository<EcAirportProposal, Integer> {

	@Modifying
	@Query("update EcAirportProposal set is_deleted='true' where id=?1")
	Integer updateEcAirportProposal(Integer airportId);

}