package service;

import dao.interfaces.PersonDao;
import model.Person;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class SecurityService {

    private PersonDao personDao;
    private static final MessageDigest md5;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public SecurityService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public Optional<Person> checkAndGetPerson(String userName, String password) {

        final Optional<String> passwordByEmail = personDao.getPasswordByEmail(userName);

        if (passwordByEmail.isPresent()) {
            String pwdHash = passwordByEmail.get();
            if (pwdHash.length() != 32) {
                pwdHash = hash(pwdHash);
                personDao.setPasswordByEmail(userName, pwdHash);
            }
            return hash(password).equals(pwdHash)
                    ? personDao.getPersonByEmail(userName)
                    : Optional.empty();
        } else
            return Optional.empty();

    }

    private String hash(String string) {

        final StringBuilder result = new StringBuilder();

        md5.reset();
        byte[] bytes = md5.digest(string.getBytes());
        for (byte b: bytes) {
            String hexVal = Integer.toHexString(0xFF & b);
            if (hexVal.length() == 1)
                result.append("0");
            result.append(hexVal);
        }

        return result.toString();
    }
}
