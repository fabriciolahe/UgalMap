package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;
import eu.crystalsystem.ugalmap.models.Value;

public class ValueResponse extends GenericResponse {
	
	private List<Value> value;

	public ValueResponse() {
		
	}
	
	public ValueResponse(GenericHeader genericHeader, List<Value> value) {
		super(genericHeader);
		this.value=value;
	}
	
	public ValueResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public List<Value> getValue() {
		return value;
	}

	public void setValue(List<Value> value) {
		this.value = value;
	}
	

}
