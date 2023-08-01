package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10LitigationAndViolationDetails;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm10ProductDetailRepository extends JpaRepository<EcForm10ProductDetails,Integer> {

    @Query("select em from EcForm10ProductDetails as em where em.ecForm10BasicInformation.id=?1")
    EcForm10ProductDetails getByEc10Id(Integer id);


}
