package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.GeneralRemarks;

public interface GeneralRemarksRepository extends JpaRepository<GeneralRemarks,Integer> {
	
	@Query("select gr from GeneralRemarks gr where gr.is_active='true' and gr.is_deleted='false' and gr.ref_id=:ref_id and gr.ref_type=:ref_type order by gr.updated_on desc")
	public List<GeneralRemarks> getGeneralRemarks(@Param(value="ref_id") Integer ref_id, @Param(value="ref_type") String ref_type);
	
	
//	@Query(value="select r.rolename from ua.user_access_mapping uam LEFT JOIN authentication.role r on uam.role_id= r.entityid where uam.entityid=:entity_id", nativeQuery=true)
//	public String getRoleName(@Param(value="entity_id") Integer entity_id);


	@Query(value="select r.rolename from authentication.user_entity ue Inner Join authentication.role r on ue.selected_role=r.entityid where ue.entityid=:entity_id", nativeQuery=true)
	public String getRoleName(@Param(value="entity_id") Integer entity_id);
}

