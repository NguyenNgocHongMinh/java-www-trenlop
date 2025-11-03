package com.se.nguyenngochongminh_shopping_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class HomeController {
    public HomeController(){
        super();
    }

    @GetMapping("/home")
    public String HomePage(Model model){
        LocalDate date = LocalDate.now();
        String mess ="Welcome Thymeleaf";
        model.addAttribute("message", mess);
        model.addAttribute("date", date.toString());
        return "home";
    }

    @GetMapping("/")
    public String index(){
        return "redirect:/product";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }
}
