package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;
import eu.crystalsystem.ugalmap.models.User;

public class UserResponse extends GenericResponse {
	
	private List<User> user;

	public UserResponse() {
		super();
	}
	
	public UserResponse(GenericHeader genericHeader,List<User> user) {
		super(genericHeader);
		this.user=user;
	}

	public UserResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}

	public List<User> getUser() {
		return user;
	}

	public void setUser(List<User> user) {
		this.user = user;
	}
	
	
}
