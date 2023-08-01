package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.ApplicationAmendmentLogs;

public interface ApplicationAmendmentLogsRepository extends JpaRepository<ApplicationAmendmentLogs, Integer> {
	
	@Query("select al from ApplicationAmendmentLogs al where al.is_active=true and al.is_deleted= false and (al.ref_id=:ref_id and al.ref_type=:ref_type)")
	public List<ApplicationAmendmentLogs> getLogs(@Param(value="ref_id") Integer ref_id, @Param(value="ref_type") String ref_type);
	
	@Query("select al from ApplicationAmendmentLogs al where al.is_active=true and al.is_deleted= false and al.ref_id=:ref_id ")
	public List<ApplicationAmendmentLogs> getLogs(@Param(value="ref_id") Integer ref_id);
	
	
}
