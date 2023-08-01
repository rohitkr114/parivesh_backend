package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcProposedProjectLandDetails;

public interface EcProposedProjectLandDetailsRepository extends JpaRepository<EcProposedProjectLandDetails, Integer> {

	@Modifying
	@Query("update EcProposedProjectLandDetails set is_deleted='true'  where id=?1")
	Integer updateEcProposedProjectLandDetails(Integer ecProposedProjectLandDetailId);

}
