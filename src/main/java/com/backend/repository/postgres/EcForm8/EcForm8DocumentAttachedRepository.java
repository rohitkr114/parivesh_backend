package com.backend.repository.postgres.EcForm8;

import com.backend.model.EcForm8TransferOfTOR.EcForm8DocumentAttached;
import com.backend.model.EcForm8TransferOfTOR.EcForm8Undertaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm8DocumentAttachedRepository extends JpaRepository<EcForm8DocumentAttached,Integer> {

  @Query("Select em from EcForm8DocumentAttached em where em.ecForm8TransferOfTOR.id=?1")
  EcForm8DocumentAttached getByEc8Id(Integer id);
}
