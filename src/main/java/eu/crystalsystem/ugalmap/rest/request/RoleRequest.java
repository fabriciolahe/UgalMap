package eu.crystalsystem.ugalmap.rest.request;

import eu.crystalsystem.ugalmap.models.Role;

public class RoleRequest {

	private Role role;

	public RoleRequest(Role role) {
		super();
		this.role = role;
	}
	
	public RoleRequest() {
		super();
		
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
