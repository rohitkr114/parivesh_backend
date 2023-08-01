package com.backend.repository.postgres.EcForm7;

import com.backend.model.EcForm7.EcForm7AttachedDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface EcForm7AttachedDocumentsRepository extends JpaRepository<EcForm7AttachedDocuments, Integer> {


    @Modifying
    @Query("update EcForm7AttachedDocuments set is_delete='true' , is_active='false'  where id=?1")
    Integer delete(Integer id);

    @Query("Select em from EcForm7AttachedDocuments em where em.ecForm7.id=?1 and em.is_delete='false'")
    EcForm7AttachedDocuments getByEcId(Integer id);

    @Query("select new EcForm7AttachedDocuments(id) from EcForm7AttachedDocuments where ec_form_7_id=?1")
    EcForm7AttachedDocuments getDataByEcId(Integer id);

    @Query(value = " select * from master.ec_form_7_attached_documents  where ec_form_7_id = ?1 ", nativeQuery = true)
    EcForm7AttachedDocuments getDataByEcFrom7(Integer id);
}
