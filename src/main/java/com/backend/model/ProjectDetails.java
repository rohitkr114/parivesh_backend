package com.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.backend.audit.Auditable;
import com.backend.dto.UpdatedUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "project_details", schema = "master")
@Audited
//@JsonIgnoreProperties({ "projectAsssignees"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "project_description", length = 1000, nullable = true)
	private String project_description;

	@Column(name = "project_name", length = 1000, nullable = false)
	private String name;
	@Column(name = "is_proposal_expansion", length = 50, nullable = true)
	private String isProposalExpansion;
	@Column(name = "sw_no", length = 100, unique = true, nullable = true)
	private String sw_no;

	@Column(name = "total_land", nullable = true)
	private double total_land;

	@Column(name = "total_project_cost", nullable = true)
	private double total_project_cost;

	@Column(name = "single_window_seq", nullable = false)
	@JsonIgnore
	private Integer single_window_seq;

	// One to One Relation with State Master

	@Column(name = "main_state", nullable = true)
	private Integer main_state;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private State state;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private District district;

	// One to One Relation with Gencode Master
	@Column(name = "project_benefits", nullable = true)
	private Integer Project_benefits;

	@Column(name = "project_benefit_description", nullable = true)
	private String Project_benefit_description;
	
	@Column(name = "aco_project_status", nullable = true)
	public String acoProjectStatus;

	@Transient
	private UpdatedUser updatedByUser;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "employee_to_project", joinColumns = { @JoinColumn(name = "project_id") }, inverseJoinColumns = {
			@JoinColumn(name = "user_id") }, schema = "master")
	@NotAudited
	@JsonIgnore
	private Set<Employee> employees = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "consultant_to_project", joinColumns = {
			@JoinColumn(name = "project_id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_id") }, schema = "master")
	@NotAudited
	@JsonIgnore
	private Set<Consultant> consultants = new HashSet<>();

	@OneToMany(mappedBy = "projectDetails")
	@NotAudited
//	@JsonManagedReference(value = "project_reference")
	private List<CommonFormDetail> commonFormDetails = new ArrayList<>();

	@OneToMany(mappedBy = "projectDetails")
	@NotAudited
	private List<ProponentApplications> proponentApplications = new ArrayList<>();

	/*
	 * @OneToMany(mappedBy = "projectDetails", cascade = CascadeType.ALL)
	 * // @JsonManagedReference("fc_reference") List<ProponentApplications>
	 * proponentApplications = new ArrayList<>();
	 */
	@Column(nullable = true)
	private Integer main_district;

	@Column(nullable = true, length = 1000)
	private String address;

	@Column(name = "is_active")
	private boolean is_active;

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public ProjectDetails() {
	}

	public ProjectDetails(Integer id, Integer main_state) {
		this.id = id;
		this.main_state = main_state;
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
		employee.getProjectDetails().add(this);
	}

	public void addConsultant(Consultant consultant) {
		this.consultants.add(consultant);
		consultant.getProjectDetails().add(this);
	}

	public void removeEmployee(long userid) {
		Employee employee = this.employees.stream().filter(t -> t.getEntityid() == userid).findFirst().orElse(null);
		if (employee != null)
			this.employees.remove(employee);
		employee.getProjectDetails().remove(this);
	}

	public void removeConsultant(long userid) {
		Consultant consultant = this.consultants.stream().filter(t -> t.getEntityid() == userid).findFirst()
				.orElse(null);
		if (consultant != null)
			this.consultants.remove(consultant);
		consultant.getProjectDetails().remove(this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSw_no() {
		return sw_no;
	}

	public void setSw_no(String sw_no) {
		this.sw_no = sw_no;
	}

	public double getTotal_land() {
		return total_land;
	}

	public void setTotal_land(double total_land) {
		this.total_land = total_land;
	}

	public double getTotal_project_cost() {
		return total_project_cost;
	}

	public void setTotal_project_cost(double total_project_cost) {
		this.total_project_cost = total_project_cost;
	}

	public Integer getSingle_window_seq() {
		return single_window_seq;
	}

	public void setSingle_window_seq(Integer single_window_seq) {
		this.single_window_seq = single_window_seq;
	}

	public Integer getMain_state() {
		return main_state;
	}

	public void setMain_state(Integer main_state) {
		this.main_state = main_state;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public String getIsProposalExpansion() {
		return isProposalExpansion;
	}

	public void setIsProposalExpansion(String isProposalExpansion) {
		this.isProposalExpansion = isProposalExpansion;
	}

	public Integer getProject_benefits() {
		return Project_benefits;
	}

	public void setProject_benefits(Integer project_benefits) {
		Project_benefits = project_benefits;
	}

	public String getProject_benefit_description() {
		return Project_benefit_description;
	}

	public void setProject_benefit_description(String project_benefit_description) {
		Project_benefit_description = project_benefit_description;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public List<CommonFormDetail> getCommonFormDetails() {
		return commonFormDetails;
	}

	public void setCommonFormDetails(List<CommonFormDetail> commonFormDetails) {
		this.commonFormDetails = commonFormDetails;
	}

	public List<ProponentApplications> getProponentApplications() {
		return proponentApplications;
	}

	public void setProponentApplications(List<ProponentApplications> proponentApplications) {
		this.proponentApplications = proponentApplications;
	}

	public String getProject_description() {
		return project_description;
	}

	public void setProject_description(String project_description) {
		this.project_description = project_description;
	}

	public UpdatedUser getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(UpdatedUser updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	public Set<Consultant> getConsultants() {
		return consultants;
	}

	public void setConsultants(Set<Consultant> consultants) {
		this.consultants = consultants;
	}

	public Integer getMain_district() {
		return main_district;
	}

	public void setMain_district(Integer main_district) {
		this.main_district = main_district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
