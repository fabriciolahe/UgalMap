package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Building;
import eu.crystalsystem.ugalmap.models.Entity;
import eu.crystalsystem.ugalmap.models.EntityLabel;
import eu.crystalsystem.ugalmap.models.Label;
import eu.crystalsystem.ugalmap.models.Schedule;
import eu.crystalsystem.ugalmap.models.User;
import eu.crystalsystem.ugalmap.models.Value;

public class EntityLabelRequest {
	
	private Building building;
	private Entity entity;
	private Schedule schedule;
	private Label label;
	private Value value;
	private EntityLabel entitylabel;
	private User requesUser;
	public EntityLabelRequest(Building building, Entity entity, Schedule schedule, Label label,
			Value value, EntityLabel entitylabel, User requesUser) {
		super();
		this.building = building;
		this.entity = entity;
		this.schedule = schedule;
		this.label = label;
		this.value = value;
		this.entitylabel = entitylabel;
		this.requesUser = requesUser;
	}
	public EntityLabelRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public Value getValue() {
		return value;
	}
	public void setValue(Value value) {
		this.value = value;
	}
	public EntityLabel getEntitylabel() {
		return entitylabel;
	}
	public void setEntitylabel(EntityLabel entitylabel) {
		this.entitylabel = entitylabel;
	}
	public User getRequesUser() {
		return requesUser;
	}
	public void setRequesUser(User requesUser) {
		this.requesUser = requesUser;
	}
	
	
	


}
