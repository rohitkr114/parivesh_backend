package com.backend.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "crz_mom_delibration", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzMomDelibrationDto implements Serializable {

	private static final long serialVersionUID = 124L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "delibration_id")
	private Integer delibrationId;

	@Column(name = "agenda_id")
	private Integer agendaId;
	
	@Column(name = "proposal_id")
	private String proposalId;

	@Column(name = "mom_id")
	private Integer momId;

	@Column(name = "mom_delibration")
	private String momDelibration;

	@Column(name = "mom_recommendation")
	private String momRecommendation;
	
	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_on")
	private Date updatedOn;

}
