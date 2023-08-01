package com.backend.repository.postgres.CrzSVReport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CrzSiteVisitCommittee;

public interface CrzSVRCommitteeRepository extends JpaRepository<CrzSiteVisitCommittee, Integer>{

	@Modifying
	@Query("update CrzSiteVisitCommittee set is_deleted='true' , is_active='false'  where id=?1")
	Integer crzSVRCommittee(Integer id);

}
