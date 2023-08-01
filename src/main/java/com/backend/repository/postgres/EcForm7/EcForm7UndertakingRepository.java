package com.backend.repository.postgres.EcForm7;

import com.backend.model.EcForm7.EcForm7Undertaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcForm7UndertakingRepository extends JpaRepository<EcForm7Undertaking, Integer> {

    @Query(" select new EcForm7Undertaking( ecu.id, ecu.i_agree, ecu.undertaking_person_name,"
            + "			ecu.undertaking_person_designation, ecu.undertaking_person_company, ecu.undertaking_person_address,"
            + "			ecu.undertaking_person_esign, ecu.undertaking_date  ) from EcForm7Undertaking ecu where ecu.ecForm7.id=?1")
    public EcForm7Undertaking getDataByEcId(Integer id);

    @Query(" select new EcForm7Undertaking( id, i_agree,"
            + "undertaking_person_name,undertaking_person_designation,"
            + "undertaking_person_company,undertaking_person_address,"
            + "undertaking_person_esign,undertaking_date"
            + ") from EcForm7Undertaking where ecForm7.id=?1 and is_delete='false'")
    public Optional<EcForm7Undertaking> getRecordExist(Integer id);

    @Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
    public String getIdentificationNo(@Param("proposal_no") String proposal_no);

    @Query(value = " select * from master.ec_form7_undertaking  where ec_form_7_id = ?1 ", nativeQuery = true)
    EcForm7Undertaking getDataByEcFrom7(Integer id);
}
