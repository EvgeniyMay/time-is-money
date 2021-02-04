package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    // TODO
    // IS ADMIN - ADMIN ?
    @GetMapping("/{login}")
    @PreAuthorize("authentication.principal.username == #login || hasRole('ADMIN')")
    public String getProfilePage(@PathVariable String login, Model model) {
        model.addAttribute("user", userService.getByLogin(login));


        return "user/userProfile";
    }
}
