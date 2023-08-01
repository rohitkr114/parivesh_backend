package com.backend.repository.postgres.FcFormB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBAfforestationDetails;
import com.backend.model.ForestClearanceFormB.FcFormBProposedLand;

public interface FcFormBAfforestationDetailsRepository extends JpaRepository<FcFormBAfforestationDetails, Integer>{

	@Query("Select pa from FcFormBAfforestationDetails pa where pa.fcFormBProjectDetails.id=?1 and pa.is_deleted='false'")
	FcFormBAfforestationDetails getByFcID(Integer id); 

	@Query("select new FcFormBAfforestationDetails(id) from FcFormBAfforestationDetails where fc_form_b_id=?1")
	FcFormBAfforestationDetails getDataByFcFormBId(Integer id);
}
