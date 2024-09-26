package Services;

import models.Appointment;

public interface Appointmentlistener {
    void appointmentAdded(Appointment appointment);  // Method to notify when an appointment is added

	void appointmentCanceled(Appointment appointment);

	
}