package com.backend.repository.postgres.FcFormCPartIII;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.FcFormCPartIII.FcFormCPartIIIProjectDetails;

public interface FcFormCPartIIIProjectDetailsRepository extends JpaRepository<FcFormCPartIIIProjectDetails, Integer> {
	
	@Query(value="select * from authority.fc_form_C_part_III_project_details fc where fc.formC_partIII_checklistDetails_id=:checkListId",nativeQuery=true)
	public FcFormCPartIIIProjectDetails getProjectDetailsByCheckListId(@Param(value="checkListId") Integer checkListId);

}
