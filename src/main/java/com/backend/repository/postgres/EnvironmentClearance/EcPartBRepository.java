package com.backend.repository.postgres.EnvironmentClearance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EnvironmentClearance.EcPartB;

public interface EcPartBRepository extends JpaRepository<EcPartB, Integer>{

	@Query("Select p from EcPartB p where ec_id=?1 and is_active=true and is_deleted=false ")
	public Optional<EcPartB> getEcPartBbyId(Integer ec_id);

	@Query("Select p from EcPartB p where id=?1 and is_active=true and is_deleted=false ")
	Optional<EcPartB> findByIdActive(Integer ecPartbId);
}
