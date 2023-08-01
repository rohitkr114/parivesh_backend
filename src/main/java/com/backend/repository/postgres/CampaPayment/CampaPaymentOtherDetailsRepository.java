package com.backend.repository.postgres.CampaPayment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CampaPayment.CampaPaymentOtherDetails;
import org.springframework.data.repository.query.Param;

public interface CampaPaymentOtherDetailsRepository extends JpaRepository<CampaPaymentOtherDetails,Integer> {
	
	@Query("select cm from CampaPaymentOtherDetails as cm where cm.campaPaymentDetails.id=?1 ")
	public CampaPaymentOtherDetails getDatabyCampaPaymentId(Integer campa_payment_id);

//	@Modifying
//	@Query(value = "update bkepay.demand d set status ='Pending' where d.application_id = (select pa.id from master.proponent_applications pa where pa.proposal_id=:proposalId)",nativeQuery = true)
//	Integer updateDemandStatus(@Param(value = "proposalId") Integer proposalId);
}
