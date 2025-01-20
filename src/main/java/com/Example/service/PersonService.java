package com.Example.service;

import com.Example.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person saveData(Person person);

    List<Person> getAllData();

    Optional<Person> verify(String username, String password);
}
