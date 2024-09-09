package models;

public abstract class User {
	private String name;
	private String contactDetails;
	private String username;
	private String password;
	
	public User(String name, String contactDetails, String username, String password) {
		this.name = name;
		this.contactDetails = contactDetails;
		this.username = username;
		this.password = password;
	}
	
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	public String getContactDetails() { return contactDetails; }
	public void setContactDetails(String contactDetails) { this.contactDetails = contactDetails; }
	
	public String getUserName() { return username; }
	public void setuserName(String username) { this.username = username; }
	
	public String getPassword() { return password; }
	public void setpassword(String password) { this.password = password; }
	
	public abstract void printUserInfo();
}
