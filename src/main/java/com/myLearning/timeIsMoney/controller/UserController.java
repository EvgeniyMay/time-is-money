package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.entity.User;
import com.myLearning.timeIsMoney.enums.MissionState;
import com.myLearning.timeIsMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());

        return "user/allUsers";
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(authentication.getName());
        model.addAttribute("user", user);

        model.addAttribute("activeMissions", user.getMissions().stream()
                .filter(m -> MissionState.ACTIVE.equals(m.getState()))
                .collect(Collectors.toList()));

        model.addAttribute("passedMissions", user.getMissions().stream()
                .filter(m -> MissionState.PASSED.equals(m.getState()))
                .collect(Collectors.toList()));

        model.addAttribute("offeredMissions", user.getMissions().stream()
                .filter(m -> MissionState.OFFERED.equals(m.getState()))
                .collect(Collectors.toList()));


        return "user/userProfile";
    }
}
