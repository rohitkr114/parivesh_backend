package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcConstructionDetail;

public interface EcConstructionDetailRepository extends JpaRepository<EcConstructionDetail, Integer> {


	@Query("select ec from EcConstructionDetail ec where ec_partb_id=?1")
	public Optional<EcConstructionDetail> getRecordExist(Integer id);
}
