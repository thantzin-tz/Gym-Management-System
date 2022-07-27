package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import config.DBConfig;
import models.Equipment;
import shared.mapper.EquipmentMapper;

public class EquipmentService {
	private final DBConfig dbConfig;
	private final EquipmentMapper equipmentMapper;
	public EquipmentService() {
		this.dbConfig = new DBConfig();
		this.equipmentMapper = new EquipmentMapper();
		new StaffService();
	}

	public void saveEquipment(Equipment equipment) {
		try {
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"Insert into equipment(equip_name,equip_condition,last_maintainDate,next_maintainDate,staff_id)values(?,?,?,?,?)");
			ps.setString(1, equipment.getEquip_name());
			ps.setString(2, equipment.getEquip_condition());
			ps.setString(3, equipment.getLast_maintainDate());
			ps.setString(4, equipment.getNext_maintainDate());
			ps.setInt(5, equipment.getStaff().getStaff_id());
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(null, "Already Exists");
			e.printStackTrace();
		}
	}

	public Equipment findEquipmentById(String id) {
		Equipment equipment = new Equipment();

		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM equipment inner join staff on equipment.staff_id=staff.staff_id WHERE equipment_id = "
					+ id + ";";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				this.equipmentMapper.mapToEquipment(equipment, rs);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return equipment;
	}

	public List<Equipment> findAllEquipment() {

		List<Equipment> equipmentList = new ArrayList<>();
		try (Statement st = this.dbConfig.getConnection().createStatement()) {

			String query = "SELECT * FROM equipment inner join staff on equipment.staff_id=staff.staff_id";

			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				Equipment equipment = new Equipment();
				equipmentList.add(this.equipmentMapper.mapToEquipment(equipment, rs));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return equipmentList;

	}

	public void updateEquipment(String id, Equipment equipment) {
		try {
			System.out.println("ID ---------------> " + id);
			PreparedStatement ps = this.dbConfig.getConnection().prepareStatement(
					"UPDATE equipment SET equip_name=?,equip_condition=?,last_maintainDate=?, next_maintainDate=?, staff_id=? Where equipment_id=?");

			ps.setString(1, equipment.getEquip_name());
			ps.setString(2, equipment.getEquip_condition());
			ps.setString(3, equipment.getLast_maintainDate());
			ps.setString(4, equipment.getNext_maintainDate());
			ps.setInt(5, equipment.getStaff().getStaff_id());
			ps.setString(6, id);

			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Please select a Equipment");
			e.printStackTrace();
		}

	}

	public void deleteEquipment(String id, Equipment equipment) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement ps = this.dbConfig.getConnection()
					.prepareStatement("Delete from equipment Where equipment_id =?");
			ps.setString(1, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int countEquipment() {
		int count = 0;
		try (Statement st = this.dbConfig.getConnection().createStatement()) {
			String query = "Select count(*) as count from equipment";
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
