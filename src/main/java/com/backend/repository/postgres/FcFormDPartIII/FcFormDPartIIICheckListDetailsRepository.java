package com.backend.repository.postgres.FcFormDPartIII;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.FcFormDPartIII.FcFormDPartIIICheckListDetails;

public interface FcFormDPartIIICheckListDetailsRepository extends JpaRepository<FcFormDPartIIICheckListDetails, Integer> {
	
	@Query("select fc from FcFormDPartIIICheckListDetails as fc where fc.id=:id")
	public FcFormDPartIIICheckListDetails getDetailsById(@Param(value="id") Integer id);
}
