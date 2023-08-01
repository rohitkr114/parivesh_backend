package com.backend.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "ec_project_details", schema = "master")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class EcProjectDetail extends Auditable<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "is_ec_avilable", nullable = false)
	private boolean is_ec_avilable;

	@Column(name = "ec_proposal_no", nullable = true, length = 50)
	private String ec_proposal_no;

	@Column(name = "ec_date_of_issue", nullable = true)
	private Date ec_date_of_issue;

	@Column(name = "ec_moefcc_file_no", nullable = true, length = 80)
	private String ec_moefcc_file_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_letter_id", nullable = true)
	private DocumentDetails ec_letter;

	@Column(name = "is_amendment_obtained", nullable = false)
	private boolean is_amendment_obtained;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "chronology_note", nullable = true)
	private DocumentDetails chronologyNote;

	@Column(name = "project_implementation_status", length = 100, nullable = true)
	private String project_implementation_status;

	@Column(name = "refrence_no_spcb", length = 50, nullable = true)
	private String refrence_no_spcb;

	@Column(name = "activity_located_in_sez", nullable = false)
	private boolean activity_located_in_sez;

	@Column(name = "industrial_area_type", length = 255, nullable = true)
	private String industrial_area_type;

	@Column(name = "industrial_area_name", length = 100, nullable = true)
	private String industrial_area_name;

	@Column(name = "industrial_area_notifictn_year", length = 50, nullable = true)
	private String industrial_area_notifictn_year;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "industrial_area_notificatn_copy", nullable = true)
	private DocumentDetails industrialAreaNotificatnCopy;

	@Column(name = "is_ec_available_for_nia", nullable = true)
	private boolean is_ec_available_for_nia;

	@Column(name = "ec_nia_date_of_issue", nullable = true)
	private Date ec_nia_date_of_issue;

	@Column(name = "ec_nia_moefcc_file_no", nullable = true, length = 80)
	private String ec_nia_moefcc_file_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_nia_letter_id", nullable = true)
	private DocumentDetails ec_nia_letter;

	@Column(name = "no_ec_available_for_nia_reason", length = 500, nullable = true)
	private String no_ec_available_for_nia_reason;

	@Column(name = "is_activity_located_in_crz", nullable = true)
	private boolean is_activity_located_in_crz;

	@Column(name = "crz_classifctn", length = 100, nullable = true)
	private String crz_classifctn;

	@Column(name = "crz_location_type", length = 255, nullable = true)
	private String crz_location_type;

	@Column(name = "is_sensitive_area_present", nullable = true)
	private boolean is_sensitive_area_present;

	@Column(name = "mangroves", nullable = true)
	private boolean mangroves;

	@Column(name = "corals_reef", nullable = true)
	private boolean corals_reef;

	@Column(name = "sand_dunes", nullable = true)
	private boolean sand_dunes;

	@Column(name = "parks_protected_area", nullable = true)
	private boolean parks_protected_area;

	@Column(name = "salt_marshes", nullable = true)
	private boolean salt_marshes;

	@Column(name = "turtle_nesting_grounds", nullable = true)
	private boolean turtle_nesting_grounds;

	@Column(name = "horseshoe_crabs", nullable = true)
	private boolean horseshoe_crabs;

	@Column(name = "sea_gross_beds", nullable = true)
	private boolean sea_gross_beds;

	@Column(name = "nesting_grounds_birds", nullable = true)
	private boolean nesting_grounds_birds;

	@Column(name = "archeological_heritage_sites", nullable = true)
	private boolean archeological_heritage_sites;

	@Column(name = "any_other", nullable = true, length = 300)
	private String any_other;

	@Column(name = "is_sczma_recomm_obtained", nullable = true)
	private boolean is_sczma_recomm_obtained;

	@Column(name = "sczma_recomm_date", nullable = true)
	private Date sczma_recomm_date;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "sczma_recomm_letter", nullable = true)
	private DocumentDetails sczmaRecommLetter;

	@Column(name = "sczma_application_status", length = 255, nullable = true)
	private String sczma_application_status;

	@Column(name = "sczma_application_date", nullable = true)
	private Date sczma_application_date;

	@Column(name = "sczma_application_file_no", length = 250, nullable = true)
	private String sczma_application_file_no;

	@Column(name = "sczma_application_current_status", length = 255, nullable = true)
	private String sczma_application_current_status;

	@Column(name = "no_sczma_application_reason", length = 500, nullable = true)
	private String no_sczma_application_reason;

	@Column(name = "is_located_in_territorial_waters", nullable = true)
	private boolean is_located_in_territorial_waters;

	@Column(name = "is_specific_condition_specified", nullable = true)
	private boolean is_specific_condition_specified;

	@Column(name = "specific_condition_details", length = 500, nullable = true)
	private String specific_condition_details;

	@Column(name = "is_located_in_ecosensitive_zone", nullable = true)
	private boolean is_located_in_ecosensitive_zone;

	@Column(name = "ecosensitive_zone_details", length = 500, nullable = true)
	private String ecosensitive_zone_details;

	@Column(name = "is_consent_under_air_water", nullable = true)
	private Boolean is_consent_under_air_water;

	@Column(name = "consent_ref_no", length = 500, nullable = true)
	private String consent_ref_no;

	@Column(name = "consent_date", nullable = true)
	private Date consent_date;

	@Column(name = "consent_validity", nullable = true)
	private Date consent_validity;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "consent_order_copy_id", nullable = true)
	private DocumentDetails consent_order_copy;

	@Column(name = "consent_reason", length = 500, nullable = true)
	private String consent_reason;

	@Column(name = "is_existing_ec_available", nullable = true)
	private Boolean is_existing_ec_available;

	@Column(name = "ec_proposal_no_ex", length = 100, nullable = true)
	private String ec_proposal_no_ex;

	@Column(name = "issue_date", nullable = true)
	private Date issue_date;

	@Column(name = "ec_file_no", length = 100, nullable = true)
	private String ec_file_no;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "upload_ec_letter_copy_id", nullable = true)
	private DocumentDetails upload_ec_letter_copy;

	@Column(name = "is_amendment_obtained_ex", nullable = true)
	private Boolean is_amendment_obtained_ex;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "chronology_clearances_doc_id", nullable = true)
	private DocumentDetails chronology_clearances_doc;

	@Column(name = "project_activity_status", nullable = true)
	private String project_activity_status;

	@Column(name = "latest_consent_ref", nullable = true)
	private String latest_consent_ref;

	@Column(name = "latest_consent_date", nullable = true)
	private Date latest_consent_date;

	@Column(name = "latest_consent_validity", nullable = true)
	private Date latest_consent_validity;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "latest_consent_copy_id", nullable = true)
	private DocumentDetails latest_consent_copy;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "brief_note_project_status_copy_id", nullable = true)
	private DocumentDetails brief_note_project_status_copy;

	@Column(name = "latest_consent_reason", length = 500, nullable = true)
	private String latest_consent_reason;

	@Column(name = "ec_clearance_reason_type", nullable = true)
	private String ec_clearance_reason_type;

	@Column(name = "ec_commencement_year", nullable = true)
	private String ec_commencement_year;

	@Column(name = "ec_latest_consent_operate", nullable = true)
	private String ec_latest_consent_operate;
	
	@Column(name = "ec_latest_consent_other", nullable = true, length = 500)
	private String ec_latest_consent_other;
	
	@Column(name = "bio_active_mud", nullable = true)
	private Boolean bio_active_mud;

	@Column(name = "ec_latest_consent_date", nullable = true)
	private Date ec_latest_consent_date;

	@Column(name = "ec_latest_consent_validity", nullable = true)
	private Date ec_latest_consent_validity;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_latest_consent_copy_id", nullable = true)
	private DocumentDetails ec_latest_consent_copy;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_amendment_transfer_id", nullable = true)
	@Where(clause = "isDelete = 'false'")
	Set<EcAmendmentTransferDetails> ecAmendmentTransferDetails = new HashSet<>();

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ec_status_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	Set<EcStatus> ecStatus = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ec_id", nullable = false)
	@JsonIgnore
	private EnvironmentClearence enviromentClearence;

	public EcProjectDetail() {
	}

	public boolean isIs_ec_avilable() {
		return is_ec_avilable;
	}

	public void setIs_ec_avilable(boolean is_ec_avilable) {
		this.is_ec_avilable = is_ec_avilable;
	}

	public boolean isIs_amendment_obtained() {
		return is_amendment_obtained;
	}

	public void setIs_amendment_obtained(boolean is_amendment_obtained) {
		this.is_amendment_obtained = is_amendment_obtained;
	}

	public boolean isIs_ec_available_for_nia() {
		return is_ec_available_for_nia;
	}

	public void setIs_ec_available_for_nia(boolean is_ec_available_for_nia) {
		this.is_ec_available_for_nia = is_ec_available_for_nia;
	}

	public boolean isIs_activity_located_in_crz() {
		return is_activity_located_in_crz;
	}

	public void setIs_activity_located_in_crz(boolean is_activity_located_in_crz) {
		this.is_activity_located_in_crz = is_activity_located_in_crz;
	}

	public boolean isIs_sensitive_area_present() {
		return is_sensitive_area_present;
	}

	public void setIs_sensitive_area_present(boolean is_sensitive_area_present) {
		this.is_sensitive_area_present = is_sensitive_area_present;
	}

	public boolean isIs_sczma_recomm_obtained() {
		return is_sczma_recomm_obtained;
	}

	public void setIs_sczma_recomm_obtained(boolean is_sczma_recomm_obtained) {
		this.is_sczma_recomm_obtained = is_sczma_recomm_obtained;
	}

	public boolean isIs_located_in_territorial_waters() {
		return is_located_in_territorial_waters;
	}

	public void setIs_located_in_territorial_waters(boolean is_located_in_territorial_waters) {
		this.is_located_in_territorial_waters = is_located_in_territorial_waters;
	}

	public boolean isIs_specific_condition_specified() {
		return is_specific_condition_specified;
	}

	public void setIs_specific_condition_specified(boolean is_specific_condition_specified) {
		this.is_specific_condition_specified = is_specific_condition_specified;
	}

	public boolean isIs_located_in_ecosensitive_zone() {
		return is_located_in_ecosensitive_zone;
	}

	public void setIs_located_in_ecosensitive_zone(boolean is_located_in_ecosensitive_zone) {
		this.is_located_in_ecosensitive_zone = is_located_in_ecosensitive_zone;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DocumentDetails getChronologyNote() {
		return chronologyNote;
	}

	public void setChronologyNote(DocumentDetails chronologyNote) {
		this.chronologyNote = chronologyNote;
	}

	public String getProject_implementation_status() {
		return project_implementation_status;
	}

	public void setProject_implementation_status(String project_implementation_status) {
		this.project_implementation_status = project_implementation_status;
	}

	public boolean isActivity_located_in_sez() {
		return activity_located_in_sez;
	}

	public void setActivity_located_in_sez(boolean activity_located_in_sez) {
		this.activity_located_in_sez = activity_located_in_sez;
	}

	public String getIndustrial_area_type() {
		return industrial_area_type;
	}

	public void setIndustrial_area_type(String industrial_area_type) {
		this.industrial_area_type = industrial_area_type;
	}

	public String getIndustrial_area_name() {
		return industrial_area_name;
	}

	public void setIndustrial_area_name(String industrial_area_name) {
		this.industrial_area_name = industrial_area_name;
	}

	public String getIndustrial_area_notifictn_year() {
		return industrial_area_notifictn_year;
	}

	public void setIndustrial_area_notifictn_year(String industrial_area_notifictn_year) {
		this.industrial_area_notifictn_year = industrial_area_notifictn_year;
	}

	public DocumentDetails getIndustrialAreaNotificatnCopy() {
		return industrialAreaNotificatnCopy;
	}

	public void setIndustrialAreaNotificatnCopy(DocumentDetails industrialAreaNotificatnCopy) {
		this.industrialAreaNotificatnCopy = industrialAreaNotificatnCopy;
	}

	public String getNo_ec_available_for_nia_reason() {
		return no_ec_available_for_nia_reason;
	}

	public void setNo_ec_available_for_nia_reason(String no_ec_available_for_nia_reason) {
		this.no_ec_available_for_nia_reason = no_ec_available_for_nia_reason;
	}

	public String getCrz_classifctn() {
		return crz_classifctn;
	}

	public void setCrz_classifctn(String crz_classifctn) {
		this.crz_classifctn = crz_classifctn;
	}

	public String getCrz_location_type() {
		return crz_location_type;
	}

	public void setCrz_location_type(String crz_location_type) {
		this.crz_location_type = crz_location_type;
	}

	public Date getSczma_recomm_date() {
		return sczma_recomm_date;
	}

	public void setSczma_recomm_date(Date sczma_recomm_date) {
		this.sczma_recomm_date = sczma_recomm_date;
	}

	public DocumentDetails getSczmaRecommLetter() {
		return sczmaRecommLetter;
	}

	public void setSczmaRecommLetter(DocumentDetails sczmaRecommLetter) {
		this.sczmaRecommLetter = sczmaRecommLetter;
	}

	public String getSczma_application_status() {
		return sczma_application_status;
	}

	public void setSczma_application_status(String sczma_application_status) {
		this.sczma_application_status = sczma_application_status;
	}

	public Date getSczma_application_date() {
		return sczma_application_date;
	}

	public void setSczma_application_date(Date sczma_application_date) {
		this.sczma_application_date = sczma_application_date;
	}

	public String getSczma_application_file_no() {
		return sczma_application_file_no;
	}

	public void setSczma_application_file_no(String sczma_application_file_no) {
		this.sczma_application_file_no = sczma_application_file_no;
	}

	public String getSczma_application_current_status() {
		return sczma_application_current_status;
	}

	public void setSczma_application_current_status(String sczma_application_current_status) {
		this.sczma_application_current_status = sczma_application_current_status;
	}

	public String getNo_sczma_application_reason() {
		return no_sczma_application_reason;
	}

	public void setNo_sczma_application_reason(String no_sczma_application_reason) {
		this.no_sczma_application_reason = no_sczma_application_reason;
	}

	public String getSpecific_condition_details() {
		return specific_condition_details;
	}

	public void setSpecific_condition_details(String specific_condition_details) {
		this.specific_condition_details = specific_condition_details;
	}

	public String getEcosensitive_zone_details() {
		return ecosensitive_zone_details;
	}

	public void setEcosensitive_zone_details(String ecosensitive_zone_details) {
		this.ecosensitive_zone_details = ecosensitive_zone_details;
	}

	public EnvironmentClearence getEnviromentClearence() {
		return enviromentClearence;
	}

	public void setEnviromentClearence(EnvironmentClearence enviromentClearence) {
		this.enviromentClearence = enviromentClearence;
	}

	public String getEc_proposal_no() {
		return ec_proposal_no;
	}

	public void setEc_proposal_no(String ec_proposal_no) {
		this.ec_proposal_no = ec_proposal_no;
	}

	public Date getEc_date_of_issue() {
		return ec_date_of_issue;
	}

	public void setEc_date_of_issue(Date ec_date_of_issue) {
		this.ec_date_of_issue = ec_date_of_issue;
	}

	public String getEc_moefcc_file_no() {
		return ec_moefcc_file_no;
	}

	public void setEc_moefcc_file_no(String ec_moefcc_file_no) {
		this.ec_moefcc_file_no = ec_moefcc_file_no;
	}

	public DocumentDetails getEc_letter() {
		return ec_letter;
	}

	public void setEc_letter(DocumentDetails ec_letter) {
		this.ec_letter = ec_letter;
	}

	public Date getEc_nia_date_of_issue() {
		return ec_nia_date_of_issue;
	}

	public void setEc_nia_date_of_issue(Date ec_nia_date_of_issue) {
		this.ec_nia_date_of_issue = ec_nia_date_of_issue;
	}

	public String getEc_nia_moefcc_file_no() {
		return ec_nia_moefcc_file_no;
	}

	public void setEc_nia_moefcc_file_no(String ec_nia_moefcc_file_no) {
		this.ec_nia_moefcc_file_no = ec_nia_moefcc_file_no;
	}

	public DocumentDetails getEc_nia_letter() {
		return ec_nia_letter;
	}

	public void setEc_nia_letter(DocumentDetails ec_nia_letter) {
		this.ec_nia_letter = ec_nia_letter;
	}

	public boolean isMangroves() {
		return mangroves;
	}

	public void setMangroves(boolean mangroves) {
		this.mangroves = mangroves;
	}

	public boolean isCorals_reef() {
		return corals_reef;
	}

	public void setCorals_reef(boolean corals_reef) {
		this.corals_reef = corals_reef;
	}

	public boolean isSand_dunes() {
		return sand_dunes;
	}

	public void setSand_dunes(boolean sand_dunes) {
		this.sand_dunes = sand_dunes;
	}

	public boolean isParks_protected_area() {
		return parks_protected_area;
	}

	public void setParks_protected_area(boolean parks_protected_area) {
		this.parks_protected_area = parks_protected_area;
	}

	public boolean isSalt_marshes() {
		return salt_marshes;
	}

	public void setSalt_marshes(boolean salt_marshes) {
		this.salt_marshes = salt_marshes;
	}

	public boolean isTurtle_nesting_grounds() {
		return turtle_nesting_grounds;
	}

	public void setTurtle_nesting_grounds(boolean turtle_nesting_grounds) {
		this.turtle_nesting_grounds = turtle_nesting_grounds;
	}

	public boolean isHorseshoe_crabs() {
		return horseshoe_crabs;
	}

	public void setHorseshoe_crabs(boolean horseshoe_crabs) {
		this.horseshoe_crabs = horseshoe_crabs;
	}

	public boolean isSea_gross_beds() {
		return sea_gross_beds;
	}

	public void setSea_gross_beds(boolean sea_gross_beds) {
		this.sea_gross_beds = sea_gross_beds;
	}

	public boolean isNesting_grounds_birds() {
		return nesting_grounds_birds;
	}

	public void setNesting_grounds_birds(boolean nesting_grounds_birds) {
		this.nesting_grounds_birds = nesting_grounds_birds;
	}

	public boolean isArcheological_heritage_sites() {
		return archeological_heritage_sites;
	}

	public void setArcheological_heritage_sites(boolean archeological_heritage_sites) {
		this.archeological_heritage_sites = archeological_heritage_sites;
	}

	public String getAny_other() {
		return any_other;
	}

	public void setAny_other(String any_other) {
		this.any_other = any_other;
	}

}
