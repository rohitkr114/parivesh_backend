package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10ProductDetails;
import com.backend.model.EcForm10NoIncreaseInPL.EcForm10Undertaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm10UndertakingRepository extends JpaRepository<EcForm10Undertaking,Integer> {

    @Query("select em from EcForm10Undertaking as em where em.ecForm10BasicInformation.id=?1")
    EcForm10Undertaking getByEc10Id(Integer id);
    
	@Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
	public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
}
