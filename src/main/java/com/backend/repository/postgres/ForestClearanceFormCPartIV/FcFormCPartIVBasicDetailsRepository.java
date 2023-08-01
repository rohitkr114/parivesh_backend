package com.backend.repository.postgres.ForestClearanceFormCPartIV;

import com.backend.model.FcFormCPartIV.FcFormCPartIVBasicDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FcFormCPartIVBasicDetailsRepository extends JpaRepository<FcFormCPartIVBasicDetails, Integer> {

	@Query("SELECT a from FcFormCPartIVBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormCPartIVBasicDetails findByIdActive(Integer id);

}
