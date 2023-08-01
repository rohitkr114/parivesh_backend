package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.ConstructionDetails;

public interface ConstructionDetailsRepository extends JpaRepository<ConstructionDetails, Integer> {

	@Modifying
	@Query("update ConstructionDetails set is_deleted='true'  where id=?1")
	Integer updateConstructionDetails(Integer id);

}
