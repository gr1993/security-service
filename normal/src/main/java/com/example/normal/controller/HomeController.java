package com.example.normal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class HomeController {

    @GetMapping("main")
    public String main() {
        return "main";
    }
}
