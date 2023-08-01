package com.backend.repository.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.HomeHeroMaster;

public interface HeroMasterRepository extends JpaRepository<HomeHeroMaster, Integer> {
	
	@Query("SELECT h FROM HomeHeroMaster h where h.enabled=cast(?1 as boolean) ORDER BY h.order_no ASC")
	List<HomeHeroMaster> get_all_Hero(String active);

	@Query("SELECT h FROM HomeHeroMaster h ORDER BY h.order_no ASC")
	List<HomeHeroMaster> get_all_Heros();
}
