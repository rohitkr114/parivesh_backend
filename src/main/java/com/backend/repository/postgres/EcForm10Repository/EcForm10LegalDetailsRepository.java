package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10HazardousWasteGeneration;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10LitigationAndViolationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm10LegalDetailsRepository extends JpaRepository<EcForm10LitigationAndViolationDetails,Integer> {



}
