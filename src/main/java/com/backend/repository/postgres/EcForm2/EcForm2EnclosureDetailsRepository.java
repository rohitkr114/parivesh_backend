package com.backend.repository.postgres.EcForm2;

import com.backend.model.EcForm2.EcForm2EnclosureDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcForm2EnclosureDetailsRepository extends JpaRepository<EcForm2EnclosureDetails, Integer> {

    @Query(value = "select * from master.ec_form_2_enclosure_details ec where ec.ec_form_2_id=:ecForm2Id",nativeQuery = true)
    Optional<EcForm2EnclosureDetails> getByForm2Id(@Param(value = "ecForm2Id") Integer ecForm2Id);
}
