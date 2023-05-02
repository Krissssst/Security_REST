package spring.boot_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot_security.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User finUsersByLogin(String username);
    User findUserById(Long id);
}