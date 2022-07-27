package models;

public class Members {

	private int member_id;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String gender;
	private String dateOfJoin;
	private int age;
	private double weight;
	private double height;
	private double bmi_result;
	
	public Members() {
	}
	public Members(int member_id, String name, String phone, String address, String email, String gender,
			String dateOfJoin, int age, double weight, double height, double bmi_result) {
		this.member_id = member_id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.dateOfJoin = dateOfJoin;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.bmi_result = bmi_result;
	}
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDateOfJoin() {
		return dateOfJoin;
	}
	public void setDateOfJoin(String dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getBmi_result() {
		return bmi_result;
	}
	public void setBmi_result(double bmi_result) {
		this.bmi_result = bmi_result;
	}
	
	
}
