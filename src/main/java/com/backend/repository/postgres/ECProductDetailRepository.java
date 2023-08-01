package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcProductDetail;

public interface ECProductDetailRepository extends JpaRepository<EcProductDetail, Integer> {

	@Query("select ec from EcProductDetail ec where ec_id=?1")
	public Optional<EcProductDetail> getRecordExist(Integer id);
}
