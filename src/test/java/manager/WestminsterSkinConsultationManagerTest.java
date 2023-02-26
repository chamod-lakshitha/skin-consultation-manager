package manager;

import model.Doctor;
import model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {

    private static WestminsterSkinConsultationManager manager;
    private static ArrayList<Person> savedData;

    @BeforeAll
    public static void initBeforeAll(){
        manager = new WestminsterSkinConsultationManager();
        manager.loadData();
        savedData = new ArrayList<>(WestminsterSkinConsultationManager.doctorArray);
    }

    @BeforeEach
    public void initBeforeEach(){
        WestminsterSkinConsultationManager.doctorArray = new ArrayList<>();
        WestminsterSkinConsultationManager.doctorArray.add(new Doctor("Jim", "King",
                LocalDate.of(2000, 11, 16), "0711234567",
                "doc601124", "cosmetic dermatology"));
        WestminsterSkinConsultationManager.doctorArray.add(new Doctor("Jane", "Queen",
                LocalDate.of(2000, 12, 26), "0711234566",
                "doc601123", "medical dermatology"));
    }

    @AfterAll
    public static void initAfterAll(){
        WestminsterSkinConsultationManager.doctorArray = new ArrayList<>(savedData);
        manager.save();
    }

    @Test
    void isAJFrameOpen() {
        assertFalse(manager.isAJFrameOpen());
    }


    @Test
    void addDoctor() {
        if(WestminsterSkinConsultationManager.doctorArray.size() <10){
            int expected = WestminsterSkinConsultationManager.doctorArray.size()+1;
            WestminsterSkinConsultationManager.doctorArray.add(new Doctor("Jen", "Probert",
                    LocalDate.of(1989, 10, 15), "0711456758",
                    "doc601129", "cosmetic dermatology"));
            assertEquals(expected, WestminsterSkinConsultationManager.doctorArray.size());
        }
    }

    @Test
    void deleteDoctor() {
        String medicalLicenceNumber = "doc601123";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(medicalLicenceNumber.getBytes());
        System.setIn(byteArrayInputStream);
        int expected = 1;
        manager.deleteDoctor();
        assertEquals(expected, WestminsterSkinConsultationManager.doctorArray.size());
    }

    @Test
    void isDoctorArrayFull() {
        if(WestminsterSkinConsultationManager.doctorArray.size() < 10) assertTrue(manager.isDoctorArrayFull());
        else assertFalse(manager.isDoctorArrayFull());
    }

    @Test
    void validateMedicalLicenceNumber() {
        String medicalLicenceNumber = "doc601114";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(medicalLicenceNumber.getBytes());
        System.setIn(byteArrayInputStream);
        String expected = "doc601114";
        assertEquals(expected, manager.validateMedicalLicenceNumber());
    }

    @Test
    void checkMedicalLicenceExists() {
        assertTrue(manager.checkMedicalLicenceExists("doc601123").isPresent());
    }

    @Test
    void checkMedicalLicenceNotExists() {
        assertFalse(manager.checkMedicalLicenceExists("doc601125").isPresent());
    }

    @Test
    void validateMobileNumber() {
        String mobileNumber = "0711234564";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileNumber.getBytes());
        System.setIn(byteArrayInputStream);
        String expected = "0711234564";
        assertEquals(expected, manager.validateMobileNumber(true));
    }

    @Test
    void checkMobileNumberExists() {
        assertTrue(manager.checkMobileNumberExists("0711234567").isPresent());
    }

    @Test
    void checkMobileNumberNotExists() {
        assertFalse(manager.checkMobileNumberExists("0701234567").isPresent());
    }

    @Test
    void save() {
        manager.save();
        manager.loadData();
        assertTrue(WestminsterSkinConsultationManager.doctorArray.size() > 0);
    }

    @Test
    void loadData() {
        manager.save();
        manager.loadData();
        assertTrue(WestminsterSkinConsultationManager.doctorArray.size() > 0);
    }

    @Test
    void getRowSeparator() {
        String expected = "+--------------------------+---------------------+------------------+------------------+---------------------+-------------------------------+";
        assertEquals(expected, manager.getRowSeparator());
    }

    @Test
    void getDate() {
        String DOB = "2000-11-12";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(DOB.getBytes());
        System.setIn(byteArrayInputStream);
        LocalDate expected = LocalDate.of(2000,11,12);
        assertEquals(expected, manager.getDate("Enter DOB (`YYYY-MM-DD`) -: ", "Date can not be empty.", true));
    }

    @Test
    void getString() {
        String firstName = "Marc";
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(firstName.getBytes());
        System.setIn(byteArrayInputStream);
        String expected = "Marc";
        assertEquals(expected, manager.getString("Enter first name -: ", "First name can not be empty."));
    }
}