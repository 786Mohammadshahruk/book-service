package com.book.service.user.controller;

import com.book.service.books.utils.Token;
import com.book.service.user.UserResponseDTO;
import com.book.service.user.record.User;
import com.book.service.user.record.UserResponse;
import com.book.service.user.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;


@Slf4j
@RestController
@CrossOrigin
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
        return ResponseEntity.ok(userService.users(name));
    }

    /*@GetMapping("/users")
    public ResponseEntity<Boolean> books(HttpServletRequest httpServletRequest) throws JsonProcessingException, InvalidTokenException {
        String token = httpServletRequest.getHeader("Authorization");
        if (token == null) {
            throw new InvalidTokenException("Token is Invalid");
        }
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));

        ObjectMapper objectMapper = new ObjectMapper();
        Token decryptToken = objectMapper.readValue(payload, Token.class);
        String name = decryptToken.getSub();
        return ResponseEntity.ok(userService.users(name));
    }*/

    @GetMapping("/users")
    public ResponseEntity<UserResponseDTO> getUserByName(@RequestHeader("Authorization") String token) {
        validateToken(token);
        Token decryptToken = decodeToken(token);
        String name = decryptToken.getSub();

        boolean userExists = userService.users(name);

        UserResponseDTO responseDTO = new UserResponseDTO(userExists);
        return ResponseEntity.ok(responseDTO);
    }

    private void validateToken(String token) {
        if (token == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is invalid");
        }
    }


    private Token decodeToken(String token) {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]), StandardCharsets.UTF_8);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(payload, Token.class);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Error decoding token", e);
        }
    }


}
