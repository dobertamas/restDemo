package com.tamasdober.demo.dao;

import com.tamasdober.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static final List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {

        DB.add(new Person(id, person.getName()));
        return 1;
    }

    @Override
    public Optional<Person> findPersonById(UUID id) {

        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();

    }

    @Override
    public int deletePersonById(UUID id) {

        Optional<Person> personByIdMaybe = findPersonById(id);
        if (personByIdMaybe.isEmpty()) {
            return 0;
        } else {
            DB.remove(personByIdMaybe.get());
            return 1;
        }
    }

    @Override
    public int updatePersonById(UUID id, Person personToUpdate) {

        return findPersonById(id).map(person -> {
            int indexOfPersonToUpdate = DB.indexOf(person);
            if (indexOfPersonToUpdate >= 0) {
                DB.set(indexOfPersonToUpdate, new Person(id, personToUpdate.getName()));
                return 1;
            }
            return 0;

        }).orElse(0);


    }

    @Override
    public List<Person> getAllPeople() {

        return DB;
    }
}
