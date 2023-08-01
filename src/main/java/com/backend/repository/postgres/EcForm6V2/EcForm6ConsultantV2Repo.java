package com.backend.repository.postgres.EcForm6V2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm6V2.EcForm6ConsultantV2;

public interface EcForm6ConsultantV2Repo extends JpaRepository<EcForm6ConsultantV2, Integer>{
	
	@Query(value="select efc.* from master.ec_form6_consultant efc  where efc.ec_form6_id =:ec_form6_id and efc.is_active =true and efc.is_deleted =false order by efc.id desc limit 1", nativeQuery=true)
	public Optional<EcForm6ConsultantV2> getDataByForm6Id(@Param(value="ec_form6_id") Integer ec_form6_id); 
}
