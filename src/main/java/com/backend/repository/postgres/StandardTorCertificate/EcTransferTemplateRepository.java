package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcMiningFreshLetterCertificate;
import com.backend.model.StandardTORCertificate.EcTransferTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcTransferTemplateRepository extends JpaRepository<EcTransferTemplate,Integer> {

    @Query(value = "select st from EcTransferTemplate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcTransferTemplate getEcTransferTemplateInfoBytPropId (Integer proponent_id, String proposalNo);
     
	//@Query(value = "select efu.identification_no  from master.ec_form7_undertaking efu where efu.ec_form_7_id = (select ef.id  from master.ec_form_7 ef where ef.proposal_no = ?1", nativeQuery = true)
    //String getIdentificationNo(String proposalNo);

	@Query(value = "select efu.identification_no  from master.ec_form7_undertaking efu left join master.ec_form_7 ef  on efu.ec_form_7_id = ef.id where ef.proposal_no  =  ?1", nativeQuery = true)
	String getIdentificationNo(String proposal_no);

}
