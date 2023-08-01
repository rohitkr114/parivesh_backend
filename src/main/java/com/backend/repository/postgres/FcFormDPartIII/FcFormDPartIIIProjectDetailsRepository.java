package com.backend.repository.postgres.FcFormDPartIII;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.FcFormDPartIII.FcFormDPartIIIProjectDetails;

public interface FcFormDPartIIIProjectDetailsRepository extends JpaRepository<FcFormDPartIIIProjectDetails, Integer> {
	
	@Query(value="select * from authority.fc_form_D_part_III_project_details fc where fc.formD_partIII_checklistDetails_id=:checkListId",nativeQuery=true)
	public FcFormDPartIIIProjectDetails getProjectDetailsByCheckListId(@Param(value="checkListId") Integer checkListId);
}
