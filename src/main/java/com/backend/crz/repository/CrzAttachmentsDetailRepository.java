package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzAttachmentsDetail;

@Repository
public interface CrzAttachmentsDetailRepository extends JpaRepository<CrzAttachmentsDetail, Integer> {

	@Query(value = "SELECT * FROM master.crz_attachments_detail u WHERE u.foreign_reference_key = :foreign_reference_key and ref_type = :ref_type ", nativeQuery = true)
	CrzAttachmentsDetail findByFkId(@Param("foreign_reference_key")Integer foreign_reference_key, @Param("ref_type") String ref_type);
	
}