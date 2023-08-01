package com.backend.repository.postgres;

import com.backend.model.RevokeProposal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RevokeProposalRepository extends JpaRepository<RevokeProposal,Integer> {

    @Query(value = "select * from authority.revoke_proposal rp where rp.created_by=:id and rp.is_reprocessed=:isReprocessed and rp.is_active=true and rp.is_deleted=false",nativeQuery = true)
    List<RevokeProposal> getList(@Param(value = "id") Integer id,@Param(value = "isReprocessed") Boolean isReprocessed);
}
