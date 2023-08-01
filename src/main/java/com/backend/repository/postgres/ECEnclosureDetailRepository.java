package com.backend.repository.postgres;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EcEnclosureDetail;


public interface ECEnclosureDetailRepository  extends JpaRepository<EcEnclosureDetail, Integer>  {

	  
	  @Query("select ec from EcEnclosureDetail ec where ec_id=?1")
	  public Optional<EcEnclosureDetail> getRecordExist(Integer id);
}
