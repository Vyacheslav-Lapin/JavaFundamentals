package users;


import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.var;

import java.time.LocalDate;

@Value
@RequiredArgsConstructor
public class User {
    private final String firstName;
    private String lastName;
    private LocalDate dob;

    public static void main(String[] args) {
        var now = LocalDate.now();


    }
}
