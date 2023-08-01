package com.backend.repository.postgres.EcForm6V2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm6V2.EcForm6ProjectDetailsV2;

public interface EcForm6ProjectDetailsV2Repo extends JpaRepository<EcForm6ProjectDetailsV2, Integer> {
	
	@Query("select efpd from EcForm6ProjectDetailsV2 efpd where efpd.id=:id and efpd.isActive=true and efpd.isDeleted=false")
	public Optional<EcForm6ProjectDetailsV2> findByFormId(@Param(value="id") Integer id);
	
	@Query(value="select a.name from master.activities a inner join master.ec_form_6_v2 ep on ep.major_activity_id = a.id where ep.id =:id limit 1", nativeQuery = true)
    public String getMajorActivityName(@Param(value="id") Integer id);
	
	@Query(value="select efv.* from master.ec_form_6_v2 efv where efv.id =:id and efv.is_active =true and efv.is_deleted =false order by efv.id desc limit 1",nativeQuery=true)
	public Optional<EcForm6ProjectDetailsV2> getDataById(@Param(value="id") Integer id);
	
	@Query(value="select a.item_no from master.activities a inner join master.ec_form_6_v2 ep on ep.major_activity_id = a.id where ep.id =:id limit 1", nativeQuery = true)
    public String getItemNo(@Param(value="id") Integer id);
}
