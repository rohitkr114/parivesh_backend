package com.backend.repository.postgres;

import com.backend.constant.AppConstant.Caf_Status;
import com.backend.model.CommonFormDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommonFormDetailRepository extends JpaRepository<CommonFormDetail, Integer> {

    @Query("select c from CommonFormDetail c where c.id=?1")
    public CommonFormDetail findDetailByCafId(Integer id);

    @Query("select c from CommonFormDetail c where c.project_id=?1")
    public List<CommonFormDetail> findDetailByProjectId(Integer id);

    @Query("select c from CommonFormDetail c where c.projectDetails.id=?1")
    public List<CommonFormDetail> findCAFDataByProjectId(Integer id);

    @Modifying
    @Query("update CommonFormDetail set caf_status='EDS_RAISED' where id=?1")
    public void updateStatus(Integer id);

    @Modifying
    @Query("update CommonFormDetail set caf_status=?2 where id=?1")
    public void updateStatus(Integer id, Caf_Status status);

    @Query("select c from CommonFormDetail c where c.id=?1")
    public CommonFormDetail getCAF(Integer id);

    @Query("select new CommonFormDetail(c.id,c.projectDetails.id,c.projectDetails.main_state) from CommonFormDetail c where c.id=?1")
    public Optional<CommonFormDetail> findByCaf(Integer id);

    @Query("select new CommonFormDetail(c.id,c.projectDetails.id,c.projectDetails.main_state) from CommonFormDetail c where c.id=?1")
    public CommonFormDetail findByCafId(Integer id);

    @Modifying
    @Query(value = " UPDATE master.caf_details set caf_id_sequence = ?1 , caf_id = ?2 , proposal_for = ?3 , caf_status= ?4 where id= ?5 ;", nativeQuery = true)
    public void updateCAF(Integer caf_id_sequence, String caf_id, String proposal_for, Integer caf_status, Integer id);

    @Query(value = "select pa.caf_id_sequence from master.caf_details pa order by caf_id_sequence desc limit 1", nativeQuery = true)
    public Integer getMaxCafSequence();

    @Query(value = " select count(*) from master.caf_details ", nativeQuery = true)
    public Long getCount();

    @Query(value = " select * from master.copy_caf_amendment_fnc(?1) ; ", nativeQuery = true)
    Integer copyCafDetails(Integer id);

    @Modifying
    @Query(value = " update master.caf_details set amendment_form =?1 where id = ?2", nativeQuery = true)
    public void updateAmendmentForm(String amendment_form, Integer id);
}
