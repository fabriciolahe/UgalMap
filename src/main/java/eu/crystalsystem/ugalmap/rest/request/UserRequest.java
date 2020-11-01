package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Role;
import eu.crystalsystem.ugalmap.models.User;

public class UserRequest {

	private Role role;
	private User requesUser;
	private User user;
	
	public UserRequest(User requesUser, User user,Role role) {
		super();
		this.requesUser = requesUser;
		this.user = user;
		this.role=role;
	}

	public UserRequest() {
		super();
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
