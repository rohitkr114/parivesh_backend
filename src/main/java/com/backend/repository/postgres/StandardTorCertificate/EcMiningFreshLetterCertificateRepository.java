package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcMiningFreshLetterCertificate;
import com.backend.model.StandardTORCertificate.EcTorAmendmentCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcMiningFreshLetterCertificateRepository extends JpaRepository<EcMiningFreshLetterCertificate,Integer> {

    @Query(value = "select st from EcMiningFreshLetterCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcMiningFreshLetterCertificate getEcMiningFreshLetterCertificateInfoBytPropId (Integer proponent_id, String proposalNo);


    @Query(value = "select epod.identification_no  from master.ec_partc_other_details epod left join master.ec_partc ep on epod.ec_id = ep.id where ep.proposal_no = ?1", nativeQuery = true)
	String getIdentificationNo(String proposal_no);

}
