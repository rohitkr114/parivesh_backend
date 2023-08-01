package com.backend.repository.postgres.EcForm6V2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm6V2.EcForm6UndertakingV2;

public interface EcForm6UndertakingV2Repo extends JpaRepository<EcForm6UndertakingV2, Integer> {
	
	@Query(value="select efuv.* from master.ec_form_6_undertaking_v2 efuv where efuv.ec_form6_id =:ec_form6_id and efuv.is_active =true and efuv.is_deleted =false", nativeQuery=true)
	public Optional<EcForm6UndertakingV2> getDataByEcId(@Param(value="ec_form6_id") Integer ec_form6_id);
	
	@Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
	public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
}
