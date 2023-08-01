package com.backend.repository.postgres.FcComplianceCondition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.FcComplianceCondition.FcComplianceCondition;

public interface FcComplianceConditionRepository extends JpaRepository<FcComplianceCondition, Integer>{
	
	@Query(value = "Select fc.* from master.fc_compliance_condition fc where fc.application_id=?1 and (fc.is_active=true or fc.is_active is null)", nativeQuery = true)
	List<FcComplianceCondition> findByFcId(Integer fc_id);
	
	@Query(value = "select ps.selected_role from authentication.user_entity ps join ua.user_access_mapping um on um.user_id = ps.entityid  where ps.entityid=:id limit 1;", nativeQuery = true)
	Integer getUserRole(@Param(value = "id") Integer id);
	
	@Query(value = "select r.rolename  from authentication.user_entity ue inner join authentication.\"role\" r on r.entityid=ue.selected_role where ue.entityid=:id ", nativeQuery = true)
	String getUserRoleName(@Param(value = "id") Integer id);

}
  