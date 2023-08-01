package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.DocumentDetails;
import com.backend.model.NoteSheetDTO;


public interface NoteSheetDTORepository extends JpaRepository<NoteSheetDTO, Integer> {

	@Query(value = "(select ns.id,"
			+ "ns.note,ns.app_history_id,ns.application_id,ns.from_role_id,ns.from_user_id,ns.to_role_id,"
			+ "ns.to_user_id,ro.rolename as from_role_name,ra.rolename as to_role_name,ue.name as from_user_name,ue.emailid as from_user_email,"
			+ "uo.name as to_user_name,uo.emailid as to_user_email,ns.created_on as created_on,ns.updated_on as updated_on "
			+ "from master.notesheet ns left join authentication.role ro on ns.from_role_id= ro.entityid "
			+ "left join authentication.role ra on ns.to_role_id=ra.entityid "
			+ "left join authentication.user_entity ue on ns.from_user_id=ue.entityid "
			+ "left join authentication.user_entity uo on ns.to_user_id=uo.entityid "
			+ "where ns.is_active=true and ns.is_deleted=false order by ns.application_id desc)", nativeQuery = true)
	List<NoteSheetDTO> getNotesheetByApplication();

	@Query(value = "(select ns.id,"
			+ "ns.note,ns.app_history_id,ns.application_id,ns.from_role_id,ns.from_user_id,ns.to_role_id,"
			+ "ns.to_user_id,ro.rolename as from_role_name,ra.rolename as to_role_name,ue.name as from_user_name,ue.emailid as from_user_email,"
			+ "uo.name as to_user_name,uo.emailid as to_user_email,ns.created_on as created_on,ns.updated_on as updated_on, ns.status "
			+ "from master.notesheet ns left join authentication.role ro on ns.from_role_id= ro.entityid "
			+ "left join authentication.role ra on ns.to_role_id=ra.entityid "
			+ "left join authentication.user_entity ue on ns.from_user_id=ue.entityid "
			+ "left join authentication.user_entity uo on ns.to_user_id=uo.entityid "
			+ "where ns.is_active=true and ns.is_deleted=false)", nativeQuery = true)
	List<NoteSheetDTO> getNotesheet();
 
	/*
	@Query(value = "(SELECT NS.ID,NS.NOTE,NS.APP_HISTORY_ID,NS.APPLICATION_ID,(select name from authentication.user_entity ue,process_engine.process_step_authority psa  where ue.entityid =psa.user_id and psa.app_history_id =NS.APP_HISTORY_ID order by id desc limit 1)as username,(select de.designation_name from authentication.employee e join ua.designation_entity de on e.designation_id = de.entityid join process_engine.process_step_authority psa on e.entityid =psa.user_id and psa.app_history_id =NS.APP_HISTORY_ID order by id desc limit 1) as designation,(SELECT RO.ROLENAME FROM AUTHENTICATION.ROLE RO,PROCESS_ENGINE.PROCESS_STEP_AUTHORITY APH WHERE APH.APP_HISTORY_ID=NS.APP_HISTORY_ID AND  RO.ENTITYID = APH.ROLE_ID ORDER BY APH.UPDATED_ON DESC LIMIT 1 ) AS ROLE_NAME,(SELECT APH.ROLE_ID FROM PROCESS_ENGINE.PROCESS_STEP_AUTHORITY APH WHERE APH.APP_HISTORY_ID=NS.APP_HISTORY_ID ORDER BY APH.UPDATED_ON DESC LIMIT 1 ) AS ROLE_ID,NS.CREATED_ON AS CREATED_ON,NS.UPDATED_ON AS UPDATED_ON,NS.STATUS, NS.COPY_OF_ATTACHMENT_ID AS DOCUMENT FROM  MASTER.NOTESHEET NS order by NS.CREATED_ON DESC)",nativeQuery = true)
	List<NoteSheetDTO> getNotesheetByHistoryId();
	
	@Query(value = "(SELECT NS.ID,NS.NOTE,NS.APP_HISTORY_ID,NS.APPLICATION_ID,(select name from authentication.user_entity ue,process_engine.process_step_authority psa  where ue.entityid =psa.user_id and psa.app_history_id =NS.APP_HISTORY_ID order by id desc limit 1)as username,(select de.designation_name from authentication.employee e join ua.designation_entity de on e.designation_id = de.entityid join process_engine.process_step_authority psa on e.entityid =psa.user_id and psa.app_history_id =NS.APP_HISTORY_ID order by id desc limit 1) as designation,(SELECT RO.ROLENAME FROM AUTHENTICATION.ROLE RO,PROCESS_ENGINE.PROCESS_STEP_AUTHORITY APH WHERE APH.APP_HISTORY_ID=NS.APP_HISTORY_ID AND  RO.ENTITYID = APH.ROLE_ID ORDER BY APH.UPDATED_ON DESC LIMIT 1 ) AS ROLE_NAME,(SELECT APH.ROLE_ID FROM PROCESS_ENGINE.PROCESS_STEP_AUTHORITY APH WHERE APH.APP_HISTORY_ID=NS.APP_HISTORY_ID ORDER BY APH.UPDATED_ON DESC LIMIT 1 ) AS ROLE_ID,NS.CREATED_ON AS CREATED_ON,NS.UPDATED_ON AS UPDATED_ON,NS.STATUS,NS.COPY_OF_ATTACHMENT_ID AS DOCUMENT FROM  MASTER.NOTESHEET NS WHERE NS.APPLICATION_ID = ?1 order by NS.CREATED_ON DESC)",nativeQuery = true)
	List<NoteSheetDTO> getNotesheetByHistoryAppId(Integer appId);
	*/
	
	@Query(value = " select * from master.get_notesheet_details(:appId) ", nativeQuery = true)
	List<NoteSheetDTO> getNotesheetByHistoryId(Integer appId);
	
	@Query(value = " select * from master.get_notesheet_details(:appId,:user_id) ", nativeQuery = true)
	List<NoteSheetDTO> getNotesheetByHistoryAppId(Integer appId, Integer user_id);
	
	@Query(value = " select de.designation_name from authentication.employee e left join ua.designation_entity de on e.designation_id = de.entityid where e.entityid =?1 ", nativeQuery = true)
    String getDesignationOther(Integer id);
    
    @Query(value = " select e.designation from master.employee e where e.entityid =?1 ", nativeQuery = true)
    String getDesignationPP(Integer id);
	
	@Query(value = "( SELECT * from master.document_details where id=?1)", nativeQuery = true)
	DocumentDetails getDoc(Integer doc_id);
	
	@Query(value="(select ue.entityid from authentication.user_entity ue,process_engine.process_step_authority psa where ue.entityid =psa.user_id and psa.app_history_id =NS.APP_HISTORY_ID order by id desc limit 1)",nativeQuery = true)
	Integer getUserIdFromNotesheet();
	

}
