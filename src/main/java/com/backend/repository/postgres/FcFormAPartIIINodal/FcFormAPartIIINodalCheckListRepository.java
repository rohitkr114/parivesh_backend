package com.backend.repository.postgres.FcFormAPartIIINodal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormAPartIIINodal.FcFormAPartIIINodalCheckListDetails;

public interface FcFormAPartIIINodalCheckListRepository  extends JpaRepository<FcFormAPartIIINodalCheckListDetails, Integer> {

	@Query("SELECT a from FcFormAPartIIINodalCheckListDetails a where a.is_active='true' and a.is_deleted='false' and a.id=?1")
	FcFormAPartIIINodalCheckListDetails findByIdActive(Integer id);
}
