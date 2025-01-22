package com.Example.config;

import com.Example.model.Person;
import com.Example.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
This Class is implementation of userDetailsService Must be annoted with service or Component
Cause we using this in Customconfig class for DAO layer user verification
 */
@Service
public class MyCustomUserDetailsService implements UserDetailsService {
    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       /* Loading user from DB */
        Person person = personRepository.findByUsername(username);
        System.out.println(person.toString());
        /*
        IF user is not Null means present then we have to verify credential and return userDetails
        */
        if (person != null) {
/*
          Method return Type is USerDetails i.e. need UserDetails implementation
            CREATED Custom UserDetails Class see config PKG PersonPrincipal class
*/
            return new PersonPrincipal(person);
        } else {
            throw new UsernameNotFoundException("User is not valid");
        }
    }
}
