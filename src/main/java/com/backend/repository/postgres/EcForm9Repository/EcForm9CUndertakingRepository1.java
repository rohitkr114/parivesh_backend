package com.backend.repository.postgres.EcForm9Repository;

import com.backend.model.EcForm8TransferOfTOR.EcForm8Undertaking;
import com.backend.model.EcForm9TransferOfEC.EcForm9Undertaking1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EcForm9CUndertakingRepository1 extends JpaRepository<EcForm9Undertaking1,Integer> {

    @Query("Select em from EcForm9Undertaking1 em where em.ecForm9TransferOfEC.id=?1")
    EcForm9Undertaking1 getByEc9Id(Integer id);
}
