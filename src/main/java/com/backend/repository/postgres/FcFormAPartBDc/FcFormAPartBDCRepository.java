package com.backend.repository.postgres.FcFormAPartBDc;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcFormAPartBDc.FcFormAPartBDC;

public interface FcFormAPartBDCRepository extends JpaRepository<FcFormAPartBDC, Integer> {

	@Query(" select dc from FcFormAPartBDC dc where dc.is_active='true' and dc.is_delete='false' and dc.id=?1 ")
	Optional<FcFormAPartBDC> getDetailsById(Integer id);

	@Query(" select dc from FcFormAPartBDC dc where dc.is_active='true' and dc.is_delete='false' and dc.fc_id=?1 ")
	Optional<FcFormAPartBDC> getDetailsByFcId(Integer fc_id);

}
