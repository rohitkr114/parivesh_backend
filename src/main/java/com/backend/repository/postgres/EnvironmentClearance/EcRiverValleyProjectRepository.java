package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcRiverValleyProject;

public interface EcRiverValleyProjectRepository extends JpaRepository<EcRiverValleyProject, Integer> {

	@Modifying
	@Query("update EcRiverValleyProject set is_deleted='true' where id=?1")
	Integer updateEcRiverValleyProject(Integer id);

	@Query("select ec from EcRiverValleyProject ec where ec_partb_id=?1")
	public Optional<EcRiverValleyProject> getRecordExist(Integer id);
}
