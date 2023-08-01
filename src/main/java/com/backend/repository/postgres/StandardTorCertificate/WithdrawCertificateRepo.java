package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.model.StandardTORCertificate.WithdrawCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface WithdrawCertificateRepo extends JpaRepository<WithdrawCertificate, Integer> {
    @Query(value = "select * from master.withdraw_certificate where proposal_no =?1 order by id desc limit 1",nativeQuery = true)
    WithdrawCertificate getByProposalNo(String proposalNo);

    @Query(value = "select pwr.created_on as withdraw_request_date from master.proposal_withdraw_request pwr where proposal_no = ?1 order by id desc limit 1",nativeQuery = true)
    String getWithdrawData(String proposalNo);
}
