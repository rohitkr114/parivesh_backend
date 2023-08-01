package com.backend.controller.ProposalWithdraw;

import com.backend.dto.UserPrincipal;
import com.backend.exceptions.PariveshException;
import com.backend.model.ProposalWithdraw.ProposalWithdrawRequest;
import com.backend.response.ResponseHandler;
import com.backend.security.CurrentUser;
import com.backend.service.ProposalWithdraw.ProposalWithdrawRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/proposalWithdrawRequest")
public class ProposalWithdrawRequestController {

    @Autowired
    private ProposalWithdrawRequestService proposalWithdrawRequestService;

    @PostMapping("/save")
    public ResponseEntity<Object> save(@RequestBody ProposalWithdrawRequest proposalWithdrawRequest) throws PariveshException {

        return ResponseHandler.generateResponse("Save ProposalWithdrawRequest", HttpStatus.OK, "", proposalWithdrawRequestService.save(proposalWithdrawRequest));
    }

    @PostMapping("/get")
    public ResponseEntity<Object> get(@CurrentUser UserPrincipal user) throws PariveshException {

        return ResponseHandler.generateResponse("Get ProposalWithdrawRequest", HttpStatus.OK, "", proposalWithdrawRequestService.get(user.getId()));
    }

    @PostMapping("/getByProposalNo")
    public ResponseEntity<Object> getByProposalNo(@RequestParam String proposalNo) throws PariveshException {

        return ResponseHandler.generateResponse("Get ProposalWithdrawRequest", HttpStatus.OK, "", proposalWithdrawRequestService.getByProposalNo(proposalNo));
    }
}
