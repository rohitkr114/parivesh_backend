package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcCroppingPattern;

public interface EcCroppingPatternRepository extends JpaRepository<EcCroppingPattern, Integer> {

	@Modifying
	@Query("update EcCroppingPattern set is_deleted='true'  where id=?1")
	Integer updateEcCroppingPattern(Integer id);

}
