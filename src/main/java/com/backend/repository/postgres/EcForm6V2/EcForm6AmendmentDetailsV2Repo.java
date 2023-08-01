package com.backend.repository.postgres.EcForm6V2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm6V2.EcForm6AmendmentDetailsV2;

public interface EcForm6AmendmentDetailsV2Repo extends JpaRepository<EcForm6AmendmentDetailsV2, Integer> {
	
	@Query(value="select efadv.* from master.ec_form_6_amendment_details_v2 efadv where efadv.ec_form6_id =:ec_form6_id and efadv.is_active =true and efadv.is_deleted =false order by efadv.id desc limit 1",nativeQuery=true)
	public Optional<EcForm6AmendmentDetailsV2> getDataByForm6Id(@Param(value="ec_form6_id") Integer ec_form6_id);
}
