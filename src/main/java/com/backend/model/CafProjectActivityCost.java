package com.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "caf_project_activity_cost", schema = "master")
public class CafProjectActivityCost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "caf_id")
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	@Column(length = 15)
	private double total_existing_cost;
	
	@Column(length = 15)
	private double total_expension_cost;
	
	private double total_cost;
	
	/*
	 * 
	 */
	
	@Column(length = 10)
	private double cp_permanent_employment;
	
	@Column(length = 10)
	private double cp_employment_period;
	
	@Column(length = 12)
	private double cp_man_of_days;
	
	@Column(length = 10)
	private double cp_temp_man_of_days;
	
	@Column(length = 12)
	private double cp_total_employment;
	
	@Column(length = 10)
	private double op_existing_permanent_employment;
	
	@Column(length = 10)
	private double op_existing_employment_period;
	
	private double op_existing_man_in_days;
	
	@Column(length = 10)
	private double op_existing_temp_man_in_days;
	
	@Column(length = 12)
	private double op_existing_total_employment;
	
	@Column(length = 10)
	private double op_proposed_permanent_employment;
	
	private double op_proposed_employment_period;
	
	@Column(length = 10)
	private double op_proposed_man_in_days;
	
	@Column(length = 10)
	private double op_proposed_temp_man_in_days;
	
	@Column(length = 12)
	private double op_proposed_total_employment;
	
	private double op_total_permanent_employment;
	
	private double op_total_employment_period;
	
	@Column(length = 12)
	private double op_total_man_in_days;
	
	@Column(length = 12)
	private double op_total_temp_man_in_days;
	
	@Column(length = 12)
	private double op_total_employment;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public CommonFormDetail getCommonFormDetail() {
		return commonFormDetail;
	}
	public void setCommonFormDetail(CommonFormDetail commonFormDetail) {
		this.commonFormDetail = commonFormDetail;
	}
	public double getTotal_existing_cost() {
		return total_existing_cost;
	}
	public void setTotal_existing_cost(double total_existing_cost) {
		this.total_existing_cost = total_existing_cost;
	}
	public double getTotal_expension_cost() {
		return total_expension_cost;
	}
	public void setTotal_expension_cost(double total_expension_cost) {
		this.total_expension_cost = total_expension_cost;
	}
	public double getTotal_cost() {
		return total_cost;
	}
	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}
	public double getCp_permanent_employment() {
		return cp_permanent_employment;
	}
	public void setCp_permanent_employment(double cp_permanent_employment) {
		this.cp_permanent_employment = cp_permanent_employment;
	}
	public double getCp_employment_period() {
		return cp_employment_period;
	}
	public void setCp_employment_period(double cp_employment_period) {
		this.cp_employment_period = cp_employment_period;
	}
	public double getCp_man_of_days() {
		return cp_man_of_days;
	}
	public void setCp_man_of_days(double cp_man_of_days) {
		this.cp_man_of_days = cp_man_of_days;
	}
	public double getCp_temp_man_of_days() {
		return cp_temp_man_of_days;
	}
	public void setCp_temp_man_of_days(double cp_temp_man_of_days) {
		this.cp_temp_man_of_days = cp_temp_man_of_days;
	}
	public double getCp_total_employment() {
		return cp_total_employment;
	}
	public void setCp_total_employment(double cp_total_employment) {
		this.cp_total_employment = cp_total_employment;
	}
	public double getOp_existing_permanent_employment() {
		return op_existing_permanent_employment;
	}
	public void setOp_existing_permanent_employment(double op_existing_permanent_employment) {
		this.op_existing_permanent_employment = op_existing_permanent_employment;
	}
	public double getOp_existing_employment_period() {
		return op_existing_employment_period;
	}
	public void setOp_existing_employment_period(double op_existing_employment_period) {
		this.op_existing_employment_period = op_existing_employment_period;
	}
	public double getOp_existing_man_in_days() {
		return op_existing_man_in_days;
	}
	public void setOp_existing_man_in_days(double op_existing_man_in_days) {
		this.op_existing_man_in_days = op_existing_man_in_days;
	}
	public double getOp_existing_temp_man_in_days() {
		return op_existing_temp_man_in_days;
	}
	public void setOp_existing_temp_man_in_days(double op_existing_temp_man_in_days) {
		this.op_existing_temp_man_in_days = op_existing_temp_man_in_days;
	}
	public double getOp_existing_total_employment() {
		return op_existing_total_employment;
	}
	public void setOp_existing_total_employment(double op_existing_total_employment) {
		this.op_existing_total_employment = op_existing_total_employment;
	}
	public double getOp_proposed_permanent_employment() {
		return op_proposed_permanent_employment;
	}
	public void setOp_proposed_permanent_employment(double op_proposed_permanent_employment) {
		this.op_proposed_permanent_employment = op_proposed_permanent_employment;
	}
	public double getOp_proposed_employment_period() {
		return op_proposed_employment_period;
	}
	public void setOp_proposed_employment_period(double op_proposed_employment_period) {
		this.op_proposed_employment_period = op_proposed_employment_period;
	}
	public double getOp_proposed_man_in_days() {
		return op_proposed_man_in_days;
	}
	public void setOp_proposed_man_in_days(double op_proposed_man_in_days) {
		this.op_proposed_man_in_days = op_proposed_man_in_days;
	}
	public double getOp_proposed_temp_man_in_days() {
		return op_proposed_temp_man_in_days;
	}
	public void setOp_proposed_temp_man_in_days(double op_proposed_temp_man_in_days) {
		this.op_proposed_temp_man_in_days = op_proposed_temp_man_in_days;
	}
	public double getOp_proposed_total_employment() {
		return op_proposed_total_employment;
	}
	public void setOp_proposed_total_employment(double op_proposed_total_employment) {
		this.op_proposed_total_employment = op_proposed_total_employment;
	}
	public double getOp_total_permanent_employment() {
		return op_total_permanent_employment;
	}
	public void setOp_total_permanent_employment(double op_total_permanent_employment) {
		this.op_total_permanent_employment = op_total_permanent_employment;
	}
	public double getOp_total_employment_period() {
		return op_total_employment_period;
	}
	public void setOp_total_employment_period(double op_total_employment_period) {
		this.op_total_employment_period = op_total_employment_period;
	}
	public double getOp_total_man_in_days() {
		return op_total_man_in_days;
	}
	public void setOp_total_man_in_days(double op_total_man_in_days) {
		this.op_total_man_in_days = op_total_man_in_days;
	}
	public double getOp_total_temp_man_in_days() {
		return op_total_temp_man_in_days;
	}
	public void setOp_total_temp_man_in_days(double op_total_temp_man_in_days) {
		this.op_total_temp_man_in_days = op_total_temp_man_in_days;
	}
	public double getOp_total_employment() {
		return op_total_employment;
	}
	public void setOp_total_employment(double op_total_employment) {
		this.op_total_employment = op_total_employment;
	}
	
}
