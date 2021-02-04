package com.myLearning.timeIsMoney.controller;

import com.myLearning.timeIsMoney.dto.UserDTO;
import com.myLearning.timeIsMoney.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class AuthorizationController {

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

        //userService.signupNewUser(userForm);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(name = "error", defaultValue = "false") boolean loginError, Model model) {
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

    @GetMapping("/accessDenied")
    public String getAccessDeniedPage() {
        return "authorization/accessDenied";
    }

}
