//package com.backend.model;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Inheritance;
//import javax.persistence.InheritanceType;
//import javax.persistence.Table;
//
//import com.backend.auditFc.AuditProvider;
//
//
//@Entity
//@Table(name = "form_project_details", schema = "ua")
////@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//public class FormProjectDetails extends AuditProvider implements Cloneable {
//
//	@Column(name = "site_inspection_report")
//	private String siteInspectionReport;
//
//	@Column(name = "recommendation_status")
//	private String recommendationStatus;
//
//	@Column(name = "recommended_area")
//	private Double recommendedArea;
//
//	@Column(name = "state_file_no")
//	private String stateFileNo;
//
//	@Column(name = "letter_of_recommendation")
//	private String letterOfRecommendation;
//
//	@Column(name = "signing_authority")
//	private String signingAuthority;
//
//	@Column(name = "total_land_to_be_diverted")
//	private Double totalLandToBeDiverted;
//
//	@Column(name = "total_forest_AreaApplied")
//	private Double totalForestAreaApplied;
//
//	@Column(name = "justification")
//	private String justification;
//
//	public String getRecommendationStatus() {
//		return recommendationStatus;
//	}
//
//	public void setRecommendationStatus(String recommendationStatus) {
//		this.recommendationStatus = recommendationStatus;
//	}
//
//	public Double getRecommendedArea() {
//		return recommendedArea;
//	}
//
//	public void setRecommendedArea(Double recommendedArea) {
//		this.recommendedArea = recommendedArea;
//	}
//
//	public String getStateFileNo() {
//		return stateFileNo;
//	}
//
//	public void setStateFileNo(String stateFileNo) {
//		this.stateFileNo = stateFileNo;
//	}
//
//	public String getLetterOfRecommendation() {
//		return letterOfRecommendation;
//	}
//
//	public void setLetterOfRecommendation(String letterOfRecommendation) {
//		this.letterOfRecommendation = letterOfRecommendation;
//	}
//
//	public String getSigningAuthority() {
//		return signingAuthority;
//	}
//
//	public void setSigningAuthority(String signingAuthority) {
//		this.signingAuthority = signingAuthority;
//	}
//
//	public String getSiteInspectionReport() {
//		return siteInspectionReport;
//	}
//
//	public void setSiteInspectionReport(String siteInspectionReport) {
//		this.siteInspectionReport = siteInspectionReport;
//	}
//
//	public String getJustification() {
//		return justification;
//	}
//
//	public void setJustification(String justification) {
//		this.justification = justification;
//	}
//
//	public Double getTotalLandToBeDiverted() {
//		return totalLandToBeDiverted;
//	}
//
//	public void setTotalLandToBeDiverted(Double totalLandToBeDiverted) {
//		this.totalLandToBeDiverted = totalLandToBeDiverted;
//	}
//
//	public Double getTotalForestAreaApplied() {
//		return totalForestAreaApplied;
//	}
//
//	public void setTotalForestAreaApplied(Double totalForestAreaApplied) {
//		this.totalForestAreaApplied = totalForestAreaApplied;
//	}
//}