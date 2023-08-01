package com.backend.model.Crz;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Where;

import com.backend.audit.Auditable;
import com.backend.model.Clearence;
import com.backend.model.CommonFormDetail;
import com.backend.model.DocumentDetails;
import com.backend.model.ProponentApplications;
import com.backend.model.CampaPaymentCompletion.FcCampaTransactionDetails;
import com.backend.model.EcPartC.EcBaseLineCollections;
import com.backend.model.EcPartC.EcBaseLineDetails;
import com.backend.model.EcPartC.EcPublicHearing;
import com.backend.model.FcFormBPartB.FcFormBPartBLegalStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
@Entity
@Table(name = "crz_basic_details", schema = "master")
public class CrzBasicDetails extends Auditable<Integer> implements Clearence {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "other_effluent_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<OtherEffluent> otherEffluent = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "facility_storage_goods_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<CrzFacilityStorageGoods> crzFacilityStorageGoods = new HashSet<>();
	
	@Column(name = "crz_project_location", nullable = true)
	private String crz_project_location;
	
	@Column(name = "project_located_in", nullable = true)
	private String project_located_in;

	@Column(name = "type_of_project", nullable = true)
	private String type_of_project;

	@Column(name = "total_built_up_area", nullable = true)
	private Double total_built_up_area;

	@Column(name = "built_up_area", nullable = true)
	private Double built_up_area;

	@Column(name = "height_of_structure", nullable = true)
	private Double height_of_structure;

	@Column(name = "fsi_ratio", nullable = true)
	private Double fsi_ratio;

	@Column(name = "governing_town_planning", nullable = true, length = 1000)
	private String governing_town_planning;

	@Column(name = "details_of_provision_of_car", nullable = true, length = 1000)
	private String details_of_provision_of_car;

	@Column(name = "area_of_land_reclamation", nullable = true)
	private Double area_of_land_reclamation;

	@Column(name = "estimated_quantity_for_reclamation", nullable = true)
	private Double estimated_quantity_for_reclamation;

	@Column(name = "carrying_capacity_of_traffic", nullable = true, length = 1000)
	private String carrying_capacity_of_traffic;

	@Column(name = "length_of_pipeline", nullable = true)
	private Double length_of_pipeline;

	@Column(name = "length_traversing_crz_area", nullable = true)
	private Double length_traversing_crz_area;

	@Column(name = "depth_of_excavation", nullable = true)
	private Double depth_of_excavation;

	@Column(name = "width_of_excavation", nullable = true)
	private Double width_of_excavation;

	@Column(name = "length_of_pipeline_from_seashore_to_deep_sea", nullable = true)
	private Double length_of_pipeline_from_seashore_to_deep_sea;

	@Column(name = "depth_of_outfall_point", nullable = true)
	private Double depth_of_outfall_point;

	@Column(name = "temperature_of_effluent", nullable = true)
	private Double temperature_of_effluent;

	@Column(name = "location_of_intake", nullable = true, length = 100)
	private String location_of_intake;

	@Column(name = "location_of_outfall", nullable = true, length = 100)
	private String location_of_outfall;

	@Column(name = "depth_of_outfall_point_marine", nullable = true)
	private Double depth_of_outfall_point_marine;

	@Column(name = "length_of_pipeline_marine", nullable = true)
	private Double length_of_pipeline_marine;

	@Column(name = "length_traversing_crz_area_marine", nullable = true)
	private Double length_traversing_crz_area_marine;

	@Column(name = "depth_of_excavation_marine", nullable = true)
	private Double depth_of_excavation_marine;

	@Column(name = "width_of_excavation_marine", nullable = true)
	private Double width_of_excavation_marine;

	@Column(name = "length_of_pipeline_from_seashore_to_deep_sea_marine", nullable = true)
	private Double length_of_pipeline_from_seashore_to_deep_sea_marine;

	@Column(name = "depth_of_outfall_point_from_surface_of_sea_water_marine", nullable = true)
	private Double depth_of_outfall_point_from_surface_of_sea_water_marine;

	@Column(name = "depth_of_water_at_disposal_point", nullable = true)
	private Double depth_of_water_at_disposal_point;

	@Column(name = "type_of_disposal", nullable = true)
	private String type_of_disposal;

	@Column(name = "name_of_product", nullable = true, length = 100)
	private String name_of_product;

	@Column(name = "type_of_product", nullable = true, length = 100)
	private String type_of_product;

	@Column(name = "no_of_tanks_for_storage", nullable = true)
	private Double no_of_tanks_for_storage;

	@Column(name = "capacity_of_tanks", nullable = true)
	private Double capacity_of_tanks;

	@Column(name = "end_use_of_the_product", nullable = true, length = 100)
	private String end_use_of_the_product;

	@Column(name = "area_of_structure", nullable = true)
	private Double area_of_structure;

	@Column(name = "height_of_structure_offshore", nullable = true)
	private Double height_of_structure_offshore;

	@Column(name = "depth_of_sea_bed", nullable = true)
	private Double depth_of_sea_bed;

	@Column(name = "any_other_information", nullable = true, length = 1000)
	private String any_other_information;

	@Column(name = "capacity_of_desalination", nullable = true)
	private Double capacity_of_desalination;

	@Column(name = "total_brine_generation", nullable = true)
	private Double total_brine_generation;

	@Column(name = "temperature_of_effluent_above_ambient_disposal", nullable = true)
	private Double temperature_of_effluent_above_ambient_disposal;

	@Column(name = "ambient_temperature_at_disposal_point", nullable = true)
	private Double ambient_temperature_at_disposal_point;

	@Column(name = "ambient_salinity", nullable = true)
	private Double ambient_salinity;

	@Column(name = "capacity_of_mining", nullable = true)
	private Double capacity_of_mining;

	@Column(name = "area_to_be_mined", nullable = true)
	private Double area_to_be_mined;

	@Column(name = "type_of_mineral_to_extracted", nullable = true, length = 50)
	private String type_of_mineral_to_extracted;

	@Column(name = "end_use_of_mineral", nullable = true, length = 100)
	private String end_use_of_mineral;

	@Column(name = "capacity", nullable = true)
	private Double capacity;

	@Column(name = "total_area_of_construction", nullable = true)
	private Double total_area_of_construction;

	@Column(name = "compliance_of_effluent_parameters", nullable = true, length = 50)
	private String compliance_of_effluent_parameters;

	@Column(name = "whether_discharge_is_in_sea_creek", nullable = true)
	private boolean whether_discharge_is_in_sea_creek;

	@Column(name = "distance_of_marine_outfall_point", nullable = true)
	private Double distance_of_marine_outfall_point;

	@Column(name = "depth_of_outfall_point_sea_water", nullable = true)
	private Double depth_of_outfall_point_sea_water;

	@Column(name = "depth_of_seabed", nullable = true)
	private Double depth_of_seabed;

	@Column(name = "total_area_of_construction_lighthouse", nullable = true)
	private Double total_area_of_construction_lighthouse;

	@Column(name = "height_of_structure_lighthouse", nullable = true)
	private Double height_of_structure_lighthouse;

	@Column(name = "capacity_of_wind_mills", nullable = true)
	private Double capacity_of_wind_mills;

	@Column(name = "transmission_lines", nullable = true)
	private String transmission_lines;

	@Column(name = "diameter_of_windmill", nullable = true)
	private Double diameter_of_windmill;

	@Column(name = "length_of_blade", nullable = true)
	private Double length_of_blade;

	@Column(name = "speed_of_rotation", nullable = true)
	private Double speed_of_rotation;

	@Column(name = "height_of_structure_wind_mills", nullable = true)
	private Double height_of_structure_wind_mills;

	@Column(name = "please_specify_with_features", nullable = true, length = 1000)
	private String please_specify_with_features;

	@Column(name = "please_specify_product", nullable = true, length = 1000)
	private String please_specify_product;
	
	@Column(name = "effluent_BOD", nullable = true, length = 100)
	private String effluent_BOD;
	
	@Column(name = "effluent_COD", nullable = true, length = 100)
	private String effluent_COD;
	
	@Column(name = "effluent_TSS", nullable = true, length = 100)
	private String effluent_TSS;
	
	@Column(name = "oil_and_grease", nullable = true, length = 100)
	private String oil_and_grease;
	
	@Column(name = "heavy_metals", nullable = true, length = 100)
	private String heavy_metals;
	
	@Column(name = "proposal_no", unique = true, length = 50, nullable = false)
	private String proposal_no;
	
	@Column(nullable = true)
	private Boolean is_amendment;
	
	@Column(nullable = true)
	private Integer parent_id;
	
	@Column(name = "crz_map_indicating_reason", nullable = true, length = 500)
	private String crz_map_indicating_reason;
	
	@Column(name = "crz_map_indicating_crz_reason", nullable = true, length = 500)
	private String crz_map_indicating_crz_reason;
	
	@Column(name = "noc_from_state_pollution_reason", nullable = true, length = 500)
	private String noc_from_state_pollution_reason;
	
	@Column(name = "project_layout_superimposed_on_crz_reason", nullable = true, length = 500)
	private String project_layout_superimposed_on_crz_reason;
	
	@Column(name = "crz_map_reason", nullable = true, length = 500)
	private String crz_map_reason;

	@OneToOne(targetEntity = CommonFormDetail.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "caf_id", nullable = true)
	@JsonIgnore
	private CommonFormDetail commonFormDetail;

	@Transient
	@JsonProperty(access = Access.READ_ONLY)
	private ProponentApplications proponentApplications;

	@OneToOne(mappedBy = "crzBasicDetails")
	private CrzProjectDetails crzProjectDetails;

	@OneToOne(mappedBy = "crzBasicDetails")
	private CrzOtherDetails crzOtherDetails;

	@OneToOne(mappedBy = "crzBasicDetails")
	private CrzUndertaking crzUndertaking;	
	
	@OneToMany(targetEntity = CrzLocationOfProject.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<CrzLocationOfProject> crzLocationOfProjects;
	
	@OneToMany(targetEntity = CoastalRoads.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<CoastalRoads> coastalRoads=new ArrayList();
	
	@OneToMany(targetEntity = DesalinationPlant.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<DesalinationPlant> desalinationPlant= new ArrayList();
	
	@OneToMany(targetEntity = Lighthouse.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<Lighthouse> lighthouse= new ArrayList();
	
	@OneToMany(targetEntity = AtomicMinerals.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<AtomicMinerals> atomicMinerals= new ArrayList();
	
	@OneToMany(targetEntity = OffshoreStructures.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<OffshoreStructures> offshoreStructures= new ArrayList();
	
	@OneToMany(targetEntity = Pipelines.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<Pipelines> pipelines= new ArrayList();
	
	@OneToMany(targetEntity = CivicAmenities.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<CivicAmenities> civicAmenities= new ArrayList();
	
	@OneToMany(targetEntity = SewageTreatmentPlants.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<SewageTreatmentPlants> sewageTreatmentPlants=new ArrayList();
	
	@OneToMany(targetEntity = WindMills.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<WindMills> windMills=new ArrayList();
	
	@OneToMany(targetEntity = MarineDisposalofTreatedEffluent.class, cascade=CascadeType.ALL)
	@JoinColumn(name="crz_basic_details_id", referencedColumnName = "id")
	@Where(clause = "is_deleted ='false'")
	private List<MarineDisposalofTreatedEffluent> marineDisposal=new ArrayList();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "crz_location_details_id", nullable = true)
	@Where(clause = "is_deleted='false'")
	private Set<CrzLocationDetails> crzLocationDetails = new HashSet<>();
	
	@OneToMany(mappedBy="crzBasicDetails", cascade=CascadeType.ALL)
	@Where(clause="is_deleted='false'")
	private List<CrzSolidWaste> crzSolidWastes;
	
	@OneToMany(mappedBy="crzBasicDetails", cascade=CascadeType.ALL)
	@Where(clause="is_deleted='false'")
	private List<CrzWaterRequirement> crzWaterRequirements;
	
	@OneToMany(mappedBy="crzBasicDetails", cascade=CascadeType.ALL)
	@Where(clause="is_deleted='false'")
	private List<CrzDisposal> crzDisposals;

}
