package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.entity.Mission;
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

import java.util.List;
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
        User user = getUserFromContext();

        model.addAttribute("user", user);

        model.addAttribute("activeMissions",
                getMissionsByState(user.getMissions(), MissionState.ACTIVE));

        model.addAttribute("passedMissions",
                getMissionsByState(user.getMissions(), MissionState.PASSED));

        model.addAttribute("offeredMissions",
                getMissionsByState(user.getMissions(), MissionState.OFFERED));

        return "user/userProfile";
    }

    private List<Mission> getMissionsByState(List<Mission> missions, MissionState state) {
        return missions.stream()
                .filter(m -> state.equals(m.getState()))
                .collect(Collectors.toList());
    }

    private User getUserFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userService.getByLogin(authentication.getName());
    }
}
