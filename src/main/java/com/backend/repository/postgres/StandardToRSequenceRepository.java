package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.StandardToRSequence;

public interface StandardToRSequenceRepository extends JpaRepository<StandardToRSequence, Integer> {
	
	@Query("select sts from StandardToRSequence sts where is_active=true and is_deleted=false and activity_id=:activity_id")
	public StandardToRSequence getStandardToRSequenceByActivityId(@Param(value="activity_id") Integer activity_id);
}
