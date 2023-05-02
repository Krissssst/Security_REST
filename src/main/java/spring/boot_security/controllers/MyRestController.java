//package spring.boot_security.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import spring.boot_security.models.User;
//import spring.boot_security.service.UserServiceImpl;
//
//import javax.validation.Valid;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class MyRestController {
//
//    private final UserServiceImpl userService;
//
//    @Autowired
//    public MyRestController(UserServiceImpl userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/admin")
//    public ResponseEntity<List<User>> getAll() {
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    //    @GetMapping("/admin/{id}")
////    public ResponseEntity<User> getUser(@PathVariable("id") long id){
////        return new ResponseEntity<>(userService.getUser(id),HttpStatus.OK);
////    }
//    @PostMapping
//    public ResponseEntity<List<User>> createUser(@RequestBody @Valid User user) {
//        userService.update(user.getRoles(), user);
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @PutMapping
//    public ResponseEntity<List<User>> updateUser(@RequestBody @Valid User user) {
//        userService.update(user.getRoles(), user);
//        return ResponseEntity.ok(userService.findAll());
//    }
//
//    @DeleteMapping
//    public ResponseEntity<List<User>> deleteUser(@PathVariable("id") long id) {
//        userService.delete(id);
//        return ResponseEntity.ok(userService.findAll());
//
//    }
//
//}
