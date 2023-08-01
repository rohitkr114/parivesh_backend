package com.backend.repository.postgres.FcFormBPartIIINodal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormBPartIIINodal.FcFormBPartIIINodalBasicDetails;

public interface FcFormBPartIIINodalBasicDetailsRepository extends JpaRepository<FcFormBPartIIINodalBasicDetails, Integer> {

	@Query("SELECT a from FcFormBPartIIINodalBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormBPartIIINodalBasicDetails findByIdActive(Integer id);

}
