package com.backend.repository.postgres.CrzTransferV2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.CrzTransferV2.CRZTransferUndertakingV2;

public interface CRZTransferUndertakingRepositoryV2 extends JpaRepository<CRZTransferUndertakingV2, Integer> {
	
	@Query(value="select ctuv.* from master.crz_transfer_undertaking_v2 ctuv where ctuv.is_active=true and ctuv.is_deleted=false and ctuv.crz_transfer_id=:crzTransferId order by id desc limit 1",nativeQuery=true)
	public Optional<CRZTransferUndertakingV2> getDataByFormId(@Param(value="crzTransferId")Integer crzTransferId);
}
