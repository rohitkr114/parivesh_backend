package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCAfforestationDetails;
import com.backend.model.ForestClearanceFormC.FcFormCOtherDetails;

public interface FcFormCOtherDetailsRepository extends JpaRepository<FcFormCOtherDetails, Integer>{

	@Query(" select  new FcFormCOtherDetails( id,is_ec_required,"
			+ "ec_application_status, ec_proposal_no, ec_issued_date, ec_moefcc_file_no,"
			+ " ec_submission_date, ec_application_sub_status,ec_non_submission_reason) from FcFormCOtherDetails where fc_form_c_id=?1 ")
	FcFormCOtherDetails getFcFormCOtherDetailsById(Integer id);

	@Query(value = " select ec_letter_id from master.fc_form_c_other_details where fc_form_c_id=?1 ", nativeQuery = true)
	List<Object[]> getDocuments(Integer id);
	
	@Query("select new FcFormCOtherDetails(id) from FcFormCOtherDetails where fc_form_c_id=?1")
	FcFormCOtherDetails getDataByFcFormCId(Integer id);
}
