package models;

import java.io.Serializable;

public class Patient implements Serializable {
    private static final long serialVersionUID = 1L; // Add a serial version UID
    private String name;
    private String birthDate;
    private int phoneNumber;
    private String email;
    private String password;

    public Patient(String name, String birthDate, int phoneNumber, String email, String password) {
        this.name = name;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
    public int getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
