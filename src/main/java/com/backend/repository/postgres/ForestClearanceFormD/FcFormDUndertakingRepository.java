package com.backend.repository.postgres.ForestClearanceFormD;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormD.FcFormDUndertaking;

public interface FcFormDUndertakingRepository extends JpaRepository<FcFormDUndertaking, Integer> {

	@Query(" select new FcFormDUndertaking( id, i_agree," + "undertaking_person_name,undertaking_person_designation,"
			+ "undertaking_person_company,undertaking_person_address," + "undertaking_person_esign,undertaking_date"
			+ ") from FcFormDUndertaking where fc_form_d_id=?1")
	FcFormDUndertaking getFcFormCUndertakingDetailsById(Integer id);

	@Query("select new FcFormDUndertaking(id) from FcFormDUndertaking where fc_form_d_id=?1")
	FcFormDUndertaking getDataByFcFormDId(Integer id);

}
