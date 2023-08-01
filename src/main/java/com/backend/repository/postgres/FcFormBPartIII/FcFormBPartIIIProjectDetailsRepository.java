package com.backend.repository.postgres.FcFormBPartIII;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.FcFormBPartIII.FcFormBPartIIIProjectDetails;

public interface FcFormBPartIIIProjectDetailsRepository extends JpaRepository<FcFormBPartIIIProjectDetails, Integer> {

	@Query(value="select * from authority.fc_form_B_part_III_project_details fc where fc.formB_partIII_checklistDetails_id=:checkListId",nativeQuery=true)
	public FcFormBPartIIIProjectDetails getProjectDetailsByCheckListId(@Param(value="checkListId") Integer checkListId);
}
