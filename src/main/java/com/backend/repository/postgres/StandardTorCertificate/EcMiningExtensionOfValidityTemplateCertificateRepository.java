package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcMiningExtensionofValidityTemplateCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcMiningExtensionOfValidityTemplateCertificateRepository extends JpaRepository<EcMiningExtensionofValidityTemplateCertificate,Integer> {


    @Query(value = "select st from EcMiningExtensionofValidityTemplateCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcMiningExtensionofValidityTemplateCertificate getEcMiningExtensionOfValidityTemplateCertificateInfoBytPropId (Integer proponent_id, String proposalNo);
}
