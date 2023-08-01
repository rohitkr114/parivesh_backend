package com.backend.repository.postgres.ForestClearanceFormE;

import com.backend.model.ForestClearanceE.FcFormE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FcFormERepository extends JpaRepository<FcFormE, Integer> {

    @Query("select new FcFormE(fc.id) from FcFormE fc where fc.id=?1")
    public FcFormE getFcFormEById(Integer id);

    @Query("select new FcFormE(fc.id, fc.state, "
            + " fc.application_for, "
            + " fc.rediversion_sought_by, "
            + " fc.purpose_of_rediversion, "
            + "	fc.justification, "
            + " fc.proposal_no, "
            + " fc.is_rediversion_proposed, "
            + " fc.rediversionCategory) from FcFormE fc where fc.id=?1 ")
    public Optional<FcFormE> getFcFormEDetailsById(Integer id);

    @Query(value = " select upload_noc_copy_id from master.fc_form_e where id=?1 ", nativeQuery = true)
    public List<Object[]> getDocuments(Integer id);

    @Modifying
    @Query(value = "Update master.fc_form_e set proposal_no =:proposalNo where id =:id", nativeQuery = true)
    public Integer updateFcFormEProposal(@Param(value = "id") Integer id, @Param(value = "proposalNo") String proposalNo);
}
