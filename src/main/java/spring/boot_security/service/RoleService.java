package spring.boot_security.service;

import spring.boot_security.models.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    void addRole(Role role);

    Set<Role> findRollsById(String roleId);

    List<Role> getAllRoles();

}