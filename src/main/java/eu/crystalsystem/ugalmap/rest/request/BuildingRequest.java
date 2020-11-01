package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Building;
import eu.crystalsystem.ugalmap.models.User;

public class BuildingRequest {
	
	private Building building;
	
	private User requesUser;

	public BuildingRequest(Building building, User requesUser) {
		super();
		this.building = building;
		this.requesUser = requesUser;
	}
	
	public BuildingRequest() {}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}
}
