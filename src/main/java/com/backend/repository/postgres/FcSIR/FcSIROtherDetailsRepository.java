package com.backend.repository.postgres.FcSIR;

import com.backend.model.FcSIR.FcSIRLegalStatusDetails;
import com.backend.model.FcSIR.FcSIROtherDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FcSIROtherDetailsRepository extends JpaRepository<FcSIROtherDetails,Integer> {

    @Query(value = "select * from authority.fc_sir_other_details fc where fc.fc_sir_id=:sirId",nativeQuery = true)
    public FcSIROtherDetails getDetailsBySirId(@Param(value = "sirId") Integer sirId);
}
