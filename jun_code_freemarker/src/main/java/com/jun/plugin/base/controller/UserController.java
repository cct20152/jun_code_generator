package com.jun.plugin.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jun.plugin.base.model.User;
import com.jun.plugin.base.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    
    @RequestMapping("user")
    public List<User> findAll() {
        return userService.findAll();
    }
}
