package service;

import dao.interfaces.PersonDao;
import model.Person;

import static crypto.EncryptPassword.encryptPassword;

public class PersonService {

    private PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public boolean checkPwd(String login, String password) {
        String encryptedPassword = encryptPassword(password);
        return personDao.getPasswordByEmail(login)
                .filter(encryptedPassword::equals)
                .isPresent();
    }

    public boolean checkPwd(Person person) {
        return checkPwd(person.getEmail(), person.getPassword());
    }
}
