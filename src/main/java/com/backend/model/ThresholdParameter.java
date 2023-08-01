package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity(name = "ThresholdParameter")
@Table(name = "threshold_parameters", schema = "master")
public class ThresholdParameter extends Auditable<Integer> {

	@Id
	@GeneratedValue(
		    strategy = GenerationType.SEQUENCE,
		    generator = "seq_threshold"
	)
	@SequenceGenerator(
		    name = "seq_threshold",
		    sequenceName = "threshold_sequence",
		    allocationSize =1, 
		    initialValue = 1
	)
	private Integer id;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "activity_id", nullable = false)
	@JsonBackReference(value = "activity_threshold")
	private Activities activities;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "subactivity_id", nullable = false)
	@JsonBackReference(value = "Subactivity_threshold")
	private SubActivities subActivities;

	@Column(nullable = true)
	private String name;

	@Column(nullable = false)
	private int unit;

	@Column(name = "min", nullable = true)
	private Double min;

	@Column(name = "max", nullable = true)
	private Double max;

	@Column(nullable = false)
	private String val;

	private String threshold_unit;

	@Column(nullable = false)
	private String data_type;

	@Column(nullable = false)
	private String rendering_type;

	@Column(nullable = true)
	private String regex;

	@Column(nullable = false)
	private boolean is_active;

	@Column(nullable = false)
	private boolean is_deleted;

	private String unit_name;

	@Column(nullable = false)
	private boolean required;

	@Column(nullable = true)
	private String description;

	public ThresholdParameter() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Activities getActivities() {
		return activities;
	}

	public void setActivities(Activities activities) {
		this.activities = activities;
	}

	public SubActivities getSubActivities() {
		return subActivities;
	}

	public void setSubActivities(SubActivities subActivities) {
		this.subActivities = subActivities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public String getThreshold_unit() {
		return threshold_unit;
	}

	public void setThreshold_unit(String threshold_unit) {
		this.threshold_unit = threshold_unit;
	}

	public String getData_type() {
		return data_type;
	}

	public void setData_type(String data_type) {
		this.data_type = data_type;
	}

	public String getRendering_type() {
		return rendering_type;
	}

	public void setRendering_type(String rendering_type) {
		this.rendering_type = rendering_type;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
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

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

}
