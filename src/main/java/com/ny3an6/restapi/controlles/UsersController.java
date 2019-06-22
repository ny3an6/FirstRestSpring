package com.ny3an6.restapi.controlles;


import com.ny3an6.restapi.models.User;
import com.ny3an6.restapi.repositories.UsersRepository;
import com.ny3an6.restapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/users")
    public List<User> getUsers(){
        return usersRepository.findAll();
    }

    @GetMapping("/users/{user-id}")
    public User getUser(@PathVariable("user-id") Long userId){
        return userService.findOne(userId);
    }

    @PostMapping("/addUser")
    public ResponseEntity<Object> addUser(@RequestBody User user){
        userService.signUp(user);
        return ResponseEntity.ok().build();
    }
}
