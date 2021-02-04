package com.myLearning.timeIsMoney.repository;

import com.myLearning.timeIsMoney.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
}
