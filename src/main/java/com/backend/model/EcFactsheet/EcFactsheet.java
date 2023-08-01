package com.backend.model.EcFactsheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import com.backend.model.ProponentApplications;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name="ec_factsheet", schema="master")
public class EcFactsheet extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="ec_id", nullable=true)
	private Integer ec_id;
	
	@Column(name="project_name", nullable=true, length=1000)
	private String project_name;
	
	@Column(name="activity_id", nullable=true)
	private Integer activity_id;
	
	@Column(name="activity_name", nullable=true, length=255)
	private String activity_name;
	
	@Column(name="project_category", nullable=true, length=100)
	private String project_category;
	
	@Column(name="project_type", nullable=true, length=100)
	private String project_type;
	
	@Column(name="organization_name", nullable=true, length=255)
	private String organization_name;
	
	@Column(name="organization_legal_status", nullable=true, length=255)
	private String organization_legal_status;
	
	@Column(name="project_state_code", nullable=true)
	private Integer project_state_code;
	
	@Column(name="project_district_code", nullable=true)
	private Integer project_district_code;
	
	@Column(name="project_state", nullable=true, length=100)
	private String project_state;
	
	@Column(name="project_district", nullable=true, length=100)
	private String project_district;

	@Column(name="project_village", nullable=true, length=100)
	private String project_village;

	@Column(name="project_village_code", nullable=true, length=100)
	private String project_village_code;

	@Column(name="capital_investment_proposed", nullable=true, length=100)
	private String capital_investment_proposed;

	@Column(name="total_employment", nullable=true)
	private Long total_employment;

	@Column(name="emp_cost_capital", nullable=true)
	private Double emp_cost_capital;
	
	@Column(name="emp_cost_recurring", nullable=true)
	private Double emp_cost_recurring;
	
	@Column(name="application_date", nullable=true)
	private Date application_date;
	
	@Column(name="tor_issue_date", nullable=true)
	private Date tor_issue_date;
	
	@Column(name="prev_ec_issue_date", nullable=true)
	private Date prev_ec_issue_date;
	

	@OneToMany(targetEntity= EcFactsheetProductdetails.class, cascade=CascadeType.ALL)
	@JoinColumn(name = "ec_factsheet_id", referencedColumnName = "id")
	@Where(clause="is_deleted='false'")
	private List<EcFactsheetProductdetails> productDetails=new ArrayList();
	
	@OneToMany(mappedBy="ecFactsheet", cascade=CascadeType.ALL)
	@Where(clause="is_deleted='false'")
	private List<EcfactsheetPublichearingdetails> hearingDetails=new ArrayList();
	
	@OneToOne(mappedBy="ecFactsheet")
	private EcFactsheetOtherDetails ecFactsheetOtherDetails;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public EcFactsheet() {
		this.is_active = true;
		this.is_deleted = false;
	}
	
	
	
	
	
	

}
