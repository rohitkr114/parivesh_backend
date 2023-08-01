package com.backend.repository.postgres.EcForm8;

import com.backend.model.EcForm8TransferOfTOR.EcForm8Activities;
import com.backend.model.EcForm8TransferOfTOR.EcForm8LocationOfProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm8LocationOfProjectRepository extends JpaRepository<EcForm8LocationOfProject,Integer> {

  @Query("Select em from EcForm8LocationOfProject em where em.ecForm8TransferOfTOR.id=?1")
  EcForm8LocationOfProject getByEc8Id(Integer id);
}
