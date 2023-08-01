package com.backend.repository.postgres.ForestClearanceFormC;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.backend.model.ForestClearanceFormC.FcFormCComplianceReport;

@Transactional
public interface FcFormCComplianceReportRepository extends JpaRepository<FcFormCComplianceReport, Integer>{

	@Query("SELECT fc from FcFormCComplianceReport fc where fc.is_delete ='false' and fc.fcFormC.id=?1")
	List<FcFormCComplianceReport> findByFcFormCByID(Integer id);

	@Modifying
	@Query("update  FcFormCComplianceReport set is_delete='true' where id=?1")
	Integer updateFcFormCById(int id);
}
