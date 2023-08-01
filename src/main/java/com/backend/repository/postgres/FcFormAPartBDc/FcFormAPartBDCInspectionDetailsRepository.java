package com.backend.repository.postgres.FcFormAPartBDc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormAPartBDc.FcFormAPartBDCInspectionDetails;

public interface FcFormAPartBDCInspectionDetailsRepository
		extends JpaRepository<FcFormAPartBDCInspectionDetails, Integer> {

	@Modifying
	@Query("update FcFormAPartBDCInspectionDetails set is_delete='true' , is_active='false' where id=?1")
	Integer updatefcFormAPartBDC(Integer id);

}
