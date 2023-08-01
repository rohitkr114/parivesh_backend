package com.backend.crz.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.model.CrzCommitteeMembersDto;

@Repository
public interface CrzCommitteeMembersRepository extends JpaRepository<CrzCommitteeMembersDto, Integer> {

	@Query(value = "select * from master.crz_committee_members_details order by 1 asc", nativeQuery = true)
	Set<CrzCommitteeMembersDto> findAllMemberByOrder();

}
