package com.ssuserserviceapp.controller;

import com.ssuserserviceapp.entity.User;
import com.ssuserserviceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;

    @GetMapping()
    private ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
