package com.dev.api.controllers;

import com.dev.api.controllers.dtos.CreateUser;
import com.dev.api.entities.User;
import com.dev.api.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController{

    @Autowired
    private UserServiceImpl userServiceImpl;
    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping("/register")
    public ResponseEntity<User> create(@RequestBody CreateUser dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.create(dto));
    }

}
