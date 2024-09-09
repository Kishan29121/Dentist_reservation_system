package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class MainGUI extends JFrame {

private static String STAFF_PASSWORD = "staff123";
private Map<String, String> patientAccounts = new HashMap<>();
private String currentProfile = "";

public MainGUI() {
    JFrame frame = this;
    frame.setSize(450, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    frame.add(panel);

    // Create a panel for the login form
    JPanel loginPanel = new JPanel();
    loginPanel.setLayout(null);
    panel.add(loginPanel, BorderLayout.CENTER);

    // Create a panel for the profile selection
    JPanel profilePanel = new JPanel();
    profilePanel.setLayout(new FlowLayout());
    panel.add(profilePanel, BorderLayout.NORTH);

    // Create profile selection buttons
 
    JButton patientButton = new JButton("Patient");

  
    profilePanel.add(patientButton);

    // Create a panel for the staff profiles
    JPanel staffProfilePanel = new JPanel();
    staffProfilePanel.setLayout(new FlowLayout());
    profilePanel.add(staffProfilePanel);

    // Create staff profile selection buttons
    JButton doctorButton = new JButton("Doctor");
    JButton receptionistButton = new JButton("Receptionist");

    profilePanel.add(doctorButton);
    profilePanel.add(receptionistButton);

    // Create a panel for the login form fields
    JPanel fieldsPanel = new JPanel();
    fieldsPanel.setLayout(null);
    loginPanel.add(fieldsPanel);

    // Create login form fields
    JLabel userLabel = new JLabel("Patient ID: ");
    userLabel.setBounds(10, 20, 120, 25);
    fieldsPanel.add(userLabel);

    JTextField userText = new JTextField(25);
    userText.setBounds(120, 20, 200, 25);
    fieldsPanel.add(userText);

    JLabel passwordLabel = new JLabel("Password: ");
    passwordLabel.setBounds(10, 50, 120, 25);
    fieldsPanel.add(passwordLabel);

    JPasswordField passwordText = new JPasswordField(25);
    passwordText.setBounds(120, 50, 200, 25);
    fieldsPanel.add(passwordText);

    // Create a panel for the login button
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(null);
    loginPanel.add(buttonPanel);

    JButton loginButton = new JButton("Login");
    loginButton.setBounds(10, 10, 80, 25);
    buttonPanel.add(loginButton);

    
    JButton changePasswordButton = new JButton("Change Password");
    changePasswordButton.setBounds(210, 10, 150, 25);
    buttonPanel.add(changePasswordButton);

    // Set bounds for the panels
    fieldsPanel.setBounds(10, 10, 300, 100);
    buttonPanel.setBounds(10, 120, 350, 40);

    // Add action listeners to the profile selection buttons
   

    doctorButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            userLabel.setText("Doctor ID: ");
            passwordLabel.setText("Password: ");
            currentProfile = "doctor";
        }
    });
    
    patientButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            userLabel.setText("Patient ID: ");
            passwordLabel.setText("Password: ");
            currentProfile = "patient";
        }
    });

    receptionistButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            userLabel.setText("Receptionist ID: ");
            passwordLabel.setText("Password: ");
            currentProfile = "receptionist";
        }
    });

    // Add action listener to the login button
    loginButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter both username and password");
            } else {
                if (currentProfile.equals("staff")) {
                    if (password.equals(STAFF_PASSWORD)) {
                        JOptionPane.showMessageDialog(panel, "Login Successful as Staff");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid password for Staff");
                    }
                }else if (currentProfile.equals("patient")) {
                    String storedPassword = patientAccounts.get(username);
                    if (storedPassword != null && storedPassword.equals(password)) {
                        JOptionPane.showMessageDialog(panel, "Login Successful as Patient");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid username or password for Patient");
                    }
                
                	
                } else if (currentProfile.equals("doctor")) {
                    if (password.equals("doctor123")) {
                        JOptionPane.showMessageDialog(panel, "Login Successful as Doctor");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid password for Doctor");
                    }
                } else if (currentProfile.equals("receptionist")) {
                    if (password.equals("receptionist123")) {
                        JOptionPane.showMessageDialog(panel, "Login Successful as Receptionist");
                    } else {
                        JOptionPane.showMessageDialog(panel, "Invalid password for Receptionist");
                    }
                }
            }
        }
    });

    // Add action listener to the add patient button
   

    // Add action listener to the change password button
    changePasswordButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String oldPassword = new String(passwordText.getPassword());
            String newPassword = JOptionPane.showInputDialog(panel, "Enter new password");

            if (oldPassword.equals(STAFF_PASSWORD) && newPassword != null) {
                STAFF_PASSWORD = newPassword;
                JOptionPane.showMessageDialog(panel, "Password changed successfully");
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid old password or new password is empty");
            }
        }
    });

    // Add window listener to the frame
    frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
            int result = JOptionPane.showConfirmDialog(panel, "Are you sure you want to exit?");
            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    });

    // Set the staff profile panel to invisible initially
    staffProfilePanel.setVisible(false);

    // Make the frame visible
    frame.setVisible(true);
}

public static void main(String[] args) {
    new MainGUI();
}
}