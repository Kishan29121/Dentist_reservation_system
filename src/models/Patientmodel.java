package models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Patientmodel {
    private List<Patient> patientList;

    public Patientmodel() {
        patientList = new ArrayList<>();
        loadPatients(); // Load patients from file when the model is created
    }

    public boolean loginPatient(String email, String password) {
        for (Patient patient : patientList) {
            if (patient.getEmail().equals(email) && patient.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean registerPatient(String name, String birthDate, int phoneNumber, String email, String password) {
        for (Patient patient : patientList) {
            if (patient.getEmail().equals(email)) {
                return false;  // Email already exists
            }
        }
        patientList.add(new Patient(name, birthDate, phoneNumber, email, password));
        savePatients(); // Save patients to file after registration
        return true;  // Registration successful
    }

    private void savePatients() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("patients.ser"))) {
            oos.writeObject(patientList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPatients() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("patients.ser"))) {
            patientList = (List<Patient>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, initializing with empty list
            patientList = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
