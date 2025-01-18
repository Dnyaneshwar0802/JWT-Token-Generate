package com.Example.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/studentRestController")
public class StudentRestController {
    @GetMapping("/getMessage")
    public String getMessage(){
    return "Welcome to SB project";
    }

}
