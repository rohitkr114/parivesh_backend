package com.backend.model.Crz;

import java.util.Date;

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
@Table(name = "other_effluent", schema = "master")
public class OtherEffluent extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "other_effluent", nullable = true)
	private String other_effluent;
	
	@Column(name = "is_active")
	private Boolean is_active;

	@Column(name = "is_deleted")
	private Boolean is_deleted;
	
	public OtherEffluent() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
