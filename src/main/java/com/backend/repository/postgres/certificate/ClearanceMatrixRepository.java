package com.backend.repository.postgres.certificate;

import com.backend.model.certificate.ClearanceMatrix;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClearanceMatrixRepository extends JpaRepository<ClearanceMatrix, Integer> {

	@Modifying
	@Query(value = "update master.clearance_matrix set is_delete='true' , is_active='false'  where id=?1", nativeQuery = true)
	Integer deleteClearanceMatrix(Integer id);

	@Query("select cm from ClearanceMatrix cm where activity_id=:activityId and is_active=true and is_delete=false order by order_range asc")
	public List<ClearanceMatrix> getClearanceMatrixByActivityId(@Param(value = "activityId") Integer activityId);

	@Query(value = "select cm.* from master.clearance_matrix cm where cm.is_active =true and cm.is_delete =false order by cm.id limit 1000", nativeQuery = true)
	public List<ClearanceMatrix> getAllClearanceMatrix();

	@Query(value = " select * from master.clearance_matrix where "
			+ "	(cast(application_id as VARCHAR)= cast(:application_id as VARCHAR)) "
			+ "	or(category in (:category )) " + "	or(sub_category = cast(:sub_category as VARCHAR)) "
			+ "	or(cast(sub_activity_id as VARCHAR) =cast(:sub_activity_id as VARCHAR)) "
			+ "	or(cast(activity_id as VARCHAR) in (:activity_id )) "
			+ "	or(sector = Cast(:sector as VARCHAR)) order by created_on ; ", nativeQuery = true)
	List<ClearanceMatrix> getClearanceMatrix(@Param(value = "application_id") String application_id,
			@Param(value = "category") List<String> category, @Param(value = "sub_category") String sub_category,
			@Param(value = "sub_activity_id") String sub_activity_id,
			@Param(value = "activity_id") List<String> activity_id, @Param(value = "sector") String sector);

	@Query(value = " select * from master.clearance_matrix order by created_on limit 1000 ", nativeQuery = true)
	List<ClearanceMatrix> findLimitedRecords();

	@Query(value = " select * from master.clearance_matrix where "
			+ "	(cast(application_id as VARCHAR)= cast(:application_id as VARCHAR)) "
			+ "	or(category in (:category )) " + "	or(sub_category = cast(:sub_category as VARCHAR)) "
			+ "	or(cast(sub_activity_id as VARCHAR) =cast(:sub_activity_id as VARCHAR)) "
			+ "	or(cast(activity_id as VARCHAR) in (:activity_id )) "
			+ "	or(sector = Cast(:sector as VARCHAR)) order by created_on OFFSET :page LIMIT :size ; ", nativeQuery = true)
	List<ClearanceMatrix> getClearanceMatrixWithLimit(@Param(value = "application_id") String application_id,
			@Param(value = "category") List<String> category, @Param(value = "sub_category") String sub_category,
			@Param(value = "sub_activity_id") String sub_activity_id,
			@Param(value = "activity_id") List<String> activity_id, @Param(value = "sector") String sector,
			@Param("page") Integer page, @Param("size") Integer size);


	@Query(value = " select distinct category from master.clearance_matrix ", nativeQuery = true)
	List<String> getCategory();

	@Query(value = " select * from master.clearance_matrix cm where activity_id in (:activity_id) and category = "
			+ ":category and type_proposal = :typeProposal order by order_range asc", nativeQuery = true)
	List<ClearanceMatrix> getClearanceMatrixV2(@Param("activity_id") List<Integer> activity_id,
			@Param("category") String category, @Param("typeProposal") String typeProposal);

	@Query(value = " select * from master.clearance_matrix cm where sector=:sector and category = "
			+ ":category and type_proposal = :typeProposal and is_common=true and is_active=true order by order_range asc", nativeQuery = true)
	List<ClearanceMatrix> getCommonClearanceMatrix(@Param("sector") String sector, @Param("category") String category,
			@Param("typeProposal") String typeProposal);

	@Query(value = " select * from master.clearance_matrix cm where (activity_id in (:activity_id) and sub_activity_id in (:sub_activity_id)) and category = "
			+ ":category and type_proposal = :typeProposal order by order_range asc", nativeQuery = true)
	List<ClearanceMatrix> getClearanceMatrixV3(@Param("activity_id") List<Integer> activity_id,
			@Param("sub_activity_id") List<Integer> sub_activity_id, @Param("category") String category,
			@Param("typeProposal") String typeProposal);

}
