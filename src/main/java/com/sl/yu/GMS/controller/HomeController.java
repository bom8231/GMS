package com.sl.yu.GMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping("/registration")
    public String registration(){
        return "board/registration";
    }

    @GetMapping("/progress")
    public String progress(){
        return "board/progress";
    }
    @GetMapping("/list")
    public String list(){
        return "board/list";
    }

    @GetMapping("/checkIn")
    public String checkIn(){
        return "board/checkIn";
    }

    @GetMapping("/checkOut")
    public String checkOut(){
        return "board/checkIn";
    }
}
