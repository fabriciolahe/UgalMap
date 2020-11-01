package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Building;
import eu.crystalsystem.ugalmap.models.BuildingEntity;
import eu.crystalsystem.ugalmap.models.Entity;
import eu.crystalsystem.ugalmap.models.User;

public class BuildingEntityRequest {
	private Entity entity;
	private User requesUser;
	private Building building;
	private BuildingEntity buildingEntity;

	public BuildingEntityRequest(Entity entity, User requesUser, Building building, BuildingEntity buildingEntity) {
		super();
		this.entity = entity;
		this.requesUser = requesUser;
		this.building = building;
		this.buildingEntity = buildingEntity;
	}
	public BuildingEntityRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BuildingEntity getBuildingEntity() {
		return buildingEntity;
	}

	public void setBuildingEntity(BuildingEntity buildingEntity) {
		this.buildingEntity = buildingEntity;
	}

	public Entity getEntity() {
		return entity;
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

	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
	}


}
