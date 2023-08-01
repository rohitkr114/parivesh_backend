package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcExtensionOfValidityCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcExtensionOfValidityCertificateRepository extends JpaRepository<EcExtensionOfValidityCertificate,Integer> {


    @Query(value = "select st from EcExtensionOfValidityCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcExtensionOfValidityCertificate getEcExtensionOfValidityCertificateInfoBytPropId (Integer proponent_id, String proposalNo);
    
    @Query(value = "select efuv.identification_no from master.ec_form_6_undertaking_v2 efuv left join master.ec_form_6_v2 efv on efv.id = efuv.ec_form6_id where efv.proposal_no = ?1", nativeQuery = true)
    String getIdentificationNo(String proposalNo);

}
