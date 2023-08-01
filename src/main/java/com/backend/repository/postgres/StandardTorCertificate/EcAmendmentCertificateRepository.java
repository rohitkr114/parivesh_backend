package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcAmendmentCertificate;
import com.backend.model.StandardTORCertificate.EcExpansionNIPLCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcAmendmentCertificateRepository extends JpaRepository<EcAmendmentCertificate,Integer> {

    @Query(value = "select st from EcAmendmentCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcAmendmentCertificate ecAmendmentCertificateInfoBytPropId (Integer proponent_id, String proposalNo);
    
    @Query(value = "select epod.identification_no from master.ec_partc_other_details epod left join master.ec_partc ep on ep.id = epod.ec_id  where ep.proposal_no = ?1", nativeQuery = true)
    String getIdentificationNo(String proposalNo);
}

