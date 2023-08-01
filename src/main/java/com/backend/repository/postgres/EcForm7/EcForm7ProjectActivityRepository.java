package com.backend.repository.postgres.EcForm7;

import com.backend.model.EcForm7.EcForm7ProjectActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EcForm7ProjectActivityRepository extends JpaRepository<EcForm7ProjectActivity, Integer> {

    @Query("select new EcForm7ProjectActivity(ec.id "
            + ") from EcForm7ProjectActivity ec where ec.id=?1")
    public Optional<EcForm7ProjectActivity> getFormById(Integer id);

    @Query("select new EcForm7ProjectActivity(ec.id, "
            + " ec.ecForm7, "
            + " ec.moef_file_no, "
            + "	ec.prior_environmental_clearance_date, "
            + " ec.is_earlier_ec_obtained "
            + ") from EcForm7ProjectActivity ec where ec.id=?1")
    public EcForm7ProjectActivity getFormByIdDet(Integer id);

    @Query("select new EcForm7ProjectActivity(ec.id, "
            + " ec.ecForm7, "
            + " ec.moef_file_no, "
            + "	ec.prior_environmental_clearance_date, "
            + " ec.is_earlier_ec_obtained "
            + ") from EcForm7ProjectActivity ec where ec.ecForm7.id=?1")
    public EcForm7ProjectActivity getFormByIdDetByEc(Integer id);

    @Query(value = "select ec.caf_id from master.ec_form_7_project_activity ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getCafByEcId(Integer id);

    @Query(value = "select ec.ec_letter_copy_id,ec.chronology_of_clearances_id from master.ec_form_7_project_activity ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getDocuments(Integer id);

    @Query(value = " select * from master.ec_form_7_project_activity  where ec_form_7_id = ?1 ", nativeQuery = true)
    EcForm7ProjectActivity getDataByEcFrom7(Integer id);

    @Query(value = "select ec.ec_letter_copy_id from master.ec_form_7_project_activity ec where ec.id=?1", nativeQuery = true)
    Integer getEc_letter_copy_id(Integer id);
}
