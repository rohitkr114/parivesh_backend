package com.backend.repository.postgres;

import com.backend.constant.AppConstant;
import com.backend.model.Applications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ApplicationsRepository extends JpaRepository<Applications, Integer> {

    @Query("Select a from Applications a where a.name=?1")
    Applications getApplicationByName(String name);


    @Query("SELECT a from  Applications a where  a.is_deleted='false' order by name")
    List<Applications> findAllApplications();

    @Query("SELECT a from  Applications a where  a.is_deleted='false' and a.for_department=cast(?1 as boolean) order by name")
    List<Applications> findAllApplicationsByStatus(String flag);

    @Query("SELECT a from  Applications a where  a.is_deleted='false' and a.form_for=?1 order by name")
    List<Applications> findAllApplicationsbyform_for(AppConstant.Form_for form_for);

    @Query("SELECT a from  Applications a where  a.is_deleted='false' and a.form_for=?1 and a.for_department=cast(?2 as boolean) order by name")
    List<Applications> findAllApplicationsbyform_forByStatus(AppConstant.Form_for form_for, String flag);

    @Query(value = "select app.form_code from master.applications app where app.dd_name =?1", nativeQuery = true)
    Integer getFormCodeByDdName(String ddName);

    @Query(value = "select app.clearance_type_code from master.applications app where app.dd_name =?1", nativeQuery = true)
    Optional<String> getClearanceTypeByDdName(String ddName);

    @Query(value = "select ap.* from master.applications ap join master.proponent_applications pa on pa.clearance_id = ap.id where pa.id =?1", nativeQuery = true)
    Applications getFcIROStageClearanceCertificateFormTypeInfoBytPropId(Integer propId);

    @Query(value = "select fc_conditions from master.applications where id =?1", nativeQuery = true)
    String getFormTypeByClearanceId(int clearanceType);

    @Query(value = "select a.abbr from master.applications a where a.id =?1", nativeQuery = true)
    String getAbbrByClearanceId(int clearanceType);

    @Query(value = " select a.* from master.applications a where a.approval_id = ?1  limit 1 ", nativeQuery = true)
    Applications getApplicationsByApprovalId(String approvalId);
}
