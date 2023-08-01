package com.backend.repository.postgres.FcFormBPartIII;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.FcFormBPartIII.FcFormBPartIIICheckListDetails;

public interface FcFormBPartIIICheckListDetailsRepository extends JpaRepository<FcFormBPartIIICheckListDetails, Integer>  {

	@Query("select fc from FcFormBPartIIICheckListDetails as fc where fc.id=:id")
	public FcFormBPartIIICheckListDetails getDetailsById(@Param(value="id") Integer id);
}
