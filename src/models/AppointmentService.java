package models;

import models.Appointment;
import models.Patient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
	private List<Appointment> appointments;
	
	public AppointmentService() {
		this.appointments = new ArrayList<>();
		
	}
	
	public void bookAppointment(Patient patient, LocalDateTime dateTime  ) {
		for (Appointment appointment : appointments) {
			if (appointment.getAppointmentDateTime().equals(dateTime)) {
				System.out.println("This slot is already booked");
				return;
			}
		}
		
		appointments.add(new Appointment(patient, dateTime));
		System.out.println("Appointment booked successfully");
	}
	
	public void cancelAppointment(Patient patient, LocalDateTime dateTime ) {
		appointments.removeIf(appointment -> appointment.getPatient().equals(patient) && appointment.getAppointmentDateTime().equals(dateTime));
		System.out.println("Appointment cancelled");
	}
	
	public void printAppointments() {
		for (Appointment appointment : appointments) {
			appointment.printAppointmentDetails();
		}
	}

}
