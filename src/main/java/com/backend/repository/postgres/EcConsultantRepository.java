package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcConsultant;

public interface EcConsultantRepository extends JpaRepository<EcConsultant, Integer> {

	  
	  @Query("select ec from EcConsultant ec where ec_id=?1")
	  public Optional<EcConsultant> getRecordExist(Integer id);
}
