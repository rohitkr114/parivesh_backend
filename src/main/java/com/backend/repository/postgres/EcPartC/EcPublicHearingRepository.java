package com.backend.repository.postgres.EcPartC;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcPublicHearing;

public interface EcPublicHearingRepository extends JpaRepository<EcPublicHearing, Integer> {

	@Modifying
	@Query("update EcPublicHearing set is_deleted='true' , is_active='false'  where id=?1")
	Integer updatePublicHearing(Integer id);

	@Query(value = "select * from master.ec_public_hearing ecb where ecb.public_hearing_id=?1 and ecb.is_deleted='false'", nativeQuery = true)
	public Set<EcPublicHearing> getDataByFormId(Integer id);
}
