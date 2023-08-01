package com.backend.service.EnvironmentClearance;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backend.exceptions.PariveshException;
import com.backend.exceptions.ProjectNotFoundException;
import com.backend.model.EnvironmentClearence;
import com.backend.model.EnvironmentClearance.ConstructionDetails;
import com.backend.model.EnvironmentClearance.CurrentLandUse;
import com.backend.model.EnvironmentClearance.DemolitionDetails;
import com.backend.model.EnvironmentClearance.EcAirPollutionMitigation;
import com.backend.model.EnvironmentClearance.EcAirportProposal;
import com.backend.model.EnvironmentClearance.EcCBWTFProposals;
import com.backend.model.EnvironmentClearance.EcCETPProposals;
import com.backend.model.EnvironmentClearance.EcCMSWMFProposals;
import com.backend.model.EnvironmentClearance.EcChecklistDetails;
import com.backend.model.EnvironmentClearance.EcConstructionDetail;
import com.backend.model.EnvironmentClearance.EcCroppingPattern;
import com.backend.model.EnvironmentClearance.EcDemolitionTempConstruction;
import com.backend.model.EnvironmentClearance.EcGreenBelt;
import com.backend.model.EnvironmentClearance.EcIndustryProposal;
import com.backend.model.EnvironmentClearance.EcIrrigationProjectCapacityVillage;
import com.backend.model.EnvironmentClearance.EcMiningProposals;
import com.backend.model.EnvironmentClearance.EcPartB;
import com.backend.model.EnvironmentClearance.EcPhysicalChanges;
import com.backend.model.EnvironmentClearance.EcPollutantDetails;
import com.backend.model.EnvironmentClearance.EcPollutionDetails;
import com.backend.model.EnvironmentClearance.EcProposedProjectLandDetails;
import com.backend.model.EnvironmentClearance.EcResource;
import com.backend.model.EnvironmentClearance.EcRiskFactor;
import com.backend.model.EnvironmentClearance.EcRiverValleyProject;
import com.backend.model.EnvironmentClearance.EcStreamCrossing;
import com.backend.model.EnvironmentClearance.EcSubmergedArea;
import com.backend.model.EnvironmentClearance.EcTSDFProposals;
import com.backend.model.EnvironmentClearance.EcTownshipProposals;
import com.backend.model.EnvironmentClearance.EcWasteDetails;
import com.backend.model.EnvironmentClearance.EcWasteProduction;
import com.backend.model.EnvironmentClearance.EcWaterDetails;
import com.backend.model.EnvironmentClearance.ProposedLandUse;
import com.backend.model.EnvironmentClearance.ProposedLandUseExpansion;
import com.backend.repository.postgres.AirportProposalRepository;
import com.backend.repository.postgres.EnvironmentClearenceRepository;
import com.backend.repository.postgres.IrrigationProjectCapacityVillageRepository;
import com.backend.repository.postgres.MiningProposalsRepository;
import com.backend.repository.postgres.ProponentApplicationRepository;
import com.backend.repository.postgres.RiverValleyProjectRepository;
import com.backend.repository.postgres.EnvironmentClearance.ConstructionDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.CurrentLandUseRepository;
import com.backend.repository.postgres.EnvironmentClearance.DemolitionDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcAirPollutionMitigationRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcAirportProposalRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcCBWTFProposalsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcCETPProposalsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcCMSWMFProposalsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcChecklistDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcConstructionDetailRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcCroppingPatternRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcDemolitionTempConstructionRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcGreenbeltRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcIndustryProposalRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcIrrigationProjectCapacityVillageRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcMiningProposalRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPartBRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPhysicalChangesRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPollutantDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcPollutionDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcProposedProjectLandDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcResourceRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcRiskFactorRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcRiverValleyProjectRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcStreamCrossingRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcSubmergedAreaRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcTSDFProposalsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcTownshipProposalRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcWasteDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcWasteProductionRepository;
import com.backend.repository.postgres.EnvironmentClearance.EcWaterDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.MajorProjectRequirementRepository;
import com.backend.repository.postgres.EnvironmentClearance.MiningMineralMinedRepository;
import com.backend.repository.postgres.EnvironmentClearance.MiningMineralReservesRepository;
import com.backend.repository.postgres.EnvironmentClearance.MiningProductionDetailsRepository;
import com.backend.repository.postgres.EnvironmentClearance.ProposedLandUseExpansionRepository;
import com.backend.repository.postgres.EnvironmentClearance.ProposedLandUseRepository;
import com.backend.repository.postgres.EnvironmentClearance.WaterRequirementBreakupRepository;
import com.backend.response.ResponseHandler;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EcPartBService {

	@Autowired
	private EcPartBRepository ecPartBRepository;

	@Autowired
	private EnvironmentClearenceRepository environmentClearenceRepository;

	@Autowired
	private EcRiverValleyProjectRepository ecRiverValleyProjectRepository;

	@Autowired
	private EcResourceRepository ecResourceRepository;

	@Autowired
	private EcDemolitionTempConstructionRepository ecDemolitionRepository;

	@Autowired
	private EcStreamCrossingRepository ecStreamCrossingsRepository;

	@Autowired
	private EcGreenbeltRepository ecGreenbeltRepository;

	@Autowired
	private EcWasteDetailsRepository ecWasteDetailsRepository;

	@Autowired
	private EcPollutantDetailsRepository ecPollutantDetailsRepository;

	@Autowired
	private EcAirPollutionMitigationRepository ecAirPollutionMitigationRepository;

	@Autowired
	private EcChecklistDetailsRepository ecChecklistDetailsRepository;

	@Autowired
	private EcPollutionDetailsRepository ecPollutionDetailsRepository;

	@Autowired
	private EcProposedProjectLandDetailsRepository ecProposedProjectLandDetailsRepository;

	@Autowired
	private EcAirportProposalRepository ecAirportProposalRepository;

	@Autowired
	private EcTSDFProposalsRepository ecTSDFProposalsRepository;

	@Autowired
	private EcCBWTFProposalsRepository ecCBWTFProposalsRepository;

	@Autowired
	private EcCETPProposalsRepository ecCETPProposalsRepository;

	@Autowired
	private EcCMSWMFProposalsRepository ecCMSWMFProposalsRepository;

	@Autowired
	private EcPhysicalChangesRepository ecPhysicalChangesRepository;

	@Autowired
	private EcConstructionDetailRepository ecConstructionDetailRepository;

	@Autowired
	private EcWaterDetailsRepository ecWaterDetailsRepository;

	@Autowired
	private EcWasteProductionRepository ecWasteProductionRepository;

	@Autowired
	private EcRiskFactorRepository ecRiskFactorRepository;

	@Autowired
	private EcMiningProposalRepository ecMiningProposalRepository;

	@Autowired
	private EcIndustryProposalRepository ecIndustryProposalRepository;

	@Autowired
	private MiningMineralMinedRepository miningMineralMinedRepository;

	@Autowired
	private MiningMineralReservesRepository miningMineralReservesRepository;

	@Autowired
	private MiningProductionDetailsRepository miningProductionDetailsRepository;

	@Autowired
	private EcTownshipProposalRepository ecTownshipProposalRepository;

	@Autowired
	private MajorProjectRequirementRepository majorProjectRequirementRepository;

	@Autowired
	private WaterRequirementBreakupRepository waterRequirementBreakupRepository;

	@Autowired
	private EcCroppingPatternRepository ecCroppingPatternRepository;

	@Autowired
	private EcIrrigationProjectCapacityVillageRepository ecIrrigationProjectCapacityVillageRepository;

	@Autowired
	private EcSubmergedAreaRepository ecSubmergedAreaRepository;

	@Autowired
	private CurrentLandUseRepository currentLandUseRepository;

	@Autowired
	private ProposedLandUseRepository proposedLandUseRepository;

	@Autowired
	private ProposedLandUseExpansionRepository proposedLandUseExpansionRepository;

	@Autowired
	private ConstructionDetailsRepository constructionDetailsRepository;

	@Autowired
	private DemolitionDetailsRepository demolitionDetailsRepository;

	@Autowired
	private ProponentApplicationRepository proponentApplicationRepository;

	@Autowired
	private MiningProposalsRepository miningProposalsRepository;

	@Autowired
	private AirportProposalRepository airportProposalRepository;

	@Autowired
	private IrrigationProjectCapacityVillageRepository irrigationRepository;

	@Autowired
	private RiverValleyProjectRepository riverValleyProjectRepository;

	public EcPartB saveEcPartB(Integer ec_id, EcPartB ecPartB) throws PariveshException {
		try {
			EnvironmentClearence temp = environmentClearenceRepository.findById(ec_id)
					.orElseThrow(() -> new ProjectNotFoundException("Environment clearance form not found with id"));
			ecPartB.setEnvironmentClearence(temp);
			return ecPartBRepository.save(ecPartB);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC PartB Details for ec_id- " + ec_id, e);
		}
	}

	public Object saveConstructionDetail(Integer ec_partb_id, EcConstructionDetail ecConstructionDetail)
			throws PariveshException {
		try {
			if (ecConstructionDetail.getId() == null || ecConstructionDetail.getId() == 0) {
				EcConstructionDetail ecConstructionDetail2 = ecConstructionDetailRepository.getRecordExist(ec_partb_id)
						.orElse(null);
				if (ecConstructionDetail2 != null) {
					ecConstructionDetail.setId(ecConstructionDetail2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.findById(ec_partb_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ec part b form not found"));
			ecConstructionDetail.setEcPartB(ecPartB);
			return ecConstructionDetailRepository.save(ecConstructionDetail);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Construction Details for ec_partb_id- " + ec_partb_id, e);
		}
	}

	public EcWaterDetails saveEcWaterDetails(Integer ec_partb_id, EcWaterDetails ecWaterDetails)
			throws PariveshException {
		try {
			if (ecWaterDetails.getId() == null || ecWaterDetails.getId() == 0) {
				EcWaterDetails ecWaterDetails2 = ecWaterDetailsRepository.getRecordExist(ec_partb_id).orElse(null);
				if (ecWaterDetails2 != null) {
					ecWaterDetails.setId(ecWaterDetails2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.findById(ec_partb_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ec part b form not found"));
			ecWaterDetails.setEcPartB(ecPartB);
			return ecWaterDetailsRepository.save(ecWaterDetails);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Water Details for ec_partb_id- " + ec_partb_id, e);
		}
	}

	public EcWasteProduction saveEcWasteProduction(Integer ec_partb_id, EcWasteProduction ecWasteProduction)
			throws PariveshException {
		try {
			if (ecWasteProduction.getId() == null || ecWasteProduction.getId() == 0) {
				EcWasteProduction ecWasteProduction2 = ecWasteProductionRepository.getRecordExist(ec_partb_id)
						.orElse(null);
				if (ecWasteProduction2 != null) {
					ecWasteProduction.setId(ecWasteProduction2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.findById(ec_partb_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ec part b form not found"));
			ecWasteProduction.setEcPartB(ecPartB);
			return ecWasteProductionRepository.save(ecWasteProduction);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Waste Production Details for ec_partb_id- " + ec_partb_id,
					e);
		}
	}

	public EcRiskFactor saveEcRiskFactor(Integer ec_partb_id, EcRiskFactor ecRiskFactor) throws PariveshException {
		try {
			if (ecRiskFactor.getId() == null || ecRiskFactor.getId() == 0) {
				EcRiskFactor ecRiskFactor2 = ecRiskFactorRepository.getRecordExist(ec_partb_id).orElse(null);
				if (ecRiskFactor2 != null) {
					ecRiskFactor.setId(ecRiskFactor2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.findById(ec_partb_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ec part b form not found"));
			ecRiskFactor.setEcPartB(ecPartB);
			return ecRiskFactorRepository.save(ecRiskFactor);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Risk Factor Details for ec_partb_id- " + ec_partb_id, e);
		}
	}

	public EcMiningProposals saveEcMiningProposals(Integer ec_partb_id, EcMiningProposals ecMiningProposals)
			throws PariveshException {
		try {
			if (ecMiningProposals.getId() == null || ecMiningProposals.getId() == 0) {
				EcMiningProposals ecMiningProposals2 = ecMiningProposalRepository.getRecordExist(ec_partb_id)
						.orElse(null);
				if (ecMiningProposals2 != null) {
					ecMiningProposals.setId(ecMiningProposals2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.findById(ec_partb_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ec part b form not found"));
			ecMiningProposals.setEcPartB(ecPartB);
//			MiningProposals miningProposals = new MiningProposals(ecMiningProposals, ecPartB);
//			miningProposalsRepository.save(miningProposals);
			return ecMiningProposalRepository.save(ecMiningProposals);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Mining Proposals Details for ec_partb_id- " + ec_partb_id,
					e);
		}
	}

	public EcIndustryProposal saveEcIndustryProposal(Integer ec_partb_id, EcIndustryProposal ecIndustryProposal)
			throws PariveshException {
		try {
			if (ecIndustryProposal.getId() == null || ecIndustryProposal.getId() == 0) {
				EcIndustryProposal ecIndustryProposal2 = ecIndustryProposalRepository.getRecordExist(ec_partb_id)
						.orElse(null);
				if (ecIndustryProposal2 != null) {
					ecIndustryProposal.setId(ecIndustryProposal2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.findById(ec_partb_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ec part b form not found"));
			ecIndustryProposal.setEcPartB(ecPartB);
			return ecIndustryProposalRepository.save(ecIndustryProposal);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Industry Proposals Details for ec_partb_id- " + ec_partb_id,
					e);
		}
	}

	public EcTownshipProposals saveEcTownshipProposals(Integer ec_partb_id, EcTownshipProposals ecTownshipProposals)
			throws PariveshException {
		try {
			if (ecTownshipProposals.getId() == null || ecTownshipProposals.getId() == 0) {
				EcTownshipProposals ecTownshipProposals2 = ecTownshipProposalRepository.getRecordExist(ec_partb_id)
						.orElse(null);
				if (ecTownshipProposals2 != null) {
					ecTownshipProposals.setId(ecTownshipProposals2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.findById(ec_partb_id)
					.orElseThrow(() -> new ProjectNotFoundException("Ec part b form not found"));
			ecTownshipProposals.setEcPartB(ecPartB);
			return ecTownshipProposalRepository.save(ecTownshipProposals);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Township Proposals Details for ec_partb_id- " + ec_partb_id,
					e);
		}
	}

	public ResponseEntity<Object> saveEcDemolitionTemp(List<EcDemolitionTempConstruction> EcDemolition,
			Integer EcPartBId) throws PariveshException {

		try {
			// EcPartB ecPartB=ecPartBRepository.getById(EcPartBId);
			EcPartB ecPartB = ecPartBRepository.getById(EcPartBId);
			// .orElseThrow(() ->obj.handleECPartBNotFoundException(null));

			List<EcDemolitionTempConstruction> EcDemolitionTemp = EcDemolition.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<EcDemolitionTempConstruction> ecDemolitionTemp = ecDemolitionRepository.saveAll(EcDemolitionTemp);
			return ResponseHandler.generateResponse("Save EC Demolition Temp ", HttpStatus.OK, "", ecDemolitionTemp);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Demolition Temp Details for ec_partb_id- " + EcPartBId, e);
		}
	}

	public ResponseEntity<Object> DeleteEcDemolitionTemp(Integer EcDemolitionTempId) throws PariveshException {
		try {

			Integer upadate = ecDemolitionRepository.updateEcDemolitionTempConstruction(EcDemolitionTempId);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + EcDemolitionTempId);
			}
			return ResponseHandler.generateResponse("Delete EC Demolition Temp ", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting EC Demolition Temp Details for EcDemolitionTempId- " + EcDemolitionTempId, e);
		}
	}

	// EC Stream Crossing saveEcStreamCrossings , DeleteEcStreamCrossings

	public ResponseEntity<Object> saveEcStreamCrossings(List<EcStreamCrossing> ecStreamCrossings, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);

			List<EcStreamCrossing> EcStreamTemp = ecStreamCrossings.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<EcStreamCrossing> ecStreamTemp = ecStreamCrossingsRepository.saveAll(EcStreamTemp);

			return ResponseHandler.generateResponse("Save EC  Stream Crossing ", HttpStatus.OK, "", ecStreamTemp);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Stream Crossing Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> DeleteEcStreamCrossings(Integer EcStreamCrossingId) throws PariveshException {
		try {

			Integer upadate = ecStreamCrossingsRepository.updateEcStreamCrossing(EcStreamCrossingId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + EcStreamCrossingId);
			}
			return ResponseHandler.generateResponse("Delete EC  Stream Crossing ", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting EC Stream Crossing Details for EcStreamCrossingId- " + EcStreamCrossingId, e);
		}
	}

	public ResponseEntity<Object> saveEcGreenBelt(EcGreenBelt ecGreenBelts, Integer EcPartBId)
			throws PariveshException {
		try {
			if (ecGreenBelts.getId() == null || ecGreenBelts.getId() == 0) {
				EcGreenBelt ecGreenBelt2 = ecGreenbeltRepository.getRecordExist(EcPartBId).orElse(null);
				if (ecGreenBelt2 != null) {
					ecGreenBelts.setId(ecGreenBelt2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.getById(EcPartBId);

			ecGreenBelts.setEcPartB(ecPartB);

			return ResponseHandler.generateResponse("Save EC  Greenbelt ", HttpStatus.OK, "",
					ecGreenbeltRepository.save(ecGreenBelts));
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in saving EC Green Belt Details for EcPartBId- " + EcPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcGreenBelt(Integer ecGreenBeltId) throws PariveshException {
		try {

			Integer upadate = ecGreenbeltRepository.updateEcGreenBelt(ecGreenBeltId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + ecGreenBeltId);
			}

			return ResponseHandler.generateResponse("Delete EC  Greenbelt  ", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Green Belt Details for ecGreenBeltId- " + ecGreenBeltId,
					e);
		}
	}

	// EC Resource saveEcResource , DeleteEcResource
	public ResponseEntity<Object> saveEcResource(List<EcResource> ecResource, Integer EcPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(EcPartBId);

			List<EcResource> EcResourceTemp = ecResource.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<EcResource> ecResourceTemp = ecResourceRepository.saveAll(EcResourceTemp);

			return ResponseHandler.generateResponse("Save EC Resource ", HttpStatus.OK, "", ecResourceTemp);

		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Resource Details for EcPartBId- " + EcPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcResource(Integer EcResourecId) throws PariveshException {
		try {

			Integer upadate = ecResourceRepository.updateEcResouce(EcResourecId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + EcResourecId);
			}

			return ResponseHandler.generateResponse("Delete EC Resource ", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Resource Details for EcResourecId- " + EcResourecId, e);
		}
	}

	public ResponseEntity<Object> saveEcWasteDetails(List<EcWasteDetails> ecWasteDetails, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);

			List<EcWasteDetails> ecWasteDetails2 = ecWasteDetails.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<EcWasteDetails> ecWasteDetails3 = ecWasteDetailsRepository.saveAll(ecWasteDetails2);

			return ResponseHandler.generateResponse("Save EC  Waste Details ", HttpStatus.OK, "", ecWasteDetails3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Waste Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcWasteDetails(Integer ecWasteDetailId) throws PariveshException {
		try {

			Integer upadate = ecWasteDetailsRepository.updateEcWasteDetails(ecWasteDetailId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + ecWasteDetailId);
			}

			return ResponseHandler.generateResponse("Delete EC  Greenbelt  ", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Waste Details for ecWasteDetailId- " + ecWasteDetailId,
					e);
		}
	}

	public ResponseEntity<Object> saveEcAirPollutionMitigation(List<EcAirPollutionMitigation> ecAirPollutionMitigation,
			Integer ecPartBId) throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);

			List<EcAirPollutionMitigation> ecAirPollutionMitigation2 = ecAirPollutionMitigation.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<EcAirPollutionMitigation> ecAirPollutionMitigation3 = ecAirPollutionMitigationRepository
					.saveAll(ecAirPollutionMitigation2);

			return ResponseHandler.generateResponse("Save EC  Air Pollution Mitigation", HttpStatus.OK, "",
					ecAirPollutionMitigation3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving EC Air Pollution Mitigation Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcAirPollutionMitigation(Integer ecMitigationId) throws PariveshException {
		try {

			Integer upadate = ecAirPollutionMitigationRepository.updateEcAirPollutionMitigation(ecMitigationId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + ecMitigationId);
			}

			return ResponseHandler.generateResponse("Delete EC  Air Pollution Mitigation  ", HttpStatus.OK, "",
					"Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting EC Air Pollution Mitigation Details for ecMitigationId- " + ecMitigationId, e);
		}
	}

	public ResponseEntity<Object> saveCheckDetails(List<EcChecklistDetails> ecChecklistDetails, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);

			List<EcChecklistDetails> ecChecklistDetails2 = ecChecklistDetails.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<EcChecklistDetails> ecChecklistDetails3 = ecChecklistDetailsRepository.saveAll(ecChecklistDetails2);

			return ResponseHandler.generateResponse("Save EC  Check Details", HttpStatus.OK, "", ecChecklistDetails3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Check Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteCheckDetails(Integer checklistId) throws PariveshException {
		try {

			Integer upadate = ecChecklistDetailsRepository.updateEcChecklistDetails(checklistId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + checklistId);
			}

			return ResponseHandler.generateResponse("Delete EC  Check List", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Check List Details for checklistId- " + checklistId, e);
		}
	}

	public ResponseEntity<Object> saveEcPollutionDetails(EcPollutionDetails ecPollutionDetails, Integer ecPartBId)
			throws PariveshException {
		try {
			if (ecPollutionDetails.getId() == null || ecPollutionDetails.getId() == 0) {
				EcPollutionDetails ecPollutionDetails2 = ecPollutionDetailsRepository.getRecordExist(ecPartBId)
						.orElse(null);
				if (ecPollutionDetails2 != null) {
					ecPollutionDetails.setId(ecPollutionDetails2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			ecPollutionDetails.setEcPartB(ecPartB);

			EcPollutionDetails ecPollutionDetails2 = ecPollutionDetailsRepository.save(ecPollutionDetails);

			return ResponseHandler.generateResponse("Save EC  Pollution Details", HttpStatus.OK, "",
					ecPollutionDetails2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Pollution Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> saveEcPollutantDetails(List<EcPollutantDetails> ecPollutantDetails, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);

			List<EcPollutantDetails> ecWasteDetails2 = ecPollutantDetails.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<EcPollutantDetails> ecWasteDetails3 = ecPollutantDetailsRepository.saveAll(ecWasteDetails2);

			return ResponseHandler.generateResponse("Save EC Pollutant Details ", HttpStatus.OK, "", ecWasteDetails3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Pollutant Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcPollutantDetails(Integer ecPollutantDetailId) throws PariveshException {
		try {

			Integer upadate = ecPollutantDetailsRepository.updateEcPollutantDetails(ecPollutantDetailId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + ecPollutantDetailId);
			}
			return ResponseHandler.generateResponse("Delete EC Pollutant Details  ", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting EC Pollutant Details for ecPollutantDetailId- " + ecPollutantDetailId, e);
		}
	}

	public ResponseEntity<Object> deleteEcPollutionDetails(Integer pollutionId) throws PariveshException {
		try {

			Integer upadate = ecPollutionDetailsRepository.updateEcPollutionDetails(pollutionId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + pollutionId);
			}

			return ResponseHandler.generateResponse("Delete EC  Check List", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Pollution Details for pollutionId- " + pollutionId, e);
		}
	}

	public EcPartB getEcPartB(Integer ec_partb_id) throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.findByIdActive(ec_partb_id)
					.orElseThrow(() -> new ProjectNotFoundException("EC partB not found for id"));
			ecPartB.setProponentApplications(proponentApplicationRepository
					.getApplicationByProposalId(ecPartB.getEnvironmentClearence().getId()));
			return ecPartB;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting EC PartB Details for ec_partb_id- " + ec_partb_id, e);
		}
	}

	public ResponseEntity<Object> saveEcAirportProposalDetails(EcAirportProposal ecAirportProposal, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			ecAirportProposal.setEcPartB(ecPartB);

			EcAirportProposal ecAirportProposal2 = ecAirportProposalRepository.save(ecAirportProposal);

//			AirportProposal airportProposal = new AirportProposal(ecAirportProposal);
//			airportProposalRepository.save(airportProposal);

			return ResponseHandler.generateResponse("Save EC  Airport Proposal", HttpStatus.OK, "", ecAirportProposal2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Airport Proposal Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcAirportProposalDetails(Integer airportId) throws PariveshException {
		try {

//			AirportProposal airportProposal = new AirportProposal(ecAirportProposal);
//			airportProposalRepository.save(airportProposal);

			Integer upadate = ecAirportProposalRepository.updateEcAirportProposal(airportId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + airportId);
			}

			return ResponseHandler.generateResponse("Delete EC  Check List", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Check List Details for airportId- " + airportId, e);
		}
	}

	public ResponseEntity<Object> saveEcRiverValleyProject(EcRiverValleyProject ecRiverValleyProject, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			ecRiverValleyProject.setEcPartB(ecPartB);

			EcRiverValleyProject ecRiverValleyProject2 = ecRiverValleyProjectRepository.save(ecRiverValleyProject);

//			RiverValleyProject riverValleyProject = new RiverValleyProject(ecRiverValleyProject);
//			riverValleyProjectRepository.save(riverValleyProject);

			return ResponseHandler.generateResponse("Save EC  River Valley Project", HttpStatus.OK, "",
					ecRiverValleyProject2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC River Valley Project Details for ecPartBId- " + ecPartBId,
					e);
		}
	}

	public ResponseEntity<Object> deleteEcRiverValleyProject(Integer id) throws PariveshException {
		try {

//			RiverValleyProject riverValleyProject = new RiverValleyProject(ecRiverValleyProject);
//			riverValleyProjectRepository.save(riverValleyProject);

			Integer upadate = ecRiverValleyProjectRepository.updateEcRiverValleyProject(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC River Valley Project", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC River Valley Project Details for id- " + id, e);
		}
	}

	public ResponseEntity<Object> saveEcProposedProjectLandDetails(
			List<EcProposedProjectLandDetails> ecProposedProjectLandDetails, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);

			List<EcProposedProjectLandDetails> ecProposedProjectLandDetails2 = ecProposedProjectLandDetails.stream()
					.map(val -> {
						val.setEcPartB(ecPartB);
						return val;
					}).collect(Collectors.toList());

			List<EcProposedProjectLandDetails> ecProposedProjectLandDetails3 = ecProposedProjectLandDetailsRepository
					.saveAll(ecProposedProjectLandDetails2);

			return ResponseHandler.generateResponse("Save EC Proposed Project Land Details ", HttpStatus.OK, "",
					ecProposedProjectLandDetails3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Proposed Project Land Details for ecPartBId- " + ecPartBId,
					e);
		}
	}

	public ResponseEntity<Object> deleteEcProposedProjectLandDetails(Integer ecProposedProjectLandDetailId)
			throws PariveshException {
		try {

			Integer upadate = ecProposedProjectLandDetailsRepository
					.updateEcProposedProjectLandDetails(ecProposedProjectLandDetailId);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + ecProposedProjectLandDetailId);
			}
			return ResponseHandler.generateResponse("Delete EC Proposed Project Land Details ", HttpStatus.OK, "",
					"Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting EC Proposed Project Land Details for ecProposedProjectLandDetailId- "
							+ ecProposedProjectLandDetailId,
					e);
		}
	}

	public ResponseEntity<Object> saveEcTSDFProposals(EcTSDFProposals ecTSDFProposals, Integer ecPartBId)
			throws PariveshException {
		try {
			if (ecTSDFProposals.getId() == null || ecTSDFProposals.getId() == 0) {
				EcTSDFProposals ecTSDFProposals2 = ecTSDFProposalsRepository.getRecordExist(ecPartBId).orElse(null);
				if (ecTSDFProposals2 != null) {
					ecTSDFProposals.setId(ecTSDFProposals2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			ecTSDFProposals.setEcPartB(ecPartB);

			EcTSDFProposals ecTSDFProposals2 = ecTSDFProposalsRepository.save(ecTSDFProposals);

			return ResponseHandler.generateResponse("Save EC  TSDF Proposals", HttpStatus.OK, "", ecTSDFProposals2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC TSDF Proposals Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcTSDFProposal(Integer id) throws PariveshException {
		try {

			Integer upadate = ecTSDFProposalsRepository.updateEcTSDFProposals(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC TSDF proposals", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC TSDF proposals Details for Id- " + id, e);
		}
	}

	public ResponseEntity<Object> saveEcCBWTFProposals(EcCBWTFProposals ecCBWTFProposals, Integer ecPartBId)
			throws PariveshException {
		try {
			if (ecCBWTFProposals.getId() == null || ecCBWTFProposals.getId() == 0) {
				EcCBWTFProposals ecCBWTFProposals2 = ecCBWTFProposalsRepository.getRecordExist(ecPartBId).orElse(null);
				if (ecCBWTFProposals2 != null) {
					ecCBWTFProposals.setId(ecCBWTFProposals2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			ecCBWTFProposals.setEcPartB(ecPartB);

			EcCBWTFProposals ecCBWTFProposals2 = ecCBWTFProposalsRepository.save(ecCBWTFProposals);

			return ResponseHandler.generateResponse("Save EC  CBWTF Proposals", HttpStatus.OK, "", ecCBWTFProposals2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC CBWTF Proposals Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcCBWTFProposal(Integer id) throws PariveshException {
		try {

			Integer upadate = ecCBWTFProposalsRepository.updateEcCBWTFProposals(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC CBWTF Proposals", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC CBWTF Proposals Details for Id- " + id, e);
		}
	}

	public ResponseEntity<Object> saveEcCETPProposals(EcCETPProposals ecCETPProposals, Integer ecPartBId)
			throws PariveshException {
		try {
			if (ecCETPProposals.getId() == null || ecCETPProposals.getId() == 0) {
				EcCETPProposals ecCETPProposals2 = ecCETPProposalsRepository.getRecordExist(ecPartBId).orElse(null);
				if (ecCETPProposals2 != null) {
					ecCETPProposals.setId(ecCETPProposals2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			ecCETPProposals.setEcPartB(ecPartB);

			EcCETPProposals ecCETPProposals2 = ecCETPProposalsRepository.save(ecCETPProposals);

			return ResponseHandler.generateResponse("Save EC  CETP Proposals", HttpStatus.OK, "", ecCETPProposals2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC CETP Proposals Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcCETPProposal(Integer id) throws PariveshException {
		try {

			Integer upadate = ecCETPProposalsRepository.updateEcCETPProposals(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC CETP proposals", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC CETP proposals Details for Id- " + id, e);
		}
	}

	public ResponseEntity<Object> saveEcCMSWMFProposals(EcCMSWMFProposals ecCMSWMFProposals, Integer ecPartBId)
			throws PariveshException {
		try {
			if (ecCMSWMFProposals.getId() == null || ecCMSWMFProposals.getId() == 0) {
				EcCMSWMFProposals ecCMSWMFProposals2 = ecCMSWMFProposalsRepository.getRecordExist(ecPartBId)
						.orElse(null);
				if (ecCMSWMFProposals2 != null) {
					ecCMSWMFProposals.setId(ecCMSWMFProposals2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			ecCMSWMFProposals.setEcPartB(ecPartB);

			EcCMSWMFProposals ecCMSWMFProposals2 = ecCMSWMFProposalsRepository.save(ecCMSWMFProposals);

			return ResponseHandler.generateResponse("Save EC  CMSWMF Proposals", HttpStatus.OK, "", ecCMSWMFProposals2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC CMSWMF Proposals Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcCMSWMFProposal(Integer id) throws PariveshException {
		try {

			Integer upadate = ecCMSWMFProposalsRepository.updateEcCMSWMFProposals(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC CMSWMF proposals", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC CMSWMF proposals Details for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveEcCroppingPattern(List<EcCroppingPattern> ecCroppingPatterns, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);

			List<EcCroppingPattern> ecCroppingPattern2 = ecCroppingPatterns.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<EcCroppingPattern> ecCroppingPattern3 = ecCroppingPatternRepository.saveAll(ecCroppingPattern2);

			return ResponseHandler.generateResponse("Save EC  Cropping Pattern", HttpStatus.OK, "", ecCroppingPattern3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Cropping Pattern Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcCroppingPattern(Integer id) throws PariveshException {
		try {

			Integer upadate = ecCroppingPatternRepository.updateEcCroppingPattern(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC CMSWMF proposals", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Cropping Pattern Details for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveEcIrrigationProjectCapacityVillage(
			List<EcIrrigationProjectCapacityVillage> ecIrrigationProjectCapacityVillages, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			List<EcIrrigationProjectCapacityVillage> ecIrrigationProjectCapacity2 = ecIrrigationProjectCapacityVillages
					.stream().map(val -> {
						val.setEcPartB(ecPartB);
						return val;
					}).collect(Collectors.toList());

//			List<IrrigationProjectCapacityVillage> irrigationProjectCapacityVillage = (List<IrrigationProjectCapacityVillage>) new IrrigationProjectCapacityVillage(
//					ecIrrigationProjectCapacity2);
//
//			irrigationRepository.saveAll(irrigationProjectCapacityVillage);

			List<EcIrrigationProjectCapacityVillage> ecIrrigationProjectCapacityVillage3 = ecIrrigationProjectCapacityVillageRepository
					.saveAll(ecIrrigationProjectCapacity2);

			return ResponseHandler.generateResponse("Save EC  Irrigation project capacity village", HttpStatus.OK, "",
					ecIrrigationProjectCapacityVillage3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Saving EC Irrigation project capacity village Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcIrrigationProjectCapacityVillage(Integer id) throws PariveshException {
		try {

//			IrrigationProjectCapacityVillage irrigationProjectCapacityVillage = new IrrigationProjectCapacityVillage(
//					ecIrrigationProjectCapacityVillage);
//
//			irrigationRepository.save(irrigationProjectCapacityVillage);

			Integer upadate = ecIrrigationProjectCapacityVillageRepository.updateEcIrrigationProjectCapacityVillage(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC Irrigation project capacity village", HttpStatus.OK, "",
					"Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException(
					"Error in Deleting EC Irrigation project capacity village Details for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveEcSubmergedArea(List<EcSubmergedArea> ecSubmergedAreas, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			List<EcSubmergedArea> ecSubmergedArea2 = ecSubmergedAreas.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());
			List<EcSubmergedArea> ecSubmergedArea3 = ecSubmergedAreaRepository.saveAll(ecSubmergedArea2);

			return ResponseHandler.generateResponse("Save EC Submerged Area", HttpStatus.OK, "", ecSubmergedArea3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Submerged Area Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcSubmergedArea(Integer id) throws PariveshException {
		try {

			Integer upadate = ecSubmergedAreaRepository.updateEcSubmergedArea(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC Submerged Area", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Submerged Area Details for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteMiningMineralMined(Integer id) throws PariveshException {
		try {

			Integer upadate = miningMineralMinedRepository.updateMiningMineralsMined(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Deleted Mining mineral mined", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Mining mineral mined Details for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteMiningMineralReserves(Integer id) throws PariveshException {
		try {

			Integer upadate = miningMineralReservesRepository.updateMiningMineralReserves(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Deleted Mining mineral Reserves", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Mining mineral mined Details for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteMiningProductionDetails(Integer id) throws PariveshException {
		try {

			Integer upadate = miningProductionDetailsRepository.updateMiningProductionDetails(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Deleted Mining production details", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Mining production details for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteMajorProjectRequirement(Integer id) throws PariveshException {
		try {

			Integer upadate = majorProjectRequirementRepository.updateMajorProjectRequirement(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}

			return ResponseHandler.generateResponse("Deleted major Project Requirement", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC major Project Requirement for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> deleteWaterRequirementBreakup(Integer id) throws PariveshException {
		try {

			Integer upadate = waterRequirementBreakupRepository.updateWaterRequirementBreakup(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}

			return ResponseHandler.generateResponse("Deleted water Requirement Breakup", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC water Requirement Breakup for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveEcPhysicalChanges(EcPhysicalChanges ecPhysicalChanges, Integer ecPartBId)
			throws PariveshException {
		try {
			if (ecPhysicalChanges.getId() == null || ecPhysicalChanges.getId() == 0) {
				EcPhysicalChanges ecPhysicalChanges2 = ecPhysicalChangesRepository.getRecordExist(ecPartBId)
						.orElse(null);
				if (ecPhysicalChanges2 != null) {
					ecPhysicalChanges.setId(ecPhysicalChanges2.getId());
				}
			}
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			ecPhysicalChanges.setEcPartB(ecPartB);

			EcPhysicalChanges ecPhysicalChanges2 = ecPhysicalChangesRepository.save(ecPhysicalChanges);

			return ResponseHandler.generateResponse("Save EC Physical Changes", HttpStatus.OK, "", ecPhysicalChanges2);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Physical Changes for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteEcPhysicalChanges(Integer id) throws PariveshException {
		try {

			Integer upadate = ecPhysicalChangesRepository.updateEcPhysicalChanges(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC Physical Changes", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Physical Changes for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveCurrentLandUse(List<CurrentLandUse> currentLandUse, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			List<CurrentLandUse> currentLandUse2 = currentLandUse.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<CurrentLandUse> currentLandUse3 = currentLandUseRepository.saveAll(currentLandUse2);

			return ResponseHandler.generateResponse("Save EC  River Valley Project", HttpStatus.OK, "",
					currentLandUse3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Current Land Use for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteCurrentLandUse(Integer id) throws PariveshException {
		try {

			Integer upadate = currentLandUseRepository.updateCurrentLandUse(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete EC Current Land Use", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Current Land Use for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveProposedLandUse(List<ProposedLandUse> proposedLandUse, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			List<ProposedLandUse> proposedLandUse2 = proposedLandUse.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<ProposedLandUse> proposedLandUse3 = proposedLandUseRepository.saveAll(proposedLandUse2);

			return ResponseHandler.generateResponse("Save EC Proposed Land Use", HttpStatus.OK, "", proposedLandUse3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Proposed Land Use for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteProposedLandUse(Integer id) throws PariveshException {
		try {
			Integer upadate = proposedLandUseRepository.updateDeleteFlag(id);
			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}

			return ResponseHandler.generateResponse("Delete Proposed Land Use", HttpStatus.OK, "", "deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Proposed Land Use for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveProposedLandUseExpansion(List<ProposedLandUseExpansion> proposedLandUseExpansion,
			Integer ecPartBId) throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			List<ProposedLandUseExpansion> proposedLandUseExpansion2 = proposedLandUseExpansion.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<ProposedLandUseExpansion> proposedLandUseExpansion3 = proposedLandUseExpansionRepository
					.saveAll(proposedLandUseExpansion2);

			return ResponseHandler.generateResponse("Save EC Proposed Land Use Expansion", HttpStatus.OK, "",
					proposedLandUseExpansion3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Proposed Land Use Expansion for ecPartBId- " + ecPartBId,
					e);
		}
	}

	public ResponseEntity<Object> deleteProposedLandUseExpansion(Integer id) throws PariveshException {
		try {

			Integer upadate = proposedLandUseExpansionRepository.updateProposedLandUseExpansion(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete Proposed Land Use Expansion", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Proposed Land use Expansion for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveDemolitionDetails(List<DemolitionDetails> demolitionDetails, Integer ecPartBId)
			throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			List<DemolitionDetails> demolitionDetails2 = demolitionDetails.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<DemolitionDetails> demolitionDetails3 = demolitionDetailsRepository.saveAll(demolitionDetails2);

			return ResponseHandler.generateResponse("Save EC Demolition Details", HttpStatus.OK, "",
					demolitionDetails3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Demolition Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteDemolitionDetails(Integer id) throws PariveshException {
		try {

			Integer upadate = demolitionDetailsRepository.updateDemolitionDetails(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}

			return ResponseHandler.generateResponse("Delete Demolition Details", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Demolition Details for ID- " + id, e);
		}
	}

	public ResponseEntity<Object> saveConstructionDetails(List<ConstructionDetails> constructionDetails,
			Integer ecPartBId) throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getById(ecPartBId);
			List<ConstructionDetails> constructionDetails2 = constructionDetails.stream().map(val -> {
				val.setEcPartB(ecPartB);
				return val;
			}).collect(Collectors.toList());

			List<ConstructionDetails> constructionDetails3 = constructionDetailsRepository
					.saveAll(constructionDetails2);

			return ResponseHandler.generateResponse("Save EC  River Valley Project", HttpStatus.OK, "",
					constructionDetails3);
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Saving EC Construction Details for ecPartBId- " + ecPartBId, e);
		}
	}

	public ResponseEntity<Object> deleteConstructionDetails(Integer id) throws PariveshException {
		try {

			Integer upadate = constructionDetailsRepository.updateConstructionDetails(id);

			if (upadate == 0) {
				throw new PariveshException("ID NOT FOUND - " + id);
			}
			return ResponseHandler.generateResponse("Delete Construction Details", HttpStatus.OK, "", "Deleted");
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Deleting EC Construction Details for ID- " + id, e);
		}
	}

	public EcPartB getEcPartBbyEcId(Integer ec_id) throws PariveshException {
		try {
			EcPartB ecPartB = ecPartBRepository.getEcPartBbyId(ec_id)
					.orElseThrow(() -> new ProjectNotFoundException("EC partB not found for id"));
			ecPartB.setProponentApplications(proponentApplicationRepository
					.getApplicationByProposalId(ecPartB.getEnvironmentClearence().getId()));
			return ecPartB;
		} catch (Exception e) {
			log.error("=======================>>>>>>>>>>>" + e.toString() + " " + e.getStackTrace()[0]);
			throw new PariveshException("Error in Getting EC Part B by EcId for ec_id- " + ec_id, e);
		}
	}

}
