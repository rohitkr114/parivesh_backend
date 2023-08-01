package com.backend.controller;

import com.backend.dto.RemovedLogsRequest;
import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.RemovedProposals;
import com.backend.security.CurrentUser;
import com.backend.service.RemovedProposalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/removedProposals")
public class RemovedProposalsController {

    @Autowired
    private RemovedProposalsService removedProposalsService;
    @PostMapping("/remove")
    public ResponseEntity<Object> removeProposal(@RequestBody RemovedProposals proposals, @CurrentUser UserPrincipal principal) throws PariveshException{
        return removedProposalsService.removeProposal(proposals, principal);
    }

    @PostMapping("/getProposalForRemoval")
    public ResponseEntity<Object> getProposalForRemoval(@CurrentUser UserPrincipal principal) throws PariveshException{
        return removedProposalsService.getProposalForRemoval(principal);
    }

    @PostMapping("/getList")
    public ResponseEntity<Object> getDeletedProposals(@RequestBody RemovedLogsRequest request, @CurrentUser UserPrincipal principal) throws PariveshException{
        return removedProposalsService.getDeletedProposals(request,principal);
    }
}
