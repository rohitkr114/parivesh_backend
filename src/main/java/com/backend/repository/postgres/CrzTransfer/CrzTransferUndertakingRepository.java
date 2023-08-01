package com.backend.repository.postgres.CrzTransfer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.CrzTransfer.CrzTransferUndertaking;

public interface CrzTransferUndertakingRepository extends JpaRepository<CrzTransferUndertaking, Integer>{

	@Query(" select new CrzTransferUndertaking( ecu.id, "
			+ "	ecu.i_agree, "
			+ " ecu.undertaking_person_name, "
			+ " ecu.undertaking_person_designation, "
			+ " ecu.undertaking_person_company, "
			+ " ecu.undertaking_person_address, "
			+ "	ecu.undertaking_person_esign, "
			+ " ecu.undertaking_date ) from CrzTransferUndertaking ecu where ecu.crzTransferDetails.id=?1")
	public Optional<CrzTransferUndertaking> getDataByCrz(Integer id);
}
