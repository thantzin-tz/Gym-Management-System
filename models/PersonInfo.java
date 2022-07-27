package models;

public class PersonInfo {

	private int staff_id;
	private String name;
	private int age;
	private String phone;
	private String address;
	private String email;
	private String gender;
	public PersonInfo() {

	}
	public PersonInfo(int staff_id, String name, int age, String phone, String address, String email, String gender) {
		this.staff_id = staff_id;
		this.name = name;
		this.age = age;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.gender = gender;
	}
	public int getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(int staff_id) {
		this.staff_id = staff_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
