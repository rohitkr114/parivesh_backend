package com.backend.repository.postgres.ForestClearancePartB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearancePartB.ForestClearanceBComponentDetails;

public interface ForestClearanceBComponentDetailsRepository extends JpaRepository<ForestClearanceBComponentDetails, Integer>{

	
	@Query("SELECT a from ForestClearanceBComponentDetails a where a.forestClearanceBBasicDetails.id=?1")
    List<ForestClearanceBComponentDetails> findAllByBasicDetailsId(Integer id);
}
