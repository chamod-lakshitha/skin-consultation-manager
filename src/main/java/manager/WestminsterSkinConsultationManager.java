package manager;

import exception.*;
import model.*;
import view.SplashScreen;

import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    public static ArrayList<Person> doctorArray = new ArrayList<>();
    public static ArrayList<Consultation> consultationArray = new ArrayList<>();

    public static void main(String[] args) {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        System.out.println("""
                                
                                Westminster Skin Consultation Manager               \s
                ====================================================================="""
        );
        manager.loadData(); //loading data from repos
        while (true) {
            manager.loadMenu();
            manager.invokeManagementAction(manager.getString("Enter manager action letter -: ",
                    "Empty manager action.").toLowerCase());
        }
    }

    /*
    calling the relevant method
     */
    public void invokeManagementAction(String option) {
        try {
            switch (option) {
                case "a" -> addDoctor();
                case "d" -> deleteDoctor();
                case "l" -> listAllDoctors();
                case "s" -> save();
                case "g" -> openGUI();
                case "q" -> System.exit(0);
                default -> throw new CustomException.InvalidManagementActionException("Invalid manager action.\n");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*
    checking if a JFrame is active
     */
    public boolean isAJFrameOpen() {
        for (Frame frame : Frame.getFrames()) { //iterating over all active frames
            if (frame.isVisible()) { //checking if the JFrame is still open
                return true;
            }
        }
        return false;
    }

    /*
    pause execution of the console application until all the JFrames are closed because of to avoid conflicts
     */
    private boolean allJFramesClosed() {
        while (true) {
            if (!isAJFrameOpen()) return true;
        }
    }

    public void openGUI() {
        System.out.println("\n============================ OPEN GUI ================================");
        System.out.println("GUI is running...");
        new SplashScreen(); //opening the splash screen
        System.out.print(allJFramesClosed() ? "GUI terminated.\n" : "");
        System.out.println("=====================================================================\n");
    }

    /*
    save data to doctorRepo
     */
    @Override
    public void save() {
        System.out.println("\n============================= SAVE ==================================");
        try {
            new ObjectOutputStream(new FileOutputStream("./src/main/repo/doctorRepo.txt")).writeObject(doctorArray);
            System.out.println("Successfully saved the data.");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("=====================================================================\n");
    }

    /*
    returning the row separator
     */
    public String getRowSeparator() {
        return "+--------------------------+---------------------+------------------+------------------+---------------------+-------------------------------+";
    }

    /*
    displaying all the doctors using their surname in alphabetical order
     */
    @Override
    public void listAllDoctors() {
        System.out.println("\n======= LIST ALL DOCTORS IN ALPHABETICAL ORDER BY SURNAME ===========");
        ArrayList<Person> arrayCopy = new ArrayList<>(doctorArray); //creating new arraylist copy to prevent modifying original array list
        Collections.sort(arrayCopy);
        if (arrayCopy.size() == 0) {
            System.out.print("Doctor list is empty.\n");
        } else {
            System.out.println(getRowSeparator());
            String format = "|\s%-25s|\s%-20s|\s%-17s|\s%-17s|\s%-20s|\s%-30s|\n"; //string formatter
            System.out.format(format, "NAME", "SURNAME", "DOB", "TEL_NUMBER", "MED_LICENSE_NUMBER", "SPECIALIZATION");
            System.out.println(getRowSeparator());
            for (Person person : arrayCopy) {
                System.out.format(format, person.getName(), person.getSurname(),
                        person.getDateOfBirth(), person.getTelNumber(), ((Doctor) person).getMedicalLicenceNumber(),
                        ((Doctor) person).getSpecialisation());
                System.out.println(getRowSeparator());
            }
        }
        System.out.println("=====================================================================\n");
    }

    /*
    delete a existing doctor
     */
    @Override
    public void deleteDoctor() {
        System.out.println("\n======================= DELETE A DOCTOR =============================");
        if (doctorArray.isEmpty()) {//checking if doctors are available before deleting
            System.out.println("Doctor list is empty.");
            System.out.println("=====================================================================\n");
            return;
        }
        String medicalLicenceNumber = getString("Enter medical licence number to delete a doctor -: ",
                "Medical licence number can not be empty.");
        Optional<Person> doctorObj = checkMedicalLicenceExists(medicalLicenceNumber);
        if (doctorObj.isPresent()) {
            doctorArray.remove(doctorObj.get());
            System.out.println("Successfully deleted a Doctor\n`" + doctorObj.get() +
                    "`\nNumber of doctors in the center currently -> " + doctorArray.size());
        } else System.out.println("No any doctor from the entered medical licence number.");
        System.out.println("=====================================================================\n");
    }

    /*
    adding a new doctor
     */
    @Override
    public void addDoctor() {
        System.out.println("\n======================== ADD NEW DOCTOR =============================");
        try {
            if (!isDoctorArrayFull())//checking if the doctor list is full
                throw new CustomException.InsufficientSpaceException("Skin clinic is currently full.\n=====================================================================\n");
            else {
                String name = getString("Enter name -: ", "Name can not be empty.");
                String surName = getString("Enter surname -: ", "Surname name can not be empty.");
                LocalDate dateOfBirth = getDate("Enter DOB (`YYYY-MM-DD`) -: ",
                        "Date can not be empty.", true);
                String mobileNumber = validateMobileNumber(true);//validating mobile number
                String medicalLicenceNumber = validateMedicalLicenceNumber(); //validating medical license number
                if (medicalLicenceNumber == null) {
                    System.out.println("=====================================================================\n");
                    return;
                }
                String specialisation = getString("Enter specialisation -: ",
                        "Specialisation can not be empty.");
                doctorArray.add(new Doctor(name, surName, dateOfBirth, mobileNumber,
                        medicalLicenceNumber, specialisation));
                System.out.println("Successfully added a Doctor\n`" + doctorArray.get(doctorArray.size() - 1) + "`");
                System.out.println("=====================================================================\n");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*
    validating medical licence number
     */
    public String validateMedicalLicenceNumber() {
        String medicalLicenceNumber = getString("Enter medical licence number -: ",
                "Medical licence number can not be empty.");
        do {
            try {
                if (checkMedicalLicenceExists(medicalLicenceNumber).isEmpty())
                    return medicalLicenceNumber;//checking if the medical licence number got duplicated
                else
                    throw new CustomException.DuplicatedMedicalLicenceNumberException("Duplicated medical licence " +
                            "numbers are not allowed. (Press `Q` to return to main menu.)");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                medicalLicenceNumber = getString("Enter medical licence number -: ",
                        "Medical licence number can not be empty.");
            }
        } while (!medicalLicenceNumber.equalsIgnoreCase("q"));
        return null;
    }

    /*
    checking if the medical licence number is duplicated(medical licence number is unique field for a doctor)
     */
    public Optional<Person> checkMedicalLicenceExists(String medicalLicenceNumber) {
        return doctorArray.stream()
                .filter(doctor -> ((Doctor) doctor).getMedicalLicenceNumber().equalsIgnoreCase(medicalLicenceNumber))
                .findAny();//checking if the medical licence number already exists
    }

    /*
    validating mobile number
     */
    public String validateMobileNumber(boolean isADoctor) {
        while (true) {
            try {
                String mobileNumber = getString("Enter mobile phone number (`07XXXXXXXX`) -: ",
                        "Mobile phone number can not be empty.");
                Integer.parseInt(mobileNumber);
                if (isADoctor) {
                    if (mobileNumber.length() == 10 && mobileNumber.startsWith("07")
                            && checkMobileNumberExists(mobileNumber).isEmpty()) //checking the length of the mobile number and checking whether it got duplicated
                        return mobileNumber;
                    else if (mobileNumber.length() != 10)
                        throw new CustomException.InvalidInputException("Invalid mobile number");
                    else if (!mobileNumber.startsWith("07")) System.out.println("Invalid mobile number");
                    else
                        throw new CustomException.DuplicatedMobileNumberException("Duplicated mobile numbers are not allowed");
                } else {
                    if (mobileNumber.length() == 10 && mobileNumber.startsWith("07")) //checking the length of the mobile number and two starting digits of the mobile number
                        return mobileNumber;
                    else if (mobileNumber.length() != 10)
                        throw new CustomException.InvalidInputException("Invalid mobile number");
                    else System.out.println("Invalid mobile number");
                }
            } catch (CustomException.DuplicatedMobileNumberException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Invalid mobile number");
            }
        }
    }

    /*
    checking if the same mobile number is duplicated(mobile number should be unique for a particular doctor)
     */
    public Optional<Person> checkMobileNumberExists(String mobileNumber) {
        return doctorArray.stream()
                .filter(doctor -> doctor.getTelNumber().equalsIgnoreCase(mobileNumber))
                .findAny();//checking if the mobile number already exists
    }

    /*
    checking if the doctor list is full or not
     */
    public boolean isDoctorArrayFull() {
        return doctorArray.size() < 10;
    }

    /*
    loading data from doctorRepo
     */
    @SuppressWarnings("unchecked")
    public void loadData() {
        if (new File("./src/main/repo/doctorRepo.txt").exists()) {
            try {
                doctorArray = (ArrayList<Person>) new ObjectInputStream(
                        new FileInputStream("./src/main/repo/doctorRepo.txt")).readObject();
                System.out.println(doctorArray.size() > 0 ? "Doctor data loaded." : "No previous doctor data.");
            } catch (Exception ex) {
                System.out.println("No previous doctor data.");
            }
        } else System.out.println("No previous doctor data.");
        if (new File("./src/main/repo/consultationRepo.txt").exists()) {
            try {
                consultationArray = (ArrayList<Consultation>) new ObjectInputStream(
                        new FileInputStream("./src/main/repo/consultationRepo.txt")).readObject();
                System.out.println(consultationArray.size() > 0 ? "Consultation data loaded." : "No previous consultation data.");
            } catch (Exception ex) {
                System.out.println("No previous consultation data.");
            }
        } else System.out.println("No previous consultation data.");
        System.out.println("=====================================================================\n");
    }

    /*
    displaying menu
     */
    public void loadMenu() {
        System.out.print("""
                ============================= MENU ==================================
                "a/A" -> Add new doctor
                "d/D" -> Delete a doctor
                "l/L" -> List all doctors in alphabetical order by surname
                "s/S" -> Save program data
                "g/G" -> Open GUI Application
                "q/Q" -> Exit
                =====================================================================
                """
        );
    }

    /*
    getting a date from the user and validating
     */
    public LocalDate getDate(String description, String errorDescription, boolean flag) {
        while (true) {
            try {
                System.out.print(description);
                String date = new Scanner(System.in).nextLine().trim();
                if (!date.isEmpty()) {
                    if (date.matches("\\d{4}-\\d{2}-\\d{2}")) { //checking the format of the date inserted
                        String[] dateFields = date.split("-");
                        //converting date to a LocalDate
                        LocalDate localDate = LocalDate.of(Integer.parseInt(dateFields[0]),
                                Integer.parseInt(dateFields[1]), Integer.parseInt(dateFields[2]));
                        if (flag) { //getting a DOB
                            if (localDate.isBefore(LocalDate.now()))
                                return localDate; //checking for possible date(birthday should be a past date and can not be a future date)
                            else
                                throw new CustomException.InvalidDateException("Birthday can not be today or a future date.");
                        } else { //getting a consultation date
                            if (localDate.isAfter(LocalDate.now()) || localDate.isEqual(LocalDate.now())) {
                                return localDate;
                            } else
                                throw new CustomException.InvalidDateException("Consultation date can not be a past date.");
                        }
                    } else throw new CustomException.InvalidDateException("Invalid date pattern.");
                } else throw new CustomException.InvalidInputException(errorDescription);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /*
    getting a user input of type 'String' and validating
     */
    public String getString(String description, String errorDescription) {
        System.out.print(description);
        String text = new Scanner(System.in).nextLine().trim();
        while (true) {
            try {
                if (!text.trim().isEmpty()) { //checking if the user has filled the required details or not (whether the user skip it)
                    return text;
                } else throw new CustomException.InvalidInputException(errorDescription);

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.print(description);
            text = new Scanner(System.in).nextLine().trim();
        }
    }
}
