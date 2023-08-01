package com.backend.repository.postgres;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.backend.model.Village;

@Repository
public class VillageRepo {

	@PersistenceContext
    private EntityManager entityManager;
	
	
    public List<Village> findAllVillagesLimitedTo(int limit) {
        return entityManager.createQuery("SELECT v from Village v",
          Village.class).setMaxResults(limit).getResultList();
    }
}
