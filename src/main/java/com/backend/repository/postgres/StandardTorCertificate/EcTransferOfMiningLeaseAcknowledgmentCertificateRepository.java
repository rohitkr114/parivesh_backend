package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.EcTransferOfMiningLeaseAcknowledgmentCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EcTransferOfMiningLeaseAcknowledgmentCertificateRepository extends JpaRepository<EcTransferOfMiningLeaseAcknowledgmentCertificate,Integer> {

    @Query(value = "select st from EcTransferOfMiningLeaseAcknowledgmentCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    EcTransferOfMiningLeaseAcknowledgmentCertificate getTransferMiningLeaseAcknowledgmentTemplateInfoBytPropId (Integer proponent_id, String proposalNo);

	@Query(value = "select efu.identification_no from master.ec_form9_undertaking_1 efu left join master.ec_form9_transfer_of_ec eftoe  on efu.ec_form9_undertaking_id  = eftoe.id where eftoe.proposal_no = ?1", nativeQuery = true)
	String getIdentificationNo(String proposal_no);
}
