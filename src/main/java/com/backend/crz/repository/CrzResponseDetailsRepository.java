package com.backend.crz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.backend.model.CrzResponseDetailsDto;

@Repository
public interface CrzResponseDetailsRepository extends JpaRepository<CrzResponseDetailsDto, Integer>{

	@Query(value = "select * from master.crz_response_details where query_id = ?1 order by 1", nativeQuery = true )
	List<CrzResponseDetailsDto> findByQueryId(Integer query_id);
}