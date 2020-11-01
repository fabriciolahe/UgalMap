package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.BuildingEntity;

public class BuildingEntityListResponse {

	private List<BuildingEntity> buildingEntityList;

	public BuildingEntityListResponse() {
	}

	public List<BuildingEntity> getBuildingEntityList() {
		return buildingEntityList;
	}

	public void setBuildingEntityList(List<BuildingEntity> buildingEntityList) {
		this.buildingEntityList = buildingEntityList;
	}

	public BuildingEntityListResponse(List<BuildingEntity> buildingEntityList) {
		this.buildingEntityList = buildingEntityList;
	}

}
