package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;
import eu.crystalsystem.ugalmap.models.Language;

public class LanguageResponse extends GenericResponse {

	private List<Language> language;

	public LanguageResponse() {
	}

	public LanguageResponse(GenericHeader genericHeader, List<Language> language) {
		super(genericHeader);
		this.language = language;
	}

	public LanguageResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public List<Language> getLanguage() {
		return language;
	}

	public void setLanguage(List<Language> language) {
		this.language = language;
	}


}
