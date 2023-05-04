package spring.boot_security.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import spring.boot_security.models.Role;
import spring.boot_security.models.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User getUserById(Long id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);


}