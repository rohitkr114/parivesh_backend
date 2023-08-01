package com.backend.repository.postgres.FcFormEPartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormEPartB.ForestClearanceEAfforestationDetails;

public interface ForestClearanceEAfforestationDetailsRepository extends JpaRepository<ForestClearanceEAfforestationDetails, Integer>{
	
	@Query("select new ForestClearanceEAfforestationDetails(id) from ForestClearanceEAfforestationDetails where fc_form_e_part_b_id=?1")
	ForestClearanceEAfforestationDetails getDataByFcFormEPartBId(Integer id);

}
