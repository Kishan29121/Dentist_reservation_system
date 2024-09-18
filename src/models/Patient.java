package models;

public class Patient {
    private String name;
    private String dob;
    private int phoneNumber;
    private String email;
    private String password;

    // Constructor
    public Patient(String name, String dob, int phoneNumber, String email, String password) {
        this.name = name;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

