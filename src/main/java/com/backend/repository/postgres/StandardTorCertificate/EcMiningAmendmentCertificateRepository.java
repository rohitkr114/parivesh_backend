package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcExtensionOfValidityCertificate;
import com.backend.model.StandardTORCertificate.EcMiningAmendmentCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcMiningAmendmentCertificateRepository extends JpaRepository<EcMiningAmendmentCertificate,Integer> {

    @Query(value = "select st from EcMiningAmendmentCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcMiningAmendmentCertificate ecMiningAmendmentCertificateInfoBytPropId (Integer proponent_id, String proposalNo);


}
