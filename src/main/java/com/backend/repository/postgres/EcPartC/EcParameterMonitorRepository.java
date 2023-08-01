package com.backend.repository.postgres.EcPartC;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcPartC.EcParameterMonitor;

public interface EcParameterMonitorRepository extends JpaRepository<EcParameterMonitor, Integer> {

	@Modifying
	@Query("update EcParameterMonitor set is_deleted='true' , is_active='false'  where id=?1")
	Integer updateParameterMonitor(Integer id);

	@Query("select new EcParameterMonitor(ecb.id, ecb.attribute, ecb.attribute_other, ecb.proposed_parameter,"
			+ "	ecb.location_lat, ecb.location_long, ecb.monitoring_mode, ecb.monitoring_frequency,"
			+ "	ecb.monitoring_frequency_other, ecb.project_phase, ecb.monitoring_agency,"
			+ "	ecb.monitoring_agency_other) from EcParameterMonitor ecb where ecb.ecPartC.id=?1 and ecb.is_deleted='false'")
	public List<EcParameterMonitor> getDataByEcId(Integer id);

	@Query(value ="select * from master.ec_parameter_monitor ecb where ecb.parameter_monitor_id=?1 and ecb.is_deleted='false'",nativeQuery = true)
	public Set<EcParameterMonitor> getDataByFormId(Integer id);

}
