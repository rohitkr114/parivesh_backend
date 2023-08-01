package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10BasicInformation;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10HazardousWasteGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm10HazardousWasteGenerationRepository extends JpaRepository<EcForm10HazardousWasteGeneration,Integer> {

    @Query("select em from EcForm10HazardousWasteGeneration as em where em.ecForm10BasicInformation.id=?1")
    EcForm10HazardousWasteGeneration getByEc10Id(Integer id);

}
