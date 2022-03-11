package com.example.pergamumlibrary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloMap {

    @RequestMapping("/hello")
    public String getHelloWorld(){
        return "Hello World and Goodbye.";
    }
}
