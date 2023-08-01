package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.backend.model.ExpansionFormData;

public interface ExpansionFormDataRepository extends JpaRepository<ExpansionFormData,Integer> {
	
	@Query("select efd from ExpansionFormData efd where efd.id=?1 and efd.applicationId=?2")
	public ExpansionFormData findByIdAndApplicationId(Integer id, Integer applicationId);
	
	@Query("select efd from ExpansionFormData efd where efd.id=?1 and efd.applicationId=?2 and efd.step=?3")
	public ExpansionFormData findByIdAndApplicationIdAndStep(Integer id, Integer applicationId,Integer step);
}
