package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Mycontroller {
    @GetMapping("/")
    public String index() {
        return "redirect:/users";
    }
}
