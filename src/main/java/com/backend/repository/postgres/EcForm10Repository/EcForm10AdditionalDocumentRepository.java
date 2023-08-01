package com.backend.repository.postgres.EcForm10Repository;

import com.backend.model.EcForm10NoIncreaseInPL.EcForm10AdditionalDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface EcForm10AdditionalDocumentRepository extends JpaRepository<EcForm10AdditionalDocument,Integer> {

    @Transactional
    @Modifying
    @Query(value="Delete from master.ec_form10_additional_documents docs where docs.id in " +
            "(SELECT docs.id from master.ec_form10_additional_document doc  " +
            "join master.ec_form10_additional_documents  docs on " +
            "doc.id=docs.ec_form10_additional_documents_id\n" +
            "where doc.ec_form_10_document_id=?1)" , nativeQuery = true)
    void deleteAdditionalDocumentsByFormId(Integer ecForm10Id);


    @Query(value = "select ec.id as id ,ec.ec_form_10_document_id as ec_form_10_document_id from master.ec_form10_additional_document ec where ec.ec_form_10_document_id = ?1", nativeQuery = true)
    EcForm10AdditionalDocument findAllByFormId1(Integer id);

    @Transactional
    @Modifying
    @Query(value ="Delete from master.ec_form10_additional_document ec where ec.ec_form_10_document_id=?1" ,nativeQuery = true)
    void deleteAllByFormId(Integer ecForm10Id);
}
