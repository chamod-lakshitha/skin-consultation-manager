package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    Person patient = new Patient("Jen", "Probert", LocalDate.of(2000, 11, 16),
            "0711234567", "pat_001");

    @Test
    void getId() {
        String expected = "pat_001";
        assertEquals(expected, ((Patient) patient).getId());
    }

    @Test
    void setId() {
        ((Patient) patient).setId("pat_002");
        String expected = "pat_002";
        assertEquals(expected, ((Patient) patient).getId());
    }
}