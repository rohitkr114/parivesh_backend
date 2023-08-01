package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.model.FCStateCertificateHeader;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FCStateCertificateHeaderRepository extends JpaRepository<FCStateCertificateHeader,Integer> {

    @Query("select p from FCStateCertificateHeader p where p.state_code=?1")
    FCStateCertificateHeader getDataByStateCode(Integer stateCode);
    
    @Query(value="select fs.header_details from master.fc_state_certificate_header fs where fs.state_code=?1 and fs.is_active=true and fs.is_delete=false", nativeQuery = true)
    public String getHeaderDetailsByStateCode(Integer stateCode);
}
