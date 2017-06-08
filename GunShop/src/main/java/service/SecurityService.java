package service;

import com.hegel.core.StringEncryptUtil;
import dao.interfaces.PersonDao;
import model.Person;

import java.util.Optional;

public class SecurityService {

    private PersonDao personDao;

    public SecurityService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public Optional<Person> checkAndGetPerson(String userName, String password) {

        final Optional<String> passwordByEmail = personDao.getPasswordByEmail(userName);

        if (passwordByEmail.isPresent()) {
            String pwdHash = passwordByEmail.get();
            if (pwdHash.length() != 32) {
                pwdHash = StringEncryptUtil.encrypt(pwdHash);
                personDao.setPasswordByEmail(userName, pwdHash);
            }
            return StringEncryptUtil.encrypt(password).equals(pwdHash)
                    ? personDao.getPersonByEmail(userName)
                    : Optional.empty();
        } else
            return Optional.empty();

    }
}
