package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcGreenBelt;

public interface EcGreenbeltRepository extends JpaRepository<EcGreenBelt, Integer> {

	@Modifying
	@Query("update EcGreenBelt set is_deleted='true' , is_active='false' where id=?1")
	Integer updateEcGreenBelt(Integer ecGreenBeltId);

	@Query("select ec from EcGreenBelt ec where ec_partb_id=?1")
	public Optional<EcGreenBelt> getRecordExist(Integer id);
}
