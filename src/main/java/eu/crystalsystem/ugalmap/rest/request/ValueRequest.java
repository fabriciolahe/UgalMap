package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.User;
import eu.crystalsystem.ugalmap.models.Value;

public class ValueRequest {

	private User requesUser;
	private Value value;
	
	
	public ValueRequest(User requesUser, Value value) {
		super();
		this.requesUser = requesUser;
		this.value = value;
	}

	public ValueRequest() {
		super();
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	
	
	
	
	
	
}
