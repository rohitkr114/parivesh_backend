package com.backend.repository.postgres.EnvironmentClearance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcResource;

public interface EcResourceRepository extends JpaRepository<EcResource, Integer> {

	@Modifying
	@Query("update EcResource set is_deleted='true' , is_active='false' where id=?1")
	Integer updateEcResouce(Integer ecGreenBeltId);

}
