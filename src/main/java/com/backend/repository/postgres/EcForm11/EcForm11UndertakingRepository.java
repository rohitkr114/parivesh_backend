package com.backend.repository.postgres.EcForm11;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm11.EcForm11Undertaking;

public interface EcForm11UndertakingRepository extends JpaRepository<EcForm11Undertaking, Integer> {
	
	@Query(value="select ef.* from master.ec_form_11_undertaking ef where ef.is_active=true and ef.is_deleted=false and ef.ec_form11_id=:ec_form11_id order by id desc limit 1",nativeQuery=true)
	public Optional<EcForm11Undertaking> getDataByForm11Id(@Param(value="ec_form11_id") Integer ecForm11Id);
	
	@Query(value = "select master.get_proposal_identification_no(:proposal_no)", nativeQuery = true)
	public String getIdentificationNo(@Param("proposal_no") String  proposal_no);
}
