package com.backend.repository.postgres.EcForm11;

import com.backend.model.EcForm11.EcForm11OtherDetails;
import com.backend.model.EcForm11.EcForm11Undertaking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EcForm11OtherDetailsRepository extends JpaRepository<EcForm11OtherDetails,Integer> {

    @Query(value="select ef.* from master.ec_form_11_other_details ef where ef.is_active=true and ef.is_deleted=false and ef.ec_form11_id=:ec_form11_id order by id desc limit 1",nativeQuery=true)
    public Optional<EcForm11OtherDetails> getDataByForm11Id(@Param(value="ec_form11_id") Integer ecForm11Id);
}
