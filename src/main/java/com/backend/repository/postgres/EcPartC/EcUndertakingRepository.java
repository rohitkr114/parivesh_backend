package com.backend.repository.postgres.EcPartC;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcUndertaking;
import org.springframework.data.repository.query.Param;

public interface EcUndertakingRepository extends JpaRepository<EcUndertaking, Integer> {

	@Query("select new EcUndertaking(ecu.id, ecu.is_undertaking_checked, ecu.name, ecu.designation, ecu.company,"
			+ "	ecu.address, ecu.esign, ecu.date) from EcUndertaking ecu where ecu.ecPartC.id=?1")
	public Optional<EcUndertaking> getDataByEcId(Integer id);

	@Modifying
	@Query(value = " update master.proponent_applications set last_status=:staus where proposal_id =:proposalId ",
			nativeQuery =
			true)
	Integer updateLastStatus(@Param("proposalId") Integer proposalId, @Param("staus") String staus);

}
