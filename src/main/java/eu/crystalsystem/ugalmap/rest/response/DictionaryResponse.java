package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.Dictionary;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;

public class DictionaryResponse extends GenericResponse {

	private List<Dictionary> dictionary;

	public DictionaryResponse() {
	}

	public DictionaryResponse(GenericHeader genericHeader, List<Dictionary> dictionary) {
		super(genericHeader);
		this.dictionary = dictionary;
	}

	public DictionaryResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public List<Dictionary> getDictionary() {
		return dictionary;
	}

	public void setDictionary(List<Dictionary> dictionary) {
		this.dictionary = dictionary;
	}

}
