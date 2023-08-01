package com.backend.repository.postgres.EKyc;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.EKyc.UpdateEKyc;

public interface UpdateEKycRepository extends JpaRepository<UpdateEKyc, Integer>{

	@Query(value = "select * from authentication.update_e_kyc uek  where uek.created_by = ?1 order by uek.created_on desc ", nativeQuery = true)
	//@Query("select uek from UpdateEKyc uek where uek.created_by = :userId")
	public List<UpdateEKyc> getListByUser(Integer userId);
	
	@Query(value = "select * from master.update_kyc_details_fnc(:in_user_id)", nativeQuery= true)
	public Optional<String> updateUserKyc(Integer in_user_id);
	
	@Query(value = "select * from authentication.update_e_kyc uek where uek.created_by = ?1  order by uek.updated_on desc  limit 1", nativeQuery = true)
	public Optional<UpdateEKyc> findByUser(Integer user_id);
	
	@Modifying
	@Query(value = "update authentication.update_e_kyc set status = 'FAILED' where status = 'PENDING' "
			+ "and age( now(), created_on) > '1 days 00:00:00'", nativeQuery = true)
	public Integer setStatusFailed();
}
