package com.backend.repository.postgres.ForestClearanceFormAPartIII;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormAPartIII.FcFormAPartIIIBasicDetails;

public interface FcFormAPartIIIBasicDetailsRepository extends JpaRepository<FcFormAPartIIIBasicDetails, Integer> {

	@Query("SELECT a from FcFormAPartIIIBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormAPartIIIBasicDetails findByIdActive(Integer id);

}
