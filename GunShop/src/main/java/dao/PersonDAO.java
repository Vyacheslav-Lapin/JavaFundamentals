package dao;

import model.Person;

import java.util.Optional;

public interface PersonDAO {
    Optional<Person> getPersonById(int id);
}
