package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcTorAmendmentCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcTorAmendmentCertificateRepository extends JpaRepository<EcTorAmendmentCertificate,Integer> {

    @Query(value = "select st from EcTorAmendmentCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcTorAmendmentCertificate getEcTorAmendmentCertificateInfoBytPropId (Integer proponent_id, String proposalNo);
}
