package com.user.Controller;


import com.user.Model.User;
import com.user.Service.CustomUserDetailsService;
import com.user.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@RestController
@RequestMapping("/secure/auth")
@CrossOrigin(origins = "http://localhost:4200/")

public class UserController {


    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/allTechnicien")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/index")
    public String index(){
        return "index page !";
    }

@GetMapping("/load/{username}")
public UserDetails get(@PathVariable("username") String username){
        return userDetailsService.loadUserByUsername(username);
}

    @PostMapping("/add")
    public User save(@RequestBody User user){
       return userService.saveUser(user);
    }


    @DeleteMapping("/delete/{id}")
    public boolean deleteUser(@PathVariable("id") int id){
    return userService.delete(id);
    }

}
