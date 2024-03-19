package org.example.Controller;
import org.example.Model.User;
import org.example.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins ="*",allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/registration")
    public User save(@RequestBody User obj) {

        User savedUser = userService.saveuser(obj);
        return savedUser;
    }

    @GetMapping("/getallusers")
    public List<User> getallusers(){
        return userService.getallusers();
    }

    @PostMapping("/login")
    public HashMap<String,Object> loginuser(@RequestBody User user){
        String email = user.getEmail();
        String password = user.getPassword();
        return userService.userLogin(email,password);
    }

}




