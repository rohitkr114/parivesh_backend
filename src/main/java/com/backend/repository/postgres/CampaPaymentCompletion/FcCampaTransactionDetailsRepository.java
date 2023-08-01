package com.backend.repository.postgres.CampaPaymentCompletion;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.CampaPaymentCompletion.FcCampaTransactionDetails;

public interface FcCampaTransactionDetailsRepository extends JpaRepository<FcCampaTransactionDetails, Integer>{

	@Query(value="select count(fctd.*) from authority.fc_campa_transaction_details fctd where fctd.transaction_id=:transactionId and fctd.is_active=true and fctd.is_deleted=false",nativeQuery=true)
	public Integer getDetailsByTransactionId(@Param(value="transactionId") String transactionId);
}
