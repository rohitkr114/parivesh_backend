package com.backend.repository.postgres.EcForm8;

import com.backend.model.EcForm8TransferOfTOR.EcForm8DetailOfTOR;
import com.backend.model.EcForm8TransferOfTOR.EcForm8DocumentAttached;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm8DetailsOfTORRepository extends JpaRepository<EcForm8DetailOfTOR,Integer> {

  @Query("Select em from EcForm8DetailOfTOR em where em.ecForm8TransferOfTOR.id=?1")
  EcForm8DetailOfTOR getByEc8Id(Integer id);
}
