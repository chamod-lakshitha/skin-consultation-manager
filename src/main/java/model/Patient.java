package model;

import java.time.LocalDate;

public class Patient extends Person{
    private String id;

    public Patient(String name, String surname, LocalDate dateOfBirth, String telNumber, String id) {
        super(name, surname, dateOfBirth, telNumber);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Patient ID -> " + id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return this.id.equalsIgnoreCase(patient.id);
    }
}
