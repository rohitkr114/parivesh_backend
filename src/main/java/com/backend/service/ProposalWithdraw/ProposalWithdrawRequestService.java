package com.backend.service.ProposalWithdraw;

import com.backend.model.ProposalWithdraw.ProposalWithdrawRequest;
import com.backend.repository.postgres.ProposalWithdraw.ProposalWithdrawRequestRepository;
import com.backend.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProposalWithdrawRequestService {

    @Autowired
    private ProposalWithdrawRequestRepository proposalWithdrawRequestRepository;

    public ProposalWithdrawRequest save(ProposalWithdrawRequest proposalWithdrawRequest) {

        return proposalWithdrawRequestRepository.save(proposalWithdrawRequest);
    }

    public List<ProposalWithdrawRequest> get(Integer id) {

        return proposalWithdrawRequestRepository.get(id);
    }

    public List<ProposalWithdrawRequest> getByProposalNo(String proposalNo) {

        return proposalWithdrawRequestRepository.getByProposalNo(proposalNo);
    }
}
