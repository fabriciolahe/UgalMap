package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.User;

public class UserAddRequest {

	private User insertUser;

	private User requestUser;

	public UserAddRequest(User insertUser, User requestUser) {
		super();
		this.insertUser = insertUser;
		this.requestUser = requestUser;
	}

	public UserAddRequest() {

	}

	public User getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(User insertUser) {
		this.insertUser = insertUser;
	}

	public User getRequestUser() {
		return requestUser;
	}

	public void setRequestUser(User requestUser) {
		this.requestUser = requestUser;
	}

}
