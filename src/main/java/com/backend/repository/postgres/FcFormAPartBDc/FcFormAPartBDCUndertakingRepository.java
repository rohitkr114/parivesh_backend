package com.backend.repository.postgres.FcFormAPartBDc;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.FcFormAPartBDc.FcFormAPartBDCUndertaking;

public interface FcFormAPartBDCUndertakingRepository extends JpaRepository<FcFormAPartBDCUndertaking, Integer> {

//	@Query("select new EcUndertaking(id, i_agree, undertaking_person_name,"
//			+ "	 undertaking_person_designation, undertaking_person_company, undertaking_person_address,"
//			+ "	 undertaking_person_esign, undertaking_date, submission_date ) from FcFormAPartBDCUndertaking dc where fc_formA_DC_id=?1")
//	public Optional<FcFormAPartBDCUndertaking> getByFcFormAPartBDCID(Integer id);
}
