package model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Person {
    private final int id;
    private String firstName;
    private String lastName;
    private boolean permission;
    private final LocalDate dob;
    private String email;
    private String password;
    private String address;
    private String telephone;
}
