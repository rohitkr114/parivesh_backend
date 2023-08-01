package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.FcOthersDetail;

public interface FcOthersDetailRepository extends JpaRepository<FcOthersDetail, Integer> {

	@Query("select ec from FcOthersDetail ec where fc_id=?1")
	public Optional<FcOthersDetail> getRecordExist(Integer id);
}
