package GUI;

import models.Appointment; // Import your Appointment model

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Services.Appointmentservice;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DentistGUI extends JPanel {
    private JTable appointmentTable;
    private DefaultTableModel tableModel;
    private SimpleDateFormat dateFormat; // Date format for displaying dates

    public DentistGUI() {
        setLayout(new BorderLayout(10, 10)); // Add spacing around the borders

        // Create date formatter for displaying Date objects
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Create table model and table with a new "Problem" column
        tableModel = new DefaultTableModel(new String[]{"Date", "Time", "Patient", "Problem", "Status"}, 0);
        appointmentTable = new JTable(tableModel);
        appointmentTable.setRowHeight(25); // Set row height for better visibility
        appointmentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        appointmentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));

        loadStaticAppointments(); // Load static appointments

        // Create buttons with proper padding and font
        JButton acceptButton = new JButton("Accept");
        acceptButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton rescheduleButton = new JButton("Reschedule");
        rescheduleButton.setFont(new Font("Arial", Font.BOLD, 14));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Button actions
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = appointmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.setValueAt("Accepted", selectedRow, 4);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an appointment to accept.");
                }
            }
        });

        rescheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = appointmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    String newDate = JOptionPane.showInputDialog("Enter new date (YYYY-MM-DD):");
                    String newTime = JOptionPane.showInputDialog("Enter new time (HH:MM AM/PM):");
                    if (newDate != null && newTime != null) {
                        tableModel.setValueAt(newDate, selectedRow, 0);
                        tableModel.setValueAt(newTime, selectedRow, 1);
                        tableModel.setValueAt("Rescheduled", selectedRow, 4);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an appointment to reschedule.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = appointmentTable.getSelectedRow();
                if (selectedRow != -1) {
                    tableModel.setValueAt("Canceled", selectedRow, 4);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select an appointment to cancel.");
                }
            }
        });

        // Layout for buttons (centered and spaced)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(acceptButton);
        buttonPanel.add(rescheduleButton);
        buttonPanel.add(cancelButton);

        // Add components to the main panel
        add(new JScrollPane(appointmentTable), BorderLayout.CENTER); // Table in the center
        add(buttonPanel, BorderLayout.SOUTH); // Buttons at the bottom
    }

    // Method to load static appointments into the table
    private void loadStaticAppointments() {
    	Appointmentservice appointmentservice = new Appointmentservice();
    	
       

    	Map<String, List<Appointment>> appointments = appointmentservice.loadAppointments();
    	System.out.println(appointments);

        // Populate the table with the static appointment data, including the problem
    	for (Map.Entry<String, List<Appointment>> entry : appointments.entrySet()) {
            List<Appointment> appointmentList = entry.getValue(); // Get the list of appointments for this date
            for (Appointment appointment : appointmentList) { // Now iterate over the appointments
                tableModel.addRow(new Object[]{
                    dateFormat.format(appointment.getAppointmentDate()), // Format the date
                    appointment.getTimeSlot(),
                    appointment.getPatientName(),
                    appointment.getProblemDescription(),
                    appointment.getStatus()
                });
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DentistGUI gui = new DentistGUI();
            JFrame frame = new JFrame("Dentist Appointments");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gui);
            frame.setSize(700, 500);
            frame.setVisible(true);
        });
    }
}