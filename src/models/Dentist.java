package models;

public class Dentist extends User {
    public Dentist(String name, String contactDetails, String username, String password) {
        super(name, contactDetails, username, password);
    }

    @Override
    public void printUserInfo() {
        // Implement your method here
        System.out.println("Dentist Name: " + getName());
        System.out.println("Contact Details: " + getContactDetails());
    }

}
