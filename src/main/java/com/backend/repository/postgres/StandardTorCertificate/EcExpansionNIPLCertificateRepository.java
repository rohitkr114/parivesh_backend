package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcExpansionNIPLCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcExpansionNIPLCertificateRepository extends JpaRepository<EcExpansionNIPLCertificate,Integer> {

    @Query(value = "select st from EcExpansionNIPLCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcExpansionNIPLCertificate getEcExpansionNIPLCertificateInfoBytPropId (Integer proponent_id, String proposalNo);
}
