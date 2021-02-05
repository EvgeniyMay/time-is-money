package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.UserDTO;
import com.myLearning.timeIsMoney.exception.LoginAlreadyExistException;
import com.myLearning.timeIsMoney.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AuthorizationController {

    private final UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        model.addAttribute("userForm", new UserDTO());

        return "authorization/signup";
    }

    @PostMapping("/signup")
    public String signupUser(@ModelAttribute("userForm") @Valid UserDTO userForm,
                             BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "authorization/signup";
        }

        userService.createUser(userForm);

        return "redirect:/login";
    }


    @GetMapping("/login")
    public String getLoginPage(@RequestParam(name = "error", defaultValue = "false") boolean loginError,
                               Model model) {
        if(loginError) {
            model.addAttribute("errorMessage", "{login.input.error.data-password}");
        }

        return "authorization/login";
    }

    @GetMapping("/loginSuccess")
    public String getLoginSuccessPage() {
        return "authorization/loginSuccess";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "authorization/logout";
    }


    @ExceptionHandler(LoginAlreadyExistException.class)
    public String handleRuntimeException(LoginAlreadyExistException e,
                                         Model model) {
        model.addAttribute("userForm", new UserDTO());
        model.addAttribute("loginExistMessage", e.getMessage());

        return "authorization/signup";
    }
}
