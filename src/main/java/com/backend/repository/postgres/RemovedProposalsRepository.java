package com.backend.repository.postgres;

import com.backend.dto.ProposalForRemovalDto;
import com.backend.model.RemovedProposals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface RemovedProposalsRepository extends JpaRepository<RemovedProposals,Integer> {
    @Modifying
    @Query(value = "update process_engine.application_process_history as aph set is_current_step =null where aph.application_id =:application_id",nativeQuery = true)
    public Integer deleteHistory(@Param(value = "application_id") Integer applicationId);

    @Modifying
    @Query(value="update process_engine.process_step_authority as psa set status='REMOVED' where psa.app_history_id =\n" +
            "(select id from process_engine.application_process_history aph where aph.application_id=:application_id order by aph.id desc limit 1)", nativeQuery = true)
    public Integer deleteStepAuthority(@Param(value = "application_id") Integer applicationId);

    @Query(value="select * from master.get_proposal_not_stage_1(:userId)",nativeQuery = true)
    public List<ProposalForRemovalDto> getProposalForRemoval(@Param(value = "userId")Integer userId);

    @Query(value="select * from master.removed_proposals rp where cast(created_on as date) between cast(:fromDate as date )and cast(:toDate as date) and rp.created_by=:entityId and rp.is_active=true order by rp.created_on desc", nativeQuery = true)
    public List<RemovedProposals> getProposalList(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate,@Param(value = "entityId") Integer entityid);
}
