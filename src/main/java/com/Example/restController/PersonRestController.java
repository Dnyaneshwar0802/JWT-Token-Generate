package com.Example.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personRestController")
public class PersonRestController {

    @GetMapping("/getMessage")
    public String getMessage() {
        return "Welcome to SB project";
    }

}
