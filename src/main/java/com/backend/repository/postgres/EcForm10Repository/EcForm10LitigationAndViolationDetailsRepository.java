package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10EmissionGeneration;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10LitigationAndViolationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm10LitigationAndViolationDetailsRepository extends JpaRepository<EcForm10LitigationAndViolationDetails,Integer> {

    @Query("select em from EcForm10LitigationAndViolationDetails as em where em.ecForm10ProjectDetails.id=?1")
    EcForm10LitigationAndViolationDetails getByEc10Id(Integer id);

}
