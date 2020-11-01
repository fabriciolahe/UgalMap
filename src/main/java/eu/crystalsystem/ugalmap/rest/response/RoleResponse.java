package eu.crystalsystem.ugalmap.rest.response;

import java.util.List;

import eu.crystalsystem.ugalmap.models.GenericHeader;
import eu.crystalsystem.ugalmap.models.GenericResponse;
import eu.crystalsystem.ugalmap.models.Role;

public class RoleResponse extends GenericResponse {
	
	private List<Role> role;

	public RoleResponse() {
		super();
	}

	public RoleResponse(GenericHeader genericHeader,List<Role> role) {
		super(genericHeader);
		this.role = role;
	}
	
	public RoleResponse(GenericHeader genericHeader) {
		super(genericHeader);
		}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}
	
	
	

}
