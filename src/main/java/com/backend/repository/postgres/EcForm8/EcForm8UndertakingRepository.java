package com.backend.repository.postgres.EcForm8;

import com.backend.model.EcForm7.EcForm7AttachedDocuments;
import com.backend.model.EcForm8TransferOfTOR.EcForm8Undertaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm8UndertakingRepository extends JpaRepository<EcForm8Undertaking,Integer> {

  @Query("Select em from EcForm8Undertaking em where em.ecForm8TransferOfTOR.id=?1")
  EcForm8Undertaking getByEc8Id(Integer id);
}