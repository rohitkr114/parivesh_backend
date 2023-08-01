package com.backend.model.FcFormAPartIIINodal;

import javax.persistence.CascadeType;
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

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.DepartmentApplication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "fc_form_A_part_III_nodal_checklist_details", schema = "authority")
public class FcFormAPartIIINodalCheckListDetails extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// For Basic Details
		@OneToOne(mappedBy = "fcFormAPartIIINodalCheckListDetails", cascade = CascadeType.ALL)
		@Where(clause = "is_deleted ='false'")
		private FcFormAPartIIINodalBasicDetails fcFormAPartIIIBasicDetails;
		
	@Column(name = "is_geo_referenced_map_is_provided")
	private boolean isGeoReferencedMapIsProvided;

	@Column(name = "is_kml_files_matches")
	private boolean isKmlfilesMatches;

	@Column(name = "is_justification_note_by_user_agency")
	private boolean isJustificationNoteByUserAgency;

	// part 2 start

	@Column(name = "is_geo_referenced_map_is_provided_partII")
	private boolean isGeoReferencedMapIsProvidedPartII;

	@Column(name = "legal_status_user_agency")
	private String legalStatusUserAgency;

	@Column(name = "is_pccf_approval_obtained")
	private boolean isPccfApprovalObtained;

	@Column(name = "pccf_remarks")
	private String pccfRemarks;

	@Column(name = "type_of_ca_land")
	private String typeOfCALand;

	/** DFL **/
	@Column(name = "is_kml_file_CA_area_is_double ")
	private boolean isKMLfileGivenCAAreaDouble;

	@Column(name = "is_ca_patches_fall_in_degrad_forest ")
	private boolean isCAPatchesFallInDegradedForest;

	@Column(name = "is_ca_patches_more_than_5ha")
	private boolean isCAPatchesGtThan5ha;

	@Column(name = "is_degraded_forest_suitable_for_plantation")
	private boolean isDegradedForestSuitableForPlantation;

	/** NFL **/

	@Column(name = "is_kml_file_CA_area_same_with_forest_area")
	private boolean isKMLfileCAAreaSameWithForestArea;

	@Column(name = "is_canon_forest_land_CA_more_than_5ha")
	private boolean isCanonForestLandCAMoreThan5ha;

	@Column(name = "is_non_forest_land_suitable_for_plantation ")
	private boolean isNonForestLandSuitableForPlantation;

	/** Combined (DFL & NFL] **/
	@Column(name = "is_ca_selected_in_same_state")
	private boolean isCASelectedInSameState;

	@Column(name = "is_violation_reported_in_proposed_area")
	private boolean isViolationReportedInProposedArea;

	@Column(name = "is_land_use_plan_is_attached")
	private boolean isLandUsePlanIsAttached;

	@Column(name = "is_project_site_is_specific")
	private boolean isProjectSiteIsSpecific;

	@Column(name = "is_court_case_pertaining_to_project")
	private boolean isCourtCasePertainingToProject;

	@Column(name = "is_court_area_within1_km_distance")
	private boolean isCourtAreaWithin1KmDistance;

	@Column(name = "is_undertaking")
	private boolean isUndertaking;
	
	@Column(nullable = true)
	private String status;

	@Column(nullable = false)
	private Integer fc_id;
	
	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private DepartmentApplication departmentApplication;
	
	private Boolean is_active;

	private Boolean is_deleted;

	public FcFormAPartIIINodalCheckListDetails() {
		this.is_active = true;
		this.is_deleted = false;
	}

}
