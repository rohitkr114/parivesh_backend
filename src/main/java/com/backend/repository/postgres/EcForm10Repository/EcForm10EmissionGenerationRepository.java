package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10BasicInformation;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10EmissionGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EcForm10EmissionGenerationRepository extends JpaRepository<EcForm10EmissionGeneration,Integer> {


    @Query("select em from EcForm10EmissionGeneration as em where em.ecForm10BasicInformation.id=?1")
    EcForm10EmissionGeneration getByEc10Id(Integer id);



}
