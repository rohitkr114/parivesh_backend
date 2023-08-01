package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.MiningProposals;

public interface MiningProposalsRepository extends JpaRepository<MiningProposals, Integer> {

	MiningProposals findMiningProposalsByCafId(Integer cafId);

}
