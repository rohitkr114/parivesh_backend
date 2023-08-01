package com.backend.service;

import com.backend.repository.postgres.FCStateCertificateHeaderRepository;
import com.backend.response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.backend.model.FCStateCertificateHeader;
import org.springframework.stereotype.Service;

@Service
public class FCStateCertificateHeaderService {

    @Autowired
    private FCStateCertificateHeaderRepository fcStateCertificateHeaderRepository;
    public ResponseEntity<Object> getHeader(Integer stateCode) {
        try {
            FCStateCertificateHeader fcStateCertificateHeader = fcStateCertificateHeaderRepository.getDataByStateCode(stateCode);
            return ResponseHandler.generateResponse("get FC state certificate header", HttpStatus.OK, null,
                    fcStateCertificateHeader);
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("get FC state certificate header", HttpStatus.BAD_REQUEST,
                    null, ex.getMessage());
        }
    }

    public ResponseEntity<Object> saveHeader(FCStateCertificateHeader fcStateCertificateHeader) {
        try {
            FCStateCertificateHeader fcStateCertificate = fcStateCertificateHeaderRepository.getDataByStateCode(fcStateCertificateHeader.getState_code());
            if(fcStateCertificate != null) {
                fcStateCertificateHeader.setId(fcStateCertificate.getId());
            }
            return ResponseHandler.generateResponse("save FC state certificate header", HttpStatus.OK, null,
                    fcStateCertificateHeaderRepository.save(fcStateCertificateHeader));
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("save FC state certificate header", HttpStatus.BAD_REQUEST,
                    null, ex.getMessage());
        }
    }

    public ResponseEntity<Object> deleteHeader(Integer id) {
        try {
            FCStateCertificateHeader fcStateCertificateHeader = fcStateCertificateHeaderRepository.findById(id).get();
            fcStateCertificateHeader.setIs_delete(true);
            fcStateCertificateHeader.setIs_active(false);
            return ResponseHandler.generateResponse("save FC state certificate header", HttpStatus.OK, null,
                    fcStateCertificateHeaderRepository.save(fcStateCertificateHeader));
        } catch (Exception ex) {
            return ResponseHandler.generateResponse("save FC state certificate header", HttpStatus.BAD_REQUEST,
                    null, ex.getMessage());
        }
    }
}
