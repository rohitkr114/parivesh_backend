package com.backend.repository.postgres.EcForm13;

import com.backend.model.EcForm13.EcForm13ConsultantDetails;
import com.backend.model.EcForm13.EcForm13ProjectSpecificDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcForm13ConsultantDetailsRepository extends JpaRepository<EcForm13ConsultantDetails,Integer> {

    @Query(value="select ec.* from master.ec_form13_consultant_details ec where ec.ec_form13_id=:id and ec.is_active=true and ec.is_deleted=false",nativeQuery = true)
    public Optional<EcForm13ConsultantDetails> getDataByForm13Id(@Param(value="id") Integer ecForm13Id);
}
