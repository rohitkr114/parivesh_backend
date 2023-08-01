package com.backend.repository.postgres.FcFormEPartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormEPartB.ForestClearanceEAfforestationDetails;
import com.backend.model.FcFormEPartB.ForestClearanceEOtherDetails;

public interface ForestClearanceEOtherDetailsRepository extends JpaRepository<ForestClearanceEOtherDetails, Integer>{

	@Query(value = " select site_inspection_report_id from master.fc_form_e_part_b_other_details where fc_e_part_b_id=?1 ", nativeQuery = true)
	Object[] getDocuments(Integer id);
	
	@Query("select new ForestClearanceEOtherDetails(id) from ForestClearanceEOtherDetails where fc_e_part_b_id=?1")
	ForestClearanceEOtherDetails getDataByFcFormEPartBId(Integer id);
}
