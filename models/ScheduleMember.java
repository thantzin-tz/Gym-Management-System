package models;

import java.util.Objects;

public class ScheduleMember {

	private int scheduleMember_id;
	private Schedule schedule;
	private Members member;
	private String scheduleJoinDate;
	private String expireDate;
	private int dayLeft;

	public String getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public ScheduleMember() {

	}

	public ScheduleMember(int scheduleMember_id, Schedule schedule, Members member, String scheduleJoinDate,
			String expireDate, int dayLeft) {
		this.scheduleMember_id = scheduleMember_id;
		this.schedule = schedule;
		this.member = member;
		this.scheduleJoinDate = scheduleJoinDate;
		this.expireDate = expireDate;
		this.dayLeft = dayLeft;

	}

	public int getScheduleMember_id() {
		return scheduleMember_id;
	}

	public void setScheduleMember_id(int scheduleMember_id) {
		this.scheduleMember_id = scheduleMember_id;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}

	public String getScheduleJoinDate() {
		return scheduleJoinDate;
	}

	public void setScheduleJoinDate(String scheduleJoinDate) {
		this.scheduleJoinDate = scheduleJoinDate;
	}

	public int getDayLeft() {
		return dayLeft;
	}

	public void setDayLeft(int dayLeft) {
		this.dayLeft = dayLeft;
	}

	///// Wakaranai Coding
	public Schedule orElseGet(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ScheduleMember that = (ScheduleMember) o;
		if (schedule == that.schedule)
			return Objects.equals(schedule, that.schedule);
		else
			return Objects.equals(member, that.member);

	}

	@Override
	public int hashCode() {
		if (schedule != null)
			return Objects.hash(schedule);
		else
			return Objects.hash(member);
	}

	@Override
	public String toString() {
		return "ScheduleMember [scheduleMember_id=" + scheduleMember_id + ", schedule=" + schedule + ", member="
				+ member + ", scheduleJoinDate=" + scheduleJoinDate + ", expireDate=" + expireDate + ", dayLeft="
				+ dayLeft + "]";
	}
	
	

}
