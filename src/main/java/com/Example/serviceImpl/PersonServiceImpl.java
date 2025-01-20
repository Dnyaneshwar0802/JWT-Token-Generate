package com.Example.serviceImpl;

import com.Example.model.Person;
import com.Example.repository.PersonRepository;
import com.Example.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
       Person p= personRepository.save(person);
        return p;
    }
}
