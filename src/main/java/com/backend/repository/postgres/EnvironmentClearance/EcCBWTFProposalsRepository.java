package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcCBWTFProposals;
import com.backend.model.EnvironmentClearance.EcCETPProposals;

public interface EcCBWTFProposalsRepository extends JpaRepository<EcCBWTFProposals, Integer> {

	@Modifying
	@Query("update EcCBWTFProposals set is_deleted='true'  where id=?1")
	Integer updateEcCBWTFProposals(Integer id);

	@Query("select ec from EcCBWTFProposals ec where ec_partb_id=?1")
	public Optional<EcCBWTFProposals> getRecordExist(Integer id);
}
