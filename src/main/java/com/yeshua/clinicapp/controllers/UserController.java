package com.yeshua.clinicapp.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yeshua.clinicapp.models.User;
import com.yeshua.clinicapp.services.UserService;
import com.yeshua.clinicapp.validator.UserValidator;

@Controller
public class UserController {
    
	@Autowired
	UserService userService;
	@Autowired
	UserValidator userValidator;
    
    @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result,
    		Model model,
    		@RequestParam("role") Long role) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "registrationPage.jsp";
        }
        if (role == 1) userService.saveUserWithDoctorRole(user);
        if (role == 2) userService.saveUserWithAdminRole(user);
        userService.saveUserWithPatientRole(user);
        return "redirect:/admin";
    }
    
    @RequestMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        String email = principal.getName();
        User user = userService.findByEmail(email);
        if (user.getRoles().get(0).getName().contains("ROLE_DOCTOR")) return "redirect:/doctor";
        if (user.getRoles().get(0).getName().contains("ROLE_ADMIN")) return "redirect:/admin";
        else return "redirect:/patient";
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }
        return "loginPage.jsp";
    }
}
