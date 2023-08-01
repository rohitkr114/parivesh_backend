package com.backend.repository.postgres.EcForm6V2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm6V2.EcForm6UnitDetailsV2;

public interface EcForm6UnitDetailsV2Repo extends JpaRepository<EcForm6UnitDetailsV2, Integer> {
	
	@Query(value="select efudv.* from master.ec_form_6_unit_details_v2 efudv where efudv.ec_form6_id =:ec_form6_id and efudv.is_active =true and efudv.is_deleted =false",nativeQuery=true)
	public List<EcForm6UnitDetailsV2> getDataByForm6Id(@Param(value="ec_form6_id") Integer ec_form6_id);
}
