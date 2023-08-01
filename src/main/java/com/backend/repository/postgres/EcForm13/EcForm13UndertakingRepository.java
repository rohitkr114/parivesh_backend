package com.backend.repository.postgres.EcForm13;

import com.backend.model.EcForm13.EcForm13Undertaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcForm13UndertakingRepository extends JpaRepository<EcForm13Undertaking,Integer> {

    @Query(value = "select ec.* from master.ec_form13_undertaking ec where ec.ec_form13_id=:id and ec.is_active=true and ec.is_deleted=false",nativeQuery = true)
    public Optional<EcForm13Undertaking> getDataByForm13Id(@Param(value="id") Integer ecForm13Id);

    @Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
    public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
}
