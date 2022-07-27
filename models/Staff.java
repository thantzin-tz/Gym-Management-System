package models;

public class Staff extends PersonInfo{
	private double salary;
	private String username;
	private String password;
	private UserRole role;
	private boolean active;
	public Staff() {
		super();
	}
	public Staff(double salary, String username, String password, UserRole role, boolean active) {
		super();
		this.salary = salary;
		this.username = username;
		this.password = password;
		this.role = role;
		this.active = active;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
