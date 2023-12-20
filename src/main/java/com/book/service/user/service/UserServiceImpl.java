package com.book.service.user.service;

import com.book.service.user.dao.UserRepository;
import com.book.service.user.entity.UserEntity;
import com.book.service.user.record.User;
import com.book.service.user.record.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse addUser(User user) {
        UserEntity bookEntity = userRepository.save(new UserEntity(null, user.name(), user.userName(), user.mobileNumber(), user.email(), user.password()));
        return new UserResponse(bookEntity);
    }

    public boolean users(String name) {
        List<UserEntity> bookEntities = userRepository.findAll();
        boolean existUserName = bookEntities.stream().filter(userEntity -> userEntity.getUserName().equals(name)).findFirst().isPresent();
        boolean existEmail = bookEntities.stream().filter(userEntity -> userEntity.getEmail().equals(name)).findFirst().isPresent();
        return existUserName || existEmail;
    }
}
