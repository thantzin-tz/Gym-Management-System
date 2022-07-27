package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import config.DBConfig;
import models.Staff;
import shared.mapper.StaffMapper;

public class StaffService {

	private final DBConfig dbConfig;
	private final StaffMapper staffMapper;

	public StaffService() {
		this.dbConfig = new DBConfig();
		this.staffMapper = new StaffMapper();
	}

	public void saveStaff(Staff staff)  throws SQLIntegrityConstraintViolationException{
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"Insert into staff(name,age,phone,address,email,gender,role,username,password,salary,active)values(?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, staff.getName());
			ps.setInt(2, staff.getAge());
			ps.setString(3, staff.getPhone());
			ps.setString(4, staff.getAddress());
			ps.setString(5, staff.getEmail());
			ps.setString(6, staff.getGender());
			ps.setString(7, String.valueOf(staff.getRole()));
			ps.setString(8, staff.getUsername());
			ps.setString(9, staff.getPassword());
			ps.setDouble(10, staff.getSalary());
			ps.setBoolean(11, true);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Already Exists");
			return;
		}
	}

	public Staff findStaffById(String id) {
		Staff staff = new Staff();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM staff WHERE staff_id = " + id + ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				this.staffMapper.mapToStaff(staff, rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return staff;
	}

	public List<Staff> findAllStaffs() {

		List<Staff> staffList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM staff";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Staff staff = new Staff();
				staffList.add(this.staffMapper.mapToStaff(staff, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return staffList;

	}

	public void updateStaff(String id, Staff staff) throws SQLIntegrityConstraintViolationException{
		try {
			System.out.println("ID ---------------> " + id);
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"UPDATE staff SET name=?, age=?, phone=?, address=?, email=?, gender=?,username=?,password=?, salary=?, role =? WHERE staff_id =?");
			ps.setString(1, staff.getName());
			ps.setInt(2, staff.getAge());
			ps.setString(3, staff.getPhone());
			ps.setString(4, staff.getAddress());
			ps.setString(5, staff.getEmail());
			ps.setString(6, staff.getGender());
			ps.setString(7, staff.getUsername());
			ps.setString(8, staff.getPassword());
			ps.setDouble(9, staff.getSalary());
			ps.setString(10, String.valueOf(staff.getRole()));
			ps.setString(11, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Username Already Exists");
//			e.printStackTrace();
		}

	}

	public void deleteStaff(String id, Staff staff) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from staff Where staff_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//// Resign
	public void retireStaff(String id, Staff staff) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE staff SET username=?, password=?, active=? WHERE staff_id=?");
			ps.setString(1, staff.getUsername());
			ps.setString(2, staff.getPassword());
			ps.setBoolean(3, false);
			ps.setString(4, id);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

////Restore
	public void restoreStaff(String id, Staff staff) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("UPDATE staff SET active=? WHERE staff_id=?");
			ps.setBoolean(1, true);
			ps.setString(2, id);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Staff> findAllStaffsByRole(String role) {

		List<Staff> staffList = new ArrayList<>();
		String query = "SELECT * FROM staff WHERE role=?";
		try (PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(query)) {
			ps.setString(1, role);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Staff staff = new Staff();
				staffList.add(this.staffMapper.mapToStaff(staff, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return staffList;

	}

	public List<Staff> findComboTrainer() {

		List<Staff> staffList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			///// role = Trainer and status is Active //////
			String query = "SELECT * FROM staff where staff.role='trainer' and active=1";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Staff staff = new Staff();
				staffList.add(this.staffMapper.mapToStaff(staff, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return staffList;
	}

	public List<Staff> findComboAdmin() {

		List<Staff> staffList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			///// role = Trainer and status is Active //////
			String query = "SELECT * FROM staff where staff.role='Admin' and active=1";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Staff staff = new Staff();
				staffList.add(this.staffMapper.mapToStaff(staff, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return staffList;
	}

}
