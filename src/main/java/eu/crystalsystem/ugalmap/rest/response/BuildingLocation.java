package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.BuildingCoordinates;
public class BuildingLocation {

	List<BuildingCoordinates> buildingCoordinates;

	public BuildingLocation() {

	}

	public BuildingLocation(List<BuildingCoordinates> buildingCoordinates) {
		this.buildingCoordinates = buildingCoordinates;
	}

	public List<BuildingCoordinates> getBuildingCoordinates() {
		return buildingCoordinates;
	}

	public void setBuildingCoordinates(List<BuildingCoordinates> buildingCoordinates) {
		this.buildingCoordinates = buildingCoordinates;
	}

}
