package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.EntityLabel;
import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;

public class EntityLabelResponse extends GenericResponse {

	private List<EntityLabel> entitylabel;

	public EntityLabelResponse() {
		super();
	}
	public EntityLabelResponse(GenericHeader genericHeader) {
		super(genericHeader);
	}
	public EntityLabelResponse(GenericHeader genericHeader, List<EntityLabel> entitylabel) {
		super(genericHeader);
		this.entitylabel = entitylabel;
	}
	public List<EntityLabel> getEntitylabel() {
		return entitylabel;
	}
	public void setEntitylabel(List<EntityLabel> entitylabel) {
		this.entitylabel = entitylabel;
	}


}
