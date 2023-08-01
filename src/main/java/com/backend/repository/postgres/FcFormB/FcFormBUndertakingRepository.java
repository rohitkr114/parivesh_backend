package com.backend.repository.postgres.FcFormB;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceFormB.FcFormBUndertaking;

public interface FcFormBUndertakingRepository extends JpaRepository<FcFormBUndertaking, Integer>{

	@Query(" select new FcFormBUndertaking( id, i_agree,"
			+ "undertaking_person_name,undertaking_person_designation,"
			+ "undertaking_person_company,undertaking_person_address,"
			+ "undertaking_person_esign,undertaking_date"
			+ ") from FcFormBUndertaking where fc_form_b_id=?1 and is_delete='false'")
	Optional<FcFormBUndertaking> getRecordExist(Integer fc_form_b_id);
	
	@Query(" select new FcFormBUndertaking( id, i_agree,"
			+ "undertaking_person_name,undertaking_person_designation,"
			+ "undertaking_person_company,undertaking_person_address,"
			+ "undertaking_person_esign,undertaking_date"
			+ ") from FcFormBUndertaking where fc_form_b_id=?1 and is_delete='false'")
	FcFormBUndertaking getRecordExists(Integer fc_form_b_id);

}
