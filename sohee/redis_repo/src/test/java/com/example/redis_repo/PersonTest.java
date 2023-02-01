package com.example.redis_repo;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonTest {
    @Autowired
    PersonRepository repo;

    @Test
    public void basicCrudOperations() {

        Person rand = new Person("sohee", "first", "last");

        repo.save(rand);

        Optional<Person> person = repo.findById(rand.getId());

        System.out.println(person.toString());

        repo.count();

        person = repo.findById(rand.getId());

        System.out.println(person.toString());

        repo.delete(rand);
    }
}
