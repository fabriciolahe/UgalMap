package eu.crystalsystem.ugalmap.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.crystalsystem.ugalmap.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findByRoleId(int roleId);
}
