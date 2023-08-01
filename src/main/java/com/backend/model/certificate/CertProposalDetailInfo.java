package com.backend.model.certificate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.backend.dto.EcPartA.EnvironmentClearanceProjectActivityDetailsDto;
import com.backend.model.DocumentDetails;
import com.backend.model.EnvironmentClearance.MiningMineralsMined;
import com.backend.model.StandardTORCertificate.EcAmendmentCertificatePlantEquipment;
import com.backend.model.StandardTORCertificate.EcFreshLetterCertificateDetailOfProduct;
import com.backend.model.StandardTORCertificate.EcFreshLetterCertificatePlantEquipment;
import com.backend.model.StandardTORCertificate.EcFreshLetterCertificateSpecificConditions;
import com.backend.model.StandardTORCertificate.EcFreshLetterCertificateStandardConditions;
import com.backend.model.StandardTORCertificate.EcMiningExpansionTemplateDetailOfMineralProduct;
import com.backend.model.StandardTORCertificate.EcMiningExpansionTemplateSpecificEcConditions;
import com.backend.model.StandardTORCertificate.EcMiningExpansionTemplateStandardEcConditions;
import com.backend.model.StandardTORCertificate.EcMiningFreshLetterCertificateDetailOfMinerals;
import com.backend.model.StandardTORCertificate.EcMiningFreshLetterCertificateSpecificConditions;
import com.backend.model.StandardTORCertificate.EcMiningFreshLetterCertificateStandardConditions;
import com.backend.model.StandardTORCertificate.EcTorAmendmentCertificatePlantEquipment;
import com.backend.model.StandardTORCertificate.RejectionTorCertificateDetailOfProduct;
import com.backend.model.StandardTORCertificate.RejectionTorCertificatePlantEquipment;
import com.backend.model.StandardTORCertificate.SpecificConditions;
import com.backend.model.StandardTORCertificate.SpecificTorCertificateDetailOfProduct;
import com.backend.model.StandardTORCertificate.SpecificTorCertificatePlantEquipment;
import com.backend.model.StandardTORCertificate.StandardTorCertificateDetailOfProduct;
import com.backend.model.StandardTORCertificate.StandardTorCertificatePlantEquipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class CertProposalDetailInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Integer> projectActivityId = new ArrayList<>();
	List<String> projectActivity = new ArrayList<>();
	private Integer id;
	private Integer form10Id;
	private String applicabilityOfGeneralConditions;
	private String applicabilityOfSpecificConditions;
	private String category;
	private String clearanceType;
	private String companyname;
	private String companyName;
	private String date;
	private String fileNo;
	private String stateFileNo;

	public String getStateFileNo() {
		return stateFileNo;
	}

	public void setStateFileNo(String stateFileNo) {
		this.stateFileNo = stateFileNo;
	}

	private String issuingAuthority;
	private String locationOfProject;
	private String nameOfOrganization;
	private String nameOfProject;
	private String projectIncludedScheduleNo;
	private String proponent;
	private String proponentEmail;
	private String registeredAddress;
	private String standardPara;
	private String standardSir;
	private String standardSubject;
	private String status;
	private String statusOfImplementationProject;
	private String torDate;
	private Integer cafId;
	private Integer proponentId;
	private Integer ecId;
	private String proposal_No;
	private Boolean isActive;
	private int version;
	private String filePath;

	private String barCodeUrl;
	private List<SpecificConditions> specificConditionArray;
	private List<StandardTorCertificatePlantEquipment> plantEquipmentArray = new ArrayList<>();
	private List<SpecificTorCertificatePlantEquipment> plantEquipmentsSpecific = new ArrayList<>();
	private List<RejectionTorCertificatePlantEquipment> plantEquipmentsRejection = new ArrayList<>();
	private List<EcTorAmendmentCertificatePlantEquipment> amendmentCertificatePlantEquipments = new ArrayList<>();
	private List<EcAmendmentCertificatePlantEquipment> ecAmendmentCertificatePlantEquipments = new ArrayList<>();
	private List<StandardTorCertificateDetailOfProduct> detailsOfProducts = new ArrayList<>();
	private List<SpecificTorCertificateDetailOfProduct> detailsOfProductsSpecific = new ArrayList<>();
	private List<RejectionTorCertificateDetailOfProduct> detailsOfProductsRejection = new ArrayList<>();
	private Set<EcMiningExpansionTemplateStandardEcConditions> standardEcConditionArray = new HashSet<>();
	private Set<EcMiningExpansionTemplateSpecificEcConditions> specificEcConditionArray = new HashSet<>();
	private Set<EcMiningExpansionTemplateDetailOfMineralProduct> detailsOfMineralsProduct = new HashSet<>();
	private Set<EcMiningFreshLetterCertificateDetailOfMinerals> detailOfMineralsArray = new HashSet<>();
	private Set<EcMiningFreshLetterCertificateStandardConditions> standardConditions = new HashSet<>();
	private Set<EcMiningFreshLetterCertificateSpecificConditions> specificConditions = new HashSet<>();
	// ##########################################################################################
	private Set<EcFreshLetterCertificateSpecificConditions> freshLetterSpecificCondition = new HashSet<>();
	private Set<EcFreshLetterCertificateStandardConditions> freshLetterStandardCondition = new HashSet<>();
	private Set<EcFreshLetterCertificatePlantEquipment> ecFreshLetterCertificatePlantEquipment = new HashSet<>();
	private Set<EcFreshLetterCertificateDetailOfProduct> ecFreshLetterCertificateDetailOfProduct = new HashSet<>();
	private IndustryAndTransportDetails industryAndTransportDetails;
	private String identificationNo;
	private String rnrInvolved;
	private Date dateOfSubmission;
	private Double totalExcavationMtpa;
	private Double totalExcavationAnnum;
	private String strippingRatio;
	private String excavationOtherInfo;
	private String leasePeriod;
	private String coalBeneficiation;
	private String nameOfPreviousLessee;
	private String nameOfSuccessfulBidder;
	private String dateOfValidity;
	private String torIdentificationNumber;
	private Double coalBeneficiationCapacity;
	private boolean proposedToInstallCrusher;
	private Integer numberOfCrusher;
	private Integer numberOfYears;
	private Double capacityOfCrusher;
	private Double totalCapacityOfCrusher;
	private Double waterBody;
	private String generalConditions;
	private String folderDir;
	private List<com.backend.dto.StandadCertificateDto.DetailsOfMineralProductAndByProduct> DetailsOfMineralProductAndByProduct = new ArrayList<>();
	private double totalNonForestLand;

	private String non_forest_land;

	private String forest_land;
	private String totalLand;
	private String rnrStatus;
	private double projectCost;

	private String project_cost;
	private String categorySafeSemiCriticalOverExploited;
	private Boolean measuresToRechargeGroundWater;
	private String detailsOfReuseRecycleOfWasteWater;
	private String detailOfProposedCSTPETP;
	private Double forestLandB;
	private boolean waterRequirement;
	private String eac;
	private Double empCost;
	private String emp_cost;
	private String employmentDetails;
	private String employment_details;
	private Set<MiningMineralsMined> miningMineralsMineds;
	private String details_of_transferee;
	private String detailsOfTransferee;
	private String detailsOfTransferor;
	private String details_of_transferor;
	private String details_of_prior_tor;
	private String transferee_name;

	private String transferer_name;
	private Integer projectID;
	private String details_of_Letter_of_Intent;
	private String other_information_Tax_Excavation;
	private String mine_lease_validity;
	private String life_of_Mine;
	private String capacity_of_beneficiation;
	private String proposed_process;
	private String beneficiation_washing_Technology;
	private String other_information_Proposed_Beneficiation;
	private String date_of_Public_Consultation;

	private String date_consultation;
	private String latitude_longitude_of__project;
	private String latitude_longitude_project;
	private String no_of_Trees_to_be_cut;
	private String area_covered_with_plantation;
	private String no_of_Trees_proposed_for_transplantation;
	private String green_Belt;
	private String details_of_the_Ground_Water_table;
	private String category_safe_exploited;
	private String measures_to_recharge_ground_water;
	private String details_of_recycle_of_wastewater;
	private String details_of_proposed_common_off_site;
	private String details_of_proposed_setup_on_Site_Sewage_Treatment_Plant;
	private String source_Wise_Probable_Air_Pollutants;
	private String benefits_of_the_Project;
	private String details_of_previous_EC_Rejection;
	private String terms_of_Reference_for_undertaking;
	// EC Form 7 implemented fields
	private String ecDate;
	private String ecValidity;
	private Double areaExistInHaANonForestLandA;
	private Double additionalAreaExistInHaBNonForestLandA;

	private Double areaExistInHaAForestLandB;
	private Double additionalAreaExistInBForestLandB;

	private Double subTotalAandBForHaA;
	private Double subTotalAandBforHaB;

	private Double totalNonForestLandtotalHaAandB;
	private Double totalForestLandtotalHaAandB;

	private String employmentDetailsApplicantName;
	private String employmentDetailsApplicantEmail;

	private Double totalAandB;

	private String standardEcConditions;

	private String specificEcConditions;
	private String validity_ec;
	private String standard_condition;
	private String specific_condition;

	private String sector;
	private String proposal_for;
	private String proposal_for_old;
	private String copy_to;

	private String copyTo;
	private Boolean sub_activity_condition_applicability;
	private List<EnvironmentClearanceProjectActivityDetailsDto> ecProjectActivityDetails = new ArrayList<>();
	private String reason;
	private Boolean WhetherAnyAmendmentToTheEarlierECHasBeenSought;
	private Boolean is_for_old_proposal;

	private String additionalTerm;
	private DocumentDetails additional_document;

	private Boolean is_details_project_table_deleted;

	private Boolean is_details_product_table_deleted;

	private String need_of_project;

	private String standardPara1;
	private String standardPara2;
	private String standardPara3;
	private String standardPara4;
	private String standardPara5;

	public List<Integer> getProjectActivityId() {
		return projectActivityId;
	}

	public void setProjectActivityId(List<Integer> projectActivityId) {
		this.projectActivityId = projectActivityId;
	}

	public List<String> getProjectActivity() {
		return projectActivity;
	}

	public void setProjectActivity(List<String> projectActivity) {
		this.projectActivity = projectActivity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getForm10Id() {
		return form10Id;
	}

	public void setForm10Id(Integer form10Id) {
		this.form10Id = form10Id;
	}

	public String getApplicabilityOfGeneralConditions() {
		return applicabilityOfGeneralConditions;
	}

	public void setApplicabilityOfGeneralConditions(String applicabilityOfGeneralConditions) {
		this.applicabilityOfGeneralConditions = applicabilityOfGeneralConditions;
	}

	public String getApplicabilityOfSpecificConditions() {
		return applicabilityOfSpecificConditions;
	}

	public void setApplicabilityOfSpecificConditions(String applicabilityOfSpecificConditions) {
		this.applicabilityOfSpecificConditions = applicabilityOfSpecificConditions;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getClearanceType() {
		return clearanceType;
	}

	public void setClearanceType(String clearanceType) {
		this.clearanceType = clearanceType;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFileNo() {
		return fileNo;
	}

	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}

	public String getIssuingAuthority() {
		return issuingAuthority;
	}

	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}

	public String getLocationOfProject() {
		return locationOfProject;
	}

	public void setLocationOfProject(String locationOfProject) {
		this.locationOfProject = locationOfProject;
	}

	public String getNameOfOrganization() {
		return nameOfOrganization;
	}

	public void setNameOfOrganization(String nameOfOrganization) {
		this.nameOfOrganization = nameOfOrganization;
	}

	public String getNameOfProject() {
		return nameOfProject;
	}

	public void setNameOfProject(String nameOfProject) {
		this.nameOfProject = nameOfProject;
	}

	public String getProjectIncludedScheduleNo() {
		return projectIncludedScheduleNo;
	}

	public void setProjectIncludedScheduleNo(String projectIncludedScheduleNo) {
		this.projectIncludedScheduleNo = projectIncludedScheduleNo;
	}

	public String getProponent() {
		return proponent;
	}

	public void setProponent(String proponent) {
		this.proponent = proponent;
	}

	public String getProponentEmail() {
		return proponentEmail;
	}

	public void setProponentEmail(String proponentEmail) {
		this.proponentEmail = proponentEmail;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public String getStandardPara() {
		return standardPara;
	}

	public void setStandardPara(String standardPara) {
		this.standardPara = standardPara;
	}

	public String getStandardSir() {
		return standardSir;
	}

	public void setStandardSir(String standardSir) {
		this.standardSir = standardSir;
	}

	public String getStandardSubject() {
		return standardSubject;
	}

	public void setStandardSubject(String standardSubject) {
		this.standardSubject = standardSubject;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusOfImplementationProject() {
		return statusOfImplementationProject;
	}

	public void setStatusOfImplementationProject(String statusOfImplementationProject) {
		this.statusOfImplementationProject = statusOfImplementationProject;
	}

	public String getTorDate() {
		return torDate;
	}

	public void setTorDate(String torDate) {
		this.torDate = torDate;
	}

	public Integer getCafId() {
		return cafId;
	}

	public void setCafId(Integer cafId) {
		this.cafId = cafId;
	}

	public Integer getProponentId() {
		return proponentId;
	}

	public void setProponentId(Integer proponentId) {
		this.proponentId = proponentId;
	}

	public Integer getEcId() {
		return ecId;
	}

	public void setEcId(Integer ecId) {
		this.ecId = ecId;
	}

	public String getProposal_No() {
		return proposal_No;
	}

	public void setProposal_No(String proposal_No) {
		this.proposal_No = proposal_No;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getBarCodeUrl() {
		return barCodeUrl;
	}

	public void setBarCodeUrl(String barCodeUrl) {
		this.barCodeUrl = barCodeUrl;
	}

	public List<SpecificConditions> getSpecificConditionArray() {
		return specificConditionArray;
	}

	public void setSpecificConditionArray(List<SpecificConditions> specificConditionArray) {
		this.specificConditionArray = specificConditionArray;
	}

	public List<StandardTorCertificatePlantEquipment> getPlantEquipmentArray() {
		return plantEquipmentArray;
	}

	public void setPlantEquipmentArray(List<StandardTorCertificatePlantEquipment> plantEquipmentArray) {
		this.plantEquipmentArray = plantEquipmentArray;
	}

	public List<SpecificTorCertificatePlantEquipment> getPlantEquipmentsSpecific() {
		return plantEquipmentsSpecific;
	}

	public void setPlantEquipmentsSpecific(List<SpecificTorCertificatePlantEquipment> plantEquipmentsSpecific) {
		this.plantEquipmentsSpecific = plantEquipmentsSpecific;
	}

	public List<RejectionTorCertificatePlantEquipment> getPlantEquipmentsRejection() {
		return plantEquipmentsRejection;
	}

	public void setPlantEquipmentsRejection(List<RejectionTorCertificatePlantEquipment> plantEquipmentsRejection) {
		this.plantEquipmentsRejection = plantEquipmentsRejection;
	}

	public List<EcTorAmendmentCertificatePlantEquipment> getAmendmentCertificatePlantEquipments() {
		return amendmentCertificatePlantEquipments;
	}

	public void setAmendmentCertificatePlantEquipments(
			List<EcTorAmendmentCertificatePlantEquipment> amendmentCertificatePlantEquipments) {
		this.amendmentCertificatePlantEquipments = amendmentCertificatePlantEquipments;
	}

	public List<EcAmendmentCertificatePlantEquipment> getEcAmendmentCertificatePlantEquipments() {
		return ecAmendmentCertificatePlantEquipments;
	}

	public void setEcAmendmentCertificatePlantEquipments(
			List<EcAmendmentCertificatePlantEquipment> ecAmendmentCertificatePlantEquipments) {
		this.ecAmendmentCertificatePlantEquipments = ecAmendmentCertificatePlantEquipments;
	}

	public List<StandardTorCertificateDetailOfProduct> getDetailsOfProducts() {
		return detailsOfProducts;
	}

	public void setDetailsOfProducts(List<StandardTorCertificateDetailOfProduct> detailsOfProducts) {
		this.detailsOfProducts = detailsOfProducts;
	}

	public List<SpecificTorCertificateDetailOfProduct> getDetailsOfProductsSpecific() {
		return detailsOfProductsSpecific;
	}

	public void setDetailsOfProductsSpecific(List<SpecificTorCertificateDetailOfProduct> detailsOfProductsSpecific) {
		this.detailsOfProductsSpecific = detailsOfProductsSpecific;
	}

	public List<RejectionTorCertificateDetailOfProduct> getDetailsOfProductsRejection() {
		return detailsOfProductsRejection;
	}

	public void setDetailsOfProductsRejection(List<RejectionTorCertificateDetailOfProduct> detailsOfProductsRejection) {
		this.detailsOfProductsRejection = detailsOfProductsRejection;
	}

	public Set<EcMiningExpansionTemplateStandardEcConditions> getStandardEcConditionArray() {
		return standardEcConditionArray;
	}

	public void setStandardEcConditionArray(
			Set<EcMiningExpansionTemplateStandardEcConditions> standardEcConditionArray) {
		this.standardEcConditionArray = standardEcConditionArray;
	}

	public Set<EcMiningExpansionTemplateSpecificEcConditions> getSpecificEcConditionArray() {
		return specificEcConditionArray;
	}

	public void setSpecificEcConditionArray(
			Set<EcMiningExpansionTemplateSpecificEcConditions> specificEcConditionArray) {
		this.specificEcConditionArray = specificEcConditionArray;
	}

	public Set<EcMiningExpansionTemplateDetailOfMineralProduct> getDetailsOfMineralsProduct() {
		return detailsOfMineralsProduct;
	}

	public void setDetailsOfMineralsProduct(
			Set<EcMiningExpansionTemplateDetailOfMineralProduct> detailsOfMineralsProduct) {
		this.detailsOfMineralsProduct = detailsOfMineralsProduct;
	}

	public Set<EcMiningFreshLetterCertificateDetailOfMinerals> getDetailOfMineralsArray() {
		return detailOfMineralsArray;
	}

	public void setDetailOfMineralsArray(Set<EcMiningFreshLetterCertificateDetailOfMinerals> detailOfMineralsArray) {
		this.detailOfMineralsArray = detailOfMineralsArray;
	}

	public Set<EcMiningFreshLetterCertificateStandardConditions> getStandardConditions() {
		return standardConditions;
	}

	public void setStandardConditions(Set<EcMiningFreshLetterCertificateStandardConditions> standardConditions) {
		this.standardConditions = standardConditions;
	}

	public Set<EcMiningFreshLetterCertificateSpecificConditions> getSpecificConditions() {
		return specificConditions;
	}

	public void setSpecificConditions(Set<EcMiningFreshLetterCertificateSpecificConditions> specificConditions) {
		this.specificConditions = specificConditions;
	}

	public Set<EcFreshLetterCertificateSpecificConditions> getFreshLetterSpecificCondition() {
		return freshLetterSpecificCondition;
	}

	public void setFreshLetterSpecificCondition(
			Set<EcFreshLetterCertificateSpecificConditions> freshLetterSpecificCondition) {
		this.freshLetterSpecificCondition = freshLetterSpecificCondition;
	}

	public Set<EcFreshLetterCertificateStandardConditions> getFreshLetterStandardCondition() {
		return freshLetterStandardCondition;
	}

	public void setFreshLetterStandardCondition(
			Set<EcFreshLetterCertificateStandardConditions> freshLetterStandardCondition) {
		this.freshLetterStandardCondition = freshLetterStandardCondition;
	}

	public Set<EcFreshLetterCertificatePlantEquipment> getEcFreshLetterCertificatePlantEquipment() {
		return ecFreshLetterCertificatePlantEquipment;
	}

	public void setEcFreshLetterCertificatePlantEquipment(
			Set<EcFreshLetterCertificatePlantEquipment> ecFreshLetterCertificatePlantEquipment) {
		this.ecFreshLetterCertificatePlantEquipment = ecFreshLetterCertificatePlantEquipment;
	}

	public Set<EcFreshLetterCertificateDetailOfProduct> getEcFreshLetterCertificateDetailOfProduct() {
		return ecFreshLetterCertificateDetailOfProduct;
	}

	public void setEcFreshLetterCertificateDetailOfProduct(
			Set<EcFreshLetterCertificateDetailOfProduct> ecFreshLetterCertificateDetailOfProduct) {
		this.ecFreshLetterCertificateDetailOfProduct = ecFreshLetterCertificateDetailOfProduct;
	}

	public IndustryAndTransportDetails getIndustryAndTransportDetails() {
		return industryAndTransportDetails;
	}

	public void setIndustryAndTransportDetails(IndustryAndTransportDetails industryAndTransportDetails) {
		this.industryAndTransportDetails = industryAndTransportDetails;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public String getRnrInvolved() {
		return rnrInvolved;
	}

	public void setRnrInvolved(String rnrInvolved) {
		this.rnrInvolved = rnrInvolved;
	}

	public Date getDateOfSubmission() {
		return dateOfSubmission;
	}

	public void setDateOfSubmission(Date dateOfSubmission) {
		this.dateOfSubmission = dateOfSubmission;
	}

	public Double getTotalExcavationMtpa() {
		return totalExcavationMtpa;
	}

	public void setTotalExcavationMtpa(Double totalExcavationMtpa) {
		this.totalExcavationMtpa = totalExcavationMtpa;
	}

	public Double getTotalExcavationAnnum() {
		return totalExcavationAnnum;
	}

	public void setTotalExcavationAnnum(Double totalExcavationAnnum) {
		this.totalExcavationAnnum = totalExcavationAnnum;
	}

	public String getStrippingRatio() {
		return strippingRatio;
	}

	public void setStrippingRatio(String strippingRatio) {
		this.strippingRatio = strippingRatio;
	}

	public String getExcavationOtherInfo() {
		return excavationOtherInfo;
	}

	public void setExcavationOtherInfo(String excavationOtherInfo) {
		this.excavationOtherInfo = excavationOtherInfo;
	}

	public String getLeasePeriod() {
		return leasePeriod;
	}

	public void setLeasePeriod(String leasePeriod) {
		this.leasePeriod = leasePeriod;
	}

	public String getCoalBeneficiation() {
		return coalBeneficiation;
	}

	public void setCoalBeneficiation(String coalBeneficiation) {
		this.coalBeneficiation = coalBeneficiation;
	}

	public String getNameOfPreviousLessee() {
		return nameOfPreviousLessee;
	}

	public void setNameOfPreviousLessee(String nameOfPreviousLessee) {
		this.nameOfPreviousLessee = nameOfPreviousLessee;
	}

	public String getNameOfSuccessfulBidder() {
		return nameOfSuccessfulBidder;
	}

	public void setNameOfSuccessfulBidder(String nameOfSuccessfulBidder) {
		this.nameOfSuccessfulBidder = nameOfSuccessfulBidder;
	}

	public String getDateOfValidity() {
		return dateOfValidity;
	}

	public void setDateOfValidity(String dateOfValidity) {
		this.dateOfValidity = dateOfValidity;
	}

	public String getTorIdentificationNumber() {
		return torIdentificationNumber;
	}

	public void setTorIdentificationNumber(String torIdentificationNumber) {
		this.torIdentificationNumber = torIdentificationNumber;
	}

	public Double getCoalBeneficiationCapacity() {
		return coalBeneficiationCapacity;
	}

	public void setCoalBeneficiationCapacity(Double coalBeneficiationCapacity) {
		this.coalBeneficiationCapacity = coalBeneficiationCapacity;
	}

	public boolean isProposedToInstallCrusher() {
		return proposedToInstallCrusher;
	}

	public void setProposedToInstallCrusher(boolean proposedToInstallCrusher) {
		this.proposedToInstallCrusher = proposedToInstallCrusher;
	}

	public Integer getNumberOfCrusher() {
		return numberOfCrusher;
	}

	public void setNumberOfCrusher(Integer numberOfCrusher) {
		this.numberOfCrusher = numberOfCrusher;
	}

	public Integer getNumberOfYears() {
		return numberOfYears;
	}

	public void setNumberOfYears(Integer numberOfYears) {
		this.numberOfYears = numberOfYears;
	}

	public Double getCapacityOfCrusher() {
		return capacityOfCrusher;
	}

	public void setCapacityOfCrusher(Double capacityOfCrusher) {
		this.capacityOfCrusher = capacityOfCrusher;
	}

	public Double getTotalCapacityOfCrusher() {
		return totalCapacityOfCrusher;
	}

	public void setTotalCapacityOfCrusher(Double totalCapacityOfCrusher) {
		this.totalCapacityOfCrusher = totalCapacityOfCrusher;
	}

	public Double getWaterBody() {
		return waterBody;
	}

	public void setWaterBody(Double waterBody) {
		this.waterBody = waterBody;
	}

	public String getGeneralConditions() {
		return generalConditions;
	}

	public void setGeneralConditions(String generalConditions) {
		this.generalConditions = generalConditions;
	}

	public String getFolderDir() {
		return folderDir;
	}

	public void setFolderDir(String folderDir) {
		this.folderDir = folderDir;
	}

	public List<com.backend.dto.StandadCertificateDto.DetailsOfMineralProductAndByProduct> getDetailsOfMineralProductAndByProduct() {
		return DetailsOfMineralProductAndByProduct;
	}

	public void setDetailsOfMineralProductAndByProduct(
			List<com.backend.dto.StandadCertificateDto.DetailsOfMineralProductAndByProduct> detailsOfMineralProductAndByProduct) {
		DetailsOfMineralProductAndByProduct = detailsOfMineralProductAndByProduct;
	}

	public double getTotalNonForestLand() {
		return totalNonForestLand;
	}

	public void setTotalNonForestLand(double totalNonForestLand) {
		this.totalNonForestLand = totalNonForestLand;
	}

	public String getNon_forest_land() {
		return non_forest_land;
	}

	public void setNon_forest_land(String non_forest_land) {
		this.non_forest_land = non_forest_land;
	}

	public String getForest_land() {
		return forest_land;
	}

	public void setForest_land(String forest_land) {
		this.forest_land = forest_land;
	}

	public String getTotalLand() {
		return totalLand;
	}

	public void setTotalLand(String totalLand) {
		this.totalLand = totalLand;
	}

	public String getRnrStatus() {
		return rnrStatus;
	}

	public void setRnrStatus(String rnrStatus) {
		this.rnrStatus = rnrStatus;
	}

	public double getProjectCost() {
		return projectCost;
	}

	public void setProjectCost(double projectCost) {
		this.projectCost = projectCost;
	}

	public String getProject_cost() {
		return project_cost;
	}

	public void setProject_cost(String project_cost) {
		this.project_cost = project_cost;
	}

	public String getCategorySafeSemiCriticalOverExploited() {
		return categorySafeSemiCriticalOverExploited;
	}

	public void setCategorySafeSemiCriticalOverExploited(String categorySafeSemiCriticalOverExploited) {
		this.categorySafeSemiCriticalOverExploited = categorySafeSemiCriticalOverExploited;
	}

	public Boolean getMeasuresToRechargeGroundWater() {
		return measuresToRechargeGroundWater;
	}

	public void setMeasuresToRechargeGroundWater(Boolean measuresToRechargeGroundWater) {
		this.measuresToRechargeGroundWater = measuresToRechargeGroundWater;
	}

	public String getDetailsOfReuseRecycleOfWasteWater() {
		return detailsOfReuseRecycleOfWasteWater;
	}

	public void setDetailsOfReuseRecycleOfWasteWater(String detailsOfReuseRecycleOfWasteWater) {
		this.detailsOfReuseRecycleOfWasteWater = detailsOfReuseRecycleOfWasteWater;
	}

	public String getDetailOfProposedCSTPETP() {
		return detailOfProposedCSTPETP;
	}

	public void setDetailOfProposedCSTPETP(String detailOfProposedCSTPETP) {
		this.detailOfProposedCSTPETP = detailOfProposedCSTPETP;
	}

	public Double getForestLandB() {
		return forestLandB;
	}

	public void setForestLandB(Double forestLandB) {
		this.forestLandB = forestLandB;
	}

	public boolean isWaterRequirement() {
		return waterRequirement;
	}

	public void setWaterRequirement(boolean waterRequirement) {
		this.waterRequirement = waterRequirement;
	}

	public String getEac() {
		return eac;
	}

	public void setEac(String eac) {
		this.eac = eac;
	}

	public Double getEmpCost() {
		return empCost;
	}

	public void setEmpCost(Double empCost) {
		this.empCost = empCost;
	}

	public String getEmp_cost() {
		return emp_cost;
	}

	public void setEmp_cost(String emp_cost) {
		this.emp_cost = emp_cost;
	}

	public String getEmploymentDetails() {
		return employmentDetails;
	}

	public void setEmploymentDetails(String employmentDetails) {
		this.employmentDetails = employmentDetails;
	}

	public String getEmployment_details() {
		return employment_details;
	}

	public void setEmployment_details(String employment_details) {
		this.employment_details = employment_details;
	}

	public Set<MiningMineralsMined> getMiningMineralsMineds() {
		return miningMineralsMineds;
	}

	public void setMiningMineralsMineds(Set<MiningMineralsMined> miningMineralsMineds) {
		this.miningMineralsMineds = miningMineralsMineds;
	}

	public String getDetails_of_transferee() {
		return details_of_transferee;
	}

	public void setDetails_of_transferee(String details_of_transferee) {
		this.details_of_transferee = details_of_transferee;
	}

	public String getDetailsOfTransferee() {
		return detailsOfTransferee;
	}

	public void setDetailsOfTransferee(String detailsOfTransferee) {
		this.detailsOfTransferee = detailsOfTransferee;
	}

	public String getDetailsOfTransferor() {
		return detailsOfTransferor;
	}

	public void setDetailsOfTransferor(String detailsOfTransferor) {
		this.detailsOfTransferor = detailsOfTransferor;
	}

	public String getDetails_of_transferor() {
		return details_of_transferor;
	}

	public void setDetails_of_transferor(String details_of_transferor) {
		this.details_of_transferor = details_of_transferor;
	}

	public String getDetails_of_prior_tor() {
		return details_of_prior_tor;
	}

	public void setDetails_of_prior_tor(String details_of_prior_tor) {
		this.details_of_prior_tor = details_of_prior_tor;
	}

	public String getTransferee_name() {
		return transferee_name;
	}

	public void setTransferee_name(String transferee_name) {
		this.transferee_name = transferee_name;
	}

	public String getTransferer_name() {
		return transferer_name;
	}

	public void setTransferer_name(String transferer_name) {
		this.transferer_name = transferer_name;
	}

	public Integer getProjectID() {
		return projectID;
	}

	public void setProjectID(Integer projectID) {
		this.projectID = projectID;
	}

	public String getDetails_of_Letter_of_Intent() {
		return details_of_Letter_of_Intent;
	}

	public void setDetails_of_Letter_of_Intent(String details_of_Letter_of_Intent) {
		this.details_of_Letter_of_Intent = details_of_Letter_of_Intent;
	}

	public String getOther_information_Tax_Excavation() {
		return other_information_Tax_Excavation;
	}

	public void setOther_information_Tax_Excavation(String other_information_Tax_Excavation) {
		this.other_information_Tax_Excavation = other_information_Tax_Excavation;
	}

	public String getMine_lease_validity() {
		return mine_lease_validity;
	}

	public void setMine_lease_validity(String mine_lease_validity) {
		this.mine_lease_validity = mine_lease_validity;
	}

	public String getLife_of_Mine() {
		return life_of_Mine;
	}

	public void setLife_of_Mine(String life_of_Mine) {
		this.life_of_Mine = life_of_Mine;
	}

	public String getCapacity_of_beneficiation() {
		return capacity_of_beneficiation;
	}

	public void setCapacity_of_beneficiation(String capacity_of_beneficiation) {
		this.capacity_of_beneficiation = capacity_of_beneficiation;
	}

	public String getProposed_process() {
		return proposed_process;
	}

	public void setProposed_process(String proposed_process) {
		this.proposed_process = proposed_process;
	}

	public String getBeneficiation_washing_Technology() {
		return beneficiation_washing_Technology;
	}

	public void setBeneficiation_washing_Technology(String beneficiation_washing_Technology) {
		this.beneficiation_washing_Technology = beneficiation_washing_Technology;
	}

	public String getOther_information_Proposed_Beneficiation() {
		return other_information_Proposed_Beneficiation;
	}

	public void setOther_information_Proposed_Beneficiation(String other_information_Proposed_Beneficiation) {
		this.other_information_Proposed_Beneficiation = other_information_Proposed_Beneficiation;
	}

	public String getDate_of_Public_Consultation() {
		return date_of_Public_Consultation;
	}

	public void setDate_of_Public_Consultation(String date_of_Public_Consultation) {
		this.date_of_Public_Consultation = date_of_Public_Consultation;
	}

	public String getDate_consultation() {
		return date_consultation;
	}

	public void setDate_consultation(String date_consultation) {
		this.date_consultation = date_consultation;
	}

	public String getLatitude_longitude_of__project() {
		return latitude_longitude_of__project;
	}

	public void setLatitude_longitude_of__project(String latitude_longitude_of__project) {
		this.latitude_longitude_of__project = latitude_longitude_of__project;
	}

	public String getLatitude_longitude_project() {
		return latitude_longitude_project;
	}

	public void setLatitude_longitude_project(String latitude_longitude_project) {
		this.latitude_longitude_project = latitude_longitude_project;
	}

	public String getNo_of_Trees_to_be_cut() {
		return no_of_Trees_to_be_cut;
	}

	public void setNo_of_Trees_to_be_cut(String no_of_Trees_to_be_cut) {
		this.no_of_Trees_to_be_cut = no_of_Trees_to_be_cut;
	}

	public String getArea_covered_with_plantation() {
		return area_covered_with_plantation;
	}

	public void setArea_covered_with_plantation(String area_covered_with_plantation) {
		this.area_covered_with_plantation = area_covered_with_plantation;
	}

	public String getNo_of_Trees_proposed_for_transplantation() {
		return no_of_Trees_proposed_for_transplantation;
	}

	public void setNo_of_Trees_proposed_for_transplantation(String no_of_Trees_proposed_for_transplantation) {
		this.no_of_Trees_proposed_for_transplantation = no_of_Trees_proposed_for_transplantation;
	}

	public String getGreen_Belt() {
		return green_Belt;
	}

	public void setGreen_Belt(String green_Belt) {
		this.green_Belt = green_Belt;
	}

	public String getDetails_of_the_Ground_Water_table() {
		return details_of_the_Ground_Water_table;
	}

	public void setDetails_of_the_Ground_Water_table(String details_of_the_Ground_Water_table) {
		this.details_of_the_Ground_Water_table = details_of_the_Ground_Water_table;
	}

	public String getCategory_safe_exploited() {
		return category_safe_exploited;
	}

	public void setCategory_safe_exploited(String category_safe_exploited) {
		this.category_safe_exploited = category_safe_exploited;
	}

	public String getMeasures_to_recharge_ground_water() {
		return measures_to_recharge_ground_water;
	}

	public void setMeasures_to_recharge_ground_water(String measures_to_recharge_ground_water) {
		this.measures_to_recharge_ground_water = measures_to_recharge_ground_water;
	}

	public String getDetails_of_recycle_of_wastewater() {
		return details_of_recycle_of_wastewater;
	}

	public void setDetails_of_recycle_of_wastewater(String details_of_recycle_of_wastewater) {
		this.details_of_recycle_of_wastewater = details_of_recycle_of_wastewater;
	}

	public String getDetails_of_proposed_common_off_site() {
		return details_of_proposed_common_off_site;
	}

	public void setDetails_of_proposed_common_off_site(String details_of_proposed_common_off_site) {
		this.details_of_proposed_common_off_site = details_of_proposed_common_off_site;
	}

	public String getDetails_of_proposed_setup_on_Site_Sewage_Treatment_Plant() {
		return details_of_proposed_setup_on_Site_Sewage_Treatment_Plant;
	}

	public void setDetails_of_proposed_setup_on_Site_Sewage_Treatment_Plant(
			String details_of_proposed_setup_on_Site_Sewage_Treatment_Plant) {
		this.details_of_proposed_setup_on_Site_Sewage_Treatment_Plant = details_of_proposed_setup_on_Site_Sewage_Treatment_Plant;
	}

	public String getSource_Wise_Probable_Air_Pollutants() {
		return source_Wise_Probable_Air_Pollutants;
	}

	public void setSource_Wise_Probable_Air_Pollutants(String source_Wise_Probable_Air_Pollutants) {
		this.source_Wise_Probable_Air_Pollutants = source_Wise_Probable_Air_Pollutants;
	}

	public String getBenefits_of_the_Project() {
		return benefits_of_the_Project;
	}

	public void setBenefits_of_the_Project(String benefits_of_the_Project) {
		this.benefits_of_the_Project = benefits_of_the_Project;
	}

	public String getDetails_of_previous_EC_Rejection() {
		return details_of_previous_EC_Rejection;
	}

	public void setDetails_of_previous_EC_Rejection(String details_of_previous_EC_Rejection) {
		this.details_of_previous_EC_Rejection = details_of_previous_EC_Rejection;
	}

	public String getTerms_of_Reference_for_undertaking() {
		return terms_of_Reference_for_undertaking;
	}

	public void setTerms_of_Reference_for_undertaking(String terms_of_Reference_for_undertaking) {
		this.terms_of_Reference_for_undertaking = terms_of_Reference_for_undertaking;
	}

	public String getEcDate() {
		return ecDate;
	}

	public void setEcDate(String ecDate) {
		this.ecDate = ecDate;
	}

	public String getEcValidity() {
		return ecValidity;
	}

	public void setEcValidity(String ecValidity) {
		this.ecValidity = ecValidity;
	}

	public Double getAreaExistInHaANonForestLandA() {
		return areaExistInHaANonForestLandA;
	}

	public void setAreaExistInHaANonForestLandA(Double areaExistInHaANonForestLandA) {
		this.areaExistInHaANonForestLandA = areaExistInHaANonForestLandA;
	}

	public Double getAdditionalAreaExistInHaBNonForestLandA() {
		return additionalAreaExistInHaBNonForestLandA;
	}

	public void setAdditionalAreaExistInHaBNonForestLandA(Double additionalAreaExistInHaBNonForestLandA) {
		this.additionalAreaExistInHaBNonForestLandA = additionalAreaExistInHaBNonForestLandA;
	}

	public Double getAreaExistInHaAForestLandB() {
		return areaExistInHaAForestLandB;
	}

	public void setAreaExistInHaAForestLandB(Double areaExistInHaAForestLandB) {
		this.areaExistInHaAForestLandB = areaExistInHaAForestLandB;
	}

	public Double getAdditionalAreaExistInBForestLandB() {
		return additionalAreaExistInBForestLandB;
	}

	public void setAdditionalAreaExistInBForestLandB(Double additionalAreaExistInBForestLandB) {
		this.additionalAreaExistInBForestLandB = additionalAreaExistInBForestLandB;
	}

	public Double getSubTotalAandBForHaA() {
		return subTotalAandBForHaA;
	}

	public void setSubTotalAandBForHaA(Double subTotalAandBForHaA) {
		this.subTotalAandBForHaA = subTotalAandBForHaA;
	}

	public Double getSubTotalAandBforHaB() {
		return subTotalAandBforHaB;
	}

	public void setSubTotalAandBforHaB(Double subTotalAandBforHaB) {
		this.subTotalAandBforHaB = subTotalAandBforHaB;
	}

	public Double getTotalNonForestLandtotalHaAandB() {
		return totalNonForestLandtotalHaAandB;
	}

	public void setTotalNonForestLandtotalHaAandB(Double totalNonForestLandtotalHaAandB) {
		this.totalNonForestLandtotalHaAandB = totalNonForestLandtotalHaAandB;
	}

	public Double getTotalForestLandtotalHaAandB() {
		return totalForestLandtotalHaAandB;
	}

	public void setTotalForestLandtotalHaAandB(Double totalForestLandtotalHaAandB) {
		this.totalForestLandtotalHaAandB = totalForestLandtotalHaAandB;
	}

	public String getEmploymentDetailsApplicantName() {
		return employmentDetailsApplicantName;
	}

	public void setEmploymentDetailsApplicantName(String employmentDetailsApplicantName) {
		this.employmentDetailsApplicantName = employmentDetailsApplicantName;
	}

	public String getEmploymentDetailsApplicantEmail() {
		return employmentDetailsApplicantEmail;
	}

	public void setEmploymentDetailsApplicantEmail(String employmentDetailsApplicantEmail) {
		this.employmentDetailsApplicantEmail = employmentDetailsApplicantEmail;
	}

	public Double getTotalAandB() {
		return totalAandB;
	}

	public void setTotalAandB(Double totalAandB) {
		this.totalAandB = totalAandB;
	}

	public String getStandardEcConditions() {
		return standardEcConditions;
	}

	public void setStandardEcConditions(String standardEcConditions) {
		this.standardEcConditions = standardEcConditions;
	}

	public String getSpecificEcConditions() {
		return specificEcConditions;
	}

	public void setSpecificEcConditions(String specificEcConditions) {
		this.specificEcConditions = specificEcConditions;
	}

	public String getValidity_ec() {
		return validity_ec;
	}

	public void setValidity_ec(String validity_ec) {
		this.validity_ec = validity_ec;
	}

	public String getStandard_condition() {
		return standard_condition;
	}

	public void setStandard_condition(String standard_condition) {
		this.standard_condition = standard_condition;
	}

	public String getSpecific_condition() {
		return specific_condition;
	}

	public void setSpecific_condition(String specific_condition) {
		this.specific_condition = specific_condition;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getProposal_for() {
		return proposal_for;
	}

	public void setProposal_for(String proposal_for) {
		this.proposal_for = proposal_for;
	}

	public String getProposal_for_old() {
		return proposal_for_old;
	}

	public void setProposal_for_old(String proposal_for_old) {
		this.proposal_for_old = proposal_for_old;
	}

	public String getCopy_to() {
		return copy_to;
	}

	public void setCopy_to(String copy_to) {
		this.copy_to = copy_to;
	}

	public String getCopyTo() {
		return copyTo;
	}

	public void setCopyTo(String copyTo) {
		this.copyTo = copyTo;
	}

	public Boolean getSub_activity_condition_applicability() {
		return sub_activity_condition_applicability;
	}

	public void setSub_activity_condition_applicability(Boolean sub_activity_condition_applicability) {
		this.sub_activity_condition_applicability = sub_activity_condition_applicability;
	}

	public List<EnvironmentClearanceProjectActivityDetailsDto> getEcProjectActivityDetails() {
		return ecProjectActivityDetails;
	}

	public void setEcProjectActivityDetails(
			List<EnvironmentClearanceProjectActivityDetailsDto> ecProjectActivityDetails) {
		this.ecProjectActivityDetails = ecProjectActivityDetails;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getWhetherAnyAmendmentToTheEarlierECHasBeenSought() {
		return WhetherAnyAmendmentToTheEarlierECHasBeenSought;
	}

	public void setWhetherAnyAmendmentToTheEarlierECHasBeenSought(
			Boolean whetherAnyAmendmentToTheEarlierECHasBeenSought) {
		WhetherAnyAmendmentToTheEarlierECHasBeenSought = whetherAnyAmendmentToTheEarlierECHasBeenSought;
	}

	public Boolean getIs_for_old_proposal() {
		return is_for_old_proposal;
	}

	public void setIs_for_old_proposal(Boolean is_for_old_proposal) {
		this.is_for_old_proposal = is_for_old_proposal;
	}

	public String getAdditionalTerm() {
		return additionalTerm;
	}

	public void setAdditionalTerm(String additionalTerm) {
		this.additionalTerm = additionalTerm;
	}

	public DocumentDetails getAdditional_document() {
		return additional_document;
	}

	public void setAdditional_document(DocumentDetails additional_document) {
		this.additional_document = additional_document;
	}

	public Boolean getIs_details_project_table_deleted() {
		return is_details_project_table_deleted;
	}

	public void setIs_details_project_table_deleted(Boolean is_details_project_table_deleted) {
		this.is_details_project_table_deleted = is_details_project_table_deleted;
	}

	public Boolean getIs_details_product_table_deleted() {
		return is_details_product_table_deleted;
	}

	public void setIs_details_product_table_deleted(Boolean is_details_product_table_deleted) {
		this.is_details_product_table_deleted = is_details_product_table_deleted;
	}

	public String getNeed_of_project() {
		return need_of_project;
	}

	public void setNeed_of_project(String need_of_project) {
		this.need_of_project = need_of_project;
	}

	public String getStandardPara1() {
		return standardPara1;
	}

	public void setStandardPara1(String standardPara1) {
		this.standardPara1 = standardPara1;
	}

	public String getStandardPara2() {
		return standardPara2;
	}

	public void setStandardPara2(String standardPara2) {
		this.standardPara2 = standardPara2;
	}

	public String getStandardPara3() {
		return standardPara3;
	}

	public void setStandardPara3(String standardPara3) {
		this.standardPara3 = standardPara3;
	}

	public String getStandardPara4() {
		return standardPara4;
	}

	public void setStandardPara4(String standardPara4) {
		this.standardPara4 = standardPara4;
	}

	public String getStandardPara5() {
		return standardPara5;
	}

	public void setStandardPara5(String standardPara5) {
		this.standardPara5 = standardPara5;
	}

}
