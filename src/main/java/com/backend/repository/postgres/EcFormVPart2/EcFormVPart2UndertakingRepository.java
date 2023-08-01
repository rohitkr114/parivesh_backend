package com.backend.repository.postgres.EcFormVPart2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcFormVPart2Model.EcFormVPart2Undertaking;

public interface EcFormVPart2UndertakingRepository extends JpaRepository<EcFormVPart2Undertaking, Integer> {

	@Query("Select ec from EcFormVPart2Undertaking ec where ec.ecFormVPart2.id=?1")
	public Optional<EcFormVPart2Undertaking> getDataByEcId(Integer id);
	
	@Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
	public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
}
