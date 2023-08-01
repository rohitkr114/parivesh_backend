package com.backend.repository.postgres.EcForm5AcceptProposal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.EcFrom5AcceptProposal.EcForm5AcceptProposals;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EcForm5AcceptProposalRepository extends JpaRepository<EcForm5AcceptProposals, Integer> {

    @Query("select ec from EcForm5AcceptProposals ec where ec_id=:ecId and isActive=true")
    public List<EcForm5AcceptProposals> getByEcId(@Param(value = "ecId") Integer ecId);
}
