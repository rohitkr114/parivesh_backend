package com.backend.repository.postgres.ProposalWithdraw;

import com.backend.model.ProposalWithdraw.ProposalWithdrawRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProposalWithdrawRequestRepository extends JpaRepository<ProposalWithdrawRequest, Integer> {

    @Query(value = " select * from master.proposal_withdraw_request where created_by = ?1 and is_active = true and is_delete = false order by created_on desc ", nativeQuery = true)
    List<ProposalWithdrawRequest> get(Integer id);

    @Query(value = " select * from master.proposal_withdraw_request where proposal_no  = ?1 and is_active = true and is_delete = false order by created_on desc ", nativeQuery = true)
    List<ProposalWithdrawRequest> getByProposalNo(String proposalNo);
}
