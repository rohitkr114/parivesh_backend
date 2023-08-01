package com.backend.model.EcForm12;

import com.backend.audit.Auditable;
import com.backend.model.DocumentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "ec_form_12_obtained", schema = "master")
public class EcForm12Obtained extends Auditable<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ec_form_12_project_activity_id", nullable = true)
	@JsonIgnore
	private EcForm12ProjectActivity ecForm12ProjectActivity;
	
	@Column(nullable = true)
	private String ec_obtained_select;

	@Column(nullable = true)
	private Date ec_obtained_date;

	@OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_obtained_document_id", nullable = true)
	private DocumentDetails ec_obtained_document;
	
	@Column(nullable = true)
	private Boolean is_delete;

	@Column(nullable = true)
	private Boolean is_active;
	
	private EcForm12Obtained() {
		this.is_active=true;
		this.is_delete=false;
	}

	public EcForm12Obtained(Integer id, String ec_obtained_select, Date ec_obtained_date) {
		this.id = id;
		this.ec_obtained_select = ec_obtained_select;
		this.ec_obtained_date = ec_obtained_date;
	}
	
}
