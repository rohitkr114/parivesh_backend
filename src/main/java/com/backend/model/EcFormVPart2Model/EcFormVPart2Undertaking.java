package com.backend.model.EcFormVPart2Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.backend.audit.Auditable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name="ec_form_v_part2_undertaking", schema="master")
public class EcFormVPart2Undertaking extends Auditable<Integer> {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "i_agree", nullable = true)
	private boolean i_agree;
	
	@Column(name = "undertaking_person_name", length = 100, nullable = true)
	private String undertaking_person_name;
	
	@Column(name = "spcb_name", nullable = true)
	private String spcb_name;
	
	@Column(name = "undertaking_person_designation", length = 100, nullable = true)
	private String undertaking_person_designation;
	
	@Column(name = "undertaking_person_esign", length = 255, nullable = true)
	private String undertaking_person_esign;
	
	@Column(name = "undertaking_date", nullable = true)
	private Date undertaking_date;
	
	@Transient
	private Date submission_date;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_form_v_part2_id", nullable = true)
	@JsonIgnore
	private EcFormVPart2 ecFormVPart2;

	private Boolean is_active;

	private Boolean is_delete;
	
	@Column(name = "identification_no", nullable = true)
	private String identification_no;
	
	public EcFormVPart2Undertaking() {
		this.is_active = true;
		this.is_delete = false;
	}
}
