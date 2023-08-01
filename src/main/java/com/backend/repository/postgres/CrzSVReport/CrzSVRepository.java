package com.backend.repository.postgres.CrzSVReport;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CrzSVReport;

public interface CrzSVRepository extends JpaRepository<CrzSVReport, Integer> {

	@Query(" select crz from CrzSVReport crz where crz.is_active='true' and crz.is_delete='false' and crz.id=?1 ")
	Optional<CrzSVReport> getDetailsById(Integer crzSVRid);
}
