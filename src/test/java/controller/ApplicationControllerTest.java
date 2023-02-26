package controller;

import manager.WestminsterSkinConsultationManager;
import model.Consultation;
import model.Doctor;
import model.Patient;
import model.Person;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationControllerTest {
    private static WestminsterSkinConsultationManager manager;

    private static ApplicationController applicationController;
    private static DefaultTableModel dtm;
    private static ArrayList<Person> savedDataDoctor;
    private static ArrayList<Consultation> savedDataConsultation;

    @BeforeAll
    public static void initBeforeAll() {
        manager = new WestminsterSkinConsultationManager();
        applicationController = new ApplicationController();
        manager.loadData();
        dtm = new DefaultTableModel();
        savedDataDoctor = new ArrayList<>(WestminsterSkinConsultationManager.doctorArray);
        savedDataConsultation = new ArrayList<>(WestminsterSkinConsultationManager.consultationArray);
    }

    @BeforeEach
    public void initBeforeEach() {
        WestminsterSkinConsultationManager.doctorArray = new ArrayList<>();
        WestminsterSkinConsultationManager.doctorArray.add(new Doctor("Jen", "Probert",
                LocalDate.of(2000, 11, 16), "0711234567",
                "doc601123", "cosmetic dermatology"));
        WestminsterSkinConsultationManager.doctorArray.add(new Doctor("Jim", "King",
                LocalDate.of(2000, 11, 16), "0711234567",
                "doc601124", "cosmetic dermatology"));
        WestminsterSkinConsultationManager.consultationArray = new ArrayList<>();
        WestminsterSkinConsultationManager.consultationArray.add(new Consultation(
                (Doctor) WestminsterSkinConsultationManager.doctorArray.get(0),
                new Patient("Mike", "Brent", LocalDate.of(1998, 10, 12),
                        "0754567891", "pat_001"),
                LocalDate.now().plusDays(2),
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                50, null, null
        ));
    }

    @AfterAll
    public static void initAfterAll() {
        WestminsterSkinConsultationManager.doctorArray = new ArrayList<>(savedDataDoctor);
        manager.save();
        WestminsterSkinConsultationManager.consultationArray = new ArrayList<>(savedDataConsultation);
        applicationController.saveConsultation();
    }

    @Test
    void setTableHeaders() {
        applicationController.setTableHeaders(dtm);
        int expected = 6;
        assertEquals(expected, dtm.getColumnCount());
    }

    @Test
    void addRows() {
        applicationController.addRows(WestminsterSkinConsultationManager.doctorArray, dtm);
        int expected = WestminsterSkinConsultationManager.doctorArray.size();
        assertEquals(expected, dtm.getRowCount());
    }

    @Test
    void addDoctorNames() {
        String[] expected = new String[WestminsterSkinConsultationManager.doctorArray.size()];
        for (int i = 0; i < WestminsterSkinConsultationManager.doctorArray.size(); i++) {
            expected[i] = "\s\s\sDr." + WestminsterSkinConsultationManager.doctorArray.get(i).getName() + " " +
                    WestminsterSkinConsultationManager.doctorArray.get(i).getSurname() + "\s\s\s";
        }
        assertArrayEquals(expected, applicationController.addDoctorNames());
    }

    @Test
    void addConsultationList() {
        assertTrue(applicationController.addConsultationList("pat_001", new JComboBox<>()));
        assertFalse(applicationController.addConsultationList("pat_002", new JComboBox<>()));
    }

    @Test
    void checkForEmptyFields() {
        assertTrue(applicationController.checkForEmptyFields(new JTextField[]{new JTextField("chamod"),
                new JTextField("lakshitha")}));
        assertFalse(applicationController.checkForEmptyFields(new JTextField[]{new JTextField("format : YYYY-MM-DD"),
                new JTextField("lakshitha")}));
    }

    @Test
    void getTime() {
        LocalTime expected = LocalTime.of(10, 30);
        assertEquals(expected, applicationController.getTime("10:30"));
    }

    @Test
    void getDate() {
        LocalDate expected = LocalDate.of(2022, 11, 12);
        assertEquals(expected, applicationController.getDate("2022-11-12"));
    }

    @Test
    void isDataValid() {
        boolean[] expected = {true, true};
        assertArrayEquals(expected, applicationController.isDataValid(new JTextField("1999-11-12"),
                new JTextField("0712345678"), new JLabel()));
    }

    @Test
    void checkTimeTime() {
        boolean expected = true;
        assertEquals(expected, applicationController.checkTimeTime(LocalTime.of(22, 0),
                LocalTime.of(23, 0)));
    }

    @Test
    void checkDateTime() {
        boolean expected = true;
        assertEquals(expected, applicationController.checkDateTime(LocalDate.now().plusDays(2),
                LocalTime.of(23, 0)));
    }

    @Test
    void checkTime() {
        LocalTime expected = LocalTime.of(1, 0);
        assertEquals(expected, applicationController.checkTime("01:00", new JLabel()));
        assertNull(applicationController.checkTime("25:00", new JLabel()));
    }

    @Test
    void checkDate() {
        LocalDate expected = LocalDate.of(2001, 10, 15);
        assertEquals(expected, applicationController.checkDate("2001-10-15", new JLabel(), true));
        assertNull(applicationController.checkDate("2022-15-10", new JLabel(), true));
    }

    @Test
    void checkDoctorAvailability() {
        JComboBox<String> jcb = new JComboBox<>(applicationController.addDoctorNames());
        jcb.setSelectedIndex(0);
        assertTrue(applicationController.checkDoctorAvailability(jcb, LocalDate.now().plusDays(2),
                LocalTime.now().plusHours(2), LocalTime.now().plusHours(4), new JLabel()));
    }

    @Test
    void checkMobileNumber() {
        boolean expected = true;
        assertEquals(expected, applicationController.checkMobileNumber(new JTextField("0712345678")));
    }

    @Test
    void calculateCost() {
        int expected = 50;
        assertEquals(expected, applicationController.calculateCost(LocalTime.of(10, 59),
                LocalTime.of(12, 0), "pat_001"));
    }

    @Test
    void saveConsultation() {
        assertTrue(applicationController.saveConsultation());
    }

    @Test
    void getSecretKey() throws IOException {
        String expected = "/_skin__Clinic_/";
        assertEquals(expected, applicationController.getSecretKey());
    }

    @Test
    void checkNotes() throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException,
            IOException, BadPaddingException, InvalidKeyException {
        assertNull(applicationController.checkNotes(new JTextArea("\s\s\s\s")));
    }

    @Test
    void deleteConsultation() {
        assertTrue(applicationController.deleteConsultation("pat_001", 0));
    }

    @Test
    void getSelectedConsultation() {
        assertTrue(applicationController.getSelectedConsultation(1));
        assertFalse(applicationController.getSelectedConsultation(0));
    }
}