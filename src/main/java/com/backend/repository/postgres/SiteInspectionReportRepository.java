package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.SiteInspectionReport;

public interface SiteInspectionReportRepository extends JpaRepository<SiteInspectionReport, Integer>{

}
