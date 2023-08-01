package com.backend.repository.postgres.ForestClearanceFormE;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.DocumentDetails;
import com.backend.model.ForestClearanceE.FcFormEKmls;
import com.backend.model.ForestClearanceE.FcFormEOtherDetails;
import com.backend.model.ForestClearanceFormC.FcFormCOtherDetails;

public interface FcFormEOtherDetailsRepository extends JpaRepository<FcFormEOtherDetails, Integer>{
	
	@Query("select new FcFormEOtherDetails(id) from FcFormEOtherDetails where fc_form_e_id=?1")
	FcFormEOtherDetails getDataByFcFormEId(Integer id);	
	
	@Query("Select pa from FcFormEOtherDetails pa where pa.fcFormE.id=?1 and pa.is_delete='false'")
	FcFormEOtherDetails getByFcID(Integer id); 
//	
//	@Query(" select  new FcFormEOtherDetails( id, "
//			+ " is_part_of_proposed_land_diverted, "
//			+ " no_of_patches, "
//			+ "	area_of_forest_land, "
//			+ " is_pa_esz_involved, "
//			+ " is_pa_esz_involved_reason, "
//			+ " protected_area_type, "
//			+ " pa_approval_status, "
//			+ " pa_proposal_no, "
//			+ " pa_approval_date, "
//			+ " pa_reference_no, "
//			+ " DocumentDetails pa_report, "
//			+ " pa_application_date, "
//			+ " pa_non_submission_reason, "
//			+ " esz_nbwl_approval_required, "
//			+ " esz_approval_status, "
//			+ " esz_proposal_no, "
//			+ " esz_approval_date, "
//			+ " esz_reference_no, "
//			+ " DocumentDetails esz_report, "
//			+ " esz_application_date, "
//			+ " esz_non_submission_reason, "
//			+ " esz_non_nbwl_approval_reason, "
//			+ " is_within_scheduled_area) from FcFormCOtherDetails where fc_form_c_id=?1 ")
//	FcFormEOtherDetails getFcFormEOtherDetailsById(Integer id);
}
