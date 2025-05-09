package models;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;

public class Appointment {
	@JsonProperty("patientName")
    private String patientName;

    @JsonProperty("appointmentDate")
    private Date appointmentDate;

    @JsonProperty("timeSlot")
    private String timeSlot;

    @JsonProperty("problemDescription")
    private String problemDescription;
    
    @JsonProperty("status")
    private String status;

    
    @JsonCreator
    public Appointment(@JsonProperty("patientName") String patientName,
            @JsonProperty("appointmentDate") Date appointmentDate,
            @JsonProperty("timeSlot") String timeSlot,
            @JsonProperty("problemDescription") String problemDescription,
            @JsonProperty("status") String status) {
    	
        this.patientName = patientName;
        this.appointmentDate = appointmentDate;
        this.timeSlot = timeSlot;
        this.problemDescription = problemDescription;
        this.status = status;
        
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
