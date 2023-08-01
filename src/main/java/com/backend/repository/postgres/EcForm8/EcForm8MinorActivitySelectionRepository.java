package com.backend.repository.postgres.EcForm8;

import com.backend.model.EcForm8TransferOfTOR.EcForm8Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm8MinorActivitySelectionRepository extends JpaRepository<EcForm8Activities,Integer> {

}
