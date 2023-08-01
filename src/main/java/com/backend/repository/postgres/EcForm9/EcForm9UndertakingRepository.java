package com.backend.repository.postgres.EcForm9;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcForm9.EcForm9Undertaking;

public interface EcForm9UndertakingRepository extends JpaRepository<EcForm9Undertaking, Integer> {

	@Query(" select new EcForm9Undertaking( ecu.id, ecu.i_agree, ecu.undertaking_person_name,"
			+ "			ecu.undertaking_person_designation, ecu.undertaking_person_company, ecu.undertaking_person_address,"
			+ "			ecu.undertaking_person_esign, ecu.undertaking_date, ecu.is_active, ecu.is_delete  ) from EcForm9Undertaking ecu where ecu.ecForm9.id=?1")
	public Optional<EcForm9Undertaking> getDataByEcId(Integer id);
}
