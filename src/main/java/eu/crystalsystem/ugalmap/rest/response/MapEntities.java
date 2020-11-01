package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.EntityLabel;

public class MapEntities {

	private List<EntityLabel> mapEntities;

	public List<EntityLabel> getMapEntities() {
		return mapEntities;
	}

	public void setMapEntities(List<EntityLabel> mapEntities) {
		this.mapEntities = mapEntities;
	}

	public MapEntities(List<EntityLabel> mapEntities) {
		super();
		this.mapEntities = mapEntities;
	}

	public MapEntities() {

	}

}
