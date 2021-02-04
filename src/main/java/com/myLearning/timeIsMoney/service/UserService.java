package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.dto.UserDTO;
import com.myLearning.timeIsMoney.entity.User;
import com.myLearning.timeIsMoney.enums.Role;
import com.myLearning.timeIsMoney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //ToDo
    // Add Exception
    @Transactional
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    //ToDo
    // Add Exception
    public User getById(Long userId) {
        // Throws
        return userRepository.findById(userId).get();
    }

    //ToDo
    // Add Exception
    public User getByLogin(String login) {
        return userRepository.findByLogin(login).get();
    }
}
