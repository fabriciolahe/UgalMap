package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;
import eu.crystalsystem.ugalmap.models.Label;

public class LabelResponse extends GenericResponse{
	
	private List<Label> label;
	
	public LabelResponse() {
	}

	public LabelResponse(GenericHeader genericHeader, List<Label> label) {
		super(genericHeader);
		this.label = label;
	}
	
	public LabelResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public List<Label> getLabel() {
		return label;
	}

	public void setLabel(List<Label> label) {
		this.label = label;
	}
}
