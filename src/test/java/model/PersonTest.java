package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    Person person = new Doctor("Jen", "Probert", LocalDate.of(1989, 10, 15), "0711234567", "doc601123", "cosmetic dermatology");

    @Test
    void getFirstName() {
        String expected = "Jen";
        assertEquals(expected, person.getName());
    }

    @Test
    void setFirstNameName() {
        person.setName("Marc");
        String expected = "Marc";
        assertEquals(person.getName(), expected);
    }

    @Test
    void getSurname() {
        String expected = "Probert";
        assertEquals(person.getSurname(), expected);
    }

    @Test
    void setSurname() {
        person.setSurname("Daniel");
        String expected = "Daniel";
        assertEquals(person.getSurname(), expected);
    }

    @Test
    void getDateOfBirth() {
        LocalDate expected = LocalDate.of(1989, 10, 15);
        assertEquals(person.getDateOfBirth(), expected);
    }

    @Test
    void setDateOfBirth() {
        person.setDateOfBirth(LocalDate.of(1989,10,20));
        LocalDate expected = LocalDate.of(1989,10,20);
        assertEquals(person.getDateOfBirth(), expected);
    }

    @Test
    void getTelNumber() {
        String expected= "0711234567";
        assertEquals(person.getTelNumber(), expected);
    }

    @Test
    void setTelNumber() {
        person.setTelNumber("0761234567");
        String expected= "0761234567";
        assertEquals(person.getTelNumber(), expected);
    }
}