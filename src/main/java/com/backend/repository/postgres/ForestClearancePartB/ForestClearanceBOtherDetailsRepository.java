package com.backend.repository.postgres.ForestClearancePartB;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearancePartB.ForestClearanceBOtherDetails;

public interface ForestClearanceBOtherDetailsRepository extends JpaRepository<ForestClearanceBOtherDetails, Integer>{

	@Query(value = " select site_inspection_report_id from master.forest_clearance_part_b_other_details where fc_partb_id=?1 ", nativeQuery = true)
	Object[] getDocuments(Integer id);
}
