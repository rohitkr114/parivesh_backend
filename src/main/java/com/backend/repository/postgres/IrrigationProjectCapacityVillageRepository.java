package com.backend.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.model.IrrigationProjectCapacityVillage;

public interface IrrigationProjectCapacityVillageRepository
		extends JpaRepository<IrrigationProjectCapacityVillage, Integer> {

	IrrigationProjectCapacityVillage findIrrigationProjectCapacityVillageByCafId(Integer cafId);

}
