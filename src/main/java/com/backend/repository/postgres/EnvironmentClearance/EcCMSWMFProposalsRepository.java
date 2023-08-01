package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcCMSWMFProposals;

public interface EcCMSWMFProposalsRepository extends JpaRepository<EcCMSWMFProposals, Integer> {

	@Modifying
	@Query("update EcCMSWMFProposals set is_deleted='true'  where id=?1")
	Integer updateEcCMSWMFProposals(Integer id);

	@Query("select ec from EcCMSWMFProposals ec where ec_partb_id=?1")
	public Optional<EcCMSWMFProposals> getRecordExist(Integer id);
}
