package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcSectorFormDetails;

public interface EcSectorFormDetailsRepository extends JpaRepository<EcSectorFormDetails, Integer> {

	@Modifying
	@Query("update EcSectorFormDetails set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateSectorForm(Integer id);

}
