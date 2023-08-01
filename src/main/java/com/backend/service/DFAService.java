package com.backend.service;

import com.backend.repository.postgres.StandardTorCertificate.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DFAService {

    @Autowired
    SpecificTorCertificateRepository specificTorCertificateRepository;

    @Autowired
    EcFreshLetterTemplateCertificateRepository ecFreshLetterTemplateCertificateRepository;

    @Autowired
    EcTorAmendmentCertificateRepository ecTorAmendmentCertificateRepository;

    @Autowired
    EcAmendmentCertificateRepository ecAmendmentCertificateRepository;

    @Autowired
    EcExtensionOfValidityCertificateRepository ecExtensionOfValidityCertificateRepository;

    @Autowired
    EcTransferTemplateCertificateRepository ecTransferTemplateCertificateRepository;

    @Autowired
    EcTorTransferTemplateRepository ecTorTransferTemplateRepository;


    public Object getDFAByTypeById(int certId, int clearenceId, String type) {

        //TOR Certificate
        if (clearenceId == 5) {
            return specificTorCertificateRepository.findById(certId);
        }
        //Amendment of ToR Certificate
        else if (clearenceId == 36) {
            return ecTorAmendmentCertificateRepository.findById(certId);
        }
        //Fresh EC Certificate
        else if (clearenceId == 2) {
            return ecFreshLetterTemplateCertificateRepository.findById(certId);
        }
        // Amendment of EC Certificate
        else if (clearenceId == 37) {
            return ecAmendmentCertificateRepository.findById(certId);
        }
        // Form 6
        else if (clearenceId == 65) {
            return ecExtensionOfValidityCertificateRepository.findById(certId);
        }// Form 7
        else if (clearenceId == 38) {
            return ecTransferTemplateCertificateRepository.findById(certId);
        }
        //  Form 8
        else if (clearenceId == 39) {
            return ecTorTransferTemplateRepository.findById(certId);
        } else {
            return null;
        }
    }

    public Object getDFAVersions(Integer proposalId, int clearenceId, String type) {
        //TOR Certificate
        if (clearenceId == 5) {
            return specificTorCertificateRepository.getByProposalIdForTor(proposalId);
        }
        //Amendment of ToR Certificate
        else if (clearenceId == 36) {
            return specificTorCertificateRepository.getByProposalIdForAmdTor(proposalId);
        }
        //Fresh EC Certificate
        else if (clearenceId == 2) {
            return specificTorCertificateRepository.getByProposalIdForEC(proposalId);
        }
        // Amendment of EC Certificate
        else if (clearenceId == 37) {
            return specificTorCertificateRepository.getByProposalIdForAmdEC(proposalId);
        }
        // Form 6
        else if (clearenceId == 65) {
            return specificTorCertificateRepository.getByProposalIdForExtEC(proposalId);
        }// Form 7
        else if (clearenceId == 38 && type.equals("DFA_MIN")) {
            return specificTorCertificateRepository.getByProposalIdForTrnEC(proposalId);
        }
        else if (clearenceId == 38 && type.equals("DFA")) {
            return specificTorCertificateRepository.getByProposalIdForTrnECMin(proposalId);
        }
        //  Form 8
        else if (clearenceId == 39) {
            return specificTorCertificateRepository.getByProposalIdForTrnTor(proposalId);
        } else {
            return null;
        }
    }
}
