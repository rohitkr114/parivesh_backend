package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10LocationOfProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm10LocationOfProjectRepository extends JpaRepository<EcForm10LocationOfProject,Integer> {

    @Query("Select em from EcForm10LocationOfProject em where em.ecForm10ProjectDetails.id=?1")
    EcForm10LocationOfProject getByEc10Id(Integer id);

}
