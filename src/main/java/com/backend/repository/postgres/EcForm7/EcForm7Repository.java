package com.backend.repository.postgres.EcForm7;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.EcForm7.EcForm7;

public interface EcForm7Repository extends JpaRepository<EcForm7, Integer>{

	@Query("select new EcForm7(ec.id, "
			+ " ec.proposal_no, "
			+ " ec.project_category, "
			+ " ec.ec_id, "
			+ " ec.is_proposed_required, "
			+ " ec.major_activity_id, "
			+ " ec.major_sub_activity_id "
			+ ") from EcForm7 ec where ec.id=?1")
	public Optional<EcForm7> getFormById(Integer id);
	
	@Query("select ec from EcForm7 ec where ec.id=?1")
	public Optional<EcForm7> getFormByIdDet(Integer id);
	
	@Query(value = "select ec.caf_id from master.ec_form_7 ec where ec.id=?1", nativeQuery = true)
	public List<Object[]> getCafByEcId(Integer id);
	
    @Query(value="select a.name from master.activities a inner join master.ec_form_7 ef on ef.major_activity_id = a.id where ef.id=:id", nativeQuery = true)
    public String getMajorActivityName(@Param(value="id") Integer id);
    
    @Query(value="select a.item_no from master.activities a inner join master.ec_form_7 ef on ef.major_activity_id = a.id where ef.id=:id", nativeQuery = true)
    public String getItemNo(@Param(value="id") Integer id);

}
