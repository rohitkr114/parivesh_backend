package com.backend.repository.postgres.EcForm12;

import com.backend.model.EcForm12.EcForm12CafKML;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EcForm12CafKMLRepository extends JpaRepository<EcForm12CafKML, Integer> {

    @Query(value = " select * from master.ec_form_12_caf_kml where ec_form_12_id = ?1 ", nativeQuery = true)
    List<EcForm12CafKML> getDataByEcId(Integer ec_id);
}
