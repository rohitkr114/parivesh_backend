package com.backend.repository.postgres.EcFormVPart2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcFormVPart2Model.EcFormVpart2HearingIssues;

public interface EcFormVpart2HearingIssuesRepository extends JpaRepository<EcFormVpart2HearingIssues, Integer> {

//	@Modifying
//	@Query("Update EcFormVpart2HearingIssues set is_deleted='true' where id=?1")
//	public Integer updateEcFormVpart2HearingIssues(Integer id);
}
