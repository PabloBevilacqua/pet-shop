package com.magicpet.petshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("")
    public String getIndex() {
        return "index";
    }
    
    @GetMapping("/about")
    public String getAbout() {
        return "about";
    }
    
    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }
}