package models;

import java.util.Objects;

public class Schedule {

	private int schedule_id;
	private String schedule_name;
	private String schedule_type;
	private String time;
	private String typeOfBody;
	private String gender;
	private Double amount;
	private int availabeMember;

	private Staff staff;
	private Schedule schedule;

	public Schedule() {

	}

	public Schedule(int schedule_id, String schedule_name, String schedule_type, String time, String typeOfBody,
			String gender, Staff staff, Double amount, int availableMember)

	{
		this.schedule_id = schedule_id;
		this.schedule_name = schedule_name;
		this.schedule_type = schedule_type;
		this.time = time;
		this.typeOfBody = typeOfBody;
		this.gender = gender;
		this.staff = staff;
		this.amount = amount;
		this.availabeMember = availableMember;

	}

	public String getSchedule_type() {
		return schedule_type;
	}

	public void setSchedule_type(String schedule_type) {
		this.schedule_type = schedule_type;
	}

	public int getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(int schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getSchedule_name() {
		return schedule_name;
	}

	public void setSchedule_name(String schedule_name) {
		this.schedule_name = schedule_name;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTypeOfBody() {
		return typeOfBody;
	}

	public void setTypeOfBody(String typeOfBody) {
		this.typeOfBody = typeOfBody;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double i) {
		this.amount = i;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public int getAvailabeMember() {
		return availabeMember;
	}

	public void setAvailabeMember(int availabeMember) {
		this.availabeMember = availabeMember;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Schedule schedule = (Schedule) o;
		return Objects.equals(schedule_name, schedule.schedule_name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(schedule);
	}

	@Override
	public String toString() {
		return "Schedule [schedule_id=" + schedule_id + ", schedule_name=" + schedule_name + ", schedule_type="
				+ schedule_type + ", time=" + time + ", typeOfBody=" + typeOfBody + ", gender=" + gender + ", amount="
				+ amount + ", availabeMember=" + availabeMember + ", staff=" + staff + ", schedule=" + schedule + "]";
	}
	
	

}
