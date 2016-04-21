package service;

import dao.interfaces.PersonDao;
import model.Person;

import java.util.Optional;

public class SecurityService {

    private PersonDao personDao;

    public SecurityService(PersonDao personDao) {
        this.personDao = personDao;
    }


    public Optional<Person> checkAndGetPerson(String userName, String password) {

    }
}
