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
//@Table(name = "checklist_form_details", schema = "ua")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//public class CheckListFormDetails extends AuditProvider implements Cloneable {
//
//	/** Form-A Part-III (Nodal Officer) CheckList start **/
//	@Column(name = "partI_geo_referenced_map_is_provided")
//	private boolean partIgeoReferencedMapIsProvided;
//
//	@Column(name = "kml_files_matches")
//	private boolean KmlfilesMatches;
//
//	@Column(name = "justification_note_by_user_agency")
//	private boolean justificationNoteByUserAgency;
//	/** Form-A Part-III (Nodal Officer) PART–I (Proposed Diversion) end **/
//
//	/**
//	 * Form-A Part-III (Nodal Officer) PART–II(Compensatory Afforestation) start
//	 */
//	@Column(name = "partII_geo_referenced_map_is_provided")
//	private boolean partIIgeoReferencedMapIsProvided;
//
//	@Column(name = "partII_legal_status_user_agency")
//	private String partIIlegalStatusUserAgency;
//
//	@Column(name = "partII_pccf_approval_obtained")
//	private boolean pccfApprovalObtained;
//
//	@Column(name = "remarks")
//	private String remarks;
//
//	@Column(name = "type_of_ca_land")
//	private String typeOfCALand;
//
//	/** section 4.1 CA **/
//	@Column(name = "kml_file_CA_area_is_double ")
//	private boolean KMLfileGivenCAAreaDouble;
//
//	@Column(name = "ca_patches_fall_in_degrad_forest ")
//	private boolean CAPatchesFallInDegradedForest;
//
//	@Column(name = "ca_patches_more_than_5ha")
//	private boolean CAPatchesGtThan5ha;
//
//	@Column(name = "degraded_forest_suitable_for_plantation")
//	private boolean degradedForestSuitableForPlantation;
//
//	/** CA on Non-Forest Land start **/
//	@Column(name = "kml_file_CA_area_same_with_forest_area")
//	private boolean KMLfileCAAreaSameWithForestArea;
//
//	@Column(name = "canon_forest_land_CA_more_than_5ha")
//	private boolean CANonForestLandCAMoreThan5ha;
//
//	@Column(name = "non_forest_land_suitable_for_plantation ")
//	private boolean nonForestLandSuitableForPlantation;
//	/** CA on Non-Forest Land end **/
//
//	/** CA on Combined (DFL & NFL] start **/
//	@Column(name = "CA_selected_in_same_state")
//	private boolean CASelectedInSameState;
//	/** CA on Combined (DFL & NFL] end **/
//
//	@Column(name = "violation_reported_in_proposed_area")
//	private boolean violationReportedInProposedArea;
//
//	@Column(name = "land_use_plan_is_attached")
//	private boolean landUsePlanIsAttached;
//
//	@Column(name = "project_site_is_specific")
//	private boolean projectSiteIsSpecific;
//
//	@Column(name = "court_case_pertaining_to_project")
//	private boolean courtCasePertainingToProject;
//
//	@Column(name = "court_area_within1_km_distance")
//	private boolean courtAreaWithin1KmDistance;
//
//	public String getRemarks() {
//		return remarks;
//	}
//
//	public void setRemarks(String remarks) {
//		this.remarks = remarks;
//	}
//
//	public boolean getPartIgeoReferencedMapIsProvided() {
//		return partIgeoReferencedMapIsProvided;
//	}
//
//	public void setPartIgeoReferencedMapIsProvided(boolean partIgeoReferencedMapIsProvided) {
//		this.partIgeoReferencedMapIsProvided = partIgeoReferencedMapIsProvided;
//	}
//
//	public boolean getKmlfilesMatches() {
//		return KmlfilesMatches;
//	}
//
//	public void setKmlfilesMatches(boolean kmlfilesMatches) {
//		KmlfilesMatches = kmlfilesMatches;
//	}
//
//	public boolean getJustificationNoteByUserAgency() {
//		return justificationNoteByUserAgency;
//	}
//
//	public void setJustificationNoteByUserAgency(boolean justificationNoteByUserAgency) {
//		this.justificationNoteByUserAgency = justificationNoteByUserAgency;
//	}
//
//	public boolean getPartIIgeoReferencedMapIsProvided() {
//		return partIIgeoReferencedMapIsProvided;
//	}
//
//	public void setPartIIgeoReferencedMapIsProvided(boolean partIIgeoReferencedMapIsProvided) {
//		this.partIIgeoReferencedMapIsProvided = partIIgeoReferencedMapIsProvided;
//	}
//
//	public String getPartIIlegalStatusUserAgency() {
//		return partIIlegalStatusUserAgency;
//	}
//
//	public void setPartIIlegalStatusUserAgency(String partIIlegalStatusUserAgency) {
//		this.partIIlegalStatusUserAgency = partIIlegalStatusUserAgency;
//	}
//
//	public boolean getPccfApprovalObtained() {
//		return pccfApprovalObtained;
//	}
//
//	public void setPccfApprovalObtained(boolean pccfApprovalObtained) {
//		this.pccfApprovalObtained = pccfApprovalObtained;
//	}
//
//	public String getTypeOfCALand() {
//		return typeOfCALand;
//	}
//
//	public void setTypeOfCALand(String typeOfCALand) {
//		this.typeOfCALand = typeOfCALand;
//	}
//
//	public boolean getKMLfileGivenCAAreaDouble() {
//		return KMLfileGivenCAAreaDouble;
//	}
//
//	public void setKMLfileGivenCAAreaDouble(boolean kMLfileGivenCAAreaDouble) {
//		KMLfileGivenCAAreaDouble = kMLfileGivenCAAreaDouble;
//	}
//
//	public boolean getCAPatchesFallInDegradedForest() {
//		return CAPatchesFallInDegradedForest;
//	}
//
//	public void setCAPatchesFallInDegradedForest(boolean cAPatchesFallInDegradedForest) {
//		CAPatchesFallInDegradedForest = cAPatchesFallInDegradedForest;
//	}
//
//	public boolean getCAPatchesGtThan5ha() {
//		return CAPatchesGtThan5ha;
//	}
//
//	public void setCAPatchesGtThan5ha(boolean cAPatchesGtThan5ha) {
//		CAPatchesGtThan5ha = cAPatchesGtThan5ha;
//	}
//
//	public boolean getDegradedForestSuitableForPlantation() {
//		return degradedForestSuitableForPlantation;
//	}
//
//	public void setDegradedForestSuitableForPlantation(boolean degradedForestSuitableForPlantation) {
//		this.degradedForestSuitableForPlantation = degradedForestSuitableForPlantation;
//	}
//
//	public boolean getKMLfileCAAreaSameWithForestArea() {
//		return KMLfileCAAreaSameWithForestArea;
//	}
//
//	public void setKMLfileCAAreaSameWithForestArea(boolean kMLfileCAAreaSameWithForestArea) {
//		KMLfileCAAreaSameWithForestArea = kMLfileCAAreaSameWithForestArea;
//	}
//
//	public boolean getCANonForestLandCAMoreThan5ha() {
//		return CANonForestLandCAMoreThan5ha;
//	}
//
//	public void setCANonForestLandCAMoreThan5ha(boolean cANonForestLandCAMoreThan5ha) {
//		CANonForestLandCAMoreThan5ha = cANonForestLandCAMoreThan5ha;
//	}
//
//	public boolean getNonForestLandSuitableForPlantation() {
//		return nonForestLandSuitableForPlantation;
//	}
//
//	public void setNonForestLandSuitableForPlantation(boolean nonForestLandSuitableForPlantation) {
//		this.nonForestLandSuitableForPlantation = nonForestLandSuitableForPlantation;
//	}
//
//	public boolean getCASelectedInSameState() {
//		return CASelectedInSameState;
//	}
//
//	public void setCASelectedInSameState(boolean cASelectedInSameState) {
//		CASelectedInSameState = cASelectedInSameState;
//	}
//
//	public boolean getViolationReportedInProposedArea() {
//		return violationReportedInProposedArea;
//	}
//
//	public void setViolationReportedInProposedArea(boolean violationReportedInProposedArea) {
//		this.violationReportedInProposedArea = violationReportedInProposedArea;
//	}
//
//	public boolean getLandUsePlanIsAttached() {
//		return landUsePlanIsAttached;
//	}
//
//	public void setLandUsePlanIsAttached(boolean landUsePlanIsAttached) {
//		this.landUsePlanIsAttached = landUsePlanIsAttached;
//	}
//
//	public boolean getProjectSiteIsSpecific() {
//		return projectSiteIsSpecific;
//	}
//
//	public void setProjectSiteIsSpecific(boolean projectSiteIsSpecific) {
//		this.projectSiteIsSpecific = projectSiteIsSpecific;
//	}
//
//	public boolean getCourtCasePertainingToProject() {
//		return courtCasePertainingToProject;
//	}
//
//	public void setCourtCasePertainingToProject(boolean courtCasePertainingToProject) {
//		this.courtCasePertainingToProject = courtCasePertainingToProject;
//	}
//
//	public boolean getCourtAreaWithin1KmDistance() {
//		return courtAreaWithin1KmDistance;
//	}
//
//	public void setCourtAreaWithin1KmDistance(boolean courtAreaWithin1KmDistance) {
//		this.courtAreaWithin1KmDistance = courtAreaWithin1KmDistance;
//	}
//
//	/**
//	 * Form-A Part-III (Nodal Officer) PART–II(Compensatory Afforestation) end
//	 */
//	/** Form-A Part-III (Nodal Officer) CheckList end **/
//
//
//}
