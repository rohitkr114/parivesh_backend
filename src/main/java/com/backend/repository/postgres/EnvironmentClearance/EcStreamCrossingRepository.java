package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcStreamCrossing;

public interface EcStreamCrossingRepository extends JpaRepository<EcStreamCrossing, Integer> {

	@Modifying
	@Query("update EcStreamCrossing set is_deleted='true' , is_active='false' where id=?1")
	Integer updateEcStreamCrossing(Integer ecStreamCrossingId);

}
