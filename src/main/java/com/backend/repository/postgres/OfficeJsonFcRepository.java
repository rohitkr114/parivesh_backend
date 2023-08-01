package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.OfficeJsonFc;

public interface OfficeJsonFcRepository extends JpaRepository<OfficeJsonFc, Integer> {

	@Query(value = " SELECT * from ua.office_json_fc(?1); ", nativeQuery = true)
	List<OfficeJsonFc> getOfficeJsonFc(Integer proposalId);

}
