package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Dictionary;

public interface DictionaryRepository extends JpaRepository<Dictionary, Integer> {

	Dictionary findByDictionaryId(int dictionaryId);
}
