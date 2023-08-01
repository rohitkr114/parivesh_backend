package com.backend.repository.postgres;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend.model.DraftFormData;

@Transactional
@Repository
public interface DraftFormDataRepository extends JpaRepository<DraftFormData, Integer> {

	@Query(value = "Select * from master.draft_form_data df where df.application_id=:applicationId and df.caf_id=:cafId and df.form_id=:formId order by id desc", nativeQuery = true)
	public List<DraftFormData> getDraftFormDataAllVersion(@Param("formId") Integer formId);

	@Query(value = "select * from master.draft_form_data dfd where dfd.form_id =?3 and dfd.application_id =?2 and dfd" +
			".step_id=?1" +
			" and dfd.is_active =true and dfd.is_deleted= false limit 1", nativeQuery = true)
	public DraftFormData getDraftFormDataCurrentVersion( Integer stepId ,Integer applicationId,Integer formId);

	@Query(value = "select * from master.draft_form_data dfd where dfd.form_id =?1 and dfd.application_id =?2 and dfd" +
			".is_active =true and dfd.is_deleted= false", nativeQuery = true)
	public List<DraftFormData> getDraftFormDataCurrent( Integer formId,Integer applicationId);

	@Query(value = "select * from master.draft_form_data dfd where dfd.form_id =?1 and dfd.application_id =?2 and " +
			"dfd.step_id=?3 and dfd" +
			".is_active =true and dfd.is_deleted= false", nativeQuery = true)
	public List<DraftFormData> getFormDataForCompareWithStep( Integer formId,Integer applicationId,Integer stepId);

	@Query(value = "select * from master.draft_form_data dfd where dfd.is_active ='true' and dfd.is_deleted='false' " +
			"and dfd.form_id =:formId and dfd.step_id=:stepId limit 1;", nativeQuery = true)
	DraftFormData getDraftFormDataCurrentVersionWithoutAppId(@Param(value="formId")Integer formId,
															 @Param("stepId") Integer stepId);

	@Query(value = "select * from master.draft_form_data dfd where dfd.form_id =?1 and dfd.application_id =?2 and " +
			"version=?3",	nativeQuery = true)
	List<DraftFormData> getDraftFormDataPrevious(Integer formId, Integer applicationId, Integer currentV);

	@Query(value = "select * from master.draft_form_data dfd where dfd.form_id =?1 and dfd.application_id =?2 and dfd" +
			".step_id =?4" +
			" and " +
			"version=?3",	nativeQuery = true)
	List<DraftFormData> getDraftFormDataPreviousWithStep(Integer formId, Integer applicationId, Integer currentV,
														 Integer stepId);

}
