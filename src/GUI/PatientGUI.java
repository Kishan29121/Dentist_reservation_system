package GUI;

import models.Appointment;
import Services.Appointmentservice;

import javax.swing.*;
import com.toedter.calendar.JCalendar;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PatientGUI extends JPanel {

    private JComboBox<String> timeSlotsDropdown;
    private JButton scheduleButton, rescheduleButton, cancelButton;
    private JSpinner dateSpinner;
    private SimpleDateFormat dateFormat;
    private JCalendar calendar;
    private JTextArea problemDescriptionArea;
    private JTextField nameField;  // New field for patient's name

    private Appointmentservice appointmentService;  // The service to handle appointment logic

    public PatientGUI() {
        appointmentService = new Appointmentservice();  // Initialize the service

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding between components

        // Initialize calendar (JCalendar)
        calendar = new JCalendar();
        calendar.setPreferredSize(new Dimension(700, 400));  // Set calendar size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(calendar, gbc);

        // Add label and Date Picker (JSpinner)
        JLabel dateLabel = new JLabel("Select Appointment Date:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(dateLabel, gbc);

        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, dateFormat.toPattern());
        dateSpinner.setEditor(dateEditor);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(dateSpinner, gbc);

        // Add label and Dropdown for available time slots
        JLabel slotLabel = new JLabel("Select Time Slot:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(slotLabel, gbc);

        String[] availableSlots = {"9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM"};
        timeSlotsDropdown = new JComboBox<>(availableSlots);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(timeSlotsDropdown, gbc);

        // Add label and input field for patient's name
        JLabel nameLabel = new JLabel("Enter Patient Name:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(nameLabel, gbc);

        nameField = new JTextField(20);  // Text field for patient's name
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(nameField, gbc);

        // Add label and TextArea for patient problem description
        JLabel problemLabel = new JLabel("Enter Problem Description:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(problemLabel, gbc);

        problemDescriptionArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(problemDescriptionArea);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(scrollPane, gbc);

        // Add buttons for scheduling, rescheduling, and canceling appointments
        scheduleButton = new JButton("Schedule Appointment");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scheduleButton, gbc);

        rescheduleButton = new JButton("Reschedule Appointment");
        gbc.gridy = 6;
        add(rescheduleButton, gbc);

        cancelButton = new JButton("Cancel Appointment");
        gbc.gridy = 7;
        add(cancelButton, gbc);

        // Add action listeners to buttons
        scheduleButton.addActionListener(e -> scheduleAppointment());
        rescheduleButton.addActionListener(e -> rescheduleAppointment());
        cancelButton.addActionListener(e -> cancelAppointment());

        // Initialize the calendar UI with default behavior
        updateCalendarUI();
    }

    // Method to schedule an appointment
    private void scheduleAppointment() {
        String patientName = nameField.getText();  // Get the entered patient name
        if (patientName == null || patientName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the patient's name.");
            return;
        }

        Date selectedDate = (Date) dateSpinner.getValue();
        String selectedTime = (String) timeSlotsDropdown.getSelectedItem();
        String problemDescription = problemDescriptionArea.getText();  // Capture problem description

        // Create a new Appointment object
        Appointment appointment = new Appointment(patientName, selectedDate, selectedTime, problemDescription);

        // Use the service to schedule the appointment
        boolean success = appointmentService.scheduleAppointment(appointment);

        if (success) {
            JOptionPane.showMessageDialog(this, "Appointment scheduled for " + patientName + " on " + dateFormat.format(selectedDate) + " at " + selectedTime);
            updateCalendarUI();  // Optionally update the calendar UI
        } else {
            JOptionPane.showMessageDialog(this, "This slot is already booked!");
        }
    }

    // Method to reschedule an appointment
    private void rescheduleAppointment() {
        String patientName = nameField.getText();
        if (patientName == null || patientName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the patient's name.");
            return;
        }
    
        Date selectedDate = (Date) dateSpinner.getValue();
        String selectedTime = (String) timeSlotsDropdown.getSelectedItem();
        String problemDescription = problemDescriptionArea.getText();
    
        // Call the rescheduleAppointment method in the AppointmentService
        boolean success = appointmentService.rescheduleAppointment(patientName, selectedDate, selectedTime, problemDescription);
    
        if (success) {
            JOptionPane.showMessageDialog(this, "Appointment rescheduled successfully for " + patientName);
            updateCalendarUI();  // Optionally update the calendar UI
        } else {
            JOptionPane.showMessageDialog(this, "Failed to reschedule the appointment. The selected time slot may already be booked.");
        }
    }
    
    // Method to cancel an appointment
    private void cancelAppointment() {
        String patientName = nameField.getText();
        if (patientName == null || patientName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter the patient's name.");
            return;
        }

        Date selectedDate = (Date) dateSpinner.getValue();
        String selectedTime = (String) timeSlotsDropdown.getSelectedItem();

        // Create an appointment object to cancel (in practice, you should retrieve the correct object)
        Appointment appointment = new Appointment(patientName, selectedDate, selectedTime, "");

        // Use the service to cancel the appointment
        boolean success = appointmentService.cancelAppointment(appointment);

        if (success) {
            JOptionPane.showMessageDialog(this, "Appointment canceled successfully for " + patientName);
            updateCalendarUI();  // Optionally update the calendar UI
        } else {
            JOptionPane.showMessageDialog(this, "No appointment found for the selected time.");
        }
    }

    // Update the calendar UI (This method will be implemented similarly to the original logic)
    private void updateCalendarUI() {
        // Placeholder for updating the calendar based on scheduled appointments
    }

    // Main method to run the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Patient Appointment Scheduler");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);  // Full screen
            frame.add(new PatientGUI());
            frame.setVisible(true);
        });
    }
}
