package spring.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot_security.models.Role;
import spring.boot_security.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void addRole(Role role) {
        roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Set<Role> findRollsById(String roleId) {
        Set<Role> roles = new HashSet<>();
        for (Role role : roleRepository.findAll()) {
            if (roleId.contains(String.valueOf(role.getId()))) {
                roles.add(role);
            }
        }
        return roles;
    }
}