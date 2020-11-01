package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.Building;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;

public class BuildingResponse extends GenericResponse {
	
	private List<Building> building;

	public BuildingResponse(GenericHeader genericHeader, List<Building> building) {
		super(genericHeader);
		this.building = building;
	}
	
	public BuildingResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public List<Building> getBuilding() {
		return building;
	}

	public void setBuilding(List<Building> building) {
		this.building = building;
	}
}
