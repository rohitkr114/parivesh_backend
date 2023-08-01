package com.backend.repository.postgres.EcForm9Repository;

import com.backend.model.EcForm8TransferOfTOR.EcForm8LocationOfProject;
import com.backend.model.EcForm9TransferOfEC.EcForm9LocationOfProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm9LocationOfProjectRepository extends JpaRepository<EcForm9LocationOfProject,Integer> {

    @Query("Select em from EcForm9LocationOfProject em where em.ecForm9TransferOfEC.id=?1")
    EcForm9LocationOfProject getByEc9Id(Integer id);
}
