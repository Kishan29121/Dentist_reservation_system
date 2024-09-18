package models;

import java.util.HashMap;
import java.util.Map;

public class Patientmodel {
	
	private Map<String, Patient> patientAccounts;

    public Patientmodel() {
        patientAccounts = new HashMap<>();
    }

    public boolean loginPatient(String email, String password) {
    	if (patientAccounts.containsKey(email)) {
    		Patient patient = patientAccounts.get(email);
    		return patient.getPassword().equals(password);
    	}
    	else {
    		return false;
    	}
    	
        
    }

    // Method to register a new patient
    public boolean registerPatient(String name, String dob, int phoneNumber, String email, String password) {
        if (patientAccounts.containsKey(email)) {
            return false;  // Email already exit
        }
        Patient PatientDetail = new Patient(name, dob, phoneNumber, email, password);
        patientAccounts.put(email, PatientDetail);
        return true;  // Registration successful
    }

}
