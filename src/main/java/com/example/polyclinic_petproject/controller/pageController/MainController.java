package com.example.polyclinic_petproject.controller.pageController;


import com.example.polyclinic_petproject.impl.PatientUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping()
    public String welcomeUser(Model model
            , @AuthenticationPrincipal PatientUserDetails userDetails) {
        String userName = userDetails.getUsername();
        model.addAttribute("userName", userName);

        return "mainPage";
    }
}
