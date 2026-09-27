package com.example.SpringOauthAssignment.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AbcController {

    private static final Logger logger = LoggerFactory.getLogger(AbcController.class);

    @GetMapping("/")
    public String home(){
        logger.info("Access to public home page");
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {

            String name = principal.getAttribute("name");
            String email = principal.getAttribute("email");

            model.addAttribute("name", name);
            model.addAttribute("email", email);
            model.addAttribute("isAuthenticated", true);
        } else {
            model.addAttribute("isAuthenticated", false);
        }
        return "dashboard";
    }
}
