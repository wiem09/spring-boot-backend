package com.jwt.springjwt.repository;

import com.jwt.springjwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
	Boolean existsRoleByName(String name);
}
