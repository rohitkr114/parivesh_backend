package com.backend.repository.postgres.CampaPaymentCompletion;

import com.backend.dto.PaymentEmailDto;
import com.backend.dto.PaymentNotificationDto;
import com.backend.model.CampaPaymentCompletion.FcCampaPaymentProposalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FcCampaPaymentProposalDetailsRepository extends JpaRepository<FcCampaPaymentProposalDetails, Integer> {


    @Query("select cm from FcCampaPaymentProposalDetails as cm where cm.fcCampaPaymentCompletionDetails.id=?1 ")
    public FcCampaPaymentProposalDetails getDatabyCampaPaymentId(Integer id);

    @Query(value = " select fctd.created_by ,fctd.transaction_id, fctd.transaction_date,cast (fctd.amount as varchar), fctd.bank_name, fctd.branch_name, fctd.account_name, fctd.payment_mode, pa.proposal_no  " +
            " from authority.fc_campa_payment_completion_details fcpcd " +
            " left join authority.fc_campa_transaction_details fctd on fctd.fc_campa_payment_completion_id = fcpcd.id " +
            " left join master.proponent_applications pa on pa.proposal_id = fcpcd.fc_id " +
            " where fcpcd.id = ?1 ", nativeQuery = true)
    List<PaymentNotificationDto> getPaymentNotificationDetails(Integer id);

    @Query(value = " select ue.emailid, ue.mobilenumber from authentication.role r " +
            " left join ua.user_access_mapping uam on uam.role_id = r.entityid " +
            " left join authentication.user_entity ue on ue.entityid = uam.user_id " +
            " where r.abbreviation in ('CAMPA','BANK') or ue.entityid = ?1 ", nativeQuery = true)
    List<PaymentEmailDto> getPaymentEmailDetails(Integer created_by);
}
