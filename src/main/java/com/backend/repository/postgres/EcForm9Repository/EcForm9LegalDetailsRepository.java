package com.backend.repository.postgres.EcForm9Repository;

import com.backend.model.EcForm8TransferOfTOR.ECForm8TransferCOP;
import com.backend.model.EcForm9TransferOfEC.EcForm9LegalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm9LegalDetailsRepository extends JpaRepository<EcForm9LegalDetails,Integer> {

    @Query("select em from EcForm9LegalDetails as em where em.ecForm9TransferOfEC.id=?1")
    EcForm9LegalDetails getByEc9Id(Integer id);
}
