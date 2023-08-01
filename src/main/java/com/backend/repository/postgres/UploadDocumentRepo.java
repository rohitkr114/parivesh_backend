//package com.backend.repository.postgres;
//
//import java.util.Optional;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.backend.model.UploadDocument;
//
//
//public interface UploadDocumentRepo extends JpaRepository<UploadDocument, Long> {
//
//	@Query("select uploadDocument from UploadDocument uploadDocument where uploadDocument.uuid=:uuid ")
//	public Optional<UploadDocument> findByUUID(String uuid);
//
//}
