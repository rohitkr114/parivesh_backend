package com.backend.repository.postgres.EcForm6V2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm6V2.EcForm6ImplementationDetailsV2;

public interface EcForm6ImplementationDetailsV2Repo extends JpaRepository<EcForm6ImplementationDetailsV2, Integer> {
	
	@Query(value="select efidv.* from master.ec_form_6_implementation_details_v2 efidv where efidv.ec_form6_id =:ec_form6_id and efidv.is_active =true and efidv.is_deleted =false ",nativeQuery=true)
	public Optional<EcForm6ImplementationDetailsV2> getDataByForm6Id(@Param(value="ec_form6_id") Integer ec_form6_id);
}
