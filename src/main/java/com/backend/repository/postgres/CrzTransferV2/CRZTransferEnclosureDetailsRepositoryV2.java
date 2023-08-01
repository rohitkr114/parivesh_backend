package com.backend.repository.postgres.CrzTransferV2;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.CrzTransferV2.CRZTransferEnclosureDetailsV2;

public interface CRZTransferEnclosureDetailsRepositoryV2 extends JpaRepository<CRZTransferEnclosureDetailsV2, Integer> {
	
	@Query(value="select cted.* from master.crz_transfer_enclosure_details_v2 cted where cted.is_active=true and cted.is_deleted=false and cted.crz_transfer_id=:crzTransferId order by id desc limit 1 ",nativeQuery=true)
	public Optional<CRZTransferEnclosureDetailsV2> getDataByFormId(@Param(value="crzTransferId")Integer crzTransferId);
}
