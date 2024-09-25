package models;

import java.util.ArrayList;
import java.util.List;

public class Staffmodel {

    private List<Staff> staffList;

    public Staffmodel() {
        staffList = new ArrayList<>();
        // Initialize with some sample staff
        staffList.add(new Staff("Jane", "jane@example.com", "staff1", "password1", "Receptionist"));
        staffList.add(new Staff("John", "john@example.com", "staff2", "password2", "Assistant"));
    }

    public boolean loginStaff(String username, String password) {
        for (Staff staff : staffList) {
            if (staff.getUserName().equals(username) && staff.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // // Method to add staff to the list
    public void addStaff(Staff staff) {
        staffList.add(staff);
    }
}
