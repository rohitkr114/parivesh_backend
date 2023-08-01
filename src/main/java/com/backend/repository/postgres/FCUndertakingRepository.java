package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FCUndertaking;

public interface FCUndertakingRepository extends JpaRepository<FCUndertaking, Integer> {

	@Query("select ec from FCUndertaking ec where fc_id=?1")
	public Optional<FCUndertaking> getRecordExist(Integer id);

}
