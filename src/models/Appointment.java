package models;

import java.time.LocalDateTime;

public class Appointment {
	private Patient patient;
	private LocalDateTime appointmentDateTime;
	private boolean isConfirmed;
	
	public Appointment(Patient patient, LocalDateTime appointmentDateTime) {
		this.patient = patient;
		this.appointmentDateTime = appointmentDateTime;
		this.isConfirmed = false;
	}
	
	public Patient getPatient() {return patient;}
	public void setPatient(Patient patient) {this.patient = patient; }
	
	public LocalDateTime getAppointmentDateTime() {return appointmentDateTime;}
	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {this.appointmentDateTime = appointmentDateTime;}
	
	public boolean isConfirmed() { return isConfirmed;}
	public void setConfirmed(boolean confirmed) {isConfirmed = confirmed;}
	
	public void printAppointmentDetails() {
		System.out.println("Appointment person: " + patient.getName());
		System.out.println("Appointment Date and Time: " + appointmentDateTime);
		System.out.println("Confirmed: " + isConfirmed);
	}

}
