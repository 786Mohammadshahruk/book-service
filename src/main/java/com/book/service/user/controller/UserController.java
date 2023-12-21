package com.book.service.user.controller;

import com.book.service.books.utils.Token;
import com.book.service.user.record.User;
import com.book.service.user.record.UserResponse;
import com.book.service.user.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;


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
    public ResponseEntity<Boolean> books(HttpServletRequest httpServletRequest, @PathVariable String name) throws JsonProcessingException {
        String token = httpServletRequest.getHeader("Authorization");
        if (token != null) {
            String[] chunks = token.split("\\.");
            Base64.Decoder decoder = Base64.getUrlDecoder();
            String payload = new String(decoder.decode(chunks[1]));

            ObjectMapper objectMapper = new ObjectMapper();
            Token token1 = objectMapper.readValue(payload, Token.class);
            name = token1.getSub();

        }
        return ResponseEntity.ok(userService.users(name));
    }


}
