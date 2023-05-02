package spring.boot_security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.boot_security.models.Role;
import spring.boot_security.models.User;
import spring.boot_security.service.RoleService;
import spring.boot_security.service.RoleServiceImpl;
import spring.boot_security.service.UserService;
import spring.boot_security.service.UserServiceImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;

    @GetMapping()
    public ResponseEntity<List<User>> showAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") long id) {
        return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/patch")
    public ResponseEntity<Void> update(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity <>(HttpStatus.OK);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles(){
        return ResponseEntity.ok().body(roleService.getAllRoles());
    }

}
