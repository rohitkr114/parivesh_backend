package com.backend.crz.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzMomChairmanQueriesDto;

@Repository
public interface CrzMomChairmanQueriesRepository extends JpaRepository<CrzMomChairmanQueriesDto, Integer> {

	@Query(value = "SELECT * FROM master.crz_mom_chairman_queries u WHERE u.mom_id =?1", nativeQuery = true)
	Optional<List<CrzMomChairmanQueriesDto>> findByMomId(Integer mom_id);

}
