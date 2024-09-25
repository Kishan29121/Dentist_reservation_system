package GUI;

import models.Appointment; // Import your Appointment model
import Services.Appointmentservice; // Import the AppointmentService class

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.Date;
import java.util.List;

public class DentistGUI extends JPanel {
    private JTable appointmentTable;
    private DefaultTableModel tableModel;
    private Appointmentservice appointmentService; // Service to fetch appointments

    public DentistGUI(Appointmentservice appointmentService) {
        this.appointmentService = appointmentService; // Initialize the appointment service

        // Create table model and table
        tableModel = new DefaultTableModel(new String[]{"Date", "Time", "Patient", "Status"}, 0);
        appointmentTable = new JTable(tableModel);
        
        // Load actual appointment data into the table
        //loadAppointments();

        // Create buttons
        JButton acceptButton = new JButton("Accept");
        JButton rescheduleButton = new JButton("Reschedule");
        JButton cancelButton = new JButton("Cancel");

        // Button actions
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to accept the appointment
                int selectedRow = appointmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.setValueAt("Accepted", selectedRow, 3);
                    // You may want to update the Appointment status in the AppointmentService here
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an appointment to accept.");
                }
            }
        });

        // rescheduleButton.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Logic to reschedule the appointment
        //         int selectedRow = appointmentTable.getSelectedRow();
        //         if (selectedRow != -1) {
        //             String newDate = JOptionPane.showInputDialog("Enter new date (YYYY-MM-DD):");
        //             String newTime = JOptionPane.showInputDialog("Enter new time (HH:MM AM/PM):");
        //             if (newDate != null && newTime != null) {
        //                 String patientName = (String) tableModel.getValueAt(selectedRow, 2); // Get patient name
        //                 // Create new appointment object to pass to AppointmentService
        //                 Appointment newAppointment = new Appointment(patientName, parseDate(newDate), newTime, "");
        //                 newAppointment.setStatus("Rescheduled");

        //                 // Assuming you have a method to handle rescheduling in AppointmentService
        //                 boolean success = appointmentService.rescheduleAppointment(null, newAppointment); // Pass the old appointment if needed
        //                 if (success) {
        //                     tableModel.setValueAt(newDate, selectedRow, 0);
        //                     tableModel.setValueAt(newTime, selectedRow, 1);
        //                     tableModel.setValueAt("Rescheduled", selectedRow, 3);
        //                 } else {
        //                     JOptionPane.showMessageDialog(null, "Failed to reschedule the appointment. The selected time slot may already be booked.");
        //                 }
        //             }
        //         } else {
        //             JOptionPane.showMessageDialog(null, "Please select an appointment to reschedule.");
        //         }
        //     }
        // });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Logic to cancel the appointment
                int selectedRow = appointmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.setValueAt("Canceled", selectedRow, 3);
                    // Update the Appointment status in the AppointmentService here as needed
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an appointment to cancel.");
                }
            }
        });

        // Layout setup
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(acceptButton);
        buttonPanel.add(rescheduleButton);
        buttonPanel.add(cancelButton);
        
        add(new JScrollPane(appointmentTable), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Method to load appointments from the AppointmentService
   /* private void loadAppointments() {
        // Assuming you have a method to get all appointments in AppointmentService
        List<Appointment> appointments = appointmentService.getAllAppointments(); // Implement this in AppointmentService

        for (Appointment appointment : appointments) {
            tableModel.addRow(new Object[]{
                appointment.getAppointmentDate(), // Format date as needed
                appointment.getTimeSlot(),
                appointment.getPatientName(),
                appointment.getStatus()
            });
        }
    }

    // Simple method to parse date from string (consider using a proper date formatter)
    // private Date parseDate(String dateString) {
    //     try {
    //         return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    //     } catch (ParseException e) {
    //         e.printStackTrace();
    //         return null;
    //     }
    // }*/

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Appointmentservice appointmentService = new Appointmentservice(); // Initialize your AppointmentService
            DentistGUI gui = new DentistGUI(appointmentService);
            JFrame frame = new JFrame("Dentist Appointments");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gui);
            frame.setSize(600, 400);
            frame.setVisible(true);
        });
    }
}
