package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.dto.StandadCertificateDto.StandardCertificateDto;
import com.backend.model.StandardTORCertificate.StandardTorCertificate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface StandardTorCertificateRepository extends JpaRepository<StandardTorCertificate, Integer> {

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
    StandardCertificateDto getDataForCertificate(Integer id);

    @Query(value = "select * from master.standard_tor_certificate st where st.proponent_id=?1 and st.is_active = true and proposal_no=?2  order by id desc limit 1", nativeQuery = true)
    StandardTorCertificate getStandardCertificateInfoBytPropId (Integer proponent_id, String proposalNo);

    @Query(value = "select * from master.standard_tor_certificate st where st.ec_id=?1 and st.is_active = true LIMIT  1", nativeQuery = true)
    StandardTorCertificate getStandardCertificateInfoBytEcId (Integer ecId);

    @Query(value = "select * from master.standard_tor_certificate where ec_id = ?1 and is_active = true LIMIT  1", nativeQuery = true)
    StandardTorCertificate getStandardTorDateByEcId(Integer ecId);
    
   // @Query(value = "select e.project_category,sa.code, cd.proposal_for from master.ec e join master.proponent_applications pa on e.id = pa.proposal_id join master.caf_details cd on cd.id =pa.caf_id join master.sub_activities sa on sa.id = e.major_sub_activity_id  where pa.proposal_no = ?1", nativeQuery = true)
    //String getStandardTorDetail(String proposalno);
    
    @Query(value = "select eod.identification_no from master.ec_others_details eod where eod.ec_id = ?1", nativeQuery = true)
    String getIdentificationNo(Integer ec_id);
    
}
