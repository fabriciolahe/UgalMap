package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Language;

public interface LanguageRepository extends JpaRepository<Language, Integer> {

	Language findByLanguageId(int languageId);

	Language findByLanguageName(String languageName);

}
