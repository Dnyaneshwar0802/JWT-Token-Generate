package com.Example.serviceImpl;

import com.Example.model.Person;
import com.Example.repository.PersonRepository;
import com.Example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
This is Service Implimentation for person model class
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    /*
        Method to save Person data into DB
    */
    @Override
    public Person saveData(Person person) {
        Person p = personRepository.save(person);
        return p;
    }

    @Override
    public List<Person> getAllData() {
        List<Person> getAll = personRepository.findAll();
        return getAll;
    }

    @Override
    public Optional<Person> verify(String username, String password) {
        Person p = personRepository.findByUsername(username);
        System.out.println(p);
        if (p.getPassword().equals(password)) {
            return Optional.of(p);
        }
        return Optional.empty();
    }
}
