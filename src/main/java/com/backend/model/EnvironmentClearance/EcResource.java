package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_resource",schema="master")
public class EcResource extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;
	
	@Column(name="resource_type",length=100)
	private String resource_type;
	
	@Column(name="resource_name",length=100)
	private String resource_name;
	
	@Column(name="source",length=100)
	private String source;
	
	@Column(name="quantity_present")
	private String quantity_present;
	
	@Column(name="quantity_expansion")
	private String quantity_expansion;
	
	@Column(name="quantity_unit",length=50)
	private String quantity_unit;
	
	@Column(name="method_of_withdrawl",length=50)
	private String method_of_withdrawl;
	
	@Column(name="distance_from_source")
	private String distance_from_source;
	
	@Column(name="transport_mode",length=50)
	private String transport_mode;
	
	@Column(name="permission_agreement_details",length=100)
	private String permission_agreement_details;
	
	@Column(name="stage",length=25)
	private String stage;
	
	@Column(name="other_information",length=100)
	private String other_information;
	
	@Column(nullable = false)
	private boolean is_active; 
	
	@Column(nullable = false)
	private boolean is_deleted;
	
	
	@ManyToOne                                         
    @JoinColumn(name = "ec_partB_id")                       
    @JsonIgnore                                       
    private EcPartB ecPartB;
	
	
	
	public EcPartB getEcPartB() {
		return ecPartB;
	}
	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getResource_type() {
		return resource_type;
	}
	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getQuantity_unit() {
		return quantity_unit;
	}
	public void setQuantity_unit(String quantity_unit) {
		this.quantity_unit = quantity_unit;
	}
	public String getMethod_of_withdrawl() {
		return method_of_withdrawl;
	}
	public void setMethod_of_withdrawl(String method_of_withdrawl) {
		this.method_of_withdrawl = method_of_withdrawl;
	}
	public String getTransport_mode() {
		return transport_mode;
	}
	public void setTransport_mode(String transport_mode) {
		this.transport_mode = transport_mode;
	}
	public String getPermission_agreement_details() {
		return permission_agreement_details;
	}
	public void setPermission_agreement_details(String permission_agreement_details) {
		this.permission_agreement_details = permission_agreement_details;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getOther_information() {
		return other_information;
	}
	public void setOther_information(String other_information) {
		this.other_information = other_information;
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
	
	public EcResource() {
		this.is_active=true;
		this.is_deleted=false;
	}
	
	
	

}
