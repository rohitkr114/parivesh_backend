package com.backend.repository.postgres.FcFormAPartIIINodal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormAPartIIINodal.FcFormAPartIIINodalBasicDetails;

public interface FcFormAPartIIINodalBasicDetailsRepository extends JpaRepository<FcFormAPartIIINodalBasicDetails, Integer> {

	@Query("SELECT a from FcFormAPartIIINodalBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormAPartIIINodalBasicDetails findByIdActive(Integer id);

}
