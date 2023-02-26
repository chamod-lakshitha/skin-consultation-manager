package model;

import java.time.LocalDate;
import java.util.Objects;

public class Doctor extends Person {
    private String medicalLicenceNumber;
    private String specialisation;

    public Doctor(String name, String surname, LocalDate dateOfBirth, String telNumber, String medicalLicenceNumber, String specialisation) {
        super(name, surname, dateOfBirth, telNumber);
        this.medicalLicenceNumber = medicalLicenceNumber;
        this.specialisation = specialisation;
    }

    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Medical licence number -> " + medicalLicenceNumber +
                ", Specialisation -> " + specialisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(medicalLicenceNumber, doctor.medicalLicenceNumber);
    }
}
