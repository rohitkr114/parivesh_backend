package com.backend.repository.postgres.EcForm12;

import com.backend.model.EcForm12.EcForm12ActivityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EcForm12ActivityStatusRepository extends JpaRepository<EcForm12ActivityStatus, Integer> {

    @Modifying
    @Query("update EcForm12ActivityStatus set is_delete='true' , is_active='false'  where id=?1")
    Integer delete(Integer id);

    @Query("Select em from EcForm12ActivityStatus em where em.ecForm12.id=?1 and em.is_delete='false'")
    EcForm12ActivityStatus getByEcId(Integer id);

    @Query("select new EcForm12ActivityStatus(id) from EcForm12ActivityStatus where ec_form_12_id=?1")
    EcForm12ActivityStatus getDataByEcId(Integer id);

    @Query(value = " select * from master.ec_form_12_activity_status  where ec_form_12_id = ?1 ", nativeQuery = true)
    EcForm12ActivityStatus getDataByEcFrom12(Integer id);
}
