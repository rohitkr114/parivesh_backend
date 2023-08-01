package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcMiningAmendmentCertificate;
import com.backend.model.StandardTORCertificate.EcMiningExpansionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcMiningExpansionTemplateRepository extends JpaRepository<EcMiningExpansionTemplate,Integer> {

    @Query(value = "select st from EcMiningExpansionTemplate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcMiningExpansionTemplate ecMiningExpansionTemplateInfoBytPropId (Integer proponent_id, String proposalNo);

	@Query(value = "select efu.identification_no  from master.ec_form7_undertaking efu left join master.ec_form_7 ef  on efu.ec_form_7_id = ef.id where ef.proposal_no  =  ?1", nativeQuery = true)
	String getIdentificationNo(String proposal_no);
}
