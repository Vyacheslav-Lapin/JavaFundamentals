package dao.interfaces;

import com.epam.courses.jf.jdbc.common.Dao;
import model.Person;

import java.util.Optional;

public interface PersonDao extends Dao {
    Optional<Person> getPersonById(int id);
    Optional<String> getPasswordByEmail(String email);
    boolean setPasswordByEmail(String userName, String pwdHash);
    Optional<Person> getPersonByEmail(String email);
}
