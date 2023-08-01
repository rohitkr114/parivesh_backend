package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.AirportProposal;

public interface AirportProposalRepository extends JpaRepository<AirportProposal, Integer> {


	AirportProposal findAirportProposalsByCafId(Integer cafId);

}
