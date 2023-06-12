package com.example.uczelnie.endpoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPage {

    @GetMapping("/main")
    public String getSpecificUrl() {
        return "index";
    }
}