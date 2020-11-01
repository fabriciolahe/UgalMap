package eu.crystalsystem.ugalmap.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.BuildingEntity;


public interface BuildingEntityRepository extends JpaRepository<BuildingEntity, Integer> {

	BuildingEntity findByBuildingEntityId(int id);

	BuildingEntity findByBuildingBuildingId(int id);

	List<BuildingEntity> findByEntityEntityId(int entityId);

}
