package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcChemicalProperties;

public interface EcChemicalPropertiesRepository extends JpaRepository<EcChemicalProperties, Integer> {
	@Modifying
	@Query("update EcChemicalProperties set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateChemicalProperties(Integer id);

	@Query("select new EcChemicalProperties(ecb.id, ecb.cp_location_lat, ecb.cp_location_long, ecb.cp_location_core,"
			+ "	ecb.cp_criteria_parameter, ecb.cp_observed_value, ecb.cp_observed_value_to, ecb.cp_unit, ecb.cp_unit_other,"
			+ "	ecb.cp_permissible_standard) from EcChemicalProperties ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcChemicalProperties> getDataByEcId(Integer id);

	@Query(value ="select * from master.ec_chemical_properties ecb where ecb.chemical_property_id=?1 and ecb.is_deleted='false'",nativeQuery = true)
	public Set<EcChemicalProperties> getDataByFormId(Integer id);
}
