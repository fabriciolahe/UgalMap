package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.BuildingEntity;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;

public class BuildingEntityResponse extends GenericResponse {

	private List<BuildingEntity> buildingEntity;

	public BuildingEntityResponse() {
		super();
	}

	public BuildingEntityResponse(GenericHeader genericHeader) {
		super(genericHeader);
		// TODO Auto-generated constructor stub
	}

	public BuildingEntityResponse(GenericHeader genericHeader, List<BuildingEntity> buildingEntity) {
		super(genericHeader);
		this.buildingEntity = buildingEntity;

	}

	public BuildingEntityResponse(List<BuildingEntity> buildingEntity) {
		super();
		this.buildingEntity = buildingEntity;
	}

	public List<BuildingEntity> getBuildingEntity() {
		return buildingEntity;
	}

	public void setBuildingEntity(List<BuildingEntity> buildingEntity) {
		this.buildingEntity = buildingEntity;
	}

}
