package com.yeshua.clinicapp.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yeshua.clinicapp.services.UserService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	UserService userService;

	@RequestMapping("")
    public String patientPage(Principal principal, Model model) {
        String email = principal.getName();
        model.addAttribute("currentUser", userService.findByEmail(email));
        return "patientPage.jsp";
    }
}
