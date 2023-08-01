package com.backend.repository.postgres.FcSIR;

import com.backend.model.FcSIR.FcSiteVisitDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FcSiteVisitDetailsRepository extends JpaRepository<FcSiteVisitDetails,Integer> {

    @Query(value = "select * from authority.fc_site_visit_details fc where fc.fc_sir_id=:sirId",nativeQuery = true)
    public FcSiteVisitDetails getDetailsBySirId(@Param(value = "sirId") Integer sirId);
}
