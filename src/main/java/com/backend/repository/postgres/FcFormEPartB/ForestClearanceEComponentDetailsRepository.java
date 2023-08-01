package com.backend.repository.postgres.FcFormEPartB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormEPartB.ForestClearanceEComponentDetails;

public interface ForestClearanceEComponentDetailsRepository extends JpaRepository<ForestClearanceEComponentDetails, Integer>{

	
	@Query("SELECT a from ForestClearanceEComponentDetails a where a.forestClearanceEBasicDetails.id=?1")
    List<ForestClearanceEComponentDetails> findAllByBasicDetailsId(Integer id);
}
