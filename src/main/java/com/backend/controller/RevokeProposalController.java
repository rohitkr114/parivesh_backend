package com.backend.controller;

import com.backend.dto.UserPrincipal;
import com.backend.model.RevokeProposal;
import com.backend.security.CurrentUser;
import com.backend.service.RevokeProposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/revokeProposal")
public class RevokeProposalController {

    @Autowired
    private RevokeProposalService revokeProposalService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody RevokeProposal revokeProposal){
        return revokeProposalService.save(revokeProposal);
    }

    @PostMapping("/getList")
    public ResponseEntity<Object> getRevokedProposalList(@CurrentUser UserPrincipal userPrincipal, @RequestParam Boolean isReprocessed){
        return revokeProposalService.getRevokedProposalList(userPrincipal,isReprocessed);
    }
}
