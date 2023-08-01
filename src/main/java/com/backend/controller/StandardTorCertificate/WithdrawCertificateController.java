package com.backend.controller.StandardTorCertificate;

import com.backend.exceptions.PariveshException;
import com.backend.model.StandardTORCertificate.WithdrawCertificate;
import com.backend.repository.postgres.StandardTorCertificate.WithdrawCertificateRepo;
import com.backend.response.ResponseHandler;
import com.backend.service.StandardTORCertificate.StandardTORCertificateService;
import com.backend.service.StandardTORCertificate.WithdrawService;
import com.backend.service.certificate.CertificatesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

/**
 * Withdraw Certificate Controller
 */
@Slf4j
@RestController
@RequestMapping("/withdrawCertificate")
public class WithdrawCertificateController {
    @Autowired
    private WithdrawCertificateRepo withdrawCertificateRepo;

    @Autowired
    private WithdrawService withdrawService;


    @PostMapping("/save")
    public ResponseEntity<Object> saveWithdrawCertificate(@RequestBody WithdrawCertificate withdrawCertificate,
                                                          HttpServletRequest request, HttpServletResponse response) throws PariveshException, IOException {
        log.info("WithdrawCertificateController: saveWithdrawCertificate");
        return ResponseHandler.generateResponse("Save Withdrawal Certificate ", HttpStatus.OK, "",
                withdrawService.saveWithdrawCertificate(withdrawCertificate, request, response));
    }


    /**
     * THis API will  return data for Withdraw Certificate
     *
     * @param proposalNo
     * @return
     */
    @GetMapping("/getByProposalNo")
    public ResponseEntity<Object> getByProposalNo(@RequestParam("proposal_no") String proposalNo) throws PariveshException, ParseException {
        log.info("WithdrawCertificateController::getByProposalNo Proposal No {}", proposalNo);
        return withdrawService.get(proposalNo);
    }


}

