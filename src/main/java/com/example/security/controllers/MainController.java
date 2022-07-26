package com.example.security.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @GetMapping("/")
    public String homePage(){
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return "secured part of web service: " + principal.getName();
    }

    @GetMapping("/read_profile")
    public String pageForReadProfile(){
        return "this is a read profile page";
    }

    @GetMapping("/only_for_admins")
    public String pageOnlyForAdmins(){
        return "this is a admins page";
    }
}
