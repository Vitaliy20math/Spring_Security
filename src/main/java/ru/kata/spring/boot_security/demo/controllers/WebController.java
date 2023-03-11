package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class WebController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    public String userPage(Principal principal) {return principal.getName();}

    @GetMapping("/test")
    public String test() {return "test";}
}
