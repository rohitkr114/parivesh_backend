package com.backend.repository.postgres.CampaPaymentCompletion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CampaPaymentCompletion.FcCampaPaymentBankDetails;

public interface FcCampaPaymentBankDetailsRepository extends JpaRepository<FcCampaPaymentBankDetails, Integer>{

	@Query("select cm from FcCampaPaymentBankDetails as cm where cm.fcCampaPaymentCompletionDetails.id=?1 ")
	public FcCampaPaymentBankDetails getDatabyCampaPaymentId(Integer id);
}
