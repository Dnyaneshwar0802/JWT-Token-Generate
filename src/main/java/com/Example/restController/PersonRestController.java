package com.Example.restController;

import com.Example.model.Person;
import com.Example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personRestController")
public class PersonRestController {
    @Autowired
    PersonService personService;

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
        Person p=personService.saveData(person);
        return p;
    }
/*
Writing Method to see all data
*/

    @GetMapping("/getAllData")
    public List<Person> getAllData() {
          List<Person> allPersonData=personService.getAllData();
        return allPersonData;
    }
}
