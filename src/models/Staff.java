package models;

public class Staff extends User {
    private String role;

    public Staff(String name, String contactDetails, String username, String password, String role) {
        super(name, contactDetails, username, password);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public void printUserInfo() {
        System.out.println("Staff Name: " + getName());
        System.out.println("Role: " + getRole());
        System.out.println("Contact Details: " + getContactDetails());
    }

    public boolean authenticate(String username, String password) {
		System.out.println(username);
        return this.getUserName().equals(username) && this.getPassword().equals(password);
    }
}
