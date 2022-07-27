package shared.mapper;

import java.sql.ResultSet;

import models.Equipment;

import models.Staff;

public class EquipmentMapper {
	public Equipment mapToEquipment(Equipment equipment, ResultSet rs) {
        try {
        	
        	equipment.setEquipment_id(rs.getInt("equipment_id"));
        	equipment.setEquip_name(rs.getString("equip_name"));
        	equipment.setEquip_condition(rs.getString("equip_condition"));
        	equipment.setLast_maintainDate(rs.getString("last_maintainDate"));
        	equipment.setNext_maintainDate(rs.getString("next_maintainDate"));
        	
        	Staff staff=new Staff();
        	staff.setStaff_id(rs.getInt("staff_id"));
        	staff.setName(rs.getString("name"));
        
        	
        	
        	equipment.setStaff(staff);
        	
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipment;
}

}
