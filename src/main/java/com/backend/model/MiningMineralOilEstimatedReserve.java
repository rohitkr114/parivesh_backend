package com.backend.model;

import javax.persistence.CascadeType;
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

import lombok.Data;

@Data
@Entity
@Table(name = "mining_mineral_oil_estimated_reserve", schema = "master")
public class MiningMineralOilEstimatedReserve extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @Column(nullable = true,length=100)
    private String estimated_reserves_mineral_oil_type;

    @Column(nullable = true)
    private Double estimated_reserves_mineral_oil_fl;

    @Column(nullable = true)
    private Double estimated_reserves_mineral_oil_nfl;

    @Column(nullable = true,length=1000)
    private String estimated_reserves_mineral_oil_remarks;
    
    @Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_deleted;
	
	public MiningMineralOilEstimatedReserve() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
