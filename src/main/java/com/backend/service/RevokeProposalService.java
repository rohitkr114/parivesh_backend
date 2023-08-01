package com.backend.service;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.RevokeProposal;
import com.backend.repository.postgres.RevokeProposalRepository;
import com.backend.response.ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RevokeProposalService {

    @Autowired
    private RevokeProposalRepository revokeProposalRepository;

    public ResponseEntity<Object> save(RevokeProposal revokeProposal){
        try {
            RevokeProposal response= revokeProposalRepository.save(revokeProposal);

            return ResponseHandler.generateResponse("proposal revoked", HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in revoking proposal",e);
        }
    }

    public ResponseEntity<Object> getRevokedProposalList(UserPrincipal userPrincipal,Boolean isReprocessed){
        try {
            List<RevokeProposal> response= revokeProposalRepository.getList(userPrincipal.getId(),isReprocessed);

            return ResponseHandler.generateResponse("grtting revoked proposal list",HttpStatus.OK,"",response);
        }catch (Exception e){
            log.error("encountered exception",e);
            throw new PariveshException("error in getting revoked proposal list");
        }
    }
}
