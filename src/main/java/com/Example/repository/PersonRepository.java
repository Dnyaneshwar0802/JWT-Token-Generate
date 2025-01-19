package com.Example.repository;

import com.Example.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
This is repo layer for Person class mentioned and primary key
*/
@Repository
public interface PersonRepository extends JpaRepository<Person,Integer> {
}
