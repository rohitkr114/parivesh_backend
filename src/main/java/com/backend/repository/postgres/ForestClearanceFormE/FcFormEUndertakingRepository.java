package com.backend.repository.postgres.ForestClearanceFormE;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceE.FcFormEUndertaking;

public interface FcFormEUndertakingRepository extends JpaRepository<FcFormEUndertaking, Integer>{

//	@Query(" select new FcFormEUndertaking( id, "
//			+ " i_agree, "
//			+ " undertaking_fc_act_copy, "
//			+ " undertaking_person_name, "
//			+ " undertaking_person_designation, "
//			+ " undertaking_person_company, "
//			+ " undertaking_person_address, "
//			+ " undertaking_person_esign, "
//			+ " undertaking_date "
//			+ " ) from FcFormEUndertaking where fc_form_e_id=?1 ")
//	public FcFormEUndertaking getByFcId(Integer id);

	@Query(" select fc from FcFormEUndertaking fc where fc.fcFormE.id=?1 ")
	public FcFormEUndertaking getByFcId(Integer id);
}
