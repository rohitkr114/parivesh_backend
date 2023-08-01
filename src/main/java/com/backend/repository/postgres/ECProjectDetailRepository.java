package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcProjectDetail;

public interface ECProjectDetailRepository extends JpaRepository<EcProjectDetail, Integer> {

	@Query("select ec from EcProjectDetail ec where ec_id=?1")
	public Optional<EcProjectDetail> getRecordExist(Integer id);
}
