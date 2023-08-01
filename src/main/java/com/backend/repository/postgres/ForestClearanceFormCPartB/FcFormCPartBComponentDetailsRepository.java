package com.backend.repository.postgres.ForestClearanceFormCPartB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormCPartB.FcFormCPartBComponentDetails;

public interface FcFormCPartBComponentDetailsRepository extends JpaRepository<FcFormCPartBComponentDetails, Integer>{

	@Query("SELECT a from FcFormCPartBComponentDetails a where a.fcFormCPartB.id=?1")
    List<FcFormCPartBComponentDetails> findAllByBasicDetailsId(Integer id);
	
}
