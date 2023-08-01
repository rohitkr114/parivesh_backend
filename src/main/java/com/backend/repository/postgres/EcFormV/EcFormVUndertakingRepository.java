package com.backend.repository.postgres.EcFormV;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcFormVModel.EcFormVUndertaking;

public interface EcFormVUndertakingRepository extends JpaRepository<EcFormVUndertaking, Integer> {

	@Query(" select new EcFormVUndertaking( ecu.id, ecu.i_agree, ecu.undertaking_person_name,"
			+ " ecu.undertaking_person_designation, ecu.undertaking_person_company, ecu.undertaking_person_address,"
			+ " ecu.undertaking_person_esign, ecu.undertaking_date, ecu.is_active, ecu.is_delete  ) from EcFormVUndertaking ecu where ecu.ecFormV.id=?1")
	public Optional<EcFormVUndertaking> getDataByEcId(Integer id);
	
	@Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
	public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
}
