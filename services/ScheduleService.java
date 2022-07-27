package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import config.DBConfig;
import models.Schedule;
import shared.mapper.ScheduleMapper;

public class ScheduleService {
	private final DBConfig dbConfig;
	private final ScheduleMapper scheduleMapper;

	public ScheduleService() {
		this.dbConfig = new DBConfig();
		this.scheduleMapper = new ScheduleMapper();
	}

	public void saveSchedule(Schedule schedule) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"Insert into schedule(schedule_name,schedule_type,time,typeOfBody,gender,staff_id,fees,availableMember)values(?,?,?,?,?,?,?,?)");
			ps.setString(1, schedule.getSchedule_name());
			ps.setString(2, schedule.getSchedule_type());
			ps.setString(3, schedule.getTime());
			ps.setString(4, schedule.getTypeOfBody());
			ps.setString(5, schedule.getGender());
			ps.setInt(6, schedule.getStaff().getStaff_id());
			ps.setDouble(7, schedule.getAmount());
			ps.setInt(8, schedule.getAvailabeMember());
			System.out.print(schedule.getStaff().getStaff_id());
			ps.executeUpdate();
			System.out.print(schedule.getStaff().getStaff_id());
			ps.close();

		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(null, "Already Exists");
			e.printStackTrace();
		}
	}

	public Schedule findScheduleById(String id) {
		Schedule schedule = new Schedule();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM schedule inner join staff on schedule.staff_id=staff.staff_id WHERE schedule_id = "
					+ id + ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				this.scheduleMapper.mapToSchedule(schedule, rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return schedule;
	}

	public List<Schedule> findAllSchedule() {

		List<Schedule> scheduleList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM gym.schedule inner join staff on schedule.staff_id=staff.staff_id";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Schedule schedule = new Schedule();
				scheduleList.add(this.scheduleMapper.mapToSchedule(schedule, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleList;

	}

	public void updateSchedule(String id, Schedule schedule) {
		try {
			System.out.println("ID ---------------> " + id);
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"UPDATE schedule SET schedule_name=?,schedule_type=?, time=?, typeOfBody=?,gender=?, staff_id=?,fees=?,availableMember=? Where schedule_id=?");

			ps.setString(1, schedule.getSchedule_name());
			ps.setString(2, schedule.getSchedule_type());
			ps.setString(3, schedule.getTime());
			ps.setString(4, schedule.getTypeOfBody());
			ps.setString(5, schedule.getGender());
			ps.setInt(6, schedule.getStaff().getStaff_id());
			ps.setDouble(7, schedule.getAmount());
			ps.setInt(8, schedule.getAvailabeMember());
			ps.setString(9, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Schedule");
			e.printStackTrace();
		}

	}

	public void deleteSchedule(String id, Schedule schedule) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from schedule Where schedule_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateAvailableMember(String id, Schedule schedule) {
		try {
			System.out.println("ID ---------------> " + id);
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE schedule SET availableMember=? Where schedule_id=?");

			ps.setInt(1, schedule.getAvailabeMember());
			ps.setString(2, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Schedule");
			e.printStackTrace();
		}

	}

	public Schedule findAvailableMemberById(int id) {
		Schedule schedule = new Schedule();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT availableMember FROM schedule WHERE schedule_id = " + id;

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				this.scheduleMapper.mapToAvailableMember(schedule, rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return schedule;
	}

	public int countAvailableSchedule() {
		int count = 0;
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "Select count(*) as count from schedule Where availableMember >= 1";
			ResultSet rs = st.executeQuery(query);

			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

}
