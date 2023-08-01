package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcEnclosures;

public interface EcEnclosuresRepository extends JpaRepository<EcEnclosures, Integer> {

	@Query("select new EcEnclosures(ecn.id, ecn.approved_mining_plan_url, ecn.district_survey_report_url,"
			+ "	ecn.replenishment_study_report_url) from EcEnclosures ecn where ecn.ecPartC.id=?1")
	public Optional<EcEnclosures> getDataByEcId(Integer id);

	@Query(value = "select ec.additional_upload_copy_id,ec.feasibility_summary_report_id,ec.replenishment_study_report_id,"
			+ "ec.district_survey_report_id,ec.final_layout_copy_id,ec.approved_mining_plan_copy_id,ec.eia_final_copy_id from master.ec_partc_enclosures ec where ec.id=?1", nativeQuery = true)
	public List<Object[]> getDocuments(Integer id);
}
