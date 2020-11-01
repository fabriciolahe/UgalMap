package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;
public class BuildingsLocationResponse {

	List<BuildingLocation> buildingLocations;

	public BuildingsLocationResponse() {

	}

	public BuildingsLocationResponse(List<BuildingLocation> buildingLocations) {
		this.buildingLocations = buildingLocations;
	}

	public List<BuildingLocation> getBuildingLocations() {
		return buildingLocations;
	}

	public void setBuildingLocations(List<BuildingLocation> buildingLocations) {
		this.buildingLocations = buildingLocations;
	}

}
