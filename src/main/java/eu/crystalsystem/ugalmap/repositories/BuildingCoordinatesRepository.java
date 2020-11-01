package eu.crystalsystem.ugalmap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.BuildingCoordinates;

public interface BuildingCoordinatesRepository extends JpaRepository<BuildingCoordinates, Integer> {

	List<BuildingCoordinates> findByBuildingBuildingId(int buildingId);
}
