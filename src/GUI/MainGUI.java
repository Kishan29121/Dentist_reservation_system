package GUI;

import javax.swing.*;
import models.Patientmodel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

public class MainGUI extends JFrame {
	

private Patientmodel patientmodel;
private String currentProfile = "Patient";

public MainGUI() {
	patientmodel = new Patientmodel();
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
    JLabel userLabel = new JLabel("Email: ");
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
    
    JButton registrationButton = new JButton("Register");
    registrationButton.setBounds(110, 10, 80, 25);
    buttonPanel.add(registrationButton);
    
    JButton changePasswordButton = new JButton("Change Password");
    changePasswordButton.setBounds(210, 10, 150, 25);
    buttonPanel.add(changePasswordButton);

    // Set bounds for the panels
    fieldsPanel.setBounds(10, 10, 300, 100);
    buttonPanel.setBounds(10, 120, 350, 40);

    // Add action listeners to the profile selection buttons
   

    doctorButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            userLabel.setText("Email: ");
            passwordLabel.setText("Password: ");
            currentProfile = "Doctor";
            registrationButton.setVisible(false);
        }
    });
    
    patientButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            userLabel.setText("Email: ");
            passwordLabel.setText("Password: ");
            currentProfile = "Patient";
            registrationButton.setVisible(true);
        }
    });

    receptionistButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            userLabel.setText("Email: ");
            passwordLabel.setText("Password: ");
            currentProfile = "Receptionist";
            registrationButton.setVisible(false);
        }
    });
    
    loginButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String username = userText.getText();
            String password = new String(passwordText.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both ID and Password.");
            } else {
            	switch (currentProfile) {
                case "Patient":
                    if (patientmodel.loginPatient(username, password)) {
                    	JPanel patientGUI = new PatientGUI();
                    	getContentPane().removeAll();
                        getContentPane().add(patientGUI);
                        revalidate();
                        repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Invalid username or password for patient.");
                    }
                    break;
            	}
            }
        }
    });

 
   
    
    registrationButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JFrame registrationFrame = new JFrame("Registration Form");
			 registrationFrame.setSize(450, 300);
			 registrationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			    
			    JPanel registerpanel = new JPanel();
			    registerpanel.setLayout(new BorderLayout());
			    registrationFrame.add(registerpanel, BorderLayout.CENTER);
			    registrationFrame.setVisible(true);
			    
			    
			    JPanel registerFieldpanel = new JPanel();
			    registerFieldpanel.setLayout(null);
			    registerpanel.add(registerFieldpanel);
			    
			    JLabel nameLabel = new JLabel("Name: ");
			    nameLabel.setBounds(50, 20, 120, 25);
			    registerFieldpanel.add(nameLabel);
			    
			    JTextField nameText = new JTextField(25);
			    nameText.setBounds(150, 20, 230, 25);
			    registerFieldpanel.add(nameText);
			    
			    JLabel BirthDate = new JLabel("<html>Birth Date<br>(dd,mm,yyyy): </html>");
			    BirthDate.setBounds(50, 50, 120, 30);
			    registerFieldpanel.add(BirthDate);
			    
			    JTextField DOB = new JTextField(25);
			    DOB.setBounds(150, 50, 230, 25);
			    registerFieldpanel.add(DOB);
			    
			    JLabel PhonenumberLabel = new JLabel("Phone Number: ");
			    PhonenumberLabel.setBounds(50, 90, 120, 25);
			    registerFieldpanel.add(PhonenumberLabel);
			    
			    JTextField Phonenumber = new JTextField(25);
			    Phonenumber.setBounds(150, 90, 230, 25);
			    registerFieldpanel.add(Phonenumber);
			    
			    JLabel EMailLabel = new JLabel("Email:");
			    EMailLabel.setBounds(50, 120, 120, 25);
			    registerFieldpanel.add(EMailLabel);
			    
			    JTextField EmailID = new JTextField(25);
			    EmailID.setBounds(150, 120, 230, 25);
			    registerFieldpanel.add(EmailID);
			    
			    JLabel PasswordLabel = new JLabel("Password: ");
			    PasswordLabel.setBounds(50, 150, 120, 25);
			    registerFieldpanel.add(PasswordLabel);
			    
			    JPasswordField PasswordText = new JPasswordField(25);
			    PasswordText.setBounds(150, 150, 230, 25);
			    registerFieldpanel.add(PasswordText);
			    
			    JButton RegisterButton = new JButton("Register");
			    RegisterButton.setBounds(180, 200, 150, 25);
			    registerFieldpanel.add(RegisterButton);
			    
			    RegisterButton.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		String name = nameText.getText();
			    		String BirthDate = DOB.getText();
			    		String PhoneNumber = Phonenumber.getText();
			    		int Phonenumber = Integer.parseInt(PhoneNumber);
			    		String Email = EmailID.getText();
			    		char[] passwordChars = PasswordText.getPassword();
		                String Password = new String(passwordChars);
			    		
			    		if (Email.isEmpty() || Password.isEmpty()) {
	                        JOptionPane.showMessageDialog(registrationFrame, "Please fill all the fields.");
	                    } else {
	                        boolean registrationSuccessful = false;
	    
	                        // Check current profile to register in the appropriate model
	                        if (currentProfile.equals("Patient")) {
	                            registrationSuccessful = patientmodel.registerPatient(name, BirthDate, Phonenumber,  Email, Password);
	                            
	                            
	                        } else if (currentProfile.equals("doctor")) {
	                            
	                        } else if (currentProfile.equals("receptionist")) {
	                           
	                        }
	                        
	                        if (registrationSuccessful) {
	                            JOptionPane.showMessageDialog(registrationFrame, "Registration successful!");
	                            registrationFrame.dispose(); // Close the registration form
	                        } else {
	
	                            JOptionPane.showMessageDialog(registrationFrame, "Email already registered.");
	                        }
	    
	                       
	                    }
			    	}
			    	
			    }); 
			    
			   
		}
    	
    });

   
   

    // Add action listener to the change password button
    changePasswordButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String oldPassword = new String(passwordText.getPassword());
            String newPassword = JOptionPane.showInputDialog(panel, "Enter new password");

            if (oldPassword.equals(newPassword) && newPassword != null) {
                oldPassword = newPassword;
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
            } else  { 
            	System.exit(DO_NOTHING_ON_CLOSE);
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