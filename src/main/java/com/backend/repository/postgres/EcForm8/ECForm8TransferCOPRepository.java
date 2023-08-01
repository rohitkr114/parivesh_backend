package com.backend.repository.postgres.EcForm8;

import com.backend.model.EcForm8TransferOfTOR.ECForm8TransferCOP;
import com.backend.model.EcForm8TransferOfTOR.EcForm8DocumentAttached;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ECForm8TransferCOPRepository extends JpaRepository<ECForm8TransferCOP,Integer> {

  @Query("Select em from ECForm8TransferCOP em where em.ecForm8TransferOfTOR.id=?1")
  ECForm8TransferCOP getByEc8Id(Integer id);
}
