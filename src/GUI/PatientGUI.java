package GUI;

import javax.swing.*;

import com.toedter.calendar.JCalendar;// Import JCalendar
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatientGUI extends JPanel {

    private JComboBox<String> timeSlotsDropdown;
    private JButton scheduleButton, rescheduleButton, cancelButton;
    private JSpinner dateSpinner;
    private SimpleDateFormat dateFormat;
    private JCalendar calendar;
    private Map<String, String> appointments;  // To store appointments

    public PatientGUI() {
        // Initialize the JFrame
        

        // Initialize the JPanel for layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));

        // Initialize calendar (JCalendar)
        calendar = new JCalendar();
        panel.add(calendar);  // Add the calendar to the panel

        // Add label and Date Picker (JSpinner)
        JLabel dateLabel = new JLabel("Select Appointment Date:");
        panel.add(dateLabel);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, dateFormat.toPattern());
        dateSpinner.setEditor(dateEditor);
        panel.add(dateSpinner);

        // Add label and Dropdown for available time slots
        JLabel slotLabel = new JLabel("Select Time Slot:");
        panel.add(slotLabel);

        String[] availableSlots = {"9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM"};
        timeSlotsDropdown = new JComboBox<>(availableSlots);
        panel.add(timeSlotsDropdown);

        // Add buttons for scheduling, rescheduling, and canceling appointments
        scheduleButton = new JButton("Schedule Appointment");
        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleAppointment();
            }
        });
        panel.add(scheduleButton);

        rescheduleButton = new JButton("Reschedule Appointment");
        rescheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rescheduleAppointment();
            }
        });
        panel.add(rescheduleButton);

        cancelButton = new JButton("Cancel Appointment");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelAppointment();
            }
        });
        panel.add(cancelButton);

        // Store appointments in a HashMap
        appointments = new HashMap<>();

        // Add the panel to the frame
        add(panel);
        setVisible(true);
    }

    // Method to handle scheduling an appointment
    private void scheduleAppointment() {
        Date selectedDate = (Date) dateSpinner.getValue();
        String selectedTime = (String) timeSlotsDropdown.getSelectedItem();
        String dateTime = dateFormat.format(selectedDate) + " " + selectedTime;
        
        if (appointments.containsKey(dateTime)) {
            JOptionPane.showMessageDialog(this, "This slot is already booked!");
        } else {
            appointments.put(dateTime, "Booked");
            JOptionPane.showMessageDialog(this, "Appointment scheduled on " + dateFormat.format(selectedDate) + " at " + selectedTime);
            updateCalendar(selectedDate, selectedTime, true);
        }
    }

    // Method to handle rescheduling an appointment
    private void rescheduleAppointment() {
        Date selectedDate = (Date) dateSpinner.getValue();
        String selectedTime = (String) timeSlotsDropdown.getSelectedItem();
        String dateTime = dateFormat.format(selectedDate) + " " + selectedTime;

        if (appointments.containsKey(dateTime)) {
            JOptionPane.showMessageDialog(this, "This slot is already booked!");
        } else {
            appointments.put(dateTime, "Rescheduled");
            JOptionPane.showMessageDialog(this, "Appointment rescheduled to " + dateFormat.format(selectedDate) + " at " + selectedTime);
            updateCalendar(selectedDate, selectedTime, true);
        }
    }

    // Method to handle canceling an appointment
    private void cancelAppointment() {
        Date selectedDate = (Date) dateSpinner.getValue();
        String selectedTime = (String) timeSlotsDropdown.getSelectedItem();
        String dateTime = dateFormat.format(selectedDate) + " " + selectedTime;

        if (appointments.containsKey(dateTime)) {
            appointments.remove(dateTime);
            JOptionPane.showMessageDialog(this, "Appointment on " + dateFormat.format(selectedDate) + " at " + selectedTime + " has been cancelled.");
            updateCalendar(selectedDate, selectedTime, false);
        } else {
            JOptionPane.showMessageDialog(this, "No appointment found on this slot to cancel.");
        }
    }

    // Method to update the calendar when appointments are scheduled, rescheduled, or canceled
    private void updateCalendar(Date date, String time, boolean isBooking) {
        // This is where you would update the calendar UI.
        // For now, we just show a message, but you can color-code days or mark booked slots.
        String message = isBooking ? "Booked on " : "Canceled on ";
        JOptionPane.showMessageDialog(this, message + dateFormat.format(date) + " at " + time);
    }

    // Main method to run the application
    public static void main(String[] args) {
        new PatientGUI();
    }
}
