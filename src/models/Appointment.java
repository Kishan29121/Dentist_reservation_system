package models;

import java.util.Date;

public class Appointment {
    private String patientName;
    private Date appointmentDate;
    private String timeSlot;
    private String problemDescription;
    private String status;

    public Appointment(String patientName, Date appointmentDate, String timeSlot, String problemDescription) {
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.problemDescription = problemDescription;
        this.status = "Schedule";
    }

    // Getters and Setters
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String setStatus(String status ) {
        return status;
    }

    public String getStatus() {
        return status;
    }
    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }



    @Override
    public String toString() {
        return "Appointment{" +
                "patientName='" + patientName + '\'' +
                ", appointmentDate=" + appointmentDate +
                ", timeSlot='" + timeSlot + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                '}';
    }
}
