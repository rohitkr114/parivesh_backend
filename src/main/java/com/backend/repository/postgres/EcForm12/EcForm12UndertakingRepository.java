package com.backend.repository.postgres.EcForm12;

import com.backend.model.EcForm12.EcForm12Undertaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcForm12UndertakingRepository extends JpaRepository<EcForm12Undertaking, Integer> {

    @Query(" select new EcForm12Undertaking( ecu.id, ecu.i_agree, ecu.undertaking_person_name,"
            + "			ecu.undertaking_person_designation, ecu.undertaking_person_company, ecu.undertaking_person_address,"
            + "			ecu.undertaking_person_esign, ecu.undertaking_date  ) from EcForm12Undertaking ecu where ecu.ecForm12.id=?1")
    public EcForm12Undertaking getDataByEcId(Integer id);

    @Query(" select new EcForm12Undertaking( id, i_agree,"
            + "undertaking_person_name,undertaking_person_designation,"
            + "undertaking_person_company,undertaking_person_address,"
            + "undertaking_person_esign,undertaking_date"
            + ") from EcForm12Undertaking where ecForm12.id=?1 and is_delete='false'")
    public Optional<EcForm12Undertaking> getRecordExist(Integer id);

    @Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
    public String getIdentificationNo(@Param("proposal_no") String proposal_no);

    @Query(value = " select * from master.ec_form12_undertaking  where ec_form_12_id = ?1 ", nativeQuery = true)
    EcForm12Undertaking getDataByEcFrom12(Integer id);
}
