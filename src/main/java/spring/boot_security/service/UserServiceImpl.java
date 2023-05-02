package spring.boot_security.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot_security.configs.WebSecurityConfig;
import spring.boot_security.models.Role;
import spring.boot_security.models.User;
import spring.boot_security.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;

    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(long id) {
        return  userRepository.getById(id);

    }

    @Override
    public User save(User user) {
        user.setPassword(WebSecurityConfig.getPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void update(Set<Role> roles, User userUpdate) {
        userUpdate.setRoles(roles);
        userUpdate.setPassword(WebSecurityConfig.getPasswordEncoder().encode(userUpdate.getPassword()));
        userRepository.save(userUpdate);

    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Set<Role> getAllRole() {
        return new HashSet<>(roleService.getAllRoles());
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(email);
        if (user == null) {
            throw new IllegalArgumentException(String.format("User '%s' not found", email));
        }
        return user;
    }
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}