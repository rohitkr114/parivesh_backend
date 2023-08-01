package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcEnclosures;

public interface FcEnclosuresRepository extends JpaRepository<FcEnclosures, Integer> {

	@Query("select ec from FcEnclosures ec where fc_id=?1")
	public Optional<FcEnclosures> getRecordExist(Integer id);
}
