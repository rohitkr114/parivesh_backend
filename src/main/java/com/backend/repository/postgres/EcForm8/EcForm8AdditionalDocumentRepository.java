package com.backend.repository.postgres.EcForm8;

import com.backend.model.EcForm8TransferOfTOR.EcForm8AdditionalDocument;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcForm8AdditionalDocumentRepository extends JpaRepository<EcForm8AdditionalDocument,Integer> {

  @Transactional
  @Modifying
  @Query(value="Delete from master.ec_form8_additional_documents docs where docs.id in " +
          "(SELECT docs.id from master.ec_form8_additional_document doc  " +
          "join master.ec_form8_additional_documents  docs on " +
          "doc.id=docs.ec_form8_additional_documents_id\n" +
          "where doc.ec_form_8_document_id=?1)" , nativeQuery = true)
  void deleteAdditionalDocumentsByFormId(Integer ecForm8Id);




  @Query(value = "select ec.id as id ,ec.ec_form_8_document_id as ec_form_8_document_id from master.ec_form8_additional_document ec where ec.ec_form_8_document_id = ?1", nativeQuery = true)
  EcForm8AdditionalDocument findAllByFormId1(Integer id);

  @Transactional
  @Modifying
  @Query(value ="Delete from master.ec_form8_additional_document ec where ec.ec_form_8_document_id=?1" ,nativeQuery = true)
  void deleteAllByFormId(Integer ecForm8Id);

}
