package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Entity;
import eu.crystalsystem.ugalmap.models.User;

public class EntityRequest {
	
	private Entity entity;
	
	private User requesUser;

	public EntityRequest(Entity entity, User requesUser) {
		super();
		this.entity = entity;
		this.requesUser = requesUser;
	}

	public Entity getEntity() {
		return entity;
	}
	

	public EntityRequest() {
		super();
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}

}
