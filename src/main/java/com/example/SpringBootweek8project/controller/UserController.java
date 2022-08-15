package com.example.SpringBootweek8project.controller;

import com.example.SpringBootweek8project.dto.CreateUserDTO;
import com.example.SpringBootweek8project.dto.UserDTO;
import com.example.SpringBootweek8project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {return userService.getAll();}

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
        return userService.create(createUserDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {userService.deleteById(id);}


}
