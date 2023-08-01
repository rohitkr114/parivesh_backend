package com.backend.repository.postgres.EcPartC;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcMajorIssuesRaised;

public interface EcMajorIssuesRepository extends JpaRepository<EcMajorIssuesRaised, Integer> {

	@Modifying
	@Query("update EcMajorIssuesRaised set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateMajorIssues(Integer id);

	@Query(value = "select * from master.ec_major_issues ecb where ecb.major_issues_id=?1 and ecb.is_deleted='false'", nativeQuery = true)
	public Set<EcMajorIssuesRaised> getDataByFormId(Integer id);
}
