package com.backend.repository.postgres.EcForm12;

import com.backend.model.EcForm12.EcForm12AttachedDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EcForm12AttachedDocumentsRepository extends JpaRepository<EcForm12AttachedDocuments, Integer> {


    @Modifying
    @Query("update EcForm12AttachedDocuments set is_delete='true' , is_active='false'  where id=?1")
    Integer delete(Integer id);

    @Query("Select em from EcForm12AttachedDocuments em where em.ecForm12.id=?1 and em.is_delete='false'")
    EcForm12AttachedDocuments getByEcId(Integer id);

    @Query("select new EcForm12AttachedDocuments(id) from EcForm12AttachedDocuments where ec_form_12_id=?1")
    EcForm12AttachedDocuments getDataByEcId(Integer id);

    @Query(value = " select * from master.ec_form_12_attached_documents  where ec_form_12_id = ?1 ", nativeQuery = true)
    EcForm12AttachedDocuments getDataByEcFrom12(Integer id);
}
