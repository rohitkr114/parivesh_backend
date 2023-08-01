package com.backend.repository.postgres.StandardTorCertificate;

import com.backend.dto.CertificateVersionDTO;
import com.backend.dto.StandadCertificateDto.SpecificCertificateDto;
import com.backend.dto.StandadCertificateDto.StandardCertificateDto;
import com.backend.model.StandardTORCertificate.RejectionTorCertificate;
import com.backend.model.StandardTORCertificate.SpecificTorCertificate;
import com.backend.model.StandardTORCertificate.StandardTorCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecificTorCertificateRepository extends JpaRepository<SpecificTorCertificate, Integer> {

    @Query(value="select * from master.specific_tor_certificate st where st.proponent_id=?1 and st.is_active = true and st.proposal_no=?2 order by id desc limit 1",nativeQuery = true)
    SpecificTorCertificate getSpecificCertificateInfoBytPropId(Integer proponent_id, String proposalNo);

    @Query(value = "select * from master.standard_tor_certificate st where st.ec_id=?1 and st.is_active = true LIMIT  1", nativeQuery = true)
    SpecificTorCertificate getSpecificTorCertificateInfoBytEcId(Integer ecId);

    @Query(value = "select * from master.specific_tor_certificate where ec_id = ?1 and is_active = true LIMIT  1", nativeQuery = true)
    SpecificTorCertificate getSpecificTorDateByEcId(Integer ecId);

    @Query(value = "select distinct version,id,name,a.status,a.created_on,a.created_by,a.is_active,proposal_no from master.specific_tor_certificate a,authentication.user_entity ue  where proponent_id=?1 and a.created_by =ue.entityid order by id desc", nativeQuery = true)
    List<CertificateVersionDTO> getByProposalIdForTor(Integer proposalId);

    @Query(value = "select distinct version,id,name,a.status,a.created_on,a.created_by,a.is_active,proposal_no from master.ec_tor_amendment_certificate a,authentication.user_entity ue  where proponent_id=?1 and a.created_by =ue.entityid order by id desc", nativeQuery = true)
    List<CertificateVersionDTO> getByProposalIdForAmdTor(Integer proposalId);

    @Query(value = "select distinct version,id,name,a.status,a.created_on,a.created_by,a.is_active,proposal_no from master.ec_fresh_letter_template_certificate a,authentication.user_entity ue  where proponent_id=?1 and a.created_by =ue.entityid order by id desc", nativeQuery = true)
    List<CertificateVersionDTO> getByProposalIdForEC(Integer proposalId);

    @Query(value = "select distinct version,id,name,a.status,a.created_on,a.created_by,a.is_active,proposal_no from master.ec_amendment_certificate a,authentication.user_entity ue  where proponent_id=?1 and a.created_by =ue.entityid order by id desc", nativeQuery = true)
    List<CertificateVersionDTO> getByProposalIdForAmdEC(Integer proposalId);


    @Query(value = "select distinct version,id,name,a.status,a.created_on,a.created_by,a.is_active,proposal_no from master.ec_extension_of_validity_certificate a,authentication.user_entity ue  where proponent_id=?1 and a.created_by =ue.entityid order by id desc", nativeQuery = true)
    List<CertificateVersionDTO> getByProposalIdForExtEC(Integer proposalId);


    @Query(value = "select distinct version,id,name,a.status,a.created_on,a.created_by,a.is_active,proposal_no from master.ec_transfer_template a,authentication.user_entity ue  where proponent_id=?1 and a.created_by =ue.entityid order by id desc", nativeQuery = true)
    List<CertificateVersionDTO> getByProposalIdForTrnEC(Integer proposalId);

    @Query(value = "select distinct version,id,name,a.status,a.created_on,a.created_by,a.is_active,proposal_no from master.ec_mining_transfer_certificate   a,authentication.user_entity ue  where proponent_id=?1 and a.created_by =ue.entityid order by id desc", nativeQuery = true)
    List<CertificateVersionDTO> getByProposalIdForTrnECMin(Integer proposalId);

    @Query(value = "select distinct version,id,name,a.status,a.created_on,a.created_by,a.is_active,proposal_no from master.ec_tor_transfer_template a,authentication.user_entity ue  where proponent_id=?1 and a.created_by =ue.entityid order by id desc", nativeQuery = true)
    List<CertificateVersionDTO> getByProposalIdForTrnTor(Integer proposalId);


}
