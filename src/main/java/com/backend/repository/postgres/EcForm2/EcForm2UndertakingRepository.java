package com.backend.repository.postgres.EcForm2;

import com.backend.model.EcForm2.EcForm2ProjectImplementationStatus;
import com.backend.model.EcForm2.EcForm2Undertaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcForm2UndertakingRepository extends JpaRepository<EcForm2Undertaking,Integer> {

    @Query(value = "select * from master.ec_form_2_undertaking ec where ec.ec_form_2_id=:ecForm2Id",nativeQuery = true)
    Optional<EcForm2Undertaking> getByForm2Id(@Param(value = "ecForm2Id") Integer ecForm2Id);

    @Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
    public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
}
