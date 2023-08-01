package com.backend.repository.postgres.ClientCode;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.model.ClientCode.ClientCode;

public interface ClientCodeRepository extends JpaRepository<ClientCode,Integer>{
	
	@Query(value="select * from master.client_code cc where cc.state_code=:state_code order by cc.id desc limit 1", nativeQuery=true)
	public ClientCode getClientCodeByStateCode(@Param(value="state_code") Integer state_code);
	
}
