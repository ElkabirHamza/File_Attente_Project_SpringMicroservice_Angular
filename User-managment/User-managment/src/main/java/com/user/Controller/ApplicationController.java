package com.user.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/res/auth")
@CrossOrigin(origins = "http://localhost:4200/")

public class ApplicationController {


    @GetMapping("/process")
    public String process(){
        return "Succes Login";
    }
}
