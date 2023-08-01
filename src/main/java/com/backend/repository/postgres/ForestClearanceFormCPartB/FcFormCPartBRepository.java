package com.backend.repository.postgres.ForestClearanceFormCPartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormCPartB.FcFormCPartB;

public interface FcFormCPartBRepository extends JpaRepository<FcFormCPartB, Integer>{

	@Query("SELECT a from FcFormCPartB a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormCPartB findByIdActive(Integer id);
}
