

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
@Table(name = "mom_specific_conditions", schema = "master")
@NoArgsConstructor
@AllArgsConstructor
public class CrzMomSpecificConditionsDto implements Serializable {

private static final long serialVersionUID = 123L;
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
private Integer id;

@Column(name = "mom_id")
private Integer mom_id;

@Column(name = "specific_condition")
private String specific_condition;

@Column(name = "created_by")
private Integer created_by;

@Column(name = "created_on")
private Date created_on;

@Column(name = "updated_by")
private Integer updated_by;

@Column(name = "updated_on")
private Date updated_on;

@Column(name = "remarks")
private String remarks;

}	