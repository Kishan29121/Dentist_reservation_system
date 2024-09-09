package models;

public class Patient extends User {
	private String medicalHistory;
	
	public Patient(String name, String contactDetails, String username, String password, String medicalHistory) {
		super(name, contactDetails, username, password);
		this.medicalHistory = medicalHistory;
	}
	
	public String getMedicalHistory() {return medicalHistory;}
	public void setMedicalHistory(String medicalHistory) {this.medicalHistory=medicalHistory;}
	
	public void printUserInfo() {
		System.out.println("Patient Name: " + getName());
		System.out.println("Contact Details: " + getContactDetails());
		System.out.println("Medical History: " + getMedicalHistory());
	}

}
