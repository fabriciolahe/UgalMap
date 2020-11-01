package eu.crystalsystem.ugalmap.rest.response;

import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;
import eu.crystalsystem.ugalmap.models.User;

public class UserAddResponse extends GenericResponse {

	private User user;

	public UserAddResponse(GenericHeader genericHeader, User user) {
		super(genericHeader);
		this.user = user;
	}

	public UserAddResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public UserAddResponse() {
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
