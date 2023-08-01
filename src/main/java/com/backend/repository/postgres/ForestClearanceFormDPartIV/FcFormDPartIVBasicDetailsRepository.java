package com.backend.repository.postgres.ForestClearanceFormDPartIV;

import com.backend.model.FormDPartIV.FcFormDPartIVBasicDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FcFormDPartIVBasicDetailsRepository extends JpaRepository<FcFormDPartIVBasicDetails, Integer> {

	@Query("SELECT a from FcFormDPartIVBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormDPartIVBasicDetails findByIdActive(Integer id);

}
