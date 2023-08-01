package com.backend.repository.postgres.FormAPartIV;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FormAPartIV.FormAPartIVBasicDetails;

public interface FormAPartIVBasicDetailsRepository extends JpaRepository<FormAPartIVBasicDetails, Integer> {

	@Query("SELECT a from FormAPartIVBasicDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FormAPartIVBasicDetails findByIdActive(Integer id);
}
