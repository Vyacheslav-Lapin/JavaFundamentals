package model;

import java.time.LocalDate;
import java.util.Objects;

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

    public Person(int id, LocalDate dob) {
        this(id, "", "", false, null, "", "", "", "");
    }

    public Person(int id, String firstName, String lastName, boolean permission, LocalDate dob, String email, String password, String address, String telephone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permission = permission;
        this.dob = dob;
        this.email = email;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean isPermission() {
        return permission;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public boolean equals(Object o) {
        Person person;
        return this == o
                || !(o == null || getClass() != o.getClass())
                && id == (person = (Person) o).id
                && Objects.equals(dob, person.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dob);
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", permission=" + permission +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
