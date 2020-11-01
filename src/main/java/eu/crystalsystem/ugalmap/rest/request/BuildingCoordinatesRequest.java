package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.BuildingCoordinates;
import eu.crystalsystem.ugalmap.models.User;

public class BuildingCoordinatesRequest {

	private BuildingCoordinates buildingCoordinates;
	private User requesUser;
	
	public BuildingCoordinatesRequest() {
		
	}

	public BuildingCoordinatesRequest(BuildingCoordinates buildingCoordinates, User requesUser) {
		super();
		this.buildingCoordinates = buildingCoordinates;
		this.requesUser = requesUser;
	}

	public BuildingCoordinates getBuildingCoordinates() {
		return buildingCoordinates;
	}

	public void setBuildingCoordinates(BuildingCoordinates buildingCoordinates) {
		this.buildingCoordinates = buildingCoordinates;
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}

}
