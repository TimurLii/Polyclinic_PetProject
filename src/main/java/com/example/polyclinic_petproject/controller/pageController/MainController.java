package com.example.polyclinic_petproject.controller.pageController;


import com.example.polyclinic_petproject.impl.PatientUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("/")
    public String welcomeUser (Model model, Principal principal) {
        UserDetails userDetails = (UserDetails) ((Authentication) principal).getPrincipal();
        if (userDetails == null) {
            return "redirect:/login";
        }
        String username = userDetails.getUsername();
        model.addAttribute("username", username);
        return "mainPage";
    }
}
