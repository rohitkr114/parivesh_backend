package com.backend.repository.postgres.FcFormBPartB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormBPartB.FcFormBPartBOtherDetails;

public interface FcFormBPartBOtherDetailsRepository extends JpaRepository<FcFormBPartBOtherDetails, Integer> {

	@Query(value = " select site_inspection_report_id from master.fc_form_c_part_b_other_details where fc_form_b_part_b_id=?1 ", nativeQuery = true)
	Object[] getDocuments(Integer id);
}
