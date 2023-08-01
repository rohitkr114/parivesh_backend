package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.dto.StandadCertificateDto.RejectionCertificateDto;
import com.backend.dto.StandadCertificateDto.StandardCertificateDto;
import com.backend.model.StandardTORCertificate.RejectionTorCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RejectionTorCertificateRepository extends JpaRepository<RejectionTorCertificate,Integer> {

    @Query(value = "select caf.applicant_name as proponent,caf.organization_name as companyname,\n" +
            "caf.applicant_email as proponentEmail,prop.moefcc_file_number as fileNo,\n" +
            "ec.project_category as category,ec.nbwl_project_location as locationOfProject,\n" +
            "caf.project_name as nameOfProject,\n" +
            "CONCAT (st.name,'  ', ds.name) as registeredAddress\n" +
            "FROM master.caf_details caf\n" +
            "RIGHT Join master.state st ON st.id=caf.organization_state\n" +
            "RIGHT Join master.district ds ON ds.id=caf.organization_district\n" +
            "LEFT Join master.proponent_applications prop ON prop.caf_id=caf.id\n" +
            "LEFT Join master.ec ec ON ec.caf_id = caf.id\n" +
            "WHERE prop.id=?1", nativeQuery = true)
    RejectionCertificateDto getDataForCertificate(Integer id)    ;

    @Query(value = "select st from RejectionTorCertificate st where st.proponentId=?1 and st.isActive = true and proposal_no=?2")
    RejectionTorCertificate getRejectionCertificateInfoBytPropId (Integer proponent_id, String proposalNo);
}

