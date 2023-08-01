package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcChecklistDetails;

public interface EcChecklistDetailsRepository extends JpaRepository<EcChecklistDetails, Integer> {

	@Modifying
	@Query("update EcChecklistDetails set is_deleted='true' , is_active='false' where id=?1")
	Integer updateEcChecklistDetails(Integer checklistId);

}
