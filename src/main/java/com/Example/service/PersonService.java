package com.Example.service;

import com.Example.model.Person;

import java.util.List;

public interface PersonService {
    Person saveData(Person person);

    List<Person> getAllData();
}
