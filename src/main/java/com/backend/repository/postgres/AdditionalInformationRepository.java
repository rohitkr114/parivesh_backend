package com.backend.repository.postgres;

import java.util.List;

import com.backend.dto.AdditionalInformationDTO;
import com.backend.dto.UsernameDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.AdditionalInformation;
import org.springframework.data.repository.query.Param;

public interface AdditionalInformationRepository extends JpaRepository<AdditionalInformation, Integer>{

	@Query("SELECT fc from AdditionalInformation fc where is_special_document=?2 and fc.is_deleted ='false' and fc.ref_id=?1 order by fc.id desc ")
	List<AdditionalInformation> findAdditionalInformationByRefId(String refId,Boolean isSpecialDocument);

	@Query("SELECT fc from AdditionalInformation fc where is_special_document=?2 and fc.is_deleted ='false' and fc.ref_id=?1")
	List<AdditionalInformationDTO> findAdditionalInformationByRefIdV2(@Param(value ="refId") String refId, @Param(value ="isSpecialDocument") Boolean isSpecialDocument);

	@Query(value = "select distinct coalesce(e.designation,de.designation_name) as designation,r.rolename,r.entityid as role_Id, ue.name from  ua.user_access_mapping uam left join authentication.user_entity ue on ue.entityid =uam.user_id left join master.employee e on ue.entityid =e.entityid left join authentication.employee em on em.entityid =ue.entityid left join ua.designation_entity de on de.entityid =em.designation_id left join authentication.\"role\" r on r.entityid =ue.selected_role where uam.user_id  = ?1 limit 1 ",nativeQuery = true)
	UsernameDTO findusername(Integer createdBy);

	
}
