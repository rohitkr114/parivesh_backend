package com.backend.repository.postgres.FcFormEPartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormEPartB.ForestClearanceEBasicDetails;

public interface ForestClearanceEBasicDetailsRepository extends JpaRepository<ForestClearanceEBasicDetails, Integer>{
	
	@Query("SELECT a from ForestClearanceEBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
    ForestClearanceEBasicDetails findByIdActive(Integer id);
}
