package models;

import java.util.ArrayList;
import java.util.List;

public class Dentistmodel {

    private List<Dentist> dentistList;

    public Dentistmodel() {
        dentistList = new ArrayList<>();
        // Initialize with some sample dentists
        dentistList.add(new Dentist("Dr. Alice", "alice@example.com", "dentist1", "password1"));
        dentistList.add(new Dentist("Dr. Bob", "bob@example.com", "dentist2", "password2"));
    }

    public boolean loginDentist(String username, String password) {
        for (Dentist dentist : dentistList) {
            if (dentist.getUserName().equals(username) && dentist.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Method to add a dentist to the list
    public void addDentist(Dentist dentist) {
        dentistList.add(dentist);
    }
}
