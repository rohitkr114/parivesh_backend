package com.backend.model;

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
@Table(name = "mining_mineral_oil_reserves",schema = "master")
public class MiningMineralOilReserves extends Auditable<Integer>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(nullable = true, length=100)
    private String mineral_oil_type;

    @Column(nullable = true)
    private Double proved_reserves_resources;

    @Column(nullable = true)
    private Double proved_probable_reserves;

    @Column(nullable = true)
    private Double proved_probable_possible_reserves;

    @Column(nullable = true,length=1000)
    private String mineral_oil_reserves_remarks;

    @Column(nullable = true)
    private Boolean is_estimated_reserves;

	@Column(nullable = true)
	private Boolean is_active;
	
	@Column(nullable = true)
	private Boolean is_deleted;
	
	public MiningMineralOilReserves() {
		this.is_active=true;
		this.is_deleted=false;
	}
}
