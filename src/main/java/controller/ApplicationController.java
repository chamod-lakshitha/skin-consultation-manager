package controller;

import manager.WestminsterSkinConsultationManager;
import model.Consultation;
import model.Doctor;
import model.Patient;
import model.Person;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import java.util.List;
import java.util.stream.IntStream;

public class ApplicationController {
    public ApplicationController() {
    }

    /*
    setting table headers
     */
    public void setTableHeaders(DefaultTableModel dtm) {
        Arrays.asList(new String[]{"DOCTOR NAME", "DOCTOR SURNAME", "DOB", "TEL NUMBER",
                "LICENCE NUMBER", "SPECIALIZATION"}).forEach(dtm::addColumn); //iterating over the arraylist and adding column names of the table
    }

    /*
    centering table data
     */
    public void justifyTableData(JTable jt, DefaultTableCellRenderer dtcr) {
        IntStream.range(0, 6).forEach(index -> jt.getColumnModel().getColumn(index).setCellRenderer(dtcr));
    }

    /*
    adding data row by row
     */
    public void addRows(ArrayList<Person> arrayList, DefaultTableModel dtm) {
        arrayList.forEach(doctor -> dtm.addRow(new String[]{
                "Dr.\s" + doctor.getName(), doctor.getSurname(),
                String.valueOf(doctor.getDateOfBirth()),
                doctor.getTelNumber(),
                ((Doctor) doctor).getMedicalLicenceNumber(),
                ((Doctor) doctor).getSpecialisation()
        }));
    }

    /*
    adding data to JTable
     */
    public void setTableData(DefaultTableModel dtm, boolean alphabeticalOrder) {
        if (alphabeticalOrder) {
            dtm.setRowCount(0);
            ArrayList<Person> tempArrayList = new ArrayList<>(WestminsterSkinConsultationManager.doctorArray);
            Collections.sort(tempArrayList); //sorting array using person comparable by surname
            addRows(tempArrayList, dtm);
        } else {
            dtm.setRowCount(0);
            addRows(WestminsterSkinConsultationManager.doctorArray, dtm);
        }
    }

    /*
    getting the name list of the doctors available in clinic
     */
    public String[] addDoctorNames() {
        String[] doctorNamesArray = new String[WestminsterSkinConsultationManager.doctorArray.size()];
        for (int i = 0; i < WestminsterSkinConsultationManager.doctorArray.size(); i++) {
            doctorNamesArray[i] = "\s\s\sDr." + WestminsterSkinConsultationManager.doctorArray.get(i).getName() + " "
                    + WestminsterSkinConsultationManager.doctorArray.get(i).getSurname() + "\s\s\s";
        }
        return doctorNamesArray;
    }

    /*
    preparing the booked consultation list of a particular patient id
     */
    public boolean addConsultationList(String patientID, JComboBox<String> jcb) {
        List<Consultation> filteredConsultationList = WestminsterSkinConsultationManager.consultationArray.stream()
                .filter(consultation -> consultation.getPatient().getId().equalsIgnoreCase(patientID)).toList();
        if (!filteredConsultationList.isEmpty()) {
            jcb.addItem("\s\s\schoose the consultation");
            for (int i = 0; i < filteredConsultationList.size(); i++) {
                jcb.addItem("\s\s\s" + (i + 1) + ".\s Dr.\s" + filteredConsultationList.get(i).getDoctor().getName()
                        + "\s" + filteredConsultationList.get(i).getDoctor().getSurname() + "\s["
                        + filteredConsultationList.get(i).getDate() + "]"
                        + "\s[" + filteredConsultationList.get(i).getConsultationStartTime() + " - "
                        + filteredConsultationList.get(i).getConsultationEndTime() + "]"
                );
            }
            return true;
        }
        return false;
    }

    /*
    checking mandatory JTextFields that are not filled
     */
    public boolean checkForEmptyFields(JTextField[] textFieldsArray) {
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("format : YYYY-MM-DD", "format (24H) : HH:MM",
                "ENTER NAME", "ENTER SURNAME", "DOB (YYYY-MM-DD)", "07XXXXXXXX", "ENTER PATIENT ID"));
        for (JTextField jtf : textFieldsArray) {
            if (arrayList.contains(jtf.getText())) return false;
        }
        return true;
    }

    /*
    validating inserted data
     */
    public boolean[] isDataValid(JTextField jtf1, JTextField jtf2, JLabel jl) {
        LocalDate DOB = checkDate(jtf1.getText().trim(), jl, true);
        if (DOB == null) return new boolean[]{false, false};
        else if (!checkMobileNumber(jtf2)) return new boolean[]{true, false};
        else return new boolean[]{true, true};
    }

    /*
    validating inserted data
     */
    public boolean[] isDataValid(JTextField[] textFieldArray, JLabel jl) {
        LocalDate date = checkDate(textFieldArray[0].getText().trim(), jl, false);
        if (date == null) return new boolean[]{false, false, false, false, false};
        else {
            LocalTime startTime = checkTime(textFieldArray[1].getText().trim(), jl);
            if (startTime == null) return new boolean[]{true, false, false, false, false};
            else {
                LocalTime endTime = checkTime(textFieldArray[2].getText().trim(), jl);
                if (endTime == null) return new boolean[]{true, true, false, false, false};
                else {
                    if (!checkDateTime(date, startTime)) return new boolean[]{true, true, true, false, false};
                    else if (!checkTimeTime(startTime, endTime)) return new boolean[]{true, true, true, true, false};
                    else return new boolean[]{true, true, true, true, true};
                }
            }
        }
    }

    /*
    checking whether the consultation start time is before the consultation end time
     */
    public boolean checkTimeTime(LocalTime startTime, LocalTime endTime) {
        return endTime.isAfter(startTime);
    }

    /*
    checking if the time is valid
     */
    public boolean checkDateTime(LocalDate date, LocalTime startTime) {
        if (date.equals(LocalDate.now())) {
            return startTime.isAfter(LocalTime.now());
        }
        return true;
    }

    /*
    time validation
     */
    public LocalTime checkTime(String time, JLabel jl) {
        try {
            if (time.matches("([01][0-9]|2[0-3]):[0-5][0-9]"))
                return getTime(time); //checking if the time entered match with the regex
            else jl.setText("INVALID TIME");
        } catch (Exception ex) {
            jl.setText(ex.getMessage().toUpperCase());
            return null;
        }
        return null;
    }

    /*
    getting the LocalTime
     */
    public LocalTime getTime(String time) {
        try {
            String[] timeFields = time.split(":");
            return LocalTime.of(Integer.parseInt(timeFields[0]), Integer.parseInt(timeFields[1]));
        } catch (Exception ex) {
            return null;
        }
    }

    /*
    date validation
     */
    public LocalDate checkDate(String date, JLabel jl, boolean flag) {
        try {
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) { //checking the format of the date inserted
                LocalDate localDate = getDate(date);
                if (localDate != null) {
                    if (flag) {// check if it is valid date for a consultation or DOB
                        if (localDate.isBefore(LocalDate.now())) return localDate;
                        else jl.setText("BIRTHDAY CAN NOT BE A FUTURE DATE OR EVEN TODAY");
                    } else {
                        if (localDate.isAfter(LocalDate.now()) || localDate.isEqual(LocalDate.now()))
                            return localDate; //checking the correctness date(consultation date should be a today or future date)
                        else jl.setText("DATE CAN NOT BE A PAST DATE");
                    }
                } else jl.setText("INVALID DATE");
            } else jl.setText("INVALID DATE");
        } catch (Exception ex) {
            jl.setText(ex.getMessage().toUpperCase());
            return null;
        }
        return null;
    }

    /*
    getting the LocalDate
     */
    public LocalDate getDate(String date) {
        try {
            String[] dateFields = date.split("-");
            return LocalDate.of(Integer.parseInt(dateFields[0]), Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[2]));
        } catch (Exception ex) {
            return null;
        }
    }

    /*
    checking the doctor availability on a particular date and time
     */
    public boolean checkDoctorAvailability(JComboBox<String> jcb, LocalDate consultationDate, LocalTime consultationStartTime,
                                           LocalTime consultationEndTime, JLabel jl) {
        String selectedDoctor = (String) jcb.getSelectedItem();
        List<Consultation> tempArraylist = new ArrayList<>(WestminsterSkinConsultationManager.consultationArray); //creating a temp consultation arraylist copy to prevent happening changes to the original arraylist
        List<Consultation> list = tempArraylist.stream()
                .filter(consultation -> consultation.getDoctor().equals(WestminsterSkinConsultationManager.doctorArray.get(
                        jcb.getSelectedIndex())) && consultation.getDate().equals(consultationDate)).toList(); //retrieving the consultation of the selected doctor on the entered date
        if (list.isEmpty()) {
            return true;
        } else {
            if (list.stream().anyMatch(consultation -> consultation.getConsultationStartTime().isBefore(consultationEndTime)
                    && consultation.getConsultationEndTime().isAfter(consultationStartTime))) { //checking if the doctor is available on the entered and time
                jl.setText("doctor is not available");
                ArrayList<Integer> availableDoctorIndexArrayList = new ArrayList<>();
                for (int i = 0; i < WestminsterSkinConsultationManager.doctorArray.size(); i++) {
                    if (i != jcb.getSelectedIndex()) {
                        int doctorIndex = i;
                        if (WestminsterSkinConsultationManager.consultationArray.stream()
                                .filter(consultation -> consultation.getDoctor().equals(WestminsterSkinConsultationManager.
                                        doctorArray.get(doctorIndex)) && consultation.getDate().equals(consultationDate))
                                .noneMatch(consultation -> consultation.getConsultationStartTime().isBefore(consultationEndTime)
                                        && consultation.getConsultationEndTime().isAfter(consultationStartTime))) { //checking for the available doctors
                            availableDoctorIndexArrayList.add(i);
                        }
                    }
                }
                if (availableDoctorIndexArrayList.isEmpty()) {
                    jl.setText("No doctors available. Try to make a consultation with new date time");
                    return false;
                } else {
                    jcb.setSelectedIndex(availableDoctorIndexArrayList.get(new Random().nextInt(availableDoctorIndexArrayList.size()))); //assigning an available doctor randomly
                    jl.setText("<HTML>" + selectedDoctor + " is not available on this specific date and time. You were assigned to " +
                            jcb.getSelectedItem() + "</html>");
                    return true;
                }
            } else {
                return true;
            }
        }
    }

    /*
    validating mobile phone number
     */
    public boolean checkMobileNumber(JTextField jtf) {
        try {
            Integer.parseInt(jtf.getText().trim());//checking whether all are numbers
            return jtf.getText().trim().length() == 10 && jtf.getText().trim().startsWith("07"); //checking the length of the mobile number and first two digits and returning
        } catch (Exception ex) {
            return false;
        }
    }

    /*
    calculating consultation cost
     */
    public int calculateCost(LocalTime localTime1, LocalTime localTime2, String id) {
        if (WestminsterSkinConsultationManager.consultationArray.isEmpty()
                || (WestminsterSkinConsultationManager.consultationArray.stream().
                noneMatch(consultation -> consultation.getPatient().getId().equalsIgnoreCase(id)))) //checking if the user it the user's first consultation with the clinic
            return (int) (15 * Math.ceil(ChronoUnit.MINUTES.between(localTime1, localTime2) / 60.0));
        else return (int) (25 * Math.ceil(ChronoUnit.MINUTES.between(localTime1, localTime2) / 60.0));
    }

    /*
    getting the secret required for encryption and decryption
     */
    public String getSecretKey() throws IOException {
        return Files.readAllLines(Paths.get("src/main/repo/secretKey.txt")).get(0);
    }

    /*
    encrypting the notes inserted notes
     */
    public byte[] encryptNotes(String notes) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(getSecretKey().getBytes(), "AES")); //initializing the encryption mode
        return cipher.doFinal(notes.getBytes());
    }

    /*
    decrypting the encrypted notes
     */
    public String decryptNotes(byte[] encryptedByteArray) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getSecretKey().getBytes(), "AES")); //initializing the decryption mode
        return new String(cipher.doFinal(encryptedByteArray));
    }

    /*
    encrypting the notes selected images
     */
    public byte[][] encryptImages(JFileChooser jfc) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[][] encryptedImagesByteArray = new byte[jfc.getSelectedFiles().length][];
        for (int i = 0; i < encryptedImagesByteArray.length; i++) {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(getSecretKey().getBytes(), "AES")); //initializing the encryption mode
            FileInputStream inputStream = new FileInputStream(jfc.getSelectedFiles()[i].getAbsolutePath());
            byte[] array = new byte[inputStream.available()];
            inputStream.read(array); //reading available bytes
            encryptedImagesByteArray[i] = cipher.doFinal(array);
        }
        return encryptedImagesByteArray;
    }

    /*
    decrypting the encrypted images
     */
    public void decryptImages(byte[][] imageArray, JPanel jp) throws NoSuchPaddingException, NoSuchAlgorithmException,
            IOException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(getSecretKey().getBytes(), "AES")); //initializing the decryption mode
        for (byte[] imageBytes : imageArray) {
            jp.add(new JLabel(new ImageIcon(new ImageIcon(cipher.doFinal(imageBytes)).getImage().
                    getScaledInstance(205, 205, Image.SCALE_SMOOTH)))); //adding image to the jLabel
        }
        jp.revalidate();
        jp.repaint();
    }

    /*
    checking if the user has added some notes
     */
    public byte[] checkNotes(JTextArea jTextArea) throws NoSuchPaddingException, IllegalBlockSizeException,
            NoSuchAlgorithmException, IOException, BadPaddingException, InvalidKeyException {
        if (jTextArea.getText().equalsIgnoreCase("\sADD NOTES (OPTIONAL)") || jTextArea.getText().trim().isEmpty())
            return null;
        else return encryptNotes(jTextArea.getText().trim());
    }

    /*
    checking if the user has added some images
     */
    public byte[][] checkImages(JFileChooser jfc) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        if (jfc.getSelectedFiles().length > 0)
            return encryptImages(jfc);
        return null;
    }

    /*
    adding the consultation
     */
    public boolean addConsultation(JComboBox<String> jcb, JTextField[] jTextFieldArray, JTextArea jta, JLabel jl, JFileChooser jfc)
            throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, BadPaddingException, InvalidKeyException {
        Consultation consultation = new Consultation((Doctor) WestminsterSkinConsultationManager.doctorArray.
                get(jcb.getSelectedIndex()), new Patient(jTextFieldArray[3].getText().trim(),
                jTextFieldArray[4].getText().trim(), getDate(jTextFieldArray[5].getText().trim()), jTextFieldArray[6].getText().trim(),
                jTextFieldArray[7].getText().trim()), getDate(jTextFieldArray[0].getText().trim()),
                getTime(jTextFieldArray[1].getText().trim()), getTime(jTextFieldArray[2].getText().trim()),
                Integer.parseInt(jl.getText().substring(2)), checkNotes(jta), checkImages(jfc));
        WestminsterSkinConsultationManager.consultationArray.add(consultation); //adding consultation to the consultation arrayList
        return saveConsultation();
    }

    public boolean deleteConsultation(String patientID, int consultationIndex) {
        List<Consultation> filteredConsultation = WestminsterSkinConsultationManager.consultationArray.stream()
                .filter(consultation -> consultation.getPatient().getId().equalsIgnoreCase(patientID)).toList();
        WestminsterSkinConsultationManager.consultationArray.remove(filteredConsultation.get(consultationIndex)); //removing consultation to the consultation arrayList
        return saveConsultation();
    }

    /*
    saving the consultation
     */
    public boolean saveConsultation() {
        try {
            new ObjectOutputStream(new FileOutputStream("./src/main/repo/consultationRepo.txt")).
                    writeObject(WestminsterSkinConsultationManager.consultationArray);
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /*
    getting selected consultation
     */
    public boolean getSelectedConsultation(int index) {
        return index != 0;
    }

    /*
    displaying/setting consultation data
     */
    public void setConsultationData(String patientID, int index, JTextField[] textFieldArray, JTextArea jta, JLabel jl, JPanel jp) {
        List<Consultation> patientConsultationList = WestminsterSkinConsultationManager.consultationArray.stream()
                .filter(consultation -> consultation.getPatient().getId().equalsIgnoreCase(patientID)).toList();
        textFieldArray[0].setText(patientConsultationList.get(index).getPatient().getId());
        textFieldArray[1].setText(patientConsultationList.get(index).getPatient().getName());
        textFieldArray[2].setText(patientConsultationList.get(index).getPatient().getSurname());
        textFieldArray[3].setText(String.valueOf(patientConsultationList.get(index).getPatient().getDateOfBirth()));
        textFieldArray[4].setText(patientConsultationList.get(index).getPatient().getTelNumber());
        textFieldArray[5].setText("Dr.\s" + patientConsultationList.get(index).getDoctor().getName() + "\s" +
                patientConsultationList.get(index).getDoctor().getSurname());
        textFieldArray[6].setText(String.valueOf(patientConsultationList.get(index).getDate()));
        textFieldArray[7].setText(patientConsultationList.get(index).getConsultationStartTime() + " - " +
                patientConsultationList.get(index).getConsultationEndTime());
        jl.setText("€\s" + patientConsultationList.get(index).getCost());
        try {
            if (patientConsultationList.get(index).getNotes() != null) //checking if there's are notes available in the selected consultation
                jta.setText(decryptNotes(patientConsultationList.get(index).getNotes()));
            else jta.setText("--");
        } catch (Exception ex) {
            jta.setText(ex.getMessage());
        }
        if (patientConsultationList.get(index).getImagesArray() != null) { //checking if there's are images available in the selected consultation
            try {
                decryptImages(patientConsultationList.get(index).getImagesArray(), jp);
            } catch (Exception ex) {
                jp.add(new JLabel("Error occurred, Images are not loaded."));
                jp.revalidate();
                jp.repaint();
            }
        }
    }
}
