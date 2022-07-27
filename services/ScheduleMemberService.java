package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import config.DBConfig;
import models.ScheduleMember;
import shared.mapper.ScheduleMemberMapper;

public class ScheduleMemberService {
	private final DBConfig dbConfig;
	private final ScheduleMemberMapper scheduleMemberMapper;

	public ScheduleMemberService() {
		this.dbConfig = new DBConfig();
		this.scheduleMemberMapper = new ScheduleMemberMapper();
		new ScheduleService();
		new MemberService();
	}

	public void saveScheduleMember(ScheduleMember scheduleMember) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"Insert into scheduledMember(schedule_id,member_id,joinDate, expireDate, dayLeft)values(?,?,?,?,?)");
			ps.setInt(1, scheduleMember.getSchedule().getSchedule_id());
			ps.setInt(2, scheduleMember.getMember().getMember_id());
			ps.setString(3, scheduleMember.getScheduleJoinDate());
			ps.setString(4, scheduleMember.getExpireDate());
			ps.setInt(5, scheduleMember.getDayLeft());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(null, "Already Exists");
			e.printStackTrace();
		}
	}

	public void updateScheduleMember(String id, ScheduleMember scheduleMember) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"UPDATE scheduledMember SET schedule_id=?,member_id=?,joinDate=?, expireDate=? Where scheduledMember_id=?");

			ps.setInt(1, scheduleMember.getSchedule().getSchedule_id());
			ps.setInt(2, scheduleMember.getMember().getMember_id());
			ps.setString(3, scheduleMember.getScheduleJoinDate());
			ps.setString(4, scheduleMember.getExpireDate());
			ps.setString(5, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Schedule Member");
			e.printStackTrace();
		}

	}

	public List<ScheduleMember> findAllScheduleMembers() {

		List<ScheduleMember> scheduleMemberList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			// String query = "SELECT * FROM workoutPlan";
			String query = "SELECT * FROM scheduledmember\n" + "INNER JOIN schedule\n"
					+ "ON schedule.schedule_id = scheduledmember.schedule_id\n " + "INNER JOIN member\n"
					+ "ON member.member_id = scheduledmember.member_id\n " + "order by scheduledMember_id";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {

				ScheduleMember scheduleMember = new ScheduleMember();
				scheduleMemberList.add(this.scheduleMemberMapper.mapToSchedule(scheduleMember, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleMemberList;
	}

	public ScheduleMember findById(String scheduleMemberId) {
		ScheduleMember scheduleMember = new ScheduleMember();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM scheduledmember\n" + "INNER JOIN schedule\n"
					+ "ON schedule.schedule_id = scheduledmember.schedule_id\n " + "INNER JOIN member\n"
					+ "ON member.member_id = scheduledmember.member_id\n " + "WHERE scheduledMember_id = "
					+ scheduleMemberId + ";";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				scheduleMember = this.scheduleMemberMapper.mapToSchedule(scheduleMember, rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleMember;
	}

	public void deleteScheduleMember(String id, ScheduleMember scheduleMember) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from scheduledmember Where scheduledMember_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<ScheduleMember> findAllScheduleMember() {
		List<ScheduleMember> scheduleMemberList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			// String query = "SELECT * FROM workoutPlan";
			String query = "SELECT * FROM scheduledmember\n" + "INNER JOIN schedule\n"
					+ "ON schedule.schedule_id = scheduledmember.schedule_id\n " + "INNER JOIN member\n"
					+ "ON member.member_id = scheduledmember.member_id\n " + "order by scheduledMember_id";
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {

				ScheduleMember scheduleMember = new ScheduleMember();
				scheduleMemberList.add(this.scheduleMemberMapper.mapToSchedule(scheduleMember, rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scheduleMemberList;
	}

	public void updateScheduleMemberDayLeft(String id, ScheduleMember scheduleMember) {
		try {

			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE scheduledMember SET dayLeft=? Where scheduledMember_id=?");
			ps.setInt(1, scheduleMember.getDayLeft());
			ps.setString(2, id);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Schedule Member");
			e.printStackTrace();
		}

	}

	public void deleteScheduleMemberExipired(ScheduleMember scheduleMember) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from scheduledmember Where dayLeft<1");
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int countExpireMember() {
		int count = 0;
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "Select count(*) as count from scheduledmember Where dayLeft<=5";
			ResultSet rs = st.executeQuery(query);
			
			if(rs.next()) {
				count = rs.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
