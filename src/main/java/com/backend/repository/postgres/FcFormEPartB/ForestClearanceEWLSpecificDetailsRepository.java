package com.backend.repository.postgres.FcFormEPartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormEPartB.ForestClearanceEWLSpecificDetails;

public interface ForestClearanceEWLSpecificDetailsRepository extends JpaRepository<ForestClearanceEWLSpecificDetails, Integer>{

	@Query("select new ForestClearanceEWLSpecificDetails(id) from ForestClearanceEWLSpecificDetails where fc_form_e_part_b_id=?1")
	ForestClearanceEWLSpecificDetails getDataByFcFormEPartBId(Integer id);
}
