package com.backend.repository.postgres.EcForm7;

import com.backend.model.EcForm7.EcForm7CafKML;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EcForm7CafKMLRepository extends JpaRepository<EcForm7CafKML, Integer> {

    @Query(value = " select * from master.ec_form_7_caf_kml where ec_form_7_id = ?1 ", nativeQuery = true)
    List<EcForm7CafKML> getDataByEcId(Integer ec_id);
}
