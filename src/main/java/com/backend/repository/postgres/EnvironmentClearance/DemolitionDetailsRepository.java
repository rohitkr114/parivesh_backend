package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.DemolitionDetails;

public interface DemolitionDetailsRepository extends JpaRepository<DemolitionDetails, Integer> {

	@Modifying
	@Query("update DemolitionDetails set is_deleted='true'  where id=?1")
	Integer updateDemolitionDetails(Integer id);

}
