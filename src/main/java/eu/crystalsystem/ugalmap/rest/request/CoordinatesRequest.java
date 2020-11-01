package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Coordinates;
import eu.crystalsystem.ugalmap.models.User;

public class CoordinatesRequest {
	
	private Coordinates coordinates;
	
	private User requesUser;

	public CoordinatesRequest(Coordinates coordinates, User requesUser) {
		super();
		this.coordinates = coordinates;
		this.requesUser = requesUser;
	}
	
	public CoordinatesRequest() {}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}
	
	
}
