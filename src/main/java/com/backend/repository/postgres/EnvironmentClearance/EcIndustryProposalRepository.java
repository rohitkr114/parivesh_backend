package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcIndustryProposal;

public interface EcIndustryProposalRepository extends JpaRepository<EcIndustryProposal, Integer> {

	@Query("select ec from EcIndustryProposal ec where ec_partb_id=?1")
	public Optional<EcIndustryProposal> getRecordExist(Integer id);
}
