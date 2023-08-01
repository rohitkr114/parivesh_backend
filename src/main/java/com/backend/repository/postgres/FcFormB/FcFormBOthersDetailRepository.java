package com.backend.repository.postgres.FcFormB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBOthersDetail;
import com.backend.model.ForestClearanceFormB.FcFormBProposedLand;

public interface FcFormBOthersDetailRepository extends JpaRepository<FcFormBOthersDetail, Integer>{

	@Query("Select pa from FcFormBOthersDetail pa where pa.fcFormBProjectDetails.id=?1 and pa.is_deleted='false'")
	FcFormBOthersDetail getByFcID(Integer id); 
	
	@Query("select new FcFormBOthersDetail(id) from FcFormBOthersDetail where fc_form_b_id=?1")
	FcFormBOthersDetail getDataByFcFormBId(Integer id);
}
