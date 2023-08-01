package com.backend.repository.postgres.ForestClearanceFormAPartIV;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormAPartIII.FcFormAPartIIIBasicDetails;
import com.backend.model.FcFormAPartIV.FcFormAPartIVBasicDetails;

public interface FcFormAPartIVBasicDetailsRepository extends JpaRepository<FcFormAPartIVBasicDetails, Integer> {

	@Query("SELECT a from FcFormAPartIVBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormAPartIVBasicDetails findByIdActive(Integer id);

}
