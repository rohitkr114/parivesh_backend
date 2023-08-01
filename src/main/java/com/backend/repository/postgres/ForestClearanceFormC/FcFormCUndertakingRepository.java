package com.backend.repository.postgres.ForestClearanceFormC;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormC.FcFormCPriorApproval;
import com.backend.model.ForestClearanceFormC.FcFormCUndertaking;

public interface FcFormCUndertakingRepository extends JpaRepository<FcFormCUndertaking, Integer>{
	
	@Query(" select new FcFormCUndertaking( id, i_agree,"
			+ "undertaking_person_name,undertaking_person_designation,"
			+ "undertaking_person_company,undertaking_person_address,"
			+ "undertaking_person_esign,undertaking_date"
			+ ") from FcFormCUndertaking where fc_form_c_id=?1")
	FcFormCUndertaking getFcFormCUndertakingDetailsById(Integer id);
	
	@Query("select new FcFormCUndertaking(id) from FcFormCUndertaking where fc_form_c_id=?1")
	FcFormCUndertaking getDataByFcFormCId(Integer id);

}
