package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Value;

public interface ValueRepository extends JpaRepository<Value, Integer> {

	Value findByValueId(int valueId);
	
	Value findByValueContent(String valueContent);
}
