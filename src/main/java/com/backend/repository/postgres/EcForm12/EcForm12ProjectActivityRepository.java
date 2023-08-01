package com.backend.repository.postgres.EcForm12;

import com.backend.model.EcForm12.EcForm12ProjectActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EcForm12ProjectActivityRepository extends JpaRepository<EcForm12ProjectActivity, Integer> {

    @Query("select new EcForm12ProjectActivity(ec.id "
            + ") from EcForm12ProjectActivity ec where ec.id=?1")
    public Optional<EcForm12ProjectActivity> getFormById(Integer id);

    @Query("select new EcForm12ProjectActivity(ec.id, "
            + " ec.ecForm12, "
            + " ec.moef_file_no, "
            + "	ec.prior_environmental_clearance_date, "
            + " ec.is_earlier_ec_obtained "
            + ") from EcForm12ProjectActivity ec where ec.id=?1")
    public EcForm12ProjectActivity getFormByIdDet(Integer id);

    @Query("select new EcForm12ProjectActivity(ec.id, "
            + " ec.ecForm12, "
            + " ec.moef_file_no, "
            + "	ec.prior_environmental_clearance_date, "
            + " ec.is_earlier_ec_obtained "
            + ") from EcForm12ProjectActivity ec where ec.ecForm12.id=?1")
    public EcForm12ProjectActivity getFormByIdDetByEc(Integer id);

    @Query(value = "select ec.caf_id from master.ec_form_12_project_activity ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getCafByEcId(Integer id);

    @Query(value = "select ec.ec_letter_copy_id,ec.chronology_of_clearances_id from master.ec_form_12_project_activity ec where ec.id=?1", nativeQuery = true)
    public List<Object[]> getDocuments(Integer id);

    @Query(value = " select * from master.ec_form_12_project_activity  where ec_form_12_id = ?1 ", nativeQuery = true)
    EcForm12ProjectActivity getDataByEcFrom12(Integer id);

    @Query(value = "select ec.ec_letter_copy_id from master.ec_form_12_project_activity ec where ec.id=?1", nativeQuery = true)
    Integer getEc_letter_copy_id(Integer id);
}
