package spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot_security.models.User;
import spring.boot_security.service.RoleServiceImpl;
import spring.boot_security.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    @Autowired
    public MyRestController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/admin")
    public List<User> showAll() {
        List<User> allUser = userService.findAll();
        return allUser;

    }
}
