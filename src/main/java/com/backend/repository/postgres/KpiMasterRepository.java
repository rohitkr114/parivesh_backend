package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.HomeKPIMaster;

public interface KpiMasterRepository extends JpaRepository<HomeKPIMaster, Integer> {

	@Query("SELECT h FROM HomeKPIMaster h where h.enabled=cast(?1 as boolean) ORDER BY h.order_no ASC")
	List<HomeKPIMaster> get_all_Kpi(String active);
	
	@Query("SELECT h FROM HomeKPIMaster h ORDER BY h.order_no ASC")
	List<HomeKPIMaster> get_all_Kpis();

}
