package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DoctorTest {

    Person doctor = new Doctor("Jen", "Probert", LocalDate.of(2000, 11, 16),
            "0711234567", "doc601123", "cosmetic dermatology");

    @Test
    void getMedicalLicenceNumber() {
        String expected = "doc601123";
        assertEquals(expected, ((Doctor) doctor).getMedicalLicenceNumber());
    }

    @Test
    void getSpecialisation() {
        String expected = "cosmetic dermatology";
        assertEquals(expected, ((Doctor) doctor).getSpecialisation());
    }

    @Test
    void setSpecialisation() {
        ((Doctor) doctor).setSpecialisation("medical dermatology");
        String expected = "medical dermatology";
        assertEquals(expected, ((Doctor) doctor).getSpecialisation());
    }
    @Test
    void setMedicalLicenceNumber() {
        ((Doctor) doctor).setMedicalLicenceNumber("doc601124");
        String expected = "doc601124";
        assertEquals(expected, ((Doctor) doctor).getMedicalLicenceNumber());
    }

    @Test
    void testEquals() {
        assertEquals(doctor, new Doctor("Jen", "Probert", LocalDate.of(1991, 10, 26), "0711234567", "doc601123", "paediatric dermatology"));
        assertNotEquals(doctor, new Doctor("Mike", "Brent", LocalDate.of(1997, 12, 18), "0761234567", "doc601124", "cosmetic dermatology"));
    }
}