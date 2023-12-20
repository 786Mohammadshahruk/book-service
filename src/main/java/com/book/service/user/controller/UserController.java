package com.book.service.user.controller;

import com.book.service.user.record.User;
import com.book.service.user.record.UserResponse;
import com.book.service.user.record.Users;
import com.book.service.user.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class UserController {
    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user", produces = "application/json")
    public ResponseEntity<UserResponse> add(@RequestBody User user) {
        UserResponse userResponse = userService.addUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", userResponse.userEntity().getUserId().toString());
        return new ResponseEntity<>(userResponse, headers, HttpStatus.CREATED);
    }

    @GetMapping("/users/{name}")
    public ResponseEntity<Boolean> books(@PathVariable String name) {
        return ResponseEntity.ok(userService.users(name));
    }

}
