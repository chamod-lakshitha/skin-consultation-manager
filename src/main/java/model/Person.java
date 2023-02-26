package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Person implements Comparable<Person>, Serializable {
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private String telNumber;

    public Person(String name, String surname, LocalDate dateOfBirth, String telNumber) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.telNumber = telNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    @Override
    public String toString() {
        return "Name -> " + name +
                ", Surname -> " + surname +
                ", DOB -> " + dateOfBirth.format(DateTimeFormatter.ofPattern("yyyy-MMMM-dd")) +
                ", Mobile number -> " + telNumber + ", ";
    }

    @Override
    public int compareTo(Person person) {
        int i = Integer.compare(this.getSurname().compareTo(person.getSurname()), 0);
        if(i == 0){
            return Integer.compare(this.getName().compareTo(person.getName()), 0);
        }else {
            return i;
        }
    }
}
