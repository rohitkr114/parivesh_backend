package com.backend.repository.postgres.FcSIR;

import com.backend.model.FcSIR.FcSIRLegalStatusDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FcSIRLegalStatusDetailsRepository extends JpaRepository<FcSIRLegalStatusDetails,Integer> {

    @Query(value = "select * from authority.fc_sir_legal_status_details fc where fc.fc_sir_id=:sirId",nativeQuery = true)
    public FcSIRLegalStatusDetails getDetailsBySirId(@Param(value = "sirId") Integer sirId);
}
