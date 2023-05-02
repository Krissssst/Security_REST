package spring.boot_security.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot_security.models.User;
import spring.boot_security.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserServiceImpl userService;

    @Autowired
    public UserRestController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public ResponseEntity<User> getInfo(@AuthenticationPrincipal User user){
        User users=userService.getUserByName(user.getName());
        return ResponseEntity.ok().body(users);
    }
    }

