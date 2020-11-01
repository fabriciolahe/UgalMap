package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Building;


public interface BuildingRepository extends JpaRepository<Building, Integer> {
	
	Building findByBuildingId(int buildingId);
	
}