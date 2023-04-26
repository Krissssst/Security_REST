package spring.boot_security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring.boot_security.models.Role;
import spring.boot_security.models.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<User> findAll();

    User getUserByUsername(String username);

    User getUser(long id);

    User save(User user);

    void update(long id, User userUpdate);

    void delete(long id);




}