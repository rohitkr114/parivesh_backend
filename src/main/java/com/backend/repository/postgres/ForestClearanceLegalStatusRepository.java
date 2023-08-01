package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ForestClearanceLegalStatus;

public interface ForestClearanceLegalStatusRepository extends JpaRepository<ForestClearanceLegalStatus, Integer> {

	@Modifying
	@Query("update ForestClearanceLegalStatus set is_deleted='true' where id=?1")
	Integer updateFcLegalStatusById(Integer id);

}
