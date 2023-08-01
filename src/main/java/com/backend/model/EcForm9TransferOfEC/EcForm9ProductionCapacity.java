package com.backend.model.EcForm9TransferOfEC;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form9_production_capacity", schema="master")
public class EcForm9ProductionCapacity extends Auditable<Integer> {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String production_capacity_mineral_name;
    
    private String production_capacity_mtpa;
    
    private Boolean isActive = true;
    
    private Boolean isDeleted=false;

}
