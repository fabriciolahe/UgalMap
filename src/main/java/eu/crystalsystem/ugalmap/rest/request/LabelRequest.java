package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Label;
import eu.crystalsystem.ugalmap.models.User;

public class LabelRequest {
	
	private Label label;
	
	private User requesUser;

	public LabelRequest(Label label, User requesUser) {
		super();
		this.label = label;
		this.requesUser = requesUser;
	}
	
	public LabelRequest() {}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}
	
	
}
