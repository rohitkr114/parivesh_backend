package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ec_demolition_temp_construction",schema ="master")
public class EcDemolitionTempConstruction extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name="type_of_work",length = 50)
	private String type_of_work;
	
	@Column(name="detail")
	private String detail;
	
	@Column(name="demolition_no")
	private Integer demolition_no;
	
	@Column(name="built_area")
	private Integer built_area;
	
	@Column(name="remarks",length = 50)
	private String remarks;

	@Column(nullable = false)  
	private boolean is_active;
	                            	
	@Column(nullable = false)
    private boolean is_deleted;
    
    @ManyToOne                                         
    @JoinColumn(name = "ec_partB_id")                       
    @JsonIgnore                                       
    private EcPartB ecPartB;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getType_of_work() {
		return type_of_work;
	}

	public void setType_of_work(String type_of_work) {
		this.type_of_work = type_of_work;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Integer getDemolition_no() {
		return demolition_no;
	}

	public void setDemolition_no(Integer demolition_no) {
		this.demolition_no = demolition_no;
	}

	public Integer getBuilt_area() {
		return built_area;
	}

	public void setBuilt_area(Integer built_area) {
		this.built_area = built_area;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean isIs_active() {
		return is_active;
	}

	public void setIs_active(boolean is_active) {
		this.is_active = is_active;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public EcDemolitionTempConstruction() {
		this.is_active=true;
		this.is_deleted=false;
	}
    
}
