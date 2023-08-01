package com.backend.model.EDSV2;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.EcFormVPart2Model.AdditionalDocuments;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="eds_form_v2",schema="authority")
public class EDSFormV2 extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer application_id;
	
	private Integer app_history_id;
	
	private Integer eds_by;
	
	private Integer eds_by_role_id;
	
	@Column(nullable=true)
	private Integer eds_by_office_id;
	
	private Integer eds_to;
	
	@Column(nullable=true)
	private Integer eds_to_role_id;
	
	@Column(nullable=true)
	private Integer eds_to_office_id;
	
	private String remarks;
	
//	@OneToMany(mappedBy="edsFormV2", cascade= CascadeType.ALL)
	@OneToMany(targetEntity=EDSQueries.class,  cascade = CascadeType.ALL)
	@JoinColumn(name="eds_form_v2_id", referencedColumnName="id")
	@Where(clause = "is_deleted='false'")
	private List<EDSQueries> edsQueries= new ArrayList<EDSQueries>();
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;
	
	private Boolean is_active=true;
	
	private Boolean is_deleted=false;

	public EDSFormV2(Integer id, Integer application_id, Integer app_history_id, Integer eds_by, Integer eds_by_role_id,
			Integer eds_by_office_id, Integer eds_to, Integer eds_to_role_id, Integer eds_to_office_id, String remarks) {
		
		this.id = id;
		this.application_id = application_id;
		this.app_history_id = app_history_id;
		this.eds_by = eds_by;
		this.eds_by_role_id = eds_by_role_id;
		this.eds_by_office_id = eds_by_office_id;
		this.eds_to = eds_to;
		this.eds_to_role_id = eds_to_role_id;
		this.eds_to_office_id = eds_to_office_id;
		this.remarks = remarks;
	}
	
	
	
	
}
