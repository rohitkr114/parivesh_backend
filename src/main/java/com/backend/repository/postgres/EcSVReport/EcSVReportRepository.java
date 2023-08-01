package com.backend.repository.postgres.EcSVReport;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcSVReport.EcSiteVisitReport;

public interface EcSVReportRepository extends JpaRepository<EcSiteVisitReport, Integer> {

	@Query(" select ec from EcSiteVisitReport ec where ec.is_active='true' and ec.is_delete='false' and ec.id=?1 ")
	Optional<EcSiteVisitReport> getDetailsById(Integer ecSVRid);

}
