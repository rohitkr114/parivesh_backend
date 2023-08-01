package com.backend.repository.postgres.EDSV2;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EDSV2.EDSFormV2;
import com.backend.model.EDSV2.EDSQueries;

public interface EDSFormV2Repository extends JpaRepository<EDSFormV2, Integer> {

	@Query(value = "select ue.selected_role from authentication.user_entity ue where ue.entityid=:entity_id limit 1", nativeQuery = true)
	public Integer getEDSByRole(@Param(value = "entity_id") Integer entity_id);

	@Query(value = "select uam.office_id from ua.user_access_mapping uam inner join authentication.user_entity ue on ue.selected_role=uam.role_id where ue.entityid=:entity_id limit 1", nativeQuery = true)
	public Integer getEDSByOffice(@Param(value = "entity_id") Integer entity_id);

	@Query(value = "select pd.created_by from master.project_details pd inner join master.proponent_applications pa on pd.id=pa.project_id where pa.id=:application_id limit 1", nativeQuery = true)
	public Integer getEDSTo(@Param(value = "application_id") Integer application_id);

	@Query(value = "select uam.role_id from ua.user_access_mapping uam inner join master.project_details pd on pd.created_by=uam.user_id where pd.created_by=:created_by limit 1", nativeQuery = true)
	public Integer getEDSToRole(@Param(value = "created_by") Integer created_by);

	@Query(value = "select r.rolename from authentication.role r where r.entityid=:role_id", nativeQuery = true)
	public String getEDSByRoleName(@Param(value = "role_id") Integer role_id);

	@Query(value = "select uam.office_id from ua.user_access_mapping uam inner join master.project_details pd on pd.created_by=uam.user_id where pd.created_by=:created_by limit 1", nativeQuery = true)
	public Integer getEDSToOffice(@Param(value = "created_by") Integer created_by);

	@Query(value = "select efv.* from authority.eds_form_v2 as efv where efv.application_id =:application_id", nativeQuery = true)
	public List<EDSFormV2> getEdsByIds(@Param(value = "application_id") Integer application_id);

	@Query(value = "select efv.* from authority.eds_form_v2 as efv where efv.application_id =:application_id and efv.eds_to_role_id=:role_id and efv.is_active=true and efv.is_deleted=false", nativeQuery = true)
	public List<EDSFormV2> getEdsByIdsandRole(@Param(value = "application_id") Integer application_id,
			@Param(value = "role_id") Integer role_id);

	@Query("select new EDSFormV2(ec.id, ec.application_id, ec.app_history_id,"
			+ " ec.eds_by, ec.eds_by_role_id,ec.eds_by_office_id,"
			+ "	ec.eds_to, ec.eds_to_role_id, ec.eds_to_office_id,ec.remarks) from EDSFormV2 ec where ec.id=?1")
	public EDSFormV2 getEdsById(Integer eds_id);

	//	@Query(value="select * from authority.eds_queries as eq join authority.eds_form_v2 as efv on eq.eds_form_v2_id=efv.id where efv.application_id=:application_id and efv.app_history_id=:app_history_id", nativeQuery=true)
//	 List<EDSQueries> getQueriesByIds(@Param(value="app_history_id") Integer app_history_id,@Param(value="application_id") Integer application_id);
}
