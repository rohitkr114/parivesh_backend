package com.backend.repository.postgres.FcFormBPartIV;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormBPartIV.FcFormBPartIVBasicDetails;

public interface FcFormBPartIVBasicDetailsRepository extends JpaRepository<FcFormBPartIVBasicDetails, Integer> {

	@Query("SELECT a from FcFormBPartIVBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormBPartIVBasicDetails findByIdActive(Integer id);
}
