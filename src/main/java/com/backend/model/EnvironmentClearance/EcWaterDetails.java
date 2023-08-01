package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ec_water_details",schema = "master")
public class EcWaterDetails extends Auditable<Integer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="is_ground_water_intersection",nullable = true)
	private Boolean Is_Ground_Water_Intersection;
	
	@Column(name="ground_water_intersection_details",nullable = true, length = 200)
	private String Ground_Water_Intersection_Details;
	
	@Column(name="ground_water_intersection_measures",nullable = true, length = 200)
	private String Ground_Water_Intersection_Measures;
	
	@Column(name="ground_water_intersection_approval",nullable = true)
	private Boolean Ground_Water_Intersection_Approval;
	
	@Column(name="is_ground_water_availability",nullable = true)
	private Boolean Is_Ground_Water_Availability;
	
	@Column(name="ground_water_availability_description",nullable = true)
	private String Ground_Water_Availability_Description;	
	
	@Column(name="is_rainwater_harvesting",nullable = true)
	private Boolean Is_Rainwater_Harvesting;
	
	@Column(name="rainwater_harvesting_description",nullable = true)
	private String Rainwater_Harvesting_Description;
	
	@Column(name="rainwater_harvesting_quantity",nullable = true)
	private Double Rainwater_Harvesting_Quantity;
	
	@Column(name="rainwater_harvesting_capacity",nullable = true)
	private Double Rainwater_Harvesting_Capacity;
	
	@Column(name="Rainwater_Capacity",nullable = true)
	private Double Rainwater_Capacity;

	@Column(name="Rainwater_Description",nullable = true, length = 255)
	private String Rainwater_Description;
	
	@Column(name="is_water_conservation",nullable = true)
	private Boolean Is_Water_Conservation;
	
	@Column(name="other_water_conservation_details",nullable = true, length = 300)
	private String Other_Water_Conservation_Details;
	
	@Column(name="is_zld_achieved",nullable = true)
	private Boolean Is_ZLD_Achieved;
	
	@Column(name="zld_achieved_Details",nullable = true, length = 200)
	private String ZLD_Achieved_Details;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_partb_id", nullable = true)
	@JsonIgnore
	private EcPartB ecPartB;
	
	private Boolean is_active;
	
	
	private Boolean is_deleted;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getIs_Ground_Water_Intersection() {
		return Is_Ground_Water_Intersection;
	}

	public void setIs_Ground_Water_Intersection(Boolean is_Ground_Water_Intersection) {
		Is_Ground_Water_Intersection = is_Ground_Water_Intersection;
	}

	public String getGround_Water_Intersection_Details() {
		return Ground_Water_Intersection_Details;
	}

	public void setGround_Water_Intersection_Details(String ground_Water_Intersection_Details) {
		Ground_Water_Intersection_Details = ground_Water_Intersection_Details;
	}

	public String getGround_Water_Intersection_Measures() {
		return Ground_Water_Intersection_Measures;
	}

	public void setGround_Water_Intersection_Measures(String ground_Water_Intersection_Measures) {
		Ground_Water_Intersection_Measures = ground_Water_Intersection_Measures;
	}

	public Boolean getGround_Water_Intersection_Approval() {
		return Ground_Water_Intersection_Approval;
	}

	public void setGround_Water_Intersection_Approval(Boolean ground_Water_Intersection_Approval) {
		Ground_Water_Intersection_Approval = ground_Water_Intersection_Approval;
	}

	public Boolean getIs_Ground_Water_Availability() {
		return Is_Ground_Water_Availability;
	}

	public void setIs_Ground_Water_Availability(Boolean is_Ground_Water_Availability) {
		Is_Ground_Water_Availability = is_Ground_Water_Availability;
	}

	public String getGround_Water_Availability_Description() {
		return Ground_Water_Availability_Description;
	}

	public void setGround_Water_Availability_Description(String ground_Water_Availability_Description) {
		Ground_Water_Availability_Description = ground_Water_Availability_Description;
	}

	public Boolean getIs_Rainwater_Harvesting() {
		return Is_Rainwater_Harvesting;
	}

	public void setIs_Rainwater_Harvesting(Boolean is_Rainwater_Harvesting) {
		Is_Rainwater_Harvesting = is_Rainwater_Harvesting;
	}

	public String getRainwater_Harvesting_Description() {
		return Rainwater_Harvesting_Description;
	}

	public void setRainwater_Harvesting_Description(String rainwater_Harvesting_Description) {
		Rainwater_Harvesting_Description = rainwater_Harvesting_Description;
	}

	public Double getRainwater_Harvesting_Quantity() {
		return Rainwater_Harvesting_Quantity;
	}

	public void setRainwater_Harvesting_Quantity(Double rainwater_Harvesting_Quantity) {
		Rainwater_Harvesting_Quantity = rainwater_Harvesting_Quantity;
	}

	public Double getRainwater_Harvesting_Capacity() {
		return Rainwater_Harvesting_Capacity;
	}

	public void setRainwater_Harvesting_Capacity(Double rainwater_Harvesting_Capacity) {
		Rainwater_Harvesting_Capacity = rainwater_Harvesting_Capacity;
	}

	public Boolean getIs_Water_Conservation() {
		return Is_Water_Conservation;
	}

	public void setIs_Water_Conservation(Boolean is_Water_Conservation) {
		Is_Water_Conservation = is_Water_Conservation;
	}

	public String getOther_Water_Conservation_Details() {
		return Other_Water_Conservation_Details;
	}

	public void setOther_Water_Conservation_Details(String other_Water_Conservation_Details) {
		Other_Water_Conservation_Details = other_Water_Conservation_Details;
	}

	public Boolean getIs_ZLD_Achieved() {
		return Is_ZLD_Achieved;
	}

	public void setIs_ZLD_Achieved(Boolean is_ZLD_Achieved) {
		Is_ZLD_Achieved = is_ZLD_Achieved;
	}

	public String getZLD_Achieved_Details() {
		return ZLD_Achieved_Details;
	}

	public void setZLD_Achieved_Details(String zLD_Achieved_Details) {
		ZLD_Achieved_Details = zLD_Achieved_Details;
	}

	public EcPartB getEcPartB() {
		return ecPartB;
	}

	public void setEcPartB(EcPartB ecPartB) {
		this.ecPartB = ecPartB;
	}

	public Boolean getIs_active() {
		return is_active;
	}

	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}

	public Boolean getIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(Boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public Double getRainwater_Capacity() {
		return Rainwater_Capacity;
	}

	public void setRainwater_Capacity(Double rainwater_Capacity) {
		Rainwater_Capacity = rainwater_Capacity;
	}

	public String getRainwater_Description() {
		return Rainwater_Description;
	}

	public void setRainwater_Description(String rainwater_Description) {
		Rainwater_Description = rainwater_Description;
	}

	public EcWaterDetails() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
