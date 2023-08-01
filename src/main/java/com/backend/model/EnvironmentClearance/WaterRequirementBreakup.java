package com.backend.model.EnvironmentClearance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "water_requirement_breakup", schema = "master")
public class WaterRequirementBreakup extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = true, length = 100)
	private String daily_use;

	@Column(nullable = true)
	private Integer idx;

	@Column(nullable = true, length = 50)
	private Double daily_quantity_present;

	@Column(nullable = true, length = 50)
	private Double daily_quantity_expansion;

	@Column(name = "is_active")
	private boolean is_active;

	@Column(name = "is_deleted")
	private boolean is_deleted;

	public WaterRequirementBreakup() {
		this.is_active = true;
		this.is_deleted = false;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDaily_use() {
		return daily_use;
	}

	public void setDaily_use(String daily_use) {
		this.daily_use = daily_use;
	}

	public Double getDaily_quantity_present() {
		return daily_quantity_present;
	}

	public void setDaily_quantity_present(Double daily_quantity_present) {
		this.daily_quantity_present = daily_quantity_present;
	}

	public Double getDaily_quantity_expansion() {
		return daily_quantity_expansion;
	}

	public void setDaily_quantity_expansion(Double daily_quantity_expansion) {
		this.daily_quantity_expansion = daily_quantity_expansion;
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

}
