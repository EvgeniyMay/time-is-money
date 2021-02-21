package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dto.UserDTO;
import com.myLearning.timeIsMoney.entity.User;
import com.myLearning.timeIsMoney.enums.Role;
import com.myLearning.timeIsMoney.exception.LoginAlreadyExistException;
import com.myLearning.timeIsMoney.exception.UserNotFountException;
import com.myLearning.timeIsMoney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public boolean create(UserDTO userDTO) {
        try {
            userRepository.save(User.builder()
                    .login(userDTO.getLogin())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .role(Role.USER)
                    .build());
            return true;
        } catch (Exception e) {
            throw new LoginAlreadyExistException("Login already exists");
        }
    }

    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(()->new UserNotFountException("User with id " + userId + " not found"));
    }

    public User getByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(()->new UserNotFountException("User with login " + login + " not found"));
    }
}
