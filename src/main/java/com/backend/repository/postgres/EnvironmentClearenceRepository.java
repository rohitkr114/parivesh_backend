package com.backend.repository.postgres;

import java.util.List;
import java.util.Optional;

import com.backend.dto.EcEmailDto;
import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EnvironmentClearanceProjectActivityDetails;
import com.backend.model.EnvironmentClearence;

public interface EnvironmentClearenceRepository extends JpaRepository<EnvironmentClearence, Integer> {

    @Query(value = "select * from master.ec ec where ec.is_deleted = 'false' and ec.proposal_no  = ?1", nativeQuery = true)
    public EnvironmentClearence findByProposalNo(String proposalNo);

    @Query("select new EnvironmentClearence(ec.id,ec.proposal_no,ec.project_category,ec.is_proposed_required,ec.major_activity_id,ec.major_sub_activity_id,ec.is_for_old_proposal,ec.is_amendment,ec.parent_id) from EnvironmentClearence ec where ec.id=?1")
    public Optional<EnvironmentClearence> findByFormId(Integer id);

    @Query(value = "select ec.caf_id from master.ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getCafByEcId(Integer id);

    @Query(value = "select ec from master.ec where ec.interlink_moefcc_file_no=?1", nativeQuery = true)
    public Optional<EnvironmentClearence> findByMoefFileNo(Integer id);

    @Query(value = "select master.is_auto_tor_applied(?1)", nativeQuery = true)
    public Boolean getTORStatus(Integer proposalId);

    @Modifying
    @Query(value = "Update master.ec set proposal_no =?2 where id =?1", nativeQuery = true)
    public Integer updateEcProposal(Integer id, String proposal_no);

    @Modifying
    @Query(value = "Update master.ec_partc set proposal_no =?2 where id =?1", nativeQuery = true)
    public Integer updateEcPartCProposal(Integer id, String proposal_no);

    @Query(value = "select a.name from master.activities a inner join master.ec e on e.major_activity_id = a.id where e.id=:id", nativeQuery = true)
    public String getMajorActivityName(@Param(value = "id") Integer id);
    
    @Query(value = "select a.item_no from master.activities a inner join master.ec e on e.major_activity_id = a.id where e.id=:id", nativeQuery = true)
    public String getItemNo(@Param(value = "id") Integer id);

    @Query(value = " select * from master.ec_proposal_dtls_fnc(?1) ;", nativeQuery = true)
    EcEmailDto getEcEmailData(String proposalNo);

    @Modifying
    @Query(value = "Update master.ec_partc set parent_id =?2 where id =?1", nativeQuery = true)
    public Integer updateEcPartCParent(Integer ec_c_id_new, Integer proposalId);

	@Modifying
    @Query(value = "Update master.ec set proposal_no =?2, legacy_proposal_type='EC_FORM_4_PART_A' where id =?1", nativeQuery = true)
    public Integer updateEcProposalLegacy(Integer ec_id, String proposal_no);
    
 


}