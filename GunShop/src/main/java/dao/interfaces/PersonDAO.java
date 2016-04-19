package dao.interfaces;

import model.Person;

import java.util.Optional;

public interface PersonDao {
    Optional<Person> getPersonById(int id);
}
