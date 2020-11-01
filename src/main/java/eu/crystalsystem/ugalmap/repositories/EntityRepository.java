package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Entity;


public interface EntityRepository extends JpaRepository<Entity, Integer> {
	
	Entity findByEntityId(int entityId);

	Entity findByEntityType(String entitytype);
}
