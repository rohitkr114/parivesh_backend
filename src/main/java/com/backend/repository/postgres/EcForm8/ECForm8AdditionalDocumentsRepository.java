package com.backend.repository.postgres.EcForm8;

import com.backend.model.EcForm8TransferOfTOR.ECForm8AdditionalDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ECForm8AdditionalDocumentsRepository extends JpaRepository<ECForm8AdditionalDocuments,Integer> {

}
