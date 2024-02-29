package com.ssuserserviceapp.controller;

import com.ssuserserviceapp.dto.UserDto;
import com.ssuserserviceapp.entity.User;
import com.ssuserserviceapp.request.UserRequest;
import com.ssuserserviceapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class UserController {

    private final UserService userService;

    @GetMapping("/allUsers")
    private ResponseEntity<List<UserDto>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{id}")
    private ResponseEntity<Optional<User>> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.userById(id));
    }

    @PostMapping("/newUser")
    private ResponseEntity<User> createUser(@RequestBody UserRequest request) {
        userService.createUSer(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
