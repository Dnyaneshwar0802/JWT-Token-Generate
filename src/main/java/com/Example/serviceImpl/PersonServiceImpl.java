package com.Example.serviceImpl;

import com.Example.model.Person;
import com.Example.service.PersonService;
import org.springframework.stereotype.Service;

/*
This is Service Implimentation for person model class
 */
@Service
public class PersonServiceImpl implements PersonService {
/*
    Method to save Person data into DB
*/
    @Override
    public Person saveData(Person person) {
        return null;
    }
}
