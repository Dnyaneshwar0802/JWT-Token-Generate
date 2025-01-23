package com.Example.restController;

import com.Example.model.Person;
import com.Example.service.PersonService;
import com.Example.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/personRestController")
public class PersonRestController {
    @Autowired
    PersonService personService;
    @Autowired
    AuthenticationProvider authenticationProvider;
    @Autowired
    JWTUtil jwtUtil;

    /*
    This method simply return message
    */
    @GetMapping("/getMessage")
    public String getMessage() {
        return "Welcome to SB project";
    }

    /*
    Writing method To Store Peron in DB
    */
    @PostMapping("/saveData")
    public Person saveData(@RequestBody Person person) {
        System.out.println(person);
        Person p = personService.saveData(person);
        return p;
    }
/*
Writing Method to see all data
*/

    @GetMapping("/getAllData")
    public List<Person> getAllData() {
        List<Person> allPersonData = personService.getAllData();
        return allPersonData;
    }

    @GetMapping("/signup/{username}/{password}")
    public Optional<Person> login(@PathVariable("username") String username, @PathVariable("password") String password) {
        System.out.println("This is Username" + username + "Ths is pass >>" + password);
        Optional<Person> p = personService.verify(username, password);
        return p;

    }

    /*
    This method is public url which help user to sign in
        After Sign in it generate the JWT token
        */
    @GetMapping("/signin")
    public String singnin(@RequestBody Person person) {
        if (person.getUsername() != null && person.getPassword() != null) {
            Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(person.getUsername(), person.getPassword()));
            if (authentication.isAuthenticated()) {
                return jwtUtil.jwtTokenGenerate(authentication);
            }
            return "Something Wrong !!";
        }
        return "Something Wrong !!";
    }
}
