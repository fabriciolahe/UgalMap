package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.Entity;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;

public class EntityResponse extends GenericResponse {

	private List<Entity> entity;

	public EntityResponse() {

	}

	public EntityResponse(GenericHeader genericHeader) {
		super(genericHeader);

	}

	public EntityResponse(GenericHeader genericHeader, List<Entity> entity) {
		super(genericHeader);
		this.entity = entity;
	}

	public List<Entity> getEntity() {
		return entity;
	}

	public void setEntity(List<Entity> entity) {
		this.entity = entity;
	}
}
