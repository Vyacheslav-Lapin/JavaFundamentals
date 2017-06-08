package users;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class UserTest {

    @Test
    @SneakyThrows
    void name() {
        User user = new User("dsf", "khgsg", LocalDate.now());
        System.out.println(user.getLastName());

        Class.forName("javaslang.control.Try");
    }
}