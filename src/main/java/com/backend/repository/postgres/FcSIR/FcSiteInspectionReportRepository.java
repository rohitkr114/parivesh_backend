package com.backend.repository.postgres.FcSIR;

import com.backend.model.FcSIR.FcSiteInspectionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FcSiteInspectionReportRepository extends JpaRepository<FcSiteInspectionReport,Integer> {

    @Query("select fc from FcSiteInspectionReport fc where fc.fcId=:fcId")
    public Optional<FcSiteInspectionReport> getDetailsByFcId(@Param(value = "fcId") Integer fcId);

}
