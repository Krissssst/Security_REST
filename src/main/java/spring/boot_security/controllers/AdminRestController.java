package spring.boot_security.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdminRestController {

    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder encoder;

    @GetMapping()
    public String allUser(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("principal", user);
        return "admin";
    }
//
//    @GetMapping("/{id}")
//    public User getUser(@PathVariable("id") long id) {
//        return userService.getUser(id);
//    }

    @PostMapping("/new")
    public String create(User user, @RequestParam("roles") Set<Role> roles) {
        userService.update(roles, user);
        return "redirect:/admin";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute("user") User user, @RequestParam("roles") Set<Role> roles) {
        userService.update(roles, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }

}
