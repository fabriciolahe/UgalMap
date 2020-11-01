package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.BuildingCoordinates;
public class LocationResponse {

	List<BuildingCoordinates> buildingCoordinates;

	public LocationResponse() {

	}

	public LocationResponse(List<BuildingCoordinates> buildingCoordinates) {
		this.buildingCoordinates = buildingCoordinates;
	}

	public List<BuildingCoordinates> getBuildingCoordinates() {
		return buildingCoordinates;
	}

	public void setBuildingCoordinates(List<BuildingCoordinates> buildingCoordinates) {
		this.buildingCoordinates = buildingCoordinates;
	}

}
