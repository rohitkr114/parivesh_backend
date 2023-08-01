package com.backend.repository.postgres.EcForm6V2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm6V2.EcForm6ProjectActivityDetailsV2;

public interface EcForm6ProjectActivityDetailsV2Repo extends JpaRepository<EcForm6ProjectActivityDetailsV2, Integer> {
	
	@Query(value="select efad.* from master.ec_form6_project_activity_details as efad where efad.ec_form6_id=:ec_form6_id and efad.is_active=true and efad.is_deleted=false ",nativeQuery=true)
	public List<EcForm6ProjectActivityDetailsV2> getProjectActivityList(@Param(value="ec_form6_id") Integer ec_form6_id);
}
