package com.book.service.books.controller;

import com.book.service.user.controller.UserController;
import com.book.service.user.entity.UserEntity;
import com.book.service.user.record.User;
import com.book.service.user.record.UserResponse;
import com.book.service.user.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl bookService;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldAbleToAddBook() throws Exception {
        User user = new User(1,"aman", "aman.kumar", "765432333", "aman.kumar@gmail.com", "test");
        UserResponse userResponse = new UserResponse(new UserEntity(1, "aman", "aman.kumar", "765432333", "aman.kumar@gmail.com", "dummyPassword"));
        when(bookService.addUser(user)).thenReturn(userResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "1"))
                .andExpect(content().string(objectMapper.writeValueAsString(userResponse)));
        verify(bookService, times(1)).addUser(user);
    }
}
