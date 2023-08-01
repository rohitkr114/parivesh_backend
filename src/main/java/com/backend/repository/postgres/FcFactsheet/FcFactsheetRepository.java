package com.backend.repository.postgres.FcFactsheet;

import com.backend.model.FcFactsheet.FcFactsheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FcFactsheetRepository extends JpaRepository<FcFactsheet,Integer> {

    @Query("select fc from FcFactsheet fc where fc.applicationId=:applicationId")
    public Optional<FcFactsheet> findByApplicationId(@Param(value = "applicationId") Integer applicationId);

    @Query(value = "select pa.clearance_id as clearance from master.proponent_applications pa where pa.id=:applicationId",nativeQuery = true)
    Integer getClearance(@Param(value = "applicationId") Integer applicationId);

    @Query(value = "select fcpbbd.id from master.proponent_applications pa join master.forest_clearance_part_b_basic_details fcpbbd on pa.proposal_id =fcpbbd.fc_id where pa.id =:applicationId order by fcpbbd.created_on  desc limit 1",nativeQuery = true)
    Optional<Integer> getFormADfoPart2Id(@Param(value = "applicationId") Integer applicationId);

    @Query(value = "select fcpbbd.id from master.proponent_applications pa join master.fc_form_b_part_b_basic_details fcpbbd on pa.proposal_id =fcpbbd.fc_id where pa.id =:applicationId order by fcpbbd.created_on  desc limit 1",nativeQuery = true)
    Optional<Integer> getFormBDfoPart2Id(@Param(value = "applicationId") Integer applicationId);

    @Query(value = "select fcpbbd.id from master.proponent_applications pa join master.fc_form_c_part_b fcpbbd on pa.proposal_id =fcpbbd.fc_id where pa.id =:applicationId order by fcpbbd.created_on  desc limit 1",nativeQuery = true)
    Optional<Integer> getFormCDfoPart2Id(@Param(value = "applicationId") Integer applicationId);

    @Query(value = "select fcpbbd.id from master.proponent_applications pa join master.fc_form_d_part_b_basic_details fcpbbd on pa.proposal_id =fcpbbd.fc_id where pa.id =:applicationId order by fcpbbd.created_on  desc limit 1",nativeQuery = true)
    Optional<Integer> getFormDDfoPart2Id(@Param(value = "applicationId") Integer applicationId);

    @Query(value = "select fcpbbd.id from master.proponent_applications pa join master.fc_form_e_part_b_basic_details fcpbbd on pa.proposal_id =fcpbbd.fc_id where pa.id =:applicationId order by fcpbbd.created_on  desc limit 1",nativeQuery = true)
    Optional<Integer> getFormEDfoPart2Id(@Param(value = "applicationId") Integer applicationId);
}
