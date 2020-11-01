package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Coordinates;

public interface CoordinatesRepository extends JpaRepository<Coordinates, Integer> {
	Coordinates findByCoordinatesId(int coordinatesId);
}
