package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcTSDFProposals;

public interface EcTSDFProposalsRepository extends JpaRepository<EcTSDFProposals, Integer> {

	@Modifying
	@Query("update EcTSDFProposals set is_deleted='true'  where id=?1")
	Integer updateEcTSDFProposals(Integer id);

	@Query("select ec from EcTSDFProposals ec where ec_partb_id=?1")
	public Optional<EcTSDFProposals> getRecordExist(Integer id);
}
