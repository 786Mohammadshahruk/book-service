package com.book.service.books.service;

import com.book.service.user.dao.UserRepository;
import com.book.service.user.entity.UserEntity;
import com.book.service.user.record.User;
import com.book.service.user.record.UserResponse;
import com.book.service.user.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

public class UserServiceTest {
    @Test
    public void shouldAddUserToDatabase() {
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository);
        UserEntity userEntity = new UserEntity(1,"aman", "aman.kumar", "765432333", "aman.kumar@gmail.com", "dummyPassword");
        UserResponse expectedResponse = new UserResponse(new UserEntity(1,"aman", "aman.kumar", "765432333", "aman.kumar@gmail.com", "dummyPassword"));
        Mockito.when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        User book = new User(1,"aman", "aman.kumar", "765432333", "aman.kumar@gmail.com", "dummyPassword");
        UserResponse actualResponse = userService.addUser(book);
        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}
