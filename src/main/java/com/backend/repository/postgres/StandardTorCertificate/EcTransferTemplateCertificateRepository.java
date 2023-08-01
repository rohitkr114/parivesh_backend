package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcTransferTemplateCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcTransferTemplateCertificateRepository extends JpaRepository<EcTransferTemplateCertificate,Integer> {

    @Query("select st from EcTransferTemplate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcTransferTemplateCertificate getEcTransferTemplateInfoBytPropId (Integer proponent_id, String proposalNo);

}
