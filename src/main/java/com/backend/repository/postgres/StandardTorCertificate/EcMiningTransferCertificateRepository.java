package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcMiningExtensionofValidityTemplateCertificate;
import com.backend.model.StandardTORCertificate.EcMiningTransferCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcMiningTransferCertificateRepository  extends JpaRepository<EcMiningTransferCertificate,Integer> {

    @Query(value = "select st from EcMiningTransferCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcMiningTransferCertificate getEcMiningTransferCertificateInfoBytPropId (Integer proponent_id, String proposalNo);

}
