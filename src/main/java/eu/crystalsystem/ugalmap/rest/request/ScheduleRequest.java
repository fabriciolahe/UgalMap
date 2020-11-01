package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Entity;
import eu.crystalsystem.ugalmap.models.Schedule;
import eu.crystalsystem.ugalmap.models.User;

public class ScheduleRequest {
	
	private Entity entity;
	private  User requesUser;
	private Schedule schedule;
	
	
	public ScheduleRequest(User requesUser, Schedule schedule,Entity entity) {
		super();
		this.requesUser = requesUser;
		this.schedule = schedule;
		this.entity=entity;
	}

	public ScheduleRequest() {
		super();
	}

	public User getRequesUser() {
		return requesUser;
	}

	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	
	
	

}
