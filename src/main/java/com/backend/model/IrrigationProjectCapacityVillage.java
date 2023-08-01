package com.backend.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.backend.audit.Auditable;
import com.backend.model.EnvironmentClearance.EcIrrigationProjectCapacityVillage;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.WildLifeClearance.WildLifeClearance;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "irrigation_project_capacity_village", schema = "master")
public class IrrigationProjectCapacityVillage extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer Id;

	@Column(name = "caf_id")
	private Integer cafId;

	@ManyToOne
	@JoinColumn(name = "ec_part_b_id")
	@JsonIgnore
	private EcPartB ecPartB;

	@ManyToOne
	@JoinColumn(name = "fc_id", nullable = true)
	@JsonIgnore
	private ForestClearance forestClearance;

	@ManyToOne
	@JoinColumn(name = "wlc_id", nullable = true)
	@JsonIgnore
	private WildLifeClearance wildLifeClearance;

	private Integer state;

	private Integer district;

	private Integer village;

	private Double areaBenefited;

	private boolean isDeleted;

	private boolean isActive;

	public IrrigationProjectCapacityVillage(
			List<EcIrrigationProjectCapacityVillage> ecIrrigationProjectCapacityVillages) {
		super();
		for (EcIrrigationProjectCapacityVillage ecIrrigationProjectCapacityVillage : ecIrrigationProjectCapacityVillages) {

			this.cafId = ecIrrigationProjectCapacityVillage.getEcPartB().getEnvironmentClearence().getCommonFormDetail()
					.getId();
			this.ecPartB = ecIrrigationProjectCapacityVillage.getEcPartB();
			this.state = ecIrrigationProjectCapacityVillage.getState();
			this.district = ecIrrigationProjectCapacityVillage.getDistrict();
			this.village = ecIrrigationProjectCapacityVillage.getVillage();
			this.areaBenefited = ecIrrigationProjectCapacityVillage.getArea_benefited();
			this.isDeleted = ecIrrigationProjectCapacityVillage.isIs_deleted();
		}
	}

	public IrrigationProjectCapacityVillage(EcIrrigationProjectCapacityVillage ecIrrigationProjectCapacityVillages) {
		super();

		this.cafId = ecIrrigationProjectCapacityVillages.getEcPartB().getEnvironmentClearence().getCommonFormDetail()
				.getId();
		this.ecPartB = ecIrrigationProjectCapacityVillages.getEcPartB();
		this.state = ecIrrigationProjectCapacityVillages.getState();
		this.district = ecIrrigationProjectCapacityVillages.getDistrict();
		this.village = ecIrrigationProjectCapacityVillages.getVillage();
		this.areaBenefited = ecIrrigationProjectCapacityVillages.getArea_benefited();
		this.isDeleted = ecIrrigationProjectCapacityVillages.isIs_deleted();

	}

	public IrrigationProjectCapacityVillage(
			List<ForestClearanceIrrigationProjectCapacityVillages> fcIrrigationProjectCapacityVillages, Integer fcId) {
		super();
		for (ForestClearanceIrrigationProjectCapacityVillages forestClearanceIrrigationProjectCapacityVillages : fcIrrigationProjectCapacityVillages) {

			this.cafId = forestClearanceIrrigationProjectCapacityVillages.getForestClearance().getCommonFormDetail()
					.getId();
			this.forestClearance = forestClearanceIrrigationProjectCapacityVillages.getForestClearance();
			this.wildLifeClearance = forestClearanceIrrigationProjectCapacityVillages.getWildLifeClearance();
			this.state = forestClearanceIrrigationProjectCapacityVillages.getState();
			this.district = forestClearanceIrrigationProjectCapacityVillages.getDistrict();
			this.village = forestClearanceIrrigationProjectCapacityVillages.getVillage();
			this.areaBenefited = forestClearanceIrrigationProjectCapacityVillages.getArea_benefited();
			this.isDeleted = forestClearanceIrrigationProjectCapacityVillages.isIs_deleted();
			this.isActive = forestClearanceIrrigationProjectCapacityVillages.isIs_active();
		}
	}

	public IrrigationProjectCapacityVillage(
			ForestClearanceIrrigationProjectCapacityVillages fcIrrigationProjectCapacityVillages) {
		super();
		this.cafId = fcIrrigationProjectCapacityVillages.getForestClearance().getCommonFormDetail().getId();
		this.forestClearance = fcIrrigationProjectCapacityVillages.getForestClearance();
		this.wildLifeClearance = fcIrrigationProjectCapacityVillages.getWildLifeClearance();
		this.state = fcIrrigationProjectCapacityVillages.getState();
		this.district = fcIrrigationProjectCapacityVillages.getDistrict();
		this.village = fcIrrigationProjectCapacityVillages.getVillage();
		this.areaBenefited = fcIrrigationProjectCapacityVillages.getArea_benefited();
		this.isDeleted = fcIrrigationProjectCapacityVillages.isIs_deleted();
		this.isActive = fcIrrigationProjectCapacityVillages.isIs_active();
	}

}
