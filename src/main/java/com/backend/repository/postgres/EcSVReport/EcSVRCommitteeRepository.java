package com.backend.repository.postgres.EcSVReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcSVReport.EcSiteVisitCommittee;

public interface EcSVRCommitteeRepository extends JpaRepository<EcSiteVisitCommittee, Integer> {

	@Modifying
	@Query("update EcSiteVisitCommittee set is_deleted='true' , is_active='false'  where id=?1")
	Integer ecSVRCommittee(Integer id);

}
