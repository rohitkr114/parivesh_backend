package com.backend.repository.postgres.FcFormCPartIII;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.FcFormCPartIII.FcFormCPartIIICheckListDetails;

public interface FcFormCPartIIICheckListDetailsRepository extends JpaRepository<FcFormCPartIIICheckListDetails, Integer> {
	
	@Query("select fc from FcFormCPartIIICheckListDetails as fc where fc.id=:id")
	public FcFormCPartIIICheckListDetails getDetailsById(@Param(value="id") Integer id);
}
