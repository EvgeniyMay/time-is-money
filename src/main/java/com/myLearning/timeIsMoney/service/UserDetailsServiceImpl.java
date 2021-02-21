package com.myLearning.timeIsMoney.service;

import com.myLearning.timeIsMoney.entity.UserDetailsImpl;
import com.myLearning.timeIsMoney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        return UserDetailsImpl.getUserDetailsFromUser(userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(login + " not found")));
    }
}
