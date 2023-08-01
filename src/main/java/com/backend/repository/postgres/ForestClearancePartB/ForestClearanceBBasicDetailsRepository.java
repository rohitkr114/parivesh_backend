package com.backend.repository.postgres.ForestClearancePartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearancePartB.ForestClearanceBBasicDetails;

public interface ForestClearanceBBasicDetailsRepository extends JpaRepository<ForestClearanceBBasicDetails, Integer>{
	
	@Query("SELECT a from ForestClearanceBBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
    ForestClearanceBBasicDetails findByIdActive(Integer id);
}
