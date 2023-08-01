package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcPhysicalChanges;

public interface EcPhysicalChangesRepository extends JpaRepository<EcPhysicalChanges, Integer> {

	@Modifying
	@Query("update EcPhysicalChanges set is_deleted='true'  where id=?1")
	Integer updateEcPhysicalChanges(Integer id);

	@Query("select ec from EcPhysicalChanges ec where ec_partb_id=?1")
	public Optional<EcPhysicalChanges> getRecordExist(Integer id);

}
