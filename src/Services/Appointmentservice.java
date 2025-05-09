package Services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import models.Appointment;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;


public class Appointmentservice {
    private Map<String, List<Appointment>> appointments;  // Store appointments (Date -> Appointments)
    private SimpleDateFormat dateFormat;
    private static final String APPOINTMENT_FILE = "appointments.json";  // File to store appointments
    private ObjectMapper objectMapper; // Jackson ObjectMapper


    public Appointmentservice() {
        appointments = new HashMap<>();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper = new ObjectMapper(); // Initialize ObjectMapper
        
        loadAppointments();  // Load existing appointments from JSON file on initialization
    }

   

   

    // Method to schedule an appointment
    public boolean scheduleAppointment(Appointment appointment) {
        String formattedDate = dateFormat.format(appointment.getAppointmentDate());

        // If there are no appointments for this date, add a new entry
        if (!appointments.containsKey(formattedDate)) {
            appointments.put(formattedDate, new ArrayList<>());
        }

        // Get the list of appointments for this date
        List<Appointment> dailyAppointments = appointments.get(formattedDate);

        // Check if the selected time slot is already booked
        for (Appointment existingAppointment : dailyAppointments) {
            if (existingAppointment.getTimeSlot().equals(appointment.getTimeSlot())) {
                return false;  // Time slot already booked
            }
        }
        System.out.println("Scheduled Appointment: "+ appointment);

        // Add the appointment to the list and set its status
        dailyAppointments.add(appointment);
       
        saveAppointments();  // Save appointments to JSON file after scheduling
        return true;  // Appointment successfully scheduled
    }

    // Method to reschedule an appointment
    public boolean rescheduleAppointment(String patientName, Date newAppointmentDate, String newTimeSlot, String problemDescription, String satus) {
        // Create a new appointment object
        Appointment newAppointment = new Appointment(patientName, newAppointmentDate, newTimeSlot, problemDescription, satus);
        newAppointment.setStatus("Rescheduled"); // Set the status to "Rescheduled"

        // Check if the new time slot is available
        if (!isTimeSlotAvailable(newAppointmentDate, newTimeSlot)) {
            return false; // The time slot is not available for the new appointment
        }
        System.out.println("Rescheduled Appointment: "+ newAppointment);

        // Schedule the new appointment
        return scheduleAppointment(newAppointment); // Schedule the new appointment
    }

    // Method to cancel an appointment
    public boolean cancelAppointment(Appointment appointment) {
        String formattedDate = dateFormat.format(appointment.getAppointmentDate());

        // Check if there are appointments for this date
        if (appointments.containsKey(formattedDate)) {
            List<Appointment> dailyAppointments = appointments.get(formattedDate);

            // Find and remove the matching appointment
            for (Iterator<Appointment> iterator = dailyAppointments.iterator(); iterator.hasNext();) {
                Appointment existingAppointment = iterator.next();
                if (existingAppointment.getTimeSlot().equals(appointment.getTimeSlot())) {
                    iterator.remove(); // Remove using iterator to avoid ConcurrentModificationException
                    existingAppointment.setStatus("Canceled"); // Update the status to "Canceled"

                    

                    // If no appointments left for the day, remove the day entry
                    if (dailyAppointments.isEmpty()) {
                        appointments.remove(formattedDate);
                    }
                    System.out.println("Appointment canceled");

                    saveAppointments();  
                    return true; 
                }
            }
        }
        return false;  
    }

    // Method to get all appointments
    public List<Appointment> getAllAppointments() {
        List<Appointment> allAppointments = new ArrayList<>();
        for (List<Appointment> dailyAppointments : appointments.values()) {
            allAppointments.addAll(dailyAppointments);
        }
        return allAppointments;
    }

    // Method to get appointments for a specific date
    public List<Appointment> getAppointmentsByDate(Date date) {
        String formattedDate = dateFormat.format(date);
        return appointments.getOrDefault(formattedDate, new ArrayList<>());
    }

    // Method to get available time slots for a specific date
    public String[] getAvailableTimeSlots(Date date) {
        String formattedDate = dateFormat.format(date);
        List<Appointment> dailyAppointments = appointments.getOrDefault(formattedDate, new ArrayList<>());

        Set<String> bookedSlots = new HashSet<>();
        for (Appointment appt : dailyAppointments) {
            bookedSlots.add(appt.getTimeSlot());
        }

        // Assuming time slots are in 30-minute increments from 9 AM to 5 PM
        List<String> availableSlots = new ArrayList<>();
        for (int hour = 9; hour < 17; hour++) {
            for (int minute = 0; minute < 60; minute += 30) {
                String slot = String.format("%02d:%02d", hour, minute);
                if (!bookedSlots.contains(slot)) {
                    availableSlots.add(slot);
                }
            }
        }
        return availableSlots.toArray(new String[0]);
    }

    // Method to check if a time slot is available for a specific date
    public boolean isTimeSlotAvailable(Date date, String timeSlot) {
        String formattedDate = dateFormat.format(date);
        List<Appointment> dailyAppointments = appointments.getOrDefault(formattedDate, new ArrayList<>());
        for (Appointment appointment : dailyAppointments) {
            if (appointment.getTimeSlot().equals(timeSlot)) {
                return false; 
            }
        }
        return true;  
    }

    // Method to check if the appointment is within the cancellation/rescheduling time frame
    public boolean isWithinTimeFrame(Date appointmentDate) {
        long currentTime = System.currentTimeMillis();
        long appointmentTime = appointmentDate.getTime();
        long timeDiff = appointmentTime - currentTime; 

       
        return timeDiff <= 24 * 60 * 60 * 1000;
    }

    // Save appointments to a JSON file
    private void saveAppointments() {
        try (Writer writer = new FileWriter(APPOINTMENT_FILE)) {
            System.out.println(appointments);
            objectMapper.writeValue(writer, appointments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load appointments from the JSON file
    public Map<String, List<Appointment>> loadAppointments() {
        File file = new File(APPOINTMENT_FILE);
        System.out.println(file);
        if (!file.exists()) {
            return null; 
        }

        try (Reader reader = new FileReader(file)) {
            appointments = objectMapper.readValue(reader, new TypeReference<Map<String, List<Appointment>>>(){});
            return appointments;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}