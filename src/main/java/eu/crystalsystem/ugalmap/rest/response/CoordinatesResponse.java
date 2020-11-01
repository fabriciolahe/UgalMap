package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.Coordinates;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;

public class CoordinatesResponse extends GenericResponse {
	
	private List<Coordinates> coordinates;

	public CoordinatesResponse() {
	}

	public CoordinatesResponse(GenericHeader genericHeader, List<Coordinates> coordinates) {
		super(genericHeader);
		this.coordinates = coordinates;
	}

	public CoordinatesResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public List<Coordinates> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Coordinates> coordinates) {
		this.coordinates = coordinates;
	}	
}
