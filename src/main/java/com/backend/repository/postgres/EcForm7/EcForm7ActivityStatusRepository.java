package com.backend.repository.postgres.EcForm7;

import com.backend.model.EcForm7.EcForm7ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EcForm7ActivityStatusRepository extends JpaRepository<EcForm7ActivityStatus, Integer> {

    @Modifying
    @Query("update EcForm7ActivityStatus set is_delete='true' , is_active='false'  where id=?1")
    Integer delete(Integer id);

    @Query("Select em from EcForm7ActivityStatus em where em.ecForm7.id=?1 and em.is_delete='false'")
    EcForm7ActivityStatus getByEcId(Integer id);

    @Query("select new EcForm7ActivityStatus(id) from EcForm7ActivityStatus where ec_form_7_id=?1")
    EcForm7ActivityStatus getDataByEcId(Integer id);

    @Query(value = " select * from master.ec_form_7_activity_status  where ec_form_7_id = ?1 ", nativeQuery = true)
    EcForm7ActivityStatus getDataByEcFrom7(Integer id);
}
